/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: MonitorController.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.full.controller
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月10日 上午10:19:25
 * @version: V1.0  
 */
package com.hnjing.full.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hnjing.config.web.exception.ParameterException;
import com.hnjing.full.model.entity.MonitorOutline;
import com.hnjing.full.service.FullSiteMonitorService;
import com.hnjing.full.service.MonitorOutlineService;
import com.hnjing.full.service.impl.bo.OutlineBo;
import com.hnjing.ws.service.impl.util.HttpToolUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: MonitorController
 * @Description: 
 * @author: Jinlong He
 * @date: 2019年1月10日 上午10:19:25
 */
@RestController
@Api(description="全站检测接口")
public class MonitorController {
	@Autowired
	private FullSiteMonitorService fullSiteMonitorService;
	
	@Autowired
	private MonitorOutlineService monitorOutlineService;
	
	@ApiOperation(value = "查询 新增全站检测", notes = "查询 新增全站检测")
	@RequestMapping(value = "/monitor/all", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public MonitorOutline queryOrAddAllSite(HttpServletResponse response,
			@ApiParam(value = "outlineBo") @RequestBody OutlineBo outlineBo) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		if(outlineBo==null) {
			throw new ParameterException("page", "参数不能为空!");
		}
		outlineBo.setPage(HttpToolUtil.getUrl(outlineBo.getPage()));
		if(outlineBo.getPage()==null) {
			throw new ParameterException("page", "检测网址不能为空!");
		}
		MonitorOutline mo = monitorOutlineService.queryMonitorOutlineByPage(outlineBo.getPage());
		if(mo==null || mo.getId()==null) {
			if(outlineBo.getMail()!=null && outlineBo.getMail().trim().length()>0) {
				if("6666666666".equals(outlineBo.getMail())) {
					outlineBo.setMail(null);
				}
				return fullSiteMonitorService.fullSiteMonitor(outlineBo.getPage(), false, null, outlineBo.getMail());
			}
			mo = new MonitorOutline();
			mo.setCheckLevel(0);
			return mo;
		}		
		return mo;		
	}
}
