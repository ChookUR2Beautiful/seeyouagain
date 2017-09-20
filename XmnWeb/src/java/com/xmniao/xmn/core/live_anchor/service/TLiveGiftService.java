/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.dao.TLiveGiftBagDao;
import com.xmniao.xmn.core.live_anchor.dao.TLiveGiftDao;
import com.xmniao.xmn.core.live_anchor.dao.TLiveGiftDetailDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGift;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGiftBag;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGiftBagSet;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGiftDetail;
import com.xmniao.xmn.core.system_settings.entity.Category;
import com.xmniao.xmn.core.system_settings.service.CategoryService;
import com.xmniao.xmn.core.util.PageConstant;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveGiftService
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-17下午2:23:38
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TLiveGiftService extends BaseService<TLiveGift> {

	/**
	 * 注入直播礼物服务
	 */
	@Autowired
	private TLiveGiftDao liveGiftDao;
	
	/**
	 * 注入直播礼物详情服务
	 */
	@Autowired
	private TLiveGiftDetailDao liveGiftDetailDao;
	
	/**
	 * 注入礼包服务
	 */
	@Autowired
	private TLiveGiftBagDao liveGiftBagDao;
	
	/**
	 * 注入商家行业分类服务
	 */
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 注入redisTemplate
	 */
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveGiftDao;
	}
	
	
	/**
	 * 
	 * 方法描述：添加礼包并返回礼包ID
	 * 创建人： huang'tao
	 * 创建时间：2016-8-30上午10:09:27
	 * @param liveGiftBag
	 * @return
	 */
	public Integer  addBagReturnId(TLiveGiftBag liveGiftBag){
		return liveGiftBagDao.addBagReturnId(liveGiftBag);
	}
	
	/**
	 * 
	 * 方法描述：查询礼包数量
	 * 创建人： huang'tao
	 * 创建时间：2016-8-30上午10:57:15
	 * @param liveGiftBag
	 * @return
	 */
	public long selectBagCount(TLiveGiftBag liveGiftBag){
		return liveGiftBagDao.selectBagCount(liveGiftBag);
	}
	
	/**
	 * 
	 * 方法描述：获取第一条礼包记录
	 * 创建人： huang'tao
	 * 创建时间：2016-8-30上午11:11:35
	 * @return
	 */
	public TLiveGiftBag selectTheFirstGiftBag(){
		return liveGiftBagDao.selectTheFirstGiftBag();
	}

	/**
	 * 
	 * 方法描述：批量添加礼包礼物
	 * 创建人： huang'tao
	 * 创建时间：2016-8-30上午11:41:13
	 * @param giftBagSet
	 * @return
	 */
	public Integer addBatchGifts(List<TLiveGiftBagSet> giftBagSet){
		return liveGiftBagDao.addBatchGifts(giftBagSet);
	}
	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： huang'tao
	 * 创建时间：2016-8-29下午8:48:11
	 * @param ids 礼物ids
	 * @param giftBagId 礼包id
	 * @return
	 */
	public Resultable giftBagSetAddBatch(String ids, String giftBagId) {
		Resultable result=new Resultable();
		try {
			List<TLiveGift> giftList=new ArrayList<TLiveGift>();
			List<TLiveGiftBagSet> giftBagSetList= new ArrayList<TLiveGiftBagSet>();
			if(ids!=null){
				giftList=liveGiftDao.getListByIds(ids.split(","));
			}
			
			if(giftList!=null && giftList.size()>0){
				for(TLiveGift liveGift:giftList){
					TLiveGiftBagSet giftBagSet=new TLiveGiftBagSet();
					giftBagSet.setGiftBagId(new Integer(giftBagId));
					giftBagSet.setGiftId(liveGift.getId());
					giftBagSet.setGiftNums(LiveConstant.GIFT_NUMS_DEFAULT);
					giftBagSet.setCreateTime(new Date());
					
					giftBagSetList.add(giftBagSet);
				}
				Integer count = addBatchGifts(giftBagSetList);
				if(count>0){
					result.setSuccess(true);
					result.setMsg("添加成功!");
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败");
		}
		
		return result;
	}


	/**
	 * 方法描述：获取礼包ID
	 * 创建人： huang'tao
	 * 创建时间：2016-8-30上午10:48:11
	 * @return
	 */
	public Integer getBagId() {
		Integer bagId=null;
		Long bagCount = selectBagCount(null);
		if(bagCount.compareTo(new Long(0))>0){
			TLiveGiftBag liveGiftBag = selectTheFirstGiftBag();
			bagId=liveGiftBag.getId();
		}else{
			TLiveGiftBag giftBag=new TLiveGiftBag();
			giftBag.setGiftBagName(LiveConstant.GIFT_BAG_NAME_DEFAULT);
			giftBag.setCreateTime(new Date());
			bagId=addBagReturnId(giftBag);
		}
		return bagId;
	}


	/**
	 * 方法描述：获取礼包礼物列表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-30下午3:44:16
	 * @param liveGiftBagSet
	 * @return
	 */
	public List<TLiveGiftBagSet> getGiftBagSetList(
			TLiveGiftBagSet liveGiftBagSet) {
		return liveGiftBagDao.selectBagSetList(liveGiftBagSet);
	}

	/**
	 * 
	 * 方法描述：获取指定礼包可添加的礼物
	 * 创建人： huang'tao
	 * 创建时间：2016-8-31上午10:49:08
	 * @param giftBagId
	 * @return
	 */
	public List<TLiveGift> getGiftListToAdd(TLiveGift liveGift){
		return liveGiftDao.getGiftListToAdd(liveGift);
	}
	
	
	/**
	 * 
	 * 方法描述：获取指定礼包可添加的礼物数量
	 * 创建人： huang'tao
	 * 创建时间：2016-8-31上午11:19:13
	 * @param giftBagId
	 * @return
	 */
	public Long getGiftToAddCount(TLiveGift liveGift){
		return liveGiftDao.getGiftToAddCount(liveGift);
	}

	/**
	 * 方法描述：获取礼包礼物数量
	 * 创建人： huang'tao
	 * 创建时间：2016-8-30下午3:44:40
	 * @param liveGiftBagSet
	 * @return
	 */
	public Long giftBagSetCount(TLiveGiftBagSet liveGiftBagSet) {
		return liveGiftBagDao.giftBagSetCount(liveGiftBagSet);
	}
	
	public Integer updateGiftNums(TLiveGiftBagSet liveGiftBagSet) {
		return liveGiftBagDao.updateGiftNums(liveGiftBagSet);
	}


	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： Administrator
	 * 创建时间：2016年9月18日上午11:30:13
	 * @param id
	 */
	public void deleteBagSetById(Integer id) {
		liveGiftBagDao.deleteBagSetById(id);
	}


	/**
	 * 方法描述：同步礼物详细信息 <br/>
	 * 1、删除原有数据<br/>
	 * 2、插入新数据<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-31上午10:32:20 <br/>
	 * @param liveGift
	 */
	public void syncLiveGiftDetailInfo(TLiveGift liveGift) {
		try {
			Integer giftKind = liveGift.getGiftKind();//礼物类型
			Integer gid = liveGift.getId();//礼物ID
			if(giftKind==null || gid==null){
				return;
			}
			liveGiftDetailDao.deleteByGid(new Long(gid));
			
			//商品礼物
			if(LiveConstant.GIFT_KIND.GIFT_KIND_PRODUCT==giftKind){
				List<TLiveGiftDetail> giftDetailList = liveGift.getGiftDetailList();
				//清除非法数据
				clearInvalidData(giftDetailList);
				boolean isNotEmpty= giftDetailList!=null && giftDetailList.size()>0;
				if(isNotEmpty){
					for(TLiveGiftDetail giftDetail:giftDetailList){
						giftDetail.setGid(gid);
						giftDetail.setGiftKind(giftKind);
					}
					liveGiftDetailDao.addBatch(giftDetailList);
				}
			}
			
			//套餐礼物
			if(LiveConstant.GIFT_KIND.GIFT_KIND_COMBO==giftKind){
				TLiveGiftDetail giftDetail=new TLiveGiftDetail();
				giftDetail.setGid(gid);
				giftDetail.setGiftKind(giftKind);
				giftDetail.setCategory(liveGift.getCategory());
				giftDetail.setGenre(liveGift.getGenre());
				liveGiftDetailDao.add(giftDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("同步礼物详细信息失败："+e.getMessage(), e);
		}
	}
	
	


	/**
	 * 方法描述：移除空数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-31上午11:21:30 <br/>
	 * @param giftDetailList
	 */
	private void clearInvalidData(List<TLiveGiftDetail> giftDetailList) {
		boolean isNotEmpty= giftDetailList!=null && giftDetailList.size()>0;
		if(isNotEmpty){
			Iterator<TLiveGiftDetail> iterator = giftDetailList.iterator();
			while(iterator.hasNext()){
				TLiveGiftDetail giftDetail = iterator.next();
				if(giftDetail.getPname()==null && giftDetail.getPvIds()==null&&giftDetail.getPvValue()==null&& giftDetail.getCategory()==null&& giftDetail.getGenre()==null){
					iterator.remove();
				}
			}
		}
	}


	/**
	 * 方法描述：加载礼物详细信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-1上午10:23:36 <br/>
	 * @param list
	 */
	public void convertGiftDetailList(List<TLiveGift> list) {
		// TODO Auto-generated method stub
		boolean isNotEmpty = list!=null && list.size()>0;
		if(isNotEmpty){
			List<Category> categoryList = categoryService.getLdAll();
			for(TLiveGift liveGift:list){
				Integer giftKind = liveGift.getGiftKind();
				for(Category categoryBean:categoryList){
					Integer tid = categoryBean.getTid();
					String tradename = categoryBean.getTradename();
					if(LiveConstant.GIFT_KIND.GIFT_KIND_COMBO==giftKind && tid!=null){
						String category = liveGift.getCategory();
						if(StringUtils.isBlank(category)){
							liveGift.setCategoryName("全部");
						}else if(category.equals(tid.toString())){
							liveGift.setCategoryName(tradename);
						}
						
						String genre = liveGift.getGenre();
						if(StringUtils.isNotBlank(genre) && genre.equals(tid.toString())){
							liveGift.setGenreName(tradename);
						}
						
					}
				}
				
				String categoryName = liveGift.getCategoryName();
				if(categoryName!=null){
					String comboCategoryName=categoryName;
					String genreName = liveGift.getGenreName()==null?"":liveGift.getGenreName();
					if(StringUtils.isNotBlank(genreName)){
						comboCategoryName+="-"+genreName;
					}
					liveGift.setComboCategoryName(comboCategoryName);
				}
				
			}
		}
	}
	
	
	/**
	 * 
	 * 方法描述：获取礼物基础信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-26上午11:19:44 <br/>
	 * @param liveGift
	 * @return
	 */
	public List<TLiveGift> getBaseList(TLiveGift liveGift){
		return liveGiftDao.getBaseList(liveGift);
	}


	/**
	 * 方法描述：更新礼物缓存列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-26上午11:58:31 <br/>
	 * @param liveGift
	 */
	public void updateGiftCache() {
		TLiveGift liveGiftParam = new TLiveGift();
		liveGiftParam.setStatus(1);//是否有效：  1 启用  0 未启用
		liveGiftParam.setLimit(PageConstant.PAGE_LIMIT_NO);
		List<TLiveGift> baseList = liveGiftDao.getBaseList(liveGiftParam);
		if(baseList!=null && baseList.size()>0){
			String giftJson = JSONObject.toJSONString(baseList);
			redisTemplate.opsForValue().set(LiveConstant.LIVE_GIFT_KEY, giftJson);
		}
	}
	
	/**
	 * 
	 * 方法描述：更新礼物详情缓存 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-26下午4:12:41 <br/>
	 * @param liveGift
	 */
	public void updateGiftDetailRedis(TLiveGift liveGift){
		Integer id = liveGift.getId();
		if(id!=null){
			TLiveGift giftDetail = getObject(new Long(id));
			if(giftDetail!=null){
				redisTemplate.opsForValue().set(LiveConstant.LIVE_GIFT_DETAIL_KEY+id, JSONObject.toJSONString(giftDetail));
			}
		}
	}


	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-26下午4:27:17 <br/>
	 * @param liveGift
	 */
	public void deleteGiftDetailRedis(TLiveGift liveGift) {
		// TODO Auto-generated method stub
		Integer id = liveGift.getId();
		Integer status = liveGift.getStatus();
		boolean toDel=id !=null && status !=null && status.intValue()==0;// status 是否有效：  1 启用  0 未启用
		boolean toAdd=id !=null && status !=null && status.intValue()==1;// status 是否有效：  1 启用  0 未启用
		if(toDel){
			redisTemplate.delete(LiveConstant.LIVE_GIFT_DETAIL_KEY+id);
		}
		
		if(toAdd){
			updateGiftDetailRedis(liveGift);
		}
	}
}
