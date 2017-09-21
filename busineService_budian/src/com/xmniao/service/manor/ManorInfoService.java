package com.xmniao.service.manor;

import static com.xmniao.service.manor.ManorConstant.FLOWER_LOCATION_LEFT;
import static com.xmniao.service.manor.ManorConstant.FLOWER_LOCATION_MIDDLE;
import static com.xmniao.service.manor.ManorConstant.FLOWER_LOCATION_RIGHT;
import static com.xmniao.service.manor.ManorConstant.FLOWER_TYPE_FLOWER;
import static com.xmniao.service.manor.ManorConstant.MANOR_FLOWER_DAYS;
import static com.xmniao.service.manor.ManorConstant.MANOR_INFO_OPERATE_TYPE_ACTIVATED;
import static com.xmniao.service.manor.ManorConstant.MANOR_INFO_OPERATE_TYPE_OPENED;
import static com.xmniao.service.manor.ManorConstant.MANOR_INFO_OPERATE_TYPE_PAY_SERVICE_FAILED;
import static com.xmniao.service.manor.ManorConstant.MANOR_PUSH_NWE_FRIEND_LIST;
import static com.xmniao.service.manor.ManorConstant.MANOR_REDIS_KEY_COUPONS;
import static com.xmniao.service.manor.ManorConstant.MANOR_REDIS_KEY_COUPONS_NEWFRIEND;
import static com.xmniao.service.manor.ManorConstant.MANOR_REDIS_KEY_COUPONS_PUSH_NEWFRIEND;
import static com.xmniao.service.manor.ManorConstant.MANOR_REDIS_KEY_PUSH_COUPONS;
import static com.xmniao.service.manor.ManorConstant.MANOR_URS_EARNINGS_OBJCET_ORIENTD;
import static com.xmniao.service.manor.ManorConstant.PREFIX_MANOR_FLOWER;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.common.DateUtil;
import com.xmniao.common.OrderSnGenerator;
import com.xmniao.common.RedisService;
import com.xmniao.common.ThriftClientFactory;
import com.xmniao.dao.coupon.CouponDao;
import com.xmniao.dao.manor.ManorCouponConfigMapper;
import com.xmniao.domain.coupon.Coupon;
import com.xmniao.domain.coupon.CouponDetail;
import com.xmniao.domain.manor.ManorCouponConfig;
import com.xmniao.domain.manor.ManorFlowerCount;
import com.xmniao.domain.manor.ManorInfo;
import com.xmniao.domain.manor.ManorInfoRecord;
import com.xmniao.domain.manor.ManorLevel;
import com.xmniao.domain.urs.BursEarningsRelationChain;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.exception.CustomException;
import com.xmniao.service.manor.vo.ManorActivateInfo;
import com.xmniao.thrift.pay.ManorPropsThriftService;
import com.xmniao.thrift.pay.Result;
import com.xmniao.urs.dao.UrsDao;
import com.xmniao.urs.dao.UrsEarningsRelationDao;
import com.xmniao.urs.dao.manor.ManorInfoMapper;
import com.xmniao.urs.dao.manor.ManorLevelMapper;

/**
 * 黄金庄园服务类
 * Created by yang.qiang on 2017/6/6.
 */
