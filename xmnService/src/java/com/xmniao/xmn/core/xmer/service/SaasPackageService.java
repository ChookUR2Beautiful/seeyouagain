package com.xmniao.xmn.core.xmer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.dao.SaasPackageDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;

/**
 * 
 * 项目名称： xmnService
 * 类名称： SaasPackageService
 * 类描述：获取押金(SAAS)套餐列表service层
 * 创建人： lifeng
 * 创建时间： 2016年5月19日下午7:21:14
 * 修改描述：修改套餐根据级别的折扣计算价格
 * 修改人 : zhengyaowen
 * @version
 */
@Service
public class SaasPackageService {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SaasPackageService.class);
	
	/*
	 * 注入DAO
	 */
	@Autowired
	private SaasPackageDao saasPackageDao;
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate;  
	
	/**
	 * 注入SessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private XmerDao xmerDao;
	
	/**
	 * 获取押金(SAAS)套餐列表
	 * @param BaseRequest
	 * @return Object
	 * @Description: 添加更新缓存
	 * @修改人 : zhengyaowen
	 */
	@SuppressWarnings("unchecked")
	public Object querySaasPackage(BaseRequest baseRequest) {
		try {
//			Integer uid = Integer.valueOf(sessionTokenService.getStringForValue(baseRequest.getSessiontoken())+"");
			
			//响应购买saas押金套餐信息
			List<Map<Object, Object>> saasPackageList = new ArrayList<Map<Object, Object>>();
			
			//根据UID查询寻蜜客信息
//			Xmer xmer = xmerDao.selectByUid(uid);
//			Integer levels = xmer.getLevels();				//级别
			
			//判断缓存中是否已存在
//			if(stringredisTemplate.hasKey(Constant.SAAS_PACKAGE_LIST_KEY)){
//				
//				//从缓存中获取
//				List<String> list = stringredisTemplate.opsForList().range(Constant.SAAS_PACKAGE_LIST_KEY, 0, -1);
//				saasPackageList = (List<Map<Object, Object>>) JSONObject.parse(list.get(0));
//			}else{
				
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("status", Constant.SAASPACKAGE_STATUS_NORMAL);// 数据状态正常
			saasPackageList = saasPackageDao.querySaasPackage(paramMap);
			
			//将数据存储到缓存中
//			if(saasPackageList != null && saasPackageList.size() > 0)
//				stringredisTemplate.opsForList().leftPush(
//						Constant.SAAS_PACKAGE_LIST_KEY, JSONObject.toJSON(saasPackageList).toString());
				
//			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("saas", saasPackageList);
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取押金(SAAS)套餐列表失败", e);
			return new BaseResponse(ResponseCode.FAILURE, "获取押金(SAAS)套餐列表失败");
		}
	}
	
	/**
	 * 
	* @Title: querySaasPackageList
	* @Description: 查询所有套餐
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> querySaasPackageList(Map<Object, Object> paramMap) {
		return saasPackageDao.querySaasPackage(paramMap);
	}

}
