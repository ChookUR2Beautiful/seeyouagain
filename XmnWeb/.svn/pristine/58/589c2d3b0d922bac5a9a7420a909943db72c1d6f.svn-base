/**
 * 
 */
package com.xmniao.xmn.core.manor.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.manor.dao.BursEarningsRelationChainDao;
import com.xmniao.xmn.core.manor.dao.ManorFlowerBranchMapper;
import com.xmniao.xmn.core.manor.dao.ManorFlowerDao;
import com.xmniao.xmn.core.manor.dao.ManorFlowerRelationDao;
import com.xmniao.xmn.core.manor.dao.ManorHoneyManageDao;
import com.xmniao.xmn.core.manor.dao.ManorInfoDao;
import com.xmniao.xmn.core.manor.entity.BursEarningsRelationChain;
import com.xmniao.xmn.core.manor.entity.ManorFlowerBranch;
import com.xmniao.xmn.core.manor.entity.TManorFlower;
import com.xmniao.xmn.core.manor.entity.TManorHoneyManage;
import com.xmniao.xmn.core.manor.entity.TManorInfo;
import com.xmniao.xmn.core.reward_dividends.dao.BursEarningsRelationDao;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.ResponsePageList;
import com.xmniao.xmn.core.thrift.service.manorService.FailureException;
import com.xmniao.xmn.core.thrift.service.manorService.ManorPropsThriftService;
import com.xmniao.xmn.core.thrift.service.manorService.ManorService;
import com.xmniao.xmn.core.thrift.service.manorService.ResponseData;
import com.xmniao.xmn.core.thrift.service.manorService.ManorRelatedService.Client;
import com.xmniao.xmn.core.thrift.service.manorService.ResultList;
import com.xmniao.xmn.core.thrift.service.manorService.ThriftResult;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;


@Service
public class ManorMemberService extends BaseService<TManorInfo> {

	
	/**
	 * 注入主播(用户)服务
	 */
	@Autowired
	private ManorInfoDao manorInfoDao;
	
	@Autowired
	private BursDao bursDao;
	
	/**
	 * 寻蜜鸟用户Service
	 */
	@Autowired
	private BursService bursService;
	
	/**
	 * 注入获取庄园的服务
	 */
	@Resource(name = "manorRelatedServiceClient")
	private ThriftClientProxy manorRelatedServiceClient;	
	@Resource(name = "manorPropsThriftServiceClient")
	private ThriftClientProxy manorPropsThriftService;	
	
	
	@Autowired
	private	ManorHoneyManageDao manorHoneyManageDao;
	
	/**
	 * 注入会员收益关系链DAO
	 */
	@Autowired
	private BursEarningsRelationDao earningsRelationDao;
	
	@Autowired
	private BursEarningsRelationChainDao bursEarningsRelationChainDao;
	
	
	@Autowired
	private ManorFlowerRelationDao manorFlowerRelationDao;
	
	@Autowired
	private ManorFlowerDao manorFlowerDao;
	
	@Autowired
	private ManorFlowerBranchMapper manorFlowerBranchMapper;
	
