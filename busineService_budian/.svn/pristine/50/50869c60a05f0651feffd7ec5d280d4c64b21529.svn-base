package com.xmniao.dao.discount;

import java.util.List;
import java.util.Map;

/**
 * 折扣模块DAO层
 * @author  LiBingBing
 * @version  [版本号, 2014年11月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface DiscountServiceDao
{
    /**
     * 获取合作商业务员的分账比例
     * @param jointId [合作商ID]
     * @return double [业务员分账比例]
     */
    public double inquirySplitAccount(int jointId);
    
    /**
     * 获取合作商业务员的总分账金额和提成折扣
     * @param staffId [业务员ID]
     * @return Map<String,String> [总分账金额和提成折扣]
     */
    public Map<String,Object> inquiryStaffDiscount(int staffId);
    
    /**
     * 查询需要更新到商户折扣设置表的折扣记录表
     * @param param
     * @return
     */
    public List<Map<String,Object>> findAgioRecord(Map<String, Object> param);
    
    /**
     * 查询周末折扣  商家折扣率还没有数据的数据
     * @param param
     * @return
     */
    public List<Map<String,Object>> findAgioRecordNotSeller(Map<String, Object> param);
    
    /**
     * 更新商家打折记录表
     * @param param
     */
    public void updateSellerAgio(Map<String, Object> param);
    
    /**
     * 插入商家打折记录表
     * @param param
     */
    public void insertSellerAgio(Map<String, Object> param);
    
    /**
     * 更新折扣设置记录表
     */
    public void updateBatchAgioRecord(Map<String, Object> param);
}
