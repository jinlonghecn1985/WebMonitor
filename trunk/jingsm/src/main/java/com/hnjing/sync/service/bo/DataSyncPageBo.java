/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: DataSyncPageBo.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.sync.service.bo
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月26日 下午5:51:29
 * @version: V1.0  
 */
package com.hnjing.sync.service.bo;

import java.io.Serializable;

/**
 * @ClassName: DataSyncPageBo
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年12月26日 下午5:51:29
 */
public class DataSyncPageBo implements Serializable{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	private Integer totalCount;
	private Integer pageNo;
	private Integer pageSize;
	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
