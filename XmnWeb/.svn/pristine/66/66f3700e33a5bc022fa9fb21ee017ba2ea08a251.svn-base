package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TDebitcardOrder;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface DebitcardOrderDao  extends BaseDao<TDebitcardOrder> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TDebitcardOrder record);

    TDebitcardOrder selectByPrimaryKey(Integer id);
    
    @DataSource("slave")
    int updateByPrimaryKeySelective(TDebitcardOrder record);
    
    @DataSource("slave")
    List<TDebitcardOrder> getDebitcardOrderDataList(TDebitcardOrder record);
    
    
	@DataSource("slave")
	public Long debitcardOrderCount(TDebitcardOrder record);

}