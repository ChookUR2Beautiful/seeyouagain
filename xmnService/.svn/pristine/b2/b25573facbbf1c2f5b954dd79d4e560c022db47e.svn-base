package com.xmniao.xmn.core.catehome.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.dao.HotWordsDao;
import com.xmniao.xmn.core.catehome.entity.HotWords;
import com.xmniao.xmn.core.catehome.request.SearchKeywordsRequest;
import com.xmniao.xmn.core.catehome.response.SearchKeywordsResponse;
import com.xmniao.xmn.core.catehome.service.SearchKeywordsService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackagePicDao;
import com.xmniao.xmn.core.sellerPackage.response.ComboListResponse;
import com.xmniao.xmn.core.sellerPackage.service.ComboService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.StrUtils;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.xmer.dao.SellerDao;

/**
 * 
* @projectName: xmnService 
* @ClassName: SearchKeywordsServiceImpl    
* @Description:关键词搜索实现   
* @author: liuzhihao   
* @date: 2017年2月21日 上午9:50:08
 */
@Service
public class SearchKeywordsServiceImpl implements SearchKeywordsService {
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(SearchKeywordsServiceImpl.class);
	
	//注入店铺dao
	@Autowired
	private SellerDao sellerDao;

	//注入关键词dao
	@Autowired
	private HotWordsDao hotWordsDao;
	
	//注入套餐图片dao
	@Autowired
	private SellerPackagePicDao sellerPackagePicDao;
	
	//注入直播记录dao
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	//注入用户dao
	@Autowired
	private UrsDao ursDao;
	
	//注入订单dao
	@Autowired
	private BillDao billDao;
	
	//注入主播用户dao
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private ComboService comboService;
	
	//注入缓存service
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//注入配置文件
	@Autowired
	private String fileUrl;
	
	/**
	 * 关键词查询
	 */
	@Override
	public Object searchSellersByKeywords(SearchKeywordsRequest searchKeywordsRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		//获取用户ID
		String uid = 
			ObjectUtils.toString(sessionTokenService.getStringForValue(searchKeywordsRequest.getSessiontoken()));
		
		if(StringUtils.isEmpty(uid)){
			uid = null;
		}
		//根据用户输入的关键词，更新关键词库
		String keyword = StrUtils.cutSpaceKey(searchKeywordsRequest.getKeyword());//去掉词语之间的空白
		//更新
		updateHotWords(keyword, searchKeywordsRequest.getCityId());
		//搜索 与套餐相关的店铺
		List<SearchKeywordsResponse> searchs = new ArrayList<SearchKeywordsResponse>();
		
		//格式化关键词
		keyword = StrUtils.generateSearchKey(keyword);
		
		try{
			searchs = findSellersIsComboByPage(uid, searchKeywordsRequest.getLat(), searchKeywordsRequest.getLon(), 
				searchKeywordsRequest.getCityId(), keyword, searchKeywordsRequest.getPage(),Constant.PAGE_LIMIT);
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"搜索关键词,查询与套餐相关的店铺信息异常");
		}
		
