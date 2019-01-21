package com.hnjing.ws.service;

import java.util.List;
import java.util.Map;


import com.hnjing.ws.model.entity.SelfIp;

/**
 * @ClassName: SelfIp
 * @Description: 我司IP信息服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
public interface SelfIpService {

    /**
	 * @Title: addSelfIp
	 * @Description:添加我司IP信息
	 * @param selfIp 实体
	 * @return Integer
	 */
	SelfIp addSelfIp(SelfIp selfIp);
	
	/**
	 * @Title modifySelfIp
	 * @Description:修改我司IP信息
	 * @param selfIp 实体
	 * @return Integer
	 */
	Integer modifySelfIp(SelfIp selfIp);
	
	/**
	 * @Title: dropSelfIpById
	 * @Description:删除我司IP信息
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropSelfIpById(Integer id);
	
	/**
	 * @Title: querySelfIpById
	 * @Description:根据实体标识查询我司IP信息
	 * @param id 实体标识
	 * @return SelfIp
	 */
	SelfIp querySelfIpById(Integer id);
	 
	/**
	 * @Title: querySelfIpForPage
	 * @Description: 根据我司IP信息属性与分页信息分页查询我司IP信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param selfIp 实体
	 * @return List<SelfIp>
	 */
	Map<String, Object> querySelfIpForPage(Integer pagenum, Integer pagesize, String sort, SelfIp selfIp);
	 
	 /**
	 * @Title: querySelfIpByProperty
	 * @Description:根据属性查询我司IP信息
	 * @return List<SelfIp>
	 */
	 List<SelfIp> querySelfIpByProperty(Map<String, Object> map);	 
	 
	 
	 /** 
	* @Title: initIPCount 
	* @Description: IP检测完成后，初始化我司IP下域名分布情况
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer initIPCount();
	
	
	/** 
	* @Title: isIpMyCompany 
	* @Description: 判断IP是否我司
	* @param ip
	* @return  
	* boolean    返回类型 
	* @throws 
	*/
	boolean isIpMyCompany(String ip);
}
