package com.hnjing.full.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.full.model.entity.SensitiveItem;

/**
 * @ClassName: SensitiveItemMapper
 * @Description: 网页敏感词映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
@Mapper
public interface SensitiveItemMapper {

	/**
	 * @Title: addSensitiveItem
	 * @Description:添加网页敏感词
	 * @param sensitiveItem 实体
	 * @return Integer
	 */
	Integer addSensitiveItem(SensitiveItem sensitiveItem);
	
	/**
	 * @Title modifySensitiveItem
	 * @Description:修改网页敏感词
	 * @param sensitiveItem 实体
	 * @return Integer
	 */
	Integer modifySensitiveItem(SensitiveItem sensitiveItem);
	
	/**
	 * @Title: dropSensitiveItemById
	 * @Description:删除网页敏感词
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropSensitiveItemById(Integer id);
	
	/**
	 * @Title: querySensitiveItemById
	 * @Description:根据实体标识查询网页敏感词
	 * @param id 实体标识
	 * @return SensitiveItem
	 */
	SensitiveItem querySensitiveItemById(Integer id);
	 
	/**
	 * @Title: querySensitiveItemForPage
	 * @Description: 根据网页敏感词属性与分页信息分页查询网页敏感词信息
	 * @param pageBounds 分页信息
	 * @param sensitiveItem 实体
	 * @return List<SensitiveItem>
	 */
	List<SensitiveItem> querySensitiveItemForPage(PageBounds pageBounds, @Param("sensitiveItem") SensitiveItem sensitiveItem);
	 
	 /**
	  * @Title: querySensitiveItemByProperty
	  * @Description:根据属性查询网页敏感词
	  * @return List<SensitiveItem>
	  */
	 List<SensitiveItem> querySensitiveItemByProperty(@Param("sensitiveItem") Map<String, Object> map);
	 
	 
	 
}
