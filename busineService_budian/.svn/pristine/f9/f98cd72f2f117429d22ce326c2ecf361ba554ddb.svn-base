package com.xmniao.service.message.maibao;

import com.xmniao.service.maibao.MaibaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by yang.qiang on 2017/5/15.
 */
public class MaibaoAdvenceLedgerThread implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(MaibaoAdvenceLedgerThread.class);

    private MaibaoService maibaoService;
    private StringRedisTemplate stringRedisTemplate;
    private String queueKey;

    public MaibaoAdvenceLedgerThread(MaibaoService maibaoService, String queueKey, StringRedisTemplate stringRedisTemplate) {
        this.queueKey = queueKey;
        this.maibaoService = maibaoService;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public void run() {
        logger.info("MaibaoAdvence 脉宝分账通知线程已启动!监听key:"+queueKey);
        while (true) {

            try {
                String ledgerId = stringRedisTemplate.opsForList().rightPop(queueKey, 0, TimeUnit.SECONDS);
                logger.info("脉宝分账通知:从队列" + queueKey + "中取得分账信息id为:" + ledgerId);
                if (ledgerId != null) {
                    System.out.println("向脉宝发送通知.........");
                    maibaoService.advenceLedgerMessage(Integer.valueOf(ledgerId));
                }
            } catch (Exception e) {
                logger.info("脉宝分账通知队列监听出现异常!");
                e.printStackTrace();
            }

        }
    }
}
