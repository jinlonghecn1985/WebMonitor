package com.hnjing.ws.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ws.model.entity.SiteHistory;

/**
 * @ClassName: SiteHistoryMapper
 * @Description: 异常网站历史记录映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Mapper
public interface SiteHistoryMapper {

	/**
	 * @Title: addSiteHistory
	 * @Description:添加异常网站历史记录
	 * @param siteHistory 实体
	 * @return Integer
	 */
	Integer addSiteHistory(SiteHistory siteHistory);
	
	/**
	 * @Title modifySiteHistory
	 * @Description:修改异常网站历史记录
	 * @param siteHistory 实体
	 * @return Integer
	 */
	Integer modifySiteHistory(SiteHistory siteHistory);
	
	/**
	 * @Title: dropSiteHistoryBySiteId
	 * @Description:删除异常网站历史记录
	 * @param siteId 实体标识
	 * @return Integer
	 */
	Integer dropSiteHistoryBySiteId(Integer siteId);
	
	/**
	 * @Title: querySiteHistoryBySiteId
	 * @Description:根据实体标识查询异常网站历史记录
	 * @param siteId 实体标识
	 * @return SiteHistory
	 */
	SiteHistory querySiteHistoryBySiteId(Integer siteId);
	 
	/**
	 * @Title: querySiteHistoryForPage
	 * @Description: 根据异常网站历史记录属性与分页信息分页查询异常网站历史记录信息
	 * @param pageBounds 分页信息
	 * @param siteHistory 实体
	 * @return List<SiteHistory>
	 */
	List<SiteHistory> querySiteHistoryForPage(PageBounds pageBounds, @Param("siteHistory") Map<String, Object> map);
	 
	 /**
	  * @Title: querySiteHistoryByProperty
	  * @Description:根据属性查询异常网站历史记录
	  * @return List<SiteHistory>
	  */
	 List<SiteHistory> querySiteHistoryByProperty(@Param("siteHistory") Map<String, Object> map);
	 
	 
	 /** 
	* @Title: recordNomalSite 
	* @Description: 备份正常网站-网站打开慢、安全级别稍高
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer recordNomalSite(@Param("source") Integer source);
	 
	 /** 
	* @Title: recordErrorSite 
	* @Description: 备份异常网站 
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer recordErrorSite(@Param("source") Integer source);

	
	 
}
