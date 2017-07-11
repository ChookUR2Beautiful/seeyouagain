package com.xmn.saas.service.bill;

import java.util.List;
import java.util.Map;

import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.base.thrift.common.ResponseList;
import com.xmn.saas.entity.bill.Bagin;
import com.xmn.saas.entity.bill.Bill;
import com.xmn.saas.entity.bill.BillList;
import com.xmn.saas.entity.bill.Coupon;
import com.xmn.saas.entity.common.SellerAccount;


public interface BillService {
    /**
     * 获取钱包的分账记录列表
     */
    public ResponseList getXmnWalletLedgerList(Map<String, String> map);


    /**
     * 账单列表，根据条件（时间、营收、收益、支出）筛选
     */
    public Map<Object, Object> list(Map<String, String> map);


    /**
     * 账单详情
     */
    public Map<Object, Object> detail(Map<String, String> map);

    /**
     * 取商户的分账信息
     */
    public ResponseData getSellerLedgerInfo(Map<String, String> map);

    /**
     * 获取用户信息
     * 
     * @param paramMap
     * @return
     */
    public ResponseData getUserMsg(Map<String, String> paramMap);

    /**
     * 
     * @Description: 手动验证
     * @author xiaoxiong
     * @throws Exception
     * @date 2016年10月14日
     */
    public void vertify(String bid, Integer coupontype) throws Exception;

    /**
     * 
     * @Description: 根据订单好查询订单信息
     * @author xiaoxiong
     * @date 2016年10月14日
     */
    public Bill selectBillByBid(String bid);


    /**
     * 
     * @Description: 根据验证码和商家ID获取订单信息
     * @author xiaoxiong
     * @date 2016年10月21日
     */
    public Bill selectBillByCodeidAndSellerid(String codeid, int sellerid);

    /**
     * 
     * @Description: 根据消费验证码和商户ID查询积分订单信息
     * @author xiaoxiong
     * @date 2016年10月21日
     */
    public Bagin selectBarginBycodeidAndSellerid(String codeid, int sellerid);


    /**
     * 
     * @Description: 验证爆品订单
     * @author xiaoxiong
     * @date 2016年10月21日
     */
    public void vertifyBargin(String string, int sellerid, int bpid);

    /**
     * 
     * @Description:根据商户ID和验证码查询优惠券信息
     * @author xiaoxiong
     * @date 2016年10月21日
     */
    public Coupon selectCouponByCodeidAndSellerid(String codeid, int sellerid);


    /**
     * 
     * @Description: 验证赠送券
     * @author xiaoxiong
     * @date 2016年10月22日
     */
    public void vertifyCoupon(int cuid);

    /**
     * 获取商户历史支出列表
     * 
     * @param map
     * @return
     */
    public ResponseList getSellerExpenseList(Map<String, String> map);

    public List<Bill> findFansList(Map<String, String> params);

    public List<String> findFansCountDate(Map<String, String> params);

    public Map<Object, Object> list(int page, int rows,int sellerid,int type) throws Exception;
    
    public Double getAmount(Bill bill);
    
    
    /**
     * 获取订单分账及消费信息
     * 
     */
    public ResponseData getOrderLedgerInfo(String id,String btype);
    
    /**
     * 获取订单分账及消费信息中的实际支付金额
     * @param bid
     * @return
     */
    public Double getOderInfoAmount(String bid,String btype);
    
    /**
     * 获取订单分账及消费信息中的实际营收金额
     * @param bid
     * @param type 1 账单流水  2 秒杀流水
     * @return
     */
    public Double getOderInfoSelleramount(BillList bill);
    
    //获取批量获取订单分账及消费信息中的实际支付金额
    public Double getOrderLedgerInfoListSellerAmount(List<Map<String,String>> paraList);

    /**
     * 查询优惠卷信息
     * @author xiaoxiong
     * @date 2016年12月12日
     */
	public Coupon queryCouponByCodeidAndSellerid(String codeid, int sellerid);

	/**
	 * 查询商家优惠卷
	 * @author xiaoxiong
	 * @date 2016年12月12日
	 */
	public void vertifySellerCoupon(int cuid);
	
	/**
	 * 调用分账获取信息
	 * @author xiaoxiong
	 * @date 2016年12月13日
	 */
	public Map<String, String> getOrderLedgerInfo(String string);
	
	
	 /**
	  * 储值消费、充值历史
	  * @param map
	  * @return
	  */
    public Map<String, Object> getValueCardList(Map<String, String> map);
    
    /**
     * 1.11.5.  获取商户充值会员列表
     * @param map
     * @return
     */
   public Map<String, Object> getUserList(Map<String, String> map);
   
   /**
    * 1.11.6获取商户充值会员详情
    * @param map
    * @return
    */
   public Map<String, Object> getUserDetail(Map<String, String> map);
   
   
   public  Map<String, Object> billConverMap(Bill bill);
   /**
    * 美食验单
    * @param codeid
    * @param sellerid
    * @return
    */
   public boolean virtifyBill(String codeid,int sellerid);
   /**
    * 商家赠送券
    * @param codeid
    * @param sellerid
    * @return
    */
   public boolean virtifySellercoupon(String codeid,int sellerid);
   /**
    * 粉丝券
    * @param codeid
    * @param sellerid
    * @return
    */
   public boolean virtifycoupon(SellerAccount sellerAccount,String codeid);
   
   /**
    * 验证储值卡套餐
    * @param sellerAccount
    * @param codeid
    * @return
    */
   public boolean virtifySellerCard(SellerAccount sellerAccount, String codeid);
   
   /**
    * 获取商户累计充值，累计剩余，充值会员
    * @param sellerAccount
    * @return
    */
   public Map<String, Object> getValueCardBalance(SellerAccount sellerAccount) ;
   
   /**
    * 判断是否普通商家
    * @param sellerAccount
    * @return
    */
   public Map<Object, Object> isNormal(SellerAccount sellerAccount);

}
