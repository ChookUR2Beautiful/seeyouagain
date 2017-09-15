package com.xmniao.xmn.core.wealth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.RefundRequest;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.wealth.dao.RefundDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：RefundService   
* 类描述：   退款Service
* 创建人：xiaoxiong   
* 创建时间：2016年6月20日 下午6:29:23   
* @version    
*
 */
@Service
public class RefundService {
	
	@Autowired
	private RefundDao refundDao;
	
	@Autowired
	private BillDao billDao;
	


	public Object refund(RefundRequest request) {
		try {
			//查询订单
			Integer verifyBean=billDao.getBillBargainById(request);
			if(verifyBean!=null&&verifyBean!=0){
				if(verifyBean==1){//但状态等于1的时候才可以退款
				int count=refundDao.queryRefundBybid(request.getBid());//查询有没有该订单记录	
				if(count==0){
					Map<Object,Object> refundMap = new HashMap<Object,Object>();
					refundMap.put("bid", request.getBid());
					refundMap.put("sellerid", request.getSellerid());
					refundMap.put("sellername", request.getSellername());
					refundMap.put("apply", request.getApply());
					refundMap.put("source", request.getSource());
					refundMap.put("sdate", DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
					refundDao.addRefund(refundMap);
					billDao.updateStatusByBid(request.getBid());
					return new BaseResponse(ResponseCode.SUCCESS, "成功");
				}else{
					return new BaseResponse(ResponseCode.BILL_IS_APPLY, "该订单已申请过退款，不能重复申请，如有问题请联系客户。");
				}
				}else{
				return new BaseResponse(ResponseCode.BILL_STATUS_FAILE, "订单状态错误,改订单状态不可申请退款");	
				}
			}else{
				return new BaseResponse(ResponseCode.BILL_NO_NOT_EXIST,"订单号错误，没有找到改订单信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "失败");
	}

	
	

}
