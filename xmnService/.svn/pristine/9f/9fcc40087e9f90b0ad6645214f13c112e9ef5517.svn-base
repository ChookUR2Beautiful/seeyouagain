package com.xmniao.xmn.core.market.service.activity.spike.impl;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.dao.FreshActivityCommonDao;
import com.xmniao.xmn.core.market.dao.FreshActivityProductDao;
import com.xmniao.xmn.core.market.dao.ProductInfoDao;
import com.xmniao.xmn.core.market.dao.FreshSpikeActivityDao;
import com.xmniao.xmn.core.market.entity.activity.spike.FreshSpikeActivity;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.service.activity.spike.SpikeService;
import com.xmniao.xmn.core.market.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yang.qiang on 2017/2/20.
 */
@Service
public class SpikeServiceImpl implements SpikeService {

    @Autowired
    private FreshSpikeActivityDao freshSpikeActivityDao;
    @Autowired
    private FreshActivityCommonDao freshActivityCommonDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private FreshActivityProductDao freshActivityProductDao;
    @Autowired
    private String fileUrl;
    @Autowired
    private ProductService productService;


    // 查询秒杀活动, 并加载场次
    @Override
    public FreshSpikeActivity getSpikeActivity(Long spikeId) {
        FreshSpikeActivity spikeActivity = freshSpikeActivityDao.selectByPrimaryKey(spikeId);
        spikeActivity.setImage(fileUrl+spikeActivity.getImage());
        List<FreshActivityCommon> sessions = freshActivityCommonDao.selectBySpikeId(spikeId);

        // 计算 距离开始, 距离结束 的时间戳
        long currentTime = new Date().getTime();
        for (FreshActivityCommon session : sessions) {
            session.setDistEndTime(session.getEndDate().getTime() - currentTime);
            session.setDistStartTime(session.getBeginDate().getTime() - currentTime);
        }
        spikeActivity.setSessions(sessions);

        return spikeActivity;
    }

    @Override
    public List<ProductInfo> getActivityProductList(Long activityId, Page page) {

        List<ProductInfo> productList = new ArrayList<>();
        FreshActivityCommon spikeActivity = freshActivityCommonDao.selectByPrimaryKey(activityId.intValue());
        productList.addAll(productInfoDao.selectSpikeProductByActivityId(activityId,page));
        // 遍历商品列表, 图片条件请求域名
        for (ProductInfo productInfo : productList) {
            productInfo.setBreviary(fileUrl+productInfo.getBreviary());
            productService.loadLabel(productInfo,spikeActivity);
        }
        return productList;
    }
}
