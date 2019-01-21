/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: WebCrawlerUtil.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.full.service.util
 * @Description: 
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月8日 下午1:52:26
 * @version: V1.0  
 */
package com.hnjing.full.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hnjing.full.model.entity.ChildInfo;
import com.hnjing.full.model.entity.LinkItem;
import com.hnjing.full.service.impl.bo.ChildInfoBo;
import com.hnjing.utils.httpclient.HttpClientMethod;
import com.hnjing.utils.httpclient.HttpClientResult;
import com.hnjing.utils.httpclient.HttpClientUtil2;
import com.hnjing.ws.service.impl.util.HttpToolUtil;

/**
 * @ClassName: WebCrawlerUtil
 * @Description: 检测网页并提取链接
 * @author: Jinlong He
 * @date: 2019年1月8日 下午1:52:26
 */
public class WebCrawlerUtil {
	private static Pattern linkPattern = Pattern.compile("<a.*?href=[\"']?((https?://)?/?[^\"']+)[\"']?.*?>(.+)</a>");  //链接识别
	private static String pageCode = "UTF-8";
	private static String endLinkTag = "</a>";
	private static String lineSeparator = System.getProperty("line.separator");
	private static String notWebLinkType = ".rar.doc.xls.pdf.docx.xlsx.exe.txt.jar";
	
	/** 
	* @Title: crawlerPage 
	* @Description: 检测网页并提取链接
	* @param baseUrl 基准网页
	* @param page 检测网页
	* @param code 网页编码
	* @return  ChildInfoBo    返回类型 
	* @throws 
	*/
	public static ChildInfoBo crawlerPage(String baseUrl, String page, String code) {
		ChildInfoBo ret = new ChildInfoBo();
		if(code==null || code.length()<2) {
			code = pageCode;
		}
		HttpClientResult r = HttpClientUtil2.sendRequest(HttpClientMethod.GET, page, null, null, code);
		ChildInfo ci = new ChildInfo();
		ret.setChildInfo(ci);
		ci.setPage(page);
		ci.setCode(r.getCode());
		if(r.getBody()!=null && r.getBody().length()>0) {
			ci.setContent(HttpToolUtil.delHTMLTag(r.getBody()).replaceAll("\\s{1,}", " "));
			//正常返回时，处理内容
			if (r.getCode() == 200) {
					List<LinkItem> linkedList = new ArrayList<LinkItem>();
					ret.setLinkList(linkedList);
					// 有内容-分析行数据
					Matcher matcher = null;
					String[] rows = r.getBody().split(lineSeparator); // 分行
					if (rows != null && rows.length > 0) {
						for (String row : rows) {
							if (row != null && row.length() > 10) {
								String[] links = row.split(endLinkTag); // 一行有多个链接
								if (links != null && links.length > 0) {
									for (String link : links) {
										matcher = linkPattern.matcher(link+endLinkTag);
										if (matcher.find()) {
											String newLink = matcher.group(1).trim().toLowerCase(); // 链接
											String anchor = matcher.group(3).trim(); // 锚点
											if(anchor!=null && anchor.length()>64) {
												anchor = anchor.substring(0, 64);
											}
											if (newLink.contains("javascript") || (newLink.contains(".") && notWebLinkType.contains(newLink.substring(newLink.lastIndexOf("."))))) {
												// 点击事件-结束本次筛选
												continue;
											}								
											// 去除链接末尾的 /
											if (newLink.endsWith("/")) {
												newLink = newLink.substring(0, newLink.length() - 1);
											}
											// 判断获取到的链接是否以http开头										
											if (!newLink.startsWith("http")) {										
												if(newLink.contains(":")) {
													System.out.println("tel: sms: tencent: 页面动作");
													continue; //
												}if (newLink.startsWith("/")) {
													newLink = baseUrl + newLink;
												} else if(!newLink.startsWith(baseUrl)){
													newLink = baseUrl + "/" + newLink;
												}
												linkedList.add(new LinkItem(page, anchor, newLink, 0)); // 子链接									
											} else if(newLink.startsWith(baseUrl)){												
												linkedList.add(new LinkItem(page, anchor, newLink, 0)); // 子链接	
											}else{
												ci.setOutLink(ci.getOutLink()+1); //统计外链数目
												linkedList.add(new LinkItem(page, anchor, newLink, 1)); // 外链
											}
										}
									} //单个提取结束
								}
							}
						} //行提取结束						
					}	
					ci.setInnerLink(linkedList.size()-ci.getOutLink()); //设置内链数目
					
			}//200结束
		}
				
		return ret;				
	}

}