@Service
public class ManorInfoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UrsEarningsRelationDao ursEarningsRelationDao;
    @Autowired
    private ManorInfoMapper manorInfoMapper;
    @Autowired
    private ManorLevelMapper manorLevelMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ManorLogService manorLogService;
    @Autowired
    private ManorFlowerProducer flowerProducer;
    @Autowired
    private ManorCouponConfigMapper manorCouponConfigMapper;
    @Autowired
    private CouponDao couponDao;
    @Autowired
    private UrsDao ursDao;
    @Autowired
    private ManorFlowerService manorFlowerService;
    /**
     * 注入支付服务IP地址
     */
    @Resource(name = "transLedgerIP")
    private String transLedgerIP;

    /**
     * 注入支付服务端口号
     */
    @Resource(name = "transLedgerPort")
    private int transLedgerPort;
    @Autowired
    private String manorUpdateFlowerTypeTag;

    /**
     * 激活/续租 黄金庄园
     * @param uid
     * @param transNo
     * @param months
     */
    @Transactional
    public ManorActivateInfo activateManor(Integer uid, String transNo, Integer months) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, TException {
        logger.info("用户["+uid+"], 激活/续租庄园 transNo="+transNo + " months="+months);
        // 查询该用户的庄园信息
        ManorInfo manorInfo = manorInfoMapper.selectByUid(uid);
        // 查询用户上级
        Integer superUid = ursEarningsRelationDao.selectSuperUidByUid(uid, 9);
        if(superUid == null || superUid.intValue() == 0){
            superUid = null;
        }

        int activateType = manorInfo == null ? 1 : 2;       // 庄园激活操作类型 1.激活 2.续租

        // 调用支付接口扣款, 并获取赠送人uid
        Result result;
        try(ThriftClientFactory.ThriftClient<ManorPropsThriftService.Client> manorPropsThriftServiceClient
                    = ThriftClientFactory.getManorPropsThriftServiceClient(transLedgerIP, transLedgerPort)){
            // Thrift 接口Client
            ManorPropsThriftService.Client client = manorPropsThriftServiceClient.getThriftClient();

            int parentUid = superUid == null ? 0 : superUid;    // 上级uid, 没有则传0
            result = client.activateManor(transNo,parentUid,uid,activateType,months);
            logger.info("调用支付接口 growFlowersByUid result:" + JSON.toJSONString(result));

        }catch (Exception e){
            logger.error("调用支付服务出现异常",e);
            throw new CustomException("调用支付服务失败!",e);
        }


        // 校验支付服务响应数据
        if (result.code != 1 && !"10000".equals(result.statusCode)) {
            String message = "调用支付服务失败 result:" + JSON.toJSONString(result);
            logger.info(message);
            if ("20002".equals(result.statusCode)) {
                throw new CustomException(message, 3);
            } else {
                manorLogService.logManorInfoRecord(new ManorInfoRecord(
                        null,null, MANOR_INFO_OPERATE_TYPE_PAY_SERVICE_FAILED,uid,JSON.toJSONString(result)
                ));
                throw new CustomException(message);
            }
        }


        HashMap<String, Object> logDetailMap = new HashMap<>();
        logDetailMap.put("支付返回数据",result);

        // 没有关系链的用户, 插入关系链
        initUrsChain(uid);

        if (manorInfo == null) {    // 首次开通庄园


            // 计算截至时间
            Date deadline = ManorDateUtils.getDateDistance(new Date(), Calendar.DAY_OF_MONTH, MANOR_FLOWER_DAYS);

            // 插入庄园信息
            ManorInfo record = new ManorInfo();
            record.setSuperUid(superUid);
            record.setManorDeadline(deadline);  // 庄园截至时间
            record.setUid(uid);
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            logger.info("用户[" + uid + "]创建庄园 manorInfo:" + JSON.toJSONString(record));
            manorInfoMapper.insertSelective(record);
            ursEarningsRelationDao.updateManorLevel(uid,1);

            // 初始化花朵链节点
            flowerProducer.pushInitNodeMessage(transNo,uid);


            // 赠送上级优惠劵
            if (superUid != null) {
                //  赠送上级优惠劵 , 更新Redis数据
                List<ManorCouponConfig> configs = manorCouponConfigMapper.selectByType(1);
                grantCoupons(superUid,uid,configs);


                logDetailMap.put("优惠劵发放数据",configs);
                // 更新优惠劵数量
                int coupons = 0;
                for (ManorCouponConfig config : configs) {
                    coupons += config.getNumber();
                }
                redisService.hincrby(PREFIX_MANOR_FLOWER+superUid,MANOR_REDIS_KEY_COUPONS,coupons);
                redisService.hincrby(PREFIX_MANOR_FLOWER+superUid,MANOR_REDIS_KEY_PUSH_COUPONS,coupons);

                // 统计新园友(上级)
                redisService.hincrby(PREFIX_MANOR_FLOWER+superUid,MANOR_REDIS_KEY_COUPONS_NEWFRIEND);
                redisService.hincrby(PREFIX_MANOR_FLOWER+superUid,MANOR_REDIS_KEY_COUPONS_PUSH_NEWFRIEND);
                redisService.lpush(MANOR_PUSH_NWE_FRIEND_LIST,superUid+"");
            }

            logDetailMap.put("庄园开通数据",record);
            
            // 插入记录信息
            manorLogService.logManorInfoRecord(new ManorInfoRecord(transNo,
                    MANOR_INFO_OPERATE_TYPE_OPENED,record.getId(),uid,JSON.toJSONString(logDetailMap)));

            return new ManorActivateInfo(activateType, deadline, MANOR_FLOWER_DAYS);

        } else {     // 续租庄园
            // 判断是否过期, 设置庄园截至时间
            boolean overdue = manorInfo.getManorDeadline().compareTo(new Date()) <= 0;
            int activateDays = months * MANOR_FLOWER_DAYS;
            Date deadline = overdue ?
                    ManorDateUtils.getDateDistance(new Date(), Calendar.DAY_OF_MONTH, activateDays) :  // 已过期 当前时间30天后
                    ManorDateUtils.getDateDistance(manorInfo.getManorDeadline(), Calendar.DAY_OF_MONTH, activateDays); // 未过期 截至日期30天后

            ManorInfo record = new ManorInfo();
            record.setManorDeadline(deadline);
            record.setId(manorInfo.getId());
            record.setActivateCount(manorInfo.getActivateCount() + 1);
            logger.info("用户[" + uid + "]续租庄园 manorInfo:" + JSON.toJSONString(record));
            manorInfoMapper.updateByPrimaryKeySelective(record);


            // 当用户第二次激活庄园的时候 更新用户初始节点花类型为花朵
            if (manorInfo.getActivateCount() == 1 && manorInfo.getSuperUid() != null) {
                // 推送更新花朵类型消息
                flowerProducer.pushUpdateInitNodeTypeMessage(transNo,manorInfo.getUid(),FLOWER_TYPE_FLOWER);

            }

            logDetailMap.put("庄园续租更新数据",record);
            
            // 插入记录信息
            manorLogService.logManorInfoRecord(new ManorInfoRecord(transNo, MANOR_INFO_OPERATE_TYPE_ACTIVATED,
                    manorInfo.getId(),uid,JSON.toJSONString(logDetailMap)));


            return new ManorActivateInfo(activateType, deadline, activateDays);
        }


    }

    /**
     * 发放黄金庄园优惠劵
     * @param uid           优惠劵发放对象
     * @param sourceUid     赠送人UID
     */
    public void grantCoupons(Integer uid, Integer sourceUid, List<ManorCouponConfig> configs ) {
        logger.info("黄金庄园发放优惠券, uid=" + uid + "  赠送人:" + sourceUid);

        Map<String, Object> ursInfo = ursDao.getUrsByUid(uid + "");

        List<CouponDetail> detailList = new ArrayList<>();
        for (ManorCouponConfig couponConfig : configs) {
            if (couponConfig.getState() != 1){
                logger.info("黄金庄园发放优惠劵-优惠劵配置开关已关闭! " + JSON.toJSON(couponConfig));
                continue;
            }

            // 查询优惠券信息
            Coupon coupon = couponDao.getCoupon(new Coupon(couponConfig.getCid()));

                /*兑换优惠券*/
            Date startDate = coupon.getStartDate() == null ? new Date() : coupon.getStartDate();
            Date endDate = coupon.getEndDate() == null ? DateUtil.calendarDay(startDate, coupon.getDayNum()) : coupon.getEndDate();
            CouponDetail detail = new CouponDetail();
            detail.setCid(coupon.getCid());
            detail.setCtype(coupon.getCtype());
            detail.setDateIssue(new Date());
            detail.setDenomination(coupon.getDenomination());
            detail.setEndDate(endDate);
            detail.setGetStatus((byte) 1);
            detail.setGetTime(new Date());
            detail.setGetWay((byte) 8);
            detail.setSendStatus(1);
            detail.setStartDate(startDate);
            detail.setUid(uid);
            detail.setPhone(ursInfo.get("phone")+"");
            detail.setUserStatus((byte) 0);
            detail.setSourceUid(sourceUid);

            for (int i = 0; i < couponConfig.getNumber(); i++) {
                detail.setSerial(OrderSnGenerator.generatorUUID());
                detailList.add(detail);
            }
        }
        if (detailList.size() > 0) couponDao.insertCouponDetailList(detailList);
    }


    /**
     * 初始化用户的关系链
     * @param uid
     */
    private void initUrsChain(Integer uid) {
        int count = ursEarningsRelationDao.countUserRelation(uid, 9);
        if ( count < 1) {
            logger.info("用户["+uid+"]没有黄金庄园关系链存在, 初始化一条关系链");
            UrsEarningsRelation ursEarningsRelation = new UrsEarningsRelation();
            ursEarningsRelation.setUid(uid);
            ursEarningsRelation.setObjectOriented(9);

            String chain = "00000000000" + uid;
            ursEarningsRelation.setUidRelationChain(chain.substring(chain.length()-11,chain.length()));

            ursEarningsRelation.setUidRelationChainLevel(1);
            ursEarningsRelation.setCreateTime(new Date());
            ursEarningsRelationDao.insertUrsEarningsRelation(ursEarningsRelation);
            
            // 初始化用户关系链
            ursEarningsRelationDao.insertInitChain(uid,uid,9);

        }


    }


    /**
     * 查询上级用户id
     * @param uid
     * @return
     */
    private Integer querySuperUid(Integer uid) {
        String userRelation = ursEarningsRelationDao.selectUserRelation(uid, 9);
        Integer superUid = null;
        if (userRelation != null) {
            String[] uids = userRelation.split(",");
            if (uids.length > 1) {
                superUid = Integer.valueOf(uids[uids.length - 2]);
            }
        }
        return superUid;
    }


    /**
     * 查询激活状态的庄园
     *
     * @return
     */
    public List<ManorInfo> queryActivatedManors() {
        return manorInfoMapper.selectActivatedManors();
    }

    /**
     * 分页查询激活状态的庄园
     *
     * @return
     */
    public List<ManorInfo> queryActivatedManorsByPage(int page,int limit) {
    	Map<String,Object> map = new HashMap<>();
    	map.put("page", page>0?page:1);
    	map.put("limit", limit);
        return manorInfoMapper.selectActivatedManorsByPage(map);
    }


    /**
     * 获取庄园等级等级
     *
     * @param flowerCountMap
     * @return
     */
    public ManorLevel getManorLevel(List<ManorFlowerCount> flowerCountMap) {
        // 玫瑰花朵数
        int roses = ManorFlowerStatUtils.statAll(flowerCountMap, FLOWER_LOCATION_LEFT);
        // 兰花花朵数
        int orchids = ManorFlowerStatUtils.statAll(flowerCountMap, FLOWER_LOCATION_MIDDLE);
        // 葵花花朵数
        int sunflowers = ManorFlowerStatUtils.statAll(flowerCountMap, FLOWER_LOCATION_RIGHT);
        return manorLevelMapper.selectManorNo(roses, orchids, sunflowers);
    }


    /**
     * 更新庄园redis数据
     *
     * @param redisKey
     * @param flowerCountMap
     * @param manorLevel
     */
    public void updateRedisManorInfo(String redisKey, List<ManorFlowerCount> flowerCountMap, ManorLevel manorLevel) {
        int allFlowerCount = ManorFlowerStatUtils.statAll(flowerCountMap);

        // 获取庄园redis旧数据
        String preFlowerCount = redisService.hget(redisKey, "cur-flower");
        String preLevel = redisService.hget(redisKey, "cur-level");

        // 向更新Redis数据
        HashMap<String, String> manorRedisInfo = new HashMap<>();
        manorRedisInfo.put("mark", "0");     // 是否已读
        manorRedisInfo.put("cur-flower", String.valueOf(allFlowerCount));   // 当前花朵总数
        manorRedisInfo.put("pre-flower", preFlowerCount);                    // 之前花朵总数
        manorRedisInfo.put("cur-level", String.valueOf(manorLevel.getNo())); // 当前庄园等级
        manorRedisInfo.put("pre-level", preLevel);                           // 之前庄园等级

        logger.info("更新redis数据 redisKey=" + redisKey + "  manorRedisInfo:" + JSON.toJSONString(manorRedisInfo));
        redisService.hmset(redisKey, manorRedisInfo);

    }


    public void updateLocationForBursRelation(Integer subUid, Integer location){
        logger.info("用户["+subUid+"] 修改摆放位置为["+location+"]");
        manorInfoMapper.updateBranchLocationByUid(location,subUid);
    }

    @Transactional
    public void updateManorLevel(ManorInfo manorInfo) throws Exception {

        logger.info("更新庄园信息 manorInfo:"+JSON.toJSONString(manorInfo));

        // 统计庄园实时花朵数
        List<ManorFlowerCount> flowerCountList = manorFlowerService.countAliveFlower(manorInfo.getUid());

        // 查询庄园等级
        ManorLevel currentLevel = getManorLevel(flowerCountList);

        // 更新庄园表
        ManorLevel preLevel = manorLevelMapper.selectByPrimaryKey(manorInfo.getManorLevel());

        if (!preLevel.getId().equals(currentLevel.getId())) {
            // 更新庄园等级
            ManorInfo record = new ManorInfo();
            record.setId(manorInfo.getId());
            record.setManorLevel(currentLevel.getId());
            record.setUpdateTime(new Date());
            manorInfoMapper.updateByPrimaryKeySelective(record);

            // 更新关系链用户等级
            ursEarningsRelationDao.updateManorLevel(manorInfo.getUid(),currentLevel.getId());

            manorLogService.logManorInfoRecord(new ManorInfoRecord(null, ManorConstant.MANOR_INFO_OPERATE_TYPE_UPDATE_LEVEL,
                    manorInfo.getId(), manorInfo.getUid(), JSON.toJSONString(record)));
        }


        // 记录庄园操作参数
        String redisKey = PREFIX_MANOR_FLOWER + manorInfo.getUid();
        updateRedisManorInfo(redisKey, flowerCountList, currentLevel);

        logger.info("庄园信息更新完成 manor_id:"+manorInfo.getId() + " uid :" + manorInfo.getUid());

    }

	 /**
	 * 方法描述：获取庄园用户链信息
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月7日上午9:58:54 <br/>
	 * @param uid
	 * @return
	 */
	public UrsEarningsRelation getManorByUid(Integer uid) {
		UrsEarningsRelation u = new UrsEarningsRelation();
		u.setUid(uid);
		u.setObjectOriented(MANOR_URS_EARNINGS_OBJCET_ORIENTD);
	   return	ursEarningsRelationDao.getUrsEarningsRelation(u);
	}

	 /**
	 * 方法描述：绑定用户关系链
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月7日上午10:31:42 <br/>
	 * @param childId
	 * @param parentId
	 * @return
	 */
	 @Transactional
	public UrsEarningsRelation usrChainBindingParent(Integer childId, Integer parentId) {
		UrsEarningsRelation parent= this.getManorByUid(parentId);
		UrsEarningsRelation bursEarningsRelation = new UrsEarningsRelation();
		bursEarningsRelation.setCreateTime(new Date());
		bursEarningsRelation.setUid(childId);
		bursEarningsRelation.setParentUid(parent.getUid().longValue());
		bursEarningsRelation.setObjectOriented(MANOR_URS_EARNINGS_OBJCET_ORIENTD);
		bursEarningsRelation.setUidRelationChainLevel(parent.getUidRelationChainLevel()+1);
		bursEarningsRelation.setManorLevel(1);
		bursEarningsRelation.setManorNectar(0);
		int j=ursEarningsRelationDao.insertUrsEarningsRelation(bursEarningsRelation);
		List<UrsEarningsRelation> parentList= this.getManorParent(parent.getUid());
		parentList.add(bursEarningsRelation);
		List<BursEarningsRelationChain> list= new ArrayList<>();
		for (UrsEarningsRelation bursParent : parentList) {
			BursEarningsRelationChain chain = new BursEarningsRelationChain();
			chain.setObjectOriented(9);
			chain.setParentId(bursParent.getUid().longValue());
			chain.setUid(bursEarningsRelation.getUid().longValue());
			list.add(chain);
		}
		int i=ursEarningsRelationDao.addChainBatch(list);
		if(i!=list.size()||j!=1){
			throw new RuntimeException("创建庄园用户关系链时出现异常");
		}
		return bursEarningsRelation;
	}

	 /**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月7日上午10:54:56 <br/>
	 * @param uid
	 * @return
	 */
	private List<UrsEarningsRelation> getManorParent(Integer uid) {
		return ursEarningsRelationDao.getParentsByUid(uid,MANOR_URS_EARNINGS_OBJCET_ORIENTD);
	}

	 /**
	 * 方法描述：迁移用户链
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月11日上午11:25:27 <br/>
	 * @param childId
	 * @param parentId
	 * @param location
	 */
	 @Transactional
	public void BindingParent(Integer childId, Integer parentId, Integer location) {
		//删除自己与上级关系链
		UrsEarningsRelation child = this.getManorByUid(childId);
		
		//目标
		UrsEarningsRelation parent = this.getManorByUid(parentId);
		
		//绑定对象所有下级(包括自己)
		List<Integer> childIds = ursEarningsRelationDao.getManorChildsUid(childId);
		//目标所有上级(包括自己)
		List<UrsEarningsRelation> manorParents = this.getManorParent(parentId);
		List<Integer> parentIds = getUids(manorParents);
		
		//原来的上级
		List<UrsEarningsRelation> beforeParent = this.getManorParent(childId);
		boolean remove = beforeParent.remove(child);
		if(!remove){
			throw new RuntimeException("数据异常");
		}
		
		//修改b_urs_earnings_relation parent_uid
		ursEarningsRelationDao.updateParentUid(childId,parentId,MANOR_URS_EARNINGS_OBJCET_ORIENTD);
		
		if(!beforeParent.isEmpty()){
			//删除与上级的关系链
			List<Integer> beforeUids = getUids(beforeParent);
			int i=ursEarningsRelationDao.deleteChains(childIds,beforeUids);
			if(i!=childIds.size()*beforeUids.size()){
				throw new RuntimeException("数据异常");
			}
		}
		
		//插入新关系链
		int j=this.insertChains(childIds,parentIds);
		
		if(j!=childIds.size()*parentIds.size()){
			throw new RuntimeException("数据异常");
		}
		
		//更新链等级
		int z=ursEarningsRelationDao.updateManorLevels(childIds, parent.getUidRelationChainLevel()+1-child.getUidRelationChainLevel());
		if(z!=childIds.size()){
			throw new RuntimeException("数据异常");
		}
		
		//修改庄园推荐人,花田位置
		this.updateManorByBinding(childId,parentId,location);
		
	}

	   /**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月11日下午3:09:04 <br/>
	 * @param childIds
	 * @param parentIds
	 * @return
	 */
	private int insertChains(List<Integer> childIds, List<Integer> parentIds) {
		List<BursEarningsRelationChain> chains = new ArrayList<>();
		for(Integer c:childIds){
			for(Integer p:parentIds){
				BursEarningsRelationChain bursEarningsRelationChain = new BursEarningsRelationChain();
				bursEarningsRelationChain.setObjectOriented(MANOR_URS_EARNINGS_OBJCET_ORIENTD);
				bursEarningsRelationChain.setParentId(p.longValue());
				bursEarningsRelationChain.setUid(c.longValue());
				chains.add(bursEarningsRelationChain);
			}
		}
		return ursEarningsRelationDao.addChainBatch(chains);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月11日下午2:43:24 <br/>
	 * @param manorParents
	 * @return
	 */
	private List<Integer> getUids(List<UrsEarningsRelation> manorParents) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (UrsEarningsRelation ursEarningsRelation : manorParents) {
			arrayList.add(ursEarningsRelation.getUid());
		}
		return arrayList;
	}

	/**
	 * 方法描述：跟新manorInfo迁移链需要跟新的操作
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月11日下午2:26:43 <br/>
	 * @param childId
	 * @param parentId
	 * @param location
	 */
	private int updateManorByBinding(Integer childId, Integer parentId, Integer location) {
		ManorInfo manorInfo = new ManorInfo();
		manorInfo.setUid(childId);
		manorInfo.setSuperUid(parentId);
		manorInfo.setBranchLocation(location);
		return manorInfoMapper.updateByPrimaryKeySelective(manorInfo);
		
	}
	
}