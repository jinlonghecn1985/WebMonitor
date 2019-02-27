package com.hnjing.ws.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.ws.model.entity.SiteHistory;

/**
 * @ClassName: SiteHistory
 * @Description: 异常网站历史记录服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
public interface SiteHistoryService {

    /**
	 * @Title: addSiteHistory
	 * @Description:添加异常网站历史记录
	 * @param siteHistory 实体
	 * @return Integer
	 */
	SiteHistory addSiteHistory(SiteHistory siteHistory);
	
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
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param siteHistory 实体
	 * @return List<SiteHistory>
	 */
	Map<String, Object> querySiteHistoryForPage(Integer pagenum, Integer pagesize, String sort, Map<String, Object> siteHistory);
	 
	 /**
	 * @Title: querySiteHistoryByProperty
	 * @Description:根据属性查询异常网站历史记录
	 * @return List<SiteHistory>
	 */
	 List<SiteHistory> querySiteHistoryByProperty(Map<String, Object> map);	 
	 
	 
	/** 
	* @Title: bakCheckDate 
	* @Description: 备份检测记录
	* @param source 检测源
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer bakCheckDate(Integer source);
	
	
	List<Map<String, String>> queryHistoryInfoByStatisticsID(String statisticsID);

	/** 
	* @Title: exportByStatisticsID 
	* @Description: 
	* @param statisticsID
	* @return  
	* HSSFWorkbook    返回类型 
	* @throws 
	*/
	HSSFWorkbook exportByStatisticsID(String statisticsID);
	 
}
