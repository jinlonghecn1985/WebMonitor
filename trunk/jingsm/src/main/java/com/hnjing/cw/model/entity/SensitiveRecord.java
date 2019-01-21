package com.hnjing.cw.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: SensitiveRecord
 * @Description: 敏感词记录实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
public class SensitiveRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tb_sensitive_record:id  标识  

	private Integer siteId;	//tb_sensitive_record:site_id  站点标识  

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String page;	//tb_sensitive_record:page  页面  

	private Integer kwId;	//tb_sensitive_record:kw_id  词标识  

	@Length(min=0, max=255, message="{org.hibernate.validator.constraints.Length.message}")
	private String keyWord;	//tb_sensitive_record:key_word  词  

	@Length(min=0, max=255, message="{org.hibernate.validator.constraints.Length.message}")
	private String keyWords;	//tb_sensitive_record:key_words  语境  

	private Integer start;	//tb_sensitive_record:start  起点  

	private Integer end;	//tb_sensitive_record:end  结束点  

	private Integer status;	//tb_sensitive_record:status  状态  

	private Date gmtCreated;	//tb_sensitive_record:gmt_created  触发时间  


	/**
	* @DatabasetableColumnName: tb_sensitive_record:id
	* @Description: 获取属性        标识
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:id
	* @Description: 设置属性        标识
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:site_id
	* @Description: 获取属性        站点标识
	* @return: Integer
	*/
	public Integer getSiteId(){
		return siteId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:site_id
	* @Description: 设置属性        站点标识
	* @return: Integer
	*/
	public void setSiteId(Integer siteId){
		this.siteId = siteId;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:page
	* @Description: 获取属性        页面
	* @return: String
	*/
	public String getPage(){
		return page;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:page
	* @Description: 设置属性        页面
	* @return: String
	*/
	public void setPage(String page){
		this.page = page;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:kw_id
	* @Description: 获取属性        词标识
	* @return: Integer
	*/
	public Integer getKwId(){
		return kwId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:kw_id
	* @Description: 设置属性        词标识
	* @return: Integer
	*/
	public void setKwId(Integer kwId){
		this.kwId = kwId;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:key_word
	* @Description: 获取属性        词
	* @return: String
	*/
	public String getKeyWord(){
		return keyWord;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:key_word
	* @Description: 设置属性        词
	* @return: String
	*/
	public void setKeyWord(String keyWord){
		this.keyWord = keyWord;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:key_words
	* @Description: 获取属性        语境
	* @return: String
	*/
	public String getKeyWords(){
		return keyWords;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:key_words
	* @Description: 设置属性        语境
	* @return: String
	*/
	public void setKeyWords(String keyWords){
		this.keyWords = keyWords;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:start
	* @Description: 获取属性        起点
	* @return: Integer
	*/
	public Integer getStart(){
		return start;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:start
	* @Description: 设置属性        起点
	* @return: Integer
	*/
	public void setStart(Integer start){
		this.start = start;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:end
	* @Description: 获取属性        结束点
	* @return: Integer
	*/
	public Integer getEnd(){
		return end;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:end
	* @Description: 设置属性        结束点
	* @return: Integer
	*/
	public void setEnd(Integer end){
		this.end = end;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:status
	* @Description: 获取属性        状态
	* @return: Integer
	*/
	public Integer getStatus(){
		return status;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:status
	* @Description: 设置属性        状态
	* @return: Integer
	*/
	public void setStatus(Integer status){
		this.status = status;	
	}	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:gmt_created
	* @Description: 获取属性        触发时间
	* @return: Date
	*/
	public Date getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_sensitive_record:gmt_created
	* @Description: 设置属性        触发时间
	* @return: Date
	*/
	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	
	
	
	
}

