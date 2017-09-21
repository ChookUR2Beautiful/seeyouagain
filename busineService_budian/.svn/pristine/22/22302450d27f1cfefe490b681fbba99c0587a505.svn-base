package com.xmniao.service.message.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 发送短信业务处理实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SendSmsImpl implements SendSms
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SendSmsImpl.class);
    
    /**
     * 发送短信的redis队列KEY
     */
    private String smsQueue;
    
    /**
     * 注入短信URL地址
     */
    private String smsUrl;
    
    /**
     * 微信消息推送URL地址
     */
    private String wxSmsUrl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public void handleMessage(String reqJson)
    {
    	try {
    		log.info("sendSms handleMessage start:" + reqJson);
            if (StringUtils.isNotBlank(reqJson))
            {
            	JSONObject resJson = JSONObject.parseObject(reqJson);
            	String phoneid = resJson.getString("phoneid");
            	//手机号码不为空，则调用短信发送接口
            	if(isWxOpenid(phoneid)){
            		Map<String, Object> map = new HashMap<String, Object>();
            		//订单类型   1附近美食订单    2生鲜商城订单 
            		map.put("type", 1);
            		//微信关注用户openid参数
                    map.put("openid", phoneid);
                    //订单编号
                    map.put("bid", resJson.getString("bid"));
                    //商户名称
                    map.put("sellername", resJson.getString("sellername"));
                    //消费金额
                    map.put("money", resJson.getString("money"));
                    //支付时间，消费时间
                    map.put("sdate", resJson.getString("sdate"));
                    //通知标题  暂为空
                    map.put("title", "");
                    //备注信息 暂为空
                    map.put("remarks", "");
                    //调用短信接口
                    String result = httpURLConnectionUtil("p=" + JSON.toJSONString(map), wxSmsUrl, "POST");
                    log.info("短信接口请求结果："+result);
                    if (StringUtils.isNotBlank(result))
                    {
                        JSONObject reObj = JSONObject.parseObject(result);
                        //若发送失败,则再调用一次
                        if (reObj.getInteger("state")!=100 && reObj.getInteger("state")!=104)
                        {
                            redisTemplate.opsForList().rightPush(smsQueue, reqJson);
                            log.error("sendSms:发送短信错误编码"+reObj.getInteger("state"));
                        }
                    }
                    log.info("Send SMS result:" + result);
            	}else if(isPhoneNumber(phoneid)){
                    Map<String, Object> map = new HashMap<String, Object>();
                    List<String> list = Arrays.asList(resJson.getString("phoneid").split(","));
                    map.put("appid", "998899");
                    map.put("text", resJson.getString("smscontent"));
                    map.put("phones", list);
                    //调用短信接口
                    String result = httpURLConnectionUtil("p=" + JSON.toJSONString(map), smsUrl, "POST");
                    if (StringUtils.isNotBlank(result))
                    {
                        JSONObject reObj = JSONObject.parseObject(result);
                        //若发送失败,则再调用一次
                        if (reObj.getInteger("state")!=100 && reObj.getInteger("state")!=104)
                        {
                            redisTemplate.opsForList().rightPush(smsQueue, reqJson);
                        }
                    }
                    log.info("Send SMS result:" + result);
    	         //如果是微信用戶，推送微信消息
            	}else{
            		log.error("sendSms:手机号码为空，无微信openId");
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        log.info("sendSms end......");
    }
    
    /**
     * 判断phoneid用户昵称是否为微信openid，非完全正确
     * @param phoneid
     * @return
     */
    public boolean isWxOpenid(String phoneid){
    	if(StringUtils.isBlank(phoneid)) return false;
    	//包含字符，下划线，横杆，数字等，目前微信openid为28位
    	Pattern pattern = Pattern.compile("^[0-9a-zA-Z_-]{28}");
    	Matcher mt = pattern.matcher(phoneid);
    	if(mt.matches()) return true;
    	return false;
    }
    
    /**
     * 判断是否为手机号码
     * @param phoneid
     * @return
     */
    public boolean isPhoneNumber(String phoneid){
    	if(StringUtils.isBlank(phoneid)) return false;
    	return true;
//    	Pattern pattern = Pattern.compile("^[1]+\\d{10}");
//    	Matcher mt = pattern.matcher(phoneid);
//    	if(mt.matches()) return true;
//    	return false;
    }
    
    /**
     * http统一请求方法
     * @param outwriteStr 发送参数值
     * @param url	请求地址
     * @param method   post/get
     * @return 请求结果
     */
    public String httpURLConnectionUtil(String outwriteStr,String smsUrl,String method){
    	HttpURLConnection connection=null;
        BufferedReader l_reader =null;
        try
        {
            URL url = new URL(smsUrl); //发送短信 url
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true); // post请求  需设置
            connection.setDoInput(true); // post请求  需设置
            connection.setConnectTimeout(6000); //设置连接超时时间
            connection.setReadTimeout(6000); //设置读取数据超时时间
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8"); //发送格式
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8");
            log.info("sendSMS param:" + outwriteStr);
            out.write(outwriteStr);
            out.flush();
            out.close();
            String sCurrentLine = "";
            String sTotalString = "";
            InputStream l_urlStream = connection.getInputStream();
            l_reader = new BufferedReader(
                    new InputStreamReader(l_urlStream, "utf-8"));
            while ((sCurrentLine = l_reader.readLine()) != null)
            {
                sTotalString += sCurrentLine;
            }
            return sTotalString;
        }catch (Exception e)
        {
            log.error("发送短信异常", e);
            return null;
        }finally
        {
            if(l_reader!=null)
            {
                try
                {
                    l_reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                connection.disconnect(); 
            }
        }
    }
    
    
    
    public String getSmsQueue()
    {
        return smsQueue;
    }

    public void setSmsQueue(String smsQueue)
    {
        this.smsQueue = smsQueue;
    }

    public String getSmsUrl()
    {
        return smsUrl;
    }

    public void setSmsUrl(String smsUrl)
    {
        this.smsUrl = smsUrl;
    }

	public String getWxSmsUrl() {
		return wxSmsUrl;
	}

	public void setWxSmsUrl(String wxSmsUrl) {
		this.wxSmsUrl = wxSmsUrl;
	}
}
