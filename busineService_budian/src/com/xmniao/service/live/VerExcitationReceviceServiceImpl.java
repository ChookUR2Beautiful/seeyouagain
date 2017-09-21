package com.xmniao.service.live;

import com.xmniao.common.ValidateUtil;
import com.xmniao.exception.CustomException;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.common.ResponsePageList;
import com.xmniao.thrift.busine.live.VerExcitationReceiveService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by yang.qiang on 2017/5/29.
 */
@Service("verExcitationReceviceServiceImpl")
public class VerExcitationReceviceServiceImpl implements VerExcitationReceiveService.Iface{

    private final Logger logger = LoggerFactory.getLogger(VerExcitationReceviceServiceImpl.class);

    @Autowired
    private VerExcitationService verExcitationService;
    /**
     * 更新V客充值奖励红酒领取状态
     *
     * @param paraMap
     */
    @Override
    public ResponseData updateReceiveStatus(Map<String, String> paraMap) throws FailureException, TException {
        return null;
    }

    /**
     * 用户领取奖励方案A, 返回已领取优惠劵
     * uid : 用户id
     * @param paramMap
     */
    @Override
    public ResponsePageList recevicePlanA(Map<String, String> paramMap) throws FailureException, TException {

        logger.info("调用Thrift接口[用户领取奖励方案A]" + paramMap);
        ResponseData responseData = new ResponseData();
        ResponsePageList responsePageList = new ResponsePageList();
        responsePageList.setDataInfo(responseData);

        Integer uid = null;
        try {   // 参数校验
            // 判断参数是否为空
            if (ValidateUtil.validateNull(paramMap, "uid")) {
                uid= Integer.valueOf(paramMap.get("uid"));
            } else {
                logger.info("参数不足, paraMap=" + paramMap);
                responseData.setState(2);
                responseData.setMsg("参数不足,paraMap=" + paramMap);
                return responsePageList;
            }

        } catch (Exception e) {
            logger.error("校验参数失败!", e);
            responseData.setState(2);
            responseData.setMsg("参数错误! paraMap=" + paramMap);
            return responsePageList;
        }

        try {   // 业务逻辑

            ArrayList<Map<String, String>> maps = verExcitationService.receiveExcitationOfPlanA(uid);
            responsePageList.setPageList(maps);
            responseData.setMsg("领取优惠劵成功!");
            responseData.setState(0);

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            responseData.setMsg(ex.getMessage());
            responseData.setState(ex.getCode());
        } catch (Exception e) {
            responseData.setState(1);
            responseData.setMsg("后台系统出现异常!");
            logger.error("调用Thrift接口[用户领取奖励方案A]", e);
        }
        return responsePageList;
    }


}
