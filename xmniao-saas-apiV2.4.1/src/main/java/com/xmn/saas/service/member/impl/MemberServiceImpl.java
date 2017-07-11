package com.xmn.saas.service.member.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.controller.api.v1.member.vo.MemberListParams;
import com.xmn.saas.dao.member.MemberDao;
import com.xmn.saas.entity.member.MemberBill;
import com.xmn.saas.entity.member.MemberDetail;
import com.xmn.saas.entity.member.MemberList;
import com.xmn.saas.entity.member.MemberStatistics;
import com.xmn.saas.service.base.UserService;
import com.xmn.saas.service.member.MemberService;

/**
 * 
*      
* 类名称：MemberServiceImpl   
* 类描述：   会员管理业务类
* 创建人：xiaoxiong   
* 创建时间：2016年9月29日 下午5:39:27   
* 修改人：xiaoxiong   
* 修改时间：2016年9月29日 下午5:39:27   
* 修改备注：   
* @version    
*
 */
@Service
public class MemberServiceImpl implements MemberService{
	
	private final static String[] WEEKDAYS = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
	
	@Autowired
	private MemberDao memberDao;
	
	
	@Autowired
	private GlobalConfig config;
	
	// 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	/**
	 * 查询会员详细信息
	 */
	@Override
	public MemberDetail detail(Integer uid,int sellerid) {
		
		return memberDao.detail(uid,sellerid);
	}

	/**
	 * 查询会员信息
	 */
	@Override
	public ResponseData getThriftUserInfo(Integer uid) {
		try {
			/**
			 * 业务服务地址
			 */
			String host=config.getThriftBusHost();
			/**
			 * 业务服务端口
			 */
			String port=config.getThriftBusPort();
			
			UserService.Client client =ThriftBuilder.build(host, Integer.parseInt(port), "UserService", UserService.Client.class);
			
			ThriftBuilder.open();
			//请求参数
			Map<String,String> paramMap=new HashMap<String, String>();
			paramMap.put("uid", uid+"");
			
			return  client.getUserMsg(paramMap);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
			
		}finally{
			ThriftBuilder.close();
		}
		
		
	}

	@Override
	public List<MemberList> list(MemberListParams request) {
		
		
		/**
		 * 查询商家订单
		 */
		List<MemberBill> billList=memberDao.queryBillBySellerid(request);
		
		/**
		 * 数据处理
		 */
		List<MemberList> memberList=converMemberList(billList);
		
		return memberList;
	}
	
