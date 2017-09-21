package com.xmniao.dao.common;

import java.util.Map;

import com.xmniao.domain.message.MessageRequest;
import com.xmniao.domain.push.PushMsgRequest;
import com.xmniao.thrift.ledger.ResponseSplitMap;
import com.xmniao.thrift.pay.ResponseData;

/**
 * 通用服务接口类
 * @author  LiBingBing
 * @version  [版本号, 2014年11月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CommonServiceDao
{
    
    /**
     * 发送短信接口
     * @param request [请求参数]
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String sendSms(MessageRequest request);
    
    /**
     * 用户中心服务接口修改用户信息
     * @param paramMap [请求参数]
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String modifyUserCenter(Map<String,Object> paramMap);
    
    /**
     * 用户中心服务的更新用户向蜜客时间
     * @param paramMap [请求参数]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String modifyUserMikeTime(Map<String,String> paramMap);
    
    /**
     * 分账系统支付服务的修改钱包余额(分账接口)
     * @param reqMap [请求参数]
     * @return int [返回是否成功标识]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int modifyWalletBalance(Map<String,String> reqMap);
    
    /**
     * 消息推送服务
     * @param req [请求参数的JSON数据]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String pushMessage(PushMsgRequest reqJson);
    
    /**
     * 采用HTTP方式发送Redis
     * @param connUrl [http发送redis的URL地址]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String sendHttpRedis(String connUrl);
    
    /**
     * 线下积分商品订单的分账计算公式
     * @Title: offlineMallLedgerFormula 
     * @Description:
     */
    public ResponseSplitMap offlineMallLedgerFormula(double purchaseMoney,double profitRate,boolean isBseller,boolean isSellerArea);
    
    /**
     * 线上积分商品订单的分账计算公式
     * @Title: offlineMallLedgerFormula 
     * @Description:
     */
    public ResponseSplitMap onlineMallLedgerFormula(double purchaseMoney,
			double sellMoney,double payment,boolean isBseller);
    
    /**
     * SAAS签约订单的分账计算公式
     * @Title: offlineMallLedgerFormula 
     * @Description:
     */
    public ResponseSplitMap saasSoldLedgerFormula(double saasPricee,
			boolean isReturn,int type,double discount,boolean isSellerArea,
			boolean isAgentscope,double purchaseDiscount,boolean hasGrandfatherXmer,boolean hasFatherXmer);
    
    /**
     * 调用支付服务添加寻蜜客钱包
     * @param reqMap
     * @return
     */
	public int addXmerWallet(Map<String, String> reqMap);

	/**
	 * 
	 * 方法描述：调用支付服务更新直播钱包
	 * 创建人： ChenBo
	 * 创建时间：2016年12月22日
	 * @param reqMap
	 * @return ResponseData
	 */
	ResponseData updateLiveWalletReturnResponse(Map<String, String> reqMap);
	
	/**
	 * 方法描述：调用支付服务更新直播钱包
	 * 创建人： chenJie
	 * 创建时间：2016年8月22日下午2:52:27
	 * @param reqMap
	 * @return
	 */
	boolean updateLiveWallet(Map<String, String> reqMap);

	/**
	 * 方法描述：查询该用户是否存在直播钱包
	 * 创建人： chenJie
	 * 创建时间：2016年9月2日下午2:15:44
	 * @param reqMap
	 * @return
	 */
	boolean checkLiveWallet(Map<String, String> reqMap);
	
	/**
	 * 添加用户钱包
	 * @param uId
	 * @param userType
	 * @return
	 */
	boolean addWallet(String uId, String userType);
	
	/**
	 * 获取商家提现与分账模式
	 * @Title: getSellerMentionLedger 
	 * @Description:
	 */
	public Map<String,String> getSellerMentionLedger(int sellerid,int type);
	
	/**
	 * 
	 * 方法描述：更新用户的商家储值卡
	 * 创建人： ChenBo
	 * 创建时间：2017年3月1日
	 * @param reqMap
	 * @return ResponseData
	 */
	public boolean updateDebitcardQuota(Map<String,String> reqMap);
}