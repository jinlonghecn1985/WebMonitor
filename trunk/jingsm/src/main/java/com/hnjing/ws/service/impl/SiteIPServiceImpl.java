/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteIPServiceImpl.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.ws.service.impl
 * @Description: IP检测服务类实现类
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月28日 上午11:17:13
 * @version: V1.0  
 */
package com.hnjing.ws.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjing.ws.model.entity.SiteUrl;
import com.hnjing.ws.service.DictionaryService;
import com.hnjing.ws.service.SelfIpService;
import com.hnjing.ws.service.SiteIPService;
import com.hnjing.ws.service.SiteUrlService;
import com.hnjing.ws.service.impl.util.DeminIPUtil;
import com.hnjing.ws.service.impl.util.LoadDeminIPThread;

/**
 * @ClassName: SiteIPServiceImpl
 * @Description: IP检测服务类实现类
 * @author: Jinlong He
 * @date: 2018年12月28日 上午11:17:13s
 */
@Service("siteIPService")
public class SiteIPServiceImpl implements SiteIPService {
	private static final Logger logger = LoggerFactory.getLogger(SiteIPServiceImpl.class);
	
	private int ping_thread_count_now = 3;//当前运行PING线程　
	private boolean is_ping_thread_running = false; //域名监测线程是否运行中
	
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private SiteUrlService siteUrlService;
	
	@Autowired
	private SelfIpService selfIpService;
	
	
	
	/*
	 * @Title: checkAllSiteIP
	 * @Description: 检测全量IP域名
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteIPService#checkAllSiteIP()
	 */
	@Override
	public Object checkAllSiteIP(Integer source) {
		if (is_ping_thread_running) {
			return "域名IP检测线程正在运行，请耐心等待！";
		}
		// 全量检测
		Map<String, Object> queryIP = new HashMap<String, Object>();
		queryIP.put("ipChecked", 1);
		if(source!=null) {
			queryIP.put("source", source);
		}
		Integer waitCheckIpCount = siteUrlService.querySiteUrlCount(queryIP); // 待查IP
		if (waitCheckIpCount == null || waitCheckIpCount.intValue() == 0) {
			//上回检测完成
			siteUrlService.initSiteUrlIPStatus(1); // 初始化待检测IP     全部设置为非我司IP，待检测
		}
		// 查询待检测IP的域名		
		List<SiteUrl> urlList = siteUrlService.querySiteUrlByProperty(queryIP);
		if(urlList==null || urlList.size()==0) {
			return "没有待检测IP的域名信息，请核查!";
		}
		return checkSiteIP(urlList);
	}

	/*
	 * @Title: checkNullSiteIP
	 * @Description: 复检空IP域名
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteIPService#checkNullSiteIP()
	 */
	@Override
	public Object checkNullSiteIP() {
		if (is_ping_thread_running) {
			return "域名IP检测线程正在运行，请耐心等待！";
		}
		// NULLIP检测
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("ipnull", 1);
		List<SiteUrl> urlList = siteUrlService.querySiteUrlByProperty(query);
		if(urlList==null || urlList.size()==0) {
			return "没有空IP的域名信息，请核查!";
		}
		return checkSiteIP(urlList);
	}
	

	/*
	 * @Title: statisticSiteIPInfo
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteIPService#statisticSiteIPInfo()
	 */ 
	@Override
	public Integer statisticSiteIPInfo() {
		int ret = siteUrlService.initSelfSite();   //判断网站是否我司		
		selfIpService.initIPCount(); //计算我司IP数据分布情况
		
		//start特殊部分　处理平台IP检测完的事项-确认非在我司托管网站不检测
		siteUrlService.initNeedCheckZero(1); //重置平台待检测状态
		siteUrlService.initNeedCheckPt();//按IP设定我司待检测数据
		//end
		return ret;
	}
	
