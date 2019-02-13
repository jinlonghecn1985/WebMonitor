/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteAccessServiceImpl.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.ws.service.impl
 * @Description: 网站访问状态检测类
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年11月17日 下午5:11:40
 * @version: V1.0  
 */
package com.hnjing.ws.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjing.dpc.service.bo.SiteBo;
import com.hnjing.ws.model.entity.SiteResult;
import com.hnjing.ws.model.entity.SiteStatistics;
import com.hnjing.ws.model.entity.SiteUrl;
import com.hnjing.ws.service.DictionaryService;
import com.hnjing.ws.service.SelfIpService;
import com.hnjing.ws.service.SiteAccessService;
import com.hnjing.ws.service.SiteHistoryService;
import com.hnjing.ws.service.SiteResultService;
import com.hnjing.ws.service.SiteStatisticsService;
import com.hnjing.ws.service.SiteUrlService;
import com.hnjing.ws.service.impl.util.DeminIPUtil;
import com.hnjing.ws.service.impl.util.HttpToolUtil;
import com.hnjing.ws.service.impl.util.LoadDeminAccessThread;
import com.hnjing.ws.service.impl.util.SiteCheckUtil;

/**
 * @ClassName: SiteAccessServiceImpl
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年11月17日 下午5:11:40
 */
@Service("siteAccessService")
public class SiteAccessServiceImpl implements SiteAccessService {
	private static final Logger logger = LoggerFactory.getLogger(SiteAccessServiceImpl.class);
	
	private int web_thread_count_now = 3;//当前运行域名检测线程　　
	private boolean is_web_thread_running = false; //域名监测线程是否运行中	
		
	@Autowired
	private SiteResultService siteResultService;
	
	@Autowired
	private SiteStatisticsService siteStatisticsService;
	
	@Autowired
	private SiteHistoryService siteHistoryService;
//	
//	@Autowired
//	private DPSService dpsService;
	
	@Autowired
	private SiteUrlService siteUrlService;	

	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private SelfIpService selfIpService;
	
	
/******************************************************* 网站检测部分开始  ****************************************************/
	/*
	 * @Title: checkSiteStatus
	 * @Description: 网站内容检测
	 * @param @param source 待检测源
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param source
	 * @return
	 * @see com.hnjing.ws.service.SiteMonitorService#checkSiteStatus(java.lang.Integer)
	 */ 
	public Object checkSiteStatus(Integer source) {
		if(is_web_thread_running) {
			return "检测正在进行中，请耐心等待。";
		}		
		logger.info("***********　域名访问状态检测开始");
		//检测是否有未完成的检测
		SiteStatistics ss = siteStatisticsService.queryNeedContinueCheckInfo();
		if(ss==null || ss.getId()==null) {
			//移除记录clearTodayBeforeHistroy
			int hisc = siteResultService.clearTodayBeforeHistroy(source); //清空检测记录
			logger.info("****准备工作，移除之前历史记录量："+hisc);
		}
		
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("source", source);
//		if(source!=null && (source==1 || source==2 || source==3)) {
			query.put("needCheck", 0); //只检测待检数据
//		}		
		
		List<SiteUrl> urlList = siteUrlService.queryWaitCheckSiteUrlByProperty(query);		//余下待检测
		if(urlList!=null && urlList.size()>0) {
			initSiteCheckThreadProcess(source, urlList, true, ss);
			return "检测启动，共计待查网站"+urlList.size()+"预计时长(分钟，共"+web_thread_count_now+"线程):"+(calculateTotalCostMinute(urlList.size(), web_thread_count_now))+"。检测完成并发现疑似异常时系统会自动发送报告给相关人员。";
		}else {
			return "网站检测任务已结束，感谢！";
		}
	}
	

