package com.hnjing.full.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.full.model.entity.MonitorOutline;

/**
 * @ClassName: MonitorOutline
 * @Description: 全站检测概要服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
public interface MonitorOutlineService {

    /**
	 * @Title: addMonitorOutline
	 * @Description:添加全站检测概要
	 * @param monitorOutline 实体
	 * @return Integer
	 */
	MonitorOutline addMonitorOutline(MonitorOutline monitorOutline);
	
	/**
	 * @Title modifyMonitorOutline
	 * @Description:修改全站检测概要
	 * @param monitorOutline 实体
	 * @return Integer
	 */
	Integer modifyMonitorOutline(MonitorOutline monitorOutline);
	
	/**
	 * @Title: dropMonitorOutlineById
	 * @Description:删除全站检测概要
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropMonitorOutlineById(Integer id);
	
	/**
	 * @Title: queryMonitorOutlineById
	 * @Description:根据实体标识查询全站检测概要
	 * @param id 实体标识
	 * @return MonitorOutline
	 */
	MonitorOutline queryMonitorOutlineById(Integer id);
	 
	/**
	 * @Title: queryMonitorOutlineForPage
	 * @Description: 根据全站检测概要属性与分页信息分页查询全站检测概要信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param monitorOutline 实体
	 * @return List<MonitorOutline>
	 */
	Map<String, Object> queryMonitorOutlineForPage(Integer pagenum, Integer pagesize, String sort, MonitorOutline monitorOutline);
	 
	 /**
	 * @Title: queryMonitorOutlineByProperty
	 * @Description:根据属性查询全站检测概要
	 * @return List<MonitorOutline>
	 */
	 List<MonitorOutline> queryMonitorOutlineByProperty(Map<String, Object> map);	 
	 
	 
	 /** 
	* @Title: queryOneNeedCheckPage 
	* @Description: 
	* @return  
	* Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> queryOneNeedCheckPage();

	/** 
	* @Title: queryMonitorOutlineByPage 
	* @Description: 
	* @param page
	* @return  
	* MonitorOutline    返回类型 
	* @throws 
	*/
	MonitorOutline queryMonitorOutlineByPage(String page);

	/** 
	* @Title: exportMonitorOutlineByProperty 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param transBean2Map
	* @return  
	* Object    返回类型 
	* @throws 
	*/
	HSSFWorkbook exportMonitorOutlineByProperty(Map<String, Object> map);
	
	
	/** 
	* @Title: queryDataBeforeDay 
	* @Description: 查询指定多少天以前的全站检测数据
	* @param days
	* @return  
	* List<MonitorOutline>    返回类型 
	* @throws 
	*/
	List<MonitorOutline> queryDataBeforeDay(Integer days);
}
