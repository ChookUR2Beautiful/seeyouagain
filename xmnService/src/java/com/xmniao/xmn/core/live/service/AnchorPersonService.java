/**
 * 2016年8月11日 下午1:37:41
 */
package com.xmniao.xmn.core.live.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.integral.PageTypeRequest;
import com.xmniao.xmn.core.common.request.live.AttentionListRequest;
import com.xmniao.xmn.core.common.request.live.ContributeListRequest;
import com.xmniao.xmn.core.common.request.live.LiverRecommendRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.AnchorPersonDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveGivedGiftInfo;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.order.dao.CouponFansOrderDao;
import com.xmniao.xmn.core.thrift.LiveWalletService;
import com.xmniao.xmn.core.thrift.LiveWalletService.Client;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.ResponseSubList;
import com.xmniao.xmn.core.thrift.TopList;
import com.xmniao.xmn.core.thrift.WalletRecord;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：AnchorSendService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月11日 下午1:37:41
 * @version
 */
@Service
public class AnchorPersonService {
	
	//日志
	private final Logger log = Logger.getLogger(AnchorPersonService.class);
		
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//注入dao
	@Autowired
	private AnchorPersonDao anchorpersonDao;
	
	@Autowired
	private PersonalCenterService  personalcenterService;
	
	@Autowired
	private CouponFansOrderDao couponFansOrderDao;
	
	/**
	 * 直播记录Dao
	 * */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;

	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 礼物service
	 * */
	@Autowired
	private LiveGiftsInfoService LiveGiftsInfoService;
	
