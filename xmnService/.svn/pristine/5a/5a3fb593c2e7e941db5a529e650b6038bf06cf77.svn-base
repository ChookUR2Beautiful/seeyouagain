package com.xmniao.xmn.core.xmer.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.wealth.dao.IncomeInfoDao;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.SaasSoldOrderDao;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.entity.Xmer;

/**
 * 
* 项目名称：xmnService   
* 类名称：XmerHomeService   
* 类描述：寻蜜客主页信息接口实现类    
* 创建人：liuzhihao   
* 创建时间：2016年5月21日 下午4:16:11   
* @version    
*
 */
@Service
public class XmerHomeService {

	private Logger log = Logger.getLogger(XmerHomeService.class);

	// 注入寻蜜客Dao
	@Autowired
	private XmerDao xmerDao;

	// 注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;


	// 注入根目录
	@Autowired
	private String fileUrl;

	@Autowired
	private BannerServcie bannerService;

	// 注入dao
	@Autowired
	private SaasOrderDao saasOrderDao;

	// 注入dao
	@Autowired
	private SaasSoldOrderDao saasSoldOrderDao;

	@Autowired
	private FlowInfoService flowInfoService;

	@Autowired
	private IncomeInfoDao incomeInfoDao;

	@Autowired
	private SellerDao sellerDao;

	@Autowired
	private PropertiesUtil properties;

	/**
	 * 注入未确认金额描述
	 */
	@Autowired
	private String wfzInfo;

	@Autowired
	private XmerAmountService xmerAmountService;

	@Autowired
	private UrsEarningsRelationDao ursEarningsRelationDao;
	

