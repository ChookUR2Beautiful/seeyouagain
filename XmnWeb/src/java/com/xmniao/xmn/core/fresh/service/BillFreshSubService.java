package com.xmniao.xmn.core.fresh.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.BillFreshSubDao;
import com.xmniao.xmn.core.fresh.dao.SaleGroupDao;
import com.xmniao.xmn.core.fresh.entity.TBillFreshSub;
import com.xmniao.xmn.core.fresh.entity.TProductBill;
import com.xmniao.xmn.core.fresh.entity.TSaleGroup;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.payRefundService.FreshRefundService;
import com.xmniao.xmn.core.thrift.service.payRefundService.RefundRequest;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 项目名称： XmnWeb
 * 类名称： BillFreshSubService.java
 * 类描述：子订单Service
 * 创建人： lifeng
 * 创建时间： 2016年6月24日下午2:09:31
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Service
public class BillFreshSubService extends BaseService<TBillFreshSub> {
	
	@Autowired
	private BillFreshSubDao billFreshSubDao;
	
	@Autowired
	private SaleGroupDao saleGroupDao;

	@Override
	protected BaseDao<TBillFreshSub> getBaseDao() {
		return billFreshSubDao;
	}
	
	@Resource(name = "freshRefundServiceClient")
	private ThriftClientProxy freshSubRefundServiceClient;

	/**
	 * @Description: 列表
	 * @Param:
	 * @return:List<TBillFreshSub>
	 * @author:lifeng
	 * @time:2016年6月24日下午2:13:59
	 */
	public List<TBillFreshSub> getTBillFreshSubList(TBillFreshSub tbillFreshSub) {
		List<TBillFreshSub> tBillFreshSubList = billFreshSubDao.getTBillFreshSubList(tbillFreshSub);
		return tBillFreshSubList;
	}

	/**
	 * @Description: 总记录数
	 * @Param:
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年6月24日下午2:14:19
	 */
	public Long tbillFreshSubCount(TBillFreshSub tbillFreshSub) {
		return billFreshSubDao.tbillFreshSubCount(tbillFreshSub);
	}

	/**
	 * @Description: 根据id查询子订单的信息
	 * @Param:
	 * @return:TBillFreshSub
	 * @author:lifeng
	 * @time:2016年6月24日下午4:21:20
	 */
	public TBillFreshSub getBillFreshSub(Long id) {
		TBillFreshSub tBillFreshSub = new TBillFreshSub();
		tBillFreshSub.setId(id);
		return billFreshSubDao.getBillFreshSub(tBillFreshSub);
	}
	
	/**
	 * @Description: 根据子订单编号获取商品列表
	 * @Param:
	 * @return:TBillFreshSub
	 * @author:lifeng
	 * @time:2016年6月24日下午4:21:20
	 */
	public List<TProductBill> getBillFreshSubProd(Map<Object, Object> map) {
		List<TProductBill> billFreshSubProdBills = billFreshSubDao.getBillFreshSubProd(map);
		for (TProductBill tProductBill : billFreshSubProdBills) {
			if(tProductBill.getAttrId()!=null){
				TSaleGroup saleGroup = saleGroupDao.getObject(tProductBill.getAttrId().longValue());
				if(saleGroup!=null){
					tProductBill.setStock(saleGroup.getStock());
				}
			}
		}
		return billFreshSubProdBills;
	}
	
	/**
	 * @Title:getBillFresh
	 * @Description:根据生鲜订单编号查询订单详情(批量查询)
	 * @param bid
	 * @return TBillFresh
	 * @throw
	 */
	public List<TBillFreshSub> getBillFreshSubList(Object[] bids){		
		List<TBillFreshSub> tbillFreshList = billFreshSubDao.getBillFreshSubList(bids);
		return tbillFreshList;
	}

	/**
	 * @Description: 导出子订单
	 * @Param:tbillFreshSub
	 * @return:List<TBillFreshSub>
	 * @author:lifeng
	 * @time:2016年6月25日上午9:13:39
	 */
	public List<TBillFreshSub> getBillFreshSubForExport(TBillFreshSub tbillFreshSub) {
		return billFreshSubDao.getBillFreshSubForExport(tbillFreshSub);
	}

	/**
	 * @Description: 检查是否有选中条件的订单
	 * @Param:tbillFreshSub
	 * @return:Boolean
	 * @author:lifeng
	 * @time:2016年6月25日上午9:14:02
	 */
	public Boolean checkdata(TBillFreshSub tbillFreshSub) {
		Boolean flag = false;
		Integer num = billFreshSubDao.checkdata(tbillFreshSub);
		if(num > 0){
			flag = true;
		}
		return flag;
	}

	/**
	 * @Description: 订单发货
	 * @Param:paramMap
	 * @return:Map<String,Object>
	 * @author:lifeng
	 * @time:2016年6月25日上午9:14:46
	 */
	public Map<String, Object> updateSubStatus(Map<String, Object> paramMap) {
		Map<String,Object> result = new HashMap<String,Object>();
		//获取当前系统时间放入paraMap作为发货时间
		Date now = new Date();
		String fhdate = DateUtil.smartFormat(now);
		paramMap.put("deliveryTime", fhdate);
		//更新结果信息以map类型返回
		billFreshSubDao.updateSubCourier(paramMap);
		result.put("info","更新成功");
		return result;
	}

	/**
	 * @Description: 调用接口，订单退款
	 * @Param:
	 * @return:Map<String,String>
	 * @author:lifeng
	 * @time:2016年6月25日上午9:15:20
	 */
	public Map<String, String> freshSubRefund(RefundRequest refundRequest) {
		FreshRefundService.Client client = (FreshRefundService.Client)(freshSubRefundServiceClient.getClient());//支付服务
		Map<String, String> result = new HashMap<String,String>();
		try {
			result = client.FreshRefund(refundRequest);
		} catch (Exception e) {
			log.error("退款失败", e);
		} finally {
			freshSubRefundServiceClient.returnCon();
		}
		return result;
	}

	/**
	 * @Description: 订单确认收货
	 * @Param:paramMap
	 * @return:Map<String,Object>
	 * @author:lifeng
	 * @time:2016年6月25日上午9:15:40
	 */
	public Map<String, Object> confirmReceive(Map<String, Object> paramMap) {
		Map<String,Object> result = new HashMap<String,Object>();
		paramMap.put("status", 6);//确认收货，订单已完成，更新状态为6
		paramMap.put("modifyTime", DateUtil.smartFormat(new Date()));//设置订单修改时间为当前的时间
		paramMap.put("memo", "平台确认收货");
		//更新结果信息以map类型返回
		billFreshSubDao.updateSubReceive(paramMap);
		result.put("info","更新成功");
		return result;
	}

}
