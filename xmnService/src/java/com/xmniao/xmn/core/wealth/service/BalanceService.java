package com.xmniao.xmn.core.wealth.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.MonthConsAmount;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.wealth.dao.IncomeInfoDao;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.entity.Xmer;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：BalanceService   
* 类描述：   我的余额service
* 创建人：yezhiyong   
* 创建时间：2016年5月23日 上午9:55:47   
* @version    
*
 */
@Service
public class BalanceService {
	
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	/**
	 * 注入寻密客dao层
	 * */
	@Autowired
	private XmerDao xmerDao;
	
	//注入dao
	@Autowired
	private SaasOrderDao saasOrderDao;
	
	@Autowired
	private IncomeInfoDao incomeInfoDao;
	
	/**
	 * 读取配置文件
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 查询月消费金额
	 */
	@Autowired
	private MonthConsAmount monthConsAmount;
	
	/**
	 * 订单
	 */
	@Autowired
	private BillDao billDao;
	

	
	/**
	 * 
	* @Title: queryBalanceInfo
	* @Description: 
	* @return Object
	 */
	public Object queryBalanceInfo(BaseRequest baseRequest) {
		try {
			//获取uid
			String uid =  sessionTokenService.getStringForValue(baseRequest.getSessiontoken())+"";
			
			//查询寻密客信息
			Xmer xmer = xmerDao.selectByUid(Integer.valueOf(uid));
			if(xmer == null){
				return new BaseResponse(ResponseCode.FAILURE,"你还未成为寻蜜客，请速速加入寻蜜客！");
			}
			
			//管理费
			String manmageAmount=propertiesUtil.getValue("manmageAmount", "conf_xmer.properties");
			//可转出金额
			Double profit=0D;
			//已提现金额（转出金额）
			Double trunout=0D;
			//查询寻蜜鸟钱包信息
			Map<Object,Object> xmerWalletMap=xmerDao.queryXmerWalletByUid(Integer.parseInt(uid));
			if(xmerWalletMap!=null){
				profit=Double.valueOf(xmerWalletMap.get("profit")+"");
				trunout=Double.valueOf(xmerWalletMap.get("trunout")+"");
			}
			Double allincome=ArithUtil.add(profit, trunout);
			
			//我的总收入
		   	BigDecimal b1 = new BigDecimal(Double.toString(profit)); 
		    BigDecimal b2 = new BigDecimal(Double.toString(trunout)); 
		    BigDecimal b3 = new BigDecimal(Double.toString(allincome)); 
		    Map<Object,Object> result = new HashMap<Object,Object>();
			    
			result.put("profit",  b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());   
			result.put("trunout",  b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); 
			result.put("allincome",  b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); 
			iscantx(Integer.parseInt(uid), result,manmageAmount);
			manmageAmount=result.get("manmageAmount")+"";
			profit=ArithUtil.sub(profit,Double.valueOf(manmageAmount));
			//如果金额小于0,则默认为0
			if(profit<0D){
				profit = 0.00D;
			}
			//设置是否转出条件
			//消息内容
			String msg=""
					+ "寻蜜客满足以下条件即可随时转出，转出金额将直接转到寻蜜鸟余额账内。\n"
					+ "1、需签约完成2套SaaS软件;\n"
					+ "2、账号余额高于"+manmageAmount+"元;\n"
					+ "3、每月需要在平台内消费满300元(寻蜜客新加入6个月内免消费满300限制)，否则次月1号扣取50元手续费（寻蜜客新加入6个月内免扣手续费），扣费后亦可提现;\n"
					+ "收入确认条件详情见寻蜜客服务协议。";
			result.put("msg", msg);
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功!");
			response.setResponse(result);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"未知错误,失败"+e.getMessage());
		}
	}
	/**
	 * 
	* @Title: iscantx
	* @Description: 是否可转出条件判断
	* @return void
	 * @throws ParseException 
	 */
	public void iscantx(Integer uid,Map<Object, Object> balanceMap,String manmageAmount) throws IOException, ParseException{
		//卖出多少可提现
		String sellNum=propertiesUtil.getValue("sellNum", "conf_xmer.properties");
		int signCount = saasOrderDao.getSignCount(uid);
		balanceMap.put("manmageAmount", manmageAmount);
		if(signCount < Integer.valueOf(sellNum)){	//签约上线的店铺少于限制的套数
			balanceMap.put("iscantx", 0);
			balanceMap.put("tipmsg", "条件：签约上线的商铺套数不足"+sellNum+"套");
			return;
		}
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("uid", uid);
		
		Xmer xmer=xmerDao.queryXmerByUid(paramMap);
		//判断是否成为寻蜜客有三个月
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         Calendar calender = Calendar.getInstance();
         calender.setTime(xmer.getSdate());
         calender.add(Calendar.MONTH, 6);
         String addDate=simpleDateFormat.format(calender.getTime());
         //3个月以内月消费金额不限制
         if(simpleDateFormat.parse(addDate).getTime()<=System.currentTimeMillis()){
        	//月消费多少可提现
     		String consAmount=propertiesUtil.getValue("consAmount", "conf_xmer.properties");
     		//月消费
     		double monthAmount=monthConsAmount.getMonthConsAmount(uid);
     		if(monthAmount<Double.parseDouble(consAmount)){
     			balanceMap.put("iscantx", 0);
     			balanceMap.put("tipmsg", "条件：本月消费金额还差"+ArithUtil.sub(Double.parseDouble(consAmount),monthAmount)+"元");
     			return;
     		}
         }else{
        	 manmageAmount="0";
        	 balanceMap.put("manmageAmount", 0);
         }
		
		//管理费
		
		
		//查询寻蜜客钱包记录
		Map<Object,Object> xmerWalletMap=xmerDao.queryXmerWalletByUid(uid);
		
		//可转出金额
		Double profit=0D;
		if(xmerWalletMap!=null){
			profit=Double.valueOf(xmerWalletMap.get("profit")+"");
		}
		if(profit<=Double.valueOf(manmageAmount)){
			balanceMap.put("iscantx", 0);
			balanceMap.put("tipmsg", "条件：可提现金额不足");
			return;
		}
		balanceMap.put("iscantx", 1);
		balanceMap.put("tipmsg", "");
	}

}
