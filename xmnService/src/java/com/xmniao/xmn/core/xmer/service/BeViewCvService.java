package com.xmniao.xmn.core.xmer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.dao.BeViewCvDao;

@Service
public class BeViewCvService {

	//注入dao
	@Autowired
	private BeViewCvDao beViewCvDao;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;

	public Object beViewCvService(BaseRequest baseRequest){
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();//存响应数据
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();//最终存储数据
		String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()).toString();//获取用户id
		if(uid.equals("") || uid.equals("null")){
			return new BaseResponse(ResponseCode.TOKENERR,"token已过期，请重新登录");
		}
		try{
			Integer cvid = beViewCvDao.queryUserCvId(Integer.valueOf(uid));
			if(cvid == null){
				return new BaseResponse(ResponseCode.FAILURE, "用户不存在");
			}
			//获取看我的用户id和时间
			List<Map<Object,Object>> suid = beViewCvDao.queryRecruitView(cvid); 
			if(suid.size() <1 || suid == null){
				return new BaseResponse(ResponseCode.SUCCESS,"没有人看过我！^_^");
			}
			//获取看过我的商铺信息
			List<Map<Object,Object>> sellerInfo = beViewCvDao.sellerInfoList(suid);
			
			for(Map<Object,Object> suidMap : suid){
				for(Map<Object,Object> sellerInfoMap : sellerInfo){
					if(suidMap.get("suid").toString().equals(sellerInfoMap.get("sellerid").toString())){
						sellerInfoMap.put("viewTime", suidMap.get("viewtime"));
						result.add(sellerInfoMap);
					}
				}
			}
			//获取商户的logo
			List<Map<Object,Object>> logoList = beViewCvDao.querySellerLogoList(suid);
			for(Map<Object,Object> logoMap : logoList){
				for(Map<Object,Object> viewMap : result){
					if(logoMap.get("sellerid").toString().equals(viewMap.get("sellerid").toString())){
						viewMap.put("storepic", logoMap.get("picurl"));
						list.add(viewMap);
					}
				}
			}
			result.clear();
			//获取商铺的招聘信息
			List<Map<Object,Object>> recruitInfo = beViewCvDao.beViewCvList(suid);
			for(Map<Object,Object> recruitMap : recruitInfo){
				for(Map<Object,Object> listMap : list){
					if(recruitMap.get("sellerid").toString().equals(listMap.get("sellerid").toString())){
						String cityName = beViewCvDao.queryCityName(Integer.valueOf(recruitMap.get("city").toString()));
						recruitMap.put("workcity", cityName);
						recruitMap.remove("city");
						listMap.put("recruit", recruitMap);
						result.add(listMap);
					}
				}
			}
			map.clear();
			map.put("result", result);
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询失败");
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
		response.setResponse(map);
		return response;
	}
}
