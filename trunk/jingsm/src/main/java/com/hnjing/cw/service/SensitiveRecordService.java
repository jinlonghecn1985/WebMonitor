package com.hnjing.cw.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.cw.model.entity.SensitiveRecord;

/**
 * @ClassName: SensitiveRecord
 * @Description: 敏感词记录服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
public interface SensitiveRecordService {

    /**
	 * @Title: addSensitiveRecord
	 * @Description:添加敏感词记录
	 * @param sensitiveRecord 实体
	 * @return Integer
	 */
	SensitiveRecord addSensitiveRecord(SensitiveRecord sensitiveRecord);
	
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
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param sensitiveRecord 实体
	 * @return List<SensitiveRecord>
	 */
	Map<String, Object> querySensitiveRecordForPage(Integer pagenum, Integer pagesize, String sort, SensitiveRecord sensitiveRecord);
	 
	 /**
	 * @Title: querySensitiveRecordByProperty
	 * @Description:根据属性查询敏感词记录
	 * @return List<SensitiveRecord>
	 */
	 List<SensitiveRecord> querySensitiveRecordByProperty(Map<String, Object> map);	 

		/** 
		* @Title: clearTable 
		* @Description: 清空数据
		* @return  
		* Integer    返回类型 
		* @throws 
		*/
		Integer clearTable();

		/** 
		* @Title: exportByProperty 
		* @Description: 
		* @param transBean2Map
		* @return  
		* HSSFWorkbook    返回类型 
		* @throws 
		*/
		HSSFWorkbook exportByProperty(Map<String, Object> map);	 
}
