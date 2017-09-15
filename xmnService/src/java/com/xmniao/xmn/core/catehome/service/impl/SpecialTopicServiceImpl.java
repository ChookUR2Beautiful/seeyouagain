package com.xmniao.xmn.core.catehome.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.dao.SpecilTopicConfigDao;
import com.xmniao.xmn.core.catehome.dao.SpecilTopicPicDao;
import com.xmniao.xmn.core.catehome.dao.SpecilTopicRelationDao;
import com.xmniao.xmn.core.catehome.entity.SpecilTopicConfig;
import com.xmniao.xmn.core.catehome.entity.SpecilTopicPic;
import com.xmniao.xmn.core.catehome.entity.SpecilTopicRelation;
import com.xmniao.xmn.core.catehome.response.SpecilTopicResponse;
import com.xmniao.xmn.core.catehome.response.SpecilTopicSubInfoResponse;
import com.xmniao.xmn.core.catehome.service.SpecialTopicService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.TResponse;
import com.xmniao.xmn.core.common.request.catehome.SpecilTopicRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.FansCouponDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;

/**
 * 
* @projectName: xmnService                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
* @ClassName: SpecialTopicServiceImpl    
* @Description:专题实现   
* @author: liuzhihao   
* @date: 2017年2月17日 上午11:30:59
 */
@Service
public class SpecialTopicServiceImpl implements SpecialTopicService {
	
	//日志报错
	private final Logger log = Logger.getLogger(SpecialTopicServiceImpl.class);
	
	//注入专题详情dao
	@Autowired
	private SpecilTopicConfigDao SpecilTopicConfigDao;
	
	//注入专题图片dao
	@Autowired
	private SpecilTopicPicDao specilTopicPicDao;
	
	//注入专题关联dao
	@Autowired
	private SpecilTopicRelationDao specilTopicRelationDao;
	
	//注入商铺dao
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	//注入主播dao
	@Autowired
	private LiveUserDao liveUserDao;
	
	//注入直播记录dao
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	//预售粉丝卷dao
	@Autowired
	private FansCouponDao fansCouponDao;
	
	//注入redis缓存实现类
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//注入服务器地址
	@Autowired
	private String fileUrl;

	//查看专题详情
	@Override
	public Object viewSpecialTopicInfo(SpecilTopicRequest specilTopicRequest) {
		
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(specilTopicRequest.getSessiontoken()));
		
		/**
		 * 1.根据专题活动ID查询专题详情,获取专题详情
		 * 2.根据专题活动ID查询与之关联的商铺等信息
		 */
		
		//查询专题详情
		SpecilTopicConfig stc = null;
		try{
			stc = SpecilTopicConfigDao.selectByPrimaryKey(specilTopicRequest.getId());
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询专题详情异常,专题ID:[id:"+specilTopicRequest.getId()+"]");
			
			return new BaseResponse(ResponseCode.FAILURE,"查询专题详情异常");
		}
		
		if(stc == null){
			return new BaseResponse(ResponseCode.FAILURE,"查询专题失败,检查专题活动ID是否存在;[id:"+specilTopicRequest.getId()+"]");
		}
		
		//获取专题类型
		Integer topicType = stc.getTopicType();
		
		//查询专题图片
		List<SpecilTopicPic> specilTopicPics = getSpecilTopicPicList(specilTopicRequest.getId());
		
		if(specilTopicPics == null){
			return new BaseResponse(ResponseCode.FAILURE,"查询专题图片异常");
		}
		
		List<String> images = new ArrayList<String>();
		for(SpecilTopicPic stp : specilTopicPics){
			images.add(fileUrl+stp.getPicUrl());
		}
		
		//查询与专题相关的关联信息
		List<SpecilTopicSubInfoResponse> sssrs = getSpecilTopicContentList(stc.getId(),topicType,uid);
		
		if(sssrs == null){
			return new BaseResponse(ResponseCode.FAILURE,"查询专题关联信息异常");
		}
		
		//设置专题详情返回结果集
		SpecilTopicResponse ssr = new SpecilTopicResponse();
		ssr.setFid(specilTopicRequest.getId());//专题ID
		ssr.setTitle(stc.getTitle());//专题标题
		ssr.setDescription(stc.getDescription());//专题描述
		ssr.setContent(stc.getContent());//专题内容
		ssr.setTopicType(stc.getTopicType());//专题类型
		ssr.setVideoUrl(stc.getVideoUrl());//专题视频
		ssr.setTopicImages(images);//专题banner图
		ssr.setSssrs(sssrs);//专题外部信息列表
		
