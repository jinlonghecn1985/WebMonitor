package com.hnjing.dpc.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.dpc.model.entity.EmployeeSite;

/**
 * @ClassName: EmployeeSiteMapper
 * @Description: 员工十分信息映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 10时54分
 */
@Mapper
public interface EmployeeSiteMapper {

	/**
	 * @Title: addEmployeeSite
	 * @Description:添加员工十分信息
	 * @param employeeSite 实体
	 * @return Integer
	 */
	Integer addEmployeeSite(EmployeeSite employeeSite);
	
	/**
	 * @Title modifyEmployeeSite
	 * @Description:修改员工十分信息
	 * @param employeeSite 实体
	 * @return Integer
	 */
	Integer modifyEmployeeSite(EmployeeSite employeeSite);
	
	/**
	 * @Title: dropEmployeeSiteById
	 * @Description:删除员工十分信息
	 * @param empId 实体标识
	 * @return Integer
	 */
	Integer dropEmployeeSiteById(Integer empId);
	
	/**
	 * @Title: queryEmployeeSiteById
	 * @Description:根据实体标识查询员工十分信息
	 * @param empId 实体标识
	 * @return EmployeeSite
	 */
	EmployeeSite queryEmployeeSiteById(Integer empId);
	 
	/**
	 * @Title: queryEmployeeSiteForPage
	 * @Description: 根据员工十分信息属性与分页信息分页查询员工十分信息信息
	 * @param pageBounds 分页信息
	 * @param employeeSite 实体
	 * @return List<EmployeeSite>
	 */
	List<EmployeeSite> queryEmployeeSiteForPage(PageBounds pageBounds, @Param("employeeSite") EmployeeSite employeeSite);
	 
	 /**
	  * @Title: queryEmployeeSiteByProperty
	  * @Description:根据属性查询员工十分信息
	  * @return List<EmployeeSite>
	  */
	 List<EmployeeSite> queryEmployeeSiteByProperty(@Param("employeeSite") Map<String, Object> map);

	/** 
	* @Title: queryEmployeeSiteBySiteId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param siteId
	* @return  
	* EmployeeSite    返回类型 
	* @throws 
	*/
	EmployeeSite queryEmployeeSiteBySiteId(@Param("siteId") Integer siteId);
	 
	 
	 
}
