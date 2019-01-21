package com.hnjing.ws.service;

import java.util.List;
import java.util.Map;


import com.hnjing.ws.model.entity.Dictionary;

/**
 * @ClassName: DictionaryService
 * @Description: 字典信息服务接口
 * @author: JIM
 * @email: mailto:
 * @date: 2017年07月26日 17时26分
 */
public interface DictionaryService {

    /**
	 * @Title: addDictionary
	 * @Description:添加字典信息
	 * @param dictionary 实体
	 * @return Integer
	 */
	Dictionary addDictionary(Dictionary dictionary);
	
	/**
	 * @Title modifyDictionary
	 * @Description:修改字典信息
	 * @param dictionary 实体
	 * @return Integer
	 */
	Integer modifyDictionary(Dictionary dictionary);
	
	/**
	 * @Title: dropDictionaryByDicId
	 * @Description:删除字典信息
	 * @param dicId 实体标识
	 * @return Integer
	 */
	Integer dropDictionaryByDicId(String dicId);
	
	/**
	 * @Title: queryDictionaryByDicId
	 * @Description:根据实体标识查询字典信息
	 * @param dicId 实体标识
	 * @return Dictionary
	 */
	Dictionary queryDictionaryByDicId(String dicId);
	
	/** 
	* @Title: queryDictionary 
	* @Description: 根据字段组及标签查询字典信息
	* @param dicGroup
	* @param dicCode
	* @return  
	* Dictionary    返回类型 
	* @throws 
	*/
	Dictionary queryDictionary(String dicGroup, String dicCode);
	 
	/**
	 * @Title: queryDictionaryForPage
	 * @Description: 根据字典信息属性与分页信息分页查询字典信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param dictionary 实体
	 * @return List<Dictionary>
	 */
	Map<String, Object> queryDictionaryForPage(Integer pagenum, Integer pagesize, String sort, Dictionary dictionary);
	 
	 /**
	 * @Title: queryDictionaryByProperty
	 * @Description:根据属性查询字典信息
	 * @return List<Dictionary>
	 */
	 List<Dictionary> queryDictionaryByProperty(Map<String, Object> map);	 
	 
	 /** 
		* @Title: queryParamsValue 
		* @Description: 获取配置
		* @param i
		* @return  
		* String    返回类型 
		* @throws 
		*/
	String queryParamsValue(int i);
	 
}
