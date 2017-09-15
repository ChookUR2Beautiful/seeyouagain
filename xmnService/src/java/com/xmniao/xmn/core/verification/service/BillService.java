package com.xmniao.xmn.core.verification.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.entity.VerifyResponseBean;

/**
 * person Service实现类
 * 
 * @author Administrator
 * 
 */
@Service
public class BillService {

	private Logger log = LoggerFactory.getLogger(BillService.class);
	@Autowired
	private BillDao billDao;

	// 获取订单列表
	public List<VerifyResponseBean> getList() {
		return billDao.list();
	}
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate;

	/**
	 * 根据订单号获取订单数据
	 * 
	 * @param bid
	 * @return
	 */
	public VerifyResponseBean getBillById(Long bid) {
		return billDao.getBillById(bid);
	}
	
	public void setFlowRedis(String bill_record){
		try {
			log.info("[bill Order String]="+bill_record);
			//获取订单流水记录
			JSONObject jsonStr = JSONObject.parseObject(bill_record);
			//获取店铺id
			String sellerid = jsonStr.getString("sellerid");
			//获取寻蜜客id
			String xmer_uid = jsonStr.getString("xmer_uid");
			//获取订单的金额
			Double money = Double.parseDouble(jsonStr.getString("money"));
			//获取redis缓存的商户消费流水key
			String sellerFlowKey = Constant.SELLER_FLOW_KEY + sellerid;
			//获取寻蜜客信息redis缓存key
			String xmerInfoKey = Constant.XMER_INFO_KEY + xmer_uid;
			//历史总流水额
			Double allAmount = 0D;
			//本月流水额
			Double monthAmount = 0D;
			//月份
			Integer monthNum = 0;
			//寻蜜客总交易流水金额
			Double flowAmount = 0D;
			if(stringredisTemplate.hasKey(sellerFlowKey)){
				//查询redis获取消费流水
				Map<Object, Object> flowMap = stringredisTemplate.opsForHash().entries(sellerFlowKey);
				
				allAmount = Double.parseDouble((String)flowMap.get("allAmount"));
				allAmount += money;
				monthNum = Integer.parseInt((String) flowMap.get("monthNum"));
				monthAmount = Double.parseDouble((String)flowMap.get("monthAmount"));
				Calendar calendar = Calendar.getInstance();
				if (monthNum == calendar.get(Calendar.MONTH)) {
					monthAmount += money;
				}else {
					monthAmount = money;
					monthNum = calendar.get(Calendar.MONTH);
				}
				stringredisTemplate.opsForHash().put(sellerFlowKey, "allAmount", allAmount+"");
				stringredisTemplate.opsForHash().put(sellerFlowKey, "monthAmount", monthAmount+"");
				stringredisTemplate.opsForHash().put(sellerFlowKey, "monthNum", monthNum+"");
			}else{
				allAmount = billDao.queryTotalflow(Integer.parseInt(sellerid));
				monthAmount = billDao.queryMonthflow(Integer.parseInt(sellerid));
				Calendar calendar = Calendar.getInstance();
				monthNum = calendar.get(Calendar.MONTH);
				stringredisTemplate.opsForHash().put(sellerFlowKey, "allAmount", allAmount+"");
				stringredisTemplate.opsForHash().put(sellerFlowKey, "monthAmount", monthAmount+"");
				stringredisTemplate.opsForHash().put(sellerFlowKey, "monthNum", monthNum+"");
			}
			if (stringredisTemplate.hasKey(xmerInfoKey)) {
				Map<Object, Object> xmerMap = stringredisTemplate.opsForHash().entries(xmerInfoKey);
				if (xmerMap.get("flowAmount") != null) {
					flowAmount = Double.parseDouble((String) xmerMap.get("flowAmount"));
				}
				flowAmount += money;
				stringredisTemplate.opsForHash().put(xmerInfoKey, "flowAmount", flowAmount+"");
			}else {
				flowAmount = billDao.queryFlowAmount(Integer.parseInt(xmer_uid));
				stringredisTemplate.opsForHash().put(xmerInfoKey, "flowAmount", flowAmount+"");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
