package com.hnjing.ws.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

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
import com.hnjing.ws.model.entity.SiteHistory;
import com.hnjing.ws.service.SiteHistoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: SiteHistoryController
 * @Description: 异常网站历史记录HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@RestController
@Api(description="异常网站历史记录")
public class SiteHistoryController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private SiteHistoryService siteHistoryService;

	
	@ApiOperation(value = "新增 添加异常网站历史记录信息", notes = "添加异常网站历史记录信息")
	@RequestMapping(value = "/sitehistory", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addSiteHistory(HttpServletResponse response,
			@ApiParam(value = "siteHistory") @RequestBody SiteHistory siteHistory) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		siteHistory.setSiteId(null);
		siteHistoryService.addSiteHistory(siteHistory);
		return siteHistory;
	}
	
	
	@ApiOperation(value = "更新 根据异常网站历史记录标识更新异常网站历史记录信息", notes = "根据异常网站历史记录标识更新异常网站历史记录信息")
	@RequestMapping(value = "/sitehistory/{siteId:.+}", method = RequestMethod.PUT)
	public Object modifySiteHistoryById(HttpServletResponse response,
			@PathVariable Integer siteId,
			@ApiParam(value = "siteHistory", required = true) @RequestBody SiteHistory siteHistory
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SiteHistory tempSiteHistory = siteHistoryService.querySiteHistoryBySiteId(siteId);
		siteHistory.setSiteId(siteId);
		if(null == tempSiteHistory){
			throw new NotFoundException("异常网站历史记录");
		}
		return siteHistoryService.modifySiteHistory(siteHistory);
	}

	@ApiOperation(value = "删除 根据异常网站历史记录标识删除异常网站历史记录信息", notes = "根据异常网站历史记录标识删除异常网站历史记录信息")
	@RequestMapping(value = "/sitehistory/{siteId:.+}", method = RequestMethod.DELETE)
	public Object dropSiteHistoryBySiteId(HttpServletResponse response, @PathVariable Integer siteId) {
		SiteHistory siteHistory = siteHistoryService.querySiteHistoryBySiteId(siteId);
		if(null == siteHistory){
			throw new NotFoundException("异常网站历史记录");
		}
		return siteHistoryService.dropSiteHistoryBySiteId(siteId);
	}
	
	@ApiOperation(value = "查询 根据异常网站历史记录标识查询异常网站历史记录信息", notes = "根据异常网站历史记录标识查询异常网站历史记录信息")
	@RequestMapping(value = "/sitehistory/{siteId:.+}", method = RequestMethod.GET)
	public Object querySiteHistoryById(HttpServletResponse response,
			@PathVariable Integer siteId) {
		SiteHistory siteHistory = siteHistoryService.querySiteHistoryBySiteId(siteId);
		if(null == siteHistory){
			throw new NotFoundException("异常网站历史记录");
		}
		return siteHistory;
	}
	
	@ApiOperation(value = "查询 根据异常网站历史记录属性查询异常网站历史记录信息列表", notes = "根据异常网站历史记录属性查询异常网站历史记录信息列表")
	@RequestMapping(value = "/sitehistory", method = RequestMethod.GET)
	public Object querySiteHistoryList(HttpServletResponse response,
			SiteHistory siteHistory) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return siteHistoryService.querySiteHistoryByProperty(ClassUtil.transBean2Map(siteHistory, false));
	}
	
	@ApiOperation(value = "查询分页 根据异常网站历史记录属性分页查询异常网站历史记录信息列表", notes = "根据异常网站历史记录属性分页查询异常网站历史记录信息列表")
	@RequestMapping(value = "/sitehistorys", method = RequestMethod.GET)
	public Object querySiteHistoryPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "id", required = false) String id, SiteHistory siteHistory) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {	
		Map<String, Object> query = ClassUtil.transBean2Map(siteHistory, false);
		if(siteHistory.getStatus()!=null && siteHistory.getStatus().intValue()!=200) {
			query.remove("status");
			query.put("nostatus", 200);
		}
		if(id!=null && id.length()>0) {
			query.put("id", id);
		}
		return siteHistoryService.querySiteHistoryForPage(pagenum, pagesize, sort, query);
	}

}
