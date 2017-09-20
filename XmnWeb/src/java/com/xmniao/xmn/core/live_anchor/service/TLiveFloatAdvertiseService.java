/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.live_anchor.dao.TLiveFloatAdvertiseDao;
import com.xmniao.xmn.core.live_anchor.dao.TLiveFloatAdvertisePositionDao;
import com.xmniao.xmn.core.live_anchor.entity.TFloatAdvert;
import com.xmniao.xmn.core.live_anchor.entity.TLiveFloatAdvertise;
import com.xmniao.xmn.core.live_anchor.entity.TLiveFloatAdvertisePosition;

@Service
public class TLiveFloatAdvertiseService  extends BaseService<TLiveFloatAdvertise> {
	
	@Autowired
	private TLiveFloatAdvertiseDao liveFloatAdvertiseDao;
	
	@Autowired
	private TLiveFloatAdvertisePositionDao liveFloatAdvertisePositionDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveFloatAdvertiseDao;
	}
	

	/**
	 * 方法描述：悬浮广告信息列表显示初始化<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月19日下午3:29:19 <br/>
	 * @param liveFloatAdvertise
	 * @return
	 */
	public Pageable<TLiveFloatAdvertise> getLiveFloatAdvertiseInfoList(TLiveFloatAdvertise liveFloatAdvertise) {
		Pageable<TLiveFloatAdvertise> liveFloatAdvertiseInfoList = new Pageable<TLiveFloatAdvertise>(liveFloatAdvertise);
		
		List<TLiveFloatAdvertise> liveFloatAdvertiseList = liveFloatAdvertiseDao.getLiveFloatAdvertiseList(liveFloatAdvertise);
		if (liveFloatAdvertiseList != null) {
			for (TLiveFloatAdvertise bean : liveFloatAdvertiseList) {// 获取悬浮广告的显示位置信息
				Integer relationId = bean.getId();
				List<TLiveFloatAdvertisePosition> liveFloatAdvertisePositionList = liveFloatAdvertisePositionDao.getLiveFloatAdvertisePositionList(relationId);
				if (liveFloatAdvertisePositionList != null && liveFloatAdvertisePositionList.size() > 0) {
					bean.setLiveFloatAdvertisePositionList(liveFloatAdvertisePositionList);
					String showPositionDesc = "";
					for (TLiveFloatAdvertisePosition object : liveFloatAdvertisePositionList) {//
						if (object.getShowPosition() != null){
							String positionDesc = "";
							int position = object.getShowPosition();
							switch (position) {
							case 0://
								positionDesc = "美食主页";
								break;
							case 1://
								positionDesc = "直播主页";
								break;
							case 2:// 等同于 1
								positionDesc = "我的主页";
								break;
							case 3:// 等同于 1
								positionDesc = "直播间";
								break;
							default:
								positionDesc = "我的主页";
								break;
							}
							showPositionDesc += ", "+positionDesc ;
						}
					}
					if (showPositionDesc.length() > 0 )
						showPositionDesc = showPositionDesc.substring(1);
					bean.setShowPositionDesc(showPositionDesc);  //位置信息
				}

			}
		}
		
		liveFloatAdvertiseInfoList.setContent(liveFloatAdvertiseList);
		liveFloatAdvertiseInfoList.setTotal(liveFloatAdvertiseDao.countLiveFloatAdvertise(liveFloatAdvertise));
		
	    return liveFloatAdvertiseInfoList;
	}
	
	/**
	 * 方法描述：悬浮广告操作初始化 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月19日下午3:29:24 <br/>
	 * @param id
	 * @return
	 */
	public TLiveFloatAdvertise getLiveFloatAdvertiseInfo(Integer id) {
		
		TLiveFloatAdvertise liveFloatAdvertise = liveFloatAdvertiseDao.selectByPrimaryKey(id);
		//获取悬浮广告的显示位置信息
		Integer relationId = liveFloatAdvertise.getId();
		List<TLiveFloatAdvertisePosition> liveFloatAdvertisePositionList = liveFloatAdvertisePositionDao.getLiveFloatAdvertisePositionList(relationId);
		if (liveFloatAdvertisePositionList != null && liveFloatAdvertisePositionList.size() > 0)  {
//			liveFloatAdvertise.setLiveFloatAdvertisePositionList(liveFloatAdvertisePositionList);
			String showPosition = "";
			for (TLiveFloatAdvertisePosition object: liveFloatAdvertisePositionList){//
				if (object.getShowPosition() != null){//去除格式化为空的数据
					showPosition += "," + object.getShowPosition();
				}
			}
			if (showPosition.length() > 1)
				showPosition = showPosition.substring(1);
			
			liveFloatAdvertise.setShowPosition(showPosition);
		}
		
	    return liveFloatAdvertise;
	}



	/**
	 * 方法描述：悬浮广告新增保存方法 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月19日下午3:29:27 <br/>
	 * @param liveFloatAdvertise
	 * @return
	 * @throws Exception
	 */
	public int saveAddActivity(TLiveFloatAdvertise liveFloatAdvertise) throws Exception{
		int result = 0;
		log.info("TLiveFloatAdvertiseService-->saveUpdateActivity-------新增悬浮广告开始");
		result = liveFloatAdvertiseDao.insertSelective(liveFloatAdvertise);
		this.saveLiveFloatAdvertisePosition(liveFloatAdvertise);
		
		log.info("TLiveFloatAdvertiseService-->saveUpdateActivity-------新增悬浮广告结束");
		return result;

	}
	
	private void saveLiveFloatAdvertisePosition(TLiveFloatAdvertise liveFloatAdvertise){
		Integer relationId = liveFloatAdvertise.getId();  //悬浮广告id
		
		List<TLiveFloatAdvertisePosition> liveFloatAdvertisePositionList = liveFloatAdvertise.getLiveFloatAdvertisePositionList();
		if (liveFloatAdvertisePositionList != null && liveFloatAdvertisePositionList.size() > 0){  //显示位置信息
			//目标数据
			List<TLiveFloatAdvertisePosition> liveFloatAdvertisePositionData = new ArrayList<TLiveFloatAdvertisePosition>();
			for (TLiveFloatAdvertisePosition object: liveFloatAdvertisePositionList){//
				if (object.getShowPosition() != null){//去除格式化为空的数据
					TLiveFloatAdvertisePosition bean = new TLiveFloatAdvertisePosition();
					bean.setShowPosition(object.getShowPosition());
					bean.setRelationId(relationId);
					liveFloatAdvertisePositionData.add(bean);
				}
			}
		    int count = liveFloatAdvertisePositionDao.addBatchDetail(liveFloatAdvertisePositionData);
			log.info("TLiveFloatAdvertiseService-->saveUpdateActivity-------新增悬浮广告位置信息记录:"+ count);
		}
		
		log.info("TLiveFloatAdvertiseService-->saveUpdateActivity-------新增悬浮广告结束");
	}
	

	/**
	 * 方法描述：悬浮广告修改保存方法<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月19日下午3:29:30 <br/>
	 * @param liveFloatAdvertise
	 * @return
	 * @throws Exception
	 */
	public int saveUpdateActivity(TLiveFloatAdvertise liveFloatAdvertise) throws Exception {
		log.info("TLiveFloatAdvertiseService-->saveUpdateActivity-------修改悬浮广告开始");
		int result = 0;
		if (liveFloatAdvertise != null) {
			liveFloatAdvertise.setUpdateTime(new Date());
			this.saveLiveFloatAdvertisePosition(liveFloatAdvertise);
			//修改悬浮广告
			result = liveFloatAdvertiseDao.updateByPrimaryKeySelective(liveFloatAdvertise);
		}
		log.info("TLiveFloatAdvertiseService-->saveUpdateActivity-------修改悬浮广告结束");
		return result ;
	}
	

	/**
	 * 方法描述：悬浮广告删除方法 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月19日下午3:29:33 <br/>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById(String ids) throws Exception {
		int result = 0;
		int count = liveFloatAdvertisePositionDao.deleteByRelationIds(ids.split(","));
		log.info("TLiveFloatAdvertiseService-->deleteById-------删除悬浮广告关联显示位置信息记录数="+count+"条");
		// 删除关联的好看推荐
		result = liveFloatAdvertiseDao.delete(ids.split(","));
		log.info("TLiveFloatAdvertiseService-->deleteById-------删除悬浮广告记录数="+result+"条");
		return result;
	}
	
	

	
	public int updateStatusOption(String ids, @RequestParam(value="state")Integer state){
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		paramsMap.put("status", state);
		paramsMap.put("ids", ids.split(","));
		return liveFloatAdvertiseDao.updateOptionStatus(paramsMap);
	}
}
