package com.xmn.saas.dao.activity;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmn.saas.controller.h5.coupon.vo.QueryCondition;
import com.xmn.saas.entity.activity.Fullreduction;

@Repository
public interface FullreductionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Fullreduction record);

    int insertSelective(Fullreduction record);

    Fullreduction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fullreduction record);

    int updateByPrimaryKey(Fullreduction record);
    
    /**
   	 * 根据商户id以及活动时间查询商户当前满减列表信息
   	 * @param sellerId商户id
   	 * @param status 活动状态
   	 * @return List<Fullreduction>    返回类型
   	 * @throws SQLException
   	 */
   	List<Fullreduction> queryListBySerllIdAndDate(QueryCondition query) throws SQLException;
}