/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteAccessService.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.ws.service
 * @Description: 网站可访问检测接口
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年11月17日 下午5:12:55
 * @version: V1.0  
 */
package com.hnjing.ws.service;

import java.util.List;

import com.hnjing.dpc.service.bo.SiteBo;

/**
 * @ClassName: SiteAccessService
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年11月17日 下午5:12:55
 */
public interface SiteAccessService {
	
	/** 
	* @Title: checkSiteStatus 
	* @Description: 检测指定来源的待检网站 
	* @param source
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	Object checkSiteStatus(Integer source);
		
	/** 
	* @Title: checkUrl 
	* @Description: 实时检测单个网站 
	* @param url
	* @return  SiteBo    返回类型 
	* @throws 
	*/
	SiteBo checkUrl(String url);

	/** 
	* @Title: checkMutiSite 
	* @Description: 实时检测多个网站
	* @param url
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	List<SiteBo> checkMutiSite(String url);
	
	/** 
	* @Title: reCheckErrorResult 
	* @Description: 异常网站重检 
	* @return  
	* boolean    返回类型 
	* @throws 
	*/
	boolean reCheckErrorResult(Integer source);
}
