package com.xmn.saas.service.activity.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.constants.ActivityConsts;
import com.xmn.saas.dao.activity.FreetryDao;
import com.xmn.saas.dao.activity.KillDao;
import com.xmn.saas.dao.activity.RoulleteDao;
import com.xmn.saas.entity.activity.Activity;
import com.xmn.saas.entity.activity.ActivityRecord;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.FcouspointsConver;
import com.xmn.saas.entity.activity.FcouspointsRecord;
import com.xmn.saas.entity.activity.FreetryRecord;
import com.xmn.saas.entity.activity.KillRecord;
import com.xmn.saas.entity.activity.RoulleteRecord;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.service.activity.AwardRelationService;
import com.xmn.saas.service.activity.FcouspointsService;
import com.xmn.saas.service.activity.FreetryRecordService;
import com.xmn.saas.service.activity.FreetryService;
import com.xmn.saas.service.activity.KillRecordService;
import com.xmn.saas.service.activity.KillService;
import com.xmn.saas.service.activity.RecordService;
import com.xmn.saas.service.activity.RoulleteRecordService;
import com.xmn.saas.service.activity.RoulleteService;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.base.UserService;
import com.xmn.saas.service.coupon.CouponService;
import com.xmn.saas.service.redpacket.RedpacketService;
import com.xmn.saas.utils.ActivityUtil;

