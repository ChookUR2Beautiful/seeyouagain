/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.dao.TFansCouponIssueRefDao;
import com.xmniao.xmn.core.live_anchor.dao.TLiveCouponDao;
import com.xmniao.xmn.core.live_anchor.entity.TFansCouponIssueRef;
import com.xmniao.xmn.core.live_anchor.entity.TLiveCoupon;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveBroadcast
 * 
 * 类描述： 直播粉丝券服务Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-24 下午3:11:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveCouponService extends BaseService<TLiveCoupon> {
	
	/**
	 * 注入直播券服务
	 */
	@Autowired
	private TLiveCouponDao couponDao;
	
	/**
	 * 注入直播券与抵用券配置关系服务
	 */
	@Autowired
	private TFansCouponIssueRefDao fansCouponRefDao;
	
	/**
	 * 
	 * 方法描述：删除直播粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:26 <br/>
	 * @param cid
	 * @return
	 */
    public int deleteByPrimaryKey(Integer cid){
    	return couponDao.deleteByPrimaryKey(cid);
    }

	/**
	 * 
	 * 方法描述：新增直播粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:30 <br/>
	 * @param record
	 * @return
	 */
    public int insertSelective(TLiveCoupon record){
    	return couponDao.insertSelective(record);
    }

	/**
	 * 
	 * 方法描述：查询直播粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:34 <br/>
	 * @param cid
	 * @return
	 */
    public TLiveCoupon selectByPrimaryKey(Integer cid){
    	return couponDao.selectByPrimaryKey(cid);
    }

	/**
	 * 
	 * 方法描述：更新直播粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:41 <br/>
	 * @param record
	 * @return
	 */
    public int updateByPrimaryKey(TLiveCoupon record){
    	return couponDao.updateByPrimaryKey(record);
    }
    
    /**
	 * 
	 * 方法描述：获取直播粉丝券列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:41 <br/>
	 * @param record
	 * @return
	 */
    public List<TLiveCoupon> getList(TLiveCoupon record){
    	return couponDao.getList(record);
    }
    
    
    /**
     * 
     * 方法描述：获取直播粉丝券列表 (只包括主表字段)<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-1下午8:54:41 <br/>
     * @param record
     * @return
     */
    public List<TLiveCoupon> getLiveCoupon(TLiveCoupon record){
    	return couponDao.getLiveCoupon(record);
    }

	/* (non-Javadoc)
	 * @see com.xmniao.xmn.core.base.BaseService#getBaseDao()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return couponDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-27下午9:06:39 <br/>
	 * @param coupon
	 * @return
	 */
	public Resultable saveInfo(TLiveCoupon coupon) {
		Resultable result=new Resultable();
		try {
			coupon.setCtype(LiveConstant.COUPONTYPE.COUPON);
			coupon.setUpdateTime(new Date());
			coupon.setCondition(BigDecimal.ZERO);
			coupon.setShowall((byte)1);
			coupon.setIsAllSeller(0);
			Integer defaultMaxNum = coupon.getDefaultMaxNum();
			coupon.setStock(defaultMaxNum);//发行剩余量,默认发行最大量
			int count = couponDao.insertSelective(coupon);
			List<TFansCouponIssueRef> voucherList = coupon.getVoucherList();
			//移除面额和使用满足金额都为null的数据
			clearInvalidData(voucherList);
			
			boolean AddBatch=count>0&& voucherList!=null && voucherList.size()>0;
			if(AddBatch){
				//TODO 添加抵扣券
				List<TLiveCoupon> voucherListToAdd=new ArrayList<TLiveCoupon>();
				for(TFansCouponIssueRef couponRef:voucherList){
					TLiveCoupon liveCoupon =setCouponBeanInfo(coupon, couponRef);
					voucherListToAdd.add(liveCoupon);
				}
				addBatchVoucher(coupon.getCid(),voucherListToAdd);
			}
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}

	/**
	 * 方法描述：清除抵用券面额和使用最低金额都为null的数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-1上午10:13:27 <br/>
	 * @param voucherList
	 */
	private void clearInvalidData(List<TFansCouponIssueRef> voucherList) {
		boolean isNotEmpty= voucherList!=null && voucherList.size()>0;
		if(isNotEmpty){
			Iterator<TFansCouponIssueRef> iterator = voucherList.iterator();
			while(iterator.hasNext()){
				TFansCouponIssueRef couponRef = iterator.next();
				if(couponRef.getDenomination()==null && couponRef.getCondition()==null){
					iterator.remove();
				}
			}
		}
		
	}

	/**
	 * 方法描述：更新直播券及包含的抵用券信息<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-29下午3:40:37 <br/>
	 * @param liveCoupon
	 * @return
	 */
	public Resultable updateInfo(TLiveCoupon liveCoupon) {
		Resultable result= new Resultable();
		try {
			List<TLiveCoupon> couponListToAdd =new ArrayList<TLiveCoupon>();//待添加的抵用券
			List<TLiveCoupon> voucherListToUpdate =new ArrayList<TLiveCoupon>();//待修改的抵用券
			Integer cid = liveCoupon.getCid();//直播券ID
			StringBuffer giftIds=new StringBuffer();//前端非空Id集合
			/*if(liveCoupon.getStatus()==2){
				liveCoupon.setIsRecom(0);
			}*/
			//更新直播券信息
			liveCoupon.setUpdateTime(new Date());
			couponDao.updateByPrimaryKey(liveCoupon);
			
			List<TFansCouponIssueRef> voucherListForm = liveCoupon.getVoucherList();//表单传递过来的抵用券
			
			clearInvalidData(voucherListForm);
			/*
			 * 数据说明:
			 * 1.voucherListForm 中元素id 为null 即待新增数据, 
			 * 2.voucherListDb 	  中元素id not in voucherListForm中非空ID ,即待删除数据
			 * 3.voucherListForm 中元素id 不为null 即待更新数据
			 */
			boolean flag1=voucherListForm!=null && voucherListForm.size()>0;
			if(flag1){
				
				for(TFansCouponIssueRef fansCouponRef:voucherListForm){
					if(fansCouponRef.getId()==null){
						//1、添加抵用券 2、抵用券配置关系
						TLiveCoupon couponBean=setCouponBeanInfo(liveCoupon,fansCouponRef);
						couponListToAdd.add(couponBean);
					}else{
						//非空即待更新抵用券数据
						Integer giftCid = fansCouponRef.getGiftCid();
						TLiveCoupon couponBean=setCouponBeanInfo(liveCoupon,fansCouponRef);
						couponBean.setCid(giftCid);
						voucherListToUpdate.add(couponBean);
						
						//gift_id not in ID非空的giftId 即待删除数据
						giftIds.append(fansCouponRef.getGiftCid()).append(",");
					}
				}
			}
			
			/*
			 * 操作步骤：(必须先删除再添加，否则新增数据也会被删除)
			 * 1、删除抵用券
			 * 2、添加新增抵用券
			 * 3、添加新增抵用券配置关系
			 * 4、更新修改抵用券
			 */
			deleteVouchers(cid,giftIds.toString());
			
			addBatchVoucher(cid,couponListToAdd);
			
			updateVouchers(voucherListToUpdate);
			
			result.setSuccess(true);
			result.setMsg("保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("保存失败!");
			this.log.error("======>保存直播券信息失败："+e.getMessage(), e);
		}
		
		return result;
	}

	/**
	 * 方法描述：根据直播券及抵用券配置关系bean设置抵用券信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-31下午3:37:21 <br/>
	 * @param liveCoupon 直播券
	 * @param fansCouponRef 抵用券配置关系
	 * @return
	 */
	private TLiveCoupon setCouponBeanInfo(TLiveCoupon liveCoupon, TFansCouponIssueRef fansCouponRef) {
		TLiveCoupon couponBean=new TLiveCoupon();
		StringBuffer cname=new StringBuffer();
		StringBuffer validityDesc=new StringBuffer();//抵用券面额描述,￥100，满100.1可用
		BigDecimal denomination = fansCouponRef.getDenomination();
		BigDecimal condition = fansCouponRef.getCondition();
		cname.append(denomination).append("元预售抵用券");
		couponBean.setCname(cname.toString());
		validityDesc.append("￥").append(denomination).append(",").append("满").append(condition).append("可用");
		couponBean.setValidityDesc(validityDesc.toString());
		couponBean.setDenomination(fansCouponRef.getDenomination());
		couponBean.setCondition(condition);
		couponBean.setDayNum(liveCoupon.getDayNum());
		couponBean.setCtype(LiveConstant.COUPONTYPE.VOUCHER);
		couponBean.setUpdateTime(new Date());
		couponBean.setShowall((byte)1);
		couponBean.setIsAllSeller(1);
		return couponBean;
	}

	/**
	 * 方法描述：更新抵用券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-31下午1:58:03 <br/>
	 * @param voucherListToUpdate
	 */
	private void updateVouchers(List<TLiveCoupon> voucherListToUpdate) {
		if(voucherListToUpdate!=null&&voucherListToUpdate.size()>0){
			for(TLiveCoupon couponBean:voucherListToUpdate){
				updateByPrimaryKey(couponBean);
			}
		}
		
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-31下午1:58:01 <br/>
	 * @param cid 
	 * @param giftIds
	 */
	private void deleteVouchers(Integer cid, String giftIds) {
		boolean notBlank = StringUtils.isNotBlank(giftIds)&&giftIds.contains(",");
		if(notBlank){
			String giftIdStr = giftIds.substring(0, giftIds.length()-1);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("cid", cid);
			map.put("giftCids", giftIdStr.split(","));
			fansCouponRefDao.deleteVouchersByGiftIdNotIn(map);
		}
	}

	/**
	 * 方法描述：添加抵用券及配置关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-31下午1:57:58 <br/>
	 * @param cid 直播券cid
	 * @param couponListToAdd 抵用券
	 */
	private void addBatchVoucher(Integer cid, List<TLiveCoupon> couponListToAdd) {
		List<TFansCouponIssueRef> couponRefList=new ArrayList<TFansCouponIssueRef>();
//		couponDao.addBatchReturnId(couponListToAdd);
		for(TLiveCoupon liveCoupon:couponListToAdd){
			couponDao.insertSelective(liveCoupon);
			TFansCouponIssueRef couponRef = new TFansCouponIssueRef();
			couponRef.setCid(cid);
			couponRef.setGiftCid(liveCoupon.getCid());
			couponRef.setNum(1);
			couponRefList.add(couponRef);
		}
		if(couponRefList.size()>0){
			fansCouponRefDao.addBatch(couponRefList);
		}
	}

	/**
	 * 方法描述：批量更新粉丝券上线状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-22下午4:46:05 <br/>
	 * @param cids
	 * @param status
	 * @return
	 */
	public Resultable updateStatusBatch(String cids, String status) {
		Resultable result=new Resultable();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			if(StringUtils.isNotBlank(cids)){
				map.put("cids", cids.split(","));
				map.put("status", status);
				if(new Integer(status)==2){
					map.put("isRecom", 0);
				}
				couponDao.updateBatch(map);
				result.setSuccess(true);
				result.setMsg("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败");
			
		}
		
		return result;
	}

	/**
	 * 方法描述：批量更新粉丝券推荐状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-22下午5:56:05 <br/>
	 * @param cids
	 * @param isRecom
	 * @return
	 */
	public Resultable updateRecomBatch(String cids, String isRecom) {
		Resultable result=new Resultable();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			if(StringUtils.isNotBlank(cids)){
				map.put("cids", cids.split(","));
				map.put("isRecom", isRecom);
				couponDao.updateBatch(map);
				result.setSuccess(true);
				result.setMsg("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败");
			
		}
		
		return result;
	}
	
	


}