	@Resource(name = "manorServiceClient")
	private ThriftClientProxy manorService;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return manorInfoDao;
	}

	
	public Pageable<TManorInfo> getManorInfoInfoList(TManorInfo manorInfo) {
		Pageable<TManorInfo> manorInfoInfoList = new Pageable<TManorInfo>(manorInfo);
		
		//通过会员昵称查询
		/*if (manorInfo.getNickname() != null && !"".equals(manorInfo.getNickname())) {
			Burs burs = new Burs();
			String nickname = manorInfo.getNickname();
			burs.setNname(nickname);
			List<Burs> bursList = bursService.getUrsList(burs);
			if (bursList != null && bursList.size() > 0) {
				Integer uid = bursList.get(0).getUid();
				manorInfo.setUid(uid);
			}
		}*/
		
		List<TManorInfo> manorInfoList = this.getManorInfoList(manorInfo);
	
		manorInfoInfoList.setContent(manorInfoList);
		manorInfoInfoList.setTotal(manorInfoDao.countManorMember(manorInfo));
		
	    return manorInfoInfoList;
	}
	
	
	
	public List<TManorInfo> getManorInfoList(TManorInfo manorInfo) {
		List<TManorInfo> manorInfoList = manorInfoDao.getManorMemberList(manorInfo);
		//通过关系链查找上级
		if (manorInfoList != null && manorInfoList.size() > 0){
			this.initManorInfoList(manorInfoList);
		}
		return manorInfoList;
	}
	
	
	private void initManorInfoList(List<TManorInfo> manorInfoList){
		List<Long> uids = new ArrayList<>();  //用户列表　
		List<Integer> superUids = new ArrayList<Integer>();  //推荐人列表
		
		for (TManorInfo ursRrelation: manorInfoList){  
			Integer uid = ursRrelation.getUid();//取下级
			uids.add(uid.longValue());
			//查询出当前用户的的下线(非当前用户的关系链信息) com.xmniao.xmn.core.util.StringUtils.generateUidStr(uid);
			Long juniors=0l;
			BursEarningsRelationChain bursEarningsRelationChain = new BursEarningsRelationChain();
			bursEarningsRelationChain.setObjectOriented(9);
			bursEarningsRelationChain.setParentId(uid.longValue());
			bursEarningsRelationChain.setUid(uid.longValue());
//			juniors = earningsRelationDao.countJuniorsNum(bursRelationInfo);
			
			juniors =  bursEarningsRelationChainDao.countBursEarningsRelationChain(bursEarningsRelationChain);
			ursRrelation.setLowerLevelNumber(Integer.parseInt(juniors.toString()) );
			
			//推荐人(当前用户关系链)
			if (ursRrelation.getSuperUid() != null)// 推荐人id
				superUids.add(ursRrelation.getSuperUid());
		}
		
		//查询推荐人名称信息
		if (superUids.size() > 0) {
			List<Burs> bursList = bursDao.getUrsListByUids(superUids.toArray());
			for (TManorInfo manorInfo: manorInfoList){
				for (Burs object : bursList) {
					if (manorInfo.getSuperUid() != null && manorInfo.getSuperUid().equals(object.getUid()) ){
						manorInfo.setSuperName(object.getNname());
					}
				}
			}
		}

		try {
			//连接接口进行查询
//			List<Long> uids = new ArrayList<>();
			com.xmniao.xmn.core.thrift.service.manorService.ManorRelatedService.Client client = (com.xmniao.xmn.core.thrift.service.manorService.ManorRelatedService.Client) (manorRelatedServiceClient.getClient());
			log.info("查询获取用户的花蜜和阳光开始");
			ResponsePageList  response = client.getEarningList (uids);
			if(response.getDataInfo().getState()!= 0){
				log.error("调用获取用户的花蜜和阳光失败");
				throw new RuntimeException("获取用户的花蜜和阳光失败, 错误信息:"+ response.getDataInfo().getState());
			}
			log.info("获取用户的花蜜和阳光结束，返回值：" + response.getDataInfo().getState());
			//接口返回用户的花蜜和阳光
			List<Map<String, String>> sunAndNectarProfitList = response.getPageList();
			
			this.getManorSunAndNectarProfitFromMap(manorInfoList, sunAndNectarProfitList);
			
		} catch (Exception e) {
			log.error("获取用户的花蜜和阳光失败", e);
//			throw new ApplicationException("修改指定收益类型提现手续费率异常", e, new Object[] { liveDepositorsTaxes.getType() });
			
		} finally {
			manorRelatedServiceClient.returnCon();
		}
	}
	
	
	public int saveUpdateActivity(TManorInfo manorInfo) throws Exception{
		manorInfo.setUpdateTime(new Date());
		return manorInfoDao.updateByPrimaryKeySelective(manorInfo);
	}
	
	public TManorInfo getManorInfoData(TManorInfo manorInfo) {
		TManorInfo manorInfoInfo = new TManorInfo();
		Integer recordId = manorInfo.getId();
		if (recordId != null) {
			manorInfoInfo = manorInfoDao.selectByPrimaryKey(recordId);
		}
		
		return manorInfoInfo;
	}
	
	public List<TManorFlower> getManorMemberLowerLevelList(TManorInfo manorInfo) {
		List<TManorFlower> manorFlowerRelationList = new ArrayList<TManorFlower>();
		if (manorInfo.getUid() != null){
			List<Integer> uids=new ArrayList<Integer>();
			//取下级
//			List<BursEarningsRelation> juniorList = earningsRelationDao.getLowerBursEarningsRelationList(earningsRelationDao.getBursEarningsRelationByUid(bursRelationInfo));
			
			BursEarningsRelationChain bursEarningsRelationChain = new BursEarningsRelationChain();
			bursEarningsRelationChain.setObjectOriented(9);
			bursEarningsRelationChain.setParentId(manorInfo.getUid().longValue());
			bursEarningsRelationChain.setUid(manorInfo.getUid().longValue());
//			juniors = earningsRelationDao.countJuniorsNum(bursRelationInfo);
			List<BursEarningsRelationChain> juniorList =  bursEarningsRelationChainDao.getBursEarningsRelationChainList(bursEarningsRelationChain);
	        for (BursEarningsRelationChain bean: juniorList){  //所有的下级信息
	        	uids.add(bean.getUid().intValue());
	        }
		
			//获取用户信息
			if(uids!=null && uids.size()>0){ //获取下级人员信息
				//取用户名称
				List<Burs> bursList = bursDao.getUrsListByUids(uids.toArray());
				//取用户当前贡献的花朵数
				manorFlowerRelationList = manorFlowerDao.getCurrentFlowerCount(uids.toArray());
				//取用户累计贡献的花朵数
				List<TManorFlower> totalFlowerRelationList = manorFlowerDao.getTotalFlowerCount(uids.toArray());
				
				if (manorFlowerRelationList != null && bursList != null) {
					for (TManorFlower manorFlowerRelation : manorFlowerRelationList) { // 所有的下级信息
						for (Burs burs : bursList) { // 所有的下级信息
							if ( manorFlowerRelation.getFloristUid().equals(burs.getUid()) ){  //用户uid相同
								if (burs.getUname() != null)  //用户名称
									manorFlowerRelation.setFloristName(burs.getNname());
							}
						}
					}
				}
				if (manorFlowerRelationList != null && totalFlowerRelationList != null) {
					for (TManorFlower manorFlowerRelation : manorFlowerRelationList) { // 所有的下级信息
						for (TManorFlower totalFlowerRelation : totalFlowerRelationList) { // 所有的下级信息
							if ( manorFlowerRelation.getFloristUid().equals(totalFlowerRelation.getFloristUid()) ){  //用户uid相同
								if (totalFlowerRelation.getTotalFlowerRelationCount() != null)  //用户名称
									manorFlowerRelation.setTotalFlowerRelationCount(totalFlowerRelation.getTotalFlowerRelationCount());
							}
						}
					}
				}
			}
	    }
		
		return manorFlowerRelationList;
	}
	
	
	/**
	 * 方法描述：阳光花密数量 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月20日下午3:58:17 <br/>
	 * @param manorInfoList
	 * @param sunAndNectarProfitList
	 * @throws Exception
	 */
	public void getManorSunAndNectarProfitFromMap(List<TManorInfo> manorInfoList, List<Map<String, String>> sunAndNectarProfitList) throws Exception{
		TManorHoneyManage manorHoneyManage = manorHoneyManageDao.getManorHoneyManageData( new TManorHoneyManage());
		
		for (TManorInfo manorInfo: manorInfoList){
			for (Map<String, String> object : sunAndNectarProfitList) {
				Integer uid = new Integer(object.get("uid"));
				if (manorInfo.getUid().equals(uid) ){
					//阳光的总数量
//					manorInfo.setSunNumber(new Double(object.get("sunNumber") == null ? "0" : object.get("sunNumber")) );
					//仓库数量
//					manorInfo.setRepositoryNumber(new Double(object.get("repositoryNumber") == null ? "0" : object.get("repositoryNumber")));
					//花蜜的总数量
					Double number = new Double(object.get("number")== null ? "0" : object.get("number"));
					//向上取整:Math.ceil()   //只要有小数都+1
					//向下取整:Math.floor()   //不取小数
					Double nectarStoreNumber = Math.floor(number/manorHoneyManage.getPotHoney().doubleValue());  //取整
					manorInfo.setNectarStoreNumber(nectarStoreNumber);
					manorInfo.setNectarNumber(number % manorHoneyManage.getPotHoney().doubleValue() );//取余余数 
	
		            //累计收益鸟币
					BigDecimal converCoin = new BigDecimal(object.get("converCoin")== null ? "0" : object.get("converCoin"));
					converCoin = converCoin.setScale(2, BigDecimal.ROUND_HALF_UP);  
					manorInfo.setConverCoin(converCoin);
				}
			}
		}
	}


	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月6日上午9:57:24 <br/>
	 * @param burs
	return
	 */
	public List<BursEarningsRelation> getUsrChainParentChoose(BursEarningsRelation burs) {
		burs.setObjectOriented(9);
		return earningsRelationDao.getListByType(burs);
	}




	/**
	 * 方法描述：判断该用户是否有庄园下级
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月7日下午4:45:06 <br/>
	 * @param uid
	 * @return
	 */
	public boolean hasChlid(Integer uid) {
		List<TManorInfo> list= manorInfoDao.getBySuperUid(uid);
		if(list==null||list.size()==0){
			return false;
		}
		return true;
	}


	/**
	 * 方法描述：获取没有下级的花田
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月8日上午9:57:19 <br/>
	 * @param flowerBranch
	 * @return
	 */
