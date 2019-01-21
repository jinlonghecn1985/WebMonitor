package com.hnjing.cw.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: SensitiveWord
 * @Description: 敏感词实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
public class SensitiveWord implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tb_sensitive_word:id  标识  

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String keyWord;	//tb_sensitive_word:key_word    

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String whiteWords;	//tb_sensitive_word:white_words    

	private Integer status;	//tb_sensitive_word:status  状态 0正常 1停用  

	private Date gmtCreated;	//tb_sensitive_word:gmt_created  新增时间  

	private Date gmtModify;	//tb_sensitive_word:gmt_modify  修改时间  


	/**
	* @DatabasetableColumnName: tb_sensitive_word:id
	* @Description: 获取属性        标识
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:id
	* @Description: 设置属性        标识
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:key_word
	* @Description: 获取属性        
	* @return: String
	*/
	public String getKeyWord(){
		return keyWord;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:key_word
	* @Description: 设置属性        
	* @return: String
	*/
	public void setKeyWord(String keyWord){
		this.keyWord = keyWord;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:white_words
	* @Description: 获取属性        
	* @return: String
	*/
	public String getWhiteWords(){
		return whiteWords;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:white_words
	* @Description: 设置属性        
	* @return: String
	*/
	public void setWhiteWords(String whiteWords){
		this.whiteWords = whiteWords;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:status
	* @Description: 获取属性        状态 0正常 1停用
	* @return: Integer
	*/
	public Integer getStatus(){
		return status;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:status
	* @Description: 设置属性        状态 0正常 1停用
	* @return: Integer
	*/
	public void setStatus(Integer status){
		this.status = status;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:gmt_created
	* @Description: 获取属性        新增时间
	* @return: Date
	*/
	public Date getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:gmt_created
	* @Description: 设置属性        新增时间
	* @return: Date
	*/
	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:gmt_modify
	* @Description: 获取属性        修改时间
	* @return: Date
	*/
	public Date getGmtModify(){
		return gmtModify;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_word:gmt_modify
	* @Description: 设置属性        修改时间
	* @return: Date
	*/
	public void setGmtModify(Date gmtModify){
		this.gmtModify = gmtModify;	
	}	
	
	private String[] wss;
	public String[] gotWhiteWords() {
		if(wss==null) {
			if(whiteWords!=null && whiteWords.length()>0) {
				wss = whiteWords.replaceAll("，", ",").split(",");
				if(wss!=null && wss.length>0) {
					for(int i=0; i<wss.length; i++) {
						wss[i] = wss[i].replaceAll("%2c", ","); //正则表达式匹配
					}
				}
			}else {
				wss = new String[0];
			}
		}
		return wss;
	}
	
	
}

