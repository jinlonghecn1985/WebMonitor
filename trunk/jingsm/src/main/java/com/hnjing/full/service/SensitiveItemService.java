package com.hnjing.full.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.full.model.entity.SensitiveItem;

/**
 * @ClassName: SensitiveItem
 * @Description: 网页敏感词服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月08日 09时59分
 */
public interface SensitiveItemService {

    /**
	 * @Title: addSensitiveItem
	 * @Description:添加网页敏感词
	 * @param sensitiveItem 实体
	 * @return Integer
	 */
	SensitiveItem addSensitiveItem(SensitiveItem sensitiveItem);
	
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
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param sensitiveItem 实体
	 * @return List<SensitiveItem>
	 */
	Map<String, Object> querySensitiveItemForPage(Integer pagenum, Integer pagesize, String sort, SensitiveItem sensitiveItem);
	 
	 /**
	 * @Title: querySensitiveItemByProperty
	 * @Description:根据属性查询网页敏感词
	 * @return List<SensitiveItem>
	 */
	 List<SensitiveItem> querySensitiveItemByProperty(Map<String, Object> map);

	/** 
	* @Title: exportByProperty 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param transBean2Map
	* @return  
	* HSSFWorkbook    返回类型 
	* @throws 
	*/
	HSSFWorkbook exportByProperty(Map<String, Object> map);	
	 
	
	 
}
