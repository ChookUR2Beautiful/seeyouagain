package com.xmniao.service.message.maibao;

import com.xmniao.service.maibao.MaibaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yang.qiang on 2017/5/15.
 */
public class MaibaoAdvenceLedgerPool implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(MaibaoAdvenceLedgerPool.class);

    private String queueKey;

    @Autowired
    private MaibaoService maibaoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public MaibaoAdvenceLedgerPool(String queueKey) {
        this.queueKey = queueKey;
    }

    public MaibaoAdvenceLedgerPool() {
        super();
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new MaibaoAdvenceLedgerThread(maibaoService,queueKey,stringRedisTemplate));

    }
}
