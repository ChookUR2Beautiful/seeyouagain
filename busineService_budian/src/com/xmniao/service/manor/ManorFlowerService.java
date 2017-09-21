package com.xmniao.service.manor;


import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.common.MakeOrderNum;
import com.xmniao.common.RedisService;
import com.xmniao.common.ThriftClientFactory;
import com.xmniao.dao.manor.ManorConfigMapper;
import com.xmniao.dao.manor.ManorFlowerBranchChainMapper;
import com.xmniao.dao.manor.ManorFlowerBranchMapper;
import com.xmniao.dao.manor.ManorFlowerMapper;
import com.xmniao.dao.manor.ManorFlowerRelationMapper;
import com.xmniao.domain.manor.*;
import com.xmniao.exception.CustomException;
import com.xmniao.thrift.pay.ManorPropsThriftService;
import com.xmniao.thrift.pay.Result;
import com.xmniao.thrift.pay.ResultList;
import org.apache.thrift.TException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static com.xmniao.service.manor.ManorConstant.*;

/**
 * 黄金庄园花朵操作Service
 * Created by yang.qiang on 2017/6/20.
 */
@Service
public class ManorFlowerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ManorFlowerRelationMapper manorFlowerRelationMapper;
    @Autowired
    private ManorLogService manorLogService;
    @Autowired
    private ManorFlowerProducer flowerProducer;
    @Autowired
    private ManorFlowerMapper manorFlowerMapper;
    @Autowired
    private ManorFlowerBranchMapper manorFlowerBranchMapper;
    @Autowired
    private ManorConfigMapper manorConfigMapper;
	@Autowired
	private ManorFlowerBranchChainMapper  manorFlowerBranchChainMapper;
    @Autowired
    private RedisService redisService;

    /**
     * 注入支付服务IP地址
     */
    @Resource(name = "transLedgerIP")
    private String transLedgerIP;

    /**
     * 注入支付服务端口号
     */
    @Resource(name = "transLedgerPort")
    private int transLedgerPort;

    /**
     * 庄园花朵施肥
     *
     * @param uid
     * @param plantOperateList
     * @param transNo          1. 已经枯萎的花苗 和 花朵都不可以施肥
     */
    @Transactional
    public ArrayList<ManorOperate> fertilization(Integer uid, ArrayList<ManorOperate> plantOperateList, String transNo) throws Exception {

        // 已更新数据统计
        ArrayList<ManorOperate> operatedList = new ArrayList<>();

        // 施肥数量总计
        int fertilizeCountAll = 0;
        for (ManorOperate manorOperate : plantOperateList) {
            // 更新花朵结束时间(延后 FLOWER_DEFAULT_DAYS 天)
            int updateCount = manorFlowerRelationMapper.updateFlower(uid, manorOperate, FLOWER_DEFAULT_DAYS);
            fertilizeCountAll += updateCount;
            operatedList.add(new ManorOperate(manorOperate.getLocation(), updateCount, manorOperate.getFlowerType()));

        }



//        Result result;
//        // 调用支付服务
//        try (ThriftClientFactory.ThriftClient<ManorPropsThriftService.Client> manorPropsThriftServiceClient
//                     = ThriftClientFactory.getManorPropsThriftServiceClient(transLedgerIP, transLedgerPort)) {
//
//            // 支付服务 Thrift服务接口
//            ManorPropsThriftService.Client thriftClient = manorPropsThriftServiceClient.getThriftClient();
//
//            // 查询庄园信息
//            Integer superUid = manorInfoMapper.selectByUid(uid).getSuperUid();
////            result = thriftClient.ferilizeByUid(transNo,uid,superUid !=null ? superUid : 0,fertilizeCountAll);
////            logger.info("支付服务调用结果:"+ JSON.toJSONString(result));
//        } catch (Exception e) {
//            logger.error("调用支付服务异常", e);
//            throw new CustomException("调用支付服务异常");
//        }
//
//        if (result.code != 1 && !"10000".equals(result.statusCode)) {
//            String message = "调用支付服务失败 result:" + JSON.toJSONString(result);
//            logger.error(message);
//            if ("20002".equals(result.statusCode)) {
//                throw new CustomException(message, 3);
//            } else {
//                throw new CustomException(message);
//            }
//        }


//        // 记录操作信息
//        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(transNo,
//                MANOR_FLOWER_RELATION_TYPE_FERTILIZATION,uid,fertilizeCountAll,JSON.toJSONString(operatedList)));

        return operatedList;
    }




    /**
     * 统计庄园存活的花朵数量
     *
     * @param uid
     */
    public List<ManorFlowerCount> countAliveFlower(Integer uid) throws Exception {
        // 查询初始节点的子节点
        List<ManorFlowerBranch> branchList = manorFlowerBranchMapper.selectByUid(uid);

        // 统计集合
        List<ManorFlowerCount> countResultList = new ArrayList<>();
        for (ManorFlowerBranch branch : branchList) {
            //遍历统计每个子节点的花朵链

            // 查询链中共享的花朵
            int[] types = {FLOWER_TYPE_FLOWER,FLOWER_TYPE_SELF};
            ManorFlowerCount flowerCount = manorFlowerBranchMapper.countFlowerByTypeInBranch(branch,types,new int[]{1}, 1);

            // 查询链中仅计算本人收益的花
            ManorFlowerCount selfFlowerCount = manorFlowerBranchMapper.countFlowerByTypeAndFloristInBranch(branch,types, new int[]{2}, 1, uid);

            flowerCount.addCount(selfFlowerCount.getCount());
            flowerCount.setPerishType(1);
            countResultList.add(flowerCount);
        }
        return countResultList;
    }



    /** 统计已枯萎 / 即将枯萎 的花朵 */
    public List<ManorFlowerCount> countFlowerByPerishTime(Integer uid) {
        // 查询初始节点的子节点
        List<ManorFlowerBranch> branchList = manorFlowerBranchMapper.selectByUid(uid);

        // 统计集合
        List<ManorFlowerCount> countResultList = new ArrayList<>();
        for (ManorFlowerBranch branch : branchList) {

            int[] types = {FLOWER_TYPE_FLOWER,FLOWER_TYPE_SELF};
            int[] shareTypes ={MANOR_FLOWER_SHARE_TYPE_SUBCHAIN,MANOR_FLOWER_SHARE_TYPE_SELF};

            // 查询关系链下  自己种植的即将枯萎花朵
            ManorFlowerCount perishFlower = manorFlowerBranchMapper.countFlowerByTypeAndFloristInBranch(branch,types,shareTypes,3,uid);
            perishFlower.setPerishType(3);
            // 查询关系链下  自己种植的已枯萎花朵
            ManorFlowerCount willPerishFlower =manorFlowerBranchMapper.countFlowerByTypeAndFloristInBranch(branch,types,shareTypes,2,uid);
            willPerishFlower.setPerishType(2);

            countResultList.add(perishFlower);
            countResultList.add(willPerishFlower);

        }
        return countResultList;
    }


    /**
     * 初始节点(initialNode) : 用户开通庄园时庄园的初始位置. 由left_node 和 right_node 包围的节点均属于该初始节点的链中.
     * 最底节点(footNode) : 关系链中最底层节点
     * <p>
     * 没有上级(fromUid=null) : 查询 (zid = 1 and level max)
     * 有结果 : 作为父节点
     * 空结果 : 首个用户 父节点为null
     * 有上级 : (uid=fromUId and leve min) p left > p.left   right < p.right and location =1  level max
     * 有结果 : 作为父节点
     * 空结果 : (uid=fromUId and leve min) 作为父节点
     */
    public ManorFlowerRelation queryParentNode(ManorFlowerRelation initialNode, ManorFlowerRelation flowerParam ) {

        // 一级下级节点
        ManorFlowerRelation subFlower = manorFlowerRelationMapper.selectByPidAndLocation(initialNode.getId(), flowerParam.getLocation().intValue());

        if (subFlower == null) {
            // 当一级节点为空, 返回用户初始节点为父级节点
            return initialNode;
        } else {
            // 返回一级下级节点的 左链最底层花朵
            flowerParam.setLocation((byte) FLOWER_LOCATION_LEFT);
            return manorFlowerRelationMapper.selectParentNodeByZid(subFlower.getZid());
        }
    }

    /**
     * @param uid           关系链所属人
     * @param flowerParam
     * @return
     */
    public ManorFlowerRelation queryParentNode(Integer uid, ManorFlowerRelation flowerParam) {
        ManorFlowerRelation initialNode = manorFlowerRelationMapper.selectInitialNode(uid);
        return queryParentNode(initialNode,flowerParam);
    }


    /**
     * 种植花苗任务, 调用支付服务
     * @param uid
     * @param plantOperateList
     * @param transNo
     */
    @Transactional
    public ArrayList<ManorFlower> plantFlowerTask(int uid, ArrayList<ManorOperate> plantOperateList, String transNo) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, TException {
        // 记录日志
        HashMap<String, Object> recordDetailMaps = new HashMap<>();
        recordDetailMaps.put("接口请求参数",plantOperateList);


        // 统计花朵总数
        int plantFlowerCount = 0;
        for (ManorOperate manorOperate : plantOperateList) plantFlowerCount += manorOperate.getQuantity();

        // 调用支付服务
        ResultList result;
        try(ThriftClientFactory.ThriftClient<ManorPropsThriftService.Client> manorPropsThriftServiceClient
                    = ThriftClientFactory.getManorPropsThriftServiceClient(transLedgerIP, transLedgerPort)) {
            ManorPropsThriftService.Client client = manorPropsThriftServiceClient.getThriftClient();

            result = client.growFlower(transNo, Long.valueOf(uid), plantFlowerCount);
            logger.info("调用支付接口 growFlowersByUid result:" + JSON.toJSONString(result));
            recordDetailMaps.put("支付响应花朵数据",result.getValues());
        } catch (Exception e) {
            logger.error("调用支付服务出现异常",e);
            throw new CustomException("调用支付服务失败!");
        }

        // 校验支付接口返回数据
        if (result.code != 1 && !"10000".equals(result.statusCode)) {
            String message = "调用支付服务失败 result:" + JSON.toJSONString(result);
            logger.info(message);
            if ("20002".equals(result.statusCode)) {
                throw new CustomException(message, 3);
            } else {
                manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                        null,MANOR_FLOWER_LOG_TYPE_PLANT_TASK,uid,null,JSON.toJSONString(result),
                        MANOR_FLOWER_LOG_STATE_PAY_SERVICE_FAILURE
                ));
                throw new CustomException(message);
            }
        }


        // 解析支付返回参数
        ArrayList<ManorFlower> manorFlowers = parseThriftResult(result);
        Iterator<ManorFlower> iterator = manorFlowers.iterator();


        HashMap<Integer, LinkedList<ManorFlower>> perishedFlowerBuffer = new HashMap<>();


        // 遍历所有
        for (ManorOperate manorOperate : plantOperateList) {
            Integer quantity = manorOperate.getQuantity();


            ArrayList<ManorFlower> pushFlowers = new ArrayList<>();
            Integer location = manorOperate.getLocation();
            for (Integer i = 0; i < quantity; i++) {
                if (!iterator.hasNext()) {
                    logger.error("黄金庄园-种植任务 用户["+uid+"]支付服务花朵数量异常!!!, 请求花朵数["+plantFlowerCount+"], 支付接口返回数量["+manorFlowers.size()+"]");
                    continue;
                }

                ManorFlower flower = iterator.next();
                if (flower.getUid() != null) {
                    // 迁移下级用户, 并种植种子
                    flowerProducer.pushMigrateFlowerChainMessage(transNo,flower.getUid(),uid, location);
                    flowerProducer.pushUpdateLocationForBursRelation(flower.getUid(),location);
                    flower.setActualOperation(1);
                }else {

                    // 缓存枯萎花朵列表
                    LinkedList<ManorFlower> bufferList = perishedFlowerBuffer.get(location);
                    if (bufferList == null) {
                        bufferList = new LinkedList<>();
                        ManorFlowerBranch branch = manorFlowerBranchMapper.selectByUidAndLocation(uid,location);
                        bufferList.addAll(manorFlowerMapper.selectPerishedFlowers(uid, branch));
                        perishedFlowerBuffer.put(location,bufferList);
                    }
                    if(bufferList.size() > 0){

                        ManorFlower record = new ManorFlower();
                        record.setPerishTime(flower.getPerishTime());
                        record.setId(bufferList.pop().getId());
                        manorFlowerMapper.updateByPrimaryKeySelective(record);
                        flower.setActualOperation(2);
                    }else {
                        flower.setActualOperation(3);
                        pushFlowers.add(flower);
                    }
                }

            }

            if(pushFlowers.size() > 0) {
                flowerProducer.pushPlantFlowerMessage(transNo,uid, location, pushFlowers);
            }
        }


        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                transNo,MANOR_FLOWER_LOG_TYPE_PLANT_TASK,uid,manorFlowers.size()
                , JSON.toJSONString(recordDetailMaps),MANOR_FLOWER_LOG_STATE_PUSH
        ));


        return manorFlowers;
    }

    /**
     * 解析支付服务种花接口返回的参数
     * @param result
     */
    private ArrayList<ManorFlower> parseThriftResult(ResultList result) {

        // 查询是否赠送的花朵是否计算他人收益
        ManorConfig shareTypeConfig =  manorConfigMapper.selectByType(1);


        ArrayList<ManorFlower> flowers = new ArrayList<>();
        try {
            for (Map<String, String> flowerMap : result.getValues()) {

                // 种子
                String type = flowerMap.get("type");
                if ("1".equals(type)) {
                    ManorFlower manorFlower = new ManorFlower();
                    manorFlower.setType(FLOWER_TYPE_SEEDING);
                    manorFlower.setPerishTime(ManorDateUtils.getFlowerPerishDate(FLOWER_DEFAULT_DAYS));
                    manorFlower.setUid(Integer.valueOf(flowerMap.get("giveUid")));
                    manorFlower.setDelayDays(FLOWER_DEFAULT_DAYS);
                    manorFlower.setShareType(MANOR_FLOWER_SHARE_TYPE_SUBCHAIN);
                    flowers.add(manorFlower);
                }
                // 花朵
                else if("3".equals(type) || "2".equals(type)){
                    Integer flowerNum = Integer.valueOf(flowerMap.get("number"));
                    Integer month = Integer.valueOf(flowerMap.get("month"));
                    for (Integer i = 0; i < flowerNum; i++) {
                        ManorFlower manorFlower = new ManorFlower();
                        int delayDays = month == 0 ? FLOWER_DEFAULT_DAYS : month * FLOWER_DEFAULT_DAYS;
                        manorFlower.setPerishTime(ManorDateUtils.getFlowerPerishDate(delayDays));
                        manorFlower.setType(FLOWER_TYPE_FLOWER);
                        manorFlower.setShareType(MANOR_FLOWER_SHARE_TYPE_SUBCHAIN);

                        manorFlower.setDelayDays(delayDays);
                        flowers.add(manorFlower);
                    }
                }else if ("4".equals(type)){
                    Integer flowerNum = Integer.valueOf(flowerMap.get("number"));
                    for (Integer i = 0; i < flowerNum; i++) {
                        ManorFlower manorFlower = new ManorFlower();
                        manorFlower.setType(FLOWER_TYPE_SELF);
                        manorFlower.setPerishTime(ManorDateUtils.getFlowerPerishDate(FLOWER_DEFAULT_DAYS));
                        manorFlower.setDelayDays(FLOWER_DEFAULT_DAYS);
                        manorFlower.setShareType(shareTypeConfig.getValue());   // 设置shareType
                        flowers.add(manorFlower);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("解析支付服务响应参数失败 "+ JSON.toJSONString(result), e);
            throw new CustomException("解析支付服务响应参数失败 "+ JSON.toJSONString(result));
        }

        return flowers;
    }



    /**
     * 自动种植花朵
     * @param uid
     */
    public void autoPlantFlowers(Integer uid) throws Exception {
        logger.info("自动种植用户"+ uid +"超过"+ AUTO_PLANT_HOURS +"没有种植的花朵");
        String transNo = MakeOrderNum.getInstance().makeOrderNum();

        ResultList resultList;
        try (ThriftClientFactory.ThriftClient<ManorPropsThriftService.Client> manorPropsThriftServiceClient = ThriftClientFactory.getManorPropsThriftServiceClient(transLedgerIP, transLedgerPort);) {
            ManorPropsThriftService.Client client = manorPropsThriftServiceClient.getThriftClient();
            resultList = client.timerGrowFlower(transNo, uid, 0);
            logger.info("支付服务调用结果:"+JSON.toJSON(resultList));

            if(resultList == null || resultList.code != 1){

                if("20015".equals(resultList.statusCode)) {
                    // 支付服务 statusCode = 20015 表示用户未开通庄园
                    return;
                }else {
                    manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                            null, MANOR_FLOWER_LOG_TYPE_AUTO_PLANT, uid, null, JSON.toJSONString(resultList),
                            MANOR_FLOWER_LOG_STATE_PAY_SERVICE_FAILURE
                    ));
                    throw new CustomException("支付服务调用失败!");
                }

            }
        }


        HashMap<String, Object> logDetailMap = new HashMap<>();
        logDetailMap.put("支付服务返回参数",resultList);

        List<Map<String, String>> values = resultList.getValues();
        if (values.size() <= 0){
            return;
        }


        ArrayList<ManorFlower> manorFlowers = parseThriftResult(resultList);
        HashMap<Integer, LinkedList<ManorFlower>> perishedFlowerBuffer = new HashMap<>();

        ArrayList<ManorFlower> pushFlowers = new ArrayList<>();
        for (ManorFlower flower : manorFlowers) {

            if (flower.getUid() != null) {
                // 迁移下级用户
                flowerProducer.pushMigrateFlowerChainMessage(transNo, flower.getUid(),uid, FLOWER_LOCATION_LEFT);
                flowerProducer.pushUpdateLocationForBursRelation(flower.getUid(),FLOWER_LOCATION_LEFT);
            }

            // 缓存枯萎花朵列表
            LinkedList<ManorFlower> bufferList = perishedFlowerBuffer.get(FLOWER_LOCATION_LEFT);
            if (bufferList == null) {
                bufferList = new LinkedList<>();
                ManorFlowerBranch branch = manorFlowerBranchMapper.selectByUidAndLocation(uid,FLOWER_LOCATION_LEFT);
                bufferList.addAll(manorFlowerMapper.selectPerishedFlowers(uid, branch));
                perishedFlowerBuffer.put(FLOWER_LOCATION_LEFT,bufferList);
            }
            if(bufferList.size() > 0){

                // 推送重置花朵MQ消息
                ManorFlower record = new ManorFlower();
                record.setPerishTime(flower.getPerishTime());
                record.setId(bufferList.pop().getId());
                manorFlowerMapper.updateByPrimaryKeySelective(record);
            }else {

                pushFlowers.add(flower);
            }

        }

        if(pushFlowers.size() > 0) {
            flowerProducer.pushPlantFlowerMessage(transNo, uid, FLOWER_LOCATION_LEFT, pushFlowers);
        }




        // 插入操作记录
        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                transNo,MANOR_FLOWER_LOG_TYPE_AUTO_PLANT, uid,pushFlowers.size(),
                JSON.toJSONString(logDetailMap),MANOR_FLOWER_LOG_STATE_PUSH
        ));
    }

    /** 查询花田是否满足种植条件 */
    public boolean fieldPlantable(Integer uid, Integer field) throws Exception {
        // 2017-08-14 激活庄园直接开启3个花田
        return  true;

//        switch (field){
//            case FLOWER_LOCATION_RIGHT:     // 右花田开启资格: 左花田需要自己种植 2朵花苗/花/系统赠送的花
//                // 已缓存的用户直接根据缓存判断
//                String rightFieldStatus = redisService.hget(MANOR_REDIS_KEY_FIELD_PREFIX + uid, FLOWER_LOCATION_RIGHT + "");
//                if ("1".equals(rightFieldStatus)) return true;
//
//                // 统计用户种植花数量(不区分花朵类型)
//                ManorFlowerBranch branch = manorFlowerBranchMapper.selectByUidAndLocation(uid, FLOWER_LOCATION_LEFT);
//                int count = manorFlowerBranchMapper.countFloristUidInBranch(branch);
//                if (count >= 2){
//                    // 将庄园开启庄园缓存到Redis中
//                    redisService.hset(MANOR_REDIS_KEY_FIELD_PREFIX + uid, FLOWER_LOCATION_RIGHT + "","1");
//                    return true;
//                }
//                return false;
//            case FLOWER_LOCATION_MIDDLE:     // 中花田开启资格: 左右链均有300颗以上的花朵
//                String middleFieldStatus = redisService.hget(MANOR_REDIS_KEY_FIELD_PREFIX + uid, FLOWER_LOCATION_MIDDLE + "");
//                if("1".equals(middleFieldStatus)) return true;
//
////                 查询左花田花朵数
//                ManorFlowerBranch leftBranch = manorFlowerBranchMapper.selectByUidAndLocation(uid, FLOWER_LOCATION_LEFT);
//                int[] flowerTypes = {1, 2};
//                ManorFlowerCount leftCount = manorFlowerBranchMapper.countFlowerByTypeInBranch(leftBranch, flowerTypes, new int[]{1}, 4);
//                ManorFlowerCount leftSelfCount = manorFlowerBranchMapper.countFlowerByTypeAndFloristInBranch(leftBranch, flowerTypes, new int[]{2}, 4, uid);
//                leftCount.addCount(leftSelfCount.getCount());
//                if (leftCount.getCount() < 300) return false;
//
//                // 查询右花田花朵数
//                ManorFlowerBranch rightBranch = manorFlowerBranchMapper.selectByUidAndLocation(uid, FLOWER_LOCATION_RIGHT);
//                ManorFlowerCount rightCount = manorFlowerBranchMapper.countFlowerByTypeInBranch(rightBranch, flowerTypes, new int[]{1}, 4);
//                ManorFlowerCount rightSelfCount = manorFlowerBranchMapper.countFlowerByTypeAndFloristInBranch(leftBranch, flowerTypes, new int[]{2}, 4, uid);
//                rightCount.addCount(rightSelfCount.getCount());
//                if(rightCount.getCount() < 300) return false;
//
//                // 缓存中花田状态到Redis
//                redisService.hset(MANOR_REDIS_KEY_FIELD_PREFIX + uid, FLOWER_LOCATION_MIDDLE + "","1");
//                return true;
//
//            default:
//                return false;
//        }
    }

    /** 种植 园友赠送的花朵 */
    public void plantFlowerWithGiveTask(int uid, ArrayList<ManorOperate> plantOperateList, String transNo) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Map<String,Object> recordDetailMaps = new HashMap<>();
        recordDetailMaps.put("接口请求参数" , plantOperateList);

        // 调用支付服务
        Result result;
        try(ThriftClientFactory.ThriftClient<ManorPropsThriftService.Client> manorPropsThriftServiceClient
                    = ThriftClientFactory.getManorPropsThriftServiceClient(transLedgerIP, transLedgerPort)) {
            ManorPropsThriftService.Client client = manorPropsThriftServiceClient.getThriftClient();


            ArrayList<Long> giveUids = new ArrayList<>();
            for (ManorOperate manorOperate : plantOperateList) {
                giveUids.add(manorOperate.getGiveUid().longValue());
            }
            result = client.growGiveFlower(transNo, uid, giveUids);
            logger.info("调用支付接口 growGiveFlower result:" + JSON.toJSONString(result));
            recordDetailMaps.put("支付响应花朵数据",result.getValues());
        } catch (Exception e) {
            logger.error("调用支付服务出现异常",e);
            throw new CustomException("调用支付服务失败!");
        }


        // 校验支付接口返回数据
        if (result.code != 1 || !"10000".equals(result.statusCode)) {
            String message = "调用支付服务失败 result:" + JSON.toJSONString(result);
            logger.info(message);
            if ("20002".equals(result.statusCode)) {
                throw new CustomException(message, 3);
            } else {
                manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                        null,MANOR_FLOWER_LOG_TYPE_WITH_GIVE,uid,null,JSON.toJSONString(result),
                        MANOR_FLOWER_LOG_STATE_PAY_SERVICE_FAILURE
                ));
                throw new CustomException(message);
            }
        }


        for (ManorOperate manorOperate : plantOperateList) {
            // 推送迁移花朵关系链 MQ
            flowerProducer.pushMigrateFlowerChainMessage(transNo, manorOperate.getGiveUid(),uid,manorOperate.getLocation());
            // 推送更新 manor_info表branch_location 字段
            flowerProducer.pushUpdateLocationForBursRelation(manorOperate.getGiveUid(),manorOperate.getLocation());
        }


        // 插入操作记录
        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                transNo,MANOR_FLOWER_LOG_TYPE_WITH_GIVE, uid,plantOperateList.size(),
                JSON.toJSONString(recordDetailMaps),MANOR_FLOWER_LOG_STATE_PUSH
        ));



    }




	 /**
	 * 方法描述：检查子级是否有下级
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月8日下午4:00:58 <br/>
	 * @param childId
	 * @return
	 */
	public boolean havParentBranch(Integer childId) {
		List<ManorFlowerBranch> selectAllSubBranchByUid = manorFlowerBranchMapper.selectAllSubBranchByUid(childId);
		if(selectAllSubBranchByUid.size()>3){
			return true;
		}
		return false;
	}




	 /**
	 * 方法描述：检查目标父级坑下是否有子级
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月8日下午4:13:16 <br/>
	 * @param parentId
	 * @param location
	 * @return
	 */
	public boolean havChildBranch(Integer parentId, Integer location) {
		ManorFlowerBranch selectLowestByUidAndLocation = manorFlowerBranchMapper.selectLowestByUidAndLocation(parentId, location);
		if(!selectLowestByUidAndLocation.getUid().equals(parentId)){
			return true;
		}
		return false;
	}




	 /**
	 * 方法描述：迁移花朵链
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月8日下午4:18:28 <br/>
	 * @param childUid
	 * @param parentUid
	 * @param location
	 */
	 @Transactional
	public void BindingParent(Integer childUid, Integer parentUid, Integer location) {
		List<ManorFlowerBranch> bs = manorFlowerBranchMapper.selectByUid(childUid);
		String parentId = bs.get(0).getParentId();
		//上级的坑
		ManorFlowerBranch p = manorFlowerBranchMapper.selectByPrimaryKey(parentId);
		//ManorFlowerBranch afterLocation = manorFlowerBranchMapper.selectByUidAndLocation(p.getUid(), afterL);
		//删除跟(需要变更顶级的那个坑)以上的所有与(需要变更顶级的那个坑及下级)所有关联的数据
		
		//查出上级的所有坑
		List<String> paIds =new ArrayList<>();
		if(!parentId.equals(p.getParentId())){
			paIds =  manorFlowerBranchChainMapper.selectByBranchId(p.getId());
		}
		List<ManorFlowerBranch> selectAllSubBranchByUid = manorFlowerBranchMapper.selectAllSubBranchByUid(bs.get(0).getUid());
	
		//查询要绑定的新节点
		ManorFlowerBranch afterBranch = manorFlowerBranchMapper.selectByUidAndLocation(parentUid, location);
		//修改左链值
		//找出做边的那个坑
		ManorFlowerBranch left = manorFlowerBranchMapper.selectByUidAndLocation(childUid, 0);		
		int j=manorFlowerBranchMapper.updateZidMigrate(left.getZid(),afterBranch.getZid(),selectAllSubBranchByUid);		
		
		//改level
		int z=manorFlowerBranchMapper.updateLevelAdd(selectAllSubBranchByUid,((afterBranch.getLevel()+1)-bs.get(0).getLevel()));
		if(z!=selectAllSubBranchByUid.size()){
			throw new RuntimeException("数据异常");
		}
		
		if(!paIds.isEmpty()){
			for (ManorFlowerBranch manorFlowerBranch : selectAllSubBranchByUid) {
				for (String pId : paIds) {
					int i = manorFlowerBranchChainMapper.deleteByBranchIdAndParentId(manorFlowerBranch.getId(),pId);
					if(1!=i){
						throw new RuntimeException("数据异常");
					}
				}
			}
		}
		
		
		/*List<String> afterBranchIds = manorFlowerBranchChainMapper.selectByBranchId(afterBranch.getId());
		afterBranchIds.add(afterBranch.getId());*/
		for (ManorFlowerBranch manorFlowerBranch : selectAllSubBranchByUid) {
  				manorFlowerBranchMapper.insertMigrateChain(manorFlowerBranch, afterBranch);
		}
		
		//修改三个坑的parentId
		int i=manorFlowerBranchMapper.updateParentId(bs,afterBranch.getId());
		if(3!=i){
			throw new RuntimeException("数据异常");
		}
		
		
		//迁移上级给下级种的花朵
		int updateFlowerByMigrate = manorFlowerMapper.updateFlowerByMigrate(selectAllSubBranchByUid,p.getId());
		
		System.out.println("迁移花朵数:"+updateFlowerByMigrate);
		
		//为上级贡献的花苗迁移 
		int updateFlowerSeedlingMigrate=manorFlowerMapper.updateFlowerSeedlingMigrate(afterBranch.getUid(),p.getUid(),childUid,afterBranch.getId());
	//	Assert.assertEquals(updateFlowerSeedlingMigrate, 1);
		
	}


}
