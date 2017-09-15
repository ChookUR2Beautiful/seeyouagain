package com.xmniao.xmn.core.live.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.util.StrUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.common.request.UidRequest;
import com.xmniao.xmn.core.live.dao.AnchorManagerDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveManagerInfo;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;


/**
 * 项目描述：XmnService
 * API描述：主播管理员操作Service
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */

@Service
public class AnchorManagerService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(AnchorManagerService.class);
	
	@Autowired
	private String fileUrl;
	
	/**
	 * 直播用户DAO
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 主播管理员DAO
	 * */
	@Autowired
	private AnchorManagerDao anchorManagerDao;
	
	@Autowired
	private StringRedisTemplate stringredisTemplate;
	
	/**
	 * 注入缓存sessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入缓存propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 描述：添加主播管理员
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55 
	 * */
	public Object addAnchorManager(UidRequest uidRequest){
		//响应
		BaseResponse response = null;
		//主播uid
		String anchorUid = "";
		//管理员uid
		Integer managerUid = null;
		try {
			//验证token
			anchorUid = sessionTokenService.getStringForValue(uidRequest.getSessiontoken()) + "";
			if ("null".equalsIgnoreCase(anchorUid) || StringUtils.isEmpty(anchorUid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效");
			}
			
			//管理员uid
			managerUid = uidRequest.getUid();
			//查询管理员的直播用户信息
			Map<Object, Object> managerLiverMap = liveUserDao.queryLiverInfoByUid(managerUid);
			if (managerLiverMap == null || managerLiverMap.size() < 1) {	
				return new BaseResponse(ResponseCode.FAILURE, "此用户不支持设置为管理员");
			}
			
			//查询主播的直播用户信息
			Map<Object, Object> anchorLiverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(anchorUid));
			if (anchorLiverMap == null || anchorLiverMap.size() < 1) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此主播信息");
			}
			
			//查询主播的管理员数量
			Integer managerSum = anchorManagerDao.queryAnchorManagerSum(Integer.parseInt(anchorLiverMap.get("id").toString()));
			Integer maxManagerNums = Integer.parseInt(propertiesUtil.getValue("maxManagerNums", "conf_live.properties"));
			if (managerSum >= maxManagerNums) {
				return new BaseResponse(ResponseCode.FAILURE, "您设置的管理员已达到上限");
			}
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("manager_id", managerLiverMap.get("id"));
			paramMap.put("anchor_id", anchorLiverMap.get("id"));
			
			//查询是否已经是该主播的管理员
			Map<Object, Object> managerMap = anchorManagerDao.queryAnchorManager(paramMap);
			if (managerMap != null && managerMap.size() > 0) {
				return new BaseResponse(ResponseCode.FAILURE, "您已添加此管理员");
			}
			
			paramMap.put("status", 1);
			paramMap.put("create_time", DateUtil.format(new Date()));
			int result = anchorManagerDao.addAnchorManagerInfo(paramMap);
			if (result>0) {
				response = new BaseResponse(ResponseCode.SUCCESS, "添加管理员成功");
			}
			
		} catch (Exception e) {
			log.info("添加管理员失败,错误信息如下:主播uid=" + anchorUid + ",添加管理员uid=" + managerUid + ",错误详情:" + e.getMessage());
			e.printStackTrace();
			response = new BaseResponse(ResponseCode.FAILURE, "添加管理员失败");
		}
		return response;
	}
	
	
	/**
	 * 描述：获取主播管理员列表
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	public Object queryManagerList(PageRequest pageRequest){
		//响应
		MapResponse response = null;
		try {
			//验证token
			String uid = sessionTokenService.getStringForValue(pageRequest.getSessiontoken()) + "";
			if ("null".equalsIgnoreCase(uid) || StringUtils.isEmpty(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效");
			}
			
			//查询主播直播用户信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if (liverMap == null || liverMap.size() < 1) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此主播信息");
			}
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", liverMap.get("id"));
			paramMap.put("page", pageRequest.getPage());
			paramMap.put("limit", Constant.PAGE_LIMIT);
			
			//获取管理员列表 
			List<LiveManagerInfo> managerList = anchorManagerDao.queryAnchorManagerList(paramMap);
			if (managerList != null && managerList.size() > 0) {
				for (LiveManagerInfo liveManagerInfo : managerList) {
					Map<Object, Object> managerMap = new HashMap<>();
					//组装响应数据
					managerMap.put("uid", liveManagerInfo.getUid());
					String nname = liveManagerInfo.getNname() == null || liveManagerInfo.getNname().equals("") ?
							StrUtils.regexReplacePhone(liveManagerInfo.getPhone()) : liveManagerInfo.getNname();
					managerMap.put("nname", nname);
					managerMap.put("avatar", fileUrl+liveManagerInfo.getAvatar());
					managerMap.put("sex", liveManagerInfo.getSex());
					managerMap.put("rank_no", liveManagerInfo.getRank_no());
					managerMap.put("rank_id", liveManagerInfo.getRank_id());
					resultList.add(managerMap);
				}
			}
			
			//响应
			resultMap.put("managerList", resultList);
			resultMap.put("maxManagerNums", propertiesUtil.getValue("maxManagerNums", "conf_live.properties"));
			response = new MapResponse(ResponseCode.SUCCESS,"获取管理员列表成功");
			response.setResponse(resultMap);
		} catch (Exception e) {
			log.info("获取管理员列表失败,错误信息如下:" + e.getMessage());
			response = new MapResponse(ResponseCode.FAILURE,"获取管理员列表失败");
		}
		return response;
	}
	
	/**
	 * 
	* @Title: deleteAnchorManager
	* @Description: 主播删除指定的管理员
	* @return Object    返回类型
	* @throws
	 */
	public Object deleteAnchorManager(UidRequest uidRequest){
		//响应
		BaseResponse response = null;
		//主播uid
		String anchorUid = "";
		//管理员uid
		Integer managerUid = null;
		try {
			//验证token
			anchorUid = sessionTokenService.getStringForValue(uidRequest.getSessiontoken()) + "";
			if ("null".equalsIgnoreCase(anchorUid) || StringUtils.isEmpty(anchorUid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效");
			}
			
			//管理员uid
			managerUid = uidRequest.getUid();
			//查询管理员的直播用户信息
			Map<Object, Object> managerLiverMap = liveUserDao.queryLiverInfoByUid(managerUid);
			if (managerLiverMap == null || managerLiverMap.size() < 1) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此管理员信息");
			}
			
			//查询主播的直播用户信息
			Map<Object, Object> anchorLiverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(anchorUid));
			if (anchorLiverMap == null || anchorLiverMap.size() < 1) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此主播信息");
			}
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("manager_id", managerLiverMap.get("id"));
			paramMap.put("anchor_id", anchorLiverMap.get("id"));
			
			//查询是否已经删除了此主播管理员
			Map<Object, Object> managerMap = anchorManagerDao.queryAnchorManager(paramMap);
			if (managerMap == null || managerMap.size() < 1) {
				return new BaseResponse(ResponseCode.FAILURE, "您已删除此管理员了");
			}
			
			//删除主播指定的管理员
			int result = anchorManagerDao.deleteAnchorManager(paramMap);
			if (result>0) {
				response = new BaseResponse(ResponseCode.SUCCESS, "删除管理员成功");
			}
			
		} catch (Exception e) {
			log.info("删除管理员失败,错误信息如下:主播uid=" + anchorUid + ",删除管理员uid=" + managerUid + ",错误详情:" + e.getMessage());
			e.printStackTrace();
			response = new BaseResponse(ResponseCode.FAILURE, "删除管理员失败");
		}
		return response;
	}
	
}
