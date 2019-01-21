package com.hnjing.ws.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: SiteHistory
 * @Description: 异常网站历史记录实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
public class SiteHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer siteId;	//tb_site_history:site_id  待检测网站标识  

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=128, message="{org.hibernate.validator.constraints.Length.message}")
	private String page;	//tb_site_history:page    

	@Length(min=0, max=512, message="{org.hibernate.validator.constraints.Length.message}")
	private String title;	//tb_site_history:title  标题  

	private java.lang.Object content;	//tb_site_history:content  内容  

	private Integer status;	//tb_site_history:status  响应代码  

	private Integer comment;	//tb_site_history:comment  评价 1响应慢  

	private Date gmtCreated;	//tb_site_history:gmt_created  检测时间 4
	
	private String ip;
	private Integer selfSite;


	/**
	* @DatabasetableColumnName: tb_site_history:site_id
	* @Description: 获取属性        待检测网站标识
	* @return: Integer
	*/
	public Integer getSiteId(){
		return siteId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_history:site_id
	* @Description: 设置属性        待检测网站标识
	* @return: Integer
	*/
	public void setSiteId(Integer siteId){
		this.siteId = siteId;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_history:page
	* @Description: 获取属性        
	* @return: String
	*/
	public String getPage(){
		return page;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_history:page
	* @Description: 设置属性        
	* @return: String
	*/
	public void setPage(String page){
		this.page = page;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_history:title
	* @Description: 获取属性        标题
	* @return: String
	*/
	public String getTitle(){
		return title;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_history:title
	* @Description: 设置属性        标题
	* @return: String
	*/
	public void setTitle(String title){
		this.title = title;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_history:content
	* @Description: 获取属性        内容
	* @return: java.lang.Object
	*/
	public java.lang.Object getContent(){
		return content;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_history:content
	* @Description: 设置属性        内容
	* @return: java.lang.Object
	*/
	public void setContent(java.lang.Object content){
		this.content = content;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_history:status
	* @Description: 获取属性        响应代码
	* @return: Integer
	*/
	public Integer getStatus(){
		return status;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_history:status
	* @Description: 设置属性        响应代码
	* @return: Integer
	*/
	public void setStatus(Integer status){
		this.status = status;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_history:comment
	* @Description: 获取属性        评价 1响应慢
	* @return: Integer
	*/
	public Integer getComment(){
		return comment;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_history:comment
	* @Description: 设置属性        评价 1响应慢
	* @return: Integer
	*/
	public void setComment(Integer comment){
		this.comment = comment;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_history:gmt_created
	* @Description: 获取属性        检测时间
	* @return: Date
	*/
	public Date getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_history:gmt_created
	* @Description: 设置属性        检测时间
	* @return: Date
	*/
	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;	
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
	
	
	
	
}

