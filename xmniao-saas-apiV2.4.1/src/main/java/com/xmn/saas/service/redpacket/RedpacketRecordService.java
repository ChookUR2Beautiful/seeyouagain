package com.xmn.saas.service.redpacket;

import java.util.Map;

/**
 * create 2016/11/10
 *
 * @author yangQiang
 */

public interface RedpacketRecordService {

    Map<String, Object> pagedRecordList(Long redpacketId, Integer pageNum, Integer pageSize) throws Exception;
}
