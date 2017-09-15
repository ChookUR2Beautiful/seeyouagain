package com.xmniao.xmn.core.market.common;

public class MarketConsts {

    public static final String CART_INFO_HEADER = "market:api:cart:";          //购物车redis头部
    public static final String COLLECT_HEADER = "market:api:collect:";          //收藏redis头部
    public static final String HISTORY_HEADER = "market:api:history:";          //浏览历史redis头部
    
    public static final String DIRECT_ORDER = "market:api:direct:";				//直接购买订单
    
    public static final String BIDDING_HEADER = "market:api:bidding:";    //竞拍redis头部


    // 不可以外部实例化该类
    private MarketConsts() {
    }
}
