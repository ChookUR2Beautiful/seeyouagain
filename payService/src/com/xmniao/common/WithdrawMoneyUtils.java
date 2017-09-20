package com.xmniao.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xmniao.service.CommonService;
@Component
public class WithdrawMoneyUtils {
	
	
	/** 提现公共服务   */
	@Autowired 
	private CommonService commonService;
	
	public boolean checkMaxMoney(String uId,String userType, Map<String, Object> wrRecordMap,double tMoney){
		
		double todayWithdrawals = commonService.getTodayWithdrawals(uId, userType);
		
		if ((todayWithdrawals + (double)wrRecordMap.get("commission") 
				              + (double)wrRecordMap.get("income")
				              + (double)wrRecordMap.get("wallet")
				              + (double)wrRecordMap.get("rebate")
//				              + (double)wrRecordMap.get("deposit")
				              ) > tMoney) {
			return true;
		}
		
		return false;
	}
	
	
	public boolean checkMenTion(Map<String, Object> mentionMap){
		
		if(mentionMap == null){
			return false;
		}else if(mentionMap.get("account") == null|| "".equals(String.valueOf(mentionMap.get("account")))){
			return false;
		}else if(mentionMap.get("bankname") == null ||"".equals(String.valueOf(mentionMap.get("bankname"))) ){
			return false;
		}else if(mentionMap.get("abbrev") == null||"".equals(String.valueOf(mentionMap.get("abbrev")))){
			return false;
		}else if(mentionMap.get("bank") == null||"".equals(String.valueOf(mentionMap.get("bank")))){
			return false;
		}else if( mentionMap.get("ispublic") == null|| "".equals(String.valueOf(mentionMap.get("ispublic")))){
			return false;
		}else if(mentionMap.get("idtype") == null|| "".equals(String.valueOf(mentionMap.get("idtype")))){
			return false;
		}else if(mentionMap.get("province") == null|| "".equals(String.valueOf(mentionMap.get("province")))){
			return false;
		}else if(mentionMap.get("cityname") == null|| "".equals(String.valueOf(mentionMap.get("cityname")))){
			return false;
		}else if(String.valueOf(mentionMap.get("ispublic")).equals("0")&&(mentionMap.get("mobileid") == null||"".equals(String.valueOf(mentionMap.get("mobileid"))))){
			return false;
		}else if(String.valueOf(mentionMap.get("ispublic")).equals("0")&&(mentionMap.get("identity") == null||"".equals(String.valueOf(mentionMap.get("identity"))))){
			return false;
		}
		
		return true;
	}

}
