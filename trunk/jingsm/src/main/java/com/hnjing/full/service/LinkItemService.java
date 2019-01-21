package com.hnjing.full.service;

import java.util.List;
import java.util.Map;

import com.hnjing.full.model.entity.LinkItem;

/**
 * @ClassName: LinkItem
 * @Description: 网页链接信息服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
public interface LinkItemService {

    /**
	 * @Title: addLinkItem
	 * @Description:添加网页链接信息
	 * @param linkItem 实体
	 * @return Integer
	 */
	LinkItem addLinkItem(LinkItem linkItem);
	
	Integer addLinkItemBatch(List<LinkItem> list);
	
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
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param linkItem 实体
	 * @return List<LinkItem>
	 */
	Map<String, Object> queryLinkItemForPage(Integer pagenum, Integer pagesize, String sort, LinkItem linkItem);
	 
	 /**
	 * @Title: queryLinkItemByProperty
	 * @Description:根据属性查询网页链接信息
	 * @return List<LinkItem>
	 */
	 List<LinkItem> queryLinkItemByProperty(Map<String, Object> map);	 
	 
}
