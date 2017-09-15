package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import net.sf.oval.constraint.DateRange;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface BeViewCvDao {
	/**
	 * 
	* @Title: queryRecruitView
	* @Description:谁查看过我接口用dao（查询b_recruit_view表）
	* @return Map<Object,Object>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("burs")
	public List<Map<Object,Object>> queryRecruitView(Integer cvid);
	
	/***
	 * 
	* @Title: queryUserCvId
	* @Description: 查看用户的简历id(查询b_user_cv表)
	* @return Integer    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("burs")
	public Integer queryUserCvId(Integer uid);
	
	/**
	 * 
	* @Title: beViewCvList
	* @Description: 查询谁看过我的商家详细信息(查询)
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("burs")
	public List<Map<Object,Object>> sellerInfoList(List<Map<Object,Object>> list);
	
	/**
	 * 
	* @Title: beViewCvList
	* @Description: 查看商铺的招聘信息
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("burs")
	public List<Map<Object,Object>> beViewCvList(List<Map<Object,Object>> list);
	
	/**
	 * 
	* @Title: querySellerLogoList
	* @Description:查询商铺的封面
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> querySellerLogoList(List<Map<Object,Object>> list);
	
	/**
	 * 
	* @Title: queryCityName
	* @Description: 查询工作市区的名称
	* @return String    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public String queryCityName(Integer cityno);
}
