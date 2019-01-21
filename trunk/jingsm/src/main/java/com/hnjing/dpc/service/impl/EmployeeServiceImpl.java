package com.hnjing.dpc.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;


import com.hnjing.dpc.model.entity.Employee;
import com.hnjing.dpc.model.dao.EmployeeMapper;
import com.hnjing.dpc.service.EmployeeService;

/**
 * @ClassName: Employee
 * @Description: 员工信息服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 10时54分
 */
@Service("employeeService")
@Transactional(readOnly=true)
public class  EmployeeServiceImpl implements EmployeeService {	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
    private EmployeeMapper employeeMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addEmployee
	 * @Description:添加员工信息
	 * @param employee 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Employee addEmployee(Employee employee){
		int ret = employeeMapper.addEmployee(employee);
		if(ret>0){
			return employee;
		}
		return null;
	}
	
	/**
	 * @Title modifyEmployee
	 * @Description:修改员工信息
	 * @param employee 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyEmployee(Employee employee){
		return employeeMapper.modifyEmployee(employee);
	}
	
	/**
	 * @Title: dropEmployeeById
	 * @Description:删除员工信息
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropEmployeeById(Integer id){
		return employeeMapper.dropEmployeeById(id);
	}
	
	/**
	 * @Title: queryEmployeeById
	 * @Description:根据实体标识查询员工信息
	 * @param id 实体标识
	 * @return Employee
	 */
	@Override
	public Employee queryEmployeeById(Integer id){
		return employeeMapper.queryEmployeeById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryEmployeeForPage
	 * @Description: 根据员工信息属性与分页信息分页查询员工信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param employee 实体
	 * @return List<Employee>
	 */
	@Override
	public Map<String, Object> queryEmployeeForPage(Integer pagenum, Integer pagesize, String sort, Employee employee){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, Employee.class);
		List<Employee> entityList = employeeMapper.queryEmployeeForPage(pageBounds, employee);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, Employee.class);
		}
		
		PageList<Employee> pagelist = (PageList<Employee>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryEmployeeByProperty
	 * @Description:根据属性查询员工信息
	 * @return List<Employee>
	 */
	@Override
	public List<Employee> queryEmployeeByProperty(Map<String, Object> map){
		return employeeMapper.queryEmployeeByProperty(map);
	}

	/*
	 * @Title: bindEmployeeByNO
	 * @Description: 根据员工工号绑定员工信息
	 * @param @param emp
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param emp
	 * @return
	 * @see com.hnjing.dpc.service.EmployeeService#bindEmployeeByNO(com.hnjing.dpc.model.entity.Employee)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Employee bindEmployeeByNO(Employee emp) {
		if(emp==null || emp.getEmplNo()==null) {
			return emp;
		}
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("emplNo", emp.getEmplNo());
		List<Employee> list = queryEmployeeByProperty(query);
		if(list==null || list.size()==0) {
			return addEmployee(emp);
		}else {
			emp.setId(list.get(0).getId());
			modifyEmployee(emp);
			return emp;
		}
		
	}


}
