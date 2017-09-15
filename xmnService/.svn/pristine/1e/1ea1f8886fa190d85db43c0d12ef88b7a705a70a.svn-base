package com.xmniao.xmn.core.wealth.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.UrsEarningsRelationInfo;
import com.xmniao.xmn.core.thrift.ResponsePageList;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StrUtils;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import com.xmniao.xmn.core.xmer.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IncomeInfoRequest;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.wealth.dao.IncomeInfoDao;
import com.xmniao.xmn.core.xmer.dao.SaasSoldOrderDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：IncomeInfoService   
* 类描述：  软件销售金额收入明细接口service
* 创建人：yezhiyong    
* 创建时间：2016年5月27日 上午10:34:45   
* @version    
*
 */
@Service
public class IncomeInfoService {

	private final Logger log = Logger.getLogger(IncomeInfoService.class);
	//注入dao
	@Autowired
	private SaasOrderDao saasOrderDao;
	@Autowired
	private SaasSoldOrderDao saasSoldOrderDao;
	@Autowired
	private SessionTokenService sessionTokenService;
	@Autowired
	private XmerService xmerService;
	@Autowired
	private LiveUserDao liveUserDao;
	@Autowired
	private XmerOrderRecordService xmerOrderRecordService;
	@Autowired
	private ThriftWalletExpansionService thriftWalletExpansionService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 
	* @Title: queryIncomeInfo
	* @Description: 查询软件销售收入明细列表
	* @return Object    返回类型
	* @throws
	 */
	public Object queryIncomeInfo(IncomeInfoRequest request) {
		try {
			//获取寻蜜客的uid
			String sessionToken = request.getSessiontoken();
			String sUid = sessionTokenService.getStringForValue(sessionToken) + "";
			if(sUid.equals("")||sUid.equals("null")){
				return new BaseResponse(ResponseCode.FAILURE, "查看收入明細列表失败,sessiontoken失效");
			}
			Integer uid = Integer.parseInt(sUid);
			//存储结果
			Map<Object, Object> resultMap = new HashMap<>();

			Map<Integer, Boolean> identityMap = xmerService.identityMap(uid);
//			1 寻觅客 2中脉 3 V客
			boolean isXmer = identityMap.get(1);
			boolean isV = identityMap.get(3);
			boolean isM = identityMap.get(2);
			boolean isAnchorV = identityMap.get(4);
			isXmer = isXmer || isAnchorV;
//			List<Integer> uids = xmerOrderRecordService.getUidsByUrs(uid, isXmer, isV, isM); //下级及下下级用户uid
			List<Integer> uids = null;
			Integer expansionType = xmerOrderRecordService.getExpansionTypeByUid(uid, isXmer, isV, isM);

			if(request.getPage() == 1){ //首页时
				// 1:saas收益    2:店铺流水
				Map<Object, Object> income = totalIncome(uid, uids, expansionType);
				resultMap.putAll(income);
			}
//			int type, Integer uid, List<Integer> uids, Integer expansionType, Integer incomeType,
//					Integer page, Integer pageSize, String startdate, String enddate
			int type = request.getType();
//			// 1 查询收入
			ResponsePageList responsePageList = xmerOrderRecordService.getOrderRecordByType(type, uid, uids, expansionType, 1,
					request.getPage(), Constant.PAGE_LIMIT, request.getStartdate(), request.getEnddate());
			if (responsePageList.getDataInfo().getState() != 0) {
				log.warn("调用远程支付扩展服务失败： " + responsePageList.getDataInfo().getMsg());
				return new BaseResponse(ResponseCode.FAILURE, "获取收入明细列表, 调用远程服务失败");
			}
			List<Map<Object,Object>> resultList = new ArrayList<Map<Object,Object>>();

			Set<Integer> sUids = new HashSet<Integer>();  // 签约店铺或者购买软件用户uid列表
			//	01开头 t_saas_order订单  02开头 t_saas_sold_order
			List<String> pSaasOrder = new ArrayList<String>();
			List<String> pSaasSoldOrder = new ArrayList<String>();

			List<Map<String,String>>  pageList = responsePageList.getPageList();
			for (int i = 0; i < pageList.size(); i++) {
				Map<String, String> item = pageList.get(i);
				String t_uid = item.get("uid");  //用户id
				String source = item.get("source");  //订单号
				String createTime = item.get("createTime");  //记录时间
				String amount = item.get("amount");  //收益金额
				String remark = item.get("remark");  //备注

				if (source != null && source.startsWith("01")) {  //01开头 t_saas_order订单
					pSaasOrder.add(source);
				} else if (source != null && source.startsWith("02")) {  //02开头 t_saas_sold_order
					pSaasSoldOrder.add(source);
				} else {
					log.warn("查看软件流水，订单号（01开头 t_saas_order订单， 02开头 t_saas_sold_order）有误：" + String.valueOf(source));
				}

				Map<Object, Object> m = new HashMap<Object, Object>();
				m.put("zdate", createTime == null ? "" : createTime);  //消费时间
				m.put("amount", amount == null ? 0 : amount);  //收入金额
				m.put("status", Constant.SIGN_STATUS_SUCCESS);  //2签约成功
				m.put("source", source);  // 不返回前端
				m.put("uid", t_uid);  //用户uid
				m.put("sellername", ""); //店铺
				resultList.add(m);
			}

			// 订单：uid map
			Map<String, Integer> orderUidMap = new HashMap<String, Integer>();
			//批量查询订单saas数量
			List<Integer> sellerIds = new ArrayList<Integer>();
			Map<String, Map<Object, Object>> saasInfoMap = new HashMap<String, Map<Object, Object>>();
			// 2.1 查看Saas软件
			if(pSaasOrder.size() > 0) {
				Map<Object, Object> param = new HashMap<Object, Object>();
				param.put("ordersnList", pSaasOrder);
				List<Map<Object, Object>> orderInfos = saasOrderDao.querySaasOrderInfoByOrdersnList(param);
				if (orderInfos != null) {
					for (Map<Object, Object> order : orderInfos) {
						try {
							String orderId = order.get("ordersn").toString();
							order.put("whichType", 1);
							saasInfoMap.put(orderId, order);

							// 获取签约用户的uid，以及映射订单和uid
							if (order.get("uid") != null) {
								Integer tUid = Integer.parseInt(order.get("uid").toString());
								orderUidMap.put(orderId, tUid);
								sUids.add(tUid);
							}
						} catch (Exception e) {
							log.warn("获取订单信息失败：" + order.toString(), e);
						}
					}
				}
			}

			// 2.2 查看签约店铺
			if (pSaasSoldOrder.size() > 0) {
				Map<Object, Object> param = new HashMap<Object, Object>();
				param.put("orderIds", pSaasSoldOrder);
				List<Map<Object, Object>> orderInfos = saasSoldOrderDao.querySaasOrderInfoByIds(param);
				if (orderInfos != null) {
					for (Map<Object, Object> order : orderInfos) {
						try {
							String orderId = order.get("ordersn").toString();
							order.put("whichType", 2);
							saasInfoMap.put(orderId, order);
							// 获取签约用户的uid，以及映射订单和uid
							if (order.get("uid") != null) {
								Integer tUid = Integer.parseInt(order.get("uid").toString());
								orderUidMap.put(orderId, tUid);
								sUids.add(tUid);
							}
						} catch (Exception e) {
							log.warn("获取订单信息失败：" + order.toString());
						}
					}
				}
			}

			// 3 批量查询用户信息
			Map<Integer, Map<Object, Object>> userMap = new HashMap<Integer, Map<Object, Object>>();
			List<Map<Object, Object>> pUids = new ArrayList<Map<Object, Object>>();
			for (Integer t_uid : sUids) {
				Map<Object, Object> tmp = new HashMap<Object, Object>();
				tmp.put("anchorUid", t_uid);
				pUids.add(tmp);
			}
			if (pUids.size() > 0) {
				List<Map<Object, Object>> userList = liveUserDao.queryLiverInfoByUidList(pUids);
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
			log.info("saas软件订单：" + pSaasOrder.toString());
			log.info("saas店铺订单：" + pSaasSoldOrder.toString());
			// 购买软件描述
			String incomeSaasTitle = "";
			try {
				incomeSaasTitle = propertiesUtil.getValue("incomeSaasTitle", "conf_common.properties");
			} catch (Exception e) {
				log.warn("解析配置错误，incomeSaasTitle conf_common.properties");
			}

			//  4.返回添加
			for (int i = 0; i < resultList.size(); i++) {
				Map<Object, Object> m = resultList.get(i);
//				String t_uid = m.get("uid") == null ? null : m.get("uid").toString();
				String source = m.get("source") == null ? null :  m.get("source").toString();

				m.remove("source");  //订单ID
				m.put("uid", "");
				m.put("nname", "");
				Integer t_uid = orderUidMap.get(source);
				if (t_uid != null) {
					Map<Object, Object> user = userMap.get(t_uid);
					if (user != null) {
						String userNname = user.get("nname") == null ? null : user.get("nname").toString();
						String phone = user.get("phone") == null ? "" : user.get("phone").toString();
						String name = StrUtils.standardName(userNname, phone);
						// 用户名
						m.put("nname", name);
					} else {
						log.warn("软件流水：user=null, uid=" + t_uid);
					}
				} else {
					log.warn("软件流水：uid=null, " + m.toString());
				}

				if (source != null) {
					Map<Object, Object> orderInfo = saasInfoMap.get(source);
					if (orderInfo != null) {
						String sellername = getNameByInfo(orderInfo, incomeSaasTitle, uid);
						m.put("sellername", sellername);
					} else {
						log.warn("软件流水：订单信息=null, 订单Id：" + source + ", " + m.toString());
					}
				} else {
					log.warn("软件流水：订单号=null, " + m.toString());
				}
			}
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
			resultMap.put("flows", resultList);
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取收入明细列表失败");
		}
	}

	// 软件销售总收入（总收入，本月收入，上个月收入）
	private Map<Object, Object> totalIncome(Integer uid, List<Integer> uids, Integer expansionType) {
		Map<Object, Object> map = xmerOrderRecordService.totalIncome(uid, uids, expansionType, 1);
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("totalincome", map.get("totalincome"));  //总收入
		resultMap.put("mouthincome", map.get("mouthincome"));  //本月
		resultMap.put("upmouthincome", map.get("upmouthincome"));  //上个月
		return resultMap;
	}

	private String getNameByInfo(Map<Object, Object> orderInfo, String formatStr, Integer uid) {
		if (orderInfo == null) {
			return "";
		}
		try {
			Integer whichType = (Integer) orderInfo.get("whichType");
			if (whichType == 1) {  //查看Saas软件
				return getSaasName(orderInfo, formatStr);
			} else if (whichType == 2) {  //查看签约店铺
				Integer toUid = Integer.parseInt(orderInfo.get("uid").toString());
				return getSellerName(orderInfo, uid.equals(toUid));
			} else {
				log.warn("查看店铺流水名称，whichType 不是支持类型" + orderInfo.toString());
			}
		} catch (Exception e) {
			log.warn("获取流水名称失败，" + orderInfo.toString());
		}
		return "";
	}

	private String getSaasName(Map<Object, Object> orderInfo, String formatStr) {
		Integer nums = 0;
		try {
			nums = Integer.parseInt(orderInfo.get("nums").toString());
		} catch (Exception e) {
			log.warn("获取saas软件数量失败: " + orderInfo.toString());
		}
		return String.format(formatStr, String.valueOf(nums));
	}

	private String getSellerName(Map<Object, Object> orderInfo, boolean isMine) {
		if (orderInfo == null || orderInfo.get("sellername") == null) {
			return "";
		}
		String sellername = orderInfo.get("sellername") == null ? "" : orderInfo.get("sellername").toString();
		if (!isMine) {  //下级店铺，需要格式化店铺名称
			return sellerService.replaceSellerName(sellername);
		}
		return sellername;
	}
}

