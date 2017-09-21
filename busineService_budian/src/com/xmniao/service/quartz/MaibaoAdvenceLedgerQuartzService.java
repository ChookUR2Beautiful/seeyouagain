package com.xmniao.service.quartz;

import com.xmniao.domain.maibao.MaibaoLedger;
import com.xmniao.service.maibao.MaibaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Created by yang.qiang on 2017/5/15.
 */
public class MaibaoAdvenceLedgerQuartzService {
    private final Logger logger = LoggerFactory.getLogger(MaibaoAdvenceLedgerQuartzService.class);
    @Autowired
    private MaibaoService maibaoService;

    public void scanAndSendLedgerMessage() throws IOException {
        logger.info("定时任务: 扫描没有成功发送分账信息的记录");
        // 查询所有未通知分账记录
        List<MaibaoLedger> ledgerList = maibaoService.getNoAdvenceLedger();
        logger.info("共计查询出"+ledgerList.size()+"条记录, 没有发送分账信息");
        for (MaibaoLedger maibaoLedger : ledgerList) {
        	try{
        		maibaoService.sendLedger(maibaoLedger);
        	}catch(Exception e){
        		logger.error("定时推送脉宝消费订单"+maibaoLedger.getTransNo()+"异常.",e);
        	}
        }
    }
}
