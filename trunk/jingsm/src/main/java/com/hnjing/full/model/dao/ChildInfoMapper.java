package com.hnjing.full.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.full.model.entity.ChildInfo;

/**
 * @ClassName: ChildInfoMapper
 * @Description: 表tf_child_info映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
@Mapper
public interface ChildInfoMapper {

	/**
	 * @Title: addChildInfo
	 * @Description:添加表tf_child_info
	 * @param childInfo 实体
	 * @return Integer
	 */
	Integer addChildInfo(ChildInfo childInfo);
	
	Integer addChildInfoBatch(@Param("list") List<ChildInfo> list);
	
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
	 * @param pageBounds 分页信息
	 * @param childInfo 实体
	 * @return List<ChildInfo>
	 */
	List<ChildInfo> queryChildInfoForPage(PageBounds pageBounds, @Param("childInfo") ChildInfo childInfo);
	 
	 /**
	  * @Title: queryChildInfoByProperty
	  * @Description:根据属性查询表tf_child_info
	  * @return List<ChildInfo>
	  */
	 List<ChildInfo> queryChildInfoByProperty(@Param("childInfo") Map<String, Object> map);
	 
	 
	 
}
