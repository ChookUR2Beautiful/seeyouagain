package com.xmniao.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.MD5;
import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.RSA;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.dao.PayRefundMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.AliPayService;

/**
 * 支付宝退款Service实现
 * 
 * @author ChenBo
 *
 */
@Service("AliPayServiceImpl")
public class AliPayServiceImpl implements AliPayService{

	@Autowired
	private PayRefundMapper payRefundMapper;
	
	//寻蜜鸟支付宝合作ID
	@Resource(name = "zfb_partner")
	private String partner;
	
	//卖家支付宝帐户
	@Resource(name = "zfb_seller_email")
	private String seller_email;
	
	// 商户（MD5）私钥
	@Resource(name = "zfb_md5Key")
	private String md5Key; 
	
	//商户(RSA)私钥
	@Resource(name = "zfb_rsaKey")
	private String rsaKey;
	
	//商户(RSA)公钥
	@Resource(name = "zfb_rsapublicKey")
	private String rsapublicKey;
	
	//服务器异步通知页面路径
	@Resource(name = "notifyServiceUrl")
	private String notifyUrl;
	
	
	// 签名方式 不需修改
	private static String sign_type = "RSA";
	
	// 字符编码格式 
	private static String input_charset = "utf-8";
	

    /**
     * 支付宝提供给商户的服务接入网关URL
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do";
    
	/**
     * 支付宝消息验证地址
     */
    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
  
    private static final String XMN_REFUND_URL = "/xmn/aliRefundNotify";
    
    private static final String FRESH_REFUND_URL = "/fresh/aliRefundNotify";
	 //初始日志类
	private final Logger log = Logger.getLogger(AliPayServiceImpl.class);
	
	//0.00
//	private final BigDecimal ZERO = new BigDecimal("0.00");  
	
	//支付宝要求的退款流水号与寻蜜鸟系统的退款流水号相比，其时间格式有着"yyyyMMdd"与"yyMMdd"的差别
	private final String ALI_PREFIX = "20";
	/**
	 * 支付宝退款接口入口
	 * @param
	 * 
	 */
	@Override
	public Map<String, String> aliPayRefund(String refundId, String orderId,
			double orderAmount, String refundNote,int serviceType){
//		Map<String,String> resultMap = new HashMap<String,String>();
		
		log.info("aliPayRefund----->refundId:"+refundId+"----->orderId:"+orderId+"----->orderAmount:"+orderAmount+"----->refundNote:"+refundNote);
		if(StringUtils.isBlank(refundId) || StringUtils.isBlank(orderId)){
			log.error("提交的退款单号及支付宝交易号不能为空!");

			return returnMap(StateCodeUtil.PR_REFUND_FAIL,"提交的退款单号及支付宝交易号不能为空!");
		}

		
		String oAmount = String.format("%.2f",orderAmount);
		
		//拼单笔数据集
		StringBuffer detail_data = new StringBuffer();
		detail_data.append(orderId);
		detail_data.append("^");
		detail_data.append(oAmount);
		detail_data.append("^");
		detail_data.append(refundNote);
		
		String result = "";
		try{
			result = RequestRefund(refundId,detail_data.toString(),serviceType);

		}catch(Exception e){
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"系统执行异常");
		}


