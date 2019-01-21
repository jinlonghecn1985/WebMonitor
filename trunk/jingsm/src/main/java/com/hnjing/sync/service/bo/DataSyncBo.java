/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: DataSyncBo.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.sync.service.bo
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月26日 下午5:36:24
 * @version: V1.0  
 */
package com.hnjing.sync.service.bo;

import java.io.Serializable;
import java.util.List;

import com.hnjing.sync.model.entity.DataSync;

/**
 * @ClassName: DataSyncBo
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年12月26日 下午5:36:24
 */
public class DataSyncBo implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	private List<DataSync> data;
	private DataSyncPageBo page;
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the data
	 */
	public List<DataSync> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<DataSync> data) {
		this.data = data;
	}
	/**
	 * @return the page
	 */
	public DataSyncPageBo getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(DataSyncPageBo page) {
		this.page = page;
	}
}
