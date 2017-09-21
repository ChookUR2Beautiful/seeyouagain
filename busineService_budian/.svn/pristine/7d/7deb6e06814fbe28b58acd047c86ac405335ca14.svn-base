package com.xmniao.service.manor;


import com.xmniao.common.DateUtil;
import com.xmniao.common.SnowflakeIdWorker;
import com.xmniao.dao.manor.ManorLevelEarningRecordMapper;
import com.xmniao.dao.manor.ManorOperateRecordMapper;
import com.xmniao.domain.manor.ManorLevel;
import com.xmniao.domain.manor.ManorLevelEarningRecord;
import com.xmniao.domain.manor.ManorOperateRecord;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.proxy.ThriftClientProxy;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.pay.ManorPropsThriftService;
import com.xmniao.thrift.pay.ManorPropsThriftService.Client;
import com.xmniao.thrift.pay.Result;
import com.xmniao.urs.dao.manor.ManorRelationMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 项目名称：busineService_manor
 * 
 * 类名称：ManorEarningService
 * 
 * 类描述： 庄园收益服务
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年6月19日 上午9:53:05 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class ManorEarningService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ManorOperateRecordMapper manorOperateRecordDao;
	
	@Autowired
	private ThriftClientProxy clientProxy;
	
	@Autowired
	private ManorRelationMapper manorRetaionMapper;
	
	@Autowired
	private ManorLevelEarningRecordMapper  manorLevelEarningRecordDao;
	
	
	/**
	 * 
	 * 方法描述：阳光兑换优惠券 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年6月19日上午9:54:26 <br/>
	 */
	@Transactional(rollbackFor={FailureException.class})
	public void convertCoupon(Map<String, String> params)
				throws FailureException, TException {
		throw new FailureException(0,"空接口，不支持兑换");
//	    	logger.info("黄金庄园兑换优惠券奖励，传入参数:"+params);
//	    	String transNo = params.get("transNo");
//	    	Integer uid = params.get("uid")==null?null:Integer.parseInt(params.get("uid"));
//	    	Integer number = params.get("number")==null?null:Integer.parseInt(params.get("number"));
//	    	Integer cid = params.get("cid")==null?null:Integer.parseInt(params.get("cid"));
//	    	Double voucherAmount = params.get("voucherAmount")==null?null:new Double(params.get("voucherAmount"));
//	    	Integer configPropsNumber = params.get("configPropsNumber")==null?null:new Integer(params.get("configPropsNumber"));
//	    	if(transNo==null || StringUtils.isBlank(transNo)
//	    		||uid==null || uid==0
//	    		|| number==null ||number==0
//	    		|| cid==null || cid==0
//	    		|| voucherAmount==null || voucherAmount == 0
//	    		|| configPropsNumber==null || configPropsNumber==0){
//	    		throw new FailureException(1, "传入参数不能为null或0");
//	    	}
//	    	
//	    	/* 获取操作记录 */
//	    	ManorOperateRecord record = manorOperateRecordDao.get(transNo);
//	    	if(record== null || record.getOperate()!=6){
//	    		throw new FailureException(1, "找不到对应操作记录");
//	    	}
//	    	if(record.getStatus()!= null && record.getStatus()==1){
//	    		return ;
//	    	}
//	    	/* 查询优惠券 */
//	    	Coupon coupon = couponDao.getCoupon(new Coupon(cid));
//	    	if(coupon==null){
//	    		throw new FailureException(1,"找不到对应优惠券");
//	    	}
//	    	
//	    	/*扣除阳光数据*/
//	    	Result thriftResult = commonServiceImp.exchangeVoucher(transNo, uid, voucherAmount, 2, configPropsNumber, number);
//	    	if(thriftResult==null){
//	    		logger.error("调用支付服务异常");
//	    		throw new FailureException(2,"操作失败，服务异常");
//	    	}
//	    	if(thriftResult.getCode()==0){
//	    		if(thriftResult.getStatusCode().equals("20003")){
//	    			//logger.info("已扣除操作");
//	    		}else{
//	    			logger.error("调用支付服务异常");
//	    			throw new FailureException(2,thriftResult.getMessage());
//	    		}
//	    	}
//	    	
//	    	try{
//		    	/*兑换优惠券*/
//		    	Date startDate = coupon.getStartDate()==null? new Date():coupon.getStartDate();
//		    	Date endDate = coupon.getEndDate()==null? DateUtil.calendarDay(startDate,coupon.getDayNum()):coupon.getEndDate();
//		    	List<CouponDetail> detailList = new ArrayList<>();
//				CouponDetail detail = new CouponDetail();
//				detail.setCid(cid);
//				detail.setCtype(coupon.getCtype());
//				detail.setDateIssue(new Date());
//				detail.setDenomination(coupon.getDenomination());
//				detail.setEndDate(endDate);
//				detail.setGetStatus((byte)1);
//				detail.setGetTime(new Date());
//				detail.setGetWay((byte)4);
//				detail.setSendStatus(1);
//				detail.setStartDate(startDate);
//				detail.setUid(uid);
//				detail.setUserStatus((byte)0);
//		    		
//		    	for(int i=0;i<number;i++){	
//		    		detail.setSerial(OrderSnGenerator.generatorUUID());
//		    		detailList.add(detail);
//		    	}
//		    	couponDao.insertCouponDetailList(detailList);
//		    	
//		    	this.updateOperateRecord(transNo, 1, null);
//	    	}catch(Exception e){
//	    		logger.error("兑换失败。。。",e);
//	    		throw new FailureException(0,"兑换异常");
//	    	}
		}
	
	public void updateOperateRecord(String transNo,Integer status,String faileMsg){
		if(StringUtils.isBlank(transNo)){
			return;
		}
		ManorOperateRecord uRecord = new ManorOperateRecord();
    	uRecord.setTransNo(transNo);
    	uRecord.setStatus(status);
    	uRecord.setFailDetails(faileMsg);
    	uRecord.setUpdateTime(new Date());
    	manorOperateRecordDao.updateStatus(uRecord);
	}
	
	public void updateEarningStatus(String transNo,Integer status,String faileMsg){
		if(transNo==null){
			return;
		}
		ManorLevelEarningRecord record = manorLevelEarningRecordDao.getByTransNo(transNo);
		if(record==null 
			|| record.getStatus()==status
			|| record.getStatus()==2){
			return;
		}
		ManorLevelEarningRecord uRecord = new ManorLevelEarningRecord();
    	uRecord.setTransNo(transNo);
    	uRecord.setStatus(status);
    	uRecord.setDescription(faileMsg);
    	uRecord.setUpdateTime(new Date());
    	manorLevelEarningRecordDao.updateStatus(uRecord);
    	
    	UrsEarningsRelation relation = new UrsEarningsRelation();
    	relation.setUid(record.getUid());
    	relation.setObjectOriented(9);
    	relation.setManorNectar(record.getNumber());
    	manorRetaionMapper.updateManorRelation(relation);
	}
	
	public void updateManorCount(String transNo,Integer uid,Integer number){
		if(uid==null || uid==0 ||number==null ||number<=0){
			return;
		}
    	UrsEarningsRelation relation = new UrsEarningsRelation();
    	relation.setUid(uid);
    	relation.setObjectOriented(9);
    	relation.setManorNectar(number);
    	manorRetaionMapper.updateManorRelation(relation);
	}
	
	/**
	 * 方法描述：庄园每日收益 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年6月14日下午3:43:45 <br/>
	 */
