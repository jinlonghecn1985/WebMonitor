/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: AdminController.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.ws.controller
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年11月17日 下午6:08:11
 * @version: V1.0  
 */
package com.hnjing.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnjing.config.web.exception.AuthorityException;
import com.hnjing.config.web.exception.ParameterException;
import com.hnjing.cw.service.SensitiveService;
import com.hnjing.dpc.service.DPSService;
import com.hnjing.full.service.FullSiteMonitorService;
import com.hnjing.sync.service.SyncSiteService;
import com.hnjing.ws.model.entity.SiteResult;
import com.hnjing.ws.service.DictionaryService;
import com.hnjing.ws.service.SiteIPService;
import com.hnjing.ws.service.SiteAccessService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: AdminController
 * @Description: 控制管理接口
 * @author: Jinlong He
 * @date: 2018年11月17日 下午6:08:11
 */
@RestController
@Api(description="系统控制-控制接口")
public class AdminController {
	
	@Autowired
	private SiteAccessService siteMonitorService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private SensitiveService sensitiveService;
	
	@Autowired
	private SyncSiteService syncSiteService;
	
	@Autowired
	private SiteIPService siteIPService;
	
	@Autowired
	private DPSService dpsService;
	
	@Autowired
	private FullSiteMonitorService fullSiteMonitorService;
	
	
	
	/** 
	* @Title: checkAuth 
	* @Description: 权限检测
	* @param token
	* @return  
	* boolean    返回类型 
	* @throws 
	*/
	private boolean checkAuth(String token) {
		if(token==null || token.length()==0) {
			return false;
		}
		System.out.println(dictionaryService.queryParamsValue(9));
		return token.equals(dictionaryService.queryParamsValue(9));
	}
	
	@ApiOperation(value = "检测网站、网页", notes = "检测网站、网页")
	@RequestMapping(value = "/check/url", method = RequestMethod.GET)
	public Object checkOneSite(HttpServletResponse response,
			@RequestParam(value = "url", required = false) String url) {
		if(url==null || url.length()<3) {
			throw new ParameterException("url", "网址长度不符合要求");
		}
		return siteMonitorService.checkUrl(url);
	}
	
	@ApiOperation(value = "检测网站、网页", notes = "检测网站、网页")
	@RequestMapping(value = "/check/urls", method = RequestMethod.POST)
	public Object checkMutiSite(HttpServletResponse response,
			@RequestParam(value = "url", required = false) String url) {
		
		if(url==null || url.length()<3) {
			throw new ParameterException("url", "网址长度不符合要求");
		}
		String[] urls = url.replaceAll("，", ",").split(",");
		List<String> ul = new ArrayList<String>();
		for(String u : urls) {
			ul.add(u);
		}
		return ul;
//		return siteMonitorService.checkMutiSite(url);
	}
	
	@ApiOperation(value = "重新检测当日异常网站,并回写检测结果", notes = "重新检测当日异常网站,并回写检测结果")
	@RequestMapping(value = "/check/recheck/{token:.+}", method = RequestMethod.GET)
	public Object recheckTodayAllErrorSite(HttpServletResponse response,
			@PathVariable String token) {
		if(token!=null && checkAuth(token)) {
			return dpsService.recheckHistory();
		}
		throw new AuthorityException("授权码错误！");
	}

	@ApiOperation(value = "检测网站-平台", notes = "检测网站")
	@RequestMapping(value = "/check/pt/{token:.+}", method = RequestMethod.GET)
	public Object checkSiteStatus(HttpServletResponse response,
			@PathVariable String token) {
		if(token!=null && checkAuth(token)) {
			return siteMonitorService.checkSiteStatus(1);
		}
		throw new AuthorityException("授权码错误！");
	}
	
	@ApiOperation(value = "检测网站-SSG", notes = "检测网站")
	@RequestMapping(value = "/check/ssg/{token:.+}", method = RequestMethod.GET)
	public Object checkSSGSiteStatus(HttpServletResponse response,
			@PathVariable String token) {
		if(token!=null && checkAuth(token)) {
			return siteMonitorService.checkSiteStatus(2);
		}
		throw new AuthorityException("授权码错误！");
	}
	
	@ApiOperation(value = "检测网站-指定来源", notes = "检测网站")
	@RequestMapping(value = "/check/source/{token:.+}", method = RequestMethod.GET)
	public Object checkSiteStatusBySource(HttpServletResponse response,			
			@PathVariable String token, @RequestParam(value = "source", required = false) Integer source) {
		if(token!=null && checkAuth(token)) {
			return siteMonitorService.checkSiteStatus(source);
		}
		throw new AuthorityException("授权码错误！");
	}
	
