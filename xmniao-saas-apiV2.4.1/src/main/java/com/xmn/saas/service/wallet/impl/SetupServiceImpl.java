package com.xmn.saas.service.wallet.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.service.base.SynthesizeService;
import com.xmn.saas.service.wallet.SetupService;


@Service
public class SetupServiceImpl implements SetupService {

    private final Logger log = LoggerFactory.getLogger(SetupServiceImpl.class);

    @Autowired
    private GlobalConfig globalConfig;

    /**
     * 钱包管理（设置自动分账）
     * 
     * @param Integer ledger 是否设置自动分账 0不自动分账，1自动分账 2 查询状态
     * @param Integer sellerId 商户id
     * 
     */
    @Override
    public Map<String, Object> autoLedger(Integer ledger, Integer sellerId) {
        Map<String, String> map = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            SynthesizeService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService",
                            SynthesizeService.Client.class);

            ThriftBuilder.open();
            if (ledger == 2) {
                map = client.getMentionLedger(2, sellerId + "");// 查询自动分账的状态
                if (map != null && map.get("state") != null) {
                    if (map.get("state").equals("0")) {
                        resultMap.put("code", 0);
                        resultMap.put("msg", "手动");
                        resultMap.put("state", 0);
                    } else if (map.get("state").equals("1")) {
                        resultMap.put("code", 0);
                        resultMap.put("msg", "自动");
                        resultMap.put("state", 1);
                    } else if (map.get("state").equals("2")) {
                        resultMap.put("code", 2);
                        resultMap.put("msg", "查询异常");
                    } else if (map.get("state").equals("3")) {
                        resultMap.put("code", 3);
                        resultMap.put("msg", "用户信息异常");
                    }
                } else {
                    resultMap.put("code", 2);
                    resultMap.put("msg", "查询异常");
                }

            } else {
                int isupdateMentionLedgerOk =
                        client.updateMentionLedger(2, ledger, sellerId + "", "0", "0");// 设置自动分账的状态
                if (isupdateMentionLedgerOk == 0) {
                    resultMap.put("code", 0);
                    resultMap.put("msg", "修改成功");
                } else if (isupdateMentionLedgerOk == 1) {
                    resultMap.put("code", 1);
                    resultMap.put("msg", "修改失败");
                } else if (isupdateMentionLedgerOk == 2) {
                    resultMap.put("code", 2);
                    resultMap.put("msg", "修改异常");
                } else if (isupdateMentionLedgerOk == 3) {
                    resultMap.put("code", 3);
                    resultMap.put("msg", "用户信息异常");
                }
            }

        } catch (Exception e) {
            log.error("调用支付系统接口修改分账方式异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return resultMap;


    }

    @Override
    public Map<String, Object> autoWithdrawal(Integer mention, Integer sellerId, double money,
            double cmAmount) {
        Map<String, String> map = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            SynthesizeService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService",
                            SynthesizeService.Client.class);

            ThriftBuilder.open();
            if (mention == 2) {
                map = client.getMentionLedger(1, sellerId + "");// 查询自动提现的状态
                if (map != null && map.get("state") != null) {
                    if (map.get("state").equals("0")) {
                        resultMap.put("code", 0);
                        resultMap.put("msg", "手动");
                        resultMap.put("state", 0);
                    } else if (map.get("state").equals("1")) {
                        resultMap.put("code", 0);
                        resultMap.put("msg", "自动");
                        resultMap.put("state", 1);
                        resultMap.put("money", map.get("income"));//店内收益
                        resultMap.put("cmAmount", map.get("money"));//店外收益
                        
                    } else if (map.get("state").equals("2")) {
                        resultMap.put("code", 2);
                        resultMap.put("msg", "查询异常");
                    } else if (map.get("state").equals("3")) {
                        resultMap.put("code", 3);
                        resultMap.put("msg", "用户信息异常");
                    }
                } else {
                    resultMap.put("code", 2);
                    resultMap.put("msg", "查询异常");
                }

            } else {
                int isupdateMentionLedgerOk =
                        client.updateMentionLedger(1, mention, sellerId + "", cmAmount+"", money+"");// 设置自动提现的状态
                if (isupdateMentionLedgerOk == 0) {
                    resultMap.put("code", 0);
                    resultMap.put("msg", "修改成功");
                } else if (isupdateMentionLedgerOk == 1) {
                    resultMap.put("code", 1);
                    resultMap.put("msg", "修改失败");
                } else if (isupdateMentionLedgerOk == 2) {
                    resultMap.put("code", 2);
                    resultMap.put("msg", "修改异常");
                } else if (isupdateMentionLedgerOk == 3) {
                    resultMap.put("code", 3);
                    resultMap.put("msg", "用户信息异常");
                }
            }

        } catch (Exception e) {
            log.error("调用支付系统接口修改提现方式异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return resultMap;
    }


}
