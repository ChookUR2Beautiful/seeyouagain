package com.xmniao.xmn.core.businessman.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.SellerAccountDao;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.Card;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerAccount;
import com.xmniao.xmn.core.businessman.entity.TSellerDetailed;
import com.xmniao.xmn.core.businessman.entity.TSellerPic;
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
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.NMD5;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerService
 * 
 * 类描述： 商家(商户)
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class MultipShopService extends BaseService<TSeller> {
	
	/**
	 * 商家dao
	 */
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerPicService sellerPicService;
	
	@Autowired
	private SellerDetailedService sellerDetailedService;
	/**
	 * 账号信息dao
	 */
	@Autowired
	private SellerAccountDao sellerAccountDao;

	@Autowired
	private SellerAccountService sellerAccountService;
	
	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;
	
	@Resource(name = "mentionAccountServiceClient")
	private ThriftClientProxy mentionAccountServiceClient;
	@Override
	protected BaseDao getBaseDao() {
		return sellerDao;
	}
	@Autowired
	private TBankService tBankService;//银行信息获取
	/**
	 * 消息推送ip地址
	 */
	private static final String sendMsgUrl = PropertiesUtil.readValue("http.msg.url");
	@Autowired
	private AreaDao areaDao;
	/**
	 * 添加或则编辑连锁店信息
	 * @param seller
	 */
	//@Transactional(propagation = Propagation.REQUIRED)
	public void addOrUpdteMultipShop(TSeller seller){
		boolean isAdd=isAdd(seller);
		try {
			if(isAdd){
				multipShopAdd(seller);//增加
			}else{
				//取得所有（新增的修改的）要同步到MongDB的子店
				multipShopUpdate(seller);//修改
			}
		}catch (Exception e) {
		   throw getException(isAdd,e,seller);
		}
	}
	
	/**
	 * 取得要同步到MongoDB的子店商家Id.
	 * 
	 * @param seller
	 * @return
	 */
	private String getSynchronizeSubShopIdsToMongoDB(TSeller seller) {
		String ids = seller.getIds();
		StringBuilder idsbuBuilder = null;
		TSeller s = new TSeller();
		s.setFatherid(seller.getSellerid());
		List<TSeller> sellers = sellerDao.getList(s);
		if (ids != null) {
			idsbuBuilder = new StringBuilder(ids);
			List<String> idsList = Arrays.asList(ids.split(","));// 前台传进来的
			for (TSeller se : sellers) {
				String id = se.getSellerid().toString();
				if (!idsList.contains(id)) {
					idsbuBuilder.append(id);
					idsbuBuilder.append(",");
				}
			}
		} else {
			idsbuBuilder = new StringBuilder();
			for (TSeller se : sellers) {
				idsbuBuilder.append(se.getSellerid().toString());
				idsbuBuilder.append(",");
			}
		}
		ids = idsbuBuilder.length() > 0 ? new String(
				idsbuBuilder.deleteCharAt(idsbuBuilder.length() - 1)) : "";
		return ids;
	}
	
	/**
	 * 取得要同步到MongoDB的子店商家Id.
	 * 
	 * @param seller
	 * @param updateMap 
	 * @return
	 */
	private void synchronizeSubShopToMongoDB(TSeller seller) {
		this.log.info("需要同步到MongDB的商家有："+seller.getIds());
		Map<String,Object> updateMap = new HashMap<String,Object>();
		updateMap.put("fatherid", seller.getFatherid());
		updateMap.put("udate", DateUtil.formatDate(DateUtil.Y_M_D_HMS,seller.getUdate()) );
		updateMap.put("lssellername", seller.getLssellername());
		System.out.println("要更新的MongoDB数据是："+updateMap);
		for(String id : seller.getIds().split(",")){
			if("" != id){
				sellerService.updateMongo(Integer.parseInt(id),updateMap);
			}
		}
	}
	private boolean isAdd(TSeller seller){//
		boolean flag=true;
		if(null!=seller.getSellerid()){
			flag=false;
		}
		return flag;
	}
	public ApplicationException getException(boolean flag,Exception e,TSeller seller){		
		if(flag){
			this.log.error("连锁店新增异常：", e);
			return new ApplicationException("连锁店新增",e,new Object[]{seller},new String[]{"连锁店编号",String.valueOf(seller.getSellerid()),"新增","新增"});
		}else{
			this.log.error("连锁店数据更新异常：", e);
			return new ApplicationException("连锁店更新",e,new Object[]{seller},new String[]{"连锁店编号",String.valueOf(seller.getSellerid()),"数据更新","更新"});
		}
	}
	public void multipShopAdd(TSeller seller){
		try {
			seller.setIsmultiple(SellerConstants.SELLER_ISMULTIPLE);//连锁店  是否为连锁店默认0否 1是
			sellerAdd(seller);//seller添加
			sellerLogoAdd(seller);//添加商家logo
			sellerDetailedAdd(seller);//添加商家参考地标
			this.subShop(seller); //关联子店
			this.log.info("修改子店后同步到MongoDB");
			synchronizeSubShopToMongoDB(seller);
		} catch (Exception e) {
			throw new ApplicationException("连锁店新增",e,new Object[]{seller});
		}
	}
    public void multipShopUpdate(TSeller seller){
    	try {
    		String ids = getSynchronizeSubShopIdsToMongoDB(seller);
			sellerUpdate(seller);//seller更新
			if(seller.getPicid()==null){
				sellerLogoAdd(seller);//添加商家logo
			}else{
				sellerLogoUpate(seller);//更新商家logo
			}
			if(seller.getDetailedId()==null){
				sellerDetailedAdd(seller);//添加商家参考地标
			}else{
				sellerDetailedUpdate(seller);//更新商家参考地标
			}
			this.subShop(seller); //关联子店
			seller.setIds(ids);
			this.log.info("修改子店后同步到MongoDB");

			synchronizeSubShopToMongoDB(seller);
		} catch (Exception e) {
			throw new ApplicationException("连锁店更新",e,new Object[]{seller});
		}
	}
	public void  putSellerAccount(TSellerAccount sellerAccount,TSeller seller){//sellerAccount拼装参数
		try {
			Date date = new Date();
			sellerAccount.setSellerid(seller.getSellerid());
			sellerAccount.setAccount(seller.getAccount());
			sellerAccount.setNname(seller.getNname());
			sellerAccount.setFullname(seller.getAccountName());
			sellerAccount.setPhone(seller.getPhoneid());
			sellerAccount.setType(SellerConstants.SELLER_ACCOUNT_TYPE);//4：连锁店帐号
			sellerAccount.setAid(seller.getAid());
			sellerAccount.setSdate(date);
			seller.setUdate(date);
			seller.setStatus(SellerConstants.SELLER_STATUS);//连锁店 审核状态默认为已签约   
			seller.setFatherid(SellerConstants.SELLER_FATHERID);//总店
			seller.setIsmultiple(SellerConstants.SELLER_ISMULTIPLE);//连锁店
		} catch (Exception e) {
			this.log.error("参数拼装异常：",e);
			throw new ApplicationException("参数拼装",e,new Object[]{sellerAccount,seller});
		}
	}
	public void sellerAdd(TSeller seller){//seller添加 --sellerAccount账户添加
		TSellerAccount sellerAccount=new TSellerAccount();
		try {
		    this.putSellerAccount(sellerAccount, seller);//添加
			String pwd = seller.getPhoneid().substring(seller.getPhoneid().length()-6,seller.getPhoneid().length());//密码默认为联系人手机后六位
			sellerAccount.setPassword(NMD5.Encode(pwd));//加密
			super.addReturnId(seller);//添加连锁店基本信息
			int sellerid = seller.getSellerid();//获取商家id
			sellerAccount.setSellerid(sellerid);
			sellerAccountDao.add(sellerAccount);//添加帐号信息
			addMoney(seller);//添加钱包	
		} catch (Exception e) {
//			this.log.error("连锁店添加异常：", e);
			throw new ApplicationException("连锁店添加",e,new Object[]{seller});
		}
	}
	public void sellerUpdate(TSeller seller){//seller更新 --sellerAccount账户更新
		TSellerAccount sellerAccount=new TSellerAccount();
		try {
		    this.putSellerAccount(sellerAccount, seller);
			super.update(seller);//编辑连锁店信息
			if(sellerAccount.getAid() != null){
				sellerAccountDao.update(sellerAccount);//编辑帐号信息
			}else{
				sellerAccountDao.add(sellerAccount);//添加帐号信息
			}
//			sellerDao.deleteRelationShop(seller.getSellerid().longValue());//清空连锁店对应的所有子店信息
		} catch (Exception e) {
			this.log.error("连锁店数据更新异常：", e);
			throw new ApplicationException("连锁店数据更新",e,new Object[]{seller});
		}
	}
	
	public void sellerLogoAdd(TSeller seller){//添加商家logo
		TSellerPic sellerLogo = new TSellerPic();
		try {//商家logo不存在就新增
			sellerLogo = new TSellerPic();
			sellerLogo.setPicurl(seller.getUrl());
			sellerLogo.setSellerid(seller.getSellerid());
			sellerLogo.setIslogo(1);
			sellerPicService.add(sellerLogo);
		} catch (Exception e) {
			this.log.error("添加商家logo异常：", e);
			throw new ApplicationException("添加商家logo",e,new Object[]{seller});
		}
	}
	
	public void sellerLogoUpate(TSeller seller){//更新商家logo
		TSellerPic sellerLogo = new TSellerPic();
		try {//商家logo更新
			sellerLogo.setSellerid(seller.getSellerid());
			sellerLogo.setPicurl(seller.getUrl());
			sellerPicService.update(sellerLogo);
		} catch (Exception e) {
			this.log.error("更新商家logo异常：", e);
			throw new ApplicationException("更新商家logo",e,new Object[]{seller});
		}
	}
	
	public void sellerDetailedAdd(TSeller seller){//添加商家参考地标
		TSellerDetailed sellerDetailed = null;
         try{
			sellerDetailed = new TSellerDetailed();
			sellerDetailed.setLandmark(seller.getLandmark());
			sellerDetailed.setSellerid(seller.getSellerid());
			sellerDetailedService.add(sellerDetailed);
		}catch (Exception e) {
		    this.log.error("添加商家参考地标异常：", e);
		    throw new ApplicationException("添加商家参考地标",e,new Object[]{seller});
		}
	}
	
	public void sellerDetailedUpdate(TSeller seller){//更新加商家参考地标
		TSellerDetailed sellerDetailed = new TSellerDetailed();
		try {
		    sellerDetailed.setSellerid(seller.getDetailedId());
			sellerDetailed.setLandmark(seller.getLandmark());
			sellerDetailedService.update(sellerDetailed);
		} catch (Exception e) {
		    this.log.error("更新商家参考地标异常：", e);
		    throw new ApplicationException("更新商家参考地标",e,new Object[]{seller});
		}
	}
	
	public void subShop(TSeller seller){//关联子店
		try {
			seller.setFatherid(seller.getSellerid());
			seller.setLssellername(seller.getSellername());
			sellerService.batchRelationShop(seller);
		} catch (Exception e) {
			this.log.error("关联子店异常：", e);
			throw new ApplicationException("关联子店",e,new Object[]{seller});
		}
	}
	/**
	 * 新增连锁店钱包
	 * @param seller
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void addMoney(TSeller seller){
		//1添加钱包成功
		int flag = -1;
		//新增钱包
		SynthesizeService.Client client = null;
		Map<String,String> paramMap=new HashMap<String,String>();	
		Map<String,String> resultMap=null;
		try {
			client = (SynthesizeService.Client)(synthesizeServiceClient.getClient());
			this.putParamsMap(paramMap, seller);
			this.log.info("连锁店添加钱包_start phoneid=" + seller.getPhoneid() + " password="
					+ paramMap.get("password") + " Sellername=" + seller.getSellername());
			//flag = client.addWallet(seller.getSellerid().toString(), "2", paramMap.get("password"),seller.getSellername());
			resultMap=client.addWalletMap(paramMap);
			this.log.info("连锁店添加钱包成功！");
		} catch (Exception e) {
			throw new ApplicationException("连锁店添加钱包失败",e);
		} finally{
			String[] s={"连锁店编号",seller.getSellerid().toString(),"新增钱包","新增"};
			if(resultMap!=null && "0".equals(resultMap.get("state"))){//0添加成功 1添加失败2 参数异常
				flag=0;
			}
			fireLoginEvent(s,flag>0?1:0);
			if(null != client){
				synthesizeServiceClient.returnCon();
			}
		}
	}
	   public void putParamsMap(Map<String,String> paramMap,TSeller seller){
		  /* String phoneid = seller.getPhoneid();
			String password = "";
			if(StringUtils.hasLength(phoneid)){
				 password = phoneid.substring(phoneid.length() - 6, phoneid.length());
			}*/
		   paramMap.put("uId", String.valueOf(seller.getSellerid()));
		   paramMap.put("userType","2");
		   paramMap.put("password", "");//密码默认为空
		   paramMap.put("phoneNumber", String.valueOf(seller.getAccount()));
		   paramMap.put("sellerName", String.valueOf(seller.getSellername())); 
	   }
	
	/**
	 * 初始化连锁店信息
	 */
	public void initSellerInfo(Integer sellerid, ModelAndView model) {
		if(null != sellerid){
			TSellerAccount sellerAccount = new TSellerAccount();
			// 账号信息
			sellerAccount.setSellerid(sellerid);
			sellerAccount.setType(SellerConstants.SELLER_ACCOUNT_TYPE);//4：连锁店帐号
			List<TSellerAccount> accountList = sellerAccountDao.getList(sellerAccount);
			if(accountList.isEmpty()||accountList.size()>1){
				throw new RuntimeException();
			}
			if(!accountList.isEmpty()){
				sellerAccount = accountList.get(0);
			}
			// 商家基本信息
			TSeller selleridList = getObject(sellerid.longValue());
			TSellerPic logo = sellerPicService.getSellerLogo(sellerid.longValue());
			if(logo!=null){
				selleridList.setUrl(logo.getPicurl());
				selleridList.setPicid(logo.getPicid());
			}
			TSellerDetailed sellerDetailed = sellerDetailedService.getObject(sellerid.longValue());
			if(sellerDetailed!=null){
				selleridList.setLandmark(sellerDetailed.getLandmark());
				selleridList.setDetailedId(sellerDetailed.getSellerid());
			}
			//获取商家主账号
			Map<String, Object> mainAccount = sellerAccountDao.getMainAccount(sellerid+"");
			selleridList.setMainAccount(mainAccount.get("account")+"");
			
			model.addObject("sellerAccount", sellerAccount);
			model.addObject("selleridList", selleridList);
		}
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
				this.log.info("连锁店绑定银行卡返回信息: " + resultMap.get("msg"));
			}catch(Exception e){
				this.log.info("连锁店绑定银行卡失败！");
				throw new ApplicationException("连锁店绑定银行卡异常", e, new Object[]{card}, new String[]{"连锁店",  card.getSellername(), "添加银行卡", "添加"});
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
	    param.put("usertype", "3");
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
			paraMap.put("userType", String.valueOf(2));//用户类型  1用户  2商家 3合作商
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
				this.log.info("连锁店修改银行卡返回信息: " + resultMap.get("msg"));
			} catch(Exception e){
				this.log.info("连锁店修改银行卡失败！");
				throw new ApplicationException("连锁店修改银行卡异常", e, new Object[]{card}, new String[]{"连锁店",  card.getSellername(), "修改银行卡", "修改"});
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
