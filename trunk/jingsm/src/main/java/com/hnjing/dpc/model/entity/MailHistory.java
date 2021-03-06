package com.hnjing.dpc.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: MailHistory
 * @Description: 邮件发送记录实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 10时54分
 */
public class MailHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tb_mail_history:id  标识  

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String sendTo;	//tb_mail_history:send_to  接收人  

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String titile;	//tb_mail_history:titile  邮件标题  

	private java.lang.Object content;	//tb_mail_history:content  邮件内容  

	private Integer errorCount;	//tb_mail_history:error_count  异常数  

	private Date gmtCreate;	//tb_mail_history:GMT_CREATE  发送时间  


	/**
	* @DatabasetableColumnName: tb_mail_history:id
	* @Description: 获取属性        标识
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tb_mail_history:id
	* @Description: 设置属性        标识
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tb_mail_history:send_to
	* @Description: 获取属性        接收人
	* @return: String
	*/
	public String getSendTo(){
		return sendTo;	
	}
	
	/**
	* @DatabasetableColumnName: tb_mail_history:send_to
	* @Description: 设置属性        接收人
	* @return: String
	*/
	public void setSendTo(String sendTo){
		this.sendTo = sendTo;	
	}	
	/**
	* @DatabasetableColumnName: tb_mail_history:titile
	* @Description: 获取属性        邮件标题
	* @return: String
	*/
	public String getTitile(){
		return titile;	
	}
	
	/**
	* @DatabasetableColumnName: tb_mail_history:titile
	* @Description: 设置属性        邮件标题
	* @return: String
	*/
	public void setTitile(String titile){
		this.titile = titile;	
	}	
	/**
	* @DatabasetableColumnName: tb_mail_history:content
	* @Description: 获取属性        邮件内容
	* @return: java.lang.Object
	*/
	public java.lang.Object getContent(){
		return content;	
	}
	
	/**
	* @DatabasetableColumnName: tb_mail_history:content
	* @Description: 设置属性        邮件内容
	* @return: java.lang.Object
	*/
	public void setContent(java.lang.Object content){
		this.content = content;	
	}	
	/**
	* @DatabasetableColumnName: tb_mail_history:error_count
	* @Description: 获取属性        异常数
	* @return: Integer
	*/
	public Integer getErrorCount(){
		return errorCount;	
	}
	
	/**
	* @DatabasetableColumnName: tb_mail_history:error_count
	* @Description: 设置属性        异常数
	* @return: Integer
	*/
	public void setErrorCount(Integer errorCount){
		this.errorCount = errorCount;	
	}	
	/**
	* @DatabasetableColumnName: tb_mail_history:GMT_CREATE
	* @Description: 获取属性        发送时间
	* @return: Date
	*/
	public Date getGmtCreate(){
		return gmtCreate;	
	}
	
	/**
	* @DatabasetableColumnName: tb_mail_history:GMT_CREATE
	* @Description: 设置属性        发送时间
	* @return: Date
	*/
	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate = gmtCreate;	
	}	
	
	
	
	
}

