/**
 * 
 */
package com.xmniao.xmn.core.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.member.entity.MemberWallet;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.FailureException;
import com.xmniao.xmn.core.thrift.service.ResponseData;
import com.xmniao.xmn.core.thrift.service.ResponsePageList;
import com.xmniao.xmn.core.thrift.service.liveService.LiveWalletService;
import com.xmniao.xmn.core.thrift.service.xmniaoWalletService.XmniaoWalletService;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MemberWalletService
 * 
 * 类描述： 会员钱包Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-8-17 上午11:55:31 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class MemberWalletService{
	
	private final Logger log = Logger.getLogger(getClass());
	
	/**
	 * 注入用户服务
	 */
	@Autowired
	private BursService bursService;
	
	/**
	 * 注入用户钱包服务
	 */
	@Resource(name = "xmniaoWalletServiceClient")
	private ThriftClientProxy xmniaoWalletServiceClient;
	
	/**
	 * 注入直播钱包服务
	 */
	@Resource(name = "liveWalletServiceServiceClient")
	private ThriftClientProxy liveWalletServiceClient;

	/**
	 * 
	 * 方法描述：获取用户信息列表(包含用户钱包信息) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-17下午2:18:45 <br/>
	 * @param burs
	 * @return
	 */
	public List<MemberWallet> getMemberWalletList(MemberWallet memberWalletReq){
		List<MemberWallet> memberWalletList=new ArrayList<MemberWallet>();
		Burs burs=new Burs();
		burs.setUid(memberWalletReq.getUid());
		burs.setNname(memberWalletReq.getNname());
		burs.setPhone(memberWalletReq.getPhone());
		burs.setPage(memberWalletReq.getPage());
		burs.setLimit(memberWalletReq.getLimit());
		List<Burs> ursList = bursService.getUrsList(burs);
		for(Burs urs:ursList){
			MemberWallet memberWallet=new MemberWallet();
			memberWallet.setUid(urs.getUid());
			memberWallet.setNname(urs.getNname());
			memberWallet.setPhone(urs.getPhone());
			memberWalletList.add(memberWallet);
		}
		try {
			loadMemberWalletInfo(memberWalletList);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("加载会员钱包数据异常:"+e.getMessage());
		}	
		
		try {
			loadLiveWalletInfo(memberWalletList);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("加载直播包数据异常:"+e.getMessage());
		}
		return memberWalletList;
	}
	
	/**
	 * 方法描述：加载直播钱包信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-18上午11:01:43 <br/>
	 * @param memberWalletList
	 */
	private void loadLiveWalletInfo(List<MemberWallet> memberWalletList) {
		if(memberWalletList!=null && memberWalletList.size()>0){
			String uids =null;
			StringBuffer sb=new StringBuffer();
			for(MemberWallet wallet:memberWalletList){
				Integer uid = wallet.getUid();
				if(uid!=null){
					sb.append(uid).append(",");
				}
			}
			String uidStr=sb.toString();
			if(uidStr.contains(",")){
				uids = uidStr.substring(0, uidStr.lastIndexOf(","));
			}
			Map<String,String> reqMap= new HashMap<String,String>();
			reqMap.put("uids", uids);
			LiveWalletService.Client client = (LiveWalletService.Client) liveWalletServiceClient.getClient();
			
			try {
				ResponsePageList responseData = client.getLiveWalletList(reqMap);
				ResponseData dataInfo = responseData.getDataInfo();
				int state = dataInfo.getState();
				if(state==0){
					List<Map<String, String>> walletList = responseData.getPageList();
					if(walletList!=null && walletList.size()>0){
						for(MemberWallet memberInfo:memberWalletList){
							Integer uid = memberInfo.getUid();
							for(Map<String,String> map:walletList){
								String walletUid = map.get("uid");
								boolean same= uid!=null && walletUid!=null && uid.toString().equals(walletUid);
								if(same){
									String liveBalance = map.get("balance");//鸟蛋余额
									String liveCommision = map.get("commision");//鸟豆余额
									String liveZbalance = map.get("zbalance");//鸟币余额
									String liveTurnEggOut = map.get("turnEggOut");//鸟蛋转出
									String liveZbalanceLock = map.get("zbalanceLock");//是否锁定鸟币
									
									memberInfo.setLiveBalance(liveBalance);
									memberInfo.setLiveCommision(liveCommision);
									memberInfo.setLiveZbalance(liveZbalance);
									memberInfo.setLiveTurnEggOut(liveTurnEggOut);
									memberInfo.setLiveZbalanceLock(liveZbalanceLock);
								}
							}
						}
					}
				}
				
			} catch (FailureException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}finally{
				liveWalletServiceClient.returnCon();
			}
			
			
		}
	}

	/**
	 * 加载会员钱包信息
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-17下午2:49:45 <br/>
	 * @param memberWalletList
	 */
	public void loadMemberWalletInfo(List<MemberWallet> memberWalletList){
		
		if(memberWalletList!=null && memberWalletList.size()>0){
			List<Map<String, String>> listMap = new ArrayList<Map<String,String>>();
			for(MemberWallet wallet:memberWalletList){
				Map<String,String> map=new HashMap<String,String>(); 
				Integer uid = wallet.getUid();
				if(uid!=null){
					map.put("uid", uid.toString());
					map.put("typeId", "1");//类型Id:1用户、2商家、3合作商、4区域代理
					listMap.add(map);
				}
			}
			XmniaoWalletService.Client client = (XmniaoWalletService.Client) xmniaoWalletServiceClient.getClient();
			
			try {
				ResponsePageList responseData = client.getWalletBalanceList(listMap);
				ResponseData dataInfo = responseData.getDataInfo();
				int state = dataInfo.getState();
				if(state==0){
					List<Map<String, String>> walletList = responseData.getPageList();
					if(walletList!=null && walletList.size()>0){
						for(MemberWallet memberInfo:memberWalletList){
							Integer uid = memberInfo.getUid();
							for(Map<String,String> map:walletList){
								String walletUid = map.get("uid");
								boolean same= uid!=null && walletUid!=null && uid.toString().equals(walletUid);
								if(same){
									String amount = map.get("amount");//钱包余额
									String balance = map.get("balance");//分账余额
									String commision = map.get("commision");//佣金余额
									String zbalance = map.get("zbalance");//赠送余额
									String integral = map.get("integral");//积分余额
									String zbalanceLock = map.get("zbalanceLock");//锁定佣金余额
									
									memberInfo.setAmount(amount);
									memberInfo.setBalance(balance);
									memberInfo.setCommision(commision);
									memberInfo.setZbalance(zbalance);
									memberInfo.setIntegral(integral);
									memberInfo.setZbalanceLock(zbalanceLock);
								}
							}
						}
					}
				}
				
			} catch (FailureException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}finally{
				xmniaoWalletServiceClient.returnCon();
			}
			
			
		}
	}

	/**
	 * 方法描述：获取会员数量 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-17下午2:22:14 <br/>
	 * @param burs
	 * @return
	 */
	public long count(MemberWallet memberWallet) {
		Integer uid = memberWallet.getUid();
		Burs burs=new Burs();
		burs.setUid(uid);
		burs.setNname(memberWallet.getNname());
		burs.setPhone(memberWallet.getPhone());
		return bursService.count(burs);
	}

	/**
	 * 方法描述：获取会员钱包信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-17下午5:46:04 <br/>
	 * @param memberWalletReq 
	 * @return
	 */
	public MemberWallet getMemberWalletInfo(MemberWallet memberWalletReq) {
		MemberWallet memberWallet=new MemberWallet();
		List<MemberWallet> memberWalletList = getMemberWalletList(memberWalletReq);
		if(memberWalletList!=null && memberWalletList.size()>0){
			memberWallet = memberWalletList.get(0);
		}
		return memberWallet;
	}

	/**
	 * 方法描述：修改用户钱包  <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-17下午6:04:04 <br/>
	 * @param memberWallet
	 */
	public void updateWallet(MemberWallet memberWallet) {
		Integer uid = memberWallet.getUid();
		if(uid!=null){
			Map<String,String> param= new HashMap<String,String>();
			param.put("uid", uid.toString());
			param.put("typeId", "1");//类型Id:1用户、2商家、3合作商、4区域代理
			param.put("type", "1");//锁定金额类型:1锁定佣金和余额
			param.put("updateOption", memberWallet.getUpdateOption());
			XmniaoWalletService.Client client = (XmniaoWalletService.Client) xmniaoWalletServiceClient.getClient();
			try {
				client.updateWalletLock(param);
			} catch (FailureException e) {
				e.printStackTrace();
				this.log.error("更新钱包异常：param="+param);
			} catch (TException e) {
				e.printStackTrace();
				this.log.error("更新钱包异常：param="+param);
			}finally{
				xmniaoWalletServiceClient.returnCon();
			}
		}
	}

	/**
	 * 方法描述：修改直播钱包 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-18下午2:07:56 <br/>
	 * @param memberWallet
	 */
	public void updateLiveWallet(MemberWallet memberWallet) {
		Integer uid = memberWallet.getUid();
		if(uid!=null){
			Map<String,String> param= new HashMap<String,String>();
			param.put("uid", uid.toString());
			param.put("type", "1");//类型:1鸟币
			param.put("updateOption", memberWallet.getUpdateOption());
			LiveWalletService.Client client = (LiveWalletService.Client) liveWalletServiceClient.getClient();
			try {
				client.updateLiveWalletLock(param);
			} catch (FailureException e) {
				e.printStackTrace();
				this.log.error("更新直播钱包异常：param="+param);
			} catch (TException e) {
				e.printStackTrace();
				this.log.error("更新直播钱包异常：param="+param);
			}finally{
				liveWalletServiceClient.returnCon();
			}
		}
	}


}
