package com.hnjing.ws.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;
import com.hnjing.ws.model.dao.SiteUrlMapper;
import com.hnjing.ws.model.entity.SiteUrl;
import com.hnjing.ws.service.SiteUrlService;
import com.hnjing.ws.service.impl.util.HttpToolUtil;

/**
 * @ClassName: SiteUrl
 * @Description: 待检测网站信息服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Service("siteUrlService")
@Transactional(readOnly=true)
public class  SiteUrlServiceImpl implements SiteUrlService {	
	private static final Logger logger = LoggerFactory.getLogger(SiteUrlServiceImpl.class);
	
	@Autowired
    private SiteUrlMapper siteUrlMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addSiteUrl
	 * @Description:添加待检测网站信息
	 * @param siteUrl 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public SiteUrl addSiteUrl(SiteUrl siteUrl){		
		if(siteUrl!=null && siteUrl.getPage()!=null) {
			siteUrl.setPage(HttpToolUtil.getUrl(siteUrl.getPage()));
			if(siteUrl.getDemin()==null) {
				siteUrl.setDemin(HttpToolUtil.getDemin(siteUrl.getPage()));
			}
		}
		int ret = siteUrlMapper.addSiteUrl(siteUrl);
		if(ret>0){
			return siteUrl;
		}
		return null;
	}
	
	
	/**
	 * @Title modifySiteUrl
	 * @Description:修改待检测网站信息
	 * @param siteUrl 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifySiteUrl(SiteUrl siteUrl){
		if(siteUrl!=null && siteUrl.getPage()!=null) {
			siteUrl.setPage(HttpToolUtil.getUrl(siteUrl.getPage()));
			if(siteUrl.getDemin()==null) {
				siteUrl.setDemin(HttpToolUtil.getDemin(siteUrl.getPage()));
			}
		}
		return siteUrlMapper.modifySiteUrl(siteUrl);
	}
	
	/**
	 * @Title: dropSiteUrlById
	 * @Description:删除待检测网站信息
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropSiteUrlById(Integer id){
		return siteUrlMapper.dropSiteUrlById(id);
	}
	
	/**
	 * @Title: querySiteUrlById
	 * @Description:根据实体标识查询待检测网站信息
	 * @param id 实体标识
	 * @return SiteUrl
	 */
	@Override
	public SiteUrl querySiteUrlById(Integer id){
		return siteUrlMapper.querySiteUrlById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: querySiteUrlForPage
	 * @Description: 根据待检测网站信息属性与分页信息分页查询待检测网站信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param siteUrl 实体
	 * @return List<SiteUrl>
	 */
	@Override
	public Map<String, Object> querySiteUrlForPage(Integer pagenum, Integer pagesize, String sort, Map<String, Object> siteUrl){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, SiteUrl.class);
		List<SiteUrl> entityList = siteUrlMapper.querySiteUrlForPage(pageBounds, siteUrl);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, SiteUrl.class);
		}
		
		PageList<SiteUrl> pagelist = (PageList<SiteUrl>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: querySiteUrlByProperty
	 * @Description:根据属性查询待检测网站信息
	 * @return List<SiteUrl>
	 */
	@Override
	public List<SiteUrl> querySiteUrlByProperty(Map<String, Object> map){
		return siteUrlMapper.querySiteUrlByProperty(map);
	}
	
	/**
	 * @Title: querySiteUrlByProperty
	 * @Description:根据属性查询待检测网站信息--当天已检测数据排除
	 * @return List<SiteUrl>
	 */
	@Override
	public List<SiteUrl> queryWaitCheckSiteUrlByProperty(Map<String, Object> map){
		return siteUrlMapper.queryWaitCheckSiteUrlByProperty(map);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Integer modifySiteUrlIPSOnBatch(List<SiteUrl> list) {
		return siteUrlMapper.modifySiteUrlIPSOnBatch(list);
	}

	/*
	 * @Title: initSiteUrlIPStatus
	 * @Description: 
	 * @param @param status
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param status
	 * @return
	 * @see com.hnjing.ws.service.SiteUrlService#initSiteUrlIPStatus(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer initSiteUrlIPStatus(Integer status) {	
		siteUrlMapper.initSiteUrlSelfSite();
		return siteUrlMapper.initSiteUrlIPStatus(status);
	}
	
	@Override
	public Integer querySiteUrlCount(Map<String, Object> map) {
		return siteUrlMapper.querySiteUrlCount(map);
	}

	/*
	 * @Title: queryReCheckIPSite
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteUrlService#queryReCheckIPSite()
	 */ 
	@Override
	public List<SiteUrl> queryReCheckIPSite() {
		return siteUrlMapper.queryReCheckIPSite();
	}

	/*
	 * @Title: modifySiteUrlIP
	 * @Description: 
	 * @param @param siteUrl
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param siteUrl
	 * @return
	 * @see com.hnjing.ws.service.SiteUrlService#modifySiteUrlIP(com.hnjing.ws.model.entity.SiteUrl)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer modifySiteUrlIP(SiteUrl siteUrl) {
		return siteUrlMapper.modifySiteUrlIP(siteUrl);
	}

	/*
	 * @Title: initNeedCheckZero
	 * @Description: 
	 * @param @param source
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param source
	 * @return
	 * @see com.hnjing.ws.service.SiteUrlService#initNeedCheckZero(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer initNeedCheckZero(Integer source) {
		return siteUrlMapper.initNeedCheckZero(source);
	}

	/*
	 * @Title: initNeedCheckPt
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteUrlService#initNeedCheckPt()
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer initNeedCheckPt() {
		return siteUrlMapper.initNeedCheckPt();
	}

	/*
	 * @Title: bindSiteUrl
	 * @Description: TODO
	 * @param @param site
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param site
	 * @return
	 * @see com.hnjing.ws.service.SiteUrlService#bindSiteUrl(com.hnjing.ws.model.entity.SiteUrl)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public SiteUrl bindSiteUrl(SiteUrl site) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("pageonly", site.getPage());
		query.put("source", site.getSource());
		List<SiteUrl> list =  querySiteUrlByProperty(query);
		if(list==null || list.size()==0) {
			return addSiteUrl(site);
		}else {
			site.setId(list.get(0).getId());
			modifySiteUrl(site);
			return site;
		}
	}

	/*
	 * @Title: initSelfSite
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteUrlService#initSelfSite()
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer initSelfSite() {
		return siteUrlMapper.initSelfSite();
	}
	


}
