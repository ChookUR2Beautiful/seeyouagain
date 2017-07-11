package com.xmn.saas.job;

import com.xmn.saas.dao.redpacket.RedpacketDao;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.service.redpacket.RedpacketService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * create 2016/11/01
 *
 * @author yangQiang
 */

public class RedpacketExpireJob extends QuartzJobBean{
    // 初始化日志处理类
    private final Logger logger = LoggerFactory.getLogger(RedpacketExpireJob.class);

    /**
     * 定时清理昨天及之前的过期红包
     * @param context
     * @throws JobExecutionException
     */
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("spring-context");
        RedpacketService redpacketService = applicationContext.getBean(RedpacketService.class);
        RedpacketDao redpacketDao = applicationContext.getBean(RedpacketDao.class);
        logger.info("[定时任务 - 清理过期红包] 开始执行!");

        // 读取配置文件
        Resource resource = new ClassPathResource("properties/cluster.properties");
        Properties properties = new Properties();
        try {
            properties.load(resource.getInputStream());
        } catch (IOException e) {
            logger.error("[定时任务 - 清理过期红包]- 读取配置文件出现异常!",e);
        }

        // 获取集群机器配置参数
        Integer machineId = Integer.valueOf(properties.getProperty("machine.id"));
        Integer machineCount = Integer.valueOf(properties.getProperty("machine.count"));

        // 查询过期的红包 根据日期状态
        List<Redpacket> redpacketList = redpacketDao.selectExpiredRedpacketByEndDateAndStatus();
        for (Redpacket redpacket : redpacketList) {
            // 根据机器数量取模结果决定哪台机器执行该条红包清理任务
            if (redpacket.getId() % machineCount == machineId) {
                logger.info("[定时任务-清理过期红包] - 清理昨天及之前的过期红包:" + redpacket.getId() + "\t 机器id:" + machineId);
                try {
                	 Map<String,String> resultMap=redpacketService.endRedpacket(redpacket.getId());
                	 logger.info("[定时任务-清理过期红包] -结果 : " + resultMap);
                } catch (Exception e) {
                    logger.error("[定时任务-清理过期红包] : 清理红包(" + redpacket.getId() + "),出现异常", e);
                }
            }

        }
        logger.info("[定时任务 - 清理过期红包] 执行结束!");
    }
}
