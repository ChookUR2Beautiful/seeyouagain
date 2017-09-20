package com.xmniao.xmn.core.common.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.common.dao.TSequenceDao;

@Service
public class TSequenceService {
	
	@Autowired
	TSequenceDao tSequenceDao;
	
	/*
	 * 获取当前系统序号并+1
	 */
	public synchronized Long getAndUpdateSid(Integer numId){
		long sid = tSequenceDao.getSid(numId);
		tSequenceDao.updateSid(numId);
		return sid;
	} 
	
	/**
	 * 
	 * 方法描述：更新系统序号
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-18上午10:56:35
	 * @param param
	 */
	public void updateSpecifiedSid(Map<String,Object> param){
		tSequenceDao.updateSpecifiedSid(param);
	}
}
