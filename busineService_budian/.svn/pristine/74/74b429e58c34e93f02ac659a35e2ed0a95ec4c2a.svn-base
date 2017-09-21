package com.xmniao.service.xmer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.ResponseState;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.urs.dao.UrsDao;
import com.xmniao.urs.dao.XmerDao;

/**
 * 寻蜜客Servicee服务类
 * 
 * 项目名称：busineService
 * 
 * 类名称：XmerService
 * 
 * 类描述：
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年7月22日 上午9:46:09
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class XmerService {
	/*
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(XmerService.class);

	@Autowired
	private XmerDao xmerDao;

	@Autowired
	private UrsDao ursDao;

	/**
	 * 获取指定寻蜜客的上级关系(不含已解约的寻蜜客)
	 * 
	 * @Title: getXmerIssues
	 * @Description:
	 */
	public Map<String, Object> getXmerIssues(Integer xmerUid) throws Exception {
		Map<String, Object> issuesMap = xmerDao.getXmerIssues(xmerUid);
		if (issuesMap != null && issuesMap.size() > 0) {

			Set<String> keys = issuesMap.keySet();
			String[] array = keys.toArray(new String[] {});
			List<String> ids = new ArrayList<String>();
			for (String name : array) {
				ids.add(issuesMap.get(name).toString());
			}
			List<Map<String, Object>> nameList = xmerDao.getXmerName(ids
					.toArray(new String[] {}));
			Iterator<String> it = keys.iterator();
			Map<String, Object> nMap = new HashMap<String, Object>();
			while (it.hasNext()) {
				String key1 = it.next();
				for (Map<String, Object> nameMap : nameList) {
					if (nameMap.get("uid").toString()
							.equals(issuesMap.get(key1).toString())) {
						String nameKey = key1.replace("Id", "Name");
						nMap.put(nameKey, nameMap.get("name"));
					}
				}
			}
			issuesMap.putAll(nMap);

		} else {
			issuesMap = new HashMap<String, Object>();
			log.error("该寻蜜客【" + xmerUid + "】已解约或尚未成为寻蜜客");
		}
		return issuesMap;
	}

	
	 /**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： 验证参数 <br/>
	 * 创建时间：2017年5月23日下午6:41:38 <br/>
	 * @param paraMap
	 * @return
	 */
	private boolean validateData(Map<String, Object> paraMap) {
		if (paraMap.get("rtype") == null 
				|| paraMap.get("uid") == null) {
			return false;
		}	
		
		return true;
	}
	
	/**
	 * 
	 * 方法描述：根据saas订单信息创建寻蜜客,并给他的上级和上上级伙伴数量加一 创建人： chenJie
	 * 创建时间：2016年8月13日下午2:27:12
	 * 
	 * @param saasOrder
	 * @return
	 */
	public boolean createXmerBySaasorder(Map<String, Object> resOrderMap) {
		log.info("createXmerBySaasorder:" + resOrderMap);
		try {
			if (!validateData(resOrderMap)) {
				log.error("传入参数不完整");
				throw new FailureException(ResponseState.PARAM_ERROR, "传入参数不完整");
			}
			String uid = resOrderMap.get("uid") + "";
			Map<String, Object> ursMap = ursDao.getUrsByUid(uid);

			String achievement = xmerDao.getInitAchievement();
			//判断是否存在
			
			// 创建寻蜜客信息
			Map<String, Object> xmerMap = new HashMap<>();
			
			String oneLevelXmerId = "", twoLevelXmerId = "";
			if (resOrderMap.get("uidRelationChain") != null) {// 有上级寻蜜客
				String uidRelationChain = (String) resOrderMap.get("uidRelationChain");  //saasOrder关系链
				String strArr[] = uidRelationChain.split(",");
				for (int i = 0; i < strArr.length; i ++){
					if (i == strArr.length - 2) {
						twoLevelXmerId = strArr[i];  //上上级寻蜜客
					}
					if (i == strArr.length - 1) {
						oneLevelXmerId = strArr[i];  //上级寻蜜客
					}
				}
				
			}
		    Map<String, Object> parentXmerMap = null;
		  //接口寻密客类型 1-个人寻密客 2-V客寻蜜客  3-中脉寻密客 4-V客赠送SAAS主播
		    Integer levels = 1;
		    String rtype =  (String) resOrderMap.get("rtype");  
			if ("1".equals(rtype)){
				if (resOrderMap.get("uidRelationChain") != null) {
					parentXmerMap = xmerDao.getXmerInfo(Integer.parseInt(oneLevelXmerId));
					if (parentXmerMap != null)
						levels = (Integer) parentXmerMap.get("levels") + 1;
				}
			}else if ("2".equals(rtype) || "3".equals(rtype)){
				levels = 1;			
			}else if ("4".equals(rtype)){
				levels = 2;
			}
			xmerMap.put("levels", levels);
			
			xmerMap.put("uid", uid);
			xmerMap.put("rtype", 1);  //1=个人寻密客,  2=企业寻密客
			xmerMap.put("phoneid", ursMap.get("phone"));
//			xmerMap.put("parentid", resOrderMap.get("parentid"));   //0605版本废除

			xmerMap.put("soldnums", 0);
			xmerMap.put("achievement", achievement);
			xmerMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date()));
			xmerMap.put("status", 0);
			xmerMap.put("updateDate", new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			log.info("添加寻蜜客信息" + xmerMap);
			int result1 = xmerDao.insertXmer(xmerMap);
			if (1 != result1) {
				log.error("创建寻蜜客失败");
				return false;
			}
			// 创建寻蜜客成功后，判断是否为一级寻蜜客，否，则给他上级寻蜜客伙伴数量加一
			/* 关系链已迁移到 b_urs_earnings_relation
			 * */
			if (resOrderMap.get("uidRelationChain") != null) {// 有上级寻蜜客
				Map<String, String> map = new HashMap<>();
				map.put("uid", oneLevelXmerId + "");
				xmerDao.updatePartnerNum(map);// 伙伴数量加一

				/*Map<String, String> xmerMap2 = new HashMap<>();
				xmerMap2.put("parentid", twoLevelXmerId + "");*/
				if (twoLevelXmerId !=null) {// 判断是否有上上级寻蜜客
					map.put("uid", twoLevelXmerId+"");
					xmerDao.updatePartnerNum(map);// 伙伴数量加一
				}
			}
		} catch (Exception e) {
			log.error("createXmerBySaasorder:添加寻觅客失败",e);
			return false;
		}

		return true;
	}
	
	/**
	 * 添加寻蜜客会员收益关系链信息
	 * @param resOrderMap
	 * @return
	 */
	public int createUrsEarningsRelation(Map<String, Object> resOrderMap) {
		log.info("开始方法：createUrsEarningsRelation:" + resOrderMap);
		
		int result = 0;
		try {
			String uid = resOrderMap.get("uid") + "";
//			String relationid = String.format("%011d", uid);    //格式化为11位    
			final String STR_FORMAT = "00000000000"; 
		    DecimalFormat df = new DecimalFormat(STR_FORMAT);
		    String uidRelation =  df.format(Integer.parseInt(uid));
			String uidRelationChain  = resOrderMap.get("uidRelationChain") == null ? uidRelation : (String) resOrderMap.get("uidRelationChain") + ',' + uidRelation;
			
			//1-个人寻密客 2-V客寻蜜客 3-中脉寻密客 4-V客赠送SAAS给主播 
			String rtype = (String) resOrderMap.get("rtype");  
			//渠道来源 1.vip 2.商家 3.直销 4.v客 5.常规寻蜜客 6.主播寻蜜客(V客赠送) 7.V客寻密客 8.脉客寻密客
			Integer objectOriented = 0;  
			switch (Integer.parseInt(rtype)) {
			case 1:
				objectOriented = 5;
				break;
			case 2:
				objectOriented = 7;
				break;
			case 3:
				objectOriented = 8;
				break;
			case 4:
				objectOriented = 6;
				break;
			}
			
			//创建寻蜜客 关系链信息
			Map<String, Object> xmerRelationMap = new HashMap<>();
			xmerRelationMap.put("uid", uid);
			xmerRelationMap.put("objectOriented", objectOriented);  //会员渠道来源 1.vip 2.商家 3.直销 4.v客 5.常规寻蜜客 6.主播寻蜜客(V客赠送) 7.V客寻密客 8.脉客寻密客
			xmerRelationMap.put("uidRelationChain", uidRelationChain);
//			xmerRelationMap.put("uidRelationChainNname", ",");
			//会员层级
			int levels = 0;
			if (uidRelationChain.indexOf(",") > -1)
				levels = uidRelationChain.split("\\,").length + 1;
			else 
				levels = 1;
			xmerRelationMap.put("uidRelationChainLevel", levels);  
			xmerRelationMap.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
			log.info("添加寻蜜客会员收益关系链信息" + xmerRelationMap);
			result = xmerDao.insertUrsEarningsRelation(xmerRelationMap);

		} catch (Exception e) {
			log.error("执行方法结束: createUrsEarningsRelation:添加寻蜜客会员收益关系链信息失败",e);
		}
		
		return result;
	}
	
	/**
	 * 
	 * 方法描述：刷新寻蜜客等级
	 * 创建人：jianming  
	 * 创建时间：2017年5月10日 下午2:25:02   
	 * @param uid 
	 * @return
	 */
	public int updateLevelByUid(Integer uid) {
		Map<String,Object> level= xmerDao.getLevelByUid(uid);
		if(level==null){
			return 0;
		}
		level.put("uid", uid);
		return xmerDao.updateXmerLevel(level);
	}


}
