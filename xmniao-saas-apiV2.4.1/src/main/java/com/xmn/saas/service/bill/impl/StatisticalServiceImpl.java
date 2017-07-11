package com.xmn.saas.service.bill.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.base.thrift.common.ResponsePageList;
import com.xmn.saas.dao.bill.BillDao;
import com.xmn.saas.entity.bill.Bill;
import com.xmn.saas.entity.bill.BillList;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.XmniaoWalletService;
import com.xmn.saas.service.bill.BillService;
import com.xmn.saas.service.bill.StatisticalService;
import com.xmn.saas.utils.CalendarUtil;
@Service
public class StatisticalServiceImpl implements StatisticalService {
    private final Logger log = LoggerFactory.getLogger(StatisticalServiceImpl.class);

    @Autowired
    private GlobalConfig globalConfig;
    
    @Autowired
    private BillDao billDao;
    
    @Autowired
    private BillService billService;

    /**
     * 获取商户的历史营收列表
     * 
     * @return
     */
    @Override
    public ResponsePageList getBusinessList(Map<String, String> map) {
        ResponsePageList responsePageList = null;
        try {
            XmniaoWalletService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()),
                            "XmniaoWalletService", XmniaoWalletService.Client.class);

            ThriftBuilder.open();
            responsePageList = client.getBusinessList(map);

        } catch (Exception e) {
            log.error("调用支付系统接口获取商户的历史营收列表异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responsePageList;
    }

    /**
     * 根据条件查询账单金额统计信息
     * 
     * @throws ParseException
     */
    @Override
    public Map<String, Object> get_by(Map<String, String> map, Integer type) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        // 访问支付库接口获取返回数据
        log.info("访问getBusinessList返回数据,【请求参数】：" +map);
        ResponsePageList responseList = this.getBusinessList(map);
        if (responseList != null) {
            ResponseData dataInfo = responseList.getDataInfo();
            if (dataInfo.getState() == 0) {// 访问成功
                List<Map<String, String>> dataList = responseList.getPageList();
                Double totalAmount = 0.0;// 周总收入
                if (dataList != null && dataList.size() > 0) {
                    resultMap.put("code", ResponseCode.SUCCESS);
                    resultMap.put("msg", "请求成功！");
                    for (Map<String, String> dataMap : dataList) {
                        String sellerAmount = dataMap.get("sellerAmount");// 店内收益
                        String date = dataMap.get("date");// 日期
                        String week = CalendarUtil.getWeek(CalendarUtil.sdf.parse(date));// 星期几
                        // 店长
                        if (type == 2) {
                            totalAmount += Double.valueOf(sellerAmount);
                            resultMap.put(week, sellerAmount);// 店内收益
                        }
                        // 老板
                        else if (type == 1) {
                            resultMap.put(week, dataMap.get("dayAmount"));// 店内外收益
                        }

                    }
                    // 返回周总营收
                    // 店长
                    if (type == 2) {
                        resultMap.put("totalAmount", totalAmount);
                    }
                    // 老板
                    else if (type == 1) {
                        resultMap.put("totalAmount", dataInfo.getResultMap().get("totalAmount"));
                    }

                }
            }
        } else {
            resultMap.put("code", ResponseCode.FAILURE);
            // 店员无权访问
            if (type == 3) {
                resultMap.put("msg", "店员无权访问！");
            } else {
                resultMap.put("msg", "获取数据失败！");
            }

        }
        log.info("访问getBusinessList返回数据,组装的resultMap  "+resultMap);
        return resultMap;

    }

    /**
     * 寻蜜鸟钱包可提现余额及提现统计
     * 
     * @param walletMap
     * @return
     */
    @Override
    public ResponseData getXmnWithdrawAmount(Map<String, String> walletMap) {
        ResponseData responseData = null;
        try {
            XmniaoWalletService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()),
                            "XmniaoWalletService", XmniaoWalletService.Client.class);

            ThriftBuilder.open();
            responseData = client.getXmnWithdrawAmount(walletMap);

        } catch (Exception e) {
            log.error("调用支付系统接口寻蜜鸟钱包可提现余额及提现统计异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responseData;
    }

    /**
     * 获取商户当天经营收支信息
     * 
     * @param walletMap
     * @return
     */
    @Override
    public ResponseData getBusinessInfo(Map<String, String> walletMap) {
        ResponseData responseData = null;
        try {
            XmniaoWalletService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()),
                            "XmniaoWalletService", XmniaoWalletService.Client.class);

            ThriftBuilder.open();
            responseData = client.getBusinessInfo(walletMap);

        } catch (Exception e) {
            log.error("调用支付系统接口获取商户当天经营收支信息异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responseData;
    }

    /**
     * 账单金额统计
     */
    @Override
    public Map<String, Object> amount(SellerAccount sellerAccount) {
        Map<String, String> walletMap = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        walletMap.put("uId", sellerAccount.getSellerid() + "");
        walletMap.put("userType", "2");
        // 访问寻蜜鸟钱包可提现余额及提现统计接口
        log.info("访问getXmnWithdrawAmount返回数据,【请求参数】：" +walletMap);
        ResponseData responseData = this.getXmnWithdrawAmount(walletMap);
        if (responseData != null && responseData.getState() == 0) {

            // 老板
            if (sellerAccount.getType() == 1) {
                resultMap.put("amount", Double.valueOf(responseData.getResultMap().get("commison"))
                        + Double.valueOf(responseData.getResultMap().get("sellerAmount")));//余额
                resultMap.put("withdrawalCount", Integer.valueOf(responseData.getResultMap().get("commisonNum"))
                        + Integer.valueOf(responseData.getResultMap().get("sellerAmountNum")));//已提总记录
                resultMap.put("withdrawalMoney", Double.valueOf(responseData.getResultMap().get("commisonCount"))
                        + Double.valueOf(responseData.getResultMap().get("sellerAmountCount")));//已提现总金额
                resultMap.put("type",1);
            }
            //店长
            else if (sellerAccount.getType() == 2) {
                resultMap.put("amount", Double.valueOf(responseData.getResultMap().get("sellerAmount")));//余额
                resultMap.put("withdrawalCount", Integer.valueOf(responseData.getResultMap().get("sellerAmountNum")));//已提总记录
                resultMap.put("withdrawalMoney", Double.valueOf(responseData.getResultMap().get("sellerAmountCount")));//已提现总金额
                resultMap.put("type",2);
            }
            else if(sellerAccount.getType() == 3){
                resultMap.put("type",3);
            }

        }
        
        // 访问获取商户当天经营收支信息接口
        ResponseData businessData = this.getBusinessInfo(walletMap);
        if(businessData != null && businessData.getState() == 0){
            walletMap.put("sdate", CalendarUtil.getDateString(new Date(), "yyyy-MM-dd")+" 00:00:00");
            walletMap.put("edate", CalendarUtil.getDateString(new Date(), "yyyy-MM-dd")+" 23:59:59");
            List<BillList> todayList = billDao.findBillCount(walletMap);
            Double todayAmount = 0.0;// 总金额
            
            List<Map<String,String>> todayParaList =new  ArrayList<Map<String,String>>();
            for (BillList vo : todayList) {
                todayParaList.add(getMap(vo.getBidno(), vo.getBillType()+""));//封装参数
               // totalAmount += this.getOderInfoSelleramount(vo);//实际支付金额
            }
            
            resultMap.put("todayAmount", String.format("%.2f",billService.getOrderLedgerInfoListSellerAmount(todayParaList)));// 今日营收
            
            walletMap.put("sdate", "2000-01-01 00:00:00");
            walletMap.put("edate", CalendarUtil.getDateString(new Date(), "yyyy-MM-dd")+" 23:59:59");
            List<BillList> totalList = billDao.findBillCount(walletMap);
            Double totalAmount = 0.0;// 总金额
            List<Map<String,String>> totalParaList =new  ArrayList<Map<String,String>>();
            for (BillList vo : totalList) {
                totalParaList.add(getMap(vo.getBidno(), vo.getBillType()+""));//封装参数
               // totalAmount += this.getOderInfoSelleramount(vo);//实际支付金额
            }
            resultMap.put("totalAmount", String.format("%.2f",billService.getOrderLedgerInfoListSellerAmount(totalParaList)));// 总营收
            
            //resultMap.put("todayAmount", businessData.getResultMap().get("sellerAmount"));//今日营收
            //resultMap.put("totalAmount", businessData.getResultMap().get("sellerAmountCount"));//总营收
            resultMap.put("todayEarnings", businessData.getResultMap().get("commison"));//今日收益
            resultMap.put("totalEarnings", businessData.getResultMap().get("commisonCount"));//总收益
            resultMap.put("todayOffer", businessData.getResultMap().get("expendAmount"));//今日支出
            resultMap.put("totalOffer", businessData.getResultMap().get("expendCount"));//总支出
            resultMap.put("offerTimes", businessData.getResultMap().get("expendNum"));//支出笔数
            Map<String,String> params = new HashMap<>();
            params.put("sellerid", sellerAccount.getSellerid()+"");
            params.put("zdate", CalendarUtil.getDateString(new Date(), "yyyy-MM-dd"));
            Map<Object,Object> result =  billDao.findMember(params);
            resultMap.put("bindingMember", result.get("bindCount"));//绑定会员
            resultMap.put("normalMember", result.get("normalCount"));//普通会员
            resultMap.put("newMember",(long) result.get("normalCount")+(long)result.get("bindCount"));//新增会员
        }

        return resultMap;
    }
    
    public  Map<String,String> getMap(String bid,String btype){
        Map<String,String>  map=  new HashMap<>();
        map.put("bid", bid);
        map.put("btype", btype);
        return map;
   }

}
