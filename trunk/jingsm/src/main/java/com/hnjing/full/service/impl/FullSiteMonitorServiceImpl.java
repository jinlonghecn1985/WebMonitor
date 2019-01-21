/**  
   * Copyright © 2019公司名字. All rights reserved.
 * @Title: FullSiteMonitorServiceImpl.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.full.service.impl
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月7日 上午10:42:56
 * @version: V1.0  
 */
package com.hnjing.full.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjing.full.model.entity.MonitorOutline;
import com.hnjing.full.service.FullSiteMonitorService;
import com.hnjing.full.service.MonitorOutlineService;
import com.hnjing.full.service.util.WebCrawlerThread;
import com.hnjing.ws.service.SelfIpService;
import com.hnjing.ws.service.impl.util.DeminIPUtil;

/**
 * @ClassName: FullSiteMonitorServiceImpl
 * @Description: 全站检测实现类
 * @author: Jinlong He
 * @date: 2019年1月7日 上午10:42:56
 */
@Service("fullSiteMonitorService")
public class FullSiteMonitorServiceImpl implements FullSiteMonitorService {
	
	private static final Logger logger = LoggerFactory.getLogger(FullSiteMonitorServiceImpl.class);
	@Autowired
	private MonitorOutlineService monitorOutlineService;
	
	@Autowired
	private SelfIpService selfIpService;
	
	

	/*
	 * @Title: fullSiteMonitor
	 * @Description: 
	 * @param @param page
	 * @param @param checkDomain
	 * @param @param acceptMail
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param page
	 * @param checkDomain
	 * @param acceptMail
	 * @return
	 * @see com.hnjing.full.service.FullSiteMonitorService#fullSiteMonitor(java.lang.String, boolean, java.lang.String)
	 */ 
	@Override
	public MonitorOutline fullSiteMonitor(String page, boolean checkDomain, String ecode, String acceptMail) {
		return doSiteCheck(page, checkDomain, ecode, acceptMail);
	}



	/*
	 * @Title: doOneSiteFullCheck
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.full.service.FullSiteMonitorService#doOneSiteFullCheck()
	 */ 
	@Override
	public Object doOneSiteFullCheck() {
		Map<String, Object> site = monitorOutlineService.queryOneNeedCheckPage();
		if(site!=null && site.containsKey("page")) {
			String charset = null;
			if(site.containsKey("charset")) {
				charset = (String)site.get("charset");
			}			
			return doSiteCheck((String)site.get("page"), false, charset, null);
		}
		logger.debug("没有查询到近一月待全站检测数据！");
		return "没有查询到近一月待全站检测数据！！";
	}
	
	private MonitorOutline doSiteCheck(String page, boolean checkDomain, String ecode, String acceptMail) {
		MonitorOutline mo = new MonitorOutline();
		mo.setPage(page);
		if(checkDomain) {
			 //后期区分
		}
		mo.setAcceptMail(acceptMail);
		if(!DeminIPUtil.isIPRes(page)) {
			mo.setIp(DeminIPUtil.cmdPingIP(page, null));
		}else {
			//IP
			mo.setIp(DeminIPUtil.getIPRes(page));
		}		
		if(mo.getIp()!=null) {
			mo.setSelfSite(selfIpService.isIpMyCompany(mo.getIp())?1:0);
		}
		monitorOutlineService.addMonitorOutline(mo); //
		WebCrawlerThread wct = new WebCrawlerThread();
		wct.initThread(mo, ecode);
		wct.start();
		return mo;
	}



	/*
	 * @Title: reCheckOneSite
	 * @Description: TODO
	 * @param @param id
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param id
	 * @return
	 * @see com.hnjing.full.service.FullSiteMonitorService#reCheckOneSite(java.lang.Integer)
	 */ 
	@Override
	public Object reCheckOneSite(Integer id) {
		
		return null;
	}



	/*
	 * @Title: deleteDataBeforeDays
	 * @Description: TODO
	 * @param @param days
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param days
	 * @return
	 * @see com.hnjing.full.service.FullSiteMonitorService#deleteDataBeforeDays(java.lang.Integer)
	 */ 
	@Override
	public Integer deleteDataBeforeDays(Integer days) {
		List<MonitorOutline> data = monitorOutlineService.queryDataBeforeDay(days);
		if(data!=null && data.size()>0) {
			for(MonitorOutline mo : data) {
				monitorOutlineService.dropMonitorOutlineById(mo.getId());
			}
			return data.size();
		}
		return 0;
	}
	


}
