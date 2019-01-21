/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: OutlineBo.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.full.service.impl.bo
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月10日 上午10:16:52
 * @version: V1.0  
 */
package com.hnjing.full.service.impl.bo;

import java.io.Serializable;

/**
 * @ClassName: OutlineBo
 * @Description: 
 * @author: Jinlong He
 * @date: 2019年1月10日 上午10:16:52
 */
public class OutlineBo implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	private String page;
	private String mail;
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
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
}
