package com.xmniao.service.impl.manor;


import com.alibaba.fastjson.JSON;
import com.xmniao.Template.SMSResultCode;
import com.xmniao.Template.Template;
import com.xmniao.common.DateUtil;
import com.xmniao.common.MapBeanUtil;
import com.xmniao.common.SMSUtil;
import com.xmniao.common.SnowflakeIdWorker;
import com.xmniao.common.XmnUtils;
import com.xmniao.dao.manor.ManorActiveConfigMapper;
import com.xmniao.dao.manor.PropsMapper;
import com.xmniao.entity.manor.ActivateManorConfig;
import com.xmniao.entity.manor.FlowerRecord;
import com.xmniao.entity.manor.FlowerSeedingGiveRecord;
import com.xmniao.entity.manor.GiveUserProps;
import com.xmniao.entity.manor.GrowFlowerRecord;
import com.xmniao.entity.manor.Props;
import com.xmniao.entity.manor.PropsRecord;
import com.xmniao.entity.manor.PropsRedpackage;
import com.xmniao.entity.manor.PropsRedpackageRecord;
import com.xmniao.entity.manor.PropsReport;
import com.xmniao.entity.manor.UserProps;
import com.xmniao.enums.ActiveManorEnum;
import com.xmniao.enums.BusinessStatusCode;
import com.xmniao.enums.BusinessStatusCodeEnum;
import com.xmniao.enums.ManorPropsRedpackageTypeEnum;
import com.xmniao.enums.PropsChannelEnum;
import com.xmniao.enums.PropsOperationTypeEnum;
import com.xmniao.enums.PropsSourceEnum;
import com.xmniao.enums.PropsTypeEnum;
import com.xmniao.enums.RedPackagTypeStatusEnum;
import com.xmniao.enums.ResultCodeEnum;
import com.xmniao.exception.CustomException;
import com.xmniao.service.PropsService;
import com.xmniao.service.pay.LiveWalletServiceImpl;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.manor.Result;
import com.xmniao.thrift.manor.ResultList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import static com.xmniao.enums.PropsSourceEnum.COMMON_ENERGY_ADD;
import static com.xmniao.enums.PropsSourceEnum.FLOWER_SEEDING_GIVE;
import static com.xmniao.enums.PropsSourceEnum.FLOWER_SEEDIN_GIVE_OWN;
import static com.xmniao.enums.PropsSourceEnum.GOLD_ENERGY_ADD;

/**
 * 黄金花园道具使用表
 *
 * @author liyuanbo
 * @create 2017-05-31 13:57
 **/
@Service("propsServiceImpl")
public class PropsServiceImpl implements PropsService {
    @Autowired
    private PropsMapper propsMapper;

    @Autowired
    private ManorActiveConfigMapper manorActiveConfigMapper;
    @Autowired
    private LiveWalletServiceImpl liveWalletService;
    @Resource(name = "manorRepackageServiceCharge")
    private double manorRepackageServiceCharge;
    @Resource(name = "energyCoverFlower")
    private int energyCoverFlower;
    @Resource(name = "giveOwnFlowerFromActiveManor")
    private int giveOwnFlowerFromActiveManor;//激活庄园是否赠送花朵,配置0就默认不送大于0就表示是赠送的花朵数量
    @Resource(name = "xmnSMSURL")
    private String xmnSMSURL;


    @Resource(name = "useOwnBuyFlowerRenew")
    private int useOwnBuyFlowerRenew;//是否使用自己购买的花苗进行续租

    private static Logger logger = Logger.getLogger(PropsServiceImpl.class);

    private final static int GIVE_OWN_FLOWER_MONTH_TYPE = -1;

