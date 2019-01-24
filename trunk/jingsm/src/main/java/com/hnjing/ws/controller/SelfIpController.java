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
import com.hnjing.ws.model.entity.SelfIp;
import com.hnjing.ws.service.SelfIpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: SelfIpController
 * @Description: 我司IP信息HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@RestController
@Api(description="系统控制-我司IP信息")
public class SelfIpController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private SelfIpService selfIpService;

	
	@ApiOperation(value = "新增 添加我司IP信息信息", notes = "添加我司IP信息信息")
	@RequestMapping(value = "/selfip", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addSelfIp(HttpServletResponse response,
			@ApiParam(value = "selfIp") @RequestBody SelfIp selfIp) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		selfIp.setId(null);
		selfIpService.addSelfIp(selfIp);
		return selfIp;
	}
	
	
	@ApiOperation(value = "更新 根据我司IP信息标识更新我司IP信息信息", notes = "根据我司IP信息标识更新我司IP信息信息")
	@RequestMapping(value = "/selfip/{id:.+}", method = RequestMethod.PUT)
	public Object modifySelfIpById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "selfIp", required = true) @RequestBody SelfIp selfIp
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SelfIp tempSelfIp = selfIpService.querySelfIpById(id);
		selfIp.setId(id);
		if(null == tempSelfIp){
			throw new NotFoundException("我司IP信息");
		}
		return selfIpService.modifySelfIp(selfIp);
	}

	@ApiOperation(value = "删除 根据我司IP信息标识删除我司IP信息信息", notes = "根据我司IP信息标识删除我司IP信息信息")
	@RequestMapping(value = "/selfip/{id:.+}", method = RequestMethod.DELETE)
	public Object dropSelfIpById(HttpServletResponse response, @PathVariable Integer id) {
		SelfIp selfIp = selfIpService.querySelfIpById(id);
		if(null == selfIp){
			throw new NotFoundException("我司IP信息");
		}
		return selfIpService.dropSelfIpById(id);
	}
	
	@ApiOperation(value = "查询 根据我司IP信息标识查询我司IP信息信息", notes = "根据我司IP信息标识查询我司IP信息信息")
	@RequestMapping(value = "/selfip/{id:.+}", method = RequestMethod.GET)
	public Object querySelfIpById(HttpServletResponse response,
			@PathVariable Integer id) {
		SelfIp selfIp = selfIpService.querySelfIpById(id);
		if(null == selfIp){
			throw new NotFoundException("我司IP信息");
		}
		return selfIp;
	}
	
	@ApiOperation(value = "查询 根据我司IP信息属性查询我司IP信息信息列表", notes = "根据我司IP信息属性查询我司IP信息信息列表")
	@RequestMapping(value = "/selfip", method = RequestMethod.GET)
	public Object querySelfIpList(HttpServletResponse response,
			SelfIp selfIp) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return selfIpService.querySelfIpByProperty(ClassUtil.transBean2Map(selfIp, false));
	}
	
	@ApiOperation(value = "查询分页 根据我司IP信息属性分页查询我司IP信息信息列表", notes = "根据我司IP信息属性分页查询我司IP信息信息列表")
	@RequestMapping(value = "/selfips", method = RequestMethod.GET)
	public Object querySelfIpPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, SelfIp selfIp) {				
		return selfIpService.querySelfIpForPage(pagenum, pagesize, sort, selfIp);
	}

}
