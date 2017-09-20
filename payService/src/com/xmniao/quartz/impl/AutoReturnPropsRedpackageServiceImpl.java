package com.xmniao.quartz.impl;


import com.xmniao.entity.manor.PropsRedpackage;
import com.xmniao.quartz.AutoReturnPropsRedpackageService;
import com.xmniao.service.PropsService;
import com.xmniao.thrift.manor.Result;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liyuanbo
 * @create 2017-06-22 14:21
 **/
@Service("autoReturnPropsRedpackageService")
public class AutoReturnPropsRedpackageServiceImpl implements AutoReturnPropsRedpackageService {
    @Autowired
    private PropsService propsService;

    private static Logger logger = Logger.getLogger(AutoReturnPropsRedpackageServiceImpl.class);

    @Override
    public void returnPropsRedpackage() {
        logger.info("开始退回用户的红包，正在进行中......");
        List<PropsRedpackage> propsRedpackages = propsService.getUserExprieRedpackage();
        if (propsRedpackages != null && propsRedpackages.size() > 0) {
            for (PropsRedpackage propsRedpackage : propsRedpackages) {
                if (propsRedpackage.getNumber() - propsRedpackage.getCurrentGetNumber() > 0) {
                    Result result = propsService.returnUserSunRedpackage(propsRedpackage.getId(), propsRedpackage.getUid(),
                            propsRedpackage.getSingleRedpackageAmount(),
                            propsRedpackage.getServiceChargeProfit(), propsRedpackage.getNumber(),
                            propsRedpackage.getNumber() - propsRedpackage.getCurrentGetNumber(),
                            propsRedpackage.getPhone(),propsRedpackage.getType(),propsRedpackage.getAmount());
                }
            }
        }
        logger.info("退回用户的红包结束......");
    }
}
