/**
 * 
 */
package com.xmn.saas.controller.h5.coupon;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.controller.api.v1.bill.vo.BillDetailRequest;
import com.xmn.saas.controller.api.v1.common.vo.ShareRequest;
import com.xmn.saas.controller.h5.coupon.vo.*;
import com.xmn.saas.entity.activity.Fullreduction;
import com.xmn.saas.entity.activity.FullreductionRecord;
import com.xmn.saas.entity.bill.Bill;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.entity.coupon.UserCoupon;
import com.xmn.saas.service.activity.FullreductionRecordService;
import com.xmn.saas.service.activity.FullreductionService;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.bill.BillService;
import com.xmn.saas.service.common.CommonService;
import com.xmn.saas.service.coupon.CouponService;
import com.xmn.saas.service.redpacket.RedpacketService;
import com.xmn.saas.service.user.UserService;
import com.xmn.saas.utils.CalendarUtil;
import com.xmn.saas.utils.NumberComputeUtil;
import com.xmn.saas.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   促销优惠-优惠券控制器
 * 创建人：huangk   
 * 创建时间：2016年9月26日 下午4:14:17      
 */
@Controller(value="h5-coupon-controller")
@RequestMapping("/h5/coupon")
public class CouponController extends AbstractController{

	private Logger log = LoggerFactory.getLogger(CouponController.class);
	
	/**
	 * 商户优惠券服务
	 */
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private GlobalConfig globalConfig;
	
	@Autowired
	private RedpacketService redpacketService;
	
	
	/**
	 * 满减活动服务
	 */
	@Autowired
	private FullreductionService fullreductionService;
	
	/**
	 * 满减活动用户领取记录服务
	 */
	@Autowired
	private FullreductionRecordService fullreductionRecordService;
	
	/**
	 * 账单服务
	 */
	@Autowired
	private BillService billService;
	
	/**
	 * 用户信息
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * 注入redis
	 */
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private GlobalConfig config;
	
	/**
	 * 促销优惠首页面
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
	public String coupon(String sessionToken) throws Exception{
		QueryCondition query = new QueryCondition();
		//获取商户id
		SellerAccount account = redisService.getSessionCacheObject(sessionToken, SellerAccount.class);//获取商户id
		if(account!=null){
			query.setSellerid(account.getSellerid());
			query.setStatus(0);
			//促销优惠列表
			List<SellerCouponDetail> couponList = couponService.queryCouponList(query);
			//没有数据则跳转促销优惠介绍页
			if(couponList==null||couponList.size()==0){
				return "/coupon/intraduce";
			}
		}
		WebUtils.getRequest().setAttribute("couponType", 0);
		return "/coupon/select-type";
	}
	
	/**
	 * 优惠券列表页
	 * @param status：0结束，1：进行中
	 * @throws Exception 
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public void list(int status,String type) throws Exception{
		QueryCondition query = new QueryCondition();
		query.setType(type);
		//获取商户id
		int sellerId = getSellerAccount().getSellerid();//获取商户id
		query.setSellerid(sellerId);
		query.setStatus(0);
		//商户进行中的优惠券活动列表
		if(status ==1){
			query.setStartDate(CalendarUtil.dateToString(new Date()));
		}
		//商户已结束的优惠券活动列表
		if(status ==0){
			query.setEndDate(CalendarUtil.dateToString(new Date()));
		}
		List<SellerCouponDetail> couponList = couponService.queryCouponList(query);
		List<CouponDetailResponse> reponselist = SellerCouponDetail.getResponseList(couponList);
		log.info("reponselist={}",reponselist!=null?reponselist.toString():null);
		Response response = new Response(0,"成功",reponselist);
		response.write();
	}
	
	/**
	 * 满减活动列表页
	 * @throws Exception 
	 */
	@RequestMapping(value = "/list_fullcut",method = RequestMethod.GET)
	@ResponseBody
	public void listFullcut(int status) throws Exception{
		QueryCondition query = new QueryCondition();
		//获取商户id
		int sellerId = getSellerAccount().getSellerid();//获取商户id
		query.setSellerid(sellerId);
		//进行中的满减活动
		if(status ==1){
			query.setStartDate(CalendarUtil.dateToString(new Date()));
		}
		//已结束的满减活动
		if(status ==0){
			query.setEndDate(CalendarUtil.dateToString(new Date()));
		}
		//商户满减活动列表
		List<Fullreduction> fullcutList = fullreductionService.queryListBySerllIdAndDate(query);
		//数据格式转换
		List<FullreductionResponse> respList = Fullreduction.getResponseList(fullcutList);
		log.info("fullcutList={}",respList!=null?respList.toString():null);
		Response response = new Response(0,"",respList);
		response.write();
	}
	
