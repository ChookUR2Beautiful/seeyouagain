/**
 *
 */
package com.xmn.saas.service.common;

import com.xmn.saas.controller.api.v1.common.vo.ShareRequest;
import com.xmn.saas.entity.common.JsPatch;
import com.xmn.saas.entity.common.SystemAnnouncement;
import com.xmn.saas.exception.SaasException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 项目名称：xmniao-saas-api
 * 类描述：
 * 创建人：huangk
 * 创建时间：2016年10月17日 下午2:22:54
 */
public interface CommonService {
    /**
     * 计算获取活动期间刺激消费金额=活动期间总营业额度  - 非活动期间总营业额度
     *
     * @param sellerid  商户id
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return BigDecimal    返回类型
     * @throws
     */
    BigDecimal getActiveAmount(int sellerid, Date beginDate, Date endDate);

    /**
     * 获取app更新相关信息
     *
     * @param systemVersion
     * @param appVersion
     * @return
     */
    Map<String, Object> appUpdate(String systemVersion, String appVersion) throws SaasException, IOException;
    
    /**
     * 获取分享地址
     * @return
     */
    public String getShareUrl(ShareRequest request);
    
    /**
     * 查询最新公告
     * @return
     */
    public SystemAnnouncement selectOne();

    JsPatch getJsPatch(JsPatch jsPatch);

    void updateJsPatch(JsPatch jsPatch) throws IOException;
}
