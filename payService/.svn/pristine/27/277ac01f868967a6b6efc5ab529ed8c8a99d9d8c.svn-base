package com.xmniao.quartz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.dao.WBRecordMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.quartz.AutoClassifyBalanceService;

@Service("autoClassifyBalanceService")
public class AutoClassifyBalanceServiceImpl implements AutoClassifyBalanceService{

	 //初始日志类
	private final Logger log = Logger.getLogger(AutoClassifyBalanceServiceImpl.class);
	
	@Autowired
	private WBRecordMapper wbRecordMapper;
	
	@Autowired
	private WalletMapper walletMapper;
	
	private final int PAGE_SIZE = 1000;
	private final String USER_TABLE = "u_wb_";
	private final String SELLER_TABLE = "s_wb_";
	private final String JOINT_TABLE = "p_wb_";
	private final int USER_TYPE = 1;
	private final int SELLER_TYPE = 2;
	private final int JOINT_TYPE = 3;
	/*
	 * 自动按用户类型将钱包余额进行分表保存
	 * @see com.xmniao.service.AutoClassifyBalanceService#autoClassifyBalance()
	 */
	@Override
	public void autoClassifyBalance() {
		long startTime = System.currentTimeMillis();
		
		//1.建表3个
		String curDate = new SimpleDateFormat("yyMMdd").format(new Date());
		String curUserTable = USER_TABLE+curDate.substring(0, 4);
		String curSellerTable = SELLER_TABLE+curDate.substring(0, 4);
		String curJonitTable = JOINT_TABLE+curDate.substring(0, 4);
		
		createUserTable(curUserTable,USER_TYPE);
		createUserTable(curSellerTable,SELLER_TYPE);
		createUserTable(curJonitTable,JOINT_TYPE);
		
		saveWalletInfo(3,curJonitTable);
		log.info("-------插入合作商表数据成功------");
		saveWalletInfo(2,curSellerTable);
		log.info("-------插入商户表数据成功------");
		saveWalletInfo(1,curUserTable);
		log.info("-------插入用户表数据成功------");
		
		log.info("本次操作共花费"+(System.currentTimeMillis()-startTime)+"ms");
	}
	
	/*
	 * 创建用户/商户/合作商钱包余额记录表
	 */
	private int createUserTable(String table,int userType){
		try{
			Map<String,String> map = new HashMap<String,String>();
			map.put("tableName", table);
			if(userType ==USER_TYPE){
				wbRecordMapper.createUserTableByname(map);
			}else if(userType ==SELLER_TYPE){
				wbRecordMapper.createSellerTableByname(map);
			}else if(userType ==JOINT_TYPE){
				wbRecordMapper.createJonitTableByname(map);
			}
		}catch(Exception e){
			log.error(table+"当前表已存在");
			return -1;
		}
		return 0;
	}
	
	
	/*
	 * 分类统计钱包总数
	 */
	private int getWalletCount(int userType){
		Map<String,String> map = new HashMap<String,String>();
		map.put("userType", userType+"");
		return walletMapper.getEffectiveWalletCount(map);
	}
	
	/*
	 * 获取钱包余额记录数据列表
	 */
	private List<Map<String,Object>> getWalletBalance(int userType,int startNo,int pageSize){
		List<Map<String,Object>> wbList = null ;
		Map<String,Object> wbMap = new HashMap<String,Object>();
		wbMap.put("startNo", startNo);
		wbMap.put("pageSize",pageSize);
		
		if(userType == USER_TYPE){
			wbList = walletMapper.getUserWalletBalance(wbMap);
		}else if(userType == SELLER_TYPE){
			wbList = walletMapper.getSellerWalletBalance(wbMap);
		}else if(userType == JOINT_TYPE){
			wbList = walletMapper.getJonitWalletBalance(wbMap);
		}
		
		return wbList;
	}
	
	/*
	 * 插入钱包记录数据至钱包余额记录表中
	 */
	private int insertListToTable(List<Map<String,Object>> list,int userType,String table){
		
		for(Map<String,Object> map : list){
			
			map.put("recordDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
		}
		
		if(userType == USER_TYPE){
			return wbRecordMapper.insertUserListData(list,table);
		}else if(userType == SELLER_TYPE){
			return wbRecordMapper.insertSellerListData(list,table);
		}else if(userType == JOINT_TYPE){
			return wbRecordMapper.insertJointListData(list,table);
		}
		
		return -1;
	}
	
	
	private void saveWalletInfo(int userType,String table){
		//2.查询有效的钱包总数
		int userWalletCount = getWalletCount(userType);
		
		log.info("userType:"+userType+",钱包总数为："+userWalletCount);
		
		int pageNo = 0;
		int pageSize = PAGE_SIZE;
		int startNo = 0;
		int result = 0;
		
		while(startNo < userWalletCount){
			
			//3.for 分页查询
			long sTime = System.currentTimeMillis();
			List<Map<String,Object>> wbList = getWalletBalance(userType,startNo,pageSize);
			log.info("测试查询时间："+(System.currentTimeMillis()-sTime)+"ms");
			log.info("用户类型:"+userType+";页码："+pageNo+";获取记录数:"+wbList.size());
			if(wbList.size()>0){
				//4.写入对应表数据
				long startTime = System.currentTimeMillis();
				result =  insertListToTable(wbList,userType,table);
				if(result != wbList.size()){
					log.error("批量插入异常!");
				}
				log.info("测试插入"+wbList.size()+"条记录所需时间："+(System.currentTimeMillis()-startTime)+"ms");
			}
			
			startNo = (++pageNo)*pageSize;
		}
	}
}
