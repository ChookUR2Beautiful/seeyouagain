package com.xmniao.xmn.core.marketingmanagement.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.marketingmanagement.dao.PhoneBillDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TPhoneBill;
import com.xmniao.xmn.core.util.HttpUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TActivityService
 * 
 * 类描述：营销活动管理
 * 
 * 创建人： yang'xu
 * 
 * 创建时间：2015年01月14日10时15分24秒
 * 
 * Copyright©广东寻蜜鸟网络技术有限公司
 */
@Service
public class PhoneBillService extends BaseService<TPhoneBill> {
	
	/**
	 * iphone6物流接口参数配置
	 */
	private static final String iPhone6OrderUrl = PropertiesUtil.readValue("http.iPhone6Order.url");
	
	@Autowired
	public PhoneBillDao phoneBillDao;

	@Override
	protected BaseDao getBaseDao() {
		return phoneBillDao;
	}
	
	/**
	 * 查询统计总数
	 * @param tphoneBill
	 * @return
	 */
	public TPhoneBill  getCountList(TPhoneBill tphoneBill){
		TPhoneBill countInfo = phoneBillDao.getCountList(tphoneBill);
		return countInfo;
	};
	
	
	/**
	 * 查看订单详情
	 * @param tphoneBill
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView viewOrder(TPhoneBill tphoneBill){
		ModelAndView  mv = new ModelAndView();
		TPhoneBill order = new TPhoneBill();
		Map<String,Object> logistics = new HashMap<String,Object>();
		
		order = phoneBillDao.getObject(tphoneBill.getPid());
		
		try {
			this.log.info("电信接口查询物流信息 参数："+order.getCode());
			logistics = HttpUtil.getInstance().getForMap(iPhone6OrderUrl + order.getCode());
		} catch (Exception e) {
			this.log.error("电信接口查询物流信息错误："+e);
		}
		mv.addObject("order", order);
		mv.addObject("logistics", logistics);
		mv.setViewName("marketingManagement/phoneBill/viewOrderDetailed");
		return mv;
	}
}
