package com.xmniao.xmn.core.businessman.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.AllianceShopDao;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.AllianceAccount;
import com.xmniao.xmn.core.businessman.entity.AllianceRelation;
import com.xmniao.xmn.core.businessman.entity.AllianceShop;
import com.xmniao.xmn.core.businessman.entity.Card;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerAccount;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.entity.TBank;
import com.xmniao.xmn.core.common.service.TBankService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.http.util.AppMessageUtil;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.synthesizeService.FailureException;
import com.xmniao.xmn.core.thrift.service.synthesizeService.MentionAccountService;
import com.xmniao.xmn.core.thrift.service.synthesizeService.SynthesizeService;
import com.xmniao.xmn.core.util.NMD5;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StringUtils;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AllianceShopService
 * 
 * 类描述：商家联盟店管理
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-01-16 11:06:45
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class AllianceShopService extends BaseService<AllianceShop>{

	/**
	 * 商家dao
	 */
	@Autowired
	private AllianceShopDao allianceShopDao;
	
	@Autowired
	private AllianceAccountService allianceAccountService;
	
	@Autowired
	private SellerDao sellerDao;
	

	
	@Override
	protected BaseDao getBaseDao() {
		return allianceShopDao;
	}
	
	@Resource(name = "mentionAccountServiceClient")
	private ThriftClientProxy mentionAccountServiceClient;
	
	/**
	 * 消息推送ip地址
	 */
	private static final String sendMsgUrl = PropertiesUtil.readValue("http.msg.url");
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private TBankService tBankService;//银行信息获取
	
	
	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;
	
	/**
	 * 插入商家联盟店与 商家关联信息
	 * @param ids
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int insertAllianceRelation(String ids,Long id){
		if(StringUtils.hasLength(ids)){
			String[] array =  StringUtils.paresToArray(ids, ",");
			AllianceRelation a=null; 
			List<AllianceRelation> list  = new ArrayList<AllianceRelation>(array.length);
			for(String i:array){
				a = new AllianceRelation();
				a.setId(id);
				a.setSellerId(Long.valueOf(i));
				list.add(a);
			}
			return allianceShopDao.insertAllianceRelation(list);
		}
		return 0;
	}
	
	/**
	 * 添加联盟店
	 * @param allianceShop
	 * @param allianceAccount
	 * @param ids
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean add(AllianceShop allianceShop,AllianceAccount allianceAccount,String ids){
		try {
			Date d=new Date();
			allianceShop.setSdate(d);
			allianceShop.setUdate(d);
			addReturnId(allianceShop);
			String allianceShopId =  allianceShop.getId().toString();
			String[] s={"区域代理编号",allianceShopId,"新增"};
			fireLoginEvent(s);
			
			insertAllianceRelation(ids, allianceShop.getId());
			String[] i={"区域代理编号",allianceShopId,"关联商家","关联商家"};
			fireLoginEvent(i);
			
			allianceAccountService.add(allianceAccount,allianceShop.getId(),d);
			String[] a={"区域代理编号",allianceShopId,"添加区域代理帐号","添加"};
			allianceAccountService.fireLoginEvent(a);
			
			//要求增加钱包信息
			this.addMoney(allianceAccount);
			String[] m = {"区域代理编号", allianceShopId, "添加区域代理钱包信息", "添加"};
			fireLoginEvent(m);
			
			return true;
		} catch (Exception e) {
			String[] s={"区域代理名称",allianceShop.getAllianceName(),"新增"};
			fireLoginEvent(s,0);
			this.log.error("添加商家联盟店信息异常", e);
		}
		return false;
	}
	
	
	public void deleteAllianceRelation(Long id){
		allianceShopDao.deleteAllianceRelation(id);
	}
	
	
	
	/**
	 * 新增区域代理钱包
	 * @param seller
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void addMoney(AllianceAccount allianceAccount){
		//1添加钱包成功
		int flag = -1;
		//新增钱包
		SynthesizeService.Client client = null;
		Map<String,String> paramMap=new HashMap<String,String>();	
		Map<String,String> resultMap=null;
		try {
			client = (SynthesizeService.Client)(synthesizeServiceClient.getClient());
			this.putParamsMap(paramMap, allianceAccount);
			this.log.info("区域代理添加钱包_start phoneid=" + allianceAccount.getAccount() + " password="
					+ paramMap.get("password") + " Sellername=" + allianceAccount.getAllianceName());
			//flag = client.addWallet(seller.getSellerid().toString(), "2", paramMap.get("password"),seller.getSellername());
			resultMap=client.addWalletMap(paramMap);
			if(resultMap!=null && "0".equals(resultMap.get("state"))){//0添加成功 1添加失败2 参数异常
			   this.log.info("区域代理添加钱包成功！");
			}
		} catch (Exception e) {
			throw new ApplicationException("区域代理添加钱包失败",e);
		} finally{
			String[] s={"区域代理编号",allianceAccount.getId().toString(),"新增钱包","新增"};
			if(resultMap!=null && "0".equals(resultMap.get("state"))){//0添加成功 1添加失败2 参数异常
				flag=0;
			}
			fireLoginEvent(s,flag>0?1:0);
			if(null != client){
				synthesizeServiceClient.returnCon();
			}
		}
	}
	
	public void putParamsMap(Map<String,String> paramMap, AllianceAccount allianceAccount){
		   paramMap.put("uId", String.valueOf(allianceAccount.getId()));
		   paramMap.put("userType","4");   // userType: 4 区域代理
		   paramMap.put("password", "");//密码默认为空
		   paramMap.put("phoneNumber", String.valueOf(allianceAccount.getAccount()));
		   paramMap.put("sellerName", String.valueOf(allianceAccount.getAllianceName())); 
	}
	
	   /**
	    * @author dong'jietao 
	    * @date 2015年4月30日 下午2:13:36
	    * @TODO 通过接口获取银行卡list
	    * @param card
	    * @return
	    */
		public Pageable<Card> getCardList(Card card) {
			Pageable<Card> sellerInfoList = new Pageable<Card>(card);
			List<Card>  cardList=new ArrayList<Card>();
			MentionAccountService.Client client = (MentionAccountService.Client)(mentionAccountServiceClient.getClient());			
			try {
				List<Map<String,String>> cardslist=client.getMentionAccount(String.valueOf(card.sellerid),2);
             for(Map m:cardslist){   
             	cardList.add(getCardFromMap(m));
             }
			}
			catch(Exception e){
				e.printStackTrace();
				this.log.info("获取数据的服务未开启或开启出现异常!");
				sellerInfoList.setTotal(0L);
				return sellerInfoList;
			}
			sellerInfoList.setContent(cardList);
			sellerInfoList.setTotal(Long.parseLong(String.valueOf(cardList.size())));
			return sellerInfoList;
		}
		/**
		 * @author dong'jietao 
		 * @date 2015年4月30日 下午5:04:14
		 * @TODO 从返回的Map中组装参数
		 * @param map
		 * @return
		 */
		public Card getCardFromMap(Map<String,String> map){
			Card card=new Card();
			card.setSellerid(Integer.parseInt(map.get("uId")));
			card.setId(map.get("id"));
			card.setCardId(String.valueOf(map.get("account")));
			card.setCardType(String.valueOf(map.get("cardtype")));
			card.setCardUserName(map.get("username"));
			card.setBankName(map.get("bankname"));
			card.setCardPhone(String.valueOf(map.get("mobileid")));
			card.setCardPurpose(String.valueOf(map.get("isuse")));
			card.setIspublic(String.valueOf(map.get("ispublic")));
			card.setIdtype(String.valueOf(map.get("idtype")));
			card.setIdentity(String.valueOf(map.get("identity")));
			card.setBank(String.valueOf(map.get("bank")));
			card.setAbbrev(String.valueOf(map.get("abbrev")));
			card.setProvince(String.valueOf(map.get("province")));
			card.setCityname(String.valueOf(map.get("cityname")));
			return card;
		}
		/**
		 * @author wangzhimin 
		 * @date 2015年8月26日 下午1:54:31
		 * @TODO 银行卡增加执行方法
		 * @param card
		 * @return
		 */
		public Resultable addCard(Card card) {
			Resultable resultable=new Resultable();
			Map<String,String> paraMap=new HashMap<String,String>();
			this.assembleParm(card);
			this.addCardParamMap(paraMap, card);
			MentionAccountService.Client client = (MentionAccountService.Client)(mentionAccountServiceClient.getClient()); 
			try {
				Map<String,String> resultMap=client.addMentionAccount(paraMap);
				resultable.setMsg(resultMap.get("msg"));
				resultable.setSuccess(String.valueOf(SellerConstants.MULTIP_SHOP_ADD_CARD_STATUS).equals(resultMap.get("state")) ? true : false);
				this.log.info("区域代理绑定银行卡返回信息: " + resultMap.get("msg"));
			}catch(Exception e){
				this.log.info("区域代理绑定银行卡失败！");
				throw new ApplicationException("区域代理绑定银行卡异常", e, new Object[]{card}, new String[]{"区域代理",  card.getSellername(), "添加银行卡", "添加"});
			}
			return resultable;
		}
		/**
		 * @author dong'jietao 
		 * @date 2015年5月11日 下午3:11:49
		 * @TODO 银行卡消息添加推送
		 * @param card
		 */
		public void sendMsg(Card card,Resultable resultable){
	    Map<String, String> param = new HashMap<String, String>();
	    SimpleDateFormat fromt = new SimpleDateFormat("yyyy-MM-dd");
	    param.put("uid", String.valueOf(card.getSellerid()));
	    param.put("usertype", "4");
	    param.put("isAll", "false");
	    param.put("tid",String.valueOf(card.getSellerid()));//推送id
	    param.put("title", "银行卡增加信息");//信息标题
	    param.put("content", "商户编号:"+card.getSellerid()+"银行卡增加成功");//信息内容
	    param.put("type", "1");//1 打开应用
	    String title=null;
	    String content=null;
		if(resultable.getSuccess()==true){
			title = "银行卡增加成功";
			content = "银行卡增加改在"+fromt.format(new Date())+"通过审核";
		}else{
			title = "银行卡增加失败";
			content = "原因："+resultable.getMsg();
		}
	    param.put("iosaction", "{\"action\" : \"\",\"alert\" : \""+title+"\",\"badge\" : 1,\"sound\" : \"default\",\"type\" : 105,\"account \" : 1}");//0 声音
		this.log.info("银行卡增加推送参数：param"+param + "url: " +sendMsgUrl+"/push/addMsg.html");
		try {
			JSONObject res = AppMessageUtil.pushMessageToApp(param);
			//发送成功后更新发送状态
			if(res != null){
				if(res.getBooleanValue("status")){
					String[] s={"商户编号",String.valueOf(card.getSellerid()),"银行卡增加信息推送","信息推送"};
					fireLoginEvent(s);
				}
			}
		 } catch (Exception e) {
			String[] s={"商户编号",String.valueOf(card.getSellerid()),"银行卡增加信息推送","信息推送"};
			fireLoginEvent(s,0);
			e.printStackTrace();
		 }
		}
		/**
		 * @author dong'jietao 
		 * @date 2015年5月8日 下午2:11:10
		 * @TODO 根据区域id 获取区域名称
		 * @param areaId
		 * @return
		 */
		public TArea getAreaName(Integer areaId){
			TArea area=new TArea();
			area.setAreaId(areaId);
			List<TArea> list=areaDao.getLdAll(area);
			if(list.size()>0){
				area=list.get(0);
			}
			return area;
		}
		/**
		 * @author dong'jietao 
		 * @date 2015年4月30日 下午4:37:44
		 * @TODO 调用接口签参数组装
		 * @param paraMap
		 * @param card
		 */
		public void addCardParamMap(Map<String,String> paraMap,Card card){
			paraMap.put("uId", String.valueOf(card.getSellerid()));
			if(!"null".equals(card.getId())&&card.getId()!=null){
				paraMap.put("id", String.valueOf(card.getId()));
			}
			paraMap.put("type", String.valueOf(2));
			paraMap.put("account", String.valueOf(card.getCardId()));
			paraMap.put("cardType", String.valueOf(card.getCardType()));
			paraMap.put("userName", String.valueOf(card.getCardUserName()));
			paraMap.put("bankName", String.valueOf(card.getBankName()));
			paraMap.put("mobileId", String.valueOf(card.getCardPhone()));
			paraMap.put("isuse", String.valueOf(card.getCardPurpose()));
			paraMap.put("userType", String.valueOf(4));//用户类型  1用户  2商家 3合作商 4 区域代理
			paraMap.put("ispublic", String.valueOf(card.getIspublic()));
			paraMap.put("idtype", String.valueOf(card.getIdtype()));
			paraMap.put("identity", String.valueOf(card.getIdentity()));
			paraMap.put("bank", String.valueOf(card.getBank()));
			paraMap.put("abbrev", String.valueOf(card.getAbbrev()));
			paraMap.put("province", String.valueOf(card.getProvince()));
			paraMap.put("cityname", String.valueOf(card.getCityname()));	
		}
		/**
		 * @author dong'jietao 
		 * @date 2015年5月5日 上午10:49:15
		 * @TODO 银行卡信息修改
		 * @param card
		 * @return
		 */
		public Card getUpdateByCard(Card card){
			TArea tarea=new TArea();
			MentionAccountService.Client client = (MentionAccountService.Client)(mentionAccountServiceClient.getClient());
			Map<String, String> resultMap=null;
			try {
				resultMap = client.getMentionAccountById(String.valueOf(card.getId()));
			} catch (FailureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(resultMap.size()>0){
				card=getCardFromMap(resultMap);
			}else{
				return null;
			} 
			tarea.setTitle(card.province);
			tarea=this.getAreaNameByName(tarea);
			card.setProvinceId(tarea.getAreaId());//根据省名获取省id
			tarea=new TArea(); 
			tarea.setTitle(card.getCityname());
			tarea.setPid(card.getProvinceId());
			card.setCitynameId(this.getAreaNameByName(tarea).getAreaId());//根据省id和区域名字 获得该区域id
			return card;
		}
		/**
		 * @author dong'jietao 
		 * @date 2015年5月8日 下午2:11:10
		 * @TODO 根据区域 获取区域名称
		 * @param area
		 * @return
		 */
		public TArea getAreaNameByName(TArea area){
			List<TArea> list=areaDao.getLdAll(area);
			if(list.size()>0){
				area=list.get(0);
			}
			return area;
		}
		/**
		 * @author wangzhimin 
		 * @date 2015年8月26日 下午2:20:31
		 * @TODO 银行卡信息修改
		 * @param card
		 * @return
		 */
		public Resultable updateCard(Card card) {
			Resultable resultable=new Resultable();
			Map<String,String> paraMap=new HashMap<String,String>();
			this.assembleParm(card);
			this.addCardParamMap(paraMap, card);
			MentionAccountService.Client client = (MentionAccountService.Client)(mentionAccountServiceClient.getClient());
			try {
				Map<String,String> resultMap=client.updateMentionAccount(paraMap);
				resultable.setMsg(resultMap.get("msg"));
				resultable.setSuccess(String.valueOf(SellerConstants.MULTIP_SHOP_UPDATE_CARD_STATUS).equals(resultMap.get("state")) ? true : false);
				this.log.info("区域代理修改银行卡返回信息: " + resultMap.get("msg"));
			} catch(Exception e){
				this.log.info("区域代理修改银行卡失败！");
				throw new ApplicationException("区域代理修改银行卡异常", e, new Object[]{card}, new String[]{"区域代理",  card.getSellername(), "修改银行卡", "修改"});
			}
			return resultable;
		}
		
		
		public void assembleParm(Card card){
			card.setProvince(this.getAreaName(card.getProvinceId()).getTitle());
			card.setCityname(this.getAreaName(card.getCitynameId()).getTitle());
			TBank tbank =new TBank();
			tbank.setAbbrev(card.getBank());
			card.setBank(tBankService.getTBank(tbank).getBankname());
		}

}
