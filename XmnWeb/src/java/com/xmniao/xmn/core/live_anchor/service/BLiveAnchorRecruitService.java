/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.businessman.dao.ClassifyDao;
import com.xmniao.xmn.core.businessman.entity.Classify;
import com.xmniao.xmn.core.common.service.TSequenceService;
import com.xmniao.xmn.core.live_anchor.dao.BLiveAnchorImageDao;
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.dao.BVerAnchorRelationDao;
import com.xmniao.xmn.core.live_anchor.dao.TLiveLevelDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorImage;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.BVerAnchorRelation;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;

@Service
public class BLiveAnchorRecruitService  extends BaseService<BVerAnchorRelation> {
	
	@Autowired
	private BVerAnchorRelationDao verAnchorRelationDao;
	
	@Autowired 
	private BLiveAnchorImageDao liveAnchorImageDao;
	
	@Autowired
	private ClassifyDao classifyDao;
	
	/**
	 * 注入支付综合服务
	 */
	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;
	
	/**
	 * 注入直播钱包服务
	 */
	@Resource(name = "liveWalletServiceServiceClient")
	private ThriftClientProxy liveWalletServiceServiceClient;
	
	private final Integer ANCHOR_NUMID = 100007;
	
	/**
	 * 注入序列号服务
	 */
	@Autowired
	private TSequenceService sequenceService;
	
