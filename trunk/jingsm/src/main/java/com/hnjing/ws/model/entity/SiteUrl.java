package com.hnjing.ws.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: SiteUrl
 * @Description: 待检测网站信息实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
public class SiteUrl implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tb_site_url:id  标识  

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String page;	//tb_site_url:page  待检测网址/落地页  

	@Length(min=0, max=128, message="{org.hibernate.validator.constraints.Length.message}")
	private String demin;	//tb_site_url:demin  归属主域名  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer source;	//tb_site_url:source  来源类型 0未分类1平台2推广  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer needCheck;	//tb_site_url:need_check  是否要检测 0检测1不检测  

	@Length(min=0, max=8, message="{org.hibernate.validator.constraints.Length.message}")
	private String charset;	//tb_site_url:charset  文字编码  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String ip;	//tb_site_url:ip  现IP  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String oldIp;	//tb_site_url:old_ip  旧IP  

	private Integer ipChecked;	//tb_site_url:ip_checked  IP检测状态 0已检1待检  

	private Integer selfSite;	//tb_site_url:self_site  是否我司托管 0非1是  

	private Date dt;	//tb_site_url:dt  IP变更时间  

	@Length(min=0, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String customer;	//tb_site_url:customer  客户  

	private Date errorTime;	//tb_site_url:error_time  最近异常时间  

	private Date gmtCreated;	//tb_site_url:gmt_created  创建时间  

	private Date gmtModify;	//tb_site_url:gmt_modify  修订时间  


	/**
	* @DatabasetableColumnName: tb_site_url:id
	* @Description: 获取属性        标识
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:id
	* @Description: 设置属性        标识
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:page
	* @Description: 获取属性        待检测网址/落地页
	* @return: String
	*/
	public String getPage(){
		return page;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:page
	* @Description: 设置属性        待检测网址/落地页
	* @return: String
	*/
	public void setPage(String page){
		this.page = page;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:demin
	* @Description: 获取属性        归属主域名
	* @return: String
	*/
	public String getDemin(){
		return demin;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:demin
	* @Description: 设置属性        归属主域名
	* @return: String
	*/
	public void setDemin(String demin){
		this.demin = demin;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:source
	* @Description: 获取属性        来源类型 0未分类1平台2推广
	* @return: Integer
	*/
	public Integer getSource(){
		return source;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:source
	* @Description: 设置属性        来源类型 0未分类1平台2推广
	* @return: Integer
	*/
	public void setSource(Integer source){
		this.source = source;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:need_check
	* @Description: 获取属性        是否要检测 0检测1不检测
	* @return: Integer
	*/
	public Integer getNeedCheck(){
		return needCheck;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:need_check
	* @Description: 设置属性        是否要检测 0检测1不检测
	* @return: Integer
	*/
	public void setNeedCheck(Integer needCheck){
		this.needCheck = needCheck;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:charset
	* @Description: 获取属性        文字编码
	* @return: String
	*/
	public String getCharset(){
		return charset;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:charset
	* @Description: 设置属性        文字编码
	* @return: String
	*/
	public void setCharset(String charset){
		this.charset = charset;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:ip
	* @Description: 获取属性        现IP
	* @return: String
	*/
	public String getIp(){
		return ip;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:ip
	* @Description: 设置属性        现IP
	* @return: String
	*/
	public void setIp(String ip){
		this.ip = ip;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:old_ip
	* @Description: 获取属性        旧IP
	* @return: String
	*/
	public String getOldIp(){
		return oldIp;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:old_ip
	* @Description: 设置属性        旧IP
	* @return: String
	*/
	public void setOldIp(String oldIp){
		this.oldIp = oldIp;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:ip_checked
	* @Description: 获取属性        IP检测状态 0已检1待检
	* @return: Integer
	*/
	public Integer getIpChecked(){
		return ipChecked;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:ip_checked
	* @Description: 设置属性        IP检测状态 0已检1待检
	* @return: Integer
	*/
	public void setIpChecked(Integer ipChecked){
		this.ipChecked = ipChecked;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:self_site
	* @Description: 获取属性        是否我司托管 0非1是
	* @return: Integer
	*/
	public Integer getSelfSite(){
		return selfSite;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:self_site
	* @Description: 设置属性        是否我司托管 0非1是
	* @return: Integer
	*/
	public void setSelfSite(Integer selfSite){
		this.selfSite = selfSite;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:dt
	* @Description: 获取属性        IP变更时间
	* @return: Date
	*/
	public Date getDt(){
		return dt;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:dt
	* @Description: 设置属性        IP变更时间
	* @return: Date
	*/
	public void setDt(Date dt){
		this.dt = dt;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:customer
	* @Description: 获取属性        客户
	* @return: String
	*/
	public String getCustomer(){
		return customer;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:customer
	* @Description: 设置属性        客户
	* @return: String
	*/
	public void setCustomer(String customer){
		this.customer = customer;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:error_time
	* @Description: 获取属性        最近异常时间
	* @return: Date
	*/
	public Date getErrorTime(){
		return errorTime;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:error_time
	* @Description: 设置属性        最近异常时间
	* @return: Date
	*/
	public void setErrorTime(Date errorTime){
		this.errorTime = errorTime;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:gmt_created
	* @Description: 获取属性        创建时间
	* @return: Date
	*/
	public Date getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:gmt_created
	* @Description: 设置属性        创建时间
	* @return: Date
	*/
	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_url:gmt_modify
	* @Description: 获取属性        修订时间
	* @return: Date
	*/
	public Date getGmtModify(){
		return gmtModify;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_url:gmt_modify
	* @Description: 设置属性        修订时间
	* @return: Date
	*/
	public void setGmtModify(Date gmtModify){
		this.gmtModify = gmtModify;	
	}	
	
	
	
	
}