//	public void scanManorEarning() {
//	    /**
//	     * 1.获取等级收益列表
//	     * 2.获取会员等级信息
//	     * 3.奖励会员花蜜
//	     */
//	    Map<Integer, ManorLevel> levelMap = new HashMap<>();
//	    List<ManorLevel> list = manorLevelDao.getList(new ManorLevel());
//	    for (ManorLevel manorLevel : list) {
//	        levelMap.put(manorLevel.getId(), manorLevel);
//	    }
//	    String date = DateUtil.dateFormatY1(new Date());
//	    List<ManorInfo> manorerList = new ArrayList<>();    //庄园信息列表
//	    List<ManorEarning> earningList = new ArrayList<>();    //庄园收益列表
//	    int pageSize = 200;
//	    long scanCount = 0;
//	    long earningCount = 0;
//	    do {
//	        manorerList = manorInfoDao.selectActivatedManors();
//	        for (ManorInfo manorInfo : manorerList) {
//	            try {
//	                Integer nectar = levelMap.get(manorInfo.getManorLevel()).getDailyNectar();
//	                if (nectar != null && nectar > 0) {
//	                    earningList.add(new ManorEarning(manorInfo.getUid(), nectar, date));
//	                }
//	            } catch (Exception e) {
//	                logger.error("给会员【{}】计算庄园每日收益异常," + e, manorInfo.getUid());
//	            }
//	        }
//	        if (earningList.size() > 0) {
//	            //给会员发放收益
//	        }
//	        scanCount = scanCount + manorerList.size();
//	        earningCount = earningCount + earningList.size();
//	        earningList.clear();
//	    } while (manorerList.size() == pageSize);
//	    logger.info("本次共扫描到{}个有效庄园，基本有{}个有庄园收益。", scanCount, earningCount);
//	}
	
	/**
	 * 
	 * 方法描述：庄园每日花园收益<br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年6月19日下午2:02:47 <br/>
	 */
	public void manorDailyNectarEarning(ManorLevel level,Integer uid,String phone,Date yesterday){
		boolean hasEarning = level.getDailyNectar()==0?false:true;
		
//		Map<String,Object> ursMap = ursDao.getUrsByUid(uid+"");
//		String phone = ursMap==null?null:(String)ursMap.get("phone");
		ManorLevelEarningRecord levelEarning = new ManorLevelEarningRecord();
		levelEarning.setTransNo((new SnowflakeIdWorker(2,2)).nextId()+"");
		levelEarning.setUid(uid);
		levelEarning.setPhone(phone);
		levelEarning.setYesterday(yesterday);
		levelEarning.setLevelId(level.getId());
		levelEarning.setLevelName(level.getName());
		levelEarning.setEarningChanel(1);
		levelEarning.setEarningType(1);
		levelEarning.setNumber(level.getDailyNectar());
		levelEarning.setStatus(hasEarning?1:2);
		levelEarning.setCreateTime(new Date());
		levelEarning.setUpdateTime(levelEarning.getCreateTime());
		manorLevelEarningRecordDao.insert(levelEarning);

	}

	@Transactional(rollbackFor=Exception.class)
	public ResponseData receiveDailyEarnings(Integer uid,String transNo) throws Exception{
		ManorLevelEarningRecord earning = manorLevelEarningRecordDao.getByTransNo(transNo);
		if(earning==null){
			return new ResponseData(1,"找不到对应收益记录",null);
		}
		if(!earning.getUid().equals(uid)){
			return new ResponseData(1,"领取对应收益记录非本人所有",null);
		}
		if(earning.getStatus()==2){
			return new ResponseData(0,"已领取成功，直接返回",null);
		}
		if(!DateUtil.dateFormatY1(earning.getCreateTime()).equals(DateUtil.dateFormatY1(new Date()))){
			return new ResponseData(1,"领取失败，红包日期已失效",null);
		}
		
		
		int status=1;
		String message="";
		Result result = null;
		try{
			ManorPropsThriftService.Client client =  (Client) clientProxy.getPayServiceClient(ManorPropsThriftService.class);
			result = client.receiveEvaryDayNectary(transNo, uid, earning.getNumber());
			message=result.getMessage();
			if(result.getCode()==1 && result.getStatusCode().equals("10000")){
				status=2;
			}else if(result.getCode()==0 && result.getStatusCode().equals("20004")){
				status=2;
			}else if(result.getCode()==0){
				status=3;
			}
		}catch(Exception e){
			logger.error("调用支付接口收获花蜜异常",e);
			message = "调用支付异常";
		}finally{
			clientProxy.returnPayServiceClient();
		}
		earning.setStatus(status);
		earning.setUpdateTime(new Date());
		manorLevelEarningRecordDao.updateStatus(earning);
		if(result!=null && result.getCode()==1 && result.getStatusCode().equals("10000")){
			this.updateManorCount(transNo,uid,earning.getNumber());
		}
		return new ResponseData(status==2?0:1,message,null);
	}

	/** 查询没有领取的花蜜 */
	public List<ManorLevelEarningRecord> queryUnclaimed() {
		return manorLevelEarningRecordDao.selectTodayByStatus(1);
	}
	
	/**
	 * 
	 * 方法描述：今天是否已进行过收益奖励 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年7月25日下午8:03:09 <br/>
	 * @return
	 */
	public boolean hasEarningToday(Date date){
		return manorLevelEarningRecordDao.countEarningToday(date)>0?true:false;
	}
}
