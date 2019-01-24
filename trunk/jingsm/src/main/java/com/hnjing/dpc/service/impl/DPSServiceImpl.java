/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: DPSServiceImpl.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.dpc.service.impl
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年12月3日 上午11:10:16
 * @version: V1.0  
 */
package com.hnjing.dpc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjing.dpc.model.entity.Employee;
import com.hnjing.dpc.model.entity.EmployeeSite;
import com.hnjing.dpc.service.DPSService;
import com.hnjing.dpc.service.EmployeeService;
import com.hnjing.dpc.service.EmployeeSiteService;
import com.hnjing.dpc.service.bo.EmployeeSiteInfo;
import com.hnjing.dpc.service.bo.SiteAllInfo;
import com.hnjing.utils.DateUtil;
import com.hnjing.utils.JsonUtil;
import com.hnjing.utils.MailUtil;
import com.hnjing.ws.model.entity.Dictionary;
import com.hnjing.ws.model.entity.SiteHistory;
import com.hnjing.ws.model.entity.SiteResult;
import com.hnjing.ws.model.entity.SiteStatistics;
import com.hnjing.ws.model.entity.SiteUrl;
import com.hnjing.ws.service.DictionaryService;
import com.hnjing.ws.service.SiteAccessService;
import com.hnjing.ws.service.SiteHistoryService;
import com.hnjing.ws.service.SiteStatisticsService;
import com.hnjing.ws.service.SiteUrlService;
import com.hnjing.ws.service.impl.util.HttpToolUtil;
import com.hnjing.ws.service.impl.util.SiteCheckUtil;

/**
 * @ClassName: DPSServiceImpl
 * @Description: TODO
 * @author: Jinlong He
 * @date: 2018年12月3日 上午11:10:16
 */
@Service("dpsService")
public class DPSServiceImpl implements DPSService{
	private static final Logger logger = LoggerFactory.getLogger(DPSServiceImpl.class);
	
	private static String author = "<div><br></div><div><font size=\\\"4\\\">何锦龙</font> /客户中心 流程信息部<br>MP：15616171188 | EMAIL：hejinlong@hnjing.com | 百度HI：景隆辖日<br>-------------------------------------------------------<br>湖南竞网智赢网络技术有限公司 百度（湖南）客户服务中心<br>服务热线：400-0731-777 官网： www.hnjing.com<br>客户俱乐部：vip.hnjing.com&nbsp;&nbsp; 智营销服务平台：e.hnjing.com<br>长沙市麓谷高新区文轩路27号麓谷企业广场C3栋一层至四层（410205）<br></div>";
	
