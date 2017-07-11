package com.xmn.saas.dao.wallet;

import java.util.Map;

import com.xmn.saas.entity.wallet.SellerDetailed;


public interface SellerDetailedDao {
    int deleteByPrimaryKey(Integer sellerid);

    int insert(SellerDetailed record);

    int insertSelective(SellerDetailed record);

    SellerDetailed selectByPrimaryKey(Integer sellerid);

    int updateByPrimaryKeySelective(SellerDetailed record);

    int updateByPrimaryKeyWithBLOBs(SellerDetailed record);

    int updateByPrimaryKey(SellerDetailed record);
    
    boolean updateAccreditStatus(Map<Object,Object> map);

    Integer selectOpertingOutBySellerid(Integer sellerid);
    
    /***-- 查询是否连锁店和是否允许总店提现 --***/
    public Map<String,Long> queryChainAndHeadDraw(int sellerid);
}