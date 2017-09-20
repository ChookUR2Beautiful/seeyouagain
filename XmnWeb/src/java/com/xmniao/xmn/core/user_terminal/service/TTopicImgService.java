package com.xmniao.xmn.core.user_terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.user_terminal.dao.TTopicImgDao;
import com.xmniao.xmn.core.user_terminal.entity.TTopicImg;
@Service
public class TTopicImgService extends BaseService<TTopicImg> {
	@Autowired
	private TTopicImgDao topicImgDao;
	
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return topicImgDao;
	}
	
	public List<TTopicImg> getTopicImgList(Long topicId){
		return topicImgDao.getTopicImgList(topicId);
	}
}
