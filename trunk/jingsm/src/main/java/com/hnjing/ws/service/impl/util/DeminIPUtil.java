/**  
 * Copyright © 2018公司名字. All rights reserved.
 * @Title: DeminIPUtil.java
 * @Prject: WebHealthMonitor
 * @Package: com.hnjing.ws.service.impl.util
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2018年10月16日 上午10:03:31
 * @version: V1.0  
 */
package com.hnjing.ws.service.impl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DeminIPUtil
 * @Description: 获取域名IP
 * @author: Jinlong He
 * @date: 2018年10月16日 上午10:03:31
 */
public class DeminIPUtil {
	
	
	private static final Logger logger = LoggerFactory.getLogger(DeminIPUtil.class);
	
	public static boolean isIPRes(String res) {
		if(res==null || res.length()<7) {
			return false;
		}
		if(res.length()>23) {
			res = res.substring(0, 23);
		}
		Matcher m = Pattern.compile("((\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3}))").matcher(res);
		while(m.find()) {
			return true;
		}
		return false;
	}
	
	public static String getIPRes(String res) {
		if(res==null || res.length()<7) {
			return null;
		}
		if(res.length()>23) {
			res = res.substring(0, 23);
		}
		Matcher m = Pattern.compile("((\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3}))").matcher(res);
		while(m.find()) {
			return m.group(1);
		}
		return null;
	}
	
	/** 
	* @Title: cmdPingIP 
	* @Description: 采用CMD ping形式获取域名IP
	* @param ipAddress
	* @return  
	* String    返回类型 
	* @throws 
	*/
	public static String cmdPingIP(String ipAddress, String oldIp) { 
		//域名处理
		if(ipAddress==null) {
			return null;
		}
		if(ipAddress.contains("/")) {
			//包含
			String[] u = ipAddress.split("/");
			for(String c : u) {
				if(c.contains(".") && c.length()>3) {
					ipAddress = c; 
					break;
				}
			}
		}
//		System.out.println(ipAddress);
		//网址取域名		
		String ret = getIPFromDemin(ipAddress);
		if(ret==null || ret.length()==0) {
			return getIPFromDemin(ipAddress); //新的为空时复检一次
		}
		return ret;
    }
	
//	public static void main(String[] args) throws Exception {
//		flushDNS();
//    }

	/** 
	* @Title: flushDNS 
	* @Description: 刷新本地DNS缓存 
	* @return  
	* boolean    返回类型 
	* @throws 
	*/
	public static boolean flushDNS() {
		Runtime r = Runtime.getRuntime();
		BufferedReader in = null;
		try { // 执行命令并获取输出
			Process p = r.exec("ipconfig /flushdns");
			if (p == null) {
				return false;
			}
			in = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK")); // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			logger.info(sb.toString());
			return true;
		} catch (Exception ex) {
			ex.printStackTrace(); // 出现异常则返回
			logger.error("****** ipconfig /flushdns [ ERROR ]" + ex.getMessage());
			return false;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static String getIPFromDemin(String ipAddress) {		
		int pingTimes = 1;
		int timeOut = 10;
        BufferedReader in = null;  
        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令
        
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes    + " -w " + timeOut;  
        try {   // 执行命令并获取输出  
//            System.out.println(pingCommand);   
            Process p = r.exec(pingCommand);   
            if (p == null) {    
                return null;
            }
            //Runtime.getRuntime().exec() 输出流阻塞的解决方法
//            Thread t=new Thread(new InputStreamRunnable(p.getErrorStream(),"ErrorStream"));
//            t.start(); 
            in = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数  
            StringBuffer sb = new StringBuffer();   
            String line = null;  
            while ((line = in.readLine()) != null) {    
            	sb.append(line);   
            }             
            return getCMDResultIP(ipAddress, sb.toString());  
        } catch (Exception ex) {   
            ex.printStackTrace();   // 出现异常则返回 
            logger.error("****** ping " +ipAddress+" [ ERROR ]"+ex.getMessage());
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException e) { 
                e.printStackTrace();
            }
        }
	}
	
	/** 
	* @Title: getCMDResultIP 
	* @Description: 从Ping结果获取相应IP并返回
	* @param demin
	* @param mes
	* @return  
	* String    返回类型 
	* @throws 
	*/
	private static String getCMDResultIP(String demin, String mes) {
		//System.out.println(demin+" ?  "+mes);
		mes = mes.replaceAll("\\[", "").replaceAll("\\]", "");
		Pattern pattern = Pattern.compile(HttpToolUtil.IPregex);
		String[] m = mes.split(" ");
		for(String c : m) {
			if(pattern.matcher(c).matches()) {
				logger.debug("****** ping " +demin+" ["+c+"]");
				return c;
			}
		}
		logger.debug("****** ping " +demin+" [ null ]");
        return null;		
	}
	
//	public static void main(String[] args) throws Exception {
//		cmdPingIP("http://www.baidu.com/aaidfa/afjafda/adfadf.html", null);
//    }
}
//
//class InputStreamRunnable implements Runnable {
//	BufferedReader bReader = null;
//	String type = null;
//
//	public InputStreamRunnable(InputStream is, String _type) {
//		try {
//			bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
//			type = _type;
//		} catch (Exception ex) {
//		}
//	}
//
//	public void run() {
//		String line;
//		int lineNum = 0;
//		try {
//			while ((line = bReader.readLine()) != null) {
//				lineNum++;
//				// Thread.sleep(200);
//			}
//			bReader.close();
//		} catch (Exception ex) {
//		}
//	}
//}
