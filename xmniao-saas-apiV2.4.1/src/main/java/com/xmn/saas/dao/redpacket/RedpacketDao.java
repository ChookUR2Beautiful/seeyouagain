package com.xmn.saas.dao.redpacket;

import java.util.List;
import java.util.Map;

import com.xmn.saas.entity.redpacket.Redpacket;


public interface RedpacketDao {
    int deleteByPrimaryKey(Long redpacketId);

    int insert(Redpacket record);

    int insertSelective(Redpacket record);

    Redpacket selectByPrimaryKey(Long redpacketId);

    int updateByPrimaryKey(Redpacket record);
    
    Redpacket findRedpacketBySellerid(Long sellerid);
    
    int updateByPrimaryKeyAndVersionLock(Redpacket redpacket);
    
    List<Redpacket> findRedpacketByParams(Map<String,Object> paramMap);
    
    Redpacket findRedpacketByOrderNo(String orderNo);
    
    /**
     * 
     * 方法描述：获取大转盘可选奖品
     * 创建人：jianming  
     * 创建时间：2016年10月10日 上午10:18:04   
     * @param sellerId
     * @return
     */
	List<Redpacket> listRoulleteAward(Integer sellerid);
	
	/**
	 * 
	 * 方法描述：锁定奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 下午6:04:20   
	 * @param awardId
	 */
	void setAward(Integer awardId);
	
	/**
	 * 
	 * 方法描述：获取营销活动奖品信息
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 上午10:37:02   
	 * @param id
	 * @param activityType
	 * @return
	 */
	List<Redpacket> getActivityAward(Integer activityId, Integer activityType);

    Redpacket selectRedpacketBySellerIdAndRedpacketTypeAndStatus(Redpacket param);

    List<Redpacket> selectExpiredRedpacketByEndDateAndStatus();

    int updateStatusById(Redpacket updateRedp);
}