    private final static int USER_BUY_FLOWER_RENEW_YES = 1;
    //发送红包未消费的REDISKEY
    private final static String REDIS_REPAKCAGE_NOT_CONSUME = "MANOR:PROPS:REDPACKAGEID:";
    @Autowired
    private StringRedisTemplate redisTemplate;

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    /**
     * 激活庄园或者续租庄园
     *
     * @param partenUid 上级用户ID
     * @param uid       用户ID
     * @param type      操作类型 1.激活庄园 2.续租庄园
     * @param transNo   交易号
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public Result activateManor(String transNo, long partenUid, long uid, int type, int number) {
        logger.info("当前用户id:" + uid + "开始" + ActiveManorEnum.getRemark(type) + ",交易消费号是" + transNo + "");
        Result result = new Result();
        Map<String, String> values = new HashMap<>();
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("操作的用户id不能为空,请输入正确值");
            return result;
        }
        if (type == 0 || type < 0 || ActiveManorEnum.getType(type) == 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("操作的类型(type)不能为空,1.激活庄园 2.续租庄园");
            return result;
        }
        if (StringUtils.isBlank(transNo)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("交易批次号(transNo)不能为空,请输入正确值");
            return result;
        }
        if (type == ActiveManorEnum.ACTIVE.getType()) {
            //查询当前用户有没有激活过庄园
            List<PropsRecord> propsRecords = propsMapper.listUserPropsRecordByUidAndChannel(uid, PropsChannelEnum.ACTIVE_MANOR.getChannel());
            if (propsRecords != null && propsRecords.size() > 0) {
                result.setCode(ResultCodeEnum.ERROR.status());
                result.setMessage("用户只能激活一次庄园");
                result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_ACTIVE_MANOR.getStatus());
                return result;
            }
        }
        //根据交易号查询当前用户是否已经发放过
        Integer count = propsMapper.countPropsRecordByTransNo(transNo);
        if (count != null && count > 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_COMMIT_STATUS.getStatus());
            result.setMessage("交易批次号(transNo)重复提交,不能重复提交");
            return result;
        }
        //获取消耗的道具配置
        List<ActivateManorConfig> activateManorConfigs = manorActiveConfigMapper.getActiveManorConfig();
        if (activateManorConfigs == null || activateManorConfigs.size() == 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.SYTEM_CONFING_STATUS.getStatus());
            result.setMessage("业务系统没有配置庄园续租或者激活的基础数据");
            return result;
        }

        int configEnergyTotal = 0;//扣除能量数量
        int channle = 0;
        String activeName = "";
        if (type == ActiveManorEnum.ACTIVE.getType()) {
            channle = PropsChannelEnum.ACTIVE_MANOR.getChannel();
            activeName = "激活庄园";
            for (ActivateManorConfig config : activateManorConfigs) {
                if (config.getType() == 1) {
                    configEnergyTotal = config.getNumber();
                }

            }
        } else if (type == ActiveManorEnum.RENEW.getType()) {
            channle = PropsChannelEnum.RENEW_MANOR.getChannel();
            activeName = "续租庄园";
            for (ActivateManorConfig config : activateManorConfigs) {
                if (config.getType() == 3) {
                    configEnergyTotal = config.getNumber();
                }
            }
        }
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        String batchNo = idWorker.nextId() + "";
        //1.激活庄园时候：普通能量和黄金能量都可以使用,激活庄园的时候要赠送本人一朵花
        //3.续租庄园时候: 普通能量和黄金能量,自己购买的30天的花朵都可以进行续租.不能使用自己购买的花朵超过30天的花朵进行续租

        int kPropsType = 0;//扣的道具类型,1.能量 2.花朵
        Props commenEnergy = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.COMMON_ENERGY.getType(), PropsSourceEnum.COMMON_ENERGY_ADD.getSourceType());
        if (commenEnergy == null) {
            commenEnergy = new Props();
        }
        Props goldEnergy = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.GOLD_ENERGY.getType(), PropsSourceEnum.GOLD_ENERGY_ADD.getSourceType());
        if (goldEnergy == null) {
            goldEnergy = new Props();
        }
        Props buyFlowerProps = new Props();
        //如果不够扣就查询黄金能量
        if (commenEnergy == null || (commenEnergy.getNumber() + goldEnergy.getNumber()) < configEnergyTotal * number) {

            //续租庄园还要判断当前用户的有没有购买的花朵数为30天
            if (ActiveManorEnum.RENEW.getType() == type && useOwnBuyFlowerRenew == USER_BUY_FLOWER_RENEW_YES) {
                buyFlowerProps = propsMapper.getFlowerPropsByMonthType(uid, PropsTypeEnum.FLOWER.getType(), PropsSourceEnum.FLOWER_SEEDING_OWN_BUY.getSourceType(), 1);
                if (buyFlowerProps == null || buyFlowerProps.getNumber() < 1) {
                    result.setCode(ResultCodeEnum.ERROR.status());
                    result.setStatusCode(BusinessStatusCodeEnum.AMONUT_NOT_ENOUGH_STATUS.getStatus());
                    result.setMessage("当前用户进行续租,30天的花朵数量余额不足，请及时充值.");
                    return result;
                }
                kPropsType = 2;
            } else {
                result.setCode(ResultCodeEnum.ERROR.status());
                result.setStatusCode(BusinessStatusCodeEnum.AMONUT_NOT_ENOUGH_STATUS.getStatus());
                result.setMessage("当前用户的余额不足，请及时充值.");
                return result;
            }
        } else {
            kPropsType = 1;
        }

        //激活或者续租扣除能量
        if (kPropsType == 1) {
            this.decuctionEnergy(transNo, uid, batchNo, activeName, (int) configEnergyTotal, commenEnergy, goldEnergy, channle);
        }

        //续租使用自己购买的30天的花朵进行续租庄园
        if (type == ActiveManorEnum.RENEW.getType() && kPropsType == 2) {
            logger.info("当前用户" + uid + "开始进行" + activeName + "操作,使用自己购买的花苗进行操作");
            //先扣除花苗记录表
            List<FlowerRecord> flowerRecords = propsMapper.findFlowerRecord(uid, PropsTypeEnum.FLOWER.getType(), 1, null);
            if (flowerRecords == null || flowerRecords.size() == 0) {
                logger.error("交易号:" + transNo + ",当前操作用户" + uid + "使用购买的花苗进行续租庄园,购买花苗操作记录表为空,数据异常。月份类型是:1");
                throw new CustomException("交易号:" + transNo + ",当前操作用户" + uid + "使用购买的花苗进行续租庄园,购买花苗操作记录表为空,数据异常。月份类型是:1");
            }
            FlowerRecord flowerRecord = flowerRecords.get(0);
            //直接扣除
            int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), 1, DateUtil.getNow(DateUtil.Y_M_D_HMS));
            if (updateResult != 1) {
                logger.error("交易号:" + transNo + ",当前操作用户" + uid + "使用购买的花苗续租庄园,扣除花苗的记录表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                throw new CustomException("交易号:" + transNo + ",当前操作用户" + uid + "使用购买的花苗续租庄园,扣除花苗的记录表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
            }

            int insertFlowerRecordResult = this.savePropsRecord(uid, transNo, batchNo, PropsChannelEnum.RENEW_MANOR.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                    1, PropsTypeEnum.FLOWER.getType(), buyFlowerProps.getId(), buyFlowerProps.getNumber(), buyFlowerProps.getNumber() - 1, 0, 0,
                    "当前用户" + uid + "使用购买的花苗进行续租庄园，使用的花苗数量是:1");
            if (insertFlowerRecordResult == 1) {
                int minusResult = propsMapper.minusPropsNumberById(buyFlowerProps.getId(), 1, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                if (minusResult != 1) {
                    logger.error("交易号:" + transNo + ",当前用户" + uid + "使用购买的花苗进行续租，更新花苗道具表失败.minusResult:" + minusResult);
                    throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "使用购买的花苗进行续租庄园，，更新花苗道具表失败..minusResult:" + minusResult);
                }
            } else {
                logger.error("交易号:" + transNo + ",当前用户" + uid + "使用购买的花苗进行续租，插入道具记录表时发生异常，..insertFlowerRecordResult:" + insertFlowerRecordResult);
                throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "使用购买的花苗进行续租，插入记录表时发生异常，更新花苗道具表失败..insertFlowerRecordResult:" + insertFlowerRecordResult);
            }
        }


        //激活庄园的时候赠送自己一朵花(根据配置)
        if (type == ActiveManorEnum.ACTIVE.getType()) {
            if (!initUserProps(transNo, uid)) {
                logger.info("用户激活庄园成功的时候，初始化用户数据失败。");
                throw new CustomException("激活庄园失败");
            }
        }
        //赠送种子
        if (type == ActiveManorEnum.ACTIVE.getType() && partenUid > 0) {
            if (!this.giveParentFlowerByActiveManor(transNo, uid, partenUid, batchNo)) {
                logger.info("用户激活庄园成功的时候,赠送上级花苗失败.");
                throw new CustomException(ActiveManorEnum.getRemark(type) + "失败.");
            }
        }
        logger.info("当前用户id:" + uid + "结束" + ActiveManorEnum.getRemark(type) + ",交易消费号是" + transNo + "");
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCodeEnum.SUCCESS_STATUS.getStatus());
        result.setValues(values);
        result.setMessage(ActiveManorEnum.getRemark(type) + "成功.");
        return result;
    }


    /**
     * 激活庄园的时候初始化用户数据
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public boolean initUserProps(String transNo, long uid) {
        logger.info("激活庄园的时候,开始初始化用户的数据" + uid + "");
        //花苗
        List<Props> propses = new ArrayList<>();
//        //花苗园又赠送
        Props props1 = new Props();
        props1.setPropsType(PropsTypeEnum.FLOWER.getType());
        props1.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
        props1.setUid(uid);
        props1.setPropsSource(FLOWER_SEEDING_GIVE.getSourceType());
        props1.setNumber(0);
        propses.add(props1);

        //激活庄园使用黄金能量的时候，自己赠送自己1朵花
        Props giveOwnPropsFlower = new Props();
        giveOwnPropsFlower.setPropsType(PropsTypeEnum.FLOWER.getType());
        giveOwnPropsFlower.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
        giveOwnPropsFlower.setUid(uid);
        giveOwnPropsFlower.setPropsSource(FLOWER_SEEDIN_GIVE_OWN.getSourceType());
        giveOwnPropsFlower.setNumber(giveOwnFlowerFromActiveManor);
        propses.add(giveOwnPropsFlower);
        //表示是激活庄园赠送的花苗
        if (giveOwnFlowerFromActiveManor > 0) {
            //插入种花道具的记录表
            FlowerRecord flowerRecord = new FlowerRecord();
            flowerRecord.setTransNo(transNo);
            flowerRecord.setPropsType(1);
            flowerRecord.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
            flowerRecord.setUid(uid);
            flowerRecord.setNumber(giveOwnFlowerFromActiveManor);
            flowerRecord.setMonthType(GIVE_OWN_FLOWER_MONTH_TYPE);//-1表示自己赠送的花朵
            int insertFlowerRecord = propsMapper.saveFlowerRecord(flowerRecord);
            if (insertFlowerRecord != 1) {
                logger.error("激活庄园的时候,赠送自己花朵失败,交易号用户:" + uid + ",交易号是:" + transNo);
                throw new CustomException("激活庄园的时候,赠送自己花朵失败,交易号用户:" + uid + ",交易号是:" + transNo);
            }
        }

        //花蜜 每日收益
        Props nectorProps = new Props();
        nectorProps.setPropsType(PropsTypeEnum.NECTAR.getType());
        nectorProps.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
        nectorProps.setUid(uid);
        nectorProps.setPropsSource(PropsSourceEnum.NECTAR_EVERY_DAY.getSourceType());
        nectorProps.setNumber(0);
        propses.add(nectorProps);

        //批量插入数据
        int insertResult = propsMapper.batchInsertProps(propses);
        if (insertResult != 3) {
            logger.error("激活庄园的时候初始化用户道具表发生异常.");
            return false;
        }
        return true;
    }

    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public boolean decuctionEnergy(String transNo, long uid, String batchNo, String remark, int kNectNumber, Props commonEnergy, Props goldEnergy, int channel) {

        //扣除能量
        logger.info("交易号:" + transNo + ",用户" + uid + "使用能量" + remark + "，扣除的能量总数是:" + kNectNumber);
        //先扣除普通能量
        double commonEnergyTotal = 0;
        double afterEnergyTotal = 0;
        if (commonEnergy != null && commonEnergy.getNumber() > 0) {

            if (commonEnergy.getNumber() >= kNectNumber) {
                commonEnergyTotal = kNectNumber;
            } else {
                commonEnergyTotal = commonEnergy.getNumber();
            }
            logger.info("当前用户uid:" + uid + ",开始扣除普通能量,扣除普通量的值是:" + commonEnergyTotal);
            List<FlowerRecord> flowerRecords = propsMapper.findFlowerRecord(uid, PropsTypeEnum.COMMON_ENERGY.getType(), 0, null);
            double kFlowerGive = 0;
            for (FlowerRecord flowerRecord : flowerRecords) {
                if (flowerRecord.getNumber() >= commonEnergyTotal && kFlowerGive == 0) {
                    //直接扣除
                    int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), commonEnergyTotal, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                    if (updateResult != 1) {
                        logger.error("当前操作用户" + uid + "使用" + remark + ",扣除花苗的记录表数据(普通能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                        throw new CustomException("当前操作用户" + uid + "使用" + remark + ",扣除花苗的记录(普通能量)表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                    }
                    break;
                } else {
                    kFlowerGive = kFlowerGive + flowerRecord.getNumber();
                    if (commonEnergyTotal - kFlowerGive >= 0) {
                        int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), flowerRecord.getNumber(), DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (updateResult != 1) {
                            logger.error("交易号:" + transNo + ",当前操作用户" + uid + "使用能量" + remark + ",扣除花苗的记录表数据(普通能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                            throw new CustomException("交易号:" + transNo + ",当前操作用户" + uid + "使用能量" + remark + ",扣除花苗的记录表(普通能量)数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                        }
                    }
                    if (commonEnergyTotal < kFlowerGive) {
                        double number1 = XmnUtils.sub(flowerRecord.getNumber(), XmnUtils.sub(kFlowerGive, commonEnergyTotal));
                        int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), number1, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (updateResult != 1) {
                            logger.error("交易号:" + transNo + ",当前操作用户" + uid + "使用普通能量" + remark + ",扣除花苗的记录表数据(普通能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                            throw new CustomException("交易号:" + transNo + ",当前操作用户" + uid + "使用普通能量" + remark + ",扣除花苗的记录表数据(普通能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                        }
                        break;
                    }
                }
            }
            int insertCommonEnergyRecordResult = this.savePropsRecord(uid, transNo, batchNo, channel, PropsOperationTypeEnum.MIUNS.getType(),
                    commonEnergyTotal, PropsTypeEnum.COMMON_ENERGY.getType(), commonEnergy.getId(), commonEnergy.getNumber(), commonEnergy.getNumber() - commonEnergyTotal, 0, 0,
                    "当前用户" + uid + "使用普通能量转换的花苗进行" + remark + "，使用转换的能量总数是:" + (commonEnergyTotal));
            if (insertCommonEnergyRecordResult == 1) {
                int minusResult = propsMapper.minusPropsNumberById(commonEnergy.getId(), commonEnergyTotal, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                if (minusResult != 1) {
                    logger.error("交易号:" + transNo + ",当前操作用户" + uid + "使用普通能量" + remark + ",减少普通能量时，更新普通失败.minusResult:" + minusResult);
                    throw new CustomException("交易号:" + transNo + ",当前操作用户" + uid + "使用普通能量" + remark + ",减少普通能量时，更新普通失败.minusResult:" + minusResult);
                }
            } else {
                logger.error("交易号:" + transNo + ",当前用户" + uid + "使用普通能量" + remark + ",插入普通能量道具记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
                throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "使用普通能量\"+remark+\",插入普通能量道具记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
            }
        }

        afterEnergyTotal = kNectNumber - commonEnergyTotal;
        //扣除黄金能量的值
        if (goldEnergy != null && goldEnergy.getNumber() > 0 && afterEnergyTotal > 0) {

            List<FlowerRecord> flowerRecords = propsMapper.findFlowerRecord(uid, PropsTypeEnum.GOLD_ENERGY.getType(), 0, null);
            double kFlowerGive = 0;
            for (FlowerRecord flowerRecord : flowerRecords) {
                if (flowerRecord.getNumber() >= afterEnergyTotal && kFlowerGive == 0) {
                    //直接扣除
                    int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), afterEnergyTotal, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                    if (updateResult != 1) {
                        logger.error("交易号:" + transNo + ",当当前操作用户" + uid + "使用普通能量" + remark + ",扣除花苗的记录表数据(黄金能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                        throw new CustomException("交易号:" + transNo + ",当前操作用户" + uid + "使用普通能量" + remark + ",扣除花苗的记录(黄金能量)表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                    }
                    break;
                } else {
                    kFlowerGive = kFlowerGive + flowerRecord.getNumber();
                    if (afterEnergyTotal - kFlowerGive >= 0) {
                        int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), flowerRecord.getNumber(), DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (updateResult != 1) {
                            logger.error("交易号:" + transNo + ",当前操作用户" + uid + "使用普通能量" + remark + ",扣除花苗的记录表数据((黄金能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                            throw new CustomException("交易号:" + transNo + ",当前操作用户" + uid + "使用普通能量" + remark + ",扣除花苗的记录表(黄金能量)数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                        }
                    }
                    if (afterEnergyTotal < kFlowerGive) {
                        double number1 = XmnUtils.sub(flowerRecord.getNumber(), XmnUtils.sub(kFlowerGive, afterEnergyTotal));
                        int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), number1, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (updateResult != 1) {
                            logger.error("交易号:" + transNo + ",当前操作用户" + uid + "使用普通能量" + remark + ",扣除花苗的记录表数据(普通能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                            throw new CustomException("交易号:" + transNo + ",当前操作用户" + uid + "使用普通能量" + remark + ",扣除花苗的记录表数据(普通能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                        }
                        break;
                    }
                }
            }
            afterEnergyTotal = XmnUtils.formatDouble2(afterEnergyTotal);
            logger.info(afterEnergyTotal + "afterEnergyTotal>>.");
            logger.info("用户种花操作,开始扣除黄金能量,扣除黄金能量的值是:" + afterEnergyTotal);
            int insertCommonEnergyRecordResult = this.savePropsRecord(uid, transNo, batchNo, channel, PropsOperationTypeEnum.MIUNS.getType(),
                    afterEnergyTotal, PropsTypeEnum.GOLD_ENERGY.getType(), goldEnergy.getId(), goldEnergy.getNumber(), goldEnergy.getNumber() - afterEnergyTotal, 0, 0,
                    "当前用户" + uid + "使用黄金能量进行" + remark + "，使用转换的能量总数是:" + (afterEnergyTotal));
            if (insertCommonEnergyRecordResult == 1) {
                int minusResult = propsMapper.minusPropsNumberById(goldEnergy.getId(), afterEnergyTotal, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                if (minusResult != 1) {
                    logger.error("交易号:" + transNo + ",当前用户" + uid + "使用黄金能量" + remark + ",减少黄金能量时，更新普通失败.minusResult:" + minusResult);
                    throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "使用黄金能量" + remark + ",减少黄金能量时，更新普通失败.minusResult:" + minusResult);
                }
            } else {
                logger.error("交易号:" + transNo + ",当前用户" + uid + "使用黄金能量" + remark + ",插入黄金能量道具记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
                throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "使用黄金能量" + remark + ",插入黄金能量道具记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
            }
        }
        return true;
    }

    /**
     * 种花
     *
     * @param transNo 交易号
     * @param uid     操作的用户ID
     * @param number  需要种的花的数量
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public ResultList growFlower(String transNo, long uid, int number) {
        logger.info("交易号:" + transNo + ",当前用户uid:" + uid + "进行种花操作,种花的数量是" + number + "");
        ResultList result = new ResultList();
        if (StringUtils.isBlank(transNo)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("交易批次号(transNo)不能为空,请输入正确值");
            return result;
        }
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("操作的用户id不能为空,请输入正确值");
            return result;
        }
        if (number == 0 || number < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("种花的数量必须大于0");
            return result;
        }
        Integer record = propsMapper.countPropsRecordByTransNo(transNo);
        if (record != null && record > 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_COMMIT_STATUS.getStatus());
            result.setMessage("当前交易号已经有记录，不能重复提交");
            return result;
        }

        //首先判断当前用户的花苗数量，种子数量，能量是否足够种花
        List<Props> flowerProps = propsMapper.listPropsByUidAndPropsType(uid, PropsTypeEnum.FLOWER.getType());
        double flowerTotal = 0;
        if (flowerProps != null && flowerProps.size() > 0) {
            for (Props flowerProp : flowerProps) {
                flowerTotal = flowerTotal + flowerProp.getNumber();
            }
        }

        int coverFlower = 0;
        //if (flowerTotal < number) {
        //查询出用户的能量总数
        //普通能量的总数
        //查询出能量转花苗的值
        //Integer coverFlowerValue = propsMapper.getCoverValue(PropsTypeEnum.COMMON_ENERGY.getType(), PropsTypeEnum.FLOWER.getType());
        Props commonEnergy = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.COMMON_ENERGY.getType(), COMMON_ENERGY_ADD.getSourceType());
        double energyTotal = 0;
        if (commonEnergy != null) {
            energyTotal = energyTotal + commonEnergy.getNumber();
        }
        //黄金能量
        Props goldEnergy = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.GOLD_ENERGY.getType(), GOLD_ENERGY_ADD.getSourceType());
        if (goldEnergy != null) {
            energyTotal = energyTotal + goldEnergy.getNumber();
        }
        coverFlower = XmnUtils.floor(energyTotal / energyCoverFlower);

        Props giveFlowerProps = new Props();
        Props giveOwnFlowerProps = new Props();
        List<Props> monthProps = new ArrayList<>();
        if (flowerProps != null && flowerProps.size() > 0) {
            for (Props flowerProp : flowerProps) {
                if (flowerProp.getPropsSource() == PropsSourceEnum.FLOWER_SEEDING_GIVE.getSourceType()) {
                    giveFlowerProps = flowerProp;
                } else if (flowerProp.getPropsSource() == PropsSourceEnum.FLOWER_SEEDIN_GIVE_OWN.getSourceType()) {
                    giveOwnFlowerProps = flowerProp;
                } else {
                    monthProps.add(flowerProp);
                }
            }
        }

        if ((coverFlower + (flowerTotal - giveFlowerProps.getNumber())) < number) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.AMONUT_NOT_ENOUGH_STATUS.getStatus());
            result.setMessage("当前用户的花苗数量不足.");
            return result;
        }
        //种花优先扣除使用黄金能量激活庄园赠送自己的花朵，能量，自己购买的花(按照最大的优先级扣除)
        //查询当前用户是否有种子花苗


        if (monthProps.size() > 0) {
            Collections.sort(monthProps, new Comparator<Props>() {
                @Override
                public int compare(Props o1, Props o2) {
                    return (o1.getFlowerMonthType() == o2.getFlowerMonthType()) ? 0 : (o1.getFlowerMonthType() < o2.getFlowerMonthType()) ? 1 : -1;
                }
            });
        }
        List<GrowFlowerRecord> growFlowerRecords = new ArrayList<>();
        logger.info("排序后的集合是:" + JSON.toJSONString(monthProps));
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        String batchNo = idWorker.nextId() + "";
        int hnumber = 0;
        //int kGiveNumber = 0;
        int kGiveOwnFlower = 0;
        List<Map<String, String>> values = new ArrayList<>();

        hnumber = number;
        if (giveOwnFlowerProps != null && giveOwnFlowerProps.getNumber() > 0) {

            //扣除激活庄园自己赠送自己的花
            // Props giveOwnFlowerProps = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.FLOWER.getType(), FLOWER_SEEDIN_GIVE_OWN.getSourceType());
            if (giveOwnFlowerProps != null && giveOwnFlowerProps.getNumber() > 0) {
                if (giveOwnFlowerProps.getNumber() >= hnumber) {
                    kGiveOwnFlower = hnumber;
                } else {
                    kGiveOwnFlower = (int) giveOwnFlowerProps.getNumber();
                }
                logger.info("用户使用激活庄园赠送的花进行种花，扣除的的数量是:>>>>>" + kGiveOwnFlower);
                //查询花苗记录表是否有自己赠送自己的花苗
                List<FlowerRecord> flowerRecords = propsMapper.findFlowerRecord(uid, PropsTypeEnum.FLOWER.getType(), GIVE_OWN_FLOWER_MONTH_TYPE, null);
                if (flowerRecords != null && flowerRecords.size() > 1) {
                    logger.error("用户种花时,使用的是用户激活庄园赠送的花进行种花，当前用户花苗记录表的赠送自己的花朵数量和道具表数量不对应,道具表id:" + uid);
                    throw new CustomException("用户种花时,使用的是用户激活庄园赠送的花进行种花，当前用户花苗记录表的赠送自己的花朵数量和道具表数量不对应,道具表id:" + uid);
                } else if (flowerRecords != null && flowerRecords.size() == 1) {
                    FlowerRecord giveOwnFlowerRecord = flowerRecords.get(0);
                    int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(giveOwnFlowerRecord.getId(), kGiveOwnFlower, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                    if (updateResult != 1) {
                        logger.error("用户种花时,使用的是用户激活庄园赠送的花进行种花，扣除花苗记录表失败,花苗记录表id:" + giveOwnFlowerRecord.getId() + ",更新的数量是:" + kGiveOwnFlower);
                        throw new CustomException("用户种花时,使用的是用户激活庄园赠送的花进行种花，扣除花苗记录表失败,花苗记录表id:" + giveOwnFlowerRecord.getId() + ",更新的数量是:" + kGiveOwnFlower);
                    }
                }

                int insertRecordResult = this.savePropsRecord(uid, transNo, batchNo, PropsChannelEnum.GROW_FLOWERS.getChannel(), PropsOperationTypeEnum.MIUNS.getType(), kGiveOwnFlower,
                        PropsTypeEnum.FLOWER.getType(), giveOwnFlowerProps.getId(), giveOwnFlowerProps.getNumber(), giveOwnFlowerProps.getNumber() - kGiveOwnFlower, 0, 0,
                        "当前用户" + uid + "使用自己激活庄园赠送自己的花，使用的赠送的数量是:" + 1);
                if (insertRecordResult == 1) {
                    int updateResult = propsMapper.minusPropsNumberById(giveOwnFlowerProps.getId(), kGiveOwnFlower, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                    if (updateResult != 1) {
                        logger.error("用户种花时,使用的是用户激活庄园赠送的花进行种花，扣除花失败,道具表id:" + uid);
                        throw new CustomException("用户种花时,使用的是用户激活庄园赠送的花进行种花，扣除花失败,道具表id:" + uid);
                    } else {
                        hnumber = hnumber - (int) kGiveOwnFlower;

                        GrowFlowerRecord growFlowerRecord = new GrowFlowerRecord();
                        growFlowerRecord.setType(4);
                        growFlowerRecord.setGiveUid(uid);
                        growFlowerRecord.setNumber((int) kGiveOwnFlower);
                        growFlowerRecords.add(growFlowerRecord);
                    }
                } else {
                    logger.error("用户种花时,使用的是用户激活庄园赠送的花进行种花，增加操作记录发生失败.");
                    throw new CustomException("用户种花时,使用的是用户激活庄园赠送的花进行种花，增加操作记录发生失败.");
                }
            }
        }

        //扣除能量
        int kNectNumber = 0;
        if (hnumber > 0 && coverFlower > 0) {
            if (hnumber >= coverFlower) {
                kNectNumber = coverFlower;
            } else if (hnumber < coverFlower) {
                kNectNumber = hnumber;
            }
            GrowFlowerRecord growFlowerRecord = new GrowFlowerRecord();
            growFlowerRecord.setType(2);
            growFlowerRecord.setNumber(kNectNumber);
            growFlowerRecords.add(growFlowerRecord);
            //String transNo,long uid,String batchNo,String remark,int  kNectNumber, Props commonEnergy,Props goldEnergy
            this.decuctionEnergy(transNo, uid, batchNo + "", "能量兑换花苗(种花操作)", kNectNumber * energyCoverFlower, commonEnergy, goldEnergy, PropsChannelEnum.GROW_FLOWERS.getChannel());
        }
        hnumber = number - kGiveOwnFlower - kNectNumber;
        if (hnumber > 0) {
            double monthFlower = 0;
            logger.info("用户种花操作,开始扣除套餐购买的花苗,扣除花苗的值:" + hnumber);
            //按照月份最大的优先级扣除
            for (Props monthProp : monthProps) {
                double monthPropNumber = monthProp.getNumber();
                if (monthPropNumber == 0 || hnumber == 0) {
                    continue;
                } else {
                    if (monthPropNumber >= hnumber) {
                        monthFlower = hnumber;
                    } else {
                        monthFlower = monthPropNumber;
                    }
                    GrowFlowerRecord growFlowerRecord = new GrowFlowerRecord();
                    growFlowerRecord.setType(3);
                    growFlowerRecord.setMonth(monthProp.getFlowerMonthType());
                    growFlowerRecord.setNumber((int) monthFlower);
                    growFlowerRecords.add(growFlowerRecord);

                    //首先扣除花苗的记录表的数据，从最老的时间排序

                    List<FlowerRecord> flowerRecords = propsMapper.findFlowerRecord(uid, PropsTypeEnum.FLOWER.getType(), monthProp.getFlowerMonthType(), null);
                    if (flowerRecords == null || flowerRecords.size() == 0) {
                        logger.error("当前操作用户" + uid + "使用购买的花苗种花时,购买花苗操作记录表为空,数据异常。月份类型是:" + monthProp.getFlowerMonthType());
                        throw new CustomException("当前操作用户" + uid + "使用购买的花苗种花时,购买花苗操作记录表为空,数据异常。月份类型是:" + monthProp.getFlowerMonthType());
                    }
                    double kFlowerGive = 0;
                    double kTotal = 0;
                    for (FlowerRecord flowerRecord : flowerRecords) {
                        if (flowerRecord.getNumber() >= monthFlower && kFlowerGive == 0) {
                            //直接扣除
                            int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), monthFlower, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                            if (updateResult != 1) {
                                logger.error("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                throw new CustomException("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                            }
                            break;
                        } else {
                            kFlowerGive = kFlowerGive + flowerRecord.getNumber();
                            System.out.println(kFlowerGive + "kFlowerGive>>>>>");
                            if (monthFlower - kFlowerGive >= 0) {
                                kTotal = kTotal + flowerRecord.getNumber();
                                int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), flowerRecord.getNumber(), DateUtil.getNow(DateUtil.Y_M_D_HMS));
                                if (updateResult != 1) {
                                    logger.error("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                    throw new CustomException("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                }
                            }
                            double kFlower2 = 0;
                            if (monthFlower < kFlowerGive) {

                                if (flowerRecord.getNumber() >= (monthFlower - kTotal)) {
                                    kFlower2 = (monthFlower - kTotal);
                                } else if (flowerRecord.getNumber() < (monthFlower - kTotal)) {
                                    kFlower2 = flowerRecord.getNumber();
                                }

                                int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), kFlower2, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                                if (updateResult != 1) {
                                    logger.error("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                    throw new CustomException("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                }
                                if ((monthFlower - kTotal) == kFlower2) {
                                    break;
                                }
                                kTotal = kTotal + flowerRecord.getNumber();

                            }
                        }
                    }

                    int insertCommonEnergyRecordResult = this.savePropsRecord(uid, transNo, batchNo, PropsChannelEnum.GROW_FLOWERS.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                            monthFlower, PropsTypeEnum.FLOWER.getType(), monthProp.getId(), monthProp.getNumber(), monthProp.getNumber() - monthFlower, 0, 0,
                            "当前用户" + uid + "使用购买的花苗进行种花，使用的花苗数量是:" + monthFlower);
                    if (insertCommonEnergyRecordResult == 1) {
                        int minusResult = propsMapper.minusPropsNumberById(monthProp.getId(), monthFlower, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (minusResult != 1) {
                            logger.error("当前用户" + uid + "使用购买的花苗进行种花，，更新普通失败.minusResult:" + minusResult);
                            throw new CustomException("当前用户" + uid + "使用购买的花苗进行种花，，更新普通失败.minusResult:" + minusResult);
                        }
                    } else {
                        logger.error("当前用户" + uid + "使用购买的花苗进行种花，插入道具记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
                        throw new CustomException("当前用户" + uid + "使用购买的花苗进行种花，插入记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
                    }
                }
                hnumber = hnumber - (int) monthFlower;
            }
        }
        if (hnumber != 0) {
            logger.error("当前用户" + uid + "使用购买的花苗进行种花，扣除用户的花苗最后的结果不为0");
            throw new CustomException("当前用户" + uid + "使用购买的花苗进行种花，扣除用户的花苗最后的结果不为0");
        }
        List<Map<String, String>> values1 = new ArrayList<>();
        if (growFlowerRecords.size() > 0) {
            for (GrowFlowerRecord growFlowerRecord : growFlowerRecords) {
                Map<String, String> value = MapBeanUtil.convertMap(growFlowerRecord, "giveUid", "number", "month", "type");
                values1.add(value);
            }
        }
        logger.info("交易号:" + transNo + ",当前用户uid:" + uid + "进行种花操作结束,种花的数量是" + number + ",返回的values是:" + JSON.toJSONString(values1));
        result.setValues(values1);
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCode.SUCCESS_STATUS.getStatus());
        result.setMessage("用户种花成功.");
        return result;

    }

    /**
     * 购买花苗
     *
     * @param transNo 交易号
     * @param uid     交易的用户ID
     * @param number  购买的花苗数量
     * @param month   购买的月数
     * @param amount  总金额
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public Result buyFlower(String transNo, long uid, int number, int month, double amount) {
        logger.info("交易号是:" + transNo + ",当前用户uid:" + uid + "进行购买花苗的操作,购买的数量是" + number + ",月份是" + month + ",鸟币的金额是" + amount + "");
        Result result = new Result();
        if (StringUtils.isBlank(transNo)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("交易批次号(transNo)不能为空,请输入正确值");
            return result;
        }
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("操作的用户id不能为空,请输入正确值");
            return result;
        }
        if (number == 0 || number < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("购买的数量必须大于0");
            return result;
        }
        if (month == 0 || month < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("购买的花苗的月数必须大于1");
            return result;
        }
        if (amount < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("购买的总金额鸟币必须大于0");
            return result;
        }
        //判断当前用户的鸟币是否足够买花苗
        Map<String, String> wallet = new HashMap<>();
        wallet.put("uid", uid + "");
        try {
            ResponseData responseData = liveWalletService.getLiveWallet(wallet);
            if (responseData.getState() == 0) {
                Map<String, String> resultMap = responseData.getResultMap();
                String status = resultMap.get("status");
                if ("2".equals(status)) {
                    result.setCode(ResultCodeEnum.ERROR.status());
                    result.setStatusCode(BusinessStatusCodeEnum.WALLET_LOCK.getStatus());
                    result.setMessage("当前用户的钱包被锁定");
                    return result;
                }
                String zbalance = resultMap.get("zbalance");
                if (Double.parseDouble(zbalance) < amount) {
                    result.setCode(ResultCodeEnum.ERROR.status());
                    result.setStatusCode(BusinessStatusCodeEnum.AMONUT_NOT_ENOUGH_STATUS.getStatus());
                    result.setMessage("当前用户的钱包的鸟币余额不足.");
                    return result;
                }
                //限制消费
                String restrictive = resultMap.get("restrictive");
                if ("002".equals(restrictive)) {
                    String limitBalance = resultMap.get("limitBalance");//消费限制
                    Double miunBalance = XmnUtils.formatDouble2(Double.parseDouble(zbalance) - Double.parseDouble(limitBalance));
                    if (miunBalance < amount) {
                        result.setCode(ResultCodeEnum.ERROR.status());
                        result.setStatusCode(BusinessStatusCodeEnum.AMONUT_NOT_ENOUGH_STATUS.getStatus());
                        result.setMessage("当前用户的钱包的鸟币余额不足.");
                        return result;
                    }
                }
                //插入种花道具的记录表
                FlowerRecord flowerRecord = new FlowerRecord();
                flowerRecord.setTransNo(transNo);
                flowerRecord.setPropsType(1);
                flowerRecord.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
                flowerRecord.setUid(uid);
                flowerRecord.setNumber(number);
                flowerRecord.setMonthType(month);
                int insertFlowerRecord = propsMapper.saveFlowerRecord(flowerRecord);
                if (insertFlowerRecord != 1) {
                    logger.error("购买花套餐时候,增加花苗的能量交易记录发生失败,交易号用户:" + uid + ",交易号是:" + transNo);
                    throw new CustomException("购买花套餐时候,增加花苗的能量交易记录发生失败,交易号用户:" + uid + ",交易号是:" + transNo);
                }

                //先查出当前用户是否当前套餐的花苗
                Props props = propsMapper.getFlowerPropsByMonthType(uid, PropsTypeEnum.FLOWER.getType(), PropsSourceEnum.FLOWER_SEEDING_OWN_BUY.getSourceType(), month);
                //增加操作记录
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                if (props == null) {
                    Props addProps = new Props();
                    addProps.setUid(uid);
                    addProps.setFlowerMonthType(month);
                    addProps.setPropsType(PropsTypeEnum.FLOWER.getType());
                    addProps.setPropsSource(PropsSourceEnum.FLOWER_SEEDING_OWN_BUY.getSourceType());
                    addProps.setNumber(number);
                    addProps.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
                    int addResult = propsMapper.insertProps(addProps);
                    if (addResult == 1) {

                        //增加操作记录
                        int insertRecordResult = this.savePropsRecord(uid, transNo, idWorker.nextId() + "", PropsChannelEnum.BUY_FLOWER_SEEDLING.getChannel(),
                                PropsOperationTypeEnum.ADD.getType(), number, PropsTypeEnum.FLOWER.getType(), addProps.getId(), 0, number, 0, 0,
                                "当前用户" + uid + "购买花苗,购买的花苗数量是" + number + "，使用的鸟币金额是:" + amount);
                        if (insertRecordResult != 1) {
                            logger.error("用户" + uid + "购买花苗,插入道具记录发生异常.");
                            throw new CustomException("用户" + uid + "购买花苗,插入道具记录发生异常.");
                        }
                    } else {
                        logger.error("用户购买花苗失败,增加用户的道具类型失败.");
                        throw new CustomException("用户购买花苗失败,增加用户的道具类型失败.");
                    }
                } else {
                    int insertRecordResult = this.savePropsRecord(uid, transNo, idWorker.nextId() + "", PropsChannelEnum.BUY_FLOWER_SEEDLING.getChannel(),
                            PropsOperationTypeEnum.ADD.getType(), number, PropsTypeEnum.FLOWER.getType(), props.getId(), props.getNumber(), props.getNumber() + number, 0, 0,
                            "当前用户" + uid + "购买花苗,购买的花苗数量是" + number + "，使用的鸟币金额是:" + amount);
                    if (insertRecordResult == 1) {
                        int addResult = propsMapper.addFlowerPropsByMonth(uid, number, PropsTypeEnum.FLOWER.getType(), PropsSourceEnum.FLOWER_SEEDING_OWN_BUY.getSourceType(), month,
                                DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (addResult != 1) {
                            logger.error("用户购买花苗失败,增加用户的自己购买的道具类型数量失败.");
                            throw new CustomException("用户购买花苗失败,增加用户的自己购买的道具类型数量失败.");
                        }
                    } else {
                        logger.error("用户购买花苗失败,增加用户的道具记录表失败.");
                        throw new CustomException("用户购买花苗失败,增加用户的道具记录表失败.");
                    }
                }
            } else {
                logger.error("获取用户的钱包信息发生异常,异常信息如下:" + responseData.getMsg());
            }
            //扣除用户的鸟币
            //扣除当前用户的鸟币
            Map<String, String> map = new HashMap<>();
            map.put("uid", uid + "");
            map.put("rtype", "35");//庄园发鸟币红包
            map.put("option", "1");
            map.put("zbalance", amount + "");
            map.put("description", number + "朵(" + month + ")个月");
            try {
                ResponseData result1 = liveWalletService.liveWalletOption(map);
                if (result1.getState() == 0) {
                    logger.info("扣除当前用户的鸟币成功.扣除的金额是" + amount);
                } else {
                    logger.info("扣除当前用户的鸟币失败.异常信息如下:" + result1.getMsg());
                    throw new CustomException("扣除当前用户的鸟币失败.异常信息如下" + result1.getMsg());
                }
            } catch (TException e) {
                logger.info("扣除当前用户的鸟币失败.");
                throw new CustomException("扣除当前用户的鸟币失败.");
            }

        } catch (TException e) {
            e.printStackTrace();
        }
        logger.info("交易号是:" + transNo + ",当前用户uid:" + uid + "进行购买花苗的操作结束,购买的数量是" + number + ",月份是" + month + ",鸟币的金额是" + amount + "");
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCode.SUCCESS_STATUS.getStatus());
        result.setMessage("用户购买的花苗套餐成功");
        return result;
    }


    /**
     * 增加用户的能量数
     *
     * @param transNo 交易号
     * @param uid     用户ID
     * @param number  能量数
     * @param type    3.普通能量 4.黄金能量
     */
    public Result addUserEnergy(String transNo, long uid, double number, int type) {
        logger.info("用户充值鸟都转换能量开始,用户uid:" + uid + ",数量:" + number + ",转换的能量类型:" + type + "");
        Result result = new Result();
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("请输入正确的参数UID");
            return result;
        }
        if (uid == 0 || number < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("请输入正确的能量数");
            return result;
        }
        int propsType = 0;
        int propsSoruce = 0;
        if (type == 3) {
            propsType = PropsTypeEnum.COMMON_ENERGY.getType();
            propsSoruce = COMMON_ENERGY_ADD.getSourceType();
        } else if (type == 4) {
            propsType = PropsTypeEnum.GOLD_ENERGY.getType();
            propsSoruce = GOLD_ENERGY_ADD.getSourceType();
        }

