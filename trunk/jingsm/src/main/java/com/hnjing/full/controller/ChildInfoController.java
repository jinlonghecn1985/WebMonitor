package com.hnjing.full.controller;

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
import com.hnjing.full.model.entity.ChildInfo;
import com.hnjing.full.service.ChildInfoService;
import com.hnjing.utils.ClassUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: ChildInfoController
 * @Description: 表tf_child_infoHTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
@RestController
@Api(description="全站检测-子链信息")
public class ChildInfoController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private ChildInfoService childInfoService;

	
	@ApiOperation(value = "新增 添加表tf_child_info信息", notes = "添加表tf_child_info信息")
	@RequestMapping(value = "/childinfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addChildInfo(HttpServletResponse response,
			@ApiParam(value = "childInfo") @RequestBody ChildInfo childInfo) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		childInfo.setId(null);
		childInfoService.addChildInfo(childInfo);
		return childInfo;
	}
	
	
	@ApiOperation(value = "更新 根据表tf_child_info标识更新表tf_child_info信息", notes = "根据表tf_child_info标识更新表tf_child_info信息")
	@RequestMapping(value = "/childinfo/{id:.+}", method = RequestMethod.PUT)
	public Object modifyChildInfoById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "childInfo", required = true) @RequestBody ChildInfo childInfo
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		ChildInfo tempChildInfo = childInfoService.queryChildInfoById(id);
		childInfo.setId(id);
		if(null == tempChildInfo){
			throw new NotFoundException("表tf_child_info");
		}
		return childInfoService.modifyChildInfo(childInfo);
	}

	@ApiOperation(value = "删除 根据表tf_child_info标识删除表tf_child_info信息", notes = "根据表tf_child_info标识删除表tf_child_info信息")
	@RequestMapping(value = "/childinfo/{id:.+}", method = RequestMethod.DELETE)
	public Object dropChildInfoById(HttpServletResponse response, @PathVariable Integer id) {
		ChildInfo childInfo = childInfoService.queryChildInfoById(id);
		if(null == childInfo){
			throw new NotFoundException("表tf_child_info");
		}
		return childInfoService.dropChildInfoById(id);
	}
	
	@ApiOperation(value = "查询 根据表tf_child_info标识查询表tf_child_info信息", notes = "根据表tf_child_info标识查询表tf_child_info信息")
	@RequestMapping(value = "/childinfo/{id:.+}", method = RequestMethod.GET)
	public Object queryChildInfoById(HttpServletResponse response,
			@PathVariable Integer id) {
		ChildInfo childInfo = childInfoService.queryChildInfoById(id);
		if(null == childInfo){
			throw new NotFoundException("表tf_child_info");
		}
		return childInfo;
	}
	
	@ApiOperation(value = "查询 根据表tf_child_info属性查询表tf_child_info信息列表", notes = "根据表tf_child_info属性查询表tf_child_info信息列表")
	@RequestMapping(value = "/childinfo", method = RequestMethod.GET)
	public Object queryChildInfoList(HttpServletResponse response,
			ChildInfo childInfo) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return childInfoService.queryChildInfoByProperty(ClassUtil.transBean2Map(childInfo, false));
	}
	
	@ApiOperation(value = "查询分页 根据表tf_child_info属性分页查询表tf_child_info信息列表", notes = "根据表tf_child_info属性分页查询表tf_child_info信息列表")
	@RequestMapping(value = "/childinfos", method = RequestMethod.GET)
	public Object queryChildInfoPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, ChildInfo childInfo) {				
		return childInfoService.queryChildInfoForPage(pagenum, pagesize, sort, childInfo);
	}

}
