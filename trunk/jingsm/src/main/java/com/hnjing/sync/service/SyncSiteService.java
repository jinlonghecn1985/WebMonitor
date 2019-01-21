/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SyncSiteService.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.sync.service
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月27日 上午8:46:07
 * @version: V1.0  
 */
package com.hnjing.sync.service;

/**
 * @ClassName: SyncSiteService
 * @Description: TODO
 * @author: Jinlong He
 * @date: 2018年12月27日 上午8:46:07
 */
public interface SyncSiteService {

	/** 
	* @Title: syncDataForSource 
	* @Description: 从第三方同步数据
	* @param source
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	Object syncDataForSource(Integer source);
	
	
	/** 
	* @Title: processDataForSource 
	* @Description: 处理同步后的数据
	* @param source
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	Object processDataForSource(Integer source);
	
	Object syncDataForSource(Integer source, String fileName);
}
