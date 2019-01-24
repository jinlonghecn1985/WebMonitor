package com.hnjing.cw.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnjing.config.validation.BeanValidator;
import com.hnjing.config.web.exception.NotFoundException;
import com.hnjing.config.web.exception.ParameterException;
import com.hnjing.cw.model.entity.SensitiveWord;
import com.hnjing.cw.service.SensitiveWordService;
import com.hnjing.utils.ClassUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: SensitiveWordController
 * @Description: 敏感词HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年12月15日 17时00分
 */
@RestController
@Api(description="系统控制-敏感词管理")
public class SensitiveWordController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private SensitiveWordService sensitiveWordService;

	
	@ApiOperation(value = "新增 添加敏感词信息", notes = "添加敏感词信息")
	@RequestMapping(value = "/sensitiveword", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addSensitiveWord(HttpServletResponse response,
			@ApiParam(value = "sensitiveWord") @RequestBody SensitiveWord sensitiveWord) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		if(sensitiveWord==null || sensitiveWord.getKeyWord()==null || sensitiveWord.getKeyWord().length()==0) {
			throw new ParameterException("keyWord", "敏感词不能为空");
		}
		
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("keyWordonly", sensitiveWord.getKeyWord());
		List<SensitiveWord> list = sensitiveWordService.querySensitiveWordByProperty(query);
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		sensitiveWord.setId(null);
		sensitiveWordService.addSensitiveWord(sensitiveWord);
		return sensitiveWord;
	}
	
	
	@ApiOperation(value = "更新 根据敏感词标识更新敏感词信息", notes = "根据敏感词标识更新敏感词信息")
	@RequestMapping(value = "/sensitiveword/{id:.+}", method = RequestMethod.PUT)
	public Object modifySensitiveWordById(HttpServletResponse response,
			@PathVariable Integer id,
			@ApiParam(value = "sensitiveWord", required = true) @RequestBody SensitiveWord sensitiveWord
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SensitiveWord tempSensitiveWord = sensitiveWordService.querySensitiveWordById(id);
		sensitiveWord.setId(id);
		if(null == tempSensitiveWord){
			throw new NotFoundException("敏感词");
		}
		sensitiveWord.setKeyWord(null);//敏感词本身不允许修订
		return sensitiveWordService.modifySensitiveWord(sensitiveWord);
	}

	@ApiOperation(value = "删除 根据敏感词标识删除敏感词信息", notes = "根据敏感词标识删除敏感词信息")
	@RequestMapping(value = "/sensitiveword/{id:.+}", method = RequestMethod.DELETE)
	public Object dropSensitiveWordById(HttpServletResponse response, @PathVariable Integer id) {
		SensitiveWord sensitiveWord = sensitiveWordService.querySensitiveWordById(id);
		if(null == sensitiveWord){
			throw new NotFoundException("敏感词");
		}
		return sensitiveWordService.dropSensitiveWordById(id);
	}
	
	@ApiOperation(value = "查询 根据敏感词标识查询敏感词信息", notes = "根据敏感词标识查询敏感词信息")
	@RequestMapping(value = "/sensitiveword/{id:.+}", method = RequestMethod.GET)
	public Object querySensitiveWordById(HttpServletResponse response,
			@PathVariable Integer id) {
		SensitiveWord sensitiveWord = sensitiveWordService.querySensitiveWordById(id);
		if(null == sensitiveWord){
			throw new NotFoundException("敏感词");
		}
		return sensitiveWord;
	}
	
	@ApiOperation(value = "查询 根据敏感词属性查询敏感词信息列表", notes = "根据敏感词属性查询敏感词信息列表")
	@RequestMapping(value = "/sensitiveword", method = RequestMethod.GET)
	public Object querySensitiveWordList(HttpServletResponse response,
			SensitiveWord sensitiveWord) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return sensitiveWordService.querySensitiveWordByProperty(ClassUtil.transBean2Map(sensitiveWord, false));
	}
	
	@ApiOperation(value = "查询分页 根据敏感词属性分页查询敏感词信息列表", notes = "根据敏感词属性分页查询敏感词信息列表")
	@RequestMapping(value = "/sensitivewords", method = RequestMethod.GET)
	public Object querySensitiveWordPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, SensitiveWord sensitiveWord) {				
		return sensitiveWordService.querySensitiveWordForPage(pagenum, pagesize, sort, sensitiveWord);
	}

}
