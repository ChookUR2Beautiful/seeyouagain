package com.xmniao.xmn.core.xmer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.xmer.dao.AbnormalDao;


@Service
public class AbnormalService {
	
	@Autowired
	private AbnormalDao abnormalDao;

}