	/**
	 * @param statistics  
	* @Title: initMutiThreadProcess 
	* @Description: 处理待检测数据及线程 
	* @param urlList  
	* void    返回类型 
	* @throws 
	*/
	protected void initSiteCheckThreadProcess(Integer source, List<SiteUrl> urlList, boolean insert, SiteStatistics statistics) {	
		is_web_thread_running = true;
		//初始化检测数据
		if(insert && (statistics==null || statistics.getId()==null)) {			
			Map<String, Object> queryIP = new HashMap<String, Object>();
			queryIP.put("source", source);
			Integer waitCheckSiteCount = siteUrlService.querySiteUrlCount(queryIP); //待查网站
			SiteStatistics ss = new SiteStatistics();
			ss.setSource(source);
			ss.setSiteTotal(waitCheckSiteCount);
			ss.setSiteCheck(urlList.size());
			siteStatisticsService.addSiteStatistics(ss);	
			statistics = ss;
		}
		//准备线程检测
		if(urlList.size()>Integer.parseInt(dictionaryService.queryParamsValue(7))) {
			int threadNum = Integer.parseInt(dictionaryService.queryParamsValue(8));
			web_thread_count_now = threadNum; //启动线程数重
			int step = urlList.size()/threadNum;
			for(int k=0; k<threadNum; k++) {
				int toL = urlList.size();
				if(k+1<threadNum) {
					toL = step*(k+1);
				}
				//注意，线程内严禁对数组进行增＼删操作
				(new LoadDeminAccessThread(this, k+1, urlList.subList(step*k, toL), insert, statistics)).start();
			}
		}else if(urlList.size()>0){
			web_thread_count_now = 1;
			(new LoadDeminAccessThread(this, 1, urlList, insert, statistics)).start();	  
		}
	}
	
	/** 
	* @Title: checkSiteThreadCallBack 
	* @Description: 网站检测回调k
	* @param threadCode  
	* void    返回类型 
	* @throws 
	*/
	public void checkSiteThreadCallBack(SiteStatistics statistics, int threadCode) {
		if(web_thread_count_now!=1) {
			//还有线程在运行时，不作特别处理
			web_thread_count_now--;
			return;
		}
		//异常网站重检IP --防止IP更新不及时
//		recheckErrorSiteIP();
		reCheckErrorResult(statistics.getSource()); //重检测异常网站		
		if (statistics != null && statistics.getId()!=null && statistics.getId().length() > 0) {
			//SiteStatistics ss = siteStatisticsService.querySiteStatisticsById(statistics.getId());
			// 确定告警数据
			processWarnSite(statistics);

			overCheckStatistics(statistics);

			siteHistoryService.bakCheckDate(statistics.getSource()); //记录异常数据　网站打开慢、安全级别稍高			
//			dpsService.processErrorMail(ss.getSource()); //发送邮件			
		}else {
			logger.info("SSID IS NULL"+statistics.getId());
		}
		
		
		
		is_web_thread_running = false; //结束
		//处理线程结束后的事情
		logger.info("************************************************************************域名访问状态检测结束");
	}
	
	private void overCheckStatistics(SiteStatistics statistics) {
		// 访问异常
		Map<String, Object> queryIP = new HashMap<String, Object>();
		queryIP.put("nostatus", 200);
		queryIP.put("source", statistics.getSource());
		Integer c = siteResultService.querySiteResultCountByProperty(queryIP);
		statistics.setSiteError(c == null ? 0 : c);
		// 加载慢
		queryIP.clear();
		queryIP.put("comment", 3);
		queryIP.put("source", statistics.getSource());
		c = siteResultService.querySiteResultCountByProperty(queryIP);
		statistics.setSiteSlow(c == null ? 0 : c);
		
		// 待告警
		queryIP.clear();
		queryIP.put("comment", 9);
		queryIP.put("source", statistics.getSource());
		c = siteResultService.querySiteResultCountByProperty(queryIP);
		statistics.setSiteWarn(c == null ? 0 : c);
		siteStatisticsService.modifySiteStatistics(statistics);
	}
	
	
	/** 
	* @Title: addSiteResultOnBatch 
	* @Description: 批量添加
	* @param rslist
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	public Integer addSiteResultOnBatch(List<SiteResult> rslist) {
		return siteResultService.addSiteResultOnBatch(rslist);
	}
	
	/** 
	* @Title: modifySiteResult 
	* @Description: 修改检测结果
	* @param result
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	public Integer modifySiteResult(SiteResult result) {
		return siteResultService.modifySiteResult(result);
	}
	
	/** 
	* @Title: processWarnSite 
	* @Description: 处理检测结果-分析出哪些应该告警
	* @param ssid  
	* void    返回类型 
	* @throws 
	*/
	private void processWarnSite(SiteStatistics ss) {		
		if(ss!=null && ss.getSource()!=null) {
			//source=1
			if(ss.getSource().intValue()==1) {
				//平台告警
				List<Map<String, Object>> errorList = siteResultService.queryErrorSiteSelf();
				if(errorList!=null && errorList.size()>0) {
					String whiteTitle = dictionaryService.queryParamsValue(2);
					if(whiteTitle!=null && whiteTitle.length()>0) {
						whiteTitle = whiteTitle.replace("，", ",");
						String wt[] = whiteTitle.split(",");
						for(Map<String, Object> error: errorList) {
							if(error.containsKey("title") && error.get("title")!=null && ((String)error.get("title")).length()>0) {
								boolean f = true; //是否告警
								for(String w : wt) {
									if(error.get("title").equals(w)) {
										f = false;										
										continue;
									}
								}
								if(f) {
									//不包括
									SiteResult sr = new SiteResult();
									sr.setSiteId((Integer)error.get("id"));
									sr.setComment(9);
									siteResultService.modifySiteResult(sr);
								}
							}else {
								//title为空
								SiteResult sr = new SiteResult();
								sr.setSiteId((Integer)error.get("id"));
								sr.setComment(9);
								siteResultService.modifySiteResult(sr);
							}
						}						
					}
				}
				return;
			}
			// end source=1	
			
			//start source=2
			if(ss.getSource().intValue()==2 || ss.getSource().intValue()==3) {
				//查询我司网站，设置为告警数据
				List<Map<String, Object>> errorList = siteResultService.queryErrorSiteSelf();
				if(errorList!=null && errorList.size()>0) {
					for(Map<String, Object> error: errorList) {
						SiteResult sr = new SiteResult();
						sr.setSiteId((Integer)error.get("id"));
						sr.setComment(9);
						siteResultService.modifySiteResult(sr);
					}
				}	
				return ;
			}
			//end source=2
			
		}
	}	
	/******************************************************* 网站检测部分结束  ****************************************************/
	

	
	