	/**
	 * 保存现金抵用券
	 * @param CashCouponSaveRequest
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save_cash",method = RequestMethod.POST)
	@ResponseBody
	public void saveCash(@Valid CashCouponSaveRequest request) throws Exception{
		log.info("saveCash start.param:{}", request);
		try{
			int sellerId = getSellerAccount().getSellerid();//获取商户id
			SellerCouponDetail coupon = request.convertRequestToBean();
			coupon.setSellerid(sellerId);
			//保存现金抵用券信息
			int cid = couponService.saveSellerCoupon(coupon);
			Map<String,Object> cashInfo = new HashMap<String,Object>();
			
			ShareRequest reqParams =new ShareRequest();
			reqParams.setId(cid);//活动id
			reqParams.setTitle(coupon.getCname());//标题
			reqParams.setType(6);//现金抵用券类型
			//请求获取分享的地址
			String url = commonService.getShareUrl(reqParams);
			cashInfo.put("url",url);
			cashInfo.put("cid",cid);
			cashInfo.put("condition", coupon.getAwardCondition());//限制条件
			Response response = new Response(0,"保存成功",cashInfo);
			response.write();
		}catch(Exception e){
			log.error("saveCash occured error={}",e);
		}
	}
	
	/**
	 * 保存赠品券信息
	 * @param GiftCouponSaveRequest
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save_gift",method = RequestMethod.POST)
	@ResponseBody
	public void saveGift(@Valid GiftCouponSaveRequest request) throws Exception{
		log.info("saveGift start.parameter:{}", request);
		try{
			int sellerId = getSellerAccount().getSellerid();//获取商户id
			SellerCouponDetail coupon = request.convertRequestToBean();
			coupon.setSellerid(sellerId);
			//保存赠品券信息
			Integer cid=couponService.saveSellerCoupon(coupon);
			HashMap<String,Object> map = new HashMap<String,Object>();
			ShareRequest reqParams =new ShareRequest();
            reqParams.setId(cid);//活动id
            reqParams.setTitle(coupon.getCname());//标题
            reqParams.setType(7);//赠品券类型
            //请求获取分享的地址
            String url = commonService.getShareUrl(reqParams);
            map.put("url",url);
            map.put("cid",cid);
            map.put("condition", coupon.getAwardCondition());//限制条件
			Response response = new Response(0,"保存成功！",map);
			response.write();
		}catch(Exception e){
			log.error("saveGift occured error={}",e);
		}
	}
	
	/**
	 * 保存满减促销优惠信息
	 * @param GiftCouponSaveRequest
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save_fullfeduction",method = RequestMethod.POST)
	@ResponseBody
	public void saveFullReduction(@Valid FullreductionSaveRequest request) {
		log.info("saveFullReduction start.parameter:{}", request);
		Response response = null;
		try{
			int sellerId = getSellerAccount().getSellerid();//获取商户id
			Fullreduction fullreduction = request.convertRequestToBean();
			fullreduction.setSellerid(sellerId);
			//保存满减活动信息
			int cid = fullreductionService.insert(fullreduction);
			HashMap<String,Object> map = new HashMap<String,Object>();
			ShareRequest reqParams =new ShareRequest();
            reqParams.setId(cid);//活动id
            reqParams.setTitle(fullreduction.getName());//标题
            reqParams.setType(8);//赠品券类型
            //请求获取分享的地址
            String url = commonService.getShareUrl(reqParams);
            map.put("url",url);
			response = new Response(0,"保存成功!",map);
			response.write();
		}catch(Exception e){
			log.error("saveFullReduction occured error={}",e);
		}
	}
	
	/**
	 * 优惠券统计明细
	 * @param couponId
	 * @throws Exception 
	 */
	public CouponDetailResponse detail(int couponId) throws Exception{
		//获取优惠券基本信息
		SellerCouponDetail coupon = couponService.querySellerCouponById(couponId);
		log.info("view coupon baseInfo={}", coupon);
		//统计优惠活动详情
		Date sdate=CalendarUtil.dateFormat(coupon.getStartDate());
		int passedDays = CalendarUtil.startDay(sdate);
		Date edate=CalendarUtil.dateFormat(coupon.getEndDate());
		int lastDays = CalendarUtil.endDay(edate);
		CouponDetailResponse respvo = new CouponDetailResponse();
		//获取店铺活动期间刺激消费金额
		BigDecimal activeAmount = commonService.getActiveAmount(
				coupon.getSellerid(), CalendarUtil.dateFormat(coupon.getStartDate()), CalendarUtil.dateFormat(coupon.getEndDate()));
		respvo.setSellerId(getSellerAccount().getSellerid());
		respvo.setActiveAmount((activeAmount.doubleValue()<0?0.00:activeAmount.doubleValue())+"");//刺激消费的金额
		respvo.setAwardNum(coupon.getAwardNumber());
		respvo.setNewUserNum(couponService.countNewuer(couponId));
		respvo.setViewNum(coupon.getViews()!=null?coupon.getViews():0);
		respvo.setPassedDays(passedDays);
		respvo.setLastDays(lastDays);
		respvo.setCname(coupon.getCname());
		respvo.setStartTime(coupon.getStartDate().substring(0,16));
		respvo.setEndTime(coupon.getEndDate().substring(0,16));
		respvo.setSendNum(coupon.getSendNum());
		respvo.setAwardCondition(coupon.getAwardCondition().toString());
		respvo.setLimitNumber(coupon.getLimitNumber().toString());
		respvo.setDescription(coupon.getDescription());
		respvo.setMaximum(coupon.getMaximum());
		//现金抵用券
		respvo.setUseCondition(coupon.getConditions()!=null?coupon.getConditions().toString():null);
		respvo.setDenomination(coupon.getDenomination().toString());
		respvo.setPayAndConsume(coupon.getLimitAmount().toString());
		log.info("view coupon Response={}", coupon);
		return respvo;
	}
	
