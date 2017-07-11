package com.xmn.saas.service.wallet.impl;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.base.thrift.common.ResponseList;
import com.xmn.saas.dao.wallet.SellerDao;
import com.xmn.saas.dao.wallet.SellerDetailedDao;
import com.xmn.saas.entity.wallet.Seller;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.base.SynthesizeService;
import com.xmn.saas.service.base.WithdrawMoneyService;
import com.xmn.saas.service.base.XmniaoWalletService;
import com.xmn.saas.service.wallet.WithdrawalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WithdrawalServiceimpl implements WithdrawalService {
    private final Logger log = LoggerFactory.getLogger(WithdrawalServiceimpl.class);

    @Autowired
    private GlobalConfig globalConfig;
    
    @Autowired
    private SellerDetailedDao sellerDetailedDao;
    
    @Autowired
    private SellerDao sellerDao;

    /**
     * 总店提现授权
     */

    @Override
    public Map<Object, Object> accredit(Integer operatingOut, Integer sellerid) {
        Map<Object, Object> params = new HashMap<>();
        params.put("operatingout", operatingOut);
        params.put("sellerid", sellerid);
        boolean isAccreditOk = false;
        try {
            isAccreditOk = sellerDetailedDao.updateAccreditStatus(params);
            params.clear();
            if (isAccreditOk) {
                params.put("code", ResponseCode.SUCCESS);
                params.put("msg", "修改成功");

            } else {
                params.put("code", ResponseCode.FAILURE);
                params.put("msg", "修改失败");
            }
        } catch (Exception e) {
            log.info("总店提现授权更新失败：" + e);
        }
        return params;

    }


    /**
     * 提现申请
     * @throws SaasException 
     */
    @Override
    public Map<Object, Object> apply(double amount, Integer type, Integer accountId, String name) throws SaasException {
        Map<String,Object> sellerAmountInfoMap =  getSellerAmountInfo(accountId);
        Map<Object, Object> resultMap = new HashMap<>();
        if(sellerAmountInfoMap!=null && sellerAmountInfoMap.size()>0){
            //每日已经提现多少
            Double totalAmount = (Double) sellerAmountInfoMap.get("totalAmount");
            Seller seller = (Seller) sellerAmountInfoMap.get("seller");
            if(seller==null ){
                resultMap.put("code", ResponseCode.PARAM_ERROR);
                resultMap.put("msg", "提现账号异常");
                return resultMap;
            }
            if(totalAmount==null){
                resultMap.put("code", ResponseCode.FAILURE);
                resultMap.put("msg", "累计提现总额错误");
                return resultMap;
            }
            //可提现
            if(seller.getDailyLimitWithdraw()>= (totalAmount +amount)){
                int isOk = 1;
                try {
                    SynthesizeService.Client client =
                            ThriftBuilder.build(globalConfig.getThriftPayHost(),
                                    Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService",
                                    SynthesizeService.Client.class);

                    ThriftBuilder.open();
                    Map<String, String> map = new HashMap<>();
                    map.put("balanceType", type + "");// 提现类型
                    map.put("amount",String.format("%.2f",amount));// 提现金额
                    if(seller.getIsPaid()==1){
                        map.put("expense", 2 + "");// 付费商家2元
                    }else if(seller.getIsPaid()==0){
                        map.put("expense", 2 + "");// 非付费商家2元
                    }
                    List<Map<String, String>> amountMapList = new ArrayList<>();
                    amountMapList.add(map);

                    Map<String, String> orderMap = new HashMap<>();
                    orderMap.put("uId", accountId + "");
                    orderMap.put("userType", "2");// 商户类型
                    orderMap.put("purpose", "提现");// 提现目的
                    orderMap.put("tdesc", "提现");// 提现目的
                    orderMap.put("purpose", "提现");// 提现目的
                    orderMap.put("cash", "1");// 商户提现
                    orderMap.put("recchannel", "2");// 商户版APP
                    orderMap.put("name", name==null?"":name);// 商户昵称
                    log.info("访问updateWithdrawalsRecord返回数据,【请求参数】：" +amountMapList +"---"+orderMap);
                    isOk = client.updateWithdrawalsRecord(amountMapList, orderMap);
                    log.info("isOk:" + isOk);
                    if (isOk == 0) {
                        resultMap.put("code", ResponseCode.SUCCESS);
                        resultMap.put("msg", "提现成功");
                    } else if (isOk == 1) {
                        resultMap.put("code", ResponseCode.FAILURE);
                        resultMap.put("msg", "提现失败");
                    } else if (isOk == 2) {
                        resultMap.put("code", ResponseCode.PARAM_ERROR);
                        resultMap.put("msg", "参数异常");
                    } else if (isOk == 3) {
                        resultMap.put("code", ResponseCode.MAX_AMOUNT);
                        resultMap.put("msg", "提现金额已经超过当天限额");
                    } else if (isOk == 4) {
                        resultMap.put("code", ResponseCode.PARAM_ERROR);
                        resultMap.put("msg", "提现账号异常");
                    } else {
                        resultMap.put("code", ResponseCode.FAILURE);
                        resultMap.put("msg", "提现失败");
                    }

                } catch (Exception e) {
                    log.error("调用支付系统接口提现申请异常！", e);
                    throw new SaasException("调用支付系统接口提现申请异常！");

                } finally {
                    ThriftBuilder.close();
                }
                
                
                
                
            }else{
                resultMap.put("code", ResponseCode.MAX_AMOUNT);
                resultMap.put("msg", "提现金额已经超过当天限额"); 
            }
        }
        
        
        
        
        return resultMap;
    }


    /**
     * 获取钱包的提现列表
     */
    @Override
    public ResponseList getXmnWithdrawList(Map<String, String> walletMap) {
        ResponseList responseList = null;
        try {
            XmniaoWalletService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()),
                            "XmniaoWalletService", XmniaoWalletService.Client.class);

            ThriftBuilder.open();
            responseList = client.getXmnWithdrawList(walletMap);

        } catch (Exception e) {
            log.error("调用支付系统接口获取钱包的提现列表异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responseList;

    }

    
    /**
     * 提现申请列表
     * 
     * @param walletMap
     * @return
     */
    @Override
    public Map<Object, Object> list(Map<String, String> walletMap) {
        Map<Object, Object> resultMap = new HashMap<Object, Object>();
        log.info("访问getXmnWithdrawList返回数据,【请求参数】：" +walletMap);
        ResponseList responseList = this.getXmnWithdrawList(walletMap);
        if(responseList.getDataInfo()!=null && responseList.getDataInfo().getState()==0){
            resultMap.put("code", ResponseCode.SUCCESS);
            resultMap.put("msg", "成功！");
            if( responseList.getDataInfo() !=null && responseList.getDataInfo().getResultMap()!=null){
                resultMap.put("totalAmount", responseList.getDataInfo().getResultMap().get("totalAmount"));//总提现金额
                resultMap.put("count", responseList.getDataInfo().getResultMap().get("count"));//总记录数
                resultMap.put("pageCount", responseList.getDataInfo().getResultMap().get("pageCount"));//总页数
            }
            //数据详情
            resultMap.put("dataList", responseList.getDataList());
        }
        
        return resultMap;
    }

    @Override
    public Integer queryOperatingOut(Integer sellerid) {
        return sellerDetailedDao.selectOpertingOutBySellerid(sellerid);
    }
    
    
    
    /**
     * 
     * 判断是否连锁店
     * 
     * 判断是否允许总店提现
     * 
     * 
     */
    @Override
    public  Map<String,Object>  isCanDraw(int sellerid , int type){
        
        Map<String,Object> returnMap = new HashMap<String,Object>();
        
        Map<String,Long> rmap = null;
        
        try{
            rmap = sellerDetailedDao.queryChainAndHeadDraw(sellerid);
        }catch(Exception e){
            log.info("查询商户详情表异常",e);
        }
        
        if(rmap == null){//没有查到限制，允许提现
            returnMap.put("isChain", true);
            //return true;
        }
        
        Long  ischain = rmap.get("ischain") == null ? 0 : rmap.get("ischain");
        Long  rebateout = rmap.get("rebateout") == null ? 0 : rmap.get("rebateout");//返利
        Long  operatingout = rmap.get("operatingout") == null ? 0 : rmap.get("operatingout");//营收
        Double amountlimit = Double.parseDouble(String.valueOf(rmap.get("amount_limit")));
        
        if(ischain == 0){//不是连锁店,允许该商户提现
            returnMap.put("isChain", true);
        }else{
            if(3 == type){//营收提现
                if(operatingout == 0){//营收不允许总店提现，允许该商户提现
                    returnMap.put("isChain", true);
                }
            }else if(1 == type){
                if(rebateout == 0){//返利不允许总店提现，允许该商户提现
                    returnMap.put("isChain", true);
                }
            }
        }
        
        returnMap.put("amountlimit", amountlimit);
        
        return returnMap;
    }
    
    /**
     * 获取商户提现限制信息
     * @param accountId
     * @return
     */
    public Map<String,Object> getSellerAmountInfo(Integer accountId){
        Map<String,Object> map = new HashMap<>();
        try {
            WithdrawMoneyService.Client withdrawMoneyServiceClient =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "WithdrawMoneyService",
                            WithdrawMoneyService.Client.class);

            ThriftBuilder.open();
            Map<String,String> orderMap =new HashMap<>();
            orderMap.put("uId", accountId + "");
            orderMap.put("userType", "2");// 商户类型
            log.info("调用【countTodayWithdrawAmount】统计提现总额接口 参数为： "+ orderMap);
            Map<String, String> resultMap = withdrawMoneyServiceClient.countTodayWithdrawAmount(orderMap);
            Double totalAmount = 0.00;
            if(resultMap!=null && resultMap.size()>0){
                totalAmount = Double.valueOf(resultMap.get("totalAmount"));
                //每日已经提现多少
                map.put("totalAmount",totalAmount);
            }
            Seller seller = sellerDao.findBySellerid(accountId);
            map.put("seller", seller);
            
        } catch (Exception e) {
            log.error("调用【countTodayWithdrawAmount】统计提现总额接口提现申请异常！", e);
            throw new SaasException("调用【countTodayWithdrawAmount】统计提现总额接口提现申请异常！");

        } finally {
            ThriftBuilder.close();
        }
        return map;
    }


    @Override
    public boolean isonline(int sellerid, int type) {
        Seller seller = sellerDao.findBySellerid(sellerid);
        if(seller.getIsonline()!=null && seller.getIsonline()==1){
            return true;
        }
        return false;
    }
}
