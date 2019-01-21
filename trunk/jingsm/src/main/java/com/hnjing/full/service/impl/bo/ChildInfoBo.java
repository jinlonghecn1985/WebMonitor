/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: ChildInfoBo.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.full.service.impl
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月7日 下午4:43:56
 * @version: V1.0  
 */
package com.hnjing.full.service.impl.bo;

import java.io.Serializable;
import java.util.List;

import com.hnjing.full.model.entity.ChildInfo;
import com.hnjing.full.model.entity.LinkItem;

/**
 * @ClassName: ChildInfoBo
 * @Description: 
 * @author: Jinlong He
 * @date: 2019年1月7日 下午4:43:56
 */
public class ChildInfoBo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ChildInfo childInfo;
	private List<LinkItem> linkList;
	/**
	 * @return the childInfo
	 */
	public ChildInfo getChildInfo() {
		return childInfo;
	}
	/**
	 * @param childInfo the childInfo to set
	 */
	public void setChildInfo(ChildInfo childInfo) {
		this.childInfo = childInfo;
	}
	/**
	 * @return the linkList
	 */
	public List<LinkItem> getLinkList() {
		return linkList;
	}
	/**
	 * @param linkList the linkList to set
	 */
	public void setLinkList(List<LinkItem> linkList) {
		this.linkList = linkList;
	}

}