	/**
	 * 满减活动统计明细
	 * @param id 满减活动id
	 * @throws Exception 
	 */
	public FullreductionResponse detailFullreduction(int id) throws Exception{
		//获取满减活动基本信息
		Fullreduction fullreduction = fullreductionService.selectByPrimaryKey(id);
		log.info("view fullreduction baseInfo={}", fullreduction);
		//统计满减活动详情
		FullreductionResponse response = new FullreductionResponse();
		response.setCname(fullreduction.getName());
		int passedDays = CalendarUtil.distanceDay(fullreduction.getBeginDate());
		int lastDays = CalendarUtil.distanceDay(fullreduction.getEndDate());
		response.setSellerId(getSellerAccount().getSellerid());
		response.setPassedDays(passedDays);
		response.setLastDays(lastDays);
		response.setJoinNum(fullreduction.getJoinNumber());
		BigDecimal activeAmount = commonService.getActiveAmount(
				fullreduction.getSellerid(), CalendarUtil.dateFormat(fullreduction.getBeginDate()), CalendarUtil.dateFormat(fullreduction.getEndDate()));
		response.setActiveAmount((activeAmount.doubleValue()<0?0.00:activeAmount.doubleValue())+"");
		response.setNewUserNum(fullreductionRecordService.countNewuserByAid(id));//统计新用户数
		response.setViewNum(fullreduction.getViews());
		response.setStartTime(fullreduction.getBeginDate().substring(0,16));
		response.setEndTime(fullreduction.getEndDate().substring(0,16));
		response.setLimitNumber(fullreduction.getLimitNumber());
		response.setConsumeAndPay(fullreduction.getConsumeAndPay());
		response.setOffsetAmount(fullreduction.getOffsetAmount());
		response.setIsDerate(fullreduction.getIsDerate());
		//返回三级减免金额规则
		if(fullreduction.getIsDerate()==1){
			response.setDerateLevel1Amount(NumberComputeUtil.trimZero(
					fullreduction.getDerateLevel1Amount().toString()));
			response.setConsumeAndPay1(NumberComputeUtil.trimZero(
					fullreduction.getConsumeAndPay1().toString()));
			response.setDerateLevel2Amount(NumberComputeUtil.trimZero(
					fullreduction.getDerateLevel2Amount().toString()));
			response.setConsumeAndPay2(NumberComputeUtil.trimZero(
					fullreduction.getConsumeAndPay2().toString()));
			response.setDerateLevel3Amount(NumberComputeUtil.trimZero(
					fullreduction.getDerateLevel3Amount().toString()));
			response.setConsumeAndPay3(NumberComputeUtil.trimZero(
					fullreduction.getConsumeAndPay3().toString()));
		}
		log.info("view fullreduction response={}", response);
		return response;
	}
	
