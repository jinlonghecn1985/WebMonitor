package com.hnjing.ws.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;
import com.hnjing.ws.model.dao.SiteStatisticsMapper;
import com.hnjing.ws.model.entity.SiteStatistics;
import com.hnjing.ws.service.SiteStatisticsService;

/**
 * @ClassName: SiteStatistics
 * @Description: 检测结果统计服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Service("siteStatisticsService")
@Transactional(readOnly=true)
public class  SiteStatisticsServiceImpl implements SiteStatisticsService {	
	private static final Logger logger = LoggerFactory.getLogger(SiteStatisticsServiceImpl.class);
	
	@Autowired
    private SiteStatisticsMapper siteStatisticsMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addSiteStatistics
	 * @Description:添加检测结果统计
	 * @param siteStatistics 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public SiteStatistics addSiteStatistics(SiteStatistics siteStatistics){
		if(null == siteStatistics.getId()){			
			siteStatistics.setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
		}
		int ret = siteStatisticsMapper.addSiteStatistics(siteStatistics);
		if(ret>0){
			return siteStatistics;
		}
		return null;
	}
	
	/**
	 * @Title modifySiteStatistics
	 * @Description:修改检测结果统计
	 * @param siteStatistics 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifySiteStatistics(SiteStatistics siteStatistics){
		return siteStatisticsMapper.modifySiteStatistics(siteStatistics);
	}
	
	/**
	 * @Title: dropSiteStatisticsById
	 * @Description:删除检测结果统计
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropSiteStatisticsById(String id){
		return siteStatisticsMapper.dropSiteStatisticsById(id);
	}
	
	/**
	 * @Title: querySiteStatisticsById
	 * @Description:根据实体标识查询检测结果统计
	 * @param id 实体标识
	 * @return SiteStatistics
	 */
	@Override
	public SiteStatistics querySiteStatisticsById(String id){
		return siteStatisticsMapper.querySiteStatisticsById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: querySiteStatisticsForPage
	 * @Description: 根据检测结果统计属性与分页信息分页查询检测结果统计信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param siteStatistics 实体
	 * @return List<SiteStatistics>
	 */
	@Override
	public Map<String, Object> querySiteStatisticsForPage(Integer pagenum, Integer pagesize, String sort, SiteStatistics siteStatistics){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, SiteStatistics.class);
		List<SiteStatistics> entityList = siteStatisticsMapper.querySiteStatisticsForPage(pageBounds, siteStatistics);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, SiteStatistics.class);
		}
		
		PageList<SiteStatistics> pagelist = (PageList<SiteStatistics>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: querySiteStatisticsByProperty
	 * @Description:根据属性查询检测结果统计
	 * @return List<SiteStatistics>
	 */
	@Override
	public List<SiteStatistics> querySiteStatisticsByProperty(Map<String, Object> map){
		return siteStatisticsMapper.querySiteStatisticsByProperty(map);
	}

	/*
	 * @Title: queryNeedContinueCheckInfo
	 * @Description: 获取最新待检测完成数据
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SiteStatisticsService#queryNeedContinueCheckInfo()
	 */ 
	@Override
	public SiteStatistics queryNeedContinueCheckInfo() {
		return siteStatisticsMapper.queryNeedContinueCheckInfo();
	}

	/*
	 * @Title: queryLastSiteStatistics
	 * @Description: TODO
	 * @param @param source
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param source
	 * @return
	 * @see com.hnjing.ws.service.SiteStatisticsService#queryLastSiteStatistics(java.lang.Integer)
	 */ 
	@Override
	public SiteStatistics queryLastSiteStatistics(Integer source) {
		return siteStatisticsMapper.queryLastSiteStatistics(source);
	}


}
