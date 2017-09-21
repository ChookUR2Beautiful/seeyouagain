package com.xmniao.service.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.domain.live.LiverBean;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.service.common.GiveRegisterGift;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.user.UserService;
import com.xmniao.urs.dao.LiverDao;
import com.xmniao.urs.dao.UrsDao;
import com.xmniao.util.MD5;


/**
 * 
 * @author chenJie
 * 
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService.Iface {

	/**
	 * 初始化日志类
	 */
	private Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UrsDao ursDao;

	@Autowired
	private CommonServiceImpl commonServiceImpl;

	@Autowired
	private GiveRegisterGift giveGift;
	
	private Set<Integer> anchorUidSet;

	@Autowired
	private LiverDao liverDao;
	/**
	 * 根据uid查询用户信息
	 */
	@Override
	public ResponseData getUserMsg(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("查询用户信息getUserMsg：" + paramMap);
		ResponseData responseData = new ResponseData();
		Map<String, String> resultMap = new HashMap<>();
		try {
			// 验证参数非空
			if (StringUtils.isBlank(paramMap.get("uid"))&&StringUtils.isBlank(paramMap.get("phone"))) {
				log.error("查询用户信息失败，传参有误");
				responseData.setState(2);
				responseData.setMsg("查询用户信息失败，传参有误");
				return responseData;
			}
			Map<String, Object> usrMap;
			
			if(StringUtils.isNotBlank(paramMap.get("uid"))){
				usrMap = ursDao.getUrsByUid(paramMap.get("uid"));
			}else{
				usrMap = ursDao.getUrsByPhone(paramMap.get("phone"));
			}
			Map<String, Object> usrInfoMap = ursDao.getUsrInfo(paramMap);

			if (usrMap == null && usrInfoMap == null) {
				log.error("查询失败，该用户不存在" + paramMap.get("uid"));
				responseData.setState(1);
				responseData.setMsg("该用户不存在");
				return responseData;
			}

			if (usrInfoMap != null) {
				resultMap.put("avatar", null == usrInfoMap.get("avatar") ? ""
						: usrInfoMap.get("avatar").toString());
			}

			if (usrMap != null) {
				resultMap.put("uid",usrMap.get("uid").toString());
				resultMap.put("nname", null == usrMap.get("nname") ? ""
						: usrMap.get("nname").toString());
				resultMap.put("phone", null == usrMap.get("phone") ? ""
						: usrMap.get("phone").toString());
				resultMap.put("attachTime", null == usrMap.get("attachTime") ? ""
						: usrMap.get("attachTime").toString());
				resultMap.put(
						"genussellerid",
						null == usrMap.get("genussellerid") ? "" : usrMap
								.get("genussellerid") + "");
				resultMap.put(
						"genusname",
						null == usrMap.get("genusname") ? "" : usrMap
								.get("genusname") + "");
				resultMap.put(
						"jointid",
						null == usrMap.get("jointid") ? "" : usrMap
								.get("jointid") + "");
				resultMap.put(
						"corporate",
						null == usrMap.get("corporate") ? "" : usrMap
								.get("corporate") + "");
				resultMap.put(
						"attachTime",
						null == usrMap.get("attachTime") ? "" : usrMap
								.get("attachTime") + "");
//				LiverBean liver = liverDao.getLiverByUid((Integer)usrMap.get("uid"));
//				String uidRelationChain = liver==null?"":liver.getUidRelationChain();
//				resultMap.put(
//						"uidRelationChain",uidRelationChain);
			}

			responseData.setState(0);
			responseData.setMsg("查询成功" + paramMap);
			responseData.setResultMap(resultMap);
			return responseData;
		} catch (Exception e) {
			log.error("查询异常getUserMsg" + e);
			responseData.setState(1);
			responseData.setMsg("系统异常");
			return responseData;
		}
	}

	/**
	 * 根据手机号验证用户是否存在
	 */
	@Override
	public ResponseData testUser(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("验证用户testUser" + paramMap);
		ResponseData responseData = new ResponseData();

		try {
			// 验证参数
			if (StringUtils.isBlank(paramMap.get("phone"))) {
				log.error("phone不能为空");
				responseData.setState(2);
				responseData.setMsg("phone不能为空");
				return responseData;
			}

			Map<String, Object> ursMsg = ursDao.getUrsByPhone(paramMap
					.get("phone"));

			if (ursMsg == null) {
				log.info("该手机用户没有寻蜜鸟账户" + paramMap.get("phone"));
				responseData.setState(1);
				responseData.setMsg("该手机用户没有寻蜜鸟账户");
				return responseData;
			} else {
				log.info("该手机用户存在寻蜜鸟账户" + paramMap.get("phone"));
				responseData.setState(0);
				responseData.setMsg("该手机用户存在寻蜜鸟账户");

				Map<String, String> resultMap = new HashMap<>();
				resultMap.put("uid", ursMsg.get("phone").toString());

				responseData.setResultMap(resultMap);
				return responseData;
			}

		} catch (Exception e) {
			log.error("验证失败，系统异常");
			responseData.setState(2);
			responseData.setMsg("验证失败，系统异常");
			return responseData;
		}
	}

	/**
	 * 注册新用户
	 */
	@Override
	public ResponseData registerUser(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("注册用户registerUser" + paramMap);
		ResponseData responseData = new ResponseData();
		Map<String, String> resultMap = new HashMap<>();
		try {
			if (StringUtils.isBlank(paramMap.get("phone"))
					|| StringUtils.isBlank(paramMap.get("password"))) {
				log.error("用户手机号或登录密码为空" + paramMap);
				responseData.setState(2);
				responseData.setMsg("用户手机号或登录密码为空");
				return responseData;
			}
			// 验证该手机用户是否已经注册
			Map<String, Object> ursMsg = ursDao.getUrsByPhone(paramMap
					.get("phone"));

			if (ursMsg != null) {
				log.info("该手机用户已经注册" + paramMap.get("phone"));
				responseData.setState(0);
				responseData.setMsg("注册成功");
				resultMap.put("uid", ursMsg.get("uid").toString());
				responseData.setResultMap(resultMap);
				return responseData;
			}
			
			//注册并添加钱包
			Map<String, Object> ursMap = register(paramMap);
			
			// 发送新手礼包
			boolean result1 = giveGift.giveRegisterGift(ursMap.get("uid") + "",
					paramMap.get("phone"));
			if (!result1) {
				log.error("发送礼包过程出错" + ursMap.get("uid"));
			}
			responseData.setState(0);
			responseData.setMsg("注册成功");
			resultMap.put("uid", ursMap.get("uid") + "");
			responseData.setResultMap(resultMap);
			return responseData;
		} catch (Exception e) {
			log.error("注册失败", e);
			responseData.setState(1);
			responseData.setMsg("注册失败");
			return responseData;
		}
	}

	/**
	 * 用户注册，并添加钱包
	 * 
	 * @return
	 * @throws FailureException
	 */
	@Transactional(rollbackFor = { FailureException.class, Exception.class })
	private Map<String, Object> register(Map<String, String> paramMap)
			throws FailureException,TException {
		// 用户注册
		Map<String, Object> ursMap = new HashMap<>();
			ursMap.put("phone", paramMap.get("phone"));// 注册手机号
			ursMap.put("password", MD5.Encode(paramMap.get("password")));// 登录密码
			ursMap.put("regtime", getFormatTime());// 注册时间
			ursMap.put("lastlogintime", getFormatTime());// 最后一次登录时间
			ursMap.put("usertype", "1");// 用户类型：普通用户
			ursMap.put("status", "1");// 1正常 2锁定 3注销

			// 新增寻蜜鸟用户
			ursDao.addUrs(ursMap);

			Map<String, String> ursInfo = new HashMap<>();
			ursInfo.put("uid", ursMap.get("uid") + "");
			ursInfo.put("phone", paramMap.get("phone"));
			//插入urs_info
			ursDao.insertBursInfo(ursInfo);
			// 添加用户钱包
			boolean result = commonServiceImpl.addWallet(
					ursMap.get("uid") + "", "1");
			if (!result) {
				log.error("添加用户钱包失败");
				throw new FailureException(1, "调用支付服务添加用户钱包失败");
			}
			return ursMap;

	}
	
	/**
	 * 根据用户uid查询主播id
	 */
	@Override
	public List<Integer> getAnchorId(List<Integer> paramMap)
			throws FailureException, TException {
		log.info("根据用户uid查询主播id"+paramMap);
		try {
			List<Integer> list = new ArrayList<>();
			for (Integer uid : paramMap) {
				Integer i =ursDao.getLiveId(uid);
				list.add(i==null?0:i);
			}
			log.info("查询主播id成功："+list);
			return list;
		} catch (Exception e) {
			log.error("查询主播id失败",e);
			throw new FailureException(1, "查询主播id失败");
		}
	}
	
	/**
	 * 获取格式为yyyy-MM-dd HH:mm:ss 的系统时间
	 * 
	 * @return
	 */
	private String getFormatTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public Set<Integer> getAnchorUidSet() {
		return anchorUidSet;
	}

	/**
	 * 启动初始化所有主播的UID
	 * @Title: initAnchorUidList 
	 * @Description:
	 */
	@PostConstruct  
	private void initAnchorUidList(){
		anchorUidSet = ursDao.getAnchorUidList(new HashMap<String,Object>());
	}
	
	/**
	 * 获取用户信息
	 */
	@Override
	public Map<String, String> getUserInfo(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("获取用户信息");
		Map<String,String> resultMap = new HashMap<>();
		for (String key : paramMap.keySet()) {
			try {
				resultMap.put(key,ursDao.getUserInfo(key).get("str").toString());
			} catch (Exception e) {
				log.info("用户："+key+"查询信息异常",e);
			}
		}
		return resultMap;
	}

}