	@Autowired
	private ThriftUtil thriftUtil;
	@Autowired
	private AnchorSignTypeService anchorSignTypeService;
	/**
	 * 
	* @Title: queryLiveAchorRecord
	* @Description:根据uid及时间查询直播用户当月直播记录
	* @return Object
	 */
	public Object queryLiveAchorRecord(String uuid,String month,int page,int year){
		Map<Object,Object> aRrecordMap=new HashMap<Object,Object>();
		try{
			int uid=Integer.parseInt(uuid);
			Map<Object,Object> achormap=personalcenterService.queryLiverPersonByUid(uid);
			if(achormap==null || achormap.size()<=0){
				aRrecordMap.put("liveRecord",new ArrayList<>() );
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未查到此直播用户记录");
				response.setResponse(aRrecordMap);
				return response;
			}
			
			List<Map<Object,Object>> anchorRecordList=this.queryAnchorRecord(achormap.get("anchorid").toString(), month, page,year);
			if(anchorRecordList==null || anchorRecordList.size()<=0){
				aRrecordMap.put("liveRecord", anchorRecordList);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未查到此直播用户记录");
				response.setResponse(aRrecordMap);
				return response;
			}
			aRrecordMap.put("liveRecord", anchorRecordList);
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取直播用户记录成功");
		response.setResponse(aRrecordMap);
		return response;
	}
	
	/**
	 * 
	* @Title: queryLiveAchorByUid
	* @Description: 根据uid查询主播基本信息
	* @return Map<Object,Object>
	 */
	public Map<Object,Object> queryLiveAchorByUid(int uid){
		Map<Object,Object> achormap=null;
		try{
			achormap=anchorpersonDao.queryLiveAchorByUid(uid);
		}catch(Exception e){
			e.printStackTrace();
			log.error("根据uid查询主播基本信息失败");
		}
		return achormap;
	}

	/**
	 * 
	* @Title: queryAnchorRecord
	* @Description:根据用户uid及时间查询当月直播记录 
	* @return List<Map<Object,Object>>
	 * @throws Exception 
	 */
	public List<Map<Object,Object>> queryAnchorRecord(String anchor_id,String month,int page,int year) throws Exception{
		if(anchor_id==null || anchor_id==""){
			return null;
		}
		Map<Object,Object> param=new HashMap<Object, Object>();
		List<Map<Object,Object>> anchorList=null;
		try{
			param.put("anchor_id", anchor_id);
			param.put("month", month);
			param.put("year", year);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			anchorList=anchorpersonDao.queryAnchorRecord(param);
		}catch(Exception e){
			e.printStackTrace();
			log.error("根据用户uid及时间查询当月直播记录失败");
			throw new Exception("查询黑名单人员 失败");
		}
		return anchorList;
	}
	
	/**
	 * 
	* @Title: queryBirdEggBlance
	* @Description: 获取鸟蛋信息和鸟蛋明细接口  type = 0 鸟蛋信息   type = 1鸟蛋转出明细  type = 2 鸟蛋收入明细-用户送礼明细   type = 3 鸟蛋收入明细-卖出粉丝卷100位明细  type = 4 鸟蛋收入明细-使用粉丝卷50位明细
	* @return Object    返回类型
	* @throws
	 */
	public Object queryBirdEggBlance(PageTypeRequest  pageTypeRequest) {
		//验证token
//		String uid = pageTypeRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(pageTypeRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//type = 0 我的鸟蛋信息   type = 1 鸟蛋转出明细     type = 2  鸟蛋收入明细-用户送礼明细     type = 3  鸟蛋收入明细-卖出粉丝卷100位明细       type = 4 鸟蛋收入明细-使用粉丝卷50位明细
		int type = pageTypeRequest.getType();
		
		if (type == 0) {
			//获取我的鸟蛋信息
			return this.getBirdEggBlanceInfo(uid);
			
		}else if (type == 1) {
			//鸟蛋转出明细
			return this.queryBirdEggTurnoutRecord(pageTypeRequest);
			
		}else if (type == 2) {
			//鸟蛋收入明细-用户送礼明细
			return this.getBirdEggIncomeRecord(pageTypeRequest);
			
		}else if (type == 3 || type == 4) {
			//鸟蛋收入明细-卖出粉丝卷100位明细
			return this.getSellFansCouponRecord(pageTypeRequest);
			
//		}else if (type == 4) {
//			//鸟蛋收入明细-使用粉丝卷50位明细
//			return this.getSellFansCouponRecord(pageTypeRequest);
			
		}else {
			//参数错误
			return new BaseResponse(ResponseCode.FAILURE, "请求参数有误");
			
		}
		
	}
	
	/**
	 * 描述：获取鸟蛋转出明细
	 * @param pageTypeRequest
	 * @return list<Object>
	 * */
	public Object queryBirdEggTurnoutRecord(PageTypeRequest pageTypeRequest){
//		String uid = pageTypeRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(pageTypeRequest.getSessiontoken()) + "";
		MapResponse response = null;
		//组装参数
		try {
			Map<Object, Object> resultMap = new HashMap<Object,Object>();
			
			Map<String, String> walletMap = new HashMap<String,String>();
			walletMap.put("uid", uid);
			walletMap.put("pageNo", pageTypeRequest.getPage().toString());
			walletMap.put("pageSize", pageTypeRequest.getPageSize().toString());
			
			LiveWalletService.Client client = null;
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			
			WalletRecord  walletRecord =client.birdEggDetail(walletMap);
			if (walletRecord!=null && walletRecord.getWalletList().size()>0) {
				List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
				List<Map<String, String>> walletList = walletRecord.getWalletList();
				for (int i = 0; i < walletList.size(); i++) {
					Map<String, String> newWwalletMap =walletList.get(i);
					//转换为整形  
					newWwalletMap.put("eggMoney", newWwalletMap.get("eggMoney")==null?"0":new BigDecimal(newWwalletMap.get("eggMoney").toString()).intValue()+"");
					newList.add(newWwalletMap);
				}
				resultMap.put("walletList", newList);
			}else {
				resultMap.put("walletList", walletRecord.getWalletList());
			}
			
			response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
			response.setResponse(resultMap);
		} catch (Exception e) {
			log.info("调用支付服务异常");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "操作异常");
		}
		return response;
		
	}
	
	
	/**
	 * 
	* @Title: getBirdEggBlanceInfo
	* @Description: 获取我的鸟蛋信息
	* @return Object    返回类型
	* @throws
	 */
	public Object getBirdEggBlanceInfo(String uid) {
		try {
			//结果集
			Map<Object,Object> resultMap = new HashMap<Object,Object>();
			
			ResponseData responseData = null;
			try {
				//组装参数
				Map<String, String> walletMap = new HashMap<String,String>();
				walletMap.put("uid", uid);
				
				//调用支付业务系统,获取我的鸟蛋今日收入，累计，可提现数量
				responseData = this.getBirdeggNums(walletMap);
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("调用支付业务系统,获取我的鸟蛋今日收入，累计，可提现数量失败");
				return new BaseResponse(ResponseCode.FAILURE, "获取我的鸟蛋失败");
			}
			
			if(responseData.getState() != 0){
				return new BaseResponse(ResponseCode.FAILURE, "获取我的鸟蛋失败");
			}
			
			//格式化数据
			Map<String, String> eggMoneymap = responseData.getResultMap();
			DecimalFormat df = new DecimalFormat("0");//格式化数据
			resultMap.put("totalbirdegg", df.format(new BigDecimal(eggMoneymap.get("balance").toString())));//鸟蛋余额
			resultMap.put("todaybirdegg", df.format(new BigDecimal(eggMoneymap.get("todayEgg").toString())));//今日鸟蛋
			resultMap.put("oldtotalbirdegg", df.format(new BigDecimal(eggMoneymap.get("totalEgg").toString())));//累计鸟蛋总额
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取我的鸟蛋成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取我的鸟蛋失败,用户uid=" + uid);
			return new BaseResponse(ResponseCode.FAILURE, "获取我的鸟蛋失败");
		}
	}
	
	
	/**
	 * 
	* @Title: getBirdEggBlanceInfo
	* @Description: 获取我的鸟蛋收入明细
	* @return Object    返回类型
	* @throws
	 */
	public Object getBirdEggIncomeRecord(PageTypeRequest pageTypeRequest) {
		
//		String uid = pageTypeRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(pageTypeRequest.getSessiontoken()) + "";
		MapResponse response = null;
		
		try {
			//结果集
			Map<Object,Object> resultMap = new HashMap<Object,Object>();
			
			try {
				// 返回记录
				List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
				
				//组装参数 获取主播信息
				Map<Object, Object> liverMap = new HashMap<Object,Object>();
				
				LiverInfo liverInfo = liveUserDao.queryLiverByUid(Long.valueOf(uid));
				if (liverInfo!=null && null!=liverInfo.getId()) {
					
					liverMap.put("anchorId", liverInfo.getId());
					liverMap.put("page", pageTypeRequest.getPage());
					liverMap.put("limit", pageTypeRequest.getPageSize());
					List<LiveGivedGiftInfo> giftInfoList = liveUserDao.queryLiverIncomeRecord(liverMap);
					if (giftInfoList.size()>0) {
						//去查询用户
						//查询发礼物的用户的基本信息
//							liverInfo liver = liveUserDao.queryl
						List<Integer> list = new ArrayList<Integer>();
						for (int i = 0; i < giftInfoList.size(); i++) {
							list.add(giftInfoList.get(i).getLiverId().intValue());
						}		
						List<Map<Object,Object>> userListMap = liveUserDao.queryLiverInfoByIdList(list);
						if (userListMap.size()>0) {
							for (int i = 0; i < giftInfoList.size(); i++) {
								for (int j = 0; j < userListMap.size(); j++) {
									LiveGivedGiftInfo givedGiftInfo = giftInfoList.get(i);
									Map<Object, Object> userMap = userListMap.get(j);
									if (givedGiftInfo.getLiverId().toString().equals(userMap.get("id").toString())) {
										Map<Object, Object> giveMap = new HashMap<Object, Object>();
										giveMap.put("nname",userMap.get("nname"));
										giveMap.put("giftName",givedGiftInfo.getGiftName());
										giveMap.put("birdEgg",givedGiftInfo.getPercentAmount().intValue());
										giveMap.put("createTime",DateUtil.format(givedGiftInfo.getCreateTime(), DateUtil.defaultSimpleFormater) );
										giveMap.put("liverId",givedGiftInfo.getLiverId());
										giveMap.put("anchorId",givedGiftInfo.getAnchorId());
										giveMap.put("giftNums",givedGiftInfo.getGiftNums());
										
										resultList.add(giveMap);
									}
								}
							}
						}
						
						//查询钱包总额
						try {
							LiveGivedGiftInfo giftInfo = liveUserDao.queryLiverIncomeRecordAmount(liverMap);
//							Map<String, Object> wallet = LiveGiftsInfoService.getLiveWalletBlance(uid);
//							if (wallet!=null && wallet.size()>0) {
////								BigDecimal turnEggOut = new BigDecimal(wallet.get("turnEggOut").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);//转出鸟蛋总额
//								BigDecimal balance = new BigDecimal(wallet.get("balance").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);//鸟蛋
//								resultMap.put("birdEgg", balance.setScale(2, BigDecimal.ROUND_HALF_UP).intValue());
//							}
							//获取送礼物的鸟蛋获得总量
							if (giftInfo!=null) {
								resultMap.put("birdEgg", giftInfo.getPercentAmount().setScale(2, BigDecimal.ROUND_HALF_UP).intValue());
							}else {
								resultMap.put("birdEgg", 0);
							}
							
						} catch (Exception e) {
							log.info("查询钱包失败");
							e.printStackTrace();
							response = new MapResponse(ResponseCode.FAILURE, "获取钱包失败");
						}
					}
						
				}
				resultMap.put("giveGiftList", resultList);
				response = new MapResponse(ResponseCode.SUCCESS, "");
				response.setResponse(resultMap);
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("调用支付业务系统,获取我的鸟蛋今日收入，累计，可提现数量失败");
				return new BaseResponse(ResponseCode.FAILURE, "获取我的鸟蛋失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取我的鸟蛋失败,用户uid=" + uid);
			return new BaseResponse(ResponseCode.FAILURE, "获取我的鸟蛋失败");
		}
		return response;
	}
	
	
	/**
	 * 
	* @Title: getBirdEggBlanceInfo
	* @Description: 获取我的鸟蛋收入明细
	* @return Object    返回类型
	* @throws
	 */
	public Object getSellFansCouponRecord(PageTypeRequest pageTypeRequest) {
		
//		String uid = pageTypeRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(pageTypeRequest.getSessiontoken()) + "";
		MapResponse response = null;
		
		//结果集
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		
		// 返回记录
		List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
		
		try {
			LiveWalletService.Client  client = null;
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client =new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			Map<String, String> paramMap = new HashMap<String, String>();
			
			paramMap.put("uid", uid);
			if (pageTypeRequest.getType() == 3) {//查询售出的
				paramMap.put("status", "9");
			}
			if (pageTypeRequest.getType() == 4) {//查询使用的
				paramMap.put("status", "10");
				paramMap.put("isUse", "2");//标示是否是需要查询已经使用的人数
			}
			paramMap.put("pageNo", pageTypeRequest.getPage().toString());
			paramMap.put("pageSize", pageTypeRequest.getPageSize().toString());
			ResponseSubList responseSubList = client.BirdeggIncomeList(paramMap);
			
			//根据uid 查询当前主播卖出粉丝券 | 使用粉丝券的位数
//			int seats = couponFansOrderDao.queryAnchorFansSellSeatsCount(paramMap);
			
			if (responseSubList!=null) {
				resultMap.put("birdEgg", new BigDecimal(responseSubList.getCountSum()).setScale(2, BigDecimal.ROUND_HALF_UP).intValue());
//				resultMap.put("seats", seats);
				List<Map<String, String>> subList = responseSubList.getSubList();
				if (subList!=null && subList.size()>0) {
					List<String> list = new ArrayList<String>();
					for (int i = 0; i < subList.size(); i++) {
						list.add(subList.get(i).get("remarks"));
					}
					if (list.size()>0) {
						//根据订单标号批量查询 优惠券信息
						List<Map<Object, Object>> fansList = couponFansOrderDao.queryFansCouponInfoByOrderNoList(list);
						if (fansList.size()>0) {
							for (int i = 0; i < subList.size(); i++) {
								for (int j = 0; j < fansList.size(); j++) {
									Map<String, String> subMap = subList.get(i);
									Map<Object, Object> fansMap = fansList.get(j);
									if (subMap.get("remarks").toString().equals(fansMap.get("order_no").toString())) {
										Map<Object, Object> fallMap = new HashMap<Object, Object>();
										fallMap.put("eggMoney", Math.round(Double.valueOf(subMap.get("eggMoney").toString())));
										fallMap.put("createTime", subMap.get("createTime"));
										fallMap.put("orderNo",subMap.get("remarks") );
										fallMap.put("seats", fansMap.get("seats"));
										fallMap.put("cname", fansMap.get("cname"));
										fallMap.put("cid", fansMap.get("cid"));
										resultList.add(fallMap);
									}
								}
							}
						}
					}
				}
				
				//获取统计主播卖出去了多少位
				int sellCount = couponFansOrderDao.queryAnchorFansSellSeatsCount(paramMap);
				resultMap.put("sellCount", sellCount);
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				resultMap.put("fansSellList", resultList);
				response.setResponse(resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取用户售出粉丝券失败"+uid);
			return new MapResponse(ResponseCode.FAILURE, "售出粉丝券查询异常");
		}finally{
			thriftUtil.coloseTransport();
		}
		return response;
	}
	
	
	/**
	 * 
	* @Title: getBirdeggNums
	* @Description: 调用支付业务系统,获取我的鸟蛋今日收入，累计，可提现数量
	* @return ResponseData    返回类型
	* @throws
	 */
	public ResponseData getBirdeggNums(Map<String,String> walletMap) throws Exception {
		LiveWalletService.Client client = null;
		ResponseData responseData = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			//获取我的鸟蛋今日收入，累计，可提现数量
			responseData = client.getBirdeggNums(walletMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("调用支付业务系统,获取我的鸟蛋今日收入，累计，可提现数量失败");
			throw new Exception("获取我的鸟蛋今日收入，累计，可提现数量失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return responseData;
	}
	
	/**
	 * 
	* @Title: queryEggMoneyNum
	* @Description: 查询我的鸟蛋今日，累计，可提现数量
	* @return Map<Object,Object>
	 */
	public Object queryEggMoneyNum(String uuid){
		int uid=Integer.parseInt(uuid);
		Map<Object,Object> eggMoneymap=new HashMap<Object,Object>();
		Map<String,String> resultMap=null;
		LiveWalletService.Client client = null;
		try {
			Map<Object,Object> AnchorMap=personalcenterService.queryLiverPersonByUid(uid);
			if(AnchorMap==null || AnchorMap.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "未获取主播个人信息，获取我的鸟蛋失败");
			}
			String ledger_ratio=AnchorMap.get("ledger_ratio")==null?"0":AnchorMap.get("ledger_ratio").toString();
			int devideScale=(int) (Double.valueOf(ledger_ratio)*10);
			
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			Map<String,String> param=new HashMap<String,String>();
			param.put("uid", uuid);
			ResponseData responseData=client.getBirdeggNums(param);
			if(responseData.getState()!=0){
				return new BaseResponse(ResponseCode.FAILURE, "获取我的鸟蛋失败,"+responseData.getMsg());
			}
			resultMap=responseData.getResultMap();
			eggMoneymap.put("totalbirdegg", new BigDecimal(resultMap.get("balance").toString()));//鸟蛋余额
			eggMoneymap.put("todaybirdegg", new BigDecimal(resultMap.get("todayEgg").toString()));//今日鸟蛋
			eggMoneymap.put("oldtotalbirdegg", new BigDecimal(resultMap.get("totalEgg").toString()));//累计鸟蛋总额
//			eggMoneymap.put("ledgerRatio", ledger_ratio);//转出鸟蛋比例
			eggMoneymap.put("ledgerRatio", "1000");//转出鸟蛋比例
//			eggMoneymap.put("eggToBlanceRatio", "0.1");//鸟蛋对余额比例
			eggMoneymap.put("eggToBlanceRatio", "1");//鸟蛋对余额比例
			eggMoneymap.put("devidescalemsg", "1000");//新返回
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询我的鸟蛋今日，累计，可提现数量失败");
			return new BaseResponse(ResponseCode.FAILURE, "获取我的鸟蛋失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取我的鸟蛋成功");
		response.setResponse(eggMoneymap);
		return response;
	}
	
	
	
	/**
	 * 
	* @Title: queryEggMoneyDetail
	* @Description: 查询鸟蛋消费明细
	* @return Object
	 */
	public Object queryEggMoneyDetail(String uuid,int page){
		Map<Object,Object> eggmoneyMap=new HashMap<Object, Object>();
		List<Map<String,String>> eggmoneyList=null;
		List<Map<String,String>> eggList=new ArrayList<>();
		try {
	  	TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
		LiveWalletService.Client client = new Client(tMultiplexedProtocol);
		thriftUtil.openTransport();
			Map<String,String> paramMap=new HashMap<String,String>();
			paramMap.put("uid", uuid);//寻蜜鸟uid(id、uid至少二填一，若都填，取id)
			paramMap.put("pageNo", page+"");//页数
			paramMap.put("pageSize",  Constant.PAGE_LIMIT+"");//每页多少条
			
			WalletRecord walletRecord=client.birdEggDetail(paramMap);
			eggmoneyList=walletRecord.getWalletList();
		
			if(eggmoneyList ==null ||eggmoneyList.size()<=0){
				eggmoneyMap.put("walletRecord", eggList);
				log.info("未有鸟蛋明细");
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未有鸟蛋明细");
				response.setResponse(eggmoneyMap);
				return response;
			}
			for(int i=0;i<eggmoneyList.size();i++){
				Map<String,String> eggMap=eggmoneyList.get(i);
				Map<String,String> eggMapt=new HashMap<>();
				eggMapt.put("id", eggMap.get("id"));//记录ID
				eggMapt.put("accountid", eggMap.get("walletId"));//钱包表ID
				eggMapt.put("rtype", eggMap.get("rtype"));//1 转出，5 主播礼物收入，6 主播私信收入 7 主播弹幕收入
				eggMapt.put("egg_money", eggMap.get("eggMoney"));//交易鸟蛋金额
				eggMapt.put("description", eggMap.get("description"));//描述
				eggMapt.put("create_time", eggMap.get("createTime"));//创建时间
//				eggMapt.put("sdate", eggMap.get("sdate"));//记录时间
				eggList.add(eggMapt);
			}
			eggmoneyMap.put("walletRecord", eggList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询鸟蛋消费明细失败");
			return new BaseResponse(ResponseCode.FAILURE, "查询鸟蛋消费明细失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		log.info("获取鸟蛋消费明细成功");
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取鸟蛋消费明细成功");
		response.setResponse(eggmoneyMap);
		return response;
	}

	/**
	 * 
	* @Title: queryContributeList
	* @Description: 土豪榜/房间贡献榜/贡献榜
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object queryContributeList(ContributeListRequest contributeListRequest) {
		try {
			//贡献榜类型
			Integer type = contributeListRequest.getType();
			
			String uid = null;
			if (contributeListRequest.getSessiontoken() != null && StringUtils.isNotEmpty(contributeListRequest.getSessiontoken())) {
				//获取uid
				uid = sessionTokenService.getStringForValue(contributeListRequest.getSessiontoken()) + "";
				
			}
			
			//组装参数
			Map<String, String> walletMap = new HashMap<>();
			walletMap.put("pageNo", contributeListRequest.getPage().toString());
			walletMap.put("pageSize", Constant.PAGE_LIMIT.toString());
			
			//结果集
			List<Map<String, String>> resultList = new ArrayList<>();
			Map<Object, Object> resultMap = new HashMap<>();
			MapResponse response = null;
			
			//此土豪榜有获取当前用户在其排行榜的排名(需要登录,暂停使用)
			if (type == 0) {
				//当前排名
				resultMap.put("currentNo", "0");
				//土豪榜(前20名)
				walletMap.put("rtype", "1");//1.平台鸟币消费排行 2.每场直播消费排行 3.主播个人的消费排行
				
				try {
					resultList = this.getContributeList(walletMap);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("获取土豪榜排行列表失败,错误信息："+e.getMessage());
					return new BaseResponse(ResponseCode.FAILURE,"获取土豪榜排行列表失败");
				}
				
				if (resultList.size() > 0) {
					//获取土豪头像,昵称,性别,等级
					resultList = getRichInfo(resultList);
					if (uid != null && StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
						//获取该用户是否在排行榜排名
						for (Map<String, String> map : resultList) {
							if (map.get("uid").toString().equals(uid)) {
								resultMap.put("currentNo", map.get("rowNo").toString());
							}
						}
					}
				}
				
				//响应
				resultMap.put("contributeList", resultList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取土豪榜排行列表成功");
				response.setResponse(resultMap);
				
			} else if (type == 1) {
				//获取直播基本信息   主播基本信息
				LiveRecordInfo recordInfo = anchorLiveRecordDao.queryLiveRecordById(contributeListRequest.getLiveRecordId());
				Map<Object, Object> liverMap = new HashMap<Object, Object>();
				if (recordInfo != null ) {
					liverMap = liveUserDao.queryLiverInfoById(recordInfo.getAnchor_id().intValue());
					if(liverMap==null || liverMap.size()<=0){
						log.error("获取主播信息异常"+liverMap.get("uid"));
						return new BaseResponse(ResponseCode.FAILURE,"获取主播信息失败");
					}
					
				}else {
					log.error("获取直播记录异常"+recordInfo.getId());
					return new BaseResponse(ResponseCode.FAILURE,"获取直播信息失败");
				}
				//房间排行榜的:当前排行
				walletMap.put("rtype", "2");//1.平台鸟币消费排行 2.每场直播消费排行 3.主播个人的消费排行
				walletMap.put("recordId",contributeListRequest.getLiveRecordId().toString());//直播记录id
				walletMap.put("ledgerRatio",liverMap.get("ledger_ratio")==null?"0":liverMap.get("ledger_ratio").toString());//分账比
				
				//获取当前房间主播历史鸟蛋总数
				String countBalance = this.queryRoomAnchorBirdEgg(contributeListRequest);
				resultMap.put("countBalance", countBalance);
				
				try {
					resultList = this.getContributeList(walletMap);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("获取房间排行榜的:当前排行列表失败,错误信息：" + e.getMessage() + ",直播记录:" + contributeListRequest.getLiveRecordId().toString());
					return new BaseResponse(ResponseCode.FAILURE,"获取房间排行榜的:当前排行列表失败");
				}
				
				//响应
				resultMap.put("contributeList", resultList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取房间贡献榜当前排行列表成功");
				response.setResponse(resultMap);
				
			} else if (type == 2) {
				//房间排行榜的:总排行
				Map<Object,Object> personMap = liveUserDao.queryAnchorByLiveRecordId(contributeListRequest.getLiveRecordId());
				walletMap.put("rtype", "3");//1.平台鸟币消费排行 2.每场直播消费排行 3.主播个人的消费排行
				walletMap.put("anchorId", personMap.get("anchor_id").toString());//主播id
				
				//获取当前房间主播历史鸟蛋总数
				String countBalance = this.queryRoomAnchorBirdEgg(contributeListRequest);
				resultMap.put("countBalance", countBalance);
				
				Map<Object, Object> liverMap = liveUserDao.queryLiverInfoById(Integer.parseInt(personMap.get("anchor_id").toString()));
				walletMap.put("ledgerRatio",liverMap.get("ledger_ratio")==null?"0":liverMap.get("ledger_ratio").toString());//分账比
				
				try {
					resultList = this.getContributeList(walletMap);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("获取房间排行榜的:总排行列表失败,错误信息：" + e.getMessage() + ",直播记录:" + contributeListRequest.getLiveRecordId().toString());
					return new BaseResponse(ResponseCode.FAILURE,"获取房间排行榜的:总排行列表失败");
				}
				
				//响应
				resultMap.put("contributeList", resultList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取房间贡献榜总排行列表成功");
				response.setResponse(resultMap);
				
			} else if (type == 3) {
				Integer uuid = null;
				if (uid != null && StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
					uuid = Integer.parseInt(uid);
				}
				
				if (contributeListRequest.getUid() != null && contributeListRequest.getUid() != 0) {
					uuid = contributeListRequest.getUid();
				}
				
				if (uuid == null) {
					return new BaseResponse(ResponseCode.FAILURE,"未知错误,请联系管理员");
				}
				
				/**
				SELECT  liveGift.*,((@rowNO := @rowNo + 1)+${(page-1)*limit}) as  rowNo
				FROM  (

					select 
					liver_id, IFNULL(sum(percentAmount),0) as percentAmount  
				from t_live_gived_gift where anchor_id = #{anchorId}  
				GROUP BY liver_id ORDER BY percentAmount desc
				LIMIT ${(page-1)*limit},#{limit}) liveGift , (SELECT @rowNO := 0) b 
				
				*/
				
				//查看主播个人中心里面的:贡献榜
				Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(uuid);
				walletMap.put("rtype", "3");//1.平台鸟币消费排行 2.每场直播消费排行 3.主播个人的消费排行
				walletMap.put("anchorId", liverMap.get("id").toString());//主播id
				walletMap.put("ledgerRatio",liverMap.get("ledger_ratio")==null?"0":liverMap.get("ledger_ratio").toString());//主播id
				
				try {
					resultList = this.getContributeList(walletMap);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("获取贡献榜列表失败,错误信息："+e.getMessage()+"，用户：" + uid);
					return new BaseResponse(ResponseCode.FAILURE,"获取贡献榜列表失败");
				}
				
				//响应
				resultMap.put("contributeList", resultList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取贡献榜列表成功");
				response.setResponse(resultMap);
				
			} else if (type == 4) {
				//查看主播个人主页里面的:贡献榜
				Integer anchorId = contributeListRequest.getAnchorId();
				walletMap.put("rtype", "3");//1.平台鸟币消费排行 2.每场直播消费排行 3.主播个人的消费排行
				walletMap.put("anchorId", anchorId + "");//主播id
				
				Map<Object, Object> liverMap = liveUserDao.queryLiverInfoById(anchorId);
				walletMap.put("ledgerRatio",liverMap.get("ledger_ratio")==null?"0":liverMap.get("ledger_ratio").toString());//分账比
				
				try {
					resultList = this.getContributeList(walletMap);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("获取贡献榜列表失败,错误信息：" + e.getMessage());
					return new BaseResponse(ResponseCode.FAILURE,"获取贡献榜列表失败");
				}
				
				//响应
				resultMap.put("contributeList", resultList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取贡献榜列表成功");
				response.setResponse(resultMap);
				
			} else if (type == 5) {   //贡献榜-实体礼物
				response = (MapResponse) queryContributedLiveAnchorGiftList(contributeListRequest);
			} else if (type == 6) {  //守护榜-实体礼物
				response = (MapResponse) queryProtectedLiveAnchorGiftList(contributeListRequest);
			} else {
				return new BaseResponse(ResponseCode.FAILURE, "获取土豪榜/房间贡献榜/贡献榜列表失败");
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取土豪榜/房间贡献榜/贡献榜列表失败");
		}
	}
	
	/**
	 * 
	* @Title: getContributeList
	* @Description: 调用支付系统,获取土豪榜/房间贡献榜/贡献榜列表
	* @return List<Map<String,String>>    返回类型
	* @throws
	 */
	public List<Map<String, String>> getContributeList(Map<String, String> walletMap) throws Exception {
		List<Map<String, String>> resultList = new ArrayList<>();
		//调用支付服务创建直播钱包
		LiveWalletService.Client client = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			WalletRecord walletRecord =	client.birdCoinList(walletMap);
			if (walletRecord != null && walletRecord.getWalletList() != null && walletRecord.getWalletList().size() > 0) {
				resultList = walletRecord.getWalletList();
			}
			
		}catch (Exception e){
			throw new Exception("调用支付系统,获取土豪榜/房间贡献榜/贡献榜列表失败");
			
		}finally {
			thriftUtil.coloseTransport();
		}
		
		if (resultList.size() > 0) {
			//获取土豪头像,昵称,性别,等级
			resultList = getRichInfo(resultList);
		}
		
		return resultList;
	}
	
	/**
	 * 获取当前房间主播鸟蛋历史记录总数
	 */
	public String queryRoomAnchorBirdEgg(ContributeListRequest contributeListRequest){
		String countBalance="";
		try {
			//获取直播记录
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", contributeListRequest.getLiveRecordId());
			LiveRecordInfo recordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
			if (recordInfo!=null) {
				Map<Object, Object> liverMap = liveUserDao.queryLiverInfoById(recordInfo.getAnchor_id().intValue());
				
				//获取从房间进入排行榜的主播本场鸟蛋 redis
				String liver_wallet_key = "liver_wallet_"+Long.valueOf(liverMap.get("uid").toString());
				Map<Object, Object> walletMap = null;
				if (stringRedisTemplate.hasKey(liver_wallet_key)) {
					walletMap =  stringRedisTemplate.opsForHash().entries(liver_wallet_key);
				}
				if (liverMap!=null) {
					String anchorUid = liverMap.get("uid").toString();
					//获取主播累计总鸟蛋数量 包含已经转出过的
					Map<String, Object> blanceMap = LiveGiftsInfoService.getLiveWalletBlance(anchorUid);
					if (blanceMap!=null) {
						//累计主播的 现有鸟蛋，历史转出鸟蛋  本场累计鸟蛋
						if (walletMap!=null) {
							countBalance = new BigDecimal(blanceMap.get("balance").toString())
							.add(new BigDecimal(blanceMap.get("turnEggOut").toString()))
							.add(new BigDecimal(walletMap.get("liverWalletEgg").toString())).toString();
						}else {
							countBalance = new BigDecimal(blanceMap.get("balance").toString())
							.add(new BigDecimal(blanceMap.get("turnEggOut").toString())).toString();
						}
						return countBalance;
					}
				}
			}
		} catch (Exception e) {
			log.info("获取当前房间主播鸟蛋历史记录失败");
			e.printStackTrace();
			return countBalance;
		}
		return countBalance;
	}
	
	
	
	/**
	 * 
	* @Title: getRichList
	* @Description: 获取消费排行
	* @return List<Map<String,String>>
	 */
	public List<Map<String,String>> getRichList(Map<String, String> paramMap) throws Exception {
		List<Map<String,String>> resultList=null;
//		LiveWalletService.Client client = null;
		try {
//			client =(LiveWalletService.Client)(liveWalletServiceClient.getClient());
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			LiveWalletService.Client client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			WalletRecord  walletrecord =client.birdCoinList(paramMap);
			resultList=walletrecord.getWalletList();
		}catch(Exception e){
			log.error("获取消费排行排行异常");
			e.printStackTrace();
			throw new Exception("获取消费排行排行异常");
		}finally{
			thriftUtil.coloseTransport();
		}
		return resultList;
	}
	
	/**
	 * 
	* @Title: getRichInfo
	* @Description: 获取土豪头像,昵称,性别,等级
	* @return List<Map<String,String>>    返回类型
	* @throws
	 */
	public List<Map<String,String>> getRichInfo(List<Map<String,String>> resultList) {
		//存储土豪uid
		List<Integer> uids = new ArrayList<>();
		if (resultList != null && resultList.size() > 0) {
			for (Map<String,String> richMap : resultList) {
				uids.add(Integer.parseInt(richMap.get("uid").toString()));
			}
		}
		
		List<Map<Object, Object>> liverList = new ArrayList<>();
		if (uids.size() > 0) {
			//查询土豪的头像,昵称,性别,等级
			liverList = liveUserDao.queryLiversInfo(uids);
		}
		
		//结果集
		List<Map<String, String>> list = new ArrayList<>();
		
		if (liverList.size() > 0) {
			//整合数据
			for (Map<String,String> richMap : resultList) {
				for (Map<Object, Object> liverMap : liverList) {
					if (richMap.get("uid").toString().equals(liverMap.get("uid").toString())) {
						Map<String, String> map = new HashMap<>();
						//会员id
						map.put("uid", liverMap.get("uid").toString());
						//头像
						map.put("avatar", fileUrl + liverMap.get("avatar").toString());
						
						//处理没有昵称的用户:使用默认的昵称，匿名用户+手机号码后四位
						String nname = liverMap.get("nname")==null?"":liverMap.get("nname").toString();
						if (StringUtils.isEmpty(nname)) {
							nname = "匿名用户" + liverMap.get("phone")==null?"":liverMap.get("phone").toString().substring(7, 11);
						}
						
						//昵称
						map.put("nname", nname);
						//性别  0 未知，1男，2女',
						map.put("sex", liverMap.get("sex").toString());
						//用户等级
						map.put("rankNo", liverMap.get("rankNo").toString());
						//格式化
						String rowNo = richMap.get("rowNo").toString().split("\\.")[0];
						//排行榜排名
						map.put("rowNo", rowNo);
						//送出鸟币数量
						map.put("birdCoin", richMap.get("birdCoin").toString());
						
						list.add(map);
					}
				}
			}
		}
		return list;
	}

	
	/**
	 * 
	* @Description:根据uid及当前位置查询推荐的主播
	* 	1.查询出我购买过的主播 并且正在直播的
	*	2.查询有预售且正在直播的主播
	*	3.查询有预售券的主播 
	*	4.查询守护榜
	* @return Object
	 */
	public Object queryLiveAchorRecommend(LiverRecommendRequest liverRecommendRequest){
		
		//获取uid
//		String uid = liverRecommendRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(liverRecommendRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		MapResponse response = null;
		//返回推荐主播总map
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		
		List<Map<Object,Object>> finalList = new ArrayList<Map<Object,Object>>();
		
		//1.查询出我购买过的主播 并且正在直播的
		Map<Object, Object> map =  new HashMap<Object, Object>();
		map.put(Constant.UID, uid);
		List<Map<Object, Object>> fansList = couponFansOrderDao.queryCouponFansOrderAnchorUidList(map);
		if (fansList.size()>=4) {
			finalList = fansList;
			//根据UId查询主播基本信息  并且返回结果
			return this.packageLiverInfoList(finalList);
		}
		//如果有列表
		//数量不足 查正在直播的有预售券的 商家
		if (fansList.size()<4) {
			//2.查询有预售且正在直播的主播
			Map<Object,Object> liveingMap = new HashMap<Object,Object>();
			liveingMap.put("zhiboType", 1);
			if (finalList.size()>0) {
				liveingMap.put("list", finalList);
			}
			
			List<Map<Object,Object>> liveingList = couponFansOrderDao.queryliveingFansRecordList(liveingMap);
			
			//去除已经存在list集合的主播
			List<Map<Object, Object>> newList = fansList;
			if (liveingList.size()>0 && liveingList.size()>0) {
				
				for (int i = 0; i < fansList.size(); i++) {
					for (int j = 0; j < liveingList.size(); j++) {
						//如果集合有重复主播  
						if (!liveingList.get(j).get("anchorUid").toString().equals(fansList.get(i).get("anchorUid").toString())) {
							newList.add(liveingList.get(j));
							liveingList.remove(j);
						}
					}
				}
			}
			if (fansList.size()<=0 && liveingList.size()>0){
				newList = liveingList;
			}
			
			//去除重复的元素  去除了重复后的集合
			List<Map<Object,Object>> liverList= new ArrayList<Map<Object,Object>>();
		    for(Map<Object, Object> newMap:newList){  
		        if(!liverList.contains(newMap)){  
		        	liverList.add(newMap);  
		        }  
		    }  
		    finalList = liverList;
		    //如果集合里面的主播 大于等于4个  就返回
		    if (finalList.size()>=4) {
		    	//根据UId查询主播基本信息  并且返回结果
				return this.packageLiverInfoList(finalList);
			}
		    
			//如果列表里面还是小于4个    //3.查询有预售券的主播 
		    List<Map<Object, Object>> preSellList = liverList;
		    if (preSellList.size()<4) {
				
				Map<Object,Object> isFansMap = new HashMap<Object,Object>();
				isFansMap.put("isFans", 0);
				if (preSellList.size()>0) {
					liveingMap.put("list", preSellList);
				}
				List<Map<Object,Object>> isFansList = couponFansOrderDao.queryliveingFansRecordList(isFansMap);
				//去除已经存在list集合的主播
				if (liverList.size()>0) {
				
					for (int i = 0; i < liverList.size(); i++) {
						for (int j = 0; j < isFansList.size(); j++) {
							//如果集合有重复主播  
							if (!isFansList.get(j).get("anchorUid").toString().equals(liverList.get(i).get("anchorUid").toString())) {
								preSellList.add(isFansList.get(j));
								isFansList.remove(j);
							}
						}
					}
				}
				if (liverList.size()<=0 && isFansList.size()>0){
					preSellList = isFansList;
				}
			}
				
		  //再次 去除重复的元素  去除了重复后的集合
			List<Map<Object,Object>> liverList2= new ArrayList<Map<Object,Object>>();
		    for(Map<Object, Object> newMap:preSellList){  
		        if(!liverList2.contains(newMap)){  
		        	liverList2.add(newMap);  
		        }  
		    }  
		    finalList = liverList2;
		    //如果集合里面的主播 大于等于4个  就返回
		    if (finalList.size()>=4) {
		    	//根据UId查询主播基本信息  并且返回结果
				return this.packageLiverInfoList(finalList);
			}
		    
		    //查询排除内部测试的账号
			StringBuffer  buffer = new StringBuffer();
			//排除UID 为 测试的内部账号
			List<LiverInfo> liverInfoList = liveUserDao.queryLiverInfosByIsinside();
			if (liverInfoList.size()>0) {
				for (int i = 0; i < liverInfoList.size(); i++) {
					LiverInfo info = liverInfoList.get(i);
					if (i<liverInfoList.size()-1) {
						buffer.append(info.getUid()).append(",");
					}else {
						buffer.append(info.getUid());
					}
				}
			}
			//设置查询主播帮的时候 也相应排除已经推荐过的主播
			StringBuffer  buffer2 = new StringBuffer();
		    if (liverList2.size()>0) {
				for (int i = 0; i < liverList2.size(); i++) {
					Map<Object, Object> info = liverList2.get(i);
					if (i<liverList2.size()-1) {
						buffer2.append(info.get("anchorUid").toString()).append(",");
					}else {
						buffer2.append(info.get("anchorUid").toString());
					}
				}
			}
		    if (buffer!=null && buffer.toString().split(",").length>0 && buffer2!=null && buffer2.toString().split(",").length>0) {
		    	buffer.append(",").append(buffer2.toString());
			}
		    if (buffer!=null && buffer.toString().split(",").length<=0 && buffer2!=null && buffer2.toString().split(",").length>0) {
		    	buffer = buffer2;
			}
		    
		    try {
		    	//组装参数
			    //调用支付服务创建直播钱包
		    	LiveWalletService.Client client = null;
		    	try {
//		    		LiveWalletService.Client client = null;
					Map<String, String> walletMap = new HashMap<>();
					walletMap.put("pageNo", "1");
					walletMap.put("pageSize", (4-finalList.size())+"");
					walletMap.put("uid", buffer.toString());//需要排除
					TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
					client = new Client(tMultiplexedProtocol);
					thriftUtil.openTransport();
					List<TopList>  topLists =	client.BirdeggTopList(walletMap);
					
					if (topLists.size() > 0) {
						for (int i = 0; i < topLists.size(); i++) {
							Map<Object, Object> topMap = new HashMap<>();
							topMap.put("anchorUid", topLists.get(i).getResultMap().get("uid"));
							topMap.put("sellername", "");
							topMap.put("zhiboType", "0");
							finalList.add(topMap);
						}
					}
					
				}catch(Exception e){
					log.error("获取主播守护榜异常");
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE, "推荐感兴趣主播异常");
				}finally{
					thriftUtil.coloseTransport();
				}
		    	
		    	//查询用户基本信息
				if (finalList.size()>=4) {
					//根据UId查询主播基本信息  并且返回结果
					return this.packageLiverInfoList(finalList);
				}else {
					//根据UId查询主播基本信息  并且返回结果
					return this.packageLiverInfoList(finalList);
				}
		    	
			} catch (Exception e) {
				log.info("调用支付服务异常");
				e.printStackTrace();
				return  new MapResponse(ResponseCode.FAILURE, "推荐感兴趣主播异常");
			}
				
		}
		
		return response;
		
	}
	
	/**
	 * 描述：按照推荐的主播查询主播基本信息  组合返回客户端
	 * @param list<Map<Object, Object>>
	 * @return object
	 * */
	public Object packageLiverInfoList(List<Map<Object, Object>> list){
		
		MapResponse response = null;
		Map<Object, Object> resMap = new HashMap<Object, Object>();
		
		try {
			List<Map<Object, Object>> liveMaps = liveUserDao.queryLiverInfoByUidList(list);
			List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
			if (liveMaps.size()>0) {
				for (int i = 0; i < liveMaps.size(); i++) {
					for (int j = 0; j < list.size(); j++) {
						Map<Object, Object> newMap = liveMaps.get(i);
						if (newMap.get("uid").toString().equals(list.get(j).get("anchorUid").toString())) {
							System.out.println(newMap.get("uid").toString().equals(list.get(j).get("anchorUid")));
							Map<Object, Object> newResMap = new HashMap<Object, Object>();
							
							newResMap.put("nname", newMap.get("nname"));
							newResMap.put("sex", newMap.get("sex"));
							newResMap.put("uid", newMap.get("uid"));
							newResMap.put("rankNo", newMap.get("rank_no"));
							newResMap.put("groupId", newMap.get("group_id"));
							newResMap.put("avatar", fileUrl+newMap.get("avatar"));
							
							newResMap.put("phone", list.get(j).get("phone"));
							newResMap.put("liveRecordId", list.get(j).get("liveRecordId"));
							newResMap.put("roomNo", list.get(j).get("roomNo"));
							newResMap.put("liveStartType", list.get(j).get("liveStartType"));
							newResMap.put("sellerName", list.get(j).get("sellername")==null?"":list.get(j).get("sellername"));
							newResMap.put("zhiboType", list.get(j).get("zhiboType")==null?"":list.get(j).get("zhiboType"));
							newResMap.put("anchorId", list.get(j).get("anchorId")==null?"":list.get(j).get("anchorId"));

							// 是否是签约主播 0 否 1 是
							newResMap.put("signType", list.get(j).get("signType") == null ? 0 : list.get(j).get("signType"));
							
							resultList.add(newResMap);
						}
					}
				}
			}
			resMap.put("liverList", resultList);
			response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
			response.setResponse(resMap);
			return response;
		} catch (Exception e) {
			log.info("查询主播基本信息异常");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "推荐失败");
		}
		
	}

	/**
	 * 描述：主播守护榜
//	 * @param ContributeListRequest
	 * @return Object
	 * */
	public Object queryLiveAnchorList(ContributeListRequest contributeListRequest){
		
		MapResponse response = null;
		
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		//查询排除内部测试的账号
		StringBuffer  buffer = new StringBuffer();
		//排除UID 为 测试的内部账号
		try {
			List<LiverInfo> liverInfoList = liveUserDao.queryLiverInfosByIsinside();
			if (liverInfoList.size()>0) {
				for (int i = 0; i < liverInfoList.size(); i++) {
					LiverInfo info = liverInfoList.get(i);
					if (i<liverInfoList.size()-1) {
						buffer.append(info.getUid()).append(",");
					}else {
						buffer.append(info.getUid());
					}
				}
			}
		} catch (Exception e) {
			log.info("主播守护榜查询需要排除的的主播异常");
			e.printStackTrace();
		}
		
		LiveWalletService.Client client = null;
    	try {
			Map<String, String> walletMap = new HashMap<>();
			walletMap.put("pageNo", contributeListRequest.getPage().toString());
			walletMap.put("pageSize", contributeListRequest.getPageSize().toString());
			walletMap.put("uid", buffer.toString());//需要排除
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			List<TopList>  topAnchorLists =	client.BirdeggTopList(walletMap);
			
			List<Map<Object, Object>> newAnchorList = new ArrayList<Map<Object, Object>>();
			
			if (topAnchorLists.size() > 0) {
				//1.查询主播/用户的基本信息  组装批量用户的UID 到 list  
				List<Object> list = new ArrayList<Object>();
				for (int i = 0; i < topAnchorLists.size(); i++) {
					Map<String, String> anchorMap = topAnchorLists.get(i).getResultMap();
					list.add(anchorMap.get("uid"));
					
					List<Map<String, String>> threeList = topAnchorLists.get(i).getTopThree();
					if (threeList.size()>0) {
						for (int j = 0; j < threeList.size(); j++) {
							list.add(threeList.get(j).get("uid"));
						}
					}
				}
				
				if (list.size()>0) {
					//批量查询直播用户信息 根据UID
					List<Map<Object, Object>> anchorList = liveUserDao.queryLiveInfoByUidList(list);
					//开始循环守护榜数据外层
					for (int i = 0; i < topAnchorLists.size(); i++) {
						for (int j = 0; j < anchorList.size(); j++) {
							
							Map<String, String> topAnchorMap = topAnchorLists.get(i).getResultMap();
							Map<Object, Object> anchorMap = anchorList.get(j);
							if (topAnchorMap.get("uid").toString().equals(anchorMap.get("uid").toString())) {
								Map<Object, Object> outMmap = new HashMap<Object, Object>();
								Map<Object, Object> newAnchorMap = new HashMap<Object, Object>();
								newAnchorMap.put("uid", topAnchorMap.get("uid"));
								newAnchorMap.put("birdeggCount", new BigDecimal(topAnchorMap.get("birdeggCount")==null?"0":topAnchorMap.get("birdeggCount").toString()).intValue());
								newAnchorMap.put("rowNo", topAnchorMap.get("rowNo"));
								newAnchorMap.put("avatar", fileUrl+anchorMap.get("avatar"));
								newAnchorMap.put("nname", anchorMap.get("nname"));
								newAnchorMap.put("sex", anchorMap.get("sex"));
								newAnchorMap.put("rankNo",anchorMap.get("rank_no"));
//								anchorMap.get("sign_type") == null ? 0 : anchorMap.get("sign_type")
								Integer signType = anchorSignTypeService.getSignType(anchorMap);
								// 是否是签约主播 0 否 1 是
								newAnchorMap.put("signType", signType);
								outMmap.put("resultMap", newAnchorMap);
								
								//主播守护榜前三的用户
								List<Map<String, String>> threeList = new ArrayList<Map<String, String>>();
								threeList = topAnchorLists.get(i).getTopThree();
								if (threeList.size()>0) {
									List<Map<Object, Object>> userList = new ArrayList<Map<Object, Object>>();
									for (int a = 0; a < threeList.size(); a++) {
										for (int b = 0; b < anchorList.size(); b++) {
											Map<String, String> userMap = threeList.get(a);
											Map<Object, Object> userLiveMap = anchorList.get(b);
											if (userMap.get("uid").toString().equals(userLiveMap.get("uid").toString())) {
												
												//循环遍历主播下的前三用户
												Map<Object, Object> threeUserMap = new HashMap<Object, Object>();
												threeUserMap.put("uid", userMap.get("uid"));
												threeUserMap.put("coinMoney", new BigDecimal(userMap.get("coinMoney")==null?"0":userMap.get("coinMoney").toString()).intValue() );
												threeUserMap.put("rowNo", userMap.get("rowNo"));
												threeUserMap.put("avatar", fileUrl+userLiveMap.get("avatar"));
												threeUserMap.put("nname", userLiveMap.get("nname"));
												threeUserMap.put("rankNo",userLiveMap.get("rank_no"));
												threeUserMap.put("sex",userLiveMap.get("sex"));
												userList.add(threeUserMap);
												
											}
											
										}
									}
									outMmap.put("topThree", userList);//将用户也组装到新的集合
									
									
//									List<Object> threeUidList = new ArrayList<Object>();
//									for (int k = 0; k < topAnchorLists.get(i).getTopThree().size(); k++) {
//										threeUidList.add(threeList.get(k).get("uid"));
//									}
									
//									List<Map<Object, Object>> userList = new ArrayList<Map<Object, Object>>();
//									if (threeUidList.size()>0) {
//										
//										//批量查询直播用户信息 根据UID
//										List<Map<Object, Object>> threeUserList = liveUserDao.queryLiveInfoByUidList(threeUidList);
//										if (threeUserList.size()>0) {
//											for (int a = 0; a < threeList.size(); a++) {
//												for (int b = 0; b < threeUserList.size(); b++) {
//													Map<String, String> userMap = threeList.get(a);
//													Map<Object, Object> userLiveMap = threeUserList.get(b);
//													if (userMap.get("uid").toString().equals(userLiveMap.get("uid").toString())) {
//														
//														Map<Object, Object> threeUserMap = new HashMap<Object, Object>();
//														threeUserMap.put("uid", userMap.get("uid"));
//														threeUserMap.put("coinMoney", new BigDecimal(userMap.get("coinMoney")==null?"0":userMap.get("coinMoney").toString()).intValue() );
//														threeUserMap.put("rowNo", userMap.get("rowNo"));
//														threeUserMap.put("avatar", fileUrl+userLiveMap.get("avatar"));
//														threeUserMap.put("nname", userLiveMap.get("nname"));
//														threeUserMap.put("rankNo",userLiveMap.get("rank_no"));
//														threeUserMap.put("sex",userLiveMap.get("sex"));
//														userList.add(threeUserMap);
//													}
//												}
//											}
//											
//										}
//										outMmap.put("topThree", userList);//将用户也组装到新的集合
//									}
								}
								newAnchorList.add(outMmap);
							}
						}
					}
				}
				
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				resultMap.put("topList", newAnchorList);
				response.setResponse(resultMap);
				
			}else {
//				resultMap.put("topAnchorLists", newAnchorList);
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				resultMap.put("topList", newAnchorList);
				response.setResponse(resultMap);
			}
			
		}catch(Exception e){
			log.error("获取主播守护榜异常");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取主播守护榜异常");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return response;
	}
	
	/**
	 * 描述：主播列表
//	 * @param ContributeListRequest
	 * @return Object
	 * */
	public Object queryLiveAnchor(AttentionListRequest attentionListRequest){
		
		//获取uid
		String uid = sessionTokenService.getStringForValue(attentionListRequest.getSessiontoken()) + "";
//		String uid = attentionListRequest.getUid().toString();
		
		MapResponse response = null;
		
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		//已登陆的情况下  返回list
		List<Map<Object, Object>> resultFallListMap = new ArrayList<Map<Object, Object>>();
		
		//未登陆的情况下  返回list
		List<Map<Object, Object>> resultListMap = new ArrayList<Map<Object, Object>>();
		
		//排除UID 为 测试的内部账号
		List<Object> excludeAnchorList = new ArrayList<Object>();
		try {
			List<LiverInfo> liverInfoList = liveUserDao.queryLiverInfosByIsinside();
			if (liverInfoList.size()>0) {
				for (int i = 0; i < liverInfoList.size(); i++) {
					LiverInfo info = liverInfoList.get(i);
					excludeAnchorList.add(info.getId());
				}
			}
		} catch (Exception e) {
			log.info("主播守护榜查询需要排除的的主播异常");
			e.printStackTrace();
		}
		
		//需要排除的主播的直播记录统计
		Map<Object, Object> paraMap = new HashMap<Object, Object>();
		paraMap.put("page", attentionListRequest.getPage());
		paraMap.put("limit", Constant.PAGE_LIMIT);
		paraMap.put("list", excludeAnchorList);
		List<Map<Object, Object>> liveRecordList = anchorLiveRecordDao.queryAnchorLiveCount(paraMap);
		if (liveRecordList.size()>0) {
			List<Integer> anchorIdList = new ArrayList<Integer>();
			for (int i = 0; i < liveRecordList.size(); i++) {
				if (liveRecordList.get(i).get("anchorId")!=null) {
					anchorIdList.add(Integer.parseInt(liveRecordList.get(i).get("anchorId").toString()));
				}
			}
			//获取主播信息
			List<Map<Object, Object>> anchorList = new ArrayList<Map<Object, Object>>();
			if (anchorIdList.size()>0) {
				anchorList = liveUserDao.queryLiverInfoByIdList(anchorIdList);
			}
			// 使用Map方式保存主播信息，避免多次使用for
			Map<Object, Map<Object, Object>> idAnchorMap = new HashMap<Object, Map<Object, Object>>();
			if (anchorList != null && anchorList.size() > 0) {
				for (Map<Object, Object> anchor : anchorList) {
					String anchorId = anchor.get("id").toString();
					idAnchorMap.put(anchorId, anchor);
				}
			}
			
			try {
				
				//循环遍历组装数据
				if (liveRecordList.size()>0 && anchorList.size()>0) {
					int page = (attentionListRequest.getPage() - 1) * Constant.PAGE_LIMIT;
					for (int i = 0; i < liveRecordList.size(); i++) {
						Map<Object, Object> liveRecordMap = liveRecordList.get(i);
						String anchorId = liveRecordMap.get("anchorId").toString();
						Map<Object, Object> anchorMap = idAnchorMap.get(anchorId);
						if (anchorMap == null) {
							continue;
						}
						page++;
						Map<Object, Object> newAnchorMap = new HashMap<Object, Object>();
						newAnchorMap.put("rowNo", page);
						newAnchorMap.put("uid", anchorMap.get("uid"));
						newAnchorMap.put("anchorId", anchorMap.get("id"));
						newAnchorMap.put("nname", anchorMap.get("nname"));
						newAnchorMap.put("avatar", fileUrl+anchorMap.get("avatar"));
						newAnchorMap.put("sex", anchorMap.get("sex"));
						newAnchorMap.put("liveCount", liveRecordMap.get("liveCount"));
						newAnchorMap.put("rankNo", anchorMap.get("rank_no"));
						newAnchorMap.put("isFocus", "0");
						// 是否是签约主播 0 否 1 是
						newAnchorMap.put("signType", anchorMap.get("signType") == null ? 0 : anchorMap.get("signType"));

						//查询主播有无直播的记录  及 正在预售的粉丝券
						Map<Object, Object> paramMap = new HashMap<Object, Object>();
						String pageSize = propertiesUtil.getValue("anchorLiveRecordListNum", "conf_common.properties");
						paramMap.put("page", 1);
						paramMap.put("limit", Integer.parseInt(pageSize));
						paramMap.put("anchorId", liveRecordMap.get("anchorId"));
						//查询主播通告 并且 并且有关联无粉丝券
						List<Map<Object, Object>> recordList = anchorLiveRecordDao.queryLiveRecordListByAnchorId(paramMap);
						//如果列表小于6个记录  去查找回放的
						if (recordList.size()<Integer.parseInt(pageSize)) {
							//还需要多少条数据才能填满
							paramMap.put("limit", Integer.parseInt(pageSize)-recordList.size());

							//查询该主播的回放记录
							List<Map<Object, Object>> recordPlayList = anchorLiveRecordDao.queryLiveRecordPlayList(paramMap);
							if (recordPlayList.size()>0) {
								for (int k = 0; k < recordPlayList.size(); k++) {
									Map<Object, Object> playMap = recordPlayList.get(k);
									playMap.put("uid", anchorMap.get("uid"));
									playMap.put("anchorId", anchorMap.get("id"));
									playMap.put("groupId", anchorMap.get("group_id"));
									playMap.put("type", playMap.get("liveStartType"));//自定义OR通告开播
									playMap.put("phone", anchorMap.get("phone"));
									playMap.put("zhiboCover", fileUrl+(playMap.get("zhiboThumbnail")==null?playMap.get("zhiboCover"):playMap.get("zhiboThumbnail")));
									playMap.put("zhiboPlaybackUrl", fileUrl+playMap.get("zhibo_playback_url"));
									playMap.put("type", playMap.get("liveStartType"));
									playMap.put("planStartDate", playMap.get("planStartDate")==null?"":DateUtil.format(DateUtil.parse(playMap.get("planStartDate").toString()), DateUtil.minuteSimpleFormater));
									recordList.add(playMap);
								}
								log.info(newAnchorMap.size()+"前面是map============================后面是recordList:"+recordList.size());
								if (recordList.size()>0) {
									newAnchorMap.put("liveRecordList", recordList);
								}

							}
						}
						//循环最终直播推荐list 计算正在直播的经纬度
						List<Map<Object, Object>> newList = new ArrayList<Map<Object, Object>>();
						for (int a = 0; a < recordList.size(); a++) {
							Map<Object, Object> recordMap = recordList.get(a);
							recordMap.put("groupId", anchorMap.get("group_id"));
							recordMap.put("type", anchorMap.get("0"));
							recordMap.put("phone", anchorMap.get("phone"));
							recordMap.put("type", recordMap.get("liveStartType"));
							recordMap.put("zhiboCover", fileUrl+(recordMap.get("zhiboThumbnail")==null?recordMap.get("zhiboCover"):recordMap.get("zhiboThumbnail")));
							recordMap.put("zhiboPlaybackUrl", fileUrl+recordMap.get("zhibo_playback_url"));

							//如果直播的状态为正在直播  计算经纬度      计算直播开播时长
							if (Integer.parseInt(recordMap.get("zhiboType").toString())  == 1) {
								log.info("计算正在直播的记录与用户距离，开播直播时长");
								//计算记录
								if (recordMap.get("longitude")!=null  && recordMap.get("latitude")!=null
										&& attentionListRequest.getLatitude()!=null && attentionListRequest.getLongitude()!=null) {
									Double sellerLongitude  = Double.valueOf(recordMap.get("longitude").toString());
									Double sellerLatitude = Double.valueOf(recordMap.get("latitude").toString());
									String distanceStr = "";
									double distance = DistanceUtil.Distance(attentionListRequest.getLongitude(), attentionListRequest.getLatitude(), sellerLongitude, sellerLatitude);
									if (distance>1000) {
										distanceStr = Math.round(ArithUtil.div(distance, 1000))+"km";
									}else {
										distanceStr = Math.round(distance)+"m";//距离主播距离
									}
									recordMap.put("distanceStr", distanceStr);
								}
								//计算开播时长
								if (recordMap.get("start_date") != null) {
									long liveingMinute = (new Date().getTime()-DateUtil.parse(recordMap.get("start_date").toString()).getTime())/60000;
									recordMap.put("liveingMinute", "已开播"+liveingMinute+"分钟");
								}else {
									recordMap.put("liveingMinute", "");
								}

							}else {
								recordMap.put("liveingMinute", "");
								recordMap.put("distanceStr", "");
							}
							if (recordMap.get("zhiboType").toString().equals("0")) {
								recordMap.put("planStartDesc", recordMap.get("planStartDate")==null?"":"将于"+DateUtil.format(DateUtil.parse(recordMap.get("planStartDate").toString()), DateUtil.minuteSimpleFormater)+"开播");
								recordMap.put("planStartDate", recordMap.get("planStartDate")==null?"":DateUtil.format(DateUtil.parse(recordMap.get("planStartDate").toString()), DateUtil.minuteSimpleFormater));
								recordMap.put("liveingMinute", "");
								recordMap.put("distanceStr", "");
							}
							newList.add(recordMap);
						}
						newAnchorMap.put("liveRecordList", newList);
						resultListMap.add(newAnchorMap);

					}
				}
				
				//查询出我所关注的主播 
				if (!StringUtils.isEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
					if (anchorIdList.size()>0) {
						LiverInfo liverInfo = liveUserDao.queryLiverByUid(Long.valueOf(uid) );
						Map<Object, Object> focusMap = new HashMap<Object, Object>();
						focusMap.put("liverStrId", liverInfo.getId());
						focusMap.put("list", anchorIdList);
						
						List<Map<Object, Object>> focusListMap = liveUserDao.queryLiveAnchorFocusList(focusMap);
						if (focusListMap.size()>0) {
							
							for (int i = 0; i < resultListMap.size(); i++) {
								for (int j = 0; j < focusListMap.size(); j++) {
//									Map<Object, Object> fallUserMap = new HashMap<Object, Object>();
									Map<Object, Object> userAnchorMap = resultListMap.get(i);
									Map<Object, Object> userFocusMap = focusListMap.get(j);
									if (userAnchorMap.get("anchorId").toString().equals(userFocusMap.get("liverEndId").toString())) {
										resultListMap.get(i).put("isFocus", 1);
									}
								}
							}
							resultMap.put("anchorList", resultListMap);
						}else {
							resultMap.put("anchorList", resultListMap);
						}
					}
					
				}else {
					resultMap.put("anchorList", resultListMap);
				}
				
				response = new MapResponse(ResponseCode.SUCCESS, "操作成");
				response.setResponse(resultMap);
				
			} catch (Exception e) {
				log.info("获取主播列表异常");
				e.printStackTrace();
				return new MapResponse(ResponseCode.FAILURE, "获取列表失败");
			}
			
		}else {
			resultMap.put("anchorList", liveRecordList);
			response = new MapResponse(ResponseCode.SUCCESS, "操作成");
			response.setResponse(resultMap);
		}
		return response;
	}

	/**
	 * 贡献榜-实体礼物
	 * @param contributeListRequest
	 * @return
	 */
	public Object queryContributedLiveAnchorGiftList(ContributeListRequest contributeListRequest) {
		try {
			Map<Object, Object> liverInfo = getAndCheckAnchorInfo(contributeListRequest);
			if (liverInfo == null) {
				String msg = "获取贡献榜-实体礼物异常, 获取不到主播信息：uid="+String.valueOf(contributeListRequest.getUid()) +
						";liveRecordId="+String.valueOf(contributeListRequest.getLiveRecordId());
				return new MapResponse(ResponseCode.FAILURE, msg);
			}

			Map<Object, Object> params = new HashMap<Object, Object>();

			params.put("anchorId", liverInfo.get("id"));
			params.put("page", contributeListRequest.getPage());
			params.put("pageSize", contributeListRequest.getPageSize());

			// 1.主播收到实体礼物列表
			List<Map<Object, Object>> anchorGiftsList = liveGiftsInfoDao.queryLiveAnchorsGiftListByAnchorId(params);
			if (anchorGiftsList == null && anchorGiftsList.size() == 0) {
				Map<Object, Object> resultMap = new HashMap<Object, Object>();
				resultMap.put("contributeList",new ArrayList<>());
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				response.setResponse(resultMap);
				return response;
			}
			Set<Object> ids = new HashSet<>(); // 所有用户id
			for (int i = 0; i < anchorGiftsList.size(); i++) {
				ids.add(anchorGiftsList.get(i).get("liverId"));
			}
			// 3. 批量获取用户信息
			List<Object> list = Arrays.asList(ids.toArray());
			Map<Object, Map<Object, Object>> idUserInfoMap = new HashMap();  //用户map
			List<Map<Object,Object>> userInfoList = liveUserDao.findAllByIdList(list);
			if (userInfoList != null && userInfoList.size() > 0) {
				for (Map<Object, Object> userInfo : userInfoList) {
					Object uid = userInfo.get("id");  //用户
					idUserInfoMap.put(uid, userInfo);
				}
			}
			List<Map<Object, Object>> contributeList = new ArrayList<Map<Object, Object>>();
			int page = (contributeListRequest.getPage() -1) * contributeListRequest.getPageSize();
			// 4 组装用户信息数据
			for (int i = 0; i < anchorGiftsList.size(); i++) {
				Object liverId = anchorGiftsList.get(i).get("liverId");  //用户ID
				Object giftNums = anchorGiftsList.get(i).get("giftNums");  //实体礼物数量
				Map<Object, Object> userInfo = idUserInfoMap.get(liverId);
				if (userInfo == null) {
					log.warn("获取贡献榜-实体礼物异常, 查找不到用户信息：liverId=" + String.valueOf(liverId));
					continue;
				}
				page++;
				Map<Object, Object> resultMap = new HashMap<>();
				defaultProtectedResponse(resultMap);
				safeToBaseUserInfo(resultMap, userInfo);
				resultMap.put("giftNums", giftNums == null ? 0 : giftNums);
				resultMap.put("rowNo", page);
				resultMap.remove("signType");

				contributeList.add(resultMap);
			}
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			resultMap.put("contributeList",contributeList);
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			log.error("获取贡献榜-实体礼物异常");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取贡献榜-实体礼物异常");
		}
	}

	private Map<Object, Object> getAndCheckAnchorInfo(ContributeListRequest contributeListRequest) {
		Integer uid = contributeListRequest.getUid();
		Long tmp_anchorId = null;

		if (uid == null) {
			try {
				//获取直播基本信息   主播基本信息
				LiveRecordInfo recordInfo = anchorLiveRecordDao.queryLiveRecordById(contributeListRequest.getLiveRecordId());
				tmp_anchorId = recordInfo.getAnchor_id();
			} catch (Exception e) {
			}

		}
		if (uid == null && tmp_anchorId == null) {
			try {
				String sUid = sessionTokenService.getStringForValue(contributeListRequest.getSessiontoken()) + "";
				uid = Integer.parseInt(sUid);
			} catch (Exception e) {
			}
		}


		if (uid == null && tmp_anchorId == null) {
			log.warn("获取贡献榜-实体礼物异常, 主播ID不存在");
			return null;
		}
		Map<Object, Object> liverMap = null;
		// 客户端以前类似模块使用 recordId获取主播Id，再通过主播ID获取主播信息
		// 当前版本使用主播uid
		if (uid != null) {
			// 查找主播信息
			liverMap = liveUserDao.queryLiverInfoByUid(uid);
		} else {
			liverMap = liveUserDao.queryLiverInfoById(Integer.valueOf(tmp_anchorId+""));
		}

		if (liverMap == null) {
			log.warn("获取贡献榜-实体礼物异常,查找不到当前主播信息，uid="+uid);
			return null;
		}
		return liverMap;
	}

	/**
	 * 守护榜-实体礼物
	 * @param contributeListRequest
	 * @return
	 */
	public Object queryProtectedLiveAnchorGiftList(ContributeListRequest contributeListRequest) {
		try {
			//查询排除内部测试的账号
			List<Long> testIds = new ArrayList<>();
			try {
				List<LiverInfo> liverInfoList = liveUserDao.queryLiverInfosByIsinside();
				if (liverInfoList.size()>0) {
					for (int i = 0; i < liverInfoList.size(); i++) {
						LiverInfo info = liverInfoList.get(i);
						testIds.add(info.getId());
					}
				}
			} catch (Exception e) {
				log.info("守护榜-实体礼物查询需要排除的的主播异常");
				e.printStackTrace();
			}
			Map<Object, Object> params = new HashMap<Object, Object>();

			params.put("anchorList", testIds);  //内部账号Id
			params.put("page", contributeListRequest.getPage());
			params.put("pageSize", contributeListRequest.getPageSize());

			// 1.主播收到实体礼物列表
			List<Map<Object, Object>> anchorGiftsList = liveGiftsInfoDao.queryLiveAnchorsGiftList(params);
			Set<Object> ids = new HashSet<>(); // 所有用户id（主播和用户）
			if (anchorGiftsList == null && anchorGiftsList.size() == 0) {
				Map<Object, Object> resultMap = new HashMap<Object, Object>();
				resultMap.put("topList",new ArrayList<>());
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				response.setResponse(resultMap);
				return response;
			}
			Map<Object, List<Map<Object, Object>>> top3UsersMap = new HashMap<>();  //主播下top3用户Map
			for (int i = 0; i < anchorGiftsList.size(); i++) {
				Object anchorId = anchorGiftsList.get(i).get("anchorId");  //主播ID
				ids.add(anchorId);
				// 2.获取Top3赠送实体礼物用户信息
				params.clear();
				params.put("anchorId", anchorId);
				params.put("page", 1);
				params.put("pageSize", 3);
				List<Map<Object, Object>> top3Users = liveGiftsInfoDao.queryLiverGiveGiftList(params);
				if (top3Users != null && top3Users.size() > 0) {
					for (int j = 0; j < top3Users.size(); j++) {  //主播下top3送实体礼物的用户ID
						ids.add(top3Users.get(j).get("liverId"));
					}
					top3UsersMap.put(anchorId, top3Users);
				}
			}

			// 3. 批量获取用户信息
			List<Object> list = new ArrayList<>();  // 所有用户id（主播和用户）
			for (Object id : ids) {
				list.add(id);
			}
			Map<Object, Map<Object, Object>> idUserInfoMap = new HashMap();  //用户map

			List<Map<Object,Object>> userInfoList = liveUserDao.findAllByIdList(list);
			if (userInfoList != null && userInfoList.size() > 0) {
				for (Map<Object, Object> userInfo : userInfoList) {
					Object uid = userInfo.get("id");  //用户/主播id
					idUserInfoMap.put(uid, userInfo);
				}
			}

			List<Map<Object, Object>> topListResponse = new ArrayList<>();
			int page = (contributeListRequest.getPage() -1 ) * contributeListRequest.getPageSize();
			// 4 组装用户信息数据
			for (int i = 0; i < anchorGiftsList.size(); i++) {
				Map<Object, Object> resultMap = new HashMap<>();
				List<Map<Object, Object>> topThree = new ArrayList<>();

				Object anchorId = anchorGiftsList.get(i).get("anchorId");  //主播ID
				List<Map<Object, Object>> topThreeUserInfo = top3UsersMap.get(anchorId);  //Top3赠送用户信息
				Map<Object, Object> userInfo = null;

				// 主播top3用户信息
				if (topThreeUserInfo != null) {
					for (int j = 0; j < topThreeUserInfo.size(); j++) {
						Map<Object, Object> liverInfo = new HashMap<>();
						defaultProtectedResponse(liverInfo);
						liverInfo.remove("signType");  //用户信息不用返回signType

						userInfo = idUserInfoMap.get(topThreeUserInfo.get(j).get("liverId"));  //用户信息
						if (userInfo != null) {
							safeToBaseUserInfo(liverInfo, userInfo);
							safeToGiftInfo(liverInfo, topThreeUserInfo.get(j));
						} else {
							liverInfo.put("rankNo", j + 1);  //客户端要求必须有值
						}
						topThree.add(liverInfo);
					}
				}
				page++;
				defaultProtectedResponse(resultMap);
				userInfo = idUserInfoMap.get(anchorId);  //用户信息

				if (userInfo != null) {
					safeToBaseUserInfo(resultMap, userInfo);
					safeToGiftInfo(resultMap, anchorGiftsList.get(i));
				} else {
					log.warn("查找不到主播信息：anchorId:" + String.valueOf(anchorId));
				}
				resultMap.put("rowNo", page);  //客户端要求必须有值

				Map<Object, Object> responseMap = new HashMap<>();
				responseMap.put("resultMap", resultMap);
				responseMap.put("topThree", topThree);
				topListResponse.add(responseMap);
			}

			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			resultMap.put("topList",topListResponse);
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			log.error("获取守护榜-实体礼物异常");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取守护榜-实体礼物异常");
		}
	}

	void safeToBaseUserInfo(Map<Object, Object> resultMap, Map<Object, Object> userInfoMap) {
		if (userInfoMap != null) {
			resultMap.put("uid", userInfoMap.get("uid") == null ? "" : userInfoMap.get("uid"));  //用户uid
			resultMap.put("avatar", userInfoMap.get("avatar") == null ? "" : fileUrl + userInfoMap.get("avatar"));
			String name = StrUtils.standardName(userInfoMap.get("nname"), userInfoMap.get("phone"));
			resultMap.put("nname", name == null ? "" : name);
			resultMap.put("rankNo", userInfoMap.get("rankNo"));
			resultMap.put("sex", userInfoMap.get("sex") == null ? 0 : userInfoMap.get("sex"));
			resultMap.put("signType", userInfoMap.get("signType") == null ? 0 : userInfoMap.get("signType"));// 是否签约主播， 1是，0否
		}
	}

	void safeToGiftInfo(Map<Object, Object> resultMap, Map<Object, Object> userInfoMap) {
		if (userInfoMap != null) {
			resultMap.put("giftNums",userInfoMap.get("giftNums") == null ? 0 : userInfoMap.get("giftNums"));  //实体礼物数量
//			resultMap.put("rowNo",userInfoMap.get("rowNo") == null ? 0 : userInfoMap.get("rowNo"));  //排名
		}
	}

	void defaultProtectedResponse(Map<Object, Object> resultMap) {
		resultMap.put("uid", "");
		resultMap.put("avatar", "");
		resultMap.put("nname", "");
		resultMap.put("rankNo", 0);
		resultMap.put("sex", 0);
		resultMap.put("signType", 0);// 是否签约主播， 1是，0否
		resultMap.put("giftNums",0);  //实体礼物数量
		resultMap.put("rowNo", 0);  //排名
	}
}
