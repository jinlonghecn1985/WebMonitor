package com.hnjing.full.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hnjing.full.model.entity.ChildInfo;

/**
 * @ClassName: ChildInfo
 * @Description: 表tf_child_info服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
public interface ChildInfoService {

    /**
	 * @Title: addChildInfo
	 * @Description:添加表tf_child_info
	 * @param childInfo 实体
	 * @return Integer
	 */
	ChildInfo addChildInfo(ChildInfo childInfo);
	
	Integer addChildInfoBatch(List<ChildInfo> list);
	
	/**
	 * @Title modifyChildInfo
	 * @Description:修改表tf_child_info
	 * @param childInfo 实体
	 * @return Integer
	 */
	Integer modifyChildInfo(ChildInfo childInfo);
	
	/**
	 * @Title: dropChildInfoById
	 * @Description:删除表tf_child_info
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropChildInfoById(Integer id);
	
	/**
	 * @Title: queryChildInfoById
	 * @Description:根据实体标识查询表tf_child_info
	 * @param id 实体标识
	 * @return ChildInfo
	 */
	ChildInfo queryChildInfoById(Integer id);
	 
	/**
	 * @Title: queryChildInfoForPage
	 * @Description: 根据表tf_child_info属性与分页信息分页查询表tf_child_info信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param childInfo 实体
	 * @return List<ChildInfo>
	 */
	Map<String, Object> queryChildInfoForPage(Integer pagenum, Integer pagesize, String sort, ChildInfo childInfo);
	 
	 /**
	 * @Title: queryChildInfoByProperty
	 * @Description:根据属性查询表tf_child_info
	 * @return List<ChildInfo>
	 */
	 List<ChildInfo> queryChildInfoByProperty(Map<String, Object> map);	 
	 
}
