package com.xmniao.xmn.core.api.controller.ledger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.dao.XmerDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：GetXmerListApi   
* 类描述：   给支付系统提供查询寻蜜客列表信息接口
* 创建人：yezhiyong   
* 创建时间：2017年2月15日 上午10:37:02   
* @version    
*
 */
@Controller
public class GetXmerListInfoApi {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(GetXmerListInfoApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入xmerDao
	 */
	@Autowired
	private XmerDao xmerDao;
	
	/**
	 * 
	* @Title: GetXmerListInfo
	* @Description:  给支付系统提供查询寻蜜客列表信息接口
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/ledger/system/xmerListInfo" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object GetXmerListInfo(Integer page,Integer pageSize,Integer type,String uids,String nname,String phone){
		try {
			//不传type或者传type等于0，则默认查询所有的寻蜜客列表信息,否则是分页查询
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("type", 0);
			if (uids != null) {
				paramMap.put("uid", JSONObject.parseArray(uids, ArrayList.class));
			}
			paramMap.put("nname", nname);
			paramMap.put("phone", phone);
			if (type != null && type != 0) {
				//分页查询寻蜜客列表信息
				if (page == null || page < 1) {
					//分页查询，不传页码或者页码小于1，默认第一页
					page = 1;
				}
				
				if (pageSize == null || pageSize < 1) {
					//分页查询，不传页大小或者页大小小于1，默认给定20
					pageSize = 20;
				}
				
				//组装参数
				paramMap.put("type", 1);
				paramMap.put("page", page);
				paramMap.put("pageSize", pageSize);
				
			}
			
			//查询寻蜜客列表信息
			List<Map<Object, Object>> xmerListInfo = xmerDao.queryXmerListInfo(paramMap);
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("xmerListInfo", xmerListInfo);
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取寻蜜客列表信息成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("支付系统提供寻蜜客列表信息失败，错误信息如下：" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "未知错误,获取寻蜜客列表信息失败");
		}
	}
	
}
