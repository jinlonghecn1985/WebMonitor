package com.hnjing.cw.controller;

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
import com.hnjing.cw.service.SensitiveRecordService;
import com.hnjing.full.model.entity.MonitorOutline;
import com.hnjing.utils.ClassUtil;
import com.hnjing.utils.HttpTool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: SensitiveRecordController
 * @Description: 敏感词记录HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
@RestController
@Api(description="网站状态-首页敏感词记录管理(日更新)")
public class SensitiveRecordController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private SensitiveRecordService sensitiveRecordService;

	
	@ApiOperation(value = "新增 添加敏感词记录信息", notes = "添加敏感词记录信息")
	@RequestMapping(value = "/sensitiverecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addSensitiveRecord(HttpServletResponse response,
			@ApiParam(value = "sensitiveRecord") @RequestBody SensitiveRecord sensitiveRecord) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		sensitiveRecord.setId(null);
		sensitiveRecordService.addSensitiveRecord(sensitiveRecord);
		return sensitiveRecord;
	}
	
	
	@ApiOperation(value = "更新 根据敏感词记录标识更新敏感词记录信息", notes = "根据敏感词记录标识更新敏感词记录信息")
	@RequestMapping(value = "/sensitiverecord/{id:.+}", method = RequestMethod.PUT)
	public Object modifySensitiveRecordById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "sensitiveRecord", required = true) @RequestBody SensitiveRecord sensitiveRecord
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SensitiveRecord tempSensitiveRecord = sensitiveRecordService.querySensitiveRecordById(id);
		sensitiveRecord.setId(id);
		if(null == tempSensitiveRecord){
			throw new NotFoundException("敏感词记录");
		}
		return sensitiveRecordService.modifySensitiveRecord(sensitiveRecord);
	}

	@ApiOperation(value = "删除 根据敏感词记录标识删除敏感词记录信息", notes = "根据敏感词记录标识删除敏感词记录信息")
	@RequestMapping(value = "/sensitiverecord/{id:.+}", method = RequestMethod.DELETE)
	public Object dropSensitiveRecordById(HttpServletResponse response, @PathVariable Integer id) {
		SensitiveRecord sensitiveRecord = sensitiveRecordService.querySensitiveRecordById(id);
		if(null == sensitiveRecord){
			throw new NotFoundException("敏感词记录");
		}
		return sensitiveRecordService.dropSensitiveRecordById(id);
	}
	
	@ApiOperation(value = "查询 根据敏感词记录标识查询敏感词记录信息", notes = "根据敏感词记录标识查询敏感词记录信息")
	@RequestMapping(value = "/sensitiverecord/{id:.+}", method = RequestMethod.GET)
	public Object querySensitiveRecordById(HttpServletResponse response,
			@PathVariable Integer id) {
		SensitiveRecord sensitiveRecord = sensitiveRecordService.querySensitiveRecordById(id);
		if(null == sensitiveRecord){
			throw new NotFoundException("敏感词记录");
		}
		return sensitiveRecord;
	}
	
	@ApiOperation(value = "查询 根据敏感词记录属性查询敏感词记录信息列表", notes = "根据敏感词记录属性查询敏感词记录信息列表")
	@RequestMapping(value = "/sensitiverecord", method = RequestMethod.GET)
	public Object querySensitiveRecordList(HttpServletResponse response,
			SensitiveRecord sensitiveRecord) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return sensitiveRecordService.querySensitiveRecordByProperty(ClassUtil.transBean2Map(sensitiveRecord, false));
	}
	
	@ApiOperation(value = "查询分页 根据敏感词记录属性分页查询敏感词记录信息列表", notes = "根据敏感词记录属性分页查询敏感词记录信息列表")
	@RequestMapping(value = "/sensitiverecords", method = RequestMethod.GET)
	public Object querySensitiveRecordPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, SensitiveRecord sensitiveRecord) {				
		return sensitiveRecordService.querySensitiveRecordForPage(pagenum, pagesize, sort, sensitiveRecord);
	}
	
	@ApiOperation(value = "导出数据", notes = "导出数据")
	@RequestMapping(value = "/sensitiverecords/export", method = RequestMethod.GET)
	public void exportDataList(HttpServletResponse response,
			SensitiveRecord sensitiveRecord) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		String fileName = "全站检测总体情况表"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = sensitiveRecordService.exportByProperty(ClassUtil.transBean2Map(sensitiveRecord, false));
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

}
