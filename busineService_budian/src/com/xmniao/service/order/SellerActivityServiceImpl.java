/**    
 * 文件名：RedPacketService.java    
 *    
 * 版本信息：    
 * 日期：2016年10月22日    
 * Copyright 足下 Corporation 2016     
 * 版权所有    
 *    
 */

package com.xmniao.service.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.dao.coupon.RedPacketDao;
import com.xmniao.dao.coupon.RedPacketRecordDao;
import com.xmniao.dao.coupon.SellerCouponDao;
import com.xmniao.dao.coupon.UserCouponDao;
import com.xmniao.dao.sellerOrder.ActivityFcouspointsDao;
import com.xmniao.dao.sellerOrder.ActivityFullreductionDao;
import com.xmniao.domain.coupon.ActivityFcouspoints;
import com.xmniao.domain.coupon.ActivityFcouspointsPoints;
import com.xmniao.domain.coupon.ActivityFcouspointsRecord;
import com.xmniao.domain.coupon.ActivityFullreduction;
import com.xmniao.domain.coupon.ActivityFullreductionRecord;
import com.xmniao.domain.coupon.RedPacket;
import com.xmniao.domain.coupon.RedPacketRecord;
import com.xmniao.domain.coupon.SellerCoupon;
import com.xmniao.domain.coupon.UserCoupon;
import com.xmniao.domain.order.BillBean;
import com.xmniao.domain.order.XmnWalletBean;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：RedPacketService
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年10月22日 下午2:48:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class SellerActivityServiceImpl {
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(OrderServiceImpl.class);
    
    /**
     * 最大随机数
     */
    private static final int MAX_RANDOM = 99999;
    
    /**
     * 最小随机数
     */
    private static final int MIN_RANDOM = 10000;
    
    /**
     * 未使用的序列码KEY
     */
    private static final String COUPON_EXIST_KEY = "serial_coupon_";
    
    /**
     * 已使用的序列码KEY
     */
    private static final String COUPON_USED_KEY = "serial_coupon_used_";
    
    /**
     * 免费尝新抽奖次数KEY
     */
    private static final String FREETRY_KEY = "freetry_";
    
    @Autowired
    private RedPacketDao redPacketDao;
    
    @Autowired
    private RedPacketRecordDao redPacketRecordDao;
    
    @Autowired
    private UserCouponDao userCouponDao;
    
    @Autowired
    private SellerCouponDao sellerCouponDao;
    
    @Autowired
    private ActivityFullreductionDao fullreductionDao;
    
    @Autowired
    private ActivityFcouspointsDao fcouspointsDao;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * 领取红包
     * @Title: insertRedPacket 
     * @Description:
     */
    @Transactional(rollbackFor={Exception.class})
    public String insertRedPacket(RedPacket sellerRedPacket,BillBean billBean) throws Exception{
    	RedPacketRecord selRecord = new RedPacketRecord();
    	selRecord.setOrderNo(billBean.getBid().toString());
    	selRecord.setUserId((long)billBean.getUid());
    	selRecord.setRedpacketId(sellerRedPacket.getId());
    	List<RedPacketRecord> recordList = redPacketRecordDao.getRedPacketRecordList(selRecord);
    	if(recordList.size()>0){
    		log.info("该订单已赠送过该红包");
    		return null;
    	}
    	RedPacketRecord record = new RedPacketRecord();
		record.setRedpacketId(sellerRedPacket.getId());
//		if(sellerRedPacket.getAmountType()!=1){
//			throw new Exception("消费满赠红包【"+sellerRedPacket.getId()+"】只支持固定金额红包");
//		}
		record.setDenomination(sellerRedPacket.getSingleAmount());
		record.setRecordTime(new Date());
		record.setUserId((long)billBean.getUid());
		record.setPhone((billBean.getPhoneid()==null||billBean.getPhoneid().length()>20)?null:billBean.getPhoneid());
		record.setIsNewUser(billBean.getBfirst());		
		record.setIsBinding(billBean.getBfirst());		
		record.setSellerid(billBean.getSellerid());
		record.setOrderNo(billBean.getBid().toString());
		record.setStatus(0);
		redPacketRecordDao.insertSelective(record);
		
		int result = redPacketDao.sendRedPacket(sellerRedPacket.getId());
		if(result==0){
			log.error("消费满赠红包【"+sellerRedPacket.getId()+"】送多了，回滚。。。");
			throw new Exception("送多了，回滚。。。");
		}
		return record.getId()+"";
    }
    
    /**
     * 领取推荐人红包
     * @Title: insertRecommendRedPacket 
     * @Description:
     */
    @Transactional(rollbackFor={Exception.class})
    public RedPacketRecord insertRecommendRedPacket(RedPacket sellerRedPacket,BillBean billBean) throws Exception{
    	RedPacketRecord selRecord = new RedPacketRecord();
    	selRecord.setOrderNo(billBean.getBid().toString());
    	selRecord.setUserId((long)billBean.getrUserId());
    	selRecord.setRedpacketId(sellerRedPacket.getId());
    	List<RedPacketRecord> recordList = redPacketRecordDao.getRedPacketRecordList(selRecord);
    	if(recordList.size()>0){
    		log.info("该订单已赠送过该红包");
    		return null;
    	}
    	//该用户是否已推荐过该用户或者一个用户只能推荐一次
    	
    	RedPacketRecord record = new RedPacketRecord();
		record.setRedpacketId(sellerRedPacket.getId());

		/*
		 * 固定金额或随机金额 
		 */
		record.setDenomination(sellerRedPacket.getSingleAmount()!=null?sellerRedPacket.getSingleAmount()
				: getRondamAmount(sellerRedPacket.getTotalAmount().subtract(sellerRedPacket.getGetRedpacket()),
						sellerRedPacket.getRandomAmountMini(),sellerRedPacket.getRandomAmountMax()));
		record.setRecordTime(new Date());
		record.setUserId((long)billBean.getrUserId());
		record.setPhone(billBean.getrPhone());
		record.setIsNewUser(0);		
		record.setIsBinding(0);		
		record.setSellerid(billBean.getSellerid());
		record.setOrderNo(billBean.getBid().toString());
		record.setStatus(0);
		redPacketRecordDao.insertSelective(record);
		
		Map<String,Object> uMap = new HashMap<String,Object>();
		uMap.put("id", sellerRedPacket.getId());
		uMap.put("denomination", record.getDenomination());
		int result = redPacketDao.sendRecommendRedPacket(uMap);
		if(result==0){
			log.error("消费满赠红包【"+sellerRedPacket.getId()+"】送多了，回滚。。。");
			throw new Exception("送多了，回滚。。。");
		}
		return record;
    }
    
    /**
     * 领取商家券
     * @Title: insertSellerCoupon 
     * @Description:
     */
    @Transactional(rollbackFor=RuntimeException.class)
    public void insertSellerCoupon(SellerCoupon sellerCoupon,BillBean billBean){

    	if(sellerCoupon.getLimitNumber()>0){
    		UserCoupon selUc = new UserCoupon();
    		selUc.setCid(sellerCoupon.getCid());
    		selUc.setUid(billBean.getUid());
        	int count = userCouponDao.getUserCouponCout(selUc);
        	if(count>=sellerCoupon.getLimitNumber()){
        		log.info("该商家券【"+sellerCoupon.getCid()+"】每个用户限领一张，该用户【"+billBean.getUid()+"】已有领取记录");
        		return ;
        	}
    	}
    	
		UserCoupon uc = new UserCoupon();
		uc.setCid(sellerCoupon.getCid());
		if(sellerCoupon.getCouponType()==4){
			String serialNo = getRedisSerial(billBean.getSellerid());
			if(StringUtils.isBlank(serialNo)){
				throw new RuntimeException("获取序列号出错");
			}
			uc.setSerialNo("3"+serialNo);
		}
		uc.setDenomination(sellerCoupon.getDenomination());
		uc.setGetWay(4);
		uc.setGetTime(new Date());
		uc.setUid(billBean.getUid());
		//uc.setSellerid();
		uc.setPhone((billBean.getPhoneid()==null||billBean.getPhoneid().length()>20)?null:billBean.getPhoneid());
		uc.setUseStatus(0);
		uc.setStartDate(sellerCoupon.getStartDate());
		uc.setEndDate(sellerCoupon.getEndDate());
		uc.setBid(billBean.getBid());
		userCouponDao.addUserCoupon(uc);
		int result = sellerCouponDao.sendSellerCoupon(sellerCoupon);
		if(result==0){
			throw new RuntimeException("送多了。。。");
		}
	
    }
    
    /**
     * 消费满减
     * @Title: insertFullreduction 
     * @Description:
     */
    @Transactional(rollbackFor={Exception.class})
    public void insertFullreduction(ActivityFullreduction fullreduction,BillBean billBean) throws Exception{
    	if(fullreduction==null){
    		return;
    	}
    	boolean bfirstJoinThisActivity  = true;
    	ActivityFullreductionRecord selRecord = new ActivityFullreductionRecord();
    	selRecord.setUid(billBean.getUid());
    	selRecord.setActivityId(billBean.getFullReductionId());
    	List<ActivityFullreductionRecord> list = fullreductionDao.getFullreductionRecord(selRecord);
    	if(list.size()>0){
    		bfirstJoinThisActivity = false;
    		for(ActivityFullreductionRecord afr:list){
    			if(afr.getBid().equals(billBean.getBid())){
    				log.info("该订单已对该用户做出消费满减活动");
    				return;
    			}
    		}
    	}
    	ActivityFullreductionRecord record = new ActivityFullreductionRecord();
    	record.setActivityId(fullreduction.getId());
    	record.setDenomination(billBean.getFullReduction());
    	record.setGetWay(3);
    	record.setGetTime(billBean.getZdate());
    	record.setUid(billBean.getUid());
    	record.setPhone((billBean.getPhoneid()==null||billBean.getPhoneid().length()>20)?null:billBean.getPhoneid());
    	record.setUseStatus(2);
    	record.setUseMoney(billBean.getFullReduction());
    	record.setBid(billBean.getBid());
    	record.setStartDate(fullreduction.getBeginDate());
    	record.setEndDate(fullreduction.getEndDate());
    	record.setIsBinding(billBean.getBfirst());
    	fullreductionDao.insertFullreductionRecord(record);

    	Map<String,Object> uMap = new HashMap<String,Object>();
    	uMap.put("firstJoin", bfirstJoinThisActivity);//人数OR人次 
    	uMap.put("denomination", billBean.getFullReduction());
    	uMap.put("id", billBean.getFullReductionId());
    	uMap.put("orderAmount", billBean.getMoney());
    	uMap.put("newuser", billBean.getBfirst());
    	int result = fullreductionDao.sendFullreduction(uMap);
    	if(result==0){
			throw new RuntimeException("没送成。。。");
		}
	
    }
    
    /**
     * 商家聚点活动
     * @Title: insertRecommendRedPacket 
     * @Description:
     */
    @Transactional(rollbackFor={Exception.class})
    public void insertSellerFcouspoints(ActivityFcouspoints activityFcouspoints,BillBean billBean) throws Exception{

    	int point = 1;
    	if(activityFcouspoints.getIsSuposition()==1){
    		BigDecimal div = billBean.getCalculateRealPayAmount().divide(activityFcouspoints.getFullPrice());
    		if(div.compareTo(BigDecimal.valueOf(2.0))>=0){
    			point = point*div.intValue();
    		}
    	}
    	ActivityFcouspointsRecord reqRecord = new ActivityFcouspointsRecord();
    	reqRecord.setBid(billBean.getBid());
    	reqRecord.setUid(billBean.getUid());
    	reqRecord.setActivityId(activityFcouspoints.getId());
    	long x = fcouspointsDao.countActivityFcouspointsRecord(reqRecord);
    	if(x>0){
    		return;
    	}
    	
    	ActivityFcouspointsRecord record = new ActivityFcouspointsRecord();
    	record.setActivityId(activityFcouspoints.getId());
    	record.setBid(billBean.getBid());
    	record.setGivePoints(point);
    	record.setGetTime(new Date());
    	record.setSellerid(billBean.getSellerid());
    	record.setUid(billBean.getUid());
    	record.setPhone(billBean.getPhoneid());
    	fcouspointsDao.insertActivityFcouspointsRecord(record);
    	
    	ActivityFcouspointsPoints pointsRecord = new ActivityFcouspointsPoints();
    	pointsRecord.setActivityId(activityFcouspoints.getId());
    	pointsRecord.setUid(billBean.getUid());
    	ActivityFcouspointsPoints activityFcouspointsPoints = fcouspointsDao.getActivityFcouspointsPoints(pointsRecord);
    	if(activityFcouspointsPoints != null){
    		pointsRecord.setId(activityFcouspointsPoints.getId());
    		pointsRecord.setPoints(point);
    		pointsRecord.setUpdateTime(new Date());
    		pointsRecord.setCreateTime(new Date());
    		fcouspointsDao.updateActivityFcouspointsPoints(pointsRecord);
    	}else{
    		pointsRecord.setUpdateTime(new Date());
    		pointsRecord.setPhone(billBean.getPhoneid());
    		pointsRecord.setPoints(point);
    		fcouspointsDao.insertActivityFcouspointsPoints(pointsRecord);
    	}
    	
    	activityFcouspoints.setCountPoints(point);
    	fcouspointsDao.updateActivityFcouspoints(activityFcouspoints);
    }
    
    /**
     * 获取商家优惠券序列码
     * @Title: getRedisSerial 
     * @Description:
     */
    public String getRedisSerial(int sellerid){
    	String existKey=COUPON_EXIST_KEY+sellerid;
    	String usedKey=COUPON_USED_KEY+sellerid;
    	String result = null;
    	if(stringRedisTemplate.opsForList().size(existKey)<10){
    		log.info("redis队列中库存不足...");

            Random random = new Random();
            List<String> usedList = stringRedisTemplate.opsForList().range(usedKey, 0, -1);
            List<String> existList = stringRedisTemplate.opsForList().range(existKey, 0, -1);

    		Set<String> numSet = new HashSet<String>();
    		while(true){
    			String rdm= (random.nextInt(MAX_RANDOM)%(MAX_RANDOM-MIN_RANDOM+1) + MIN_RANDOM)+"";
    			//已使用队列中没有 && 剩余队列中没有 才允许添加
    			if(usedList.contains(rdm) || existList.contains(rdm)){
    				continue;
    			}
    			
    			numSet.add(rdm);
    			if(numSet.size()==500){	//一次添500个
    				break;
    			}
    		}
    		String[] s =  numSet.toArray(new String[numSet.size()]);
    		stringRedisTemplate.opsForList().leftPushAll(existKey, s);
    	}
    	result = stringRedisTemplate.opsForList().rightPop(existKey);
    	boolean isExists = stringRedisTemplate.hasKey(usedKey);
    	stringRedisTemplate.opsForList().leftPush(usedKey, result); 
    	if(!isExists){
    		//设置过期时间
    		Date now = new Date();
    		Date tomorrow = DateUtil.calendarDay(now, 1);
    		Date end = DateUtil.parseDate(DateUtil.getDateTime("yyyy-MM-dd",tomorrow)+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
    		long expireSeconds = ((end.getTime()-now.getTime())/1000)+1;
    		stringRedisTemplate.expire(usedKey, expireSeconds, TimeUnit.SECONDS);
    	}
    	return result;
    }
    
    /**
     * 获取随机金额
     * @Title: getRondamAmount 
     * @Description:
     */
    public  BigDecimal getRondamAmount(BigDecimal allAmount,BigDecimal minAmount,BigDecimal maxAmount){
    	if(allAmount.compareTo(minAmount)<=0){
    		log.info("当前金额小于最小金额，直接送最小金额");
    		return allAmount;
    	}
    	Random random = new Random();
    	double cur =random.nextDouble() * (maxAmount.subtract(minAmount).doubleValue()) + minAmount.doubleValue();
    	BigDecimal temp = new BigDecimal(cur).setScale(2,BigDecimal.ROUND_DOWN);
    	if(temp.compareTo(allAmount)>=0){
    		log.info("计算金额>最大金额，直播送最大金额");
    		return allAmount;
    	}
    	if(allAmount.subtract(temp).compareTo(minAmount)<0){
    		log.info("计算金额后，余留金额小于最小金额，直播送全部金额");
    		return allAmount;
    	}
    	return temp;
    }
    
    /**
     * 商家免费尝新队列
     * @Title: insertSellerFreetryRedis 
     * @Description:
     */
    public void insertSellerFreetryRedis(String key,int uid,Long seconds){
    	Object obj =stringRedisTemplate.opsForHash().get(key, uid+"");
    	log.info("商家活动抽奖Redis:key-"+key+",uid-"+uid+",index-"+obj);
    	Integer index = obj==null?null:Integer.parseInt(obj.toString());
    	if(index==null){
    		index = -1;
    		stringRedisTemplate.opsForHash().put(key, uid+"",index+"");
    		stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    	}else if(index>=0){
    		index=index-1;
    		stringRedisTemplate.opsForHash().put(key, uid+"",index+"");
    	}
    }
    
    public static void main(String[] args) {
		BigDecimal all = new BigDecimal(200);
		BigDecimal min = new BigDecimal(1);
		BigDecimal max = new BigDecimal(5);
		BigDecimal cur = null;
		SellerActivityServiceImpl sas = new SellerActivityServiceImpl();
		for(int i=0;i<100;i++){
			cur = sas.getRondamAmount(all, min,max);
			all = all.subtract(cur);
			System.out.println("第"+i+"个用户领取"+cur+"元，还剩"+all+"元");
			if(all.compareTo(BigDecimal.ZERO)<=0){
				System.out.println("领取完毕！");
				break;
			}
		}
	}
}
