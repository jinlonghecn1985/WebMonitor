package com.hnjing.cw.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.cw.model.entity.SensitiveRecord;

/**
 * @ClassName: SensitiveRecordMapper
 * @Description: 敏感词记录映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
@Mapper
public interface SensitiveRecordMapper {

	/**
	 * @Title: addSensitiveRecord
	 * @Description:添加敏感词记录
	 * @param sensitiveRecord 实体
	 * @return Integer
	 */
	Integer addSensitiveRecord(SensitiveRecord sensitiveRecord);
	
	/**
	 * @Title modifySensitiveRecord
	 * @Description:修改敏感词记录
	 * @param sensitiveRecord 实体
	 * @return Integer
	 */
	Integer modifySensitiveRecord(SensitiveRecord sensitiveRecord);
	
	/**
	 * @Title: dropSensitiveRecordById
	 * @Description:删除敏感词记录
	 * @param id 实体标识
	 * @return Integer
	 */
	Integer dropSensitiveRecordById(Integer id);
	
	/**
	 * @Title: querySensitiveRecordById
	 * @Description:根据实体标识查询敏感词记录
	 * @param id 实体标识
	 * @return SensitiveRecord
	 */
	SensitiveRecord querySensitiveRecordById(Integer id);
	 
	/**
	 * @Title: querySensitiveRecordForPage
	 * @Description: 根据敏感词记录属性与分页信息分页查询敏感词记录信息
	 * @param pageBounds 分页信息
	 * @param sensitiveRecord 实体
	 * @return List<SensitiveRecord>
	 */
	List<SensitiveRecord> querySensitiveRecordForPage(PageBounds pageBounds, @Param("sensitiveRecord") SensitiveRecord sensitiveRecord);
	 
	 /**
	  * @Title: querySensitiveRecordByProperty
	  * @Description:根据属性查询敏感词记录
	  * @return List<SensitiveRecord>
	  */
	 List<SensitiveRecord> querySensitiveRecordByProperty(@Param("sensitiveRecord") Map<String, Object> map);
	 
	 /** 
	* @Title: clearTable 
	* @Description: 清空数据
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer clearTable();
	 
}
