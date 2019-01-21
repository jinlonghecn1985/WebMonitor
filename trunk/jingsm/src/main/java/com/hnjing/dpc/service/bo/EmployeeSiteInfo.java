/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteAllInfo.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.dpc.service.bo
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月4日 上午11:46:36
 * @version: V1.0  
 */
package com.hnjing.dpc.service.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hnjing.dpc.model.entity.Employee;

/**
 * @ClassName: SiteAllInfo
 * @Description: TODO
 * @author: Jinlong He
 * @date: 2018年12月4日 上午11:46:36
 */
public class EmployeeSiteInfo implements Serializable{
	
	private Employee employee;
	
	private List<SiteAllInfo> siteList;

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the siteList
	 */
	public List<SiteAllInfo> getSiteList() {
		return siteList;
	}

	/**
	 * @param siteList the siteList to set
	 */
	public void setSiteList(List<SiteAllInfo> siteList) {
		this.siteList = siteList;
	}
	
	public void setSiteAllInfo(SiteAllInfo site) {
		if(siteList==null) {
			siteList = new ArrayList<SiteAllInfo>();
		}
		siteList.add(site);
	}

}
