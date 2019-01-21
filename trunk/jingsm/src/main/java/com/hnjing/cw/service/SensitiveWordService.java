package com.hnjing.cw.service;

import java.util.List;
import java.util.Map;


import com.hnjing.cw.model.entity.SensitiveWord;

/**
 * @ClassName: SensitiveWord
 * @Description: 敏感词服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
public interface SensitiveWordService {

    /**
	 * @Title: addSensitiveWord
	 * @Description:添加敏感词
	 * @param sensitiveWord 实体
	 * @return Integer
	 */
	SensitiveWord addSensitiveWord(SensitiveWord sensitiveWord);
	
	/**
	 * @Title modifySensitiveWord
	 * @Description:修改敏感词
	 * @param sensitiveWord 实体
	 * @return Integer
	 */
	Integer modifySensitiveWord(SensitiveWord sensitiveWord);
	
	/**
	 * @Title: dropSensitiveWordById
	 * @Description:删除敏感词
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropSensitiveWordById(Integer id);
	
	/**
	 * @Title: querySensitiveWordById
	 * @Description:根据实体标识查询敏感词
	 * @param id 实体标识
	 * @return SensitiveWord
	 */
	SensitiveWord querySensitiveWordById(Integer id);
	 
	/**
	 * @Title: querySensitiveWordForPage
	 * @Description: 根据敏感词属性与分页信息分页查询敏感词信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param sensitiveWord 实体
	 * @return List<SensitiveWord>
	 */
	Map<String, Object> querySensitiveWordForPage(Integer pagenum, Integer pagesize, String sort, SensitiveWord sensitiveWord);
	 
	 /**
	 * @Title: querySensitiveWordByProperty
	 * @Description:根据属性查询敏感词
	 * @return List<SensitiveWord>
	 */
	 List<SensitiveWord> querySensitiveWordByProperty(Map<String, Object> map);	 
	 
}
