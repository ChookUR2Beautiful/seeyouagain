/**
 * 
 */
package com.xmniao.xmn.core.reward_dividends.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.live_anchor.dao.BLiveFansRankDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRank;
import com.xmniao.xmn.core.reward_dividends.dao.TLivePrivilegeDao;
import com.xmniao.xmn.core.reward_dividends.dao.TVerExcitationDetailDao;
import com.xmniao.xmn.core.reward_dividends.dao.TVerExcitationProjectDao;
import com.xmniao.xmn.core.reward_dividends.dao.TVerExcitationReceiveDao;
import com.xmniao.xmn.core.reward_dividends.entity.TLivePrivilege;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationDetail;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationProject;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationReceive;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;


@Service
public class RechargeRewardService extends BaseService<TVerExcitationProject> {
	@Autowired
	private TLivePrivilegeDao livePrivilegeDao;
	
	@Autowired
	private TVerExcitationProjectDao verExcitationProjectDao;
	
	@Autowired
	private TVerExcitationDetailDao verExcitationDetailDao;
	
	@Autowired
	private BLiveFansRankDao liveFansRankDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {

		return verExcitationProjectDao;
	}

	@Autowired
	private TVerExcitationReceiveDao verExcitationReceiveDao;
	
	@Autowired
	private BursService bursService;
	
	/**
	 * 注入会员DAO
	 */
	@Autowired
	private BursDao bursDao;
	
	/**
	 * 方法描述：查询显示数据 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:48:50 <br/>
	 * @param verExcitationProject
	 * @return
	 */
	
	public Pageable<TLivePrivilege> getLivePrivilegeInfoList(TLivePrivilege livePrivilege) {
		Pageable<TLivePrivilege> verLivePrivilegeInfoList = new Pageable<TLivePrivilege>(livePrivilege);
		
		boolean findflag = true;
		Long total = (long) 0;
		//通过电话号码查询
		if ( (livePrivilege.getPhone() != null && !"".equals(livePrivilege.getPhone()) ) || (livePrivilege.getNickname() != null&& !"".equals(livePrivilege.getNickname()))  ){
			Burs burs = new Burs();
			if (!"".equals(livePrivilege.getPhone())) {
				String phone = livePrivilege.getPhone();
				burs.setPhone(phone);
			}
			if (!"".equals(livePrivilege.getNickname())) {
				String nickname = livePrivilege.getNickname();
				burs.setNname(nickname);
			}
			
			List<Burs> bursList = bursService.getUrsList(burs);
			if (bursList != null && bursList.size() > 0 ){
				Integer uid = 0;
				uid = bursList.get(0).getUid();
				if (livePrivilege.getUid() == null ){
					livePrivilege.setUid(uid);
				}
			}else{
				findflag = false;
			}
		}
		
		List<TLivePrivilege> verLivePrivilegeList = new ArrayList<TLivePrivilege>();
		if (findflag){
		    verLivePrivilegeList = livePrivilegeDao.getLivePrivilegeList(livePrivilege);
		    total = livePrivilegeDao.countLivePrivilege(livePrivilege);
		}
		if (verLivePrivilegeList != null){//
			BLiveFansRank fansRank = new BLiveFansRank();
			fansRank.setRankType(2);  //V客类型
			List<BLiveFansRank> liveFansRankList = liveFansRankDao.getList(fansRank);  //v客等级
			
			List<Integer> uids=new ArrayList<Integer>();
			for (TLivePrivilege bean: verLivePrivilegeList){
				if (liveFansRankList != null){
					for (BLiveFansRank object: liveFansRankList){
						if (bean.getLedgerLevel()!= null && bean.getLedgerLevel().equals(Integer.parseInt(object.getId().toString()))){
							 bean.setRankName(object.getRankName());
						}
					}
				}
				//查询用户uid
				Integer uid = bean.getUid();
				if(uid != null){
					uids.add(uid);
				}
				
				if (bean.getCreateTime() != null){//返还时间
					bean.setStartRefundTime(bean.getCreateTime());
					Date now = bean.getCreateTime();  
					Date date = new Date();
					if ("A".equals(bean.getProjectName()) ){
						date = DateUtils.addMonths(now, 12);  //返还10月
					}else{
						date = DateUtils.addMonths(now, 1); //返还1月
					}
					bean.setEndRefundTime(date);
				}
			}
			
			if(uids!=null && uids.size()>0){
				List<Burs> bursList = bursDao.getUrsListByUids(uids.toArray());
				if (bursList != null){
					for (TLivePrivilege bean: verLivePrivilegeList){
						for (Burs object: bursList){
							if (bean.getUid()!= null && bean.getUid().equals(object.getUid()) ){
								if (object.getNname() != null)   //昵称
									bean.setNickname(object.getNname());
								if (object.getPhone() != null)  //手机号码
									bean.setPhone(object.getPhone());
							}
						}
					}
				}
			}
			
		
		}
	
		verLivePrivilegeInfoList.setContent(verLivePrivilegeList);
		verLivePrivilegeInfoList.setTotal(total);
	    return verLivePrivilegeInfoList;
	}
    
    
	/**
	 * 方法描述：保存前端数据 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:48:10 <br/>
	 * @param verExcitationProject
	 */
	@Transactional(propagation=Propagation.NEVER)
	public void saveActivity(TVerExcitationProject verExcitationProject) throws Exception{
		
		//须区分是那种等级的券信息
		List<TVerExcitationDetail> verExcitationDetailList = initVerExcitationDetail(verExcitationProject);
		
		List<TVerExcitationProject> verExcitationProjectList = verExcitationProject.getVerExcitationProjectList();
		if (verExcitationProjectList != null){
			for (TVerExcitationProject bean: verExcitationProjectList){
				// RankId: b_live_fans_rank.id
				if (bean.getRankId() != null && bean.getProjectName()!= null){  
					Integer period = 0;  //总奖励期数 A方案-12期, B方案-1期
					if ("A".equals(bean.getProjectName()) ){
						period = 12;
					}else {
						period = 1;
					}
					bean.setPeriod(period.byteValue());
					//单次保存
					int count = verExcitationProjectDao.insertSelective(bean);
					log.info("保存V客充值奖励方案, 共计：["+count+"]条记录");
					
					saveVerExcitationDetail(bean, verExcitationDetailList);
				}
				
			}
		}
		
	}
	
