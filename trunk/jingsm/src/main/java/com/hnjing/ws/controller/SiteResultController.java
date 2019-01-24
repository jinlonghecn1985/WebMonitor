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
import com.hnjing.ws.model.entity.SiteResult;
import com.hnjing.ws.service.SiteResultService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: SiteResultController
 * @Description: 检测结果HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@RestController
@Api(description="网站状态-检测结果(每日)")
public class SiteResultController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private SiteResultService siteResultService;

	
	@ApiOperation(value = "新增 添加检测结果信息", notes = "添加检测结果信息")
	@RequestMapping(value = "/siteresult", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addSiteResult(HttpServletResponse response,
			@ApiParam(value = "siteResult") @RequestBody SiteResult siteResult) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		siteResult.setSiteId(null);
		siteResultService.addSiteResult(siteResult);
		return siteResult;
	}
	
	
	@ApiOperation(value = "更新 根据检测结果标识更新检测结果信息", notes = "根据检测结果标识更新检测结果信息")
	@RequestMapping(value = "/siteresult/{siteId:.+}", method = RequestMethod.PUT)
	public Object modifySiteResultById(HttpServletResponse response,
			@PathVariable Integer siteId,
			@ApiParam(value = "siteResult", required = true) @RequestBody SiteResult siteResult
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SiteResult tempSiteResult = siteResultService.querySiteResultBySiteId(siteId);
		siteResult.setSiteId(siteId);
		if(null == tempSiteResult){
			throw new NotFoundException("检测结果");
		}
		return siteResultService.modifySiteResult(siteResult);
	}

	@ApiOperation(value = "删除 根据检测结果标识删除检测结果信息", notes = "根据检测结果标识删除检测结果信息")
	@RequestMapping(value = "/siteresult/{siteId:.+}", method = RequestMethod.DELETE)
	public Object dropSiteResultBySiteId(HttpServletResponse response, @PathVariable Integer siteId) {
		SiteResult siteResult = siteResultService.querySiteResultBySiteId(siteId);
		if(null == siteResult){
			throw new NotFoundException("检测结果");
		}
		return siteResultService.dropSiteResultBySiteId(siteId);
	}
	
	@ApiOperation(value = "查询 根据检测结果标识查询检测结果信息", notes = "根据检测结果标识查询检测结果信息")
	@RequestMapping(value = "/siteresult/{siteId:.+}", method = RequestMethod.GET)
	public Object querySiteResultById(HttpServletResponse response,
			@PathVariable Integer siteId) {
		SiteResult siteResult = siteResultService.querySiteResultBySiteId(siteId);
		if(null == siteResult){
			throw new NotFoundException("检测结果");
		}
		return siteResult;
	}
	
	@ApiOperation(value = "查询 根据检测结果属性查询检测结果信息列表", notes = "根据检测结果属性查询检测结果信息列表")
	@RequestMapping(value = "/siteresult", method = RequestMethod.GET)
	public Object querySiteResultList(HttpServletResponse response,
			SiteResult siteResult) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return siteResultService.querySiteResultByProperty(ClassUtil.transBean2Map(siteResult, false));
	}
	
	@ApiOperation(value = "查询分页 根据检测结果属性分页查询检测结果信息列表", notes = "根据检测结果属性分页查询检测结果信息列表")
	@RequestMapping(value = "/siteresults", method = RequestMethod.GET)
	public Object querySiteResultPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, SiteResult siteResult) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {		
		Map<String, Object> query = ClassUtil.transBean2Map(siteResult, false);
		if(siteResult.getStatus()!=null && siteResult.getStatus().intValue()!=200) {
			query.remove("status");
			query.put("nostatus", 200);
		}
		return siteResultService.querySiteResultForPage(pagenum, pagesize, sort, query );
	}

}
