package com.xmniao.xmn.core.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.member.entity.MemberProvodedRequest;
import com.xmniao.xmn.core.member.entity.MemberProvodedResponse;
import com.xmniao.xmn.core.member.entity.ResponseContainer;
import com.xmniao.xmn.core.member.entity.WithdrawalsDetails;
import com.xmniao.xmn.core.member.entity.WithdrawalsProcess;
import com.xmniao.xmn.core.member.util.ResultUtil;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.Page;
import com.xmniao.xmn.core.thrift.service.Result;
import com.xmniao.xmn.core.thrift.service.commonService.CommonAccountService;
import com.xmniao.xmn.core.thrift.service.jointService.JointeAccountService;
import com.xmniao.xmn.core.util.JsonUtil;

@Service
public class MemberProvidedService {
	
	private Logger log = Logger.getLogger(getClass());
	

	@Resource(name="commonServiceClient")
	private ThriftClientProxy commonAccountService;
	@Resource(name="jointServiceClient")
	private ThriftClientProxy jointAccountService;
	
	/**
	 * 设置请求分页信息
	 * @param request
	 * @return
	 */
	private Page setPage(MemberProvodedRequest request){
		int page=Integer.parseInt(request.getPage());
		int pagesize = Integer.parseInt(request.getPageSize());
		Page p =new Page();
		p.setPage(page);
		p.setPageSize(pagesize);
		request.setPage(null);
		request.setPageSize(null);
		return p;
	} 
	
	/**
	 * 提现接口
	 */
	@SuppressWarnings("unchecked")
	public ResponseContainer get(MemberProvodedRequest request) {
		Page page = setPage(request);
		Map<String, String> param = (HashMap<String, String>)JSON.parseObject(JSONObject.toJSONString(request), Map.class);
		log.info("requestinfo---->:"+param);
		CommonAccountService.Client client = (CommonAccountService.Client)commonAccountService.getClient();
		Result resulet = null;
		try {
			resulet = client.withdrawPageList(page, param);//提现查询
		} catch (TException e) {
			log.error("查询提现数据失败", e);
		} finally{
			commonAccountService.returnCon();
		}
		return ResultUtil.setContainer(resulet,MemberProvodedResponse.class);
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月27日 下午4:08:13
	 * @TODO 提现状态查询
	 * @param id
	 */
	public void jointWithdrawals(Long id,ModelAndView modelAndView){
		CommonAccountService.Client client = (CommonAccountService.Client)commonAccountService.getClient();
		WithdrawalsProcess withdrawalsProcess=new WithdrawalsProcess();
		try {
			Map<String,String> map=client.withdrawFlow(id);
			this.putJointWithdrawalsParams(withdrawalsProcess, map);
		} catch (TException e) {
			log.error("查询提现数据失败", e);
		}finally{
			commonAccountService.returnCon();
			
		}
		modelAndView.addObject("withdrawalsProcess", withdrawalsProcess);
	}
	public void putJointWithdrawalsParams(WithdrawalsProcess withdrawalsProcess,Map<String,String> map){
		withdrawalsProcess.setState(map.get("state"));
		withdrawalsProcess.setSubmitdate(map.get("submitdate"));
		withdrawalsProcess.setDisposedate(map.get("disposedate"));
		withdrawalsProcess.setReceivedate(map.get("receivedate"));
		withdrawalsProcess.setFaildate(map.get("faildate"));
		withdrawalsProcess.setFailmsg(map.get("failmsg"));
		withdrawalsProcess.setBackdate(map.get("backdate"));
		withdrawalsProcess.setBackmsg(map.get("backmsg"));
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月27日 下午4:08:37
	 * @TODO 合作商提现详细信息
	 * @param id
	 */
	public void jointDetails(Long id,ModelAndView modelAndView){
		JointeAccountService.Client client = (JointeAccountService.Client)jointAccountService.getClient();
		WithdrawalsDetails withdrawalsDetails=new WithdrawalsDetails();
		try {
			Map<String,String> map=client.jointeWithdrawInfo(id);
			this.putjointDetailsParams(withdrawalsDetails,map);
		}catch (TException e) {
			log.error("查询提现数据失败", e);
		}finally{
			jointAccountService.returnCon();
		}
		modelAndView.addObject("withdrawalsDetails", withdrawalsDetails);
	}
	public void putjointDetailsParams(WithdrawalsDetails withdrawalsDetails,Map<String,String> map){
		withdrawalsDetails.setFlowid(map.get("flowid"));
		withdrawalsDetails.setDate(map.get("date"));
		withdrawalsDetails.setUserid(map.get("userid"));
		withdrawalsDetails.setUsername(map.get("username"));
		withdrawalsDetails.setBalance(map.get("balance"));
		withdrawalsDetails.setMoney(map.get("money"));
		withdrawalsDetails.setUsertype(map.get("usertype"));		
		withdrawalsDetails.setState(map.get("state"));
		withdrawalsDetails.setSource(map.get("source"));
		withdrawalsDetails.setType(map.get("type"));
		withdrawalsDetails.setAccount(map.get("account"));
		withdrawalsDetails.setAccountname(map.get("accountname"));
		withdrawalsDetails.setInvoice(map.get("invoice"));
		withdrawalsDetails.setExpress(map.get("express"));
		withdrawalsDetails.setExpressid(map.get("expressid"));
		withdrawalsDetails.setOpinion(map.get("opinion"));
	}
}