	/** 
	* @Title: isIpMyCompany 
	* @Description:
	* @param ip
	* @return  
	* boolean    返回类型 
	* @throws 
	*/
	public boolean isIpMyCompany(String ip) {
		return selfIpService.isIpMyCompany(ip);
	}

	/*
	 * @Title: checkUrl
	 * @Description: 
	 * @param @param url
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param url
	 * @return
	 * @see com.hnjing.ws.service.SiteMonitorService#checkUrl(java.lang.String)
	 */ 
	@Override
	public SiteBo checkUrl(String url) {
		return checkSite(url);
	}

	/*
	 * @Title: checkMutiSite
	 * @Description: 
	 * @param @param url
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param url
	 * @return
	 * @see com.hnjing.ws.service.SiteMonitorService#checkMutiSite(java.lang.String)
	 */ 
	@Override
	public List<SiteBo> checkMutiSite(String url) {
		String[] urls = url.replaceAll("，", ",").split(",");
		List<SiteBo> ret = new ArrayList<SiteBo>();
		for(String u : urls) {
			ret.add(checkSite(u));
		}
		return ret;
	}
	
	/*
	 * @Title: reCheckErrorResult
	 * @Description: 重检测异常网站
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteAccessService#reCheckErrorResult()
	 */ 
	@Override	
	public boolean reCheckErrorResult(Integer source) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("nostatus", 200);
		query.put("source", source);
		List<SiteResult> errorList = siteResultService.querySiteResultByProperty(query);
		if(errorList!=null && errorList.size()>0) {
			for(SiteResult r : errorList) {
				SiteResult s = SiteCheckUtil.doCheckSite(r.getPage());
				if(s!=null && s.getStatus()!=null && s.getStatus().intValue()==200) {
					s.setSiteId(r.getSiteId());
					siteResultService.modifySiteResult(s);
				}
			}
		}
		return true;
	}
	
	
	
	private SiteBo checkSite(String url){
		url = HttpToolUtil.getUrl(url);
		
		SiteBo site = new SiteBo();
		String ip = DeminIPUtil.cmdPingIP(url, "127.0.0.1");
		site.setIp(ip);
		site.setPage(url);
		site.setSelfSite(isIpMyCompany(ip)?1:0);
		String demin = url;
		if(url.contains("/")) {
			//包含
			String[] u = url.split("/");
			for(String c : u) {
				if(c.contains(".") && c.length()>3) {
					demin = c; 
					break;
				}
			}
		}
		site.setDemin(demin);
		SiteUrl s = new SiteUrl();
		s.setPage(url);
		SiteResult r = SiteCheckUtil.doCheckSite(s);
		site.setStatus(r.getStatus());
		site.setTitle(r.getTitle());
		site.setContent(r.getContent());
		site.setComment(r.getComment());
		
		return site;
	}

	/** 
	* @Title: calculateTotalCostMinute 
	* @Description: 根据待检测网站与线程数计算预期时长
	* @param siteCount
	* @param threadCount
	* @return  
	* int    返回类型 
	* @throws 
	*/
	public static int calculateTotalCostMinute(Integer siteCount, int threadCount) {
		if(siteCount==null || siteCount.intValue()<30) {
			return 2;
		}
		int checkPerMin = 60*threadCount*2/3;
		return 5+siteCount/checkPerMin;
	}
		

	
}