	/*
	 * @Title: recheckErrorSiteIP
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteMonitorService#recheckErrorSiteIP()
	 */ 
	@Override	
	public Integer recheckErrorSiteIP(Integer source) {
		List<SiteUrl> reIPList = siteUrlService.queryReCheckIPSite();
		if(reIPList!=null && reIPList.size()>0) {			
			for(SiteUrl surl : reIPList) {
				String ip = DeminIPUtil.cmdPingIP(surl.getDemin(), surl.getIp()); //获取新IP
				if( ((ip==null || ip.length()==0) && (surl.getIp()==null || surl.getIp().length()==0)) ||  (ip != null  && surl.getIp()!=null && ip.equals(surl.getIp()))) {
					//没有变化时
				}else {
					//前后两次IP不相同时	
					SiteUrl su = new SiteUrl();
					su.setId(surl.getId());
					su.setOldIp(surl.getIp()); // 新旧替换
					su.setIp(ip); //设置新IP	
					su.setDt(new Date());
					su.setSelfSite(selfIpService.isIpMyCompany(ip)==true?1:0); //是否我司托管网站
					siteUrlService.modifySiteUrlIP(su);
				}
			}
			return reIPList.size();
		}
		return 0;
	}

	
	
	/** 
	* @Title: checkSiteIP 
	* @Description: 根据待检数量规划线程量
	* @param urlList
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	private Object checkSiteIP(List<SiteUrl> urlList) {
		DeminIPUtil.flushDNS(); // 刷新本地DNS缓存
		// 根据数据量进行操作
		is_ping_thread_running = true; // 设置运行
		logger.info("*************域名IP检测开始，待检测域名"+ urlList.size());
		if (urlList.size() > Integer.parseInt(dictionaryService.queryParamsValue(5))) {
			// 量大时，采用多线程处理
			int threadCount = Integer.parseInt(dictionaryService.queryParamsValue(6));
			ping_thread_count_now = threadCount; // 最多线程
			int step = urlList.size() / threadCount; // 计算每个线程处理数据量
			for (int k = 0; k < threadCount; k++) {
				int toL = urlList.size();// 最后一个线程处理的终点
				if (k + 1 < threadCount) {
					toL = step * (k + 1); // 非最后一个线程的结束点
				}
				new LoadDeminIPThread(this, urlList.subList(step * k, toL), k + 1).start();
			}
		} else {
			ping_thread_count_now = 1;
			new LoadDeminIPThread(this, urlList, 1).start();
		}
		return "待检测IP的域名数量:" + urlList.size() + " 预计处理时长(分钟):"
				+ (calculationTime(urlList.size(), ping_thread_count_now));
	}
	
	
	/** 
	* @Title: checkWebIPThreadCallback 
	* @Description: IP获取线程结束后回调方法  
	* void    返回类型 
	* @throws 判断IP是否我司
	*/
	public void checkWebIPThreadCallback() {
		if(ping_thread_count_now>1) {
			ping_thread_count_now--;
			return ;
		}
		is_ping_thread_running=false; //PING线程结束
		siteUrlService.initSiteUrlIPStatus(0);//重新设置待检测标志位-检测完
		
		statisticSiteIPInfo();
		//处理线程结束后的事情
		logger.info("**********域名IP检测结束");
	}
	
	/** 
	* @Title: modifySiteUrlIPSOnBatch 
	* @Description: 执行批量数据写入
	* @param batchIPList
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	public Integer modifySiteUrlIPSOnBatch(List<SiteUrl> batchIPList) {
		return siteUrlService.modifySiteUrlIPSOnBatch(batchIPList);
	}

	
	/** 
	* @Title: calculationTime 
	* @Description: 计算预计时长
	* @param totalCount
	* @param threadNum
	* @return  
	* int    返回类型 
	* @throws 
	*/
	private int calculationTime(int totalCount, int threadNum) {
		if(totalCount<50) {
			return 5;
		}
		return totalCount*7/(9*60*threadNum);
	}

}
