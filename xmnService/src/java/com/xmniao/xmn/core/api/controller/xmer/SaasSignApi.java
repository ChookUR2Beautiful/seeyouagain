/**
 * 2016年5月23日 下午4:01:06
 */
package com.xmniao.xmn.core.api.controller.xmer;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SaasSignRequest;
import com.xmniao.xmn.core.xmer.service.SaasOrderService;

/**
 * @项目名称：xmnService
 * @类名称：SaasSignApi
 * @类描述：商户签约API
 * @创建人： zhangchangyuan
 * @创建时间 2016年5月23日 下午4:01:06
 * @version
 */
@Controller
public class SaasSignApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SaasSignApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private SaasOrderService saasOrderService;
	
	private Long sellerCount = 10L; 
	
	
	@RequestMapping(value="/saasSign",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object saasSign(SaasSignRequest saasSignRequest){
		log.info("saasSign参数:"+saasSignRequest.toString());
		List<ConstraintViolation> result = validator.validate(saasSignRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		
		//系统版本：急救方案
		String systemversion = saasSignRequest.getSystemversion().toLowerCase();
		Integer apiversion = saasSignRequest.getApiversion();
		
		if (systemversion.contains("ios") && apiversion == 1) {
			return versionControl(2,saasSignRequest);
		}
		return versionControl(saasSignRequest.getApiversion(),saasSignRequest);
		
	}
	
	public Object versionOne(Object obj){
		SaasSignRequest saasSignRequest = (SaasSignRequest) obj;
		return saasOrderService.createSaasOrder(saasSignRequest);
		
		
	}
	
	public Object versionTwo(Object obj){
		SaasSignRequest saasSignRequest = (SaasSignRequest) obj;
		return saasOrderService.createSaasOrderTwo(saasSignRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.xmniao.xmn.core.base.BaseVControlInf#versionControl(int, java.lang.Object)
	 */
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionOne(object);
		case 2: return versionTwo(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
	}
}	
	
	@RequestMapping(value="/sellerPressureTest")
	@ResponseBody
	public Object sellerPressureTest(){
		SaasSignRequest request = new SaasSignRequest();
		request.setSellername("测试店铺" + sellerCount);//	商铺名称
		request.setProvince("广东省");//省名称
		request.setCity("广州市");//市名称
		request.setArea("天河区");//区名称
		request.setAddress("石牌东路" + sellerCount + "号");//详细地址
		request.setCategory(1);//经营类型ID
		request.setGenre(10);//二级经营类型ID
		request.setBewrite("不清楚");//二级经营类型名称
		request.setFullname("狗蛋");//法人名称
		request.setPhoneid("12345678923");//本店法人手机
		request.setTel("02856888888");//座机号码
		request.setConsume("99");//人均消费
		request.setSvalidity("2016-07-02 10:10:00");//店铺签约开始时间
		request.setEvalidity("2017-07-02 10:10:00");//店铺签约结束时间
		request.setAgio(0.8D);//折扣设置
		request.setSdate("08:00-18:00");//开业时间
		request.setSendtype(1);//发送状态:0 存草稿箱   1提交订单
		request.setLongitude(20D);//经度
		request.setLatitude(30D);//维度
		request.setAppversion("1.0.0");
		request.setApiversion(1);
		request.setSystemversion("android 4.3");
		request.setSessiontoken("49477948c49bd3599d1514790a909caa");
		this.saasSign(request);
		sellerCount ++;
		return "OK,come on baby";
	}
}
