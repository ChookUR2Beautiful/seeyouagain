package com.xmniao.dao;

import com.xmniao.entity.LiveWalletRecord;

public interface LiveWalletRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LiveWalletRecord record);

    int insertSelective(LiveWalletRecord record);

    LiveWalletRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiveWalletRecord record);

    int updateByPrimaryKey(LiveWalletRecord record);
    
    /**
     * 
     * 方法描述：根据订单号统计条数
     * 创建人：jianming  
     * 创建时间：2017年5月22日 下午5:37:37   
     * @param source
     * @return
     */
	Long countByRemarks(String source);
}