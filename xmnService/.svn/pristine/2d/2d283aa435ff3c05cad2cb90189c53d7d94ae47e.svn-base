package com.xmniao.xmn.core.api.controller.vod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.live.common.LiveHomeConstants;
import com.xmniao.xmn.core.live.service.LiveHomeV2Service;
import com.xmniao.xmn.core.live.service.LiveHomeV2ServiceImpl;
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
import com.xmniao.xmn.core.common.request.vod.VodListRequest;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;
import com.xmniao.xmn.core.live.service.LiveUserService;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
 * 
* 类名称：VodlistApi   
* 类描述：   回放列表
* 创建人：xiaoxiong   
* 创建时间：2016年11月30日 下午4:43:09
 */
@Controller
@RequestMapping("/vod")
public class VodlistApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(VodlistApi.class);
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@Autowired
	private AnchorLiveRecordService liveService;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private UrsService ursService;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private LiveUserService liveUserService;
	
	@Autowired
	private AnchorLiveRecordService anchorLiveRecordService;

	@Autowired
	private LiveHomeV2Service liveHomeV2Service;
	
	@ResponseBody
	@RequestMapping("/list")
	public Object list(VodListRequest request){
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new MapResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
	}
	}

	private Object versionControlOne(Object object) {
		try {
			VodListRequest request =(VodListRequest) object;
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("page", request.getPage());
			params.put("pageSize", request.getPageSize());
			params.put("longitude", request.getLongitude());
			params.put("latitude", request.getLatitude());
			params.put("sdate", request.getSdate());
			params.put("edate", request.getEdate());
			params.put("type", request.getType());
			params.put("zhiboType", 3);
			//判断用户是否登入
			String uid = null;
			if(request.getSessiontoken()!=null){
				uid = sessionService.getStringForValue(request.getSessiontoken())+"";
				if(!uid.equals("")&&!uid.equals("null")){
					params.put("uid", uid);
					
					//查询直播用户信息
					LiverInfo liveInfo = liveUserService.queryLiverByUid(uid);
					if(liveInfo!=null)
					{
						params.put("liver_str_id",liveInfo.getId());
						params.put("phone", liveInfo.getPhone());
					}
				}else{
					if(request.getType()==1||request.getType()==2){
						return new BaseResponse(ResponseCode.TOKENERR, "您还没有登入，请先登入！");
					}
					
				}
			}else{
				if(request.getType()==1||request.getType()==2){
					return new BaseResponse(ResponseCode.TOKENERR, "您还没有登入，请先登入！");
				}
			}
			if (request.getType() == 0) {  //查看全部，不需要按距离排序
				params.remove("longitude");
				params.remove("latitude");
			}
			
			//查询回放数据
			List<Map<String,Object>> tempVodList = liveService.vodList(params);
			//处理数据
			List<Map<String,Object>> vodListResponse = vodListResponse(tempVodList,uid);
			
			Map<Object,Object> result = new HashMap<>();
			result.put("list", vodListResponse);
			
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS,"成功");
			mapResponse.setResponse(result);
			
			return mapResponse;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"错误");
	}

	private List<Map<String, Object>> vodListResponse(List<Map<String, Object>> tempVodList,String uid) {
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			if(tempVodList!=null && tempVodList.size()>0)
			{
				for(Map<String,Object> map:tempVodList)
				{
					try 
					{	int lable=0;
						if(uid!=null&&!uid.equals("")&&!uid.equals("null")&&map.get("sellerid")!=null)
						{
							 lable =lable(Integer.parseInt(map.get("sellerid")+""), Integer.parseInt(uid));
							 if(lable == 0){
								 //距离近
								 if(map.get("range")!=null&&Double.parseDouble(map.get("range")+"")<=500)
								 {
									 lable = 4;
								 }
							 }
						}
						map.put("lable", lable);
						map.remove("bil_s_order");
						map.remove("bro_s_order");
						map.remove("focu_order");
						map.remove("urs_s_order");
						map.remove("record_c");
						//处理头像
						map.put("avatar", fileUrl+map.get("avatar"));
						map.put("zhibo_cover", fileUrl+map.get("zhibo_cover"));
						//查询性别
						LiverInfo liveInfo = liveUserService.queryLiverById(Long.valueOf(map.get("anchorId")+""));
						map.put("signType", 0);
						if(liveInfo!=null){
							//查询用户基本信息
							UrsInfo ursInfo= anchorLiveRecordService.queryUrsByUid(liveInfo.getUid());
							String sex = ursInfo.getSex() == null?"0": ursInfo.getSex()+"";			// 性别
							map.put("sex", sex);
							map.put("signType", liveInfo.getSign_type() == null ? 0 : liveInfo.getSign_type());  //是否是签约主播 0 否 1 是 2 内部测试账号
						}
						//处理距离
						if(map.get("range")!=null){
							Long range =(long) Double.parseDouble(map.get("range")==null?"0":map.get("range")+"");
							if(range<1000){
								map.put("range", range+"m");
							}else{
								Long r = range/1000;
								map.put("range", r+"km");
							}
						}
						
					} catch (Exception e) 
					{
						e.printStackTrace();
						continue;
					}
					
					result.add(map);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 
	 * @Description: 查询商家标签
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	public int lable(int sellerid,int uid){
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
		return lable;
	}
	
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
	
	
}
