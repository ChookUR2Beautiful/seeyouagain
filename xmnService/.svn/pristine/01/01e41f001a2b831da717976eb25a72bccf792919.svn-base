/**
 * 2016年8月15日 下午4:48:22
 */
package com.xmniao.xmn.core.live.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.EggToBirdMoneyDao;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：EggToBirdMoneyService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月15日 下午4:48:22
 * @version
 */
@Service
public class EggToBirdMoneyService {
	//日志
	private final Logger log = Logger.getLogger(EggToBirdMoneyService.class);
	@Autowired
	private EggToBirdMoneyDao eggtobirdmoneyDao;
	
	@Autowired
	private PersonalCenterService personalcenterService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 
	* @Title: updateEggToBirdMoney
	* @Description: 鸟蛋转出鸟币余额
	* @return Object
	 */
	public Object updateEggToBirdMoney(int uid,Double eggNum)
	{
		
		
		try {
			Map<Object,Object> AnchorMap=personalcenterService.queryLiverPersonByUid(uid);
			if(AnchorMap==null || AnchorMap.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "未到获取主播个人信息，鸟蛋转出余额失败");
			}
			
			Map<String,String> WalletInfo=personalcenterService.getLiveWalletInfo(uid+"");
	 		
			if(WalletInfo==null || WalletInfo.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "未获取到个人钱包，鸟蛋转出余额失败");
			}
			
			Double balance=Double.parseDouble(WalletInfo.get("balance").toString());
			
			if(balance<eggNum || eggNum<=0){
				return new BaseResponse(ResponseCode.FAILURE, "不能输入大于当前鸟蛋的数量且不能小于等于0");
			}
			
			Map<String,String> parambl=new HashMap<String,String>();
			String devideScale=AnchorMap.get("ledger_ratio").toString();
			parambl.put("uid", uid+"");//寻蜜鸟用户uid
			parambl.put("money", eggNum+"");//转出金额
			parambl.put("percent", "1");//分账比例
			ResponseData responseData=personalcenterService.turnoutLiveWallet(parambl);
			if(responseData.getState()!=0){
				log.error("转出余额失败，失败描述："+responseData.getMsg());
				return	new BaseResponse(ResponseCode.FAILURE, "转出余额失败，失败描述："+responseData.getMsg());
			}
			log.info("鸟蛋转出余额成功:"+uid);
			return new BaseResponse(ResponseCode.SUCCESS, "鸟蛋转出余额成功");
		} catch (Exception e) {
			log.error("鸟蛋转出鸟币失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: queryBirdEggByUid
	* @Description: 查询当前鸟蛋和钱包状态
	* @return Map<Object,Object>
	 */
	public Map<Object,Object> queryBirdEggByUid(int uid){
		Map<Object,Object> eggMap=null;
		try {
			eggMap=eggtobirdmoneyDao.queryBirdEggByUid(uid);
		} catch (Exception e) {
			log.error("查询当前鸟蛋和钱包状态失败");
			e.printStackTrace();
		}
		return eggMap;
	}
}
