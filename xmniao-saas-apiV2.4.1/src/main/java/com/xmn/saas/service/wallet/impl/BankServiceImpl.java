package com.xmn.saas.service.wallet.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.base.thrift.tbase.MentionAccount;
import com.xmn.saas.dao.wallet.BankDao;
import com.xmn.saas.entity.wallet.BankApply;
import com.xmn.saas.entity.wallet.BankList;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.base.SynthesizeService;
import com.xmn.saas.service.wallet.BankService;


@Service
public class BankServiceImpl implements BankService {

    private final Logger log = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private GlobalConfig globalConfig;
    
    @Autowired
    private BankDao bankDao;

    @Override
    public Map<Object, Object> put(BankApply bankApply) {
        if (bankApply.getAppid() != null) {
            return resultMap(updateMentionAccount(bankApply));
        } else {
            return resultMap(addMentionAccount(bankApply));
        }
    }

    public Map<Object, Object> resultMap(int isOk) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (0 == isOk) {
            // 修改成功
            map.put("code", ResponseCode.SUCCESS);
            map.put("msg", "绑定/修改银行卡成功！");
        } else if (1 == isOk) {
            map.put("code", ResponseCode.FAILURE);
            map.put("msg", "绑定/修改银行卡失败！");
        } else if (2 == isOk) {
            map.put("code", ResponseCode.PARAM_ERROR);
            map.put("msg", "参数异常！");
        } else if (3 == isOk) {
            map.put("code", ResponseCode.PARAM_ERROR);
            map.put("msg", "银行卡已添加！");
        } else {
            map.put("code", ResponseCode.FAILURE);
            map.put("msg", "绑定/修改银行卡失败！");
        }
        return map;
    }


    // 请求支付服务，更新银行卡
    private int updateMentionAccount(BankApply bankApply) {
        int isUpdateMentionAccountOk = 1;
        try {
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(), Integer.parseInt(globalConfig
                    .getThriftPayPort()), "SynthesizeService", SynthesizeService.Client.class);
            
            ThriftBuilder.open();
            // 主键，银行卡类型，银行卡号，默认储蓄卡，持卡人姓名，支行名称，银行卡预留手机号码，用途，用户类型，对公对私，证件类型，默认身份证，证件证号码，开户行名称，开户行缩写
            isUpdateMentionAccountOk =
                    client.updateMentionAccount(bankApply.getAppid() + "", 2,
                            bankApply.getBankid(), 1, bankApply.getFullname(),
                            bankApply.getBankname(), bankApply.getBankphone(), 1, bankApply.getCityname(),
                            bankApply.getIspublic(), 1, bankApply.getIdcard(), bankApply.getBank(),
                            bankApply.getAbbrev(), bankApply.getLocation());
            // 1普通用户 2商家 3合作商
            log.info("isUpdateMentionAccountOk:" + isUpdateMentionAccountOk);


        } catch (Exception e) {
            log.error("调用支付系统接口修改银行卡异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return isUpdateMentionAccountOk;
    }

    // 请求支付服务，添加银行卡
    private int addMentionAccount(BankApply bankApply) {
        int addMentionAccount = 1;
        try{
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(), Integer.parseInt(globalConfig
                    .getThriftPayPort()), "SynthesizeService", SynthesizeService.Client.class);
            
            ThriftBuilder.open();

            // 用途，
            // 商户id，银行卡类型，银行卡号，默认储蓄卡，持卡人姓名，支行名称，银行卡预留手机号码，用途，用户类型，对公对私，证件类型，默认身份证，证件证号码，开户行名称，开户行缩写
            // int isuse, String uId, int type, String account, int cardType, String userName,
            // String bankName, String mobileId, int userType, int ispublic, int idtype, String
            // identity, String bank, String abbrev, String province, String cityname
            addMentionAccount =
                    client.AddMentionAccount(1, bankApply.getSellerId() + "", 2,
                            bankApply.getBankid(), 1, bankApply.getFullname(),
                            bankApply.getBankname(), bankApply.getBankphone(), 2,
                            bankApply.getIspublic(), 1, bankApply.getIdcard(), bankApply.getBank(),
                            bankApply.getAbbrev(), bankApply.getLocation(), bankApply.getCityname());
            // 1普通用户 2商家 3合作商
            log.info("addMentionAccount:" + addMentionAccount);


        } catch (Exception e) {
            log.error("调用支付系统接口添加银行卡异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return addMentionAccount;
    }
    /**
     * 获取银行卡列表
     */
    @Override
    public Map<String,Object> list(String sellerId) {
        List<BankApply> bankList = new ArrayList<BankApply>();
        Map<String,Object> resultMap = new HashMap<>();
        try {
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(), Integer.parseInt(globalConfig
                    .getThriftPayPort()), "SynthesizeService", SynthesizeService.Client.class);
            
            ThriftBuilder.open();
            Map<String,String> params = new HashMap<>();
            params.put("uId", sellerId);
            params.put("userType", 2+"");
            log.info("访问getMentionAccountList返回数据,【请求参数】：" +params);
            MentionAccount mentionAccount =  client.getMentionAccountList(params);
            //获取银行卡列表
            List<Map<String,String>>  list = mentionAccount.getAccountList();
            if (list != null && list.size() > 0) {
                for (Map<String, String> map : list) {
                    BankApply bankApply = new BankApply();
                    bankApply.setAppid(Integer.valueOf(map.get("id")));
                    bankApply.setSellerId(Integer.valueOf(map.get("uId")));
                    bankApply.setType(2);
                    bankApply.setBankid(map.get("account"));
                    bankApply.setIdtype(1);
                    bankApply.setFullname(map.get("username"));
                    bankApply.setBankname(map.get("bankname"));
                    bankApply.setBankphone(map.get("mobileid"));
                    bankApply.setIspublic(Integer.valueOf(map.get("setIspublic")==null?0+"":map.get("setIspublic")));
                    bankApply.setIdtype(Integer.valueOf(map.get("idtype")));
                    bankApply.setIdcard(map.get("identity"));
                    bankApply.setBank(map.get("bank"));
                    bankApply.setAbbrev(map.get("abbrev"));
                    bankList.add(bankApply);
                }
                resultMap.put("dataList", bankList);
                resultMap.put("count", mentionAccount.getCount());
                resultMap.put("pageCount", mentionAccount.getPageCount());
            }

        } catch (Exception e) {
            log.error("调用支付系统接口查询银行卡列表异常！", e);

        } finally {
            ThriftBuilder.close();
        }

        return resultMap;
    }
    /**
     * 删除银行卡
     */
    @Override
    public Map<Object, Object> delete(String id) {
        Map<Object, Object> resultMap = new HashMap<>();
        int isOk = 1;
        try {
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(), Integer.parseInt(globalConfig
                    .getThriftPayPort()), "SynthesizeService", SynthesizeService.Client.class);
            
            ThriftBuilder.open();
            isOk = client.delMentionAccount(id);
            if (0 == isOk) {
                // 修改成功
                resultMap.put("code", ResponseCode.SUCCESS);
                resultMap.put("msg", "删除成功！");
            } else if (1 == isOk) {
                resultMap.put("code", ResponseCode.FAILURE);
                resultMap.put("msg", "删除失败！");
            } else if (2 == isOk) {
                resultMap.put("code", ResponseCode.PARAM_ERROR);
                resultMap.put("msg", "参数异常！");
            } else {
                resultMap.put("code", ResponseCode.FAILURE);
                resultMap.put("msg", "删除失败！");
            }
            
        } catch (Exception e) {
            log.error("调用支付系统接口删除银行卡详情异常！", e);
        } finally {
            ThriftBuilder.close();
        } 
            
        
        return resultMap;
    }
    
    /**
     * 查询所有银行列表
     */
	@Override
	public List<BankList> bankList() {
		
		return bankDao.bankList();
	}


}
