package com.xmniao.xmn.core.xmer.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.common.request.FlowInfoListPayRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.util.StrUtils;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.FlowInfoListRequest;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.xmer.dao.FlowInfoDao;

/**
 * 项目名称： xmnService
 * 类名称： FlowInfoService
 * 类描述：店铺流水明细（未分账和分账的流水）
 * 创建人： lifeng
 * 创建时间： 2016年5月24日下午1:02:38
 * @version
 */
@Service
public class FlowInfoService {
	/**
	 * 日志报错
	 */
	private Logger log = Logger.getLogger(FlowInfoService.class);
	
	
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
	private LiveUserDao liveUserDao;
	@Autowired
	private SaasOrderDao saasOrderDao;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private XmerOrderRecordService xmerOrderRecordService;
	
	/**
	 * @Title: 查询流水明细列表
	 * @Description: 
	 * @return
	 */
	public Object queryFlowInfo(FlowInfoListRequest flowInfoListRequest) {
		try {
			//存储响应参数实例
			Map<Object,Object> map = new HashMap<Object,Object>();
			//验证token
			String sUid = sessionTokenService.getStringForValue(flowInfoListRequest.getSessiontoken()).toString();
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
			List<Integer> uids = xmerOrderRecordService.getUidsByUrs(uid, isXmer, isV, isM); //下级及下下级用户uid
			int type = Integer.valueOf(flowInfoListRequest.getType());
			uids.add(uid);


			List<Map<String, Integer>> sellers = sellersByUids(uids);
			List<Integer> sellerIds = new ArrayList<Integer>();
			Map<Integer, Integer> sellerChannel = new HashMap<Integer, Integer>();
			for (Map<String, Integer> seller : sellers) {
				Integer sellerid = seller.get("sellerid");
				sellerIds.add(sellerid);
				sellerChannel.put(sellerid, seller.get("saas_channel"));
			}


			if(flowInfoListRequest.getPage() == 1){ //首页时
				Map<Object, Object>  total = totalFlowInfoList(sellerIds, sellerChannel, uid);
				map.putAll(total);
			}

			List<Map<Object,Object>> result = getSellersFlowByType(sellerIds, type, flowInfoListRequest.getPage(), Constant.PAGE_LIMIT,
					flowInfoListRequest.getStartdate(), flowInfoListRequest.getEnddate());
			Map<String, String> userNameMap = getUserNameByUid(uids);

			List<Map<Object,Object>> resultList = new ArrayList<Map<Object,Object>>();
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					Map<Object, Object> item = result.get(i);
					Map<Object, Object> m = new HashMap<Object, Object>();
					Object xmer_uid = item.get("xmer_uid");
					Integer sellerid = item.get("sellerid") == null ? 0 : Integer.parseInt(item.get("sellerid").toString());
					if (xmer_uid == null) {
						continue;
					}
					int xUid = Integer.parseInt(xmer_uid.toString());
					Integer saas_channel = sellerChannel.get(sellerid);
					// 不需要查询，下级 v客，中脉，主播赠送v客，签约的店铺
					if (saas_channel == null || (xUid != uid && saas_channel != 1)) {
						continue;
					}
					String nname = userNameMap.get(xmer_uid.toString());
					String sellername = item.get("sellername") == null ? "" : item.get("sellername").toString();
					// 下级店铺名称，需要格式化
					if (Integer.parseInt(xmer_uid.toString()) != uid) {
						sellername = sellerService.replaceSellerName(sellername);
					}
					m.put("sellername", sellername);

					m.put("sdate", item.get("sdate").toString());
					m.put("amount", item.get("amount").toString());
					m.put("nname", nname == null ? "" : nname);
					resultList.add(m);
				}
			}

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

	// 统计店铺总流水流水（分账和未分账）
	public Map<Object,Object> totalFlowAmount(Integer uid) {
		Map<Integer, Boolean> identityMap = xmerService.identityMap(uid);
//			1 寻觅客 2中脉 3 V客 4 主播v客
		boolean isXmer = identityMap.get(1);
		boolean isV = identityMap.get(3);
		boolean isM = identityMap.get(2);
		boolean isAnchorV = identityMap.get(4);
		//获取所有需要统计流水的寻蜜客id
		List<Integer> uids = new ArrayList<Integer>();
		List<Integer> uidToOnes = ursEarningsRelationDao.queryRelationList(uid);
		if (uidToOnes != null) {
			uids.addAll(uidToOnes);
		}
		uids.add(uid);
		List<Map<String, Integer>> sellers = sellersByUids(uids);
		Map<Integer, Integer> sellerChannel = new HashMap<Integer, Integer>();
		List<Integer> sellerIds = new ArrayList<Integer>();
		for (Map<String, Integer> m : sellers) {
			sellerIds.add(m.get("sellerid"));
			sellerChannel.put(m.get("sellerid"), m.get("saas_channel"));
		}
		Map<Object, Object>  total = totalFlowInfoList(sellerIds, sellerChannel, uid);
		return total;
	}

