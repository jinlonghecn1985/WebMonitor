/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: SyncSiteServiceImpl.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.sync.service.impl
 * @Description: 
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月27日 上午8:46:43
 * @version: V1.0  
 */
package com.hnjing.sync.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjing.config.web.exception.CustomException;
import com.hnjing.dpc.model.entity.Employee;
import com.hnjing.dpc.model.entity.EmployeeSite;
import com.hnjing.dpc.service.EmployeeService;
import com.hnjing.dpc.service.EmployeeSiteService;
import com.hnjing.sync.model.entity.DataSync;
import com.hnjing.sync.service.DataSyncService;
import com.hnjing.sync.service.SyncSiteService;
import com.hnjing.sync.service.bo.DataSyncBo;
import com.hnjing.utils.JsonUtil;
import com.hnjing.utils.file.office.ExcelUtil;
import com.hnjing.utils.httpclient.HttpClientMethod;
import com.hnjing.utils.httpclient.HttpClientResult;
import com.hnjing.utils.httpclient.HttpClientUtil2;
import com.hnjing.ws.model.entity.SiteUrl;
import com.hnjing.ws.service.DictionaryService;
import com.hnjing.ws.service.SiteUrlService;
import com.hnjing.ws.service.impl.util.HttpToolUtil;

/**
 * @ClassName: SyncSiteServiceImpl
 * @Description: 数据同步服务类
 * @author: Jinlong He
 * @date: 2018年12月27日 上午8:46:43
 */
@Service("syncSiteService")	
public class SyncSiteServiceImpl implements SyncSiteService{
	private static final Logger logger = LoggerFactory.getLogger(SyncSiteServiceImpl.class);
	
	@Autowired
	private DataSyncService dataSyncService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private EmployeeService employeeService;	
	
	@Autowired
	private EmployeeSiteService employeeSiteService;
	
	@Autowired
	private SiteUrlService siteUrlService;

	
	
	private Map<String, DataSync> dataMap = new HashMap<String, DataSync>();
	/*
	 * @Title: syncDataForSource
	 * @Description: 
	 * @param @param source
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param source
	 * @return
	 * @see com.hnjing.sync.service.DataSyncService#syncDataForSource(java.lang.Integer)
	 */ 
	@Override
	public Object syncDataForSource(Integer source) {
		if(source!=null && source.intValue()==2) {
			return syncSSGData(source);
		}
		return "错误数据源";
	}
	
	
	/*
	 * @Title: processDataForSource
	 * @Description: 
	 * @param @param siteList
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param siteList
	 * @return
	 * @see com.hnjing.dpc.service.DPSService#processImportExcel(java.util.List)
	 */ 
	@Override
	public Object processDataForSource(Integer source) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("source", source);
		query.put("hasChange", 1);  //数据变更过的
//		query.put("isDelete", 0);
//		query.put("hasEmail", 0);
//		query.put("hasEmplNo", 0);
//		query.put("today", 0); //不限今天
//		query.put("sevenday", 0);	//最近7天变更
		