	private List<TVerExcitationDetail> initVerExcitationDetail(TVerExcitationProject verExcitationProject) {
		List<TVerExcitationDetail> verExcitationDetailList = new ArrayList<TVerExcitationDetail>();
		
		//专题关联的信息
		String productJson = verExcitationProject.getProductJson();
		JSONObject fromObject = JSONObject.fromObject(productJson);
		JSONArray jsonArray = fromObject.getJSONArray("json");
		for (Object object : jsonArray) {
			TVerExcitationDetail verExcitationDetail = new TVerExcitationDetail();
			//JSONObject -> bean
			TVerExcitationDetail bean = (TVerExcitationDetail) JSONObject.toBean(JSONObject.fromObject(object.toString()), TVerExcitationDetail.class);
			verExcitationDetail.setCid(bean.getCid());
			verExcitationDetail.setNum(bean.getNum());
			verExcitationDetail.setRankId(bean.getRankId());
//			verExcitationDetail.setWorth(bean.getWorth());
			verExcitationDetailList.add(verExcitationDetail);
		}
      
		return verExcitationDetailList;
	}
	
	private void saveVerExcitationDetail(TVerExcitationProject verExcitationProject, List<TVerExcitationDetail> verExcitationDetailList) {
		List<TVerExcitationDetail> list = new ArrayList<TVerExcitationDetail>();
		Integer type = 0;
		
		for (TVerExcitationDetail bean: verExcitationDetailList){
			if (bean.getRankId().equals(verExcitationProject.getRankId()) ){
				bean.setPid(verExcitationProject.getId()); //奖励方案，t_ver_excitation_project主键
				//奖励类型 1优惠券 2实物(红酒)现金
				if (bean.getCid() != null)
					type = 1;
				else
					type = 2;
				bean.setType(type); 
				list.add(bean);
				
			}
		}
		// 保存红酒信息
		if ("B".equals(verExcitationProject.getProjectName()) && verExcitationProject.getWorth() != null && !verExcitationProject.getWorth().equals(0.00)) {
			TVerExcitationDetail bean = new TVerExcitationDetail();
			bean.setWorth(verExcitationProject.getWorth());
			bean.setPid(verExcitationProject.getId());
			type = 2; //奖励类型 1优惠券 2实物(红酒)现金
			bean.setType(type); 
			list.add(bean);
		}
		
		
		int count = 0;
		if (list != null && list.size() > 0)
			count = verExcitationDetailDao.insertVerExcitationDetailBatch(list);
		
		log.info("保存V客充值奖励方案明细, 共计：["+count+"]条记录");
	}
		
	
	/**
	 * 方法描述：删除内容 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:42:01 <br/>
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id) throws Exception{
		//删除关联的公式数据
		verExcitationDetailDao.deleteByPid(id);
		
		return verExcitationProjectDao.deleteByPrimaryKey(id);
	}
	
	
	public int deleteRelationData(String projectName) throws Exception{
		int num = 0;
		if(!StringUtils.isBlank(projectName)){
			List<TVerExcitationProject> verExcitationProjectList = verExcitationProjectDao.getExcitationProjectByProjectName(projectName);
		    String ids = "";
			for (TVerExcitationProject bean : verExcitationProjectList) {
				ids += ", " + bean.getId().toString();
			}
			if (ids.length() > 1) {
				ids = ids.substring(1);
				
				// 删除关联的公式数据
				int count = verExcitationDetailDao.deleteByPids(ids.split(","));
				log.info("删除V客充值奖励方案明细, 共计：[" + count + "]条记录");
				
				num = verExcitationProjectDao.deleteByIds(ids.split(","));
				log.info("删除V客充值奖励方案, 共计：[" + num + "]条记录");
			}
		}
		
		return num;
	}		
	
	
	/**
	 * 方法描述：更新前端数据 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:48:31 <br/>
	 * @param verExcitationProject
	 */
	public int saveUpdateActivity(TVerExcitationProject verExcitationProject) throws Exception{
//		verExcitationProject.setUpdateTime(new Date());
		return verExcitationProjectDao.updateByPrimaryKeySelective(verExcitationProject);
	}
	
