package com.xmn.saas.service.celebrity;

import com.xmn.saas.base.Page;
import com.xmn.saas.entity.celebrity.Celebrity;
import com.xmn.saas.entity.celebrity.CelebrityAriticle;
import com.xmn.saas.entity.celebrity.CelebrityOrder;
import com.xmn.saas.entity.common.SellerAccount;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * create 2016/11/30
 *
 * @author yangQiang
 */

public interface ReviewerService {

    /**
     *
     */
    List<Celebrity> queryReviewerList(Page page, Integer order);

    Celebrity queryReviewerDetail(Long reviewerId);

    void submitOrder(CelebrityOrder order);

    /**
     * 统计名嘴数量
     */
    Long countReviewer();

    /** 查询 最大观看次数的食评 */
    CelebrityAriticle queryMaxViewsAriticle(Long reviewerId);

    /** 查询进行中订单列表 */
    List<CelebrityOrder> queryOrderList(SellerAccount sellerAccount,Page page);

    /** 查询订单总统计数 */
    Integer queryOrderCount(SellerAccount sellerAccount);

    /** 查询已发布文章列表 */
    List<CelebrityAriticle> queryAriticleList(SellerAccount sellerAccount, Page page);

    /** 统计文章总记录数 */
    Integer countAriticle(SellerAccount sellerAccount);

    /** 根据订单主键查询订单 */
    CelebrityOrder queryOrder(Long orderId);

    /** 确认支付 */
    HashMap<String, Object> confirmPay(CelebrityOrder reviewerOrder, SellerAccount sellerAccount) throws IOException;

    /** 根据文章id 查询文章 */
    String queryAriticle(Long articleId);
}
