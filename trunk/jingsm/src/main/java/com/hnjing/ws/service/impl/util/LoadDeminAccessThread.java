/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: LoadPTDeminStatusThread.java
 * @Prject: WebHealthMonitor
 * @Package: com.hnjing.ws.service.impl.util
 * @Description: 
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年10月22日 上午11:27:25
 * @version: V1.0  
 */
package com.hnjing.ws.service.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hnjing.ws.model.entity.SiteResult;
import com.hnjing.ws.model.entity.SiteStatistics;
import com.hnjing.ws.model.entity.SiteUrl;
import com.hnjing.ws.service.impl.SiteAccessServiceImpl;

/**
 * @ClassName: LoadPTDeminStatusThread
 * @Description: 
 * @author: Jinlong He
 * @date: 2018年10月22日 上午11:27:25
 */
public class LoadDeminAccessThread extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(LoadDeminAccessThread.class);
	private List<SiteUrl> urlList = null;	
	private int threadCode;//代号-下标
	private boolean firstLoad = true; //避免同时批量操作入库，进行错时启动
	private SiteAccessServiceImpl siteAccessServiceImpl;
	
	private boolean is_insert = false; //首检   复查
	private SiteStatistics ss;
	
	/**
	 * @Title:LoadWebThread
	 * @Description:
	 * @param siteMonitorServiceImpl
	 * @param threadCode
	 * @param urlList  注意：urlList不允许进行改变大小的增加、删除等操作。否则引起其它线程异常
	 * @param siteResultService
	 */
	public LoadDeminAccessThread(SiteAccessServiceImpl siteAccessServiceImpl, int threadCode, List<SiteUrl> urlList, boolean insert, SiteStatistics ssid){
		this.siteAccessServiceImpl = siteAccessServiceImpl;
		this.threadCode = threadCode;
		this.urlList = urlList;
		this.is_insert = insert;
		this.ss = ssid;
	}

	
    @Override
    public void run() {    	
		List<SiteResult> rslist = new ArrayList<SiteResult>(); //检测结果
		if (firstLoad && threadCode > 1) {
			logger.debug("********** 网站检测线程 " + threadCode + "错开启动时长(分钟):"+threadCode);
			try {
				Thread.sleep(1000  * threadCode);// 错时启动时长
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.debug("*********** 网站检测开始，线程 " + threadCode + " 待检测域名:" + urlList.size());		
		int temp = 0;
    	while(temp<urlList.size()) {  
    		String urlpage = urlList.get(temp).getPage();
    		if(urlpage==null || urlpage.length()==0) {
    			urlpage = urlList.get(temp).getPage();
    		}
    		if(is_insert) {
    			//新增
//    			siteResultService.addSiteResult(SiteCheckUtil.doCheckSite(urlpage, null));
    			SiteResult sr = SiteCheckUtil.doCheckSite(urlList.get(temp));
    			sr.setSource(ss.getSource());
	    		rslist.add(sr);	    		
	    		if(rslist.size()==50) {
	    			logger.debug("********* 网站检测线程 " + threadCode +" ("+temp+"/"+urlList.size()+") 写入结果。");
	    			siteAccessServiceImpl.addSiteResultOnBatch(rslist);
	    			rslist.clear();
	    		}			
    		}else {
    			//更新
    			SiteResult rs = SiteCheckUtil.doCheckSite(urlList.get(temp));
    			if(rs.getStatus().intValue()==0) {
    				siteAccessServiceImpl.modifySiteResult(rs); //恢复正常后
    			}  
    		}
    		temp++;  
    		//休眠
//			try {
//				Thread.sleep(100);//
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
    	}
	    if(rslist.size()>0) {
	    	logger.debug("********* 线程 " + threadCode +" 写入最后结果。");
	    	if(is_insert) {
	    		//新增
	    		siteAccessServiceImpl.addSiteResultOnBatch(rslist);
				rslist.clear();
	    	}
		}    	
    	logger.debug("*********** 线程 " + threadCode + "错开操作完成,检测网站:" + urlList.size());    	
    	siteAccessServiceImpl.checkSiteThreadCallBack(ss, threadCode);	
    }  
}
