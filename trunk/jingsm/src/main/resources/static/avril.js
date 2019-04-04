document.write("<script language='javascript' src='./jl/http.jl.js'></script>");
document.write("<script language='javascript' src='./jl/jquery.ajax.jl.js'></script>");
document.write("<script language='javascript' src='./jl/jquerysession.js'></script>");

//初始化代码
$(document).ready(function(){ 	
	console.log('load avril.js');	
	AVRIL.info();		
});

function showJLWarning(message){
	new $.zui.Messager(message, {
		type : 'warning',
		placement : 'top-right',
		close : false
	// 禁用关闭按钮
	}).show();
}

function showJLSuccess(message){
	new $.zui.Messager(message, {
		type : 'success',
		placement : 'top-right',
		close : false
	// 禁用关闭按钮
	}).show();
}

function showJLError(message){
	new $.zui.Messager(message, {
		type : 'danger',
		placement : 'top-right',
		close : false
	// 禁用关闭按钮
	}).show();
}

(function($) {
	//注册全局系统对象
	window['AVRIL'] = {};
	
	AVRIL.setPersion=function(persionId, persionName){
		$.session.set("persionId", persionId);
		$.session.set("persionName", persionName);
	};
	
	AVRIL.clearPersion=function(){
		$.session.remove("persionId");
		$.session.remove("persionName");
	};	
	
	AVRIL.persionId=function(){
		return $.session.get("persionId");
	}
	
	AVRIL.persionName=function(){
		return $.session.get("persionName");
	}	
	
	//定义方法-属性
	AVRIL.info = function(){
		console.log('AVRIL FUCK');
	};
	
	//删除配置
	AVRIL.deleteConfig=function(dicId, $row){
		myAjax("/dictionary/"+dicId, "delete", {}, function(data){
			if($row){
				$row.remove();
			}
		}, false);
	}
	
	//配置添加或修改
	AVRIL.bindConfig=function(params, fn){
		var url = '/dictionary';
		//params.dicGroup = 'config_ele_group';
		if(params.dicId!=undefined && params.dicId.length>0){
			url += ('/'+params.dicId);
			//params.dicGroup = undefined;
		}
		myAjaxJson(url, url=='/dictionary'?"post":"put", params, function(data){			
			//if(data!=undefined && data.batchCode!=undefined){
				if(fn){fn(data);}				
			//}
		}, false);
	}
	
	//配置信息查询
	AVRIL.queryConfig=function(){		
		myAjax("/dictionarys?pageNo=1&pageSize=100&sort=%7B%22dicFlag%22%3A%22asc%22%2C%22dicFlag%22%3A%22asc%22%7D", "get", {}, function(data){
			$configTable = $("#configTable");
			$configTable.html('');
			if(data!=undefined && data.data!=undefined && data.data.length>0){
				$data = data.data;
				var ihtml = "";
				for(var i=0; i<$data.length; i++){
					ihtml+=("<tr>"
							+"<td><i onclick=\"doModify(this)\" class=\"icon icon-cog modify\"  data-toggle=\"tooltip\" data-placement=\"left\" title=\"修改\"></i>" 
								+"<i onclick=\"doCannel(this)\" class=\"icon icon-circle-arrow-left saves elehidden\"  data-toggle=\"tooltip\" data-placement=\"left\" title=\"取消\"></i>&nbsp;&nbsp;"
								+"<input type=\"hidden\" class=\"dicId\" value=\""+$data[i].dicId+"\">"
//								+"<input type=\"hidden\" class=\"dicGroup\" value=\""+$data[i].dicGroup+"\">"
								+"<i onclick=\"doSave(this)\" class=\"icon icon-save saves elehidden\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"保存\"></i>"
								+('config_ele_group'==$data[i].dicGroup?
										"<i class=\"icon icon-remove-sign modify\" onclick=\"doDrop(this)\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"删除\"></i>"
								:"")
							+"</td>"
							+"<td>"+$data[i].dicCode+"</td>"
							+"<td>"+$data[i].dicValue+"</td>"
							+"<td>"+$data[i].dicFlag+"</td>"		
							+"<td>"+$data[i].dicGroup+"</td>"	
							+"<td>"+($data[i].dicNote==undefined?"":$data[i].dicNote)+"</td>"
							+"<td>"+$data[i].gmtCreated+"</td>"
							+"<td>"+$data[i].gmtModify+"</td>"
							+"</tr>");
				}
				$configTable.html(ihtml);				
				$configTable.children("td").children("i.saves").hide();
			}
		}, false);
	}
	
	// 查询人员交易历史记录
	AVRIL.queryIPS=function(fn){		
		myAjax("selfips?pageNo=1&pageSize=100&sort=%7B%22count%22%3A%22desc%22%7D", "get", {}, function(data){
			if(fn){
				fn(data);
			}
		}, false);	
	}		
	
	
	//
	AVRIL.savePersionInfo=function(fn, params){
		myAjaxJson("/siteurl/"+params.id, "put", params, function(data){
			if(fn){fn(data);}
		}, false);
	}
	
	//更新交易状态
	AVRIL.addIPS=function(fn, ips){
		var params = {};
		params.ip = ips;
		params.count=0;
		myAjaxJson("/selfip", "post", params, function(data){
			if(fn){fn(data);}
		}, false);
	}
	
	//查询敏感词
	AVRIL.loadSensitive=function(fn, c){
		var params = {};
		params.content = c;
		myAjaxJson("/check/word/12306F2882EF4D3ABE2C0881A2C8D893", "post", params, function(data){
			if(fn){fn(data);}
		}, false);
	}
	
	//更新交易状态
	AVRIL.delIPS=function(fn, ips){
		myAjaxJson("/selfip/"+ips, "delete", {}, function(data){
			if(fn){fn(data);}
		}, false);
	}
	
	//查询交易数据
	AVRIL.queryCheckStastics=function(fn, pn, ps, searchWord){
		var sou = "";
		if(searchWord!=undefined && searchWord.length>0){
			sou = "&source="+searchWord;
		}
		myAjax("/sitestatisticss?sort=%7B%22beginTime%22%3A%22desc%22%7D&pageNo="+pn+"&pageSize="+ps+sou, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	AVRIL.loadSyncList=function(fn, pn, ps, searchWord, searchType){
		var sou = "";
		if(searchWord!=undefined && searchWord.length>0){
			sou += "&emplNo="+searchWord;
		}
		if(searchType!=undefined && searchType.length>0){
			sou += "&isDelete="+searchType;
		}
		myAjax("/datasyncs?sort=%7B%22gmtModify%22%3A%22desc%22%7D&pageNo="+pn+"&pageSize="+ps+sou, "get", {}, function(data){
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	
	
	AVRIL.loadMailList=function(fn, pn, ps, mail){
		var so = '';
		if(mail!=undefined && mail.length>0){
			so = "&sendTo="+mail;
		}
		myAjax("/mailhistorys?sort=%7B%22gmtCreate%22%3A%22desc%22%7D&pageNo="+pn+"&pageSize="+ps+so, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	
	//查询交易数据
	AVRIL.queryKeyWord=function(fn, pn, ps, searchWord){
		var sou = "";
		if(searchWord!=undefined && searchWord.length>0){
			sou = "&status="+searchWord;
		}
		myAjax("/sensitiverecords?sort=%7B%22siteId%22%3A%22desc%22%7D&pageNo="+pn+"&pageSize="+ps+sou, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	
	//加载待检网站信息
	AVRIL.loadKWList=function(fn, pn, ps, searchWord, searchSource){
		var sw = '';
		if(searchWord!=undefined && searchWord.length>0){
			sw = '&keyWord='+searchWord;
		}
		if(searchSource!=undefined && searchSource.length>0){
			sw+=("&status="+searchSource);
		}
		myAjax("/sensitivewords?pageNo="+pn+"&pageSize="+ps+"&sort=%7B%22status%22%3A%22asc%22%7D"+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.deleteKWInfo=function(fn, kwid, kw2){
		myAjaxJson("/sensitiveword/"+kwid, "delete", {}, function(data){
			if(fn){fn(data);}
		}, false);
	}
	
	//更新交易状态
	AVRIL.saveKWInfo=function(fn, params){
		if(params.type=='add'){
			myAjaxJson("/sensitiveword", "post", params, function(data){
				if(fn){fn(data);}
			}, false);
		}else if(params.id!=undefined){
			myAjaxJson("/sensitiveword/"+params.id, "put", params, function(data){
				if(fn){fn(data);}
			}, false);
		}
//		if(params.type=)
//		var params = {};
//		params.ip = ips;
//		params.count=0;
//		myAjaxJson("/selfip", "post", params, function(data){
//			if(fn){fn(data);}
//		}, false);
	}
	
	
	//加载待检网站信息
	AVRIL.loadSiteUrlList=function(fn, pn, ps, searchWord, searchSource){
		var sw = '';
		if(searchWord!=undefined && searchWord.length>0){
			sw = '&demin='+searchWord;
		}
		if(searchSource!=undefined && searchSource.length>0){
			sw+=("&source="+searchSource);
		}
		myAjax("/siteurls?pageNo="+pn+"&pageSize="+ps+"&sort=%7B%22persionName%22%3A%22asc%22%7D"+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	//加载待检网站信息
	AVRIL.loadSiteUrlIPList=function(fn, pn, ps, searchWord){
		var sw = '';
		if(searchWord!=undefined && searchWord.length>0){
			sw = '&ip='+searchWord;
		}else{
			sw = '&noip=1';
		}
		myAjax("/siteurls?pageNo="+pn+"&pageSize="+ps+"&sort=%7B%22persionName%22%3A%22asc%22%7D"+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	//加载检测结果
	AVRIL.loadResultList=function(fn, pn, ps, searchWord, searchStatus, searchComment){
		var sw = '';
		if(searchWord!=undefined && searchWord.length>0){
			sw = '&page='+searchWord;
		}
		if(searchStatus!=undefined && searchStatus>0){
			sw += '&status='+searchStatus;
		}
		if(searchComment!=undefined && searchComment>0){
			sw += '&comment='+searchComment;
		}
		myAjax("/siteresults?pageNo="+pn+"&pageSize="+ps+"&sort=%7B%22status%22%3A%22DESC%22%2C%22comment%22%3A%22DESC%22%7D"+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.loadResultHisList=function(fn, pn, ps, sid, searchWord, searchStatus, searchComment){
		var sw = '&id='+sid;
		if(searchWord!=undefined && searchWord.length>0){
			sw += '&page='+searchWord;
		}
		if(searchStatus!=undefined && searchStatus>0){
			sw += '&status='+searchStatus;
		}
		if(searchComment!=undefined && searchComment>0){
			sw += '&comment='+searchComment;
		}
		myAjax("/sitehistorys?pageNo="+pn+"&pageSize="+ps+"&sort=%7B%22status%22%3A%22DESC%22%2C%22comment%22%3A%22DESC%22%7D"+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.checkOneSite=function(fn, url){
		myAjax("/check/url?url="+url, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.checkMutiByOneSite = function(fn, url, f){
		myAjax("/check/url?url="+url, "get", {}, function(data){			
			if(fn){
				fn(data, f);
			}
		}, false);
	}
	
	AVRIL.checkMutiSite=function(fn, url){		
		var params = {};
		params.url = url;
		myAjax("/check/urls", "post", params, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.loadFullWebList = function(fn, pn, ps, url, order, id){
		var sw = '';
		if(url!=undefined && url.length>0){
			sw = '&page='+url;
		}
		if(order!=undefined && order.length>0){
			sw +="&sort=%7B%22"+order+"%22%3A%22desc%22%7D";
		}else{
			sw +="&sort=%7B%22gmtCreated%22%3A%22desc%22%7D";
		}
		if(id!=undefined && id.length>0){
			sw += '&id='+id;
		}
		myAjax("/monitoroutlines?pageNo="+pn+"&pageSize="+ps+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.doAllSiteCrawler = function(fn, page, mail){
		var params = {};
		params.page = page;
		if(mail!=undefined && mail.length>0){
			params.mail = mail;
		}
		myAjaxJson("/monitor/all", "post", params, function(data){
			if(fn){fn(data);}
		}, false);
	}
	
	AVRIL.loadFullDetailList= function(fn, pn, ps, id, order){
		var sw = '';
		if(id!=undefined && id.length>0){
			sw = '&id='+id;
		}
		if(order!=undefined && order.length>0){
			sw +="&sort=%7B%22"+order+"%22%3A%22desc%22%7D";
		}else{
			sw +="&sort=%7B%22code%22%3A%22desc%22%7D";
		}
		myAjax("/childinfos?pageNo="+pn+"&pageSize="+ps+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.loadKeyWordList = function(fn, pn, ps, kw, id, types, sour){
		var sw = '';
		if(kw!=undefined && kw.length>0){
			sw = '&keyWord='+kw;
		}
		if(id!=undefined && id.length>0){
			sw += '&id='+id;
		}
		if(types!=undefined && types.length>0){
			sw += '&status='+types;
		}
		if(sour!=undefined && sour.length>0){
			sw += '&page='+sour;
		}
		myAjax("/sensitiveitems?pageNo="+pn+"&pageSize="+ps+"&sort=%7B%22status%22%3A%22asc%22%7D"+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.loadLinkList = function(fn, pn, ps, kw, id, types, sour){
		var sw = '';
		if(kw!=undefined && kw.length>0){
			sw = '&link='+kw;
		}
		if(id!=undefined && id.length>0){
			sw += '&id='+id;
		}
		if(types!=undefined && types.length>0){
			sw += '&status='+types;
		}
		if(sour!=undefined && sour.length>0){
			sw += '&page='+sour;
		}
		myAjax("/linkitems?pageNo="+pn+"&pageSize="+ps+"&sort=%7B%22status%22%3A%22desc%22%7D"+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	
	AVRIL.loadHistoryUrlList=function(fn, pn, ps, url){
		var sw = '&page='+url;
		myAjax("/sitehistorys?pageNo="+pn+"&pageSize="+ps+"&sort=%7B%22gmtCreated%22%3A%22desc%22%7D"+sw, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.doControlFunction=function(fn, path, param, token){
		if(param!=undefined){
			token += "?source="+param;
		}
		myAjax("/check/"+path+"/"+token, "get", {}, function(data){	
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	
	/**
	 * 
	 * 初始化分页条
	 * @param pageBe
	 */
	AVRIL.initPageBar = function(pageBe){
		var maxPage;
		if(pageBe==undefined || pageBe.totalCount==0){
			maxPage=1;
			$(".pager").html("<li class=\"active\"><a href=\"javascript:void(0);\" onclick=\"goPage(1)\" class=\"on\">刷新</a></li>");
			//无数据
			return;
		}
		if(pageBe.totalPages==1){
			$(".pager").html("<li class=\"active\"><a href=\"javascript:void(0);\" onclick=\"goPage(1)\" class=\"on\">1</a></li>");
			return;
		}
		var khtml = "";
		if(pageBe.hasPrePage==true){
			khtml+="<li class=\"previous\"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.page-1)+")\">« 上一页</a></li>";
		}
		if(pageBe.totalPages<10){
			var phtml=khtml;
			for(var j=0; j<pageBe.totalPages; j++){
				phtml += "<li "+(j+1==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage("+(j+1)+")\" >"+(j+1)+"</a></li>";
			}
			if(pageBe.page!=pageBe.totalPages){
				phtml+="<li class=\"next\"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.page+1)+")\">下一页  »</a></li>";
			}
			$(".pager").html(phtml);
			return;
		}
		khtml += "<li "+(1==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage(1)\" >1</a></li><li "+(2==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage(2)\" >2</a></li>";
		var cpage = pageBe.page; //准备中间页
		if(pageBe.page<6){
			//在头5页时
			cpage=3;
		}else if(pageBe.page>pageBe.totalPages-4){
			cpage=pageBe.totalPages-6;
			khtml+=" <li><a href=\"javascript:void(0);\">⋯</a></li>";
		}else{
			cpage=pageBe.page-2;
			khtml+=" <li><a href=\"javascript:void(0);\">⋯</a></li>";
		}
		
		for(var k=0; k<5; k++){
			khtml+=("<li "+((cpage+k)==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage("+(cpage+k)+")\" >"+(cpage+k)+"</a></li>");
		}
		if(pageBe.page<pageBe.totalPages-4){
			khtml+=" <li><a href=\"javascript:void(0);\">⋯</a></li>";
		}
		khtml += ("<li "+((pageBe.totalPages-1)==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.totalPages-1)+")\" >"+(pageBe.totalPages-1)+"</a></li><li "+(pageBe.totalPages==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.totalPages)+")\" >"+(pageBe.totalPages)+"</a></li>");
		if(pageBe.hasNextPage==true){
			khtml+="<li class=\"next\"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.page+1)+")\">下一页  »</a></li>";
		}
		$(".pager").html(khtml);
		return;
	};
	
})(jQuery);