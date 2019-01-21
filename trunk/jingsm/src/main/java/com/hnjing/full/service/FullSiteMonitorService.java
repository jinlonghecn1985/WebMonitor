/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: FullSiteMonitorService.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.full.service.impl
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月7日 上午10:35:15
 * @version: V1.0  
 */
package com.hnjing.full.service;

import com.hnjing.full.model.entity.MonitorOutline;

/**
 * @ClassName: FullSiteMonitorService
 * @Description: 全站检测
 * @author: Jinlong He
 * @date: 2019年1月7日 上午10:35:15
 */
public interface FullSiteMonitorService {

	/** 
	* @Title: fullSiteMonitor 
	* @Description: 全站检测
	* @param page 检测页
	* @param ecode 网页编码
	* @param checkDomain 基于域名检测
	* @param acceptMail 接收邮箱
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	MonitorOutline fullSiteMonitor(String page, boolean checkDomain, String ecode, String acceptMail);
	
	
	/** 
	* @Title: doOneSiteFullCheck 
	* @Description: 按规则检测一个网站
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	Object doOneSiteFullCheck();
	
	
	/** 
	* @Title: reCheckOneSite 
	* @Description: 对某个网站进行重检 
	* @param id
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	Object reCheckOneSite(Integer id);
	
	
	/** 
	* @Title: deleteDataBeforeDays 
	* @Description: 删除多少天前的检测记录
	* @param days
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer deleteDataBeforeDays(Integer days);
	
	
}
