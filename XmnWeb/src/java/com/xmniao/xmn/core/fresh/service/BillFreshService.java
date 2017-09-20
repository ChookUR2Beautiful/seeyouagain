package com.xmniao.xmn.core.fresh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.BillFreshDao;
import com.xmniao.xmn.core.fresh.entity.TBillFresh;
import com.xmniao.xmn.core.fresh.entity.TBillFreshSub;
import com.xmniao.xmn.core.fresh.entity.Texpress;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.payRefundService.FreshRefundService;
import com.xmniao.xmn.core.thrift.service.payRefundService.RefundRequest;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * @ClassName:BillFreshService
 * @Description:生鲜订单service层
 * @author hls
 * @date:2016年1月5日下午4:05:10
 */
@Service
public class BillFreshService extends BaseService<TBillFresh>{ 

	@Autowired
	private BillFreshDao billfreshdao;
	@Override
	protected BaseDao<TBillFresh> getBaseDao() {
		return billfreshdao;
	}
	@Resource(name = "freshRefundServiceClient")
	private ThriftClientProxy freshRefundServiceClient;
	/**
	 * @Title:getFreshList
	 * @Description:查询生鲜订单列表
	 * @param paraMap
	 * @return TBillFresh
	 * @throw
	 */
	public List<TBillFresh> selectTBillFreshInfoList(TBillFresh tbillFresh){
		//tbillFresh.setStatus(0);//只显示待支付的订单
		return billfreshdao.getFreshList(tbillFresh);
	}
	/**
	 * 
	 * @Title:tbillFreshInfoCount
	 * @Description:获取生鲜订单总数
	 * @param tbillFresh
	 * @return long
	 * @throw
	 */
	public long tbillFreshInfoCount(TBillFresh tbillFresh){
		return billfreshdao.getFreshCount(tbillFresh);
	}
	/**
	 * @Title:updateWstatus
	 * @Description:发货操作更新订单状态、发货时间、物流订单号
	 * @param paraMap void
	 * @throw
	 */
	public Map<String,Object> updateWstatus(Map<String,Object> paraMap){
		Map<String,Object> result = new HashMap<String,Object>();
		//获取当前系统时间放入paraMap作为发货时间
		Date now = new Date();
		String ddate = DateUtil.smartFormat(now);
		paraMap.put("ddate", ddate);
		//更新结果信息以map类型返回
		billfreshdao.updateWstatus(paraMap);
		result.put("info","更新成功");
		return result;
	}
	
	/**
	 * @Title:freshRefund
	 * @Description:调用外部退款接口
	 * @param refundRequest
	 * @return Map<String,String>
	 * @throw
	 */
	public Map<String,String> freshRefund(RefundRequest refundRequest){
		FreshRefundService.Client client = (FreshRefundService.Client)(freshRefundServiceClient.getClient());//支付服务
		Map<String, String> result = new HashMap<String,String>();
		try {
			result = client.FreshRefund(refundRequest);
		} catch (Exception e) {
			log.error("退款失败", e);
		} finally {
			freshRefundServiceClient.returnCon();
		}
		return result;
	}
	/**
	 * @Title:getBillFresh
	 * @Description:根据生鲜订单编号查询订单详情
	 * @param bid
	 * @return TBillFresh
	 * @throw
	 */
	public TBillFresh getBillFresh(Long bid){		
		TBillFresh tbillFresh = billfreshdao.getBillFresh(bid);
		return tbillFresh;
	}
	
	/**
	 * @Title:getBillFresh
	 * @Description:根据生鲜订单编号查询订单详情(批量查询)
	 * @param bid
	 * @return TBillFresh
	 * @throw
	 */
	public List<TBillFresh> getBillFreshList(Object[] bids){		
		List<TBillFresh> tbillFreshList = billfreshdao.getBillFreshList(bids);
		return tbillFreshList;
	}
	
	
	/**
	 * @Title:getBillFreshForExport
	 * @Description:根据时间获取订单数据
	 * @return List<TBillFresh>
	 * @throw
	 */
	public List<TBillFresh> getBillFreshForExport(TBillFresh billFresh){
		List<TBillFresh> list = billfreshdao.getBillFreshForExport(billFresh);
		return list;
	}
	
	/**
	 * @Title:getBillFreshForExport
	 * @Description:根据时间获取订单导出数据（父订单）
	 * @return List<TBillFresh>
	 * @throw
	 */
	public List<TBillFresh> getBillFreshForExport1(TBillFresh billFresh){
		List<TBillFresh> list = billfreshdao.getBillFreshForExport1(billFresh);
		return list;
	}
	/**
	 * @Title:checkdata
	 * @Description:判断是否有订单数据
	 * @param billFresh
	 * @return Boolean
	 * @throw
	 */
	public Boolean checkdata(TBillFresh billFresh){
		Boolean flag = false;
		Integer num = billfreshdao.checkdata(billFresh);
		if(num > 0){
			flag = true;
		}
		return flag;
	}
	/**
	 * @Title:getExpressList
	 * @Description:获取快递方式列表
	 * @return List<Texpress>
	 * @throw
	 */
	public List<Texpress> getExpressList(){
		List<Texpress> list = billfreshdao.getExpressList();
		return list;
	}
	/**
	 * 方法描述：统计一个月内订单数
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月15日上午10:21:52 <br/>
	 * @param specifiedDate 
	 * @return
	 */
	public List<Map<String, Object>> countOrderNum(String specifiedDate) {
		 try {
			List<Map<String,Object>> data = billfreshdao.countOrderNum(specifiedDate);
			 String dateStr = (String) data.get(0).get("date");
				Date date = DateUtil.formatStringToDate(dateStr, "");
				return recursionDateCount(data,date);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月15日下午3:10:03 <br/>
	 * @param data
	 * @param now 
	 * @param date 
	 * @return
	 * @throws Exception 
	 */
	private List<Map<String, Object>> recursionDateCount(List<Map<String, Object>> data, Date date) throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		int j=0;
		Map<String, Object> mapPojo = data.get(j);
		while(!DateUtil.isSameDate(DateUtil.getNearbyDate(date,-1),new Date())) {
			Map<String, Object> map=new HashMap<>();
			Date date2 = DateUtil.formatStringToDate((String)mapPojo.get("date"),"");
			if(DateUtil.isSameDate(date,date2)){
				list.add(mapPojo);
				if(j<data.size()-1){
					mapPojo = data.get(++j);
				}
			}else{
				map.put("date", DateUtil.formatDate(DateUtil.Y_M_D,date));
				map.put("num",0);
				map.put("wareNum", 0);
				map.put("money", 0.00);
				list.add(map);
			}
			date=DateUtil.getNearbyDate(date,1);
		}
		return list;
	}
	/**
	 * 方法描述：获得某天的订单总数
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月15日下午4:35:06 <br/>
	 * @param formatDate
	 * @return
	 */
	public Map<String, Object> getOrderByDay(String date) {
		return billfreshdao.getOrderByDay(date);
	}
	
	
}
