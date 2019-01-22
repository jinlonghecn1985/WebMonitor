package com.hnjing.sync.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.sync.model.entity.DataSync;

/**
 * @ClassName: DataSyncMapper
 * @Description: 数据中心映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月26日 17时21分
 */
@Mapper
public interface DataSyncMapper {

	/**
	 * @Title: addDataSync
	 * @Description:添加数据中心
	 * @param dataSync 实体
	 * @return Integer
	 */
	Integer addDataSync(DataSync dataSync);
	
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
	* @Title: modifyDataSyncDeleteOnBatch 
	* @Description: 批量更新删除状态
	* @param list
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer modifyDataSyncDeleteOnBatch(@Param("list") List<DataSync> list);
	
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
	 * @param pageBounds 分页信息
	 * @param dataSync 实体
	 * @return List<DataSync>
	 */
	List<DataSync> queryDataSyncForPage(PageBounds pageBounds, @Param("dataSync") DataSync dataSync);
	 
	 /**
	  * @Title: queryDataSyncByProperty
	  * @Description:根据属性查询数据中心
	  * @return List<DataSync>
	  */
	 List<DataSync> queryDataSyncByProperty(@Param("dataSync") Map<String, Object> map);
	 
	 
	 
}
