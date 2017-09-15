package com.xmniao.xmn.core.api.controller.seller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.SelleridRequest;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;
import com.xmniao.xmn.core.live.service.LiveUserService;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.xmer.service.SellerService;


/**
*      
* 类名称：SellerNowLiveApi   
* 类描述：   查询商家正在直播的记录
* 创建人：xiaoxiong   
* 创建时间：2016年11月16日 下午5:39:15     
*
 */
@Controller
@RequestMapping("seller")
public class SellerNowLiveApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(SellerNowLiveApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private AnchorLiveRecordService anchorLiveRecordService;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private LiveUserService liveUserService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private KSCloudService ksCloudService;
	
	@ResponseBody
	@RequestMapping("/nowLive")
	public Object nowLive(SelleridRequest request){
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
	
	/**
	 * @Description: 查询商家正在直播的记录
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	private Object versionControlOne(Object object) {
		
		SelleridRequest request = (SelleridRequest) object ;
		Map<Object,Object> result = new HashMap<Object, Object>();
		try {
			/**
			 * 查询商家正在直播的列表
			 */
			List<LiveRecordInfo> liveList = anchorLiveRecordService.liveListBySellerid(request);
			if(liveList != null  && liveList.size()>=0){
				List<Map<String,Object>> resultLiveList = liveList(liveList,request.getSellerid());
				result.put("liveList", resultLiveList);
			}
			
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			mapResponse.setResponse(result);
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
		
	}
	
	/**
	 * 
	 * @Description: 根据返回商家回放记录数据
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	private List<Map<String,Object>> liveList(List<LiveRecordInfo> liveList,int sellerid) {
		
		
		List<Map<String,Object>> resultliveList = new ArrayList<>();
			
		LiveRecordInfo liveRecordInfo = null;
		
			for(LiveRecordInfo record:liveList){
				try {
					Map<String,Object> result=new HashMap<>();
					result.put("vedioUrl", record.getVedio_url());
					long sdate = record.getStart_date().getTime();
					long newDate = new Date().getTime();
					long longTime = (newDate - sdate)/1000/60;
					result.put("longTime", longTime);
					result.put("img", fileUrl+record.getZhibo_cover());
					result.put("nname", record.getNname());
					result.put("viewCount", record.getView_count());
					result.put("sellerName", record.getSellername());
					result.put("rid", record.getId());
					result.put("type", record.getZhibo_type());
					result.put("roomNo", record.getAnchor_room_no());
					result.put("id", record.getId());
					result.put("anchorId", record.getAnchor_id());
					result.put("liveType", record.getLive_start_type());
					//查询主播信息表
					LiverInfo liverInfo = liveUserService.queryLiverById(record.getAnchor_id());
					if(liverInfo!=null){
						//查询用户基本信息
						UrsInfo ursInfo= anchorLiveRecordService.queryUrsByUid(liverInfo.getUid());
						result.put("sex", ursInfo.getSex());
						result.put("avatar", fileUrl+ursInfo.getAvatar());
						result.put("groupId", liverInfo.getGroup_id());
						result.put("account", liverInfo.getPhone());
						result.put("uid", liverInfo.getUid());
					}
					//查查询用户商圈信息
					Seller seller = sellerService.querySellerBySellerid(record.getSellerid());
					//查询商家商圈信息
					Map<Object,Object> busMap = sellerService.queryBusinessByZoneid(seller.getZoneid());
					if(busMap!=null){
						result.put("zoneName", busMap.get("titile"));
					}
					//查寻直播预售
					Map<String,Object> params = new HashMap<>();
					params.put("page",1);
					params.put("pageSize",1);
					params.put("recordId", record.getId());
					
					List<Map<String,Object>> couponList = sellerService.queryCouponBySellerid(params);
					if(couponList!=null&&couponList.size()>0){
						result.put("isCoupon", 1);//是否有预售0没有1有
						result.put("couponName", couponList.get(0).get("cname"));
						result.put("denomination", couponList.get(0).get("denomination"));
						//预售卷购买地址
						String annunciate_info_url = propertiesUtil.getValue("annunciate_info_url", "conf_live.properties");
						result.put("url",annunciate_info_url+"?liveid="+record.getId());
					}else{//查询预告
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						result.put("isCoupon", 0);
						if(liveRecordInfo!=null){
							result.put("tid", liveRecordInfo.getId());
							result.put("sdate", sdf.format(liveRecordInfo.getPlan_start_date()));
							result.put("edate", sdf.format(liveRecordInfo.getPlan_end_date()));
							result.put("title", liveRecordInfo.getZhibo_title());
							result.put("isTra", 1);
						}else{
							Map<String,Object> liveParams = new HashMap<>();
							liveParams.put("pageSize", 1);
							liveParams.put("page",1);
							liveParams.put("type", 0);
							liveParams.put("sellerid", sellerid);
							//查询预告列表
							List<LiveRecordInfo>  list = sellerService.queryLiveRecordInfoBySellerIdAndType(liveParams);
							if(list!=null&&list.size()>0){
								liveRecordInfo = list.get(0);
								result.put("tid", liveRecordInfo.getId());
								result.put("sdate", sdf.format(liveRecordInfo.getPlan_start_date()));
								result.put("edate", sdf.format(liveRecordInfo.getPlan_end_date()));
								result.put("title", liveRecordInfo.getZhibo_title());
								result.put("isTra", 1);
							}else{
								result.put("isTra", 0);
							}
						}	
						
					}


					result.put("livePlatform", 1); //直播使用平台  1 腾讯直播  2 金山云直播
					result.put("liveRtmpUrl", "");  //拉流地址
					String uid = result.get("uid") == null ? "" : result.get("uid").toString().trim();
					if (!uid.equals("")) {
						// 金山云拉流
						try {
							KSLiveEntity entity = ksCloudService.createKSLPullUrl(uid, liverInfo);
							if (entity != null) {
								result.put("livePlatform", entity.getPlatform()); //直播使用平台  1 腾讯直播  2 金山云直播
								result.put("liveRtmpUrl", entity.getUrl());  //拉流地址
							}
						} catch (UnsupportedEncodingException e) {
							log.warn("生成金山云拉流失败", e);
						}
					}
					resultliveList.add(result);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			return resultliveList;
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
	

}
