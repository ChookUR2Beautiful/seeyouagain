package com.xmniao.service.manor;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.RedisService;
import com.xmniao.dao.manor.ManorFlowerRelationMapper;
import com.xmniao.domain.manor.ManorFlowerRelation;
import com.xmniao.domain.manor.ManorFlowerRelationRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

import static com.xmniao.service.manor.ManorConstant.*;

/**
 * 黄金庄园MQ消息消费类
 * Created by yang.qiang on 2017/7/4.
 */
@Service
public class ManorMqConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;
    @Autowired
    private ManorFlowerService manorFlowerService;
    @Autowired
    private ManorLogService manorLogService;

    @Autowired
    private ManorFlowerRelationMapper manorFlowerRelationMapper;


    @Transactional
    public void insertInitNode(Integer uid){
        logger.info("用户["+uid+"]插入初始节点 ");

        // 获取Redis锁
        String key = "manor:lock:plant-flower";
        String identifier = redisService.lock(key, 60000);

        // 获取主键
        Integer flowerPrimekey = redisService.increment("manor:primeKey").intValue();

        ManorFlowerRelation record = new ManorFlowerRelation();
        record.setType((byte)FLOWER_TYPE_SEEDING);        // 初始节点默认为 种子
        record.setEndTime(ManorDateUtils.getDateDistance(new Date(), Calendar.DAY_OF_MONTH,FLOWER_DEFAULT_DAYS));
        record.setUid(uid);
        record.setLocation((byte)FLOWER_LOCATION_LEFT);
        record.setId(flowerPrimekey);
        record.setZid(flowerPrimekey);
        record.setLevel(1);
        record.setFromUid(uid);
        record.setRelationChain(String.format("%012d", flowerPrimekey));

        manorFlowerRelationMapper.insertSelective(record);

        // 插入记录
        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                null,MANOR_FLOWER_LOG_TYPE_INIT_NODE, uid,1,JSON.toJSONString(record),MANOR_FLOWER_LOG_STATE_CONSUMED
        ));
        // 释放锁
        redisService.unlock(key, identifier);

    }

    /**
     * 种植花朵
     * @param flowerInfo
     */
    @Transactional
    public void plantFlower(ManorFlowerRelation flowerInfo){
        long startTime = System.currentTimeMillis();

        // 获取Redis锁
        String key = "manor:lock:plant-flower";
        String identifier = redisService.lock(key, 60000);
        logger.info("在关系链中插入一朵花苗 "+ JSON.toJSONString(flowerInfo));

        // 获取Redis 主键
        int flowerPrimeKey = redisService.increment("manor:primeKey").intValue();
        flowerInfo.setId(flowerPrimeKey);

        // 查询初始节点
        ManorFlowerRelation initialNode = manorFlowerRelationMapper.selectInitialNode(flowerInfo.getFromUid());

        // 获取父节点
        ManorFlowerRelation parentNode = manorFlowerService.queryParentNode(initialNode,flowerInfo);

        // 包装节点
        flowerInfo.setUid(null);
        flowerInfo.setType((byte)FLOWER_TYPE_FLOWER);
        flowerInfo.setState((byte)0);
        flowerInfo.setPid(parentNode.getId());      // 节点父id
        // 当节点位置不是0的时候, zid需要使用自身主键
        int zid = flowerInfo.getLocation() != 0 ? flowerPrimeKey : parentNode.getZid();
        flowerInfo.setZid(zid);
        flowerInfo.setLevel(parentNode.getLevel() + 1);

        // 插入节点到树
        manorFlowerRelationMapper.insertFlowerToBranch(flowerInfo,parentNode);

        redisService.unlock(key, identifier);
        logger.info("种植花朵完成消耗时间:" + (System.currentTimeMillis() - startTime));
    }


    /**
     * 移植用户花朵关系树位置,到某一节点下方
     *
     * 1. 修改关系链
     * 2. 更新左链
     * 3. 更新关系链
     * 4. 设置节点位置
     * uid : 被迁移的用户
     * fromUid : 迁移到fromUid用户下
     * location : 迁移位置
     */
    @Transactional
    public void migrateFlowerChain(ManorFlowerRelation flowerParam){
        // 获取Redis锁
        String key = "manor:lock:plant-flower";
        String identifier = redisService.lock(key, 60000);


        String paramString = JSON.toJSONString(flowerParam);
        logger.info("迁移用户["+flowerParam.getUid()+"]花朵链, 到用户["+flowerParam.getFromUid()+"]的关系链下"+ paramString);
        // 1. 查询被迁移用户的初始节点
        ManorFlowerRelation initialNode = manorFlowerRelationMapper.selectInitialNode(flowerParam.getUid());

        // 2. 根据上级用户uid 和 location 查询一个节点作为父级节点
        ManorFlowerRelation parentNode = manorFlowerService.queryParentNode(flowerParam.getFromUid(),flowerParam);

        // 3. 修改左值
        int count = manorFlowerRelationMapper.updateZid(parentNode.getZid(),initialNode.getZid());

        // 4. 修改 pid  location
        ManorFlowerRelation record = new ManorFlowerRelation();
        record.setId(initialNode.getId());
        record.setPid(parentNode.getId());                  // pid 和 location 必须一起修改, 不然会报索引错误
        record.setLocation(flowerParam.getLocation());
        record.setFromUid(flowerParam.getFromUid());        // 修改种花人
        manorFlowerRelationMapper.updateByPrimaryKeySelective(record);

        // 5. 更新关系链 和 level
        // 计算level差
        int difference = parentNode.getLevel() - initialNode.getLevel();
        // 更新整颗树下级的关系链和level
        int updateCount = manorFlowerRelationMapper.updateLevelAndChainByInitNode(difference+1,initialNode,parentNode);

        // 插入记录
        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                null,MANOR_FLOWER_LOG_TYPE_MIGRATE, flowerParam.getUid(),updateCount,JSON.toJSONString(record),MANOR_FLOWER_LOG_STATE_CONSUMED
        ));

        redisService.unlock(key, identifier);
    }


    /**
     * 更新用户初始节点为花朵
     * @param uid
     * @param flowerType
     */
    public void updateInitNodeType(Integer uid, int flowerType) {
        logger.info("更新用户["+uid+"]初始节点花朵类型为["+ flowerType+"]");
        int updateCount = manorFlowerRelationMapper.updateFlowerType(uid, flowerType);

        // 插入记录
        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                null,MANOR_FLOWER_LOG_TYPE_UPDATE_TYPE, uid,updateCount,("uid:"+uid+" type:"+flowerType),MANOR_FLOWER_LOG_STATE_CONSUMED
        ));

    }
}
