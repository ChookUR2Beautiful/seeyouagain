package com.xmniao.service.impl;

import com.xmniao.common.MapBeanUtil;
import com.xmniao.common.Page;
import com.xmniao.common.ValidateUtil;
import com.xmniao.entity.Experiencecard;
import com.xmniao.entity.ExperiencecardRecord;
import com.xmniao.exception.CustomException;
import com.xmniao.service.ExperiencecardAccess;
import com.xmniao.thrift.ledger.ExperiencecardService;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.ledger.ResponseSubList;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 美食体验卡Thrift接口服务实现类
 * Created by yang.qiang on 2017/5/8.
 */
@Service("ExperiencecardServiceImpl")
public class ExperiencecardServiceImpl implements ExperiencecardService.Iface {
    private static final Logger logger = LoggerFactory.getLogger(ExperiencecardServiceImpl.class);

    @Autowired
    private ExperiencecardAccess experiencecardAccess;

    /**
     * 创建/更新 美食体验卡
     * 参数
     * uid : 用户编号
     * dueDate : 截至时间(yyyy-MM-dd HH:mm:ss)
     * stock : 美食体验卡初始次数
     *
     * @param paramMap
     */
    @Override
    public ResponseData createExperiencecard(Map<String, String> paramMap) throws FailureException, TException {
        logger.info("调用Thrift接口[创建/更新 美食体验卡], 参数" + paramMap);
        ResponseData responseData = new ResponseData();

        Experiencecard experiencecard = new Experiencecard();
        ExperiencecardRecord experiencecardRecord = new ExperiencecardRecord();
        try {
            // 判断参数是否为空
            if (ValidateUtil.validateNull(paramMap, "uid", "dueDate", "stock", "source")) {
                // 判断参数格式是否可以正确转换
                experiencecard.setUid(Integer.valueOf(paramMap.get("uid")));
                experiencecard.setStock(Integer.valueOf(paramMap.get("stock")));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                experiencecard.setDueDate(simpleDateFormat.parse(paramMap.get("dueDate")));

                experiencecardRecord.setSource(paramMap.get("source"));
                experiencecardRecord.setRemark(paramMap.get("remark"));
            } else {
                logger.info("参数不足, paraMap=" + paramMap);
                responseData.setState(2);
                responseData.setMsg("参数不足,paraMap=" + paramMap);
                return responseData;
            }

        } catch (Exception e) {
            logger.error("校验参数失败!", e);
            responseData.setState(2);
            responseData.setMsg("参数错误! paraMap=" + paramMap);
            return responseData;
        }

        try {

            // 创建更新体验卡
            experiencecardAccess.createCard(experiencecard, experiencecardRecord);
            responseData.setState(0);
            responseData.setMsg("创建/更新体验卡成功");

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            responseData.setMsg(ex.getMessage());
            responseData.setState(ex.getCode());
        } catch (Exception e) {
            responseData.setState(1);
            responseData.setMsg("支付系统出现异常!");
            logger.error("调用Thrift接口[创建/更新 美食体验卡], 出现异常!", e);
        }

        return responseData;
    }

    /**
     * 1.13.2 获取美食体验卡信息
     * 参数
     * uid : 用户编号
     *
     * @param paramMap
     */
    @Override
    public ResponseData getExperiencecard(Map<String, String> paramMap) throws FailureException, TException {
        logger.info("调用Thrift接口[1.13.2 获取美食体验卡信息], 参数" + paramMap);
        ResponseData responseData = new ResponseData();

        Integer uid = null;
        try {
            // 判断参数是否为空
            if (ValidateUtil.validateNull(paramMap, "uid")) {
                // 判断参数格式是否可以正确转换
                uid = Integer.valueOf(paramMap.get("uid"));
            } else {
                logger.info("参数不足, paraMap=" + paramMap);
                responseData.setState(2);
                responseData.setMsg("参数不足,paraMap=" + paramMap);
                return responseData;
            }

        } catch (Exception e) {
            logger.error("校验参数失败!", e);
            responseData.setState(2);
            responseData.setMsg("参数错误! paraMap=" + paramMap);
            return responseData;
        }

        try {
            Experiencecard experiencecard = experiencecardAccess.queryExperiencecard(uid);
            if (experiencecard == null) {
                logger.info("未查询到用户uid为["+uid+"]的美食体验卡");
                responseData.setState(3);
                responseData.setMsg("用户["+uid+"], 没有开通美食体验卡!");
                return responseData;
            }

            // 转换Bean为Map
            Map<String, String> resultMap = MapBeanUtil.convertMap(experiencecard,
                    "id", "uid", "status", "stock", "used", "dueDate", "updateTime");
            responseData.setResultMap(resultMap);
            responseData.setMsg("请求成功");
            responseData.setState(0);

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            responseData.setMsg(ex.getMessage());
            responseData.setState(ex.getCode());
        } catch (Exception e) {
            responseData.setState(1);
            responseData.setMsg("支付系统出现异常!");
            logger.error("调用Thrift接口[1.13.2 获取美食体验卡信息], 出现异常!", e);
        }

        return responseData;
    }

