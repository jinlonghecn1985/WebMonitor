/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: DPSService.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.dpc.service
 * @Description: 
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月3日 上午11:09:16
 * @version: V1.0  
 */
package com.hnjing.dpc.service;

import java.util.List;

/**
 * @ClassName: DPSService
 * @Description: 数据处理中心
 * @author: Jinlong He
 * @date: 2018年12月3日 上午11:09:16
 */
public interface DPSService {
	
	Object processErrorMail(Integer source);
	
	Object processAllErrorMail();
	
}
