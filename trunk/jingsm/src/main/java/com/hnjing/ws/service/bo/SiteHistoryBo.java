/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteHistoryBo.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.ws.service.bo
 * @Description: 
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年11月26日 下午1:25:27
 * @version: V1.0  
 */
package com.hnjing.ws.service.bo;

import java.io.Serializable;

import com.hnjing.ws.model.entity.SiteHistory;
import com.hnjing.ws.model.entity.SiteUrl;

/**
 * @ClassName: SiteHistoryBo
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年11月26日 下午1:25:27
 */
public class SiteHistoryBo  implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	private SiteHistory siteHistory;
	private SiteUrl siteUrl;
	
	/**
	 * @return the siteUrl
	 */
	public SiteUrl getSiteUrl() {
		return siteUrl;
	}
	/**
	 * @param siteUrl the siteUrl to set
	 */
	public void setSiteUrl(SiteUrl siteUrl) {
		this.siteUrl = siteUrl;
	}
	/**
	 * @return the siteHistory
	 */
	public SiteHistory getSiteHistory() {
		return siteHistory;
	}
	/**
	 * @param siteHistory the siteHistory to set
	 */
	public void setSiteHistory(SiteHistory siteHistory) {
		this.siteHistory = siteHistory;
	}

}
