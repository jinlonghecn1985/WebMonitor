package com.hnjing.ws.service.impl;

import java.util.ArrayList;
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
import com.hnjing.ws.model.dao.SiteResultMapper;
import com.hnjing.ws.model.entity.SiteResult;
import com.hnjing.ws.service.SiteResultService;
import com.hnjing.ws.service.SiteUrlService;
import com.hnjing.ws.service.bo.SiteResultBo;

/**
 * @ClassName: SiteResult
 * @Description: 检测结果服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Service("siteResultService")
@Transactional(readOnly=true)
public class  SiteResultServiceImpl implements SiteResultService {	
	private static final Logger logger = LoggerFactory.getLogger(SiteResultServiceImpl.class);
	
	@Autowired
    private SiteResultMapper siteResultMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	@Autowired
	private SiteUrlService siteUrlService;
	
	/**
	 * @Title: addSiteResult
	 * @Description:添加检测结果
	 * @param siteResult 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public SiteResult addSiteResult(SiteResult siteResult){
		int ret = siteResultMapper.addSiteResult(siteResult);
		if(ret>0){
			return siteResult;
		}
		return null;
	}
	
	/**
	 * @Title modifySiteResult
	 * @Description:修改检测结果
	 * @param siteResult 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifySiteResult(SiteResult siteResult){
		return siteResultMapper.modifySiteResult(siteResult);
	}
	
	/**
	 * @Title: dropSiteResultBySiteId
	 * @Description:删除检测结果
	 * @param siteId 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropSiteResultBySiteId(Integer siteId){
		return siteResultMapper.dropSiteResultBySiteId(siteId);
	}
	
	/**
	 * @Title: querySiteResultBySiteId
	 * @Description:根据实体标识查询检测结果
	 * @param siteId 实体标识
	 * @return SiteResult
	 */
	@Override
	public SiteResult querySiteResultBySiteId(Integer siteId){
		return siteResultMapper.querySiteResultBySiteId(siteId);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: querySiteResultForPage
	 * @Description: 根据检测结果属性与分页信息分页查询检测结果信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param siteResult 实体
	 * @return List<SiteResult>
	 */
	@Override
	public Map<String, Object> querySiteResultForPage(Integer pagenum, Integer pagesize, String sort, Map<String, Object> siteResult){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, SiteResult.class);
		List<SiteResult> entityList = siteResultMapper.querySiteResultForPage(pageBounds, siteResult);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, SiteResult.class);
		}
		
		PageList<SiteResult> pagelist = (PageList<SiteResult>) entityList;
		
		List<SiteResultBo> boList = new ArrayList<SiteResultBo>();
		if(pagelist!=null && pagelist.size()>0) {
			for(SiteResult r : entityList) {
				SiteResultBo bo = new SiteResultBo();
				bo.setSiteResult(r);
				bo.setSiteUrl(siteUrlService.querySiteUrlById(r.getSiteId()));
				boList.add(bo);
			}
		}
		
		
		returnMap.put(Constant.PAGELIST, boList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: querySiteResultByProperty
	 * @Description:根据属性查询检测结果
	 * @return List<SiteResult>
	 */
	@Override
	public List<SiteResult> querySiteResultByProperty(Map<String, Object> map){
		return siteResultMapper.querySiteResultByProperty(map);
	}

	/*
	 * @Title: addSiteResultOnBatch
	 * @Description: TODO
	 * @param @param resultList
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param resultList
	 * @return
	 * @see com.hnjing.ws.service.SiteResultService#addSiteResultOnBatch(java.util.List)
	 */ 
	@Override
	@Transactional(readOnly=false)
	public Integer addSiteResultOnBatch(List<SiteResult> resultList) {
		return siteResultMapper.addResultOnBatch(resultList);
	}

	/*
	 * @Title: clearTodayBeforeHistroy
	 * @Description: 
	 * @param @param source
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param source
	 * @return
	 * @see com.hnjing.ws.service.SiteResultService#clearTodayBeforeHistroy(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly=false)
	public int clearTodayBeforeHistroy(Integer source) {
		return siteResultMapper.clearTodayBeforeHistroy(source);
	}

	/*
	 * @Title: querySiteResultCountByProperty
	 * @Description: 根据属性查询数据量
	 * @param @param map
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param map
	 * @return
	 * @see com.hnjing.ws.service.SiteResultService#querySiteResultCountByProperty(java.util.Map)
	 */ 
	@Override
	public Integer querySiteResultCountByProperty(Map<String, Object> map) {
		return siteResultMapper.querySiteResultCountByProperty(map);
	}

	/*
	 * @Title: queryErrorSiteSelf
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteResultService#queryErrorSiteSelf()
	 */ 
	@Override
	public List<Map<String, Object>> queryErrorSiteSelf() {
		return siteResultMapper.queryErrorSiteSelf();
	}

	/*
	 * @Title: clearTable
	 * @Description: TODO
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteResultService#clearTable()
	 */ 
	@Override
	@Transactional(readOnly=false)
	public Integer clearTable() {
		return siteResultMapper.clearTable();
	}


}
