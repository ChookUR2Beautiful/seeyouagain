/**
 * 
 */
package com.xmniao.service.live;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.live.VerSaasService;

/**
 * 
 * 项目名称：busineService_mall_vke
 * 
 * 类名称：VerSaasServiceImpl
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： Administrator
 * 
 * 创建时间：2017年8月15日 下午6:15:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class VerSaasServiceImpl implements VerSaasService.Iface{

	private final Logger log = Logger.getLogger(VerSaasServiceImpl.class);
	
	@Autowired
	private VerRechargeLiveOrder verLiveOrder;
	
	/**
	 * 
SELECT t1.*,t2.* FROM 
(SELECT uid,SUM(payment) amt,COUNT(1) num FROM t_live_privilege WHERE object_oriented=4 AND payment IN(9000,18000,36000) GROUP BY uid) t1
LEFT JOIN 
(SELECT uid,SUM(nums),GROUP_CONCAT(id) FROM t_saas_order WHERE saas_channel=3 AND STATUS=1 GROUP BY uid) t2
ON t1.uid=t2.uid
WHERE t2.uid IS NULL
	 * 
	 * 
	 */
	@Override
	public ResponseData replenishSaasNumber(int uid, int saasChannel, int num) throws FailureException, TException {
		log.info("【"+uid+"】补充"+saasChannel+"类型的saas "+ num+"套");
		try{
			int nums = verLiveOrder.replenishSaasNumber(uid, saasChannel, num);
			Map<String,String> map = new HashMap<>();
			map.put("addnum", nums+"");
			return new ResponseData(0, "完美！", map);
		}catch(Exception e){
			log.error("给用户【"+uid+"】补充SAAS异常",e);
		}
		return new ResponseData(1, "异常了", null);
	}

}

