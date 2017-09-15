package com.xmniao.xmn.core.xmer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.FlowInfoListPayRequest;
import com.xmniao.xmn.core.common.request.FlowInfoListRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.thrift.ResponsePageList;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.StrUtils;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.xmer.dao.FlowInfoDao;
import com.xmniao.xmn.core.xmer.dao.SaasSoldOrderDao;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 店铺流水明细（已分账）
 */
@Service
public class FlowInfoPayService {
	/**
	 * 日志报错
	 */
	private Logger log = Logger.getLogger(FlowInfoPayService.class);
	
	
	@Autowired
	private FlowInfoDao flowInfoDao;
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private SessionTokenService sessionTokenService;

	@Autowired
	private UrsEarningsRelationDao ursEarningsRelationDao;

	@Autowired
	private XmerService xmerService;

	@Autowired
	private FlowInfoService flowInfoService;
	@Autowired
	private XmerOrderRecordService xmerOrderRecordService;
	@Autowired
	private ThriftWalletExpansionService thriftWalletExpansionService;
	@Autowired
	private LiveUserDao liveUserDao;
	@Autowired
	private SaasSoldOrderDao saasSoldOrderDao;
	@Autowired
	private SellerService sellerService;
	
	/**
	 * @Title: 查询流水明细列表
	 * @Description: 
	 * @return
	 */
	public Object queryFlowInfo(FlowInfoListPayRequest request) {
		try {
			//存储响应参数实例
			Map<Object,Object> map = new HashMap<Object,Object>();
			//验证token
			String sUid = sessionTokenService.getStringForValue(request.getSessiontoken()).toString();
			if(sUid.equals("")||sUid.equals("null")){
				return new BaseResponse(ResponseCode.FAILURE, "查询流水明细列表,sessiontoken失效");
			}
			Integer uid = Integer.parseInt(sUid);

			Map<Integer, Boolean> identityMap = xmerService.identityMap(uid);
//			1 寻觅客 2中脉 3 V客
			boolean isXmer = identityMap.get(1);
			boolean isV = identityMap.get(3);
			boolean isM = identityMap.get(2);
			boolean isAnchorV = identityMap.get(4);
			isXmer = isXmer || isAnchorV;
			//下级及下下级用户uid (寻觅客才有)
//			List<Integer> uids = xmerOrderRecordService.getUidsByUrs(uid, isXmer, isV, isM);
			List<Integer> uids = null;
			Integer expansionType = xmerOrderRecordService.getExpansionTypeByUid(uid, isXmer, isV, isM);

			int type = Integer.valueOf(request.getType());
			if(request.getPage() == 1){ //首页时
				Map<Object, Object>  total = totalFlowInfoList(uid, uids, expansionType);
				map.putAll(total);
			}
			long t1 = System.currentTimeMillis();
			// 查询流水金额
			ResponsePageList responsePageList = xmerOrderRecordService.getOrderRecordByType(type, uid, uids, expansionType, 2,
					request.getPage(), Constant.PAGE_LIMIT, request.getStartdate(), request.getEnddate());
			if (responsePageList.getDataInfo().getState() != 0) {
				log.warn("调用远程支付扩展服务失败： " + responsePageList.getDataInfo().getMsg());
				return new BaseResponse(ResponseCode.FAILURE, "获取收入明细列表, 调用远程服务失败");
			}
			log.warn("调用远程支付扩展服务时间：" + (System.currentTimeMillis() - t1));

			List<Map<Object,Object>> resultList = new ArrayList<Map<Object,Object>>();

			List<String> pOrders = new ArrayList<>();

			List<Map<String,String>>  pageList = responsePageList.getPageList();
			for (int i = 0; i < pageList.size(); i++) {
				Map<String, String> item = pageList.get(i);
				String t_uid = item.get("uid");  //用户id
				String source = item.get("source");  //订单号
				String createTime = item.get("createTime");  //记录时间
				String amount = item.get("amount");  //收益金额
				String remark = item.get("remark");  //备注

				pOrders.add(source);

				Map<Object, Object> m = new HashMap<Object, Object>();
				m.put("zdate", createTime == null ? "" : createTime);  //消费时间
				m.put("amount", amount == null ? 0 : amount);  //收入金额
				m.put("status", Constant.SIGN_STATUS_SUCCESS);  //2签约成功
				m.put("source", source);  // 不返回前端
				m.put("uid", t_uid);  //用户uid
				m.put("sellername", ""); //店铺
				resultList.add(m);
			}

			t1 = System.currentTimeMillis();
			// 订单，对应uid
			Map<String, Integer> orderUidMap = new HashMap<String, Integer>();
			Set<Integer> uidSet = new HashSet<Integer>();
			//批量查询订单店铺信息
			Map<String, Map<Object, Object>> orderInfoMap = new HashMap<String, Map<Object, Object>>();
			if (pOrders.size() > 0) {
				Map<Object, Object> param = new HashMap<Object, Object>();
				param.put("bids", pOrders);
				List<Map<Object, Object>> orderInfos = billDao.queryBillByBids(param);
				if (orderInfos != null) {
					for (Map<Object, Object> order : orderInfos) {
						try {
							String bid = order.get("bid").toString();
							orderInfoMap.put(bid, order);
							// 签约店铺的用户uid
							Integer signUid = order.get("signUid") == null ? 0 : Integer.parseInt(order.get("signUid").toString());
							uidSet.add(signUid);
							orderUidMap.put(bid, signUid);
						} catch (Exception e) {
							log.warn("获取订单信息失败：" + order.toString());
						}
					}
				}
			}
			log.warn("查询订单信息时间：" + (System.currentTimeMillis() - t1));
			// 批量查询用户信息
			Map<Integer, Map<Object, Object>> userMap = new HashMap<Integer, Map<Object, Object>>();
			List<Map<Object, Object>> pUids = new ArrayList<Map<Object, Object>>();
			if (uidSet.size() > 0) {
				for (Integer tUid : uidSet) {
					Map<Object, Object> m = new HashMap<Object, Object>();
					m.put("anchorUid", tUid);
					pUids.add(m);
				}
				t1 = System.currentTimeMillis();
				List<Map<Object, Object>> userList = liveUserDao.queryLiverInfoByUidList(pUids);
				log.warn("查询用户信息时间：" + (System.currentTimeMillis() - t1));
				if (userList != null) {
					for (Map<Object, Object> user : userList) {
						try {
							String t_uid = user.get("uid").toString();
							userMap.put(Integer.parseInt(t_uid), user);
						} catch (Exception e) {
							log.warn("获取用户信息失败： " + user.toString());
						}
					}
				}
			}


			// 返回添加
			for (int i = 0; i < resultList.size(); i++) {
				Map<Object, Object> m = resultList.get(i);

				String source = m.get("source") == null ? null :  m.get("source").toString();  //订单

				Integer t_uid = orderUidMap.get(source);
				m.remove("source");
				m.put("uid", "");
				m.put("nname", "");


				if (t_uid != null) {
					Map<Object, Object> user = userMap.get(t_uid);
					if (user != null) {
						String userNname = user.get("nname") == null ? null : user.get("nname").toString();
						String phone = user.get("phone") == null ? "" : user.get("phone").toString();
						String name = StrUtils.standardName(userNname, phone);
						// 用户名
						m.put("nname", name);
					} else {
						log.warn("店铺流水, user=null, uid=" + t_uid);
					}
				} else {
					log.warn("店铺流水, uid=null, " + m.toString());
				}

				if (source != null) {
					Map<Object, Object> orderInfo = orderInfoMap.get(source);
					if (orderInfo != null) {
						String sellername = orderInfo.get("sellername") == null ? "" : orderInfo.get("sellername").toString();
						// 下级店铺名称，需要加*
						if (t_uid != null && !t_uid.equals(uid)) {
							sellername = sellerService.replaceSellerName(sellername);
						}
						m.put("sellername", sellername);
					} else {
						log.warn("店铺流水，orderInfo=null，订单Id=" + source + " ,"+ m.toString());
					}
				} else {
					log.warn("店铺流水，订单Id=null，" + m.toString());

				}
			}
			// 响应
			MapResponse respnose = new MapResponse(ResponseCode.SUCCESS,"成功");
			map.put("flows", resultList);
			respnose.setResponse(map);
			return respnose;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询商家订单明细出错："+e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE,"查询商家订单明细出错");
		}
	}

	// 店铺流水总收入（总收入，本月收入，上个月收入）
	private Map<Object, Object> totalFlowInfoList(Integer uid, List<Integer> uids, Integer expansionType) {
		Map<Object, Object> map = xmerOrderRecordService.totalIncome(uid, uids, expansionType, 2);
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("totalincome", map.get("totalincome"));  //总收入
		resultMap.put("mouthincome", map.get("mouthincome"));  //本月
		resultMap.put("upmouthincome", map.get("upmouthincome"));  //上个月
		return map;
	}





}
