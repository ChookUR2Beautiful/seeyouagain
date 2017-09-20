package com.xmniao.xmn.core.common.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_cooperation.dao.StaffDao;
import com.xmniao.xmn.core.business_cooperation.entity.TStaff;
import com.xmniao.xmn.core.businessman.dao.SellerAccountDao;
import com.xmniao.xmn.core.businessman.entity.TSellerAccount;
import com.xmniao.xmn.core.common.dao.AppPushDao;
import com.xmniao.xmn.core.common.entity.TAppPush;
import com.xmniao.xmn.core.common.util.commonConstants;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.HttpUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StringUtils;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AppPushService
 * 
 * 类描述： 用户
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月12日17时25分33秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class AppPushService extends BaseService<TAppPush> {

	@Autowired
	private AppPushDao appPushDao;
	
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private SellerAccountDao sellerAccountDao;
	
	
	
	private static final String msgUrl = PropertiesUtil.readValue("http.msg.url");
	
	@Override
	protected BaseDao getBaseDao() {
		return appPushDao;
	}
	/*
	 *添加消息推送对象 
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(TAppPush appPush) {
		StringBuffer objectStr = new StringBuffer();
		StringBuffer uid = new StringBuffer("0");
		String isAll = "true";
		StringBuffer iosaction = new StringBuffer();
		appPush.setSdate(new Date());
		appPush.setStatus(1);
		iosaction.append("{\"action\":\"").append(appPush.getAction()).append("\",\"alert\":\"").append(appPush.getTitle()).append("\",\"badge\":1,\"sound\":\"default\",\"type\":100}");
		if(appPush.getSendObjectType() == 0){
			appPush.setSendType(0);
			appPush.setObject(null);
			appPush.setProvince(null);
			appPush.setCity(null);
		}else if(appPush.getSendObjectType() == 1){
			appPush.setSendType(2);
			appPush.setProvince(null);
			appPush.setCity(null);
			isAll = "false";
			uid = new StringBuffer("[");
			objectStr = new StringBuffer("{object:[");
			//用户消息推送管理
			if(appPush.getClient() == commonConstants.PUSH_USER_TERMINAL){
				String[] uStr = null;
				try {
					uStr = StringUtils.paresToArray(URLDecoder.decode(appPush.getObject(), "UTF-8"), "!@#");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					uStr = StringUtils.paresToArray(appPush.getObject(), "!@#");
				}
				//String[] uStr = appPush.getObject().split("!@#");
				for(String us : uStr){
					String[] uu = us.split(":;");
					uid.append("{\"uid\":\"").append(uu[0]).append("\",\"iostoken\":\"").append(uu[1]).append("\"},");
					objectStr.append("{\"uid\":\"").append(uu[0]).append("\",\"phone\":\"").append(uu[2]).append("\",\"nickname\":\"").append(uu.length == 4 ? uu[3] : "").append("\"},");
				}
			//商家消息推送管理
			}else if(appPush.getClient() == commonConstants.PUSH_BUSINESSMAN){
				List<TSellerAccount> accounts = sellerAccountDao.getAdminsBySeller(appPush.getObject().split(","));
				for(TSellerAccount account : accounts){
					uid.append("{\"uid\":\"").append(account.getAid()).append("\",\"iostoken\":\"").append(account.getIostoken() == null ? "" : account.getIostoken()).append("\"},");
					objectStr.append("{\"uid\":\"").append(account.getAid()).append("\",\"phone\":\"").append(account.getPhone()).append("\",\"nickname\":\"").append(account.getNname()).append("\"},");
				}
			//合作商消息推送管理
			}else if(appPush.getClient() == commonConstants.PUSH_BUSINESS_COPERATION){
				List<TStaff> staffs = staffDao.getAdminsByJoint(appPush.getObject().split(","));
				for(TStaff staff : staffs){
					uid.append("{\"uid\":\"").append(staff.getStaffid()).append("\",\"iostoken\":\"").append("").append("\"},");
					objectStr.append("{\"uid\":\"").append(staff.getStaffid()).append("\",\"phone\":\"").append(staff.getPhoneid()).append("\",\"nickname\":\"").append(staff.getNickname()).append("\"},");
				}
			}
			uid.replace(uid.length() - 1, uid.length(), "");
			objectStr.replace(objectStr.length() - 1, objectStr.length(), "");
			uid.append("]");
			objectStr.append("]}");
			log.info(uid);
			log.info(objectStr);
			appPush.setObject(objectStr.toString());
		}else if(appPush.getSendObjectType() == 2){
			appPush.setObject(null);
			appPush.setSendType(1);
		}
		super.add(appPush);
		Long tid = appPushDao.getMaxId();
		Map<String, String> param = new HashMap<String, String>();
		param.put("tid", tid.toString());
		param.put("uid", uid.toString());
		param.put("isAll", isAll);
		param.put("title", appPush.getTitle());
		param.put("content", appPush.getContent());
		param.put("type", appPush.getType().toString());
		param.put("action", appPush.getAction());
		param.put("remind", appPush.getRemind().toString());
		param.put("client", appPush.getClient().toString());
		param.put("iosaction", iosaction.toString());
		param.put("usertype", appPush.getClient() == 3 ? "4" : appPush.getClient() == 2 ? "3" : "1");
		param.put("iosaction", "{\"action\" : \"\",\"alert\" : \""+appPush.getTitle()+"\",\"badge\" : 1,\"sound\" : \"default\",\"type\" : 100,\"account \" : 1}");
		if(appPush.getEdate() != null){
			param.put("edate", DateUtil.smartFormat(appPush.getEdate()));
		}
		if(appPush.getTdate() != null){
			param.put("tdate", DateUtil.smartFormat(appPush.getTdate()));
		}
		if(appPush.getCity()!=null){
			param.put("regcity",appPush.getCity());
		}
		log.info("推送URL>>>>>" + msgUrl + "/push/addMsg.html");
		log.info("推送参数>>>>>" + param.toString());
		try {
			JSONObject res = HttpUtil.getInstance().postForObject(msgUrl + "/push/addMsg.html", param);
			if(res != null){
				if(res.getBooleanValue("status")){
					appPush.setTid(tid.intValue());
//					if(appPush.getTdate() == null){
//						appPush.setTdate(new Date());
//					}
					appPush.setStatus(1);
				}else{
					appPush.setTid(tid.intValue());
					appPush.setStatus(0);
				}
				super.update(appPush);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
