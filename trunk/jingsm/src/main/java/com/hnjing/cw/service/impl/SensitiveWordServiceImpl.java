package com.hnjing.cw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.cw.model.dao.SensitiveWordMapper;
import com.hnjing.cw.model.entity.SensitiveWord;
import com.hnjing.cw.service.SensitiveWordService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: SensitiveWord
 * @Description: 敏感词服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
@Service("sensitiveWordService")
@Transactional(readOnly=true)
public class  SensitiveWordServiceImpl implements SensitiveWordService {	
	private static final Logger logger = LoggerFactory.getLogger(SensitiveWordServiceImpl.class);
	
	@Autowired
    private SensitiveWordMapper sensitiveWordMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addSensitiveWord
	 * @Description:添加敏感词
	 * @param sensitiveWord 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public SensitiveWord addSensitiveWord(SensitiveWord sensitiveWord){
		int ret = sensitiveWordMapper.addSensitiveWord(sensitiveWord);
		if(ret>0){
			return sensitiveWord;
		}
		return null;
	}
	
	/**
	 * @Title modifySensitiveWord
	 * @Description:修改敏感词
	 * @param sensitiveWord 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifySensitiveWord(SensitiveWord sensitiveWord){
		return sensitiveWordMapper.modifySensitiveWord(sensitiveWord);
	}
	
	/**
	 * @Title: dropSensitiveWordById
	 * @Description:删除敏感词
	 * @param id 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropSensitiveWordById(Integer id){
		return sensitiveWordMapper.dropSensitiveWordById(id);
	}
	
	/**
	 * @Title: querySensitiveWordById
	 * @Description:根据实体标识查询敏感词
	 * @param id 实体标识
	 * @return SensitiveWord
	 */
	@Override
	public SensitiveWord querySensitiveWordById(Integer id){
		return sensitiveWordMapper.querySensitiveWordById(id);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: querySensitiveWordForPage
	 * @Description: 根据敏感词属性与分页信息分页查询敏感词信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param sensitiveWord 实体
	 * @return List<SensitiveWord>
	 */
	@Override
	public Map<String, Object> querySensitiveWordForPage(Integer pagenum, Integer pagesize, String sort, SensitiveWord sensitiveWord){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, SensitiveWord.class);
		List<SensitiveWord> entityList = sensitiveWordMapper.querySensitiveWordForPage(pageBounds, sensitiveWord);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, SensitiveWord.class);
		}
		
		PageList<SensitiveWord> pagelist = (PageList<SensitiveWord>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: querySensitiveWordByProperty
	 * @Description:根据属性查询敏感词
	 * @return List<SensitiveWord>
	 */
	@Override
	public List<SensitiveWord> querySensitiveWordByProperty(Map<String, Object> map){
		return sensitiveWordMapper.querySensitiveWordByProperty(map);
	}


}
