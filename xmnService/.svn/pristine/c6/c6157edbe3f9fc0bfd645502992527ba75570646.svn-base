package com.xmniao.xmn.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;
/**
 * 
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PushSingleDevice
 * @类描述：推送消息给设备
 * @创建人： yeyu
 * @创建时间 2016年9月13日 上午10:51:42
 * @version
 */
public class PushSingleUtil {
	
	/**
	 * 批量发送用户
	* @Title: AndroidAccountList
	* @Description: 
	* @return JSONObject    返回类型
	* @throws
	 */
	public static JSONObject AndroidAccountList(Long accessid,String secretkey,int deviceType,String title,String msg,List<String> accountList,Map<String, Object> custom,String sendTime){
		XingeApp x = new XingeApp(accessid, secretkey);
		JSONObject obj = x.pushAccountList(deviceType, accountList,message(title,msg,custom,sendTime));
		return obj;
	}
	
	/**
	 * 批量发送用户
	* @Title: ISOAccountList
	* @Description: 
	* @return JSONObject    返回类型
	* @throws
	 */
	public static JSONObject ISOAccountList(Long accessid,String secretkey,int deviceType,String title,List<String> accountList,Map<String, Object> custom,String sendTime){
		XingeApp x = new XingeApp(accessid, secretkey);
		JSONObject obj = x.pushAccountList(deviceType, accountList, messageIOS(title, "beep.wav", "",custom,sendTime), XingeApp.IOSENV_DEV);
		return obj;
	}
	/**
	 * 单个发送用户
	* @Title: AndroidAccountList
	* @Description: 
	* @return JSONObject    返回类型
	* @throws
	 */
	public static JSONObject AndroidAccount(Long accessid,String secretkey,int deviceType,String title,String msg,String account,Map<String, Object> custom,String sendTime){
		XingeApp x = new XingeApp(accessid, secretkey);
		JSONObject obj = x.pushSingleAccount(deviceType, account,message(title,msg,custom,sendTime));
		return obj;
	}
	

	
	/**
	 * 
	 * @param accessid 应用ID
	 * @param secretkey 密钥
	 * @param devicetoken 手机唯一身份识别码
	 * @param title 消息标题
	 * @param msg  消息内容
	 * @return
	 */
	public static JSONObject Android(Long accessid,String secretkey,String devicetoken,String title,String msg,Map<String, Object> custom,String sendTime){
		XingeApp x = new XingeApp(accessid, secretkey);
		JSONObject obj = x.pushSingleDevice(devicetoken, message(title,msg,custom,sendTime));
		return obj;
	}
	
	/**
	 * 
	 * @param accessid 应用ID
	 * @param secretkey 密钥
	 * @param devicetoken 手机唯一身份识别码
	 * @param alert 消息标题
	 * @param sound	通知声音
	 * @param category iOS类别
	 * @return
	 */
	public static JSONObject IOS(Long accessid,String secretkey,String devicetoken,String alert,String sound,String category,Map<String, Object> custom,String sendTime){
		XingeApp x = new XingeApp(accessid, secretkey);
		JSONObject obj = x.pushSingleDevice(devicetoken, messageIOS(alert,sound,category,custom,sendTime), 1);
		return obj;
	}
	
	
	/**
	 * 单个发送用户
	* @Title: iosAccountList
	* @Description: 
	* @return JSONObject    返回类型
	* @throws
	 */
	public static JSONObject IOSAccount(Long accessid,String secretkey,int deviceType,String title,String msg,String account,String sendType,Map<String, Object> custom,String sendTime){
		XingeApp x = new XingeApp(accessid, secretkey);
		JSONObject obj = x.pushSingleAccount(deviceType, account, messageIOS(msg,"beep.wav","",custom,sendTime), XingeApp.IOSENV_DEV);
		return obj;
	}
	
	/**
	 * 
	* @Title: pushAccountListMultiple
	* @Description: 批量发送
	* @return JSONObject
	 */
/*	public static JSONObject pushAccountListMultiple(Long accessid,String secretkey,int deviceType,String title,String msg,Map<String, Object> custom,List<String> accountList,String sendTime){
		XingeApp x = new XingeApp(accessid, secretkey);
		JSONObject ret = x.createMultipush(message(title,msg,custom,sendTime));
		if (ret.getInt("ret_code") != 0){
            return (ret);
		}else {
			int pushId=ret.getJSONObject("result").getInt("push_id");
			JSONObject obj=x.pushAccountListMultiple(pushId, accountList);
			return obj;
		}
	
	}*/
	