	/**
	 * 满减用户领取列表
	 * @param int fid 满减活动id
	 * @throws Exception 
	 */
	@RequestMapping(value = "/award_fullreduction_list",method = RequestMethod.GET)
	@ResponseBody
	public void awardFullreducttionList(int aid) {
		Response response = null;
		//领取列表
		try{
			List<Map<String,Object>> recordList = fullreductionRecordService.queryListByAid(aid);
			log.info("awardFullreducttionList result={}", recordList);
			response = new Response(0,"",recordList);
			response.write();
		}catch(Exception e){
			log.error("Load awardFullreducttionList occured error={}",e);
		}
		
	}
	
	/**
	 * 优惠券用户领取列表
	 * @param couponId 优惠券id
	 * @throws Exception 
	 */
	@RequestMapping(value = "/award_list",method = RequestMethod.GET)
	@ResponseBody
	public void awardList(String couponId,Integer sellerId) {
		try{
			//领取列表
			List<Map<String,Object>> couponList = couponService.
					queryUserCouponListByCid(Integer.valueOf(couponId),sellerId);
			log.info("Coupon awardList result={}", couponList);
			Response response = new Response(0,"",couponList);
			response.write();
		}catch(Exception e){
			log.error("awardList occured error={}",e);
		}
	}
	
	/**
	 * 优惠券用户领取详情
	 * @param recordId 用户领取记录id
	 * @throws Exception 
	 */
	@RequestMapping(value = "/award_detail",method = RequestMethod.GET)
	@ResponseBody
	public void awardDetail(int recordId) throws Exception{
		//用户优惠券详情
		UserCoupon coupon = couponService.queryUserCouponById(recordId);
		log.info("Coupon awardDetail result={}", coupon);
		Response response = new Response(0,"",coupon);
		response.write();
	}
	
	/**
	 * 终止促销优惠活动
	 * @param idd 活动id
	 * @throws Exception 
	 */
	@RequestMapping(value = "/stop")
	@ResponseBody
	public void stop(int id,int type) throws Exception{
		Response response = new Response(0,"执行成功！");
		switch(type){
			case 6:
				SellerCouponDetail cash = couponService.querySellerCouponById(id);
				//活动结束时间大于当前时间，并且正在运营中的
				if(CalendarUtil.dateFormat(cash.getEndDate()).getTime() >= new Date().getTime()&&
						cash.getSendStatus()!=3){
					cash.setEndDate(CalendarUtil.stringFormat(new Date()));
					cash.setSendStatus(3);
					couponService.updateSellerCoupon(cash);
					response = new Response(0,"执行成功！");
				}else{
					if(CalendarUtil.dateFormat(cash.getEndDate()).getTime()<=new Date().getTime()){
						response = new Response(1,"该现金抵用券活动已结束！");
					}
					if(CalendarUtil.dateFormat(cash.getStartDate()).getTime() >new Date().getTime()){
						response = new Response(1,"该现金抵用券活动还未开始！");
					}
				}
				break;
			case 7:
				SellerCouponDetail freebie = couponService.querySellerCouponById(id);
				if(CalendarUtil.dateFormat(freebie.getEndDate()).getTime()>= new Date().getTime()&&
						freebie.getSendStatus()!=3){
					freebie.setEndDate(CalendarUtil.stringFormat(new Date()));
					freebie.setSendStatus(3);
					couponService.updateSellerCoupon(freebie);
					response = new Response(0,"执行成功！");
				}else{
					if(CalendarUtil.dateFormat(freebie.getEndDate()).getTime() < new Date().getTime()){
						response = new Response(1,"该赠品券活动已结束！");
					}
				}
//				if(freebie.getStartDate().getTime()>new Date().getTime()){
//					response = new Response(1,"该赠品券活动还未开始！");
//				}
				break;
			case 8:
				Fullreduction fullreduction = fullreductionService.selectByPrimaryKey(id);
				if(CalendarUtil.dateFormat(fullreduction.getEndDate()).getTime()>=
					new Date().getTime()&&fullreduction.getStatus()==0){
					fullreduction.setEndDate(CalendarUtil.stringFormat(new Date()));
					fullreduction.setStatus(1);
					fullreductionService.updateByPrimaryKey(fullreduction);
					response = new Response(0,"执行成功！");
				}else{
					if(CalendarUtil.dateFormat(fullreduction.getEndDate()).getTime()<=
							new Date().getTime()){
							response = new Response(1,"该赠品券活动已结束！");
					}
				}
				break;
		}
		response.write();
	}
	
