/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteAllInfo.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.dpc.service.bo
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月4日 上午11:49:30
 * @version: V1.0  
 */
package com.hnjing.dpc.service.bo;

import java.io.Serializable;

import com.hnjing.dpc.model.entity.EmployeeSite;
import com.hnjing.ws.model.entity.SiteHistory;
import com.hnjing.ws.model.entity.SiteUrl;

/**
 * @ClassName: SiteAllInfo
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年12月4日 上午11:49:30
 */
public class SiteAllInfo implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	private SiteHistory siteHistory;
	private SiteUrl siteUrl;
	private EmployeeSite employeeSite;
	public SiteAllInfo() {
		
	}
	public SiteAllInfo(SiteHistory sh, SiteUrl su, EmployeeSite es) {
		siteHistory = sh;
		siteUrl = su;
		employeeSite = es;
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
	 * @return the employeeSite
	 */
	public EmployeeSite getEmployeeSite() {
		return employeeSite;
	}
	/**
	 * @param employeeSite the employeeSite to set
	 */
	public void setEmployeeSite(EmployeeSite employeeSite) {
		this.employeeSite = employeeSite;
	}
}
