package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* 项目名称：xmnService   
* 类名称：ModifySellerInfoDao   
* 类描述：商户信息dao      
* 创建人：liuzhihao   
* 创建时间：2016年5月24日 下午5:48:01   
* @version    
*
 */
@Repository
public interface ModifySellerInfoDao {
	
	//更新商铺信息
	@DataSource("joint")
	public int modifySellerInfo(Map<Object,Object> map);
	
	//插入商铺信息修改记录
	@DataSource("joint")
	public int modifySellerInfoToDetail(Map<Object,Object> map);
	
	//插入环境图
	@DataSource("joint")
	public int insertSellerPic(List<Map<Object,Object>> list);
	
	//把所有环境图插入到图片审核表中
	@DataSource("joint")
	public int addSellerPicApply(List<Map<Object,Object>> list);
	//删除环境图
	@DataSource("joint")
	public void deleteSellerPic(Map<Object,Object> map);
	
	//从图片审核表中删除所有环境图
	@DataSource("joint")
	public void delSellerPicByType(Map<Object,Object> map);
	
	//插入商铺封面图
	@DataSource("joint")
	public int insertCoverPic(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: querySellerInfo
	* @Description:查询店铺资料 
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public Map<Object, Object> querySellerInfo(Integer sellerid);
	
	//查询商家id是否存在
	@DataSource("joint")
	public Map<Object,Object> querySellerId(Integer sellerid);
	
	//修改订单号
	@DataSource("joint")
	public void modifyOerderId(Map<Object,Object> map);
	
	//插入一条修改商铺信息记录
	@DataSource("joint")
	public int insertSellerInfoRecord(Map<Object,Object> map);
	
	//查询一条商铺修改记录
	@DataSource("joint")
	public Integer querySellerInfoRecord(Integer sellerid);
	
	//修改商铺折扣
	@DataSource("joint")
	public void modifySellerAgio(Map<Object,Object> map);
	
	//修改商铺折扣记录
	@DataSource("joint")
	public void modifyAgioReocrd(Map<Object,Object> map);
	

}
