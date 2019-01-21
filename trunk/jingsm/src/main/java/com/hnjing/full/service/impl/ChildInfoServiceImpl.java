package com.hnjing.full.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.full.model.dao.ChildInfoMapper;
import com.hnjing.full.model.entity.ChildInfo;
import com.hnjing.full.service.ChildInfoService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: ChildInfo
 * @Description: 表tf_child_info服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月07日 10时31分
 */
@Service("childInfoService")
@Transactional(readOnly=true)
public class  ChildInfoServiceImpl implements ChildInfoService {	
	private static final Logger logger = LoggerFactory.getLogger(ChildInfoServiceImpl.class);
	
	@Autowired
    private ChildInfoMapper childInfoMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addChildInfo
	 * @Description:添加表tf_child_info
	 * @param childInfo 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public ChildInfo addChildInfo(ChildInfo childInfo){
		if(childInfo!=null && childInfo.getPage()!=null && childInfo.getPage().length()>400) {
			childInfo.setPage(childInfo.getPage().substring(0, 400));
		}
		int ret = childInfoMapper.addChildInfo(childInfo);
		if(ret>0){
			return childInfo;
		}
		return null;
	}
	
	/**
	 * @Title modifyChildInfo
	 * @Description:修改表tf_child_info
	 * @param childInfo 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyChildInfo(ChildInfo childInfo){
		return childInfoMapper.modifyChildInfo(childInfo);
	}
	
	/**
	 * @Title: dropChildInfoById
	 * @Description:删除表tf_child_info
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropChildInfoById(Integer id){
		return childInfoMapper.dropChildInfoById(id);
	}
	
	/**
	 * @Title: queryChildInfoById
	 * @Description:根据实体标识查询表tf_child_info
	 * @param id 实体标识
	 * @return ChildInfo
	 */
	@Override
	public ChildInfo queryChildInfoById(Integer id){
		return childInfoMapper.queryChildInfoById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryChildInfoForPage
	 * @Description: 根据表tf_child_info属性与分页信息分页查询表tf_child_info信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param childInfo 实体
	 * @return List<ChildInfo>
	 */
	@Override
	public Map<String, Object> queryChildInfoForPage(Integer pagenum, Integer pagesize, String sort, ChildInfo childInfo){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, ChildInfo.class);
		List<ChildInfo> entityList = childInfoMapper.queryChildInfoForPage(pageBounds, childInfo);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, ChildInfo.class);
		}
		
		PageList<ChildInfo> pagelist = (PageList<ChildInfo>) entityList;		
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryChildInfoByProperty
	 * @Description:根据属性查询表tf_child_info
	 * @return List<ChildInfo>
	 */
	@Override
	public List<ChildInfo> queryChildInfoByProperty(Map<String, Object> map){
		return childInfoMapper.queryChildInfoByProperty(map);
	}

	/*
	 * @Title: addChildInfoBatch
	 * @Description: TODO
	 * @param @param list
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param list
	 * @return
	 * @see com.hnjing.full.service.ChildInfoService#addChildInfoBatch(java.util.List)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer addChildInfoBatch(List<ChildInfo> list) {
		return childInfoMapper.addChildInfoBatch(list);
	}


}
