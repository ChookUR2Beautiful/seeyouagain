/**    
 * 文件名：ManorMQServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2017年6月5日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.impl;

import com.xmniao.common.DateUtil;
import com.xmniao.dao.manor.PropsMapper;
import com.xmniao.exception.CustomException;
import com.xmniao.service.ManorMQService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 
 * 项目名称：payService
 * 
 * 类名称：ManorMQServiceImpl
 * 
 * 类描述： 黄金庄园MQ消费者业务处理
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年6月5日 下午6:09:39 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class ManorMQServiceImpl implements ManorMQService{

	@Autowired
	private PropsMapper propsMapper;
	
//	@Autowired
//	private PropsMapper propsMapper;
	
	/**
	 * 添加黄金庄园的能量
	 */
	@Transactional(rollbackFor={CustomException.class,RuntimeException.class})
	@Override
	public void addManorEnergy(Map<String, Object> map) throws CustomException{
		Long uid = Long.parseLong(map.get("uid").toString());
		Integer propsType = 5;
		Integer propsSource = 500001;
		Integer number = (Integer)map.get("number");
		int result = propsMapper.addPropsNumber(uid, propsType, propsSource, number, DateUtil.getNow(DateUtil.Y_M_D_HMS));
		if(result==0){
			throw new CustomException("更新异常");
		}
		
		//propsRecordMapper.addRecord()...
		if(result==0){
			throw new CustomException("插入记录异常");
		}
	}

}
