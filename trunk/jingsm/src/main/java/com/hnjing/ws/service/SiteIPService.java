/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteIPService.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.ws.service
 * @Description: IP检测服务类
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月28日 上午11:13:53
 * @version: V1.0  
 */
package com.hnjing.ws.service;

/**
 * @ClassName: SiteIPService
 * @Description: IP检测服务类接口
 * @author: Jinlong He
 * @date: 2018年12月28日 上午11:13:53
 */
public interface SiteIPService {

	/**
	 * @param source  
	* @Title: checkAllSiteIP 
	* @Description: 检测全量IP域名
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	Object checkAllSiteIP(Integer source);
	
	
	
	/** 
	* @Title: checkNullSiteIP 
	* @Description: 复检空IP域名
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	Object checkNullSiteIP();
	
	/** 
	* @Title: statisticSiteIPInfo 
	* @Description: 统计站点\IP信息
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer statisticSiteIPInfo();
	
	
	/*
	 * @Title: recheckErrorSiteIP
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteMonitorService#recheckErrorSiteIP()
	 */
	Integer recheckErrorSiteIP(Integer source);
	
	
}
