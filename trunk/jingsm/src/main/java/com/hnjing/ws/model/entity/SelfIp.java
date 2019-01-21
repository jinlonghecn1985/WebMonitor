package com.hnjing.ws.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: SelfIp
 * @Description: 我司IP信息实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
public class SelfIp implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tb_self_ip:id  标识  

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=255, message="{org.hibernate.validator.constraints.Length.message}")
	private String ip;	//tb_self_ip:ip    

	private Integer count;	//tb_self_ip:count  数量  

	private Date gmtCreated;	//tb_self_ip:gmt_created  创建时间  

	private Date gmtModify;	//tb_self_ip:gmt_modify  修订时间  


	/**
	* @DatabasetableColumnName: tb_self_ip:id
	* @Description: 获取属性        标识
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tb_self_ip:id
	* @Description: 设置属性        标识
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tb_self_ip:ip
	* @Description: 获取属性        
	* @return: String
	*/
	public String getIp(){
		return ip;	
	}
	
	/**
	* @DatabasetableColumnName: tb_self_ip:ip
	* @Description: 设置属性        
	* @return: String
	*/
	public void setIp(String ip){
		this.ip = ip;	
	}	
	/**
	* @DatabasetableColumnName: tb_self_ip:count
	* @Description: 获取属性        数量
	* @return: Integer
	*/
	public Integer getCount(){
		return count;	
	}
	
	/**
	* @DatabasetableColumnName: tb_self_ip:count
	* @Description: 设置属性        数量
	* @return: Integer
	*/
	public void setCount(Integer count){
		this.count = count;	
	}	
	/**
	* @DatabasetableColumnName: tb_self_ip:gmt_created
	* @Description: 获取属性        创建时间
	* @return: Date
	*/
	public Date getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_self_ip:gmt_created
	* @Description: 设置属性        创建时间
	* @return: Date
	*/
	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	/**
	* @DatabasetableColumnName: tb_self_ip:gmt_modify
	* @Description: 获取属性        修订时间
	* @return: Date
	*/
	public Date getGmtModify(){
		return gmtModify;	
	}
	
	/**
	* @DatabasetableColumnName: tb_self_ip:gmt_modify
	* @Description: 设置属性        修订时间
	* @return: Date
	*/
	public void setGmtModify(Date gmtModify){
		this.gmtModify = gmtModify;	
	}	
	
	
	
	
}

