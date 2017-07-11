package com.xmn.saas.dao.celebrity;

import com.xmn.saas.base.Page;
import com.xmn.saas.entity.celebrity.CelebrityOrder;
import com.xmn.saas.entity.common.SellerAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CelebrityOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(CelebrityOrder record);

    int insertSelective(CelebrityOrder record);

    CelebrityOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CelebrityOrder record);

    int updateByPrimaryKey(CelebrityOrder record);

    /** 查询食进行中的食评订单
     * @param sellerAccount
     * @param page*/
    List<CelebrityOrder> selectReviewerOrderBySellerId(@Param("sellerAccount") SellerAccount sellerAccount, @Param("page") Page page);

    Integer selectCountOrderBySellerId(Integer sellerid);

    int updateVersionLock(CelebrityOrder record);
}