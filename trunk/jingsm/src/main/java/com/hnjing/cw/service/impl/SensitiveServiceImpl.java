/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SensitiveServiceImpl.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.cw.service.impl
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月15日 下午5:06:13
 * @version: V1.0  
 */
package com.hnjing.cw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjing.cw.model.entity.SensitiveRecord;
import com.hnjing.cw.model.entity.SensitiveWord;
import com.hnjing.cw.service.SensitiveRecordService;
import com.hnjing.cw.service.SensitiveService;
import com.hnjing.cw.service.SensitiveWordService;
import com.hnjing.ws.service.SiteResultService;
import com.hnjing.ws.service.bo.SiteResultBo;

/**
 * @ClassName: SensitiveServiceImpl
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年12月15日 下午5:06:13
 */
@Service("sensitiveService")
public class SensitiveServiceImpl implements SensitiveService {
	private static final Logger logger = LoggerFactory.getLogger(SensitiveServiceImpl.class);
	@Autowired
	private SiteResultService siteResultService;
	
	@Autowired
	private SensitiveWordService sensitiveWordService;
	
	@Autowired
	private SensitiveRecordService sensitiveRecordService;
	
	private static List<SensitiveWord> sensitiveWordList;
	
	private static int clength = 30; //取内容长度

	
	/** 
	* @Title: hasWhiteWord 
	* @Description:
	* @param s
	* @param keyWords
	* @return  
	* boolean    返回类型 
	* @throws 
	*/
	private boolean hasWhiteWord(SensitiveWord s, String keyWords) {
		String w = s.getWhiteWords();
		if(w==null || w.length()==0) {
			return false;
		}		
		String[] ws = w.split(",");		
		for(String k : ws) {
			k = k.replaceAll("%2c", ","); //正则表达式匹配
			Pattern p = Pattern.compile(k);
	        Matcher m = p.matcher(keyWords);
	        if (m.find()) {	
	        	return true;
	        }
		}
		return false;
	}

	/*
	 * @Title: checkResultSensitive
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.cw.service.SensitiveService#checkResultSensitive()
	 */
	@Override
	public Object checkResultSensitive() {
		Map<Integer, String> siteMap = new HashMap<Integer, String>();		
		Map<String, Object> siteResult = new HashMap<String, Object>();
		siteResult.put("status", 200);
		int max = siteResultService.querySiteResultCountByProperty(siteResult);
		int step = 100;
		sensitiveRecordService.clearTable(); //清空历史数据
		for(int page=0 ; step*page<max; page++) {
			Map<String, Object> ret = siteResultService.querySiteResultForPage(page, 100, null, siteResult);			
			if(ret !=null && ret.containsKey("data")) {
				@SuppressWarnings("unchecked")
				List<SiteResultBo> boList = (List<SiteResultBo>)ret.get("data");
				for(SiteResultBo srb : boList) {
					if(!siteMap.containsKey(srb.getSiteUrl().getId())) {
						//有时出现重复数据？
						siteMap.put(srb.getSiteUrl().getId(), null);
						List<SensitiveRecord> slist = checkText((String)srb.getSiteResult().getContent());
						if(slist!=null && slist.size()>0) {
							for(SensitiveRecord r : slist) {
								r.setPage(srb.getSiteResult().getPage());
								r.setSiteId(srb.getSiteResult().getSiteId());
								sensitiveRecordService.addSensitiveRecord(r);							
							}
						}
					}					
				}			
			}
		}		
		
		return null;
	}
	
	/*
	 * @Title: checkText
	 * @Description: 
	 * @param @param content
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param context
	 * @return
	 * @see com.hnjing.cw.service.SensitiveService#checkText(java.lang.String)
	 */ 
	@Override
	public List<SensitiveRecord> checkText(String content) {
		if(content==null || content.length()<1) {
			return new ArrayList<SensitiveRecord>();
		}
		List<SensitiveRecord> ret = new ArrayList<SensitiveRecord>();
//		Map<String, String> keys = new HashMap<String, String>();
		
		if(sensitiveWordList==null || sensitiveWordList.size()==0) {
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("status", 0);
			sensitiveWordList = sensitiveWordService.querySensitiveWordByProperty(query );
			logger.debug("加载敏感词总量"+sensitiveWordList.size());
		}
				
		for(SensitiveWord s : sensitiveWordList) {
			Pattern p = Pattern.compile(s.getKeyWord().replaceAll("%2c", ","));
	        Matcher m = p.matcher(content);
	        if (m.find()) {
	        	int startF =  m.end()-s.getKeyWord().length();
	        	int endF = m.end();
        	
	        	int f = startF;
	        	if(f<=clength) {
	        		f=0;
	        	}else {
	        		f = f-clength;
	        	}
	        	int e = endF;
	        	if(e<content.length()-clength) {
	        		e = e+clength;
	        	}else {
	        		e = content.length();
	        	}
//	        	if(!keys.containsKey(s.getId()+"-"+startF)) {
//	        		keys.put(s.getId()+"-"+startF, null);
	        		SensitiveRecord re = new SensitiveRecord();
		        	re.setKeyWord(s.getKeyWord());
		        	re.setKeyWords(content.substring(f, e));
		        	re.setKwId(s.getId());
		        	re.setStart(startF);
		        	re.setEnd(content.length());
		        	if(hasWhiteWord(s, re.getKeyWords())) {
		        		re.setStatus(1);
		        	}else {
		        		re.setStart(0);
		        	}
		        	ret.add(re); 
//	        	}	        	       	
	        }
		}
		
		return ret;        
	}

	/*
	 * @Title: clearSensitive
	 * @Description: TODO
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.cw.service.SensitiveService#clearSensitive()
	 */ 
	@Override
	public Integer clearSensitive() {
		if(sensitiveWordList!=null) {
			sensitiveWordList.clear();		
		}
		return 0;
	}
	

}
