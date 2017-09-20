package com.xmniao.xmn.core.http.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.http.entity.InterfacRequest;
import com.xmniao.xmn.core.http.entity.Member;
import com.xmniao.xmn.core.http.entity.MemberStatusUpdate;
import com.xmniao.xmn.core.http.entity.PUserRequestSelect;
import com.xmniao.xmn.core.http.entity.PUserResponseSelect;
import com.xmniao.xmn.core.http.entity.PhpHttpPageable;
import com.xmniao.xmn.core.http.entity.RequestWhere;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorService;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.ResponseData;
import com.xmniao.xmn.core.thrift.service.synthesizeService.SynthesizeService;
import com.xmniao.xmn.core.thrift.service.synthesizeService.SynthesizeService.Client;
import com.xmniao.xmn.core.thrift.service.xmerWalletService.XmerWalletService;
import com.xmniao.xmn.core.util.DateHelper;
import com.xmniao.xmn.core.util.HttpUtil;
import com.xmniao.xmn.core.util.JsonUtil;
import com.xmniao.xmn.core.util.NMD5;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;
import com.xmniao.xmn.core.util.holder.ExecutorServiceHolder;
import com.xmniao.xmn.core.xmnpay.dao.WalletDao;
import com.xmniao.xmn.core.xmnpay.entity.Bwallet;

/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：PUserService
 * 
 * 类描述： PHP用户接口
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年10月22日 下午3:12:52
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
@Service
public class PuserService extends BaseService<Void> {

	private final Logger log = Logger.getLogger(PuserService.class);

	private String url = null;
	private int exportDateRange;
	private String getuserInfo = "/user/getuserInfo.html";// 查询接口
	private String adduser = "user/register.html";
	private String updateuser = "user/updateUserAll.html";
	private String getByGsidAtatime = "user/getByGsidAtatime.html";// 查询接口
	private AreaHandler areaHandler = AreaHandler.getAreaHandler();

	@Autowired
	private WalletDao walletDao;

	/**
	 * 注入主播服务
	 */
	@Autowired
	private TLiveAnchorService anchorService;
	
	/**
	 * 注入redisTemplate
	 */
	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 注入支付综合服务
	 */
	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;
	
	@Resource(name = "xmerWalletServicClient")
	private ThriftClientProxy xmerWalletServiceClient;

	public PuserService() {

		this.url = PropertiesUtil.readValue("http.user.url");
		this.exportDateRange = Integer.parseInt(PropertiesUtil.readValue("http.exportDateRange"));

	}

	/**
	 * 
	 * getuserInfo(PHP 用户查询接口)
	 * 
	 * @param request
	 * @return
	 * @throws HttpHostConnectException
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws ClientProtocolException
	 * @throws Exception
	 * 
	 *             author：zhou'sheng
	 */

	public PhpHttpPageable<PUserResponseSelect> getuserInfo(PUserRequestSelect request) throws HttpHostConnectException,
			ConnectTimeoutException, SocketTimeoutException, ClientProtocolException, Exception {
		PhpHttpPageable<PUserResponseSelect> response = handel(getuserInfo, request.getParam());
		setPageInfo(response, request);
		@SuppressWarnings("unchecked")
		List<PUserResponseSelect> l = (List<PUserResponseSelect>) response.getData();

		// 取出uid查询钱包信息
		PUserResponseSelect user = null;
		List<Bwallet> wl = null;
		List<BLiver> anchorList = null;
		BLiver anchor = new BLiver();
		int count = 0;
		StringBuilder uidStr = new StringBuilder();
		String uid = null;
		for (int i = 0; i < l.size(); i++) {
			user = JSON.parseObject(JSON.toJSONString(l.get(i), JsonUtil.vfilter), PUserResponseSelect.class);
			uid = user.getUid();
			uidStr = uidStr.append(uid + ",");
		}
		String[] uids = uidStr.toString().split(",");
		wl = walletDao.selectWallet(uids);
		anchorList = anchorService.selectLiversByUids(uids);
		if (anchorList != null) {
			count = anchorList.size();
		}

		for (int m = 0; m < l.size(); m++) {
			user = JSON.parseObject(JSON.toJSONString(l.get(m), JsonUtil.vfilter), PUserResponseSelect.class);
			for (int j = 0; j < wl.size(); j++) {
				if (user.getUid().equals(wl.get(j).getUid().toString())) {
					user.setWalletStatus(wl.get(j).getStatus());
					user.setAmount(wl.get(j).getAmount());
					user.setCommision(wl.get(j).getCommision());
					user.setBalance(wl.get(j).getBalance());
					user.setZbalance(wl.get(j).getZbalance());
					user.setIntegral(wl.get(j).getIntegral());
					l.set(m, user);
					break;
				}
			}

			for (int index = 0; index < count; index++) {
				anchor = anchorList.get(index);
				if (user.getUid().equals(anchor.getUid().toString())) {
					// TODO 设置主播信息
					user.setRankNo(anchor.getRankNo());
					user.setConcernNums(anchor.getConcernNums());
					user.setGiveGiftsNums(anchor.getGiveGiftsNums());
					l.set(m, user);
					break;
				}
			}

		}
		addPUserResponseSelectToList(l);// 填充区域名称 如：深圳-南山区
		response.setData(l);
		return response;
	}

