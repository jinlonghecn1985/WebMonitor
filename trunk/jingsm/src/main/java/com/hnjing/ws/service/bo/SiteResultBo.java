/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteResultBo.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.ws.service.bo
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年11月26日 上午9:18:14
 * @version: V1.0  
 */
package com.hnjing.ws.service.bo;

import java.io.Serializable;

import com.hnjing.ws.model.entity.SiteResult;
import com.hnjing.ws.model.entity.SiteUrl;

/**
 * @ClassName: SiteResultBo
 * @Description: TODO
 * @author: Jinlong He
 * @date: 2018年11月26日 上午9:18:14
 */
public class SiteResultBo  implements Serializable{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	private SiteResult siteResult;
	private SiteUrl siteUrl;
	/**
	 * @return the siteResult
	 */
	public SiteResult getSiteResult() {
		return siteResult;
	}
	/**
	 * @param siteResult the siteResult to set
	 */
	public void setSiteResult(SiteResult siteResult) {
		this.siteResult = siteResult;
	}
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

}
