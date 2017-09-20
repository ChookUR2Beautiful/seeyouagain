/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.live_anchor.dao.TExperienceofficerConfigDao;
import com.xmniao.xmn.core.live_anchor.dao.TExperienceofficerOrderDao;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerConfig;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerOrder;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerUser;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.exception.FailureException;
import com.xmniao.xmn.core.thrift.service.ResponseData;
import com.xmniao.xmn.core.thrift.service.experienceofficer.ExperienceOfficerOrderService;
import com.xmniao.xmn.core.thrift.service.experienceofficer.ExperiencecardService;
import com.xmniao.xmn.core.thrift.service.experienceofficer.ResponseSubList;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveExperienceofficerActivityService
 * 
 * 类描述：会员美食体验卡管理Service
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年5月8日 下午3:22:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class LiveExperienceofficerUserService {

	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	private TExperienceofficerConfigDao experienceofficerConfigDao;
	
	@Autowired
	private TExperienceofficerOrderDao experienceofficerOrderDao; 
	
	@Resource(name = "experienceofficerOrderServiceClient")
	private ThriftClientProxy experienceofficerOrderServiceClient;

	@Resource(name = "experiencecardServiceClient")	
	private ThriftClientProxy experiencecardServiceClient;
	
	private int status=0;
	/**
	 * 
	 * 方法描述：体验卡列表
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月15日上午10:32:51 <br/>
	 * @return
	 * @throws TException 
	 * @throws FailureException 
	 * @throws ParseException 
	 */
	public Pageable<TExperienceofficerUser> getList(TExperienceofficerUser experienceofficerUser) throws FailureException, TException, ParseException{
		
		Pageable<TExperienceofficerUser> pageable = new Pageable<>(experienceofficerUser);
		List<TExperienceofficerUser>  resultList = new ArrayList<>();

		if(StringUtils.isNotBlank(experienceofficerUser.getPhone())){
			//跨库查询，若针对用户模糊查询，增加实现复杂度
			TExperienceofficerUser user = new TExperienceofficerUser();
			user.setPhone(experienceofficerUser.getPhone());
			user.setNname(experienceofficerUser.getNname());
			TExperienceofficerUser user2 = experienceofficerOrderDao.getExperienceofficerUser(experienceofficerUser);
			if(user2==null){
				pageable.setTotal((long)0);
				pageable.setContent(resultList);
				return pageable;
			}
			ExperiencecardService.Client client = (ExperiencecardService.Client)experiencecardServiceClient.getClient();
			Map<String,String> paramMap = new HashMap<>();
			paramMap.put("uid", user2.getUid()+"");
			ResponseData  responseData = client.getExperiencecard(paramMap);
			
			Map<String,String> map = responseData.getResultMap();
			TExperienceofficerUser officerUser = new TExperienceofficerUser();
			officerUser.setCardId(Integer.parseInt(map.get("id")));
			officerUser.setUnused(Integer.parseInt(map.get("stock")));
			officerUser.setUsed(Integer.parseInt(map.get("used")));
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dueDate = dateFormat1.parse(map.get("dueDate"));
			officerUser.setLimitTime(dueDate);
			officerUser.setStatus(Integer.parseInt(map.get("status")));
			officerUser.setUid(Integer.parseInt(map.get("uid")));
			
			officerUser.setPhone(user2.getPhone());
			officerUser.setNname(user2.getNname());
			officerUser.setBuyTime(user2.getBuyTime());
			
			resultList.add(officerUser);
			pageable.setTotal((long)1);
			pageable.setContent(resultList);
		}else{
			ExperiencecardService.Client client = (ExperiencecardService.Client)experiencecardServiceClient.getClient();
			Map<String,String> paramMap = new HashMap<>();
			if(experienceofficerUser.getUid()!=null){
				paramMap.put("uid", experienceofficerUser.getUid()+"");
			}
			Integer getStatus = experienceofficerUser.getStatus();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(getStatus!=null){
				switch (getStatus) {
				case 0://使用中
					paramMap.put("status", experienceofficerUser.getStatus()+"");
					paramMap.put("startDueDate", df.format(new Date()) );	//未过期的
					paramMap.put("endDueDate", "2099-01-01 00:00:00" );
					break;
				case 1://锁定
					paramMap.put("status", experienceofficerUser.getStatus()+"");
					break;
				case 2://已停用
					paramMap.put("status", "0");	//正常的
					paramMap.put("startDueDate", "2017-01-01 00:00:00" );
					paramMap.put("endDueDate", df.format(new Date()) );
					break;
				default:
					break;
				}
				
			}

			paramMap.put("pageNum", experienceofficerUser.getPage()+"");
			paramMap.put("pageSize", experienceofficerUser.getLimit()+"");
			
			ResponseSubList responseSubList = client.queryExperiencerdList(paramMap);
			if(responseSubList.getCountNum()!=0){
				for(Map<String,String> map:responseSubList.getSubList()){
					
					TExperienceofficerUser officerUser = new TExperienceofficerUser();
					officerUser.setCardId(Integer.parseInt(map.get("id")));
					officerUser.setUnused(Integer.parseInt(map.get("stock")));
					officerUser.setUsed(Integer.parseInt(map.get("used")));
					DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
					Date dueDate = dateFormat1.parse(map.get("dueDate"));
					officerUser.setLimitTime(dueDate);
					officerUser.setStatus(Integer.parseInt(map.get("status")));
					officerUser.setUid(Integer.parseInt(map.get("uid")));
					resultList.add(officerUser);
				}
				
				StringBuffer uids = new StringBuffer();
				for(TExperienceofficerUser sUser:resultList){
					uids.append(sUser.getUid()).append(",");
				}
				TExperienceofficerUser tUser = new TExperienceofficerUser();
				tUser.setUids(uids.substring(0, uids.length()-1));
				List<TExperienceofficerUser> orderList = experienceofficerOrderDao.getExperienceofficerUserList(tUser);
				for(TExperienceofficerUser sUser:resultList){
					for(TExperienceofficerUser ouser:orderList){
						if(ouser.getUid().equals(sUser.getUid())){
							sUser.setPhone(ouser.getPhone());
							sUser.setNname(ouser.getNname());
							sUser.setBuyTime(ouser.getBuyTime());
							break;
						}
					}
				}
				pageable.setTotal((long)responseSubList.getCountNum());
				pageable.setContent(resultList);
			}else{
				pageable.setTotal((long)responseSubList.getCountNum());
				pageable.setContent(resultList);
			}
		}
		return pageable;
	}

	/**
	 * 
	 * 方法描述：使用记录
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月15日上午10:32:54 <br/>
	 * @return
	 */
	public List<TExperienceofficerUser> getUsedList(){
		
		return null;
	}
	
	/**
	 * 
	 * 方法描述：体验设定
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月15日上午10:32:54 <br/>
	 * @return
	 */
	public void experienceSetting(TExperienceofficerConfig config){
		TExperienceofficerConfig settingConfig = experienceofficerConfigDao.getConfig(config);
		if(settingConfig==null){
			experienceofficerConfigDao.add(config);
		}else{
			experienceofficerConfigDao.update(config);
		}
	}
	
	/**
	 * 
	 * 方法描述：体验设定
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月15日上午10:32:54 <br/>
	 * @return
	 */
	public TExperienceofficerConfig getExperienceSetting(TExperienceofficerConfig config){
		return experienceofficerConfigDao.getConfig(config);
	}
	
	/**
	 * 
	 * 方法描述：添加会员体验卡
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月15日上午10:32:54 <br/>
	 * @return
	 * @throws Exception 
	 */
	public void addExperienceCard(TExperienceofficerOrder experienceofficerOrder) throws Exception{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String orderNo = "014"+sdf.format(date)+((long)(Math.random()*9000)+1000);
		experienceofficerOrder.setIsFree(1);
		experienceofficerOrder.setOrderNo(orderNo);
		experienceofficerOrder.setPayState(0);
		experienceofficerOrder.setCreateTime(date);
		experienceofficerOrder.setDescription("系统发放体验卡");
		experienceofficerOrderDao.add(experienceofficerOrder);
		//调用业务服务
		ExperienceOfficerOrderService.Client client = (ExperienceOfficerOrderService.Client) (experienceofficerOrderServiceClient.getClient());
		Map<String,String> uMap = new HashMap<>();
		uMap.put("orderNo", experienceofficerOrder.getOrderNo());
		uMap.put("uid", experienceofficerOrder.getUid().toString());
		uMap.put("amount", "0");
		uMap.put("payType", "");
		uMap.put("payid", "");
		uMap.put("liveCoin", "0");
		uMap.put("walletAmount", "0");
		uMap.put("samount", "0");
		uMap.put("payState", "1");
		ResponseData  responseData = client.updateExperienceOfficerOrder(uMap);
		if(responseData.getState()!=0){
			throw new Exception(responseData.getMsg());
		}
	}
	
	public ResponseData enableExperienceCard(TExperienceofficerUser experienceofficerUser) throws FailureException, TException{
		ExperiencecardService.Client client = (ExperiencecardService.Client)experiencecardServiceClient.getClient();
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("id", experienceofficerUser.getCardId().toString());
		paramMap.put("status", experienceofficerUser.getStatus().toString());
		return   client.updateExperiencecardStatus(paramMap);
	}

	public List<Map<String,Object>> getUrsList(Map<String,Object> map){
		return experienceofficerOrderDao.getUrs(map);
	}
	
	public static void main(String[] args) {
		int a=0;int b=0;int c=0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		for(int i=0;i<100;i++){
			Date date = new Date();
			
			String orderNo = "014"+sdf.format(date)+((long)(Math.random()*9000)+1000);
			System.out.println(orderNo);
		}
		System.out.println("over");
	}
}
