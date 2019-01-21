package com.hnjing.ws.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ws.model.entity.SiteStatistics;

/**
 * @ClassName: SiteStatisticsMapper
 * @Description: 检测结果统计映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Mapper
public interface SiteStatisticsMapper {

	/**
	 * @Title: addSiteStatistics
	 * @Description:添加检测结果统计
	 * @param siteStatistics 实体
	 * @return Integer
	 */
	Integer addSiteStatistics(SiteStatistics siteStatistics);
	
	/**
	 * @Title modifySiteStatistics
	 * @Description:修改检测结果统计
	 * @param siteStatistics 实体
	 * @return Integer
	 */
	Integer modifySiteStatistics(SiteStatistics siteStatistics);
	
	/**
	 * @Title: dropSiteStatisticsById
	 * @Description:删除检测结果统计
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropSiteStatisticsById(String id);
	
	/**
	 * @Title: querySiteStatisticsById
	 * @Description:根据实体标识查询检测结果统计
	 * @param id 实体标识
	 * @return SiteStatistics
	 */
	SiteStatistics querySiteStatisticsById(String id);
	 
	/**
	 * @Title: querySiteStatisticsForPage
	 * @Description: 根据检测结果统计属性与分页信息分页查询检测结果统计信息
	 * @param pageBounds 分页信息
	 * @param siteStatistics 实体
	 * @return List<SiteStatistics>
	 */
	List<SiteStatistics> querySiteStatisticsForPage(PageBounds pageBounds, @Param("siteStatistics") SiteStatistics siteStatistics);
	 
	 /**
	  * @Title: querySiteStatisticsByProperty
	  * @Description:根据属性查询检测结果统计
	  * @return List<SiteStatistics>
	  */
	 List<SiteStatistics> querySiteStatisticsByProperty(@Param("siteStatistics") Map<String, Object> map);
	 
	 /** 
	* @Title: queryNeedContinueCheckInfo 
	* @Description: 获取最新待检测完成数据
	* @return  
	* SiteStatistics    返回类型 
	* @throws 
	*/
	SiteStatistics queryNeedContinueCheckInfo();
	
	
	/** 
	* @Title: queryLastSiteStatistics 
	* @Description: 查询指定来源最新一次检测信息
	* @param source
	* @return  
	* SiteStatistics    返回类型 
	* @throws 
	*/
	SiteStatistics queryLastSiteStatistics(@Param("source") Integer source);
	 
}
