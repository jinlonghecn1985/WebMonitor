package com.hnjing.sync.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.sync.model.dao.DataSyncMapper;
import com.hnjing.sync.model.entity.DataSync;
import com.hnjing.sync.service.DataSyncService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;


/**
 * @ClassName: DataSync
 * @Description: 数据中心服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月26日 17时21分
 */
@Service("dataSyncService")
@Transactional(readOnly=true)
public class  DataSyncServiceImpl implements DataSyncService {	
	private static final Logger logger = LoggerFactory.getLogger(DataSyncServiceImpl.class);
	
	@Autowired
    private DataSyncMapper dataSyncMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addDataSync
	 * @Description:添加数据中心
	 * @param dataSync 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public DataSync addDataSync(DataSync dataSync){
		int ret = dataSyncMapper.addDataSync(dataSync);
		if(ret>0){
			return dataSync;
		}
		return null;
	}
	
	/**
	 * @Title modifyDataSync
	 * @Description:修改数据中心
	 * @param dataSync 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyDataSync(DataSync dataSync){
		return dataSyncMapper.modifyDataSync(dataSync);
	}
	
	/**
	 * @Title: dropDataSyncById
	 * @Description:删除数据中心
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropDataSyncById(Integer id){
		return dataSyncMapper.dropDataSyncById(id);
	}
	
	/**
	 * @Title: queryDataSyncById
	 * @Description:根据实体标识查询数据中心
	 * @param id 实体标识
	 * @return DataSync
	 */
	@Override
	public DataSync queryDataSyncById(Integer id){
		return dataSyncMapper.queryDataSyncById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryDataSyncForPage
	 * @Description: 根据数据中心属性与分页信息分页查询数据中心信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param dataSync 实体
	 * @return List<DataSync>
	 */
	@Override
	public Map<String, Object> queryDataSyncForPage(Integer pagenum, Integer pagesize, String sort, DataSync dataSync){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, DataSync.class);
		List<DataSync> entityList = dataSyncMapper.queryDataSyncForPage(pageBounds, dataSync);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, DataSync.class);
		}
		
		PageList<DataSync> pagelist = (PageList<DataSync>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryDataSyncByProperty
	 * @Description:根据属性查询数据中心
	 * @return List<DataSync>
	 */
	@Override
	public List<DataSync> queryDataSyncByProperty(Map<String, Object> map){
		return dataSyncMapper.queryDataSyncByProperty(map);
	}

	/*
	 * @Title: initUpdateStatusBySource
	 * @Description: 
	 * @param @param source
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param source
	 * @return
	 * @see com.hnjing.sync.service.DataSyncService#initUpdateStatusBySource(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer initUpdateStatusBySource(Integer source) {
		return dataSyncMapper.initUpdateStatusBySource(source);
	}

	/*
	 * @Title: overUpdateStatusBySource
	 * @Description: 
	 * @param @param source
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param source
	 * @return
	 * @see com.hnjing.sync.service.DataSyncService#overUpdateStatusBySource(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer overUpdateStatusBySource(Integer source) {
		return dataSyncMapper.overUpdateStatusBySource(source);
	}

	/*
	 * @Title: modifyDataSyncDeleteOnBatch
	 * @Description: 
	 * @param @param list
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param list
	 * @return
	 * @see com.hnjing.sync.service.DataSyncService#modifyDataSyncDeleteOnBatch(java.util.List)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer modifyDataSyncDeleteOnBatch(List<DataSync> list) {
		return dataSyncMapper.modifyDataSyncDeleteOnBatch(list);
	}

	
	


}
