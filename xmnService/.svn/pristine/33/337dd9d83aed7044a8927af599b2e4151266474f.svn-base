package com.xmniao.xmn.core.timer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.market.dao.FreshActivityGroupDao;
import com.xmniao.xmn.core.market.dao.MarketBillFreshDao;
import com.xmniao.xmn.core.market.dao.ProductBillDao;
import com.xmniao.xmn.core.market.dao.SaleGroupDao;
import com.xmniao.xmn.core.market.entity.pay.BillFresh;
import com.xmniao.xmn.core.market.entity.pay.ProductBill;

/**
 * 
* @projectName: xmnService 
* @ClassName: ClearIntegralOrderQuertz    
* @Description:定时清除未支付的积分订单   
* @author: liuzhihao   
* @date: 2016年12月28日 上午9:45:25
 */
@Service
public class ClearIntegralOrderQuertz {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ClearIntegralOrderQuertz.class);
	@Autowired
	private MarketBillFreshDao marketBillFreshDao;
	
	@Autowired
	private ProductBillDao productBillDao;
	
	@Autowired
	private SaleGroupDao saleGroupDao;
	
	@Autowired
	private FreshActivityGroupDao freshActivityGroupDao;
	
	public void clearIntegralOrder(){
		//查询所有待支付和取消订单的订单信息
		
		List<BillFresh> billFreshs = marketBillFreshDao.findAllByQuertz();
		
		if(billFreshs != null && !billFreshs.isEmpty()){
			
			for(BillFresh billFresh : billFreshs){
				
				//撤销订单
//				OrderNoRequest onr = new OrderNoRequest();
//				onr.setBid(billFresh.getBid());
				
				
				//修改未支付的订单状态
				billFresh.setStatus(2);//取消订单
				
				try{
					marketBillFreshDao.updateByPrimaryKeySelective(billFresh);
					log.info("定时修改订单的状态为2取消订单");
				}catch(Exception e){
					e.printStackTrace();
					log.info("更新订单状态异常");
				}
				
				//把原来扣除的库存还回去
				
				//通过订单ID查询商品订单详情
				
				Long bid = billFresh.getBid();
				try{
					List<ProductBill> productBills = productBillDao.findAllByBid(bid);
					
					if(productBills != null && !productBills.isEmpty()){
						
						for(ProductBill productBill : productBills){
							
							Integer buys = billFresh.getWarenum();//用户购买的商品数量
							
							Integer activityId = productBill.getActivityid();//活动ID
							
							Integer codeId = productBill.getCodeid();//商品ID
							
							String attrids = productBill.getAttrids();//商品规格ID组合
							
							if(activityId != null){
								//非活动
								//还会非活动的库存
							
								try{
									saleGroupDao.updateExceptionStock(codeId, attrids, buys);
									log.info("还活动库存");
								}catch(Exception e){
									e.printStackTrace();
									log.info("还库存异常");
								}
								
							}else{
								//活动
								try{
									freshActivityGroupDao.updateExceptionStock(activityId, codeId.longValue(), attrids, buys);
									log.info("还非活动库存");
								}catch(Exception e){
									e.printStackTrace();
									log.info("还库存异常");
								}
							}
						}
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
					log.info("查询订单商品详情异常");
				}
				
				
				
			}
		}
	}
}