	// 批量签约店铺
	public List<Map<String, Integer>> sellersByUids(List<Integer> uidList) {
		List<Map<String, Integer>> sellers = new ArrayList<Map<String, Integer>>();
		Map<Object, Object> param = new HashMap<Object, Object>();
		param.put("uids", uidList);
		List<Map<Object, Object>> sellerList = saasOrderDao.getSignSellerByUidList(param);
		if (sellerList != null && sellerList.size() > 0) {
			for (int i = 0; i < sellerList.size(); i++) {
				Object sellerid = sellerList.get(i).get("sellerid");
				if (sellerid != null) {
					Map<String, Integer> t = new HashMap<String, Integer>();
					t.put("sellerid", Integer.parseInt(sellerid.toString()));
					Integer saas_channel = sellerList.get(i).get("saas_channel") == null ? 1 : Integer.parseInt(sellerList.get(i).get("saas_channel").toString());
					t.put("saas_channel", saas_channel);
					sellers.add(t);
				}
			}
		}
		return sellers;
	}

	// 统计店铺流水
	public Map<Object, Object> totalFlowInfoList(List<Integer> sellerIds, Map<Integer, Integer> sellerChannel, Integer uid) {
		Map<Object, Object> param = new HashMap<Object, Object>();
		// 查询店铺的总流水，当月，上个月
		Double totalincome = 0d;
		Double mouthincome = 0d;
		Double upmouthincome = 0d;
		if (sellerIds != null && sellerIds.size() > 0) {
			param.clear();
			param.put("sellerIds", sellerIds);
			List<Map<Object, Object>> totalFlow = billDao.queryAllSellersFlow(param);
			for (Map<Object, Object> m : totalFlow) {
				try {
					int xmer_uid = Integer.parseInt(m.get("xmer_uid").toString());
					Double money = Double.parseDouble(m.get("amount").toString());
					int sellerid = Integer.parseInt(m.get("sellerid").toString());
					Integer saas_chanel = sellerChannel.get(sellerid) == null ? 1 : sellerChannel.get(sellerid);
					// 下级账号，不需要查询非寻觅客签约流水
					if (xmer_uid != uid && saas_chanel != 1) {
						continue;
					}
					totalincome = totalincome + money;
				} catch (Exception e) {
					log.info("统计店铺所有流水金额失败");
				}
			}
//			所有
//			totalincome = billDao.sumSellerOrderBySellerIds(param);
//			totalincome = totalincome == null ? 0d : totalincome;

			param.clear();
			param.put("sellerIds", sellerIds);
//			当月
			param.put("sdate", DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.now()),"yyyy-MM-dd"));
			param.put("edate", DateUtil.format(DateUtil.lastDayOfMonth(DateUtil.now()),"yyyy-MM-dd"));
			List<Map<Object, Object>> mouthFlow = billDao.queryAllSellersFlow(param);
			for (Map<Object, Object> m : mouthFlow) {
				try {
					int xmer_uid = Integer.parseInt(m.get("xmer_uid").toString());
					Double money = Double.parseDouble(m.get("amount").toString());
					int sellerid = Integer.parseInt(m.get("sellerid").toString());
					Integer saas_chanel = sellerChannel.get(sellerid) == null ? 1 : sellerChannel.get(sellerid);
					// 下级账号，不需要查询非寻觅客签约流水
					if (xmer_uid != uid && saas_chanel != 1) {
						continue;
					}
					mouthincome = mouthincome + money;
				} catch (Exception e) {
					log.info("统计店铺当月流水金额失败");
				}
			}

//			mouthincome = billDao.sumSellerOrderBySellerIds(param);
//			mouthincome = mouthincome == null ? 0d : mouthincome;

			param.clear();
			param.put("sellerIds", sellerIds);
//			上个月
			param.put("sdate", DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.addMonth(DateUtil.now(), -1)),"yyyy-MM-dd"));
			param.put("edate", DateUtil.format(DateUtil.lastDayOfMonth(DateUtil.addMonth(DateUtil.now(), -1)),"yyyy-MM-dd"));
			List<Map<Object, Object>> upMouthFlow = billDao.queryAllSellersFlow(param);
			for (Map<Object, Object> m : upMouthFlow) {
				try {
					int xmer_uid = Integer.parseInt(m.get("xmer_uid").toString());
					Double money = Double.parseDouble(m.get("amount").toString());
					int sellerid = Integer.parseInt(m.get("sellerid").toString());
					Integer saas_chanel = sellerChannel.get(sellerid) == null ? 1 : sellerChannel.get(sellerid);
					// 下级账号，不需要查询非寻觅客签约流水
					if (xmer_uid != uid && saas_chanel != 1) {
						continue;
					}
					upmouthincome = upmouthincome + money;
				} catch (Exception e) {
					log.info("统计店铺当月流水金额失败");
				}
			}

