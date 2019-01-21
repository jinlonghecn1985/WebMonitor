package com.hnjing.sync.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: DataSync
 * @Description: 数据中心实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月26日 17时21分
 */
public class DataSync implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tb_data_sync:id  自增标识  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer source;	//tb_data_sync:source  来源  

	@Length(min=0, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String accountName;	//tb_data_sync:account_name  帐号  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String emplName;	//tb_data_sync:empl_name  员工姓名  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String organ1;	//tb_data_sync:organ1  一级部门名  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String organ2;	//tb_data_sync:organ2  二级部门名  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String organ3;	//tb_data_sync:organ3  三级部门名  

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String companyName;	//tb_data_sync:company_name  客户公司名  

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String siteUrl;	//tb_data_sync:site_url  网址  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	@Email(message="{org.hibernate.validator.constraints.Email.message}")
	private String email;	//tb_data_sync:email  员工邮箱  

	@Length(min=0, max=8, message="{org.hibernate.validator.constraints.Length.message}")
	private String emplNo;	//tb_data_sync:empl_no  员工工号  

	@Length(min=0, max=128, message="{org.hibernate.validator.constraints.Length.message}")
	private String note;	//tb_data_sync:note  说明  

	private Date gmtCreated;	//tb_data_sync:gmt_created  创建时间  

	private Date gmtModify;	//tb_data_sync:gmt_modify  修订时间 
	
	
	private Integer isDelete;	//tb_data_sync:isDelete  是否删除
	
	private Integer hasChange;	//tb_data_sync:hasChange  变化位

	/**
	* @DatabasetableColumnName: tb_data_sync:id
	* @Description: 获取属性        自增标识
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:id
	* @Description: 设置属性        自增标识
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:source
	* @Description: 获取属性        来源
	* @return: Integer
	*/
	public Integer getSource(){
		return source;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:source
	* @Description: 设置属性        来源
	* @return: Integer
	*/
	public void setSource(Integer source){
		this.source = source;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:account_name
	* @Description: 获取属性        帐号
	* @return: String
	*/
	public String getAccountName(){
		return accountName;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:account_name
	* @Description: 设置属性        帐号
	* @return: String
	*/
	public void setAccountName(String accountName){
		this.accountName = accountName;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:empl_name
	* @Description: 获取属性        员工姓名
	* @return: String
	*/
	public String getEmplName(){
		return emplName;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:empl_name
	* @Description: 设置属性        员工姓名
	* @return: String
	*/
	public void setEmplName(String emplName){
		this.emplName = emplName;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:organ1
	* @Description: 获取属性        一级部门名
	* @return: String
	*/
	public String getOrgan1(){
		return organ1;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:organ1
	* @Description: 设置属性        一级部门名
	* @return: String
	*/
	public void setOrgan1(String organ1){
		this.organ1 = organ1;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:organ2
	* @Description: 获取属性        二级部门名
	* @return: String
	*/
	public String getOrgan2(){
		return organ2;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:organ2
	* @Description: 设置属性        二级部门名
	* @return: String
	*/
	public void setOrgan2(String organ2){
		this.organ2 = organ2;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:organ3
	* @Description: 获取属性        三级部门名
	* @return: String
	*/
	public String getOrgan3(){
		return organ3;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:organ3
	* @Description: 设置属性        三级部门名
	* @return: String
	*/
	public void setOrgan3(String organ3){
		this.organ3 = organ3;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:company_name
	* @Description: 获取属性        客户公司名
	* @return: String
	*/
	public String getCompanyName(){
		return companyName;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:company_name
	* @Description: 设置属性        客户公司名
	* @return: String
	*/
	public void setCompanyName(String companyName){
		this.companyName = companyName;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:site_url
	* @Description: 获取属性        网址
	* @return: String
	*/
	public String getSiteUrl(){
		return siteUrl;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:site_url
	* @Description: 设置属性        网址
	* @return: String
	*/
	public void setSiteUrl(String siteUrl){
		this.siteUrl = siteUrl;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:email
	* @Description: 获取属性        员工邮箱
	* @return: String
	*/
	public String getEmail(){
		return email;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:email
	* @Description: 设置属性        员工邮箱
	* @return: String
	*/
	public void setEmail(String email){
		this.email = email;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:empl_no
	* @Description: 获取属性        员工工号
	* @return: String
	*/
	public String getEmplNo(){
		return emplNo;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:empl_no
	* @Description: 设置属性        员工工号
	* @return: String
	*/
	public void setEmplNo(String emplNo){
		this.emplNo = emplNo;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:note
	* @Description: 获取属性        说明
	* @return: String
	*/
	public String getNote(){
		return note;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:note
	* @Description: 设置属性        说明
	* @return: String
	*/
	public void setNote(String note){
		this.note = note;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:gmt_created
	* @Description: 获取属性        创建时间
	* @return: Date
	*/
	public Date getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:gmt_created
	* @Description: 设置属性        创建时间
	* @return: Date
	*/
	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	/**
	* @DatabasetableColumnName: tb_data_sync:gmt_modify
	* @Description: 获取属性        修订时间
	* @return: Date
	*/
	public Date getGmtModify(){
		return gmtModify;	
	}
	
	/**
	* @DatabasetableColumnName: tb_data_sync:gmt_modify
	* @Description: 设置属性        修订时间
	* @return: Date
	*/
	public void setGmtModify(Date gmtModify){
		this.gmtModify = gmtModify;	
	}

	/**
	 * @return the isDelete
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the hasChange
	 */
	public Integer getHasChange() {
		return hasChange;
	}

	/**
	 * @param hasChange the hasChange to set
	 */
	public void setHasChange(Integer hasChange) {
		this.hasChange = hasChange;
	}	
	
	
	
	
}

