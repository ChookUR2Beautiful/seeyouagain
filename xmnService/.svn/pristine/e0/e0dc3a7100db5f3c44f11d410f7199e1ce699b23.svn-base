/**
 * 2016年8月10日 下午3:43:45
 */
package com.xmniao.xmn.core.live.service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.AnchorInfoBasicRequest;
import com.xmniao.xmn.core.common.request.live.RelieveblackRequst;
import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.PersonalCenterDao;
import com.xmniao.xmn.core.live.dao.PersonalDetailDao;
import com.xmniao.xmn.core.live.dao.PrivateMessageDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.market.dao.FreshIndianaRobotDao;
import com.xmniao.xmn.core.thrift.*;
import com.xmniao.xmn.core.thrift.LiveWalletService.Client;
import com.xmniao.xmn.core.util.*;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.vstar.dao.VStarPlayerInfoDao;
import com.xmniao.xmn.core.xmer.dao.CouponDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PersonalCenterService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月10日 下午3:43:45
 * @version
 */
@Service
public class PersonalCenterService {
	//日志
	private final Logger log = Logger.getLogger(PersonalCenterService.class);
	
	//注入dao
	@Autowired
	private PersonalCenterDao personalcenterDao;
	
	@Autowired
	private PersonalDetailDao personalDetailDao;
	
	//注入服务器地址  图片服务器配置地址
	@Autowired
	private String fileUrl;
	
	@Autowired
	private ThriftUtil thriftUtil;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private LiveUserService liveUserService;
	@Autowired
	private AnchorLiveRecordService anchorLiveRecordService;
	@Autowired
	private FreshIndianaRobotDao freshIndianaRobotDao;
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private AnchorPersonService anchorPersonService;
	@Autowired
	private PrivateMessageDao privatemessageDao;
	
	/**
	 * 注入stringRedisTemplate
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 注入ursDao
	 */
	@Autowired
	private UrsDao ursDao;
	
	/**
	 * 注入xmerDao
	 */
	@Autowired
	private XmerDao xmerDao;
	
	//注入优惠卷dao
	@Autowired
	private CouponDao couponDao;

	@Autowired
	private AnchorSignTypeService anchorSignTypeService;
	
	//报名主播DAO
	@Autowired
	private VStarPlayerInfoDao vStarPlayerInfoDao;
	@Autowired
	private KSCloudService ksCloudService;
	
	/**
	 * 
	* @Title: queryLivePersonByUid
	* @Description: 根据uid查询直播观众的信息
	* @return Map<Object,Object>
	 * @throws Exception 
	 */
	
	public Map<Object, Object>  queryLiverPersonByUid(int uid) throws Exception{
		Map<Object, Object> personMap=null;
		try {
			personMap=personalcenterDao.queryLiverPersonByUid(uid);
		} catch (Exception e) {
			log.error("根据uid查询直播观众的信息失败");
			e.printStackTrace();
			throw new Exception("获取直播观众的信息失败");
			
		}
		return personMap;
	}
	
	/**
	 * 
	* @Title: getWalletInfo
	* @Description: 调用支付服务端接口，查询直播钱包信息
	* @return Map<String,String>
	 */
	public Map<String, String> getLiveWalletInfo(String uid) throws Exception{
		LiveWalletService.Client client = null;
		Map<String, String> walletMap=null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			//client =(LiveWalletService.Client)(liveWalletServiceClient.getClient());
			Map<String,String> param=new HashMap<String,String>();
			param.put("uid", uid);
			param.put("checked", "1");
			ResponseData responseData =	client.getLiveWallet(param);
			if(responseData.getState()!=0){
				log.error("获取直播钱包失败,错误信息："+responseData.getMsg()+"，用户："+uid);
				throw new Exception("获取直播钱包失败,错误信息："+responseData.getMsg()+"，用户："+uid);
			}
			walletMap=responseData.resultMap;
			log.info("获取直播钱包成功,用户："+uid);
		}catch(Exception e){
			log.error("获取直播钱包失败");
			e.printStackTrace();
			throw new Exception("获取直播钱包信息失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return walletMap;
	}
	
	/**
	 * 
	* @Title: getWalletMoney
	* @Description: 查询用户钱包余额
	* @return Map<String,String>
	 */
	public Map<String, String> getWalletMoney(String uid,int typeId) throws Exception{
		SynthesizeService.Client client = null;
		Map<String, String> walletBalanceMap=null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("SynthesizeService");
			 client =new SynthesizeService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
//			client =(SynthesizeService.Client)(synthesizeServiceClient.getClient());
			walletBalanceMap =	client.getWalletBalance(uid, typeId);
			log.info("钱包余额查询成功,用户："+uid);
		}catch(Exception e){
			log.error("钱包余额查询失败");
			e.printStackTrace();
			throw new Exception("获取钱包余额失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return walletBalanceMap;
	}
	
	/**
	 * 
	* @Title: updateWalletAmount
	* @Description: 更新直播钱包钱包余额
	* @return ResponseData
	 */
	public ResponseData updateWalletAmount(Map<String,String> walletMap) throws Exception{
		LiveWalletService.Client client = null;
		ResponseData responseData =null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			 client = new Client(tMultiplexedProtocol);		
			thriftUtil.openTransport();
//			client =(LiveWalletService.Client)(liveWalletServiceClient.getClient());
			responseData =	client.updateWalletAmount(walletMap);
		} catch (Exception e) {
			log.error("更新直播钱包余额异常");
			e.printStackTrace();
			throw new Exception("更新直播钱包余额异常，请联系管理员");
		}finally{
			thriftUtil.coloseTransport();
		}
		return responseData;
	}
	
	
	/**
	 * 
	* @Title: updateBalance
	* @Description: 修改寻蜜客钱包余额
	* @return Map<String,String>
	 */
	public ResponseData turnoutLiveWallet(Map<String,String> walletMap) throws Exception{
		LiveWalletService.Client client = null;
		ResponseData responseData =null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
		    client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
//			client =(LiveWalletService.Client)(liveWalletServiceClient.getClient());
			responseData =	client.turnoutLiveWallet(walletMap);
		} catch (Exception e) {
			log.error("更新钱包余额失败");
			e.printStackTrace();
			throw new Exception("更新钱包余额异常，请联系管理员");
		}finally{
			thriftUtil.coloseTransport();
		}
		return responseData;
	}
	
	
	/**
	 * 
	* 描述: 根据id查询直播观众及的信息
	* @return Map<Object,Object>
	 * @throws Exception 
	 */
	
	public Map<Object, Object>  queryLiverPersonById(int id) throws Exception{
		Map<Object, Object> personMap=null;
		try {
			personMap=personalcenterDao.queryLiverPersonById(id);
		} catch (Exception e) {
			log.error("根据id查询直播观众的信息失败");
			e.printStackTrace();
			throw new Exception("获取直播观众的信息");
			
		}
		return personMap;
	}
	
	
	
