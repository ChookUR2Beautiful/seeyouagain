package com.xmniao.xmn.core.personal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.api.controller.personal.ReceivingAddressListApi;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.personal.AddReceivingAddressRequest;
import com.xmniao.xmn.core.common.request.personal.DeleteReceivingAddressRequest;
import com.xmniao.xmn.core.common.request.urs.UserAddressRequest;
import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import com.xmniao.xmn.core.personal.dao.ReceivingAddressDao;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ReceivingAddressService   
* 类描述：   收货地址管理service
* 创建人：yezhiyong   
* 创建时间：2016年11月11日 下午5:17:24   
* @version    
*
 */
@Service
public class ReceivingAddressService {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ReceivingAddressListApi.class);
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入receivingAddressDao
	 */
	@Autowired
	private ReceivingAddressDao receivingAddressDao;
	
	/**
	 * 注入sellerInfoDao
	 */
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 
	* @Title: queryReceivingAddressList
	* @Description: 获取收货地址列表信息
	* @return Object    返回类型
	* @throws
	 */
	public Object queryReceivingAddressList(UserAddressRequest userAddressRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(userAddressRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			resultMap.put("receivingAddressList", resultList);
			
			//用户的收货地址列表
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("uid", uid);
			paramMap.put("isdefault", userAddressRequest.getDefaults());
			
			List<ReceivingAddress> receivingAddressList = receivingAddressDao.queryReceivingAddressListByUid(paramMap);
			//调整返回数据
			if (receivingAddressList != null && receivingAddressList.size() > 0) {
				for (ReceivingAddress receivingAddress : receivingAddressList) {
					Map<Object, Object> map = new HashMap<>();
					map.put("receivingAddressId", receivingAddress.getId());
					map.put("userName", null==receivingAddress.getUsername()?"":receivingAddress.getUsername());
					map.put("province", null==receivingAddress.getProvince()?"":receivingAddress.getProvince());
					map.put("provinceId", receivingAddress.getProvinceid());
					map.put("cityId", receivingAddress.getCityid());
					map.put("areaId", receivingAddress.getAreaid());
					map.put("city", null==receivingAddress.getCity()?"":receivingAddress.getCity());
					map.put("areaName", null==receivingAddress.getAreaname()?"":receivingAddress.getAreaname());
					map.put("address", null==receivingAddress.getAddress()?"":receivingAddress.getAddress());
					map.put("phoneId", null==receivingAddress.getPhoneid()?"":receivingAddress.getPhoneid());
					map.put("isDefault",null== receivingAddress.getIsdefault()?0:receivingAddress.getIsdefault());
					resultList.add(map);
				}
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取用户收货地址列表信息成功");
			resultMap.put("livePointMark", propertiesUtil.getValue("livePointMark", "conf_live.properties"));
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取用户收货地址列表失败,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取用户收货地址列表信息失败");
		}
	}

	/**
	 * 
	* @Title: addReceivingAddress
	* @Description: 添加收货地址
	* @return Object    返回类型
	* @throws
	 */
	public Object addReceivingAddress(AddReceivingAddressRequest addReceivingAddressRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(addReceivingAddressRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}

		try {
			//组装参数
			Map<Object, Object> paramMap = this.getReceivingAddressParamMap(addReceivingAddressRequest);
			paramMap.put("uid", Integer.parseInt(uid));
			paramMap.put("region", 86);
			paramMap.put("dstatus", 0);//'数据状态 默认0     0正常    1已删除',
			
			if (addReceivingAddressRequest.getIsDefault() == 1) {
				//如果新添的收货地址是默认地址，则先取消其他默认收货地址
				receivingAddressDao.updateReceivingAddressByUid(Integer.parseInt(uid));
			}
			
			//添加收货地址
			receivingAddressDao.insertReceivingAddress(paramMap);
			//响应
			return new BaseResponse(ResponseCode.SUCCESS, "添加收货地址成功");
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.info("添加收货地址失败,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "添加收货地址失败");
		}
	}
	
	/**
	 * 
	* @Title: getParamMap
	* @Description: 组装收货地址的参数
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	public Map<Object, Object> getReceivingAddressParamMap(AddReceivingAddressRequest addReceivingAddressRequest) {
		//组装参数
		Map<Object, Object> paramMap = new HashMap<>();
		
		paramMap.put("username", addReceivingAddressRequest.getUserName());
		paramMap.put("phoneId", addReceivingAddressRequest.getPhoneId());
		
		String province = addReceivingAddressRequest.getProvince().trim();
		paramMap.put("province", province);
		paramMap.put("city", addReceivingAddressRequest.getCity());
		paramMap.put("areaname", addReceivingAddressRequest.getAreaName()==null?"":addReceivingAddressRequest.getAreaName());
		paramMap.put("provinceId", addReceivingAddressRequest.getProvinceId());
		paramMap.put("cityId", addReceivingAddressRequest.getCityId());
		paramMap.put("areaId", addReceivingAddressRequest.getAreaId());
		paramMap.put("address", addReceivingAddressRequest.getAddress()==null?"":addReceivingAddressRequest.getAddress());
		paramMap.put("isDefault", addReceivingAddressRequest.getIsDefault());//'默认0    0不是默认地址    1是默认地址，添加第1条地址时默认为1'
		paramMap.put("rdate", DateUtil.format(new Date()));
		paramMap.put("udate", DateUtil.format(new Date()));
		
		return paramMap;
		
	}

	/**
	 * 
	* @Title: modifyReceivingAddress
	* @Description: 编辑收货地址
	* @return Object    返回类型
	* @throws
	 */
	public Object modifyReceivingAddress(AddReceivingAddressRequest addReceivingAddressRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(addReceivingAddressRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
				
		try {
			if (addReceivingAddressRequest.getReceivingAddressId() == null) {
				return new BaseResponse(ResponseCode.FAILURE, "提交的参数不正确");
			}
			
			//查询是否存在以前的收货记录
			Map<Object, Object> receivingAddressMap = receivingAddressDao.queryReceivingAddressById(addReceivingAddressRequest.getReceivingAddressId());
			if (receivingAddressMap == null || receivingAddressMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "查无编辑的历史收货地址");
			}
			
			if (addReceivingAddressRequest.getIsDefault() == 1) {
				//如果编辑的收货地址是默认地址，则先取消其他默认收货地址
				receivingAddressDao.updateReceivingAddressByUid(Integer.parseInt(uid));
			}
			
			//组装参数
			Map<Object, Object> receivingAddressParamMap = this.getReceivingAddressParamMap(addReceivingAddressRequest);
			receivingAddressParamMap.put("receivingAddressId", addReceivingAddressRequest.getReceivingAddressId());
			//更新收货地址
			receivingAddressDao.updateReceivingAddressById(receivingAddressParamMap);
			
			return new BaseResponse(ResponseCode.SUCCESS, "编辑收货地址信息成功");
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.info("编辑收货地址信息失败,uid=" + uid + "错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "编辑收货地址信息失败");
		}
	}

	/**
	 * 
	* @Title: deleteReceivingAddress
	* @Description: 删除收货地址
	* @return Object    返回类型
	* @throws
	 */
	public Object deleteReceivingAddress(DeleteReceivingAddressRequest deleteReceivingAddressRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(deleteReceivingAddressRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//查询是否存在以前的收货记录
			Map<Object, Object> receivingAddressMap = receivingAddressDao.queryReceivingAddressById(deleteReceivingAddressRequest.getReceivingAddressId());
			if (receivingAddressMap == null || receivingAddressMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "您已经删除此收货地址了");
			}
			
			//删除收货地址信息(软删除)
			Map<Object, Object> receivingAddressParamMap = new HashMap<>();
			receivingAddressParamMap.put("receivingAddressId", deleteReceivingAddressRequest.getReceivingAddressId());
			receivingAddressParamMap.put("dstatus", 1);//'数据状态 默认0     0正常    1已删除'
			receivingAddressDao.updateReceivingAddressById(receivingAddressParamMap);
			return new BaseResponse(ResponseCode.SUCCESS, "删除收货地址信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("删除收货地址信息失败,uid=" + uid + "错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "删除收货地址信息失败");
		}
	}

}