	/**
	 * 
	 * @Description: 数据处理后返回前台，MemberList每天数据
	 * @author xiaoxiong
	 * @date 2016年10月10日
	 */
	public List<MemberList> converMemberList(List<MemberBill> billList) {
		try {
			if(billList==null){
				return null;
			}
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
			Calendar cal = Calendar.getInstance();
			List<MemberList> list=new ArrayList<>();
			for (MemberBill bill : billList) {
				try {
					boolean flag = true;
					for (int i = 0; i < list.size(); i++) {
						MemberList tempMList = list.get(i);
						if (tempMList.getSdate().equals(bill.getZdate())) {
							flag = false;
							List<MemberBill> tempBillList = tempMList
									.getMemberBillList();
							boolean bflag = true;
							for (MemberBill mb : tempBillList) {
								if (mb.getUid() == bill.getUid()) {
									bflag = false;
									/*bill.setAvatar(mb.getAvatar());//设置头像
									bill.setName(mb.getName());*/
									break;
								}
							}
							if (bflag) {
								tempMList.setCount(tempMList.getCount() + 1);
								getAvatar(bill);//查询头像
								tempBillList.add(bill);
							}
							
							tempMList.setMemberBillList(tempBillList);
							list.remove(i);
							list.add(i, tempMList);
						}
					}
					if (flag) {
						MemberList mList = new MemberList();
						mList.setCount(1);
						List<MemberBill> tempList = new ArrayList<>();
						//查询头像
						getAvatar(bill);
						tempList.add(bill);
						mList.setMemberBillList(tempList);
						mList.setSdate(bill.getZdate());
						cal.setTimeInMillis(sdf.parse(bill.getZdate()).getTime());
						int day = cal.get(Calendar.DAY_OF_WEEK)-1;
						mList.setWeekDay(WEEKDAYS[day]);
						list.add(mList);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	


	@Override
	public MemberStatistics statistics(Integer userType, Integer searchType,int sellerid,String sdate,String edate) {
		
		MemberStatistics statistics=new MemberStatistics();
		
		/**
		 * 总人数
		 */
		int allCount=memberDao.queryMemberAllCount(sellerid,userType,null,null,null);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy.MM.dd");
		
//		String sdate=""; 
//		String sdate2=""; 
//		if(searchType==1){
//			 sdate =sdf.format(new Date());//本日
//			 sdate2 =sdf2.format(new Date());//本日
//		}
//		if(searchType==2){
//			Calendar cal =Calendar.getInstance();
//	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
//	        sdate=sdf.format(cal.getTime());
//	        sdate2=sdf2.format(cal.getTime());
//		}
//		if(searchType==3){
//			Calendar cal =Calendar.getInstance();
//	        cal.set(Calendar.DATE,cal.getActualMinimum(Calendar.DATE)); //获取本月的第一天日期
//	        sdate=sdf.format(cal.getTime());
//	        sdate2=sdf2.format(cal.getTime());
//		}
//		
		/**
		 * 新增总人数
		 */
		int addCount=memberDao.queryMemberAllCount(sellerid,userType,searchType,sdate,edate);
		
		statistics.setAllCount(allCount);
		statistics.setAddCount(addCount);
		
		if(searchType!=null && searchType!=0){
			try {
				if(sdate!=null){
					statistics.setSdate(sdf2.format(sdf.parse(sdate)));	
				}
				
				if(edate!=null){
					statistics.setEdate(sdf2.format(sdf.parse(edate)));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return statistics;
	}
	
	public void getAvatar(MemberBill bill){
		//查询头像
		ResponseData responseData = null;
		
		try {
			/**
			 * 业务服务地址
			 */
			String host=config.getThriftBusHost();
			/**
			 * 业务服务端口
			 */
			String port=config.getThriftBusPort();
			
			UserService.Client client =ThriftBuilder.build(host, Integer.parseInt(port), "UserService", UserService.Client.class);
			
			ThriftBuilder.open();
			//请求参数
			Map<String,String> paramMap=new HashMap<String, String>();
			paramMap.put("uid", bill.getUid()+"");
			responseData=client.getUserMsg(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("调用接口获取用户信息失败："+bill.getUid());
		}finally{
			ThriftBuilder.close();
		}
		if(responseData!=null&&responseData.getResultMap()!=null){
			Map<String, String> userMap=responseData.getResultMap();
			bill.setAvatar(userMap.get("avatar")==null?"":config.getImageHost()+userMap.get("avatar"));
			bill.setName(userMap.get("nname")==null?"":userMap.get("nname"));
		}else{
			bill.setAvatar("");
		}
	}
	public static void main(String[] args) {
		MemberBill m1 = new MemberBill();
		m1.setCount(1);
		MemberBill m2 = new MemberBill();
		m1.setCount(1);
		MemberBill m3 = new MemberBill();
		m1.setCount(2);
		MemberBill m4 = new MemberBill();
		m1.setCount(2);
		MemberBill m5 = new MemberBill();
		m1.setCount(3);
		MemberBill m6 = new MemberBill();
		m1.setCount(3);
		List<MemberBill> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		list.add(m5);
		list.add(m6);
		List<MemberList> mlist = new ArrayList<>();
		for(MemberBill m:list){
			boolean flag = true;
			for(MemberList me : mlist){
				if(me.getCount()==m.getCount()){
					
				}
				
			}
			
		}
		
	}
	
}