	@ApiOperation(value = "检测敏感语", notes = "检测敏感语")
	@RequestMapping(value = "/check/word/{token:.+}", method = RequestMethod.GET)
	public Object queryTodayErrStatus(HttpServletResponse response,
			@PathVariable String token) {
		if(token!=null && checkAuth(token)) {
			return sensitiveService.checkResultSensitive();
		}
		throw new AuthorityException("授权码错误！");
	}
//	
	@ApiOperation(value = "发送异常邮件", notes = "发送异常邮件")
	@RequestMapping(value = "/check/mail/{token:.+}", method = RequestMethod.GET)
	public Object sendErrMail(HttpServletResponse response,
			@PathVariable String token, Integer source) {
		if(token!=null && checkAuth(token)) {
			if(source==null || source.intValue()==99) {
				return dpsService.processAllErrorMail();
			}else {
				return dpsService.processErrorMail(source);
			}
		}
		throw new AuthorityException("授权码错误！");
	}
	
	@ApiOperation(value = "检测域名IP", notes = "检测域名IP")
	@RequestMapping(value = "/check/ips/{token:.+}", method = RequestMethod.GET)
	public Object checkWebIP(HttpServletResponse response,
			@PathVariable String token, Integer source) {
		if(token!=null && checkAuth(token)) {
			if(source!=null && source.intValue()==99) {
				return siteIPService.checkNullSiteIP();
			}else {
				return siteIPService.checkAllSiteIP(source);
			}
		}
		throw new AuthorityException("授权码错误！");
	}
	
	@ApiOperation(value = "同步待检测数据", notes = "同步待检测数据")
	@RequestMapping(value = "/check/sync/{token:.+}", method = RequestMethod.GET)
	public Object syncData(HttpServletResponse response,
			@PathVariable String token, Integer source) {
		if(token!=null && checkAuth(token)) {
			if(source==null || source.intValue()==0) {
				source = 2;
			}			
			return syncSiteService.syncDataForSource(source);
		}
		throw new AuthorityException("授权码错误！");
	}
	
	@ApiOperation(value = "同步待检测数据", notes = "同步待检测数据")
	@RequestMapping(value = "/check/excel/{token:.+}", method = RequestMethod.GET)
	public Object syncExcelData(HttpServletResponse response,
			@PathVariable String token, Integer source, String fileName) {
		if(token!=null && checkAuth(token)) {
			if(source==null || source.intValue()==0) {
				source = 2;
			}
			return fileName+" -> "+syncSiteService.syncDataForSource(source, fileName);
		}
		throw new AuthorityException("授权码错误！");
	}
	
	
	
	@ApiOperation(value = "处理同步待检测数据", notes = "处理同步待检测数据")
	@RequestMapping(value = "/check/process/{token:.+}", method = RequestMethod.GET)
	public Object processData(HttpServletResponse response,
			@PathVariable String token, Integer source) {
		if(token!=null && checkAuth(token)) {
			if(source==null || source.intValue()==0) {
				source = 2;
			}			
			return syncSiteService.processDataForSource(source);
		}
		throw new AuthorityException("授权码错误！");
	}
	
	@ApiOperation(value = "网站全站检测", notes = "网站全站检测")
	@RequestMapping(value = "/check/total/{token:.+}", method = RequestMethod.GET)
	public Object checkAllSiteData(HttpServletResponse response,
			@PathVariable String token, String url, String mails) {
		if(token!=null && checkAuth(token)) {
			System.out.println(url);
			System.out.println(mails);
			return fullSiteMonitorService.fullSiteMonitor(url, false, null, mails); 
		}
		throw new AuthorityException("授权码错误！");
	}
	
	
	
	@ApiOperation(value = "检测文本内容", notes = "检测文本内容")
	@RequestMapping(value = "/sensitive/text", method = RequestMethod.POST)
	public Object checkTextSensitive(HttpServletResponse response, @PathVariable String token,
			@ApiParam(value = "content", required = true) @RequestBody SiteResult site) {		
		if(site==null || site.getContent()==null) {
			throw new ParameterException("content", "检测内容为空");
		}
		if(token!=null && checkAuth(token)) {			
			return sensitiveService.checkText((String)site.getContent());
		}
		throw new AuthorityException("授权码错误！");
	}
}