	private static String tableHeard = "<table style=\"font-family: verdana,arial,sans-serif; font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;\">";
	public static String getHttpStringByCode(Integer code) {
		if(HttpToolUtil.errorMap.containsKey(code)) {
			return HttpToolUtil.errorMap.get(code);
		}
		return "未定义("+code+")";
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private EmployeeSiteService employeeSiteService;
	
	@Autowired
	private SiteAccessService siteAccessService;
	
	@Autowired
	private SiteUrlService siteUrlService;
	
	@Autowired
	private SiteStatisticsService siteStatisticsService;
	
	@Autowired
	private SiteHistoryService siteHistoryService;
	
	@Autowired
	private DictionaryService dictionaryService;
//	
//	@Autowired
//	private EmployeeService employeeService;

	
	

	
	
	/*
	 * @Title: processAllErrorMail
	 * @Description: 
	 * @param @return    参数  
	 * @author Jinlong He
	 * @return
	 * @see com.hnjing.dpc.service.DPSService#processAllErrorMail()
	 */ 
	@Override
	public Object processAllErrorMail() {
		Dictionary dic = dictionaryService.queryDictionaryByDicId("mail_source");
		if(dic!=null && dic.getDicValue()!=null) {
			String[] ss = dic.getDicValue().replaceAll("，", ",").split(",");
			for(String s : ss) {
				try {
					int sou = Integer.parseInt(s);
					processErrorMail(sou);
				}catch (NumberFormatException e) {
					logger.error("mail_source:"+dic.getDicValue());
				}
			}			
		}
		return null;
	}
	

	
	/*
	 * @Title: processErrorMail
	 * @Description: 
	 * @param @param source
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param source
	 * @return
	 * @see com.hnjing.dpc.service.DPSService#processErrorMail(java.lang.Integer)
	 */ 
	@SuppressWarnings("unchecked")
	@Override
	public Object processErrorMail(Integer source) {		
		SiteStatistics ss = siteStatisticsService.queryLastSiteStatistics(source);
		if(ss==null) {
			return null;
		}
		if(ss.getEndTime()==null) {
			return "检测未结束，请耐心等待";
		}
//		siteAccessService.reCheckErrorResult(source); //复检
		String endTime = DateUtil.getDateTime(ss.getEndTime());
		Map<String, Object> siteHistory = new HashMap<String, Object>();
		siteHistory.put("id", ss.getId());
		siteHistory.put("nostatus", 200);
		if(source.intValue()==1) {
			siteHistory.put("comment", 9);
		}
		//查询 + 复查
		List<SiteHistory> ret = reCheckErrorSite(siteHistoryService.querySiteHistoryByProperty(siteHistory));
		if(ret==null || ret.size()==0) {
			return null;
		}
		if(ret.size()>50) {
			try {
				mailUtil.sendSimpleMail("hejinlong@hnjing.com", "【重要】 网站访问异常"+DateUtil.getDate()+"_"+ret.size(), 
						"<div>Dear：</div><div>&nbsp;&nbsp;&nbsp;&nbsp; 您好！检测无法打开的托管网站超量，请及时处理(检测时间："+endTime+")！</div><br/>"
						+"<div><br>&nbsp;&nbsp;&nbsp;&nbsp;访问以下链接查看详情：<a href=\"http://192.168.50.175:8090/records.html?id="+ss.getId()+"\"> http://192.168.50.175:8090/records.html?id="+ss.getId()+"</a><br/>"
						+JsonUtil.object2json(ss)
						+"感谢您的支持，如有识别错误或建议请联系本人以升级策略！</div>"+author);
				return null;
			} catch (ExecutionException e) {
				logger.error("************************************************************************邮件发送失败：");
			}
			return null;
		}
		
		if(source.intValue()==1) {
			processPTErrorMail(endTime, ret);
		}else if(source.intValue()==3 || source.intValue()==4) {
			processSSGErrorMail(endTime, ret);			
		}
		return null;
	}
	
	/** 
	* @Title: reCheckErrorSite 
	* @Description: 实时复查
	* @param ret
	* @return  
	* List<SiteHistory>    返回类型 
	* @throws 
	*/
	private List<SiteHistory> reCheckErrorSite(List<SiteHistory> ret){
		if(ret==null || ret.size()==0) {
			return null;
		}
		for(int i=ret.size(); i>0; i--) {
			SiteResult sr = SiteCheckUtil.doCheckSite(ret.get(i-1).getPage());
			if(sr!=null && sr.getStatus()!=null && sr.getStatus().intValue()==200) {
				ret.remove(i-1);
			}
		}
		return ret;
	}
	
	/** 
	* @Title: processSSGErrorMail 
	* @Description: SSG邮件发送
	* @param endTime
	* @param hisList
	* @return  
	* boolean    返回类型 
	* @throws 
	*/
	private boolean processSSGErrorMail(String endTime, List<SiteHistory> hisList) {
		Map<String, EmployeeSiteInfo> esMap = new HashMap<String, EmployeeSiteInfo>(); //数据存储				
		for(SiteHistory sh : hisList) {
			EmployeeSite es = employeeSiteService.queryEmployeeSiteBySiteId(sh.getSiteId());
			if(es!=null && !esMap.containsKey(""+es.getEmpId())) {
				//人员是否存在
				System.out.println("ADD:"+es.getEmpId());
				EmployeeSiteInfo esi = new EmployeeSiteInfo();
				esi.setEmployee(employeeService.queryEmployeeById(es.getEmpId()));
				esMap.put(""+es.getEmpId(), esi);
			}
			esMap.get(""+es.getEmpId()).setSiteAllInfo(new SiteAllInfo(sh, siteUrlService.querySiteUrlById(es.getSiteId()), es));			
		}
		for(String key: esMap.keySet()) {
			//解决小仙子重复发送的问题
			if(esMap.get(key).getEmployee()!=null && esMap.get(key).getEmployee().getEmail()!=null && !esMap.get(key).getEmployee().getEmail().toLowerCase().equals("jwcc@hnjing.com")) {
				mailSSGPersion(endTime, esMap.get(key));
			}
		}

		mailAll(endTime, esMap);
		return true;
	}
	
	
	
	/** 
	* @Title: mailSSGPersion 
	* @Description: 
	* @param endTime
	* @param employeeSiteInfo  
	* void    返回类型 
	* @throws 
	*/
	private boolean mailSSGPersion(String endTime, EmployeeSiteInfo employeeSiteInfo) {
		boolean mysite = false;
		int errorCount = 0;
		StringBuffer sb = new StringBuffer();
		sb.append(tableHeard+"<tr/>")
		.append(getTableTitle("序号"))
		.append(getTableTitle("客服名称(工号)"))
		.append(getTableTitle("客户名称"))
		.append(getTableTitle("帐号"))
		.append(getTableTitle("是否在我司"))
		.append(getTableTitle("IP"))
		.append(getTableTitle("异常原因"))
		.append(getTableTitle("网页"))
		.append("</tr>");
		int i=0;
		
		errorCount += employeeSiteInfo.getSiteList().size();
		for (SiteAllInfo in : employeeSiteInfo.getSiteList()) {
			if (in.getSiteUrl().getSelfSite() == 1) {
				mysite = true; // 包括我司托管网站
			}
			sb.append("<tr>")
			.append(getTableTD(+i + ""))
			.append(getTableTD(employeeSiteInfo.getEmployee().getRealName() + "(" + employeeSiteInfo.getEmployee().getEmplNo()+ ")"))
			.append(getTableTD(in.getSiteUrl().getCustomer()))
			.append(getTableTD(in.getEmployeeSite().getSfName()))
			.append(getTableTD(in.getSiteUrl().getSelfSite() == 1 ? "是" : "否"))
			.append(getTableTD(in.getSiteUrl().getIp()))
			.append(getTableTD(
					in.getSiteHistory().getStatus() + ":" + getHttpStringByCode(in.getSiteHistory().getStatus())))
			.append(
					getTableTD("<a href=\"" + in.getSiteUrl().getPage() + "\">" + in.getSiteUrl().getPage()) + "</a>")
			.append("</tr>");
		}
		sb.append("</table>");
		
		try {				
			mailUtil.sendSimpleMail(employeeSiteInfo.getEmployee().getEmail()+(mysite?";zengxianbo@hnjing.com":""), "【重要】  网站访问异常"+DateUtil.getDate()+"_"+errorCount, 
					"<div>Dear "+employeeSiteInfo.getEmployee().getRealName()+"：</div><div>&nbsp;&nbsp;&nbsp;&nbsp; 您好！以下推广网站无法打开，请知晓并核查(检测时间："+endTime+")！</div><br/>"
					+sb.toString()+"<div><br>&nbsp;&nbsp;&nbsp;&nbsp; 感谢您的支持，如有识别错误或建议请联系本人以升级策略！</div>"+author);
			return true;
		} catch (ExecutionException e) {
			logger.error("************************************************************************邮件发送失败：");
		}		
		return false;
		
	}




	/** 
	* @Title: mailPT 
	* @Description: 
	* @param hisList  
	* void    返回类型 
	* @throws 
	*/
	private boolean processPTErrorMail(String endTime, List<SiteHistory> hisList) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(tableHeard+"<tr/>")
			.append(getTableTitle("序号"))
			.append(getTableTitle("IP"))
			.append(getTableTitle("域名"))
			.append(getTableTitle("是否在我司"))
			.append(getTableTitle("异常原因"));
		int i=0;
		for(SiteHistory his : hisList) {			
			sb.append("<tr>")
			.append(getTableTD(++i+""))
			.append(getTableTD(his.getIp()))
			.append(getTableTD("<a href=\""+his.getPage()+"\">"+his.getPage()))
			.append(getTableTD(his.getSelfSite()==1?"是":"否"))
			.append(getTableTD(his.getStatus()+":"+getHttpStringByCode(his.getStatus())))
			.append("</tr>");
		}
		sb.append("</table>");
		try {
			mailUtil.sendSimpleMail(dictionaryService.queryParamsValue(0), "【重要】 网站访问异常"+DateUtil.getDate()+"_"+hisList.size(), 
					"<div>Dear 运维小哥哥：</div><div>&nbsp;&nbsp;&nbsp;&nbsp; 您好！以下托管网站无法打开，请及时处理(检测时间："+endTime+")！</div><br/>"
					+sb.toString()+"<div><br>&nbsp;&nbsp;&nbsp;&nbsp; 感谢您的支持，如有识别错误或建议请联系本人以升级策略！</div>"+author);
			return true;
		} catch (ExecutionException e) {
			logger.error("************************************************************************邮件发送失败：");
		}		
		return false;
	}
	
