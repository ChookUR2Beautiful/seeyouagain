package com.xmniao.xmn.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tls.sigcheck.tls_sigcheck;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：TLSSigUtil   
* 类描述：   TLS后台API接口调用工具类
* 创建人：yezhiyong   
* 创建时间：2016年8月1日 下午2:36:09   
* @version    
*
 */
public class TLSUtil {
	
    public TLSUtil() {}
	
	/**
	 * 报错日志
	 */
	private final static Logger log = LoggerFactory.getLogger(TLSUtil.class);

	/**
	 * @throws Exception 
	 * 
	* @Title: getTLSSig
	* @Description: 生成tls的 sig 的方法
	* @return String    返回类型
	* @author
	* @throws
	 */
	public static String getTLSSig(String sdkAppid,String identifier) throws Exception {
		
		String basePath = TLSUtil.class.getClassLoader().getResource("").getPath();
		log.info("TLSUtil getTLSSig basePath={}",basePath);
		tls_sigcheck demo = new tls_sigcheck();
		if(System.getProperty("os.name").toLowerCase().contains("windows")){
			demo.loadJniLib(basePath + "tls/jnisigcheck.dll");//windows环境下,动态库的加载路径
		}else{
			demo.loadJniLib(basePath + "tls/jnisigcheck.so"); //linux环境下,动态库的加载路径
		}
		
        // windows环境下,动态库的加载路径
/*		log.info(Thread.currentThread().getContextClassLoader().getResource("tls\\jnisigcheck.dll").toString().replace("file:", ""));
		demo.loadJniLib(Thread.currentThread().getContextClassLoader().getResource("tls\\jnisigcheck.dll").toString().replace("file:", ""));*/
		
//		System.out.println(Thread.currentThread().getContextClassLoader().getResource("tls/jnisigcheck.dll").toString().replace("file:", ""));
//        demo.loadJniLib(Thread.currentThread().getContextClassLoader().getResource("tls/jnisigcheck.dll").toString().replace("file:", ""));
		//log.info(new PropertiesUtil().getValue("jniLib", "conf_live.properties") + "jnisigcheck.so");
		//demo.loadJniLib(new PropertiesUtil().getValue("jniLib", "conf_live.properties") + "jnisigcheck.so");
		
//        demo.loadJniLib("D:\\xmnwork\\apache-tomcat-7.0.69\\webapps\\xmnService\\WEB-INF\\classes\\tls\\jnisigcheck.dll");
        //demo.loadJniLib("/home/tls/tls_sig_api/src/jnisigcheck.so"); //linux环境下,动态库的加载路径
        
        //生成私钥
//		File priKeyFile = new File(basePath + "tls/ec_key.pem");//线上秘钥
		File priKeyFile = new File(basePath + "tls/private_key.pem");//测试服务器专用
        //File priKeyFile = new File(new PropertiesUtil().getValue("jniLib", "conf_live.properties") + "ec_key.pem");
        StringBuilder strBuilder = new StringBuilder();
        String s = "";
        
        BufferedReader br = new BufferedReader(new FileReader(priKeyFile));
        while ((s = br.readLine()) != null) {
            strBuilder.append(s + '\n');
        }
        br.close();
        String priKey = strBuilder.toString();        
		int ret = demo.tls_gen_signature_ex2(sdkAppid, identifier, priKey);
        
        if (0 != ret) {
        	log.info("ret " + ret + " " + demo.getErrMsg());
        }

        //验证秘钥
//        File pubKeyFile = new File(basePath + "tls/public.pem");//线上共钥
        File pubKeyFile = new File(basePath + "tls/public_key.pem");//测试服务器专用
        //File pubKeyFile = new File(new PropertiesUtil().getValue("jniLib", "conf_live.properties") + "public.pem");
        br = new BufferedReader(new FileReader(pubKeyFile));
		strBuilder.setLength(0);
        while ((s = br.readLine()) != null) {
            strBuilder.append(s + '\n');
        }
        br.close();
        String pubKey = strBuilder.toString();        
		ret = demo.tls_check_signature_ex2(demo.getSig(), pubKey, sdkAppid, identifier);
        if (0 != ret) {
        	log.info("ret " + ret + " " + demo.getErrMsg());
        	return demo.getErrMsg();
        }
        return demo.getSig(); 
	}
	