	/**
	 * 导出用户数据
	 * 
	 * @param param
	 *            参数
	 * @return
	 * @throws HttpHostConnectException
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws ClientProtocolException
	 * @throws Exception
	 */
	private PhpHttpPageable<PUserResponseSelect> exportUserInfo(Map<String, String> param)
			throws HttpHostConnectException, ConnectTimeoutException, SocketTimeoutException, ClientProtocolException,
			Exception {
		PhpHttpPageable<PUserResponseSelect> response = handel(getuserInfo, param);
		@SuppressWarnings("unchecked")
		List<PUserResponseSelect> l = (List<PUserResponseSelect>) response.getData();
		// 取出uid查询钱包信息
		PUserResponseSelect user = null;
		List<Bwallet> wl = null;
		StringBuilder uidStr = new StringBuilder();
		String uid = null;
		for (int i = 0; i < l.size(); i++) {
			user = JSON.parseObject(JSON.toJSONString(l.get(i), JsonUtil.vfilter), PUserResponseSelect.class);
			uid = user.getUid();
			uidStr = uidStr.append(uid + ",");
		}
		wl = walletDao.selectWallet(uidStr.toString().split(","));
		for (int m = 0; m < l.size(); m++) {
			user = JSON.parseObject(JSON.toJSONString(l.get(m), JsonUtil.vfilter), PUserResponseSelect.class);
			for (int j = 0; j < wl.size(); j++) {
				if (user.getUid().equals(wl.get(j).getUid().toString())) {
					user.setWalletStatus(wl.get(j).getStatus());
					user.setAmount(wl.get(j).getAmount());
					user.setCommision(wl.get(j).getCommision());
					user.setBalance(wl.get(j).getBalance());
					user.setZbalance(wl.get(j).getZbalance());
					user.setIntegral(wl.get(j).getIntegral());
					l.set(m, user);
					break;
				}
			}
		}
		addPUserResponseSelectToList(l);// 填充区域名称 如：深圳-南山区
		response.setData(l);
		return response;
	}

	private void addPUserResponseSelectToList(List<PUserResponseSelect> l) {
		PUserResponseSelect user = null;
		try {
			for (int i = 0; i < l.size(); i++) {
				user = JSON.parseObject(JSON.toJSONString(l.get(i), JsonUtil.vfilter), PUserResponseSelect.class);
				if (null != user) {
					if (StringUtils.hasLength(user.getCity())) {
//						user.setCity(areaHandler.getAreaIdByTitle(Integer.parseInt(user.getCity())));// 所属城市
					}
					if (StringUtils.hasLength(user.getRegion())) {
						user.setRegion(areaHandler.getAreaIdByTitle(Integer.parseInt(user.getRegion())));// 所属区域
					}
					if (StringUtils.hasLength(user.getRegcity())) {
						user.setRegcityId(user.getRegcity()); // 保存接口传过来的数值,不将其转化为名称，保留区域ID,注意在转换为字符串之前
						user.setRegcity(areaHandler.getAreaIdByTitle(Integer.parseInt(user.getRegcity())));// 注册所在城市

					}
					if (StringUtils.hasLength(user.getRegarea())) {
						user.setRegareaId(user.getRegarea()); // 保存接口传过来的数值,不将其转化为名称，保留区域ID,注意在转换为字符串之前
						user.setRegarea(areaHandler.getAreaIdByTitle(Integer.parseInt(user.getRegarea())));// 注册所在区域

					}
				}
				l.set(i, user);
			}
		} catch (NumberFormatException e) {
			this.log.error("加载程序异常："+user.toString());
			e.printStackTrace();
		}

	}

