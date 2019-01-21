package com.hnjing.full.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.full.model.dao.MonitorOutlineMapper;
import com.hnjing.full.model.entity.MonitorOutline;
import com.hnjing.full.service.ChildInfoService;
import com.hnjing.full.service.LinkItemService;
import com.hnjing.full.service.MonitorOutlineService;
import com.hnjing.full.service.SensitiveItemService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.DateUtil;
import com.hnjing.utils.file.office.ExcelWriteUtil;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: MonitorOutline
 * @Description: 全站检测概要服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
@Service("monitorOutlineService")
@Transactional(readOnly=true)
public class  MonitorOutlineServiceImpl implements MonitorOutlineService {	
	private static final Logger logger = LoggerFactory.getLogger(MonitorOutlineServiceImpl.class);
	
	@Autowired
    private MonitorOutlineMapper monitorOutlineMapper;   
	@Autowired
	private SensitiveItemService sensitiveItemService;
	@Autowired
	private LinkItemService linkItemService;
	@Autowired
	private ChildInfoService childInfoService;
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addMonitorOutline
	 * @Description:添加全站检测概要
	 * @param monitorOutline 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public MonitorOutline addMonitorOutline(MonitorOutline monitorOutline){
		if(monitorOutline!=null && monitorOutline.getPage()!=null && monitorOutline.getPage().length()>400) {
			monitorOutline.setPage(monitorOutline.getPage().substring(0, 400));
		}
		int ret = monitorOutlineMapper.addMonitorOutline(monitorOutline);
		if(ret>0){
			return monitorOutline;
		}
		return null;
	}
	
	/**
	 * @Title modifyMonitorOutline
	 * @Description:修改全站检测概要
	 * @param monitorOutline 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyMonitorOutline(MonitorOutline monitorOutline){
		return monitorOutlineMapper.modifyMonitorOutline(monitorOutline);
	}
	
	
	/**
	 * @Title: dropMonitorOutlineById
	 * @Description:删除全站检测概要
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropMonitorOutlineById(Integer id){
		sensitiveItemService.dropSensitiveItemById(id);
		linkItemService.dropLinkItemById(id);
		childInfoService.dropChildInfoById(id);		
		return monitorOutlineMapper.dropMonitorOutlineById(id);
	}
	
	/**
	 * @Title: queryMonitorOutlineById
	 * @Description:根据实体标识查询全站检测概要
	 * @param id 实体标识
	 * @return MonitorOutline
	 */
	@Override
	public MonitorOutline queryMonitorOutlineById(Integer id){
		return monitorOutlineMapper.queryMonitorOutlineById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryMonitorOutlineForPage
	 * @Description: 根据全站检测概要属性与分页信息分页查询全站检测概要信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param monitorOutline 实体
	 * @return List<MonitorOutline>
	 */
	@Override
	public Map<String, Object> queryMonitorOutlineForPage(Integer pagenum, Integer pagesize, String sort, MonitorOutline monitorOutline){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, MonitorOutline.class);
		List<MonitorOutline> entityList = monitorOutlineMapper.queryMonitorOutlineForPage(pageBounds, monitorOutline);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, MonitorOutline.class);
		}
		
		PageList<MonitorOutline> pagelist = (PageList<MonitorOutline>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryMonitorOutlineByProperty
	 * @Description:根据属性查询全站检测概要
	 * @return List<MonitorOutline>
	 */
	@Override
	public List<MonitorOutline> queryMonitorOutlineByProperty(Map<String, Object> map){
		return monitorOutlineMapper.queryMonitorOutlineByProperty(map);
	}

	@Override
	public Map<String, Object> queryOneNeedCheckPage() {
		return  monitorOutlineMapper.queryOneNeedCheckPage();
	}

	/*
	 * @Title: queryMonitorOutlineByPage
	 * @Description: TODO
	 * @param @param page
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param page
	 * @return
	 * @see com.hnjing.full.service.MonitorOutlineService#queryMonitorOutlineByPage(java.lang.String)
	 */ 
	@Override
	public MonitorOutline queryMonitorOutlineByPage(String page) {
		return monitorOutlineMapper.queryMonitorOutlineByPage(page);
	}

	/*
	 * @Title: exportMonitorOutlineByProperty
	 * @Description: TODO
	 * @param @param map
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param map
	 * @return
	 * @see com.hnjing.full.service.MonitorOutlineService#exportMonitorOutlineByProperty(java.util.Map)
	 */ 
	@Override
	public HSSFWorkbook exportMonitorOutlineByProperty(Map<String, Object> map) {
		String[] title = {"检测号", "是否我司", "站点IP", "检测网站", "检测网页量", "异常子链", "子链总量", "外链总量", "白名单总量", "疑似敏感词量", "检测时间"};
		String[][] data = null;
		List<MonitorOutline> info = queryMonitorOutlineByProperty(map);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][11];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = info.get(j).getId()+"";
				data[j][1] = info.get(j).getSelfSite()==1?"是":"否";
				data[j][2] = info.get(j).getIp();
				data[j][3] = info.get(j).getPage();
				data[j][4] = info.get(j).getSiteId()+"";
				data[j][5] = info.get(j).getErrorPage()+"";
				data[j][6] = info.get(j).getInnerPage()+"";
				data[j][7] = info.get(j).getOuterPage()+"";
				data[j][8] = info.get(j).getKeyWord()+"";
				data[j][9] = info.get(j).getIllegalWord()+"";
				data[j][10] = DateUtil.getDateTime(info.get(j).getGmtCreated());				
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("检测结果", title, data, null);
	}

	/*
	 * @Title: queryDataBeforeDay
	 * @Description: TODO
	 * @param @param days
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param days
	 * @return
	 * @see com.hnjing.full.service.MonitorOutlineService#queryDataBeforeDay(java.lang.Integer)
	 */ 
	@Override
	public List<MonitorOutline> queryDataBeforeDay(Integer days) {
		return monitorOutlineMapper.queryDataBeforeDay(days);
	}
}
