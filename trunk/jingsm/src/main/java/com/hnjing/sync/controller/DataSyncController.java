package com.hnjing.sync.controller;

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
import com.hnjing.sync.model.entity.DataSync;
import com.hnjing.sync.service.DataSyncService;
import com.hnjing.utils.ClassUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: DataSyncController
 * @Description: 数据中心HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月26日 17时21分
 */
@RestController
@Api(description="数据中心")
public class DataSyncController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private DataSyncService dataSyncService;

	
	@ApiOperation(value = "新增 添加数据中心信息", notes = "添加数据中心信息")
	@RequestMapping(value = "/datasync", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addDataSync(HttpServletResponse response,
			@ApiParam(value = "dataSync") @RequestBody DataSync dataSync) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		dataSync.setId(null);
		dataSyncService.addDataSync(dataSync);
		return dataSync;
	}
	
	
	@ApiOperation(value = "更新 根据数据中心标识更新数据中心信息", notes = "根据数据中心标识更新数据中心信息")
	@RequestMapping(value = "/datasync/{id:.+}", method = RequestMethod.PUT)
	public Object modifyDataSyncById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "dataSync", required = true) @RequestBody DataSync dataSync
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		DataSync tempDataSync = dataSyncService.queryDataSyncById(id);
		dataSync.setId(id);
		if(null == tempDataSync){
			throw new NotFoundException("数据中心");
		}
		return dataSyncService.modifyDataSync(dataSync);
	}

	@ApiOperation(value = "删除 根据数据中心标识删除数据中心信息", notes = "根据数据中心标识删除数据中心信息")
	@RequestMapping(value = "/datasync/{id:.+}", method = RequestMethod.DELETE)
	public Object dropDataSyncById(HttpServletResponse response, @PathVariable Integer id) {
		DataSync dataSync = dataSyncService.queryDataSyncById(id);
		if(null == dataSync){
			throw new NotFoundException("数据中心");
		}
		return dataSyncService.dropDataSyncById(id);
	}
	
	@ApiOperation(value = "查询 根据数据中心标识查询数据中心信息", notes = "根据数据中心标识查询数据中心信息")
	@RequestMapping(value = "/datasync/{id:.+}", method = RequestMethod.GET)
	public Object queryDataSyncById(HttpServletResponse response,
			@PathVariable Integer id) {
		DataSync dataSync = dataSyncService.queryDataSyncById(id);
		if(null == dataSync){
			throw new NotFoundException("数据中心");
		}
		return dataSync;
	}
	
	@ApiOperation(value = "查询 根据数据中心属性查询数据中心信息列表", notes = "根据数据中心属性查询数据中心信息列表")
	@RequestMapping(value = "/datasync", method = RequestMethod.GET)
	public Object queryDataSyncList(HttpServletResponse response,
			DataSync dataSync) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return dataSyncService.queryDataSyncByProperty(ClassUtil.transBean2Map(dataSync, false));
	}
	
	@ApiOperation(value = "查询分页 根据数据中心属性分页查询数据中心信息列表", notes = "根据数据中心属性分页查询数据中心信息列表")
	@RequestMapping(value = "/datasyncs", method = RequestMethod.GET)
	public Object queryDataSyncPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, DataSync dataSync) {				
		return dataSyncService.queryDataSyncForPage(pagenum, pagesize, sort, dataSync);
	}

}
