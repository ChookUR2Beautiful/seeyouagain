package com.xmniao.xmn.core.reward_dividends.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.manor.dao.BursEarningsRelationChainDao;
import com.xmniao.xmn.core.reward_dividends.constant.RewardDividendsConstant;
import com.xmniao.xmn.core.reward_dividends.dao.BursEarningsRelationDao;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.manorService.ThriftResult;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BursEarningsRelationService
 * 
 * 类描述： 会员收益关系链Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-15 下午4:27:40 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BursEarningsRelationService extends BaseService<BursEarningsRelation>{
	
	private static final int OFFSET = 100;//单次最多查询数据行数
	
	/**
	 * 注入会员收益关系链DAO
	 */
	@Autowired
	private BursEarningsRelationDao earningsRelationDao;
	
	@Autowired
	private BursEarningsRelationChainDao bursEarningsRelationChainDao;
	
	/**
	 * 注入商家DAO
	 */
	@Autowired
	private SellerDao sellerDao;
	
	/**
	 * 注入会员DAO
	 */
	@Autowired
	private BursDao bursDao;
	
	/**
	 * 注入直播会员DAO
	 */
	@Autowired
	private BLiverDao liverDao;
	
	@Resource(name="manorServiceClient")
	private ThriftClientProxy  manorService;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return earningsRelationDao;
	}
	
	/**
	 * 
	 * 方法描述：根据ID查询会员收益关系链 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	public BursEarningsRelation getObjectById(Long id){
		return earningsRelationDao.getObjectById(id);
	}
	
	/**
	 * 
	 * 方法描述：查询会员收益关系链列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	public List<BursEarningsRelation> getList(BursEarningsRelation relation){
		return earningsRelationDao.getList(relation);
	}
	
	/**
	 * 
	 * 方法描述：统计会员收益关系链记录数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	public Long count(BursEarningsRelation relation){
		return earningsRelationDao.count(relation);
	}
	
	/**
	 * 
	 * 方法描述：添加会员收益关系链 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	public void add(BursEarningsRelation relation){
		 earningsRelationDao.add(relation);
	}
	
	/**
	 * 
	 * 方法描述：更新会员收益关系链 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	public Integer update(BursEarningsRelation relation){
		return earningsRelationDao.update(relation);
	}
	
	/**
	 * 
	 * 方法描述：获取等下级会员关系链信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-19下午6:16:09 <br/>
	 * @param bursRelationInfo
	 * @return
	 */
	public List<BursEarningsRelation> getJuniorList(BursEarningsRelation bursRelationInfo){
		return earningsRelationDao.getJuniorList(bursRelationInfo);
	}
	
	/**
	 * 
	 * 方法描述：获取会员收益关系链基础信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-22下午2:23:42 <br/>
	 * @param paramMap
	 * @return
	 */
	public List<BursEarningsRelation> getBaseList(Map<String,Object> paramMap){
		return earningsRelationDao.getBaseList(paramMap);
	}

	/**
	 * 方法描述：获取会员收益关系链详细信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-18上午11:38:58 <br/>
	 * @param relationChain
	 * @return
	 */
	public List<BursEarningsRelation> getDetailList(
			BursEarningsRelation relationChain) {
		List<BursEarningsRelation> baseList = getList(relationChain);
		List<Map<String, Object>> sellerList=new ArrayList<Map<String,Object>>();
		List<Burs> bursList=new ArrayList<Burs>();
		
		List<String> sellerIdList= new ArrayList<String>();
		List<Integer> uids=new ArrayList<Integer>();
		for(BursEarningsRelation bursRelation:baseList){
			Integer sellerId = bursRelation.getReferrerSellerid();
			if(sellerId!=null){
				sellerIdList.add(sellerId.toString());
			}
			
			Integer superiorUid = bursRelation.getSuperiorUid();
			if(superiorUid != null){
				uids.add(superiorUid);
			}
		}

		if(sellerIdList!=null && sellerIdList.size()>0){
			int size = sellerIdList.size();
			// 拆分批量查询
			int queryBatchCount=size/OFFSET;
			int remainder = size % OFFSET;
			if(queryBatchCount>0){
				for(int j=0;j<queryBatchCount;j++){
					List<String> subList = sellerIdList.subList(OFFSET*j, OFFSET*(j+1));
					List<Map<String, Object>> sellerMap = sellerDao.getSellerName(subList);
					if(sellerMap!=null && !sellerMap.isEmpty()){
						sellerList.addAll(sellerMap);
					}
				}
			}
			
			if(remainder > 0){
				List<String> subList = sellerIdList.subList(OFFSET*queryBatchCount,OFFSET*queryBatchCount + remainder );
				List<Map<String, Object>> sellerMap = sellerDao.getSellerName(subList);
				if(sellerMap!=null && !sellerMap.isEmpty()){
					sellerList.addAll(sellerMap);
				}
			}
		}
		
		if(uids!=null && uids.size()>0){
			int size = uids.size();
			// 拆分批量查询
			int queryBatchCount=size/OFFSET;
			int remainder = size % OFFSET;
			if(queryBatchCount>0){
				for(int j=0;j<queryBatchCount;j++){
					List<Integer> subList = uids.subList(OFFSET*j, OFFSET*(j+1));
					List<Burs> subBursList = bursDao.getUrsListByUids(subList.toArray());
					if(subBursList!=null && subBursList.size()>0){
						bursList.addAll(subBursList);
					}
				}
			}
			
			if(remainder > 0){
				List<Integer> subList = uids.subList(OFFSET*queryBatchCount,OFFSET*queryBatchCount + remainder );
				List<Burs> subBursList = bursDao.getUrsListByUids(subList.toArray());
				if(subBursList!=null && subBursList.size()>0){
					bursList.addAll(subBursList);
				}
			}
		}
		
		for(BursEarningsRelation bursRelation:baseList){
			
			Integer objectOriented = bursRelation.getObjectOriented();
			Integer uidRelationChainLevel = bursRelation.getUidRelationChainLevel()==null ? 0:bursRelation.getUidRelationChainLevel();
			//获取上级信息(商家),商家一级会员
			if(objectOriented!=null && objectOriented.intValue()==RewardDividendsConstant.OBJECT_ORIENTED.SELLER && uidRelationChainLevel.intValue()==1){
				
				Integer referrerSellerid = bursRelation.getReferrerSellerid()==null ? 0 : bursRelation.getReferrerSellerid();
				for(Map<String,Object> sellerMap:sellerList){
					Integer sellerId = sellerMap.get("sellerid")==null ? -1 :new Integer(sellerMap.get("sellerid").toString());
					String sellerName=sellerMap.get("sellername")==null ? "":sellerMap.get("sellername").toString();
					if(referrerSellerid.compareTo(sellerId)==0){
						String sellerInfo=null;
						bursRelation.setSellerName(sellerName);
						if(referrerSellerid!=0){
							sellerInfo=sellerId.toString();
							if(StringUtils.isNotBlank(sellerName)){
								sellerInfo=sellerId.toString()+"|"+sellerName;
							}
						}
						bursRelation.setSellerInfo(sellerInfo);
						break;
					}
				}
			}
			
			
			//获取上级信息(会员信息)
			if(objectOriented!=null && 
					(
							objectOriented.intValue()!=RewardDividendsConstant.OBJECT_ORIENTED.SELLER|| 
							(objectOriented.intValue()==RewardDividendsConstant.OBJECT_ORIENTED.SELLER && uidRelationChainLevel.intValue()>1)
					)
			){
				
				Integer superiorUid = bursRelation.getSuperiorUid()==null ? 0 : bursRelation.getSuperiorUid();
				for(Burs bursInfo:bursList){
					Integer uid = bursInfo.getUid()==null ? -1 :bursInfo.getUid();
					String nname = bursInfo.getNname();
					if(superiorUid.compareTo(uid)==0){
						String superiorInfo=null;
						bursRelation.setSuperiorName(nname);
						if(superiorUid != 0){
							superiorInfo=superiorUid.toString();
							if(StringUtils.isNotBlank(nname)){
								superiorInfo=superiorUid.toString()+"|"+nname;
							}
						}
						bursRelation.setSuperiorInfo(superiorInfo);
						break;
					}
				}
			}
			
		}
		
		return baseList;
	}

	/**
	 * 方法描述：设置上级信息及间接收益人信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-19上午11:34:14 <br/>
	 * @param bursRelation
	 * @param model
	 */
	public void setSuperiorInfo(BursEarningsRelation bursRelation, Model model) {
		BursEarningsRelation bursRelationInfo = getObjectById(bursRelation.getId().longValue());
		Integer superiorUid = bursRelationInfo.getSuperiorUid();
		if(superiorUid != null){
			BLiver superiorReq=new BLiver();
			superiorReq.setUid(superiorUid);
			BLiver superiorInfo = liverDao.selectBLiver(superiorReq);
			String phone = superiorInfo.getPhone();
			String nickname = superiorInfo.getNickname();
			StringBuffer superiorSb = new StringBuffer();
			superiorSb.append(phone);
			if(StringUtils.isNotBlank(nickname)){
				superiorSb.append("[").append(nickname).append("]");
			}
			String superior = superiorSb.toString();
			model.addAttribute("superiorInfo", superiorInfo);//上级
			model.addAttribute("superior", superior);//上级手机号码和昵称
		}
		//V客间接上级UID
		Integer indirectUid = bursRelationInfo.getIndirectUid();
		if(indirectUid!=null){
			BLiver superiorReq=new BLiver();
			superiorReq.setUid(indirectUid);
			BLiver indirectInfo = liverDao.selectBLiver(superiorReq);
			model.addAttribute("indirectInfo", indirectInfo);//间接上级
		}else if(null != superiorUid){
			BLiver superiorReq=new BLiver();
			superiorReq.setUid(superiorUid);
			BLiver indirectInfo = liverDao.selectBLiver(superiorReq);
			model.addAttribute("indirectInfo", indirectInfo);//间接上级
		}
		
		
		//下线人数
		long juniors = countJuniorsByUid(bursRelationInfo);
		bursRelationInfo.setJuniors(juniors);
		model.addAttribute("bursRelationInfo", bursRelationInfo);
	}
	
	/**
	 * 
	 * 方法描述：根据UID,会员渠道等统计下线人数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-11下午8:29:40 <br/>
	 * @param bursRelationInfo
	 * @return
	 */
	public long countJuniorsByUid(BursEarningsRelation bursRelationInfo){
		long juniors=0l;
		juniors = earningsRelationDao.countJuniorsNum(bursRelationInfo);
		return juniors;
	}

	/**
	 * 方法描述：获取会员列表(1、同渠道，2、非等下级) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-19下午5:00:07 <br/>
	 * @param liveAnchor
	 * @return
	 */
	public List<BLiver> getLiverInfoExcludeList(BLiver liver) {
	    this.log.info("获取会员列表(1、同渠道，2、非等下级)...");
		List<BLiver> liverInfoList=new ArrayList<BLiver>();
		try {
			liver.setOrder(PageConstant.NOT_ORDER);
			String filterVal = liver.getFilterVal();
			if(StringUtils.isNotBlank(filterVal) && !filterVal.equals("|")){
				boolean contains = filterVal.contains("|");
				String uid="";
				String objectOriented="";
				if(contains){
					String[] split = filterVal.split("\\|");
					uid=split[0];
					objectOriented=split[1];
				}
				liver.setFilterVal(com.xmniao.xmn.core.util.StringUtils.generateUidStr(new Integer(uid)));
				liver.setObjectOriented(new Integer(objectOriented));
			}
			liverInfoList = earningsRelationDao.getLiverInfoList(liver);
		} catch (NumberFormatException e) {
			this.log.error("执行BursEarningsRelationService——>getLiverInfoExcludeList方法异常");
			e.printStackTrace();
		}
		return liverInfoList;
	}

	/**
	 * 方法描述：验证间接上级与上级的关系 <br/>
	 * 1、不在同一关系链（前端可选择直属上级作为间接收益人，但不保存间接上级信息）<br/>
	 * 2、具有相同起始来源<br/>
	 * 3、同一关系链上会员的间接上级不允许绑定同一人
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-27下午3:46:15 <br/>
	 * @param liver
	 * @return
	 */
	public Resultable indirectValidate(BLiver liver) {
		Resultable result=new Resultable();
		
		try {
			Integer uid = liver.getUid();//当前用户的会员UID
			if(uid == null){
				result.setSuccess(false);
				result.setMsg("获取直播会员信息失败!");
				return result;
			}
			
			Integer objectOriented = liver.getObjectOriented();//当前会员收益关系链渠道类型
//			String uidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(uid);
//			liverReq.setUidStr(uidStr);
			//等下级会员信息
//			List<BLiver> juniorList = liverDao.getJuniorList(liverReq);
			BursEarningsRelation bursRelation = new BursEarningsRelation();
			bursRelation.setUid(uid);
			bursRelation.setObjectOriented(objectOriented);
			List<BursEarningsRelation> juniorList = getJuniorList(bursRelation);
			
			BLiver liverReq=new BLiver();
			liverReq.setUid(uid);
			BLiver currentLiver = liverDao.selectBLiver(liverReq);//当前直播会员信息
			if(currentLiver==null){
				result.setSuccess(false);
				result.setMsg("获取直播会员信息失败!");
				return result;
			}
			
			String superiorIdChosen = liver.getSuperiorIdChosen();//选择的上级
			Integer chooseIndirectId = liver.getIndirectId();//选择的间接上级
			if(StringUtils.isBlank(superiorIdChosen) || chooseIndirectId==null){
				result.setSuccess(false);
				result.setMsg("请选择上级和间接上级!");
				return result;
			}
			
			
			Integer superiorId=new Integer(superiorIdChosen);
			BLiver superiorReq=new BLiver();
			superiorReq.setId(superiorId);
			superiorReq.setObjectOriented(objectOriented);
//			BLiver superiorInfo = liverDao.selectByPrimaryKey(superiorId);//待绑定的上级信息
			BLiver superiorInfo = liverDao.selectBLiver(superiorReq);
			String superiorUidRelationChain = superiorInfo.getUidRelationChain();
			
			
			BLiver indirectReq=new BLiver();
			indirectReq.setObjectOriented(objectOriented);
			indirectReq.setId(chooseIndirectId);
//			BLiver chooseIndirectIdInfo = liverDao.selectByPrimaryKey(chooseIndirectId);//待绑定的间接上级信息
			BLiver chooseIndirectIdInfo = liverDao.selectBLiver(indirectReq);
			String choosedIndirectUidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(chooseIndirectIdInfo.getUid());//选择的间接收益人UID
			
			
			//一、查找所有等下级，再拼接上级关系链，组成当前关系链A
			StringBuffer relationChainA=new StringBuffer();
			relationChainA.append(superiorUidRelationChain);
			
			if(juniorList!=null && juniorList.size()>0){
				for(BursEarningsRelation junior:juniorList){
					String juniorUidRelationChain = junior.getUidRelationChain();
					if(StringUtils.isNotBlank(juniorUidRelationChain)){
						relationChainA.append(",").append(juniorUidRelationChain);
					}
				}
			}
			
			String relationChainAStr = relationChainA.toString();
			
			//二、判断选择的间接收益人是否在关系链A中：
			//1、已有间接收益人，不可选择关系链A中会员
			//2、无间接受益人，可选择直接上级作为间接收益人
			Integer currentIndirectUid = currentLiver.getIndirectUid();//当前直播会员前间接上级
			if(currentIndirectUid!=null){
//				String indirectUidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(currentIndirectUid);
				if(relationChainAStr.contains(choosedIndirectUidStr)){
					result.setSuccess(false);
					result.setMsg("已有间接上级，不可选择当前会员关系链中的会员!");
					return result;
				}
			}else{
				if(!chooseIndirectId.toString().equals(superiorIdChosen) && relationChainAStr.contains(choosedIndirectUidStr)){
					result.setSuccess(false);
					result.setMsg("不可选择当前会员关系链中除直接上级以外的会员!");
					return result;
				}
			}
			
			//三、选择的间接收益人，其关系链与关系链A的一级会员需相同
			String chooseIndirectUidRelationChain = chooseIndirectIdInfo.getUidRelationChain();
			boolean toCompare=StringUtils.isNotBlank(chooseIndirectUidRelationChain)&&StringUtils.isNotBlank(relationChainAStr);
			if(toCompare){
				String first1 = chooseIndirectUidRelationChain;
				if(chooseIndirectUidRelationChain.contains(",")){
					first1=chooseIndirectUidRelationChain.substring(0, chooseIndirectUidRelationChain.indexOf(",", 1));//顶级会员
				}
				
				String first2 = relationChainAStr;
				if(relationChainAStr.contains(",")){
					first2 = relationChainAStr.substring(0, relationChainAStr.indexOf(",", 1));//顶级会员
				}
						
				if(!first1.equals(first2)){
					result.setSuccess(false);
					result.setMsg("间接上级与绑定上级不具有相同来源!");
					return result;
				}
			}
			
			//四、选择的间接收益人，与关系链A中的所有间接收益人都需不同。 
			List<Object> uids=new ArrayList<Object>();
			String[] uidStrArray = relationChainAStr.split(",");
			for(String uidStrItem:uidStrArray){
				String uidItem=uidStrItem.replace("^(0+)", "");
				uids.add(uidItem);
			}
			
			StringBuffer relationChianAIndirectUidAll=new StringBuffer();
			List<Object> uidList = new ArrayList<>(new HashSet<>(uids));//去除重复UID
			if(uidList!=null && uidList.size()>0){
//				List<BLiver> relationChainALiverAll = liverDao.selectLiversByUids(uidList.toArray());
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("objectOriented", RewardDividendsConstant.OBJECT_ORIENTED.V_KE);
				paramMap.put("uids", uidList);
				List<BursEarningsRelation> relationChainALiverAll = getBaseList(paramMap);
				for(BursEarningsRelation relationBean:relationChainALiverAll){
					String indirectUidStr=relationBean.getIndirectUid()==null?"":relationBean.getIndirectUid().toString();
					if(StringUtils.isNotBlank(indirectUidStr)){
						relationChianAIndirectUidAll.append(indirectUidStr).append(",");
					}
				}
			}
			
			
			if(relationChianAIndirectUidAll!=null && relationChianAIndirectUidAll.toString().contains(choosedIndirectUidStr)){
				result.setSuccess(false);
				result.setMsg("同一关系链中会员的间接上级不能重复，请重新选择间接上级!");
				return result;
			}
			
			//五、间接收益人，不能互相绑定(A,B不能互相作为对方的间接上级)
			Integer chooseIndirectUid = chooseIndirectIdInfo.getIndirectUid();
			if(chooseIndirectUid!=null && chooseIndirectUid.compareTo(uid)==0){
				result.setSuccess(false);
				result.setMsg("间接上级，不能互相绑定!");
				return result;
			}
			result.setSuccess(true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.log.error("验证间接上级失败:"+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 方法描述：绑定上级信息 <br/>
	 * 0、查询等下级直播会员<br/>
	 * 1、更新关系链<br/>
	 * 2、更新关系链名称<br/>
	 * 3、更新会员在关系链中的等级(遍历0查询的所有等下级用户)<br/>
	 * 4、更新企业级uid(如果为null)<br/>
	 * 5、更新企业级nname<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-7下午4:09:16 <br/>
	 * @param liver
	 * @return
	 */
	public Resultable bindSuperiorInfo(BLiver liver) {
		Resultable result=new Resultable();
		
		try {
			Integer uid = liver.getUid();//当前用户的会员UID
			String uidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(uid);
			Integer objectOriented = liver.getObjectOriented();
			
			
			BursEarningsRelation relationReq = new BursEarningsRelation();
			relationReq.setObjectOriented(objectOriented);
			relationReq.setUid(uid);
			//等下级会员信息
			List<BursEarningsRelation> juniorList= getJuniorList(relationReq);
			
			BLiver liverReq=new BLiver();
			liverReq.setUid(uid);
			liverReq.setObjectOriented(objectOriented);
			BLiver currentLiver = liverDao.selectBLiver(liverReq);//当前会员信息
			if(currentLiver==null){
				result.setSuccess(false);
				result.setMsg("获取直播会员信息失败!");
				return result;
			}
			
			if(StringUtils.isBlank(currentLiver.getUidRelationChain())){
				this.log.error("直播会员 UID:"+uid+",关系链为null,不能绑定上级!");
			}
			
			String superiorIdChosen = liver.getSuperiorIdChosen();
			if(StringUtils.isNotBlank(superiorIdChosen)){
				Integer superiorId=new Integer(superiorIdChosen);
				//待绑定的上级信息 0522
				BLiver superiorReq = new BLiver();
				superiorReq.setId(superiorId);
				superiorReq.setObjectOriented(objectOriented);
//				BLiver superiorInfo = liverDao.selectByPrimaryKey(superiorId);
				BLiver superiorInfo = liverDao.selectBLiver(superiorReq);
				String superiorUidRelationChain = superiorInfo.getUidRelationChain();
				
				for(BursEarningsRelation relationInfo:juniorList){
					String uidRelationChain = relationInfo.getUidRelationChain();
					int indexOf = uidRelationChain.indexOf(uidStr);
					String latterUidRelationChain = uidRelationChain.substring(indexOf);
					
					StringBuffer uidRelationChainSb=new StringBuffer();
					String cycleUidRelationChain = uidRelationChainSb.append(superiorUidRelationChain).append(",").append(latterUidRelationChain).toString();
					
					Integer cycleUid = relationInfo.getUid();
					String  cycleUidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(cycleUid);
					int cycleUidRelationChainLevel = com.xmniao.xmn.core.util.StringUtils.getArrayIndexFromStr(cycleUidRelationChain, cycleUidStr, ",");
					
					relationInfo.setUidRelationChain(cycleUidRelationChain);
					relationInfo.setUidRelationChainLevel(cycleUidRelationChainLevel);
					
					//更新层级关系
					relationInfo.setCreateTime(new Date());
//					updateRelationChainInfo(relationInfo);
					update(relationInfo);
					
					
					//上级发生变更，更新间接上级(全部置空) 
					String superiorUidOld = currentLiver.getSuperiorUid()==null?"":currentLiver.getSuperiorUid();//数据库保存上级
					String superiorUidNew = superiorInfo.getUid()==null?"":superiorInfo.getUid().toString();//当前选择上级
					if(!superiorUidOld.equals(superiorUidNew)){
						BursEarningsRelation bursRelation = new BursEarningsRelation();
						bursRelation.setId(relationInfo.getId());
						earningsRelationDao.updateIndirect2Null(bursRelation);
					}
				}
				
				
				result.setSuccess(true);
				result.setMsg("操作成功!");
			}else{
				result.setSuccess(false);
				result.setMsg("请选择上级");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
		}
		return result;
	}

	/**
	 * 方法描述：绑定间接上级 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-22下午3:13:51 <br/>
	 * @param liver
	 * @param relationReq 
	 */
	public void bindInderectSuperiorInfo(BLiver liver, BursEarningsRelation relationReq) {
		Integer objectOriented = liver.getObjectOriented();
		if(objectOriented==null || objectOriented.intValue()!= RewardDividendsConstant.OBJECT_ORIENTED.V_KE){
			return ;
		}
		String superiorIdChosen = liver.getSuperiorIdChosen()==null?"":liver.getSuperiorIdChosen();//选择的上级
		Integer chooseIndirectId = liver.getIndirectId();
		String chooseIndirectIdStr=liver.getIndirectId()==null?"":liver.getIndirectId().toString();//选择的间接上级
		if(superiorIdChosen.equals(chooseIndirectIdStr)){
			return ;
		}
		
		BLiver chooseIndirectIdInfo = liverDao.selectByPrimaryKey(chooseIndirectId);//待绑定的间接上级信息
		if(chooseIndirectIdInfo==null){
			return ;
		}
		Integer indirectUid = chooseIndirectIdInfo.getUid();
		Integer id = relationReq.getId();
		if(id==null){
			return ;
		}
		BursEarningsRelation relationBean=new BursEarningsRelation();
		relationBean.setId(id);
		relationBean.setIndirectUid(indirectUid);
		earningsRelationDao.update(relationBean);
		
	}

	/**
	 * 方法描述：初始化直播用户下拉框(指定渠道来源)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-24下午5:15:35 <br/>
	 * @param liveAnchor
	 * @return
	 */
	public List<BLiver> getLiverInfoByObjectOriented(BLiver liveAnchor) {
		String objectOriented = liveAnchor.getFilterVal();
		List<BLiver> liverInfoList = new ArrayList<BLiver>();
		try {
			if(StringUtils.isNotBlank(objectOriented)){
				liveAnchor.setObjectOriented(new Integer(objectOriented));
				liveAnchor.setFilterVal(null);
			}
			liverInfoList = earningsRelationDao.getLiverInfoList(liveAnchor);
		} catch (NumberFormatException e) {
			this.log.error("初始化直播用户下拉框(指定渠道来源)异常:");
			e.printStackTrace();
		}
		
		return liverInfoList;
	}

	/**
	 * 方法描述：绑定当前会员是否已有此渠道会员身份 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-25上午10:52:23 <br/>
	 * @param anchor
	 * @return
	 */
	public Resultable haveObjectOriented(BLiver anchor) {
		Resultable result=new Resultable();
		List<BLiver> liverInfoList = earningsRelationDao.getLiverInfoList(anchor);
		if(liverInfoList!=null && liverInfoList.size()>0){
			result.setSuccess(true);
			result.setMsg("当前会员已有此渠道会员身份");
		}else{
			result.setSuccess(false);
			result.setMsg("当前会员无此渠道会员身份");
		}
		return result;
	}

	/**
	 * 方法描述：添加会员关系链 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-25上午11:26:06 <br/>
	 * @param liver
	 * @return
	 */
	public Resultable addRelation(BLiver liver) {
		// TODO Auto-generated method stub
		Resultable result = new Resultable();
		try {
			Integer id = liver.getId();
			Integer objectOriented = liver.getObjectOriented();
			
			BLiver currentliver=null;
			if(id!=null){
				currentliver= liverDao.selectByPrimaryKey(id);//当前会员信息
			}
			
			if(currentliver==null){
				result.setSuccess(false);
				result.setMsg("获取直播会员信息失败!");
				return result;
			}
			
			Integer currentUid = currentliver.getUid();//当前会员UID
			String currentUidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(currentUid);
			
			BursEarningsRelation  earningsRelation=new BursEarningsRelation();
			String uidRelationChain=null;
			int uidRelationChainLevel=1;
			
			String superiorIdChosen = liver.getSuperiorIdChosen();
			BLiver superiorInfo=null;
			if(StringUtils.isNotBlank(superiorIdChosen)){
				Integer superiorId=new Integer(superiorIdChosen);
				//待绑定的上级信息 0522
				BLiver superiorReq = new BLiver();
				superiorReq.setId(superiorId);
				superiorReq.setObjectOriented(objectOriented);
				superiorInfo = liverDao.selectBLiver(superiorReq);
				String superiorUidRelationChain = superiorInfo.getUidRelationChain();
				
				
				if(StringUtils.isBlank(superiorUidRelationChain)){
					result.setSuccess(false);
					result.setMsg("上级会员关系链为空!");
					return result;
				}
				
				StringBuffer uidRelationChainSb=new StringBuffer();
				uidRelationChain = uidRelationChainSb.append(superiorUidRelationChain).append(",").append(currentUidStr).toString();
				uidRelationChainLevel = com.xmniao.xmn.core.util.StringUtils.getArrayIndexFromStr(uidRelationChain, currentUidStr, ",");
			}else{
				uidRelationChain=currentUidStr;
				uidRelationChainLevel = 1;
			}
			
			Integer indirectId = liver.getIndirectId();
			BLiver indirentliver=null;
			if(indirectId!=null){
				indirentliver = liverDao.selectByPrimaryKey(indirectId);//当前会员信息
			}
			
			if(indirentliver!=null){
				Integer indirectUid = indirentliver.getUid();
				if(superiorInfo!=null){
					Integer superiorUid = superiorInfo.getUid();
					if(indirectUid.compareTo(superiorUid)!=0){
						earningsRelation.setIndirectUid(indirectUid);
					}
				}
			}
			
			earningsRelation.setUid(currentUid);
			earningsRelation.setObjectOriented(objectOriented);
			earningsRelation.setUidRelationChain(uidRelationChain);
			earningsRelation.setUidRelationChainLevel(uidRelationChainLevel);
			earningsRelation.setCreateTime(new Date());
			earningsRelationDao.add(earningsRelation);
			
			result.setSuccess(true);
			result.setMsg("添加成功!");
			
		} catch (NumberFormatException e) {
			this.log.error("添加会员关系链异常：");
			e.printStackTrace();
			
			result.setSuccess(false);
			result.setMsg("添加失败!");
		}
		
		return result;
	}

	/**
	 * 方法描述:根据uid查询庄园信息
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月5日上午10:22:10 <br/>
	 * @param uid
	 * @return
	 */
	public BursEarningsRelation getManorByUid(Integer uid) {
		return earningsRelationDao.getByUid(9,uid);
	}

	/**
	 * 方法描述：绑定用户上级
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月5日下午4:14:55 <br/>
	 * @param childId
	 * @param parentId
	 * @return
	 * @throws Exception 
	 */

	public Resultable usrChainBindingParent(Integer childId, Integer parentId) throws Exception {
		HashMap<String,String> map = new HashMap<>();
		map.put("childId", childId+"");
		map.put("parentId", parentId+"");
		ThriftResult result=manorService.doClient("usrChainBindingParent", map);
		if(result.getState()==0){
			return Resultable.success();
		}else{
			return Resultable.defeat(result.getMsg());
		}
		
	}

	/**
	 * 方法描述：根据下级获取所有父级chain
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月5日下午4:24:33 <br/>
	 * @param childId
	 * @return
	 */
	public List<BursEarningsRelation> getManorParent(Integer childId) {
	  return bursEarningsRelationChainDao.getByUid(childId,9);
	}
	
	

}