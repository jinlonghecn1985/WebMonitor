package com.hnjing.cw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.cw.model.dao.SensitiveRecordMapper;
import com.hnjing.cw.model.entity.SensitiveRecord;
import com.hnjing.cw.service.SensitiveRecordService;
import com.hnjing.full.model.entity.MonitorOutline;
import com.hnjing.utils.Constant;
import com.hnjing.utils.DateUtil;
import com.hnjing.utils.file.office.ExcelWriteUtil;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: SensitiveRecord
 * @Description: 敏感词记录服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
@Service("sensitiveRecordService")
@Transactional(readOnly=true)
public class  SensitiveRecordServiceImpl implements SensitiveRecordService {	
	private static final Logger logger = LoggerFactory.getLogger(SensitiveRecordServiceImpl.class);
	
	@Autowired
    private SensitiveRecordMapper sensitiveRecordMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addSensitiveRecord
	 * @Description:添加敏感词记录
	 * @param sensitiveRecord 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public SensitiveRecord addSensitiveRecord(SensitiveRecord sensitiveRecord){
		int ret = sensitiveRecordMapper.addSensitiveRecord(sensitiveRecord);
		if(ret>0){
			return sensitiveRecord;
		}
		return null;
	}
	
	/**
	 * @Title modifySensitiveRecord
	 * @Description:修改敏感词记录
	 * @param sensitiveRecord 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifySensitiveRecord(SensitiveRecord sensitiveRecord){
		return sensitiveRecordMapper.modifySensitiveRecord(sensitiveRecord);
	}
	
	/**
	 * @Title: dropSensitiveRecordById
	 * @Description:删除敏感词记录
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropSensitiveRecordById(Integer id){
		return sensitiveRecordMapper.dropSensitiveRecordById(id);
	}
	
	/**
	 * @Title: querySensitiveRecordById
	 * @Description:根据实体标识查询敏感词记录
	 * @param id 实体标识
	 * @return SensitiveRecord
	 */
	@Override
	public SensitiveRecord querySensitiveRecordById(Integer id){
		return sensitiveRecordMapper.querySensitiveRecordById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: querySensitiveRecordForPage
	 * @Description: 根据敏感词记录属性与分页信息分页查询敏感词记录信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param sensitiveRecord 实体
	 * @return List<SensitiveRecord>
	 */
	@Override
	public Map<String, Object> querySensitiveRecordForPage(Integer pagenum, Integer pagesize, String sort, SensitiveRecord sensitiveRecord){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, SensitiveRecord.class);
		List<SensitiveRecord> entityList = sensitiveRecordMapper.querySensitiveRecordForPage(pageBounds, sensitiveRecord);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, SensitiveRecord.class);
		}
		
		PageList<SensitiveRecord> pagelist = (PageList<SensitiveRecord>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: querySensitiveRecordByProperty
	 * @Description:根据属性查询敏感词记录
	 * @return List<SensitiveRecord>
	 */
	@Override
	public List<SensitiveRecord> querySensitiveRecordByProperty(Map<String, Object> map){
		return sensitiveRecordMapper.querySensitiveRecordByProperty(map);
	}

	/*
	 * @Title: clearTable
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.cw.service.SensitiveRecordService#clearTable()
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer clearTable() {
		return sensitiveRecordMapper.clearTable();
	}

	/*
	 * @Title: exportByProperty
	 * @Description: 
	 * @param @param map
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param map
	 * @return
	 * @see com.hnjing.cw.service.SensitiveRecordService#exportByProperty(java.util.Map)
	 */ 
	@Override
	public HSSFWorkbook exportByProperty(Map<String, Object> map) {
		String[] title = {"站点", "类型", "敏感词", "上下文",  "网站","检测时间"};
		String[][] data = null;
		List<SensitiveRecord> info = querySensitiveRecordByProperty(map);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][11];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = info.get(j).getSiteId()+"";
				data[j][1] = info.get(j).getStatus()==0?"疑似词":"白名单";
				data[j][2] = info.get(j).getKeyWord();
				data[j][3] = info.get(j).getKeyWords();
				data[j][4] = info.get(j).getPage();
				data[j][5] = DateUtil.getDateTime(info.get(j).getGmtCreated());				
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("敏感词", title, data, null);
	}


}
