package com.xmniao.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;









import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import chinapnr.SecureLink;

import com.xmniao.common.StateCodeUtil;
import com.xmniao.dao.PayRefundMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.PnrRefundService;
import com.xmniao.service.WalletService;

/**
 * 汇付天下 退款业务
 * @author ChenBo
 *
 */
public class PnrRefundServiceImpl implements PnrRefundService{

	 //初始日志类
	private final Logger log = Logger.getLogger(PnrRefundServiceImpl.class);
	//0.00
	private final BigDecimal ZERO = new BigDecimal("0.00");  
	//"000000"
	private final String PNR_SUCCESS = "000000";
	
	//版本号
	private String version;
	//消息类型
	private String refundCmdId;
	//商户号
	private String merId;
	//后台返回url
	@Resource(name = "notifyServiceUrl")
	private String notifyUrl;
	//请求url
	private String refundUrl;
	//退款查询类型
	private String queryRefundCmdId;
	//退款查询url
	private String queryRefundUrl;
	//退款总金额
	private String refAmt="";
	//订单号
	private String ordId="";
	//原始订单号
	private String oldOrdId="";
	//签名
	private String chkValue="";
	//订单详情
	private String divDetails="";

	@Autowired
	private PayRefundMapper payRefundMapper;
	
	@Autowired
	private WalletMapper walletMapper;
	
	@Autowired
	private WalletService walletService;
	
    private static final String XMN_REFUND_URL = "/xmn/PnrRefundNotify";
    
    private static final String FRESH_REFUND_URL = "/fresh/PnrRefundNotify";
	