	/***
	 * 寻蜜客主页信息
	 */
	public Object xmerHome(BaseRequest baseRequest) {
		try {
			DecimalFormat df = new DecimalFormat("0.00");// 格式化数据
			// 创建一个存储数据的map实例
			Map<Object, Object> map = new HashMap<Object, Object>();
			String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()).toString();
			// 获取寻蜜客对象
			Xmer xmer = xmerDao.selectByUid(Integer.valueOf(uid));
			if (xmer == null) {
				return new BaseResponse(ResponseCode.XMER_NOT_EXIST, "查询没有该寻蜜客！");
			}
			if (xmer.getAchievement() == null) {
				xmer.setAchievement("");
			}
			if (xmer.getSoldNums() == null) {
				xmer.setSoldNums(0);
			}
			map.put("achievement", xmer.getAchievement());// 头衔名称

			List<Integer> relationList = ursEarningsRelationDao.queryRelationList(Integer.parseInt(uid));
			map.put("partnernums", relationList.size());// 伙伴数量

			map.put("sex", xmer.getSex());
			
			// 查询已签约的店铺数量，包括自己和下级，下下级已上线和待上线总数量
			Integer readyNums = sellerDao.queryReadyNumByUids(relationList,Integer.parseInt(uid));
			Integer waitNums = sellerDao.queryWaitNumByUid(Integer.parseInt(uid));
			/*
			 * int sellernums=0; try {
			 * sellernums=sellerDao.queryXmerSellerNums(Integer.valueOf(uid));//
			 * 查询寻蜜客签约店铺数量 } catch (Exception e) { e.printStackTrace(); }
			 */
			map.put("soldnums", readyNums + waitNums);// 寻蜜客签约商家店铺数量(以前显示的寻蜜客卖出数量)
			map.put("xmertype", xmer.getRtype()); // 寻蜜客类型
			// 查询寻蜜客购买的总套餐数量跟库存数量
			Map<Object, Object> saasOrderMap = saasOrderDao.sumNumsAndStockByUid(Integer.parseInt(uid));
			if (saasOrderMap != null && saasOrderMap.size() > 0
					&& StringUtils.isNotBlank(saasOrderMap.get("nums").toString())
					&& StringUtils.isNotBlank(saasOrderMap.get("stock").toString())) {
				map.put("nums", saasOrderMap.get("nums"));// 套餐数量
				map.put("stock", saasOrderMap.get("stock"));// 库存数量
			} else {
				// return new BaseResponse(ResponseCode.FAILURE, "查询套餐/库存数量异常");
				map.put("nums", 0);// 套餐数量
				map.put("stock", 0);// 库存数量
			}


			Double totalincome = 0D;
			Double saasAmount = 0D;
			Double sellerIncome = 0D;
			try {
				Map<Object, Object> result = xmerAmountService.amount(Integer.parseInt(uid));
				totalincome = Double.parseDouble(result.get("totalIncome").toString());
				saasAmount = Double.parseDouble(result.get("softIncome").toString());
			} catch (Exception e) {
				log.warn("查询寻蜜客收入异常");
				e.printStackTrace();
			}
			
			// 流水总额,店铺总流水
			Map<Object, Object> result = flowInfoService.totalFlowAmount(Integer.parseInt(uid));
			sellerIncome = Double.parseDouble(result.get("totalincome").toString());
			
			BigDecimal b1 = new BigDecimal(sellerIncome);
			String tincome = df.format(b1.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的总流水
			map.put("flowtotal", tincome);
			
			
			//总收入额
			BigDecimal b3 = new BigDecimal(totalincome);// 格式化寻蜜客总收入
			String income = df.format(b3.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的寻蜜客总收入
			map.put("income", income);
			
			//软件销售额
			BigDecimal b4 = new BigDecimal(saasAmount);// 格式化寻蜜客软件销售总金额
			String sAmount = df.format(b4.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的寻蜜客软件销售总金额
			map.put("saasAmount", sAmount);

			// 头像
			String avatar = xmerDao.queryXmerAvatar(Integer.valueOf(uid));
			if (avatar == null || avatar.equals("") && avatar.equals("null")) {
				avatar = "";
			}

			map.put("avatar", fileUrl + avatar);

			//是否显示 预计可以再赚金额
			Integer showWzfInfo= Integer.parseInt(properties.getValue("showWzfInfo", "conf_xmer.properties"));
			map.put("showWzfInfo",showWzfInfo);
			
			String amount = "";
			if(showWzfInfo.equals(1)){
				// 获取未到账的金额
				Double wfzAmount = getWfzAmount(Integer.parseInt(uid));// 未到账金额
				BigDecimal b2 = new BigDecimal(wfzAmount);
				amount = df.format(b2.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的未到账金额
			}
			map.put("wfzAmount", amount);// 未到账

			// 未确认金额描述
			map.put("wfzInfo", wfzInfo);
			

			List list = bannerService.getBannerList();
			map.put("banners", list);
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "成功");
			response.setResponse(map);

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误，请联系管理员");
		}
	}

	/**
	 * @throws IOException
	 * 
	 * @Title: getWfzAmount @Description: 获取未到账的金额 @return Double
	 * 返回类型 @author @throws
	 */
	public Double getWfzAmount(Integer uid) throws IOException {

		Double orderMount = 0D;
		List<Integer> uids = new ArrayList<>();
		// 查询自己签约店铺的流水分账
		Map<Object, Object> orderMap = new HashMap<>();
		orderMap.put("uid", uid);
		List<Map<Object, Object>> orderList = incomeInfoDao.queryMikeAmountByuid(orderMap);
		for (Map<Object, Object> order : orderList) {
			if (order.get("commission") == null)
				continue;
			Double mikeAmount = 0D;
			try {
				JSONObject json = JSONObject.parseObject(order.get("commission").toString());
				if (json.getDouble("mike_amount") != null) {
					mikeAmount = json.getDouble("mike_amount");
				}
			} catch (Exception e) {
				e.printStackTrace();
				mikeAmount = 0D;
			}
			orderMount += mikeAmount;
		}
		// 查询下一级的店铺流水分账
		List<Integer> uidToOnes = flowInfoService.queryFriendShipByUid(uid);
		for (Integer uidToOne : uidToOnes) {
			uids.add(uidToOne);
			orderMap = new HashMap<>();// 订单map
			orderMap.put("uid", uidToOne);
			orderList = incomeInfoDao.queryMikeAmountByuid(orderMap);
			for (Map<Object, Object> order : orderList) {
				if (order.get("commission") == null)
					continue;
				Double parentMikeAmount = 0D;
				try {
					JSONObject json = JSONObject.parseObject(order.get("commission").toString());
					if (json.getDouble("parent_mike_amount") != null) {
						parentMikeAmount = json.getDouble("parent_mike_amount");
					}
				} catch (Exception e) {
					e.printStackTrace();
					parentMikeAmount = 0D;
				}
				orderMount += parentMikeAmount;
			}
			// 查询下下级的店铺流水分账
			List<Integer> uidToTwos = flowInfoService.queryFriendShipByUid(uidToOne);
			for (Integer uidToTwo : uidToTwos) {
				uids.add(uidToTwo);
				orderMap = new HashMap<>();// 订单map
				orderMap.put("uid", uidToTwo);
				orderList = incomeInfoDao.queryMikeAmountByuid(orderMap);
				for (Map<Object, Object> order : orderList) {
					if (order.get("commission") == null)
						continue;
					Double topMikeAmount = 0D;
					try {
						JSONObject json = JSONObject.parseObject(order.get("commission").toString());
						if (json.getDouble("top_mike_amount") != null) {
							topMikeAmount = json.getDouble("top_mike_amount");
						}

					} catch (Exception e) {
						e.printStackTrace();
						topMikeAmount = 0D;
					}
					orderMount += topMikeAmount;
				}
			}
		}
		Double saasAmount = 0.00D;
		if (uids != null && uids.size() > 0) {

			
			/** 分账规则修改日期（9月7号（不含7号）之前购买的套餐按108算，7号及7号后按72算） */
			String wfzDate = properties.getValue("wfzDate", "conf_xmer.properties");
			/** 请求参数 */
			Map<String, Object> params = new HashMap<>();
			params.put("uids", uids);
			params.put("wfzDate", wfzDate);
			params.put("type", 0);

			/** 查询9月7日前的 */
			// 总的寻蜜客签约套数
			Integer allSaasNums = saasOrderDao.getXmerSaasNums(params);
			if (allSaasNums != null && allSaasNums > 0) {
				// 查寻9月7日前签约套数的订单id
				List<String> ordersns = saasOrderDao.getXmerSaasOrdersns(params);
				// 总的已上线店铺数
				int allSellerNums = saasSoldOrderDao.getXmerSellerNums(ordersns);
				// 9月7日前未到账金额
				saasAmount = ArithUtil.mul(allSaasNums - allSellerNums, Constant.SAAS_RETURN_AMOUNT);
			}

			/** 9月7日后未到账金额 */
			params.put("type", 1);
			// 总的寻蜜客签约套数
			Integer nallSaasNums = saasOrderDao.getXmerSaasNums(params);
			if (nallSaasNums != null && nallSaasNums > 0) {
				// 查寻9月7日前签约套数的订单id
				List<String> nordersns = saasOrderDao.getXmerSaasOrdersns(params);
				// 9月7日后已到帐数量
				int nallSellerNums = saasSoldOrderDao.getXmerSellerNums(nordersns);
				// 9月7日前未到账金额需要减去优惠卷金额
				Double newsaasAmount = ArithUtil.mul(nallSaasNums - nallSellerNums,
						ArithUtil.sub(Constant.SAAS_RETURN_AMOUNT, Constant.SAAS_PRE_AMOUNT));
				saasAmount = ArithUtil.add(saasAmount, newsaasAmount);
			}
			
			
			
		}

		return ArithUtil.add(orderMount, saasAmount);
	}
}

