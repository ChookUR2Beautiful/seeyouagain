package com.xmniao.service.quartz;

import com.xmniao.domain.live.LivePrivilege;
import com.xmniao.domain.live.TVerExcitationDetail;
import com.xmniao.service.live.VerExcitationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang.qiang on 2017/5/29.
 */
public class VerExcitationQuartzService {
    @Autowired
    private VerExcitationService verExcitationService;
    private final Logger logger = LoggerFactory.getLogger(VerExcitationQuartzService.class);

    /**
     * 扫描V客奖励 方案A满足发放的充值记录
     */
    public void scanPlanA(){
        logger.info("定时任务:发送奖励方案A 启动....");

        // 奖励方案详情列表 缓存Map
        Map<Integer,List<TVerExcitationDetail>> excitationDetailsMap = new HashMap<>();

        // 查询满足奖励条件的方案A充值记录
        List<LivePrivilege> livePrivileges = verExcitationService.queryPlanA();
        for (LivePrivilege livePrivilege : livePrivileges) {
            verExcitationService.receiveExcitation(excitationDetailsMap, livePrivilege);
        }

        logger.info("定时任务:发送奖励A已完成!");
    }
}
