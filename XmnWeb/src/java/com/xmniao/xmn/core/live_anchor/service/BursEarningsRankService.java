package com.xmniao.xmn.core.live_anchor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.live_anchor.dao.BursEarningsRankDao;
import com.xmniao.xmn.core.live_anchor.entity.BursEarningsRank;

/**
 * 
 * 
 * 项目名称：XmnWeb_412
 * 
 * 类名称：BursEarningsRankService
 * 
 * 类描述： 会员活动收益等级Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-4-8 下午4:58:23 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BursEarningsRankService {
	
	@Autowired
	private BursEarningsRankDao earningsRankDao;
	
	/**
	 * 
	 * 方法描述：根据主键删除纪录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-8下午4:58:53 <br/>
	 * @param id
	 * @return
	 */
    public  int deleteById(Long id){
    	return earningsRankDao.deleteById(id);
    }

	/**
	 * 
	 * 方法描述：新增纪录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-8下午4:59:08 <br/>
	 * @param record
	 * @return
	 */
    public int add(BursEarningsRank record){
    	return earningsRankDao.add(record);
    }

    /**
     * 
     * 方法描述：根据主键查询记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-4-8下午4:59:25 <br/>
     * @param id
     * @return
     */
    public BursEarningsRank selectById(Long id){
    	return earningsRankDao.selectById(id);
    }
    
    /**
     * 
     * 方法描述：根据bean对象查询记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-4-8下午4:59:25 <br/>
     * @param id
     * @return
     */
    public BursEarningsRank selectByBean(BursEarningsRank record){
    	return earningsRankDao.selectByBean(record);
    }

    /**
     * 
     * 方法描述：更新记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-4-8下午4:59:41 <br/>
     * @param record
     * @return
     */
    public int update(BursEarningsRank record){
    	return earningsRankDao.update(record);
    }

}