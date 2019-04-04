package com.hnjing.ws.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.hnjing.ws.service.SiteIPService;
import com.hnjing.cw.service.SensitiveService;
import com.hnjing.dpc.service.DPSService;
import com.hnjing.full.service.FullSiteMonitorService;
import com.hnjing.sync.service.SyncSiteService;
import com.hnjing.ws.service.SiteAccessService;
import com.hnjing.ws.service.SiteHistoryService;
import com.hnjing.ws.service.SiteResultService;



/**
 * @ClassName: SchedulingConfig
 * @Description: 定时任务配置类     秒 分 时 日 月 周
 * @author: Jinlong He
 * @date: 2017年10月8日 下午3:38:15
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
	private static final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);
	
	@Value("${spring.profiles.active}")
	private String activeType;

	@Autowired
	private SiteAccessService siteMonitorService;
	
	@Autowired
	private SiteIPService siteIPService;
	
	@Autowired
	private SiteResultService siteResultService;
	
	@Autowired
	private DPSService dpsService;
	
	@Autowired
	private SyncSiteService syncSiteService;
	
	@Autowired
	private SensitiveService sensitiveService;
	
	@Autowired
	private SiteHistoryService siteHistoryService;
	
	@Autowired
	private FullSiteMonitorService fullSiteMonitorService;
	
	
	/*************************************************************** IP check start ***/
	/** 
	* @Title: doCheckAllWebIPScheduler 
	* @Description: 定时任务-开始检测网站IP   每天下午13点
	* @throws 
	*/
	@Scheduled(cron = "0 26 20 * * ?")
	public void doCheckAllWebIPScheduler() {
		try {
			siteIPService.checkAllSiteIP(null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：checkAllSiteIPScheduler" + e.getMessage());
		}
	}
	
	/** 
	* @Title: checkNullSiteIPScheduler 
	* @Description: 复查空IP 每天上午及晚上11点
	* void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 28 11,23 * * ?")
	public void checkNullSiteIPScheduler() {
		try {
			siteIPService.checkNullSiteIP();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：checkNullSiteIPScheduler" + e.getMessage());
		}
	}	
	/*************************************************************** IP check over ***/
	
	
	/*************************************************************** web check start ***/
	/** 
	* @Title: doClearResultScheduler 
	* @Description: 清空上日检测数据 
	* void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 1 0 * * ?")
	public void doClearResultScheduler() {
		try {
			siteResultService.clearTable();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：doClearResultScheduler" + e.getMessage());
		}
	}
	
	/** 
	* @Title: doCheckSitePTScheduler 
	* @Description:  定时任务-检测PT网站
	* void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 10 0 * * ?")
	public void doCheckSitePTScheduler() {
		try {
			siteMonitorService.checkSiteStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：doCheckSitePTScheduler" + e.getMessage());
		}
	}
	
	/** 
	* @Title: doCheckSiteSSGScheduler 
	* @Description: 定时任务-每日3点40分开始检测SSG网站
	* @throws 
	*/
	@Scheduled(cron = "0 0 4 * * ?")
	public void doCheckSiteSSGScheduler() {
		try {
			siteMonitorService.checkSiteStatus(2);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：doCheckSiteSSGScheduler" + e.getMessage());
		}
	}	
	
	
	/** 
	* @Title: doSyncSSGDataScheduler 
	* @Description: 同步SSG检测数据源   
	* void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 40 10,22 * * ?")
	public void doSyncSSGDataScheduler() {
		try {
			syncSiteService.syncDataForSource(2);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：doSyncSSGDataScheduler" + e.getMessage());
		}
	}
	
	/** 
	* @Title: doProcessDataForSource 
	* @Description: 处理SSG同步到的数据
	* void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 00 23 * * ?")
	public void doProcessDataForSource() {
		try {
			syncSiteService.processDataForSource(2);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：doProcessDataForSource" + e.getMessage());
		}
	}
	
	/** 
	* @Title: doProcessSensitiveWordScheduler 
	* @Description: 敏感词检测   
	* void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 10 12 * * FRI")
	public void doProcessSensitiveWordScheduler() {
		try {
			sensitiveService.checkResultSensitive();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：doProcessSensitiveWordScheduler" + e.getMessage());
		}
	}
	
	/** 
	* @Title: recheckTodayAllErrorSiteScheduler 
	* @Description: 重新检测当日异常网站,并回写检测结果 
	* void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 0,30 7 * * ?")
	public void recheckTodayAllErrorSiteScheduler() {
		try {
			dpsService.recheckHistory();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：recheckTodayAllErrorSiteScheduler" + e.getMessage());
		}
	}
	
	
	/** 
	* @Title: doProcessAllErrorMailScheduler 
	* @Description: 检测不可访问网站邮件发送 
	* void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 20 8 * * ?")
	public void doProcessAllErrorMailScheduler() {
		try {
			dpsService.processAllErrorMail();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：doProcessAllErrorMailScheduler" + e.getMessage());
		}
	}
	
	/** 
	* @Title: doCheckAllSiteScheduler 
	* @Description: 全站检测
	* void    返回类型 
	* @throws 
	*/
//	@Scheduled(cron = "0 0/4 9-23 * * ?")
//	public void doCheckAllSiteScheduler() {
//		try {
//			//正式环境才执行全站检测
//			if("pub".equals(activeType)) {
//				fullSiteMonitorService.doOneSiteFullCheck();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("定时任务执行发生错误：doCheckAllSiteScheduler" + e.getMessage());
//		}
//	}
	
	/** 
	* @Title: doclearAllSite30Scheduler 
	* @Description: 全站检测数据清理  
	* void    返回类型 
	* @throws 
	*/
//	@Scheduled(cron = "0 5 0 * * ?")
//	public void doclearAllSite30Scheduler() {
//		try {
//			siteHistoryService.deleteDataBeforeDays(30);
//			fullSiteMonitorService.deleteDataBeforeDays(30);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("定时任务执行发生错误：doclearAllSite30Scheduler" + e.getMessage());
//		}
//	}
	
	
		
	
}
