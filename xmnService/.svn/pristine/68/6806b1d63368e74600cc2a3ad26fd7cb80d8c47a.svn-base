package com.xmniao.xmn.core.api.controller.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SendCodeRequest;
import com.xmniao.xmn.core.util.utilClass;
import com.xmniao.xmn.core.xmer.service.SendCodeService;

/**
 * 
* 项目名称：xmnService   
* 类名称：SendMsgApi   
* 类描述：获取短信验证码 
* 创建人：liuzhihao   
* 创建时间：2016年5月20日 上午9:30:11   
* @version    
*
 */
@Controller
public class SendCodeApi implements BaseVControlInf{
	
	//日志报错
	private final Logger log = Logger.getLogger(SendCodeApi.class);
	
	//验证
	@Autowired
	private Validator validator;
	
	//注入短信发送service
	@Autowired
	private SendCodeService senCodeService;
	
	/**
     * 注入redis缓存
     */
    @Autowired
    private SessionTokenService sessionTokenService;
    
	
	//验证码
	int code = 0;
	
	@RequestMapping(value="sendCode",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object sendMsg(SendCodeRequest sendCodeRequest){
		//验证参数是否为空
		List<ConstraintViolation> result = validator.validate(sendCodeRequest); 
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		//手机号码正确
		if(!sendCodeRequest.getPhone().matches(Constant.PHONE_REG)){
			return new BaseResponse(ResponseCode.FAILURE,"手机号码不正确");
		}
		return versionControl(sendCodeRequest.getApiversion(),sendCodeRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		case 2: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	public Object versionControlOne(Object object){
		SendCodeRequest sendCodeRequest= (SendCodeRequest) object;
		if(sendCodeRequest.getSendType() == 1){
			//四位随机数
			code = utilClass.RandomNum();
		}
		if(sendCodeRequest.getSendType() == 2){
			//六位数
			code = utilClass.Random6Num();
		}
		sendCodeRequest.setSendType(code);
		String msg = senCodeService.sendCode(sendCodeRequest);
		if(msg != null && !msg.equals("")){
			String state = JSON.parseObject(msg).getString("state");
			if(state.equals("100")){
				String strphone = sendCodeRequest.getPhone();
				String codekey = strphone+Constant.USER_XMER_KEY;
                log.info("验证码在redis的key:"+codekey);
//                sessionTokenService.setStringForValue(codekey, String.valueOf(code), 5, TimeUnit.MINUTES);
                //测试数据缓存30天
                sessionTokenService.setStringForValue(codekey, String.valueOf(code), 30, TimeUnit.DAYS);
				log.info("send smg success!");
				return new BaseResponse(ResponseCode.SUCCESS,"验证码发送成功！");
			}
		}
		return new BaseResponse(ResponseCode.FAILURE,"发送验证码失败!");
	}
}