		return returnMap(StateCodeUtil.PR_REQUEST_THIRD_SUCCESS,"",result);
	}
	
	/**
	 * 
	 * @param refundId
	 * @param detail_data
	 * @return
	 * @throws Exception
	 */
	private String RequestRefund(String refundId, String detail_data,int serviceType) throws Exception{
	
		////////////////////////////////////请求参数//////////////////////////////////////
		//接口名
		String service ="refund_fastpay_by_platform_pwd";

		//退款当前日期
		String refund_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		//退款笔数
		String batch_num ="1";
		//必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）
		
	
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", service);
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", input_charset);
		sParaTemp.put("notify_url", generateActionUrl(serviceType));
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("refund_date", refund_date);
		sParaTemp.put("batch_no", refundId);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);
		
		//////////////////////////////////////////////////////////////////////////////////
		
		log.info("Map:"+sParaTemp.toString());
		//生成HTML表单形式字符串
		
		
	    return  buildRequest(sParaTemp);
	}
	
    /**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @return 提交表单HTML文本
     * @throws UnsupportedEncodingException 
     */
    public String buildRequest(Map<String, String> sParaTemp){
        //待请求参数数组
        Map<String, String> sPara = this.buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<html><head><meta charset='UTF-8'><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /></head><body><form id='alipaysubmit' name='alipaysubmit' action='" + ALIPAY_GATEWAY_NEW +"?_input_charset="+ input_charset + "' method='POST'>");

        for (int i = 0; i < keys.size(); i++) {
            String name = keys.get(i);
            String value = sPara.get(name);

            if("_input_charset".equals(name)){
            	continue;
            }
            sbHtml.append("<input type='hidden' name='" + name + "' value='" + value + "'/>");
       
        }

        sbHtml.append("</form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script></body></html>");
//        System.out.println(sbHtml);
        return sbHtml.toString();
    }
    
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
    	
        //除去数组中的空值和签名参数
        Map<String, String> sPara = this.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = this.buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", sign_type);

        return sPara;
    }
    
	 /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    private Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	private String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = this.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String mysign = "";
    	if("MD5".equals(sign_type)){
    		mysign = MD5.Encode(prestr+md5Key, input_charset);
    	}
    	else if("RSA".equals(sign_type)){
    		mysign = RSA.sign(prestr, rsaKey, input_charset);
    	}
    		

        
        return mysign;
    }
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    
    /**
     * 验证消息是否是支付宝发出的合法消息
     * @param params 通知返回来的参数数组
     * @return 验证结果
     */
    @Override
    public boolean verify(Map<String, String> params) {

        //判断responsetTxt是否为true，isSign是否为true
        //responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
        //isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
    	String responseTxt = "true";
		if(params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
	    String sign = "";
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    boolean isSign = getSignVeryfy(params, sign);

        if (isSign && responseTxt.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
    /**
    * 获取远程服务器ATN结果,验证返回URL
    * @param notify_id 通知校验ID
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    */
    private String verifyResponse(String notify_id) {
        //获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求

        String veryfy_url = HTTPS_VERIFY_URL + "partner=" + this.partner + "&notify_id=" + notify_id;

        return checkUrl(veryfy_url);
    }

    /**
    * 获取远程服务器ATN结果
    * @param urlvalue 指定URL路径地址
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    */
    private String checkUrl(String urlvalue) {
        String inputLine = "";

        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
                .getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }

        return inputLine;
    }
    
    /**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
	private boolean getSignVeryfy(Map<String, String> Params, String sign) {
    	//过滤空值、sign与sign_type参数
    	Map<String, String> sParaNew = this.paraFilter(Params);
        //获取待签名字符串
        String preSignStr = this.createLinkString(sParaNew);
        //获得签名验证结果
        //return RSA.verify(preSignStr, sign, md5Key, input_charset);
        boolean isSign = false;
        if(sign_type.equals("MD5")) {
        	isSign = MD5.verify(preSignStr+md5Key, sign, input_charset);
        } 
        else if(sign_type.equals("RSA")){
        	isSign = RSA.verify(preSignStr, sign, rsapublicKey, input_charset);
        }
        return isSign;
    }
	

	/**
	 * 支付宝退款异步回调处理，更新支付订单状态及钱包金额
	 * @param params 
	 * @return 状态码
	 */
	@Override
	public int RefoundNotity(Map<String, String> params,PayRefund payRefund){

		log.debug(params.toString());
		log.debug(JSON.toJSONString(payRefund));
//		Map<String,Object> refundMap = new HashMap<String,Object>();
		String refundId = params.get("batch_no") == null?"":params.get("batch_no");
//		String successNum = params.get("success_num") == null? "":params.get("success_num");
		// 交易号^退款金额^处理结果$退费账号^退费账户ID^退费金额^处理结果
		String resultDetails = params.get("result_details") == null? "":params.get("result_details");
		try{
			String[] result = StringUtils.split(resultDetails, "$");
			String[] refundInfo = StringUtils.split(result[0], "^");
	
//			String pn = refundInfo[0]; // 支付交易号
			String ra = refundInfo[1]; // 退款金额
			String rr = refundInfo[2]; // 退款处理结果

			//是否 该订单已经退款成功，属于重复数据
			if(PayRefundStatus.PROCESS_SUCCESS == payRefund.getStatus()){
				log.error("退款流水号:"+refundId+"该订单已经退款成功，重复数据");
				return 1;
			}
			
			if ("SUCCESS".equalsIgnoreCase(rr)) {
				BigDecimal refundAmount = new BigDecimal(ra);
				//是否 实际退款金额与申请退款金额一致
				if (refundAmount.compareTo(payRefund.getSamount()) == 0) {
					log.info("支付宝服务器，退款成功！");
					return 0;
					
				} else {
					log.error("退款流水号"+refundId+"退款金额不一致" );
				}
			} else {
				log.error("退款流水号"+refundId+"退款失败,code"+rr);
			}
		}catch(Exception e){
			log.error("支付宝退款异常",e);
		}
		
		return 2;
	}
	
	//查找退款记录
	@Override
	public PayRefund GetRefund(String refundId){
		return payRefundMapper.getPayRefundByRefundId(refundId);
	}

	@Override
	public String batchNoTorefundId(String aliBatchNo) {
		if(aliBatchNo.startsWith(ALI_PREFIX)){
			return aliBatchNo.substring(ALI_PREFIX.length());
		}
		return aliBatchNo;
	}

	@Override
	public String refundIdTobatchNo(String refundId) {

		return ALI_PREFIX+refundId;
	}
	
	public Map<String,String> returnMap(String code,String msg){
		return this.returnMap(code,msg,"");
	}
	//返回的Map数据
	public Map<String,String> returnMap(String code,String msg,String response){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("code", code);
		resultMap.put("Msg",msg);
		resultMap.put("response",response);
		return resultMap;
	}

	private String generateActionUrl(int serviceType){
		String nUrl ="";
		if(0==serviceType){
			nUrl = notifyUrl+XMN_REFUND_URL;
		}else if(1 == serviceType){
			nUrl = notifyUrl+FRESH_REFUND_URL;
		}
		return nUrl;
	}
}
