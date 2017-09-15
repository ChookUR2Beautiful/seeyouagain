package com.xmniao.xmn.core.api.controller.seller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.SelleridRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.UserPayBirdCoinDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.seller.dao.DebitcardSellerDao;
import com.xmniao.xmn.core.seller.dao.ExperienceCommentDao;
import com.xmniao.xmn.core.seller.entity.DebitcardSeller;
import com.xmniao.xmn.core.seller.entity.ExperienceCommentMedia;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.seller.entity.SellerDetailed;
import com.xmniao.xmn.core.seller.entity.SellerLandMark;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.seller.entity.UnsignedSeller;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageDao;
import com.xmniao.xmn.core.thrift.UserActionService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.UnsignedSellerDao;
import com.xmniao.xmn.core.xmer.service.SellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
*      
* 类名称：SellerDetailApi   
* 类描述：  查询店铺详情
* 创建人：xiaoxiong   
* 创建时间：2016年11月16日 上午10:01:35     
*
 */
@Controller
@RequestMapping("seller")
public class SellerDetailApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(SellerDetailApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private UrsService ursService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private String ip_number_business;
	
	@Autowired
	private String port_business;
	
	@Autowired
	private LiveUserDao liveUrsDao;
	
	@Autowired
	private BillDao billDao;
	
	//注入店铺dao
	@Autowired
	private SellerDao sellerDao;

	//套餐dao
	@Autowired
	private SellerPackageDao sellerPackageDao;
	
	//注入充值卡dao
	@Autowired
	private DebitcardSellerDao debitcardSellerDao;
	
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	//注入服务器地址
	@Autowired
	private String fileUrl;
	
	@Autowired
	private ExperienceCommentDao experienceCommentDao;
	
	@Autowired
	private UserPayBirdCoinDao userPayBirdCoinDao;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private UnsignedSellerDao unsignedSellerDao;
	
	@ResponseBody
	@RequestMapping("/detail")
	public Object detail(SelleridRequest request){
		log.info("IDRequest data:" + request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题:"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);	
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		SelleridRequest request = (SelleridRequest) object;
		Map<Object,Object> result = new HashMap<Object, Object>();
		
		String uid = sessionService.getStringForValue(request.getSessiontoken())+"";
		Integer sellerid = request.getSellerid();
		
		try {
			/*
			 * sellerType = 2时，为非签约店铺详情 
			 */
			Integer sellerType = request.getSellerType();
			if(sellerType !=null && sellerType.equals(2)){
				
				UnsignedSeller unsignedSeller = unsignedSellerDao.querySellerById(sellerid);
				if(unsignedSeller==null){
					return new BaseResponse(ResponseCode.DATA_NULL, "没有找到商家基本信息！");
				}
				result.put("sellerInfo", unsignedSeller);
				result.put("sellerType", sellerType);
				
				//查询最新的一张通过审核点评图片 和 总图片数量
				List<ExperienceCommentMedia> photos = experienceCommentDao.listSellerPhotos(sellerid, 1, null);
				String photoUrl = "";
				if(!photos.isEmpty()){
					ExperienceCommentMedia photo = photos.get(0);
					photoUrl = fileUrl + photo.getMediaUrl(); 
				}
				result.put("photoUrl", photoUrl);
				result.put("photoCount", photos.isEmpty() ? 0 : photos.size());
				
				shareInfo(sellerid, result, unsignedSeller.getSellername());
				
				MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
				mapResponse.setResponse(result);
				
				return mapResponse;
			}
			//查询商家基本信息
			Seller seller = sellerService.querySellerBySellerid(Long.parseLong(request.getSellerid().toString()));
			if(seller==null){
				return new BaseResponse(ResponseCode.DATA_NULL, "没有找到商家基本信息！");
			}
			result.put("sellerType", sellerType);
			result.put("tips",seller.getTips());
			//TODO判断店铺是否存有预售、套餐、充值卡等
			//根据店铺ID查询套餐
			List<Map<Object,Object>> combos = new ArrayList<Map<Object,Object>>();
			try{
				combos = sellerPackageDao.selectCombosBySellerId(sellerid);
				
				if(combos != null && combos.size() > 0){
					
					//获取套餐详情的接口地址
					String url = propertiesUtil.getValue("comboUrl", "conf_common.properties");
					
					for(Map<Object,Object> combo : combos){
						combo.put("comboimage", StringUtils.isEmpty(
							ObjectUtils.toString(combo.get("comboimage"))
							)?"":fileUrl+combo.get("comboimage").toString());
						
						combo.put("url", url);// 套餐详情跳转地址
					}
				}else{
					combos = new ArrayList<Map<Object,Object>>();
				}
				
				result.put("combos", combos);
			}catch(Exception e){
				e.printStackTrace();
				log.info("查询套餐异常");
			}
			
			//判断是否开启充值按钮
			String buton = hideDebitcardSellerButon(String.valueOf(request.getSellerid()));
			result.put("hideButon", buton);//是否开启充值按钮 0 关闭 1 开启 
			
			//IOS审核期间不开启充值按钮
			String iosPay = propertiesUtil.getValue("is_iospay", "conf_live.properties");
			if (iosPay.equals("0")) {
				if (request.getSystemversion().indexOf("ios")>=0) {
					result.put("hideButon", 0);//是否开启充值按钮 0 关闭 1 开启 
				}
			}
			
			//根据ID 查询充值信息
			result.put("debitcardType", 0);//充值卡类型 0 没有充值卡 1.普通商家 2.连锁总店 3 区域代理
			try{
				DebitcardSeller debitcard = debitcardSellerDao.findBySellerId(String.valueOf(sellerid));
				if(debitcard != null){
					result.put("debitcardType", debitcard.getSellertype());// 0 没有充值卡 1.普通商家 2.连锁总店 3 区域代理
					result.put("cardId", debitcard.getId());//充值卡ID
				}
			}catch(Exception e){
				e.printStackTrace();
				log.info("查询充值卡异常");
			}
			//店铺标签
			List<String> tags = new ArrayList<String>();
			String tagIds = seller.getTagIds();
			if(StringUtils.isNotEmpty(tagIds)){
				String[] arr = tagIds.split(",");
				for(String s : arr){
					tags.add(s.trim());
				}
			}
			
			List<String> tagNames = new ArrayList<String>();
			if(!tags.isEmpty()){
				
				try{
					tagNames = sellerDao.findSellerTagNameBySellerId(tags);
				}catch(Exception e){
					e.printStackTrace();
					log.info("查询店铺标签异常");
				}
			}
			
			result.put("tagNames", tagNames);
			//isonline==1商家上线，其他状态不显示
			if(seller.getIsonline()!=1){
				return new BaseResponse(ResponseCode.SELLER_IS_NOT_ONLINE, "商家未上线或已下线！");
			}
			result.put("address", seller.getAddress());	//地址
			result.put("sellerName", seller.getSellername());//商家名称
			result.put("sdate", seller.getSdate());	//营业时间
			result.put("typename", seller.getTypename());//一级类别
			result.put("tradename", seller.getTradename());//二级类别
			result.put("tel", seller.getTel());//二级类别
			result.put("genre", seller.getGenre());//二级分类ID
			result.put("city", seller.getCity());
			//是否是寻蜜客
			if(uid.equals("")||uid.equals("null"))
			{
				result.put("isXmer", 0);
				result.put("lable", 0);
			}else
			{	//已登入用户操作，判断是否是寻蜜客店铺
				if(seller.getUid()!=null){
					result.put("isXmer", seller.getUid()==Integer.parseInt(uid)?1:0);
					 //查询商家标签(如果不是寻蜜客店铺查询标签)
					if(seller.getUid()!=Integer.parseInt(uid)){
						lable(result,request.getSellerid(),Integer.parseInt(uid));
					}
				}else{
					//查商家标签
					lable(result,request.getSellerid(),Integer.parseInt(uid));
				}
				
				 //查询浏览表中是否有浏览的记录
				int viewCount = sellerService.querySellerBrowsedCount(uid,request.getSellerid());
				if(viewCount>0){
					 // 用户浏览店铺的数量+1
					sellerService.updateSellerBrowSed(uid,request.getSellerid());
				}else{
					
					 //添加用户浏览店铺记录
					sellerService.insertSellerBrowsed(uid,request.getSellerid());
				}
				
				 //调用业务服务修改权重
				TTransport transport = null;
				try {
					 // 设置调用的服务地址为本地，端口为 7911
		            transport = new TSocket(ip_number_business,Integer.valueOf(port_business));
		            TFramedTransport frame = new TFramedTransport(transport);
		            // 设置传输协议为 TBinaryProtocol
		            TProtocol protocol = new TBinaryProtocol(frame);
		            
		            //用户服务服务模块
		            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(
		                    protocol, "UserActionService");
		            UserActionService.Client client = new UserActionService.Client(orderProtocol);
		            transport.open();
		            /*查询直播用户信息*/
		            LiverInfo liverInfo = liveUrsDao.queryLiverByUid(Long.valueOf(uid));
		            
		            String utype="1";
		            if(liverInfo!=null){
		            	utype = liverInfo.getUtype()==1?"2":"1";
		            }
		            
		            List<Map<String,String>> list = new ArrayList<>();
		            Map<String,String> params =new HashMap<>();
		            params.put("actiontype", "1");
		            params.put("xmntype", utype);/*1普通用户2主播*/
		            params.put("operate", "1");
		            params.put("sellerid",request.getSellerid()+"");
		            params.put("uid", uid);
		            list.add(params);
		            client.userActionService(list);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(transport!=null){
						transport.close();
					}
				}
				
			}
			try {
				result.put("agio",calculationSellerDiscount(seller.getAgio()+""));
			} catch (Exception e) {
				e.printStackTrace();
				log.info("立减金额处理失败！");
			}
			/**
			 * 查询详细信息
			 */
			SellerDetailed sellerDetailed = sellerService.querySellerDetailBySellerid(request.getSellerid());
			if(sellerDetailed!=null){
				result.put("isWifi", sellerDetailed.getIsWifi());//是否有wifi
				result.put("consume", sellerDetailed.getConsume());//人均消费
				result.put("isParking", sellerDetailed.getIsParking());//停车场
			}
			/**
			 * 查询商家图片 2封面图0环境图
			 */
			List<SellerPic> temPic = sellerService.querySellerPicBySelleridAndType(request.getSellerid(),null);
			List<SellerPic> pics = new ArrayList<>();
			if(temPic!=null&&temPic.size()>0){
				for(SellerPic pic : temPic)
				{
					if(pic.getType()==1){
						result.put("logo",pic.getUrl());
					}else{
						pics.add(pic);
					}
				}
			}
			result.put("pics",pics);
		
			/**
			 * 查询商家商圈信息
			 */
			Map<Object,Object> businessMap=sellerService.queryBusinessByZoneid(seller.getZoneid());
			if(businessMap!=null&&businessMap.get("title")!=null){
				result.put("zoneName", businessMap.get("title"));
			}
			/**
			 * 查询商家是否正在直播
			 */
			isLive(result,request.getSellerid());
		
			/**
			 * 查询用户离商家的距离
			 */
			SellerLandMark landMark = sellerService.querySellerLandMarkBySellerid(request.getSellerid());
			if(landMark!=null){
				result.put("longitude",landMark.getLongitude());
				result.put("latitude",landMark.getLatitude());
				if(request.getLatitude()!=null&&request.getLongitude()!=null){
					Long range =(long) distance(request.getLongitude(),request.getLatitude(),landMark.getLongitude(),landMark.getLatitude());
					if(range<1000){
						result.put("range", range+"m");
					}else{
						Long r = range/1000;
						result.put("range", r+"km");
					}
				}
			}
			/**
			 * 查询是否收藏
			 */
			if(uid.equals("")||uid.equals("null")){
				result.put("isCollect", 0);
			}else{
				int isCollect = ursService.isCollectSeller(Integer.parseInt(uid),request.getSellerid());
				if(isCollect > 0){
					result.put("isCollect", 1);
				}else{
					result.put("isCollect", 0);
				}
			}
			/**
			 * 查询消费人数
			 */
			consumeCount(result,request.getSellerid());
			/**
			 * 查询是否有活动
			 */
			List<Map<String, Object>> activityList = sellerService.queryActivityList(request.getSellerid());
			if(activityList!=null&&activityList.size()>0){
				result.put("isActivity", 1);
			}else{
				result.put("isActivity", 0);
			}
			
			 //标签
			if(Integer.parseInt(result.get("lable")==null?"0":result.get("lable")+"")==0){
				if(request.getLatitude()!=null&&request.getLongitude()!=null){
					try {
						double range=distance(request.getLongitude(),request.getLatitude(),landMark.getLongitude(),landMark.getLatitude());
						if(range <= 1000){
							result.put("lable",4);
						}else{
							if (activityList != null && activityList.size() > 0 ) {
								result.put("lable",5);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			//查询是否可买单
			//查询商家订单单日下单总额  限制消费
			result.put("isPay",1);
			try {
				Map<Object, Object> dayMap = billDao.queryDayOrderAmountBySellerId(Long.valueOf(request.getSellerid()));
				if (dayMap!=null) {
					if (dayMap.get("dayMoney")!=null && new BigDecimal(dayMap.get("dayMoney").toString()).compareTo(seller.getDailyLimitTurnover())>=0 ) {
						result.put("isPay",0);
					}
				}
				//查询商家订单单日下单总额    限制消费
				Map<Object, Object> totalMap = billDao.queryTotalOrderAmountBySellerId(Long.valueOf(request.getSellerid()));
				if (totalMap!=null) {
					if (totalMap.get("totalMoney")!=null && new BigDecimal(totalMap.get("totalMoney").toString()).compareTo(seller.getTotalLimitTurnover())>=0 ) {
						result.put("isPay",0);
					}
				}
			} catch (Exception e) {
				log.info("查询商家限额失败sellerid:"+request.getSellerid());
				e.printStackTrace();
			}
			
			//分享
			shareInfo(sellerid, result, seller.getSellername());
			
			
			/*===========v3.6版本增加网红点评列表(新写一个接口)，修改底部按钮显示  start============================================*/
			
			
			DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(""+sellerid);
			Integer haveDebitcard = 0;
			if(debitcardSeller!=null && debitcardSeller.getRechargeItem()!=null){
				String[] comboIds = debitcardSeller.getRechargeItem().split(",");
				//根据套餐id数组，查询商家是否有专享卡套餐
				Integer currentSellerDebitcard = userPayBirdCoinDao.queryDebitcardRechargeComboListByIds(comboIds);
				if(currentSellerDebitcard>0){
					haveDebitcard = 1;
				}
			}
			result.put("haveDebitcard", haveDebitcard);
			
			/*===========v3.6版本增加网红点评列表，修改底部按钮显示  end===================*/
			
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			mapResponse.setResponse(result);
			
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
	}

	/**
	 * 商家分享的一些信息
	 * @Title:shareInfo
	 * @Description:TODO
	 * @param sellerid
	 * @param result
	 * @param sellerName
	 * @throws IOException void
	 * 2017年6月5日下午5:51:21
	 */
	private void shareInfo(Integer sellerid, Map<Object, Object> result, String sellerName) throws IOException {
		String shareUrl = propertiesUtil.getValue("share.url", "conf_common.properties")+"?sellerid="+sellerid;
		String shareTitle = String.format(propertiesUtil.getValue("share.title", "conf_common.properties"), sellerName);
		String shareDesc = propertiesUtil.getValue("share.desc", "conf_common.properties");
		result.put("shareUrl",shareUrl);
		result.put("shareTitle",shareTitle);
		result.put("shareDesc",shareDesc);
		result.put("isAgio", propertiesUtil.getValue("isAgio", "conf_common.properties")) ;//订单是否有下单立减
		result.put("isIntegral", propertiesUtil.getValue("isIntegral", "conf_common.properties"));//订单是否有赠送积分
	}
	


	/**
	 * 描述 ：计算商家折扣多少
	 * 计算方式 ：商户折扣大于等于 0.95  (1 - 商户折扣) * 0.5    / 小于的 0.95 - 商户折扣
	 * @param  Map： sellerId ，  agio 
	 * @return String  折扣信息
	 * */
	public String calculationSellerDiscount(String baseagio){
		String rellayAgio = "0";
		
			BigDecimal agio = new BigDecimal(baseagio);
			if (agio.compareTo(new BigDecimal("0.95"))>=0) {
				//商户折扣大于等于 0.95  (1 - 商户折扣) * 0.5 
				rellayAgio = (new BigDecimal(1).subtract(agio)).multiply(new BigDecimal("0.5")).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
			}else {
				//小于的 0.95 - 商户折扣
				rellayAgio = new BigDecimal(0.95).subtract(agio).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
			}
		
		return rellayAgio;
	}
	
	/**
	 * 
	 * @Description: 查询店铺消费人数
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	public void consumeCount(Map<Object,Object> result,int sellerid){
		int consumeCount = 0;
		try {
			consumeCount = sellerService.consumeCount(sellerid);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询消费人数失败！");
		}
		result.put("consumeCount", consumeCount);
	}
	/**
	 * 
	 * @Description: 查询商家标签
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	public void lable(Map<Object,Object> result,int sellerid,int uid){
		int lable = 0;
		try {
			//是否消费过
			if(sellerService.billCountBySelleridAndUid(sellerid,uid)>0)
			{
				lable=1;
			}else
			{	//是否收藏
				if(ursService.isCollectSeller(uid,sellerid)>0)
				{
					lable=2;
				}else
				{	//是否浏览
					if(ursService.queryBrowsedCountByUidAndSellerid(uid,sellerid)>0)
					{
						lable=3;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("lable",lable);
	}
	
	/**
	 * 
	 * @Description: 查询是否有直播
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	public void isLive(Map<Object,Object> result,int sellerid){
		int isLive = 0;
		int isVod = 0;
		try {
			/**
			 * 查询商家正在直播记录的数量
			 */
			int count = sellerService.nowLiveCount(sellerid,1);
			if(count>0){
				isLive = 1;
				
			}
			/**
			 * 是否有回放记录
			 */
			int vodCount = sellerService.nowLiveCount(sellerid,3);
			if(vodCount>0){
				isVod = 1;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("isLive", isLive);
		result.put("isVod", isVod);
	}
	/** 
	 * 计算地球上任意两点(经纬度)距离 
	 *  
	 * @param long1 
	 *            第一点经度 
	 * @param lat1 
	 *            第一点纬度 
	 * @param long2 
	 *            第二点经度 
	 * @param lat2 
	 *            第二点纬度 
	 * @return 返回距离 单位：米 
	 */  
	public static double distance(double long1, double lat1, double long2,  
	        double lat2) {  
	    double a, b, R;  
	    R = 6378137; // 地球半径  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2  
	            * R  
	            * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                    * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}  
	public static void main(String[] args) {
		double s=distance(39.447143,116.269744,39.447143,116.269744);
		System.out.println(s);
	}
	

	/**
	 * 隐藏充值按钮
	 * @return
	 */
	private String hideDebitcardSellerButon(String sellerid){
		String buton = "0";//默认 关闭
		
		try{
			//获取商家是否配置储值卡，储值卡是否已经达到限额
			DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(sellerid);
			if (debitcardSeller!=null) {
				log.info("获取商家是否成功配置储值卡:是"+sellerid);
				//商户储值卡限额
				double limitRecharge = debitcardSeller.getTotalLimitRecharge();
				//获取商家储值卡目前充值金额
				Map<String, String> cardBalanceMap = new HashMap<String, String>();
				cardBalanceMap.put("sellerid", debitcardSeller.getSellerid().toString());
				Map<String, String> totalBalance = liveGiftsInfoService.getSellerCardTotalBalance(cardBalanceMap);
				double balance = Double.parseDouble(totalBalance.get(debitcardSeller.getSellerid().toString()).split(",")[0]) ;//
				if (balance<limitRecharge) {
					//返回参数隐藏 充值按钮
					buton="1";//开启 
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询商户当前充值限额额度异常");
		}
		log.info("当前返回状态："+buton);
		return buton;
	}
	

}
