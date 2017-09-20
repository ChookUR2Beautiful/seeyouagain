package com.xmniao.controller;


import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmniao.common.HttpRequest;
import com.xmniao.common.PayConstants;
import com.xmniao.common.PayRefundStatus;
import com.xmniao.kuaiqian.ParseXMLUtil;
import com.xmniao.service.CommonService;
import com.xmniao.service.TongPayService;
import com.xmniao.tonglian.AipgRsp;
import com.xmniao.tonglian.QTDetail;
import com.xmniao.tonglian.QTransRsp;
@Controller
public class TongLNotifyController {
	
	 //初始日志类
	private final Logger log = Logger.getLogger(TongLNotifyController.class);

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private TongPayService tongPayService;
	
	@RequestMapping(value="/tongLPayNotify",method={RequestMethod.GET,RequestMethod.POST})
	public void tongLPayNotify(HttpServletRequest request,HttpServletResponse response){
		
		log.info("通联代付回调----tongLPayNotify  start");
		long edate = System.currentTimeMillis();
		log.info("盛付通代付回调时间-----------："+edate);
		String orderId = null;
		try {

			InputStream is = request.getInputStream();
			String str = HttpRequest.inputStreamToString(is);// 字节流转换为String
			log.info("返回的参数："+str);
			// 取出XML中的数据
			HashMap<String, String> hm = ParseXMLUtil
					.paseXML(new StringBufferInputStream(str));
			String batchNo = hm.get("NOTIFY");

			// log.info(batchNo.trim());
			String status = PayConstants.WITHDRAW_STATUS_AUDIT;// 财务处理中
            String msg = "";
			AipgRsp aipgrsp = tongPayService.queryTradeNew(batchNo.trim());
			if ("0000".equals(aipgrsp.getINFO().getRET_CODE())) {
				QTransRsp qrsq = (QTransRsp) aipgrsp.getTrxData().get(0);
				log.info("查询成功，具体结果明细如下:");
				List<QTDetail> details = qrsq.getDetails();
				for (QTDetail lobj : details) {
					String batchid = lobj.getBATCHID();
					log.info("原支付交易批次号:" + lobj.getBATCHID() + "  ");
					log.info("记录序号:" + lobj.getSN() + "  ");
					log.info("返回结果:" + lobj.getRET_CODE() + "  ");

					if ("0000".equals(lobj.getRET_CODE())) {
						status = PayConstants.WITHDRAW_STATUS_SUCCESS;
						msg=PayConstants.WITHDRAW_MSG_SUCCESS;
						log.info("提现成功  -------返回说明:交易成功  ");
					} else {
						 status = PayConstants.WITHDRAW_STATUS_FAIL;
						msg=PayConstants.WITHDRAW_MSG_FAIL+","+lobj.getERR_MSG();
						log.info("提现失败 ------返回说明:" + lobj.getERR_MSG() + "  ");
					}

					String[] orderIdArray = batchid.split("_");

					orderId = orderIdArray[0];
					String userType = orderIdArray[1];
					
					String sta = commonService.getWithdrawStatus(userType,orderId);

					// 验证是否已经修改了支付状态
					if (!PayConstants.WITHDRAW_STATUS_PROCESS.equals(sta)) {
						return;
					}
					if ("-1".equals(sta)) {
						log.error("汇付天下代付回调参数异常----userType:"+userType+",orderId:"+orderId);
						return;
					}

					Map<String, String> uwsMap = new HashMap<String, String>();
					uwsMap.put("orderNumber", orderId);
					uwsMap.put("status", status);
					uwsMap.put("Message", msg);
					uwsMap.put("platform_code", batchid);
					//修改提现状态
					commonService.updateWithdrawState(orderId, userType,
							Integer.valueOf(status), PayConstants.WITHDRAW_TYPE_TL, uwsMap);
				}
			}
		} catch (Exception e) {
			log.info("通联代付回调异常,订单号为："+orderId, e);
		}

		log.info("通联代付回调----tongLPayNotify  end");

	}
	

}
