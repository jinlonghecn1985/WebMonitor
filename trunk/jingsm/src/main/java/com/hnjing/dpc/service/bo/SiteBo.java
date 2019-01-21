/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteBo.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.ws.service.bo
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月4日 上午10:06:02
 * @version: V1.0  
 */
package com.hnjing.dpc.service.bo;

import java.io.Serializable;

/**
 * @ClassName: SiteBo
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年12月4日 上午10:06:02
 */
public class SiteBo implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;

	private String page;	//tb_site_url:page  待检测网址/落地页  

	private String demin;	//tb_site_url:demin  归属主域名  

	private Integer source;	//tb_site_url:source  来源类型 0未分类1平台2推广 
		
	private String charset;	//tb_site_url:charset  文字编码  
	
	private String ip;	//tb_site_url:ip  现IP  

	private Integer selfSite;	//tb_site_url:self_site  是否我司托管 0非1是  

	private Integer status;	//状态 

	private String customer;	//客户
	
	private String title;	//标题
	
	private java.lang.Object content;	//内容
	
	private Integer comment;	//评价
	

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
	 * @return the demin
	 */
	public String getDemin() {
		return demin;
	}

	/**
	 * @param demin the demin to set
	 */
	public void setDemin(String demin) {
		this.demin = demin;
	}

	/**
	 * @return the source
	 */
	public Integer getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Integer source) {
		this.source = source;
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
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the selfSite
	 */
	public Integer getSelfSite() {
		return selfSite;
	}

	/**
	 * @param selfSite the selfSite to set
	 */
	public void setSelfSite(Integer selfSite) {
		this.selfSite = selfSite;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public java.lang.Object getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(java.lang.Object content) {
		this.content = content;
	}

	/**
	 * @return the comment
	 */
	public Integer getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(Integer comment) {
		this.comment = comment;
	}
}
