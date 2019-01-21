package com.hnjing.ws.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjing.utils.Constant;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;
import java.util.UUID;

import com.hnjing.ws.model.entity.Dictionary;
import com.hnjing.ws.model.dao.DictionaryMapper;
import com.hnjing.ws.service.DictionaryService;

/**
 * @ClassName: DictionaryServiceImpl
 * @Description: 字典信息服务实现类
 * @author: JIM
 * @email: mailto:
 * @date: 2017年07月26日 17时26分
 */
@Service("dictionaryService")
@Transactional(readOnly=true)
public class  DictionaryServiceImpl implements DictionaryService {

	@Autowired
    private DictionaryMapper dictionaryMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	//参数配置-缺省    注意，顺序不能打乱
	private static final String[][] params = {{"config_pt", "mail_accept", "zengxianbo@hnjing.com;jwcc@hnjing.com", "网站异常接受邮箱", "0"}
					, {"config_pt", "mail_warnning", "hejinlong@hnjing.com", "网站涉敏接受邮箱", "1"}
					, {"config_pt", "title_nomal", "阻断页面,not found", "我司正常无法访问网站-标题识别词", "2"}
					, {"config_", "unused", "999", "未定义", "3"}
					, {"config_", "unused", "999", "未定义", "4"}
					, {"thread", "ip_thread_top", "300", "IP检测：单线程处理最大数--超出此数目后，程序将启用多个线程", "5"}
					, {"thread", "ip_thread_count", "4", "IP检测：启动多线程时，线程数", "6"}
					, {"thread", "thread_top", "200", "网站检测：单线程处理最大数--超出此数目后，程序将启用多个线程", "7"}
					, {"thread", "thread_count", "3", "网站检测：启动多线程时，线程数", "8"}
					, {"sys_token", "admin", "12306F2882EF4D3ABE2C0881A2C8D893", "命令执行控制代码", "9"}
					, {"config_ssg", "ssg_sync", "http://ck.open.ejw.cn/dscheck/v1/empl/account", "SSG数据同步源", "10"}
					, {"config_ssg", "mail_accept", "jwcc@hnjing.com", "SSG异常接收邮件", "11"}
					
				};
		
	
	/**
	 * @Title: addDictionary
	 * @Description:添加字典信息
	 * @param dictionary 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Dictionary addDictionary(Dictionary dictionary){
		if(null == dictionary.getDicId()){			
         	dictionary.setDicId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
		}
		int ret = dictionaryMapper.addDictionary(dictionary);
		if(ret>0){
			return dictionary;
		}
		return null;
	}
	
	/**
	 * @Title modifyDictionary
	 * @Description:修改字典信息
	 * @param dictionary 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyDictionary(Dictionary dictionary){
		return dictionaryMapper.modifyDictionary(dictionary);
	}
	
	/**
	 * @Title: dropDictionaryByDicId
	 * @Description:删除字典信息
	 * @param dicId 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropDictionaryByDicId(String dicId){
		return dictionaryMapper.dropDictionaryByDicId(dicId);
	}
	
	/**
	 * @Title: queryDictionaryByDicId
	 * @Description:根据实体标识查询字典信息
	 * @param dicId 实体标识
	 * @return Dictionary
	 */
	@Override
	public Dictionary queryDictionaryByDicId(String dicId){
		return dictionaryMapper.queryDictionaryByDicId(dicId);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryDictionaryForPage
	 * @Description: 根据字典信息属性与分页信息分页查询字典信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param dictionary 实体
	 * @return List<Dictionary>
	 */
	@Override
	public Map<String, Object> queryDictionaryForPage(Integer pagenum, Integer pagesize, String sort, Dictionary dictionary){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, Dictionary.class);
		List<Dictionary> entityList = dictionaryMapper.queryDictionaryForPage(pageBounds, dictionary);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, Dictionary.class);
		}
		if (!entityList.isEmpty()) {
			PageList<Dictionary> pagelist = (PageList<Dictionary>) entityList;
			returnMap.put(Constant.PAGELIST, entityList);
			returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		}
		return returnMap;
	}
	 
	/**
	 * @Title: queryDictionaryByProperty
	 * @Description:根据属性查询字典信息
	 * @return List<Dictionary>
	 */
	@Override
	public List<Dictionary> queryDictionaryByProperty(Map<String, Object> map){
		return dictionaryMapper.queryDictionaryByProperty(map);
	}

	/*
	 * @Title: queryDictionaryByDicId
	 * @Description: 根据字段组及标签查询字典信息
	 * @param @param dicGroup
	 * @param @param dicCode
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param dicGroup
	 * @param dicCode
	 * @return
	 * @see com.hnjing.ws.service.DictionaryService#queryDictionaryByDicId(java.lang.String, java.lang.String)
	 */ 
	@Override
	public Dictionary queryDictionary(String dicGroup, String dicCode) {
		return dictionaryMapper.queryDictionary(dicGroup, dicCode);
	}
	
	
	/** 
	* @Title: queryParamsValue 
	* @Description: 查询参数配置-找不到时返回缺省参数
	* @param i
	* @return  
	* String    返回类型 
	* @throws 
	*/
	@Transactional(readOnly=false)
	public String queryParamsValue(int i) {
		if (i >= params.length) {
			return null;
		}
		Dictionary d = queryDictionary(params[i][0], params[i][1]);
		if (d == null) {
			d = new Dictionary();
			d.setDicGroup(params[i][0]);
			d.setDicCode(params[i][1]);
			d.setDicValue(params[i][2]);
			d.setDicNote(params[i][3]);
			addDictionary(d);
		} else if (d.getDicValue() == null || d.getDicValue().length() == 0) {
			d.setDicValue(params[i][2]);
			modifyDictionary(d);
		}
		return d.getDicValue();
	}


}
