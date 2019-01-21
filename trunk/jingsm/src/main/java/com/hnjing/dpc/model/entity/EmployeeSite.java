package com.hnjing.dpc.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: EmployeeSf
 * @Description: 员工十分信息实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 12时30分
 */
public class EmployeeSite implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tb_employee_sf:id    

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer empId;	//tb_employee_sf:emp_id  员工标识

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer siteId; //tb_employee_sf:site_id  站点标识

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String sfName;	//tb_employee_sf:SF_Name  十分帐号  

	@Length(min=0, max=2, message="{org.hibernate.validator.constraints.Length.message}")
	private String other;	//tb_employee_sf:other  备注  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Date gmtCreate;	//tb_employee_sf:GMT_CREATE  创建时间  

	private Date gmtModified;	//tb_employee_sf:GMT_MODIFIED  修订时间  


	/**
	* @DatabasetableColumnName: tb_employee_sf:id
	* @Description: 获取属性        
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee_sf:id
	* @Description: 设置属性        
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee_sf:emp_id
	* @Description: 获取属性        员工标识
	* @return: Integer
	*/
	public Integer getEmpId(){
		return empId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee_sf:emp_id
	* @Description: 设置属性        员工标识
	* @return: Integer
	*/
	public void setEmpId(Integer empId){
		this.empId = empId;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee_sf:SF_Name
	* @Description: 获取属性        十分帐号
	* @return: String
	*/
	public String getSfName(){
		return sfName;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee_sf:SF_Name
	* @Description: 设置属性        十分帐号
	* @return: String
	*/
	public void setSfName(String sfName){
		this.sfName = sfName;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee_sf:other
	* @Description: 获取属性        备注
	* @return: String
	*/
	public String getOther(){
		return other;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee_sf:other
	* @Description: 设置属性        备注
	* @return: String
	*/
	public void setOther(String other){
		this.other = other;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee_sf:GMT_CREATE
	* @Description: 获取属性        创建时间
	* @return: Date
	*/
	public Date getGmtCreate(){
		return gmtCreate;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee_sf:GMT_CREATE
	* @Description: 设置属性        创建时间
	* @return: Date
	*/
	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate = gmtCreate;	
	}	
	/**
	* @DatabasetableColumnName: tb_employee_sf:GMT_MODIFIED
	* @Description: 获取属性        修订时间
	* @return: Date
	*/
	public Date getGmtModified(){
		return gmtModified;	
	}
	
	/**
	* @DatabasetableColumnName: tb_employee_sf:GMT_MODIFIED
	* @Description: 设置属性        修订时间
	* @return: Date
	*/
	public void setGmtModified(Date gmtModified){
		this.gmtModified = gmtModified;	
	}

	/**
	 * @return the siteId
	 */
	public Integer getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}	
	
	
	
	
}

