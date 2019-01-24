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
import com.hnjing.dpc.model.entity.EmployeeSite;
import com.hnjing.dpc.service.EmployeeSiteService;
import com.hnjing.utils.ClassUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: EmployeeSiteController
 * @Description: 员工十分信息HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 10时54分
 */
@RestController
@Api(description="网站状态-员工网站SF关联信息")
public class EmployeeSiteController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private EmployeeSiteService employeeSiteService;

	
	@ApiOperation(value = "新增 添加员工十分信息信息", notes = "添加员工十分信息信息")
	@RequestMapping(value = "/employeesf", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addEmployeeSite(HttpServletResponse response,
			@ApiParam(value = "employeeSite") @RequestBody EmployeeSite employeeSite) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		employeeSite.setEmpId(null);
		employeeSiteService.addEmployeeSite(employeeSite);
		return employeeSite;
	}
	
	
	@ApiOperation(value = "更新 根据员工十分信息标识更新员工十分信息信息", notes = "根据员工十分信息标识更新员工十分信息信息")
	@RequestMapping(value = "/employeesf/{empId:.+}", method = RequestMethod.PUT)
	public Object modifyEmployeeSiteById(HttpServletResponse response,
			@PathVariable Integer empId,
			@ApiParam(value = "employeeSite", required = true) @RequestBody EmployeeSite employeeSite
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		EmployeeSite tempEmployeeSite = employeeSiteService.queryEmployeeSiteById(empId);
		employeeSite.setEmpId(empId);
		if(null == tempEmployeeSite){
			throw new NotFoundException("员工十分信息");
		}
		return employeeSiteService.modifyEmployeeSite(employeeSite);
	}

	@ApiOperation(value = "删除 根据员工十分信息标识删除员工十分信息信息", notes = "根据员工十分信息标识删除员工十分信息信息")
	@RequestMapping(value = "/employeesf/{empId:.+}", method = RequestMethod.DELETE)
	public Object dropEmployeeSiteByEmpId(HttpServletResponse response, @PathVariable Integer empId) {
		EmployeeSite employeeSite = employeeSiteService.queryEmployeeSiteById(empId);
		if(null == employeeSite){
			throw new NotFoundException("员工十分信息");
		}
		return employeeSiteService.dropEmployeeSiteById(empId);
	}
	
	@ApiOperation(value = "查询 根据员工十分信息标识查询员工十分信息信息", notes = "根据员工十分信息标识查询员工十分信息信息")
	@RequestMapping(value = "/employeesf/{empId:.+}", method = RequestMethod.GET)
	public Object queryEmployeeSiteById(HttpServletResponse response,
			@PathVariable Integer empId) {
		EmployeeSite employeeSite = employeeSiteService.queryEmployeeSiteById(empId);
		if(null == employeeSite){
			throw new NotFoundException("员工十分信息");
		}
		return employeeSite;
	}
	
	@ApiOperation(value = "查询 根据员工十分信息属性查询员工十分信息信息列表", notes = "根据员工十分信息属性查询员工十分信息信息列表")
	@RequestMapping(value = "/employeesf", method = RequestMethod.GET)
	public Object queryEmployeeSiteList(HttpServletResponse response,
			EmployeeSite employeeSite) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return employeeSiteService.queryEmployeeSiteByProperty(ClassUtil.transBean2Map(employeeSite, false));
	}
	
	@ApiOperation(value = "查询分页 根据员工十分信息属性分页查询员工十分信息信息列表", notes = "根据员工十分信息属性分页查询员工十分信息信息列表")
	@RequestMapping(value = "/employeesfs", method = RequestMethod.GET)
	public Object queryEmployeeSitePage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, EmployeeSite employeeSite) {				
		return employeeSiteService.queryEmployeeSiteForPage(pagenum, pagesize, sort, employeeSite);
	}

}
