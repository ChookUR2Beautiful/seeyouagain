package com.xmniao.xmn.core.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.dao.HelpDao;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.xmer.dao.XmerDao;

@Service
public class HelpService {
	
	@Autowired
	private HelpDao helpDao;
	


	/**
	 * 查询帮助列表
	* @Title: getHelpList
	* @Description: 
	* @return Object    返回类型
	* @throws
	 */
	public  Object getHelpList(BaseRequest baseRequest) {
		try {
			//查询帮助与反馈类型
			List<Map<Object, Object>> itemList=helpDao.queryItemList();
			if(itemList!=null&&itemList.size()>0){
				JSONArray jsonArray=new JSONArray();
				for(Map<Object,Object> map:itemList){	//遍历帮助类型
					Integer itemid=Integer.parseInt(map.get("id").toString());
					JSONObject itemJSON=new JSONObject();
					itemJSON.put("id", itemid);
					itemJSON.put("name", map.get("item"));
					List<Map<Object,Object>> infoList=helpDao.queryInfoByItemid(itemid);
					if(infoList!=null&&infoList.size()>0){
						JSONArray infoArray=new JSONArray();
						for(Map<Object, Object> infoMap:infoList){//遍历每种类型的具体帮助
							JSONObject infoJson=new JSONObject();
							infoJson.put("id", infoMap.get("id"));
							infoJson.put("title", infoMap.get("title"));
							infoJson.put("context", HtmlUtils.htmlUnescape(infoMap.get("context")+""));//处理HTML特殊字符串
							infoArray.add(infoJson);
						}
						itemJSON.put("infoList", infoArray);
					}
					jsonArray.add(itemJSON);
				}
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
				Map<Object,Object> map=new HashMap<Object, Object>();
				map.put("data", jsonArray);
				mapResponse.setResponse(map);
				return mapResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "未知错误");
	}


	/**
	 * 查询帮助具体信息
	* @Title: getHelpInfo
	* @Description: 
	* @return Object    返回类型
	* @throws
	 */
	public Object getHelpInfo(IDRequest idquery) {
		try {
			//根据ID查询具体帮助信息
		Map<Object, Object> infoMap=helpDao.queryInfoByid(idquery.getId());
		if(infoMap!=null){
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
			mapResponse.setResponse(infoMap);
			return mapResponse;
		}else{
			return new BaseResponse(ResponseCode.DATA_NULL, "没有找到具体帮助信息");
		}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new BaseResponse(ResponseCode.FAILURE,"未知错误");
	}
	
	

}
