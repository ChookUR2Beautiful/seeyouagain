package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import com.xmniao.xmn.core.user_terminal.entity.THelpInfo;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：THelpInfoDao
 *
 * 类描述：帮助条目DAO
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2016年8月11日下午4:25:24
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface HelpInfoDao {
	@DataSource("savle")
	public int insterHelpInfo(THelpInfo helpInfo);
	
	@DataSource("savle")
	public int deleteHelpInfo(Integer id);
	
	@DataSource("savle")
	public int deleteHelpInfoByItem(Integer itemId);
	
	@DataSource("savle")
	public int updateHelpInfo(THelpInfo helpInfo);
	
	@DataSource("savle")
	public List<THelpInfo> getHelpList(THelpInfo helpInfo);
	
	@DataSource("savle")
	public long getHelpCount(THelpInfo helpInfo);
	
	@DataSource("savle")
	public THelpInfo getHelpInfo(Integer id);
}