//			upmouthincome = billDao.sumSellerOrder(param);
//			upmouthincome = upmouthincome == null ? 0d : upmouthincome;
		}

		Map<Object, Object> map = new HashMap<>();
		map.put("totalincome", totalincome);  //总流水
		map.put("mouthincome", mouthincome);  //当月
		map.put("upmouthincome", upmouthincome);  //上个月
		return map;
	}

	List<Map<Object,Object>> getSellersFlowByType(List<Integer> sellerIds, int type, int page, int pageSize, String startDate, String endDate) {
		List<Map<Object,Object>> result = null;
		switch(type){
			case 0://全部
				result = sumSellersFlow(sellerIds, page, pageSize, null, null);
				return result;
			case 1:  //本月流水
				result = sumSellersFlow(sellerIds, page, pageSize,
						DateUtil.format(DateUtil.getMonthFirstDay(DateUtil.now()),"yyyy-MM-dd"),
						DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.addMonth(DateUtil.now(), 1)),"yyyy-MM-dd"));
				return result;
			case 2:  //上月流水

				result = sumSellersFlow(sellerIds, page, pageSize,
						DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.addMonth(DateUtil.now(), -1)),"yyyy-MM-dd"),
						DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.addMonth(DateUtil.now(), 0)),"yyyy-MM-dd"));
				return result;
			case 3:  //自定义
				Date sdate = DateUtil.parse(startDate, "yyyy-MM-dd");
				Date edate = DateUtil.parse(endDate,"yyyy-MM-dd");
				result = sumSellersFlow(sellerIds, page, pageSize,
						DateUtil.format(sdate, "yyyy-MM-dd"),
						DateUtil.format(DateUtil.addDay(edate, 1), "yyyy-MM-dd"));
				return result;
			default:
				throw new IllegalStateException("查询类型错误： " + String.valueOf(type));
		}
	}

	List<Map<Object,Object>> sumSellersFlow(List<Integer> sellerIds, int page, int pageSize, String sdate, String edate) {
		if (sellerIds == null || sellerIds.size() == 0) {
			log.warn("查询店铺流水，sellerIds == null || sellerIds.size() == 0");
			return null;
		}
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("page", page);//页数
		map.put("limit", pageSize);//页行数
		map.put("sellerIds", sellerIds);
		map.put("sdate", sdate);
		map.put("edate", edate);
		List<Map<Object,Object>> result = billDao.queryAllSellersFlow(map);
		return result;
	}
	

	/**
	 * 
	* @Title: queryFriendShipByUid
	* @Description: 根据uid查询寻蜜客的下级
	* @return List<Integer>
	 */
	public List<Integer> queryFriendShipByUid(Integer uid){
		return flowInfoDao.queryFriendShipByUid(uid);
	}


	/**
	 * 查询上下级
	 * @param uid
	 * @return
	 */
	/**
	 *
	 * @Title: getUids
	 * @Description: 获取需要统计流水的的寻蜜客id
	 * @return List<Integer>    返回类型
	 * @throws
	 */
	public List<Integer> getUids(Integer uid) {
		//存储所有寻蜜客的下级跟下下级的寻蜜客id
		List<Integer> uids = new ArrayList<>();
		uids.add(uid);
		//查询下线和下下线的uid
		List<Integer> uidToOnes = ursEarningsRelationDao.queryRelationList(uid);
		if (uidToOnes != null) {
			uids.addAll(uidToOnes);
		}
		return uids;
	}

	/**
	 * 根据uid列表获取用户昵称或标准化手机号码
	 * @param uids
	 * @return
	 */
	public Map<String, String> getUserNameByUid(List<Integer> uids) {
		Map<String, String> userNameMap = new HashMap<String, String>();
		if (uids != null) {
			List<Map<Object, Object>> param = new ArrayList<Map<Object, Object>>();
			for (int i = 0; i < uids.size(); i++) {
				Map<Object, Object> m = new HashMap<Object, Object>();
				m.put("anchorUid", uids.get(i));
				param.add(m);
			}
			// 获取签约人
			List<Map<Object, Object>> userList = liveUserDao.queryLiverInfoByUidList(param);
			for (Map<Object, Object> user : userList) {
				String userUid = user.get("uid").toString();
				String userNname = user.get("nname") == null ? null : user.get("nname").toString();
				String phone = user.get("phone") == null ? "" : user.get("phone").toString();
				String name = StrUtils.standardName(userNname, phone);
				userNameMap.put(userUid, name);
			}
		}
		return userNameMap;
	}

}
