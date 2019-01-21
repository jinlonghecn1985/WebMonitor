package com.hnjing.ws.service;

import java.util.List;
import java.util.Map;

import com.hnjing.ws.model.entity.SiteResult;

/**
 * @ClassName: SiteResult
 * @Description: 检测结果服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
public interface SiteResultService {

    /**
	 * @Title: addSiteResult
	 * @Description:添加检测结果
	 * @param siteResult 实体
	 * @return Integer
	 */
	SiteResult addSiteResult(SiteResult siteResult);
	
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
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param siteResult 实体
	 * @return List<SiteResult>
	 */
	Map<String, Object> querySiteResultForPage(Integer pagenum, Integer pagesize, String sort, Map<String, Object> siteResult);
	 
	 /**
	 * @Title: querySiteResultByProperty
	 * @Description:根据属性查询检测结果
	 * @return List<SiteResult>
	 */
	 List<SiteResult> querySiteResultByProperty(Map<String, Object> map);	 
	 
	 /** 
		* @Title: querySiteResultCountByProperty 
		* @Description: 根据属性查询数据量 
		* @param map
		* @return  
		* Integer    返回类型 
		* @throws 
		*/
		Integer querySiteResultCountByProperty(Map<String, Object> map);

	 /** 
	* @Title: addSiteResultOnBatch 
	* @Description: 批量写入结果 
	* @param resultList
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer addSiteResultOnBatch(List<SiteResult> resultList);

	/** 
	* @Title: clearTodayBeforeHistroy 
	* @Description: 清理历史检测记录
	* @param source
	* @return  
	* int    返回类型 
	* @throws 
	*/
	int clearTodayBeforeHistroy(Integer source);
	
	
	/** 
	* @Title: queryErrorSiteSelf 
	* @Description: 查询异常网站基本信息
	* @return  
	* List<Map>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> queryErrorSiteSelf();
	
	
	/** 
	* @Title: clearTable 
	* @Description: 清空表格 
	* void    返回类型 
	* @throws 
	*/
	Integer clearTable();
	 
}
