package com.xmniao.xmn.core.api.controller.integral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.common.request.integral.PageTypeRequest;
import com.xmniao.xmn.core.integral.response.IntegralBillListResponse;
import com.xmniao.xmn.core.integral.service.BillFreshService;
import com.xmniao.xmn.core.integral.service.IntegralMallService;
import com.xmniao.xmn.core.market.entity.pay.ProductBill;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* 类名称：IntegralBillListApi   
* 类描述：   积分订单列表
* 创建人：xiaoxiong   
* 创建时间：2016年11月25日 上午9:42:38
 */

@Controller
@RequestMapping("integral/bill")
public class IntegralBillListApi implements BaseVControlInf{
	
	//报错日志
	private final Logger log = Logger.getLogger(IntegralBillListApi.class);
	
	//注入service
	@Autowired
	private BillFreshService billFreshService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@Autowired
	private SessionTokenService sessionService;

    @Autowired
    private PropertiesUtil propertiesUtil;

	@RequestMapping("/list")
	@ResponseBody
	public Object list(PageTypeRequest requet){
		List<ConstraintViolation> result = validator.validate(requet);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new MapResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(requet.getApiversion(), requet);
		
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
	}

}

	private Object versionControlOne(Object object) {
		try {
			PageTypeRequest request =(PageTypeRequest) object;
			String uid = sessionService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("")||uid.equals("null")){
				return new BaseResponse(ResponseCode.DATAERR, "token错误是错误或已过期，请重新登入！");
			}
			
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			Map<Object,Object> result = new HashMap<Object, Object>();
			
			Map<String,Object> params = new HashMap<>();
			params.put("page", request.getPage());
			params.put("uid", uid);
			params.put("pageSize",request.getPageSize());
			params.put("type",request.getType());
			/**
			 * 查询订单信息
			 */
			List<IntegralBillListResponse> list = billFreshService.queryBillFreshByUid(params);
			if(list!=null && list.size()>0){
				getBillProduct(list);
			}else{
				list = new ArrayList<>();
			}
			result.put("list", list);
			result.put("platformPhone", propertiesUtil.getValue("platform_telphone", "conf_common.properties"));
			mapResponse.setResponse(result);
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"错误");
	}
	
	/**
	 * 查询订单商品
	 * @author xiaoxiong
	 * @date 2016年11月25日
	 */
	private void getBillProduct(List<IntegralBillListResponse> list) {
		for(IntegralBillListResponse bill:list){
			try {
				List<ProductBill> productBillList = billFreshService.queryBillProductByBid(bill.getBid(),bill.getType());
				if(productBillList==null){
					productBillList = new ArrayList<>();
				}
				
				bill.setSubSet(productBillList);
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询订单商品失败：");
			}
		}
		
	}
}
