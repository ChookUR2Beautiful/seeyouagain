package com.xmn.saas.dao.bill;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.bill.Bagin;
import com.xmn.saas.entity.bill.Bill;
import com.xmn.saas.entity.bill.BillList;
import com.xmn.saas.entity.bill.BillRecord;
import com.xmn.saas.entity.bill.Coupon;

public interface BillDao {
    int deleteByPrimaryKey(Long bid);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Long bid);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKeyWithBLOBs(Bill record);

    int updateByPrimaryKey(Bill record);

    Map<Object, Object> findMember(Map<String, String> params);

    List<BillList> findBillCount(Map<String, String> params);
    
    List<Bill> findLoginBillCount(Map<String, String> params);

    List<BillList> findBillList(Map<String, String> params);
    
    List<Bill> findLoginBillList(Map<String, String> params);
    
    List<Bill> findFansList(Map<String, String> params);

    List<String> findFansCountDate(Map<String, String> params);
    

    int findConsumTimes(Map<String, String> params);

    BillList findBillDetail(Map<String, String> params);
    
	Bill selectBillByBid(String bid);

	void inserBillRecord(BillRecord billRecord);

	void modifyHstatus(Bill bill);

	Bill selectBillByCodeidAndSellerid(@Param("codeid")String codeid,@Param("sellerid")int sellerid);

	Bagin selectBarginBycodeidAndSellerid(@Param("codeid")String codeid,@Param("sellerid")int sellerid);
	
	void modifyBarginBycodeid(@Param("bid")String bid,@Param("sellerid")int sellerid);

	void modifyBarginCount(int bpid);
	
	Coupon selectCouponByCodeidAndSellerid(@Param("codeid")String codeid, @Param("sellerid")int sellerid);

	void modifyCouponSellerStatusByCuid(@Param("cuid")int cuid, @Param("date")Date date);
	
	List<String> findCountDate(Map<String, String> params);
	
	List<String> findLoginCountDate(Map<String, String> params);
	
	List<String> findCountDateSum(Map<String, String> params);
	
	/**
	 * 
	 * 方法描述：统计满减活动参与人数
	 * 创建人：jianming  
	 * 创建时间：2016年11月9日 上午10:57:48   
	 * @param couponId
	 * @return
	 */
	Integer countFullJoinNum(Integer couponId);
	
	/**
	 * 
	 * 方法描述：查询首次消费时间
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 上午11:21:40   
	 * @param sellerId 
	 * @param uid 
	 * @return
	 */
	Date selectFirstConsume(@Param("uid")Integer uid, @Param("sellerid")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询最近消费时间
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 上午11:42:27   
	 * @param uid
	 * @param sellerId
	 * @return
	 */
	Date selectLastConsume(@Param("uid")Integer uid, @Param("sellerid")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：总消费次数
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 上午11:47:35   
	 * @param uid
	 * @param sellerId
	 * @return
	 */
	Integer CountConsume(@Param("uid")Integer uid, @Param("sellerid")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：消费总额
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 上午11:51:32   
	 * @param uid
	 * @param sellerId
	 * @return
	 */
	BigDecimal sumConsume(@Param("uid")Integer uid, @Param("sellerid")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：最高消费
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 上午11:54:44   
	 * @param uid
	 * @param sellerId
	 * @return
	 */
	BigDecimal maxConsume(@Param("uid")Integer uid, @Param("sellerid")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：最低消费
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 上午11:56:14   
	 * @param uid
	 * @param sellerId
	 * @return
	 */
	BigDecimal minConsume(@Param("uid")Integer uid, @Param("sellerid")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：平均消费
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 上午11:57:36   
	 * @param uid
	 * @param sellerId
	 * @return
	 */
	BigDecimal avgConsume(@Param("uid")Integer uid, @Param("sellerid")Integer sellerId);
	
	/**
	 * 查询粉丝卷订单
	 * @author xiaoxiong
	 * @date 2016年12月12日
	 */
	Coupon queryCouponByCodeidAndSellerid(@Param("codeid")String codeid,@Param("sellerid")int sellerid);
	
	/**
	 * 修改粉丝卷
	 * @author xiaoxiong
	 * @date 2016年12月12日
	 */
	void modifyCouponStatusByCuid(@Param("cdid")int cdid, @Param("date")Date date);
	


}
