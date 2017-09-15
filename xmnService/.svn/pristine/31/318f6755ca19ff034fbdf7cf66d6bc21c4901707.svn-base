package com.xmniao.xmn.core.api.controller.catehome;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.entity.HotWords;
import com.xmniao.xmn.core.catehome.response.SearchResponse;
import com.xmniao.xmn.core.catehome.service.HotWordsService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.catehome.KeyWordRequest;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;
import com.xmniao.xmn.core.live.service.LiveUserService;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.xmer.service.SellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;


/**
 * 
* 类名称：SearchApi   
* 类描述：   关键字搜索
* 创建人：xiaoxiong   
* 创建时间：2016年11月22日 下午4:56:42
 */

@Controller
public class SearchApi implements BaseVControlInf{
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SearchApi.class);
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private HotWordsService hotWordService;
	
	@Autowired
	private AnchorLiveRecordService anchorLiveRecordService;
	
	@Autowired
	private LiveUserService liveUserService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	

	@ResponseBody
	@RequestMapping("search")
	public Object search(KeyWordRequest request){
		
		// 验证参数
		log.info("cateCommentRequest data:" + request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if (result != null && result.size() > 0) {
			log.info("提交的数据有问题" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
			case 1:
				return versionOne(object);//改版之前
//				return versionOne(object);
				default :
					return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	/*private Object versionOne(Object object){
		
		KeyWordRequest request =(KeyWordRequest)object;
		
		return sellerService.searchSellerList(request); 
	}
	*/
	private Object versionOne(Object object) {
		try {
			KeyWordRequest request =(KeyWordRequest)object;
			/**
			 * 添加热搜关键字
			 */
			try {
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//查询是否有这个关键字
				HotWords hotWord = hotWordService.keyWordCount(request.getKeyWord(),request.getAreaId());
				if(hotWord == null){
					hotWord = new HotWords();
					hotWord.setAreaId(request.getAreaId());
					hotWord.setUpdateTime(sdf.format(new Date()));
					hotWord.setHotWords(request.getKeyWord());
					hotWord.setCreatedTime(sdf.format(new Date()));
					hotWord.setHotNum(1);
					hotWord.setHotStatus(1);
					hotWord.setHotType(2);
					hotWordService.insertSelective(hotWord);
				}else{
					if(hotWord.getHotType()==1){
						int orderCount = hotWord.getHotOrder()==null?1:hotWord.getHotOrder()+1;
						hotWord.setHotOrder(orderCount);
					}else{
						int hotNumCount = hotWord.getHotNum()==null?1:hotWord.getHotNum()+1;
						hotWord.setHotNum(hotNumCount);
					}
					hotWordService.updateByPrimaryKeySelective(hotWord);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			/**
			 * 查询店铺信息
			 */
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("page", request.getPage());
			params.put("pageSize", request.getPageSize());
			params.put("longitude", request.getLongitude());
			params.put("latitude", request.getLatitude());
			params.put("areaId", request.getAreaId());
			//关键字处理
			String keyWord = keyWord(request.getKeyWord());
			params.put("keyWord", keyWord);
			
			if(request.getSessiontoken()!=null){
				String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+"";
				if(!uid.equals("null")&&!uid.equals("")){
					params.put("uid", uid);
				}
			}
			/**
			 * 根据关键字查询店铺
			 */
			List<Map<String,Object>> searchList = sellerService.search(params);
			
			List<SearchResponse> sellerList = converSearchRsponse(searchList);
			
			Map<Object, Object> result = new HashMap<>();
			result.put("sellerList", sellerList);
			
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			mapResponse.setResponse(result);
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
	}
	/**
	 * 处理转换
	 * @author xiaoxiong
	 * @date 2016年11月23日
	 */
	private List<SearchResponse> converSearchRsponse(List<Map<String, Object>> searchList) {
		
		List<SearchResponse> sellerList = new ArrayList<>();
		try {
			if(searchList!=null&&searchList.size()>0)
			{
				for(Map<String,Object> map : searchList)
				{
					try
					{
						SearchResponse temp = new SearchResponse();
						BeanUtils.copyProperties(temp, map);
						//标签
						int lable = lable(map);
						temp.setLable(lable);
						//查询直播回放
						int isLive = Integer.parseInt(map.get("live_s_order")+"");
						if(isLive==0){
							temp.setIsLive(1);
							isLive(temp,1);
						}else{
							temp.setIsLive(0);
							isLive(temp,0);
						}
						/*商家logo*/
						List<SellerPic> pics = sellerService.querySellerPicBySelleridAndType(temp.getSellerid(), 1);
						if(pics!=null&&pics.size()>0){
							temp.setUrl(pics.get(0).getUrl());
						}else{
							List<SellerPic> pic = sellerService.querySellerPicBySelleridAndType(temp.getSellerid(), 0);
							if(pic!=null&&pic.size()>0){
								temp.setUrl(pic.get(0).getUrl());
							}
						}
						//距离处理
						long range = (long) Double.parseDouble(map.get("range")==null?"0":map.get("range")+"");
							if(range<1000){
								temp.setRange(range+"m");
							}else{
								Long r = range/1000;
								temp.setRange(r+"km");
							}
							temp.setCount(sellerService.getConsuCount(temp.getCount(), temp.getSellerid()));
							
						sellerList.add(temp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sellerList;
	}
	


	private int lable(Map<String, Object> map) {
		int lable = 0;
		try {
			if(map.get("bil_s_order")!=null&&Integer.parseInt(map.get("bil_s_order")+"")==0)
			{
				lable = 1;	//消费过
			}else
			{
				if(map.get("urs_s_order")!=null&&Integer.parseInt(map.get("urs_s_order")+"")==0)
				{
					lable = 2;
				}else
				{
					if(map.get("bro_s_order")!=null&&Integer.parseInt(map.get("bro_s_order")+"")==0)
					{
						lable = 3;
					}else{
						if(map.get("range")!=null&&Double.parseDouble(map.get("range")+"")<=1000){
							lable=4;
						}else{
							List<Map<String, Object>> activityList = sellerService.queryActivityList(Integer.valueOf(map.get("sellerid")+""));
							if (activityList != null && activityList.size() > 0 ) {
								lable=5;
							}
						}
						
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lable;
	}

	/**
	 * 关键字处理
	 * @author xiaoxiong
	 * @date 2016年11月22日
	 */
	private static String keyWord(String keyWord) {
		String word="%";
		try {
			for(int i = 0; i<keyWord.length();i++){
				word+=keyWord.substring(i,i+1)+"%";
			}
		} catch (Exception e) {
			e.printStackTrace();
			word = "";
		}
		return word;
	}
	
	/**
	 * 
	 * @Description: 查询是否有直播
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	public void isLive(SearchResponse response,int type){
		Map<String,Object> map = new HashMap<>();
		map.put("page", 1);
		map.put("pageSize",1);
		map.put("sellerid",response.getSellerid());
		try {
			//直播类型 -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4历史通告 5结束直播',
			//查询正在直播的
			
			List<LiveRecordInfo> list = null;
			if(type==1)
			{
				map.put("type", 1);
				list = anchorLiveRecordService.queryLiveRecordInfoBySellerIdAndType(map);
				if(list==null||list.size()==0){
					//如果没有正在直播的查询预告
					map.put("type",0);
					list = anchorLiveRecordService.queryLiveRecordInfoBySellerIdAndType(map);
					if(list==null||list.size()==0){
						map.put("type",3);
						list = anchorLiveRecordService.queryLiveRecordInfoBySellerIdAndType(map);
						if(list!=null&&list.size()>0){
							response.setZhibo_type(2);//回放
						}
					}else{
						response.setZhibo_type(1);//预告
					}
				}else{
					response.setZhibo_type(0);//直播
				}
			}
			
			
			if(list!=null&&list.size()>0){
				LiveRecordInfo liveRecoreInfo=list.get(0);
				Map<String,Object> subMap = new HashMap<>();
				subMap.put("type", liveRecoreInfo.getZhibo_type());//直播类型
				subMap.put("roomNo", liveRecoreInfo.getAnchor_room_no());//房间号
				subMap.put("title",liveRecoreInfo.getZhibo_title());
				if(liveRecoreInfo.getZhibo_type()==0){
					response.setZhibomark("主播 "+liveRecoreInfo.getNname()+" 即将直播");
				}else if(liveRecoreInfo.getZhibo_type()==1){
					response.setZhibomark("主播 "+liveRecoreInfo.getNname()+" 正在直播");
				}else if(liveRecoreInfo.getZhibo_type()==3){
					response.setZhibomark("主播 "+liveRecoreInfo.getNname()+" 来过");
				}
				subMap.put("anchorId", liveRecoreInfo.getAnchor_id());//主播ID
				subMap.put("id", liveRecoreInfo.getId());//记录ID
				subMap.put("avetar",fileUrl+liveRecoreInfo.getAvatar());//封面
				subMap.put("liveType",liveRecoreInfo.getLive_start_type());//直播类型 预告或自定义开播
				//如果是正在直播的需要查询groupid进房间使用
				if( liveRecoreInfo.getZhibo_type()==1){
					LiverInfo liveInfo = liveUserService.queryLiverInfoByAnchorId(Integer.parseInt(liveRecoreInfo.getAnchor_id()+""));
					if(liveInfo!=null){
						subMap.put("groupId", liveInfo.getGroup_id());
					}
				}
				if(liveRecoreInfo.getVedio_url()!=null){
					subMap.put("url", liveRecoreInfo.getVedio_url());
				}
				response.setSubSet(subMap);
			}else{
				map.put("type",4);
				list = anchorLiveRecordService.queryLiveRecordInfoBySellerIdAndType(map);
				if(list!=null&&list.size()>0){
					response.setZhibomark("主播 "+list.get(0).getNname()+" 来过");
				}
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
