/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: WaitCheckSite.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.full.service.impl.bo
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月9日 下午1:58:23
 * @version: V1.0  
 */
package com.hnjing.full.service.impl.bo;

import java.util.Date;

/**
 * @ClassName: WaitCheckSite
 * @Description: TODO
 * @author: Jinlong He
 * @date: 2019年1月9日 下午1:58:23
 */
public class WaitCheckSiteBo {
	private String page;
	private String charset;
	private Integer id;
	private Date gmtModify;
	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}
	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}
	/**
	 * @param charset the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the gmtModify
	 */
	public Date getGmtModify() {
		return gmtModify;
	}
	/**
	 * @param gmtModify the gmtModify to set
	 */
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}
	

}
