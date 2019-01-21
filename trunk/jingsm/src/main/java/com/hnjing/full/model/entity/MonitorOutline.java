package com.hnjing.full.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: MonitorOutline
 * @Description: 全站检测概要实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
public class MonitorOutline implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;	//tf_monitor_outline:id  自增标识  

	private Integer siteId;	//tf_monitor_outline:site_id  检测网站标识  

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String page;	//tf_monitor_outline:page  检测网页  

	@Length(min=0, max=128, message="{org.hibernate.validator.constraints.Length.message}")
	private String acceptMail;	//tf_monitor_outline:accept_mail  收件人，多个收件人之间分号分隔  

	private Integer checkLevel;	//tf_monitor_outline:check_level  检测深度

	private Integer errorPage;

	private Integer innerPage;	//tf_monitor_outline:inner_page  检测子页面  

	private Integer outerPage;	//tf_monitor_outline:outer_page  外链数量  

	private Integer keyWord;	//tf_monitor_outline:key_word  敏感词总量  

	private Integer illegalWord;	//tf_monitor_outline:illegal_word  疑似非法用词  

	private Date gmtCreated;	//tf_monitor_outline:gmt_created  创建时间  

	private Date gmtModify;	//tf_monitor_outline:gmt_modify  修订时间 
	
	private String ip;
	private Integer selfSite;


	/**
	* @DatabasetableColumnName: tf_monitor_outline:id
	* @Description: 获取属性        自增标识
	* @return: Integer
	*/
	public Integer getId(){
		return id;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:id
	* @Description: 设置属性        自增标识
	* @return: Integer
	*/
	public void setId(Integer id){
		this.id = id;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:site_id
	* @Description: 获取属性        检测网站标识
	* @return: Integer
	*/
	public Integer getSiteId(){
		return siteId;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:site_id
	* @Description: 设置属性        检测网站标识
	* @return: Integer
	*/
	public void setSiteId(Integer siteId){
		this.siteId = siteId;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:page
	* @Description: 获取属性        检测网页
	* @return: String
	*/
	public String getPage(){
		return page;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:page
	* @Description: 设置属性        检测网页
	* @return: String
	*/
	public void setPage(String page){
		this.page = page;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:accept_mail
	* @Description: 获取属性        收件人，多个收件人之间分号分隔
	* @return: String
	*/
	public String getAcceptMail(){
		return acceptMail;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:accept_mail
	* @Description: 设置属性        收件人，多个收件人之间分号分隔
	* @return: String
	*/
	public void setAcceptMail(String acceptMail){
		this.acceptMail = acceptMail;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:check_level
	* @Description: 获取属性        检测深度
	* @return: Integer
	*/
	public Integer getCheckLevel(){
		return checkLevel;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:check_level
	* @Description: 设置属性        检测深度
	* @return: Integer
	*/
	public void setCheckLevel(Integer checkLevel){
		this.checkLevel = checkLevel;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:inner_page
	* @Description: 获取属性        检测子页面
	* @return: Integer
	*/
	public Integer getInnerPage(){
//		if(innerPage==null) {
//			return 0;
//		}
		return innerPage;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:inner_page
	* @Description: 设置属性        检测子页面
	* @return: Integer
	*/
	public void setInnerPage(Integer innerPage){
		this.innerPage = innerPage;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:outer_page
	* @Description: 获取属性        外链数量
	* @return: Integer
	*/
	public Integer getOuterPage(){
//		if(outerPage==null) {
//			return 0;
//		}
		return outerPage;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:outer_page
	* @Description: 设置属性        外链数量
	* @return: Integer
	*/
	public void setOuterPage(Integer outerPage){
		this.outerPage = outerPage;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:key_word
	* @Description: 获取属性        敏感词总量
	* @return: Integer
	*/
	public Integer getKeyWord(){
//		if(keyWord==null) {
//			return 0;
//		}
		return keyWord;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:key_word
	* @Description: 设置属性        敏感词总量
	* @return: Integer
	*/
	public void setKeyWord(Integer keyWord){
		this.keyWord = keyWord;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:illegal_word
	* @Description: 获取属性        疑似非法用词
	* @return: Integer
	*/
	public Integer getIllegalWord(){
//		if(illegalWord==null) {
//			return 0;
//		}
		return illegalWord;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:illegal_word
	* @Description: 设置属性        疑似非法用词
	* @return: Integer
	*/
	public void setIllegalWord(Integer illegalWord){
		this.illegalWord = illegalWord;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:gmt_created
	* @Description: 获取属性        创建时间
	* @return: Date
	*/
	public Date getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:gmt_created
	* @Description: 设置属性        创建时间
	* @return: Date
	*/
	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:gmt_modify
	* @Description: 获取属性        修订时间
	* @return: Date
	*/
	public Date getGmtModify(){
		return gmtModify;	
	}
	
	/**
	* @DatabasetableColumnName: tf_monitor_outline:gmt_modify
	* @Description: 设置属性        修订时间
	* @return: Date
	*/
	public void setGmtModify(Date gmtModify){
		this.gmtModify = gmtModify;	
	}

	/**
	 * @return the selfSite
	 */
	public Integer getSelfSite() {
		return selfSite;
	}

	/**
	 * @param selfSite the selfSite to set
	 */
	public void setSelfSite(Integer selfSite) {
		this.selfSite = selfSite;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the errorPage
	 */
	public Integer getErrorPage() {
		return errorPage;
	}

	/**
	 * @param errorPage the errorPage to set
	 */
	public void setErrorPage(Integer errorPage) {
		this.errorPage = errorPage;
	}	
	
	
	
	
}

