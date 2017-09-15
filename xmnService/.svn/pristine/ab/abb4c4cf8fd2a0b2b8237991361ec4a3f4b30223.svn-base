package com.xmniao.xmn.core.api.controller.seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.seller.entity.Redpacket;
import com.xmniao.xmn.core.seller.entity.SellerCouponDetail;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.service.SellerService;


/**
 * 
*      
* 类名称：SellerPreActivityListApi   
* 类描述：   店铺详情优惠活动
* 创建人：xiaoxiong   
* 创建时间：2016年11月18日 下午2:08:32     
*
 */
@Controller
@RequestMapping("seller/detail/")
public class SellerDetailPreActivityApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(SellerDetailPreActivityApi.class);
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
	private String localDomain;
	
	@ResponseBody
	@RequestMapping("/actity")
	public Object actity(SelleridPageRequest request){
		log.info("SelleridRequest data:" + request.toString());
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
		SelleridPageRequest request= (SelleridPageRequest)object;
		Map<Object,Object> result = new HashMap<>();
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("page", 1);
			params.put("pageSize", 1);
			params.put("sellerid", request.getSellerid());
			//分享地址
			String url= propertiesUtil.getValue("share.url", "conf_config.properties");
			/**
			 * 查询商家优惠卷
			 */
			params.put("pageSize", 3);
			List<SellerCouponDetail> tempcouponList = sellerService.querySellerCoupon(params);
			List<SellerCouponDetail> couponList = new ArrayList<>();
			if(tempcouponList!=null&&tempcouponList.size()>0){
				for(SellerCouponDetail sellerCoDetail:tempcouponList){
					sellerCoDetail.setUrl(url+"/activitys/coupons?s_id="+sellerCoDetail.getSellerid()+"&codeType=99&c_type="+sellerCoDetail.getType()+"");
					couponList.add(sellerCoDetail);
				}
				
			}else{
				/*应急处理*/
				List<SellerCouponDetail> killList = sellerService.queryActivityKill(params);
				if(killList!=null&&killList.size()>0){
					for(SellerCouponDetail sellerCoDetail:killList){
						String killUrl=propertiesUtil.getValue("share.kill.url", "conf_common.properties");
						sellerCoDetail.setUrl(killUrl+"?act_id="+sellerCoDetail.getCid()+"&codeType=99&title="+sellerCoDetail.getCname()+"");
						couponList.add(sellerCoDetail);
					}
					
				}
				
			}
			result.put("coupon", couponList);
			/** 
			 * 查询是否有到店红包 type = 1
			 */
			params.put("pageSize", 1);
			params.put("flag", 1);
			List<Redpacket> redpachketList = sellerService.queryRedPacket(params);
			if(redpachketList!=null && redpachketList.size()>0){
				Redpacket redpacket = redpachketList.get(0); 
				redpacket.setUrl(url+"/activitys/red_packets?id="+redpacket.getId()+"&codeType=99");
				result.put("redPacket", redpacket);
			}
			/** 
			 * 查询是否有到店红包 type = 2
			 */
			params.put("type", 1);
			params.put("flag", 2);
			List<Redpacket> limtredpachketList = sellerService.queryRedPacket(params);
			if(limtredpachketList!=null && limtredpachketList.size()>0){
				Redpacket redpacket = limtredpachketList.get(0); 
				redpacket.setUrl(url+"/activitys/red_packets?id="+redpacket.getId()+"&codeType=99");
				redpacket.setImg(localDomain+"/images/red_paper@3x.png");
				result.put("limitRedPacket", redpacket);
			}
			
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			
			mapResponse.setResponse(result);
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
	}


}
