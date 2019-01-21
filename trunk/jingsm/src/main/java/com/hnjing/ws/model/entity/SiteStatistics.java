package com.hnjing.ws.model.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: SiteStatistics
 * @Description: 检测结果统计实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
public class SiteStatistics implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private String id;	//tb_site_statistics:id  标识  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer source;	//tb_site_statistics:source  来源  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer siteTotal;	//tb_site_statistics:site_total  检测总量  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer siteCheck;	//tb_site_statistics:site_check  待查总量  

	private Integer siteError;	//tb_site_statistics:site_error  异常网站 
	
	private Integer siteSlow;	//tb_site_statistics:site_slow  加载慢网站

	private Integer siteWarn;	//tb_site_statistics:site_warn  警报网站  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private java.sql.Timestamp beginTime;	//tb_site_statistics:begin_time  开始时间  

	private java.sql.Timestamp endTime;	//tb_site_statistics:end_time  结束时间  


	/**
	* @DatabasetableColumnName: tb_site_statistics:id
	* @Description: 获取属性        标识
	* @return: String
	*/
	public String getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:id
	* @Description: 设置属性        标识
	* @return: String
	*/
	public void setId(String id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_statistics:source
	* @Description: 获取属性        来源
	* @return: Integer
	*/
	public Integer getSource(){
		return source;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:source
	* @Description: 设置属性        来源
	* @return: Integer
	*/
	public void setSource(Integer source){
		this.source = source;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_slow
	* @Description: 获取属性        加载慢网站
	* @return: Integer
	*/
	public Integer getSiteSlow(){
		return siteSlow;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_slow
	* @Description: 设置属性        加载慢网站
	* @return: Integer
	*/
	public void setSiteSlow(Integer siteSlow){
		this.siteSlow = siteSlow;	
	}	
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_total
	* @Description: 获取属性        检测总量
	* @return: Integer
	*/
	public Integer getSiteTotal(){
		return siteTotal;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_total
	* @Description: 设置属性        检测总量
	* @return: Integer
	*/
	public void setSiteTotal(Integer siteTotal){
		this.siteTotal = siteTotal;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_check
	* @Description: 获取属性        待查总量
	* @return: Integer
	*/
	public Integer getSiteCheck(){
		return siteCheck;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_check
	* @Description: 设置属性        待查总量
	* @return: Integer
	*/
	public void setSiteCheck(Integer siteCheck){
		this.siteCheck = siteCheck;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_error
	* @Description: 获取属性        异常网站
	* @return: Integer
	*/
	public Integer getSiteError(){
		return siteError;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_error
	* @Description: 设置属性        异常网站
	* @return: Integer
	*/
	public void setSiteError(Integer siteError){
		this.siteError = siteError;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_warn
	* @Description: 获取属性        警报网站
	* @return: Integer
	*/
	public Integer getSiteWarn(){
		return siteWarn;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:site_warn
	* @Description: 设置属性        警报网站
	* @return: Integer
	*/
	public void setSiteWarn(Integer siteWarn){
		this.siteWarn = siteWarn;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_statistics:begin_time
	* @Description: 获取属性        开始时间
	* @return: java.sql.Timestamp
	*/
	public java.sql.Timestamp getBeginTime(){
		return beginTime;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:begin_time
	* @Description: 设置属性        开始时间
	* @return: java.sql.Timestamp
	*/
	public void setBeginTime(java.sql.Timestamp beginTime){
		this.beginTime = beginTime;	
	}	
	/**
	* @DatabasetableColumnName: tb_site_statistics:end_time
	* @Description: 获取属性        结束时间
	* @return: java.sql.Timestamp
	*/
	public java.sql.Timestamp getEndTime(){
		return endTime;	
	}
	
	/**
	* @DatabasetableColumnName: tb_site_statistics:end_time
	* @Description: 设置属性        结束时间
	* @return: java.sql.Timestamp
	*/
	public void setEndTime(java.sql.Timestamp endTime){
		this.endTime = endTime;	
	}	
	
	
	
	
}

