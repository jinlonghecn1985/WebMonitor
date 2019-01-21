/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SiteCheckUtil.java
 * @Prject: WebHealthMonitor
 * @Package: com.hnjing.ws.service.impl.util
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年10月15日 下午3:34:16
 * @version: V1.0  
 */
package com.hnjing.ws.service.impl.util;

import com.hnjing.utils.StringUtil;
import com.hnjing.utils.httpclient.HttpClientResult;
import com.hnjing.utils.httpclient.HttpClientSiteUtil;
import com.hnjing.ws.model.entity.SiteResult;
import com.hnjing.ws.model.entity.SiteUrl;

/**
 * @ClassName: SiteCheckUtil
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年10月15日 下午3:34:16
 */
public class SiteCheckUtil {
	
	public static SiteResult doCheckSite(String page) {
		SiteUrl site = new SiteUrl();
		site.setPage(page);
		return doCheckSite(site);
	}
	
	public static SiteResult doCheckSite(SiteUrl site) {
		if(site==null || site.getPage()==null || site.getPage().length()==0) {
			return null;
		}
		HttpClientResult re = HttpClientSiteUtil.getDeminTitle(site.getPage(), site.getCharset());	//正常检测		
		SiteResult result = new SiteResult();
		result.setSiteId(site.getId());		
		result.setPage(site.getPage());
		result.setStatus(re.getCode());
		result.setComment(re.getFastSlow());
		if(re.getCode()!=null && re.getCode().intValue()==200) {
			if (re.getBody() != null && re.getBody().length() > 0) {				
				result.setTitle(StringUtil.getStringFromContent(re.getBody(), "<title>", "</title>"));
				result.setContent(HttpToolUtil.delHTMLTag(re.getBody()).replaceAll("\\s{1,}", " "));				
				if(result.getTitle().length()>256) {
					result.setTitle(result.getTitle().substring(0, 252)); //防止标题过长
				}
			} else {
				result.setTitle("空白网站");
				result.setStatus(200);//疑似问题
			}
		}else {
			if(result.getStatus()==null) {
				result.setStatus(404);// 加载失败-
			}
			if(re.getBody()!=null && re.getBody().length()>0) {
				result.setTitle(StringUtil.getStringFromContent(re.getBody(), "<title>", "</title>"));
				result.setContent(HttpToolUtil.delHTMLTag(re.getBody()).replaceAll("\\s{1,}", " "));
				if(result.getTitle().length()>256) {
					result.setTitle(result.getTitle().substring(0, 252)); //防止标题过长
				}
			}			
		}		
		return result;
	}
	

	/** 
	* @Title: hasWhiteWord 
	* @Description: 是否包含白名单
	* @param content
	* @return  
	* boolean    返回类型 
	* @throws 
	*/
//	public static boolean hasWhiteWord(String content, String[] whiteWord) {		
//		for(String w : whiteWord) {
//			if(content.contains(w)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
//	private static SiteResult doCheckSite(String url) {
//		SiteUrl s = new SiteUrl();
//		s.setPage(url);
//		s.setCharset(null);
//		return doCheckSite(s);
//	}
	
//	public static void main(String[] arg) {
//		System.out.println("start");
//		doCheckSite();
//	}
}
