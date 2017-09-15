package com.xmniao.xmn.core.live.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.LiveLedgerRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.MakeHaoDao;
import com.xmniao.xmn.core.live.entity.LiveFansRank;
import com.xmniao.xmn.core.live.request.MyFriendsRequest;
import com.xmniao.xmn.core.live.request.MyIndirectlyFriendsRequest;
import com.xmniao.xmn.core.live.response.RichFriendResponse;
import com.xmniao.xmn.core.live.service.MyRichFriendService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.StrUtils;

/**
 * 
* @projectName: xmnService 
* @ClassName: MyRichFriendServiceImpl    
* @Description:我的壕友实现类   
* @author: liuzhihao   
* @date: 2017年3月1日 上午11:44:57
 */
@Service
public class MyRichFriendServiceImpl implements MyRichFriendService{

	/**
	 * 日志报错
	 */
	private final Logger log = Logger.getLogger(MyRichFriendServiceImpl.class);
	
	//注入壕友dao
	@Autowired
	private MakeHaoDao makeHaoDao;
	
	//注入用户dao
	@Autowired
	private LiveUserDao liveUserDao;
	
	//注入平台分账dao
	@Autowired
	private LiveLedgerRecordDao liveLedgerRecordDao;
	
	//注入缓存service
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//注入服务器地址
	@Autowired
	private String fileUrl;
	
