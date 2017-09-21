package com.xmniao.dao.experienceOfficer;

import java.util.Map;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：TExperienceOfficerOrderDao
 * 
 * 类描述： 美食体验官订单服务DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-8 下午5:38:37 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TExperienceOfficerOrderDao {


    /**
     * 
     * 方法描述：根据订单号查询订单信息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-5-8下午5:44:14 <br/>
     * @param orderNo
     * @return
     */
    Map<String,Object> selectByOrderNo(String orderNo);

    /**
     * 
     * 方法描述：更新订单 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-5-8下午5:41:21 <br/>
     * @param paramMap
     * @return
     */
    int update(Map<String,Object> paramMap);
    

}