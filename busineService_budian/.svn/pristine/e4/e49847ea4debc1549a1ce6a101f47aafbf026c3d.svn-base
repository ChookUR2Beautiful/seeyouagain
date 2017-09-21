package com.xmniao.service.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.service.vstar.VstarPlayerInfoService;

/**
 * 
 * 
 * 项目名称：busineService_vstar
 * 
 * 类名称：VstarPlayerRankQuartzService
 * 
 * 类描述： 新时尚大赛选手排名信息定时任务
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-9 下午3:54:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class VstarPlayerRankQuartzService {
    private final Logger logger = LoggerFactory.getLogger(VstarPlayerRankQuartzService.class);
    
    
    @Autowired
    private VstarPlayerInfoService playerInfoService;

	/**
     * 
     * 方法描述：选手排名信息定时任务 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-6-9下午3:57:01 <br/>
     */
    public void startJob(){
        logger.info("选手排名信息定时任务 star....");
        long starTime = System.currentTimeMillis();

        playerInfoService.executeRank();
        
        playerInfoService.executeRankWeek();
        
        playerInfoService.executeRankMonth();
        
        long endTime = System.currentTimeMillis();

        logger.info("选手排名信息定时任务 end....,耗时(秒)："+(endTime-starTime)/1000);
    }
    
}
