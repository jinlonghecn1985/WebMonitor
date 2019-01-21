package com.hnjing.cw.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.cw.model.entity.SensitiveWord;

/**
 * @ClassName: SensitiveWordMapper
 * @Description: 敏感词映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
@Mapper
public interface SensitiveWordMapper {

	/**
	 * @Title: addSensitiveWord
	 * @Description:添加敏感词
	 * @param sensitiveWord 实体
	 * @return Integer
	 */
	Integer addSensitiveWord(SensitiveWord sensitiveWord);
	
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
	 * @param pageBounds 分页信息
	 * @param sensitiveWord 实体
	 * @return List<SensitiveWord>
	 */
	List<SensitiveWord> querySensitiveWordForPage(PageBounds pageBounds, @Param("sensitiveWord") SensitiveWord sensitiveWord);
	 
	 /**
	  * @Title: querySensitiveWordByProperty
	  * @Description:根据属性查询敏感词
	  * @return List<SensitiveWord>
	  */
	 List<SensitiveWord> querySensitiveWordByProperty(@Param("sensitiveWord") Map<String, Object> map);
	 
	 
	 
}
