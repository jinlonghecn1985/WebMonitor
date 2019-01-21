package com.hnjing.dpc.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.dpc.model.entity.Employee;

/**
 * @ClassName: EmployeeMapper
 * @Description: 员工信息映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 10时54分
 */
@Mapper
public interface EmployeeMapper {

	/**
	 * @Title: addEmployee
	 * @Description:添加员工信息
	 * @param employee 实体
	 * @return Integer
	 */
	Integer addEmployee(Employee employee);
	
	/**
	 * @Title modifyEmployee
	 * @Description:修改员工信息
	 * @param employee 实体
	 * @return Integer
	 */
	Integer modifyEmployee(Employee employee);
	
	/**
	 * @Title: dropEmployeeById
	 * @Description:删除员工信息
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropEmployeeById(Integer id);
	
	/**
	 * @Title: queryEmployeeById
	 * @Description:根据实体标识查询员工信息
	 * @param id 实体标识
	 * @return Employee
	 */
	Employee queryEmployeeById(Integer id);
	 
	/**
	 * @Title: queryEmployeeForPage
	 * @Description: 根据员工信息属性与分页信息分页查询员工信息信息
	 * @param pageBounds 分页信息
	 * @param employee 实体
	 * @return List<Employee>
	 */
	List<Employee> queryEmployeeForPage(PageBounds pageBounds, @Param("employee") Employee employee);
	 
	 /**
	  * @Title: queryEmployeeByProperty
	  * @Description:根据属性查询员工信息
	  * @return List<Employee>
	  */
	 List<Employee> queryEmployeeByProperty(@Param("employee") Map<String, Object> map);
	 
	 
	 
}
