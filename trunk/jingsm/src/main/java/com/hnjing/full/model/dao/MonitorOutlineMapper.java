package com.hnjing.full.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.full.model.entity.MonitorOutline;

/**
 * @ClassName: MonitorOutlineMapper
 * @Description: 全站检测概要映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
@Mapper
public interface MonitorOutlineMapper {

	/**
	 * @Title: addMonitorOutline
	 * @Description:添加全站检测概要
	 * @param monitorOutline 实体
	 * @return Integer
	 */
	Integer addMonitorOutline(MonitorOutline monitorOutline);
	
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
	 * @param pageBounds 分页信息
	 * @param monitorOutline 实体
	 * @return List<MonitorOutline>
	 */
	List<MonitorOutline> queryMonitorOutlineForPage(PageBounds pageBounds, @Param("monitorOutline") MonitorOutline monitorOutline);
	 
	 /**
	  * @Title: queryMonitorOutlineByProperty
	  * @Description:根据属性查询全站检测概要
	  * @return List<MonitorOutline>
	  */
	 List<MonitorOutline> queryMonitorOutlineByProperty(@Param("monitorOutline") Map<String, Object> map);
	 
	 Map<String, Object> queryOneNeedCheckPage();

	/** 
	* @Title: queryMonitorOutlineByPage 
	* @Description: 
	* @param page
	* @return  
	* MonitorOutline    返回类型 
	* @throws 
	*/
	MonitorOutline queryMonitorOutlineByPage(@Param("page") String page);
	
	/** 
	* @Title: queryDataBeforeDay 
	* @Description: 查询指定多少天以前的全站检测数据
	* @param days
	* @return  
	* List<MonitorOutline>    返回类型 
	* @throws 
	*/
	List<MonitorOutline> queryDataBeforeDay(@Param("days") Integer days);
	 
}
