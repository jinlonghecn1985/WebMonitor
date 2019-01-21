package com.hnjing.full.model.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: SensitiveItem
 * @Description: 网页敏感词实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
public class SensitiveItem implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tf_sensitive_item:id  检测源标识  

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String page;	//tf_sensitive_item:page  页面  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer status;	//tf_sensitive_item:status  状态 0疑似 1白名单  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String keyWord;	//tf_sensitive_item:key_word  敏感词  

	@Length(min=0, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String context;	//tf_sensitive_item:context  上下文  


	/**
	* @DatabasetableColumnName: tf_sensitive_item:id
	* @Description: 获取属性        检测源标识
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tf_sensitive_item:id
	* @Description: 设置属性        检测源标识
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tf_sensitive_item:page
	* @Description: 获取属性        页面
	* @return: String
	*/
	public String getPage(){
		return page;	
	}
	
	/**
	* @DatabasetableColumnName: tf_sensitive_item:page
	* @Description: 设置属性        页面
	* @return: String
	*/
	public void setPage(String page){
		this.page = page;	
	}	
	/**
	* @DatabasetableColumnName: tf_sensitive_item:status
	* @Description: 获取属性        状态 0疑似 1白名单
	* @return: Integer
	*/
	public Integer getStatus(){
		return status;	
	}
	
	/**
	* @DatabasetableColumnName: tf_sensitive_item:status
	* @Description: 设置属性        状态 0疑似 1白名单
	* @return: Integer
	*/
	public void setStatus(Integer status){
		this.status = status;	
	}	
	/**
	* @DatabasetableColumnName: tf_sensitive_item:key_word
	* @Description: 获取属性        敏感词
	* @return: String
	*/
	public String getKeyWord(){
		return keyWord;	
	}
	
	/**
	* @DatabasetableColumnName: tf_sensitive_item:key_word
	* @Description: 设置属性        敏感词
	* @return: String
	*/
	public void setKeyWord(String keyWord){
		this.keyWord = keyWord;	
	}	
	/**
	* @DatabasetableColumnName: tf_sensitive_item:context
	* @Description: 获取属性        上下文
	* @return: String
	*/
	public String getContext(){
		return context;	
	}
	
	/**
	* @DatabasetableColumnName: tf_sensitive_item:context
	* @Description: 设置属性        上下文
	* @return: String
	*/
	public void setContext(String context){
		this.context = context;	
	}	
	
	
	
	
}

