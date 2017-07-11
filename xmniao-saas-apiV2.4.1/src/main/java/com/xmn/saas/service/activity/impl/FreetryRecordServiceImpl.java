package com.xmn.saas.service.activity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.dao.activity.FreetryRecordDao;
import com.xmn.saas.entity.activity.Freetry;
import com.xmn.saas.entity.activity.FreetryRecord;
import com.xmn.saas.service.activity.FreetryRecordService;
import com.xmn.saas.service.activity.FreetryService;
@Service
public class FreetryRecordServiceImpl implements FreetryRecordService {
	@Autowired
	private FreetryRecordDao freetryRecordDao;
	
	@Autowired
	private FreetryService freetryService;

	@Override
	public List<FreetryRecord> list(Integer sellerId, Integer activityId, Integer pageSize, Integer pageIndex) {
		
		return freetryRecordDao.list(sellerId,activityId,pageSize,pageIndex);
	}

	@Override
	public FreetryRecord detail(Integer id, Integer activityId) {
		return freetryRecordDao.detail(id,activityId);
	}

	@Override
	public Freetry getFreetryById(Integer activityId,Integer sellerId) {
		return freetryService.detail(activityId, sellerId);
	}

	@Override
	public Integer countFreetryRecord(Integer activityId) {
		Long count = freetryRecordDao.countFreetryRecord(activityId);
		return count.intValue();
	}

	@Override
	public Integer countRecord(Integer id) {
		return freetryRecordDao.countRecord(id);
	}

	
}
