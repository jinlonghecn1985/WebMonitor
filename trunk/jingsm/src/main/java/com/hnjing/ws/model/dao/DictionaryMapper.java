package com.hnjing.ws.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ws.model.entity.Dictionary;

/**
 * @ClassName: DictionaryMapper
 * @Description: 字典信息映射
 * @author: JIM
 * @email: mailto:
 * @date: 2017年07月26日 17时26分
 */
@Mapper
public interface DictionaryMapper {

	/**
	 * @Title: addDictionary
	 * @Description:添加字典信息
	 * @param dictionary 实体
	 * @return Integer
	 */
	Integer addDictionary(Dictionary dictionary);
	
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
	 * @Title: queryDictionaryForPage
	 * @Description: 根据字典信息属性与分页信息分页查询字典信息信息
	 * @param pageBounds 分页信息
	 * @param dictionary 实体
	 * @return List<Dictionary>
	 */
	List<Dictionary> queryDictionaryForPage(PageBounds pageBounds, @Param("dictionary") Dictionary dictionary);
	 
	 /**
	  * @Title: queryDictionaryByProperty
	  * @Description:根据属性查询字典信息
	  * @return List<Dictionary>
	  */
	 List<Dictionary> queryDictionaryByProperty(@Param("dictionary") Map<String, Object> map);
	 
	 /**
	 * @Title: clearTableData
	 * @Description: 清空表信息
	 * @return 
	 */
	 void clearTableData();
	/*
	* @Title: queryDictionary 
	* @Description: 根据属性查询字典信息
	* @param map
	* @return  
	* Dictionary    返回类型 
	* @throws 
	*/
	Dictionary queryDictionary(@Param("dicGroup")String dicGroup, @Param("dicCode")String dicCode);	 
	 
	 
}
