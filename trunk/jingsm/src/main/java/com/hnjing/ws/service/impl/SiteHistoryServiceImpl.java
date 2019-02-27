package com.hnjing.ws.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.utils.Constant;
import com.hnjing.utils.file.office.ExcelWriteUtil;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;
import com.hnjing.ws.model.dao.SiteHistoryMapper;
import com.hnjing.ws.model.entity.SiteHistory;
import com.hnjing.ws.service.SiteHistoryService;
import com.hnjing.ws.service.SiteUrlService;
import com.hnjing.ws.service.bo.SiteHistoryBo;

/**
 * @ClassName: SiteHistory
 * @Description: 异常网站历史记录服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Service("siteHistoryService")
@Transactional(readOnly=true)
public class  SiteHistoryServiceImpl implements SiteHistoryService {	
	private static final Logger logger = LoggerFactory.getLogger(SiteHistoryServiceImpl.class);
	
	@Autowired
    private SiteHistoryMapper siteHistoryMapper;   
	
	@Autowired
	private SiteUrlService siteUrlService;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addSiteHistory
	 * @Description:添加异常网站历史记录
	 * @param siteHistory 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public SiteHistory addSiteHistory(SiteHistory siteHistory){
		int ret = siteHistoryMapper.addSiteHistory(siteHistory);
		if(ret>0){
			return siteHistory;
		}
		return null;
	}
	
	/**
	 * @Title modifySiteHistory
	 * @Description:修改异常网站历史记录
	 * @param siteHistory 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifySiteHistory(SiteHistory siteHistory){
		return siteHistoryMapper.modifySiteHistory(siteHistory);
	}
	
	/**
	 * @Title: dropSiteHistoryBySiteId
	 * @Description:删除异常网站历史记录
	 * @param siteId 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropSiteHistoryBySiteId(Integer siteId){
		return siteHistoryMapper.dropSiteHistoryBySiteId(siteId);
	}
	
	/**
	 * @Title: querySiteHistoryBySiteId
	 * @Description:根据实体标识查询异常网站历史记录
	 * @param siteId 实体标识
	 * @return SiteHistory
	 */
	@Override
	public SiteHistory querySiteHistoryBySiteId(Integer siteId){
		return siteHistoryMapper.querySiteHistoryBySiteId(siteId);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: querySiteHistoryForPage
	 * @Description: 根据异常网站历史记录属性与分页信息分页查询异常网站历史记录信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param siteHistory 实体
	 * @return List<SiteHistory>
	 */
	@Override
	public Map<String, Object> querySiteHistoryForPage(Integer pagenum, Integer pagesize, String sort, Map<String, Object> siteHistory){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, SiteHistory.class);
		List<SiteHistory> entityList = siteHistoryMapper.querySiteHistoryForPage(pageBounds, siteHistory);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, SiteHistory.class);
		}
		
		PageList<SiteHistory> pagelist = (PageList<SiteHistory>) entityList;
		
		List<SiteHistoryBo> boList = new ArrayList<SiteHistoryBo>();
		if(pagelist!=null && pagelist.size()>0) {
			for(SiteHistory r : entityList) {
				SiteHistoryBo bo = new SiteHistoryBo();
				bo.setSiteHistory(r);
				bo.setSiteUrl(siteUrlService.querySiteUrlById(r.getSiteId()));
				boList.add(bo);
			}			
		}
		
		
		returnMap.put(Constant.PAGELIST, boList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: querySiteHistoryByProperty
	 * @Description:根据属性查询异常网站历史记录
	 * @return List<SiteHistory>
	 */
	@Override
	public List<SiteHistory> querySiteHistoryByProperty(Map<String, Object> map){
		return siteHistoryMapper.querySiteHistoryByProperty(map);
	}

	/*
	 * @Title: bakCheckDate
	 * @Description: 
	 * @param @param source
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param source
	 * @return
	 * @see com.hnjing.ws.service.SiteHistoryService#bakCheckDate(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer bakCheckDate(Integer source) {
		//暂时未按检测源处理，统一全部处理
		Integer ret = siteHistoryMapper.recordNomalSite(source);
		Integer ret2 = siteHistoryMapper.recordErrorSite(source);		
		return (ret==null?0:ret)+(ret2==null?0:ret2);
	}

	/*
	 * @Title: queryHistoryInfoByStatisticsID
	 * @Description: TODO
	 * @param @param statisticsID
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param statisticsID
	 * @return
	 * @see com.hnjing.ws.service.SiteHistoryService#queryHistoryInfoByStatisticsID(java.lang.String)
	 */ 
	@Override
	public List<Map<String, String>> queryHistoryInfoByStatisticsID(String statisticsID) {
		return siteHistoryMapper.queryHistoryInfoByStatisticsID(statisticsID);
	}

	/*
	 * @Title: exportByStatisticsID
	 * @Description: TODO
	 * @param @param statisticsID
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param statisticsID
	 * @return
	 * @see com.hnjing.ws.service.SiteHistoryService#exportByStatisticsID(java.lang.String)
	 */ 
	@Override
	public HSSFWorkbook exportByStatisticsID(String statisticsID) {
		String[] title = {"客户名称", "SF帐号", "客服", "客服姓名", "客服邮件", "域名", "标题", "内容", "异常代码",  "域名IP", "是否我司", "检测时间"};
		String[][] data = null;
		List<Map<String, String>> info = queryHistoryInfoByStatisticsID(statisticsID);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][12];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = info.get(j).get("customer");
				data[j][1] = info.get(j).get("SF_Name");
				data[j][2] = info.get(j).get("EMPL_NO");			
				data[j][3] = info.get(j).get("REAL_NAME");
				data[j][4] = info.get(j).get("email");	
				data[j][5] = info.get(j).get("page");		
				data[j][6] = info.get(j).get("title");
//				data[j][7] = info.get(j).get("content");
				data[j][7] = "";
				data[j][8] = ""+info.get(j).get("status");
				data[j][9] = info.get(j).get("ip");				
				data[j][10] = "1".equals(info.get(j).get("self_site"))?"是":"否";
				data[j][11] = info.get(j).get("gmt_created")!=null?info.get(j).get("gmt_created"):"";
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("检测异常网站详情", title, data, null);
	}


}
