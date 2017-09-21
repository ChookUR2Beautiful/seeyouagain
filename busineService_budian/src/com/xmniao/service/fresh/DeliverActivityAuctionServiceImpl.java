package com.xmniao.service.fresh;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.ResponseState;
import com.xmniao.dao.order.BillActivityDao;
import com.xmniao.dao.order.FreshActivityAuctionDao;
import com.xmniao.dao.order.FreshActivityAuctionRecordDao;
import com.xmniao.domain.order.BillActivity;
import com.xmniao.domain.order.FreshActivityAuction;
import com.xmniao.domain.order.FreshActivityAuctionRecord;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.fresh.DeliverActivityAuctionService;



@Service("deliverActivityAuctionServiceImpl")
public class DeliverActivityAuctionServiceImpl implements DeliverActivityAuctionService.Iface {

    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(DeliverActivityAuctionServiceImpl.class);
	
	@Autowired
	private BillActivityDao billActivityDao;
	
	@Autowired
	FreshActivityAuctionDao freshActivityAuctionDao;
	
	
	@Autowired
	FreshActivityAuctionRecordDao freshActivityAuctionRecordDao;
	
	
	@Override
	public ResponseData updateBillActivityAuctionStatus(Map<String, String> paraMap) throws FailureException, TException {
		ResponseData responseData = new ResponseData();
		
		String pid = paraMap.get("orderNo");
		String paymoney = paraMap.get("paymoney");
		
		try {
			if (pid != null && !"".equals(pid)){
				log.info("DeliverActivityAuctionServiceImpl begin: 订单编号" + pid);
				
				BillActivity bean = billActivityDao.selectByPrimaryKey(pid.trim());
				if (bean != null && bean.getActivityType() == 2){
					//订半状态: 01待付款 , 02待发货,  03已发货 
					bean.setState(2);
					bean.setUpdateTime(new Date());
					//已支付金额
					BigDecimal receivedAmount = new BigDecimal(paymoney);  
					//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)   
					receivedAmount = receivedAmount.setScale(2, BigDecimal.ROUND_HALF_UP);  
					receivedAmount = receivedAmount.add(bean.getAuctionDeposit()); //加已支付的金额
					bean.setAmountReceived(receivedAmount);
					bean.setPayTime(new Date());
					
					billActivityDao.updateByPrimaryKey(bean);
					
					//竞拍活动中产品信息状态
					Integer activityId = bean.getActivityId().intValue();
					FreshActivityAuction freshActivityAuction = freshActivityAuctionDao.selectByPrimaryKey(activityId);
					if (freshActivityAuction != null) {
						// 产品状态product_status (0:竞拍中 1:竞拍成功(待支付尾款) 2:已退还库存(流拍) 3:已售出(已支付尾款) 4:已退还库存(超时未支付尾款))
						freshActivityAuction.setProductStatus(3);
						freshActivityAuction.setUpdateTime(new Date());
						freshActivityAuctionDao.updateByPrimaryKeySelective(freshActivityAuction);
					}
					
					//更改竞拍获奖人支付尾款状态
					FreshActivityAuctionRecord freshActivityAuctionRecord = freshActivityAuctionRecordDao.selectByActivityIdAndOrderId(activityId, pid);
					if (freshActivityAuctionRecord != null) {
						freshActivityAuctionRecord.setState(4);  //4已支付尾款
						freshActivityAuctionRecordDao.updateByPrimaryKeySelective(freshActivityAuctionRecord);
					}
					
					responseData.setState(0);
					responseData.setMsg("处理成功, 订单编号:["+pid+"] 订单状态已更新为待发货");
					
				    log.info("订单编号:["+pid+"]该订单已更新");
				}else{
					responseData.setMsg("处理失败, 订单编号:["+pid+"] 不是竞拍活动");
					responseData.setState(1);
				}
			
			}else{
				throw new Exception("处理失败，支付订单编号不能为空");
			}
			
		} catch (Exception e) {
//			responseData.setState(1);
//			responseData.setMsg("订单编号:["+pid+"]" + e.getMessage());
            log.error("活动订单更新异常", e);
            throw new FailureException(ResponseState.ORDERFAIL, e.getMessage());
		}
		
		log.info("DeliverActivityAuctionServiceImpl end: 订单编号" + pid);
		
		return responseData;
	}

}