		if(searchs.isEmpty()){
			//当没有查询到结果的时候
			try{
				List<ComboListResponse> combos = comboService.recommendCombo(
					searchKeywordsRequest.getLat(), searchKeywordsRequest.getLon(), searchKeywordsRequest.getCityId());
				
				if(combos != null && combos.size() > 0){
					searchs = formatRecommend(combos);
				}
				
			}catch(Exception e){
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"搜索关键词,查询与套餐相关的店铺信息异常");
			}
		}
		map.put("searchs", searchs);
		//格式化数据
		MapResponse response = new MapResponse(ResponseCode.SUCCESS, "搜索成功");
		response.setResponse(map);
		return response;
	}
	
	/**
	 * 分页查询与套餐相关的店铺信息
	 */
	@Override
	public List<SearchKeywordsResponse> findSellersIsComboByPage(String uid, Double lat, Double lon, Integer cityId,
		String keyword,Integer page,Integer pageSize) throws Exception{
		List<SearchKeywordsResponse> skrs = new ArrayList<SearchKeywordsResponse>();
		//查询
		List<Map<Object,Object>> sellers = 
			sellerDao.searchSellersByKeyword(uid, lat, lon, cityId, keyword, page, pageSize);
		if(sellers != null && sellers.size() > 0){
			for(Map<Object,Object> seller : sellers){
				//返回结果对象
				SearchKeywordsResponse skr = new SearchKeywordsResponse();
				
				skr.setUid(uid);//用户ID
				skr.setSellerId(Integer.parseInt(seller.get("sellerid").toString()));//店铺ID
				skr.setComboId(
					StringUtils.isEmpty(
						ObjectUtils.toString(seller.get("comboid"))
						)?null:Integer.parseInt(seller.get("comboid").toString()));//套餐ID
				skr.setSellerName(ObjectUtils.toString(seller.get("sellername")));//店铺名称
				skr.setComboTitle(ObjectUtils.toString(seller.get("comboname")));//套餐标题
				
				
				skr.setComboCoin(BigDecimal.valueOf(Double.parseDouble(seller.get("combocoin").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//套餐鸟币价格
				skr.setComboPrice(BigDecimal.valueOf(Double.parseDouble(seller.get("comboprice").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//套餐价格
				skr.setTradeName(ObjectUtils.toString(seller.get("tradename")));//二级分类名称
				skr.setZoneName(ObjectUtils.toString(seller.get("zonename")));//商圈名称
				skr.setZhiboType(Integer.parseInt(seller.get("rsort").toString()));//直播状态
				
				//距离
				Double ranges = StringUtils.isEmpty(
					ObjectUtils.toString(seller.get("ranges"))
					)?0.0:Double.parseDouble(seller.get("ranges").toString());
				
				int mark = 0;//与我相关的标签  0 无标签 1 我消费过 2我收藏过的 3 我浏览过的 4 我附近的 5优质的
				//判断与我相关的状态
				if(ranges <= 1000){
					mark=4;//与我相关的标签  0 无标签 1 我消费过 2我收藏过的 3 我浏览过的 4 我附近的 5优质的
					skr.setRanges(ranges+"m");//距离
				}else{
					skr.setRanges(ArithUtil.div(ranges, 1000)+"km");//距离
					
					List<Map<String, Object>> activitys = 
						sellerDao.queryActivityList(Integer.parseInt(seller.get("sellerid").toString()));
					if(activitys != null && activitys.size() > 0){
						mark = 5;//与我相关的标签  0 无标签 1 我消费过 2我收藏过的 3 我浏览过的 4 我附近的 5优质的
					}
				}
				
				if(StringUtils.isNotEmpty(uid)){
					//我消费过的
					int bsort = Integer.parseInt(seller.get("bsort").toString());
					//我收藏过的
					int csort = Integer.parseInt(seller.get("csort").toString());
					//我浏览过的
					int vsort = Integer.parseInt(seller.get("vsort").toString());
					
					if(bsort > 0){
						mark=1;//与我相关的标签  0 无标签 1 我消费过 2我收藏过的 3 我浏览过的 4 我附近的 5优质的
					}else if(csort > 0){
						mark=2;//与我相关的标签  0 无标签 1 我消费过 2我收藏过的 3 我浏览过的 4 我附近的 5优质的
					}else if(vsort > 0){
						mark=3;//与我相关的标签  0 无标签 1 我消费过 2我收藏过的 3 我浏览过的 4 我附近的 5优质的
					}
				}
				skr.setMark(mark);
				
				//店铺直播状态
			/*	int livetype = skr.getZhiboType();
				
				switch(livetype){
					case 0:
						skr.setZhiboType(null);
						break;
					case 1:
						//判断该种情况下是否存在 直播或者预告
						skr.setZhiboType(getLiveType(skr.getSellerId()));//直播状态 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播
						break;
					case 2://当livetype=2的时候,证明相同sellerId下面最大值为 0 所以不需在次判断
						skr.setZhiboType(0);//直播状态 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播 
						break;
					case 3://当livetype=3的时候,证明相同sellerId下面最大值为 1 所以不需在次判断
						skr.setZhiboType(1);//直播状态 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播
						break;
						default:
							break;
				}*/
				//设置直播标签
				getComeLiver(skr);
				//店铺图片
				String image = getImage(Integer.parseInt(seller.get("sellerid").toString()));
				skr.setSellerCoverImage(image);
				//获取套餐封面图
				Integer comboId = Integer.parseInt(seller.get("comboid").toString());
				if(comboId != 0){
					List<String> comboCoverImage = sellerPackagePicDao.getCoverImage(comboId, 1);
					if(comboCoverImage != null && comboCoverImage.size() > 0){
						skr.setComboCoverImage(fileUrl+comboCoverImage.get(0));//套餐封面图
					}
				}
				
				//店铺消费人数
				int consums = billDao.sumAllOrdersBySellerId(Integer.parseInt(seller.get("sellerid").toString()));
				
				skr.setConsums(getConsuCount(consums, Integer.parseInt(seller.get("sellerid").toString())));//店铺消费人次
				
				skrs.add(skr);
			}
		}
		return skrs;
	}

	public int getConsuCount(int billCount, int sellerid) {
		int consuNumber = 0;
		try {
			// 缓存随机消费人数key
			String randomKey = Constant.SELLER_RANDOM_CONSU_NUMBER;

			Map<Object, Object> redisNumber = sessionTokenService.getsessionToken(randomKey);
			if (redisNumber == null) {
				redisNumber = new HashMap<>();
			}
			if (redisNumber.get(sellerid + "") == null) {

				int randomNumber = (int) (Math.random() * (500 - 100 + 1) + 100);
				consuNumber = billCount + randomNumber;
				redisNumber.put(sellerid + "", randomNumber + "");
				// 保存到redis
				sessionTokenService.setMapForObject(randomKey, redisNumber, 0, null);
			} else {
				consuNumber = billCount + Integer.valueOf(redisNumber.get(sellerid + "") + "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return consuNumber;
	}
	/**
	 * 更新用户搜索的关键词 
	 */
	private void updateHotWords(String keyword,Integer cityId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("keyWord", keyword);
		params.put("areaId", cityId);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			HotWords hotWord = hotWordsDao.queryKeyWordByKeyWord(params);
			if(hotWord == null){
				hotWord = new HotWords();
				hotWord.setAreaId(cityId);
				hotWord.setUpdateTime(sdf.format(new Date()));
				hotWord.setHotWords(keyword);
				hotWord.setCreatedTime(sdf.format(new Date()));
				hotWord.setHotNum(1);
				hotWord.setHotStatus(1);
				hotWord.setHotType(2);
				hotWordsDao.insertSelective(hotWord);
			}else{
				if(hotWord.getHotType()==1){
					int orderCount = hotWord.getHotOrder()==null?1:hotWord.getHotOrder()+1;
					hotWord.setHotOrder(orderCount);
				}else{
					int hotNumCount = hotWord.getHotNum()==null?1:hotWord.getHotNum()+1;
					hotWord.setHotNum(hotNumCount);
				}
				hotWordsDao.updateByPrimaryKeySelective(hotWord);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("更新热词异常");
		}
	}


	/**
	 * 直播状态
	 * @param sellerid
	 * @return
	 */
	private int getLiveType(Integer sellerid){
		
		int result = 3;
		
		int type = 0;//直播状态 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播
		do{
			int count = anchorLiveRecordDao.findOneBySellerId(type, sellerid);
			if(count == 0){
				type++;
			}else{
				result = type;
				break;
			}
		}while(type<2);
		
		return result;
	}

	/**
	 * 获取店铺图片
	 * @param sellerid
	 * @return
	 */
	private String getImage(Integer sellerid){
		
		String image = "";
		
		List<SellerPic> pics = new ArrayList<SellerPic>();

		int type = 1;// 商铺图片状态 0 环境图 1 logo图  默认为1
		//封装查询商铺logo图数据
		Map<String,Object> params=new HashMap<>();
		params.put("fileUrl", fileUrl);
		params.put("sellerid", sellerid);
		do{
			params.put("type", type);
			try{
				pics = sellerDao.querySellerPicBySelleridAndType(params);
			}catch(Exception e){
				e.printStackTrace();
				log.info("查询店铺图片异常");
			}
			if(pics.isEmpty()){
				type = 0;
			}else{
				image = pics.get(0).getUrl();
				break;
			}
			
		}while(type<2);
		
		return image;
	}


	/**
	 * 获取直播标签
	 * @return
	 */
	private void getComeLiver(SearchKeywordsResponse skr){
		
		Integer zhiboType = skr.getZhiboType();

		String zhiboMark = "";
		
		if(zhiboType == null){
			
			//关注过的人来过
			if(StringUtils.isNotEmpty(skr.getUid())){
				List<Integer> fuids = ursDao.findUserFollowsByUid(skr.getUid(), 1, 10);
				if(fuids != null && fuids.size() > 0){
					List<Urs> urs = ursDao.findUrsByPage(fuids, 1, 4);
					if(urs != null && urs.size() > 0){
						StringBuilder sb = new StringBuilder();
						for(Urs u : urs){
							sb.append(u.getNname()).append("、");
						}
						sb.replace(sb.length()-1, sb.length(), "好友来过");
						zhiboMark = sb.toString();
					}
				}
			}
			
		}else{
			if(zhiboType == 1){//正在直播状态
				LiveRecordInfo record = anchorLiveRecordDao.selectOneBySellerId(skr.getSellerId(), 1);//正在直播
				if(record != null){
					skr.setAnchorId(record.getAnchor_id().intValue());//主播ID
					skr.setAvetar(fileUrl+record.getAvatar());//主播头像
					skr.setRoomNo(record.getAnchor_room_no().intValue());//主播房间编号
					skr.setTitle(record.getZhibo_title());//直播标题
					skr.setRecordId(record.getId().intValue());//直播记录ID
					skr.setLiveType(record.getLive_start_type());//直播自定义类型
					skr.setUrl(ObjectUtils.toString(record.getVedio_url()));//直播地址
					LiverInfo liveInfo = liveUserDao.queryLiverInfoByAnchorId(record.getAnchor_id().intValue());
					if(liveInfo != null){
						skr.setGroupId(liveInfo.getGroup_id());//群组号
					}
					
					zhiboMark = "主播"+record.getNname()+"正在店里直播";
				}
			}else{
				List<LiveRecordInfo> lives = anchorLiveRecordDao.selectLiveRecordListBySellerId(skr.getSellerId(), 1, 3);
				if(lives != null && lives.size() > 0){
					StringBuilder sb = new StringBuilder("主播");
					for(LiveRecordInfo live : lives){
						sb.append(live.getNname()).append("、");
					}
					sb.replace(sb.length()-1, sb.length(), "等");
					sb.append("等主播来过");
					zhiboMark = sb.toString();
				}
			}
		}
		skr.setZhiboMark(zhiboMark);
	}

	
	private List<SearchKeywordsResponse> formatRecommend(List<ComboListResponse> combos){
		
		List<SearchKeywordsResponse> skrs = new ArrayList<SearchKeywordsResponse>();
		
		for(ComboListResponse combo : combos){
			SearchKeywordsResponse skr = new SearchKeywordsResponse();
			
			skr.setSellerId(combo.getSellerId());//店铺ID
			skr.setSellerName(combo.getSellerName());//店铺名称
			skr.setComboId(combo.getComboId());//套餐ID
			skr.setTradeName(combo.getTradeName());//分类名称
			skr.setZoneName(combo.getZoneName());//商圈名称
			skr.setRanges(combo.getRanges());//距离
			skr.setComboPrice(combo.getComboPrice());//套餐价格
			skr.setComboCoverImage(combo.getComboImage());//套餐封面
			skr.setComboCoin(combo.getComboCoin());//套餐鸟币价格
			skr.setComboTitle(combo.getComboTitle());//套餐标题
			skrs.add(skr);
		}
		return skrs;
	}
}
