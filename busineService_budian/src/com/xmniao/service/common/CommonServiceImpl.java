package com.xmniao.service.common;

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

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.dao.common.CommonServiceDao;
import com.xmniao.domain.live.LiveSalary;
import com.xmniao.domain.message.MessageRequest;
import com.xmniao.domain.push.PushMsgRequest;
import com.xmniao.thrift.ledger.ResponseSplitMap;
import com.xmniao.thrift.ledger.SplitService;
import com.xmniao.thrift.pay.LiveWalletService;
import com.xmniao.thrift.pay.ManorPropsThriftService;
import com.xmniao.thrift.pay.ResponseData;
import com.xmniao.thrift.pay.Result;
import com.xmniao.thrift.pay.SynthesizeService;
import com.xmniao.thrift.pay.ValueCardService;
import com.xmniao.thrift.pay.XmerWalletService;

/**
 * 通用接口服务实现类
 * @author  LiBingBing
 * @version  [版本号, 2014年11月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommonServiceImpl implements CommonServiceDao
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(CommonServiceImpl.class);
    
    /**
     * 注入短信URL地址
     */
    private String smsUrl;
    
    /**
     * 注入用户中心服务URL地址
     */
    private String userServerUrl;
    
    /**
     * 注入支付服务的修改钱包余额接口的IP地址
     */
    private String transLedgerIP;
    
    /**
     * 注入支付服务的修改钱包余额接口的端口号
     */
    private int transLedgerPort;
    
    /**
     * 注入推送消息
     */
    private String pushMsgUrl;
    
    /**
     * 分账服务
     */
    private String ledgerIP;
    
    /**
     * 分账服务
     */
    private int ledgerPort;
    /**
     * 发送短信服务接口
     * @param request [请求参数]
     */
    @Override
    public String sendSms(MessageRequest request)
    {
        log.info("sendSms start......");
        String result = null;
        HttpURLConnection connection = null;
        BufferedReader l_reader = null;
        try
        {
            URL url = new URL(smsUrl); //发送短信 url
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true); // post请求  需设置
            connection.setDoInput(true); // post请求  需设置
            connection.setConnectTimeout(3000); //设置连接超时时间
            connection.setReadTimeout(3000); //设置读取数据超时时间
            connection.setRequestProperty("Content-Type",
                   "application/x-www-form-urlencoded; charset=UTF-8"); //发送格式
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8");
            Map<String, Object> map = new HashMap<String, Object>();
            List<String> list = new ArrayList<String>();
            //list.add(request.getMobileId());
            list = Arrays.asList(request.getMobileId().split(","));
            map.put("appid", "998899");
            map.put("text", request.getContent());
            map.put("phones", list);
            log.info("sendSMS param:"+map);
            out.write("p=" + JSON.toJSONString(map));
            out.flush();
            out.close();
            String sCurrentLine = "";
            String sTotalString = "";
            InputStream l_urlStream = connection.getInputStream();
            l_reader = new BufferedReader(new InputStreamReader(
                    l_urlStream, "utf-8"));
            while ((sCurrentLine = l_reader.readLine()) != null)
            {
                sTotalString += sCurrentLine;
            }
            result = sTotalString;
        }
        catch (Exception e)
        {
            log.error("发送短信异常", e);
        }finally
        {
            //关闭连接
            stopConnection(l_reader,connection);
        }
        log.info("sendSms end......");
        return result;
    }
    
    /**
     * 调用用户中心服务
     * @param paramMap [请求参数]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public String modifyUserCenter(Map<String, Object> paramMap)
    {
        String result = null;
        HttpURLConnection connection =null;
        BufferedReader l_reader=null;
        log.info("modifyUserCenter start::" + paramMap);
        try
        {
            Map<String, Object> map = new HashMap<String, Object>();
            
            //将所属商家ID、所属商家名称、所属商家所属合作商ID、所属商家所属合作商名称、归属于商户或者向蜜客的时间作为更新参数
            Map<String, String> param = new HashMap<String, String>();
            param.put("genussellerid", paramMap.get("genussellerid").toString());
            param.put("genusname", paramMap.get("genusname").toString());
            if(paramMap.containsKey("jointid") && null!=paramMap.get("jointid"))
            {
                param.put("jointid", paramMap.get("jointid").toString());
            }else
            {
                param.put("jointid", null);
            }
            if(paramMap.containsKey("corporate"))
            {
                param.put("corporate", paramMap.get("corporate").toString());
            }else
            {
                param.put("corporate", null);
            }
            param.put("mike_type", "2");
            param.put("attach_time", paramMap.get("attach_time").toString());
            Map<String, String> para = new HashMap<String, String>();
            para.put("uid", paramMap.get("uid").toString());
            
            map.put("update", param);
            //将t_bill订单中的用户ID作为更新条件
            map.put("where", para);
            
            log.info("userCenter Server param:" + JSONObject.toJSONString(map));
            //用户中心服务URL地址
            URL url = new URL(userServerUrl + "updateUserAll.html");
            log.info("userCenter Server URL::" + url);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true); // post请求  需设置
            connection.setDoInput(true); // post请求  需设置
            connection.setConnectTimeout(15000); //设置连接超时时间
            connection.setReadTimeout(15000); //设置读取数据超时时间
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.write("p=" + JSONObject.toJSONString(map));
            out.flush();
            out.close();
            String sCurrentLine = "";
            String sTotalString = "";
            InputStream l_urlStream = connection.getInputStream();
            l_reader = new BufferedReader(new InputStreamReader(
                    l_urlStream, "utf-8"));
            while ((sCurrentLine = l_reader.readLine()) != null)
            {
                sTotalString += sCurrentLine;
            }
            result = sTotalString;
        }
        catch (Exception e)
        {
            log.error("调用用户中心修改用户信息服务接口异常", e);
        }finally
        {
            //关闭连接
            stopConnection(l_reader,connection);
        }
        return result;
    }
    
    /**
     * 修改用户向蜜客时间
     * @param paramMap
     * @return
     */
    @Override
    public String modifyUserMikeTime(Map<String, String> paramMap)
    {
        String result = null;
        HttpURLConnection connection =null;
        BufferedReader l_reader=null;
        log.info("modifyUserMikeTime start::" + paramMap);
        try
        {
            //用户中心更新用户向蜜客时间URL地址
            URL url = new URL(userServerUrl + "updateUserMikeTime.html");
            log.info("updateUserMikeTime Server URL::" + url);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true); // post请求  需设置
            connection.setDoInput(true); // post请求  需设置
            connection.setConnectTimeout(15000); //设置连接超时时间
            connection.setReadTimeout(15000); //设置读取数据超时时间
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8"); //发送格式
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8");
            out.write("uid=" + paramMap.get("userid"));
            out.flush();
            out.close();
            String sCurrentLine = "";
            String sTotalString = "";
            InputStream l_urlStream = connection.getInputStream();
            l_reader = new BufferedReader(new InputStreamReader(
                    l_urlStream, "utf-8"));
            while ((sCurrentLine = l_reader.readLine()) != null)
            {
                sTotalString += sCurrentLine;
            }
            result = sTotalString;
        }
        catch (Exception e)
        {
            log.error("调用用户中心修改用户向蜜客时间服务接口异常", e);
        }finally
        {
            //关闭连接
            stopConnection(l_reader,connection);
        }
        return result;
    }
    
    /**
     * 分账系统支付服务的修改钱包余额接口
     * @param reqMap [请求参数]
     * @return int [返回是否成功标识]
     */
    @Override
    public int modifyWalletBalance(Map<String, String> reqMap)
    {
        //返回是否修改成功
        int reStateFlag = 0;
        TTransport transport = null;
        try
        {
            //调用分账系统支付服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账系统支付服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "SynthesizeService");
            SynthesizeService.Client client = new SynthesizeService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            //将请求参数放到LIST集合中
            List<Map<String, String>> walletListMap = new ArrayList<Map<String, String>>();
            walletListMap.add(reqMap);
            //调用修改钱包余额接口
            Map<String,String> resFlag = client.updateBalance(walletListMap);
           //获取返回MAP中的state状态
           reStateFlag = Integer.valueOf(resFlag.get("state"));
            //判断调用是否成功,若调用成功,则返回为0，若失败，则返回为1
            if (reStateFlag == 0)
            {
                return reStateFlag;
            }
            else
            {
                reStateFlag = 1;
            }
        }
        catch (TException e)
        {
            //若调用抛出异常,则返回标识为-1
            reStateFlag = -1;
            log.error("调用分账系统支付服务的修改钱包余额接口异常", e);
        }
        finally
        {
            //关闭连接
            transport.close();
        }
        return reStateFlag;
    }
    
    
    /**
     * 调用支付服务添加寻觅客钱包
     * @param reqMap [请求参数]
     * @return int [返回是否成功标识]
     */
    @Override
    public int addXmerWallet(Map<String, String> reqMap)
    {
        //返回是否修改成功
        int reStateFlag = 0;
        TTransport transport = null;
        try
        {
            //调用分账系统支付服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账系统支付服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "XmerWalletService");
            XmerWalletService.Client client = new XmerWalletService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            
            //调用添加钱包的接口
            ResponseData responseData = client.addXmerWallet(reqMap);
            reStateFlag = responseData.getState();
            //判断调用是否成功,若调用成功,则返回为0，若失败，则返回为1
            if (reStateFlag == 0)
            {
                return reStateFlag;
            }
            else
            {
                reStateFlag = 1;
            }
        }
        catch (TException e)
        {
            //若调用抛出异常,则返回标识为-1
            reStateFlag = -1;
            log.error("调用支付服务的添加寻蜜客钱包接口异常", e);
        }
        finally
        {
            //关闭连接
            transport.close();
        }
        return reStateFlag;
    }

    @Override
	public ResponseData updateLiveWalletReturnResponse(
			Map<String, String> reqMap) {
        //返回是否修改成功
        TTransport transport = null;
        try
        {
            //调用分账系统支付服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账系统支付服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
            LiveWalletService.Client liveWClient = new LiveWalletService.Client(orderProtocol);
            //打开端口,开始调用
            transport.open();
            
            //调用更新钱包的接口
            return liveWClient.updateWalletAmount(reqMap);
        }
        catch (TException e)
        {
            log.error("调用支付服务的更新直播钱包接口异常"+e);
        }catch(Exception e){
        	log.error("",e);
        }finally
        {
            //关闭连接
            transport.close();
        }
        return null;
	}

	/**
     * 调用支付服务更新直播钱包
     * @param reqMap [请求参数]
     * @return int [返回是否成功标识]
     */
    @Override
    public boolean updateLiveWallet(Map<String, String> reqMap)
    {
		ResponseData responseData = this.updateLiveWalletReturnResponse(reqMap);
    	if(responseData==null){
    		return false;
    	}
    	return responseData.getState()==0;
    }
    
    /**
     * 调用支付服务添加用户钱包
     * @return int [返回是否成功标识]
     */
    @Override
    public boolean addWallet(String uId,String userType)
    {
        //返回是否修改成功
        TTransport transport = null;
        try
        {
            //调用分账系统支付服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账系统支付服务的综合服务接口模块
            TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(protocol, "SynthesizeService");
            SynthesizeService.Client client = new SynthesizeService.Client(tMultiplexedProtocol);
            //打开端口,开始调用
            transport.open();
            
            //调用添加钱包的接口
            int result = client.addWallet(uId, userType,"","");
            //判断调用是否成功,若调用成功,则返回为0，若失败，则返回为1
            if (result == 0)
            {	
            	log.info("添加会员钱包成功"+uId);
                return true;
            }
            else
            {
            	log.info("添加会员钱包失败"+uId);
            	return false;
            }
        }
        catch (TException e)
        {
            log.error("调用支付服务的添加用户钱包接口异常"+e);
            return false;
        }catch(Exception e){
        	log.error("调用支付服务的添加用户钱包接口异常",e);
        	return false;
        }
        finally
        {
            //关闭连接
            transport.close();
        }
    }
    
    /**
     * 调用支付服务检测并添加钱包
     * @param reqMap [请求参数]
     * @return int [返回是否成功标识]
     */
    @Override
    public boolean checkLiveWallet(Map<String, String> reqMap)
    {
        //返回是否修改成功
        TTransport transport = null;
        try
        {
            //调用分账系统支付服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账系统支付服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
            LiveWalletService.Client liveWClient = new LiveWalletService.Client(orderProtocol);
            //打开端口,开始调用
            transport.open();
            
            //调用查询钱包的接口
            ResponseData responseData = liveWClient.getLiveWallet(reqMap);
            //判断调用是否成功,若调用成功,则返回为0，若失败，则返回为1
            if (responseData.getResultMap() != null)
            {	
                return true;
            }
            else
            {
            	//调用查询钱包的接口
            	ResponseData responseData2 = liveWClient.addLiveWallet(reqMap.get("uid"));
            	if(responseData2.getState() == 0){
            		log.info("调用支付服务，添加直播钱包成功");
            		return true;
            	}else {
            		return false;
				}
            }
        }
        catch (TException e)
        {
            log.error("调用支付服务的查询直播钱包接口异常"+e);
            return false;
        }catch(Exception e){
        	log.error("调用支付服务的查询直播钱包接口异常",e);
        	return false;
        }
        finally
        {
            //关闭连接
            transport.close();
        }
    }

	@Override
	public Map<String, String> getSellerMentionLedger(int sellerid,int type) {
        //返回是否修改成功
        TTransport transport = null;
        try
        {
            //调用分账系统支付服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账系统支付服务的综合服务接口模块
            TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(protocol, "SynthesizeService");
            SynthesizeService.Client client = new SynthesizeService.Client(tMultiplexedProtocol);
            //打开端口,开始调用
            transport.open();
            
            //调用获取提现/分账设置的接口
            Map<String,String> result = client.getMentionLedger(type,sellerid+"");
            //判断调用是否成功,若调用成功,则返回为0，若失败，则返回为1
            if (result.get("state").equals("0")
            	|| result.get("state").equals("1")){	
                return result;
            }
        }
        catch (TException e)
        {
            log.error("调用支付服务的获取提现/分账设置接口异常"+e);
        }catch(Exception e){
        	log.error("调用支付服务的获取提现/分账设置接口异常",e);
        }
        finally{
            //关闭连接
            transport.close();
        }
        return null;
    }
	
    /**
     * 消息推送服务
     * @param req
     * @return
     */
    @Override
    public String pushMessage(PushMsgRequest reqJson)
    {
        String resultPhp = null;
        BufferedReader l_reader =null;
        log.info("pushMessage PHP start::" + JSONObject.toJSONString(reqJson));
        HttpClient httpClient = new HttpClient();
        PostMethod method=null;
        try
        {
            //设置连接超时时间为5秒
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
            //设置读取数据超时时间为5秒
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(15000);
            method = new PostMethod(pushMsgUrl);
            method.getParams().setContentCharset("UTF-8");
            NameValuePair[] namePair = new NameValuePair[11];
            namePair[0] = new NameValuePair("uid", reqJson.getUid());
            namePair[1] = new NameValuePair("usertype", reqJson.getUsertype());
            namePair[2] = new NameValuePair("isAll", reqJson.getIsAll());
            namePair[3] = new NameValuePair("title", reqJson.getTitle());
            namePair[4] = new NameValuePair("content", reqJson.getContent());
            namePair[5] = new NameValuePair("action", reqJson.getAction());
            namePair[6] = new NameValuePair("iosaction", reqJson.getIosaction());
            namePair[7] = new NameValuePair("client", reqJson.getClient());
            namePair[8] = new NameValuePair("tdate", reqJson.getTdate());
            namePair[9] = new NameValuePair("edate", reqJson.getEdate());
            namePair[10] = new NameValuePair("type", reqJson.getType());
            method.setRequestBody(namePair);
            //开始调用PHP的消息推送服务
            int reStatus=httpClient.executeMethod(method);
            
            if(reStatus==HttpStatus.SC_OK)
            {
                String sCurrentLine = "";
                String sTotalString = "";
                //获取响应结果
                InputStream l_urlStream = method.getResponseBodyAsStream();
                l_reader = new BufferedReader(new InputStreamReader(
                        l_urlStream, "utf-8"));
                while ((sCurrentLine = l_reader.readLine()) != null)
                {
                    sTotalString += sCurrentLine;
                }
                resultPhp = sTotalString;
                log.info("pushMessage PHP responseBody::" + resultPhp);
            }
        }
        catch (Exception e)
        {
            log.error("pushMessage PHP error", e);
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
            if(method!=null)
            {
                method.releaseConnection();   
                httpClient.getHttpConnectionManager().closeIdleConnections(0);
            }
        }
        log.info("pushMessage PHP end");
        return resultPhp;
    }
    
    /**
     * 采用HTTP方式发送Redis
     * @param connUrl [HTTP方式发送redis的URL地址]
     */
    @Override
    public String sendHttpRedis(String connUrl)
    {
        log.info("sendHttpRedis connection start::" + connUrl);
        String result="";
        BufferedReader l_reader=null;
        HttpURLConnection connection =null;
        try
        {
            //用户中心更新用户向蜜客时间URL地址
            URL url = new URL(connUrl);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); //设置连接超时时间
            connection.setReadTimeout(5000); //设置读取数据超时时间
            connection.setDoOutput(true);  // post请求  需设置
            connection.setDoInput(true);  // post请求  需设置
            connection.setRequestProperty("Content-Type",
                    "application/json;charset=UTF-8"); //发送格式
            connection.connect();
            String sCurrentLine = "";
            String sTotalString = "";
            l_reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));
            while ((sCurrentLine = l_reader.readLine()) != null)
            {
                sTotalString += sCurrentLine;
            }
            result=sTotalString;
            
        }
        catch (Exception e)
        {
            log.error("sendHttpRedis error", e);
            return null;
        }finally
        {
            //关闭连接
            stopConnection(l_reader,connection);
        }
        return result;
    }
    
    /**
     * 关闭连接
     * @param l_reader
     * @param connection [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private static void stopConnection(BufferedReader l_reader,HttpURLConnection connection)
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
        try
        {
            if(connection!=null)
            {
                connection.disconnect();   
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @Title: offlineMallLedgerFormula 
     * @Description:
     */
	@Override
	public ResponseSplitMap offlineMallLedgerFormula(double purchaseMoney,double profitRate,boolean isBseller,boolean isSellerArea) {
		log.info("请求计算线下积分订单分账参数："+purchaseMoney+","+profitRate+","+isBseller+","+isSellerArea);
        //返回是否修改成功
		ResponseSplitMap responseSplitMap = null; 
		TTransport transport = null;
        try
        {
            //调用分账服务的IP和端口号
            transport = new TSocket(ledgerIP, ledgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "SplitService");
            SplitService.Client client = new SplitService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            responseSplitMap = client.goodsLedger(purchaseMoney,profitRate, isBseller, isSellerArea);

            //判断调用是否成功,若调用成功,则返回为0，若失败，则返回为1
            if (responseSplitMap.getCode()!=1)
            {
            	responseSplitMap = null;
            }
        }
        catch (TException e)
        {
            //若调用抛出异常,则返回标识为-1
        	responseSplitMap = null;
            log.error("调用分账系统分账订单公式接口异常", e);
        }
        finally
        {
            //关闭连接
            transport.close();
        }
        return responseSplitMap;
    		
	}
	
	@Override
	public ResponseSplitMap onlineMallLedgerFormula(double purchaseMoney,
			double sellerMoney,double payment,boolean isBseller) {
		log.info("请求计算线上积分订单分账参数："+purchaseMoney+","+sellerMoney+","+payment+","+isBseller);
        //返回是否修改成功
		ResponseSplitMap responseSplitMap = null; 
		TTransport transport = null;
        try
        {
            //调用分账服务的IP和端口号
            transport = new TSocket(ledgerIP, ledgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账服务的分账接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "SplitService");
            SplitService.Client client = new SplitService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            responseSplitMap = client.freshLedger(purchaseMoney,sellerMoney,payment, isBseller);

            //判断调用是否成功,若调用成功,则返回为0，若失败，则返回为1
            if (responseSplitMap.getCode()!=1)
            {
            	log.info("调用分账计算公式接口失败："+responseSplitMap);
            	responseSplitMap = null;
            }
        }
        catch (TException e)
        {
            //若调用抛出异常,则返回标识为-1
        	responseSplitMap = null;
            log.error("调用分账系统分账订单公式接口异常", e);
        }
        finally
        {
            //关闭连接
            transport.close();
        }
        return responseSplitMap;
    		
	}
	

	
	@Override
	public ResponseSplitMap saasSoldLedgerFormula(double saasPricee,
			boolean isReturn, int type, double discount, boolean isSellerArea,
			boolean isAgentscope, double purchaseDiscount,
			boolean hasGrandfatherXmer, boolean hasFatherXmer) {
		log.info("请求计算saas签约订单分账参数："+saasPricee+","+isReturn+","+type+","+discount+","+isSellerArea+","
				+isAgentscope+","+purchaseDiscount+","+hasGrandfatherXmer+","+hasFatherXmer);
        //返回是否修改成功
		ResponseSplitMap responseSplitMap = null; 
		TTransport transport = null;
        try
        {
            //调用分账服务的IP和端口号
            transport = new TSocket(ledgerIP, ledgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账服务的分账计算接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "SplitService");
            SplitService.Client client = new SplitService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            //调用计算分账
            responseSplitMap = client.saasLedger(saasPricee, isReturn, type, discount, isSellerArea, isAgentscope, purchaseDiscount, hasGrandfatherXmer, hasFatherXmer);

            //判断调用是否成功,若调用成功,则返回为0，若失败，则返回为1
            if (responseSplitMap.getCode()!=1)
            {
            	log.info("调用分账计算公式接口失败："+responseSplitMap);
            	responseSplitMap = null;
            }
        }
        catch (TException e)
        {
            //若调用抛出异常,则返回标识为-1
        	responseSplitMap = null;
            log.error("调用分账系统分账订单公式接口异常", e);
        }
        finally
        {
            //关闭连接
            transport.close();
        }
        return responseSplitMap;
	}
	
	@Override
	public boolean updateDebitcardQuota(Map<String, String> reqMap) {

		log.info("更新用户-商家储值卡参数："+reqMap);

		TTransport transport = null;
        try
        {
            //调用分账服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "ValueCardService");
            ValueCardService.Client client = new ValueCardService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            ResponseData  responseData = client.updateValueCard(reqMap);
            //判断调用是否成功,若调用成功,则返回为0，若失败，则返回为1
            if (responseData.getState()==0)
            {
            	return true;
            }
        }
        catch (TException e)
        {
            //若调用抛出异常,则返回标识为-1
            log.error("调用支付服务修改用户商家额度接口异常", e);
        }
        finally
        {
            //关闭连接
            transport.close();
        }
        return false;
	}
	
	public ResponseData updateExchangeCoin(List<Map<String, String>> list) {


		TTransport transport = null;
        try
        {
            //调用分账服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
            LiveWalletService.Client client = new LiveWalletService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            return client.updateExchangeCoinAmount(list);
            
        }
        catch (TException e)
        {
            //若调用抛出异常,则返回标识为-1
            log.error("调用支付服务修改用户商家额度接口异常", e);
        }
        finally
        {
            //关闭连接
            transport.close();
        }
        return null;
	}
	
	
	public ResponseData liveWalletOption(Map<String, String> map) {


		TTransport transport = null;
        try
        {
            //调用分账服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
            LiveWalletService.Client client = new LiveWalletService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            return client.liveWalletOption(map);
            
        }
        catch (TException e)
        {
            //若调用抛出异常,则返回标识为-1
            log.error("调用支付服务修改用户商家额度接口异常", e);
        }
        finally
        {
            //关闭连接
            transport.close();
        }
        return null;
	}
	
	public ResponseData updateLiveWalletInternalChange(
			Map<String, String> reqMap) {
        //返回是否修改成功
        TTransport transport = null;
        try
        {
            //调用分账系统支付服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账系统支付服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
            LiveWalletService.Client liveWClient = new LiveWalletService.Client(orderProtocol);
            //打开端口,开始调用
            transport.open();
            
            //调用更新钱包的接口
            return liveWClient.updateWalletInternalChange(reqMap);
        }
        catch (TException e)
        {
            log.error("调用支付服务的更新直播钱包接口异常"+e);
        }catch(Exception e){
        	log.error("",e);
        }finally
        {
            //关闭连接
            transport.close();
        }
        return null;
	}
    