	/**
	 * 获取商户信息
	 * @return String 获取商户信息
	 */
	private SellerAccount getSellerAccount(){
		String sessionToken = getCookieToken();
		//String sessionToken = "4557c64d3b944946abf4df961679153f";
		SellerAccount sellerAccount=redisService.getSessionCacheObject(sessionToken, SellerAccount.class);
		return sellerAccount;
	}
	
	/**
	 * 选择优惠券列表页
	 */
	@RequestMapping(value = "/select")
	public String selectCoupon(int couponType){
		WebUtils.getRequest().setAttribute("type", couponType);
		String view = "";
		switch (couponType) {
			case 3:
				view = "/coupon/list-cash";// 现金抵用券视图
				break;
			case 4:
				view = "/coupon/list-freebie";// 赠品券视图 
				break;
			case 5:
				view = "/coupon/list-fullcut";// 满减视图
				break;
			default:
					view = "/coupon/select-type";
		}
		return view;
	}
	
	/**
	 * 创建优惠券入口
	 * @param int couponType 优惠券类型
	 * @param couponType 3：现金抵用券 4：赠品券
	 * @param adlistUrl huodong奖品列表url
	 * @throws Exception
	 */
	@RequestMapping(value = "/input",  method = RequestMethod.GET)
	public String input(int couponType,String url) throws Exception{
		WebUtils.getRequest().setAttribute("adlistUrl", url);
		WebUtils.getRequest().setAttribute("sellerId", 
				getSellerAccount().getSellerid());//获取商户id
		String view = "";
		switch (couponType) {
			case 3:
				view = "/coupon/input-cash";// 现金抵用券视图
				break;
			case 4:
				view = "/coupon/input-freebie";// 赠品券视图
				break;
			case 5:
				view = "/coupon/input-fullcut";// 满减视图
				break;
			default:
				view = "/coupon/input-cash";// 现金抵用券视图
				break;
		}
		return view;
	}
	
	/**
	 * 添加成功后的活动预览页面
	 * @param type 活动类型
	 * @param id 活动id
	 */
	@RequestMapping(value = "/share")
	public String share(int type, int id, HttpServletRequest request){
		String downloadUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		request.setAttribute("downloadUrl", downloadUrl);
		request.setAttribute("type", type);
		request.setAttribute("id", id);
		request.setAttribute("sellerId", getSellerAccount().getSellerid());
		return "/coupon/share";
	}
	