    /**
     * 1.13.3 扣除美食体验卡
     * 参数
     * uid : 用户编号
     * source : 消费订单号
     * operate : 操作类型 1:扣除美食体验卡   2:退还体验卡
     *
     * @param paramMap
     */
    @Override
    public ResponseData deductExperiencecard(Map<String, String> paramMap) throws FailureException, TException {
        logger.info("调用Thrift接口[1.13.3 扣除美食体验卡, 参数" + paramMap);
        ResponseData responseData = new ResponseData();

        Integer uid = null;
        Integer operate = null;
        ExperiencecardRecord experiencecardRecord = new ExperiencecardRecord();
        try {
            // 判断参数是否为空
            if (ValidateUtil.validateNull(paramMap, "uid","operate")) {
                // 判断参数格式是否可以正确转换
                uid = Integer.valueOf(paramMap.get("uid"));
                operate = Integer.valueOf(paramMap.get("operate"));

                experiencecardRecord.setSource(paramMap.get("source"));
                experiencecardRecord.setRemark(paramMap.get("remark"));
            } else {
                logger.info("参数不足, paraMap=" + paramMap);
                responseData.setState(2);
                responseData.setMsg("参数不足,paraMap=" + paramMap);
                return responseData;
            }

        } catch (Exception e) {
            logger.error("校验参数失败!", e);
            responseData.setState(2);
            responseData.setMsg("参数错误! paraMap=" + paramMap);
            return responseData;
        }

        try {
            // 扣除美食体验卡
            experiencecardAccess.dudectExperiencecard(uid,experiencecardRecord,operate);

            responseData.setMsg("扣除/退还美食体验卡成功");
            responseData.setState(0);

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            responseData.setMsg(ex.getMessage());
            responseData.setState(ex.getCode());
        } catch (Exception e) {
            responseData.setState(1);
            responseData.setMsg("支付系统出现异常!");
            logger.error("调用Thrift接口[1.13.3 扣除美食体验卡, 出现异常!", e);
        }

        return responseData;
    }

    /**
     * 1.13.4 根据条件查询美食体验卡列表
     * id : 编号
     * uid : 用户编号
     * status : 美食体验卡状态 0正常 1锁定
     * startDueDate : 截至时间范围yyyy-MM-dd HH:mm:ss 条件due_date >= startDueDate
     * endDueDate : 截至时间范围yyyy-MM-dd HH:mm:ss 条件due_date < endDueDate
     * pageNum : 分页页数,从1开始
     * pageSize : 分页页面大小
     *
     *
     * @param paramMap
     */
    @Override
    public ResponseSubList queryExperiencerdList(Map<String, String> paramMap) throws FailureException, TException {
        logger.info("调用Thrift接口[1.13.4 根据条件查询美食体验卡列表]" + paramMap);
        ResponseSubList responseSubList = new ResponseSubList();

        Page page = new Page();
        try {   // 参数校验
            if (paramMap.get("pageNum") != null)
                page.setPageNum(Integer.valueOf(paramMap.get("pageNum")));
            if (paramMap.get("pageSize") != null)
                page.setPageSize(Integer.valueOf(paramMap.get("pageSize")));

        } catch (Exception e) {
            logger.error("校验参数失败!", e);
            throw new FailureException(2, "参数错误! " + paramMap);
        }

        try {   // 业务逻辑

            // 统计体验卡数量
            responseSubList.setCountNum(experiencecardAccess.countExperiencecard(paramMap));
            // 查询美食体验卡列表
            List<Experiencecard> excardList = experiencecardAccess.queryExperiencecardList(paramMap, page);

            // 将List<Experiencecard>转换成List<Map>作为响应数据
            ArrayList<Map<String, String>> mapList = new ArrayList<>();
            responseSubList.setSubList(mapList);
            for (Experiencecard experiencecard : excardList) {
                Map<String, String> convertMap = MapBeanUtil.convertMap(experiencecard, "id", "uid", "status", "used", "dueDate","stock");
                mapList.add(convertMap);
            }

        } catch (Exception e) {
            logger.error("调用Thrift接口[1.13.4 根据条件查询美食体验卡列表]", e);
            throw new FailureException(1, "系统内部异常!");

        }

        return responseSubList;
    }

