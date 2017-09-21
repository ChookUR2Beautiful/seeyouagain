package com.xmniao.service.discount;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.ResponseState;
import com.xmniao.dao.discount.DiscountServiceDao;
import com.xmniao.thrift.busine.discount.DiscountService;
import com.xmniao.thrift.busine.common.FailureException;

/**
 * 折扣模块接口实现类
 * @author  LiBingBing
 * @version  [版本号, 2014年11月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("discountServiceImpl")
public class DiscountServiceImpl implements DiscountService.Iface
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(DiscountServiceImpl.class);
    
    /**
     * 注入折扣模块DAO层
     */
    @Autowired
    private DiscountServiceDao discountDao;
    
    /**
     * 获取合作商业务员的分账比例
     * @param jointid [合作商ID]
     * @throws FailureException [异常处理]
     * @throws TException [异常处理]
     */
    @Override
    public double inquirySplitAccount(int jointid) throws FailureException,
            TException
    {
        log.info("inquirySplitAccount start......" + jointid);
        double result = 0;
        try
        {
            //调用折扣模块DAO层的获取合作商业务员的分账比例接口
            result = discountDao.inquirySplitAccount(jointid);
        }
        catch (Exception e)
        {
            log.error("查询合作商业务员的分账比例服务异常", e);
            throw new FailureException(ResponseState.ELSEEROR,
                    "查询合作商业务员的分账比例出错");
        }
        log.info("inquirySplitAccount end......" + result);
        return result;
    }
    
    /**
     * 获取合作商业务员的总分账金额和提成折扣
     * @param staffid [业务员ID]
     * @return
     * @throws FailureException
     * @throws TException
     */
    @Override
    public Map<String, String> inquiryStaffDiscount(int staffid)
            throws FailureException, TException
    {
        log.info("inquiryStaffDiscount start......" + staffid);
        Map<String, String> resMap = new HashMap<String, String>();
        try
        {
            //调用折扣服务DAO层的获取合作商业务员的总分账金额和提成折扣接口
            Map<String, Object> res = discountDao.inquiryStaffDiscount(staffid);
            if (null != res && !res.isEmpty())
            {
                if(res.containsKey("amount"))
                {
                    resMap.put("amount", res.get("amount").toString());
                }else
                {
                    resMap.put("amount", "0.00");
                }
                if(res.containsKey("baseagio"))
                {
                    resMap.put("baseagio", res.get("baseagio").toString());
                }else
                {
                    resMap.put("baseagio", "0.00");
                }
            }
        }
        catch (Exception e)
        {
            log.error("获取合作商业务员的总分账金额和提成折扣服务异常", e);
            throw new FailureException(ResponseState.ELSEEROR,
                    "获取合作商业务员的总分账金额和提成折扣出错");
        }
        log.info("inquiryStaffDiscount end......" + resMap);
        return resMap;
    }
}
