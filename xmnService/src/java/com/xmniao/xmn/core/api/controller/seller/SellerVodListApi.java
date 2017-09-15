package com.xmniao.xmn.core.api.controller.seller;

import java.text.SimpleDateFormat;
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

import com.xmniao.xmn.core.api.controller.xmer.SellerInfoApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;
import com.xmniao.xmn.core.live.service.LiveUserService;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
*      
* 类名称：SellerVodListApi   
* 类描述： 查询店铺直播回放列表  
* 创建人：xiaoxiong   
* 创建时间：2016年11月14日 上午10:40:34     
*
 */
@Controller
@RequestMapping("seller/vod")
public class SellerVodListApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerInfoApi.class);
		
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入anchorLiveRecordService
	 */
	@Autowired 
	private AnchorLiveRecordService anchorLiveRecordService;
	
	@Autowired
	private UrsService ursService;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private LiveUserService liveUserService;

	
	@ResponseBody
	@RequestMapping("/list")
	public Object list(SelleridPageRequest request){
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
			return versionControl(request.getApiversion(),request);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	/**
	 * 
	 * @Description: 查询店铺回放列表
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	private Object versionOne(Object object) {
		
		SelleridPageRequest request=(SelleridPageRequest)object;
		//查询商家回放记录
		List<LiveRecordInfo> liveList=anchorLiveRecordService.vodListBySellerid(request);
		if(liveList==null){
			return new BaseResponse(ResponseCode.FAILURE,"无商家回放记录或查询错误！");
		}
		//返回数据
		List<Map<String,Object>> vodlist=new ArrayList<>();
		//数据处理
		vodList(vodlist,liveList);
		MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
		
		Map<Object, Object> map=new HashMap<>();
		map.put("vodList", vodlist);
		
		mapResponse.setResponse(map);
		
		
		return mapResponse;
	}
	
	/**
	 * 
	 * @Description: 根据返回商家回放记录数据，查询商家商圈
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	private void vodList(List<Map<String, Object>> vodlist,List<LiveRecordInfo> liveList) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
			for(LiveRecordInfo record:liveList){
				try {
					Map<String,Object> result=new HashMap<>();
					result.put("vedioUrl", record.getZhibo_playback_url());
					result.put("sdate", sdf.format(record.getStart_date()));
					result.put("edate", sdf.format(record.getEnd_date()));
					result.put("img", fileUrl+record.getZhibo_cover());
					result.put("nname", record.getNname());
					result.put("viewCount", record.getView_count());
					result.put("sellerName", record.getSellername());
					result.put("roomNo", record.getAnchor_room_no());
					result.put("anchorId", record.getAnchor_id());
					
					result.put("id", record.getId());
					//查询主播信息表
					LiverInfo liverInfo = liveUserService.queryLiverById(record.getAnchor_id());
					//查询用户基本信息
					UrsInfo ursInfo= anchorLiveRecordService.queryUrsByUid(liverInfo.getUid());
					result.put("sex", ursInfo.getSex());
					result.put("avatar", fileUrl+ursInfo.getAvatar());
					//查查询用户商圈信息
					Seller seller = sellerService.querySellerBySellerid(record.getSellerid());
					//查询商家商圈信息
					Map<Object,Object> busMap = sellerService.queryBusinessByZoneid(seller.getZoneid());
					if(busMap!=null){
						result.put("zoneName", busMap.get("titile"));
					}
					vodlist.add(result);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
	}
}
