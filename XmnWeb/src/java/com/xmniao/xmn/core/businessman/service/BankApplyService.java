package com.xmniao.xmn.core.businessman.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.BankApplyDao;
import com.xmniao.xmn.core.businessman.dao.SellerAccountDao;
import com.xmniao.xmn.core.businessman.entity.TSellerAccount;
import com.xmniao.xmn.core.businessman.entity.TbankApply;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.http.util.AppMessageUtil;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.synthesizeService.MentionAccountService;
import com.xmniao.xmn.core.util.PropertiesUtil;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BankApplyService
 * 
 * 类描述： 商家银行卡修改申请
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2015年1月4日17时02分54秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BankApplyService extends BaseService<TbankApply> {
	
	@Autowired
	private BankApplyDao bankApplyDao;
	
	/**
	 * 账号信息dao
	 */
	@Autowired
	private SellerAccountDao sellerAccountDao;
	
	/**
	 * 商家service
	 */
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 消息推送ip地址
	 */
	private static final String sendMsgUrl = PropertiesUtil.readValue("http.msg.url");

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return bankApplyDao;
	}
/*	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;
	*/
	@Resource(name = "mentionAccountServiceClient")
	private ThriftClientProxy mentionAccountServiceClient;
	/**
	 * 推送消息
	 * @param seller
	 */
	public void sendNews(TbankApply  bankApply) throws Exception{

			Map<String, String> param = new HashMap<String, String>();
			SimpleDateFormat fromt = new SimpleDateFormat("yyyy-MM-dd");
			StringBuffer sendObject = new StringBuffer();
			//StringBuffer action = new StringBuffer();
			String title = "";//标题
			String content = "";//内容
			String iostoken = "";//ios标志
			Integer sellerid = 0;
			Integer aid = 0;//帐号id
			//查询银行卡信息
			TbankApply bankApplys = bankApplyDao.getObject(bankApply.getAppid().longValue());
			bankApply.setApplytype(bankApplys.getApplytype());
			sellerid = bankApplys.getAid();
			//获取iosToken标识
			TSellerAccount sellerAccount = new TSellerAccount();
			sellerAccount.setSellerid(sellerid);
			List<TSellerAccount> listAccount = sellerAccountDao.getAccountList(sellerAccount);
			if(null != listAccount && !listAccount.isEmpty()){
				iostoken = listAccount.get(0).getIostoken();
				aid = listAccount.get(0).getAid();
			}
			// 将获取的推送对象转换为指定格式
			sendObject.append("[");
			sendObject.append("{\"uid\":\"").append(aid).append("\",\"iostoken\":\"").append(iostoken==null ? "" :iostoken).append("\"}");
			sendObject.append("]");
			Map<String,String> contentMap=this.getContent(bankApply);
			param.put("uid", sendObject.toString());
			param.put("isAll", "false");//部分发送
			param.put("usertype", "3");//商户
			param.put("type", "3");//1 打开应用
			param.put("title", contentMap.get("title"));
			param.put("content",contentMap.get("content"));
			param.put("client", "2" );//商户
			String activity=null;
			param.put("action", 
					"{\"pushId\":\""+sellerid+"\",\"pushTitle\":\""+contentMap.get("title")+"\",\"pushContent\":\""+contentMap.get("content")+"\",\"openType\":3,\"action\":2,\"hintType\":0,\"client\":2,\"pushType\":1,\"activity\":\""+contentMap.get("activity")+"\"}");//7:审核
			param.put("contenttype", "1");//1:提示信息 
			param.put("remind", "0");//0 声音
			param.put("iosaction", "{\"action\" : \"\",\"alert\" : \""+contentMap.get("title")+"\",\"badge\" : 1,\"sound\" : \"default\",\"type\" : 105,\"account \" : 1}");//0 声音
			this.log.info("银行卡修改推送参数：param"+param + "url: " +sendMsgUrl+"/push/addMsg.html");
			JSONObject res = AppMessageUtil.pushMessageToApp(param);
			this.log.info(res);
	}
     
	public Map<String,String> getContent(TbankApply bankApply){
		SimpleDateFormat fromt = new SimpleDateFormat("yyyy-MM-dd");
		String title = null;
		String content = null;
		String activity=null;
		Map<String,String> map=new HashMap<String,String>();
		if(bankApply.getApplytype()==0){
		if(bankApply.getHandletype() == 0){
		    title = "银行卡修改审核通过";
		    content = "银行卡修改在"+fromt.format(new Date())+"通过审核";
		    activity="9";
		  }else{
		    title = "银行卡修改审核不通过";
			content = "原因："+bankApply.getHandleremark();
			activity="10";
		 }
		}else{
			if(bankApply.getHandletype() == 0){
			      title = "银行卡新增成功";
			      content = "银行卡新增在"+fromt.format(new Date())+"成功";
			      activity="9";
			  }else{
				  title = "银行卡新增失败";
				  content = "原因："+bankApply.getHandleremark();
				  activity="10";
			 }
		}
	  map.put("title", title);
	  map.put("content", content);
	  map.put("activity", activity);
	  return map;
	}
	
   public Object updateNot(TbankApply  bankApply) {//银行卡审核不通过
		Resultable resultable = null;		
		try {
			//进行异常捕获推送失败也要执行修改银行卡信息方法
			this.updateBankAndSeller(bankApply);
			this.sendNews(bankApply);
			resultable = this.logRe(true);
			this.fireLog(bankApply,resultable);
		} catch (Exception e) {
			logRe(false);
			this.log.error("修改异常", e);
			throw new ApplicationException("银行卡审核不通过",e,new Object[]{bankApply},new String[]{"修改银行卡申请编号",bankApply.getAppid().toString(),"审核不通过","审核不通过"});
		}
		return resultable;	
	}
      public void fireLog(TbankApply  bankApply,Resultable resultable){
		   String[] s={"修改银行卡申请编号",bankApply.getAppid().toString(),"审核不通过","审核不通过"};
		   super.fireLoginEvent(s,resultable.getSuccess()?1:0);
      }
      
      public Resultable logRe(boolean flag){//日志
    	  if(flag){
    		  this.log.info("修改成功");
  			  return  new Resultable(flag, "操作成功");
    	  }else{
    		  this.log.info("修改失败");
    		  return  new Resultable(flag, "操作失败");
    	  }
       }
       
       public void  updateBankAndSeller(TbankApply bankApply)throws Exception {//更新数据
    		try {
				super.update(bankApply);
				sellerService.updateSellerDateTime(bankApply.getAid());//更新主表t_seller 数据操作时间
			} catch (Exception e) {
				this.log.error("银行卡修改数据更新：", e);
				throw new ApplicationException("银行卡修改数据更新",e,new Object[]{bankApply});
			}
       }
	/**
	 * update(新增&通过)
	 * @param bankApply
	 * @return
	 */
	 public Object updateServer(TbankApply  bankApply){
			Resultable resultable = null;
			Map<String,String> mapParam=new HashMap<String,String>(30);
			TbankApply bankApplys=null;
			try {
				bankApplys = super.getObject(bankApply.getAppid().longValue());//根据id查询			
				this.sendParamMap(mapParam,bankApplys);//组装参数
				if(bankApplys.getApplytype().intValue()==0){
				    resultable =this.updateServerUpdate(mapParam,bankApplys);//更新
				}else{
					resultable =this.updateServerAdd(mapParam,bankApplys);//新增
				}
			    updateServerLog(bankApplys,resultable);//日志记录
			} catch (Exception e) {
				throw this.returnException(bankApply, bankApplys, e);
			}
			return resultable;
	 }
	 public ApplicationException returnException(TbankApply  bankApply,TbankApply  bankApplys,Exception e){//异常信息封装处理
		 if(bankApplys.getApplytype()==0){
			return new ApplicationException("银行卡更新审核",e,new Object[]{bankApply},new String[]{"修改银行卡申请编号",bankApply.getAppid().toString(),"审核通过","审核通过"});
			}else{
			return new ApplicationException("银行卡新增审核",e,new Object[]{bankApply},new String[]{"修改银行卡申请编号",bankApply.getAppid().toString(),"审核通过","审核通过"});
			}
	 }
	 
	 public Resultable  updateServerUpdate(Map<String,String> mapParam,TbankApply  bankApplys)throws Exception{
		 Resultable resultable = null;
		 try {
			MentionAccountService.Client client = (MentionAccountService.Client)(mentionAccountServiceClient.getClient());
			    //更新
			    Map<String,String> resultMap=null;
				resultMap=this.updateServerUpdateClient(client,bankApplys,mapParam);
			    String state=resultMap.get("state");
				if(state.equals("0")){     //0修改成功 ;1修改失败
					//进行异常捕获推送失败也要执行修改银行卡信息方法
					bankApplys.setHandletype(0);//修改为：已处理状态
					updateServerUpdate(bankApplys);//更新
					sendNews(bankApplys);
				}
				resultable = resultableLog(state.equals("0")?true:false,"修改",resultMap);
		} catch (Exception e) {
			this.log.error("修改银行卡：", e);
			throw new ApplicationException("修改银行卡",e,new Object[]{bankApplys});
		}finally{
			mentionAccountServiceClient.returnCon();//关闭连接
		}
		 return resultable;
	 }
	 
	 public Map<String,String> updateServerUpdateClient(MentionAccountService.Client client,TbankApply  bankApplys,Map<String,String> mapParam)throws Exception{
		    Map<String,String> resultMap=null;
		    this.log.info("修改银行卡接口开始：接口名称：updateMentionAccount");
			this.log.info("审核银行卡接口参数：bankApplys"+bankApplys+"  accountid:" + bankApplys.getAccountid().toString());
			resultMap=client.updateMentionAccount(mapParam);
			this.log.info("0修改银行卡接口调用成功 ;1修改银行卡接口调用失败  ："+resultMap.get("msg"));
			this.log.info("修改银行卡接口结束：");
			return resultMap;
	 }
	 
	 public Resultable  updateServerAdd(Map<String,String> mapParam,TbankApply  bankApplys)throws Exception{ 
		    Resultable resultable = null;
		    Map<String,String> resultMap=null;
		    MentionAccountService.Client client = (MentionAccountService.Client)(mentionAccountServiceClient.getClient());
			try {
				 resultMap=this.updateServerAddClient(client, bankApplys, mapParam);
				 String state=resultMap.get("state");
				if(state.equals("0")){//0修改成功 ;1修改失败
					bankApplys.setHandletype(0);//进行异常捕获推送失败也要执行修改银行卡信息方法
					updateServerUpdate(bankApplys);
					sendNews(bankApplys);//消息推送
				}
				resultable = resultableLog(state.equals("0")?true:false,"修改",resultMap);
			}catch (Exception e) {
				this.log.error("新增银行卡：", e);
				throw new ApplicationException("新增银行卡",e,new Object[]{bankApplys});
			   }finally{
				mentionAccountServiceClient.returnCon();//关闭连接
			}
			return resultable;
	 }
	 
	 public Map<String,String> updateServerAddClient(MentionAccountService.Client client,TbankApply  bankApplys,Map<String,String> mapParam)throws Exception{
		    Map<String,String> resultMap=null;
		    this.log.info("新增银行卡接口开始：接口名称：addMentionAccount");
			this.log.info("新增银行卡接口参数：bankApplys"+bankApplys+"  accountid:" + bankApplys.getAccountid().toString());
			resultMap=client.addMentionAccount(mapParam);
			this.log.info("0添加银行卡接口调用成功 ;1添加银行卡接口调用失败  countnumb："+resultMap.get("msg"));
			this.log.info("添加银行卡接口结束：");
			return resultMap;
	 }  
	 
     public void updateServerUpdate(TbankApply bankApplys)throws Exception {//更新数据
  		try {
				super.update(bankApplys);
				sellerService.updateSellerDateTime(bankApplys.getAid());//更新主表t_seller 数据操作时间
			} catch (Exception e) {
				this.log.error("银行卡修改数据更新：", e);
				throw new ApplicationException("银行卡修改数据更新",e,new Object[]{bankApplys});
			}
     }
     
	 public Resultable resultableLog(boolean flag,String info,Map<String,String> resultMap){
		 Resultable resultable=new Resultable();
		 if(flag){
			  this.log.info(info+"成功");
			  resultable= new Resultable(flag, "操作成功");
		 }else{
			 this.log.info(info+"失败");
			 resultable= new Resultable(flag, "操作失败");
		 }
		 resultable.setMsg(resultMap.get("msg"));
		 return resultable;
	 }
	 
	 public void updateServerLog(TbankApply bankApplys,Resultable resultable){//新增&通过 --》日志
		 String[] strArray=new String[4];
		 int applyType=bankApplys.getApplytype().intValue();
		 strArray[0]="银行卡编号";
		 strArray[1]=bankApplys.getAppid().toString();
		 strArray[2]=applyType==1?"新增银行卡":"修改银行卡";
		 strArray[3]="审核";
		 fireLoginEvent(strArray,resultable.getSuccess()?1:0);	
	 }
	 
	//查看补充资料
	public TbankApply getSupplementaryInformation(Long id){
		TbankApply bankApply=bankApplyDao.getObject(id);
		//为补充资料图片填充服务器路径
		String baseUrl=PropertiesUtil.readValue("file.upload.fastDFS.http");
		if(bankApply.getLicense()!=null){
			bankApply.setLicense(baseUrl+bankApply.getLicense());
		}
		if(bankApply.getUpidcard()!=null){
			bankApply.setUpidcard(baseUrl+bankApply.getUpidcard());
		}
		if(bankApply.getDwidcard()!=null){
			bankApply.setDwidcard(baseUrl+bankApply.getDwidcard());
		}
		return bankApply;
	}
	/**
	 * 银行卡调用接口新增与修改参数组装 
	 * @author：dong'jietao
	 * @param map
	 * @param bankApplys
	 * @param accountid
	 */
