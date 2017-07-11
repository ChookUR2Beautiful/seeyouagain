package com.xmn.saas.service.wallet.impl;

import java.util.HashMap;
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
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.base.SynthesizeService;
import com.xmn.saas.service.wallet.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private RedisService redisService;

    private final Logger log = LoggerFactory.getLogger(PasswordServiceImpl.class);


    /**
     * 首次设置密码
     *
     */
    @Override
    public Map<Object, Object> setPassword(SellerAccount account) {
        // 【商户id】，【旧密码】，【用户类型 1普通用户 2商家 3合作商
        return resultMap(updateWalletPwd(account.getSellerid() + "", account.getPassword(), 2));
    }


    /**
     * 重置密码
     *
     */
    @Override
    public Map<Object, Object> resetPassword(SellerAccount account) {
        // 【商户id】，【旧密码】，【用户类型 1普通用户 2商家 3合作商
        return resultMap(updateWalletPwd(account.getSellerid() + "", account.getPassword(), 2));

    }

    /**
     * 验证密码
     */
    @Override
    public Map<Object, Object> verifyPassword(SellerAccount account) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        // 【商户id】，【旧密码】，【用户类型 1普通用户 2商家 3合作商
        int isCheckWalletPwdOk =
                verifyWalletPwd(account.getSellerid() + "", account.getPassword(), 2);
        if (0 == isCheckWalletPwdOk) {
            // 验证成功
            map.put("code", ResponseCode.SUCCESS);
            map.put("msg", "验证密码成功！");
        } else if (1 == isCheckWalletPwdOk) {
            map.put("code", ResponseCode.FAILURE);
            map.put("msg", "验证密码失败！");
        } else if (2 == isCheckWalletPwdOk) {
            map.put("code", ResponseCode.PARAM_ERROR);
            map.put("msg", "参数异常！");
        } else if (3 == isCheckWalletPwdOk) {
            map.put("code", ResponseCode.PARAM_ERROR);
            map.put("msg", "用户不存在！");
        } else {
            map.put("code", ResponseCode.FAILURE);
            map.put("msg", "验证密码失败！");
        }
        return map;
    }

    public Map<Object, Object> resultMap(int isCheckWalletPwdOk) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (0 == isCheckWalletPwdOk) {
            // 验证成功
            map.put("code", ResponseCode.SUCCESS);
            map.put("msg", "设置支付密码成功！");
        } else if (1 == isCheckWalletPwdOk) {
            map.put("code", ResponseCode.FAILURE);
            map.put("msg", "设置支付密码失败！");
        } else if (2 == isCheckWalletPwdOk) {
            map.put("code", ResponseCode.PARAM_ERROR);
            map.put("msg", "参数异常！");
        } else if (3 == isCheckWalletPwdOk) {
            map.put("code", ResponseCode.PARAM_ERROR);
            map.put("msg", "用户不存在！");
        } else {
            map.put("code", ResponseCode.FAILURE);
            map.put("msg", "设置支付密码失败！");
        }
        return map;
    }

    private int updateWalletPwd(String sellerId, String password, int type) {
        int isUpdateWalletPwdOk = 1;
        try {
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(), Integer.parseInt(globalConfig
                    .getThriftPayPort()), "SynthesizeService", SynthesizeService.Client.class);
            
            ThriftBuilder.open();
            isUpdateWalletPwdOk = client.updateWalletPwd(sellerId, password, type);// 【商户id】，【旧密码】，【用户类型
            // 1普通用户 2商家 3合作商
            log.info("isUpdateWalletPwdOk:" + isUpdateWalletPwdOk);


        } catch (Exception e) {
            log.error("调用支付系统接口设置钱包密码异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return isUpdateWalletPwdOk;
    }

    private int verifyWalletPwd(String sellerId, String password, int type) {
        int isVerifyWalletPwdOk = 300;
        try {
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(), Integer.parseInt(globalConfig
                    .getThriftPayPort()), "SynthesizeService", SynthesizeService.Client.class);
            
            ThriftBuilder.open();
            isVerifyWalletPwdOk = client.checkWalletPwd(sellerId, password, type);// 【商户id】，【旧密码】，【用户类型
            // 1普通用户 2商家 3合作商
            log.info("isVerifyWalletPwdOk:" + isVerifyWalletPwdOk);


        } catch (Exception e) {
            log.error("调用支付系统接口验证钱包密码异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return isVerifyWalletPwdOk;
    }



}
