package com.xmniao.xmn.core.market.service.activity;

import com.xmniao.xmn.core.market.entity.activity.BillFreshActivity;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuction;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuctionBidding;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuctionRecord;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface AuctionService {
    /**
     * 竞拍规则
     * @return
     * @throws IOException
     */
    public String text(Integer type) throws IOException;
    
    /**
     * 竞拍活动列表
     * @param auctionListRequest
     * @return
     */
    public Map<String, Object> list(FreshActivityAuction freshActivityAuction);
    
    /**
     * 竞拍活动详情
     * @param id
     * @return
     */
    public Map<String, Object> detail(Integer id,String uid);
    
    /**
     * 竞拍
     * @param freshActivityAuctionBidding
     * @return
     */
    public Map<String, Object> bid(FreshActivityAuctionBidding freshActivityAuctionBidding);
    
    /**
     * 竞拍排名
     * @param freshActivityAuctionBidding
     * @return
     */
    public Map<String, Object> bidList(String uid,Integer activityId);
    
    /**
     * 轮播图
     * @return
     */
    public Map<String, Object> banner();
    
    /**
     * 竞拍记录
     * @param freshActivityAuctionRecord
     * @return
     */
    public Map<String, Object> record(FreshActivityAuctionRecord freshActivityAuctionRecord);
    /**
     * 竞拍支付尾款
     * @param billFreshActivity
     * @return
     */
    public Map<String, Object> pay(BillFreshActivity billFreshActivity);


    BigDecimal queryAuctionPirce() throws IOException;
}
