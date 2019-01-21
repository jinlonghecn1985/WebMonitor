package com.hnjing.full.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.full.model.entity.LinkItem;

/**
 * @ClassName: LinkItemMapper
 * @Description: 网页链接信息映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
@Mapper
public interface LinkItemMapper {

	/**
	 * @Title: addLinkItem
	 * @Description:添加网页链接信息
	 * @param linkItem 实体
	 * @return Integer
	 */
	Integer addLinkItem(LinkItem linkItem);
	
	Integer addLinkItemBatch(@Param("list") List<LinkItem> list);
	
	/**
	 * @Title modifyLinkItem
	 * @Description:修改网页链接信息
	 * @param linkItem 实体
	 * @return Integer
	 */
	Integer modifyLinkItem(LinkItem linkItem);
	
	/**
	 * @Title: dropLinkItemById
	 * @Description:删除网页链接信息
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropLinkItemById(Integer id);
	
	/**
	 * @Title: queryLinkItemById
	 * @Description:根据实体标识查询网页链接信息
	 * @param id 实体标识
	 * @return LinkItem
	 */
	LinkItem queryLinkItemById(Integer id);
	 
	/**
	 * @Title: queryLinkItemForPage
	 * @Description: 根据网页链接信息属性与分页信息分页查询网页链接信息信息
	 * @param pageBounds 分页信息
	 * @param linkItem 实体
	 * @return List<LinkItem>
	 */
	List<LinkItem> queryLinkItemForPage(PageBounds pageBounds, @Param("linkItem") LinkItem linkItem);
	 
	 /**
	  * @Title: queryLinkItemByProperty
	  * @Description:根据属性查询网页链接信息
	  * @return List<LinkItem>
	  */
	 List<LinkItem> queryLinkItemByProperty(@Param("linkItem") Map<String, Object> map);
	 
	 
	 
}