	private static Message message(String title,String msg,Map<String, Object> custom,String sendTime){
		Message message = new Message();
		Style style = new Style(1);
		ClickAction action = new ClickAction();
		style = new Style(3,1,0,1,0);
		action.setActionType(ClickAction.TYPE_ACTIVITY);
		action.setUrl("http://xg.qq.com");
		message.setTitle(title);
		message.setContent(msg);
		message.setStyle(style);
		message.setAction(action);
		message.setCustom(custom);
		TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime1);
		
		if(sendTime!=null && !"".equals(sendTime))
		message.setSendTime(sendTime);
		message.setType(Message.TYPE_NOTIFICATION);
		return message;
	}
	
	private static MessageIOS messageIOS(String alert,String sound,String category,Map<String, Object> custom,String sendTime){
		MessageIOS message = new MessageIOS();
		message.setExpireTime(86400);
		message.setAlert(alert);
		message.setBadge(1);
		message.setSound(sound);
		message.setCategory(category);
		if(sendTime!=null && !"".equals(sendTime))
		message.setSendTime(sendTime);
		TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime1);
		message.setCustom(custom);
		return message;
	}
	//取消定时消息
	public static JSONObject cancelTimingPush(Long accessid,String secretkey,String pushId){
		XingeApp x = new XingeApp(accessid, secretkey);
		JSONObject obj=x.cancelTimingPush(pushId);
		return obj;
	}
	
	public static void main(String[] args) throws ParseException {		
		
		testAndroid();
		//testIos();		
	}
	public static void testAndroid () {
		//寻蜜鸟秘钥
		Long anroidAccessid = 2100219303L;
		String anroidSecreKey ="3ef89f469d0c1c1b48e1e87033aab3da";
		
		//鸟人直播秘钥
		Long birdAccessid = 2100250185l;
		String birdAnroidSecreKey ="20f8e76b40ec56c3b04747a3616d0ad6";
		JSONObject xmnAndroidret =PushSingleUtil.AndroidAccount(anroidAccessid, anroidSecreKey, 0, "您有一条新消息", "你关注的主播【昵称】在【店名】直播，喊你到现场参加", "605909",null, "2017-06-11 00:22:00");
		//JSONObject birdAndroidret =PushSingleUtil.AndroidAccount(birdAccessid, birdAnroidSecreKey, 0, "您有一条新消息", "你关注的主播【昵称】在【店名】直播，喊你到现场参加", "606673",null, "2017-06-11 00:22:00");
		System.out.println("寻蜜鸟Android信鸽推送----"+xmnAndroidret.toString());	
		//System.out.println("鸟人直播Android信鸽推送----"+birdAndroidret.toString());	
	}
	
	public static void testIos () {
		//寻蜜鸟秘钥
		Long iosAccessId = 2200219020L;
		String iosSecreKey = "a6f046a0f3a09a37026c18c620009e1c";
		
		//鸟人直播秘钥
		Long birdIosAccessId = 2200249736L;
		String birdIosSecreKey = "154b20fd52e7fec42732a14125ae9ae4";
		JSONObject xmnIosRet = PushSingleUtil.IOSAccount(iosAccessId, iosSecreKey, 1, "您有一条新消息", "你关注的主播【昵称】在【店名】直播，喊你到现场参加", "606673",  "0", null, "2017-06-11 00:22:00");
		JSONObject birdIosRet = PushSingleUtil.IOSAccount(birdIosAccessId, birdIosSecreKey, 1, "您有一条新消息", "你关注的主播【昵称】在【店名】直播，喊你到现场参加", "606673",  "0", null, "2017-06-11 00:22:00");
		System.out.println("寻蜜鸟Ios信鸽推送----"+xmnIosRet.toString());	
		System.out.println("鸟人直播Ios信鸽推送----"+birdIosRet.toString());	
	}
}