	/**
	 * 会员添加（会员接口）
	 * 
	 * @param req
	 * @param request
	 * @return
	 * @throws HttpHostConnectException
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws ClientProtocolException
	 * @throws Exception
	 */
	public PhpHttpPageable<PUserResponseSelect> add(HttpServletRequest req, Member request)
			throws HttpHostConnectException, ConnectTimeoutException, SocketTimeoutException, ClientProtocolException,
			Exception {
		request.setUname(request.getPhone());
 		String ip = StringUtils.getIpAddr(req);
		request.setIp(ip);
		// md5加密并且前六位和后六位互换
		String md = NMD5.Encode(request.getPassword());
		String md5BeforeSix = md.substring(0, 6);
		String md5InSix = md.substring(6, 26);
		String md5AfterSix = md.substring(26, 32);
		String md5Nmber = md5AfterSix + md5InSix + md5BeforeSix;
		request.setPassword(md5Nmber);
		// request.setPassword(NMD5.Encode(request.getPassword()));
		PhpHttpPageable<PUserResponseSelect> response = handel(adduser, request);
		redisTemplate.opsForValue().set("b_urs$changed", "true");
		Object data = response.getData();
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("uId", data.toString());
		paramMap.put("userType", "1");// 用户类型 1用户 2商家 3合作商
		Map<String, String> doClient = (Map<String, String>) synthesizeServiceClient.doClient("addWalletMap", paramMap);
		log.info("添加用户钱包接口返回状态为:"+doClient.get("state")+" uid:"+data);
		// addWalletMap(request, (Integer)data,"1");
		if(request.getUsertype()==2){
			paramMap=new HashMap<>();
			paramMap.put("uid", data.toString());
			paramMap.put("uname",org.apache.commons.lang.StringUtils.isNotBlank(request.getUname())?request.getUname():request.getPhone());
			ResponseData  responseData = (ResponseData) xmerWalletServiceClient.doClient("addXmerWallet", paramMap);
			log.info("调用创建寻蜜客钱包接口返回结果:"+responseData.state+" 用户id:"+data);
		}
		setPageInfo(response, request);
		return response;
	}


	/**
	 * 会员修改（会员接口）
	 * 
	 * @param request
	 * @return
	 * @throws HttpHostConnectException
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws ClientProtocolException
	 * @throws Exception
	 */
	public PhpHttpPageable<PUserResponseSelect> update(Member request) throws HttpHostConnectException,
			ConnectTimeoutException, SocketTimeoutException, ClientProtocolException, Exception {
		Map<String, Object> map = new HashMap<String, Object>(1);
		String uid = request.getUid();
		request.setUid(null);
		request.setUname(request.getPhone());
		// 密码设置
		String mdp = request.getPassword();
		if (mdp.equals("******")) {// 不修改
			request.setPassword(request.getOldpassword());
		} else {// 修改
				// request.setPassword(NMD5.Encode(request.getPassword()));
				// md5加密并且前六位和后六位互换
			String md = NMD5.Encode(request.getPassword());
			String md5BeforeSix = md.substring(0, 6);
			String md5InSix = md.substring(6, 26);
			String md5AfterSix = md.substring(26, 32);
			String md5Nmber = md5AfterSix + md5InSix + md5BeforeSix;
			request.setPassword(md5Nmber);
		}
		map.put("update", request);
		RequestWhere where = new RequestWhere(uid);
		map.put("where", where);
		PhpHttpPageable<PUserResponseSelect> response = handel(updateuser, map);
		redisTemplate.opsForValue().set("b_urs$changed", "true");
		setPageInfo(response, request);
		Integer usertype = request.getUsertype();
		if(usertype!=request.getBaseusertype()){
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("uid", uid);
			ResponseData  responseData = (ResponseData) xmerWalletServiceClient.doClient("getXmerWallet", hashMap);
			if(usertype==2){
				//普通改寻蜜客
				if(responseData.state==0){
					//解锁
					hashMap.put("state", "1");
					xmerWalletServiceClient.doClient("lockXmerWallet",hashMap);
				}else{
					//创建
					hashMap.put("uname",org.apache.commons.lang.StringUtils.isNotBlank(request.getUname())?request.getUname():request.getPhone());
					ResponseData  data = (ResponseData) xmerWalletServiceClient.doClient("addXmerWallet", hashMap);
				}
			}else{
				//寻蜜客改普通
					hashMap.put("state", "2");
					xmerWalletServiceClient.doClient("lockXmerWallet",hashMap);
			}
		}
		return response;
	}

