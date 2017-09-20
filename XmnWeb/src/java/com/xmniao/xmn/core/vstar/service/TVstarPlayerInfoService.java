/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.liveService.FailureException;
import com.xmniao.xmn.core.thrift.service.liveService.LiveWalletService;
import com.xmniao.xmn.core.thrift.service.liveService.TopList;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.AreaHandler;
import com.xmniao.xmn.core.vstar.dao.TVstarPlayerInfoDao;
import com.xmniao.xmn.core.vstar.entity.TVstarPlayerInfo;
import com.xmniao.xmn.core.vstar.entity.TVstarSellerInfo;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarPlayerInfoService
 * 
 * 类描述： 星食尚大赛选手信息表Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-2 下午4:17:42 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TVstarPlayerInfoService extends BaseService<TVstarPlayerInfo> {

	/**
	 * 注入星食尚大赛选手信息DAO
	 */
	@Autowired
	private TVstarPlayerInfoDao starPlayerInfoDao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return starPlayerInfoDao;
	}
	
	/**
	 * 注入直播钱包服务
	 */
	@Resource(name = "liveWalletServiceServiceClient")
	private ThriftClientProxy liveWalletServiceServiceClient;
	
	/**
	 * 注入会员DAO
	 */
	@Autowired
	private BursDao bursDao;

	/**
	 * 方法描述：获取选手信息列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-12下午3:49:04 <br/>
	 * @param vstarPlayer
	 * @return
	 */
	public List<TVstarPlayerInfo> getListInfo(TVstarPlayerInfo vstarPlayer) {
		List<TVstarPlayerInfo> list = getList(vstarPlayer);
		convertAreaInfo(list);//转换区域信息
		convertSellerInfo(list);//转换签约商家信息
		loadReferrerInfoInBatches(list);//分批次查询推荐人信息
		return list;
	}
	
	/**
	 * 方法描述：设置推荐人手机号码 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-22下午3:07:59 <br/>
	 * @param vstarPlayer
	 */
	public void setReferrerPhone(TVstarPlayerInfo vstarPlayer){
		this.log.info("设置推荐人手机号码：参数"+vstarPlayer);
		String rPhone = vstarPlayer.getrPhone();
		if(StringUtils.isNotBlank(rPhone)){
			Burs burs = new Burs();
			burs.setPhone(rPhone);
			Burs referrer = bursDao.getUrs(burs);
			if(referrer!=null){
				Integer rUid = referrer.getUid();
				vstarPlayer.setReferrerUid(rUid);
			}else{
				vstarPlayer.setReferrerUid(-1);//推荐人为空时，设置推荐人UID为-1
			}
		}
	}
	
	/**
	 * 方法描述：分批次查询推荐人信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-15下午4:31:11 <br/>
	 * @param list
	 */
	private void loadReferrerInfoInBatches(List<TVstarPlayerInfo> list) {
		// TODO Auto-generated method stub
		if(list!=null && list.size()>0){
			int  size = list.size();
			int page=size/PageConstant.OFF_SET;
			int  remainder=size%PageConstant.OFF_SET;
			if(page>0){
				for(int i=0;i<page;i++){
					List<TVstarPlayerInfo> subList = list.subList(i*PageConstant.OFF_SET, (i+1)*PageConstant.OFF_SET);
					loadReferrerInfo(subList);
				}
			}
			
			if(remainder>0){
				List<TVstarPlayerInfo> subList = list.subList(page*PageConstant.OFF_SET, page*PageConstant.OFF_SET+remainder);
				loadReferrerInfo(subList);
			}
		}
	}

	/**
	 * 方法描述：加载推荐人信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-15下午4:32:46 <br/>
	 * @param subList
	 */
	private void loadReferrerInfo(List<TVstarPlayerInfo> subList) {
		// TODO Auto-generated method stub
		if(subList!=null && subList.size()>0){
			List<Integer> uidList=new ArrayList<Integer>();
			for(TVstarPlayerInfo recordItem:subList){
				Integer referrerUid = recordItem.getReferrerUid();
				uidList.add(referrerUid);
			}
			if(uidList!=null && uidList.size()>0){
				List<Burs> ursList = bursDao.getUrsListByUids(uidList.toArray());
				for(TVstarPlayerInfo recordItem:subList){
					Integer referrerUid = recordItem.getReferrerUid();
					for(Burs urs:ursList){
						Integer uid = urs.getUid();
						boolean same=referrerUid !=null && uid!=null && referrerUid.compareTo(uid)==0;
						if(same){
							recordItem.setrName(urs.getNname());
							recordItem.setrPhone(urs.getPhone());
							break;
						}
					}
				}
				
			}
		}
	}

	/**
	 * 方法描述：转换签约商家信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-9上午11:28:08 <br/>
	 * @param list
	 */
	private void convertSellerInfo(List<TVstarPlayerInfo> list) {
		if(list!=null && list.size()>0){
			int  size = list.size();
			int page=size/PageConstant.OFF_SET;
			int  remainder=size%PageConstant.OFF_SET;
			if(page>0){
				for(int i=0;i<page;i++){
					List<TVstarPlayerInfo> subList = list.subList(i*PageConstant.OFF_SET, (i+1)*PageConstant.OFF_SET);
					loadSellerInfo(subList);
				}
			}
			
			if(remainder>0){
				List<TVstarPlayerInfo> subList = list.subList(page*PageConstant.OFF_SET, page*PageConstant.OFF_SET+remainder);
				loadSellerInfo(subList);
			}
		}
		
	}

	/**
	 * 方法描述：加载商家信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-9上午11:49:41 <br/>
	 * @param subList
	 */
	private void loadSellerInfo(List<TVstarPlayerInfo> subList) {
		if(subList!=null && subList.size()>0){
			List<Integer> uidList = new ArrayList<Integer>();
			for(TVstarPlayerInfo playerInfo:subList){
				Integer uid = playerInfo.getUid();
				uidList.add(uid);
			}
			
			if(uidList!=null && uidList.size()>0){
				Map<String, Object> param = new HashMap<String,Object>();
				param.put("uidList", uidList);
				List<Map<String,Object>> signedSellerNumList = getSignedSellerNum(param);
				if(signedSellerNumList!=null && signedSellerNumList.size()>0){
					
					for(TVstarPlayerInfo playerInfo:subList){
						Integer baseUid = playerInfo.getUid();
						
						for(Map<String,Object> signedSellerNumMap:signedSellerNumList){
							Object uid = signedSellerNumMap.get("uid");
							Object sellerNum = signedSellerNumMap.get("sellerNum");
							boolean same=baseUid!=null && uid!=null && sellerNum!=null && baseUid.compareTo(new Integer(uid.toString()))==0;
							if(same){
								playerInfo.setSellerNum(new Integer(sellerNum.toString()));
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 方法描述：将区域信息id转化为name <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-2上午11:25:31 <br/>
	 * @param list
	 */
	private void convertAreaInfo(List<TVstarPlayerInfo> list) {
		String province=null;
		String city=null;
		String area=null;
		for(TVstarPlayerInfo playerInfo:list){
			StringBuffer areaText=new StringBuffer();
			Integer provinceId = playerInfo.getProvinceId();
			Integer cityId = playerInfo.getCityId();
			Integer areaId = playerInfo.getAreaId();
			if(provinceId!=null){
				province = AreaHandler.getAreaHandler().getAreaIdByTitle(provinceId);
			}
			if(cityId!=null){
				city = AreaHandler.getAreaHandler().getAreaIdByTitle(cityId);
			}
			if(areaId != null){
				area = AreaHandler.getAreaHandler().getAreaIdByTitle(areaId);
			}
			if(StringUtils.isNotBlank(province)){
				areaText.append(province);
			}
			
			if(StringUtils.isNotBlank(city)){
				areaText.append("-"+city);
			}
			
			if(StringUtils.isNotBlank(area)){
				areaText.append("-"+area);
			}
			playerInfo.setAreaText(areaText.toString());
		}
		
	}
	
	
	/**
	 * 
	 * 方法描述：获取排名统计列表<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-19上午9:31:02 <br/>
	 * @param playerReq
	 * @return
	 */
	public List<TVstarPlayerInfo> getRankList(TVstarPlayerInfo playerReq){
		return starPlayerInfoDao.getRankList(playerReq);
	}
	
	/**
	 * 
	 * 方法描述：获取排名统计选手总数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-19上午9:31:38 <br/>
	 * @param playerReq
	 * @return
	 */
	public long getRankCount(TVstarPlayerInfo playerReq){
		return starPlayerInfoDao.getRankCount(playerReq);
	}

	/**R
	 * 方法描述：获取选手排名列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-19上午10:01:59 <br/>
	 * @param vstarPlayer
	 * @return
	 */
	public List<TVstarPlayerInfo> getRankListInfo(TVstarPlayerInfo vstarPlayer) {
		List<TVstarPlayerInfo> rankList = getRankList(vstarPlayer);
		try {
			convertAreaInfo(rankList);//转化区域信息
			String startTime = vstarPlayer.getStartTime();
			String endTime = vstarPlayer.getEndTime();
			loadCountInfoInBatches(rankList,startTime,endTime);//分批次查询鸟蛋和粉丝统计信息
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("获取选手排名列表异常:"+e.getMessage());
		}
		return rankList;
	}
	
	
	/**
	 * 方法描述：分批次查询统计 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-15上午11:09:52 <br/>
	 * @param rankList
	 * @param startTime
	 * @param endTime
	 */
	private void loadCountInfoInBatches(List<TVstarPlayerInfo> rankList,
			String startTime, String endTime) {
		if(rankList!=null && rankList.size()>0){
			int  size = rankList.size();
			int page=size/PageConstant.OFF_SET;
			int  remainder=size%PageConstant.OFF_SET;
			if(page>0){
				for(int i=0;i<page;i++){
					List<TVstarPlayerInfo> subList = rankList.subList(i*PageConstant.OFF_SET, (i+1)*PageConstant.OFF_SET);
					loadEggCountInfo(subList,startTime,endTime);//加载指定时间区间的鸟蛋
					loadFansCountInfo(subList,startTime,endTime);//加载指定时间区间的粉丝
				}
			}
			
			if(remainder>0){
				List<TVstarPlayerInfo> subList = rankList.subList(page*PageConstant.OFF_SET, page*PageConstant.OFF_SET+remainder);
				loadEggCountInfo(subList,startTime,endTime);//加载指定时间区间的鸟蛋
				loadFansCountInfo(subList,startTime,endTime);//加载指定时间区间的粉丝
			}
		}
		
		
	}

	/**
	 * 方法描述：加载指定时间区间的鸟蛋与粉丝数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-15上午11:15:17 <br/>
	 * @param subList
	 * @param startTime
	 * @param endTime
	 */
	private void loadFansCountInfo(List<TVstarPlayerInfo> subList,
			String startTime, String endTime) {
		if(subList!=null && subList.size()>0){
			List<Integer> uidList=new ArrayList<Integer>();
			for(TVstarPlayerInfo playerInfo:subList){
				Integer uid = playerInfo.getUid();
				uidList.add(uid);
			}
			
			Map<String,Object> param= new HashMap<String,Object>();
			param.put("uidList", uidList);
			param.put("startTime", startTime);
			param.put("endTime", endTime);
			List<Map<String, Object>> vstarFansList = getVstarFansByUidList(param);
			for(TVstarPlayerInfo playerInfo:subList){
				Integer uid = playerInfo.getUid();
				for(Map<String, Object> vstarFansMap:vstarFansList){
					Object playerUid = vstarFansMap.get("playerUid");
					boolean same=uid!=null && playerUid!=null && uid.compareTo(new Integer(playerUid.toString()))==0;
					if(same){
						Object countFansNum = vstarFansMap.get("fansCount");
						if(countFansNum !=null){
							playerInfo.setCountFansNum(new Integer(countFansNum.toString()));
						}
						break;
					}
				}
			}
		}
		
	}

	/**
	 * 方法描述：加载指定时间区间的鸟蛋 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-14下午5:47:41 <br/>
	 * @param rankList
	 * @param endTime 
	 * @param startTime 
	 */
	private void loadEggCountInfo(List<TVstarPlayerInfo> rankList, String startTime, String endTime) {
		
		try {
			if(rankList!=null && rankList.size()>0){
				List<Integer> uidList=new ArrayList<Integer>();
				for(TVstarPlayerInfo playerInfo:rankList){
					Integer uid = playerInfo.getUid();
					uidList.add(uid);
				}
				
				
				LiveWalletService.Client client = (LiveWalletService.Client)liveWalletServiceServiceClient.getClient();
				
				Map<String,String> paraMap=new HashMap<String,String>();
			    int size = uidList.size();
			    paraMap.put("pageSize", size+"");
			    paraMap.put("uids",com.xmniao.xmn.core.util.StringUtils.listToString(uidList, ',') );
			    paraMap.put("sdate", startTime);
			    paraMap.put("edate", endTime);
			    List<TopList> birdeggTopList = client.BirdeggTopList(paraMap);
			    
			    for(TVstarPlayerInfo playerInfo:rankList){
	            	Integer uid = playerInfo.getUid();
	            	for(TopList birdEggItem:birdeggTopList){
	            		Map<String, String> resultMap = birdEggItem.resultMap;
	            		String uidItem = resultMap.get("uid");
	            		String birdeggCountItem = resultMap.get("birdeggCount")==null?"":resultMap.get("birdeggCount");
	            		boolean isSame= uid!=null && uidItem!=null && uidItem.equals(uid.toString());
	            		if(isSame){
	            			BigDecimal birdEggCount=new BigDecimal(birdeggCountItem);
	            			birdEggCount.setScale(0, BigDecimal.ROUND_UP);
	            			playerInfo.setCountRewardEgg(birdEggCount.intValue());
	            			break;
	            		}
	            	}
	            }
			}
		} catch (FailureException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}finally{
			//关闭连接
			liveWalletServiceServiceClient.returnCon();
		}
		
	}

	/**
	 * 
	 * 方法描述：根据UID查询签约商家数量<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-8下午6:31:06 <br/>
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> getSignedSellerNum(Map<String,Object> param){
		return starPlayerInfoDao.getSignedSellerNum(param);
	}
	
	/**
	 * 
	 * 方法描述：根据UID查询签约商家列表信息<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-8下午6:31:06 <br/>
	 * @param param
	 * @return
	 */
	public List<TVstarSellerInfo> getSellerListByUid(TVstarSellerInfo param){
		return starPlayerInfoDao.getSellerListByUid(param);
	}
	
	/**
	 * 
	 * 方法描述：根据UID查询签约商家数量<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-8下午6:31:06 <br/>
	 * @param param
	 * @return
	 */
	public long getSellerCountByUid(TVstarSellerInfo param){
		return starPlayerInfoDao.getSellerCountByUid(param);
	}
	
	/**
	 * 
	 * 方法描述：根据商家ID集合sellerIdList获取商家流水 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-9上午9:57:44 <br/>
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> getSellerEarningsBySellerIdList(Map<String,Object> param){
		return starPlayerInfoDao.getSellerEarningsBySellerIdList(param);
	}

	/**
	 * 方法描述：查询签约店铺信息(计算流水收益) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-9下午4:35:32 <br/>
	 * @param sellerInfo
	 * @return
	 */
	public List<TVstarSellerInfo> getSellerListInfo(TVstarSellerInfo sellerInfo) {
		List<TVstarSellerInfo> sellerList = getSellerListByUid(sellerInfo);
		if(sellerList!=null && sellerList.size()>0){
			List<Integer> sellerIdList = new ArrayList<Integer>();
			for(TVstarSellerInfo sellerItem:sellerList){
				Integer sellerid = sellerItem.getSellerid();
				sellerIdList.add(sellerid);
			}
			
			if(sellerIdList!=null && sellerIdList.size()>0){
				Map<String, Object> param = new HashMap<String,Object>();
				param.put("sellerIdList", sellerIdList);
				List<Map<String, Object>> sellerEarningsList = starPlayerInfoDao.getSellerEarningsBySellerIdList(param );
				
				if(sellerEarningsList!=null && sellerEarningsList.size()>0){
					
					for(TVstarSellerInfo sellerItem:sellerList){
						Integer baseSellerid = sellerItem.getSellerid();
						BigDecimal moneySum=new BigDecimal(0);
						BigDecimal mikeAmountSum=new BigDecimal(0);
						
						for(Map<String, Object> sellerMap:sellerEarningsList){
							Object sellerid = sellerMap.get("sellerid");
							boolean same=baseSellerid!=null && sellerid!=null && baseSellerid.compareTo(new Integer(sellerid.toString()))==0;
							if(same){
								BigDecimal money = (BigDecimal) sellerMap.get("money");
								Object commission = sellerMap.get("commission");
								JSONObject commissionJson = JSON.parseObject(commission.toString());
								BigDecimal mikeAmount = commissionJson.getBigDecimal("mike_amount");
								moneySum=moneySum.add(money);
								if(mikeAmount!=null){
									mikeAmountSum=mikeAmountSum.add(mikeAmount);
								}
							}
						}
						
						sellerItem.setMoneySum(moneySum);
						sellerItem.setMikeAmountSum(mikeAmountSum);
					}
				}
			}
		}
		
		return sellerList;
	}

	/**
	 * 方法描述：设置默认查询时间 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-14下午3:31:03 <br/>
	 * @param model
	 */
	public void setDefaultTime(ModelAndView model) {
		Date endTime=new Date();
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DATE, -6);
		Date startTime = instance.getTime();
		
		model.addObject("startTime", DateUtil.formatDate(startTime, DateUtil.Y_M_D));
		model.addObject("endTime", DateUtil.formatDate(endTime, DateUtil.Y_M_D));
		
	}
	
	/**
	 * 
	 * 方法描述：根据选手UID集合获取粉丝数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-15上午10:26:56 <br/>
	 * @return
	 */
	public List<Map<String,Object>> getVstarFansByUidList(Map<String,Object> param){
		return starPlayerInfoDao.getVstarFansByUidList(param);
	}
	

}
