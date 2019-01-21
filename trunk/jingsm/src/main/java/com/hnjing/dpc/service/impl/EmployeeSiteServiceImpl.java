package com.hnjing.dpc.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;
import com.hnjing.dpc.model.entity.Employee;
import com.hnjing.dpc.model.entity.EmployeeSite;
import com.hnjing.dpc.model.dao.EmployeeSiteMapper;
import com.hnjing.dpc.service.EmployeeSiteService;

/**
 * @ClassName: EmployeeSiteService
 * @Description: 员工十分信息服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月03日 10时54分
 */
@Service("employeeSiteService")
@Transactional(readOnly=true)
public class  EmployeeSiteServiceImpl implements EmployeeSiteService {	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeSiteServiceImpl.class);
	
	@Autowired
    private EmployeeSiteMapper employeeSiteMapper;
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addEmployeeSite
	 * @Description:添加员工十分信息
	 * @param employeeSite 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public EmployeeSite addEmployeeSite(EmployeeSite employeeSite){
		int ret = employeeSiteMapper.addEmployeeSite(employeeSite);
		if(ret>0){
			return employeeSite;
		}
		return null;
	}
	
	/**
	 * @Title modifyEmployeeSite
	 * @Description:修改员工十分信息
	 * @param employeeSite 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyEmployeeSite(EmployeeSite employeeSite){
		return employeeSiteMapper.modifyEmployeeSite(employeeSite);
	}
	
	/**
	 * @Title: dropEmployeeSiteByEmpId
	 * @Description:删除员工十分信息
	 * @param empId 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropEmployeeSiteById(Integer empId){
		return employeeSiteMapper.dropEmployeeSiteById(empId);
	}
	
	/**
	 * @Title: queryEmployeeSiteByEmpId
	 * @Description:根据实体标识查询员工十分信息
	 * @param empId 实体标识
	 * @return EmployeeSite
	 */
	@Override
	public EmployeeSite queryEmployeeSiteById(Integer empId){
		return employeeSiteMapper.queryEmployeeSiteById(empId);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryEmployeeSiteForPage
	 * @Description: 根据员工十分信息属性与分页信息分页查询员工十分信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param employeeSite 实体
	 * @return List<EmployeeSite>
	 */
	@Override
	public Map<String, Object> queryEmployeeSiteForPage(Integer pagenum, Integer pagesize, String sort, EmployeeSite employeeSite){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, EmployeeSite.class);
		List<EmployeeSite> entityList = employeeSiteMapper.queryEmployeeSiteForPage(pageBounds, employeeSite);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, EmployeeSite.class);
		}
		
		PageList<EmployeeSite> pagelist = (PageList<EmployeeSite>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryEmployeeSiteByProperty
	 * @Description:根据属性查询员工十分信息
	 * @return List<EmployeeSite>
	 */
	@Override
	public List<EmployeeSite> queryEmployeeSiteByProperty(Map<String, Object> map){
		return employeeSiteMapper.queryEmployeeSiteByProperty(map);
	}

	/*
	 * @Title: bindEmployeeSF
	 * @Description: 
	 * @param @param employeeSite
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param employeeSite
	 * @return
	 * @see com.hnjing.dpc.service.EmployeeSiteService#bindEmployeeSF(com.hnjing.dpc.model.entity.EmployeeSite)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public EmployeeSite bindEmployeeSite(EmployeeSite employeeSite) {
		if(employeeSite==null || employeeSite.getEmpId()==null || employeeSite.getSfName()==null) {
			return employeeSite;
		}
		Map<String, Object> query = new HashMap<String, Object>();
		if(employeeSite.getSiteId()!=null) {
			query.put("siteId", employeeSite.getSiteId());
		}else {
			query.put("sfName", employeeSite.getSfName());
			//query.put("empId", employeeSite.getEmpId());
		}		
		List<EmployeeSite> list = queryEmployeeSiteByProperty(query);
		if(list==null || list.size()==0) {
			return addEmployeeSite(employeeSite);
		}else {
			employeeSite.setId(list.get(0).getId());
			modifyEmployeeSite(employeeSite);
			return employeeSite;
		}
	}

	/*
	 * @Title: queryEmployeeSiteBySiteId
	 * @Description: TODO
	 * @param @param siteId
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param siteId
	 * @return
	 * @see com.hnjing.dpc.service.EmployeeSiteService#queryEmployeeSiteBySiteId(java.lang.Integer)
	 */ 
	@Override
	public EmployeeSite queryEmployeeSiteBySiteId(Integer siteId) {
		return employeeSiteMapper.queryEmployeeSiteBySiteId(siteId);
	}


}
