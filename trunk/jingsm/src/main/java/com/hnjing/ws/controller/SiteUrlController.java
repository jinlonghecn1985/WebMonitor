package com.hnjing.ws.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
import com.hnjing.ws.model.entity.SiteUrl;
import com.hnjing.ws.service.SiteUrlService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: SiteUrlController
 * @Description: 待检测网站信息HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@RestController
@Api(description="待检测网站信息")
public class SiteUrlController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private SiteUrlService siteUrlService;

	
	@ApiOperation(value = "新增 添加待检测网站信息信息", notes = "添加待检测网站信息信息")
	@RequestMapping(value = "/siteurl", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addSiteUrl(HttpServletResponse response,
			@ApiParam(value = "siteUrl") @RequestBody SiteUrl siteUrl) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		siteUrl.setId(null);
		siteUrlService.addSiteUrl(siteUrl);
		return siteUrl;
	}
	
	
	@ApiOperation(value = "更新 根据待检测网站信息标识更新待检测网站信息信息", notes = "根据待检测网站信息标识更新待检测网站信息信息")
	@RequestMapping(value = "/siteurl/{id:.+}", method = RequestMethod.PUT)
	public Object modifySiteUrlById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "siteUrl", required = true) @RequestBody SiteUrl siteUrl
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SiteUrl tempSiteUrl = siteUrlService.querySiteUrlById(id);
		siteUrl.setId(id);
		if(null == tempSiteUrl){
			throw new NotFoundException("待检测网站信息");
		}
		if(siteUrl.getCharset()!=null && siteUrl.getCharset().length()==0) {
			siteUrl.setCharset(null);
		}
		return siteUrlService.modifySiteUrl(siteUrl);
	}

	@ApiOperation(value = "删除 根据待检测网站信息标识删除待检测网站信息信息", notes = "根据待检测网站信息标识删除待检测网站信息信息")
	@RequestMapping(value = "/siteurl/{id:.+}", method = RequestMethod.DELETE)
	public Object dropSiteUrlById(HttpServletResponse response, @PathVariable Integer id) {
		SiteUrl siteUrl = siteUrlService.querySiteUrlById(id);
		if(null == siteUrl){
			throw new NotFoundException("待检测网站信息");
		}
		return siteUrlService.dropSiteUrlById(id);
	}
	
	@ApiOperation(value = "查询 根据待检测网站信息标识查询待检测网站信息信息", notes = "根据待检测网站信息标识查询待检测网站信息信息")
	@RequestMapping(value = "/siteurl/{id:.+}", method = RequestMethod.GET)
	public Object querySiteUrlById(HttpServletResponse response,
			@PathVariable Integer id) {
		SiteUrl siteUrl = siteUrlService.querySiteUrlById(id);
		if(null == siteUrl){
			throw new NotFoundException("待检测网站信息");
		}
		return siteUrl;
	}
	
	@ApiOperation(value = "查询 根据待检测网站信息属性查询待检测网站信息信息列表", notes = "根据待检测网站信息属性查询待检测网站信息信息列表")
	@RequestMapping(value = "/siteurl", method = RequestMethod.GET)
	public Object querySiteUrlList(HttpServletResponse response,
			SiteUrl siteUrl) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return siteUrlService.querySiteUrlByProperty(ClassUtil.transBean2Map(siteUrl, false));
	}
	
	@ApiOperation(value = "查询分页 根据待检测网站信息属性分页查询待检测网站信息信息列表", notes = "根据待检测网站信息属性分页查询待检测网站信息信息列表")
	@RequestMapping(value = "/siteurls", method = RequestMethod.GET)
	public Object querySiteUrlPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "noip", required = false) String noip, SiteUrl siteUrl) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {	
		Map<String, Object> query = ClassUtil.transBean2Map(siteUrl, false);
		if(query==null ) {
			query = new HashMap<String, Object>();			
		}
		if(noip!=null) {
			query.put("noip", 1);
		}
		return siteUrlService.querySiteUrlForPage(pagenum, pagesize, sort, query);
	}

}
