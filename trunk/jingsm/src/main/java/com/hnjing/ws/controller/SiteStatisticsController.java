package com.hnjing.ws.controller;

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
import com.hnjing.utils.ClassUtil;
import com.hnjing.ws.model.entity.SiteStatistics;
import com.hnjing.ws.service.SiteStatisticsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: SiteStatisticsController
 * @Description: 检测结果统计HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@RestController
@Api(description="网站状态-检测结果统计")
public class SiteStatisticsController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private SiteStatisticsService siteStatisticsService;

	
	@ApiOperation(value = "新增 添加检测结果统计信息", notes = "添加检测结果统计信息")
	@RequestMapping(value = "/sitestatistics", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addSiteStatistics(HttpServletResponse response,
			@ApiParam(value = "siteStatistics") @RequestBody SiteStatistics siteStatistics) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		siteStatistics.setId(null);
		siteStatisticsService.addSiteStatistics(siteStatistics);
		return siteStatistics;
	}
	
	
	@ApiOperation(value = "更新 根据检测结果统计标识更新检测结果统计信息", notes = "根据检测结果统计标识更新检测结果统计信息")
	@RequestMapping(value = "/sitestatistics/{id:.+}", method = RequestMethod.PUT)
	public Object modifySiteStatisticsById(HttpServletResponse response,
			@PathVariable String id,
			@ApiParam(value = "siteStatistics", required = true) @RequestBody SiteStatistics siteStatistics
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SiteStatistics tempSiteStatistics = siteStatisticsService.querySiteStatisticsById(id);
		siteStatistics.setId(id);
		if(null == tempSiteStatistics){
			throw new NotFoundException("检测结果统计");
		}
		return siteStatisticsService.modifySiteStatistics(siteStatistics);
	}

	@ApiOperation(value = "删除 根据检测结果统计标识删除检测结果统计信息", notes = "根据检测结果统计标识删除检测结果统计信息")
	@RequestMapping(value = "/sitestatistics/{id:.+}", method = RequestMethod.DELETE)
	public Object dropSiteStatisticsById(HttpServletResponse response, @PathVariable String id) {
		SiteStatistics siteStatistics = siteStatisticsService.querySiteStatisticsById(id);
		if(null == siteStatistics){
			throw new NotFoundException("检测结果统计");
		}
		return siteStatisticsService.dropSiteStatisticsById(id);
	}
	
	@ApiOperation(value = "查询 根据检测结果统计标识查询检测结果统计信息", notes = "根据检测结果统计标识查询检测结果统计信息")
	@RequestMapping(value = "/sitestatistics/{id:.+}", method = RequestMethod.GET)
	public Object querySiteStatisticsById(HttpServletResponse response,
			@PathVariable String id) {
		SiteStatistics siteStatistics = siteStatisticsService.querySiteStatisticsById(id);
		if(null == siteStatistics){
			throw new NotFoundException("检测结果统计");
		}
		return siteStatistics;
	}
	
	@ApiOperation(value = "查询 根据检测结果统计属性查询检测结果统计信息列表", notes = "根据检测结果统计属性查询检测结果统计信息列表")
	@RequestMapping(value = "/sitestatistics", method = RequestMethod.GET)
	public Object querySiteStatisticsList(HttpServletResponse response,
			SiteStatistics siteStatistics) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return siteStatisticsService.querySiteStatisticsByProperty(ClassUtil.transBean2Map(siteStatistics, false));
	}
	
	@ApiOperation(value = "查询分页 根据检测结果统计属性分页查询检测结果统计信息列表", notes = "根据检测结果统计属性分页查询检测结果统计信息列表")
	@RequestMapping(value = "/sitestatisticss", method = RequestMethod.GET)
	public Object querySiteStatisticsPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, SiteStatistics siteStatistics) {				
		return siteStatisticsService.querySiteStatisticsForPage(pagenum, pagesize, sort, siteStatistics);
	}

}
