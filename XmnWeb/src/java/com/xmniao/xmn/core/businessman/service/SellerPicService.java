package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerPicDao;
import com.xmniao.xmn.core.businessman.entity.MSellerPic;
import com.xmniao.xmn.core.businessman.entity.TSellerPic;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerPicService
 * 
 * 类描述： 商家环境图片
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时56分48秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class SellerPicService extends BaseService<TSellerPic> {

    @Autowired
    private SellerPicDao sellerPicDao;

    @Override
    protected BaseDao getBaseDao() {
	return sellerPicDao;
    }

    public TSellerPic getSellerLogo(Long sellerid) {
	return sellerPicDao.getSellerLogo(sellerid);
    }

    /**
     * 取得商家图片.
     * 
     * @param sellerid
     * @return
     */
    public TSellerPic getSellerPic(Long sellerid) {
	return sellerPicDao.getSellerPic(sellerid);
    }

    /**
     * mongodb 商家logo对象信息
     * 
     * @param sellerId
     * @return
     */
    public MSellerPic getMSellerLogo(Long sellerId) {
	return sellerPicDao.getMSellerLogo(sellerId);
    }
    
    /**
     * mongodb 商家logo对象信息
     * 
     * @param sellerId
     * @return
     */
    public MSellerPic getMSellerCover(Long sellerId) {
	return sellerPicDao.getMSellerCover(sellerId);
    }

    /**
     * mongodb 商家环境对象信息
     * 
     * @param sellerId
     * @return
     */
    public List<MSellerPic> getMSellerPics(Long sellerId) {
	return sellerPicDao.getMSellerPics(sellerId);
    }

}
