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

import com.hnjing.full.model.dao.SensitiveItemMapper;
import com.hnjing.full.model.entity.SensitiveItem;
import com.hnjing.full.service.SensitiveItemService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.file.office.ExcelWriteUtil;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: SensitiveItem
 * @Description: 网页敏感词服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
@Service("sensitiveItemService")
@Transactional(readOnly=true)
public class  SensitiveItemServiceImpl implements SensitiveItemService {	
	private static final Logger logger = LoggerFactory.getLogger(SensitiveItemServiceImpl.class);
	
	@Autowired
    private SensitiveItemMapper sensitiveItemMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addSensitiveItem
	 * @Description:添加网页敏感词
	 * @param sensitiveItem 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public SensitiveItem addSensitiveItem(SensitiveItem sensitiveItem){
		if(sensitiveItem!=null && sensitiveItem.getPage()!=null && sensitiveItem.getPage().length()>400) {
			sensitiveItem.setPage(sensitiveItem.getPage().substring(0, 400));
		}
		int ret = sensitiveItemMapper.addSensitiveItem(sensitiveItem);
		if(ret>0){
			return sensitiveItem;
		}
		return null;
	}
	
	/**
	 * @Title modifySensitiveItem
	 * @Description:修改网页敏感词
	 * @param sensitiveItem 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifySensitiveItem(SensitiveItem sensitiveItem){
		return sensitiveItemMapper.modifySensitiveItem(sensitiveItem);
	}
	
	/**
	 * @Title: dropSensitiveItemById
	 * @Description:删除网页敏感词
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropSensitiveItemById(Integer id){
		return sensitiveItemMapper.dropSensitiveItemById(id);
	}
	
	/**
	 * @Title: querySensitiveItemById
	 * @Description:根据实体标识查询网页敏感词
	 * @param id 实体标识
	 * @return SensitiveItem
	 */
	@Override
	public SensitiveItem querySensitiveItemById(Integer id){
		return sensitiveItemMapper.querySensitiveItemById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: querySensitiveItemForPage
	 * @Description: 根据网页敏感词属性与分页信息分页查询网页敏感词信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param sensitiveItem 实体
	 * @return List<SensitiveItem>
	 */
	@Override
	public Map<String, Object> querySensitiveItemForPage(Integer pagenum, Integer pagesize, String sort, SensitiveItem sensitiveItem){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, SensitiveItem.class);
		List<SensitiveItem> entityList = sensitiveItemMapper.querySensitiveItemForPage(pageBounds, sensitiveItem);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, SensitiveItem.class);
		}
		
		PageList<SensitiveItem> pagelist = (PageList<SensitiveItem>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: querySensitiveItemByProperty
	 * @Description:根据属性查询网页敏感词
	 * @return List<SensitiveItem>
	 */
	@Override
	public List<SensitiveItem> querySensitiveItemByProperty(Map<String, Object> map){
		return sensitiveItemMapper.querySensitiveItemByProperty(map);
	}

	/*
	 * @Title: exportByProperty
	 * @Description: TODO
	 * @param @param map
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param map
	 * @return
	 * @see com.hnjing.full.service.SensitiveItemService#exportByProperty(java.util.Map)
	 */ 
	@Override
	public HSSFWorkbook exportByProperty(Map<String, Object> map) {
		String[] title = {"类型", "敏感词", "上下文",  "网站"};
		String[][] data = null;
		List<SensitiveItem> info = querySensitiveItemByProperty(map);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][11];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = info.get(j).getStatus()==0?"疑似词":"白名单";
				data[j][1] = info.get(j).getKeyWord();
				data[j][2] = info.get(j).getContext();
				data[j][3] = info.get(j).getPage();		
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("敏感词", title, data, null);
	}

	


}
