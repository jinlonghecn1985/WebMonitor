/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: AIS.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.utils.file.office
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月17日 上午9:31:28
 * @version: V1.0  
 */
package com.hnjing.utils.file.office;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.cw.model.entity.SensitiveWord;

/**
 * @ClassName: AIS
 * @Description: 
 * @author: Jinlong He
 * @date: 2019年1月17日 上午9:31:28
 */
public class AIS {
	
	private static String lineSeparator = System.getProperty("line.separator");
	
//	private static String yesWord = "";
//	private static String noWord = "不.{0%2c4}[清楚,懂],开会,开车,";
//	private static String unknowWord = "";
	
	
	private static String[][] keyword = new String[][]{
		new String[]{"I：您好，老板，我是湖南百度竞网的。我刚想百度一下你们公司的产品，先请问一下你们公司有做官网吗？", "有，做了，有做官网，有官网，有啊", "没，没有，没做，没有做，没官网，没有做过，不用，不需要"},
		new String[]{"：可是我刚才百度了一下，发现怎么你们公司的信息很少呢，你们有做网络推广吗？", "有，做了，有啊，有做推广，做了推广，已经有推广，", "没，没有，没做，不用，不需要，没推广"},
		new String[]{"难怪，我刚才搜索你们的产品，都是您同行的信息呢。（同行刺激）您应该也了解，我们的企业今天很大部分（强调语气）客户都来自线上，对吧？我们也在百度后台的大数据库里看到很多网民都在搜索你这个产品和服务，您有接到过网上客户的咨询吗", "有啊，有接到过啊，接到过啊，能收到咨询啊", "没有，不用，不需要，没做过，没搞过"},
		new String[]{"。既然您做了推广，那我想您肯定希望推广效果更好一些吧？", "是，是啊，对啊，肯定啊，那肯定的不，那是肯定，希望效果好，那肯定了，是的", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要"},
		new String[]{"恩，没关系。（先附和）但我们了解到啊：一般人在消费之前，都习惯搜索一下这个公司的真实性，这种经历您一定也有过吧？", "有，有点道理，是的", "没有，不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，用不上，公司小不要，没必要"},
		new String[]{" 是的，网站其实是一家企业的标配呢！假如您的客户在想和您合作之前，上网核算一下你们公司情况，结果发现什么信息都没有，我相信您的客户心里肯定是没底的。您认可吧", "认可，是的，有，说的对，有点道理，也是，也差不多，是这样，确实是这样", "不对，不认可，不需要，不要，不用"},
		new String[]{"给您介绍一下，我们湖南竞网拥有湖南最大的建站团队，湖南本土企业每 4个网站就有 1 个是我们做的。湖南竞网也是全国互联网百强企业， 您要做网站啊，找竞网一定没错的！", "好，哦，嗯，是很不错，是的，还好，不错", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，用不上"},
		new String[]{"：看得出来您对这个还是很有兴趣的。是这样的，老板，我们是需要根据您的产品特征、行业分析，匹配最好的方案给您。就像买车，也分高中低配，对吧？这个部分需要与业的策划顾问帮您来详细策划的。这样，您保持电话畅通，我让我们的策划顾问待会就电话联系您、详细给您讲解一下吧！", "好，可以，好的，行的，行行行，好好好，ok", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，用不上"},
		new String[]{"您也不用拒绝得这么快，每天有大量的客户在百度上寻找您的产品，你花个一分钟具体了解一下这个获得客户的方法对您也没有什么损失，您说对吧", "好，可以， 能找到啊 ，可以找到啊 ，可以搜索到啊， 能啊， 行啊", "不需要，不用，谢谢不需要，谢谢没必要，没兴趣，不想了解，没必要了解，没什么用，对我没什么用，我们不需要，不必，没这个必要， 哦不用"},
		new String[]{"好的，那您先忙，我这边是湖南百度客服中心的，我叫笑笑，晚些时候我再给你来电话？", "好，可以，好的，行的，行行行，好好好，ok", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，"},
		new String[]{"我们公司是百度湖南地区的唯一授权服务中心，我们的办公地址在：长沙市岳麓区文轩路27号麓谷广场C3栋1-4层 （就是中联重科的斜对面）", "好，可以，好的，行的，行行行，好好好，ok", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，"},
		new String[]{"我们百度的服务本来就是按效果付费的，只有搜索与你产品有关的这些准客户才能看到你的信息，你想想他都在百度上搜索这个产品了，肯定是意向很高了，没有比我们这个宣传更精准的了", "恩，是的，没错，好的", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，"},
		new String[]{"我们是收取预存款和服务费2个部分的，预存款起步是1万元，再加一个3600的服务费  具体的话让我们经理给您做下详细介绍吧", "好，可以，好的，行的，行行行，好好好，ok", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，"},
		new String[]{"百度您应该有用过吧，你可以搜索一下你们产品相关的词语，你就可以看到已经有同行在上面做了，你做了后就和他们一样的效果，很直观的。", "哦，好的，我看看", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，"},
		new String[]{"我们是百度官方授权的，湖南地区唯一服务商，我们主要是在地方上为你提供服务的，我们公司名字叫湖南竞网，在长沙、常德、湘潭都有分公司的。", "哦，好的，我知道了", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，"},
		new String[]{"这个预存款是百度官方定价的，10000块只是存在这，真正客户过来才几毛钱、几块钱而已，要不这样，我先让客服经理根据你的产品帮你做个费用测算，她测算好了会给你打电话，你再看看这个花费能不能承受，你看可以吗？", "可以，好好好，行行行，恩好，可以的，好吧，那好吧，那可以咯", "以后再说吧， 我在考虑， 再考虑一下， 再考虑考虑， 到时候再说 ，有需要在联系你， 有需要再联系 ，我想一下 ，先了解一下 ，先看一下 ，在看吧 ，再看吧 ，商量一下， 再看看"},
		new String[]{"好的，老板，您看这样，因为我这个电话只能打出去 您是打不进来的，为了方便您更好的了解或者之后有需要了找我们，您可以关注一下我们的公众号，您搜索“湖南百度”就有了。 ", "好，可以，好的，行的，行行行，好好好，ok", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，"},
		new String[]{"我们是有专业的客服团队会帮你操作的，也会有专门的培训，你可以来学习操作，前期刚开始做的时候，我们客服都会帮你操作好，后期你自己熟悉了，自己也可以进后台去调整", "好的，行的，行行行，好好好，ok，我知道了", "我不会，操作我不会，搞不会，不会做啊"},
		new String[]{"我是百度湖南竞网外呼机器人笑笑", "嗯，哦，好，好的，ok", "不需要，谢谢不用，不要在联系我"},
		new String[]{"那很好啊，现在网民想要买个东西什么的都习惯性的要到我们百度上先看看，增加信任，您也是非常有互联网营销意识的嘛，我们有更专业的人来帮您操作，会让您的客户跟容易找到你", "嗯，哦，好，好的，ok，行，可以", "不需要，不用，不要，没必要，已经有了"},
		new String[]{"我们的官方400电话是400-0731-177，或者您保持电话畅通，稍后让我们的经理跟您联系", "嗯，哦，好，好的，ok，行，可以", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，"},
		new String[]{"是这样的 老板。 如果您这边要是感兴趣的话我让我们这边经理来联系您给您做下详细介绍您看可以吗。 ", "嗯，哦，好，好的，ok，行，可以", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，没兴趣"},
		new String[]{"好的 我一会加您备注是百度推广 你你记得通过一下 稍后我们经理也会再联系您来电话的 请您保持电话畅通 注意接听哦", "嗯，哦，好，好的，ok，行，可以", "不需要，不要，不用，暂时不需要，暂时不要，暂不要，现在没有必要，没兴趣"},
		new String[]{"感谢您的信任与支持，我们是湖南百度服务中心的我叫笑笑  祝您生活愉快 再见， ", "嗯，哦", "不需要，不要，不用"},
		new String[]{"好的 ，感谢您的接听，祝您生活愉快，再见。", "嗯，哦", "不需要，不要，不用"}
		
	};
	
	private static String xlsTitle = "通话详情";


	/** 
	* @Title: main 
	* @Description: 
	* @param args  
	* void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) {		
		//1、读取数据
		System.out.println("1、读取开始------------------------");	
		String file = "D:\\已接通电话1717个.xls";
		List<List<String>> xls = null;		
		try {
			xls = ExcelUtil.readExcel(file, true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取错误，结束程式！");
			return ;
		}
		System.out.println("读取数据："+xls.size());
		//2、处理数据
		System.out.println("2、处理开始------------------------");
		Map<String, FQ> all = null;		
		if(xls!=null && xls.size()>0){
			all = xlsDataToFQ(xls);
		}else {
			System.out.println("无数据要处理，结束程式！");
		}
		System.out.println("处理数据："+all.size());
		//3、处理识别词
		System.out.println("3、初始识别词------------------------");
		Map<String, String[]> wordMap = initKeyWord();		
		int notS = all.size();
		for(String key : all.keySet()) {
			if(!wordMap.containsKey(key)) {
				System.out.println("问题未找到识别词："+key);
				notS--;
			}
		}
		System.out.println("初始识别词结束:"+(notS+"/"+all.size())+"------------------------");
		//4、数据分析
		for(String key : all.keySet()) {
			if(wordMap.containsKey(key)) {
				distinguishData(all.get(key), wordMap.get(key));
			}
		}
		
		
		//0 输出
		String[] title = new String[] {"内容","回答总量","肯定总量","否定总量","其它总量","分析详情"};
		String[][] content = new String[all.size()][6];
		int step = 0;
		for(String key : all.keySet()) {
//			content[step][0] = key;
//			content[step][1] = all.get(key).getAllCount()+"";
//			content[step][2] = all.get(key).getYesCount()+"";
//			content[step][3] = all.get(key).getNoCount()+"";
//			content[step][4] = (all.get(key).getAllCount()-all.get(key).getYesCount()-all.get(key).getNoCount())+"";
//			content[step][5] = "中国"; //all.get(key).answerDetailToString();				
			System.out.println("");
			System.out.println(key);
			System.out.println(all.get(key).answerToString());
			System.out.println(all.get(key).answerDetailToString());	
			step++;
		}
//		HSSFWorkbook hbook = ExcelWriteUtil.getHSSFWorkbook("AI数据分析", title, content, null);
//		ExcelWriteUtil.recordXls(hbook.getBytes(), "AI数据分析-已接通电话1717个");
		System.out.println("写入文件结束:"+"------------------------");
		

	}
	
	
	/** 
	* @Title: distinguishData 
	* @Description: 识别肯定与否定
	* @param fq
	* @param words  
	* void    返回类型 
	* @throws 
	*/
	private static void distinguishData(FQ fq, String[] words) {
		String[] yesWordList = words[0].split(",");
    	String[] noWordList = words[1].split(",");
    	if(yesWordList!=null && yesWordList.length>0) {
    		for(String yes : yesWordList) {
    			yes = yes.replaceAll("%2c", ","); //
    		}    		
    	}
    	if(noWordList!=null && noWordList.length>0) {
    		for(String no : noWordList) {
    			no = no.replaceAll("%2c", ","); //
    		}    		
    	}
    	
		if(fq!=null && fq.getAnswer()!=null) {
			for(String key: fq.getAnswer().keySet()) {
				if(handleWord(key, noWordList, fq.getAnswer().get(key))) {
					fq.setAnswerType(key, 2);	//否定
				}else if(handleWord(key, yesWordList, fq.getAnswer().get(key))) {
					fq.setAnswerType(key, 1); //肯定
				} 
			}
		}
		
	}
        
    /** 
    * @Title: handleWord 
    * @Description: 检测词汇是否存在
    * @param content
    * @param word
    * @return  
    * boolean    返回类型 
    * @throws 
    */
    private static boolean  handleWord(String content, String[] word, FQDetail fq) {    	
    	for(String s : word) {
	    	Pattern p = Pattern.compile(s);
	        Matcher m = p.matcher(content);
	        if (m.find()) {	  
	        	fq.setWord(s);
	        	return true;
	        }
    	}
    	return false;
    }
	
	
	/** 
	* @Title: xlsDataToFQ 
	* @Description: 原始数据转换
	* @param xls
	* @return  
	* Map<String,FQ>    返回类型 
	* @throws 
	*/
	private static Map<String, FQ> xlsDataToFQ(List<List<String>> xls) {
		Map<String, FQ> all = new HashMap<String, FQ>();
		List<String> title = xls.get(0);
		int contextFlag = 0;
		for(int i=0; i<title.size(); i++) {
			if(xlsTitle.equals(title.get(i))) {
				contextFlag = i;
			}
		}
//		System.out.println("通话详情 待分析数据所在列号:"+contextFlag);
//		System.out.println("------------------------");
		for(int j=1; j<xls.size(); j++) {
//			System.out.println(j+" "+xls.get(j).get(contextFlag));	
			if(xls.get(j)!=null && xls.get(j).size()>=contextFlag) {				
				String question = null;
				String[] qa = xls.get(j).get(contextFlag).split(lineSeparator); //按行读取问答数据
//				int code = 0;  //内行号
				for(String l : qa) {
					String line = l.replace("\n", ""); //问题合并
					if(line.startsWith("AI：")) {
						//判断是否问题
						question = line.substring(4, line.length()-1);
						if(!all.containsKey(question)) {
							all.put(question, new FQ(question));
						}	
//						System.out.println(++code+" Q:"+(question));
					}else {
						//回答
						line = line.substring(4, line.length()-1);
						all.get(question).setAnswer(line);
//						System.out.println(++code+" A:"+(line));
					}						
				}
			
			} //有数据时才处理
//			j = xls.size();
		}
		return all;
	}
	
	
	/** 
	* @Title: initKeyWord 
	* @Description: 初始化判定用词 
	* @return  
	* Map<String,String[]>    返回类型 
	* @throws 
	*/
	private static Map<String, String[]> initKeyWord(){
		Map<String, String[]> ret = new HashMap<String, String[]>();
		if(keyword!=null && keyword.length>0) {
			for(int i=0; i<keyword.length; i++) {
				if(keyword[i]!=null && keyword[i].length>2) {
					ret.put(keyword[i][0], new String[] {keyword[i][1].trim().replaceAll("，", ","), keyword[i][2].trim().replaceAll("，", ",")});
				}
			}
		}		
		return ret;
	}

	

}
