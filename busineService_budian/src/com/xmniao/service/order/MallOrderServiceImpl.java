package com.xmniao.service.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.dao.order.BillBargainDao;
import com.xmniao.dao.order.MallOrderDao;
import com.xmniao.dao.order.MallPackageDao;
import com.xmniao.domain.live.UrsEarningsRank;
import com.xmniao.domain.order.BillBargain;
import com.xmniao.domain.order.MallOrderBean;
import com.xmniao.domain.order.MallOrderProductBean;
import com.xmniao.domain.order.MallPackage;
import com.xmniao.domain.order.MallSubOrderBean;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.service.live.VerMallService;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.order.MallOrderService;

@Service("mallOrderServiceImpl")
public class MallOrderServiceImpl  implements MallOrderService.Iface{
	private final static Logger logger = LoggerFactory.getLogger(MallOrderServiceImpl.class);
	
	@Autowired
	private MallOrderDao mallOrderDao;
	
	@Autowired
	private BillBargainDao billBargainDao;
	
	@Resource(name="smsqueue")
	private String smsQueue;
	
	
	
	@Resource(name="waterCoupon")
	private Map<String,String> waterCoupon;

	@Autowired
	private MallService mallService;
	
	@Autowired
	private VerMallService verMallService;
	
	
	@Override
	public Map<String, String> notifyForPayComplete(Map<String, String> params) throws FailureException, TException {

		Map<String, String> result = mallService.notifyForPayComplete(params);
		
		try{
			MallOrderBean order = mallOrderDao.getByBid(Long.valueOf(params.get("bid")));
			if(order.getStatus()==1 && order.getRankType()!=null && order.getRankId()!=null && order.getRankType()==2){
				verMallService.mallVerConnect(order.getUid(), order.getMoney().doubleValue(), order.getRankId(),order.getBid()+"", new Date());
			}
		}catch(Exception e){
			logger.error("给会员赋予V客权限奖励失败",e);
		}
		return result;
	}

	@Override
	public Map<String, String> modifyOfflineOrderInfo(Map<String, String> paraMap) throws FailureException, TException {
		Map<String,String> result = new HashMap<String,String>();
		logger.info("线下积分订单支付成功通知，参数:{}",JSON.toJSONString(paraMap));
		String bid = paraMap.get("bid");
		if(StringUtils.isEmpty(bid)){
			throw new FailureException(1001,"bid必须填");
		}
		
		//1 已支付 2 支付失败
		String status = paraMap.get("status");
		if(StringUtils.isEmpty(status) || !("1".equals(status) || "2".equals(status))){
			throw new FailureException(1002,"status必须填,且值必须是1或2");
		}
		
		BillBargain bill = this.billBargainDao.getByBid(Long.valueOf(bid));
		if(bill == null){
			throw new FailureException(1003,"订单不存在");
		}
		
		if(bill.getStatus().intValue() != 0 && bill.getStatus().intValue() !=2){
			throw new FailureException(1004,"重复通知");
		}
		
		
		String amount = paraMap.get("amount");
		String integral = paraMap.get("integral");
		String balance = paraMap.get("balance");
		String paymentType = paraMap.get("paymentType");
		String payid = paraMap.get("payid");
		String thirdid = paraMap.get("thirdid");
		String thirduid = paraMap.get("thirduid");
		String payTime = paraMap.get("payTime");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bid", bill.getBid());
		map.put("version", bill.getVersion());
		map.put("status",Integer.valueOf(status));
		map.put("paymentType",paymentType);
		map.put("payid",payid);
		map.put("thirdid",thirdid);
		map.put("thirduid",thirduid);
		map.put("amount",StringUtils.isNotEmpty(amount) ? new BigDecimal(amount) : new BigDecimal(0));
		map.put("integral",StringUtils.isNotEmpty(integral) ? new BigDecimal(integral) : new BigDecimal(0));
		map.put("balance",StringUtils.isNotEmpty(balance) ? new BigDecimal(balance) : new BigDecimal(0));
		map.put("payTime",StringUtils.isNotEmpty(payTime) ? DateUtil.parseDate1(payTime) : new Date());
		
		int count = this.billBargainDao.updateForPayComplete(map);
		
		if(count > 0){
			result.put("is_success", "T");
			return result;
		}else{
			result.put("is_success", "F");
			return result;
		}
		
	}
	
}
