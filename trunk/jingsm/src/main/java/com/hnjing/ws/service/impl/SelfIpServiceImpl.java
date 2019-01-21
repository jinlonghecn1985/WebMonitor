package com.hnjing.ws.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;
import com.hnjing.ws.model.dao.SelfIpMapper;
import com.hnjing.ws.model.entity.SelfIp;
import com.hnjing.ws.service.SelfIpService;

/**
 * @ClassName: SelfIp
 * @Description: 我司IP信息服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Service("selfIpService")
@Transactional(readOnly=true)
public class  SelfIpServiceImpl implements SelfIpService {	
	private static final Logger logger = LoggerFactory.getLogger(SelfIpServiceImpl.class);
	
	@Autowired
    private SelfIpMapper selfIpMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	private static Map<String, String> ipString = new HashMap<String, String>(); //我司IP数据
	
	
	/**
	 * @Title: addSelfIp
	 * @Description:添加我司IP信息
	 * @param selfIp 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public SelfIp addSelfIp(SelfIp selfIp){
		int ret = selfIpMapper.addSelfIp(selfIp);
		if(ret>0){
			return selfIp;
		}
		return null;
	}
	
	/**
	 * @Title modifySelfIp
	 * @Description:修改我司IP信息
	 * @param selfIp 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifySelfIp(SelfIp selfIp){
		return selfIpMapper.modifySelfIp(selfIp);
	}
	
	/**
	 * @Title: dropSelfIpById
	 * @Description:删除我司IP信息
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropSelfIpById(Integer id){
		return selfIpMapper.dropSelfIpById(id);
	}
	
	/**
	 * @Title: querySelfIpById
	 * @Description:根据实体标识查询我司IP信息
	 * @param id 实体标识
	 * @return SelfIp
	 */
	@Override
	public SelfIp querySelfIpById(Integer id){
		return selfIpMapper.querySelfIpById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: querySelfIpForPage
	 * @Description: 根据我司IP信息属性与分页信息分页查询我司IP信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param selfIp 实体
	 * @return List<SelfIp>
	 */
	@Override
	public Map<String, Object> querySelfIpForPage(Integer pagenum, Integer pagesize, String sort, SelfIp selfIp){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, SelfIp.class);
		List<SelfIp> entityList = selfIpMapper.querySelfIpForPage(pageBounds, selfIp);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, SelfIp.class);
		}
		
		PageList<SelfIp> pagelist = (PageList<SelfIp>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: querySelfIpByProperty
	 * @Description:根据属性查询我司IP信息
	 * @return List<SelfIp>
	 */
	@Override
	public List<SelfIp> querySelfIpByProperty(Map<String, Object> map){
		return selfIpMapper.querySelfIpByProperty(map);
	}

	/*
	 * @Title: initIPCount
	 * @Description: IP检测完成后，初始化我司IP下域名分布情况
	 * @param 参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.ws.service.SelfIpService#initIPCount()
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer initIPCount() {
		selfIpMapper.initIPCountZero();
		return selfIpMapper.initIPCount();
	}
	
	
	/*
	 * @Title: isIpMyCompany
	 * @Description: 判断是否我司IP
	 * @param @param ip
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param ip
	 * @return
	 * @see com.hnjing.ws.service.SiteMonitorService#isIpMyCompany(java.lang.String)
	 */ 
	@Override
	public boolean isIpMyCompany(String ip) {		
		if( ip==null ||  ip.length()==0) {
			return false;
		}
		ip = ip.trim();
		if(ipString==null || ipString.size()==0) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<SelfIp> ips = querySelfIpByProperty(map);
			if(ips!=null && ips.size()>0) {
				for(SelfIp si : ips) {
					ipString.put(si.getIp(), si.getIp());
				}				
			}			
		}
		
		if(ipString!=null && ipString.size()>0) {
			if(ipString.containsKey(ip)) {
				return true;
			}
		}
		return false;		
	}


}
