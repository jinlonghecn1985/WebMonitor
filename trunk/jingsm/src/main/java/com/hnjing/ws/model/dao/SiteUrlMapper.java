package com.hnjing.ws.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ws.model.entity.SiteUrl;

/**
 * @ClassName: SiteUrlMapper
 * @Description: 待检测网站信息映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Mapper
public interface SiteUrlMapper {

	/**
	 * @Title: addSiteUrl
	 * @Description:添加待检测网站信息
	 * @param siteUrl 实体
	 * @return Integer
	 */
	Integer addSiteUrl(SiteUrl siteUrl);
	
	/**
	 * @Title modifySiteUrl
	 * @Description:修改待检测网站信息
	 * @param siteUrl 实体
	 * @return Integer
	 */
	Integer modifySiteUrl(SiteUrl siteUrl);
	
	/** 
	* @Title: modifySiteUrlIP 
	* @Description: 修改待检测网站IP信息
	* @param siteUrl
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer modifySiteUrlIP(SiteUrl siteUrl);
	
	
	/**
	 * @Title: dropSiteUrlById
	 * @Description:删除待检测网站信息
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropSiteUrlById(Integer id);
	
	/**
	 * @Title: querySiteUrlById
	 * @Description:根据实体标识查询待检测网站信息
	 * @param id 实体标识
	 * @return SiteUrl
	 */
	SiteUrl querySiteUrlById(Integer id);
	 
	/**
	 * @Title: querySiteUrlForPage
	 * @Description: 根据待检测网站信息属性与分页信息分页查询待检测网站信息信息
	 * @param pageBounds 分页信息
	 * @param siteUrl 实体
	 * @return List<SiteUrl>
	 */
	List<SiteUrl> querySiteUrlForPage(PageBounds pageBounds, @Param("siteUrl") Map<String, Object> map);
	 
	 /**
	  * @Title: querySiteUrlByProperty
	  * @Description:根据属性查询待检测网站信息
	  * @return List<SiteUrl>
	  */
	 List<SiteUrl> querySiteUrlByProperty(@Param("siteUrl") Map<String, Object> map);
	 
	 /**
	  * @Title: queryWaitCheckSiteUrlByProperty
	  * @Description:根据属性查询待检测网站信息--当天已检测数据排除
	  * @return List<SiteUrl>
	  */
	 List<SiteUrl> queryWaitCheckSiteUrlByProperty(@Param("siteUrl") Map<String, Object> map);
	 
	 /** 
		* @Title: initSiteUrlIPStatus 
		* @Description: 初始IP检测状态
		* @param list
		* @return  
		* Integer    返回类型 
		* @throws 
		*/
	Integer initSiteUrlIPStatus(@Param("val") Integer status);
	
	/** 
	* @Title: modifySiteUrlIPSOnBatch 
	* @Description: 批量变更IP
	* @param list
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer modifySiteUrlIPSOnBatch(@Param("list") List<SiteUrl> list);
	
	/** 
	* @Title: querySiteUrlCount 
	* @Description: 查询待检测IP
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer querySiteUrlCount(@Param("siteUrl") Map<String, Object> map);
	
	/** 
	* @Title: queryReCheckIPSite 
	* @Description: 查询待重检测IP网站
	* @return  
	* List<SiteUrl>    返回类型 
	* @throws 
	*/
	List<SiteUrl> queryReCheckIPSite();
	
	/** 
	* @Title: initNeedCheckZero 
	* @Description: 初始化待检测规则为0
	* @param source 
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer initNeedCheckZero(@Param("source") Integer source);
	
	/** 
	* @Title: initNeedCheckPt 
	* @Description: 根据网站IP是否我司处理待检测网站
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer initNeedCheckPt();
	
	/** 
	* @Title: initSelfSite 
	* @Description: 判断我司网站
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer initSelfSite();
	
	/** 
	* @Title: initSiteUrlSelfSite 
	* @Description: 初始化是否我司ＩＰ为否
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer initSiteUrlSelfSite();
	 
	 
}