	/**
	 * 数据详情页面
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/detail")
	public String predetail(String couponId,int type,HttpServletRequest request) throws Exception{
		request.setAttribute("id", couponId);
		request.setAttribute("type", type);
		String downloadUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		request.setAttribute("downloadUrl", downloadUrl);
		try{
			switch(type){
				case 6://现金抵用券
					CouponDetailResponse detailCash = detail(Integer.valueOf(couponId));
					if(detailCash.getEndTime().compareTo(CalendarUtil.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"))<0){
					    request.setAttribute("isShow", 1);//不展示下载物料和终止活动按钮
					}
					request.setAttribute("detailData", detailCash);
					break;
				case 7://赠品券
					CouponDetailResponse detailData = detail(Integer.valueOf(couponId));
					if(detailData.getEndTime().compareTo(CalendarUtil.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"))<0){
                        request.setAttribute("isShow", 1);//不展示下载物料和终止活动按钮
                    }
					request.setAttribute("detailData", detailData);
					break;
				case 8:// 满减送
					FullreductionResponse fullreductionDetailData1 = detailFullreduction(Integer.valueOf(couponId));
					if(fullreductionDetailData1.getEndTime().compareTo(CalendarUtil.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"))<0){
                        request.setAttribute("isShow", 1);//不展示下载物料和终止活动按钮
                    }
					request.setAttribute("detailData", fullreductionDetailData1);
					break;
				default:
					request.setAttribute("info","活动类型错误!");
					return "/coupon/error";
			}
		}catch(Exception e){
			log.error("predetail occured error={}", e);
		}
		return "/coupon/detail";//数据详情
	}

	/**
	 * 预览明细页面
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preview")
	public String preview(String couponId,int type) throws Exception{
		WebUtils.getRequest().setAttribute("couponId", couponId);
		WebUtils.getRequest().setAttribute("type", type);
		CouponDetailResponse coupon = new CouponDetailResponse();//赠品券、现金抵用券
		FullreductionResponse fullreduction = new FullreductionResponse();//满就减
		String view = "";
		try{
			switch (type) {
			// 现金抵用券、赠品券、满减预览明细视图
			case 3:
			case 4:
				coupon = detail(Integer.valueOf(couponId));
				WebUtils.getRequest().setAttribute("condition", coupon.getAwardCondition());//领取条件
				WebUtils.getRequest().setAttribute("coupon", coupon);
				WebUtils.getRequest().setAttribute("title", coupon.getCname());//标题
				view = "/coupon/preview";
				break;
			case 5:
				fullreduction = detailFullreduction(Integer.valueOf(couponId));
				WebUtils.getRequest().setAttribute("fullreduction", fullreduction);
				WebUtils.getRequest().setAttribute("title", fullreduction.getCname());//标题
				view = "/coupon/preview";
				break;
			// 现金抵用券、赠品券、满减用户领取列表视图
			case 6:
			case 7:
				coupon = detail(Integer.valueOf(couponId));
				view = "/coupon/list-record";
				if(coupon==null){
					return view;
				}

				WebUtils.getRequest().setAttribute("cname", coupon.getCname());
				WebUtils.getRequest().setAttribute("sellerId", coupon.getSellerId());
				WebUtils.getRequest().setAttribute("sendNum", coupon.getMaximum());//发放的数量
				WebUtils.getRequest().setAttribute("lastNum", coupon.getMaximum()-coupon.getSendNum());//剩余数量
				break;
			case 8:
				fullreduction = detailFullreduction(Integer.valueOf(couponId));
				view = "/coupon/list-record";// 现金抵用券、赠品券用户领取列表视图
				if(fullreduction==null){
					return view;
				}
				WebUtils.getRequest().setAttribute("cname", fullreduction.getCname());
				//通过减免记录表统计返回满减活动期间总的减免金额
				BigDecimal reductionAmount = fullreductionRecordService.countReductionAmountByAid(Integer.valueOf(couponId));
				WebUtils.getRequest().setAttribute("reductionAmount",reductionAmount);
				//参与人数
				WebUtils.getRequest().setAttribute("joinNum", fullreductionService.countJoinNum(Integer.valueOf(couponId)));
				break;
		}
		}catch(Exception e){
			log.error("preview occured error={}", e);
		}
		return view;
	}
	
	/**
	 * 促销优惠、满减交易详细页面
	 * @throws Exception 
	 */
	@RequestMapping(value = "/detail_order")
	public String orderDetail(int recordId,int type,Integer sellerId) throws Exception{
		WebUtils.getRequest().setAttribute("recordId", recordId);
		WebUtils.getRequest().setAttribute("type", type);
		OrderDetailResponse order = new OrderDetailResponse();//订单详情
		try{
			switch (type) {
			case 6:
			case 7://现金抵用券、赠品券订单支出详情
				UserCoupon coupon = couponService.queryUserCouponById(recordId);//优惠券基本信息
				//User user = userService.findUserByPrimaryKey(Long.parseLong(coupon.getUid()+""));//查询用户信息
				Map<String,String> params = new HashMap<>();
				params.put("uid", coupon.getUid()+"");
				ResponseData responseData = redpacketService.getUserMsg(params);
				if(responseData!=null && responseData.getState()==0){
				   String nname=  responseData.getResultMap().get("nname");
				   String avatar=  responseData.getResultMap().get("avatar");
				   String genussellerid=  responseData.getResultMap().get("genussellerid");
				   order.setIsBind(genussellerid.equals(sellerId+"")?0:1);//是否绑定会员
				   if(StringUtils.isNotBlank(avatar)){
				       order.setAvatar( globalConfig.getImageHost()+avatar);//用户头像
				   }
				   String phone=  responseData.getResultMap().get("phone");
				   if(StringUtils.isNotBlank(nname)){
				       order.setUserName(nname);//用户名
				   }else{
				       order.setUserName(StringUtils.isNotBlank(phone)?phone:"匿名");//用户名
				   }
				}
				order.setAwardTime(CalendarUtil.dateFormat(coupon.getGetTime(),CalendarUtil.FORMAT1));//领取时间
				order.setUseTime(CalendarUtil.dateFormat(coupon.getUseTime(),CalendarUtil.FORMAT1));//使用时间
				order.setOrderId(coupon.getBid());//订单号
				order.setSerialNo(coupon.getSerialNo());//优惠券序列码
				order.setIsShare(coupon.getIsShare()!=null?coupon.getIsShare():0);//是否分享
				order.setIsaccount(coupon.getIsVerify()!=null?coupon.getIsVerify():0);//是否核销
				order.setGetWay(coupon.getGetWay());//获取方式
				break;
			case 8://满减活动订单支出详情
				FullreductionRecord bean = fullreductionRecordService.queryById(recordId);//满减活动信息
				BillDetailRequest request = new BillDetailRequest();
				request.setBid(bean.getBid().toString());//订单号
				request.setSearchType(1);//查询营收
				/*查询账单信息*/
				SellerAccount sellerAccount = getSellerAccount();
				Map<Object,Object> detail = billService.detail(request.converToBean(sellerAccount, request));
				order.setUserName(detail.get("nname").toString());
				order.setPaytype(detail.get("payType").toString());
				order.setAmount((BigDecimal)detail.get("money"));
				Bill bill = billService.selectBillByBid(bean.getBid()+"");
				if(bill.getReduction()==null){
				    bill.setReduction(BigDecimal.ZERO);
				}
				order.setReduction(bill.getReduction());
				BigDecimal realPay = (BigDecimal.valueOf(Double.valueOf(detail.get("money").toString()))).subtract(BigDecimal.valueOf(Double.valueOf(bill.getReduction().toString())));
				order.setRealPay(realPay);
				order.setRebate(BigDecimal.valueOf(Double.valueOf(detail.get("rebate").toString())));
				order.setRealIncome(BigDecimal.valueOf(Double.valueOf(detail.get("rebate").toString())));
				order.setStatus(detail.get("status").toString());
				order.setPaytime(CalendarUtil.dateFormat(bean.getGetTime(),CalendarUtil.FORMAT1));
				order.setOrderId(bean.getBid());//订单号
				order.setCodeid(detail.get("codeid").toString());
				break;
		}
		}catch(Exception e){
			log.error("orderDetail occured error={}", e);
		}
		WebUtils.getRequest().setAttribute("order", order);
		return "/coupon/detail-order";
	}
	
	
	

    /**
     * 预览页面
     * @param CashCouponSaveRequest
     * @throws Exception 
     */
    @RequestMapping(value = "/view_activity",method = RequestMethod.POST)
    @ResponseBody
    public void viewActivity(String title,Integer id,Integer type) throws Exception{
        try{
            ShareRequest reqParams =new ShareRequest();
            reqParams.setId(id);//活动id
            reqParams.setTitle(title);//标题
            if(type==3){
                reqParams.setType(6);//现金抵用券类型
            }else if(type==4){
                reqParams.setType(7);//赠品券
            }else if(type==5){
                reqParams.setType(8);//满就减
            }
            //请求获取分享的地址
            String url = commonService.getShareUrl(reqParams);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("url",url);
            Response response = new Response(0,"获取预览地址成功",map);
            response.write();
        }catch(Exception e){
            log.error("saveCash occured error={}",e);
        }
    }
	
}

