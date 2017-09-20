package com.xmniao.xmn.core.businessman.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.businessman.dao.SpecialTopicDao;
import com.xmniao.xmn.core.businessman.dao.SpecialTopicPicDao;
import com.xmniao.xmn.core.businessman.dao.SpecialTopicRelationDao;
import com.xmniao.xmn.core.businessman.entity.TSpecialTopic;
import com.xmniao.xmn.core.businessman.entity.TSpecialTopicPic;
import com.xmniao.xmn.core.businessman.entity.TSpecialTopicRelation;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SpecialTopicService
 * 
 * 类描述： 商家(商户)
 * 
 * 创建人： caiyl
 * 
 * 创建时间：
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class SpecialTopicService extends BaseService<TSpecialTopic> {
	
	/**
	 * 专题dao
	 */
	@Autowired
	private SpecialTopicDao specialTopicDao;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SpecialTopicRelationDao specialTopicRelationDao;
	
	@Autowired
	private SpecialTopicPicDao specialTopicPicDao;


	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return specialTopicDao;
	}

	
	
	/**
	 * 获取商家列表信息
	 * 
	 * @param seller
	 * @return
	 */
	public Pageable<TSpecialTopic> getSpecialTopicInfoList(TSpecialTopic specialTopic) {
		Pageable<TSpecialTopic> specialTopicInfoList = new Pageable<TSpecialTopic>(specialTopic);
		// 商家列表内容
		List<TSpecialTopic> specialTopicList = specialTopicDao.getSpecialTopicList(specialTopic);
		//循环计算
		/*for (TSpecialTopic tspecialTopic : specialTopicList) {
			if(tspecialTopic.getUid()!=null && !tspecialTopic.getUid().equals("")){
				String xmerName = xmerDao.getXmerName(tSeller.getUid());
				tspecialTopic.setXmerName(xmerName==null?"":xmerName);
			}
		}*/
		specialTopicInfoList.setContent(specialTopicList);
		// 总条数
		specialTopicInfoList.setTotal(getSellerCount(specialTopic));
		return specialTopicInfoList;
	}
	
	
	public Long getSellerCount(TSpecialTopic specialTopic) {
		return specialTopicDao.specialTopicCount(specialTopic);
	}

	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveActivity(TSpecialTopic specialTopic) {
        this.saveSpecialTopicInfo(specialTopic);
        this.saveSpecialTopicRelation(specialTopic);
	}
	
	private void saveSpecialTopicInfo(TSpecialTopic specialTopic) {
		//保存专题信息
		specialTopic.setUpdateTime(new Date());
		specialTopicDao.insertSelective(specialTopic);
	}
	
	/**
	 * 方法描述：保存专题图片及关联的信息 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月13日下午2:13:31 <br/>
	 * @param specialTopic
	 */
	private void saveSpecialTopicRelation(TSpecialTopic specialTopic) {
		// 保存专题图片
		TSpecialTopicPic specialTopicPic = new TSpecialTopicPic();
		specialTopicPic.setFid(specialTopic.getId());
		specialTopicPic.setPicUrl(specialTopic.getPicUrl());
		specialTopicPic.setUpdateTime(new Date());
		specialTopicPicDao.insertSelective(specialTopicPic);
		
		//专题关联的信息
		String productJson = specialTopic.getProductJson();
		JSONObject fromObject = JSONObject.fromObject(productJson);
		JSONArray jsonArray = fromObject.getJSONArray("json");
		List<TSpecialTopicRelation> specialTopicRelationList = new ArrayList<TSpecialTopicRelation>();
		for (Object object : jsonArray) {
			TSpecialTopicRelation specialTopicRelation = new TSpecialTopicRelation();
			//JSONObject -> bean
			TSpecialTopicRelation bean = (TSpecialTopicRelation) JSONObject.toBean(JSONObject.fromObject(object.toString()), TSpecialTopicRelation.class);
			specialTopicRelation.setSubId(bean.getSubId());
			specialTopicRelation.setSubName(bean.getSubName());
			specialTopicRelation.setTopicType(specialTopic.getTopicType());
			specialTopicRelation.setUpdateTime(new Date());
			
			specialTopicRelationList.add(specialTopicRelation);
		}
		
		if (specialTopicRelationList != null && specialTopicRelationList.size() > 0)
			specialTopic.setSpecialTopicRelation(specialTopicRelationList);// 保存专题类型对应的数据
			for (TSpecialTopicRelation specialTopicRelation : specialTopicRelationList) {
				specialTopicRelation.setFid(specialTopic.getId());
				specialTopicRelationDao.insertSelective(specialTopicRelation);
			}
	}
	
	/**
	 * 初始化专题信息
	 */
	public void initSpecialTopicInfo(Integer topicid, ModelAndView model) {
		if (null != topicid) {
			TSpecialTopic specialTopicInfo = specialTopicDao.selectByPrimaryKey(topicid);
			//更改图片信息
			TSpecialTopicPic specialTopicPic = specialTopicPicDao.getSpecialTopicPicByFid(topicid);
			specialTopicInfo.setPicUrl(specialTopicPic.getPicUrl());
			
			List<TSpecialTopicRelation> specialTopicRelationList = new ArrayList<TSpecialTopicRelation>();
			try {
				specialTopicRelationList = getEditTopicRelationInfoVo(specialTopicInfo);
				specialTopicInfo.setSpecialTopicRelation(specialTopicRelationList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			model.addObject("isType", "update");
			model.addObject("specialTopicData", specialTopicInfo);
		}

	}
	
	public List<TSpecialTopicRelation> getEditTopicRelationInfoVo(TSpecialTopic specialTopicInfo) throws Exception {
		List<TSpecialTopicRelation> specialTopicRelationInfoList = new ArrayList<TSpecialTopicRelation>();
		if (specialTopicInfo != null) {  //KillService.getKill
			int topicType = specialTopicInfo.getTopicType();
			Integer pid = specialTopicInfo.getId();
			// 根据 专题类型 1.商户 2.连锁店 3.区域代理 4.主播 5.预告 6.粉丝券 7.套餐 8.商品
			switch (topicType) {
			case 1:
				// 1.获取商户信息
				specialTopicRelationInfoList = specialTopicRelationDao.getSellerRelationDataList(pid);
				break;
			case 2:
				// 2.获取连锁店信息
				specialTopicRelationInfoList = specialTopicRelationDao.getMultipRelationDataList(pid);
				break;
			case 3:
				// 3.获取区域代理信息
				specialTopicRelationInfoList = specialTopicRelationDao.getAllianceRelationDataList(pid);
				break;
			case 4:
				// 4.获取主播信息
				
				break;				
			case 5:
				// 5.获取预告信息
				specialTopicRelationInfoList = specialTopicRelationDao.getBeforliveRelationDataList(pid);
				break;	
			case 6:
				// 6.获取粉丝券信息
				specialTopicRelationInfoList = specialTopicRelationDao.getFansCouponRelationDataList(pid);
				break;					
			case 7:
				// 7.获取套餐信息
				specialTopicRelationInfoList = specialTopicRelationDao.getSellerPackageRelationDataList(pid);
				break;		
			case 8:
				// 8.获取商品信息
				specialTopicRelationInfoList = specialTopicRelationDao.getProductInfoRelationDataList(pid);
				break;					
			default:
				break;
			}
		}
		//取理前端的隐藏数据
		if (specialTopicRelationInfoList != null && specialTopicRelationInfoList.size() > 0) {
			for (TSpecialTopicRelation bean: specialTopicRelationInfoList){
				// 把对象转换成JSONObject类型
//				JSONObject map = JSONObject.fromObject(bean);
				ObjectMapper objectMapper = new ObjectMapper();
				String relationInfoJson = objectMapper.writeValueAsString(bean);
				bean.setRelationInfoJson(relationInfoJson);
			}	
		}
		
		return specialTopicRelationInfoList;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUpdatActivity(TSpecialTopic specialTopic) {
		//保存专题信息
		specialTopic.setUpdateTime(new Date());
		specialTopicDao.updateByPrimaryKey(specialTopic);	
		
		int pid = specialTopic.getId();
		//删除专题关联的图片信息
		this.deleteSpecialTopicRelation(pid);
		//保存关联的信息
		this.saveSpecialTopicRelation(specialTopic);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteActivity(Integer id) {
		//删除专题信息
		specialTopicDao.deleteByPrimaryKey(id);
        this.deleteSpecialTopicRelation(id);
		
	}
	
	
	private void deleteSpecialTopicRelation(Integer id) {
		//删除专题关联的图片信息
		specialTopicPicDao.deleteSpecialTopicPicByFid(id);
		//删除专题关联的信息
		specialTopicRelationDao.deleteSpecialTopicRelationByFid(id);
	}
	
	/**
	 * 初始化专题信息
	 */
	public void initSpecialTopicRelationInfo(TSpecialTopicRelation specialTopicRelationInfo, Pageable<TSpecialTopicRelation> pageable) throws Exception {
		if (null != specialTopicRelationInfo) {
			int topicid = specialTopicRelationInfo.getFid();
			TSpecialTopic specialTopicInfo = specialTopicDao.selectByPrimaryKey(topicid);
			List<TSpecialTopicRelation> specialTopicRelationList = getEditTopicRelationInfoVo(specialTopicInfo);
			pageable.setContent(specialTopicRelationList);
			long count = specialTopicRelationList.size();
			pageable.setTotal(count);
		}

	}
	
	
}
