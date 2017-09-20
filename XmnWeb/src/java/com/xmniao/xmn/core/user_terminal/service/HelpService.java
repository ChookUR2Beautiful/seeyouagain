/**
 * 
 */
package com.xmniao.xmn.core.user_terminal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.user_terminal.dao.HelpInfoDao;
import com.xmniao.xmn.core.user_terminal.dao.HelpItemDao;
import com.xmniao.xmn.core.user_terminal.entity.THelpInfo;
import com.xmniao.xmn.core.user_terminal.entity.THelpItem;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：HelpService
 * 
 * 类描述： 
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2016年8月11日 下午5:31:37 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class HelpService {

	@Autowired
	private HelpInfoDao helpDao;
	
	@Autowired
	private HelpItemDao helpItemDao;

	public void insterHelpInfo(THelpInfo helpInfo){
		helpInfo.setUdate(new Date());
		helpDao.insterHelpInfo(helpInfo);
	}
	
	public void deleteHelpInfo(Integer id){
		helpDao.deleteHelpInfo(id);
	}
	
	public void updateHelpInfo(THelpInfo helpInfo){
		helpInfo.setUdate(new Date());
		helpDao.updateHelpInfo(helpInfo);
	}
	
	public List<THelpInfo> getHelpList(THelpInfo helpInfo){
		return helpDao.getHelpList(helpInfo);
	}
	
	public long getHelpCount(THelpInfo helpInfo){
		return helpDao.getHelpCount(helpInfo);
	}
	
	public THelpInfo getHelpInfo(Integer id){
		return helpDao.getHelpInfo(id);
	}
	
	public void insterHelpItem(THelpItem helpItem){
		helpItem.setUdate(new Date());
		helpItemDao.addHelpItem(helpItem);
	}
	
	public void deleteHelpItem(Integer id){
		helpItemDao.deleteHelpItem(id);
		helpDao.deleteHelpInfo(id);
	}
	
	public void updateHelpItem(THelpItem helpItem){
		helpItem.setUdate(new Date());
		helpItemDao.upadteHelpItem(helpItem);
	}
	
	public List<THelpItem> getHelpItemList(THelpItem helpItem){
		return helpItemDao.getHelpItemList(helpItem);
	}
	
	public long getHelpItemCount(THelpItem helpItem){
		return helpItemDao.getHelpItemCount(helpItem);
	}
	
	public THelpItem getHelpItem(Integer id){
		return helpItemDao.getHelpItem(id);
	}
}
