/**  
 * Copyright ? 2018公司名字. All rights reserved.
 * @Title: WebCrawlerUtil.java
 * @Prject: WebHealthMonitor
 * @Package: com.hnjing.utils.httpclient
 * @Description: 
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年11月23日 下午3:00:59
 * @version: V1.0  
 */
package com.hnjing.full.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.hnjing.cw.model.entity.SensitiveRecord;
import com.hnjing.cw.service.SensitiveService;
import com.hnjing.full.model.entity.ChildInfo;
import com.hnjing.full.model.entity.LinkItem;
import com.hnjing.full.model.entity.MonitorOutline;
import com.hnjing.full.model.entity.SensitiveItem;
import com.hnjing.full.service.ChildInfoService;
import com.hnjing.full.service.LinkItemService;
import com.hnjing.full.service.MonitorOutlineService;
import com.hnjing.full.service.SensitiveItemService;
import com.hnjing.full.service.impl.bo.ChildInfoBo;
import com.hnjing.utils.MailUtil;
import com.hnjing.utils.SpringUtils;


public class WebCrawlerThread extends Thread {
	
	ApplicationContext applicationAware =  SpringUtils.getApplicationContext();
	
	private LinkItemService linkItemService = applicationAware.getBean(LinkItemService.class);
	
	
	private ChildInfoService shildInfoService = applicationAware.getBean(ChildInfoService.class);
	
	
	private SensitiveService sensitiveService = applicationAware.getBean(SensitiveService.class);
	
	
	private SensitiveItemService sensitiveItemService = applicationAware.getBean(SensitiveItemService.class);
	
	
	private MonitorOutlineService monitorOutlineService = applicationAware.getBean(MonitorOutlineService.class);
	
	private MailUtil mailUtil = applicationAware.getBean(MailUtil.class);
	
	private static final Logger logger = LoggerFactory.getLogger(WebCrawlerThread.class); 
	
	private static Pattern homePagePattern = Pattern.compile("(https?://)?[^/\\s]*"); //网页比如：http://www.zifangsky.cn
	
	private static final int MAX_CHECK_PAGE = 800; //单站最大检测网页数
	private static final int MAX_CHECK_CYCLE = 7; //单站最多检测深度
	private int current_count = 0; //当前检测网页次数
	private int current_cycle = 0; //当前检测网页深度

//	private List<WebCrawlerLink> innerLinkedList =  new ArrayList<WebCrawlerLink>(); // 存储内链
	
//	private Map<String, HttpClientPage> pageLinkMap = new HashMap<String, HttpClientPage>();  //检测内容
	private MonitorOutline mo = null;
	private String homePage = "";  //主页
	private String pageCode = "UTF-8";
	private Map<String, Boolean> oldMap = new HashMap<String, Boolean>(); // 存储链接-是否被遍历
	private Map<String, Boolean> newMap = new LinkedHashMap<String, Boolean>(); //存储链接-新增
	private boolean running = true;
	
	/**
	 * @Title:WebCrawlerThread
	 * @Description:
	 * @param fullServive
	 * @param mo
	 * @param homePage
	 * @param ecode
	 */
	public WebCrawlerThread() {		
		logger.debug("Crawler Config > 1。max check page:"+MAX_CHECK_PAGE+"  2。max check cycle:"+MAX_CHECK_CYCLE);
	}
	
	public void initThread(MonitorOutline mo, String ecode) {
		this.mo = mo;
		if(ecode!=null && ecode.length()>2) {
			pageCode = ecode;
		}
		Matcher m = homePagePattern.matcher(mo.getPage());
        if (m.find()) {
        	homePage = m.group().toLowerCase(); //抽出主页
    	}  
        oldMap.put(mo.getPage(), false); //主页未检测
	}

    public void run() {    	  
        while(running) {
        	current_cycle++; //深度+1 
        	mo.setCheckLevel(current_cycle); //记录最新检测深度
        	logger.debug("**Crawler CYCLE :"+current_cycle);  //当前检测深度
        	if(current_cycle>MAX_CHECK_CYCLE) {
        		doEndThread();        		
        	}else {
    	    	if(oldMap!=null && oldMap.size()>0) {
    	    		List<ChildInfo> addList = new ArrayList<ChildInfo>();
    	    		for(String link : oldMap.keySet()) {    	    			
    	    			if(current_count>=MAX_CHECK_PAGE) {
    	    				logger.debug("**Crawler check to max page ("+current_count+"), stop it by force.");
//    	    				if(addList.size()>0) {
//    	    	    			shildInfoService.addChildInfoBatch(addList);
//    	    	    			addList.clear();
//    	    	    		}  //for结束前写数据
    	    				doEndThread();
    	    				break;
    	    			}else {
    	    				//继续深入检测
    	    				if(!oldMap.get(link)) {	 
        	    				current_count++;
        	    				mo.setSiteId(current_count); //更新检测次数
        	    				ChildInfoBo cib = WebCrawlerUtil.crawlerPage(homePage, link, pageCode);
        	    				if(current_cycle==1 && (cib.getChildInfo().getCode()==null || cib.getChildInfo().getCode().intValue()!=200)) {
        	    					cib = WebCrawlerUtil.crawlerPage(homePage, link, pageCode); //首页重检
        	    				}
        	    				oldMap.put(link, true);  //检测状态变更
        	    				
        	    				ChildInfo ci = cib.getChildInfo();
        	    				ci.setPage(link);
        	    				ci.setCheckCycle(current_cycle);
    	    					ci.setCheckOrder(current_count);
        	    				ci.setId(mo.getId());  
        	    				if(cib.getChildInfo().getCode()!=null && cib.getChildInfo().getCode().intValue()==200) {
        	    					processLinkData(ci, cib.getLinkList());        	    				
            	    				processSensitiveData(ci); 
        	    				}else {
        	    					mo.setErrorPage(mo.getErrorPage()==null?1:1+mo.getErrorPage());
        	    				}
        	    				addList.add(ci);        	    				
        	    			}    	    				
    	    			}    	    			
    	    		}
    	    		
    	    		if(addList.size()>0) {
    	    			shildInfoService.addChildInfoBatch(addList);
    	    			addList.clear();
    	    		}
    	    		//处理新增的内链
    	    		if(newMap!=null && newMap.size()>0) {
    	    			oldMap.putAll(newMap);
    	    			newMap.clear();
    	    		}else {
    	    			//无新链接
    	    			if(mo.getInnerPage()==null) {
    	    				mo.setInnerPage(0);
    	    			}
    	    			if(mo.getOuterPage()==null) {
    	    				mo.setOuterPage(0);
    	    			}
    	    			doEndThread();    	    			
    	    		}
    	    	}
        	}
        }        
    }    
    
