package com.xmniao.service.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.domain.vstar.TVstarPlayerInfo;
import com.xmniao.service.vstar.VstarPlayerInfoService;

/**
 * 
 * 
 * 项目名称：busineService_vstar
 * 
 * 类名称：VstarPlayerInfoQuartzService
 * 
 * 类描述： 新时尚大赛选手统计信息定时任务
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-9 下午3:54:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class VstarPlayerInfoQuartzService {
    private final Logger logger = LoggerFactory.getLogger(VstarPlayerInfoQuartzService.class);
    
    private Integer nodeTotal;//节点总数
    
    private Integer currentNode;//当前节点标志
    
    private Integer limit;//单次查询记录数
    
    @Autowired
    private VstarPlayerInfoService playerInfoService;

	/**
     * 
     * 方法描述：统计选手信息定时任务(基础信息-总榜) <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-6-9下午3:57:01 <br/>
     */
    public void startJob(){
        logger.info("统计选手信息定时任务 star....");
        long starTime = System.currentTimeMillis();

        logger.info("nodeTotal="+nodeTotal);
        
        TVstarPlayerInfo playerReq = new TVstarPlayerInfo();
        playerReq.setNodeTotal(nodeTotal);
        playerReq.setCurrentNode(currentNode);
        playerReq.setLimit(limit);
        playerInfoService.executeCount(playerReq);
        
        logger.info("currentNode="+currentNode);
        
        long endTime = System.currentTimeMillis();

        logger.info("统计选手信息定时任务 end....,耗时(秒)："+(endTime-starTime)/1000);
    }
    
    /**
     * 
     * 方法描述：统计选手鸟蛋、粉丝信息定时任务(周榜、月榜) <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-6-9下午3:57:01 <br/>
     */
    public void startJobRankInfo(){
        logger.info("统计选手信息(周榜、月榜)定时任务 star....");
        long starTime = System.currentTimeMillis();

        logger.info("nodeTotal="+nodeTotal);
        
        TVstarPlayerInfo playerReq = new TVstarPlayerInfo();
        playerReq.setNodeTotal(nodeTotal);
        playerReq.setCurrentNode(currentNode);
        playerReq.setLimit(limit);
        
        playerInfoService.executeCountWeek(playerReq);
        
        playerInfoService.executeCountMonth(playerReq);
        
        logger.info("currentNode="+currentNode);
        
        long endTime = System.currentTimeMillis();

        logger.info("统计选手信息(周榜、月榜)定时任务 end....,耗时(秒)："+(endTime-starTime)/1000);
    }
    
    
    /**
	 * @return the nodeTotal
	 */
	public Integer getNodeTotal() {
		return nodeTotal;
	}


	/**
	 * @param nodeTotal the nodeTotal to set
	 */
	public void setNodeTotal(Integer nodeTotal) {
		this.nodeTotal = nodeTotal;
	}


	/**
	 * @return the currentNode
	 */
	public Integer getCurrentNode() {
		return currentNode;
	}

	/**
	 * @param currentNode the currentNode to set
	 */
	public void setCurrentNode(Integer currentNode) {
		this.currentNode = currentNode;
	}


	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	
}
