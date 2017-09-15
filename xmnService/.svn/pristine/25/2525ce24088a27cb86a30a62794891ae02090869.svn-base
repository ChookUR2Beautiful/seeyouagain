package com.xmniao.xmn.core.xmer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.entity.Xmer;

/**   
 * 项目名称：xmnService   
 * 类名称：GetXmerMarkService   
 * 类描述： 寻蜜客标示查询(在进入寻蜜客主页前，调用此接口查询是否有注册寻蜜客资料)     
 * 创建人：zhengyaowen
 * 创建时间：2016年5月28日 下午2:41:02   
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version     
 */
@Service
public class GetXmerMarkService { 
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(GetXmerMarkService.class);
	
	/**
	 * 注入XmerDao
	 */
	@Autowired
	private XmerDao xmerDao;
	
	/**
	 * 注入saasOrderDao
	 */
	@Autowired
	private SaasOrderDao saasOrderDao;
	
	/**
	 * 注入SessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	* @Title: getXmerMark
	* @Description: 寻蜜客标示查询
	* @param BaseRequest
	* @return Object 返回类型
	* @author zhengyaowen
	* @Description 修改描述
	* @update 修改人
	* @date 修改日期
	* @throws
	*/
	public Object getXmerMark(BaseRequest baseRequest){
		
		try{
			//用户ID
			String sellesionUid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken())+"";
			if (StringUtils.isEmpty(sellesionUid) || "null".equalsIgnoreCase(sellesionUid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
				
			}
			Integer uid = Integer.parseInt(sellesionUid);
			log.info("uid:"+ uid);
			Map<Object,Object> result = new HashMap<Object,Object>();
			/*//查询是否解除过寻蜜客  ,如果解除过则不需 做任何操作
			Xmer relieveXmer = xmerDao.queryByUidAndStatus(uid);
			if(relieveXmer!=null){
				result.put("isrelieve", 1);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
				response.setResponse(result);
				return response;
			}*/
			//查询是否存在寻蜜客信息
			Xmer xmer = xmerDao.selectByUid(uid);
			
			List<Map<Object,Object>> saasOrderList = saasOrderDao.querySaasOrderList(uid);
			//如果不是寻蜜客
			if(null == xmer||saasOrderList==null||saasOrderList.size()==0){
				result.put("isxmer", 0);		//不是寻蜜客
				result.put("isrecommend", 0);	//没有推荐人
				result.put("isinfointact", 0);	//没有完善资料
				if(saasOrderList!=null&&saasOrderList.size()>0){
					result.put("isbuy", 1);	//已购买套餐      跳去完善资料
				}else{
					result.put("isbuy", 0);	//未购买套餐
				}
				
			}else{
				result.put("isxmer", 1);
				if(StringUtils.isNotBlank(xmer.getPhoneid()) && 
						StringUtils.isNotBlank(xmer.getEmail()) && 
							StringUtils.isNotBlank(xmer.getWeixinid())){
					result.put("isinfointact", 1); //资料是否完整  默认1；  1 完整
				}else{
					result.put("isinfointact", 0); //资料是否完整  默认1； 0 不完整      跳去完善资料
				}
				if(xmer.getParentid() == null || xmer.getParentid() == 0){
					result.put("isrecommend",0); //是否有他人推荐	默认0  0 不是  1 是
				}else{
					result.put("isrecommend",1); //是否有他人推荐	默认0  0 不是  1 是
				}
				result.put("isbuy", 1);	//已购买套餐   
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			response.setResponse(result);
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询寻蜜客标示失败！");
		}
		
	}
}
