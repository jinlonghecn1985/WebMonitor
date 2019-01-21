package com.hnjing.full.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: ChildInfo
 * @Description: 表tf_child_info实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
public class ChildInfo implements Serializable {
private static final long serialVersionUID = 1L;
	

	private Integer id;	//tf_child_info:id  归属检测  

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String page;	//tf_child_info:page  检测页面  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer checkCycle;	//tf_child_info:check_cycle  检测深度  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer checkOrder;	//tf_child_info:check_order  检测序号  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer code;	//tf_child_info:code  检测代码  

	private String content;	//tf_child_info:content  页面内容  

	private Integer innerLink;	//tf_child_info:inner_link  内链  

	private Integer outLink;	//tf_child_info:out_link  外链  

	private Integer whiteWord;	//tf_child_info:white_word  白名单敏感词  

	private Integer illegalWord;	//tf_child_info:illegal_word  疑似敏感词  

	private Date gmtCreated;	//tf_child_info:gmt_created  创建时间  


	/**
	* @DatabasetableColumnName: tf_child_info:id
	* @Description: 获取属性        归属检测
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:id
	* @Description: 设置属性        归属检测
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:page
	* @Description: 获取属性        检测页面
	* @return: String
	*/
	public String getPage(){
		return page;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:page
	* @Description: 设置属性        检测页面
	* @return: String
	*/
	public void setPage(String page){
		this.page = page;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:check_cycle
	* @Description: 获取属性        检测深度
	* @return: Integer
	*/
	public Integer getCheckCycle(){
		return checkCycle;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:check_cycle
	* @Description: 设置属性        检测深度
	* @return: Integer
	*/
	public void setCheckCycle(Integer checkCycle){
		this.checkCycle = checkCycle;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:check_order
	* @Description: 获取属性        检测序号
	* @return: Integer
	*/
	public Integer getCheckOrder(){
		return checkOrder;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:check_order
	* @Description: 设置属性        检测序号
	* @return: Integer
	*/
	public void setCheckOrder(Integer checkOrder){
		this.checkOrder = checkOrder;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:code
	* @Description: 获取属性        检测代码
	* @return: Integer
	*/
	public Integer getCode(){
		return code;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:code
	* @Description: 设置属性        检测代码
	* @return: Integer
	*/
	public void setCode(Integer code){
		this.code = code;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:content
	* @Description: 获取属性        页面内容
	* @return: java.lang.Object
	*/
	public String getContent(){
		return content;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:content
	* @Description: 设置属性        页面内容
	* @return: java.lang.Object
	*/
	public void setContent(String content){
		this.content = content;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:inner_link
	* @Description: 获取属性        内链
	* @return: Integer
	*/
	public Integer getInnerLink(){
		if(innerLink==null) {
			return 0;
		}
		return innerLink;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:inner_link
	* @Description: 设置属性        内链
	* @return: Integer
	*/
	public void setInnerLink(Integer innerLink){
		this.innerLink = innerLink;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:out_link
	* @Description: 获取属性        外链
	* @return: Integer
	*/
	public Integer getOutLink(){
		if(outLink==null) {
			return 0;
		}
		return outLink;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:out_link
	* @Description: 设置属性        外链
	* @return: Integer
	*/
	public void setOutLink(Integer outLink){
		this.outLink = outLink;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:white_word
	* @Description: 获取属性        白名单敏感词
	* @return: Integer
	*/
	public Integer getWhiteWord(){
		if(whiteWord==null) {
			return 0;
		}
		return whiteWord;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:white_word
	* @Description: 设置属性        白名单敏感词
	* @return: Integer
	*/
	public void setWhiteWord(Integer whiteWord){
		this.whiteWord = whiteWord;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:illegal_word
	* @Description: 获取属性        疑似敏感词
	* @return: Integer
	*/
	public Integer getIllegalWord(){
		if(illegalWord==null) {
			return 0;
		}
		return illegalWord;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:illegal_word
	* @Description: 设置属性        疑似敏感词
	* @return: Integer
	*/
	public void setIllegalWord(Integer illegalWord){
		this.illegalWord = illegalWord;	
	}	
	/**
	* @DatabasetableColumnName: tf_child_info:gmt_created
	* @Description: 获取属性        创建时间
	* @return: Date
	*/
	public Date getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tf_child_info:gmt_created
	* @Description: 设置属性        创建时间
	* @return: Date
	*/
	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	
	
	
	
}
