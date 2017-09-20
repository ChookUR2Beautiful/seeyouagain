package com.xmn.designer.service.order.impl;

import com.xmn.designer.base.GlobalConfig;
import com.xmn.designer.base.ThriftBuilder;
import com.xmn.designer.entity.account.User;
import com.xmn.designer.service.base.RedisService;
import com.xmn.designer.service.base.SynthesizeService;
import com.xmn.designer.service.order.WalletService;
import com.xmn.designer.utils.MD5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Service
public class WalletServiceImpl implements WalletService {
    private final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);


    @Autowired
    private GlobalConfig globalConfig;
    
    @Autowired
    private RedisService redisService;

    // 调用支付服务获取钱包余额
    @Override
    public Map<String, String> getWalletBalance(User user) {
        Map<String, String> map = new HashMap<>();
        Long  userId = user.getOutId();
        
        try {
            SynthesizeService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService",
                            SynthesizeService.Client.class);

            ThriftBuilder.open();
            map = client.getWalletBalance(userId + "", 2);


        } catch (Exception e) {
            log.error("调用支付系统接口查询钱包余额异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return map;
    }

    
    
    /**
     * 更新钱包余额
     * @Title: updateWalletAmount 
     * @param @param paramMap
     * @param @return    设定文件 
     * @return Map<String,String>    返回类型 
     * @throws
     */
    public Map<String, String> updateWalletAmount(Map<String, String> paramMap){
    	Map<String, String> resultMap=new HashMap<String, String>();
		try {
			SynthesizeService.Client client =ThriftBuilder.build(globalConfig.getThriftPayHost(),
			                 Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService",
			                 SynthesizeService.Client.class);
			 ThriftBuilder.open();
			 resultMap= client.updateWalletAmount(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调用支付服务更新钱包余额异常！", e);
		} finally {
            ThriftBuilder.close();
        }
    	return resultMap;
    }
    
    
    @Override
    public Map<String, Object> checkPayPasswd(String payPasswd, User user) throws Exception {

        // Redis统计输错密码的数据的字段
        String redisKeyCount = RedisService.PREFIX_DESIGNER_PAY_COUNT + user.getOutId();
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
            Integer code = client.checkWalletPwd(user.getOutId() + "", payPasswd, 2);
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
