/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: LoadDeminIPThread.java
 * @Prject: WebHealthMonitor
 * @Package: com.hnjing.ws.service.impl.util
 * @Description: 
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年10月22日 上午9:17:09
 * @version: V1.0  
 */
package com.hnjing.ws.service.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hnjing.ws.model.entity.SiteUrl;
import com.hnjing.ws.service.impl.SiteIPServiceImpl;

/**
 * @ClassName: LoadDeminIPThread
 * @Description: 加载域名IP线程
 * @author: Jinlong He
 * @date: 2018年10月22日 上午9:17:09
 */
public class LoadDeminIPThread extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(LoadDeminIPThread.class);
	private SiteIPServiceImpl siteIPServiceImpl;
	private List<SiteUrl> urlList = null;
	private Integer threadCode;
	private boolean firstLoad = true;
	
	/**
	 * @param siteMonitorServiceImpl 
	 * @Title:LoadDeminIPThread
	 * @Description:
	 * @param siteMonitorServiceImpl
	 * @param threadCode 线程代号
	 * @param urlList
	 *            注意：urlList不允许进行改变大小的增加、删除等操作。否则引起其它线程异常
	 * @param resultService
	 */
	public LoadDeminIPThread(SiteIPServiceImpl siteIPServiceImpl, List<SiteUrl> urlList, Integer threadCode) {
		this.urlList = urlList;
		this.threadCode = threadCode;
		this.siteIPServiceImpl = siteIPServiceImpl;
	}

	@Override
	public void run() {		
		if (firstLoad && threadCode != 0) {
			logger.debug("*********域名IP检测线程 " + threadCode );
			try {
				Thread.sleep(10000 * threadCode);// 错时启动时长
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.debug("*******域名IP检测线程"+threadCode+" 启动, 待检测域名:"+ urlList.size());
		
		List<SiteUrl> batchIPList = new ArrayList<SiteUrl>(); //待更新IP域名数据
		int temp = 0;
		while (temp < urlList.size()) {
			SiteUrl su = urlList.get(temp);
			String ip = DeminIPUtil.cmdPingIP(su.getDemin(), su.getIp()); //获取新IP
			if( ((ip==null || ip.length()==0) && (su.getIp()==null || su.getIp().length()==0)) ||  (ip != null  && su.getIp()!=null && ip.equals(su.getIp()))) {
				//没有变化时 
			}else {
				//前后两次IP不相同时					
				su.setOldIp(su.getIp()); // 新旧替换
				su.setIp(ip); //设置新IP	
				//su.setSelfSite(isIPisMyCompany(su.getIp())); //是否我司托管网站
				batchIPList.add(su);				
				if (batchIPList.size() == 50) {					
					siteIPServiceImpl.modifySiteUrlIPSOnBatch(batchIPList);
					logger.debug("*********域名IP检测线程"+threadCode+" 进度：" + temp	+ "/" + urlList.size());
					batchIPList.clear();
				}
			}
			temp++;			
		}
		
		//执行完成后，如果还有待批量更新数据
		if (batchIPList.size() > 0) {
			siteIPServiceImpl.modifySiteUrlIPSOnBatch(batchIPList);
			batchIPList.clear();
		}
		logger.debug("*********域名IP检测线程"+threadCode+" 检测完成，总计完成检测域名总量："	+ urlList.size());
		siteIPServiceImpl.checkWebIPThreadCallback();//回调主		
	}
	

}
