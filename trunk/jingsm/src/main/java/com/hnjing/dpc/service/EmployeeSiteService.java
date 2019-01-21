package com.hnjing.dpc.service;

import java.util.List;
import java.util.Map;


import com.hnjing.dpc.model.entity.EmployeeSite;

/**
 * @ClassName: EmployeeSite
 * @Description: 员工十分信息服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 10时54分
 */
public interface EmployeeSiteService {

    /**
	 * @Title: addEmployeeSite
	 * @Description:添加员工十分信息
	 * @param employeeSite 实体
	 * @return Integer
	 */
	EmployeeSite addEmployeeSite(EmployeeSite employeeSite);
	
	/**
	 * @Title modifyEmployeeSite
	 * @Description:修改员工十分信息
	 * @param employeeSite 实体
	 * @return Integer
	 */
	Integer modifyEmployeeSite(EmployeeSite employeeSite);
	
	/**
	 * @Title: dropEmployeeSiteByEmpId
	 * @Description:删除员工十分信息
	 * @param empId 实体标识
	 * @return Integer
	 */
	Integer dropEmployeeSiteById(Integer empId);
	
	/**
	 * @Title: queryEmployeeSiteByEmpId
	 * @Description:根据实体标识查询员工十分信息
	 * @param empId 实体标识
	 * @return EmployeeSite
	 */
	EmployeeSite queryEmployeeSiteById(Integer empId);
	 
	/**
	 * @Title: queryEmployeeSiteForPage
	 * @Description: 根据员工十分信息属性与分页信息分页查询员工十分信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param employeeSite 实体
	 * @return List<EmployeeSite>
	 */
	Map<String, Object> queryEmployeeSiteForPage(Integer pagenum, Integer pagesize, String sort, EmployeeSite employeeSite);
	 
	 /**
	 * @Title: queryEmployeeSiteByProperty
	 * @Description:根据属性查询员工十分信息
	 * @return List<EmployeeSite>
	 */
	 List<EmployeeSite> queryEmployeeSiteByProperty(Map<String, Object> map);	 
	
	 /** 
	* @Title: bindEmployeeSite 
	* @Description: 根据员工工号及SF数据
	* @param employeeSite
	* @return  
	* EmployeeSite    返回类型 
	* @throws 
	*/
	EmployeeSite bindEmployeeSite(EmployeeSite employeeSite);

	/** 
	* @Title: queryEmployeeSiteBySiteId 
	* @Description: 根据站点标识获取
	* @param siteId
	* @return  
	* EmployeeSite    返回类型 
	* @throws 
	*/
	EmployeeSite queryEmployeeSiteBySiteId(Integer siteId);
}
