package com.xmniao.xmn.core.api.controller.common;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xmniao.xmn.core.common.request.login.UserValidateRequest;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.business.java.XmnCommonService;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;


@Controller
public class SendApi {
	/**
	 * 初始日志类
	 */
	private final Logger log = Logger.getLogger(SendApi.class);

	
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	@RequestMapping(value = "/send/msg" ,produces={"application/json;charset=utf-8"})
	public void productionCode(UserValidateRequest request)throws Exception{
	
		//匹配完毕 发短信
		XmnCommonService.Client client = null;
		try {
			
			
			String phone = "13602467951,13602467951";
			String [] phoneArr = phone.split(",");
			for (int i = 0; i < phoneArr.length; i++) {
				
				System.out.println(phoneArr[i]);
				//发送短信
				Map<String, String> sendMap = new HashMap<String, String>();
				sendMap.put("phone", phoneArr[i]);//request.getPhone()
				sendMap.put("smsContent", "亲爱的参赛选手,恭喜您成功进入星食尚海选复赛阶段!您的直播权限已开通,请寻找合适的吃喝玩乐商户," +
						"进行不限次数且大于30分钟的直播,复赛阶段由您成功进入复赛起至2017年8月31日,届时我们将根据选手的粉丝数和打赏数进行由高到低的排名," +
						"前100名的选手进入城市决赛,有机会赢取百万年薪大奖！");
				TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("XmnCommonService");
				client = new XmnCommonService.Client(tMultiplexedProtocol); 
				thriftBusinessUtil.openTransport();
				ResponseData responseData =	client.sendXmnSms(sendMap);
				System.out.println("发送状态:"+responseData.getState());
				
			}
			
		}catch(Exception e){
			log.info("发送短信验证码失败");
			e.printStackTrace();
		}
	
	
	}
	
	
}