	public TVerExcitationProject getVerExcitationProjectData(TVerExcitationProject verExcitationProject) {
		TVerExcitationProject verExcitationProjectInfo = new TVerExcitationProject();
		Integer recordId = verExcitationProject.getId();
		if (recordId != null) {
			verExcitationProjectInfo = verExcitationProjectDao.selectByPrimaryKey(recordId);
		}

		return verExcitationProjectInfo;
	}
	
	public List<TVerExcitationDetail> getVerExcitationDetailList(){
		List<TVerExcitationDetail> verExcitationDetailList = verExcitationDetailDao.getVerExcitationDetail();
		if (verExcitationDetailList != null && verExcitationDetailList.size() > 0) {
			for (TVerExcitationDetail bean: verExcitationDetailList){
				if (bean.getType().equals(1)){  //如果是券信息
                 //	把对象转换成JSONObject类型
//				    JSONObject map = JSONObject.fromObject(bean);
					ObjectMapper objectMapper = new ObjectMapper();
					String relationInfoJson;
					try {
						relationInfoJson = objectMapper.writeValueAsString(bean);
						bean.setProductJson(relationInfoJson);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
			}
		}
		
		return verExcitationDetailList;
	}
	
	
	public List<TVerExcitationReceive> getVerExcitationReceiveDetailList(Integer type, Integer uid){
		List<TVerExcitationReceive> verExcitationDetailList = verExcitationReceiveDao.selectUnclaimedByType(type, uid);
		
		return verExcitationDetailList;
	}
	

}
