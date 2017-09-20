/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TLiveRechargeComboDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRechargeCombo;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveRechargeComboService
 * 
 * 类描述： 
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-8-31 下午6:37:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveRechargeComboService extends BaseService<TLiveRechargeCombo> {

	/**
	 * 注入直播鸟币充值套餐服务
	 */
	@Autowired
	private TLiveRechargeComboDao liveRechargeComboDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveRechargeComboDao;
	}
	
	/**
	 * 
	 * 方法描述：删除直播鸟币充值套餐
	 * 创建人：  huang'tao
	 * 创建时间：2016-8-31下午6:40:40
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id){
		return liveRechargeComboDao.deleteById(id);
	}
	
	/**
	 * 
	 * 方法描述：获取粉丝级别可充值套餐 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-21下午3:33:34 <br/>
	 * @return
	 */
	public List<TLiveRechargeCombo> getRechargeOfFansRankId(){
		return liveRechargeComboDao.getRechargeOfFansRankId();
	}

	/**
	 * 方法描述：处理绑定类型，将数组转换为字符串 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-1上午9:34:14 <br/>
	 * @param liveRechargeCobo
	 */
	public void dealObjectOrienteds(TLiveRechargeCombo liveRechargeCobo) {
		List<String> objectOrienteds = liveRechargeCobo.getObjectOrienteds();
		if(objectOrienteds!=null && objectOrienteds.size()>0){
			StringBuffer objectOrienteSb=new StringBuffer();
			String objectOriented=null;
			for(String item:objectOrienteds){
				if(StringUtils.isNotBlank(item)){
					objectOrienteSb.append(item).append(",");
				}
			}
			String objectOrienteStr = objectOrienteSb.toString();
			if(objectOrienteStr.endsWith(",")){
				objectOriented = objectOrienteStr.substring(0, objectOrienteStr.lastIndexOf(","));
			}
			liveRechargeCobo.setObjectOriented(objectOriented);
		}else{
			liveRechargeCobo.setObjectOriented("");
		}
	}

	/**
	 * 方法描述：批量更新充值套餐有效状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017/3/24 10:46 <br/>
	 */
	public void updateBatchStatus(Map<String,Object> map){
		  liveRechargeComboDao.updateBatch(map);
	}

}
