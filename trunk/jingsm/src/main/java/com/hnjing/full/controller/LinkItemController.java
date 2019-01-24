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
import com.hnjing.full.model.entity.LinkItem;
import com.hnjing.full.service.LinkItemService;
import com.hnjing.utils.ClassUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: LinkItemController
 * @Description: 网页链接信息HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
@RestController
@Api(description="全站检测-网页外链信息")
public class LinkItemController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private LinkItemService linkItemService;

	
	@ApiOperation(value = "新增 添加网页链接信息信息", notes = "添加网页链接信息信息")
	@RequestMapping(value = "/linkitem", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addLinkItem(HttpServletResponse response,
			@ApiParam(value = "linkItem") @RequestBody LinkItem linkItem) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		linkItem.setId(null);
		linkItemService.addLinkItem(linkItem);
		return linkItem;
	}
	
	
	@ApiOperation(value = "更新 根据网页链接信息标识更新网页链接信息信息", notes = "根据网页链接信息标识更新网页链接信息信息")
	@RequestMapping(value = "/linkitem/{id:.+}", method = RequestMethod.PUT)
	public Object modifyLinkItemById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "linkItem", required = true) @RequestBody LinkItem linkItem
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		LinkItem tempLinkItem = linkItemService.queryLinkItemById(id);
		linkItem.setId(id);
		if(null == tempLinkItem){
			throw new NotFoundException("网页链接信息");
		}
		return linkItemService.modifyLinkItem(linkItem);
	}

	@ApiOperation(value = "删除 根据网页链接信息标识删除网页链接信息信息", notes = "根据网页链接信息标识删除网页链接信息信息")
	@RequestMapping(value = "/linkitem/{id:.+}", method = RequestMethod.DELETE)
	public Object dropLinkItemById(HttpServletResponse response, @PathVariable Integer id) {
		LinkItem linkItem = linkItemService.queryLinkItemById(id);
		if(null == linkItem){
			throw new NotFoundException("网页链接信息");
		}
		return linkItemService.dropLinkItemById(id);
	}
	
	@ApiOperation(value = "查询 根据网页链接信息标识查询网页链接信息信息", notes = "根据网页链接信息标识查询网页链接信息信息")
	@RequestMapping(value = "/linkitem/{id:.+}", method = RequestMethod.GET)
	public Object queryLinkItemById(HttpServletResponse response,
			@PathVariable Integer id) {
		LinkItem linkItem = linkItemService.queryLinkItemById(id);
		if(null == linkItem){
			throw new NotFoundException("网页链接信息");
		}
		return linkItem;
	}
	
	@ApiOperation(value = "查询 根据网页链接信息属性查询网页链接信息信息列表", notes = "根据网页链接信息属性查询网页链接信息信息列表")
	@RequestMapping(value = "/linkitem", method = RequestMethod.GET)
	public Object queryLinkItemList(HttpServletResponse response,
			LinkItem linkItem) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return linkItemService.queryLinkItemByProperty(ClassUtil.transBean2Map(linkItem, false));
	}
	
	@ApiOperation(value = "查询分页 根据网页链接信息属性分页查询网页链接信息信息列表", notes = "根据网页链接信息属性分页查询网页链接信息信息列表")
	@RequestMapping(value = "/linkitems", method = RequestMethod.GET)
	public Object queryLinkItemPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, LinkItem linkItem) {				
		return linkItemService.queryLinkItemForPage(pagenum, pagesize, sort, linkItem);
	}

}
