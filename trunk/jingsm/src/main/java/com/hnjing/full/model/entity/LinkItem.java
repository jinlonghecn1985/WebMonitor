package com.hnjing.full.model.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: LinkItem
 * @Description: 网页链接信息实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
public class LinkItem implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tf_link_item:id    

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String page;	//tf_link_item:page    

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer status;	//tf_link_item:status  类型 0内链 1外链 2非法外链  

	@Length(min=0, max=128, message="{org.hibernate.validator.constraints.Length.message}")
	private String anchor;	//tf_link_item:anchor    

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String link;	//tf_link_item:link  链接  

	public LinkItem() {
		
	}
	public LinkItem(String page, String anchor, String link, Integer status) {
		super();
		this.page = page;
		this.anchor = anchor;
		this.link = link;
		this.status = status;
	}

	/**
	* @DatabasetableColumnName: tf_link_item:id
	* @Description: 获取属性        
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tf_link_item:id
	* @Description: 设置属性        
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tf_link_item:page
	* @Description: 获取属性        
	* @return: String
	*/
	public String getPage(){
		return page;	
	}
	
	/**
	* @DatabasetableColumnName: tf_link_item:page
	* @Description: 设置属性        
	* @return: String
	*/
	public void setPage(String page){
		this.page = page;	
	}	
	/**
	* @DatabasetableColumnName: tf_link_item:status
	* @Description: 获取属性        类型 0内链 1外链 2非法外链
	* @return: Integer
	*/
	public Integer getStatus(){
		return status;	
	}
	
	/**
	* @DatabasetableColumnName: tf_link_item:status
	* @Description: 设置属性        类型 0内链 1外链 2非法外链
	* @return: Integer
	*/
	public void setStatus(Integer status){
		this.status = status;	
	}	
	/**
	* @DatabasetableColumnName: tf_link_item:anchor
	* @Description: 获取属性        
	* @return: String
	*/
	public String getAnchor(){
		return anchor;	
	}
	
	/**
	* @DatabasetableColumnName: tf_link_item:anchor
	* @Description: 设置属性        
	* @return: String
	*/
	public void setAnchor(String anchor){
		this.anchor = anchor;	
	}	
	/**
	* @DatabasetableColumnName: tf_link_item:link
	* @Description: 获取属性        链接
	* @return: String
	*/
	public String getLink(){
		return link;	
	}
	
	/**
	* @DatabasetableColumnName: tf_link_item:link
	* @Description: 设置属性        链接
	* @return: String
	*/
	public void setLink(String link){
		this.link = link;	
	}	
	
	
	
	
}