		TResponse<SpecilTopicResponse> response = new TResponse<SpecilTopicResponse>(ResponseCode.SUCCESS, "查看成功");
		response.setResponse(ssr);
		return response;
	}
	
	/**
	 * 查询专题图片
	 * @param id
	 * @return
	 */
	private List<SpecilTopicPic> getSpecilTopicPicList(Integer fid){
		
		try{
			List<SpecilTopicPic> list =  specilTopicPicDao.findAllByFid(fid);
			
			if(!list.isEmpty()){

				for(SpecilTopicPic stp : list){
					//重置图片地址
					stp.setPicUrl(stp.getPicUrl()+fileUrl);;
				}
				
			}else{
				list = new ArrayList<SpecilTopicPic>();
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取专题内容列表
	 * @param id
	 * @return
	 */
	private List<SpecilTopicSubInfoResponse> getSpecilTopicContentList(Integer fid,Integer type,String uid){
		
		List<SpecilTopicSubInfoResponse> sssrs = new ArrayList<SpecilTopicSubInfoResponse>();
		
		//查询专题关联信息
		List<SpecilTopicRelation> strs = null;
		try{
			strs = specilTopicRelationDao.findAllByFid(fid, type);
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询专题关联信息异常");
			return null;
		}
		
		//封装专题信息
		
		List<Integer> subIds = new ArrayList<Integer>();
		if(strs != null && strs.size() >0){
			//抽取关联ID
			for(SpecilTopicRelation str : strs){
				subIds.add(Integer.parseInt(str.getSubId()));
			}
		}
		
		if(subIds.size() > 0){
			//专题类型 1.商户 2.连锁店 3.区域代理 4.主播 5.预告 6.粉丝券 7.套餐 8.商品
			switch(type){
				case 1://店铺类型
					sssrs = querySellers(subIds);
					break;
				case 4://主播类型
					sssrs = queryLivers(subIds, uid);
					break;
				case 5://预告类型
					sssrs = queryPrepares(subIds);
					break;
				case 6://预售粉丝卷类型
					sssrs = queryFansCoupons(subIds);
					break;
				case 7://套餐类型
					break;
					default:
						break;
			}
			
		}
		return sssrs;
	}

	/**
	 * 查询关联商铺信息
	 * @param sellerids
	 * @return
	 */
	private List<SpecilTopicSubInfoResponse> querySellers(List<Integer> sellerids){
		List<SpecilTopicSubInfoResponse> sssrs = new ArrayList<SpecilTopicSubInfoResponse>();
		try{
			
			List<Map<Object,Object>> sellers = sellerInfoDao.findSellerAndConsumsBySellerId(sellerids);
			
			if(sellers != null && !sellers.isEmpty()){
				
				for(Map<Object,Object> seller : sellers){
					
					SpecilTopicSubInfoResponse sssr = new SpecilTopicSubInfoResponse();
					
					sssr.setBreviaryImage(fileUrl+seller.get("picurl"));//店铺logo图片
					sssr.setSubId(Integer.parseInt(seller.get("sellerid").toString()));//店铺ID
					sssr.setSellerTradeName(seller.get("tradename").toString());//店铺二级分类名称
					sssr.setZoneName(seller.get("title").toString());//店铺商圈名称
					sssr.setSellerName(seller.get("sellername").toString());//店铺名称
					sssr.setConsumes(Integer.parseInt(seller.get("consums").toString()));//店铺消费人数
					
					sssrs.add(sssr);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询商铺信息异常");
		}
		return sssrs;
	}


	/**
	 * 查询主播信息
	 * @param liverIds
	 * @return
	 */
	private List<SpecilTopicSubInfoResponse> queryLivers(List<Integer> uids,String uid){
		//返回结果集
		List<SpecilTopicSubInfoResponse> sssrs = new ArrayList<SpecilTopicSubInfoResponse>();
		
		try{
			//查询主播信息
			List<Map<Object,Object>> livers = liveUserDao.findAllByUid(uids);
			
			if(livers != null && livers.size() > 0){
				
				for(Map<Object,Object> liver : livers){
					
					SpecilTopicSubInfoResponse ssr = new SpecilTopicSubInfoResponse();
					
					ssr.setSubId(Integer.parseInt(liver.get("uid").toString()));
					
					ssr.setBreviaryImage(fileUrl+liver.get("avatar"));//主播头像
					//设置性别
					ssr.setSex(StringUtils.isEmpty(ObjectUtils.toString(liver.get("sex")))?0:Integer.parseInt(ObjectUtils.toString(liver.get("sex"))));
					
					String name = ObjectUtils.toString(liver.get("nname"));
					if(StringUtils.isEmpty(name)){
						name = ObjectUtils.toString(liver.get("uname"));
						
						if(StringUtils.isNotEmpty(name)){
							String regex = "(\\d{3})\\d{4}(\\d{4})";
							name = name.replaceAll(regex, "$1****$2");
						}
					}
					
					ssr.setLiverName(name);//主播名称
					
					//是否关注主播
					ssr.setIsFollow(0);//是否关注主播 0 否 1 是  默认 没关注
					
					if(StringUtils.isNotEmpty(uid)){
						
						int	isFollow = 
							liveUserDao.queryTwoUidIsRelation(uid, liver.get("uid").toString());
						
						if(isFollow > 0){
							
							ssr.setIsFollow(1);//是否关注主播 0 否 1 是  默认 没关注
						}
					
					}
					
					
					sssrs.add(ssr);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询主播信息异常");
		}
		return sssrs;
	}

	
	/**
	 * 查询预告信息
	 * @param pres
	 * @return
	 */
	private List<SpecilTopicSubInfoResponse> queryPrepares(List<Integer> pres){
		
		//返回结果集
		List<SpecilTopicSubInfoResponse> sssrs = new ArrayList<SpecilTopicSubInfoResponse>();
		
		try{
			List<Map<Object,Object>> liveRecords = anchorLiveRecordDao.findAllByType(0, pres);
			
			if(liveRecords != null && liveRecords.size() > 0){
				
				for(Map<Object,Object> liveRecord : liveRecords){
					
					SpecilTopicSubInfoResponse ssr = new SpecilTopicSubInfoResponse();
					
					ssr.setSubId(Integer.parseInt(liveRecord.get("id").toString()));//预告记录ID
					
					ssr.setBreviaryImage(fileUrl+ObjectUtils.toString(liveRecord.get("zhiboCover")));//预告封面图
					
					ssr.setStartDate(ObjectUtils.toString(liveRecord.get("startDate")));//预告时间
					
					ssr.setPreTitle(ObjectUtils.toString(liveRecord.get("zhiboTitle")));//预告标题
					
					String sellername = ObjectUtils.toString(liveRecord.get("sellername"));//店铺名称
					
					if(StringUtils.isEmpty(sellername)){
						sellername = ObjectUtils.toString(liveRecord.get("selleralias"));
					}
					
					ssr.setSellerName(sellername);//店铺名称
					
					ssr.setRoomNo(Integer.parseInt(ObjectUtils.toString(liveRecord.get("roomno"))));//直播房间号
					
					sssrs.add(ssr);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询预告列表异常");
		}
		return sssrs;
	}

	/**
	 * 查询粉丝卷列表信息
	 * @param coupons
	 * @return
	 */
	private List<SpecilTopicSubInfoResponse> queryFansCoupons(List<Integer> coupons){
		//返回结果集
		List<SpecilTopicSubInfoResponse> sssrs = new ArrayList<SpecilTopicSubInfoResponse>();
		try{
			List<Map<Object,Object>> fanCoupons = fansCouponDao.findAll(coupons);
			
			if(fanCoupons != null && fanCoupons.size() > 0){
				
				for(Map<Object,Object> fanCoupon : fanCoupons){
					
					SpecilTopicSubInfoResponse ssr = new SpecilTopicSubInfoResponse();
					
					ssr.setBreviaryImage(fileUrl+ObjectUtils.toString(fanCoupon.get("zhiboCover")));//预售封面
					
					ssr.setPreTitle(ObjectUtils.toString(fanCoupon.get("cname")));//预售标题
					//粉丝卷价格
					ssr.setFansPrice(BigDecimal.valueOf(Double.parseDouble(ObjectUtils.toString(fanCoupon.get("denomination")))));
				
					ssr.setLiverName(ObjectUtils.toString(fanCoupon.get("nname")));//主播名称
					
					String sellername = ObjectUtils.toString(fanCoupon.get("sellername"));
					if(StringUtils.isEmpty(sellername)){
						sellername = ObjectUtils.toString(fanCoupon.get("selleralias"));
					}
					
					ssr.setSellerName(sellername);
					
					ssr.setSubId(Integer.parseInt(ObjectUtils.toString(fanCoupon.get("fid"))));//粉丝卷ID
					
					
					sssrs.add(ssr);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询粉丝券异常");
		}
		return sssrs;
	}
}
