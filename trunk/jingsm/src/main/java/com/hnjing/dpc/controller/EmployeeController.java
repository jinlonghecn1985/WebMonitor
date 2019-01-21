package com.hnjing.dpc.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnjing.config.validation.BeanValidator;
import com.hnjing.config.web.exception.NotFoundException;
import com.hnjing.dpc.model.entity.Employee;
import com.hnjing.dpc.service.EmployeeService;
import com.hnjing.utils.ClassUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: EmployeeController
 * @Description: 员工信息HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 10时54分
 */
@RestController
@Api(description="员工信息")
public class EmployeeController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private EmployeeService employeeService;

	
	@ApiOperation(value = "新增 添加员工信息信息", notes = "添加员工信息信息")
	@RequestMapping(value = "/employee", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addEmployee(HttpServletResponse response,
			@ApiParam(value = "employee") @RequestBody Employee employee) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		employee.setId(null);
		employeeService.addEmployee(employee);
		return employee;
	}
	
	
	@ApiOperation(value = "更新 根据员工信息标识更新员工信息信息", notes = "根据员工信息标识更新员工信息信息")
	@RequestMapping(value = "/employee/{id:.+}", method = RequestMethod.PUT)
	public Object modifyEmployeeById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "employee", required = true) @RequestBody Employee employee
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Employee tempEmployee = employeeService.queryEmployeeById(id);
		employee.setId(id);
		if(null == tempEmployee){
			throw new NotFoundException("员工信息");
		}
		return employeeService.modifyEmployee(employee);
	}

	@ApiOperation(value = "删除 根据员工信息标识删除员工信息信息", notes = "根据员工信息标识删除员工信息信息")
	@RequestMapping(value = "/employee/{id:.+}", method = RequestMethod.DELETE)
	public Object dropEmployeeById(HttpServletResponse response, @PathVariable Integer id) {
		Employee employee = employeeService.queryEmployeeById(id);
		if(null == employee){
			throw new NotFoundException("员工信息");
		}
		return employeeService.dropEmployeeById(id);
	}
	
	@ApiOperation(value = "查询 根据员工信息标识查询员工信息信息", notes = "根据员工信息标识查询员工信息信息")
	@RequestMapping(value = "/employee/{id:.+}", method = RequestMethod.GET)
	public Object queryEmployeeById(HttpServletResponse response,
			@PathVariable Integer id) {
		Employee employee = employeeService.queryEmployeeById(id);
		if(null == employee){
			throw new NotFoundException("员工信息");
		}
		return employee;
	}
	
	@ApiOperation(value = "查询 根据员工信息属性查询员工信息信息列表", notes = "根据员工信息属性查询员工信息信息列表")
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Object queryEmployeeList(HttpServletResponse response,
			Employee employee) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return employeeService.queryEmployeeByProperty(ClassUtil.transBean2Map(employee, false));
	}
	
	@ApiOperation(value = "查询分页 根据员工信息属性分页查询员工信息信息列表", notes = "根据员工信息属性分页查询员工信息信息列表")
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public Object queryEmployeePage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, Employee employee) {				
		return employeeService.queryEmployeeForPage(pagenum, pagesize, sort, employee);
	}

}