	private PhpHttpPageable<PUserResponseSelect> handel(String requestUrl, Object request)
			throws HttpHostConnectException, ConnectTimeoutException, SocketTimeoutException, ClientProtocolException,
			Exception {
		return HttpUtil.getInstance().phpPost(url + "/" + requestUrl, request);

	}

	/**
	 * 会员修改（会员接口）
	 * 
	 * @param request
	 * @return
	 * @throws HttpHostConnectException
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws ClientProtocolException
	 * @throws Exception
	 */
	public PhpHttpPageable<PUserResponseSelect> updateStatus(Member request) throws HttpHostConnectException,
			ConnectTimeoutException, SocketTimeoutException, ClientProtocolException, Exception {
		Map<String, Object> map = new HashMap<String, Object>(1);
		String uid = request.getUid();
		request.setUid(null);
		MemberStatusUpdate memberStatusUpdate = new MemberStatusUpdate();
		memberStatusUpdate.setStatus(request.getStatus());
		map.put("update", memberStatusUpdate);
		RequestWhere where = new RequestWhere(uid);
		map.put("where", where);
		PhpHttpPageable<PUserResponseSelect> response = handel(updateuser, map);
		setPageInfo(response, memberStatusUpdate);
		return response;
	}

	/**
	 * 分页信息设置
	 * 
	 * @param response
	 * @param request
	 */
	private void setPageInfo(PhpHttpPageable<PUserResponseSelect> response, InterfacRequest request) {
		if (null != response && null != request) {
			response.setPage(request.getPage());
			response.setPageSize(request.getPageSize());
		}
	}

	/**
	 * （查看）获取指定会员信息
	 * 
	 * @param mv
	 * @param puser
	 * @throws Exception
	 */
	public void getPuserInfo(ModelAndView mv, PUserRequestSelect puser, boolean isQuery) {
		try {
			PUserResponseSelect ps = new PUserResponseSelect();
			PhpHttpPageable<PUserResponseSelect> p = getuserInfo(puser);
			if (p.getStatus()) {
				@SuppressWarnings("unchecked")
				List<PUserResponseSelect> l = (List<PUserResponseSelect>) p.getData();
				if (null != l) {
					if (!isQuery) {
						mv.addObject("member", l.get(0));
					} else {
						mv.addObject("member", l);
						mv.addObject("memberPage", Long.parseLong(p.getPage()));
						mv.addObject("memberTotal", Long.parseLong(p.getTotal()));
					}
				}
			}
		} catch (Exception e) {
			log.error("获取-- " + puser + " --会员信息失败", e);
		}

	}

	/**
	 * 
	 * @author dong'jt 创建时间：2015年8月19日 下午6:34:53 描述：（查看）获取指定会员信息
	 * @param puser
	 * @return
	 */
	public Map<String, Object> getPuserInfos(PUserRequestSelect puser) {
		try {
			PhpHttpPageable<PUserResponseSelect> p = getuserInfo(puser);
			if (p.getStatus()) {
				Map<String, Object> map = new HashMap<>();
				map.put("content", p.getData());
				map.put("total", Long.parseLong(p.getTotal()));
				map.put("page", Long.parseLong(p.getPage()));
				return map;
			}
		} catch (Exception e) {
			log.error("获取-- " + puser + " --会员信息失败", e);
		}
		return null;
	}

	/**
	 * 获取会员列表信息
	 * 
	 * @param puser
	 * @return
	 * @author wangzhimin 2015/08/17 16:12(下午)
	 */
	public Map<String, Object> getUserInfo(PUserRequestSelect puser) {
		try {
			PhpHttpPageable<PUserResponseSelect> p = getuserInfo(puser);
			if (p.getStatus()) {
				String data = p.getData().toString();
				System.out.println(data);
				Map<String, Object> map = new HashMap<>();
				map.put("content", p.getData());
				map.put("total", Long.parseLong(p.getTotal()));
				map.put("page", Long.parseLong(p.getPage()));
				return map;
			}
		} catch (Exception e) {
			log.error("获取-- " + puser + " --会员信息失败", e);
		}
		return null;
	}

