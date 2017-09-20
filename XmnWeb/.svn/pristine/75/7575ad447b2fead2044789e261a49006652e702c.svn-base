package com.xmniao.xmn.core.businessman.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerInternalInfoDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerInternalInfo;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.http.util.AppMessageUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.ResultUtil;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerApplyService
 * 
 * 类描述： 商户信息修改申请
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时50分01秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class SellerInternalInfoService extends BaseService<TSellerInternalInfo> {

	@Autowired
	private SellerInternalInfoDao sellerInternalInfoDao;

	/**
	 * 商家service
	 */
	@Autowired
	private SellerService sellerService;
	
	private static final String msgUrl = PropertiesUtil.readValue("http.msg.url");

	@Override
	protected BaseDao<TSellerInternalInfo> getBaseDao() {
		return sellerInternalInfoDao;
	}

	/**
	 * 添加和修改商家站内消息
	 * 
	 * @param sellerInternalInfo
	 * @return
	 * @throws Exception
	 */
	/*@Transactional(propagation = Propagation.REQUIRED)
	public void addOrUpdateMsg(TSellerInternalInfo sellerInternalInfo, HttpServletRequest request) throws Exception {
		//1、添加商圈基本信息 2、添加推送对象信息
		if (null != sellerInternalInfo) {
			// 获取省市区商圈id
			TSeller seller = new TSeller();
			seller.setProvince(sellerInternalInfo.getProvince());
			seller.setArea(sellerInternalInfo.getArea());
			seller.setCity(sellerInternalInfo.getCity());
			
			if(null != sellerInternalInfo.getZoneid() && "" != sellerInternalInfo.getZoneid()){
				seller.setZoneid(new Integer(sellerInternalInfo.getZoneid()));
			}
			seller.setLimit(-1);
			// 查询已签约并且已经上线的商家根据省市区商圈查询推送对象
			List<TSeller> list = sellerService.getSellerMsg(seller);
			StringBuilder sendObject = new StringBuilder();
			// 将获取的推送对象转换为指定格式
			if (null != list && !list.isEmpty()) {
				sendObject.append("[");
				for (TSeller s:list) {
					sendObject.append("{\"uid\":\"").append(s.getAid()).append("\",\"iostoken\":\"").append(s.getIostoken()==null ? "" :s.getIostoken()).append("\"},");
				}
				sendObject.replace(sendObject.length()-1, sendObject.length(), "]");
				
				sellerInternalInfo.setSendObject(sendObject.toString());
			}

			// 获取创建人和创建时间
			if (null != request.getSession()) {
				TUser user = ResultUtil.getCurrentUser(request);
				sellerInternalInfo.setCreator(user.getName());
				sellerInternalInfo.setUpdator(user.getName());
			}
			Date d = new Date();
			sellerInternalInfo.setDateCreated(d);
			sellerInternalInfo.setDateUpdated(d);
			// 待发送
			sellerInternalInfo.setDataState(0);
			//设置发送总人数
			if(null != list && !list.isEmpty()){
				sellerInternalInfo.setSendNum(list.size());
			}
			// 如果主键id不为空则表示添加
			if (null != sellerInternalInfo.getMsgId()) {
				sellerInternalInfoDao.update(sellerInternalInfo);
				String[] s={"站内消息编号",sellerInternalInfo.getMsgId().toString(),"修改","修改"};
				fireLoginEvent(s);
			} else {
				sellerInternalInfoDao.add(sellerInternalInfo);
				String[] s={"站内消息",sellerInternalInfo.getTitle(),"新增"};
				fireLoginEvent(s);
			}
			
			Long msgId = sellerInternalInfoDao.getMaxId();
			
			//2 2.1 组装数据 2.2 添加关联关系
			beachRealationSeller(sellerInternalInfo, list,msgId);
			
			//发送消息
			sendInfo(sellerInternalInfo, sendObject, msgId);
		}
	}*/
	
	
	/**
	 * 添加或更新站内消息
	 * 步骤：1、添加商圈基本信息 2、添加推送对象信息
	 * @param sellerInternalInfo
	 * @param request
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addOrUpdateMsg(TSellerInternalInfo sellerInternalInfo, HttpServletRequest request) throws Exception {
		boolean flag = checkAddOrUpdate(sellerInternalInfo);
		try{
			List<TSeller> list = getListTSeller(sellerInternalInfo);
			StringBuilder sendObject = getTSellerInternalInfo(sellerInternalInfo, list, request);
			Integer msgId = sellerInternalInfo.getMsgId();
			if (!flag) {  //更新
				sellerInternalInfoDao.update(sellerInternalInfo);
			} else {  //添加
				sellerInternalInfoDao.addReturnId(sellerInternalInfo);
				msgId = sellerInternalInfo.getMsgId();
				sellerInternalInfo.setMsgId(null);
			}
			buildDataAndRelation(sellerInternalInfo, list, sendObject, msgId.longValue());
		}catch(Exception e){
			throw getExceptionObject(flag, sellerInternalInfo, request, e);
		}
	}
	
	/**
	 * 判断是添加还是修改
	 * true 表示添加  false代表修改
	 * @param sellerInternalInfo
	 * @return
	 */
	private boolean checkAddOrUpdate(TSellerInternalInfo sellerInternalInfo){
		return null == sellerInternalInfo.getMsgId() ? true : false;  
	}
	
	/**
	 * 获取异常对象
	 * @param joint
	 * @param e
	 * @return
	 */
	private ApplicationException getExceptionObject(boolean flag, TSellerInternalInfo sellerInternalInfo, HttpServletRequest request, Exception e){
		if(!flag){  //修改
			return new ApplicationException("修改站内消息", e, new Object[]{sellerInternalInfo, request}, new String[]{"站内消息编号", sellerInternalInfo.getMsgId().toString(), "修改", "修改"});
		}else{ //添加
			return new ApplicationException("添加站内消息", e, new Object[]{sellerInternalInfo, request}, new String[]{"站内消息标题",sellerInternalInfo.getTitle(), "添加", "添加"});
		}
	}	
	
	private void buildDataAndRelation(TSellerInternalInfo sellerInternalInfo, List<TSeller> list, StringBuilder sendObject, Long msgId){
		try{
		//	Long msgId = sellerInternalInfoDao.getMaxId();
			//1 组装数据 2 添加关联关系
			beachRealationSeller(sellerInternalInfo, list, msgId);
			//发送消息
			sendInfo(sellerInternalInfo, sendObject, msgId);
		}catch(Exception e){
			throw new ApplicationException("消息发送异常", e , new Object[]{sellerInternalInfo, sendObject, list});
		}
	}
	
	/**
	 * 设置 TSellerInternalInfo 对象的其他属性值并返回拼装的消息发送字符串 
	 * @param sellerInternalInfo
	 * @param list
	 * @param request
	 * @return
	 */
	private StringBuilder getTSellerInternalInfo(TSellerInternalInfo sellerInternalInfo, List<TSeller> list, HttpServletRequest request){
		StringBuilder sendObject = new StringBuilder();
		// 将获取的推送对象转换为指定格式
		if (null != list && !list.isEmpty()) {
			sendObject.append("[");
			for (TSeller s:list) {
				sendObject.append("{\"uid\":\"").append(s.getAid()).append("\",\"iostoken\":\"").append(s.getIostoken()==null ? "" :s.getIostoken()).append("\"},");
			}
			sendObject.replace(sendObject.length()-1, sendObject.length(), "]");
			
			sellerInternalInfo.setSendObject(sendObject.toString());
		}
		// 获取创建人和创建时间
		if (null != request.getSession()) {
			String userName = ResultUtil.getCurrentUser(request).getUsername();
			sellerInternalInfo.setCreator(userName);
			sellerInternalInfo.setUpdator(userName);
		}
		Date d = new Date();
		sellerInternalInfo.setDateCreated(d);
		sellerInternalInfo.setDateUpdated(d);
		// 待发送
		sellerInternalInfo.setDataState(0);
		//设置发送总人数
		if(null != list && !list.isEmpty()){
			sellerInternalInfo.setSendNum(list.size());
		}
		return sendObject;
	}
	
	/**
	 * 查询已签约并且已经上线的商家根据省市区商圈查询推送对象
	 * @param sellerInternalInfo
	 * @return
	 */
	private List<TSeller> getListTSeller(TSellerInternalInfo sellerInternalInfo){
		return sellerService.getSellerMsg(getTSeller(sellerInternalInfo));
	}
	
	/**
	 * 封装 TSeller 对象返回
	 * @param sellerInternalInfo
	 * @return
	 */
	private TSeller getTSeller(TSellerInternalInfo sellerInternalInfo){
		// 获取省市区和商圈id
		TSeller seller = new TSeller();
		seller.setProvince(sellerInternalInfo.getProvince());
		seller.setArea(sellerInternalInfo.getArea());
		seller.setCity(sellerInternalInfo.getCity());
		if(null != sellerInternalInfo.getZoneid() && "" != sellerInternalInfo.getZoneid()){
			seller.setZoneid(new Integer(sellerInternalInfo.getZoneid()));
		}
		seller.setLimit(-1);
		return seller;
	}
		
	/**
	 * 发送消息
	 * @param sellerInternalInfo
	 * @param sendObject
	 * @param msgId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void sendInfo(TSellerInternalInfo sellerInternalInfo, StringBuilder sendObject, Long msgId) {
		try {
			JSONObject res = AppMessageUtil.pushMessageToApp( getParam(sellerInternalInfo, sendObject, msgId));
			//发送成功后更新发送状态
			if(res != null){
				if(res.getBooleanValue("status")){
					TSellerInternalInfo updateInfo = getTSellerInternalInfo(msgId);
					sellerInternalInfoDao.update(updateInfo);
					recordLog(true, updateInfo.getMsgId().toString());
				}
			}
		} catch (Exception e) {
			recordLog(false, msgId.toString());
			throw new ApplicationException("消息发送异常", e , new Object[]{sellerInternalInfo, sendObject, msgId});
		}
	}
	
	/**
	 * 日志记录
	 * @param flag
	 * @param msgId
	 */
	private void recordLog(boolean flag, String msgId){
		String[] s={"站内类消息编号",msgId,"更新消息状态","更新消息状态"};
		if(flag){
			fireLoginEvent(s);
		}else{
			fireLoginEvent(s,0);
		}
	}

	private TSellerInternalInfo getTSellerInternalInfo(Long msgId){
		TSellerInternalInfo updateInfo = new TSellerInternalInfo();
		updateInfo.setMsgId(msgId.intValue());
		updateInfo.setDateUpdated(new Date());
		//已发送
		updateInfo.setDataState(1);
		return updateInfo;
	}
	
	private Map<String, String> getParam(TSellerInternalInfo sellerInternalInfo, StringBuilder sendObject, Long msgId){
		SimpleDateFormat fromt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, String> param = new HashMap<String, String>();
		param.put("tid", msgId.toString());
		param.put("uid", sendObject.toString());
		param.put("isAll", "false");
		param.put("title", sellerInternalInfo.getTitle());
		param.put("content", sellerInternalInfo.getContent());
		param.put("usertype", "3");
		param.put("client", "2" );//商户客户端
		param.put("iosaction", "{\"action\" : \"\",\"alert\" : \""+sellerInternalInfo.getTitle()+"\",\"badge\" : 1,\"sound\" : \"default\",\"type\" : 100,\"account \" : 1}");
		if(null != sellerInternalInfo.getDateSend()){
			param.put("tdate", fromt.format(sellerInternalInfo.getDateSend()));
		}
		if(null != sellerInternalInfo.getDateEndSend()){
			param.put("edate", fromt.format(sellerInternalInfo.getDateEndSend()));
		}
		return param;
	}
	
	/**
	 * 关联商家
	 * 
	 * @param sellerInternalInfo
	 * @param list
	 * @param msgId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void beachRealationSeller(TSellerInternalInfo sellerInternalInfo, List<TSeller> list, Long msgId) {
		if(null != list && !list.isEmpty()){
			String sellerInternalInfoId = msgId.toString();
			Integer msgIdTemp = sellerInternalInfo.getMsgId();
			if (!checkDeleteOrRelate(msgIdTemp)) {
				sellerInternalInfoDao.deleteRelation(msgIdTemp.longValue());
				recordLog(sellerInternalInfoId, "DELETE");			
			} else {
				sellerInternalInfo.setMsgId(msgId.intValue());
			}
			sellerInternalInfoDao.beachRelationSeller(getTSellerInternalInfoList(sellerInternalInfo, list));		
			recordLog(sellerInternalInfoId, null);		
		}
	}
	
	private void recordLog(String sellerInternalInfoId, String flag){
		String[] s = null;
		if(null != flag){
			s = new String[]{"站内类消息编号",sellerInternalInfoId,"关联商家删除","删除"};
		}else{
			s = new String[]{"站内类消息编号",sellerInternalInfoId,"关联商家","关联商家"};
		}
		fireLoginEvent(s);
	}
	
	private boolean checkDeleteOrRelate(Integer msgId){
		return null == msgId ? true : false;
	}
	
	/**
	 * 将获取的推送对象转换为指定格式
	 * @param sellerInternalInfo
	 * @param list
	 * @return
	 */
	private List<TSellerInternalInfo> getTSellerInternalInfoList(TSellerInternalInfo sellerInternalInfo, List<TSeller> list){
		List<TSellerInternalInfo> infoList = new ArrayList<TSellerInternalInfo>();
		StringBuilder sb = new StringBuilder();			
		Date date = new Date();
		TSellerInternalInfo RInfo = null;
		for (TSeller tSeller : list) {
			RInfo = new TSellerInternalInfo();
			RInfo.setRsId(sellerInternalInfo.getRsId());
			RInfo.setCreator(sellerInternalInfo.getCreator());
			RInfo.setUpdator(sellerInternalInfo.getUpdator());
			RInfo.setDateCreated(date);
			RInfo.setDateUpdated(date);
			RInfo.setSellerid(tSeller.getSellerid());
			sb.append(tSeller.getSellerid());
			sb.append(",");
			RInfo.setMsgId(sellerInternalInfo.getMsgId());
			RInfo.setDataState(sellerInternalInfo.getDataState());
			infoList.add(RInfo);
		}
		return infoList;
	}
}
