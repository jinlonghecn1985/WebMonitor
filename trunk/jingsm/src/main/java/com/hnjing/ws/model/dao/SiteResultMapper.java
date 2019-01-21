package com.hnjing.ws.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ws.model.entity.SiteResult;

/**
 * @ClassName: SiteResultMapper
 * @Description: 检测结果映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Mapper
public interface SiteResultMapper {

	/**
	 * @Title: addSiteResult
	 * @Description:添加检测结果
	 * @param siteResult 实体
	 * @return Integer
	 */
	Integer addSiteResult(SiteResult siteResult);
	
	/**
	 * @Title modifySiteResult
	 * @Description:修改检测结果
	 * @param siteResult 实体
	 * @return Integer
	 */
	Integer modifySiteResult(SiteResult siteResult);
	
	/**
	 * @Title: dropSiteResultBySiteId
	 * @Description:删除检测结果
	 * @param siteId 实体标识
	 * @return Integer
	 */
	Integer dropSiteResultBySiteId(Integer siteId);
	
	/**
	 * @Title: querySiteResultBySiteId
	 * @Description:根据实体标识查询检测结果
	 * @param siteId 实体标识
	 * @return SiteResult
	 */
	SiteResult querySiteResultBySiteId(Integer siteId);
	 
	/**
	 * @Title: querySiteResultForPage
	 * @Description: 根据检测结果属性与分页信息分页查询检测结果信息
	 * @param pageBounds 分页信息
	 * @param siteResult 实体
	 * @return List<SiteResult>
	 */
	List<SiteResult> querySiteResultForPage(PageBounds pageBounds, @Param("siteResult") Map<String, Object> map);
	 
	 /**
	  * @Title: querySiteResultByProperty
	  * @Description:根据属性查询检测结果
	  * @return List<SiteResult>
	  */
	 List<SiteResult> querySiteResultByProperty(@Param("siteResult") Map<String, Object> map);
	 
	 
	 /** 
	* @Title: querySiteResultCountByProperty 
	* @Description: 根据属性查询数据量 
	* @param map
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer querySiteResultCountByProperty(@Param("siteResult") Map<String, Object> map);
	 
	 /** 
		* @Title: addResultOnBatch 
		* @Description: 批量写入结果 
		* @param resultList
		* @return  
		* Integer    返回类型 
		* @throws 
		*/
	Integer addResultOnBatch(@Param("list") List<SiteResult> resultList);
	
	/** 
	* @Title: clearTable 
	* @Description: 清空表格 
	* void    返回类型 
	* @throws 
	*/
	Integer clearTable();
	
	
	/** 
	* @Title: clearTodayBeforeHistroy 
	* @Description: 清空表格中某个来源的检测数据 
	* void    返回类型 
	* @throws 
	*/
	Integer clearTodayBeforeHistroy(@Param("source") Integer source);
	
	/** 
	* @Title: queryErrorSiteSelf 
	* @Description: 查询异常网站基本信息
	* @return  
	* List<Map>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> queryErrorSiteSelf();
	 
}