/*	public List<ManorFlowerBranch> getUsrChainEditParentChoose(ManorFlowerBranch flowerBranch) {
		List<Burs> ursList=null;
		
		if(flowerBranch!=null){
			if(StringUtils.isNotBlank(flowerBranch.getPhone())||flowerBranch.getUid()!=null){
				Burs userInfo = new Burs();
				userInfo.setPhone(flowerBranch.getPhone());
				userInfo.setUid(flowerBranch.getUid());
				ursList = bursService.getUrsList(userInfo);
			}
		}
		 List<ManorFlowerBranch> manorFlowerBranchs = manorFlowerBranchMapper.getNotChildsFlowerRelation(ursList);
		 setUserMsg(manorFlowerBranchs);
		 return manorFlowerBranchs;
	}*/


	/**
	 * 方法描述：设置用户信息
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月8日上午10:52:43 <br/>
	 * @param manorFlowerBranchs
	 *//*
	private void setUserMsg(List<ManorFlowerBranch> manorFlowerBranchs) {
		if(manorFlowerBranchs==null||manorFlowerBranchs.size()<=0){
			return;
		}
		List<Integer> uids= new ArrayList<>();
		for (ManorFlowerBranch manorFlowerBranch : manorFlowerBranchs) {
			uids.add(manorFlowerBranch.getUid());
		}
		List<Burs> urs = bursService.getUrsListByUids(uids.toArray());
		one:for (ManorFlowerBranch manorFlowerBranch : manorFlowerBranchs) {
			for (int i=0;i<urs.size();i++) {
				Burs burs = urs.get(i);
				if(manorFlowerBranch.getUid()==burs.getUid()){
					manorFlowerBranch.setPhone(burs.getPhone());
					urs.remove(i);
					continue one;
				}
			}
		}
	}*/


	/**
	 * 方法描述：获取庄园下的画田
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月8日下午2:18:02 <br/>
	 * @param uid
	 * @return
	 */
	public List<ManorFlowerBranch> getFlowerBranch(Integer uid) {
		List<ManorFlowerBranch> manorFlowerBranchs = manorFlowerBranchMapper.getByUid(uid);
		return manorFlowerBranchs;
	}


	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月12日上午10:38:36 <br/>
	 * @param childId
	 * @param parentId
	 * @param location
	 * @return
	 */
	public Resultable bindingManorParent(Integer childId, Integer parentId, Integer location) {
		HashMap<String,String> map = new HashMap<>();
		map.put("childId", childId+"");
		map.put("parentId", parentId+"");
		map.put("location", location+"");
		ThriftResult result = null;
		try {
			result = manorService.doClient("flowerChainBindingParent", map);
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
		if(result.getState()==0){
			return Resultable.success();
		}
		return Resultable.defeat(result.getMsg());
	}


	/**
	 * 方法描述：调用支付接口获取用户道具
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月12日上午11:40:07 <br/>
	 * @param uid
	 * @return 
	 */
	public List<Map<String, String>> statisticsUserProps(Integer uid) {
		ManorPropsThriftService.Client client =	(com.xmniao.xmn.core.thrift.service.manorService.ManorPropsThriftService.Client) manorPropsThriftService.getClient();
		try{
			ResultList statisticsUserProps = client.statisticsUserProps(uid.longValue());
			if(statisticsUserProps.getCode()==1){
				return statisticsUserProps.getValues();
			}
		} catch (TException e) {
			log.error(e);
		}
		return new ArrayList<>();
	}


	/**
	 * 方法描述：调用业务服务方法
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月12日下午2:12:19 <br/>
	 * @param uid
	 * @return 
	 * @throws TException 
	 * @throws FailureException 
	 */
	public ResponseData activateManorThrift(Integer uid) throws FailureException, TException {
		Map<String,String> map = new HashMap<>();
		map.put("uid", uid+"");
		map.put("transNo",System.currentTimeMillis()+"");
		ManorService.Client client = (com.xmniao.xmn.core.thrift.service.manorService.ManorService.Client) manorService.getClient();
		return client.activateManor(map);
	}
		
}