	/** 
	* @Title: mailAll 
	* @Description: 
	* @param esMap  
	* void    返回类型 
	* @throws 
	*/
	private boolean mailAll(String endTime, Map<String, EmployeeSiteInfo> esMap) {

//		boolean mysite = false;
		int errorCount = 0;
		StringBuffer sb = new StringBuffer();
		sb.append(tableHeard+"<tr/>")
		.append(getTableTitle("序号"))
		.append(getTableTitle("客服名称(工号)"))
		.append(getTableTitle("客户名称"))
		.append(getTableTitle("帐号"))
		.append(getTableTitle("是否在我司"))
		.append(getTableTitle("IP"))
		.append(getTableTitle("异常原因"))
		.append(getTableTitle("网页"))
		.append("</tr>");
		int i=0;
		for(String user : esMap.keySet()) {
			EmployeeSiteInfo esi = esMap.get(user);
			errorCount +=  esi.getSiteList().size();
			for(SiteAllInfo in : esi.getSiteList()) {
//				if(in.getSiteUrl().getSelfSite()==1) {
//					mysite = true; //包括我司托管网站
//				}
				sb.append("<tr>")
				.append(getTableTD(++i+""))
				.append(getTableTD(esi.getEmployee().getRealName()+"("+esi.getEmployee().getEmplNo()+")"))
				.append(getTableTD(in.getSiteUrl().getCustomer()))
				.append(getTableTD(in.getEmployeeSite().getSfName()))
				.append(getTableTD(in.getSiteUrl().getSelfSite()==1?"是":"否"))
				.append(getTableTD(in.getSiteUrl().getIp()))
				.append(getTableTD(in.getSiteHistory().getStatus()+":"+getHttpStringByCode(in.getSiteHistory().getStatus())))
				.append(getTableTD("<a href=\""+in.getSiteUrl().getPage()+"\">"+in.getSiteUrl().getPage())+"</a>")	
				.append("</tr>");
			}
		}
		
		sb.append("</table>");
		try {				
			mailUtil.sendSimpleMail(dictionaryService.queryParamsValue(11), "【重要】  网站异常数据总表"+DateUtil.getDate()+"_"+errorCount, 
					"<div>Dear All：</div><div>&nbsp;&nbsp;&nbsp;&nbsp; 您好！以下客户推广网站无法打开，请知晓(检测时间："+endTime+")！</div><br/>"
					+""+sb.toString()+"<div><br>&nbsp;&nbsp;&nbsp;&nbsp; 感谢您的支持，如有识别错误或建议请联系本人以升级策略！</div>"+author);
			return true;
		} catch (ExecutionException e) {
			logger.error("************************************************************************邮件发送失败：");
		}		
		return false;
	}

	/** 
	* @Title: getTableTD 
	* @Description: 组装表格单元 
	* @param text
	* @return  
	* String    返回类型 
	* @throws 
	*/
	private String getTableTD(String text) {
		return "<td style=\"border-width: 1px;	padding: 8px;	border-style: solid;border-color: #666666;	background-color: #ffffff;\" >"+text+"</td>";
	}
	
	/** 
	* @Title: getTableTitle 
	* @Description: 组装表格头单元 
	* @param titleName
	* @return  
	* String    返回类型 
	* @throws 
	*/
	private String getTableTitle(String titleName) {
		return "<th style=\"border-width: 1px;	padding: 8px;border-style: solid;border-color: #666666;	background-color: #dedede;\">"+titleName+"</th>";
	}
	

}