	/**
	 * 个人中心用户及主播基本信息
	* @Title: queryLivePersonByUid
	* @Description: 
	* @return Object
	 */
	public Object queryLivePersonByUid(String uuid,BaseRequest baseRequest){
		int uid=Integer.parseInt(uuid);
		Map<Object,Object> resultMap=new HashMap<>();
		Map<String, String> WalletInfo=null;
		Map<Object, Object> personMap=null;
		int vstarState = 0;  //星时尚报名状态
		try{
			personMap=this.queryLiverPersonByUid(uid);
			if(personMap==null || personMap.size()<=0){
				//创建腾讯云账号信息
				BaseResponse response=(BaseResponse) liveUserService.createTlsUser(uuid);
				if(response.getState()!=100){
					return new BaseResponse(ResponseCode.FAILURE, response.getInfo());
				}
				
				//查询直播用户信息
				personMap=this.queryLiverPersonByUid(uid);
				if(personMap==null || personMap.size()<=0){
					log.info("未获取到个人中心信息");
					return new BaseResponse(ResponseCode.FAILURE, "个人中心信息,未知错误，请联系管理员");
				}
			}
			
			int systemversion = baseRequest.getSystemversion().indexOf("android");
			String appversion = baseRequest.getAppversion();
			appversion = appversion.replace(".", "");
			int appv = Integer.parseInt(appversion);
			
			log.info("获取个人信息成功:----OK,uid:"+uid);
			
			//如果是主播身份   并且  没有groupId  则强制退出 重新登录  IOS直播间退不出去在这里加强一步
			if (personMap.get("utype")!=null && personMap.get("utype").toString().equals("1")) {
				if (personMap.get("group_id")==null || org.apache.commons.lang.StringUtils.isEmpty(personMap.get("group_id").toString()) || personMap.get("group_id").toString().equals("null")) {
					stringRedisTemplate.delete("USERID_"+uid);
					stringRedisTemplate.delete(baseRequest.getSessiontoken());
					return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
				}
			}
			
			//如果 当前用户状态为  0  并且又是 主播  则设置为 用户身份 
			Integer utype= 2;
			if (personMap.get("status").toString().equals("0")) {//如果停用
				if (personMap.get("utype").toString().equals("1")) {//判断是否为主播
					resultMap.put("utype", utype);
				}else {
					resultMap.put("utype", Integer.parseInt(personMap.get("utype").toString()));
				}
			}else {
				utype = personMap.get("utype")==null?2:Integer.parseInt(personMap.get("utype").toString());//用户类型
				resultMap.put("utype", utype);
			}
			
			resultMap=this.getResultMap(resultMap,personMap);
			String anchorid= personMap.get("anchorid")==null?null:personMap.get("anchorid").toString();//ID
			
			//调用接口查询直播用户财产情况
			try {
				WalletInfo=this.getLiveWalletInfo(uid+"");   
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			
			if(WalletInfo==null || WalletInfo.size()<=0){
				resultMap.put("birdEgg", 0);//鸟蛋余额
				resultMap.put("commision", 0);//鸟豆余额
				resultMap.put("zbalance", 0.00);//鸟币余额
			}else{
				resultMap.put("birdEgg", Double.valueOf(WalletInfo.get("balance").toString()).intValue());//鸟蛋余额
				
				BigDecimal sellerCoin = WalletInfo.get("sellerCoin")==null?new BigDecimal(0):new BigDecimal(WalletInfo.get("sellerCoin").toString());
				BigDecimal zbalance = WalletInfo.get("zbalance")==null?new BigDecimal(0):new BigDecimal(WalletInfo.get("zbalance").toString());
				resultMap.put("zbalanceCoin", zbalance.setScale(2, BigDecimal.ROUND_HALF_UP)) ;//鸟币余额(壕赚显示 商家鸟币+公共鸟币)
					
				resultMap.put("birdEgg", Double.valueOf(WalletInfo.get("balance").toString()).intValue());//鸟蛋余额
				if (appversion!=null && appv<335 ) { //判断版本号是否小于3.3.5
					resultMap.put("commision", Double.valueOf(WalletInfo.get("commision").toString()).intValue());//鸟豆余额
					resultMap.put("zbalance", Double.valueOf(zbalance.add(sellerCoin).toString()).intValue()) ;//鸟币余额(壕赚显示 商家鸟币+公共鸟币)
					
				}else {
					resultMap.put("commision", new BigDecimal(WalletInfo.get("commision")).setScale(2, BigDecimal.ROUND_HALF_UP));//鸟豆余额
					resultMap.put("zbalance", zbalance.add(sellerCoin).setScale(2, BigDecimal.ROUND_HALF_UP).toString()) ;//鸟币余额(壕赚显示 商家鸟币+公共鸟币)
				}

				resultMap.put("commision", Double.valueOf(WalletInfo.get("commision").toString()).intValue());//鸟豆余额
				resultMap.put("zbalance", zbalance.add(sellerCoin).setScale(2, BigDecimal.ROUND_HALF_UP).toString()) ;//鸟币余额(壕赚显示 商家鸟币+公共鸟币)
			}
			
			//获取寻蜜客钱包余额
			Map<String, String> walletBalanceMap=this.getWalletMoney(uid+"", 1);
			log.info("获取寻蜜客钱包余额"+uid);
			if(walletBalanceMap==null || walletBalanceMap.size()<=0){
				resultMap.put("integralSum", "0.00");//积分余额
				resultMap.put("balance", "0.00");//钱包余额
			}else{
				String integralSum=walletBalanceMap.get("integral")==null?"0":walletBalanceMap.get("integral").toString();
				//整数小于6位，则保留2位小数,否则取整
				if (Double.parseDouble(integralSum) < 1000000) {
					resultMap.put("integralSum", new BigDecimal(integralSum).setScale(2, BigDecimal.ROUND_HALF_UP)+"");//积分余额
				}else {
					resultMap.put("integralSum", (int)Double.parseDouble(integralSum));//积分余额,向下取整
					
				}
				
				BigDecimal commision=new BigDecimal(walletBalanceMap.get("commision").toString());//佣金余额
				BigDecimal zbalance=new BigDecimal(walletBalanceMap.get("zbalance").toString());//赠送余额
				BigDecimal sums=commision.add(zbalance);//分账余额
				//整数小于6位，则保留2位小数,否则取整
				if (sums.compareTo(new BigDecimal(1000000)) == -1) {
					resultMap.put("balance",sums.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}else {
					resultMap.put("balance",sums.intValue() + "");
					
				}
			}
			resultMap.put("isBalance", propertiesUtil.getValue("is_iospay", "conf_live.properties"));//余额是否为0,0是,1否
			//查询直播总记录数跟通告条数,如果用户类型为1(即主播用户)，直播记录在库中查询，若是用户类型不为1，默认直播记录为0
			if(utype==1){
				Map<Object, Object> liveRecordNumMap = null;
				try{
					liveRecordNumMap = personalcenterDao.queryLiveRecordNum(Integer.parseInt(anchorid));
				}catch(Exception e){
					e.printStackTrace();
					log.error("根据用户id查看直播记录数跟通告条数失败");
					return new BaseResponse(ResponseCode.FAILURE, "获取主播直播记录数异常");
				}
				resultMap.put("liveRecordSum", liveRecordNumMap.get("liveRecordSum")==null?0:liveRecordNumMap.get("liveRecordSum"));//直播记录数
				resultMap.put("annunciateSum", liveRecordNumMap.get("annunciateSum")==null?0:liveRecordNumMap.get("annunciateSum"));//通告条数
			}else{
				resultMap.put("liveRecordSum", 0);//直播记录数
				resultMap.put("annunciateSum", 0);//通告数
			}
			
			//预售订单描述
			resultMap.put("fansCouponOrderDescribe", "");
			
			//查询用户购买的未使用的粉丝卷
			List<Map<Object, Object>> unusedFansCouponList = personalcenterDao.queryUnusedFansCouponByUid(uid);
			
			//判断未使用的预售券使用时间是否是当天
			if (unusedFansCouponList != null && unusedFansCouponList.size() > 0) {
				for (Map<Object, Object> unusedFansCouponMap : unusedFansCouponList) {
					if (unusedFansCouponMap != null && unusedFansCouponMap.size() > 0 && unusedFansCouponMap.get("startDate") != null) {
						long dayFirstTime = DateUtil.getDayFirstTime(DateUtil.parse(unusedFansCouponMap.get("startDate").toString())).getTime();
						long nowDayFirstTime = DateUtil.getDayFirstTime(new Date()).getTime();
						if (nowDayFirstTime == dayFirstTime) {
							resultMap.put("fansCouponOrderDescribe", propertiesUtil.getValue("fansCouponOrderDescribe", "conf_common.properties"));
							break;
						}
					}
				}
			}
			
			resultMap.put("isXmer", 0);
			// 查询是否是寻蜜客
			Map<Object, Object> xmerMap = xmerDao.queryXmerInfo(uid);
			if (xmerMap != null && xmerMap.size() > 0) {
				resultMap.put("isXmer", 1);
			}
			
			//查询用户优惠券总数
			Integer couponNum=this.queryCouponNum(uid);
			log.info("查询用户优惠券总数"+couponNum+",uid="+couponNum);
			resultMap.put("couponSum", couponNum);//优惠券数
			
			//套餐卷的数量
			Integer sellerPackageCounts = getSellerPackageCounts(String.valueOf(uid));
			resultMap.put("sellerPackageCounts", sellerPackageCounts);//充值卡数量
			resultMap.put("receiptImgUrl", propertiesUtil.getValue("IOSAppStore_receiptUrl", "conf_live.properties"));//优惠券数
			
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("vperson", 4);//只查询V客身份 标示
			map.put("uid", uid);//只查询V客身份 标示
			List<Map<Object, Object>>  vList = liveUserDao.queryBursEarningsRank(map);
			
			resultMap.put("personalLiveWalletRemark", propertiesUtil.getValue("personalLiveWalletRemark", "conf_live.properties"));// "我的钱包"字段
			
			resultMap.put("personalBirdCoinRecordUrl", propertiesUtil.getValue("personalBirdCoinRecordUrl", "conf_live.properties"));//点击鸟币跳转
			if (appv<=360 && baseRequest.getAppSource().equals("xmn")) {//兼容旧版本路径
				resultMap.put("personalLiveWalletRecordUrl", propertiesUtil.getValue("personalLiveWalletRecordUrl1", "conf_live.properties"));//"点击我的钱包"
				if (vList.size()>0 ) {
					if (vList.get(0).get("rankSource").toString().equals("4")) {// 判断是否是V客
						resultMap.put("personalLiveWalletRecordUrl", propertiesUtil.getValue("personalVUrl1", "conf_live.properties"));//"直播钱包"URL
						resultMap.put("personalLiveWalletRemark", propertiesUtil.getValue("personalLiveWalletVkeRemark", "conf_live.properties"));// "我的V客"字段
					}
				}
				resultMap.put("personalWalletRecordUrl", propertiesUtil.getValue("personalWalletRecordUrl1", "conf_live.properties"));//"点击余额跳转URL"
				
			}else {//新版本路径
				resultMap.put("personalVAction", propertiesUtil.getValue("personalLiveWalletRecordAction", "conf_live.properties"));//"直播钱包"账单 Action
				resultMap.put("personalLiveWalletRecordUrl", propertiesUtil.getValue("personalLiveWalletRecordUrl", "conf_live.properties"));//"直播钱包"URL 账单
				if (vList.size()>0 ) {
					if (vList.get(0).get("rankSource").toString().equals("4")) {// 判断是否是V客
						resultMap.put("personalLiveWalletRecordUrl", propertiesUtil.getValue("personalVUrl", "conf_live.properties"));//"V客主页"URL
						resultMap.put("personalVAction", propertiesUtil.getValue("personalVAction", "conf_live.properties"));//"V客跳转的action
						resultMap.put("personalLiveWalletRemark", propertiesUtil.getValue("personalLiveWalletVkeRemark", "conf_live.properties"));// "我的V客"字段
					}
				}
				resultMap.put("personalWalletRecordUrl", propertiesUtil.getValue("personalWalletRecordUrl", "conf_live.properties"));//"会员钱包URL"
				resultMap.put("personalWalletRecordAction", propertiesUtil.getValue("personalWalletRecordAction", "conf_live.properties"));//"会员钱包URL"
			}
			resultMap.put("personalExperienceEnrty", propertiesUtil.getValue("personalExperienceEnrty", "conf_live.properties"));//"美食体官入口URL"
			resultMap.put("personalExperienceAction", propertiesUtil.getValue("personalExperienceAction", "conf_live.properties"));//"美食体官入口URL"
			
			//IOS审核期间不开启余额, 鸟币 H5接口
			String iosPay = propertiesUtil.getValue("is_iospay", "conf_live.properties");
			String version  = propertiesUtil.getValue("checkIOSManorVersion", "conf_common.properties");//获取IOS审核版本
			if (iosPay.equals("0") && version.equals(appversion)) {
				if (baseRequest.getSystemversion().indexOf("ios")>=0) {
					resultMap.put("personalOriginalWalletSwitch", 0);//"会员钱包开关" 默认0 不开启  1开启
					resultMap.put("personalOriginalLiveWalletSwitch", 0);//"直播钱包开关" 默认0 不开启  1开启
				}else {
					resultMap.put("personalOriginalWalletSwitch", propertiesUtil.getValue("personalOriginalWalletSwitch", "conf_live.properties"));//"会员钱包开关" 默认0 不开启  1开启
					resultMap.put("personalOriginalLiveWalletSwitch", propertiesUtil.getValue("personalOriginalLiveWalletSwitch", "conf_live.properties"));//"直播钱包开关" 默认0 不开启  1开启
				}
			}else {
				resultMap.put("personalOriginalWalletSwitch", propertiesUtil.getValue("personalOriginalWalletSwitch", "conf_live.properties"));//"会员钱包开关" 默认0 不开启  1开启
				resultMap.put("personalOriginalLiveWalletSwitch", propertiesUtil.getValue("personalOriginalLiveWalletSwitch", "conf_live.properties"));//"直播钱包开关" 默认0 不开启  1开启
				String appleApprovalUid =  propertiesUtil.getValue("appleApprovalUid", "conf_live.properties");//"会员钱包URL" 
				if (appleApprovalUid.indexOf(uuid)>=0) {
					resultMap.put("personalOriginalWalletSwitch", "0");//"会员钱包开关" 默认0 不开启  1开启
				}
			}
			
			Integer signType = anchorSignTypeService.getSignType(personMap);
			resultMap.put("signType", signType);  //0 未签约主播， 1 签约主播， 2 内部测试账号，3美食体验官
			
			String fastEntry = propertiesUtil.getValue("personalMatchFastEntrySwitch", "conf_live.properties");//是否开启了主播星食尚主播快速入口
			resultMap.put("fastEntrySwitch", "0"); 
			resultMap.put("vstarStatus",getVstarStatus(uid)); 
			if (fastEntry.equals("1")) {//开启 则查询主播当前出于什么状态
				Map<Object, Object> vstarInfoMap =  vStarPlayerInfoDao.selectVStarInfoByUid(uid);
				if (vstarInfoMap!=null && vstarInfoMap.size()>0) {
					vstarState = Integer.parseInt(vstarInfoMap.get("status").toString());
					resultMap.put("fastEntrySwitch", "1"); 
					resultMap.put("vstarState", vstarInfoMap.get("status")!=null?vStarInfoState(vstarState):"");
					resultMap.put("fastEntryUrl", propertiesUtil.getValue("personalRealNameEntryUrl", "conf_live.properties")); 
					resultMap.put("fastEntryAction", propertiesUtil.getValue("personalMatchFastEntryAction", "conf_live.properties")); 
				}
			}
			
			String acnhorSchoolSwitch = propertiesUtil.getValue("personalAnchorSchoolSwitch", "conf_live.properties");//主播学堂入口是否开启
			resultMap.put("acnhorSchoolSwitch", "0"); //默认初始化为0
			if (acnhorSchoolSwitch.equals("1")) {
				if (personMap.get("utype").toString().equals("1")) {
					resultMap.put("acnhorSchoolSwitch", "1"); 
					resultMap.put("acnhorSchoolRemark",propertiesUtil.getValue("personalAcnhorSchoolRemark", "conf_live.properties")); 
					resultMap.put("acnhorSchoolUrl",propertiesUtil.getValue("personalAnchorSchoolUrl", "conf_live.properties")); 
					resultMap.put("acnhorSchoolAction",propertiesUtil.getValue("personalAnchorSchoolAction", "conf_live.properties")); 
				}
			}
			resultMap.put("experienceSwitch", propertiesUtil.getValue("experienceSwitch", "conf_live.properties")); 
			
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取个人信息失败");
		}
		// 客服电话
		resultMap.put("helpPhone", "");
		try {
			String helpPhone = propertiesUtil.getValue("helpPhone", "conf_common.properties");
			resultMap.put("helpPhone", helpPhone);
		} catch (Exception e) {
			log.warn("解析配置，获取客服电话失败");
		}
		resultMap.put("preSwitch", 1);  // 0 关  1 开
		resultMap.put("hasPwdSwitch", 1);  // 0 关  1 开
		// 通告开播按钮开关
		if (personMap != null) {
			int signType = personMap.get("sign_type") == null ? 0 : Integer.parseInt(personMap.get("sign_type").toString());
			if (signType == 4) {
				resultMap.put("preSwitch", 0);
				resultMap.put("hasPwdSwitch", 0);
			}
//			int root_role = personMap.get("root_role") == null ? 0 : Integer.parseInt(personMap.get("root_role").toString());
//			if (root_role == 3) {  //活动合作
//				resultMap.put("preSwitch", 0);
//			}
		}
		// 签约店铺同意协议h5地址
		resultMap.put("modifyStoreAgreeUrl", "");
		try {
			String modifyStoreAgreeUrl = propertiesUtil.getValue("modifyStoreAgreeUrl", "conf_xmer.properties");
			//控制 “美食频道，鸟币商城，我的卡卷”图片链接
			String foodPic = propertiesUtil.getValue("foodPic", "conf_common.properties");
			String mallPic = propertiesUtil.getValue("mallPic", "conf_common.properties");
			String myCouponPic = propertiesUtil.getValue("myCouponPic", "conf_common.properties");
			resultMap.put("modifyStoreAgreeUrl", modifyStoreAgreeUrl);
			resultMap.put("foodPic", foodPic);
			resultMap.put("mallPic", mallPic);
			resultMap.put("myCouponPic", myCouponPic);
		} catch (Exception e) {
			log.warn("解析配置失败, modifyStoreAgreeUrl, conf_xmer");
		}
		
		// 我要报名，报名详情
		String vstarPic = getVStarPic(vstarState);
		resultMap.put("vstarPic", vstarPic);
		String vstarEntryUrl = "";
		try {
			vstarEntryUrl = propertiesUtil.getValue("vstarEntryUrl", "conf_common.properties") + "&sessiontoken=" + baseRequest.getSessiontoken();
		} catch (Exception e) {
			log.warn("解析配置失败，vstarEntryUrl");
		}
		resultMap.put("vstarEntryUrl", vstarEntryUrl);  //大赛跳转url

		// 大赛分享数据
		String vstarShareUrl = "";
		String vstarShareTitle = "";
		String vstarShareContent = "";
		String vstarShareImg = "";
		try {
			vstarShareUrl = propertiesUtil.getValue("vstarShareUrl", "conf_common.properties");
			vstarShareTitle = propertiesUtil.getValue("vstarShareTitle", "conf_common.properties");
			vstarShareContent = propertiesUtil.getValue("vstarShareContent", "conf_common.properties");
			vstarShareImg = propertiesUtil.getValue("vstarShareImg", "conf_common.properties");
		} catch (Exception e) {
			log.warn("解析配置失败，vstarShareUrl vstarShareTitle vstarShareContent vstarShareImg");
		}
		resultMap.put("vstarShareUrl", vstarShareUrl);
		resultMap.put("vstarShareTitle", vstarShareTitle);
		resultMap.put("vstarShareContent", vstarShareContent);
		resultMap.put("vstarShareImg", vstarShareImg);


		// 使用哪种平台开播（1：腾讯，2：金山）
		int livePlatform = 1;  // 1 为腾讯 2 为金山
		String liveUrl = "";
		// 直播平台
		try {
			KSLiveEntity entity = ksCloudService.getAndCreateLiveInfo(personMap, 2);  //推流
			livePlatform = entity == null ? 1 : entity.getPlatform();
			liveUrl = entity == null ? "" : entity.getUrl();
		} catch (Exception e) {
			log.warn("获取直播平台失败，使用默认腾讯直播");
		}
		resultMap.put("livePlatform", livePlatform);
		resultMap.put("liveRtmpUrl", liveUrl);

		
		//响应
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取个人信息成功");
		response.setResponse(resultMap);
		return response;
	}

	private String getVStarPic(int vstarState) {
		String vstarRegisterPic = "";
		String vstarDetailPic = "";
		try {
			vstarRegisterPic = propertiesUtil.getValue("vstarRegisterPic", "conf_common.properties");
			vstarDetailPic = propertiesUtil.getValue("vstarDetailPic", "conf_common.properties");
		} catch (Exception e) {
			log.warn("解析配置失败，vstarRegisterPic， vstarDetailPic");
		}
		if (vstarState == 0 || vstarState == 3) {  //未报名，报名拒绝
			return vstarRegisterPic;
		} else {
			return vstarDetailPic;
		}
	}
	
	
	private String getVstarStatus (Integer uid) {
		//查询参赛的主播的状态
		Map<Object,Object> vstarMap = vStarPlayerInfoDao.selectVStarInfoByUid(uid);
		if (vstarMap==null || vstarMap.isEmpty()) {
			return null;
		}
		return ObjectUtils.toString(vstarMap.get("status"));
	}
//	/**
//	 * 个人用户及主播基本信息   不包含钱包的
//	* @Title: queryLivePersonByUid
//	* @return Object
//	 */
//	public Object queryLiverByAnchorId(LiverRecommendRequest liverRecommendRequest){
//		MapResponse response = null;
//		Map<Object, Object> resultMap = new HashMap<Object, Object>();
//		String uid = sessionTokenService.getStringForValue(liverRecommendRequest.getSessiontoken())+"";
//		if (org.apache.commons.lang.StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
//			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
//		}
//		//根据主播ID 获取直播用户的基本信息
//		Map<Object, Object> map = liveUserDao.queryLiverInfoById(liverRecommendRequest.getAnchorId());
//		if (map!=null) {
//			
//			resultMap.put("id", map.get("id")==null?"":map.get("id").toString()); //'直播用户id',
//			resultMap.put("avatar", map.get("avatar")==null?"":map.get("avatar").toString()); //'头像',
//			resultMap.put("uid", map.get("uid")==null?"":map.get("uid").toString()); //' 寻蜜鸟会员id',
//			resultMap.put("utype", map.get("utype")==null?"":map.get("utype").toString()); //'直播用户类型： 1 主播 2 普通用户',
//			resultMap.put("sex", map.get("sex")==null?"":map.get("sex").toString()); //'性别',
//			resultMap.put("phone", map.get("phone")==null?"":map.get("phone").toString()); //'手机号码',
//			resultMap.put("nname", com.xmniao.xmn.core.util.StringUtils.getUserNameStr(map.get("nname")==null?"":map.get("nname").toString()));
//			
//			response = new MapResponse(ResponseCode.SUCCESS,"获取个人信息成功");
//			response.setResponse(resultMap);
//		}else {
//			response = new MapResponse(ResponseCode.FAILURE,"获取个人信息失败");
//		}
//		return response;
//	}
//	
	
	/**
	 * 
	* @Title: queryLiveWalletInfo
	* @Description: 根据uid查询直播观众的财产信息
	* @return Map<Object,Object>
	 * @throws Exception 
	 */
	public Map<Object, Object> queryLiveWalletInfo(int uid) throws Exception{
		Map<Object,Object> WalletInfo=null;
		try{
		WalletInfo= personalcenterDao.queryLiveWalletInfo(uid);
		}catch(Exception e){
			e.printStackTrace();
			log.error("根据uid查询直播观众的财产信息");
			throw new Exception("查询直播观众的财产信息失败");
		}
		return WalletInfo;
	}
	
	/**
	 * 
	* @Title: queryCouponNum
	* @Description:根据用户uid查询优惠券总数 
	* @return Integer
	 * @throws Exception 
	 */
	public Integer queryCouponNum(int uid) throws Exception{
		int xmnCoupons = 0;//寻蜜鸟卷数量
		int saasCoupons =0;//商铺卷数量
		try{
			xmnCoupons = couponDao.sumXMNXMNCouponCount(String.valueOf(uid));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			saasCoupons = couponDao.sumSAASCouponCount(String.valueOf(uid));
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		try{
//			couponNum=personalcenterDao.queryCouponNum(uid);
//		}catch(Exception e){
//			e.printStackTrace();
//			log.error("根据用户uid查询优惠券总数失败");
//			throw new Exception("查询优惠券总数失败");
//			
//		}
		return xmnCoupons+saasCoupons;
	}
	/**
	 * 
	* @Title: queryliveIntegral
	* @Description: 根据用户uid查询积分对象
	* @return Map<Object,Object>
	 * @throws Exception 
	 */
	public Map<Object,Object> queryliveIntegral(int uid) throws Exception{
		Map<Object,Object> IntegralMap=null;
		try{
			IntegralMap=personalcenterDao.queryliveIntegral(uid);
		}catch(Exception e){
			e.printStackTrace();
			log.error("根据用户uid查询积分对象失败");
			throw new Exception("查询积分对象失败");
		}
		return IntegralMap;
	}
	
	
	

	/**
	 * 
	* @Title: queryAttentionAnchor
	* @Description: 根据查询关注用户
	* @return List<Map<Object,Object>>
	 */
	public List<Map<Object,Object>>  queryAttentionAnchor(String anchorid,int page,int status){
		int uid=Integer.parseInt(anchorid);
		Map<Object,Object> param=new HashMap<Object,Object>();
		List<Map<Object,Object>> attentionList=null;
		try{
		param.put("anchorid", uid);
		param.put("status", 3);
		param.put("page", page);
		param.put("limit", Constant.PAGE_LIMIT);
		attentionList=personalcenterDao.queryAttentionAnchor(param);
		
		}catch(Exception e){
			e.printStackTrace();
			log.error("根据uid查询关注黑名单人员失败");
		}
		return attentionList;
	}
	
	
	
	/**
	 * 
	* @Title: queryAttentionAnchor
	* @Description: 根据uid查询关注黑名单用户
	* @return Map<Object,Object>
	 */
	public Object queryAttentionBlackAnchor(String anchorid,int page){
		
			List<Map<Object,Object>> blacklist=new ArrayList<Map<Object,Object>>();
			Map<Object,Object> blackMap=new HashMap<Object,Object>();
			
			try{
			
			//查询主播id
			/*Map<Object, Object> personMap=this.queryLiverPersonByUid(Integer.parseInt(uuid));
			if(personMap==null || personMap.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "未获取此主播信息");
			}*/
//			String anchorid= personMap.get("anchorid")==null?null:personMap.get("anchorid").toString();
			
			//根据主播id查询关注主播的黑名单人员
			List<Map<Object,Object>> attentionList=this.queryBlackPerson(anchorid, page);
			if(attentionList==null || attentionList.size()<=0){
				blackMap.put("blacklist", blacklist);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未有黑名单人员");
				response.setResponse(blackMap);
				return response;
			}
			List<String> ids=new ArrayList<String>();
			for(Map<Object,Object> attentiomap:attentionList){
				String liver_end_id=attentiomap.get("liver_end_id")==null?"0":attentiomap.get("liver_end_id").toString();
				ids.add(liver_end_id);
			}
			//根据黑名单人员的观众id查询观众基本信息
			List<Map<Object,Object>> personList=this.queryLiverPersonByListId(ids);
			if(personList!=null && personList.size()>0){
				for(Map<Object,Object> generalInfo:personList){
					Map<Object,Object> blackattmap=new HashMap<Object,Object>();
					int id=generalInfo.get("anchorid")==null?-1:Integer.parseInt(generalInfo.get("anchorid").toString());
					for(Map<Object,Object> attentiomap:attentionList){
						int liver_end_id=attentiomap.get("liver_end_id")==null?0:Integer.parseInt(attentiomap.get("liver_end_id").toString());
						if(id==liver_end_id){
							blackattmap.put("id", attentiomap.get("id"));
							blackattmap.put("viewer_id", attentiomap.get("liver_end_id"));
							//blackattmap.put("status", 0);
							blackattmap.put("anchor_id", attentiomap.get("liver_str_id"));
							blackattmap.put("create_time", attentiomap.get("create_time"));
						break;
						}
					}
						blackattmap.put("nname", generalInfo.get("nname").toString());
						String avatar=generalInfo.get("avatar")==null||generalInfo.get("avatar").equals("")?"":(fileUrl+generalInfo.get("avatar").toString());
						blackattmap.put("avatar", avatar);
						blackattmap.put("achievement", generalInfo.get("achievement").toString());
						blackattmap.put("rank_id", generalInfo.get("levels_id").toString());
						blackattmap.put("member_rank_name", generalInfo.get("member_rank_name"));
						blackattmap.put("sex", generalInfo.get("sex"));
						blackattmap.put("rank_no", generalInfo.get("rank_no"));
						blacklist.add(blackattmap);
				}
				blackMap.put("blacklist", blacklist);
			}else{
				blackMap.put("blacklist", blacklist);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未有黑名单人员");
				response.setResponse(blackMap);
				return response;
			}
			
			}catch(Exception e){
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取黑名单人员成功");
			response.setResponse(blackMap);
			return response;
	}
	
	
	/**
	 * 
	* @Title: queryBlackPerson
	* @Description: 查询黑名单人员 
	* @return List<Map<Object,Object>>
	 * @throws Exception 
	 */
	public List<Map<Object,Object>> queryBlackPerson(String liver_str_id,int page) throws Exception{
		Map<Object,Object> param=new HashMap<Object,Object>();
		List<Map<Object,Object>> blackList=null;
		try {
			param.put("liver_str_id", liver_str_id);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			blackList=personalcenterDao.queryBlackPerson(param);
		} catch (Exception e) {
			log.error("查询黑名单人员 失败");
			e.printStackTrace();
			throw new Exception("查询黑名单人员 失败");
		}
		return blackList;
		
	}
	
	/**
	 * 
	* @Title: addAndReviceblack_List
	* @Description: 黑名单增加/解除(yezhiyong)
	* @return Object
	 */
	public Object addAndReviceblack(RelieveblackRequst relieveblackrequst){
		try{
			//验证token
			String uid = sessionTokenService.getStringForValue(relieveblackrequst.getSessiontoken()) + "";
			if (org.apache.commons.lang.StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
			}
			
			//操作类型 1加入黑名单    2解除黑名单
			Integer type = relieveblackrequst.getType();
			//操作用户对象
			Integer liverId = relieveblackrequst.getLiverId();
			
			//查询用户信息
			Map<Object, Object> personMap=this.queryLiverPersonByUid(Integer.parseInt(uid));
			if(personMap==null || personMap.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "未获取到用户个人信息");
			}
			
			//发起拉黑者
			String strId= personMap.get("anchorid").toString();
			
			//查询是否存在拉黑记录
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("liver_str_id", strId);
			paramMap.put("liver_end_id", liverId);
			Integer result = personalcenterDao.queryLiveBlack(paramMap);
			
			if(type==1){
				//加入黑名单
				if (result > 0) {
					return new BaseResponse(ResponseCode.FAILURE, "你已拉黑此用户");
				}
				return this.addBlackInfo(strId, liverId);
				
			}else if(type==2){
				//解除黑名单
				if (result == 0) {
					return new BaseResponse(ResponseCode.FAILURE, "你已解除此用户黑名单");
				}
				return this.deleteBlackInfo(strId,liverId);
				
			}else{
				return new BaseResponse(ResponseCode.FAILURE, "黑名单操作失败,操作参数异常");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "黑名单操作失败");
		}
	}
	
	/**
	 * 
	* @Title: addAndReviceblack_List
	* @Description: 黑名单增加/解除(yeyu)
	* @return Object
	 */
	public Object addAndReviceblack_List(RelieveblackRequst relieveblackrequst){
		//验证token
		String uid = sessionTokenService.getStringForValue(relieveblackrequst.getSessiontoken()) + "";
		if (org.apache.commons.lang.StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//操作类型 1加入黑名单    2解除黑名单
		Integer type = relieveblackrequst.getType();
		Integer liverId = relieveblackrequst.getLiverId();
		try{
			Map<Object, Object> personMap=this.queryLiverPersonByUid(Integer.parseInt(uid));
			if(personMap==null || personMap.size()<=0){
				log.info("未获取到用户个人信息");
				return new BaseResponse(ResponseCode.FAILURE, "未获取到用户个人信息");
			}
			String strId= personMap.get("anchorid")==null?null:personMap.get("anchorid").toString();
			String strphone= personMap.get("phone")==null?null:personMap.get("phone").toString();
			
			
			Map<Object, Object> TopersonMap=this.queryLiverPersonById(liverId);
			if(TopersonMap==null || TopersonMap.size()<=0){
				log.info("未获取到对方用户个人账号信息");
				return new BaseResponse(ResponseCode.FAILURE, "未获取到对方用户个人账号信息");
			}
			String to_phone= TopersonMap.get("phone")==null?null:TopersonMap.get("phone").toString();

			Map<Object,Object> paramMap=this.getparamMap(strphone, to_phone);
			if(paramMap==null || paramMap.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "黑名单操作失败,未获取到调用腾讯云参数");
			}
			
			if(type==1){//加入黑名单
				if(TLSUtil.BlackListOperation(paramMap, 1)){
				return this.addBlackInfo(strId, liverId);
				}
			}else if(type==2){//解除黑名单
				if(TLSUtil.BlackListOperation(paramMap, 2)){
				return this.deleteBlackInfo(strId,liverId);
				}
			}else{
				return new BaseResponse(ResponseCode.FAILURE, "黑名单操作失败,操作参数异常");
			}
		}catch(Exception e){
			log.equals("(type:1增加黑名单，2解除黑名单)type="+type+"，操作黑名单失败,"+e.getMessage());
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
		}
		return new BaseResponse(ResponseCode.FAILURE, "黑名单操作失败");
	}
	
	/**
	 * 
	* @方法名称: getparamMap
	* @描述: 获取腾讯黑名单参数
	* @返回类型 void
	* @创建时间 2016年10月10日
	* @param From_Account
	* @param To_Account
	* @throws Exception
	 */
	public Map<Object,Object> getparamMap(String From_Account,String To_Account) {
		Map<Object,Object> paramMap=null;
		try{
			//从redis中,获取管理员签名
			String adminSig = stringRedisTemplate.opsForValue().get("adminSig");
			
			String sdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
			String identifier = propertiesUtil.getValue("identifier", "conf_live.properties");
			
			if (adminSig == null) {
				//调用tls,获取管理员tls的sig
				adminSig = TLSUtil.getTLSSig(sdkAppid, identifier);
				stringRedisTemplate.opsForValue().set("adminSig", adminSig);
				stringRedisTemplate.expire("adminSig", 180, TimeUnit.DAYS);
			}
			
			paramMap=new HashMap<Object,Object>();
			paramMap.put("tlsSig", adminSig);
			paramMap.put("sdkAppid", sdkAppid);
			paramMap.put("identifier", identifier);
			paramMap.put("From_Account", From_Account);
			List<String> toList=new ArrayList<>();
			toList.add(To_Account);
			paramMap.put("To_Account", toList);
		}catch(Exception e){
			log.error("方法getparamMap()黑名单操作获取腾讯云参数异常");
			e.printStackTrace();
		}
		return paramMap;
	}
	
	
	
	/**
	 * 
	* @Title: deleteBlackInfo
	* @Description: 解除黑名单
	* @return Object
	 * @throws Exception 
	 */
	public Object deleteBlackInfo(String strId,Integer liverId) throws Exception{
		try {
			Map<Object,Object> param=new HashMap<>();
			param.put("liver_str_id", strId);//发起拉黑用户ID
			param.put("liver_end_id", liverId);//被拉黑用户ID
			Integer deletenums=personalcenterDao.deleteBlackInfo(param);
			if(deletenums>0){
				log.info("解除黑名单成功");
				return new BaseResponse(ResponseCode.SUCCESS, "解除黑名单成功");
			}
			log.info("解除黑名单失败");
			return new BaseResponse(ResponseCode.FAILURE, "解除黑名单失败");
		} catch (Exception e) {
			log.error("解除黑名单"+e.getMessage());
			e.printStackTrace();
			throw new Exception("解除黑名单异常");
		}
	}
	
	/**
	 * 
	* @Title: addBlackInfo
	* @Description: 新增黑名单
	* @return Object
	 */
	public Object addBlackInfo(String strId,Integer liverId) throws Exception{
		try{
		Map<Object,Object> param=new HashMap<>();
		param.put("liver_str_id", strId);//发起拉黑用户ID
		param.put("liver_end_id", liverId);//被拉黑用户ID
		param.put("create_time", DateUtil.format(new Date()));//创建时间
		param.put("update_time", DateUtil.format(new Date()));//更新时间
		Integer blacknum=personalcenterDao.addBlackInfo(param);
		if(blacknum<=0){
			return new BaseResponse(ResponseCode.FAILURE, "拉入黑名单失败");
		}
		log.info("拉入黑名单成功,客户ID"+strId);
		return new BaseResponse(ResponseCode.SUCCESS, "拉入黑名单成功");
		}catch(Exception e){
			log.error("拉入黑名单失败");
			e.printStackTrace();
			throw new Exception("拉入黑名单失败");
		}
		
	}
	/**
	 * 
	* @Title: queryLiverPersonByListId
	* @Description: 根据集合ID查询用户信息
	* @return List<Map<Object,Object>>
	 * @throws Exception 
	 */
	public List<Map<Object, Object>> queryLiverPersonByListId(List<String> ids) throws Exception{
		List<Map<Object, Object>> personList=null;
		try{
			if(ids!=null && ids.size()>0){
			personList=personalcenterDao.queryLiverPersonByListId(ids);
			}
		}catch(Exception e){
			log.error("根据集合ID查询用户信息失败");
			e.printStackTrace();
			throw new Exception("根据集合ID查询用户信息失败");
		}
		return personList;
	}
	
	/**
	 * 
	* @方法名称: queryLivePersonByListName
	* @描述: 搜索主播
	* @返回类型 Object
	* @创建时间 2016年10月17日
	* @param parameterText
	* @param page
	* @return
	* 
	 */
	public Object queryLivePersonByListName(String parameterText,int page){
		List<Map<Object, Object>> personalList=null;
		List<Map<Object, Object>> relustList=new ArrayList<>();
		try{
			Map<Object,Object> result=new HashMap<>();
			Map<Object,Object> param=new HashMap<>();
			param.put("parameterText", parameterText);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			personalList=personalcenterDao.queryLivePersonByListName(param);
			if(personalList!=null && personalList.size()>0){
				for(Map<Object,Object> personMap:personalList){
					Map<Object,Object> resultMap=new HashMap<>();
					String avatar=(personMap.get("avatar")==null||personMap.get("avatar").equals(""))?"":(fileUrl+personMap.get("avatar").toString());
					resultMap.put("uname", personMap.get("uname").toString());//登陆名称
//					resultMap.put("nname", StringUtils.getUserNameStr(personMap.get("nname").toString()));//用户昵称
					
					
					if (org.apache.commons.lang.StringUtils.isEmpty(personMap.get("nname").toString().trim())) {
						//如果昵称为空,则设置用户昵称为手机号码，中间4位数为*
						String unameStr = personMap.get("uname").toString();
						String nnameStr = unameStr.substring(0, 3) + "****" + unameStr.substring(unameStr.length() - 4);
						resultMap.put("nname", nnameStr);
						//同步数据库用户昵称
						Map<Object, Object> paramMap = new HashMap<>();
						paramMap.put("nname", nnameStr);
						ursDao.updateUrsByUid(paramMap);
						
					}else {
						//返回昵称(过长处理)
						resultMap.put("nname", StringUtils.getUserNameStr(personMap.get("nname").toString()));//用户昵称
						
					}
					
					resultMap.put("avatar", avatar);//用户头像
					resultMap.put("rank_no", personMap.get("rank_no"));//等级数
					resultMap.put("sex", personMap.get("sex"));//性别
					resultMap.put("anchorRoomNo", personMap.get("anchor_room_no"));//房间号
					resultMap.put("uid", personMap.get("uid"));//uid
					String anchorid= personMap.get("anchorid")==null?null:personMap.get("anchorid").toString();//ID
					resultMap.put("anchorId", anchorid);//主播ID
					relustList.add(resultMap);
				}
			}
			log.info("获取主播列表成功+parameterText："+parameterText+",page:"+page);
			result.put("anchorList", relustList);
			MapResponse response=new MapResponse(ResponseCode.SUCCESS, "获取主播列表成功");
			response.setResponse(result);
			return response;
		}catch(Exception e){
			log.error("搜索主播用户失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "搜索主播用户异常，请联系管理员");
		}
	}
	
	/**
	 * 
	* @方法名称: getResultMap
	* @描述: 获取到返回结果Map
	* @返回类型 Map<Object,Object>
	* @创建时间 2016年10月17日
	* @param resultMap
	* @param personMap
	* @return
	 */
	public Map<Object,Object> getResultMap(Map<Object,Object> resultMap,Map<Object,Object> personMap){
		String avatar=(personMap.get("avatar")==null||personMap.get("avatar").equals(""))?"":(fileUrl+personMap.get("avatar").toString());
		resultMap.put("uname", personMap.get("uname").toString());//登录名称
		if (org.apache.commons.lang.StringUtils.isEmpty(personMap.get("nname").toString())) {
			String nnameStr = "";
			if (personMap.get("uname") != null) {
				//判断是微信用户还是手机号码用户,如果是手机号码用户,并且昵称为空,则设置用户昵称为手机号码，中间4位数为*,如果是微信用户，并且用户昵称为空,则取微信用户 + 中间4位
				String unameStr = personMap.get("uname").toString();
				if(unameStr.length() == 11){
					nnameStr = unameStr.substring(0, 3) + "****" + unameStr.substring(unameStr.length() - 4);
				}else{
					nnameStr="微信用户" + unameStr.substring(3,7);
				}
			}else {
//				nnameStr="微信用户" + personMap.get("openid").toString().substring(3,7);
				nnameStr="匿名用户";
			}
			
			resultMap.put("nname", nnameStr);
			
		}else {
			//返回昵称(过长处理)
			resultMap.put("nname", StringUtils.getUserNameStr(personMap.get("nname").toString()));//用户昵称
			
		}
		resultMap.put("avatar", avatar);//用户头像
		resultMap.put("weixin", personMap.get("weixin")==null?"":personMap.get("weixin").toString());//主播微信
		resultMap.put("achievement", personMap.get("achievement"));// 头衔
		resultMap.put("levels_id", personMap.get("levels_id"));// 等级ID
		resultMap.put("member_rank_name", personMap.get("member_rank_name"));//等级名称
		resultMap.put("givegifts", personMap.get("givegifts"));//送出
		resultMap.put("follow", personMap.get("follow"));//关注数
//		resultMap.put("fans", personMap.get("fans"));//粉丝数
		resultMap.put("rank_no", personMap.get("rank_no"));//等级数
		resultMap.put("sex", personMap.get("sex"));//性别
		resultMap.put("anchorRoomNo", personMap.get("anchor_room_no"));//房间号
		
		Map<Object, Object> fansMap = new HashMap<Object, Object>();
		fansMap.put("liver_end_id", personMap.get("id"));
		fansMap.put("uid", personMap.get("uid"));
		resultMap.put("fans", personalDetailDao.queryEachFocusAndFansCount(fansMap));//粉丝数
		
		return resultMap;
	}
	
	
	/**
	 * 
	* @方法名称: queryAnchorInfoBasic
	* @描述: 获取主播个人基本信息
	* @返回类型 Object
	* @创建时间 2016年10月17日
	* @return
	 */
	public Object queryAnchorInfoBasic(AnchorInfoBasicRequest aibRequest){
		Map<Object,Object> resultMap=new HashMap<>();
		try {
			
			String uid = (String) sessionTokenService.getStringForValue(aibRequest.getSessiontoken());
			if(uid==null||uid==""|| "null".equalsIgnoreCase(uid)){
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
			}
			log.info("↓======================获取用户基本信息，主播UID="+uid);
			//获取登陆用户的基本信息
			Map<Object,Object> liverMap=this.queryLiverPersonByUid(Integer.parseInt(uid));
			if(liverMap==null || liverMap.size()<=0){
				log.info("未获取到当前用户的基本信息,UID="+uid);
				return new BaseResponse(ResponseCode.FAILURE, "未获取到当前用户的基本信息");
			}
			log.info("获取到当前用户的基本信息="+liverMap.toString());
			String liverId= liverMap.get("anchorid")==null?null:liverMap.get("anchorid").toString();//liverId

			
			
			log.info("↓======================获取主播基本信息，主播AnchorId="+aibRequest.getAnchorId());
			//获取主播的基本信息
            Map<Object,Object> anchorMap = new HashMap<Object, Object>();
            if (aibRequest.getUserType() !=null && aibRequest.getUserType() == 1) {
                anchorMap = freshIndianaRobotDao.selectById(aibRequest.getAnchorId());
            }else {
			    anchorMap=this.queryLiverPersonById(aibRequest.getAnchorId());
            }
			if(anchorMap==null || anchorMap.size()<=0){
				log.info("未获取到主播基本信息--X,AnchorId="+aibRequest.getAnchorId());
				return new BaseResponse(ResponseCode.FAILURE, "未获取到主播基本信息");
			}
			log.info("获取到主播基本信息"+anchorMap.toString());
			resultMap=this.getResultMap(resultMap, anchorMap);
			resultMap.put("sign", anchorMap.get("sign"));//主播签名
			resultMap.put("phone", anchorMap.get("phone"));//手机号码
			resultMap.put("signType", anchorMap.get("signType") == null ? 0 : anchorMap.get("signType"));
			
			
			log.info("↓======================获取对主播的贡献榜，主播AnchorId="+aibRequest.getAnchorId());
			//贡献榜
			List<Map<String,String>> reusltconList=new ArrayList<>();
			List<Map<String,String>> conList=this.contributionList(aibRequest.getAnchorId()+"", "1");//主播个人贡献榜列表
			if (conList!=null && conList.size() > 0) {
				//获取土豪头像,昵称,性别,等级
				reusltconList = anchorPersonService.getRichInfo(conList);
			}
			resultMap.put("conList", reusltconList);//贡献榜
			
			
			
			
			log.info("↓======================获取主播的直播记录及回放列表，主播AnchorId="+aibRequest.getAnchorId());
			List<Map<Object, Object>>  resultliveRecordList=new ArrayList<>();
			//获取主播的直播记录及回放记录
			List<LiveRecordInfo> liveRecordList=anchorLiveRecordService.getliveRecordList(1, aibRequest.getAnchorId(), Integer.parseInt(uid));
			if(liveRecordList!=null && liveRecordList.size()>0){
				resultliveRecordList = anchorLiveRecordService.updateLiveRecordList(liveRecordList);

				// 金山云拉流
				if (resultliveRecordList != null && resultliveRecordList.size() > 0) {
					for (Map<Object, Object> record : resultliveRecordList) {
						record.put("livePlatform", 1); //直播使用平台  1 腾讯直播  2 金山云直播
						record.put("liveRtmpUrl", "");  //拉流地址
						try {
							int zhiboType = record.get("zhiboType") == null ? 0 : Integer.parseInt(record.get("zhiboType").toString());
							// 正在直播
							if (zhiboType != 1) {
								continue;
							}
							//  生成拉流地址
							KSLiveEntity entity = ksCloudService.createKSLPullUrl(anchorMap.get("uid").toString(), anchorMap);
							if (entity != null) {
								resultMap.put("livePlatform", entity.getPlatform());
								resultMap.put("liveRtmpUrl", entity.getUrl());
							}
						} catch (Exception e) {
							log.warn("添加金山拉流失败", e);
						}
					}
				}

			}
			resultMap.put("liveRecordList", resultliveRecordList);
			
			
			
			log.info("↓======================获取客户是否有关注过此主播，客户LiverID="+liverId+"，主播AnchorId="+aibRequest.getAnchorId());
			//是否已关注
			//返回观众有无关注过该房间主播 
			Map<String, Object> focusMap = new HashMap<String, Object>();
			focusMap.put("liver_str_id", liverId);
			focusMap.put("liver_end_id", aibRequest.getAnchorId());
			int  focusCount = liveUserDao.queryFocusCount(focusMap);
			if (focusCount>0) {
				resultMap.put("isFocus", "1");
			}else {
				resultMap.put("isFocus", "0");
			}
			
			
			
			//查询之前是否发送过私信
			Map<Object,Object> param=new HashMap<Object,Object>();
//			param.put("send_liver_id", liverId);//消息发送者用户ID
//			param.put("to_liver_id", aibRequest.getAnchorId());//消息接收者用户ID
			param.put("send_liver_uname", liverMap.get("phone").toString());//消息发送者
			param.put("to_liver_uname", anchorMap.get("phone"));//消息接收者
//			Map<Object, Object> secretMarkMap = privatemessageDao.queryPrivateMessageBysendId(param);
			Map<Object, Object> secretMarkMap = privatemessageDao.queryPrivateMessage(param);
			if (secretMarkMap != null && secretMarkMap.size() > 0) {
				//是否发送私信免费
				resultMap.put("isFree", 1);//Android
				resultMap.put("isfree", 1);//IOS
			}else {
				resultMap.put("isFree", 0);
				resultMap.put("isfree", 0);
			}
			log.info("获取主播个人基本信息成功:客户UID="+uid);
			
			MapResponse response=new MapResponse(ResponseCode.SUCCESS, "获取主播个人基本信息成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			log.error("获取主播基本信息失败，主播anchorId="+aibRequest.getAnchorId()+e);
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取主播基本信息异常，请联系管理员");
		}
	}
	
	/**
	 * 
	* @方法名称: contributionList
	* @描述: 主播个人贡献榜
	* @返回类型 List<Map<String,String>>
	* @创建时间 2016年10月18日
	* @param anchorId
	* @param page
	* @return
	* @throws Exception
	 */
	public List<Map<String,String>>  contributionList(String anchorId,String page ) throws Exception{
		LiveWalletService.Client  client=null;
		List<Map<String,String>> walletList=null;
		try {
			Map<String, String> walletMap = new HashMap<>();
			walletMap.put("pageNo", page);
			walletMap.put("pageSize", Constant.PAGE_LIMIT.toString());
			walletMap.put("rtype", "3");//1.平台鸟币消费排行 2.每场直播消费排行 3.主播个人的消费排行
			walletMap.put("anchorId", anchorId);//主播id
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			 client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			WalletRecord walletRecord =	client.birdCoinList(walletMap);
			if(walletRecord!=null){
				walletList=walletRecord.getWalletList();
			}
			log.info("贡献榜列表成功,主播liverID：" + anchorId);
		} catch (Exception e) {
			log.error("获取主播贡献榜异常，主播liverID："+anchorId);
			e.printStackTrace();
			throw new Exception("获取主播贡献榜异常,请联系管理员");
		}finally {
			thriftUtil.coloseTransport();
		}
		return walletList;
	}

	
	private int getSellerPackageCounts(String uid){
		int counts = 0;
		try {
			Map<String,String> paraMap = new HashMap<String,String>();
			paraMap.put("uid", uid);
			paraMap.put("isOverdue", String.valueOf(1));
			paraMap.put("page", String.valueOf(1));
			paraMap.put("pagesize", String.valueOf(20));
			
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("ValueCardService");
			
			ValueCardService.Client client = new ValueCardService.Client(tMultiplexedProtocol);
			
			thriftUtil.openTransport();
			//调业务接口获取用户储值卡信息
			List<Map<String,String>> cards = client.getValueCardMsg(paraMap);		
			if(cards != null && cards.size() > 0){
				counts = Integer.parseInt(cards.get(0).get("cardNum").toString());
			}
			thriftUtil.coloseTransport();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询用户消费记录信息异常");
		} 
		
		return counts;
	}
	
	/**
	 * 报名主播的当前审核状态
	 * */
	public String vStarInfoState(int state){
		switch (state) {
			case 1:return "已报名";
			case 2:return "报名审核通过";
			case 3:return "报名审核拒绝";
			case 4:return "实名认证待审核";
			case 5:return "实名认证通过";
			case 6:return "实名认证拒绝";
			case 7:return "试播审核通过";
			case 8:return "试播审核拒绝";
			default:return "";
		}
	}
	
	
}
