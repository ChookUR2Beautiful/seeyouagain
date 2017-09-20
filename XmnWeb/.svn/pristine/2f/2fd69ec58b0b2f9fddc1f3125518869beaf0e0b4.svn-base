/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.dao.TFloatAdvertDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TFloatAdvert;

/**
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveShareService
 * 
 * 类描述： 直播邀请分享Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-22 上午10:01:32 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TFloatAdvertService extends BaseService<TFloatAdvert> {
	
	@Autowired
	private TFloatAdvertDao floatAdvertDao;

	/**
	 * 注入主播(用户)服务
	 */
	@Autowired
	private BLiverDao liverDao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return floatAdvertDao;
	}
	
	
	public Pageable<TFloatAdvert> getFloatAdvertInfoList(TFloatAdvert floatAdvert) {
		Pageable<TFloatAdvert> floatAdvertInfoList = new Pageable<TFloatAdvert>(floatAdvert);
		List<TFloatAdvert> floatAdvertList = floatAdvertDao.getFoatAdvertList(floatAdvert);
		//需要计算出主播名称

		//用户类型 1 主播 2 普通用户
		BLiver liveAnchor = new BLiver();
		liveAnchor.setUtype(LiveConstant.UTYPE_ANCHOR);
		List<BLiver> anchorList = liverDao.selectFloatAdvertLivesInfo(liveAnchor);
		//通过计算
		for (int i = 0 ; i < floatAdvertList.size(); i ++){  //循环beanList;
			TFloatAdvert bean = floatAdvertList.get(i);
			List<String> uidList = new ArrayList<>();
			String uidStr = bean.getAnchorIds() == null ? "" :bean.getAnchorIds().trim();  
			if (uidStr != null && !"".equals(uidStr) ){
				if (uidStr.indexOf(",") > -1) { // 选择了主播
					String[] uids = uidStr.toString().split(",");
					for (String uid : uids) {
						uidList.add(uid);
					}
				}else{
					uidList.add(uidStr);
				}
				if (uidList.size() > 0){
					String nicknames = "";
					for (int j = 0; j < uidList.size(); j++){
						Integer uid = Integer.parseInt(uidList.get(j));
						for (BLiver beanLiver : anchorList) {
							if (uid.equals(beanLiver.getUid())){
								nicknames += beanLiver.getNickname() == null ? ""
										: ", " + beanLiver.getNickname();
							}
						}
					}
					if (nicknames.length() > 0) {
						nicknames = nicknames.substring(1);
						bean.setNickname(nicknames);
					}
				}
			}
		}
		
		floatAdvertInfoList.setContent(floatAdvertList);
		floatAdvertInfoList.setTotal(getAdvertInfoCount(floatAdvert));
	    return floatAdvertInfoList;
	}
	
    public Long getAdvertInfoCount(TFloatAdvert floatAdvert){
    	return floatAdvertDao.floatAdvertCount(floatAdvert);
    }
    
    
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveActivity(TFloatAdvert floatAdvert) {
		//保存套餐信息
		floatAdvert.setStatus(2);  //未开始
		floatAdvert.setUpdateTime(new Date());
		floatAdvertDao.insertSelective(floatAdvert);
	}
	
	public int deleteById(Integer id) {
		return floatAdvertDao.deleteByPrimaryKey(id);
	}
	
	
	public TFloatAdvert getFloatAdvertInfoById(Integer id) {
		return floatAdvertDao.selectByPrimaryKey(id);
	}

	
	/**
	 * 方法描述：更改广告上线下线 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月3日下午3:18:10 <br/>
	 * @param objects
	 * @param status
	 * @return
	 */
	public int updateStatusOption(TFloatAdvert floatAdvert){
		floatAdvert.setUpdateTime(new Date());
		return floatAdvertDao.updateStatusOption(floatAdvert);
	}
}
