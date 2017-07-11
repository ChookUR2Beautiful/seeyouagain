/**
 * 
 */
package com.xmn.saas.service.activity.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.controller.h5.coupon.vo.QueryCondition;
import com.xmn.saas.dao.activity.FullreductionDao;
import com.xmn.saas.dao.bill.BillDao;
import com.xmn.saas.entity.activity.Fullreduction;
import com.xmn.saas.service.activity.FullreductionService;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   满减活动接口实现
 * 创建人：huangk   
 * 创建时间：2016年9月29日 上午11:21:24      
 */
@Service
public class FullreductionServiceImpl implements FullreductionService {
	
	/**
	 * 注入满减活动mapper接口
	 */
	@Autowired
	private FullreductionDao fullreductionDao; 
	
	@Autowired
	private BillDao billDao;
	/** 
	 * 根据主键查询满减活动信息
	 * @param id 主键
	 * @return Fullreduction
	 */
	@Override
	public Fullreduction selectByPrimaryKey(Integer id) {
		return fullreductionDao.selectByPrimaryKey(id);
	}

	/** 
	 * 新增满减活动信息(直接新增)
	 * @param Fullreduction
	 */
	@Transactional
	@Override
	public int insert(Fullreduction record) {
		fullreductionDao.insert(record);
		return record.getId();
	}

	/** 
	 * 新增满减活动信息(字段为空判断)
	 * @param Fullreduction
	 */
	@Transactional
	@Override
	public int insertSelective(Fullreduction record) {
		return fullreductionDao.insertSelective(record);
	}

	/** 
	 * 更新满减活动信息(字段为空判断)
	 * @param Fullreduction
	 */
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(Fullreduction record) {
		return fullreductionDao.updateByPrimaryKeySelective(record);
	}

	/** 
	 * 更新满减活动信息(直接更新)
	 * @param Fullreduction
	 */
	@Transactional
	@Override
	public int updateByPrimaryKey(Fullreduction record) {
		return fullreductionDao.updateByPrimaryKey(record);
	}

	/** 
	 * 根据主键删除满减活动信息
	 * @param id 主键
	 */
	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return fullreductionDao.deleteByPrimaryKey(id);
	}

	/** 
	 * 根据商户id以及活动时间查询商户当前满减列表信息
	 */
	@Override
	public List<Fullreduction> queryListBySerllIdAndDate(QueryCondition query) throws SQLException {
		List<Fullreduction> dataList = fullreductionDao.queryListBySerllIdAndDate(query);
		if(dataList!=null&&dataList.size()>0){
			return dataList;
		}
		return null;
	}

	@Override
	public Integer countJoinNum(Integer couponId) {
		return billDao.countFullJoinNum(couponId);
	}
}