	/**
	 * 汇付退款业务
	 * @param refundId 退款ID
	 * @param payId  支付ID
	 * @param orderAmount 退款金额
	 * @param refundNote 订单详情
	 * @return 
	 */
	@Override
	public Map<String, String> pnrPayRefund(String refundId, String payId,
			double orderAmount, String refundNote,int serviceType){
		Map<String,String> resultMap = new HashMap<String,String>();
		log.info("pnrPayRefund----->refundId:"+refundId+"----->payId:"+payId+"----->orderAmount:"+orderAmount+"----->refundNote:"+refundNote);


		//1.构建数据
		refAmt=String.format("%.2f",orderAmount);
		ordId=refundId;
		oldOrdId=payId;
		divDetails="";
		
		//2.验证并拿到签名
		String MerKeyFile = this.getPath();
		String	MerData = version + refundCmdId + merId + divDetails + refAmt + ordId + oldOrdId + generateActionUrl(serviceType);	
		SecureLink sl=new SecureLink();
		int ret=sl.SignMsg(merId,MerKeyFile,MerData);
		if (ret != 0) 
		{
			log.error("签名错误 ret=" + MerKeyFile );
			return returnMap(StateCodeUtil.PR_REFUND_FAIL,"签名错误 ！");
		}
		chkValue = sl.getChkValue( );
		
		//3.构建请求语句
		String url = getUrl(serviceType);
		
		//4.提交
		try {
			GetMethod method = new GetMethod(url);
			HttpClient client = new HttpClient();
			int result;
			
			result = client.executeMethod(method);
			if(result != HttpStatus.SC_OK){
				log.error("http请求错误 ");
				return returnMap(StateCodeUtil.PR_THIRD_NOT_CONNECT,"http请求错误!");
			}
			String str = method.getResponseBodyAsString();
			log.info("请求返回结果："+str);
			Map<String,String> map = stringToMap(str);
			
			if(map.get("RespCode") != null){
				if(PNR_SUCCESS.compareToIgnoreCase(map.get("RespCode")) == 0){
					log.info("汇付反馈提示成功！");
					resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"");
				}else{
					log.error("汇付反馈提示错误！");
					resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,map.get("ErrMsg")==null?"":map.get("ErrMsg").toString());
				}
			}
			else{
				log.error("汇付反馈无状态码");
				resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"汇付反馈无状态码");
			}
		} catch (HttpException e) {
			resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"HttpException");
			log.error("HttpException");
			e.printStackTrace();
		} catch (IOException e) {
			resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"IoException");
			log.error("IoException");
			e.printStackTrace();
		}catch(Exception e){
			resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"Exception");
			log.error("系统错误");
			e.printStackTrace();
		}

		return resultMap;
	}
	
	@Override
	public PayRefund getPayRefundByRefundId(String refundId) {
		
		return payRefundMapper.getPayRefundByRefundId(refundId);
	}

	//退款URL
	public String getUrl(int serviceType){
		StringBuffer sb = new StringBuffer(refundUrl).append("?");
		sb.append("Version=").append(version.toString()).append("&");
		sb.append("CmdId=").append(refundCmdId.toString()).append("&");
		sb.append("MerId=").append(merId.toString()).append("&");
		try {
			sb.append("DivDetails=").append(URLEncoder.encode(divDetails.toString(),"GBK")).append("&");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sb.append("RefAmt=").append(refAmt.toString()).append("&");
		sb.append("OrdId=").append(ordId.toString()).append("&");
		sb.append("OldOrdId=").append(oldOrdId.toString()).append("&");
		sb.append("BgRetUrl=").append(generateActionUrl(serviceType).toString()).append("&");
		sb.append("ChkValue=").append(chkValue.toString());
		return sb.toString();
	}
	
	//拿到文件路径
	public String getPath(){
		
		File file = new File(this.getClass().getResource("/").getPath()+"MerPrK872881.key");
		return file.getPath();
	}
	
	//String----->Map
	//将请求返回的数据，存到MAP中
	public Map<String,String> stringToMap(String str){
		
		if(str == null || str.trim() ==""){
			return null;
		}
		
		Map<String,String> map = new HashMap<String,String>();

		String[] strArray = str.split("\n");
		
		for(int i=0;i<strArray.length;i++){
			strArray[i] = StringUtils.trim(strArray[i]);
			String[] subStr = new String[2];
			subStr = strArray[i].split("=");
			for(String sub:subStr){
			}
			if(subStr.length == 2){
			map.put(subStr[0], subStr[1] == null?"":subStr[1]);
			}
		}
		return map;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getMerId() {
		return merId;
	}
	
	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	public String getRefundUrl() {
		return refundUrl;
	}
	
	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}
	public String getRefundCmdId() {
		return refundCmdId;
	}

	public void setRefundCmdId(String refundCmdId) {
		this.refundCmdId = refundCmdId;
	}

	public String getQueryRefundCmdId() {
		return queryRefundCmdId;
	}

	public void setQueryRefundCmdId(String queryRefundCmdId) {
		this.queryRefundCmdId = queryRefundCmdId;
	}

	public String getQueryRefundUrl() {
		return queryRefundUrl;
	}

	public void setQueryRefundUrl(String queryRefundUrl) {
		this.queryRefundUrl = queryRefundUrl;
	}


	/**
	 * 查询退款订单状态
	 */
	@Override
	public Map<String, String> pnrPayRefundQuery(String refundId) {
		Map<String,String> resultMap = new HashMap<String,String>();

		ordId = refundId;
		//验证并拿到签名
		String MerKeyFile = this.getPath();
		String	MerData = version + queryRefundCmdId + merId + ordId ;	
		SecureLink sl=new SecureLink();
		int ret=sl.SignMsg(merId,MerKeyFile,MerData);
		if (ret != 0) 
		{
			log.error("签名错误 ret=" + MerKeyFile );
			return returnMap(StateCodeUtil.PR_REFUND_FAIL,"签名错误 ！");
		}
		chkValue = sl.getChkValue( );
		
		//3.构建请求语句
		String url = getRefundQueryUrl(ordId);
		
		//4.提交
		try {
			GetMethod method = new GetMethod(url);
			HttpClient client = new HttpClient();
			int result;
			
			result = client.executeMethod(method);
			if(result != HttpStatus.SC_OK){
				log.error("http请求错误 ");
				return returnMap(StateCodeUtil.PR_REFUND_FAIL,"http请求错误!");
			}
			String str = method.getResponseBodyAsString();
			log.info("请求返回结果："+str);
			Map<String,String> map = stringToMap(str);
			
			if(map.get("RespCode") != null){
				if(PNR_SUCCESS.compareToIgnoreCase(map.get("RespCode")) == 0){
					log.info("汇付反馈提示查询成功！");
					String proStat = map.get("ProcStat");
					log.info("汇付退款处理状态："+proStat);
					switch(Integer.parseInt(proStat)){
					case 0:
						log.error("该支付交易不存在");
						resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"该支付交易不存在");
						break;
					case 1:
						log.info("该支付交易已成功支付，并已经结算");
						resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"该支付交易已成功支付，并已经结算");
						break;
					case 2:
						log.info("该交易未支付，或支付失败");
						resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"该交易未支付，或支付失败");
						break;
					case 3:
						log.info("该交易已成功支付，但未结算");
						resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"该交易已成功支付，但未结算");
						break;
					case 5:
						log.info("退款交易，已扣款，系统处理中");
						resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"退款交易，已扣款，系统处理中");
						break;
					case 6:
						log.info("退款交易，处理失败");
						resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"退款交易，处理失败");
						break;
					case 7:
						log.info("退款交易，未处理");
						resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"退款交易，未处理");
						break;
					case 8:
						log.info("退款交易已成功");
						resultMap = returnMap(StateCodeUtil.PR_SUCCESS,"退款交易已成功");
						break;
					default:
						log.error("未知处理状态");
						resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"未知处理状态");
						break;
					}
					
				}else{
					log.error("汇付反馈提示查询出错！");
					resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,map.get("ErrMsg")==null?"查询出错":map.get("ErrMsg").toString());
				}
			}
		} catch (HttpException e) {
			resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"HttpException");
			log.error("HttpException");
			e.printStackTrace();
		} catch (IOException e) {
			resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"IoException");
			log.error("IoException");
			e.printStackTrace();
		}catch(Exception e){
			resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"Exception");
			log.error("系统错误",e);
			e.printStackTrace();
		}

		return resultMap;
	}
	
	//退款查询URL
	public String getRefundQueryUrl(String OrdId){
		StringBuffer sb = new StringBuffer(queryRefundUrl).append("?");
		sb.append("Version=").append(version.toString()).append("&");
		sb.append("CmdId=").append(queryRefundCmdId.toString()).append("&");
		sb.append("MerId=").append(merId.toString()).append("&");
		sb.append("OrdId=").append(OrdId.toString()).append("&");
		sb.append("ChkValue=").append(chkValue.toString());
		return sb.toString();
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