	@Autowired
	private TLiveLevelDao liveLevelDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return verAnchorRelationDao;
	}
	
	/**
	 * 注入主播用户服务
	 */
	@Autowired 
	private BLiverDao liverDao;
	
	
	public Pageable<BVerAnchorRelation> getVerAnchorRelationInfoList(BVerAnchorRelation verAnchorRelation) {
		Pageable<BVerAnchorRelation> verAnchorRelationInfoList = new Pageable<BVerAnchorRelation>(verAnchorRelation);
		List<BVerAnchorRelation> verAnchorRelationList = verAnchorRelationDao.getAnchorRecruitDataList(verAnchorRelation);
		
		/*BLiver liver = new BLiver();
		List<BLiver> liverList = liverDao.getList(liver);
		if (liverList != null && verAnchorRelationList != null){
			for (BLiver bean : liverList) {
				for (BVerAnchorRelation object : verAnchorRelationList) {
                      if (object.getAnchorUid() != null && object.getAnchorUid().equals(bean.getUid()) ){
                    	  object.setLiveType(bean.getRootRole());
                      }
				}
			}
		}*/
	
		verAnchorRelationInfoList.setContent(verAnchorRelationList);
		verAnchorRelationInfoList.setTotal(verAnchorRelationDao.countAnchorRecruit(verAnchorRelation));
	    return verAnchorRelationInfoList;
	}
    

	public int saveUpdateActivity(BVerAnchorRelation verAnchorRelation) throws Exception{
		verAnchorRelation.setUpdateTime(new Date());
		return verAnchorRelationDao.updateByPrimaryKeySelective(verAnchorRelation);
	}

	public BLiver getUrsDetailInfoByUid(Integer uid) throws Exception{
		BLiver liver = liverDao.getUrsDetailInfoByUid(uid);
		
		//风格标签
		String styleLabel = liver.getStyleLabel();
		if (styleLabel != null && styleLabel.indexOf(",") > 0){
			List<Classify> classifyList = classifyDao.getClassifyListByType(styleLabel.split(","));
			if (classifyList != null && classifyList.size() > 0 ){
				String styleLabelDesc = "";
				for (int i = 0; i < classifyList.size(); i ++) {
				   styleLabelDesc += "," + classifyList.get(i).getClassifyName() ;
				}
				if (styleLabelDesc.length() > 1){
					styleLabelDesc = styleLabelDesc.substring(1);
					liver.setStyleLabelDesc(styleLabelDesc);
				}
			}
		}
		
		//日常照片
		List<BLiveAnchorImage> liveAnchorImageList = new ArrayList<>();
		if (liver.getId() != null ){
			Integer id = liver.getId();
			BLiveAnchorImage liveAnchorImage = new BLiveAnchorImage();
			liveAnchorImage.setAnchorId(id);
			liveAnchorImage.setStatus(1);  //默认 1启用 0停用
			liveAnchorImage.setImageType(1); //相册类型 1 主播 2 机器人
			liveAnchorImageList  = liveAnchorImageDao.getList(liveAnchorImage);
		}
		if (liveAnchorImageList != null && liveAnchorImageList.size() > 0)
			liver.setLiveAnchorImageList(liveAnchorImageList);
		return liver;
	}
	
	/**
	 * 方法描述：更改审核状态 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月31日上午11:23:45 <br/>
	 * @param verAnchorRelation
	 * @return
	 * @throws Exception
	 */
	public int updateOptionState(BVerAnchorRelation verAnchorRelation) throws Exception{
		//更改数据库中的推荐状态
		int cout = 0; //更新记录数
		
		//推荐状态 1 审核中 2 审核通过 3 审核被拒
		if (verAnchorRelation != null && verAnchorRelation.getRecommendStatus().equals(2) ){ //审核通过
			List<BVerAnchorRelation> verAnchorRelationList = verAnchorRelationDao.getVerAnchorRelationList(verAnchorRelation);
			if (verAnchorRelationList != null && verAnchorRelationList.size() > 0){
				throw new Exception("开通播权限失败, 该主播信息已审核通过");
			}
			
			//更改主播记录的状态 给主播房间编号
			int uid = verAnchorRelation.getAnchorUid();
			BLiver liver = new BLiver();
			liver.setUid(uid);
			BLiver liverInfo = liverDao.selectBLiver(liver);
		    if (liverInfo != null){
		    	liverInfo.setUtype(1);  //更改主播权限 直播用户类型： 1 主播 2 普通用户
				if ("".equals(liverInfo.getAnchorRoomNo().trim()))
					liverInfo.setAnchorRoomNo(getAnchorRoomNo()); // 设置主播房间编号
		    	liverInfo.setLedgerRatio(new BigDecimal("0.03"));  //产品定为0.03
//		    	TLiveLevel liveLevel = liveLevelDao.getLiveLevelDetail("D");
//				if (liveLevel != null) {
//					liverInfo.setLevelId(liveLevel.getId()); // 设置主播工资等级
//					liverInfo.setLedgerRatio(liveLevel.getGiftAllot() == null ? new BigDecimal("0.03")   //分账比例
//									: liveLevel.getGiftAllot().divide(new BigDecimal("100"), 2,BigDecimal.ROUND_UP));
//				}
		    	
			    int count = liverDao.updateByPrimaryKeySelective(liverInfo);
			    
			    this.log.info("开能主播权限: "+ uid + ", 共计"+count);
		    }else{
		    	log.error("开通播权限失败");
				throw new Exception("开通播权限失败, 未获取到主播信息");
		    }
			
			//添加用户钱包
			/*SynthesizeService.Client client = (SynthesizeService.Client)(synthesizeServiceClient.getClient());
	 		try {
				Map<String,String> paramMap = new HashMap<String,String>();
	 			paramMap.put("uId", String.valueOf(uid ));
	 			paramMap.put("userType", "1");//用户类型 1用户 	2商家	3合作商
	 			
				log.info("添加V客推荐主播用户钱包开始，uid：" + String.valueOf(uid ) + ",userType:1,password:" + paramMap.get("password"));
				Map<String,String> resultMap=client.addWalletMap(paramMap);
				log.info("添加V客推荐主播用户钱包结束，返回值：" + resultMap.get("state"));
				fireLoginEvent(new String[]{"V客推荐主播用户编号",String.valueOf(uid),"添加钱包","添加钱包"},1);
				
			} catch (Exception e) {
				log.error("审核V客推荐主播信息失败", e);
				throw new ApplicationException("添加V客推荐主播用户钱包异常", e, new Object[]{verAnchorRelation});
				
			} finally{
				synthesizeServiceClient.returnCon();
			}
	 		
	 		//添加直播钱包
			com.xmniao.xmn.core.thrift.service.liveService.LiveWalletService.Client client1 = (com.xmniao.xmn.core.thrift.service.liveService.LiveWalletService.Client)(liveWalletServiceServiceClient.getClient());
	 		try {
				log.info("添加V客推荐主播直播钱包开始，uid：" + String.valueOf(uid));
				ResponseData responseData = client1.addLiveWallet(String.valueOf(uid));
				log.info("添加V客推荐主播直播钱包结束，返回值：" + responseData.getState());
				fireLoginEvent(new String[]{"V客推荐主播用户编号",String.valueOf(uid),"添加钱包","添加钱包"},1);
			} catch (Exception e) {
				log.error("添加直播钱包失败", e);
				throw new ApplicationException("添加V客推荐主播直播钱包异常", e, new Object[]{uid});
			} finally{
				liveWalletServiceServiceClient.returnCon();
			}	*/ 		
		}
		
		verAnchorRelation.setUpdateTime(new Date());
		cout = verAnchorRelationDao.updateOptionState(verAnchorRelation);
		
		return cout;
	}
	

	/**
	 * 方法描述：获取主播房间编号 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年7月18日下午2:12:00 <br/>
	 * @return
	 */
	private String getAnchorRoomNo() {
		Long currentSid = sequenceService.getAndUpdateSid(ANCHOR_NUMID);//当前系统序号
		Long nextSid=currentSid+1;
		String nextSidStr = nextSid.toString();
		if(nextSidStr.contains("4")){
			String sid=nextSidStr.replace("4", "5");
			Map<String,Object> param = new HashMap<String,Object>(); 
			param.put("sid", sid);
			param.put("numId", ANCHOR_NUMID);
			sequenceService.updateSpecifiedSid(param);
		}
		return currentSid.toString();
	}


}