public void sendParamMap(Map<String,String> map,TbankApply bankApplys){//银行卡调用接口新增与修改参数组装 
	 map.put("id", bankApplys.getAccountid().toString());
	 map.put("type", String.valueOf(SellerConstants.CARD_BANK_TYPE));
	 map.put("account", String.valueOf(bankApplys.getBankid()));
	 map.put("cardType", String.valueOf(bankApplys.getType()));
	 map.put("isuse", String.valueOf(bankApplys.getModifitype()));
	 map.put("userName", String.valueOf(bankApplys.getFullname()));
	 map.put("bankName", String.valueOf(bankApplys.getBankname()));
	 map.put("mobileId", String.valueOf(bankApplys.getBankphone()));
	 map.put("ispublic", String.valueOf(bankApplys.getIspublic()));
	 map.put("idtype", String.valueOf(bankApplys.getIdtype()));
	 map.put("identity", String.valueOf(bankApplys.getIdcard()));
	 map.put("bank",  String.valueOf(bankApplys.getBank()));
	 map.put("abbrev", String.valueOf(bankApplys.getAbbrev()));
	 map.put("province",String.valueOf(bankApplys.getLocation()));
	 map.put("cityname", String.valueOf(bankApplys.getCityname()));
	 map.put("license", String.valueOf(bankApplys.getLicense()));
	 map.put("upidcard", String.valueOf(bankApplys.getUpidcard()));
	 map.put("dwidcard", String.valueOf(bankApplys.getDwidcard()));	 
	 if(bankApplys.getApplytype()==1){//银行卡增加必须的参数
		 /**
		  * 修改前 用户类型为商家 其余为用户
		  */
		// bankApplys.setAccounttype(bankApplys.getAccounttype()==1?2:1);//商家类型 1 商家 2 会员----》支付系统用户类型 1用户 2商家 3合作商
		 /**
		  * 修改后 用户类型 默认为商家 其余类型不作处理
		  */
		Integer accountType =  bankApplys.getAccounttype();
		if(accountType==1){//商家类型 1 商家 2 会员----》支付系统用户类型 1用户 2商家 3合作商     参数互转
			accountType = 2;
		}
		bankApplys.setAccounttype(accountType);//商家类型 1 商家 2 会员----》支付系统用户类型 1用户 2商家 3合作商
		map.put("userType", String.valueOf(bankApplys.getAccounttype()));
		map.put("uId", String.valueOf(bankApplys.getAid()));//这里的aid就是商户id,和app端已经确认。
	 }
  }
}
