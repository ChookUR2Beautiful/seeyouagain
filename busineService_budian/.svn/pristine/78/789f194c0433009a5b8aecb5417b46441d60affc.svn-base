package com.xmniao.service.order;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.dao.common.CommonServiceDao;
import com.xmniao.dao.order.MaterialOrderDao;
import com.xmniao.domain.message.MessageRequest;
import com.xmniao.domain.order.MaterialOrderBean;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.order.MaterialOrderService;

@Service("materialOrderServiceImpl")
public class MaterialOrderServiceImpl implements MaterialOrderService.Iface{
	private final static Logger logger = LoggerFactory.getLogger(MaterialOrderServiceImpl.class);
	
	@Autowired
	private MaterialOrderDao materialOrderDao;

	@Autowired
	private CommonServiceImpl commonService;
	
	@Resource(name="materialPhone")
	private String smsPhone;
	
	@Override
	@Transactional
	public Map<String, String> notifyForPayComplete(Map<String, String> params) throws FailureException, TException {
		logger.info("物料订单支付完成通知，参数:{}",JSON.toJSONString(params));
		
		String orderSn = params.get("orderSn");
		
		if(StringUtils.isEmpty(orderSn)){
			throw new FailureException(1001,"参数orderSn不能为空");
		}
		
		String status = params.get("status");
		
		if(StringUtils.isEmpty(status)){
			throw new FailureException(1002,"参数status不能为空");
		}
		
		if(!("1".equals(status) || "2".equals(status))){
			throw new FailureException(1003,"参数status值必须为1或2");
		}
		
		MaterialOrderBean bean = materialOrderDao.getByOrderSn(orderSn);
		
		if(bean == null){
			throw new FailureException(1004,"订单不存在");
		}
		
		if(bean.getStatus().intValue() != 0 && bean.getStatus().intValue() != 2){
			throw new FailureException(1005,"重复通知，订单非待支付/取消支付状态");
		}
		
		Map<String, String> result = new HashMap<String, String>();
		
		if(bean.getStatus().intValue() == Integer.valueOf(status).intValue()){
			result.put("is_success", "T");
			return result;
		}
		
		bean.setStatus(Integer.valueOf(status));
		
		bean.setPayid(params.get("payid"));
		bean.setPaymentType(params.get("paymentType"));
		bean.setThirdUid(params.get("thirdUid"));
		bean.setThirdSerial(params.get("thirdSerial"));
		
		if(StringUtils.isNotEmpty(params.get("cash"))){
			bean.setCash(new BigDecimal(params.get("cash")));
		}else{
			bean.setCash(new BigDecimal("0"));
		}
		
		if(StringUtils.isNotEmpty(params.get("balance"))){
			bean.setBalance(new BigDecimal(params.get("balance")));
		}else{
			bean.setBalance(new BigDecimal("0"));
		}
		
		if(StringUtils.isNotEmpty(params.get("integral"))){
			bean.setIntegral(new BigDecimal(params.get("integral")));
		}else{
			bean.setIntegral(new BigDecimal("0"));
		}
		
		bean.setModifyTime(new Date());
		
		int count = materialOrderDao.updateForPayComplete(bean);
		
		if(count <= 0){
			throw new FailureException(1006,"并发更新，版本号冲突");
		}
		
		this.sendSms(orderSn);
		
		result.put("is_success", "T");
		return result;
	}

    /**
     * 发送短信处理
     * @param resOrderMap [订单信息MAP]
     * @param phoneid [手机号码]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
	/*
	 * 订单号，客户姓名，客户电话，客户店铺名称，下单时间，订单详情：xx物料X数量
	 * 您有新的物料订单，请注意查看。订单号【12121221】，客户姓名：联系方式：店铺名：下单时间：物料详情：
	 */
    public void sendSms(String orderSn)
    {
        try
        {
        	if(StringUtils.isBlank(smsPhone)){
        		logger.error("下发短信号码为空，不发送短信");
        		return ;
        	}
        	Map<String,Object> orderMap = materialOrderDao.getMaterialOrderInfo(orderSn);
        	List<Map<String,Object>> itemList = materialOrderDao.getMaterialOrderItem(orderSn);
        	StringBuilder smsSb = new StringBuilder();
        	smsSb.append("物料订单（").append(orderMap.get("order_sn")).append("）下单成功！");
        	smsSb.append("收货人【").append(orderMap.get("consignee")).append("】，");
        	smsSb.append("联系方式【").append(orderMap.get("mobile")).append("】，");
        	smsSb.append("店铺名【").append(orderMap.get("sellername")).append("】，");
        	smsSb.append("下单时间【").append(DateUtil.getCurrentTimeStr()).append("】，");
        	smsSb.append("物料详情：");
        	for(int i=0;i<itemList.size();i++){
        		if(i>5){
        			smsSb.append("等"+itemList.size()+"种物料。");
        			break;
        		}else{
            		Map<String,Object> itemMap = itemList.get(i);
            		smsSb.append(itemMap.get("material_name")).append(" ").append(itemMap.get("quantity")).append("份、");
            		
        		}
        	}
        	
            MessageRequest mr = new MessageRequest();
            mr.setContent(smsSb.toString());
            mr.setMobileId(smsPhone);
            commonService.sendSms(mr);
        }
        catch (Exception e)
        {
        	logger.error("发送短信出现异常",e);
        }
    }
    
}