@Service
public class RecordServiceImpl implements RecordService {
	
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);
	
	/**
	 * 注入各种活动业务层
	 */
	@Autowired
	private FreetryService freetryService;

	@Autowired
	private KillService killService;

	@Autowired
	private RoulleteService roulleteService;

	@Autowired
	private FreetryRecordService freetryRecordService;

	@Autowired
	private KillRecordService killRecordService;

	@Autowired
	private RoulleteRecordService roulleteRecordService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private AwardRelationService awardRelationService;

	@Autowired
	private CouponService couponService;

	@Autowired
	private RedpacketService redpacketService;

	@Autowired
	private GlobalConfig globalConfig;
	
	@Autowired
	private FreetryDao freetryDao;
	
	@Autowired
	private RoulleteDao roulleteDao;
	
	@Autowired
	private KillDao killDao;
	
	@Autowired
	private FcouspointsService fcouspointsService;

	@Override
	public List<Object> getList(SellerAccount sellerAccount, boolean beingorend,Integer pageSize, Integer pageIndex) {
		List<Object> list = new ArrayList<>();
		Integer sellerId = sellerAccount.getSellerid();
		list.addAll(freetryService.list(sellerId, beingorend,pageSize,pageIndex));
		list.addAll(killService.list(sellerId, beingorend,pageSize,pageIndex));
		list.addAll(roulleteService.list(sellerId, beingorend,pageSize,pageIndex));
		list.addAll(fcouspointsService.list(sellerId, beingorend, pageSize, pageIndex));
		return list;
	}

	@Override
	public Activity detailActivity(Integer activityId, Integer activityType, Integer sellerId) {
		if (activityType == 1) {
			return freetryService.detail(activityId, sellerId);
		} else if (activityType == 2) {
			return roulleteService.detail(activityId, sellerId);
		} else if (activityType == 3) {
			return killService.detail(activityId, sellerId);
		}else if(activityType==ActivityConsts.ACTIVITY_TYPE_FCOUSPONTS){
			return fcouspointsService.detail(activityId, sellerId);
		}
		return null;
	}

	@Override
	public List<ActivityRecord> listActivityRecord(Integer activityId, Integer activityType, Integer sellerId, Integer pageSize, Integer pageIndex) {
		List<ActivityRecord> activityRecords = new ArrayList<>();
		if (activityType == 1) {
			List<FreetryRecord> freetryRecords = freetryRecordService.list(sellerId, activityId,pageSize,pageIndex);
			activityRecords.addAll(freetryRecords);
		} else if (activityType == 2) {
			List<RoulleteRecord> roulleteRecords = roulleteRecordService.list(activityId, sellerId,pageSize,pageIndex);
			activityRecords.addAll(roulleteRecords);
		} else if (activityType == 3) {
			List<KillRecord> killRecords = killRecordService.list(activityId, sellerId,pageSize,pageIndex);
			activityRecords.addAll(killRecords);
		}
		List<ActivityRecord> dList=new ArrayList<ActivityRecord>();
		try {
			ResponseData responseData = null;
			UserService.Client client = ThriftBuilder.build(globalConfig.getThriftBusHost(),
					Integer.parseInt(globalConfig.getThriftBusPort()), "UserService", UserService.Client.class);
			ThriftBuilder.open();
				for (ActivityRecord activityRecord : activityRecords) {
						HashMap<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("uid", String.valueOf(activityRecord.getUid()));
						responseData = client.getUserMsg(paramMap);
						if (responseData != null) {
							if (responseData.state == 0) {
								Map<String, String> dataMap = responseData.resultMap;
								if (dataMap != null && !dataMap.isEmpty()) {
									String avatar=dataMap.get("avatar");
									String genussellerid = dataMap.get("genussellerid");
									if(StringUtils.isNotBlank(avatar)){
										activityRecord.setHead(globalConfig.getImageHost()+avatar);
									}
									if (StringUtils.isNotBlank(genussellerid)) {
										if (Integer.parseInt(genussellerid) == sellerId) {
											activityRecord.setVipName("绑定会员");
										} else {
											activityRecord.setVipName("消费会员");
										}
									}else{
										activityRecord.setVipName("消费会员");
									}
									String nname=StringUtils.isBlank(dataMap.get("nname"))?dataMap.get("phone"):dataMap.get("nname");
									activityRecord.setUsrName(nname);
								}
							}
						}
						dList.add(activityRecord);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ThriftBuilder.close();
			}
			return dList;
		}
	
	@Override
	public List<ActivityRecord> getUserMsg(List<? extends ActivityRecord> list,Integer sellerId){
		List<ActivityRecord> dList=new ArrayList<ActivityRecord>();
		try {
			ResponseData responseData = null;
			UserService.Client client = ThriftBuilder.build(globalConfig.getThriftBusHost(),
					Integer.parseInt(globalConfig.getThriftBusPort()), "UserService", UserService.Client.class);
			ThriftBuilder.open();
				for (ActivityRecord activityRecord : list) {
						HashMap<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("uid", String.valueOf(activityRecord.getUid()));
						responseData = client.getUserMsg(paramMap);
						if (responseData != null) {
							if (responseData.state == 0) {
								Map<String, String> dataMap = responseData.resultMap;
								if (dataMap != null && !dataMap.isEmpty()) {
									String avatar=dataMap.get("avatar");
									String genussellerid = dataMap.get("genussellerid");
									if(StringUtils.isNotBlank(avatar)){
										activityRecord.setHead(globalConfig.getImageHost()+avatar);
									}
									if (StringUtils.isNotBlank(genussellerid)) {
										if (Integer.parseInt(genussellerid) == sellerId) {
											activityRecord.setVipName("绑定会员");
										} else {
											activityRecord.setVipName("消费会员");
										}
									}else{
										activityRecord.setVipName("消费会员");
									}
									String nname=StringUtils.isBlank(dataMap.get("nname"))?dataMap.get("phone"):dataMap.get("nname");
									activityRecord.setUsrName(nname);
								}
							}
						}
						dList.add(activityRecord);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ThriftBuilder.close();
			}
			return dList;
		
	}
	
	
	@Override
	public ActivityRecord getUserMsg(ActivityRecord activityRecord,Integer sellerId){
		try {
			ResponseData responseData = null;
			UserService.Client client = ThriftBuilder.build(globalConfig.getThriftBusHost(),
					Integer.parseInt(globalConfig.getThriftBusPort()), "UserService", UserService.Client.class);
			ThriftBuilder.open();
						HashMap<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("uid", String.valueOf(activityRecord.getUid()));
						responseData = client.getUserMsg(paramMap);
						if (responseData != null) {
							if (responseData.state == 0) {
								Map<String, String> dataMap = responseData.resultMap;
								if (dataMap != null && !dataMap.isEmpty()) {
									String avatar=dataMap.get("avatar");
									String genussellerid = dataMap.get("genussellerid");
									if(StringUtils.isNotBlank(avatar)){
										activityRecord.setHead(globalConfig.getImageHost()+avatar);
									}
									if (StringUtils.isNotBlank(genussellerid)) {
										if (Integer.parseInt(genussellerid) == sellerId) {
											activityRecord.setVipName("绑定会员");
										} else {
											activityRecord.setVipName("消费会员");
										}
										activityRecord.setAttachTime(dataMap.get("attachTime"));
									}else{
										activityRecord.setVipName("消费会员");
									}
									String nname=StringUtils.isBlank(dataMap.get("nname"))?dataMap.get("phone"):dataMap.get("nname");
									activityRecord.setUsrName(nname);
								}
							}
						}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ThriftBuilder.close();
			}
			return activityRecord;
		
	}
	
	@Override
	public ActivityRecord detailRecord(Integer activityId, Integer activityType, Integer recordId,Integer sellerId) {
		ActivityRecord activityRecord = null;
		Integer uid = null;
		if (activityType == 1) { // 免尝
			FreetryRecord freetryRecord = freetryRecordService.detail(recordId, activityId);
			uid = freetryRecord.getUid();
			activityRecord = freetryRecord;
		} else if (activityType == 2) { // 大转盘
			RoulleteRecord roulleteRecord = roulleteRecordService.detail(recordId, activityId);
			uid = roulleteRecord.getUid();
			activityRecord = roulleteRecord;
		} else if (activityType == 3) { // 秒杀
			KillRecord killRecord = killRecordService.detail(recordId, activityId);
			uid = killRecord.getUid();
			activityRecord = killRecord;
		}
		if (activityRecord != null && uid != null) {
			ResponseData responseData = null;
			try {
				UserService.Client client = ThriftBuilder.build(globalConfig.getThriftBusHost(),
						Integer.parseInt(globalConfig.getThriftBusPort()), "UserService", UserService.Client.class);

				ThriftBuilder.open();
				HashMap<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("uid", String.valueOf(uid));
				responseData = client.getUserMsg(paramMap);
				if (responseData != null) {
					if (responseData.state == 0) {
						Map<String, String> dataMap = responseData.resultMap;
						if (dataMap != null && !dataMap.isEmpty()) {
							String genussellerid = dataMap.get("genussellerid");
							String avatar=dataMap.get("avatar");
							if(StringUtils.isNotBlank(avatar)){
								activityRecord.setHead(globalConfig.getImageHost()+avatar);
							}
							if (StringUtils.isNotBlank(genussellerid)) {
								if (Integer.parseInt(genussellerid) == sellerId) {
									activityRecord.setVipName("绑定会员");
								} else {
									activityRecord.setVipName("消费会员");
								}
							}else{
								activityRecord.setVipName("消费会员");
							}
						}
					}else{
						activityRecord.setVipName("消费会员");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ThriftBuilder.close();
			}
		}
		return activityRecord;

	}

	@Override
	public void saveTempActivity(Activity activity, Integer sellerId) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(activity);
			logger.info("【临时活动创建添加到redis中】"+sellerId+"json="+json);
			 Calendar calendar = Calendar.getInstance();
		        // 从配置文件中获取Redis缓存时间
		        calendar.add(Calendar.MINUTE,30);	//在redis保存30分钟
		        // 存入Redis
		        redisService.setString("activity" + sellerId ,json,calendar.getTime());
			//redisService.setString("activity" + sellerId, json, new Date());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.info("【临时活动创建添加到redis中异常】");
			logger.error("【临时活动创建添加到redis中异常】"+sellerId,e);
			throw new RuntimeException();
		}
	}

	@Override
	public <T> T giveTempActivity(Integer sellerId, Class<? extends Activity> activityClass) {
		String json = redisService.getString("activity" + sellerId);
		logger.info("【从redis中取回临时活动】"+sellerId+json);
		if (StringUtils.isNotBlank(json)) {
			redisService.deleteKey("activity" + sellerId);
			ObjectMapper mapper = new ObjectMapper();
			try {
				return (T) mapper.readValue(json, activityClass);
			} catch (Exception e) {
				logger.info("[取回活动时出现类型转换异常]");
				//e.printStackTrace();
				//throw new RuntimeException();
			}
		}
		return null;
	}

	@Override
	public Integer hasActivity(Integer activityType, Integer sellerId) {
		switch (activityType) {
		case 1:
			return  freetryDao.CountBeingActivity(sellerId);
		case 2:
			return  roulleteDao.CountBeingActivity(sellerId);
		case 3:
			return  killDao.CountBeingActivity(sellerId);
		case 4:
			return  fcouspointsService.CountBeingActivity(sellerId);
		default:
			break;
		}
		return -1;
	}

	@Override
	public Integer countResidue(Activity activity) {
		Integer countRecord=0;	//领取总数
		Integer awardSum=awardRelationService.sumAward(activity.getId(),activity.getActivityType());	//活动奖品总数量
		switch (activity.getActivityType()) {
		case 1:	//免尝
			countRecord=freetryRecordService.countRecord(activity.getId());
			break;
		case 2:	//大转盘
			countRecord=roulleteRecordService.countRecord(activity.getId());
			break;
		case 3:	//秒杀
			countRecord=killRecordService.countRecord(activity.getId());
			break;
		default:
			return 0;
		}
		return awardSum-countRecord;
	}

	@Override
	public void endActivity(Integer activityId, Integer activityType,Integer sellerId) {
		switch (activityType) {
		case 1:	//免尝
			freetryService.endActivity(activityId,sellerId);
			break;
		case 2:	//大转盘
			roulleteService.endActivity(activityId,sellerId);
			break;
		case 3:	//秒杀
			killService.endActivity(activityId,sellerId);
			break;
		case 4:	//集点
			fcouspointsService.endActivity(activityId,sellerId);
			break;
		default:
			throw new RuntimeException("活动类型有误");
		}
	}

}
