package com.hnjing.sync.service;

import java.util.List;
import java.util.Map;

import com.hnjing.sync.model.entity.DataSync;

/**
 * @ClassName: DataSync
 * @Description: 数据中心服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月26日 17时21分
 */
public interface DataSyncService {

    /**
	 * @Title: addDataSync
	 * @Description:添加数据中心
	 * @param dataSync 实体
	 * @return Integer
	 */
	DataSync addDataSync(DataSync dataSync);
	
	/**
	 * @Title modifyDataSync
	 * @Description:修改数据中心
	 * @param dataSync 实体
	 * @return Integer
	 */
	Integer modifyDataSync(DataSync dataSync);
	
	/** 
	* @Title: initUpdateStatusBySource 
	* @Description: 初始化更新状态
	* @param source
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer initUpdateStatusBySource(Integer source);
	
	
	/** 
	* @Title: overUpdateStatusBySource 
	* @Description: 完成更新
	* @param source
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer overUpdateStatusBySource(Integer source);
	
	/**
	 * @Title: dropDataSyncById
	 * @Description:删除数据中心
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropDataSyncById(Integer id);
	
	/**
	 * @Title: queryDataSyncById
	 * @Description:根据实体标识查询数据中心
	 * @param id 实体标识
	 * @return DataSync
	 */
	DataSync queryDataSyncById(Integer id);
	 
	/**
	 * @Title: queryDataSyncForPage
	 * @Description: 根据数据中心属性与分页信息分页查询数据中心信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param dataSync 实体
	 * @return List<DataSync>
	 */
	Map<String, Object> queryDataSyncForPage(Integer pagenum, Integer pagesize, String sort, DataSync dataSync);
	 
	 /**
	 * @Title: queryDataSyncByProperty
	 * @Description:根据属性查询数据中心
	 * @return List<DataSync>
	 */
	 List<DataSync> queryDataSyncByProperty(Map<String, Object> map);	 
	 

	/** 
	* @Title: modifyDataSyncDeleteOnBatch 
	* @Description: 批量更新删除状态
	* @param list
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer modifyDataSyncDeleteOnBatch(List<DataSync> list);
	 
	
	 
}
