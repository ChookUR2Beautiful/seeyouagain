package com.xmniao.xmn.core.xmer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.dao.XmerLevelDao;

/**
 * 
 * 项目名称： xmnService
 * 类名称： XmerLevelService
 * 类描述：成就详情列表service层
 * 创建人： lifeng
 * 创建时间： 2016年5月20日上午9:41:13
 * @version
 */
@Service
public class XmerLevelService {

	/*
	 * 注入DAO
	 */
	@Autowired
	private XmerLevelDao xmerLevelDao;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate; 
	
	public Object queryXmerLevel(BaseRequest baseRequest) {
		
		try {
		//引入json转换工具
		JSONObject jsonObject = new JSONObject();
		
		//存储成就详情列表数据
		List<Map<Object, Object>> xmerLevelList = new ArrayList<>();
		
		//用于json数据转换
		String jsonStr = "";
		
		//先从缓存命中成就详情列表
		if (stringredisTemplate.hasKey(Constant.XMER_LEVEL_KEY)) {
			jsonStr = stringredisTemplate.opsForValue().get(Constant.XMER_LEVEL_KEY);
			xmerLevelList = jsonObject.parseObject(jsonStr, java.util.List.class);
		}else {
			//查询成就详情列表数据，第一次查询数据库，将查询出来的结果存放进redis中
			xmerLevelList = xmerLevelDao.queryXmerLevel();
			jsonStr = jsonObject.toJSONString(xmerLevelList);
			stringredisTemplate.opsForValue().set(Constant.XMER_LEVEL_KEY, jsonStr);
		}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("levels", xmerLevelList);
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取成就详情列表失败!");
		}
	}

}