//	/**
//	 * 
//	 * 方法描述：兑换优惠券 <br/>
//	 * 创建人： ChenBo <br/>
//	 * 创建时间：2017年6月16日下午5:41:44 <br/>
//	 * @param reqMap
//	 * @return
//	 */
//	public Result exchangeVoucher(String transNo, long uid, double voucherAmount, int propsType, int configPropsNumber, int number) {
//        //返回是否修改成功
//        TTransport transport = null;
//        try
//        {
//            //调用分账系统支付服务的IP和端口号
//            transport = new TSocket(transLedgerIP, transLedgerPort);
//            TFramedTransport frame = new TFramedTransport(transport);
//            // 设置传输协议为 TBinaryProtocol
//            TProtocol protocol = new TBinaryProtocol(frame);
//            //分账系统支付服务的综合服务接口模块
//            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "ManorPropsThriftService");
//            ManorPropsThriftService.Client client = new ManorPropsThriftService.Client(orderProtocol);
//            //打开端口,开始调用
//            transport.open();
//            
//            //调用更新钱包的接口
//            return client.exchangeVoucher(transNo, uid,  voucherAmount,  propsType,  configPropsNumber, number);
//        }
//        catch (TException e)
//        {
//            log.error("调用支付服务的更新直播钱包接口异常"+e);
//        }catch(Exception e){
//        	log.error("",e);
//        }finally
//        {
//            //关闭连接
//            transport.close();
//        }
//        return null;
//	}
	
    public String getSmsUrl()
    {
        return smsUrl;
    }
    
    public void setSmsUrl(String smsUrl)
    {
        this.smsUrl = smsUrl;
    }
    
    public String getUserServerUrl()
    {
        return userServerUrl;
    }
    
    public void setUserServerUrl(String userServerUrl)
    {
        this.userServerUrl = userServerUrl;
    }

    public String getTransLedgerIP()
    {
        return transLedgerIP;
    }

    public void setTransLedgerIP(String transLedgerIP)
    {
        this.transLedgerIP = transLedgerIP;
    }

    public int getTransLedgerPort()
    {
        return transLedgerPort;
    }

    public void setTransLedgerPort(int transLedgerPort)
    {
        this.transLedgerPort = transLedgerPort;
    }

    public String getPushMsgUrl()
    {
        return pushMsgUrl;
    }

    public void setPushMsgUrl(String pushMsgUrl)
    {
        this.pushMsgUrl = pushMsgUrl;
    }

	public String getLedgerIP() {
		return ledgerIP;
	}

	public void setLedgerIP(String ledgerIP) {
		this.ledgerIP = ledgerIP;
	}

	public int getLedgerPort() {
		return ledgerPort;
	}

	public void setLedgerPort(int ledgerPort) {
		this.ledgerPort = ledgerPort;
	}

	public CommonServiceImpl() {
		
	}




}