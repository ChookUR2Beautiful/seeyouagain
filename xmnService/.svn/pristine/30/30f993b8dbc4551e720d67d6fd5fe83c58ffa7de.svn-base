package com.xmniao.xmn.core.xmer.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SaasOrderRequest;
import com.xmniao.xmn.core.common.request.SignPayConfirmRequest;
import com.xmniao.xmn.core.live.service.AnchorPersonService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EnumUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.SaasPackageDao;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import com.xmniao.xmn.core.xmer.entity.SaasOrder;
import com.xmniao.xmn.core.xmer.entity.SaasPackage;

/**
 *@ClassName:BuySaasPackageService
 *@Description:创建SaaS套餐订单
 *@author hls
 *@date:2016年5月20日下午2:31:19
 */
@Service
public class BuySaasPackageService {

	
	//日志
	private final Logger log = Logger.getLogger(BuySaasPackageService.class);
	
	/**
	 * 注入saasPackageDao
	 */
	@Autowired
	private SaasPackageDao saasPackageDao;
	
	/**
	 * 注入saasOrderDao
	 */
	@Autowired
	private SaasOrderDao saasOrderDao;
	
	/**
	 * 注入paySecret
	 */
	@Autowired
	private String paySecret;
	
	/**
	 * 注入redis
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入会员关系链dao
	 */
	@Autowired
	private UrsEarningsRelationDao ursEarningsRelationDao;
	
