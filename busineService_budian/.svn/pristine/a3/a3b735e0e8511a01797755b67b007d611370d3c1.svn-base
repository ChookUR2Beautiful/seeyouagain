package com.xmniao.service.refund.census;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.ConversionTypeUtil;
import com.xmniao.dao.order.OrderServiceDao;

/**
 * 修改合作商订单统计实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年9月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class MdyJointDayCensusImpl
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(MdyJointDayCensusImpl.class);
    
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    public void modifyJointDayCensus(Map<String, Object> mdyAfterOrderMap)
    {
        log.info("mdySellerDayCensus start::" + mdyAfterOrderMap);
        
        try
        {
            //若所属合作商与消费合作商相同
            if (String.valueOf(mdyAfterOrderMap.get("jointid")).equals(String.valueOf(mdyAfterOrderMap.get("consume_jointid"))))
            {
                //消费合作商统计处理
                consumeJointidCensusProcess(mdyAfterOrderMap);
            }
            else
            {
                //消费合作商统计处理
                consumeJointidCensusProcess(mdyAfterOrderMap);
                //所属合作商统计处理
                jointidCensusProcess(mdyAfterOrderMap);
            }
        }
        catch (Exception e)
        {
            log.error("mdySellerDayCensus error::", e);
        }
        log.info("mdySellerDayCensus end");
    }
    
    /**
     * 消费合作商统计处理
     * @param mdyAfterOrderMap [更新已分账退款后的订单信息]
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void consumeJointidCensusProcess(Map<String,Object> mdyAfterOrderMap) throws Exception
    {
        SimpleDateFormat mft = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("jointId",String.valueOf(mdyAfterOrderMap.get("consume_jointid")));
        paraMap.put("orderDate",mft.format(mft.parse(String.valueOf(mdyAfterOrderMap.get("zdate")))));
        //根据消费合作商ID和订单支付时间查询合作商统计信息
        Map<String, Object> jointCensusMap = orderDao.queryJointCensusByJointAndDate(paraMap);
        if(null!=jointCensusMap)
        {
            //组装更新合作商订单统计表MAP
            Map<String, String> mdyParaMap = new HashMap<String, String>();
            mdyParaMap.putAll(paraMap);
            BigDecimal censusProfitTotal = ConversionTypeUtil.conversionToBigDecimal(jointCensusMap.get("profit_total"));
            BigDecimal profitTotal = new BigDecimal(0.00);
            BigDecimal cpartnerAmount = new BigDecimal(0.00);
            if(StringUtils.isNotBlank(String.valueOf(mdyAfterOrderMap.get("commission"))))
            {
                JSONObject resJson = JSONObject.parseObject(String.valueOf(mdyAfterOrderMap.get("commission")));
                cpartnerAmount = resJson.getBigDecimal("cpartner_amount").add(resJson.getBigDecimal("consumeJointidMoney"));
            }
            //若计算的数据不为0,才去进行更新
            if(cpartnerAmount.doubleValue()!=0)
            {
                profitTotal = censusProfitTotal.subtract(cpartnerAmount);
                mdyParaMap.put("profitTotal",String.valueOf(profitTotal.doubleValue()));
                //更新合作商订单统计信息
                orderDao.modifyJointDayCensus(mdyParaMap);
            }
        }
        //移除MAP参数中的zdate，PUT进去 fdate
        paraMap.remove("orderDate");
        paraMap.put("orderDate",mft.format(mft.parse(String.valueOf(mdyAfterOrderMap.get("fdate")))));
        //根据合作商ID和订单到账时间查询合作商统计信息
        jointCensusMap = orderDao.queryJointCensusByJointAndDate(paraMap);
        if(null != jointCensusMap)
        {
            //组装更新合作商订单统计表MAP
            Map<String, String> mdyParaMap = new HashMap<String, String>();
            mdyParaMap.putAll(paraMap);
            BigDecimal censusAccountTotal = ConversionTypeUtil.conversionToBigDecimal(jointCensusMap.get("account_total"));
            BigDecimal accountTotal = new BigDecimal(0.00);
            BigDecimal cpartnerAmount = new BigDecimal(0.00);
            if(StringUtils.isNotBlank(String.valueOf(mdyAfterOrderMap.get("commission"))))
            {
                JSONObject resJson = JSONObject.parseObject(String.valueOf(mdyAfterOrderMap.get("commission")));
                cpartnerAmount = resJson.getBigDecimal("cpartner_amount").add(resJson.getBigDecimal("consumeJointidMoney"));
            }
            //若计算的数据不为0,才去进行更新
            if(cpartnerAmount.doubleValue()!=0)
            {
                accountTotal = censusAccountTotal.subtract(cpartnerAmount);
                mdyParaMap.put("accountTotal",String.valueOf(accountTotal.doubleValue()));
                //更新合作商订单统计信息
                orderDao.modifyJointDayCensus(mdyParaMap);
            }
        }
    }
    
    /**
     * 所属合作商统计处理
     * @param mdyAfterOrderMap [更新已分账退款后的订单信息]
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void jointidCensusProcess(Map<String,Object> mdyAfterOrderMap) throws Exception
    {
        SimpleDateFormat mft = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("jointId",String.valueOf(mdyAfterOrderMap.get("jointid")));
        paraMap.put("orderDate",mft.format(mft.parse(String.valueOf(mdyAfterOrderMap.get("zdate")))));
        //根据所属合作商ID和订单支付时间查询合作商订单统计信息
        Map<String, Object> jointCensusMap = orderDao.queryJointCensusByJointAndDate(paraMap);
        if(null!=jointCensusMap)
        {
            //组装更新合作商订单统计信息的MAP
            Map<String, String> mdyParaMap = new HashMap<String, String>();
            mdyParaMap.putAll(paraMap);
            BigDecimal censusProfitTotal = ConversionTypeUtil.conversionToBigDecimal(jointCensusMap.get("profit_total"));
            BigDecimal profitTotal = new BigDecimal(0.00);
            BigDecimal bpartnerAmount = new BigDecimal(0.00);
            if(StringUtils.isNotBlank(String.valueOf(mdyAfterOrderMap.get("commission"))))
            {
                JSONObject resJson = JSONObject.parseObject(String.valueOf(mdyAfterOrderMap.get("commission")));
                bpartnerAmount = resJson.getBigDecimal("bpartner_amount").add(resJson.getBigDecimal("memberJointMoney"));
            }
            //若计算的数据不为0,才去进行更新
            if(bpartnerAmount.doubleValue()!=0)
            {
                profitTotal = censusProfitTotal.subtract(bpartnerAmount);
                mdyParaMap.put("profitTotal",String.valueOf(profitTotal.doubleValue()));
                orderDao.modifyJointDayCensus(mdyParaMap);
            }
        }
        //移除MAP参数中的zdate,PUT进去 fdate
        paraMap.remove("orderDate");
        paraMap.put("orderDate",mft.format(mft.parse(String.valueOf(mdyAfterOrderMap.get("fdate")))));
        //根据所属合作商ID和订单的到账时间查询合作商订单统计信息
        jointCensusMap = orderDao.queryJointCensusByJointAndDate(paraMap);
        if(null != jointCensusMap)
        {
            //组装更新合作商订单统计信息的MAP
            Map<String, String> mdyParaMap = new HashMap<String, String>();
            mdyParaMap.putAll(paraMap);
            BigDecimal censusAccountTotal = ConversionTypeUtil.conversionToBigDecimal(jointCensusMap.get("account_total"));
            BigDecimal accountTotal = new BigDecimal(0.00);
            BigDecimal bpartnerAmount = new BigDecimal(0.00);
            if(StringUtils.isNotBlank(String.valueOf(mdyAfterOrderMap.get("commission"))))
            {
                JSONObject resJson = JSONObject.parseObject(String.valueOf(mdyAfterOrderMap.get("commission")));
                bpartnerAmount = resJson.getBigDecimal("bpartner_amount").add(resJson.getBigDecimal("memberJointMoney"));
            }
            //若计算的数据不为0,才去进行更新
            if(bpartnerAmount.doubleValue()!=0)
            {
                accountTotal = censusAccountTotal.subtract(bpartnerAmount);
                mdyParaMap.put("accountTotal",String.valueOf(accountTotal.doubleValue()));
                orderDao.modifyJointDayCensus(mdyParaMap);
            }
        }
    }
}
