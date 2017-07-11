package com.xmn.saas.service.pay.impl;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.base.SynthesizeService;
import com.xmn.saas.service.pay.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * create 2016/10/26
 * 支付服务实现类
 * @author yangQiang
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private GlobalConfig globalConfig;

    @Override
    public Map<String, Object> checkPayPasswd(String payPasswd, SellerAccount sellerAccount) throws Exception {

        // Redis统计输错密码的数据的字段
        String redisKeyCount = RedisService.PREFIX_PAY_COUNT + sellerAccount.getSellerid();
        String value = redisService.getString(redisKeyCount);
        Integer count;
        if (value == null) {
            count = 0;
        }else {
            count = new Integer(value);
        }

        // 封装响应数据
        Map<String, Object> result = new HashMap<>();
        // 如果记录数 >= 3 表示该账户已被冻结
        if (count >= 3){
            result.put("count", count);
            result.put("status",1);
            return result;
        }

        // 检查支付密码
        try {
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(),Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService", SynthesizeService.Client.class);
            ThriftBuilder.open();

            // 调用验证钱包密码接口, 验证成功返回 0
            Integer code = client.checkWalletPwd(sellerAccount.getSellerid() + "", payPasswd, 2);
            if (code == 0){
                result.put("count",0);
                result.put("status",0);
                // 输入正确删除这个统计
                redisService.deleteKey(redisKeyCount);
                return result;
            }else { // 如果密码输入错误, 修改响应参数
                // 支付密码输入错误统计次数+1
                redisService.increment(redisKeyCount);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MINUTE,globalConfig.getPayCountTime());
                redisService.setExpire(redisKeyCount,calendar.getTime());

                result.put("count",++count);
                result.put("status",1);
                return result;
            }
        } finally {
            ThriftBuilder.close();
        }
    }
}
