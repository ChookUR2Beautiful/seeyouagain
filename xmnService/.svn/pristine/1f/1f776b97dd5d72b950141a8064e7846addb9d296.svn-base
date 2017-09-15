package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.util.TLSUtil;

public class TLSSigTest {
	
	/**
	 * 日志
	 */
	private static final Logger log = Logger.getLogger(TLSSigTest.class);

	public static void main(String[] args) {
		try {
			//测试获取tls的UserSig
			String adminSig = TLSUtil.getTLSSig("1400014980", "oEPmJwEHiaAtkrWeuQjaM1wcCTyA");//最多32位
			System.out.println(adminSig);
			
			//创建群组
			Map<Object, Object> groupMap = new HashMap<>();
			//应用id
			groupMap.put("sdkAppid", "1400014980");
			//管理员账号
			groupMap.put("identifier", "admin");
			//群主id(主播账号)
			groupMap.put("account", "123456654");
			//管理员签名
			groupMap.put("tlsSig", adminSig);
			//群名称
			groupMap.put("name", "创建群组");
			//群组形态
			groupMap.put("type", "AVChatRoom");
			//自定义群组
			String groupId = "@xmniao#123";
			//群组id
			groupMap.put("groupId", groupId);
			
			boolean groupResult = TLSUtil.createGroup(groupMap);
			
			
			/*Map<Object, Object> map = new HashMap<>();
			map.put("sdkAppid", "1400013873");
			map.put("identifier", "admin");
			map.put("account", "123456654");
			map.put("nickName", "测试群组");
			map.put("avatar", "img/M00/01/C3/wKgyMld3K6GAC9_bAAd5w7UDK8I090.jpg");
			map.put("adminSig", adminSig);
			map.put("selfSignature", "个性签名123456");
			//导入账号信息
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("sdkAppid", map.get("sdkAppid").toString());
			paramMap.put("identifier", map.get("identifier").toString());
			paramMap.put("account", map.get("account").toString());
			paramMap.put("nickName", new String(map.get("nickName").toString().getBytes(), "ISO-8859-1"));
			paramMap.put("image", "http://gzdev.xmniao.com:88/" + map.get("avatar").toString());
			
			paramMap.put("tlsSig", map.get("adminSig").toString());
			
			try {
				//导入账号
				boolean importResult = TLSUtil.accountImport(paramMap);
				if (!importResult) {
					log.info("导入自有账号失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("导入自有账号失败");
			}
			
			//上传个性签名
			paramMap.put("selfSignature", new String(map.get("nickName").toString().getBytes(), "ISO-8859-1"));
			paramMap.remove("nickName");
			paramMap.remove("image");
			
			try {
				//上传腾讯云资料
				boolean setResult = TLSUtil.setTlsUserInfo(paramMap);
				
				if (!setResult) {
					log.info("上传腾讯云资料失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("上传腾讯云资料失败");
			}*/
			
			
				
		/*	//方式一:
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("From_Account", "kuangfeng");
			JSONArray jsa = new JSONArray();
			Map<Object, Object> map = new HashMap<>();
			map.put("Tag", "Tag_Profile_IM_Nick");
			map.put("Value", "maosong");
			jsa.add(map);
	    	paramMap.put("ProfileItem", jsa);
	    	JSONObject parseObject = JSONObject.parseObject(JSONObject.toJSONString(paramMap));
	    	doPost(urlStr, parseObject);*/
	    	
			
			
			//方式二
			/*Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("From_Account", "kuangfeng");
			JSONArray jsa = new JSONArray();
			Map<Object, Object> map = new HashMap<>();
			map.put("Tag", "Tag_Profile_IM_Nick");
			map.put("Value", "maosong12");
			jsa.add(map);
	    	paramMap.put("ProfileItem", jsa);
	    	doPost(urlStr, JSONObject.toJSONString(paramMap));*/
	    	
			
	    	/*//回调函数
	    	String callBackUrl = "http://172.16.130.155:8080/tlscallback?SdkAppid=1400012767&CallbackCommand=State.StateChange&contenttype=json";
	    	Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("CallbackCommand", "State.StateChange");
			JSONArray jsa = new JSONArray();
			Map<Object, Object> map = new HashMap<>();
			map.put("Action", "login");
			map.put("To_Account", "kuangfeng");
			map.put("Reason", "Unregister");
			jsa.add(map);
	    	paramMap.put("Info", jsa);
	    	doPost(callBackUrl, JSONObject.toJSONString(paramMap));*/
	    		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String callback() {
		//回调函数
    	String callBackUrl = "http://192.168.2.205:28080/tlscallback?SdkAppid=1400012767&CallbackCommand=State.StateChange&contenttype=json";
    	Map<Object, Object> paramMap = new HashMap<>();
		paramMap.put("CallbackCommand", "State.StateChange");
		JSONArray jsa = new JSONArray();
		Map<Object, Object> map = new HashMap<>();
		map.put("Action", "login");
		map.put("To_Account", "kuangfeng");
		map.put("Reason", "Unregister");
		jsa.add(map);
    	paramMap.put("Info", jsa);
    	doPost(callBackUrl, JSONObject.toJSONString(paramMap));
    	log.info("回调中");
		return "回调中";
	}
	
	public static JSONObject doPost(String url, JSONObject json) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		try {
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(entity);// 返回json格式：
				System.err.println("===================");
				System.out.println(result);
				System.err.println("===================");
				response = JSONObject.parseObject(result);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	
	public static String doPost(String url, String paramStr) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);

		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(10000).setConnectTimeout(10000)
				.setSocketTimeout(10000).build();
		CloseableHttpResponse response = null;
		try {
			StringEntity s = new StringEntity(paramStr);
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(s);
			post.setConfig(config);
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			System.out.println("content:" + content);
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JSONObject.toJSONString(response);
	}

}