		List<DataSync> siteList = dataSyncService.queryDataSyncByProperty(query);
		if(siteList!=null && siteList.size()>0) {
//			siteUrlService.initNeedCheckZero(source);  //采用增量处理后不再调用
				for (DataSync row : siteList) {
					//处理员工
					Employee e = new Employee();
					e.setEmplNo(row.getEmplNo());
					e.setRealName(row.getEmplName());					
					e.setEmail(row.getEmail());
					if(row.getOrgan3()!=null && ("客户联络中心".equals(row.getOrgan3()) )) {
						e.setEmail("jwcc@hnjing.com"); //特殊处理
						e.setEmplNo("77777");
						e.setRealName("小仙子");
					}
					e.setOrgName(isNull(row.getOrgan1()) + "_" + isNull(row.getOrgan2()) + "_" + isNull(row.getOrgan3()));
					e = employeeService.bindEmployeeByNO(e);

					//处理站点
					SiteUrl su = new SiteUrl();
					su.setCustomer(row.getCompanyName());
					su.setPage(HttpToolUtil.getUrl(row.getSiteUrl()));
					if(row.getIsDelete()!=null && row.getIsDelete().intValue()==0) {
						su.setNeedCheck(0);
					}else {
						su.setNeedCheck(1);
					}
					su.setSource(source);
					siteUrlService.bindSiteUrl(su);
					
					EmployeeSite sf = new EmployeeSite();
					sf.setEmpId(e.getId());
					sf.setSiteId(su.getId());
					sf.setSfName(row.getAccountName());
					employeeSiteService.bindEmployeeSite(sf);
				}
				dataSyncService.overUpdateStatusBySource(source); // 批量恢复变更状态
				logger.info("处理同步数据完成："+siteList.size());
		}else {
			logger.info("processDataForSource 没有适配到数据");
		}
		return null;
	}
	
	private String isNull(String org) {
		return org==null?"--":org;
	}
	
	private Object syncSSGData(Integer source) {		
		int pageSize = 50;
		int pageNo = 1;
		int totalCount = 21;
		dataMap.clear();
		dataSyncService.initUpdateStatusBySource(source);
		String url = dictionaryService.queryParamsValue(10);
		while(pageSize*(pageNo-1)<totalCount) {
			DataSyncBo dsb = syncSSGData(pageNo, pageSize, url);
			pageNo++;
			if(dsb!=null && dsb.getPage()!=null && dsb.getPage().getTotalCount()!=null) {
				//totalCount = (dsb.getPage().getTotalCount()/pageSize)+((dsb.getPage().getTotalCount()%pageSize==0?0:1));
				totalCount = dsb.getPage().getTotalCount();
				processData(source, dsb.getData());
			}
		}
		logger.info("同步数据完成："+totalCount);
		return "同步数据完成："+totalCount;
	}
	
	private DataSync queryData(Integer source, DataSync ds) {
		if(dataMap.size()==0) {
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("source", source);
			List<DataSync> list = dataSyncService.queryDataSyncByProperty(query);
			if(list!=null && list.size()>0) {
				for(DataSync d : list) {
					dataMap.put(d.getAccountName(), d);
				}
			}			
		}
		if(dataMap.containsKey(ds.getAccountName())) {
			return dataMap.get(ds.getAccountName());
		}else {
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("accountName", ds.getAccountName());				
			List<DataSync> list = dataSyncService.queryDataSyncByProperty(query);
			if(list==null || list.size()==0 || list.get(0)==null) {
				return null;
			}else {
				dataMap.put(ds.getAccountName(), list.get(0));
				return  list.get(0);
			}			
		}
	}
	
	private Integer processData(Integer source, List<DataSync> dsList) {
		if(dsList!=null && dsList.size()>0) {
			List<DataSync> datas = new ArrayList<DataSync>();
			for(DataSync ds :dsList) {	
				ds.setSiteUrl(HttpToolUtil.getUrl(ds.getSiteUrl())); //统一处理链接地址
				//检查是否存在
				DataSync oldDS = queryData(source, ds);
				if(oldDS==null) {
					ds.setSource(source);
					ds.setHasChange(1);
					ds.setIsDelete(0);
					dataSyncService.addDataSync(ds); //添加
				}else {
					if(isDataChanged(oldDS, ds)) {
						ds.setId(oldDS.getId());
						ds.setHasChange(1);
						ds.setIsDelete(0);
						dataSyncService.modifyDataSync(ds); //变化数据						
					}else {	
						datas.add(oldDS);						
					}
				}				
			}
			if(datas.size()>0) {
				dataSyncService.modifyDataSyncDeleteOnBatch(datas); //还原
			}
			return dsList.size();
		}
		return 0;		
	}
	
	
	private boolean isDataChanged(DataSync olddata, DataSync newdata) {
		if(isStringChanged(olddata.getCompanyName(), newdata.getCompanyName())) {
			return true;
		}
		if(isStringChanged(olddata.getSiteUrl(), newdata.getSiteUrl())) {
			return true;
		}
		if(isStringChanged(olddata.getEmplNo(), newdata.getEmplNo())) {
			return true;
		}
		if(isStringChanged(olddata.getEmplName(), newdata.getEmplName())) {
			return true;
		}		
		if(isStringChanged(olddata.getEmail(), newdata.getEmail())) {
			return true;
		}
		if(isStringChanged(olddata.getOrgan1(), newdata.getOrgan1())) {
			return true;
		}
		if(isStringChanged(olddata.getOrgan2(), newdata.getOrgan2())) {
			return true;
		}
		if(isStringChanged(olddata.getOrgan3(), newdata.getOrgan3())) {
			return true;
		}		
		return false;
	}
	
	/** 
	* @Title: isStringChanged 
	* @Description: 两个字符是否发生变化 是true
	* @param oldS
	* @param newS
	* @return  
	* boolean    返回类型 
	* @throws 
	*/
	private boolean isStringChanged(String oldS, String newS) {
		if((oldS==null && newS==null) || (oldS!=null && oldS.equals(newS))) {
			return false;
		}
		return true;
	}
	
	
	
	/** 
	* @Title: syncSSGData 
	* @Description: 获取数据
	* @param page
	* @param pageSize
	* @return  
	* DataSyncBo    返回类型 
	* @throws 
	*/
	private DataSyncBo syncSSGData(int page, int pageSize, String url) {
		// 发送请求		
		Map<String, String> heard = new HashMap<String, String>();
		heard.put("Content-type", "application/json");
		DataSyncBo dsb = null;
		try {
			HttpClientResult hcr = HttpClientUtil2.sendRequest(HttpClientMethod.GET,
					url + "?pageNo=" + page + "&pageSize=" + pageSize, null, heard, false, "utf-8");
//			System.out.println(hcr.getCode());
			if (hcr.getCode() != 200) {
				throw new CustomException(hcr.getCode(), hcr.getBody());
			}
//			System.out.println(hcr.getBody());
			dsb = JsonUtil.json2object(hcr.getBody(), DataSyncBo.class);
			if (dsb == null || dsb.getData() == null) {
				return null;
			}
			return dsb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}


	/*
	 * @Title: syncDataForSource
	 * @Description: TODO
	 * @param @param fileName
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param fileName
	 * @return
	 * @see com.hnjing.sync.service.SyncSiteService#syncDataForSource(java.lang.String)
	 */ 
	@Override
	public Object syncDataForSource(Integer source, String fileName)  {
		List<List<String>> dataList = null;
		try {
			dataList = ExcelUtil.readExcel(fileName, false);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		if (dataList != null && dataList.size() > 0) {
			siteUrlService.initNeedCheckZero(source);
			for (List<String> row : dataList) {
				// 处理员工
				Employee e = new Employee();
				e.setEmplNo(row.get(8));
				e.setRealName(row.get(1));
				e.setEmail(row.get(7));
				e.setOrgName(row.get(4) + "_" + row.get(3) + "_" + row.get(2));
				e = employeeService.bindEmployeeByNO(e);

				// 处理站点
				SiteUrl su = new SiteUrl();
				su.setCustomer(row.get(5));
				su.setPage(HttpToolUtil.getUrl(row.get(6)));
				su.setNeedCheck(0);//
				su.setSource(source);
				siteUrlService.bindSiteUrl(su);

				EmployeeSite sf = new EmployeeSite();
				sf.setEmpId(e.getId());
				sf.setSiteId(su.getId());
				sf.setSfName(row.get(0));
				employeeSiteService.bindEmployeeSite(sf);
			}

		}
		return dataList.size();
	}
}
