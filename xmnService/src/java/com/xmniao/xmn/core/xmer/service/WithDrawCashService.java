package com.xmniao.xmn.core.xmer.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.thrift.FailureException;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.XmerWalletService;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.wealth.service.BalanceService;
import com.xmniao.xmn.core.xmer.dao.WithDrawCashDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;

/**
 * 
* 项目名称：xmnService   
* 类名称：WithDrawCashService   
* 类描述：提现接口  
* 创建人：liuzhihao   
* 创建时间：2016年5月23日 上午9:26:11   
* @version    
*
 */
@Service
public class WithDrawCashService {
	//日志报错
		private final Logger log = Logger.getLogger(WithDrawCashService.class);
	//注入提现接口DAO
	@Autowired
	private WithDrawCashDao withDrawCashDao;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private BalanceService balanceService;
	

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate;
	
	/**
	 * 支付服务接口
	 */
	@Resource(name = "xmerWalletServiceClient")
	private ThriftClientProxy xmerWalletServiceClient;
	
	/**
	 * 综合服务接口
	 */
	@Resource(name="synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;
	
	
	//注入寻蜜客dao
	@Autowired
	private XmerDao xmerDao;
	
	/**
	 * 
	* @Title: withDrawCash
	* @Description:提现接口实现类
	* @return Object    返回类型
	* @throws
	 */
	public Object withDrawCash(BaseRequest baseRequest){
		try{
			//创建查询参数map实例
			Map<Object,Object> map = new HashMap<Object,Object>();
			//从缓存中获取用户id
			Integer uid = Integer.valueOf(sessionTokenService.getStringForValue(baseRequest.getSessiontoken()).toString());
			// 根据uid查询钱包信息
			Map<Object,Object> xmerWalletMap = withDrawCashDao.queryXmerWalletId(uid);
			if(xmerWalletMap == null){
				return new BaseResponse(ResponseCode.DATA_NULL, "寻蜜客电子钱包(账户)不存在");
			}
			//获取配置文件押金
			String manmageAmount=propertiesUtil.getValue("manmageAmount", "conf_xmer.properties");
			balanceService.iscantx(uid, map,manmageAmount);
			if("0".equals(String.valueOf(map.get("iscantx")))){
				return new BaseResponse(ResponseCode.FAILURE,String.valueOf(map.get("tipmsg")));
			}
			manmageAmount=map.get("manmageAmount")+"";//寻蜜客加入3个月无需管理费
			Double profit=Double.valueOf(xmerWalletMap.get("profit")+"");
			XmerWalletService.Client client =null;
			try {
				//调用支付服务申请提现
				client = (XmerWalletService.Client)(xmerWalletServiceClient.getClient());
				Map<String,String> orderMap = new HashMap<String,String>();	
				orderMap.put("id", xmerWalletMap.get("id").toString());
				orderMap.put("uid", uid.toString());
				orderMap.put("turnOutAll", "0");
				orderMap.put("money", ArithUtil.sub(profit,Double.valueOf(manmageAmount))+"");
				ResponseData responseData =	client.turnOutXmerWallet(orderMap);
				if(responseData.getState()==0){
					try {
						//新增提现记录
						Map<String,Object> xmerWalleRecordtMap=new HashMap<String, Object>();
						profit=ArithUtil.sub(profit,Double.valueOf(manmageAmount));
						xmerWalleRecordtMap.put("profit", profit);//转出金额
						xmerWalleRecordtMap.put("rtype", 1);
						xmerWalleRecordtMap.put("xin",xmerWalletMap.get("id"));//寻蜜客钱包ID
						xmerWalleRecordtMap.put("hprofit",manmageAmount);//转出后(押金)
						xmerWalleRecordtMap.put("qprofit", xmerWalletMap.get("profit"));
						xmerWalleRecordtMap.put("sdate", new Date());
						xmerWalleRecordtMap.put("remark", uid);
						withDrawCashDao.insertXmerWalletRecord(xmerWalleRecordtMap);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("添加转出记录失败");
					}
					return new BaseResponse(ResponseCode.SUCCESS,"转出成功");
				}else{
					return new BaseResponse(ResponseCode.FAILURE,"转出失败："+responseData.getMsg());
				}
			}catch (FailureException e) {
				log.error("寻蜜客转出金额失败,错误信息："+e.getInfo()+"，用户："+uid);
				if(xmerWalletServiceClient != null){
					xmerWalletServiceClient.returnCon();
				}
				return new BaseResponse(ResponseCode.FAILURE,"寻蜜客转出金额失败");
				
			} catch (TException e) {
				log.error("寻蜜客转出金额失败,错误信息："+e.getMessage()+"，用户："+uid);
				if(xmerWalletServiceClient != null){
					xmerWalletServiceClient.returnCon();
				}
				
				return new BaseResponse(ResponseCode.FAILURE,"寻蜜客转出金额失败");
				
			}catch (Exception e){
				log.error("寻蜜客转出金额失败,错误信息："+e.getMessage()+"，用户："+uid);
				if(xmerWalletServiceClient != null){
					xmerWalletServiceClient.returnCon();
				}
				return new BaseResponse(ResponseCode.FAILURE,"寻蜜客转出金额失败");
				
			}finally {
				if (xmerWalletServiceClient != null){
					xmerWalletServiceClient.returnCon();
				}
			}

			
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"未知错误，请联系管理员");
		}
	}
	
	/**
	 * 
	* @Title: setWithDrawCashRedis
	* @Description: 设置提现明细缓存
	* @return void    返回类型
	* @throws
	 */
	public void setWithDrawCashRedis(String wallet_withdraw) {
		try {
			//提现明细
			JSONObject jsonStr = JSONObject.parseObject(wallet_withdraw);
			//提现金额类型
			String type = jsonStr.getString("type");
			//提现金额
			String amount = jsonStr.getString("amount");
			//用户id
			String uid = jsonStr.getString("uid");
			//提现明细redis缓存key
			String myDepositKey = Constant.MY_DEPOSIT_KEY + uid;
			//获取寻蜜客信息redis缓存key
			String xmerInfoKey = Constant.XMER_INFO_KEY + uid;
			if ("1".equals(type)) {
				//存储提现明细信息
				Map<Object, Object> map = new HashMap<>();
				map.put("description", jsonStr.getString("description"));
				map.put("sdate", jsonStr.getString("sdate"));
				map.put("amount", jsonStr.getString("amount"));
				map.put("status", 1);
				stringredisTemplate.opsForZSet().add(myDepositKey, JSONObject.toJSONString(map), DateUtil.parse(DateUtil.getCurrentTimeStr()).getTime());
				if (stringredisTemplate.hasKey(xmerInfoKey)) {
					Double returnDeposit = Double.parseDouble((String)stringredisTemplate.opsForHash().get(xmerInfoKey, "returnDeposit"));
					if (returnDeposit > Double.parseDouble(amount)) {
						returnDeposit -= Double.parseDouble(amount);
					}else {
						returnDeposit = 0D;
					}
					stringredisTemplate.opsForHash().put(xmerInfoKey, "returnDeposit", returnDeposit + "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	
	private boolean isDate(Date date){
		int year = DateUtil.year(date);//获取寻蜜客加入时的年份
		int nyear = DateUtil.year();//获取当前系统时间的年份
		int mouth = DateUtil.month(date);//获取寻蜜客加入时的月份
		int nmouth = DateUtil.month();//获取当前系统时间的月份
		if(year == nyear){
			if((nmouth-mouth) < 4){
				return true;
			}
		}
		return false;
	} 
}
