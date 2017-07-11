package com.xmn.saas.dao.activity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmn.saas.entity.activity.FullreductionRecord;

@Repository
public interface FullreductionRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FullreductionRecord record);

    int insertSelective(FullreductionRecord record);

    /**
	 * 根据满减活动id查询用户满减明细
	 * @param recordId 领取记录id 
	 * @return FullreductionRecord 用户领取记录和用户信息
	 */
    FullreductionRecord selectByPrimaryKey(int recordId);

    int updateByPrimaryKeySelective(FullreductionRecord record);

    int updateByPrimaryKey(FullreductionRecord record);
    
    /**
     * 查询用户领取记录
	 * @param aid 满减活动id
	 * @return List<Map<String,Object>> 用户领取记录
	 * @throws SQLException
	 */
	List<Map<String,Object>> queryListByAid(int aid) throws SQLException;
	
	/**
	 * 统计活动总的免减金额
	 * @param aid 满减活动id
	 * @return BigDecimal 总的减免金额
	 * @throws SQLException
	 */
	BigDecimal countReductionAmountByAid(int aid);
	
	/**
	 * 统计活动总的参与人数
	 * @param aid 满减活动id
	 * @return int 总的参与人数
	 */
	int countJoinNumByAid(int aid);
	
	/**
	 * 统计活动总新绑定会员
	 * @param aid 满减活动id
	 * @return int 新会员数
	 */
	int countNewuserByAid(int aid);
	
}