        //根据交易号查询当前用户是否已经发放过
        Integer count = propsMapper.countPropsRecordByTransNo(transNo);
        if (count != null && count > 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_COMMIT_STATUS.getStatus());
            result.setMessage("交易批次号(transNo)重复提交,不能重复增加用户的能量");
            return result;
        }
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        //查询当前用户是否有能量统计的记录

        //插入种花道具的记录表
        FlowerRecord flowerRecord = new FlowerRecord();
        flowerRecord.setTransNo(transNo);
        flowerRecord.setPropsType(type);
        flowerRecord.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
        flowerRecord.setUid(uid);
        flowerRecord.setNumber(number);
        int insertFlowerRecord = propsMapper.saveFlowerRecord(flowerRecord);
        if (insertFlowerRecord != 1) {
            logger.error("累计能量的时候,增加花苗的能量交易记录发生失败,交易号用户:" + uid + ",交易号是:" + transNo);
            throw new CustomException("累计能量的时候,增加花苗的能量交易记录发生失败,交易号用户:" + uid + ",交易号是:" + transNo);
        }
        List<Props> propses = propsMapper.listPropsByUidAndPropsType(uid, propsType);
        if (propses == null || propses.size() == 0) {
            //        //能量 打赏累计
            Props energy = new Props();
            energy.setPropsType(propsType);
            energy.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
            energy.setUid(uid);
            energy.setPropsSource(propsSoruce);
            energy.setNumber(number);
            int insertResult = propsMapper.insertProps(energy);
            if (insertResult == 1) {
                int insertRecordResult = this.savePropsRecord(uid, transNo, idWorker.nextId() + "", PropsChannelEnum.RECHARGE_CONVERSION.getChannel(), PropsOperationTypeEnum.ADD.getType(),
                        number, propsType, energy.getId(), energy.getNumber(), number, 0, 0,
                        "用户" + uid + "充值鸟豆转换的能量,转换的能量数是:" + number);
                if (insertRecordResult != 1) {
                    throw new CustomException("当前用户" + uid + "增加能量失败.交易号用户:" + uid + ",交易号是:" + transNo);
                }
            } else if (insertResult != 1) {
                throw new CustomException("当前用户" + uid + "增加能量失败.交易号用户:" + uid + ",交易号是:" + transNo);
            }
        } else {
            Props nectarProps = propsMapper.getUserPropsByUidAndTypeAndSource(uid, propsType, propsSoruce);
            int insertRecordResult = this.savePropsRecord(uid, transNo, idWorker.nextId() + "", PropsChannelEnum.RECHARGE_CONVERSION.getChannel(), PropsOperationTypeEnum.ADD.getType(),
                    number, propsType, nectarProps.getId(), nectarProps.getNumber(), nectarProps.getNumber() + number, 0, 0,
                    "用户" + uid + "充值鸟豆转换的能量,转换的能量数是:" + number);
            if (insertRecordResult == 1) {
                int miunsNumber = propsMapper.addPropsNumber(uid, propsType, propsSoruce, number, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                if (miunsNumber != 1) {
                    throw new CustomException("当前用户" + uid + "增加能量失败.交易号用户:" + uid + ",交易号是:" + transNo);
                }
            } else {
                throw new CustomException("当前用户" + uid + "增加能量失败.交易号用户:" + uid + ",交易号是:" + transNo);
            }
        }
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCode.SUCCESS_STATUS.getStatus());
        result.setMessage("增加用户能量成功.");
        return result;
    }


    /**
     * 下级激活庄园赠送花苗
     */
    private boolean giveParentFlowerByActiveManor(String transNo, long uid, long parentUid, String batchNo) {
        logger.info("下级激活庄园使时候,赠送上级种子.下级UID:" + uid + ",上级UID:" + parentUid + ",激活庄园的消费交易号:" + transNo + "");
        boolean result = true;
        //用户激活

        Props props = propsMapper.getUserPropsByUidAndTypeAndSource(parentUid, PropsTypeEnum.FLOWER.getType(), FLOWER_SEEDING_GIVE.getSourceType());
        int insertRecordResult = this.savePropsRecord(parentUid, transNo, batchNo, PropsChannelEnum.GIVE.getChannel(), PropsOperationTypeEnum.ADD.getType(),
                1, PropsTypeEnum.FLOWER.getType(), props.getId(), props.getNumber(), props.getNumber() + 1, 0, 0,
                "用户" + uid + "激活庄园,赠送给上级用户(" + parentUid + ")1课种子");
        if (insertRecordResult == 1) {
            //关系记录表插入一条记录
            int insertResult = propsMapper.insertFlowerGiveRecord(parentUid, uid, DateUtil.getNow(DateUtil.Y_M_D_HMS));
            if (insertResult == 1) {
                insertResult = propsMapper.addPropsNumber(parentUid, PropsTypeEnum.FLOWER.getType(), FLOWER_SEEDING_GIVE.getSourceType(), 1,
                        DateUtil.getNow(DateUtil.Y_M_D_HMS));
                if (insertResult != 1) {
                    result = false;
                }
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 统计用户道具
     *
     * @param uid 用户ID
     */
    public ResultList statisticsUserProps(long uid) {
        logger.info("当前用户id:" + uid + "统计自己的用户道具信息");
        ResultList result = new ResultList();

        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("操作的用户id不能为空,请输入正确的值");
            return result;
        }
        //获取当前用户每一种道具的总和
        List<UserProps> propsStatisticses = propsMapper.statisticsPropsByUserId(uid);
        List<Map<String, String>> coverMap = new ArrayList<>();

        if (propsStatisticses != null && propsStatisticses.size() > 0) {
            for (UserProps props : propsStatisticses) {
                Map<String, String> value = MapBeanUtil.convertMap(props, "type", "number", "sourceType");
                coverMap.add(value);
            }
        }
        Map<String, String> hasnewNectarMap = new HashMap<>();
        hasnewNectarMap.put("coverValue", energyCoverFlower + "");
        coverMap.add(hasnewNectarMap);


        Map<String, String> monthFlowwerMap = new HashMap<>();
        if (useOwnBuyFlowerRenew == USER_BUY_FLOWER_RENEW_YES) {
            Props monthFlower = propsMapper.getFlowerPropsByMonthType(uid, PropsTypeEnum.FLOWER.getType(), PropsSourceEnum.FLOWER_SEEDING_OWN_BUY.getSourceType(), 1);
            if (monthFlower != null) {
                monthFlowwerMap.put("oneMonthFlowerNumber", monthFlower.getNumber() + "");
            } else {
                monthFlowwerMap.put("oneMonthFlowerNumber", "0");
            }
        } else {
            monthFlowwerMap.put("oneMonthFlowerNumber", "0");
        }
        coverMap.add(monthFlowwerMap);

        result.setMessage("获取当前用户的每种道具的数量统计");
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setValues(coverMap);
        return result;
    }


    /**
     * 用户领取每日统计的花蜜
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public Result receiveEvaryDayNectary(String transNo, long uid, double number) {
        logger.info(uid + "开始领取用户每日统计的花蜜,交易号是:" + transNo);
        Result result = new Result();
        if (uid == 0 || uid < 0) {
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setMessage("当前用户UID不能为空");
            return result;
        }
        //查询当前的交易号是否已经领取过
        Integer record = propsMapper.countPropsRecordByTransNo(transNo);
        if (record != null && record > 0) {
            result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_GIVE_STAUTS.getStatus());
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setMessage("当前用户交易号已经提交过.");
            return result;
        }
        //插入道具记录表
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        //double number = nectarGiveRecord.getNumber();
        Props props = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.NECTAR.getType(), PropsSourceEnum.NECTAR_EVERY_DAY.getSourceType());
        int insertRecordResult = this.savePropsRecord(uid, transNo, idWorker.nextId() + "", PropsChannelEnum.EVERY_DAY_EARNINGS.getChannel(), PropsOperationTypeEnum.ADD.getType(),
                number, PropsTypeEnum.NECTAR.getType(), props.getId(), props.getNumber(), props.getNumber() + number, 0, 0,
                "用户" + uid + "领取每日收益的花蜜,领取的花蜜数量是:" + number);
        if (insertRecordResult == 1) {
            int addNumber = propsMapper.addNectaryPropsNumber(uid, PropsSourceEnum.NECTAR_EVERY_DAY.getSourceType(), number, DateUtil.getNow(DateUtil.Y_M_D_HMS));
            if (addNumber != 1) {
                logger.error("用户" + uid + "领取每日的花蜜失败,增加用户的花蜜数量失败，更新的结果是addNumber:" + addNumber);
                throw new CustomException("用户" + uid + "领取每日的花蜜失败,增加用户的花蜜数量失败，更新的结果是addNumber:" + addNumber);
            }
        } else {
            logger.error("用户" + uid + "领取每日的花蜜失败,插入用户的道具记录表失败,，更新的结果是insertRecordResult:" + insertRecordResult);
            throw new CustomException("用户领取每日的花蜜失败,插入用户的道具记录表失败,，更新的结果是insertRecordResult:" + insertRecordResult);
        }

        logger.info("用户" + uid + "领取花蜜成功,领取的数量是:" + number);
        Map<String, String> values = new HashMap<>();
        values.put("number", number + "");

        result.setStatusCode(BusinessStatusCodeEnum.SUCCESS_STATUS.getStatus());
        result.setValues(values);
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setMessage("当前用户领取花蜜成功.");
        return result;
    }

    /**
     * 统计用户仓库的道具信息
     */
    public Result statisticsUserRepertory(long uid) {
        logger.info("当前用户id:" + uid + "统计用户仓库信息");
        Result result = new Result();
        if (uid == 0 || uid < 0) {
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setMessage("当前用户UID不能为空");
            return result;
        }

        //1.获取仓库的花蜜滴数 2.今日收益的花蜜数  3.今日收获的花蜜数量
        //获取花蜜数
        Map<String, String> values = new HashMap<>();
        //查询当前用户累计的花蜜数
        Double nectaryTotal = propsMapper.getUserAddNectarTotal(uid);
        values.put("total", nectaryTotal == null ? "0" : nectaryTotal + "");
        //查询本月收益的花蜜
        Double addMonthNectary = propsMapper.getUserAddNectarToday(uid, DateUtil.firstDayByMonth() + " 00:00:00", DateUtil.lastDayByMonth() + " 23:59:59");
        values.put("addMonthNectary", addMonthNectary == null ? "0" : addMonthNectary + "");
        //查询今日收获的花蜜
        Double addTodayNectary = propsMapper.getUserAddNectarToday(uid, DateUtil.getNow(DateUtil.Y_M_D) + " 00:00:00", DateUtil.getNow(DateUtil.Y_M_D) + " 23:59:59");
        values.put("addTodayNectary", addTodayNectary == null ? "0" : addTodayNectary + "");

        result.setValues(values);
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCodeEnum.SUCCESS_STATUS.getStatus());
        result.setMessage("统计用户的仓库道具信息");
        return result;
    }


    /**
     * 赠送用户蜜罐
     *
     * @param transNo    交易号
     * @param uid        赠送人ID
     * @param giveUid    被赠送人UID
     * @param number     蜜罐数量
     * @param coverValue 多少蜜滴
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public Result giveUserNectary(String transNo, long uid, long giveUid, int number, int coverValue) {
        logger.info("交易号:" + transNo + ",当前用户uid:" + uid + "开始赠送花蜜，赠送的用户是:" + giveUid + ",赠送的数量是" + number + ",花蜜转换的配置是:" + coverValue + "");
        Result result = new Result();
        if (StringUtils.isBlank(transNo)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("交易消费号不能为空");
            return result;
        }
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("赠送用户ID不能为空");
            return result;
        }
        if (giveUid == 0 || giveUid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("被赠送用户ID不能为空");
            return result;
        }
        if (number == 0 || number < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("赠送的蜜罐数量必须大于0");
            return result;
        }
        if (coverValue == 0 || coverValue < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("蜜滴转换蜜罐的数量必须大于0.");
            return result;
        }
        Integer record = propsMapper.countPropsRecordByTransNo(transNo);
        if (record != null && record > 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_COMMIT_STATUS.getStatus());
            result.setMessage("记录已存在，请勿重复提交");
            return result;
        }
        //查询当前用户的花蜜是否足够赠送
        Props props = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.NECTAR.getType(), PropsSourceEnum.NECTAR_EVERY_DAY.getSourceType());
        double total = number * coverValue;
        if (props == null || props.getNumber() < total) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.AMONUT_NOT_ENOUGH_STATUS.getStatus());
            result.setMessage("当前用户的花蜜数量不足够赠送");
            return result;
        }
        //扣除用户的花蜜数量
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        int insertRecordResult = this.savePropsRecord(uid, transNo, idWorker.nextId() + "", PropsChannelEnum.GIVE_NECTARY.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                total, PropsTypeEnum.NECTAR.getType(), props.getId(), props.getNumber(), props.getNumber() - total, number, giveUid,
                "当前用户" + uid + "赠送用户" + giveUid + "," + number + "罐花蜜,花蜜转换的配置是" + coverValue + ".");
        if (insertRecordResult == 1) {
            //扣除用户的花蜜
            int minusResult = propsMapper.minusPropsNumber(uid, PropsTypeEnum.NECTAR.getType(), PropsSourceEnum.NECTAR_EVERY_DAY.getSourceType(),
                    total, DateUtil.getNow(DateUtil.Y_M_D_HMS));
            if (minusResult == 1) {
                //增加一条赠送记录表
                GiveUserProps giveUserProps = new GiveUserProps();
                giveUserProps.setTransNo(transNo);
                giveUserProps.setUid(uid);
                giveUserProps.setGiveUid(giveUid);
                giveUserProps.setCoverValue(coverValue);
                giveUserProps.setNumber(number);
                giveUserProps.setPropsType(PropsTypeEnum.NECTAR.getType());
                giveUserProps.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
                int insertResult = propsMapper.saveGiveUserProps(giveUserProps);
                if (insertResult != 1) {
                    throw new CustomException("用户赠送蜜罐时,增加被赠送人的赠送记录表发生异常.");
                }
            } else {
                throw new CustomException("用户赠送蜜罐时,扣除用户的花蜜总数时发生失败.");
            }
        } else {
            throw new CustomException("用户赠送蜜罐时插入赠送的记录发送失败.");
        }
        logger.info("交易号:" + transNo + ",当前用户uid:" + uid + "结束赠送花蜜，赠送的用户是:" + giveUid + ",赠送的数量是" + number + ",花蜜转换的配置是:" + coverValue + "");
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCode.SUCCESS_STATUS.getStatus());
        result.setMessage("用户" + uid + "赠送" + giveUid + "用户蜜罐成功.");
        return result;
    }

    /**
     * 领取用户赠送的蜜罐
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public ResultList receiveUserGiveNectary(String transNo, long uid) {
        logger.info("交易号:" + transNo + ",当前用户" + uid + "开始领取被赠送的蜜罐");
        ResultList result = new ResultList();
        if (StringUtils.isBlank(transNo)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("交易消费号不能为空");
            return result;
        }
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("赠送用户ID不能为空");
            return result;
        }
        //查询当前用户是否有未领取的道具红包
        List<GiveUserProps> giveUserPropses = propsMapper.listGiveUserPropsRecord(uid, PropsTypeEnum.NECTAR.getType());
        if (giveUserPropses == null || giveUserPropses.size() == 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.GIVE_REDPACKAGE_FINISH_STATUS.getStatus());
            result.setMessage("当前用户没有赠送的记录");
            return result;
        }
        List<Map<String, String>> values = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        for (GiveUserProps giveUserProps : giveUserPropses) {
            Map<String, String> map = new HashMap<>();
            map.put("transNo", giveUserProps.getTransNo());
            map.put("number", giveUserProps.getNumber() + "");
            map.put("coverValue", giveUserProps.getCoverValue() + "");
            values.add(map);
            ids.add(giveUserProps.getId());
        }
        //更新用户赠送的道具状态为已领取
        int upateResult = propsMapper.updateUserGiveStatus(ids, transNo, PropsTypeEnum.NECTAR.getType(), DateUtil.getNow(DateUtil.Y_M_D_HMS));

        if (upateResult == giveUserPropses.size()) {
            //增加当前用户的花蜜
            //首先增加道具记录表
            Props props = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.NECTAR.getType(), PropsSourceEnum.NECTAR_EVERY_DAY.getSourceType());
            double currentNumber = props.getNumber();
            double total = 0;


            int givetotal = 0;
            int giveNumber = 0;
            double propsCurrentNumber = 0;
            double propsQnumber = 0;
            List<PropsRecord> propsRecords = new ArrayList<>();
            int i = 0;
            for (GiveUserProps giveUserProps : giveUserPropses) {
                givetotal = givetotal + giveUserProps.getCoverValue() * giveUserProps.getNumber();
                giveNumber = giveNumber + giveUserProps.getNumber();
                if (i == 0) {
                    propsCurrentNumber = propsCurrentNumber + currentNumber;
                    propsQnumber = propsCurrentNumber + giveUserProps.getCoverValue() * giveUserProps.getNumber();
                } else {
                    propsCurrentNumber = propsQnumber;
                    propsQnumber = propsCurrentNumber + giveUserProps.getCoverValue() * giveUserProps.getNumber();
                }
                i = i + 1;
                PropsRecord propsRecord = this.savePropsRecords(uid, giveUserProps.getTransNo(), idWorker.nextId() + "", PropsChannelEnum.GET_NECTARY.getChannel(),
                        PropsOperationTypeEnum.ADD.getType(),
                        giveUserProps.getCoverValue() * giveUserProps.getNumber(), PropsTypeEnum.NECTAR.getType(), props.getId(), propsCurrentNumber, propsQnumber,
                        giveUserProps.getNumber(),
                        giveUserProps.getUid(), "当前用户" + uid + "领取赠送的花蜜道具,领取的数量是:" + giveUserProps.getCoverValue() * giveUserProps.getNumber());

                propsCurrentNumber = propsCurrentNumber + currentNumber;
                propsRecords.add(propsRecord);

            }

            int insertRecordResult = propsMapper.batchSavePropsRecord(propsRecords);
//            int insertRecordResult = this.savePropsRecord(uid, transNo, idWorker.nextId() + "", PropsChannelEnum.GET_NECTARY.getChannel(), PropsOperationTypeEnum.ADD.getType(),
//                    givetotal, PropsTypeEnum.NECTAR.getType(), props.getId(), currentNumber, currentNumber + givetotal, giveNumber, 0,
//                    "当前用户" + uid + "领取赠送的花蜜道具,领取的数量是:" + givetotal + "");

            if (insertRecordResult == giveUserPropses.size()) {
                //增加当前用户的花蜜
                int minusResult = propsMapper.addNectaryPropsNumber(uid, PropsSourceEnum.NECTAR_EVERY_DAY.getSourceType(), givetotal, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                if (minusResult != 1) {
                    throw new CustomException("当前用户领取赠送的花蜜,增加用户的花蜜数量发生失败");
                }
            } else {
                throw new CustomException("当前用户领取赠送的花蜜,领取失败.当前用户插入的道具记录表返回的值和用户赠送的记录不对应.");
            }
        } else {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.GIVE_REDPACKAGE_FINISH_STATUS.getStatus());
            result.setMessage("当前用户没有赠送的记录");
            return result;
        }
        logger.info("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的蜜罐结束.");
        result.setValues(values);
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCodeEnum.SUCCESS_STATUS.getStatus());
        result.setMessage("当前用户领取赠送的花蜜成功");
        return result;
    }


    /**
     * 领取用户赠送的红包
     *
     * @param transNo 交易号
     * @param uid     用户ID
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public ResultList receiveUserGiveRedpackage(String transNo, long uid, List<String> trandsNoList) {
        logger.info("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包.,领取的红包交易号:" + trandsNoList);
        ResultList result = new ResultList();
        if (StringUtils.isBlank(transNo)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("交易消费号不能为空");
            return result;
        }
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("赠送用户ID不能为空");
            return result;
        }
        if (trandsNoList == null || trandsNoList.size() == 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("领取的交易号集合不能为空.");
            return result;
        }
        if (checkUserAccountBridStatus(uid)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.WALLET_LOCK.getStatus());
            result.setMessage("当前用户的钱包被锁定");
            return result;
        }
        List<PropsRedpackage> propsRedpackages = propsMapper.selectRedpackageByTransNos(trandsNoList);

        if (propsRedpackages == null || propsRedpackages.size() == 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("未查到当前用户有需要领取红包的记录,请检查传入的参数是正确");
            return result;
        }
        List<Long> oldids = new ArrayList<>();
        List<Long> newids = new ArrayList<>();
        List<Map<String, String>> values = new ArrayList<>();
        List<PropsRedpackageRecord> redpackageRecords = new ArrayList<>();
        List<Long> updateRecordIds = new ArrayList<>();

        double total = 0;
        for (PropsRedpackage r : propsRedpackages) {
            Map<String, String> map = new HashMap<>();
            map.put("transNo", r.getTransNo());
            if (r.getType() != ManorPropsRedpackageTypeEnum.FRIENDS.getType()) {
                map.put("status", "10003");//不属于园又赠送的红包类型
            } else {
                if (r.getStatus() == 1) {//未领取
                    //2017/08/02改变了园又赠送红包的接口。要兼容以前的版本
                    if (r.getGiveUid() > 0) {//旧版本
                        oldids.add(r.getId());
                        map.put("status", "10001");//本次领取
                        total = total + r.getSingleRedpackageAmount();
                        PropsRedpackageRecord propsRedpackageRecord = new PropsRedpackageRecord();
                        propsRedpackageRecord.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        propsRedpackageRecord.setUpdateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        propsRedpackageRecord.setAmount(r.getSingleRedpackageAmount());
                        propsRedpackageRecord.setGiveUid(uid);
                        propsRedpackageRecord.setRedpackageId(r.getId());
                        redpackageRecords.add(propsRedpackageRecord);
                    } else {//新版本
                        //根据红包ID和赠送人ID查询当前用户是否已经领取过红包
                        // PropsRedpackageGiveRecord propsRedpackageGiveRecord = propsMapper.getUserReceiveFriendsTypeRedpackage(r.getId(), uid);
                        PropsRedpackageRecord record = propsMapper.getGiveUserRepackage(r.getId(), uid);
                        if (record != null) {
                            if (record.getStatus() == 1) {
                                newids.add(r.getId());
                                updateRecordIds.add(record.getId());
                                map.put("status", "10001");//本次领取
                                total = total + record.getAmount();
                            } else if (record.getStatus() == 2) {
                                map.put("status", "10002");//之前就领取过
                            }
                        }
                    }
                } else if (r.getStatus() == 2) {//已领取
                    map.put("status", "10002");//之前就领取过
                } else if (r.getStatus() == 3) {
                    map.put("status", "10004");//红包已经退回，不能再进行领取
                }
            }
            values.add(map);
        }

        if (oldids.size() == 0 && newids.size() == 0) {
            logger.info("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包.返回的vaues是:" + JSON.toJSONString(values));
            result.setValues(values);
            result.setCode(ResultCodeEnum.SUCCESS.status());
            result.setMessage("当前用户" + uid + "领取用户赠送的红包.");
            return result;
        }
        if (oldids.size() > 0) {
            int updateResult = propsMapper.updateRedpackageGiveStatus(oldids, uid);
            if (updateResult != oldids.size()) {
                logger.error("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),更新红包发送表失败,updateResult:" + updateResult);
                throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),更新红包发送表失败,updateResult:" + updateResult);
            }
        }
        if (newids.size() > 0) {
            int updateRedpackageResult = propsMapper.batchUpdateRedpackage(newids);
            if (updateRedpackageResult != newids.size()) {
                logger.error("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),更新红包发送表失败,updateResult:" + updateRedpackageResult);
                throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),更新红包发送表失败,updateResult:" + updateRedpackageResult);
            }
        }

        if (redpackageRecords.size() > 0) {
            int insertResult = propsMapper.batchInsertPropsRedpackageRecord(redpackageRecords);
            if (insertResult != redpackageRecords.size()) {
                logger.error("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),增加用户领取红包的操作记录失败,增加的领取记录数量:" + insertResult);
                throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),增加用户领取红包的操作记录失败,增加的领取记录数量:" + insertResult);
            }
        }
        if (updateRecordIds.size() > 0) {
            //更新红包领取状态为已领取
            int updateRecordStatusResult = propsMapper.updateRedpackageRecordStatus(updateRecordIds, DateUtil.getNow(DateUtil.Y_M_D_HMS));
            if (updateRecordStatusResult != updateRecordIds.size()) {
                logger.error("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型).更新红包记录表状态失败,updateRecordStatusResult=" + updateRecordStatusResult);
                throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型).更新红包记录表状态失败,updateRecordStatusResult=" + updateRecordStatusResult);
            }
        }
        //扣除用户领取的手续费
        double serviceCharge = total * (manorRepackageServiceCharge / 100);

        //领取的鸟币增加到用户的账户中
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid + "");
        map.put("rtype", "32");//庄园发鸟币红包
        map.put("option", "0");
        map.put("zbalance", XmnUtils.formatDouble(XmnUtils.sub(total, serviceCharge), 2) + "");
        try {
            ResponseData result1 = liveWalletService.liveWalletOption(map);
            if (result1.getState() == 0) {
                logger.info(uid + "领取所有的红包金额,增加用户" + uid + "的鸟币发生成功,增加的数量是:" + total);
            } else {
                logger.info("增加用户的鸟币发生失败.异常信息:" + JSON.toJSONString(result1));
                throw new CustomException("增加用户的鸟币发生失败.");
            }
        } catch (FailureException e) {
            logger.info("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),增加用户的鸟币发生失败.");
            throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),增加用户的鸟币发生失败.");
        } catch (TException e) {
            logger.info("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),增加用户的鸟币发生失败.");
            throw new CustomException("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包（园又赠送类型),增加用户的鸟币发生失败.");
        }


        logger.info("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包.返回的vaues是:" + JSON.toJSONString(values));
        result.setValues(values);
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setMessage("当前用户" + uid + "领取用户赠送的红包.");
        return result;
    }

    /**
     * 领取用户赠送的红包
     *
     * @param transNo 交易号
     * @param uid     用户ID
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public ResultList receiveUserGiveRedpackage(String transNo, long uid) {
        logger.info("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包.");
        ResultList result = new ResultList();
        if (StringUtils.isBlank(transNo)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("交易消费号不能为空");
            return result;
        }
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("赠送用户ID不能为空");
            return result;
        }
        //查询当前用户所有赠送的红包.
        List<PropsRedpackage> propsRedpackages = propsMapper.listUserGiveRedpackages(uid);
        if (propsRedpackages == null || propsRedpackages.size() == 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.GIVE_REDPACKAGE_FINISH_STATUS.getStatus());
            result.setMessage("当前用户没有未领取的红包");
            return result;
        }
        if (checkUserAccountBridStatus(uid)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.WALLET_LOCK.getStatus());
            result.setMessage("当前用户的钱包被锁定");
            return result;
        }
        //开始领取红包
        //1.更新红包的状态表为已领取
        List<Long> ids = new ArrayList<>();
        double total = 0;
        List<PropsRedpackageRecord> redpackageRecords = new ArrayList<>();
        List<Map<String, String>> values = new ArrayList<>();
        for (PropsRedpackage propsRedpackage : propsRedpackages) {
            Map<String, String> value = new HashMap<>();
            ids.add(propsRedpackage.getId());
            total = total + propsRedpackage.getSingleRedpackageAmount();

            PropsRedpackageRecord propsRedpackageRecord = new PropsRedpackageRecord();
            propsRedpackageRecord.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
            propsRedpackageRecord.setAmount(propsRedpackage.getSingleRedpackageAmount());
            propsRedpackageRecord.setGiveUid(uid);
            propsRedpackageRecord.setRedpackageId(propsRedpackage.getId());
            redpackageRecords.add(propsRedpackageRecord);
            value.put("transNo", propsRedpackage.getTransNo());
            value.put("redpackageId", propsRedpackage.getId() + "");
            values.add(value);
        }

        int updateResult = propsMapper.updateRedpackageGiveStatus(ids, uid);
        if (updateResult == propsRedpackages.size()) {
            int insertResult = propsMapper.batchInsertPropsRedpackageRecord(redpackageRecords);
            if (insertResult != updateResult) {
                logger.error("用户领取所有红包失败,增加用户领取红包的操作记录失败,增加的领取记录数量:" + insertResult + ",更新的数据量是:" + updateResult);
                throw new CustomException("用户领取所有红包失败,增加用户领取红包的操作记录失败,增加的领取记录数量:" + insertResult + ",更新的数据量是:" + updateResult);
            }

            //领取的鸟币增加到用户的账户中
            Map<String, String> map = new HashMap<>();
            map.put("uid", uid + "");
            map.put("rtype", "32");//庄园发鸟币红包
            map.put("option", "0");
            map.put("zbalance", total + "");
            try {
                ResponseData result1 = liveWalletService.liveWalletOption(map);
                if (result1.getState() == 0) {
                    logger.info(uid + "领取所有的红包金额,增加用户" + uid + "的鸟币发生成功,增加的数量是:" + total);
                } else {
                    logger.info("增加用户的鸟币发生失败.异常信息:" + JSON.toJSONString(result1));
                    throw new CustomException("增加用户的鸟币发生失败.");
                }

            } catch (FailureException e) {
                logger.info("增加用户的鸟币发生失败.");
                throw new CustomException("增加用户的鸟币发生失败.");
            } catch (TException e) {
                e.printStackTrace();
            }
        } else {
            throw new CustomException("更新被赠送的红包的状态失败,更新红包的记录数是:" + updateResult + ",查询出来的记录数是:" + propsRedpackages.size());
        }
        logger.info("交易号:" + transNo + ",当前用户" + uid + "领取被赠送的红包.返回的vaues是:" + JSON.toJSONString(values));
        result.setValues(values);
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setMessage("当前用户" + uid + "领取用户赠送的红包.");
        return result;
    }


    /**
     * 获取当前用户的红包历史记录
     *
     * @param uid 用户ID
     */
    public ResultList listUserPropsRedpackageHistoryByUid(long uid, int offset, int limit) {
        ResultList result = new ResultList();
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("操作的用户id不能为空.请输入正确值");
            return result;
        }
        //根据用户ID获取当前用户的红包历史记录
        List<PropsRedpackage> propsRedpackages = propsMapper.listUserPropsRedpackageHistoryByUid(uid, offset, limit);
        List<Map<String, String>> values = new ArrayList<>();
        if (propsRedpackages != null && propsRedpackages.size() > 0) {
            for (PropsRedpackage record : propsRedpackages) {
                Map<String, String> value = MapBeanUtil.convertMap(record, "id", "transNo", "uid", "type", "number", "amount",
                        "singleRedpackageAmount", "status", "currentGetNumber", "createTime", "exprieTime");
                values.add(value);
            }
        }
        result.setValues(values);
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCodeEnum.SUCCESS_STATUS.getStatus());
        result.setMessage("根据该用户ID获取当前用户发送的红包记录");
        return result;
    }

    /**
     * 根据发送的红包ID获取领取的用户记录
     *
     * @param redpackageId 红包ID
     */
    public ResultList listPropsRedpackageRecordByRedpackageId(long redpackageId, long uid) {
        ResultList result = new ResultList();
        if (redpackageId == 0 || redpackageId < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("红包ID不能为空.请输入正确值.");
            return result;
        }
        logger.info("根据红包ID" + redpackageId + ",查询红包的领取记录。");
        PropsRedpackage propsRedpackage = propsMapper.getRedpackage(redpackageId);
        if (propsRedpackage.getUid() != uid) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_NOT_MATCH.getStatus());
            result.setMessage("红包的用户ID和传入的参数ID不匹配.");
            return result;
        }
        List<PropsRedpackageRecord> propsRedpackageRecords = propsMapper.listPropsRedpackageRecordByRedpackageId(redpackageId);

        List<Map<String, String>> values = new ArrayList<>();
        if (propsRedpackageRecords != null && propsRedpackageRecords.size() > 0) {
            for (PropsRedpackageRecord record : propsRedpackageRecords) {
                Map<String, String> value = MapBeanUtil.convertMap(record, "id", "redpackageId", "giveUid", "amount", "createTime", "status");
                values.add(value);

            }
            result.setValues(values);
        } else {
            result.setValues(new ArrayList<Map<String, String>>());
        }
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCodeEnum.SUCCESS_STATUS.getStatus());
        StringBuffer resultMessage = new StringBuffer(propsRedpackage.getNumber() + "个红包共");
        if (propsRedpackage.getType() == 1) {
            resultMessage.append(propsRedpackage.getAmount() + "鸟币,");
        } else {
            resultMessage.append(propsRedpackage.getNumber() * propsRedpackage.getSingleRedpackageAmount() + "鸟币,");
        }

        if (propsRedpackage.getStatus() == RedPackagTypeStatusEnum.FINISH.getStatus()) {
            resultMessage.append("已领完");
        } else if (propsRedpackage.getStatus() == RedPackagTypeStatusEnum.NOT.getStatus()) {
            resultMessage.append("剩余" + (propsRedpackage.getNumber() - propsRedpackage.getCurrentGetNumber()) + "个未领");
        } else if (propsRedpackage.getStatus() == RedPackagTypeStatusEnum.RETURN.getStatus()) {
            //随机的要计算一共领取了多少个红包。然后用总数减去已领取的
            if (propsRedpackage.getType() == 1) {
                Double surplusAmount = 0d;
                Double sumAmount = propsMapper.getAllReceiveRedpackageMoneyByRedpackageId(propsRedpackage.getId());
                if (sumAmount == null) {
                    surplusAmount = propsRedpackage.getAmount();
                } else {
                    surplusAmount = XmnUtils.sub(propsRedpackage.getAmount(), sumAmount);
                }
                resultMessage.append(surplusAmount + "鸟币退回账号中");
            } else {
                resultMessage.append((propsRedpackage.getNumber() - propsRedpackage.getCurrentGetNumber()) * propsRedpackage.getSingleRedpackageAmount() + "鸟币退回账号中");
            }
        }

        result.setMessage(resultMessage.toString());
        return result;
    }

    /**
     * 用户领取红包ID
     *
     * @param uid 用户ID
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public Result getPropsRedpackage(long redpackageId, long uid) {
        logger.info("当前用户UID:" + uid + ",开始领取红包,红包ID是:" + redpackageId);
        Result result = new Result();
        if (redpackageId == 0 || redpackageId < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("请输入正确的红包ID,请输入正确值.");
            return result;
        }
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("请输入正确的用户ID,请输入正确值");
            return result;
        }

        //判断当前用户是否已经领取过红包
        PropsRedpackageRecord record = propsMapper.getUserRedpacageRecord(redpackageId, uid);
        if (record != null) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_GIVE_STAUTS.getStatus());
            result.setMessage("已经领取过红包，请勿重复领取");
            return result;
        }
        //判断当前的红包是否过期
        PropsRedpackage redpackage = propsMapper.getRedpackage(redpackageId);
        if (redpackage == null) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.NOT_FOUND_STATUS.getStatus());
            result.setMessage("红包数据不存在");
            return result;
        }
        if (redpackage.getStatus() == 2) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.GIVE_REDPACKAGE_FINISH_STATUS.getStatus());
            result.setMessage("红包已经领取完毕.");
            return result;
        }
        if (DateUtil.compare_date(redpackage.getExprieTime(), DateUtil.getNow(DateUtil.Y_M_D_HMS)) != 1) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.EXPRIE_TIME_STATUS.getStatus());
            result.setMessage("红包时间已过期,已经无法领取");
            return result;
        }
        if (checkUserAccountBridStatus(uid)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.WALLET_LOCK.getStatus());
            result.setMessage("当前用户的钱包被锁定");
            return result;
        }
        double amount = 0;
        //如果红包类型是随机的
        if (redpackage.getType() == ManorPropsRedpackageTypeEnum.RANDOM.getType()) {
            //从未消费的队列中取出金额
            ListOperations<String, String> redpackageRedis = redisTemplate.opsForList();
            String amoutStr = redpackageRedis.rightPop(REDIS_REPAKCAGE_NOT_CONSUME + redpackage.getId());
            amount = Double.parseDouble(amoutStr);
        } else {
            amount = redpackage.getSingleRedpackageAmount();
        }

        //扣除手续费
        double serviceCharge = amount * (manorRepackageServiceCharge / 100);
        amount = XmnUtils.formatDouble(XmnUtils.sub(amount, serviceCharge), 2);
        //用户领取红包步骤，1.增加领取记录 2.更新红包表数据  3.对当前用户增加道具消费记录 4.增加用户的账户记录表
        PropsRedpackageRecord propsRedpackageRecord = new PropsRedpackageRecord();
        propsRedpackageRecord.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
        propsRedpackageRecord.setAmount(amount);
        propsRedpackageRecord.setGiveUid(uid);
        propsRedpackageRecord.setRedpackageId(redpackageId);
        int saveRedpackageReord = propsMapper.insertPropsRedpackageRecord(propsRedpackageRecord);
        if (saveRedpackageReord == 1) {
            //更新红包表的状态
            //赠送园又类型

            int updateRedpackage = propsMapper.updateRedpackage(redpackageId);
            if (updateRedpackage == 1) {
                //增加用户的钱包
                //扣除当前用户的鸟币
                Map<String, String> map = new HashMap<>();
                map.put("uid", uid + "");
                map.put("rtype", "32");//庄园发鸟币红包
                map.put("option", "0");
                map.put("zbalance", amount + "");
                try {
                    ResponseData result1 = liveWalletService.liveWalletOption(map);
                    if (result1.getState() == 0) {
                        logger.info("当前用户领取红包成功.增加当前用户" + uid + "的鸟币成功.鸟币的金额是" + amount);
                    } else {
                        logger.info("当前用户领取红包失败.异常信息如下" + result1.getMsg());
                        throw new CustomException("当前用户领取红包失败.异常信息如下" + result1.getMsg());
                    }
                } catch (TException e) {
                    e.printStackTrace();
                }
            } else {
                logger.error("" + uid + "用户领取红包失败,更新红包表的状态失败");
                throw new CustomException("当前用户领取红包失败，更新红包表的状态失败");
            }
        } else {
            logger.error("当前用户" + uid + "领取红包失败,插入领取的红包记录发生失败.");
            throw new CustomException("当前用户领取红包失败,插入领取的红包记录发生失败.");
        }

        Map<String, String> values = new HashMap<>();
        values.put("amount", amount + "");

        logger.info("当前用户UID:" + uid + ",领取红包结束,红包ID是:" + redpackageId + ",领取的金额是:" + amount);
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCodeEnum.SUCCESS_STATUS.getStatus());
        result.setValues(values);
        result.setMessage("用户领取红包成功.");
        return result;
    }


    /**
     * 发送鸟币红包
     *
     * @param uid    用户ID
     * @param amount 单个红包额度
     * @number 红包数量
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public Result sendPropsRedpackage(String transNo, long uid, List<Long> giveUids, int number, double amount, int type, String phone) {
        logger.info("交易号:" + transNo + ",当前用户UID:" + uid + "，开始发送鸟币红包，红包的数量:" + number + ",发送的金额:" + amount + ",type类型是:" + type + ",giveUids:" + giveUids);
        Result result = new Result();
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("用户ID参数错误.");
            return result;
        }
        if (number == 0 || number < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("请输入正确的红包数量.");
            return result;
        }
        if (amount == 0 || amount < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("请输入正确的红包金额.");
            return result;
        }
        if (StringUtils.isBlank(transNo)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("请输入正确的消费交易号.");
            return result;
        }

        if (type == ManorPropsRedpackageTypeEnum.FRIENDS.getType()) {
            if (giveUids == null || giveUids.size() <= 0) {
                result.setCode(ResultCodeEnum.ERROR.status());
                result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
                result.setMessage("请输入赠送人的UID.");
                return result;
            }
        }
        //根据交易号查询当前用户是否已经发放过
        if (propsMapper.countRedpackageByTransNoAndUid(uid, transNo) > 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_COMMIT_STATUS.getStatus());
            result.setMessage("交易批次号(transNo)重复提交,不能重复发送红包");
            return result;
        }
        double sendRedpacketAmount = 0;
        //随机
        if (type == ManorPropsRedpackageTypeEnum.RANDOM.getType()) {
            sendRedpacketAmount = amount;
        } else if (type == ManorPropsRedpackageTypeEnum.FIXED.getType()) {
            sendRedpacketAmount = amount * number;
        } else if (type == ManorPropsRedpackageTypeEnum.FRIENDS.getType()) {
            sendRedpacketAmount = amount * number;
        }

        Map<String, String> wallet = new HashMap<>();
        wallet.put("uid", uid + "");

        //double serviceCharge = amount * number * (manorRepackageServiceCharge / 100);
        try {
            ResponseData responseData = liveWalletService.getLiveWallet(wallet);
            if (responseData.getState() == 0) {
                Map<String, String> resultMap = responseData.getResultMap();
                String status = resultMap.get("status");
                if ("2".equals(status)) {
                    result.setCode(ResultCodeEnum.ERROR.status());
                    result.setStatusCode(BusinessStatusCodeEnum.WALLET_LOCK.getStatus());
                    result.setMessage("当前用户的钱包被锁定");
                    return result;
                }
                String zbalance = resultMap.get("zbalance");

                //logger.info("当前手续费的金额是:" + serviceCharge + ",手续费比例是:" + manorRepackageServiceCharge);
                if (Double.parseDouble(zbalance) < sendRedpacketAmount) {
                    result.setCode(ResultCodeEnum.ERROR.status());
                    result.setStatusCode(BusinessStatusCodeEnum.AMONUT_NOT_ENOUGH_STATUS.getStatus());
                    result.setMessage("当前用户的钱包金额不足");
                    return result;
                }
                //限制消费
                String restrictive = resultMap.get("restrictive");
                if ("002".equals(restrictive)) {
                    String limitBalance = resultMap.get("limitBalance");//消费限制
                    Double miunBalance = XmnUtils.formatDouble2(Double.parseDouble(zbalance) - Double.parseDouble(limitBalance));
                    if (miunBalance < sendRedpacketAmount) {
                        result.setCode(ResultCodeEnum.ERROR.status());
                        result.setStatusCode(BusinessStatusCodeEnum.ACCOUNT_LIMIT_CONSUM.getStatus());
                        result.setMessage("当前用户的钱包已限制消费,请联系客服400-7766-333");
                        return result;
                    }
                }
            } else {
                logger.error("获取用户的钱包信息发生异常,异常信息如下:" + responseData.getMsg());
            }

        } catch (TException e) {
            e.printStackTrace();
        }
        Map<String, String> values = new HashMap<>();

        PropsRedpackage propsRedpackage = new PropsRedpackage();
        propsRedpackage.setTransNo(transNo);//交易号
        propsRedpackage.setUid(uid);//发送红包的用户ID
        propsRedpackage.setServiceChargeProfit(manorRepackageServiceCharge);
        propsRedpackage.setType(type);//红包类型

        propsRedpackage.setPhone(phone);
        if (type == ManorPropsRedpackageTypeEnum.RANDOM.getType()) {
            propsRedpackage.setAmount(amount);
        } else {
            propsRedpackage.setSingleRedpackageAmount(amount);//单个红包额度
        }
        propsRedpackage.setNumber(number);

        propsRedpackage.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));//创建时间
        propsRedpackage.setExprieTime(DateUtil.getAddHoursafterDate(DateUtil.getNow(DateUtil.Y_M_D_HMS), 24, DateUtil.Y_M_D_HMS));//过期时间 24小时之后
        int insertResult = propsMapper.saveRedpackage(propsRedpackage);
        if (insertResult != 1) {
            logger.error("交易号:" + transNo + ",当前用户UID:" + uid + "，红包的数量:" + number + ",发送的金额:" + amount + ",type类型是:" + type + ",,插入红包发送表失败");
            throw new CustomException("交易号:" + transNo + ",当前用户UID:" + uid + "，红包的数量:" + number + ",发送的金额:" + amount + ",type类型是:" + type + ",插入红包发送表失败");
        } else {
            //如果是园又的类型就将赠送的园又ID记录到记录表中
            if (type == ManorPropsRedpackageTypeEnum.FRIENDS.getType()) {
                List<PropsRedpackageRecord> records = new ArrayList<>();
                for (long guid : giveUids) {
//                    PropsRedpackageGiveRecord propsRedpackageGiveRecord = new PropsRedpackageGiveRecord();
//                    propsRedpackageGiveRecord.setAmount(amount);
//                    propsRedpackageGiveRecord.setRedpackageId(propsRedpackage.getId());
//                    propsRedpackageGiveRecord.setGiveUid(guid);
//                    propsRedpackageGiveRecord.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
//                    giveRecords.add(propsRedpackageGiveRecord);
                    //

                    PropsRedpackageRecord propsRedpackageRecord = new PropsRedpackageRecord();
                    propsRedpackageRecord.setStatus(1);
                    propsRedpackageRecord.setGiveUid(guid);
                    propsRedpackageRecord.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
                    propsRedpackageRecord.setAmount(amount);
                    propsRedpackageRecord.setRedpackageId(propsRedpackage.getId());
                    records.add(propsRedpackageRecord);
                }
                int insertGiveRecordResult = propsMapper.batchInsertPropsRedpackageRecord(records);
                if (insertGiveRecordResult != giveUids.size()) {
                    logger.error("交易号:" + transNo + ",当前用户UID:" + uid + "，红包的数量:" + number + ",发送的金额:" + amount + ",type类型园又赠送类型,,插入红包记录表失败");
                    throw new CustomException("交易号:" + transNo + ",当前用户UID:" + uid + "，红包的数量:" + number + ",发送的金额:" + amount + ",type类型园又赠送类型,,插入红包记录表失败");
                }
            }
        }
        values.put("redpackageId", propsRedpackage.getId() + "");
        //扣除当前用户的鸟币
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid + "");
        map.put("rtype", "31");//庄园发鸟币红包
        map.put("option", "1");
        map.put("zbalance", sendRedpacketAmount + "");
        try {
            ResponseData result1 = liveWalletService.liveWalletOption(map);
            if (result1.getState() == 0) {
                logger.info("扣除当前用户的鸟币成功.扣除的金额是" + sendRedpacketAmount);
            } else {
                logger.info("扣除当前用户的鸟币失败.异常信息如下" + result1.getMsg());
                throw new CustomException("扣除当前用户的鸟币失败.异常信息如下" + result1.getMsg());
            }
        } catch (TException e) {
            e.printStackTrace();
        }

        /**
         * 如果红包类型是随机的。 则预先生成对应的随机的红包个数放到redis中.
         */
        if (type == ManorPropsRedpackageTypeEnum.RANDOM.getType()) {
            //预选生成对应的红包个数金额放到redis中.
            double[] redpakcageAmount = XmnUtils.getMoney(amount, number);
            ListOperations<String, String> notConsumeRedpacket = redisTemplate.opsForList();
            //notConsumeRedpacket.leftPush(REDIS_REPAKCAGE_NOT_CONSUME+propsRedpackage.getId(),redpakcageAmount);
            for (double r : redpakcageAmount) {
                try {
                    notConsumeRedpacket.leftPush(REDIS_REPAKCAGE_NOT_CONSUME + propsRedpackage.getId(), String.valueOf(r));
                } catch (Exception e) {
                    logger.error("交易号:" + transNo + ",当前用户UID:" + uid + "发送红包,往redis中初始化红包金额数据发生失败.异常信息如下:" + e);
                    throw new CustomException("交易号:" + transNo + ",当前用户UID:" + uid + "发送红包,往redis中初始化红包金额数据发生失败.异常信息如下:" + e);
                }
            }
        }
        logger.info("交易号:" + transNo + ",当前用户UID:" + uid + "，发送鸟币红包结束，红包的数量:" + number + ",发送的金额:" + amount + ",type类型是:" + type);
        result.setStatusCode(BusinessStatusCodeEnum.SUCCESS_STATUS.getStatus());
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setValues(values);
        return result;
    }

    /**
     * 获取所有过期的红包
     */
    public List<PropsRedpackage> getUserExprieRedpackage() {
        return propsMapper.listExpriePropsRepackage(DateUtil.getNow(DateUtil.Y_M_D_HMS));
    }

    /**
     * 查询当前的账号是否可用
     */
    private boolean checkUserAccountBridStatus(long uid) {
        Map<String, String> wallet = new HashMap<>();
        wallet.put("uid", uid + "");
        ResponseData responseData = null;
        try {
            responseData = liveWalletService.getLiveWallet(wallet);
            if (responseData.getState() == 0) {
                Map<String, String> resultMap = responseData.getResultMap();
                String status = resultMap.get("status");
                if ("2".equals(status)) {
                    return true;
                }
            }
        } catch (TException e) {
            logger.error("查询当前用户的账号出错,异常信息如下:" + e);
            throw new CustomException("查询当前用户的账号出错");
        }
        return false;
    }

    /**
     * 退回用户红包
     *
     * @param redpackageId        红包ID
     * @param uid                 用户ID
     * @param singleAmount        单个红包金额
     * @param serviceChargeProfit 手续费
     * @param totalNumber         红包总数
     * @param qhum                剩余的红包数量
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public Result returnUserSunRedpackage(long redpackageId, long uid, double singleAmount, double serviceChargeProfit, int totalNumber,
                                          int qhum, final String phone, int type,double amount) {
        Result result = new Result();

        Map<String, String> wallet = new HashMap<>();
        wallet.put("uid", uid + "");

        if (checkUserAccountBridStatus(uid)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.WALLET_LOCK.getStatus());
            result.setMessage("当前用户的钱包被锁定");
            return result;
        }

        //1.首先红包的的状态为已退回。2.将剩余的金额退回到用户的账户中.
        int updateResult = propsMapper.updateRedpackageReturnStatus(redpackageId);
        if (updateResult == 1) {
            //退回用户的鸟币红包
            double aAmount = 0;
            if (type == 2) {
                aAmount = singleAmount * qhum;
            } else if (type == 1) {
                 //查询所有已经领取过的红包
                Double sumAmount = propsMapper.getAllReceiveRedpackageMoneyByRedpackageId(redpackageId);
                if (sumAmount == null) {
                    aAmount = amount;
                } else {
                    aAmount = XmnUtils.sub(amount, sumAmount);
                }
            }
            final double returnAmount = aAmount;
            //double profitAmount = XmnUtils.formatDouble(singleAmount / serviceChargeProfit, 3) * qhum;//手续费鸟币红包
            //退回鸟币红包到用户账户上面
            Map<String, String> map = new HashMap<>();
            map.put("uid", uid + "");
            map.put("rtype", "33");//庄园发鸟币红包
            map.put("option", "0");
            map.put("zbalance", (aAmount) + "");
            try {
                ResponseData result1 = liveWalletService.liveWalletOption(map);
                if (result1.getState() == 0) {
                    logger.info("红包:" + redpackageId + "退回鸟币红包成功,退回的金额是:" + (aAmount));
                    cachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            Template template = new Template();
                            template.setPhones(phone);
                            template.setText("您发放鸟币红包过期无人领取，已退回" + returnAmount + "到您的账号中,请注意查收.");
                            SMSResultCode resultCode = SMSUtil.send(xmnSMSURL, template);
                        }
                    });
                } else {
                    logger.info("扣除当前用户的鸟币失败.异常信息如下" + result1.getMsg());
                    throw new CustomException("扣除当前用户的鸟币失败.异常信息如下" + result1.getMsg());
                }
            } catch (TException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("更新用户发送的红包为已退回状态失败.");
            throw new CustomException("更新用户发送的红包为已退回状态失败.");
        }
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setMessage("退回用户的红包成功");
        return result;

    }

    /**
     * 花蜜兑换现金红包
     *
     * @param moneyRedpackageAmount 红包额度
     * @param transNo               交易号 消费交易号
     * @param uid                   用户ID  用户ID
     * @param number                数量    购买的数量
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public Result exchangeMoneyRedpackage(String transNo, long uid, int number, double moneyRedpackageAmount) {
        logger.info("交易号:" + transNo + ",当前的用户ID:" + uid + "开始花蜜兑换鸟币,蜜罐的数量是:" + number + ",红包总金额是:" + moneyRedpackageAmount);
        Result result = new Result();
        if (StringUtils.isBlank(transNo)) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("交易消费号不能为空");
            return result;
        }
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("操作用户ID不能为空");
            return result;
        }
        if (number == 0 || number < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("购买的数量必须大于0.");
            return result;
        }

        //判断当前用户是否已经进行了交易
        if (propsMapper.countPropsRecordByTransNo(transNo) > 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_COMMIT_STATUS.getStatus());
            result.setMessage("交易批次号(transNo)重复提交,请勿重复提交");
            return result;
        }
        logger.info("用户uid:" + uid + "使用花蜜兑换现金红包.交易号:" + transNo + ",兑换的数量:" + number + ",1滴兑换的鸟币金额是:"+moneyRedpackageAmount);
        //查询当前用户的花蜜数量是否足够扣除
        Props props = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.NECTAR.getType(), PropsSourceEnum.NECTAR_EVERY_DAY.getSourceType());
        if (props != null && number > props.getNumber()) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.AMONUT_NOT_ENOUGH_STATUS.getStatus());
            result.setMessage("当前用户:" + uid + "无法兑换现金，花蜜余额不足请及时充值");
            return result;
        }
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        int insertRecordResult = this.savePropsRecord(uid, transNo, idWorker.nextId() + "", PropsChannelEnum.EXCHANGE_MONEY_REDPACKAGE.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                number, PropsTypeEnum.NECTAR.getType(), props.getId(), props.getNumber(), props.getNumber() - number, number, 0,
                "当前用户" + uid + "将" + number + "花蜜数量兑换了"+number * moneyRedpackageAmount +"鸟币");
        if (insertRecordResult == 1) {
            //扣除当前用户的花蜜数量
            int minusResult = propsMapper.minusPropsNumber(uid, PropsTypeEnum.NECTAR.getType(), PropsSourceEnum.NECTAR_EVERY_DAY.getSourceType(),
                    number , DateUtil.getNow(DateUtil.Y_M_D_HMS));
            if (minusResult == 1) {

                Map<String, String> wallet = new HashMap<>();
                wallet.put("uid", uid + "");
                wallet.put("rtype", "34");//庄园发鸟币红包
                wallet.put("option", "0");
                wallet.put("zbalance", (number * moneyRedpackageAmount) + "");
                wallet.put("description", number + "滴花蜜");
                try {
                    logger.info("用户uid:" + uid + "使用花蜜兑换现金红包.交易号:" + transNo + ",往用户的直播钱包转入红包现金,转入的红包金额是:" + number * moneyRedpackageAmount);
                    ResponseData result1 = liveWalletService.liveWalletOption(wallet);
                    if (result1.getState() == 0) {
                        logger.info("增加当前用户的鸟币成功.金额是" + number * moneyRedpackageAmount);
                    } else {
                        logger.error("扣除当前用户的鸟币失败.异常信息如下" + result1.getMsg());
                        throw new CustomException("扣除当前用户的鸟币失败.异常信息如下" + result1.getMsg());
                    }
                } catch (TException e) {
                    logger.error("扣除当前用户的鸟币失败.调取直播接口发生异常.");
                    throw new CustomException("扣除当前用户的鸟币失败.调取直播接口发生异常.");
                }

            } else {
                logger.info("用户uid:" + uid + "使用花蜜兑换现金红包.交易号:" + transNo + ",兑换的数量:" + number + ",扣除花蜜数量失败");
                throw new CustomException("使用花蜜兑换现金红包失败");
            }
        } else {
            throw new CustomException("使用花蜜兑换现金红包失败");
        }
        logger.info("用户uid:" + uid + "使用花蜜兑换现金红包结束.交易号:" + transNo + ",兑换的数量:" + number + ",兑换的鸟币金额是:"+number *moneyRedpackageAmount);
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCodeEnum.SUCCESS_STATUS.getStatus());
        result.setMessage("用户使用花蜜兑换现在红包成功");
        return result;
    }

    /**
     * 用户花蜜的报表
     *
     * @param offset 偏移量
     * @param limit  每页条数
     */

    public ResultList nectaryReport(long uid, int offset, int limit) {
        ResultList result = new ResultList();
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("操作用户ID不能为空");
            return result;
        }
        //获取当前用户的花蜜报表
        List<Map<String, String>> values = new ArrayList<>();
        List<PropsReport> listUserNectaryReports = propsMapper.listUserNectaryReport(uid, offset, limit);
        if (listUserNectaryReports != null && listUserNectaryReports.size() > 0) {
            for (PropsReport propsReport : listUserNectaryReports) {
                Map<String, String> map = MapBeanUtil.convertMap(propsReport, "transNo", "number", "type", "channel", "createTime",
                        "commonUid", "commonNumber");
                values.add(map);
            }
        }
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setValues(values);
        result.setStatusCode(BusinessStatusCode.SUCCESS_STATUS.getStatus());
        result.setMessage("获取当前用户的花蜜报表");
        return result;
    }

    /**
     * 获取所有未完成种花的用户
     */
    public ResultList getAllNotFinishGrowUserByHours(int hours) {
        logger.info("定时任务开始统计所有在" + hours + "外未种完花的用户.");
        ResultList resultList = new ResultList();
        final Set<Long> userIds = new HashSet<>();
//查询一定小时数未种完的种子

        final String miunsTime = DateUtil.miunsHours(hours);
        System.out.println(miunsTime);
        //获取所有未使用
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int offset = 0;
                int page = 0;
                int limit = 20;
                boolean flowerSeedFlag = true;
                while (flowerSeedFlag) {
                    offset = page * limit;
                    List<Long> ids = propsMapper.getAllNotFinishGrowSeedUser(miunsTime, offset, limit);
                    System.out.println("thread1:" + JSON.toJSONString(ids));
                    if (ids == null || ids.size() < limit) {
                        for (Long id : ids) {
                            userIds.add(id);
                        }
                        flowerSeedFlag = false;
                        break;
                    } else {
                        for (Long id : ids) {
                            userIds.add(id);
                        }
                        page = page + 1;
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int offset = 0;
                int page = 0;
                int limit = 20;
                boolean flowerFlag = true;
                while (flowerFlag) {
                    offset = page * limit;
                    List<Long> ids = propsMapper.getAllNotFinishGrowUser(miunsTime, offset, limit);
                    System.out.println("thread2:" + JSON.toJSONString(ids));
                    if (ids == null || ids.size() < limit) {
                        for (Long id : ids) {
                            userIds.add(id);
                        }
                        flowerFlag = false;
                        break;
                    } else {
                        for (Long id : ids) {

                            userIds.add(id);
                        }
                        page = page + 1;
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Map<String, String>> uidsMap = new ArrayList<>();
        if (userIds != null && userIds.size() > 0) {
            for (Long id : userIds) {
                Map map = new HashMap();
                map.put("uid", id + "");
                uidsMap.add(map);
            }
        }


        logger.info("未种完花的用户IDS:" + JSON.toJSONString(uidsMap));
        resultList.setCode(ResultCodeEnum.SUCCESS.status());
        resultList.setStatusCode(BusinessStatusCode.SUCCESS_STATUS.getStatus());
        resultList.setValues(uidsMap);
        resultList.setMessage("获取所有未种完花的用户成功");
        return resultList;

    }

    /**
     * 定时任务种花操作
     *
     * @param transNo 交易号
     * @param hours   未种花苗操作的小时数
     */
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public ResultList timerGrowFlower(String transNo, long uid, int hours) {
        ResultList resultList = new ResultList();
        //自动种花的时候，把用户买的花的时间24小时之内的花全部扣除掉
        logger.info("定时种花任务开始执行,用户ID是" + uid + ",交易号是:" + transNo);
        //判断当前的用户有没有激活过庄园
        //查询当前用户有没有激活过庄园
        List<PropsRecord> propsRecords = propsMapper.listUserPropsRecordByUidAndChannel(uid, PropsChannelEnum.ACTIVE_MANOR.getChannel());
        if (propsRecords == null || propsRecords.size() == 0) {
            resultList.setCode(ResultCodeEnum.ERROR.status());
            resultList.setMessage("当前用户没有激活庄园");
            resultList.setStatusCode(BusinessStatusCodeEnum.NOT_ACTIVE_MANOR.getStatus());
            return resultList;
        }

        //首先查询当前用户的种子是否有24小时没种的
        String miunsTime = DateUtil.miunsHours(hours);
        List<FlowerSeedingGiveRecord> flowerSeedingGiveRecords = propsMapper.getAllGiveFlowerByDate(uid, miunsTime);
        //更新种子的状态为已使用
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        String batchNo = idWorker.nextId() + "";
        List<GrowFlowerRecord> growFlowerRecords = new ArrayList<>();
        if (flowerSeedingGiveRecords != null && flowerSeedingGiveRecords.size() > 0) {
            List<Long> giveFlowerIds = new ArrayList<>();
            for (FlowerSeedingGiveRecord flowerSeedingGiveRecord : flowerSeedingGiveRecords) {
                giveFlowerIds.add(flowerSeedingGiveRecord.getId());
                GrowFlowerRecord growFlowerRecord = new GrowFlowerRecord();
                growFlowerRecord.setType(1);
                growFlowerRecord.setGiveUid(flowerSeedingGiveRecord.getGiveUid());
                growFlowerRecord.setNumber(1);
                growFlowerRecords.add(growFlowerRecord);
            }
            int kGiveFlower = flowerSeedingGiveRecords.size();
            int updateGiveRecords = propsMapper.updateGiveRecordIds(uid, giveFlowerIds, DateUtil.getNow(DateUtil.Y_M_D_HMS));
            if (updateGiveRecords == flowerSeedingGiveRecords.size()) {
                //更新用户的道具表
                Props props = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.FLOWER.getType(), PropsSourceEnum.FLOWER_SEEDING_GIVE.getSourceType());
                int insertCommonEnergyRecordResult = this.savePropsRecord(uid, transNo, batchNo, PropsChannelEnum.GROW_FLOWERS.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                        kGiveFlower, PropsTypeEnum.FLOWER.getType(), props.getId(), props.getNumber(), props.getNumber() - kGiveFlower, 0, 0,
                        "定时任务自动种花,扣除用户赠送的种子数量是:" + kGiveFlower + "");
                if (insertCommonEnergyRecordResult == 1) {
                    System.out.println(props.getId() + ">>>>");
                    int miunsResult = propsMapper.minusPropsNumberById(props.getId(), kGiveFlower, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                    if (miunsResult != 1) {
                        logger.error("定时任务种花时,当前用户" + uid + "扣除用户道具表种子时，扣除失败,miunsResult:." + miunsResult);
                        throw new CustomException("定时任务种花时,当前用户" + uid + "扣除用户道具表种子时，扣除失败,miunsResult:." + miunsResult);
                    }
                } else {
                    logger.error("定时任务种花时,当前用户" + uid + "扣除用户道具表种子时，增加道具操作记录表发生异常,insertCommonEnergyRecordResult:." + insertCommonEnergyRecordResult);
                    throw new CustomException("定时任务种花时,当前用户" + uid + "扣除用户道具表种子时，增加道具操作记录表发生异常,insertCommonEnergyRecordResult:." + insertCommonEnergyRecordResult);
                }
            } else {
                logger.error("定时任务种花时,当前用户" + uid + "扣除用户赠送的种子时发生异常.updateGiveRecords:=" + updateGiveRecords + ",flowerSeedingGiveRecords:=" + flowerSeedingGiveRecords.size());
                throw new CustomException("定时任务种花时,当前用户" + uid + "扣除用户赠送的种子时发生异常.updateGiveRecords:=" + updateGiveRecords + ",flowerSeedingGiveRecords:=" + flowerSeedingGiveRecords.size());
            }
        }

        //扣除用户的普通能量
        Map<String, Double> buyFlowerMaps = new HashMap<>();
        double commonEnergyTotal = 0;
        double goldEnergyTotal = 0;
        List<FlowerRecord> flowerRecords = propsMapper.findFlowerRecordByDate(uid, miunsTime);
        if (flowerRecords != null && flowerRecords.size() > 0) {
            for (FlowerRecord flowerRecord : flowerRecords) {
                //购买的花朵
                if (flowerRecord.getPropsType() == 1) {
                    int monthType = flowerRecord.getMonthType();
                    if (buyFlowerMaps.get(monthType + "") == null) {
                        buyFlowerMaps.put(monthType + "", flowerRecord.getNumber());
                    } else {
                        double number = buyFlowerMaps.get(monthType + "");
                        buyFlowerMaps.put(monthType + "", number + flowerRecord.getNumber());
                    }
                    int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), flowerRecord.getNumber(), DateUtil.getNow(DateUtil.Y_M_D_HMS));
                    if (updateResult != 1) {
                        logger.error("更新用户购买花的操作记录(自己购买的花苗)表失败,更新的花苗记录表的ID是:" + flowerRecord.getId());
                        throw new CustomException("更新用户购买花的操作记录(自己购买的花苗)表失败,更新的花苗记录表的ID是:" + flowerRecord.getId());
                    }
                } else if (flowerRecord.getPropsType() == 3) {//普通能量
                    commonEnergyTotal = commonEnergyTotal + flowerRecord.getNumber();

                } else if (flowerRecord.getPropsType() == 4) {
                    goldEnergyTotal = goldEnergyTotal + flowerRecord.getNumber();

                }
            }
        }

        //扣除能量数
        //if()  commonEnergyTotal + goldEnergyTotal

        if (goldEnergyTotal + commonEnergyTotal > 0) {
            int coverFlower = XmnUtils.floor((goldEnergyTotal + commonEnergyTotal) / energyCoverFlower);
            if (coverFlower > 0) {
                GrowFlowerRecord growFlowerRecord = new GrowFlowerRecord();
                growFlowerRecord.setNumber(coverFlower);
                growFlowerRecord.setType(2);
                growFlowerRecords.add(growFlowerRecord);

                int energyTotal = coverFlower * energyCoverFlower;
                logger.info("用户" + uid + "定时任务自动种花,种花的数量是:" + coverFlower);
                //先扣除普通能量
                double kcommonEnergyTotal = 0;
                double afterEnergyTotal = 0;

                //普通能量
                if (commonEnergyTotal > 0) {
                    if (commonEnergyTotal >= energyTotal) {
                        commonEnergyTotal = energyTotal;
                    }

                    Props commonEnergy = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.COMMON_ENERGY.getType(), COMMON_ENERGY_ADD.getSourceType());
                    logger.info("用户种花操作,开始扣除普通能量,扣除普通量的值是:" + commonEnergyTotal);
                    List<FlowerRecord> commonflowerRecords = propsMapper.findFlowerRecord(uid, PropsTypeEnum.COMMON_ENERGY.getType(), 0, miunsTime);
                    double kFlowerGive = 0;
                    for (FlowerRecord flowerRecord : commonflowerRecords) {
                        if (flowerRecord.getNumber() >= commonEnergyTotal && kFlowerGive == 0) {
                            //直接扣除
                            int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), commonEnergyTotal, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                            if (updateResult != 1) {
                                logger.error("当前操作用户" + uid + "定时任务种花，使用普通能量种花,扣除花苗的记录表数据(普通能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                throw new CustomException("当前操作用户" + uid + "定时任务种花，使用普通能量种花,扣除花苗的记录(普通能量)表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                            }
                            break;
                        } else {
                            kFlowerGive = kFlowerGive + flowerRecord.getNumber();
                            if (commonEnergyTotal - kFlowerGive >= 0) {
                                int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), flowerRecord.getNumber(), DateUtil.getNow(DateUtil.Y_M_D_HMS));
                                if (updateResult != 1) {
                                    logger.error("当前操作用户" + uid + "定时任务种花，使用普通能量种花,扣除花苗的记录表数据(普通能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                    throw new CustomException("当前操作用户" + uid + "定时任务种花，使用普通能量种花,扣除花苗的记录表(普通能量)数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                }
                            }

                            if (commonEnergyTotal < kFlowerGive) {
                                double number = XmnUtils.sub(flowerRecord.getNumber(), XmnUtils.sub(kFlowerGive, commonEnergyTotal));
                                int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), number, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                                if (updateResult != 1) {
                                    logger.error("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据(黄金能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                    throw new CustomException("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据(黄金能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                }
                                break;
                            }
                        }
                    }
                    int insertCommonEnergyRecordResult = this.savePropsRecord(uid, transNo, batchNo, PropsChannelEnum.GROW_FLOWERS.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                            commonEnergyTotal, PropsTypeEnum.COMMON_ENERGY.getType(), commonEnergy.getId(), commonEnergy.getNumber(), commonEnergy.getNumber() - commonEnergyTotal, 0, 0,
                            "当前用户" + uid + "定时任务种花,使用普通能量转换的花苗进行种花，使用转换的能量总数是:" + (commonEnergyTotal));
                    if (insertCommonEnergyRecordResult == 1) {
                        int minusResult = propsMapper.minusPropsNumberById(commonEnergy.getId(), commonEnergyTotal, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (minusResult != 1) {
                            logger.error("当前用户" + uid + "定时任务种花,使用普通能量转换花苗,减少普通能量时，更新普通失败.minusResult:" + minusResult);
                            throw new CustomException("当前用户" + uid + "定时任务种花,使用普通能量转换花苗,减少普通能量时，更新普通失败.minusResult:" + minusResult);
                        }
                    } else {
                        logger.error("当前用户" + uid + "定时任务种花,使用普通能量转换花苗,插入普通能量道具记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
                        throw new CustomException("当前用户" + uid + "定时任务种花,使用普通能量转换花苗,插入普通能量道具记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
                    }
                }

                afterEnergyTotal = XmnUtils.formatDouble2(energyTotal - commonEnergyTotal);
                //扣除黄金能量的值
                //黄金能量
                if (goldEnergyTotal > 0 && afterEnergyTotal > 0) {
                    Props goldEnergy = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.GOLD_ENERGY.getType(), GOLD_ENERGY_ADD.getSourceType());

                    List<FlowerRecord> goldflowerRecords = propsMapper.findFlowerRecord(uid, PropsTypeEnum.GOLD_ENERGY.getType(), 0, miunsTime);
                    double kFlowerGive = 0;
                    for (FlowerRecord flowerRecord : goldflowerRecords) {
                        if (flowerRecord.getNumber() >= afterEnergyTotal && kFlowerGive == 0) {
                            //直接扣除
                            int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), afterEnergyTotal, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                            if (updateResult != 1) {
                                logger.error("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据(黄金能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                throw new CustomException("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录(黄金能量)表数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                            }
                            break;
                        } else {
                            kFlowerGive = kFlowerGive + flowerRecord.getNumber();

                            if (afterEnergyTotal - kFlowerGive >= 0) {

                                int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), flowerRecord.getNumber(), DateUtil.getNow(DateUtil.Y_M_D_HMS));
                                if (updateResult != 1) {
                                    logger.error("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据((黄金能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                    throw new CustomException("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表(黄金能量)数据发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                }
                            }
                            if (afterEnergyTotal < kFlowerGive) {
                                //System.out.println(kFlowerGive - afterEnergyTotal   +">>>>>>>>>...");
                                double number = XmnUtils.sub(flowerRecord.getNumber(), XmnUtils.sub(kFlowerGive, afterEnergyTotal));
                                int updateResult = propsMapper.updateGiveFlowerRecordNumberByUid(flowerRecord.getId(), number, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                                if (updateResult != 1) {
                                    logger.error("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据(黄金能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                    throw new CustomException("当前操作用户" + uid + "使用购买的花苗种花时,扣除花苗的记录表数据(黄金能量)发生异常，updateResult:" + updateResult + ",花苗的记录表ID:" + flowerRecord.getId());
                                }
                                break;
                            }
                        }
                    }

                    logger.info(afterEnergyTotal + "afterEnergyTotal>>.");
                    logger.info("用户种花操作,开始扣除黄金能量,扣除黄金能量的值是:" + afterEnergyTotal);
                    int insertCommonEnergyRecordResult = this.savePropsRecord(uid, transNo, batchNo, PropsChannelEnum.GROW_FLOWERS.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                            afterEnergyTotal, PropsTypeEnum.GOLD_ENERGY.getType(), goldEnergy.getId(), goldEnergy.getNumber(), goldEnergy.getNumber() - afterEnergyTotal, 0, 0,
                            "当前用户" + uid + "定时任务进行种花，使用转换的能量总数是:" + (afterEnergyTotal) + "");
                    if (insertCommonEnergyRecordResult == 1) {
                        int minusResult = propsMapper.minusPropsNumberById(goldEnergy.getId(), afterEnergyTotal, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (minusResult != 1) {
                            logger.error("当前用户" + uid + "定时任务进行种花，使用黄金能量转换花苗,减少黄金能量时，更新普通失败.minusResult:" + minusResult);
                            throw new CustomException("当前用户" + uid + "定时任务进行种花，使用黄金能量转换花苗,减少黄金能量时，更新普通失败.minusResult:" + minusResult);
                        }
                    } else {
                        logger.error("当前用户" + uid + "定时任务进行种花，使用黄金能量转换花苗,插入普通能量道具记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
                        throw new CustomException("当前用户" + uid + "定时任务进行种花，使用黄金能量转换花苗,插入黄金能量道具记录表时发生异常，更新普通失败.insertCommonEnergyRecordResult:" + insertCommonEnergyRecordResult);
                    }
                }
            }
        }

        //扣除道具表的数据
        if (buyFlowerMaps.size() > 0) {
            for (String monthType : buyFlowerMaps.keySet()) {
                //monthtype等于-1，就表示为激活庄园赠送的花
                if (monthType.equals(GIVE_OWN_FLOWER_MONTH_TYPE + "")) {
                    GrowFlowerRecord growFlowerRecord = new GrowFlowerRecord();
                    growFlowerRecord.setNumber(buyFlowerMaps.get(monthType).intValue());
                    growFlowerRecord.setType(4);
                    growFlowerRecords.add(growFlowerRecord);
                    double number = buyFlowerMaps.get(monthType);
                    Props giveOwnFlower = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.FLOWER.getType(), PropsSourceEnum.FLOWER_SEEDIN_GIVE_OWN.getSourceType());
                    int insertCommonEnergyRecordResult = this.savePropsRecord(uid, transNo, batchNo, PropsChannelEnum.GROW_FLOWERS.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                            number, PropsTypeEnum.FLOWER.getType(), giveOwnFlower.getId(), giveOwnFlower.getNumber(), giveOwnFlower.getNumber() - number, 0, 0,
                            "当前用户" + uid + "使用激活庄园赠送的花朵进行种花（定时任务），,使用的花朵数量是:" + number);
                    if (insertCommonEnergyRecordResult == 1) {
                        int minusResult = propsMapper.minusPropsNumberById(giveOwnFlower.getId(), number, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (minusResult != 1) {
                            logger.error("当前用户" + uid + "使用激活庄园赠送的花朵进行种花（定时任务）,更新用户道具表数量失败.minusResult:" + minusResult + ",道具表ID是:" + giveOwnFlower.getId());
                            throw new CustomException("当前用户" + uid + "使使用激活庄园赠送的花朵进行种花（定时任务）,更新用户道具表数量失败.minusResult:" + minusResult + ",道具表ID是:" + giveOwnFlower.getId());
                        }
                    } else {
                        logger.error("当前用户" + uid + "使用激活庄园赠送的花朵进行种花（定时任务）,插入用户的道具记录表发生异常.");
                        throw new CustomException("当前用户" + uid + "使用激活庄园赠送的花朵进行种花（定时任务）,插入用户的道具记录表发生异常.");
                    }
                } else {
                    GrowFlowerRecord growFlowerRecord = new GrowFlowerRecord();
                    growFlowerRecord.setMonth(Integer.parseInt(monthType));
                    growFlowerRecord.setNumber(buyFlowerMaps.get(monthType).intValue());
                    growFlowerRecord.setType(3);
                    growFlowerRecords.add(growFlowerRecord);

                    double number = buyFlowerMaps.get(monthType);
                    Props props = propsMapper.listUserBuyFlower(uid, Integer.parseInt(monthType));
                    //插入记录表
                    int insertCommonEnergyRecordResult = this.savePropsRecord(uid, transNo, batchNo, PropsChannelEnum.GROW_FLOWERS.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                            number, PropsTypeEnum.FLOWER.getType(), props.getId(), props.getNumber(), props.getNumber() - number, 0, 0,
                            "当前用户" + uid + "使用购买的花苗进行种花（定时任务），花的月份类型是:" + props.getFlowerMonthType() + ",使用的花苗数量是:" + number);
                    if (insertCommonEnergyRecordResult == 1) {
                        int minusResult = propsMapper.minusPropsNumberById(props.getId(), number, DateUtil.getNow(DateUtil.Y_M_D_HMS));
                        if (minusResult != 1) {
                            logger.error("当前用户" + uid + "使用购买的花苗进行种花（定时任务）,更新用户道具表数量失败.minusResult:" + minusResult + ",道具表ID是:" + props.getId());
                            throw new CustomException("当前用户" + uid + "使用购买的花苗进行种花（定时任务）,更新用户道具表数量失败.minusResult:" + minusResult + ",道具表ID是:" + props.getId());
                        }
                    } else {
                        logger.error("当前用户" + uid + "使用购买的花苗进行种花（定时任务）,插入用户的道具记录表发生异常.");
                        throw new CustomException("当前用户" + uid + "使用购买的花苗进行种花（定时任务）,插入用户的道具记录表发生异常.");
                    }
                }
            }
        }


        List<Map<String, String>> values1 = new ArrayList<>();
        if (growFlowerRecords.size() > 0) {
            for (GrowFlowerRecord growFlowerRecord : growFlowerRecords) {
                Map<String, String> value = MapBeanUtil.convertMap(growFlowerRecord, "giveUid", "number", "month", "type");
                values1.add(value);
            }
        }
        resultList.setMessage("定时任务扣除用户种花操作成功.");
        resultList.setCode(ResultCodeEnum.SUCCESS.status());
        resultList.setStatusCode(BusinessStatusCode.SUCCESS_STATUS.getStatus());
        resultList.setValues(values1);
        return resultList;
    }

    @Override
    public ResultList listAllNotUseGiveFlower(long uid) {
        logger.info("用户UID:" + uid + ",获取所有未使用完的下级赠送的花苗.");
        ResultList resultList = new ResultList();
        if (uid == 0 || uid < 0) {
            resultList.setCode(ResultCodeEnum.ERROR.status());
            resultList.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            resultList.setMessage("操作用户ID不能为空");
            return resultList;
        }
        List<Map<String, String>> values = new ArrayList<>();
        List<GiveUserProps> giveUserPropses = propsMapper.listAllNotUseGiveFlower(uid);
        if (giveUserPropses != null && giveUserPropses.size() > 0) {
            for (GiveUserProps giveUserProps : giveUserPropses) {
                Map<String, String> value = MapBeanUtil.convertMap(giveUserProps, "id", "uid", "giveUid", "createTime");
                value.put("number", "1");
                values.add(value);
            }
        }
        resultList.setMessage("获取所有用户未种完的花苗集合");
        resultList.setCode(ResultCodeEnum.SUCCESS.status());
        resultList.setStatusCode(BusinessStatusCode.SUCCESS_STATUS.getStatus());
        resultList.setValues(values);

        return resultList;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.SERIALIZABLE, timeout = 10)
    public Result growGiveFlower(String transNo, long uid, List<Long> giveUids) {


        Result result = new Result();
        if (uid == 0 || uid < 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("操作用户ID不能为空");
            return result;
        }
        Integer record = propsMapper.countPropsRecordByTransNo(transNo);
        if (record != null && record > 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.REPEATEDLY_COMMIT_STATUS.getStatus());
            result.setMessage("当前交易号已经有记录，不能重复提交");
            return result;
        }
        if (giveUids == null || giveUids.size() == 0) {
            result.setCode(ResultCodeEnum.ERROR.status());
            result.setStatusCode(BusinessStatusCodeEnum.PARAM_ERROR_STATUS.getStatus());
            result.setMessage("赠送用户ID不能为空");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        for (Long giveUid : giveUids) {
            sb.append(giveUid + ",");
        }
        logger.info("当前交易号:" + transNo + ",用户ID是:" + uid + ",扣除的花苗数量是:" + giveUids.size() + ",下级的UID是:" + sb.toString() + ",种花操作开始..");
        int updateGiveFlower = propsMapper.updateUserGiveFloweUseStatus(uid, giveUids, DateUtil.getNow(DateUtil.Y_M_D_HMS));
        if (updateGiveFlower == giveUids.size()) {
            SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1, 1);
            Props props = propsMapper.getUserPropsByUidAndTypeAndSource(uid, PropsTypeEnum.FLOWER.getType(), PropsSourceEnum.FLOWER_SEEDING_GIVE.getSourceType());
            int insertRecordResult = this.savePropsRecord(uid, transNo, snowflakeIdWorker.nextId() + "", PropsChannelEnum.GROW_FLOWERS.getChannel(), PropsOperationTypeEnum.MIUNS.getType(),
                    giveUids.size(), PropsTypeEnum.FLOWER.getType(), props.getId(), props.getNumber(), props.getNumber() - giveUids.size(), 0, 0,
                    "当前用户" + uid + "使用赠送的花苗进行种花，使用的赠送的数量是:" + giveUids.size() + ",赠送人的UID是:" + sb.toString());
            if (insertRecordResult == 1) {
                int updateResult = propsMapper.minusPropsNumberById(props.getId(), giveUids.size(), DateUtil.getNow(DateUtil.Y_M_D_HMS));
                if (updateResult != 1) {
                    logger.error("交易号:" + transNo + ",uid:" + uid + "使用下级赠送的花苗种花，种花失败,扣除用户的道具表发生失败,道具表ID;" + props.getId() + ",扣除的数量是:" + giveUids.size());
                    throw new CustomException("交易号:" + transNo + ",uid:" + uid + "使用下级赠送的花苗种花，种花失败,扣除用户的道具表发生失败,道具表ID;" + props.getId() + ",扣除的数量是:" + giveUids.size());
                }
            } else {
                logger.error("交易号:" + transNo + ",uid:" + uid + "使用下级赠送的花苗种花，种花失败,增加用户的道具记录表失败,下级的UID是:" + sb.toString());
                throw new CustomException("交易号:" + transNo + ",uid:" + uid + "使用下级赠送的花苗种花，种花失败,增加用户的道具记录表失败,下级的UID是:" + sb.toString());
            }
        } else {
            logger.error("交易号:" + transNo + ",uid:" + uid + "使用下级赠送的花苗种花，种花失败,更新用户的花苗赠送记录表失败,下级的UID是:" + sb.toString());
            throw new CustomException("交易号:" + transNo + ",uid:" + uid + "使用下级赠送的花苗种花，种花失败,更新用户的花苗赠送记录表失败,下级的UID是:" + sb.toString());

        }
        result.setMessage("用户使用园又赠送的花苗种花操作成功");
        result.setCode(ResultCodeEnum.SUCCESS.status());
        result.setStatusCode(BusinessStatusCode.SUCCESS_STATUS.getStatus());
        logger.info("当前交易号:" + transNo + ",用户ID是:" + uid + ",扣除的花苗数量是:" + giveUids.size() + ",种花操作成功");
        return result;
    }


    /**
     * 增加交易记录
     *
     * @param uid       操作用户UID
     * @param transNo   消费交易号
     * @param channel   交易类型
     * @param type      交易类型（）
     * @param number    操作数量
     * @param propsType 道具类型
     * @param remark    备注
     */
    private int savePropsRecord(long uid, String transNo, String batchNo, int channel, int type, double number, int propsType, long propsId, double qhun, double hnum,
                                int giveNumber, long giveUid, String remark) {
        /**
         * long propsId,double qhum, double hnum,
         */
        PropsRecord rp = new PropsRecord();
        rp.setUid(uid);
        rp.setTransNo(transNo);
        rp.setBatchNo(batchNo);
        rp.setChannel(channel);
        rp.setType(type);
        rp.setNum(number);
        rp.setPropsType(propsType);

        rp.setPropsId(propsId);
        rp.setQnum(qhun);
        rp.setHnum(hnum);
        rp.setCommonUid(giveUid);
        rp.setCommonNumber(giveNumber);
        rp.setRemark(remark);
        rp.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
        return propsMapper.savePropsRecord(rp);
    }

    /**
     * 增加交易记录
     *
     * @param uid       操作用户UID
     * @param transNo   消费交易号
     * @param channel   交易类型
     * @param type      交易类型（）
     * @param number    操作数量
     * @param propsType 道具类型
     * @param remark    备注
     */
    private PropsRecord savePropsRecords(long uid, String transNo, String batchNo, int channel, int type, double number, int propsType, long propsId, double qhun, double hnum,
                                         int giveNumber, long giveUid, String remark) {
        PropsRecord rp = new PropsRecord();
        rp.setUid(uid);
        rp.setTransNo(transNo);
        rp.setBatchNo(batchNo);
        rp.setChannel(channel);
        rp.setType(type);
        rp.setNum(number);
        rp.setPropsType(propsType);

        rp.setPropsId(propsId);
        rp.setQnum(qhun);
        rp.setHnum(hnum);
        rp.setCommonUid(giveUid);
        rp.setCommonNumber(giveNumber);
        rp.setRemark(remark);
        rp.setCreateTime(DateUtil.getNow(DateUtil.Y_M_D_HMS));
        return rp;
    }

}
