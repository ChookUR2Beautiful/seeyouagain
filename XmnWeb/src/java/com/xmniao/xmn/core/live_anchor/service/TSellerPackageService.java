package com.xmniao.xmn.core.live_anchor.service;

import java.math.BigDecimal;
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
import com.xmniao.xmn.core.live_anchor.dao.TLiveCouponDao;
import com.xmniao.xmn.core.live_anchor.dao.TSellerPackageDao;
import com.xmniao.xmn.core.live_anchor.dao.TSellerPackageIssueRefDao;
import com.xmniao.xmn.core.live_anchor.dao.TSellerPackagePicDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveCoupon;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackage;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackageIssueRef;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackagePic;
import com.xmniao.xmn.core.util.StringUtils;

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
public class TSellerPackageService extends BaseService<TSellerPackage> {
	
	/**
	 * 套餐dao
	 */
	@Autowired
	private TSellerPackageDao sellerPackageDao;
	
	
	@Autowired
	private TSellerPackagePicDao sellerPackagePicDao;
	
	/**
	 * 注入直播券服务
	 */
	@Autowired
	private TLiveCouponDao couponDao;
	
	@Autowired
	private TSellerPackageIssueRefDao sellerPackageIssueRefDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return sellerPackageDao;
	}

	/**
	 * 获取商家列表信息
	 * 
	 * @param seller
	 * @return
	 */
	public Pageable<TSellerPackage> getSellerPackageInfoList(TSellerPackage sellerPackage) {
		// 商家列表内容
		Pageable<TSellerPackage> sellerPackageInfoList = new Pageable<TSellerPackage>(sellerPackage);
		List<TSellerPackage> sellerPackageList = sellerPackageDao.getSellerPackageList(sellerPackage);
		
		 //获取对应关系
	    List<TSellerPackageIssueRef> sellerPackageIssueRefInfoList = sellerPackageIssueRefDao.getSellerPackageIssueRefDataList();
        if (sellerPackageList != null && sellerPackageList != null ){
			for(TSellerPackage bean: sellerPackageList){
				String couponDesc = "";
				for(int i = 0; i < sellerPackageIssueRefInfoList.size(); i ++){
					TSellerPackageIssueRef object = sellerPackageIssueRefInfoList.get(i);
					if (bean.getId() == object.getPid()){
						couponDesc += ", "+object.getCouponDesc();
					}
				}
				
				if (couponDesc.length() > 1){  //赠送抵用券面值
					couponDesc = couponDesc.substring(1);
					bean.setCouponDesc(couponDesc);
				}
				
				if (bean.getStock() == 0){//库存为0时为售罄
					bean.setStatusDesc("售罄");
				}
			}
        }
		
		
		sellerPackageInfoList.setContent(sellerPackageList);
		// 总条数
		sellerPackageInfoList.setTotal(getSellerPackageCount(sellerPackage)); 
		return sellerPackageInfoList;
	}
	
	
	public Long getSellerPackageCount(TSellerPackage sellerPackage) {
		return sellerPackageDao.sellerPackageCount(sellerPackage);
	}

	
	/**
	 * 方法描述：保存套餐信息<br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月8日下午5:41:11 <br/>
	 * @param sellerPackage
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveActivity(TSellerPackage sellerPackage) throws Exception {
		// 保存套餐信息
		this.saveSellerPackageInfo(sellerPackage);
		// 保存套餐关联的数据
		this.saveSellerPackageReference(sellerPackage);
	}
	
	/*保存套餐信息*/
	public void saveSellerPackageInfo(TSellerPackage sellerPackage) throws Exception{
		sellerPackage.setUpdateTime(new Date());
		sellerPackageDao.insertSelective(sellerPackage);
	}
	
	/*保存套餐图片, 套餐优惠券信息*/
	public void saveSellerPackageReference(TSellerPackage sellerPackage)  throws Exception{
		//套餐图片信息
		List<TSellerPackagePic> sellerPackagePicList = sellerPackage.getSellerPackagePicList();
		if (sellerPackagePicList != null && sellerPackagePicList.size() > 0){
			int number = 0;
			for(TSellerPackagePic bean:sellerPackagePicList){
				if (bean.getPicUrl() != null && !"".equals(bean.getPicUrl())){ 
					//判断图片地址是否存在
					TSellerPackagePic sellerPackagePic = new TSellerPackagePic();
					sellerPackagePic.setPid(sellerPackage.getId());
					sellerPackagePic.setPicType(number == 0 ? 1 : 2);
					sellerPackagePic.setPicUrl(bean.getPicUrl());
					sellerPackagePic.setSort(bean.getSort());
					sellerPackagePicDao.insertSelective(sellerPackagePic);
					number++;
				}
			}
		}
		//保存套餐对应的优惠券信息
		List<TSellerPackageIssueRef> voucherList = sellerPackage.getVoucherList();
		if (voucherList != null && voucherList.size() > 0){
			List<TLiveCoupon> voucherListToAdd = new ArrayList<TLiveCoupon>();
			for(TSellerPackageIssueRef couponRef:voucherList){
				if ( couponRef.getDenomination() != null && couponRef.getCondition() != null){  //面额与条件不为空时
					TLiveCoupon liveCoupon = setCouponBeanInfo(couponRef);
					voucherListToAdd.add(liveCoupon);
				}
			}
			if (voucherListToAdd.size() > 0)
				addBatchVoucher(sellerPackage.getId(), voucherListToAdd);
		}
	}
	
	
	/**
	 * 方法描述：生成优惠券信息<br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月8日下午5:37:46 <br/>
	 * @param fansCouponRef
	 * @return
	 */
	private TLiveCoupon setCouponBeanInfo(TSellerPackageIssueRef fansCouponRef) {
		StringBuffer cname = new StringBuffer();
		StringBuffer validityDesc = new StringBuffer();// 抵用券面额描述,￥100，满100.1可用
		BigDecimal denomination = fansCouponRef.getDenomination();
		BigDecimal condition =  fansCouponRef.getCondition();
		
		cname.append("面值[").append(denomination).append("]元 套餐抵用券");
		validityDesc.append("￥").append(denomination).append(",").append("满").append(condition).append("可用");
		
		TLiveCoupon couponBean = new TLiveCoupon();
		couponBean.setCname(cname.toString());
		couponBean.setValidityDesc(validityDesc.toString());
		couponBean.setDenomination(fansCouponRef.getDenomination());
		couponBean.setCondition(condition);
		couponBean.setCtype(LiveConstant.COUPONTYPE.COUPESP);
		couponBean.setUpdateTime(new Date());
		couponBean.setShowall((byte) 1);
		couponBean.setIsAllSeller(1);
		couponBean.setDayNum(60);  //有效间隔天数(单位为天)
		couponBean.setUseNum(1);
		
		return couponBean;
	}
	
	/**
	 * 方法描述：保存套餐关联的优惠券信息及关联关系 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月8日下午5:37:10 <br/>
	 * @param id
	 * @param couponListToAdd
	 */
	private void addBatchVoucher(Integer id, List<TLiveCoupon> couponListToAdd) {
		for (TLiveCoupon liveCoupon:couponListToAdd) {
			couponDao.insertSelective(liveCoupon);  //保存抵用券信息
			TSellerPackageIssueRef sellerPackageIssueRef = new TSellerPackageIssueRef();
			sellerPackageIssueRef.setPid(id);
			sellerPackageIssueRef.setNum(1);
			sellerPackageIssueRef.setGiftCid(liveCoupon.getCid());
			sellerPackageIssueRefDao.insertSelective(sellerPackageIssueRef); //保存套餐与优惠券关联
		}

	}
	
	
	/**
	 * 方法描述：删除套餐信息 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月8日下午5:39:05 <br/>
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id) {
		return sellerPackageDao.deleteByPrimaryKey(id);
	}
	

	
	/**
	 * 方法描述：更新套餐信息 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月8日下午5:40:32 <br/>
	 * @param sellerPackage
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateActivity(TSellerPackage sellerPackage) throws Exception {
		// 先册除关联的信息
		Integer pid = sellerPackage.getId();
		if (pid != null) {
			sellerPackagePicDao.deleteDataListByPid(pid);
			this.deleteCouponInfoListByPid(pid); // 套餐抵用券有修改的情况

			// 保存套餐关联的信息
			sellerPackage.setUpdateTime(new Date()); // 修改时间
			sellerPackageDao.updateByPrimaryKeySelective(sellerPackage);
			this.saveSellerPackageReference(sellerPackage);
		}
	}
	
	/**
	 * 方法描述：删除套餐关联的优惠券信息 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月8日下午5:34:28 <br/>
	 * @param sellerPackage
	 */
	public void deleteCouponInfoListByPid(Integer pid)  throws Exception{
		 //获取对应关系
		 List<TSellerPackageIssueRef> sellerPackageIssueRefInfoList = getFansCouponIssueRefInfoList(pid);
		 if (sellerPackageIssueRefInfoList != null && sellerPackageIssueRefInfoList.size() > 0){
			 String giftCids = "";
			 List<Integer> sellerPackageIssueRefIds = new ArrayList<>();
			 for (TSellerPackageIssueRef bean :sellerPackageIssueRefInfoList){
				 giftCids += ", "  + bean.getGiftCid();  //抵用券编号
				 sellerPackageIssueRefIds.add(bean.getPid());  //关联关系表
			 }
			 
			 //获取抵用券
			 couponDao.deleteLiveCouponByCids(giftCids.split(","));
			 //删除对应的关联关系
			 sellerPackageIssueRefDao.deleteSellerPackageIssueRef(sellerPackageIssueRefIds);
			 
		 }
	}
	
	
	/**
	 * 方法描述：获取套餐图片信息 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月8日下午5:34:09 <br/>
	 * @param sellerPackage
	 * @return
	 */
	public List<TSellerPackagePic> getSellerPackagePicInfoList(Integer pid) {
	    List<TSellerPackagePic> sellerPackagePicList = sellerPackagePicDao.getDataListByPid(pid);
		
		return sellerPackagePicList;
	}
	
	/**
	 * 方法描述：获取赠送优惠券 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月8日下午5:33:05 <br/>
	 * @param sellerPackage
	 * @return
	 */
	public List<TSellerPackageIssueRef> getFansCouponIssueRefInfoList(Integer pid) {
		List<TSellerPackageIssueRef> fansCouponIssueRefList = sellerPackageIssueRefDao.getSellerPackageIssueRefListByPid(pid);
		
		return fansCouponIssueRefList;
	}
	
	
	/**
	 * 方法描述：取套餐明细 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月8日下午5:32:50 <br/>
	 * @param sellerPackage
	 * @return
	 */
	public TSellerPackage getSellerPackageInfo(TSellerPackage sellerPackage) {
		
		List<TSellerPackage> sellerPackageList = sellerPackageDao.getSellerPackageList(sellerPackage);
		if (sellerPackageList.size() > 0)
			return sellerPackageList.get(0);
		
		return null;
	}
	
	
	
	/**
	 * 
	 * 方法描述：批量修改商家   公开商户，参与分红，付费商家  状态
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月29日上午11:06:09 <br/>
	 * @param seller
	 */
	@Transactional
	public void updateStatusOption(TSellerPackage sellerPackage) throws Exception{
		sellerPackage.setUpdateTime(new Date());
		Object[] objects = StringUtils.paresToArray(sellerPackage.getIds(), ",");
		sellerPackage.setArray(objects);
		//批量修改商家状态
		Integer result = sellerPackageDao.updateStatusOption(sellerPackage);
		if(result<=0){
			log.error("批量修改商家套餐状态失败"+sellerPackage.getIds());
			throw new RuntimeException();
		}
	
	}
	
	
}
