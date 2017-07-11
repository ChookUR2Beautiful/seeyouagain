package com.xmn.saas.service.activity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.dao.activity.KillRecordDao;
import com.xmn.saas.entity.activity.KillRecord;
import com.xmn.saas.service.activity.KillRecordService;

@Service
public class KillRecordServiceImpl implements KillRecordService {
	@Autowired
	private KillRecordDao killRecordDao;

	@Override
	public List<KillRecord> list(Integer activityId, Integer sellerId, Integer pageSize, Integer pageIndex) {
		return killRecordDao.list(activityId,sellerId,pageSize,pageIndex);
	}

	@Override
	public KillRecord detail(Integer recordId, Integer activityId) {
		return killRecordDao.detail(recordId,activityId);
	}

	@Override
	public Integer countRecord(Integer id) {
		return killRecordDao.countRecord(id);
	}
}
