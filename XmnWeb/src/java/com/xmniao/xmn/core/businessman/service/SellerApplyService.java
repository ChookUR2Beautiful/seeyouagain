package com.xmniao.xmn.core.businessman.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.FoodDao;
import com.xmniao.xmn.core.businessman.dao.SellerApplyDao;
import com.xmniao.xmn.core.businessman.dao.SellerDetailedDao;
import com.xmniao.xmn.core.businessman.dao.SellerPicDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerApply;
import com.xmniao.xmn.core.businessman.entity.TSellerDetailed;
import com.xmniao.xmn.core.businessman.entity.TSellerLandmark;
import com.xmniao.xmn.core.businessman.entity.TSellerPic;
import com.xmniao.xmn.core.businessman.entity.TSellerPicApply;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.HashUtil;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerApplyService
 * 
 * 类描述： 商户信息修改申请
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时50分01秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class SellerApplyService extends BaseService<TSellerApply> {

	@Autowired
	private SellerApplyDao sellerApplyDao;
	
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerDetailedDao sellerDetailedDao;
	
	@Autowired
	private SellerPicDao sellerPicDao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return sellerApplyDao;
	}
	
	@Autowired
	private FoodDao foodDao;
	
	/**
	 * 更新状态
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateApplyStauts(TSellerApply sellerApply){
		Resultable resultable=null;
		try {
			if (SellerConstants.TSELLERAPPLY_STAUTS_PASS==sellerApply.getStatus()) {// 审批通过  1、更新审批表状态  2、将对应信息同步更新到商家表
				this.updatePass(sellerApply);
			}else if (SellerConstants.TSELLERAPPLY_STAUTS_NOTPASS==sellerApply.getStatus()) {//审批不通过  1、更新状态同时更新时间
				this.updateApplyStatus(sellerApply);
			}
			resultable=logRes(sellerApply);//日志以及返回信息组装
		} catch (Exception e) {
			this.log.error("审核处理：", e);
			throw new ApplicationException("审核处理",e,new Object[]{sellerApply},new String[]{"信息修改申请编号",sellerApply.getIds().toString(),"审核","审核"});
		}
		return resultable;
	}
	public Resultable logRes(TSellerApply sellerApply){
		this.log.info("修改成功");
		String[] s={"信息修改申请编号",sellerApply.getIds().toString(),"审核","审核"};
		this.fireLoginEvent(s);
		return  new Resultable(true, "操作成功");
	}
	public void updatePass(TSellerApply sellerApply){
		 try {
			 updateApplyStatus(sellerApply);
			 List<TSellerApply> applyList = sellerApplyDao.getsellerApplyList(sellerApply);
			 for (TSellerApply tSellerApply : applyList) {
				 if(tSellerApply.getSource() == 1){//更新:source 1商家基本信息修改 (基本资料、logo)2 图片信息(商家环境)和使用说明修改  （更新版本更新基本信息不更新logo,logo属于图片信息，不属于基本信息2016-10-20 cj）
					 updateApplyStautsSeller(tSellerApply);//同步更新商家列表信息（基本资料和使用说明）t_seller
					 updateLandMark(tSellerApply);
					 updateSellerLandmark(tSellerApply);//更新经纬度
				 }else if(tSellerApply.getSource() == 2){
					 updateSellerPic(tSellerApply);//更新原商家图片（环境图和使用说明）t_seller_pic
				 }
				 
				 int sellerid = tSellerApply.getSellerid();
				 TSeller seller = sellerService.getObject((long)sellerid);
				 if(seller.getIsonline()==1){
					 //只有已上线商家才更新MongoDB
					 sellerService.InsertOrUpdateMongo(tSellerApply.getSellerid());//同步到MongoDB	 
				 }
				 
			}
		} catch (Exception e) {
			this.log.info("审批通过更新：", e);
			throw new ApplicationException("审批通过更新",e,new Object[]{sellerApply});
	   } 
	}
	public void  updateApplyStatus(TSellerApply sellerApply){//更新状态
		 try {
			 sellerApply.setEdate(new Date());
			 sellerApply.setArray(sellerApply.getIds().split(","));
			 sellerApplyDao.updateApplyStatus(sellerApply);
		} catch (Exception e) {
			this.log.error("审批状态更新：", e);
			throw new ApplicationException("审批状态更新",e,new Object[]{sellerApply});
		}
	}
	public void updateApplyStautsSeller(TSellerApply tSellerApply){//同步更新商家列表信息（基本资料和使用说明）t_seller
		 try {
			 /**
			  * 更新logo
			  */
			 /*Integer sellerid = tSellerApply.getSellerid();
			 String logo = tSellerApply.getLogo();
			 if(null != sellerid && !"".equals(sellerid) &&  StringUtils.isNotBlank(logo)){
				 TSellerPic sellerLogoPic = new TSellerPic();
				 sellerLogoPic.setSellerid(sellerid);
				 sellerLogoPic.setBreviary(logo);
				 sellerLogoPic.setPicurl(logo);
				 sellerLogoPic.setSdate(new Date());
				 sellerLogoPic.setIslogo(1);
				 sellerPicDao.update(sellerLogoPic);
			 }*/
			 /**
			  * 更新基本信息
			  */
			 TSeller seller = new TSeller();
			 seller.setSellerid(tSellerApply.getSellerid());
			 seller.setSellername(tSellerApply.getSellername());
			 seller.setProvince(tSellerApply.getProvince());
			 seller.setCity(tSellerApply.getCity());
			 seller.setArea(tSellerApply.getArea());
			 seller.setZoneid(tSellerApply.getZoneid());
			 seller.setAddress(tSellerApply.getAddress());
			 //2016-04-20新增更新字段 add by hls 
			 seller.setTel(tSellerApply.getPhone());
			 seller.setSexplain(tSellerApply.getSexplain());
			 sellerService.update(seller);
		} catch (Exception e) {
			this.log.error("商家列表同步更新：", e);
			throw new ApplicationException("商家列表同步更新",e,new Object[]{tSellerApply});
		}
	}
	
	/**
	 * @Title:updateSellerPic
	 * @Description:2016-04-20更新原商家图片（logo和环境图）t_seller_pic
	 * @param tSellerApply void
	 * @throw
	 */
	public void updateSellerPic(TSellerApply tSellerApply){
		 try {
			 List<TSellerPic> sellerPicAddList = new ArrayList<>();//新增环境图片列表
			 Integer sellerid = tSellerApply.getSellerid();
			/**
			 * 更新 t_seller_pic 0  环境图    1  logo图  2  商家图  
			 *	   t_seller_pic_apply 0  LOGO图   1  环境图  2  商家图  
			 */
			//根据申请表id(sellerApply.getId();)查询t_seller_pic_apply申请修改的图片add by hls
			TSellerPicApply tp = new TSellerPicApply();
			tp.setSaid(tSellerApply.getId());
			List<TSellerPicApply> piclist = getPicApplyList(tp);
			if(!piclist.isEmpty()){
				tSellerApply.setSellerPicApply(piclist);
				List<TSellerPicApply> sellerPicApplyList = tSellerApply.getSellerPicApply();
				
				//批量删除
				Set set = new HashSet<>();
				for (TSellerPicApply tSellerPicApply : sellerPicApplyList) {
					
					/**
					 *  更新 t_seller_pic 0  环境图    1  logo图  2  商家图  
					 *	t_seller_pic_apply 0  LOGO图   1  环境图  2  商家图  
					 */
					if(tSellerPicApply.getType() == 0){
						tSellerPicApply.setType(1);
					}else if(tSellerPicApply.getType() == 1){
						tSellerPicApply.setType(0);
					}
						tSellerPicApply.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						set.add(tSellerPicApply.getType());//修改类型
				}
				StringBuffer sb = new StringBuffer();
				for (Object object : set) {
					sb.append(object+",");
				}
				sellerPicDao.deleteSellerPic(sellerid,sb.toString().substring(0,sb.toString().lastIndexOf(",")));
				
				//添加修改数据
				sellerPicDao.batchAdd(sellerPicApplyList);
				/* for (int i = 0; i < sellerPicApplyList.size(); i++) {
					  if(sellerPicApplyList.get(i).getType() == 1){//过滤取得环境图 : 先删除 ，再添加
					    //封装添加数据
						TSellerPic sellerEnvironmentPic = new TSellerPic();
						sellerEnvironmentPic.setSellerid(sellerPicApplyList.get(i).getSellerid());
						sellerEnvironmentPic.setBreviary(sellerPicApplyList.get(i).getPicurl());
						sellerEnvironmentPic.setPicurl(sellerPicApplyList.get(i).getPicurl());
						sellerEnvironmentPic.setBewrite(sellerPicApplyList.get(i).getPicname());
						sellerEnvironmentPic.setSdate(new Date());
						sellerEnvironmentPic.setIslogo(0);
						sellerPicAddList.add(sellerEnvironmentPic);
					  }
				}
				 //批量删除商家环境图
				 if(null != sellerid && !"".equals(sellerid)){
					 sellerPicDao.deleteSellerPic(sellerid);
				 }
				//批量添加商家环境图
				 if(!"".equals(sellerPicAddList) && null != sellerPicAddList && sellerPicAddList.size() > 0){
					 sellerPicDao.batchAdd(sellerPicAddList);
				 }*/
			}
			/**
			 * 更新使用说明
			 */
			 /*TSeller seller = new TSeller();
			 seller.setSellerid(tSellerApply.getSellerid());
			 seller.setSexplain(tSellerApply.getSexplain());
			 sellerService.update(seller);*/
		} catch (Exception e) {
			this.log.error("商家环境图片或使用说明批量更新：", e);
			throw new ApplicationException("商家环境图片或使用说明批量更新",e,new Object[]{tSellerApply});
		}
	}
	/**
	 * @Title:updateSellerLandmark
	 * @Description:更新经纬度
	 * @param sellerLandmark
	 * @return Object
	 * @throw
	 */
	public void updateSellerLandmark(TSellerApply tSellerApply) {
		TSellerLandmark sellerLandmark = new TSellerLandmark();
		try {
			sellerLandmark.setSellerid(tSellerApply.getSellerid());//封装商家ID
			sellerLandmark.setLongitude(tSellerApply.getLongitude());//封装经度
			sellerLandmark.setLatitude(tSellerApply.getLatitude());//封装纬度
			// latitude 纬度 longitude 经度(计算HASH值)
			String geohashs = "";
			try {
				geohashs = HashUtil.getInstance().getGeoHash(
						sellerLandmark.getLatitude(),
						sellerLandmark.getLongitude());
			} catch (Exception e) {
				this.log.error("经纬度格式不正确", e);
			}
			sellerLandmark.setGeohash(geohashs);
			sellerLandmark.setSdate(new Date());
			int num = sellerService.updateSellerLandmark(sellerLandmark);
			if (num !=1) {
				log.error("经纬度更新失败");
				throw new ApplicationException("经纬度更新失败");
			}
			sellerService.updateSellerDateTime(sellerLandmark.getSellerid());//更新主表t_seller 数据操作时间
			this.log.info("经纬度更新成功!");  
		} catch (Exception e) {
			this.log.error("经纬度更新：", e);
			throw new ApplicationException("经纬度更新",e,new Object[]{tSellerApply});
		}
		
	}
	
	/**
	 * 
	 * 方法描述：更新商家详情参考地表
	 * 创建人： chenJie
	 * 创建时间：2016年8月29日下午5:59:06
	 * @param tSellerApply
	 */
	public void updateLandMark(TSellerApply tSellerApply){
		TSellerDetailed tSellerDetailed = new TSellerDetailed();
		try {
			tSellerDetailed.setSellerid(tSellerApply.getSellerid());
			tSellerDetailed.setLandmark(tSellerApply.getLandmark());
			sellerDetailedDao.update(tSellerDetailed);
		} catch (Exception e) {
			this.log.error("更新商家详情参考地表：", e);
			throw new ApplicationException("更新商家详情参考地表",e,new Object[]{tSellerApply});
		}
	}
	
	/**
	 * @Title:getPicApplyList
	 * @Description:获取申请修改图片
	 * @param sellerApply
	 * @return List<TSellerPicApply>
	 * @throw
	 */
	public List<TSellerPicApply> getPicApplyList(TSellerPicApply tp){
		try{
			List<TSellerPicApply> piclist = sellerApplyDao.getPicApplyList(tp);
			return piclist;
		}catch (Exception e){
			throw new ApplicationException("获取申请修改图片",e,new Object[]{tp});
		}
	}
	
	public static void main(String[] args) {
		Set set = new HashSet<>();
		set.add(1);
		set.add(1);
		set.add(2);
		set.add(2);
		set.add(3);
		StringBuffer sb = new StringBuffer();
		for (Object object : set) {
			sb.append(object+",");
		}
		
		System.out.println(sb.toString().substring(0,sb.toString().lastIndexOf(",")));
	}
}

