package com.xmniao.xmn.core.market.service.home.impl;

import com.xmniao.xmn.core.market.dao.FreshActivityCommonDao;
import com.xmniao.xmn.core.market.dao.FreshImageDao;
import com.xmniao.xmn.core.market.dao.FreshModuleDao;
import com.xmniao.xmn.core.market.entity.home.FreshModule;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.service.home.HomeConstant;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 积分超市首页数据访问封装类
 * Created by yang.qiang on 2017/3/20.
 */
@Service
public class HomeAccess {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FreshImageDao freshImageDao;
    @Autowired
    private String fileUrl;
    @Autowired
    private FreshModuleDao freshModuleDao;
    @Autowired
    private FreshActivityCommonDao freshActivityCommonDao;



    /**
     * 查询模块列表
     * @param moduleType    模块类型
     * @param typeId        积分超市商品分类 0 首页
     * @return
     */
    protected List<FreshModule> queryModuleList(Integer moduleType, Long typeId) {
        List<FreshModule> moduleList = freshModuleDao.selectModuleListByModelTypeAndTypeId(moduleType,typeId);
        return moduleList;
    }

    /**
     * 查询模块
     * @param moduleType    模块类型
     * @param typeId        积分超市商品分类 0 首页
     * @return
     */
    protected  FreshModule queryModule(Integer moduleType, Long typeId){
        FreshModule freshModule = null;
        try {
            List<FreshModule> freshModules = this.queryModuleList(moduleType, typeId);
            if (freshModules.size()==0){
                return null;
            }
            freshModule = freshModules.get(0);
        } catch (Exception e) {
            logger.error("查询商品模块出现异常 moduleType="+moduleType + " typeId"+typeId,e);
        }
        return freshModule;
    }

    /**
     * 判断模块是否有效
     * @param module
     * @return
     */
    protected boolean isAliveModule(FreshModule freshModule){
        if (freshModule == null) {
            logger.info("模块[" + freshModule.getId() + "]为空");
            return false;
        }

        Integer moduleType = freshModule.getModuleType();
        // 模块类型是 01 商品模块 或 02 精选模块
        if (moduleType == HomeConstant.FRESH_MODULE_TYPE_PRODUCT || moduleType == HomeConstant.FRESH_MODULE_TYPE_SELECTED) {
            Integer productType = freshModule.getProductType();
            switch (productType){
                case HomeConstant.FRESH_MODULE_PRODUCT_TYPE_HOT:        // 模块的商品类型为热销商品
                    return true;

                case HomeConstant.FRESH_MODULE_PRODUCT_TYPE_SELECTED:   // 模块的商品类型为精选商品
                    return true;

                case HomeConstant.FRESH_MODULE_PRODUCT_TYPE_COMMON:     // 模块的商品类型为通用活动
                    FreshActivityCommon activity = freshActivityCommonDao.selectByPrimaryKey(freshModule.getActivityId().intValue());
                    long currentTime = System.currentTimeMillis();

                    // 判断开始时间大于当前时间, 结束时间小于当前时间
                    if (activity.getBeginDate().getTime() > currentTime || activity.getEndDate().getTime() < currentTime) {
                        logger.info("模块["+freshModule.getId()+"]对应的通用活动["+activity.getId()+"]未到开始时间或已结束");
                        return false;
                    }

                    // 判断通用活动状态是否正常
                    if (activity.getStatus() != HomeConstant.FRESH_COMMON_ACTIVITY_STATUES_NORMAL) {
                        logger.info("模块["+freshModule.getId()+"]对用的通用活动["+activity.getId()+"]的状态为"+activity.getStatus());
                        return false;
                    }

                    
                    return true;

                case HomeConstant.FRESH_MODULE_PRODUCT_TYPE_KILL:       // 模块的商品类型为秒杀活动
                    // 判断秒杀活动是否有效
                    return true;

                default:
                    logger.error("未知模块商品类型 module.getProductType="+freshModule.getProductType());
                    return false;
            }
        }

        // 模块类型是否为专场模块
        if (moduleType == HomeConstant.FRESH_MODULE_TYPE_ACTIVITY) {
            return true;
        }

        return false;
    }


}
