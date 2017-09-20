package com.xmniao.xmn.core.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.member.entity.MemberCard;
import com.xmniao.xmn.core.member.util.ResultUtil;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.synthesizeService.MentionAccount;
import com.xmniao.xmn.core.thrift.service.synthesizeService.MentionAccountService;
import com.xmniao.xmn.core.util.StringUtils;

@Service
public class MemberBankCardService extends BaseService<Void>{
	
	private static final Logger log = Logger.getLogger(MemberBankCardService.class);
	
	/**
	 * 银行卡接口服务
	 */
	@Resource(name = "mentionAccountServiceClient")
	private ThriftClientProxy mentionAccountServiceClient;
	
	@Override
	protected BaseDao getBaseDao() {
		return null;
	}
	
	/**
	 * 银行卡查询
	 * @param card
	 * @return
	 */
	public Pageable<MemberCard> getCardList(MemberCard card) {
		Pageable<MemberCard> sellerInfoList = new Pageable<MemberCard>(card);
		List<MemberCard>  cardList=null;
		try {
			MentionAccountService.Client client = (MentionAccountService.Client)(mentionAccountServiceClient.getClient());			
			MentionAccount account = client.getMentionAccountList(card.getWhere());
			cardList = ResultUtil.setResponseValue(account.getAccountList(),MemberCard.class);
			sellerInfoList.setContent(cardList);
			sellerInfoList.setTotal(((long)account.count));
		} catch (Exception e) {
			sellerInfoList.setTotal(0l);
			log.error("会员银行卡查询异常", e);
		} finally{
			mentionAccountServiceClient.returnCon();
		}
		return sellerInfoList;
	}

	/**
	 * 银行卡解绑
	 * @param card 请求参数
	 * @return
	 */
	public Resultable unbundlingBankCard(MemberCard card){
		Map<String, String> map = new HashMap<String, String>(5);
		Resultable resultable = new Resultable();
		String[] logmsg= null;
		try {
			if(validateParameter(card,map)){
				
				MentionAccountService.Client client = (MentionAccountService.Client)(mentionAccountServiceClient.getClient());	
				setSuccessMsg(resultable,map,client);
				logmsg=logMsg(resultable,card);
				fireLoginEvent(logmsg,resultable.getSuccess()?1:0);
			}else{
				loginfo(map,logmsg,resultable);
			}
		} catch (Exception e) {
			this.log.error("银行卡解绑：", e);
			throw new ApplicationException("银行卡解绑",e,new Object[]{card},new String[]{"会员编号",card.getUId(),"银行卡解绑","银行卡解绑"});
		} finally{
			mentionAccountServiceClient.returnCon();
		}
		return resultable;
	}
	public String[] logMsg(Resultable resultable,MemberCard card){
		String logMsg = "银行卡解绑"+(resultable.getSuccess()?"成功":resultable.getMsg());
		log.info(logMsg);
		return new String[]{ "会员编号",card.getUId(),logMsg, "解绑" };
	}
	public void loginfo(Map<String, String> map,String[] logmsg,Resultable resultable){
		String m = map.toString();
		logmsg = new String[]{ "解绑银行卡参数",m,"参数不正确", "解绑" };
		log.info("银行卡解绑参数不正确!");
		resultable.setSuccess(false);
		resultable.setMsg("参数不正确!");
		
	}
	
	/**
	 * 银行卡解绑参数验证
	 * @param card 前台请求参数
	 * @param map 服务调用参数
	 * @return
	 */
	private boolean validateParameter(MemberCard card,Map<String, String> map){
		boolean validate=true;
		if(!StringUtils.hasLength(card.getId())){
			validate=false;
			return validate;
		}
		if(!StringUtils.hasLength(card.getUId())){
			validate=false;
			return validate;
		}
		if(!StringUtils.hasLength(card.getType())){
			validate=false;
			return validate;
		}
		if(!StringUtils.hasLength(card.getAccount())){
			validate=false;
			return validate;
		}
		if(validate){
			map.put("id", card.getId());
			map.put("uId", card.getUId());
			map.put("type", card.getType());
			map.put("account", card.getAccount());
			map.put("userType", "1");
		}
		return validate;
	}
	
	/**
	 * 解绑银行卡调用成功参数设置
	 * @param resultable 返回前台参数
	 * @param map 服务返回参数
	 */

private void setSuccessMsg(Resultable resultable,Map<String, String> inMap,MentionAccountService.Client client){
		try {
			Map<String, String> map=client.unbundlingAccount(inMap);
			String msg = map.get("msg");
			switch (map.get("state")) {
			case "0":
				resultable.setSuccess(true);
				resultable.setMsg("解绑成功!");
				break;
			case "1":
				resultable.setSuccess(false);
				resultable.setMsg("解绑失败!"+msg);
				break;
			case "2":
				resultable.setSuccess(false);
				resultable.setMsg("参数异常!"+msg);
				break;
			case "3":
				resultable.setSuccess(false);
				resultable.setMsg("程序异常!"+msg);
				break;
			}
		}  catch (Exception e) {
			this.log.error("解绑银行卡调用：", e);
			throw new ApplicationException("解绑银行卡调用",e,new Object[]{resultable,inMap,client});
		}
	}

	
	
}
