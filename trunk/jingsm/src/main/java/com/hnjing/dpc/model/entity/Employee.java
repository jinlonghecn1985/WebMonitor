package com.hnjing.dpc.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: Employee
 * @Description: 员工信息实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 10时54分
 */
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tb_employee:id  员工标识  

	@Length(min=0, max=8, message="{org.hibernate.validator.constraints.Length.message}")
	private String did;	//tb_employee:did  DIS员工标识  

	@Length(min=0, max=8, message="{org.hibernate.validator.constraints.Length.message}")
	private String emplNo;	//tb_employee:EMPL_NO  员工工号  

	@Length(min=0, max=255, message="{org.hibernate.validator.constraints.Length.message}")
	private String realName;	//tb_employee:REAL_NAME  员工姓名  

	private Integer orgId;	//tb_employee:ORG_ID  组织标识  

	@Length(min=0, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String orgName;	//tb_employee:ORG_NAME  组织全称  

	@Length(min=0, max=255, message="{org.hibernate.validator.constraints.Length.message}")
	@Pattern(regexp="1[34578]\\d{9}", message="{validator.phone.message}")
	private String mobile;	//tb_employee:MOBILE  手机号码  

	@Length(min=0, max=255, message="{org.hibernate.validator.constraints.Length.message}")
	@Email(message="{org.hibernate.validator.constraints.Email.message}")
	private String email;	//tb_employee:EMAIL  邮箱号码  

	
	private Integer isactive;	//tb_employee:ISACTIVE  状态 0正常  

	private Date gmtCreate;	//tb_employee:GMT_CREATE  创建时间  

	private Date gmtModified;	//tb_employee:GMT_MODIFIED  修订时间  


	/**
	* @DatabasetableColumnName: tb_employee:id
	* @Description: 获取属性        员工标识
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:id
	* @Description: 设置属性        员工标识
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:did
	* @Description: 获取属性        DIS员工标识
	* @return: String
	*/
	public String getDid(){
		return did;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:did
	* @Description: 设置属性        DIS员工标识
	* @return: String
	*/
	public void setDid(String did){
		this.did = did;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:EMPL_NO
	* @Description: 获取属性        员工工号
	* @return: String
	*/
	public String getEmplNo(){
		return emplNo;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:EMPL_NO
	* @Description: 设置属性        员工工号
	* @return: String
	*/
	public void setEmplNo(String emplNo){
		this.emplNo = emplNo;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:REAL_NAME
	* @Description: 获取属性        员工姓名
	* @return: String
	*/
	public String getRealName(){
		return realName;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:REAL_NAME
	* @Description: 设置属性        员工姓名
	* @return: String
	*/
	public void setRealName(String realName){
		this.realName = realName;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:ORG_ID
	* @Description: 获取属性        组织标识
	* @return: Integer
	*/
	public Integer getOrgId(){
		return orgId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:ORG_ID
	* @Description: 设置属性        组织标识
	* @return: Integer
	*/
	public void setOrgId(Integer orgId){
		this.orgId = orgId;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:ORG_NAME
	* @Description: 获取属性        组织全称
	* @return: String
	*/
	public String getOrgName(){
		return orgName;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:ORG_NAME
	* @Description: 设置属性        组织全称
	* @return: String
	*/
	public void setOrgName(String orgName){
		this.orgName = orgName;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:MOBILE
	* @Description: 获取属性        手机号码
	* @return: String
	*/
	public String getMobile(){
		return mobile;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:MOBILE
	* @Description: 设置属性        手机号码
	* @return: String
	*/
	public void setMobile(String mobile){
		this.mobile = mobile;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:EMAIL
	* @Description: 获取属性        邮箱号码
	* @return: String
	*/
	public String getEmail(){
		return email;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:EMAIL
	* @Description: 设置属性        邮箱号码
	* @return: String
	*/
	public void setEmail(String email){
		this.email = email;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:ISACTIVE
	* @Description: 获取属性        状态 0正常
	* @return: String
	*/
	public Integer getIsactive(){
		return isactive;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:ISACTIVE
	* @Description: 设置属性        状态 0正常
	* @return: String
	*/
	public void setIsactive(Integer isactive){
		this.isactive = isactive;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:GMT_CREATE
	* @Description: 获取属性        创建时间
	* @return: Date
	*/
	public Date getGmtCreate(){
		return gmtCreate;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:GMT_CREATE
	* @Description: 设置属性        创建时间
	* @return: Date
	*/
	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate = gmtCreate;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee:GMT_MODIFIED
	* @Description: 获取属性        修订时间
	* @return: Date
	*/
	public Date getGmtModified(){
		return gmtModified;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee:GMT_MODIFIED
	* @Description: 设置属性        修订时间
	* @return: Date
	*/
	public void setGmtModified(Date gmtModified){
		this.gmtModified = gmtModified;	
	}	
	
	
	
	
}

