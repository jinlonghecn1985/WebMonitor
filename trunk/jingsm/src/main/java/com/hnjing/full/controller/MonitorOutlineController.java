package com.hnjing.full.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnjing.config.validation.BeanValidator;
import com.hnjing.config.web.exception.NotFoundException;
import com.hnjing.full.model.entity.MonitorOutline;
import com.hnjing.full.service.MonitorOutlineService;
import com.hnjing.utils.ClassUtil;
import com.hnjing.utils.HttpTool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: MonitorOutlineController
 * @Description: 全站检测概要HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
@RestController
@Api(description="全站检测-检测整体详情")
public class MonitorOutlineController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private MonitorOutlineService monitorOutlineService;

	
	@ApiOperation(value = "新增 添加全站检测概要信息", notes = "添加全站检测概要信息")
	@RequestMapping(value = "/monitoroutline", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addMonitorOutline(HttpServletResponse response,
			@ApiParam(value = "monitorOutline") @RequestBody MonitorOutline monitorOutline) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		monitorOutline.setId(null);
		monitorOutlineService.addMonitorOutline(monitorOutline);
		return monitorOutline;
	}
	
	
	@ApiOperation(value = "更新 根据全站检测概要标识更新全站检测概要信息", notes = "根据全站检测概要标识更新全站检测概要信息")
	@RequestMapping(value = "/monitoroutline/{id:.+}", method = RequestMethod.PUT)
	public Object modifyMonitorOutlineById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "monitorOutline", required = true) @RequestBody MonitorOutline monitorOutline
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		MonitorOutline tempMonitorOutline = monitorOutlineService.queryMonitorOutlineById(id);
		monitorOutline.setId(id);
		if(null == tempMonitorOutline){
			throw new NotFoundException("全站检测概要");
		}
		return monitorOutlineService.modifyMonitorOutline(monitorOutline);
	}

	@ApiOperation(value = "删除 根据全站检测概要标识删除全站检测概要信息", notes = "根据全站检测概要标识删除全站检测概要信息")
	@RequestMapping(value = "/monitoroutline/{id:.+}", method = RequestMethod.DELETE)
	public Object dropMonitorOutlineById(HttpServletResponse response, @PathVariable Integer id) {
		MonitorOutline monitorOutline = monitorOutlineService.queryMonitorOutlineById(id);
		if(null == monitorOutline){
			throw new NotFoundException("全站检测概要");
		}
		return monitorOutlineService.dropMonitorOutlineById(id);
	}
	
	@ApiOperation(value = "查询 根据全站检测概要标识查询全站检测概要信息", notes = "根据全站检测概要标识查询全站检测概要信息")
	@RequestMapping(value = "/monitoroutline/{id:.+}", method = RequestMethod.GET)
	public Object queryMonitorOutlineById(HttpServletResponse response,
			@PathVariable Integer id) {
		MonitorOutline monitorOutline = monitorOutlineService.queryMonitorOutlineById(id);
		if(null == monitorOutline){
			throw new NotFoundException("全站检测概要");
		}
		return monitorOutline;
	}
	
	@ApiOperation(value = "查询 根据全站检测概要属性查询全站检测概要信息列表", notes = "根据全站检测概要属性查询全站检测概要信息列表")
	@RequestMapping(value = "/monitoroutline", method = RequestMethod.GET)
	public Object queryMonitorOutlineList(HttpServletResponse response,
			MonitorOutline monitorOutline) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return monitorOutlineService.queryMonitorOutlineByProperty(ClassUtil.transBean2Map(monitorOutline, false));
	}
	
	@ApiOperation(value = "查询 根据全站检测概要属性查询全站检测概要信息列表", notes = "根据全站检测概要属性查询全站检测概要信息列表")
	@RequestMapping(value = "/monitoroutlines/export", method = RequestMethod.GET)
	public void exportMonitorOutlineList(HttpServletResponse response,
			MonitorOutline monitorOutline) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		String fileName = "全站检测总体情况表"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = monitorOutlineService.exportMonitorOutlineByProperty(ClassUtil.transBean2Map(monitorOutline, false));
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}
	
	@ApiOperation(value = "查询分页 根据全站检测概要属性分页查询全站检测概要信息列表", notes = "根据全站检测概要属性分页查询全站检测概要信息列表")
	@RequestMapping(value = "/monitoroutlines", method = RequestMethod.GET)
	public Object queryMonitorOutlinePage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, MonitorOutline monitorOutline) {				
		return monitorOutlineService.queryMonitorOutlineForPage(pagenum, pagesize, sort, monitorOutline);
	}

}
