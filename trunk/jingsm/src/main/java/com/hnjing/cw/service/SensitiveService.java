/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SensitiveService.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.cw.service
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月15日 下午5:05:00
 * @version: V1.0  
 */
package com.hnjing.cw.service;

import java.util.List;

import com.hnjing.cw.model.entity.SensitiveRecord;

/**
 * @ClassName: SensitiveService
 * @Description: TODO
 * @author: Jinlong He
 * @date: 2018年12月15日 下午5:05:00
 */
public interface SensitiveService {
	
	/** 
	* @Title: checkResultSensitive 
	* @Description: 检测最新检测结果内容
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	Object checkResultSensitive();
	
	
	/** 
	* @Title: checkText 
	* @Description: 文本内容检测
	* @param content 
	* @return  
	* List<SensitiveRecord>    返回类型 
	* @throws 
	*/	
	List<SensitiveRecord> checkText(String content);
	
	
	Integer clearSensitive();

}