	/**
	 * 
	* @Title: getTlsUserInfo
	* @Description: 调用TLS后台API设置用户资料
	* @return boolean    返回类型
	* @author
	* @throws
	 */
	public static boolean getTlsUserInfo(Map<Object, Object> paramMap) {
		//日志
		log.info("paramMap:" + paramMap);
		
		String tlsSig = "";
		String sdkAppid = "";
		String identifier = "";
		String account = "";
		
		try {
			//tlsSig
			if (paramMap.get("tlsSig") != null) {
				tlsSig = paramMap.get("tlsSig").toString();
			}
			//应用id
			if (paramMap.get("sdkAppid") != null) {
				sdkAppid = paramMap.get("sdkAppid").toString();
			}
			//管理员账号
			if (paramMap.get("identifier") != null) {
				identifier = paramMap.get("identifier").toString();
			}
			//用户账号
			if (paramMap.get("account") != null) {
				account = paramMap.get("account").toString();
			}
			
			Integer randNo=new Random().nextInt(99999999);
			//调用tls REST API设置用户资料接口URL
			String url = "https://console.tim.qq.com/v4/profile/portrait_get?usersig=" + tlsSig + "&sdkappid=" + sdkAppid + "&identifier=" + identifier + "&random="+randNo +"&contenttype=json";
			
			//修改资料的账号
			Map<Object, Object> getMap = new HashMap<>();
			JSONArray jsonArray1 = new JSONArray();
			jsonArray1.add(account);
			getMap.put("To_Account", jsonArray1);
			
			//修改资料的信息
			JSONArray jsonArray2 = new JSONArray();
			jsonArray2.add("Tag_Profile_IM_Nick");
			/**
			 * Tag_Profile_IM_Nick	昵称
			 * Tag_Profile_IM_Image 头像url
			 * Tag_Profile_IM_AllowType 加好友验证方式
			 * Tag_Profile_IM_SelfSignature 个性签名
			 *
			 */
			
			getMap.put("TagList", jsonArray2);
			System.out.println(JSONObject.toJSONString(getMap));
	    	//获取腾讯云资料
	    	String result = HttpConnectionUtil.doPost(url, JSONObject.toJSONString(getMap));
	    	
	    	if (StringUtils.isNotEmpty(result)) {
				JSONObject json = JSONObject.parseObject(result);
				if ("fail".equalsIgnoreCase(json.get("ActionStatus").toString())) {
					return false;
				}else {
					return true;
				}
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	* @Title: setTlsUserInfo
	* @Description: 调用TLS后台API设置用户资料
	* @return boolean    返回类型
	* @author
	* @throws
	 */
	public static boolean setTlsUserInfo(Map<Object, Object> paramMap) {
		//日志
		log.info("paramMap:" + paramMap);
		
		String tlsSig = "";
		String sdkAppid = "";
		String identifier = "";
		String account = "";
		String nickName = "";
		String image = "";
		String selfSignature = "";
		
		try {
			//tlsSig
			if (paramMap.get("tlsSig") != null) {
				tlsSig = paramMap.get("tlsSig").toString();
			}
			//应用id
			if (paramMap.get("sdkAppid") != null) {
				sdkAppid = paramMap.get("sdkAppid").toString();
			}
			//管理员账号
			if (paramMap.get("identifier") != null) {
				identifier = paramMap.get("identifier").toString();
			}
			//用户账号
			if (paramMap.get("account") != null) {
				account = paramMap.get("account").toString();
			}
			//设置用户的昵称
			if (paramMap.get("nickName") != null) {
				nickName = paramMap.get("nickName").toString();
			}
			//设置用户的头像
			if (paramMap.get("image") != null) {
				image = paramMap.get("image").toString();
			}
			//设置用户的个性签名
			if (paramMap.get("selfSignature") != null) {
				selfSignature = paramMap.get("selfSignature").toString();
			}
			Integer randNo=new Random().nextInt(99999999);
			//调用tls REST API设置用户资料接口URL
			String url = "https://console.tim.qq.com/v4/profile/portrait_set?usersig=" + tlsSig + "&sdkappid=" + sdkAppid + "&identifier=" + identifier + "&random="+randNo +"&contenttype=json";
			
			//修改资料的账号
			Map<Object, Object> setMap = new HashMap<>();
			setMap.put("From_Account", account);
			
			//修改资料的信息
			JSONArray jsonArray = new JSONArray();
			
			/**
			 * Tag_Profile_IM_Nick	昵称
			 * Tag_Profile_IM_Image 头像url
			 * Tag_Profile_IM_AllowType 加好友验证方式
			 * Tag_Profile_IM_SelfSignature 个性签名
			 *
			 */
			if (StringUtils.isNotEmpty(nickName)) {
				//修改昵称
				Map<Object, Object> map = new HashMap<>();
				map.put("Tag", "Tag_Profile_IM_Nick");
				map.put("Value", nickName);
				jsonArray.add(map);
			}
			
			if (StringUtils.isNotEmpty(image)) {
				//修改头像
				Map<Object, Object> map = new HashMap<>();
				map.put("Tag", "Tag_Profile_IM_Image");
				map.put("Value", image);
				jsonArray.add(map);
			}
			
			if (StringUtils.isNotEmpty(selfSignature)) {
				//修改个性签名
				Map<Object, Object> map = new HashMap<>();
				map.put("Tag", "Tag_Profile_IM_SelfSignature");
				map.put("Value", selfSignature);
				jsonArray.add(map);
			}
			
			setMap.put("ProfileItem", jsonArray);
	    	//请求设置资料
	    	String result = HttpConnectionUtil.doPost(url, JSONObject.toJSONString(setMap));
	    	
	    	if (StringUtils.isNotEmpty(result)) {
				JSONObject json = JSONObject.parseObject(result);
				if ("fail".equalsIgnoreCase(json.get("ActionStatus").toString())) {
					return false;
				}else {
					return true;
				}
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	* @Title: accountImport
	* @Description: 导入账号信息到腾讯云
	* @return boolean    返回类型
	* @author
	* @throws
	 */
	public static boolean accountImport(Map<Object, Object> paramMap) {
		//日志
		log.info("paramMap:" + paramMap);
		
		String tlsSig = "";
		String sdkAppid = "";
		String identifier = "";
		String account = "";
		String nickName = "";
		String image = "";
		
		try {
			//tlsSig
			if (paramMap.get("tlsSig") != null) {
				tlsSig = paramMap.get("tlsSig").toString();
			}
			//应用id
			if (paramMap.get("sdkAppid") != null) {
				sdkAppid = paramMap.get("sdkAppid").toString();
			}
			//管理员账号
			if (paramMap.get("identifier") != null) {
				identifier = paramMap.get("identifier").toString();
			}
			//用户账号
			if (paramMap.get("account") != null) {
				account = paramMap.get("account").toString();
			}
			//设置用户的昵称
			if (paramMap.get("nickName") != null) {
				nickName = paramMap.get("nickName").toString();
			}
			//设置用户的头像
			if (paramMap.get("image") != null) {
				image = paramMap.get("image").toString();
			}
			Integer randNo=new Random().nextInt(99999999);
			//调用导入 API  URL
			String url = "https://console.tim.qq.com/v4/im_open_login_svc/account_import?usersig=" + tlsSig + "&identifier=" + identifier + "&sdkappid=" + sdkAppid + "&random="+randNo +"&contenttype=json";
			
			//导入账号信息
			Map<Object, Object> importMap = new HashMap<>();
			importMap.put("Identifier", account);
			//账号昵称
			importMap.put("Nick", nickName);
			//头像地址
			importMap.put("FaceUrl", image);
			
			//请求接口
			String result = HttpConnectionUtil.doPost(url, JSONObject.toJSONString(importMap));
			
			if (StringUtils.isNotEmpty(result)) {
				JSONObject json = JSONObject.parseObject(result);
				if ("fail".equalsIgnoreCase(json.get("ActionStatus").toString())) {
					return false;
				}else {
					return true;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			log.info(""+e);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	* @Title: createGroup
	* @Description: 创建腾讯群组
	* @return boolean    返回类型
	* @author
	* @throws
	 */
	public static boolean createGroup(Map<Object, Object> paramMap) {
		//日志
		log.info("paramMap:" + paramMap);
		
		String tlsSig = "";
		String sdkAppid = "";
		String identifier = "";
		String account = "";
		String name = "";
		String type = "";
		String groupId = "";

		try {
			// tlsSig
			if (paramMap.get("tlsSig") != null) {
				tlsSig = paramMap.get("tlsSig").toString();
			}
			// 应用id
			if (paramMap.get("sdkAppid") != null) {
				sdkAppid = paramMap.get("sdkAppid").toString();
			}
			// 管理员账号
			if (paramMap.get("identifier") != null) {
				identifier = paramMap.get("identifier").toString();
			}
			// 群主id
			if (paramMap.get("account") != null) {
				account = paramMap.get("account").toString();
			}
			// 群名称
			if (paramMap.get("name") != null) {
				name = paramMap.get("name").toString();
			}
			// 群组形态
			if (paramMap.get("type") != null) {
				type = paramMap.get("type").toString();
			}
			// 群主id
			if (paramMap.get("groupId") != null) {
				groupId = paramMap.get("groupId").toString();
			}
			Integer randNo=new Random().nextInt(99999999);
			//腾讯REST API创建群组url 
			String url = "https://console.tim.qq.com/v4/group_open_http_svc/create_group?usersig=" + tlsSig + "&identifier=" + identifier +"&sdkappid=" + sdkAppid + "&random="+randNo +"&contenttype=json";
			
			Map<Object, Object> groupMap = new HashMap<>();
			//群主id
			groupMap.put("Owner_Account", account);
			//群组形态
			groupMap.put("Type", type);
			//群组id
			groupMap.put("GroupId", groupId);
			//群名称
			groupMap.put("Name", name);
			//申请加群处理方式:包含FreeAccess（自由加入），NeedPermission（需要验证），DisableApply（禁止加群），不填默认为NeedPermission（需要验证）。
			groupMap.put("ApplyJoinOption", "FreeAccess");
			
			//请求接口
			String result = HttpConnectionUtil.doPost(url, JSONObject.toJSONString(groupMap));
			
			if (StringUtils.isNotEmpty(result)) {
				JSONObject json = JSONObject.parseObject(result);
				if ("fail".equalsIgnoreCase(json.get("ActionStatus").toString())) {
					return false;
				}else {
					return true;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 
	* @Title: sendBarrage
	* @Description: 发送弹幕
	* @return boolean
	 */
	public static boolean sendBarrage(Map<Object, Object> paramMap) {
		//日志
		log.info("paramMap:" + paramMap);
		
		String tlsSig = "";
		String sdkAppid = "";
		String identifier = "";
		String account = "";
		String MsgType = "";
		String Text = "";
		String groupId = "";
		String Index="";
		String Data="";

		try {
			// tlsSig
			if (paramMap.get("tlsSig") != null) {
				tlsSig = paramMap.get("tlsSig").toString();
			}
			// 应用id
			if (paramMap.get("sdkAppid") != null) {
				sdkAppid = paramMap.get("sdkAppid").toString();
			}
			// 管理员账号
			if (paramMap.get("identifier") != null) {
				identifier = paramMap.get("identifier").toString();
			}
			// 群主id
			if (paramMap.get("account") != null) {
				account = paramMap.get("account").toString();
			}
			// 消息类型
			if (paramMap.get("MsgType") != null) {
				MsgType = paramMap.get("MsgType").toString();
			}
			// 消息内容
			if (paramMap.get("Text") != null) {
				Text = paramMap.get("Text").toString();
			}
			// 群主id
			if (paramMap.get("groupId") != null) {
				groupId = paramMap.get("groupId").toString();
			}
			// 表情下标
			if (paramMap.get("Index") != null) {
				Index = paramMap.get("Index").toString();
			}
			// 表情数据
			if (paramMap.get("Data") != null) {
				Data = paramMap.get("Data").toString();
			}
			Integer randNo=new Random().nextInt(99999999);
			//腾讯REST API创建群组url 
			String url="https://console.tim.qq.com/v4/group_open_http_svc/send_group_msg?usersig=" + tlsSig + "&identifier=" + identifier +"&sdkappid=" + sdkAppid + "&random="+randNo +"&contenttype=json";
			
			Map<Object, Object> groupMap = new HashMap<>();
			//指定消息发送者
			groupMap.put("From_Account", account);
			//Random
			groupMap.put("Random", randNo);
			//群组id
			groupMap.put("GroupId", groupId);

			List<Map<Object,Object>> MsgBodyMap=new ArrayList<Map<Object,Object>>();
			
			Map<Object, Object> MsgMap = new HashMap<Object, Object>();
			//消息
			if(MsgType.equals("TIMTextElem")){
				MsgMap.put("MsgType", MsgType);
				Map<Object, Object> MsgContent = new HashMap<Object, Object>();
				MsgContent.put("Text", Text);
				MsgMap.put("MsgContent", MsgContent);
				MsgBodyMap.add(MsgMap);
			}else{
				//表情
				MsgMap.put("MsgType", MsgType);
				Map<Object, Object> MsgContentEm = new HashMap<Object, Object>();
				MsgContentEm.put("Index",Index );
				MsgContentEm.put("Data", Data);
				MsgMap.put("MsgContent", MsgContentEm);
				MsgBodyMap.add(MsgMap);
			}
			
			groupMap.put("MsgBody", MsgBodyMap);
			//请求接口
			String result = HttpConnectionUtil.doPost(url, JSONObject.toJSONString(groupMap));
			
			if (StringUtils.isNotEmpty(result)) {
				JSONObject json = JSONObject.parseObject(result);
				if ("fail".equalsIgnoreCase(json.get("ActionStatus").toString())) {
					return false;
				}else {
					return true;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	* @Title: sendmsg
	* @Description: 发送群聊
	* @param paramMap post参数
	* @return boolean 成功/失败
	* @throws
	 */
	public static boolean sendmsg(Map<Object, Object> map){
		log.info("TLSUtil sendmsg()开始执行：pramap={}",map);
		try{
			//用户加签
			String tlsSig = map.get("tlsSig").toString();
			//用户账号
			String identifier = map.get("identifier").toString();
			//应用标示
			String sdkappid = map.get("sdkappid").toString();
			//随机数
			Integer random=new Random().nextInt(99999999);
			
			//拼接发送群组信息请求url
			StringBuffer urlBuff = new StringBuffer();
			urlBuff.append("https://console.tim.qq.com/v4/openim/sendmsg").append("?");
			urlBuff.append("usersig=").append(tlsSig).append("&");
			urlBuff.append("identifier=").append(identifier).append("&");
			urlBuff.append("sdkappid=").append(sdkappid).append("&");
			urlBuff.append("random=").append(random).append("&contenttype=json");
			
			//组装 
			Map<Object,Object> paraMap = new HashMap<Object,Object>();
			paraMap.put("To_Account", map.get("To_Account"));
			paraMap.put("MsgRandom", random);
			paraMap.put("MsgTimeStamp", new Long(System.currentTimeMillis()).intValue());
			paraMap.put("MsgRandom", random);
			List<Map<Object,Object>> contentList = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> contentMap = new HashMap<Object,Object>();
			contentMap.put("MsgType", map.get("MsgType").toString());
			contentMap.put("MsgContent", map.get("MsgContent"));
			contentList.add(contentMap);
			paraMap.put("MsgBody", contentList);
			
			//转换请求参数map为json格式字符串
			String paramJson = JSONObject.toJSONString(paraMap);
			log.info("sendmsg()开始请求腾讯API接口：url={},postParam={}",urlBuff.toString(),paramJson);
			//请求接口
			String result = HttpConnectionUtil.doPost(urlBuff.toString(), paramJson);
			System.out.println("升级用户  发送结果：----------------------------------："+result);
			//请求结果处理判断
			if (StringUtils.isNotEmpty(result)) {
				JSONObject json = JSONObject.parseObject(result);
				if ("fail".equalsIgnoreCase(json.get("ActionStatus").toString())) {
					return false;
				}
			}else {
				return false;
			}
			log.info("TLSUtil sendmsg()执行结束:result={}",result);
			return true;
		}catch(Exception e){
			log.error("TLSUtil调用腾讯单聊接口异常:{}",e.toString());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	* @Title: sendGroupMsg
	* @Description: 发送群聊信息
	* @param paramMap post参数
	* @return boolean 成功/失败
	* @throws
	 */
	public static boolean sendGroupMsg(Map<Object, Object> map){
		/**
		 * {
			    "GroupId": "@TGS#2C5SZEAEF",
			    "From_Account": "leckie", //指定消息发送者（选填）
			    "Random": 8912345, // 随机数字，五分钟数字相同认为是重复消息
			    "MsgBody": [  // 消息体，由一个element数组组成，详见字段说明
			        {
			            "MsgType": "TIMTextElem", // 文本
			            "MsgContent": {
			                "Text": "red packet"
			            }
			        },
			        {
			            "MsgType": "TIMFaceElem", // 表情
			            "MsgContent": {
			                "Index": 6,
			                "Data": "abc\u0000\u0001"
			            }
			        }
			    ]
			}
		 */
		
		log.info("TLSUtil sendGroupMsg()开始执行：pramap={}",map);
		try{
			//用户加签
			String tlsSig = map.get("tlsSig").toString();
			//用户账号
			String identifier = map.get("identifier").toString();
			//应用标示
			String sdkappid = map.get("sdkappid").toString();
			//随机数
			Integer random=new Random().nextInt(99999999);
			
			//拼接发送群组信息请求url
			//https://console.tim.qq.com/v4/group_open_http_svc/send_group_msg?usersig=xxx&identifier=admin&sdkappid=88888888&random=99999999&contenttype=json
			StringBuffer urlBuff = new StringBuffer();
			urlBuff.append("https://console.tim.qq.com/v4/group_open_http_svc/send_group_msg").append("?");
			urlBuff.append("usersig=").append(tlsSig).append("&");
			urlBuff.append("identifier=").append(identifier).append("&");
			urlBuff.append("sdkappid=").append(sdkappid).append("&");
			urlBuff.append("random=").append(random).append("&contenttype=json");
			
			//组装请求参数
			Map<Object,Object> paraMap = new HashMap<Object,Object>();
			paraMap.put("GroupId", map.get("GroupId").toString());
			
			//是否指定发送者
			if (map.get("From_Account") != null && StringUtils.isNotEmpty(map.get("From_Account").toString())) {
				paraMap.put("From_Account", map.get("From_Account").toString());
			}
			paraMap.put("Random", random);
			
			//群聊信息内容
			List<Map<Object,Object>> contentList = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> contentMap = new HashMap<Object,Object>();
			contentMap.put("MsgType", map.get("MsgType").toString());
			contentMap.put("MsgContent", map.get("MsgContent"));
			contentList.add(contentMap);
			paraMap.put("MsgBody", contentList);
			
			//转换请求参数map为json格式字符串
			String paramJson = JSONObject.toJSONString(paraMap);
			log.info("sendGroupMsg()开始请求腾讯API接口：url={},postParam={}",urlBuff.toString(),paramJson);
			//请求接口
			String result = HttpConnectionUtil.doPost(urlBuff.toString(), paramJson);
			System.out.println("发送结果：----------------------------------："+result);
			
			//请求结果处理判断
			if (StringUtils.isNotEmpty(result)) {
				JSONObject json = JSONObject.parseObject(result);
				if ("fail".equalsIgnoreCase(json.get("ActionStatus").toString())) {
					return false;
				}
			}else {
				return false;
			}
			
			log.info("TLSUtil sendGroupMsg()执行结束:result={}",result);
			return true;
			
		}catch(Exception e){
			log.error("TLSUtil调用腾讯群聊接口异常:{}",e.toString());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	* @方法名称: BlackListOperation
	* @描述: 黑名单操作(拉入黑名单/解除黑名单/校验黑名单)
	* @返回类型 boolean
	* @创建时间 2016年10月10日
	* @param map
	* @param type (1:拉入黑名单，2：解除黑名单，3：校验黑名单)
	* @return
	 */
	public static boolean BlackListOperation(Map<Object, Object> map,int type){
		try{
			//用户加签
			String tlsSig = map.get("tlsSig").toString();
			//用户账号
			String identifier = map.get("identifier").toString();
			//应用标示
			String sdkappid = map.get("sdkappid").toString();
			//随机数
			Integer random=new Random().nextInt(99999999);
			
			
			StringBuffer urlBuff = new StringBuffer();
			if(type==1){
				//拉入黑名单url
			urlBuff.append("https://console.tim.qq.com/v4/sns/black_list_add").append("?");
			}else if(type==2){
				//解除黑名单
			urlBuff.append("https://console.tim.qq.com/v4/sns/black_list_delete").append("?");
			}else if(type==3){
				//校验黑名单
			urlBuff.append("https://console.tim.qq.com/v4/sns/black_list_check").append("?");
			}else{
				//参数类型异常
				log.info("TLSUtil BlackListOperation()执行结束:黑名单操作失败，操作类型type参数异常");
				return false;
			}
			urlBuff.append("usersig=").append(tlsSig).append("&");
			urlBuff.append("identifier=").append(identifier).append("&");
			urlBuff.append("sdkappid=").append(sdkappid).append("&");
			urlBuff.append("random=").append(random).append("&contenttype=json");
			Map<Object,Object> paramMap=new HashMap<>();
			paramMap.put("From_Account", map.get("From_Account"));
			paramMap.put("To_Account", map.get("To_Accoun"));
			//如果是黑名单校验，则需要传入校验类型（校验类型：“BlackCheckResult_Type_Both”表示双向校验；“BlackCheckResult_Type_Singal”表示单向校验。）
			if(type==3){
				paramMap.put("CheckType","BlackCheckResult_Type_Singal");
			}
			
			//转换请求参数map为json格式字符串
			String paramJson = JSONObject.toJSONString(paramMap);
			log.info("BlackListOperation()开始请求腾讯API接口：url={},postParam={}",urlBuff.toString(),paramJson);
			//请求接口
			String result = HttpConnectionUtil.doPost(urlBuff.toString(), paramJson);
			System.out.println("发送结果：----------------------------------："+result);
			
			//求结果处理判断
			if (StringUtils.isNotEmpty(result)) {
				JSONObject json = JSONObject.parseObject(result);
				if ("fail".equalsIgnoreCase(json.get("ActionStatus").toString())) {
					log.info("TLSUtil BlackListOperation()执行结束:黑名单操作失败，result={},操作类型type={}",result,type);
					return false;
				}
			}else {
				log.info("TLSUtil BlackListOperation()执行结束:黑名单操作失败，result={},操作类型type={}",result,type);
				return false;
			}
			
			log.info("TLSUtil BlackListOperation()执行结束:result={},操作类型type={}",result,type);
			return true;
			
		}catch(Exception e){
			log.error("TLSUtil调用腾讯黑名单操作异常:{}",e.toString());
			e.printStackTrace();
			return false;
		}
		
	}
}