    /** 
	* @Title: processSensitiveData 
	* @Description: 
	* @param ci  
	* void    返回类型 
	* @throws 
	*/
	private void processSensitiveData(ChildInfo ci) {
		sensitiveService.clearSensitive();
		List<SensitiveRecord> slist = sensitiveService.checkText(ci.getContent());
		if(slist!=null && slist.size()>0) {
			//有敏感词
			int illWordCount = 0;
			for(SensitiveRecord s : slist) {
				SensitiveItem  item = new SensitiveItem();
				item.setId(mo.getId());
				item.setPage(ci.getPage());
				item.setKeyWord(s.getKeyWord());
				item.setContext(s.getKeyWords());
				item.setStatus(s.getStatus());
				
				if(item.getStatus()==null || (item.getStatus()!=null && item.getStatus().intValue()==0)) {
					illWordCount++;	
				}
				sensitiveItemService.addSensitiveItem(item);
			}
			//数量统计
			ci.setIllegalWord(illWordCount);
			ci.setWhiteWord(slist.size()-illWordCount);
			mo.setIllegalWord((mo.getIllegalWord()==null?0:mo.getIllegalWord())+ci.getIllegalWord());
			mo.setKeyWord((mo.getKeyWord()==null?0:mo.getKeyWord())+ci.getWhiteWord());
		}
	}

	/** 
	* @Title: processLinkData 
	* @Description: 
	* @param ci
	* @param list  
	* void    返回类型 
	* @throws 
	*/
	private void processLinkData(ChildInfo ci, List<LinkItem> list) {
    	//处理链接数据
		if(list!=null && list.size()>0){
			List<LinkItem> addList = new ArrayList<LinkItem>();
			//有链接
			int outlinkcount = 0;
			for(LinkItem item : list) {				
				item.setId(mo.getId());
				item.setPage(ci.getPage());		
				if(item.getAnchor()!=null) {
					item.setAnchor(item.getAnchor().replaceAll("<", "").replaceAll(">", ""));
				}
				if(item.getStatus().intValue()!=0) {
					if(item.getLink().contains("www.hnjing.") || item.getLink().contains("www.miitbeian.") || item.getLink().contains("www.baidu.") || item.getLink().contains(".hnjing.com")) {
						continue; //以上相关链接暂不计入外链
					}
					outlinkcount++;					
					addList.add(item);					 
				}else {
					//内链
					if(!oldMap.containsKey(item.getLink())) {
						newMap.put(item.getLink(), false);   //新的内链
					}
				}
			}
			if(addList.size()>0) {
				linkItemService.addLinkItemBatch(addList);  //指量写入
			}
			//更新量
			ci.setOutLink(outlinkcount);
			ci.setInnerLink(list.size()-outlinkcount);
			mo.setOuterPage((mo.getOuterPage()==null?0:mo.getOuterPage())+ci.getOutLink());  //外链总量
			mo.setInnerPage((mo.getInnerPage()==null?0:mo.getInnerPage())+ci.getInnerLink()); //内链总量
		} 
    }
    
    private void doEndThread() {
    	if(running) {
    		//结束前的动作
    		if(mo.getSiteId()==null || mo.getSiteId().intValue()<2) {
    			mo.setCheckLevel(1);
    		}    		
    		monitorOutlineService.modifyMonitorOutline(mo);
    		doSendMail();    		
    	}
    	running = false;  //深度超过，不再深入
    }
 
    private void doSendMail() {
    	if(mo.getAcceptMail()!=null && mo.getAcceptMail().length()>0) {
    		try {
				mailUtil.sendSimpleMail(mo.getAcceptMail(), "【提醒】  全站检测结束", 
						"<div>Dear:</div><div>&nbsp;&nbsp;&nbsp;&nbsp; 您好！全站检测已经结束，请访问以下链接以查看详情：<a href=\"http://192.168.50.175:8090/fullweb.html?id="+mo.getId()+"\">点击跳转</a><br/>如未跳转，请复制以下链接：http://192.168.50.175:8090/fullweb.html?id="+mo.getId());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
   
 

 
}
