package com.hnjing.full.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.full.model.dao.LinkItemMapper;
import com.hnjing.full.model.entity.LinkItem;
import com.hnjing.full.service.LinkItemService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: LinkItem
 * @Description: 网页链接信息服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
@Service("linkItemService")
@Transactional(readOnly=true)
public class  LinkItemServiceImpl implements LinkItemService {	
	private static final Logger logger = LoggerFactory.getLogger(LinkItemServiceImpl.class);
	
	@Autowired
    private LinkItemMapper linkItemMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addLinkItem
	 * @Description:添加网页链接信息
	 * @param linkItem 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public LinkItem addLinkItem(LinkItem linkItem){
		if(linkItem!=null && linkItem.getLink()!=null && linkItem.getLink().length()>400) {
			linkItem.setLink(linkItem.getLink().substring(0, 400));
		}
		if(linkItem!=null && linkItem.getPage()!=null && linkItem.getPage().length()>400) {
			linkItem.setPage(linkItem.getPage().substring(0, 400));
		}
		int ret = linkItemMapper.addLinkItem(linkItem);
		if(ret>0){
			return linkItem;
		}
		return null;
	}
	
	/**
	 * @Title modifyLinkItem
	 * @Description:修改网页链接信息
	 * @param linkItem 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyLinkItem(LinkItem linkItem){
		return linkItemMapper.modifyLinkItem(linkItem);
	}
	
	/**
	 * @Title: dropLinkItemById
	 * @Description:删除网页链接信息
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropLinkItemById(Integer id){
		return linkItemMapper.dropLinkItemById(id);
	}
	
	/**
	 * @Title: queryLinkItemById
	 * @Description:根据实体标识查询网页链接信息
	 * @param id 实体标识
	 * @return LinkItem
	 */
	@Override
	public LinkItem queryLinkItemById(Integer id){
		return linkItemMapper.queryLinkItemById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryLinkItemForPage
	 * @Description: 根据网页链接信息属性与分页信息分页查询网页链接信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param linkItem 实体
	 * @return List<LinkItem>
	 */
	@Override
	public Map<String, Object> queryLinkItemForPage(Integer pagenum, Integer pagesize, String sort, LinkItem linkItem){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, LinkItem.class);
		List<LinkItem> entityList = linkItemMapper.queryLinkItemForPage(pageBounds, linkItem);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, LinkItem.class);
		}
		
		PageList<LinkItem> pagelist = (PageList<LinkItem>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryLinkItemByProperty
	 * @Description:根据属性查询网页链接信息
	 * @return List<LinkItem>
	 */
	@Override
	public List<LinkItem> queryLinkItemByProperty(Map<String, Object> map){
		return linkItemMapper.queryLinkItemByProperty(map);
	}

	/*
	 * @Title: addLinkItemBatch
	 * @Description: TODO
	 * @param @param list
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param list
	 * @return
	 * @see com.hnjing.full.service.LinkItemService#addLinkItemBatch(java.util.List)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer addLinkItemBatch(List<LinkItem> list) {
		return linkItemMapper.addLinkItemBatch(list);
	}


}
