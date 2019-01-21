package com.hnjing.ws.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ws.model.entity.SelfIp;

/**
 * @ClassName: SelfIpMapper
 * @Description: 我司IP信息映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年11月17日 11时28分
 */
@Mapper
public interface SelfIpMapper {

	/**
	 * @Title: addSelfIp
	 * @Description:添加我司IP信息
	 * @param selfIp 实体
	 * @return Integer
	 */
	Integer addSelfIp(SelfIp selfIp);
	
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
	 * @param pageBounds 分页信息
	 * @param selfIp 实体
	 * @return List<SelfIp>
	 */
	List<SelfIp> querySelfIpForPage(PageBounds pageBounds, @Param("selfIp") SelfIp selfIp);
	 
	 /**
	  * @Title: querySelfIpByProperty
	  * @Description:根据属性查询我司IP信息
	  * @return List<SelfIp>
	  */
	 List<SelfIp> querySelfIpByProperty(@Param("selfIp") Map<String, Object> map);
	 
	 /** 
	* @Title: initIPCountZero 
	* @Description: IP网站数据归零
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer initIPCountZero();
	 
	 /** 
	* @Title: initIPCount 
	* @Description:   重新计算我司IP下网站数量
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer initIPCount();
	 
	 
	 
}
