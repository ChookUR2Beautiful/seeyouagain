/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.dao.TLivePayOrderDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLivePayOrder;
import com.xmniao.xmn.core.live_anchor.entity.TLivePurse;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BLiveMemberService
 * 
 * 类描述： 直播会员Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-7 下午3:36:59 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class BLiveMemberService extends BaseService<BLiver> {
	
	/**
	 * 注入主播(用户)服务
	 */
	@Autowired
	private BLiverDao liverDao;
	
	/**
	 * 注入直播推荐人服务
	 */
	@Autowired
	private  TLiveReferrerService referrerService;
	
	/**
	 * 注入直播鸟币充值订单服务
	 */
	@Autowired
	private TLivePayOrderDao livePayOrderDao;
	
	/**
	 * 注入直播钱包服务
	 */
	@Resource(name = "liveWalletServiceServiceClient")
	private ThriftClientProxy liveWalletServiceServiceClient;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liverDao;
	}
	
	/**
	 * 
	 * 方法描述：根据uidStr获取等下级会员信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-7下午4:47:53 <br/>
	 * @param liver
	 * @return
	 */
	public List<BLiver> getJuniorList(BLiver liver){
		return liverDao.getJuniorList(liver);
	}

	/**
	 * 方法描述：设置上级信息及间接收益人信息<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-7下午3:42:11 <br/>
	 * @param liver
	 * @param model
	 */
	public void setSuperiorInfo(BLiver liver, Model model) {
		BLiver liverInfo = liverDao.selectByPrimaryKey(liver.getId());
		String superiorUid = liverInfo.getSuperiorUid();
		if(StringUtils.isNotBlank(superiorUid)){
			String superiorUidRel = superiorUid.replaceAll("^(0+)", "");
			BLiver superiorReq=new BLiver();
			superiorReq.setUid(Integer.valueOf(superiorUidRel));
			BLiver superiorInfo = liverDao.selectBLiver(superiorReq);
			String phone = superiorInfo.getPhone();
			String nickname = superiorInfo.getNickname();
			StringBuffer superiorSb = new StringBuffer();
			superiorSb.append(phone);
			if(StringUtils.isNotBlank(nickname)){
				superiorSb.append("[").append(nickname).append("]");
			}
			String superior = superiorSb.toString();
			model.addAttribute("superiorInfo", superiorInfo);//上级
			model.addAttribute("superior", superior);//上级手机号码和昵称
		}
		//V客间接上级UID
		Integer indirectUid = liverInfo.getIndirectUid();
		if(indirectUid!=null){
			BLiver superiorReq=new BLiver();
			superiorReq.setUid(indirectUid);
			BLiver indirectInfo = liverDao.selectBLiver(superiorReq);
			model.addAttribute("indirectInfo", indirectInfo);//间接上级
		}else if(StringUtils.isNotBlank(superiorUid)){
			String superiorUidRel = superiorUid.replaceAll("^(0+)", "");
			BLiver superiorReq=new BLiver();
			superiorReq.setUid(Integer.valueOf(superiorUidRel));
			BLiver indirectInfo = liverDao.selectBLiver(superiorReq);
			model.addAttribute("indirectInfo", indirectInfo);//间接上级
		}
		
		
		//下线人数
		long juniors = countJuniorsByUid(liverInfo.getUid());
		liverInfo.setJuniors(juniors);
		model.addAttribute("liverInfo", liverInfo);
	}
	
	
	/**
	 * 
	 * 方法描述：根据uid统计下线人数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-11下午8:29:40 <br/>
	 * @param uid
	 * @return
	 */
	public long countJuniorsByUid(Integer uid){
		long juniors=0l;
		BLiver liver = liverDao.countJuniorsByUid(uid);
		if(liver!=null){
			juniors = liver.getJuniors();
		}
		return juniors;
	}
	
	
	/**
	 * 方法描述：绑定上级信息 <br/>
	 * 0、查询等下级直播会员<br/>
	 * 1、更新关系链<br/>
	 * 2、更新关系链名称<br/>
	 * 3、更新会员在关系链中的等级(遍历0查询的所有等下级用户)<br/>
	 * 4、更新企业级uid(如果为null)<br/>
	 * 5、更新企业级nname<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-7下午4:09:16 <br/>
	 * @param liver
	 * @return
	 */
	public Resultable bindSuperiorInfo(BLiver liver) {
		Resultable result=new Resultable();
		
		try {
			Integer uid = liver.getUid();//当前用户的会员uid
			String uidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(uid);
			BLiver liverReq=new BLiver();
			liverReq.setUidStr(uidStr);
			//等下级会员信息
			List<BLiver> juniorList = liverDao.getJuniorList(liverReq);
			
			liverReq.setUid(uid);
			BLiver currentLiver = liverDao.selectBLiver(liverReq);//当前会员信息
			if(currentLiver==null){
				result.setSuccess(false);
				result.setMsg("获取直播会员信息失败!");
				return result;
			}
			
			if(StringUtils.isBlank(currentLiver.getUidRelationChain())){
				this.log.error("直播会员 UID:"+uid+",关系链为null,不能绑定上级!");
			}
			
			String superiorIdChosen = liver.getSuperiorIdChosen();
			if(StringUtils.isNotBlank(superiorIdChosen)){
				Integer superiorId=new Integer(superiorIdChosen);
				//待绑定的上级信息
				BLiver superiorInfo = liverDao.selectByPrimaryKey(superiorId);
				String superiorUidRelationChain = superiorInfo.getUidRelationChain();
				String superiorUidRelationChainNname = superiorInfo.getUidRelationChainNname();
				Integer superiorUidRelationChainLevel = superiorInfo.getUidRelationChainLevel();
				if(superiorUidRelationChainLevel.compareTo(new Integer(1))==0){
					superiorUidRelationChainNname=superiorUidRelationChainNname==null?"":superiorUidRelationChainNname;
				}
				
				Integer currentLevel = currentLiver.getUidRelationChainLevel();
				for(BLiver liverInfo:juniorList){
					String uidRelationChain = liverInfo.getUidRelationChain();
					int indexOf = uidRelationChain.indexOf(uidStr);
					String latterUidRelationChain = uidRelationChain.substring(indexOf);
					String uidRelationChainNname = liverInfo.getUidRelationChainNname();
//					String latterUidRelationChainNname="";
					if(currentLevel.compareTo(new Integer(1))==0){
						uidRelationChainNname=uidRelationChainNname==null?"":uidRelationChainNname;
//						latterUidRelationChainNname=","+uidRelationChainNname;
					}else{
//						int characterPosition = com.xmniao.xmn.core.util.StringUtils.getCharacterPosition(uidRelationChainNname, currentLevel-1, ",");
//						latterUidRelationChainNname=uidRelationChainNname.substring(characterPosition);
					}
					
					StringBuffer uidRelationChainSb=new StringBuffer();
//					StringBuffer uidRelationChainNnameSb=new StringBuffer();
					String cycleUidRelationChain = uidRelationChainSb.append(superiorUidRelationChain).append(",").append(latterUidRelationChain).toString();
//					String cycleUidRelationChainNname = uidRelationChainNnameSb.append(superiorUidRelationChainNname).append(latterUidRelationChainNname).toString();
					
					Integer cycleUid = liverInfo.getUid();
					String  cycleUidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(cycleUid);
					int cycleUidRelationChainLevel = com.xmniao.xmn.core.util.StringUtils.getArrayIndexFromStr(cycleUidRelationChain, cycleUidStr, ",");
					
					liverInfo.setUidRelationChain(cycleUidRelationChain);
					//2017-02-21去掉uidRelationChainNname维护
//					liverInfo.setUidRelationChainNname(cycleUidRelationChainNname);
					liverInfo.setUidRelationChainLevel(cycleUidRelationChainLevel);
					
					//更新层级关系
					liverInfo.setUpdateTime(new Date());
					updateRelationChainInfo(liverInfo);
					Integer enterpriseUid = liverInfo.getEnterpriseUid();
					
					
					//更新企业级推荐人信息
					if(enterpriseUid==null){
						BLiver liverBean=new BLiver();
						liverBean.setId(liverInfo.getId());
						liverBean.setUid(liverInfo.getUid());
						liverBean.setUidRelationChain(liverInfo.getUidRelationChain());
						setEnterpriseInfo(liverBean);
						updateRelationChainInfo(liverBean);
					}
					
					//上级发生变更，更新间接上级(全部置空) TODO
					String superiorUidOld = currentLiver.getSuperiorUid()==null?"":currentLiver.getSuperiorUid();//数据库保存上级
					String superiorUidNew = superiorInfo.getUid()==null?"":superiorInfo.getUid().toString();//当前选择上级
					if(!superiorUidOld.equals(superiorUidNew)){
						BLiver liverBean=new BLiver();
						liverBean.setId(liverInfo.getId());
						liverDao.updateIndirect2Null(liverBean);
					}
				}
				
				
				result.setSuccess(true);
				result.setMsg("操作成功!");
			}else{
				result.setSuccess(false);
				result.setMsg("请选择上级");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
		}
		return result;
	}
	
	/**
	 * 方法描述：设置企业级推荐人相关信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-12上午9:25:03 <br/>
	 * @param liver
	 */
	public void setEnterpriseInfo(BLiver liver) {
		String uidRelationChain = liver.getUidRelationChain();
		Integer uidInfo = liver.getUid();
		String UidStrInfo = com.xmniao.xmn.core.util.StringUtils.generateUidStr(uidInfo);
		uidRelationChain=uidRelationChain.replace(UidStrInfo, "");//去除本身uidStr
		String[] uidRelationChainArray = uidRelationChain.split(",");
		List<String> uids=new ArrayList<String>();
		for(String uidStr:uidRelationChainArray){
			String uid=uidStr.replace("^(0+)", "");
			uids.add(uid);
		}
		
		if(uids!=null&&uids.size()>0){
			BLiver enterpriseInfo = liverDao.selectEnterpriseByUids(uids.toArray());
			if(enterpriseInfo!=null){
				liver.setEnterpriseUid(enterpriseInfo.getUid());
				liver.setEnterpriseNname(enterpriseInfo.getNickname());
			}
		}
	}
	
	/**
	 * 
	 * 方法描述：更新会员关系链等信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-8下午2:01:52 <br/>
	 * @param liver
	 * @return
	 */
	public int updateRelationChainInfo(BLiver liver){
		return liverDao.updateRelationChainInfo(liver);
	}
	
	
	/**
	 * 
	 * 方法描述：获取下线累计充值总额 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-10下午5:23:08 <br/>
	 * @param uid
	 * @return
	 */
	public BigDecimal getJuniorAmountByUid(String uid){
		BigDecimal amount=new BigDecimal(0);
		List<String> juniorUidList = referrerService.getJuniorUidList(uid);
		
		if(juniorUidList!=null && juniorUidList.size()>0){
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("uids", juniorUidList);
			Map<String, Object> amountCountMap = referrerService.juniorAmountCountByUids(map);
			Object amountTotal = amountCountMap.get("amountTotal");
			if(amountTotal!=null){
				amount=new BigDecimal(amountTotal.toString());
			}
		}
		
		return amount;
	}
	
	public void getPurseListPage(TLivePurse livePurse, Pageable<TLivePurse> pageable) {
		List<TLivePurse> livePurseList = this.searchPurseDataList(livePurse);
		
		pageable.setContent(livePurseList);
		long count = livePurseList.size();
		
		pageable.setTotal(count);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TLivePurse> searchPurseDataList(TLivePurse bean) {
		List<TLivePurse> livePurseList = new ArrayList<>();

		try {
			if (bean != null && bean.getUid() != null){
				//连接接口进行查询
				com.xmniao.xmn.core.thrift.service.liveService.LiveWalletService.Client client = (com.xmniao.xmn.core.thrift.service.liveService.LiveWalletService.Client) (liveWalletServiceServiceClient.getClient());	
			
				Map<String, String> paraMap = new HashMap<>();	
				int uid = bean.getUid();
				log.info("查询直播钱包记录开始，uid：" + uid);
				paraMap.put("uid", String.valueOf(uid));
				
				if (bean.getType() != null) {
					int type = bean.getType();
					paraMap.put("type", String.valueOf(type)); // 类型(虚拟货币)1 鸟豆  2鸟币
				} 
				if (bean.getOption() != null) {
					int option = bean.getOption();
					paraMap.put("option", String.valueOf(option)); // 类型 1增加, 2减扣减
				}
				if (bean.getRtype() != null) {
					int rtype = bean.getRtype();
					paraMap.put("rtype", String.valueOf(rtype)); // 类型(渠道)平台充值
				}

				// paraMap.put("cPage", arg1); //页码
				// paraMap.put("pageSize", arg1); /页大小
				List<Map<String, String>> livePurseDataList = client
						.getUserRecord(paraMap);
				if (livePurseDataList.size() > 0) {
					for (Map object : livePurseDataList) {
						livePurseList.add(getlivePurseFromMap(object, uid));
					}
				}
				log.info("查询直播钱包结束，返回值：" + livePurseDataList.size());
			}

		} catch (Exception e) {
			log.error("查询直播钱包失败", e);
//			throw new ApplicationException("查询直播钱包异常", e, new Object[] { uid });
		} finally {
			liveWalletServiceServiceClient.returnCon();
		}
		
		return livePurseList;
	}
	
	@SuppressWarnings("static-access")
	public TLivePurse getlivePurseFromMap(Map<String, String> map, Integer uid) throws Exception{
		/*beans_money  q_beans_money h_beans_money*/
		//2 赠送礼物 打赏  鸟豆 － 鸟币 +
		TLivePurse livePurse = new TLivePurse();
		livePurse.setUid(uid);  //用户编号
		livePurse.setWalletId(Integer.parseInt(map.get("walletId")));
		
		BigDecimal tradeMoney ;
		//数字字符串   
		String beansMoney = map.get("beansMoney");  //鸟币
		String coinMoney = map.get("coinMoney");  //鸟豆
		//构造以字符串内容为值的BigDecimal类型的变量bd   
		tradeMoney = new BigDecimal(beansMoney);   
		//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)   
		tradeMoney = tradeMoney.setScale(0, BigDecimal.ROUND_HALF_UP);   
		int option = 0;  
		if (tradeMoney.compareTo(BigDecimal.ZERO) == 1) {// 结果是 -1 小于  0 等于 1 大于
			livePurse.setType(2);  // 1-鸟豆  2-鸟币	
			
			BigDecimal hbeansMoney = new BigDecimal(map.get("hbeansMoney"));
			hbeansMoney = hbeansMoney.setScale(2, BigDecimal.ROUND_HALF_UP);   
			BigDecimal qbeansMoney = new BigDecimal(map.get("qbeansMoney"));
			qbeansMoney = qbeansMoney.setScale(2, BigDecimal.ROUND_HALF_UP);  
			
			if (hbeansMoney.compareTo(qbeansMoney.ZERO) == 1) {// -1 小于  0 等于 1 大于
				option = 1;  //1 增加 2扣减
			}else{
				option = 2;
			}
		}else{
			tradeMoney = new BigDecimal(coinMoney);   
			tradeMoney = tradeMoney.setScale(0, BigDecimal.ROUND_HALF_UP);  
			livePurse.setType(1);  // 1-鸟豆  2-鸟币	
			
			BigDecimal hcoinMoney = new BigDecimal(map.get("hcoinMoney"));
			hcoinMoney = hcoinMoney.setScale(2, BigDecimal.ROUND_HALF_UP);   
			BigDecimal qcoinMoney = new BigDecimal(map.get("qcoinMoney"));
			qcoinMoney = qcoinMoney.setScale(2, BigDecimal.ROUND_HALF_UP);  
			
			if (hcoinMoney.compareTo(qcoinMoney.ZERO) == 1) {// -1 小于  0 等于 1 大于
				option = 1;  //1 增加 2扣减
			}else{
				option = 2;
			}
		}

		livePurse.setOption(option); // 1-增加 2-扣减
		livePurse.setRtype(Integer.parseInt(map.get("rtype")));  //渠道
		
		livePurse.setMoney((option == 1 ? "+" : "-")+tradeMoney.toString());
		String createTime = map.get("createTime");
		Date dateTime = DateUtil.formatStringToDate(createTime, DateUtil.Y_M_D_HMS);
		livePurse.setCreateTime(dateTime);
		
		return livePurse;
	}

	/**
	 * 方法描述：验证间接上级与上级的关系 <br/>
	 * 1、不在同一关系链（前端可选择直属上级作为间接收益人，但不保存间接上级信息）<br/>
	 * 2、具有相同起始来源<br/>
	 * 3、同一关系链上会员的间接上级不允许绑定同一人
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-27下午3:46:15 <br/>
	 * @param liver
	 * @return
	 */
	public Resultable indirectValidate(BLiver liver) {
		Resultable result=new Resultable();
		
		try {
			Integer uid = liver.getUid();//当前用户的会员UID
			String uidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(uid);
			BLiver liverReq=new BLiver();
			liverReq.setUidStr(uidStr);
			//等下级会员信息
			List<BLiver> juniorList = liverDao.getJuniorList(liverReq);
			
			
			liverReq.setUid(uid);
			BLiver currentLiver = liverDao.selectBLiver(liverReq);//当前直播会员信息
			if(currentLiver==null){
				result.setSuccess(false);
				result.setMsg("获取直播会员信息失败!");
				return result;
			}
			
			String superiorIdChosen = liver.getSuperiorIdChosen();//选择的上级
			Integer chooseIndirectId = liver.getIndirectId();//选择的间接上级
			if(StringUtils.isBlank(superiorIdChosen) || chooseIndirectId==null){
				result.setSuccess(false);
				result.setMsg("请选择上级和间接上级!");
				return result;
			}
			
			
			Integer superiorId=new Integer(superiorIdChosen);
			BLiver superiorInfo = liverDao.selectByPrimaryKey(superiorId);//待绑定的上级信息
			String superiorUidRelationChain = superiorInfo.getUidRelationChain();
			
			BLiver chooseIndirectIdInfo = liverDao.selectByPrimaryKey(chooseIndirectId);//待绑定的间接上级信息
			String choosedIndirectUidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(chooseIndirectIdInfo.getUid());//选择的间接收益人UID
			
			
			//一、查找所有等下级，再拼接上级关系链，组成当前关系链A
			StringBuffer relationChainA=new StringBuffer();
			relationChainA.append(superiorUidRelationChain);
			
			if(juniorList!=null && juniorList.size()>0){
				for(BLiver junior:juniorList){
					String juniorUidRelationChain = junior.getUidRelationChain();
					if(StringUtils.isNotBlank(juniorUidRelationChain)){
						relationChainA.append(",").append(juniorUidRelationChain);
					}
				}
			}
			
			String relationChainAStr = relationChainA.toString();
			
			//二、判断选择的间接收益人是否在关系链A中：
			//1、已有间接收益人，不可选择关系链A中会员
			//2、无间接受益人，可选择直接上级作为间接收益人
			Integer currentIndirectUid = currentLiver.getIndirectUid();//当前直播会员前间接上级
			if(currentIndirectUid!=null){
//				String indirectUidStr = com.xmniao.xmn.core.util.StringUtils.generateUidStr(currentIndirectUid);
				if(relationChainAStr.contains(choosedIndirectUidStr)){
					result.setSuccess(false);
					result.setMsg("已有间接上级，不可选择当前会员关系链中的会员!");
					return result;
				}
			}else{
				if(!chooseIndirectId.toString().equals(superiorIdChosen) && relationChainAStr.contains(choosedIndirectUidStr)){
					result.setSuccess(false);
					result.setMsg("不可选择当前会员关系链中除直接上级以外的会员!");
					return result;
				}
			}
			
			//三、选择的间接收益人，其关系链与关系链A的一级会员需相同
			String chooseIndirectUidRelationChain = chooseIndirectIdInfo.getUidRelationChain();
			boolean toCompare=StringUtils.isNotBlank(chooseIndirectUidRelationChain)&&StringUtils.isNotBlank(relationChainAStr);
			if(toCompare){
				String first1 = chooseIndirectUidRelationChain;
				if(chooseIndirectUidRelationChain.contains(",")){
					first1=chooseIndirectUidRelationChain.substring(0, chooseIndirectUidRelationChain.indexOf(",", 1));//顶级会员
				}
				
				String first2 = relationChainAStr;
				if(relationChainAStr.contains(",")){
					first2 = relationChainAStr.substring(0, relationChainAStr.indexOf(",", 1));//顶级会员
				}
						
				if(!first1.equals(first2)){
					result.setSuccess(false);
					result.setMsg("间接上级与绑定上级不具有相同来源!");
					return result;
				}
			}
			
			//四、选择的间接收益人，与关系链A中的所有间接收益人都需不同。
			List<Object> uids=new ArrayList<Object>();
			String[] uidStrArray = relationChainAStr.split(",");
			for(String uidStrItem:uidStrArray){
				String uidItem=uidStrItem.replace("^(0+)", "");
				uids.add(uidItem);
			}
			
			StringBuffer relationChianAIndirectUidAll=new StringBuffer();
			List<Object> uidList = new ArrayList<>(new HashSet<>(uids));//去除重复UID
			if(uidList!=null && uidList.size()>0){
				List<BLiver> relationChainALiverAll = liverDao.selectLiversByUids(uidList.toArray());
				for(BLiver liverBean:relationChainALiverAll){
					String indirectUidStr=liverBean.getIndirectUid()==null?"":liverBean.getIndirectUid().toString();
					if(StringUtils.isNotBlank(indirectUidStr)){
						relationChianAIndirectUidAll.append(indirectUidStr).append(",");
					}
				}
			}
			
			
			if(relationChianAIndirectUidAll!=null && relationChianAIndirectUidAll.toString().contains(choosedIndirectUidStr)){
				result.setSuccess(false);
				result.setMsg("同一关系链中会员的间接上级不能重复，请重新选择间接上级!");
				return result;
			}
			
			//五、间接收益人，不能互相绑定(A,B不能互相作为对方的间接上级)
			Integer chooseIndirectUid = chooseIndirectIdInfo.getIndirectUid();
			if(chooseIndirectUid!=null && chooseIndirectUid.compareTo(uid)==0){
				result.setSuccess(false);
				result.setMsg("间接上级，不能互相绑定!");
				return result;
			}
			result.setSuccess(true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.log.error("验证间接上级失败:"+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 方法描述：绑定间接上级 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-28上午11:52:42 <br/>
	 * @param liver
	 */
	public void bindInderectSuperiorInfo(BLiver liver) {
		String superiorIdChosen = liver.getSuperiorIdChosen()==null?"":liver.getSuperiorIdChosen();//选择的上级
		Integer chooseIndirectId = liver.getIndirectId();
		String chooseIndirectIdStr=liver.getIndirectId()==null?"":liver.getIndirectId().toString();//选择的间接上级
		if(superiorIdChosen.equals(chooseIndirectIdStr)){
			return ;
		}
		
		BLiver chooseIndirectIdInfo = liverDao.selectByPrimaryKey(chooseIndirectId);//待绑定的间接上级信息
		if(chooseIndirectIdInfo==null){
			return ;
		}
		Integer indirectUid = chooseIndirectIdInfo.getUid();
		Integer id = liver.getId();
		if(id==null){
			return ;
		}
		BLiver liverBean=new BLiver();
		liverBean.setId(id);
		liverBean.setIndirectUid(indirectUid);
		liverDao.updateByPrimaryKeySelective(liverBean);
		
	}

	/**
	 * 方法描述：获取当前会员是否有V客充值订单信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-9下午8:19:57 <br/>
	 * @param anchor
	 * @return
	 */
	public long getRechargeInfo(BLiver anchor) {
		long count=0l;
		Integer uid = anchor.getUid();
		if(uid==null){
			return count;
		}
		TLivePayOrder livePayOrder=new TLivePayOrder();
		livePayOrder.setUid(uid);
		livePayOrder.setObjectOriented(LiveConstant.OBJECT_ORIENTED.OBJECT_ORIENTED_4);//充值渠道 0.常规 1.VIP 2.商家 3.直销 4.V客
		livePayOrder.setPayState(1);//充值支付状态 ：0 未支付; 1 已支付成功; 2 取消支付
		count = livePayOrderDao.count(livePayOrder);
		return count;
	}
	
	
}
