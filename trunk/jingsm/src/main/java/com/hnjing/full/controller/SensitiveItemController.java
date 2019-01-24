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
import com.hnjing.cw.model.entity.SensitiveRecord;
import com.hnjing.full.model.entity.SensitiveItem;
import com.hnjing.full.service.SensitiveItemService;
import com.hnjing.utils.ClassUtil;
import com.hnjing.utils.HttpTool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: SensitiveItemController
 * @Description: 网页敏感词HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
@RestController
@Api(description="全站检测-敏感词详情")
public class SensitiveItemController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private SensitiveItemService sensitiveItemService;

	
	@ApiOperation(value = "新增 添加网页敏感词信息", notes = "添加网页敏感词信息")
	@RequestMapping(value = "/sensitiveitem", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addSensitiveItem(HttpServletResponse response,
			@ApiParam(value = "sensitiveItem") @RequestBody SensitiveItem sensitiveItem) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		sensitiveItem.setId(null);
		sensitiveItemService.addSensitiveItem(sensitiveItem);
		return sensitiveItem;
	}
	
	
	@ApiOperation(value = "更新 根据网页敏感词标识更新网页敏感词信息", notes = "根据网页敏感词标识更新网页敏感词信息")
	@RequestMapping(value = "/sensitiveitem/{id:.+}", method = RequestMethod.PUT)
	public Object modifySensitiveItemById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "sensitiveItem", required = true) @RequestBody SensitiveItem sensitiveItem
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SensitiveItem tempSensitiveItem = sensitiveItemService.querySensitiveItemById(id);
		sensitiveItem.setId(id);
		if(null == tempSensitiveItem){
			throw new NotFoundException("网页敏感词");
		}
		return sensitiveItemService.modifySensitiveItem(sensitiveItem);
	}

	@ApiOperation(value = "删除 根据网页敏感词标识删除网页敏感词信息", notes = "根据网页敏感词标识删除网页敏感词信息")
	@RequestMapping(value = "/sensitiveitem/{id:.+}", method = RequestMethod.DELETE)
	public Object dropSensitiveItemById(HttpServletResponse response, @PathVariable Integer id) {
		SensitiveItem sensitiveItem = sensitiveItemService.querySensitiveItemById(id);
		if(null == sensitiveItem){
			throw new NotFoundException("网页敏感词");
		}
		return sensitiveItemService.dropSensitiveItemById(id);
	}
	
	@ApiOperation(value = "查询 根据网页敏感词标识查询网页敏感词信息", notes = "根据网页敏感词标识查询网页敏感词信息")
	@RequestMapping(value = "/sensitiveitem/{id:.+}", method = RequestMethod.GET)
	public Object querySensitiveItemById(HttpServletResponse response,
			@PathVariable Integer id) {
		SensitiveItem sensitiveItem = sensitiveItemService.querySensitiveItemById(id);
		if(null == sensitiveItem){
			throw new NotFoundException("网页敏感词");
		}
		return sensitiveItem;
	}
	
	@ApiOperation(value = "查询 根据网页敏感词属性查询网页敏感词信息列表", notes = "根据网页敏感词属性查询网页敏感词信息列表")
	@RequestMapping(value = "/sensitiveitem", method = RequestMethod.GET)
	public Object querySensitiveItemList(HttpServletResponse response,
			SensitiveItem sensitiveItem) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return sensitiveItemService.querySensitiveItemByProperty(ClassUtil.transBean2Map(sensitiveItem, false));
	}
	
	@ApiOperation(value = "查询分页 根据网页敏感词属性分页查询网页敏感词信息列表", notes = "根据网页敏感词属性分页查询网页敏感词信息列表")
	@RequestMapping(value = "/sensitiveitems", method = RequestMethod.GET)
	public Object querySensitiveItemPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, SensitiveItem sensitiveItem) {				
		return sensitiveItemService.querySensitiveItemForPage(pagenum, pagesize, sort, sensitiveItem);
	}

	@ApiOperation(value = "导出数据", notes = "导出数据")
	@RequestMapping(value = "/sensitiveitems/export", method = RequestMethod.GET)
	public void exportDataList(HttpServletResponse response,
			SensitiveItem sensitiveItem) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		String fileName = "sensitive_item_"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = sensitiveItemService.exportByProperty(ClassUtil.transBean2Map(sensitiveItem, false));
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}
}