	/**
	 * update by wangzhimin 2015/08/18 下午 13:52
	 * 
	 * @param puser
	 * @return
	 */
	public List getPHPMemberData(PUserRequestSelect puser) {
		List<PUserResponseSelect> list = new CopyOnWriteArrayList<>();
		try {
			puser.setPageSize("0"); // 导出时不要进行分页
			Map<String, String> map = puser.getParam();
			Map<String, String> param = null;
			List<DateInfo> dateInfos = getDateList(map.get("staregtime"), map.get("endregtime"));
			if (!dateInfos.isEmpty()) {
				CountDownLatch latch = new CountDownLatch(dateInfos.size());
				// 根据使用多线程根据得到的区间日期查询用户数据
				for (DateInfo dateInfo : dateInfos) {
					param = new HashMap<>(map.size());
					param.putAll(map);
					param.put("staregtime", dateInfo.getSdate());
					param.put("endregtime", dateInfo.getEnddate());
					ExecutorServiceHolder.getExecutorService().execute(new UserQuery(latch, param, list));
				}
				latch.await();
			}
		} catch (Exception e) {
			log.error("获取-- " + puser + " --会员信息失败", e);
		}
		return list;
	}

	/**
	 * 根据 导出日期区间参数 生成导出月份日期区间
	 * 
	 * @param sdate
	 *            导出月份开始日期
	 * @param enddate
	 *            导出月份结束日期
	 * @return
	 * @throws ParseException
	 */
	private List<DateInfo> getDateList(String sdate, String enddate) throws Exception {
		String newsdate = null, newEndDate = null;
		int days = Integer.parseInt(enddate.split("-")[2]);
		int section = days / exportDateRange;
		section = days % exportDateRange > 0 ? section + 1 : section;
		List<DateInfo> s = new ArrayList<>(section);
		DateInfo dateInfo = null;

		for (int i = 0; i < section; i++) {
			dateInfo = new DateInfo();
			dateInfo.setSdate(sdate);
			newEndDate = DateHelper.getOtherDayByDay(sdate, exportDateRange);
			// 根据区间获取日期 如果日期大于结束日期 结束
			if (newEndDate.compareTo(enddate) >= 0) {
				dateInfo.setEnddate(enddate);
				s.add(dateInfo);
				break;
			}
			// 获取以结束日期的下一天为新的开始日期
			newsdate = DateHelper.getTomorrow(newEndDate);
			// 如果开始日期大于等于 结束日期 结束
			if (newsdate.compareTo(enddate) >= 0) {
				dateInfo.setEnddate(enddate);
				s.add(dateInfo);
				break;
			}
			// 设置新的开始日期
			sdate = newsdate;
			dateInfo.setEnddate(newEndDate);
			s.add(dateInfo);

		}
		return s;
	}

	/**
	 * 导出
	 * 
	 * @param puser
	 * @return
	 *//*
		 * public Map<String, Object> getExportMemberData(PUserRequestSelect
		 * puser){ try { PhpHttpPageable<PUserResponseSelect> p =
		 * getuserInfo(puser); if (p.getStatus()) { Map<String, Object> map =
		 * new HashMap<>(); map.put("content", p.getData()); return map; }
		 * }catch(Exception e) { log.error("获取-- " + puser + " --会员信息失败", e); }
		 * return null; }
		 */

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 日期信息保存类
	 * 
	 * @author ch
	 *
	 */
	private class DateInfo {
		private String sdate;
		private String enddate;

		public String getSdate() {
			return sdate;
		}

		public void setSdate(String sdate) {
			this.sdate = sdate;
		}

		public String getEnddate() {
			return enddate;
		}

		public void setEnddate(String enddate) {
			this.enddate = enddate;
		}

		@Override
		public String toString() {
			return "DateInfo [sdate=" + sdate + ", enddate=" + enddate + "]";
		}
	}

	/**
	 * 用户导出数据查询线程类
	 * 
	 * @author ch
	 *
	 */
	private class UserQuery extends Thread {
		private CountDownLatch latch;
		private Map<String, String> param;
		private List<PUserResponseSelect> list;

		public UserQuery(CountDownLatch latch, Map<String, String> param, List<PUserResponseSelect> list) {
			this.latch = latch;
			this.param = param;
			this.list = list;
		}

		@Override
		public void run() {
			try {
				PhpHttpPageable<PUserResponseSelect> p = exportUserInfo(param);
				if (p.getStatus()) {
					list.addAll((List<PUserResponseSelect>) p.getData());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}

	}
}