	/**
	 * 查看我的壕友列表
	 */
	@Override
	public Object queryMyDirectFriends(MyFriendsRequest request) {
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		//获取用户UID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
		
		if(StringUtils.isEmpty(uid)){
			return new BaseResponse(ResponseCode.FAILURE,"登录异常,请重新登录");
		}
		
		List<RichFriendResponse> friends = findMyFriends(uid,null,request.getPage(),Constant.PAGE_LIMIT,request.getFriendType());
		
		if(friends != null && friends.size() > 0){
			map.put("friends", friends);
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
	}

	/**
	 * 查看我的间接壕友
	 * @return
	 */
	@Override
	public Object queryMyIndirectlyFriends(MyIndirectlyFriendsRequest request){
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		//获取用户UID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
		
		if(StringUtils.isEmpty(uid)){
			return new BaseResponse(ResponseCode.FAILURE,"登录异常,请重新登录");
		}
		
		List<RichFriendResponse> friends = findMyFriends(uid,request.getFuid(),request.getPage(),Constant.PAGE_LIMIT,request.getFriendType());
		
		if(friends != null && friends.size() > 0){
			map.put("friends", friends);
		}
		
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
		
	}
	
	
	/**
	 * 查询我的壕友
	 * @param uid 用户ID
	 * @param page
	 * @param pageSize
	 * @param friendType 壕友类型 0 直接壕友 1 间接壕友
	 * @return
	 */
	public List<RichFriendResponse> findMyFriends(String uid,String fuid,Integer page,Integer pageSize,Integer friendType){
		
		List<RichFriendResponse> response = new ArrayList<RichFriendResponse>();
		
		try{
			//查询我的直接好友
			List<String> friends = new ArrayList<String>();
			if(friendType == 0){
				//直接壕友
				friends = makeHaoDao.fiadMyDirectFriends(uid, page, pageSize);
			}else{
				//间接壕友
				friends = makeHaoDao.fiadMyDirectFriends(fuid, page, pageSize);
			}
			
			if(friends != null && friends.size() > 0){
			
				//批量查询我的好友信息
				List<Map<Object,Object>> friendsInfo = liveUserDao.findMyFriendsByUid(friends);
				
				if(friendsInfo != null && friendsInfo.size() > 0){

					Iterator<Map<Object,Object>> i = friendsInfo.iterator();
					while(i.hasNext()){
						//实例一个壕友对象
						RichFriendResponse richFriend = new RichFriendResponse();
						
						Map<Object,Object> friendMap = i.next();
						
						//壕友头像
						richFriend.setAvatar(
							StringUtils.isEmpty(ObjectUtils.toString(friendMap.get("avatar"))
								)?"":fileUrl+friendMap.get("avatar"));
						//壕友昵称
						String nname = 
							StringUtils.isEmpty(ObjectUtils.toString(friendMap.get("nname"))
								)?(
									StringUtils.isEmpty(ObjectUtils.toString(friendMap.get("uname"))
										)?"":StrUtils.regexReplacePhone(ObjectUtils.toString(friendMap.get("uname")))
								):ObjectUtils.toString(friendMap.get("nname"));
									
						richFriend.setNname(nname);
						//壕友性别  1男 2女  默认为1
						richFriend.setSex(StringUtils.isEmpty(ObjectUtils.toString(friendMap.get("sex"))
							)?String.valueOf(1):ObjectUtils.toString(friendMap.get("sex")));
						//壕友UID
						richFriend.setFuid(ObjectUtils.toString(friendMap.get("uid")));
						
						//壕友等级 1 普通 2 VIP 3 钻石  默认为1
						richFriend.setLevel(StringUtils.isEmpty(ObjectUtils.toString(friendMap.get("fans_rank_no"))
							)?String.valueOf(1):friendMap.get("fans_rank_no").toString());
						//壕友等级名称
						richFriend.setLevelName(StringUtils.isEmpty(ObjectUtils.toString(friendMap.get("fans_rank_name"))
							)?"普通会员":friendMap.get("fans_rank_name").toString());
						//壕友的等级图标
						richFriend.setLevelImage(getFansPic(Integer.parseInt(richFriend.getLevel())));
						richFriend.setLabel(String.valueOf(friendType));//壕友类型 0 直接好友 1 间接壕友
						//壕友的贡献值
						Map<Object,Object> valuemap = getContributionValue(uid,richFriend.getFuid(),richFriend.getLevel(),friendType);
						//直接
						richFriend.setFriendCounts(String.valueOf(sumFriendCounts(richFriend.getFuid())));
						
						richFriend.setBirdCoinStatus(ObjectUtils.toString(valuemap.get("ratioType")));//贡献类型
						richFriend.setBirdCoin(ObjectUtils.toString(valuemap.get("birdCoin")));//贡献鸟币
						response.add(richFriend);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询我的直接好友异常");
		}
		
		return response;
	}
		
	/**
	 * 获取壕友的贡献值
	 * @param fuid 我的壕友的UID
	 * @param uid  登录用户的UID
	 * @param level 用户等级
	 * @param friendType 壕友类型 
	 * @return Map<Object,Object> 
	 * 		   key:"ratioType" 贡献类型 1 预计贡献 2直接贡献	
	 * 		   key:"birdCoin"  贡献鸟币	
	 * 			
	 */
	private Map<Object,Object> getContributionValue(String uid,String fuid,String level,Integer friendType){
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		Double birdCoin = 0.00;
		
		try{
			birdCoin = liveLedgerRecordDao.getContributionValue(fuid, uid);
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询我的壕友对我的贡献值异常");
		}
		
		//计算比例
		Double ratio = 0.0;
		Map<Object,Object> ratiomap = makeHaoDao.selectContributionRatioByRankNo(level);
		if(level.compareTo("4") < 0){
			//金粉等级
			ratiomap = makeHaoDao.selectContributionRatioByRankNo(String.valueOf(4));
		}
		
		if(ratiomap != null && ratiomap.size() > 0){
			
			if(friendType == 0){//壕友类型 直接壕友
				//计算比例
				ratio = ArithUtil.div(Double.parseDouble(ratiomap.get("firstRatio").toString()), 100.00);
			}else{//壕友类型 间接壕友
				//计算比例
				ratio = ArithUtil.div(Double.parseDouble(ratiomap.get("secondRatio").toString()), 100.00);
			}
			
		}else{
			
			if(friendType == 0){//壕友类型 直接壕友
				//计算比例
				ratio = 0.2;
			}else{//壕友类型 直接壕友
				//计算比例
				ratio = 0.1;
			}
		}
		//默认为直接贡献
		map.put("ratioType", 2);//直接贡献
		if(birdCoin == null || birdCoin == 0){
			//直接贡献值
			map.put("ratioType", 1);//预计贡献
			//用户没有充值的情况
			if(level.compareTo("4") < 0){
				//非金粉的情况下,按五千的基础进行预计贡献
				birdCoin = ArithUtil.mul(ratio, 5000.00);
			}else{
				//按照用户等级的最低充值来计算预计贡献值
				birdCoin = StringUtils.isEmpty(ObjectUtils.toString(getLiveFansRanks().get(level))
					)?5000.00:Double.parseDouble(getLiveFansRanks().get(level).toString());//根据用户等级获取用户充值最低金额
				birdCoin = ArithUtil.mul(ratio, birdCoin);
			}
		}
		map.put("birdCoin", birdCoin);//用户贡献值
		return map;
	}
	
	
	/**
	 * 获取壕友的排行图片
	 * @param level 壕友等级
	 * @return
	 */
	private String getFansPic(int level){
		
		String image = "";
		//查询粉丝排行榜
		try{
			List<LiveFansRank> fansRank = liveUserDao.queryLiveFansRankList();
			
			if(fansRank != null && fansRank.size() > 0){
				
				Iterator<LiveFansRank> i = fansRank.iterator();
				
				while(i.hasNext()){
					LiveFansRank rank = i.next();
					
					if(rank.getRankNo().compareTo(level)==0){
						image = fileUrl+rank.getPicUrl();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询粉丝排行信息异常");
		}
		
		return image;
	}

	/**
	 * 通过用户ID查询壕友的UID
	 * @param uid
	 * @return
	 */
	private Integer sumFriendCounts(String uid){
		
		int counts = 0;
		
		try{
			counts = makeHaoDao.sumMyFriendCounts(uid);
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询用户壕友UID异常");
		}
		
		return counts;
	}
	
	
	/**
	 * 获取充值排行
	 * @return
	 */
	private Map<Object,Object> getLiveFansRanks(){
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		//查询所有排行
		List<LiveFansRank> ranks = liveUserDao.queryLiveFansRankList();
		
		for(LiveFansRank rank : ranks){
			map.put(rank.getRankNo(), rank.getRewardLowest());
		}
		
		return map;
	}
	
	
	public static void main(String[] args) {
		String n = String.valueOf(1);
		System.out.println(n);
	}
}