    /**
     * 1.13.5 更新美食体验卡状态
     * id : 美食体验卡编号
     * status : 需要更新的状态 0正常 1锁定
     *
     * @param paramMap
     */
    @Override
    public ResponseData updateExperiencecardStatus(Map<String, String> paramMap) throws FailureException, TException {
        logger.info("调用Thrift接口[1.13.5 更新美食体验卡状态]" + paramMap);
        ResponseData responseData = new ResponseData();

        Experiencecard experiencecard = new Experiencecard();
        try {   // 参数校验
            // 判断参数是否为空
            if (ValidateUtil.validateNull(paramMap, "id","status")) {
                experiencecard.setId(Integer.valueOf(paramMap.get("id")));
                experiencecard.setStatus(Integer.valueOf(paramMap.get("status")));
            } else {
                logger.info("参数不足, paraMap=" + paramMap);
                responseData.setState(2);
                responseData.setMsg("参数不足,paraMap=" + paramMap);
                return responseData;
            }

        } catch (Exception e) {
            logger.error("校验参数失败!", e);
            responseData.setState(2);
            responseData.setMsg("参数错误! paraMap=" + paramMap);
            return responseData;
        }

        try {   // 业务逻辑

            experiencecardAccess.updateExcardStatus(experiencecard);

            responseData.setMsg("更新状态成功");
            responseData.setState(0);

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            responseData.setMsg(ex.getMessage());
            responseData.setState(ex.getCode());
        } catch (Exception e) {
            responseData.setState(1);
            responseData.setMsg("后台系统出现异常!");
            logger.error("调用Thrift接口[1.13.5 更新美食体验卡状态]", e);
        }
        return responseData;
    }

    /**
     * 分页获取美食体验卡记录信息
     * rtype 0充值 | 1消费 | 2退还
     * uid     用户uid
     * pageSize    分页页面数量
     * page        分页页数
     *
     * @param paramMap
     */
    @Override
    public List<Map<String, String>> getExperiencecardRecordList(Map<String, String> paramMap) throws FailureException, TException {
        return null;
    }

    /**
     * 根据用户uid 批量获取美食体验卡
     * @param uids
     */
    @Override
    public List<Map<String, String>> getExperiencecardByUids(List<Integer> uids) throws FailureException, TException {


        logger.info("调用Thrift接口[1.13.6 批量获取用户美食体验卡]" + uids);

        List<Map<String, String>> experiencecardList = new ArrayList<>();


        try {   // 业务逻辑

            if (uids.size() != 0) {
                List<Experiencecard> experiencecards = experiencecardAccess.queryExperiencecardList(uids);
                for (Experiencecard experiencecard : experiencecards) {
                    experiencecardList.add(MapBeanUtil.convertMap(experiencecard,
                            "id", "uid", "status", "stock", "used", "dueDate", "updateTime"));
                }
            }
        } catch (Exception e) {
            logger.error("调用Thrift接口[1.13.6 批量获取用户美食体验卡]", e);
            throw new FailureException(1,"查询用户体验卡出现异常!");
        }
        return experiencecardList;
    }
}