	/**
	 * 
	* @Title: createSaasOder
	* @Description: 直接创建寻蜜客，非分享
	* @return Object
	 */
	public Object createSaasOder(SaasOrderRequest saasOrderRequest) {
		try {
			//获取uid
			String token = saasOrderRequest.getSessiontoken();
			String uid =  sessionTokenService.getStringForValue(token)+"";
			
			//查询saas套餐列表信息
			SaasPackage sp = saasPackageDao.selectSaasPackage(saasOrderRequest.getId());
			
			//saas套餐订单请求参数
			SaasOrder so = new SaasOrder();
			String sbid = "01"+SaasBidType.getBid();
			so.setOrdersn(sbid);
			so.setDpid(sp.getId());
			so.setXmerid(Integer.parseInt(uid));
			so.setAmount(sp.getPrice());
			so.setStatus(0);	//未支付
			so.setSdate(DateUtil.now());//创建时间
			so.setPayType(saasOrderRequest.getType());
			so.setNum(sp.getNums());
			so.setAgio(sp.getAgio());//套餐折扣
			so.setSoldnums(0);//卖出数量
			so.setStock(sp.getNums());//库存数量
//			so.setParentid(saasOrderRequest.getParentid());//邀请人的id
			so.setZdate(DateUtil.now());//支付时间
			so.setAppSourceStatus(EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, saasOrderRequest.getAppSource()));
			//计算每一个单价
			Double danjia = ArithUtil.div(ArithUtil.mul(sp.getPrice(), sp.getAgio()), sp.getNums(), 0);
			so.setPrice(danjia);
			
			//V3.6.0版本，会员关系链迁移，需从 b_urs_earnings_relation表中查询，将上级关系链存入t_saas_order表中，若没有上级则不存
			String uidRelationChain = queryRelationChain(uid);
			
			if(!StringUtils.isEmpty(uidRelationChain) && !"null".equals(uidRelationChain) && uidRelationChain.contains(",")){	//有','号， 表示存在上级
				uidRelationChain = uidRelationChain.substring(0, uidRelationChain.lastIndexOf(","));
				so.setUidRelationChain(uidRelationChain);
			}
			so.setSaas_channel(1);
			
			//创建购买saas套餐订单
			int result = saasOrderDao.addSaasOrder(so);
			if(result == 0){
				return new BaseResponse(ResponseCode.SUCCESS,"创建订单失败");
			}
			
			//响应
			MapResponse br = new MapResponse(ResponseCode.SUCCESS, "创建saas押金套餐订单成功");
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("orderid",sbid);
			resultMap.put("uid", uid);
			
			//获取打折后的套餐金额
			BigDecimal acount = new BigDecimal(sp.getPrice()*sp.getAgio());
			resultMap.put("amount", acount.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
			//resultMap.put("amount", "0.01D");
			
			resultMap.put("source", "001");
			String sign = transMap(resultMap);
			resultMap.put("sign", sign);
			br.setResponse(resultMap);
			return br;
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "创建saas押金套餐订单失败");
		}
	}
	
	/**
	 * 
	* @Title: addSoldOrder
	* @Description: 支付订单的时候创建订单   分享微信创建寻蜜客的购买saas套餐
	* @return Object    返回类型
	* @author liuzhihao
	* @throws
	 */
	public Object addSoldOrder(SignPayConfirmRequest signPayConfirmRequest){
		try{
			//验证uid
			String uid = sessionTokenService.getStringForValue(signPayConfirmRequest.getSessiontoken()).toString();
			if(StringUtils.isEmpty(uid)){
				return new BaseResponse(ResponseCode.FAILURE, "用户不存在");
			}
			//saas套餐订单请求参数
			SaasPackage sp = saasPackageDao.selectSaasPackage(signPayConfirmRequest.getId());
			if(sp==null){
				return new BaseResponse(ResponseCode.FAILURE, "没有找到套餐信息");
			}
			
			SaasOrder so = new SaasOrder();
			String sbid = "01"+SaasBidType.getBid();
			so.setOrdersn(sbid);
			so.setDpid(sp.getId());
			so.setXmerid(Integer.parseInt(uid));
			so.setAmount(sp.getPrice());
			so.setStatus(0);
			so.setSdate(DateUtil.now());
			so.setPayType(1000003);
			so.setNum(sp.getNums());
			so.setAgio(sp.getAgio());//套餐折扣
			so.setSoldnums(0);//卖出数量
			so.setStock(sp.getNums());//库存数量
			
			if(signPayConfirmRequest.getOtherPay()!=null)so.setOtherPay(signPayConfirmRequest.getOtherPay());//是否是代付
			if(signPayConfirmRequest.getOtherTel()!=null)so.setOtherTel(signPayConfirmRequest.getOtherTel());//代付手机号码
			if(signPayConfirmRequest.getOpenid()!=null)so.setOpenid(signPayConfirmRequest.getOpenid());//openid
			so.setZdate(DateUtil.now());//支付时间
			System.out.println(signPayConfirmRequest.getOpenid());
			
			//计算每一个单价
			Double danjia = ArithUtil.div(ArithUtil.mul(sp.getPrice(), sp.getAgio()), sp.getNums(), 0);
			so.setPrice(danjia);
			
			//V3.6.0版本，会员关系链迁移，需从 b_urs_earnings_relation表中查询，存入t_saas_order表中
			
			if (null!=signPayConfirmRequest.getParentid() ) {
				so.setParentid(signPayConfirmRequest.getParentid());//邀请人的id
				String uidRelationChain = queryRelationChain(signPayConfirmRequest.getParentid().toString());
				so.setUidRelationChain(uidRelationChain);
			}
			
			so.setSaas_channel(1);
			
			//创建购买saas套餐订单
			saasOrderDao.addSaasOrder(so);
			
			//响应
			MapResponse br = new MapResponse(ResponseCode.SUCCESS, "创建saas押金套餐订单成功");
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("orderid",sbid);
			resultMap.put("uid", uid);
			
			//获取每个套餐每个店铺的单价金额
			BigDecimal acount = new BigDecimal(sp.getPrice()*sp.getAgio());
			resultMap.put("amount", acount.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
			//resultMap.put("amount", "0.01D");
			
			resultMap.put("source", "001");
			String sign = transMap(resultMap);
			resultMap.put("sign", sign);
			br.setResponse(resultMap);
			return br;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "创建saas押金套餐订单失败");
		}
	}
	
	/**
	 * 
	* @Title: transMap
	* @Description: 换map类型
	* @return String    返回类型
	* @author
	* @throws
	 */
	public String transMap(Map<Object,Object> map){
		Map<String,String> transmap = new HashMap<String,String>();
		transmap.put("orderid", map.get("orderid").toString());
		transmap.put("uid", map.get("uid").toString());
		transmap.put("amount", map.get("amount").toString());
		transmap.put("source", map.get("source").toString());
		String md5Map = Signature.sign(transmap, paySecret);
		return md5Map;
	}
	
	/**
	 * 
	 * @Title:queryRelationChain
	 * @Description:会员关系链迁移，创建订单的时候需要从  b_urs_earnings_relation 表中查询 寻蜜客关系链 uid_relation_chain 并存到t_saas_order表中
	 * @param uid 寻蜜客uid
	 * @return String
	 * 2017年5月3日下午2:42:46
	 */
	public String queryRelationChain(String uid){
		return ursEarningsRelationDao.queryRelationChain(uid);
	}
}
