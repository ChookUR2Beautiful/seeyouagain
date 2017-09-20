package com.xmniao.quartz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.UtilString;
import com.xmniao.dao.SellerStatisticsMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.quartz.AutoCountSellerFundsService;

/**
 * 统计商户前一天的资金流动
 * @author Administrator
 *
 */
@Service("autoCountSellerFundsService")
public class AutoCountSellerFundsServiceImpl implements AutoCountSellerFundsService{

	 //初始日志类
	private final Logger log = Logger.getLogger(AutoCountSellerFundsService.class);
	
	@Autowired
	private SellerStatisticsMapper sellerStatisticsMapper;
	
	@Autowired
	private WalletMapper walletMapper;
	
	private final int PAGE_SIZE = 1000;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 统计哪一天的商家记录
	 */
	private String countDate;
	
	/*
	 * 自动按用户类型将钱包余额进行分表保存
	 * @see com.xmniao.service.AutoCountSellerFundsService#autoClassifyBalance()
	 */
	public void autoClassifyBalance() {
		try{
			log.info("-------保存商户记录开始------");
			countDate  = UtilString.theDateOfYesterday(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			log.info("今天统计的是"+countDate+"的数据");
			
			saveWalletInfo(AmountType.SELLERAMOUNT);
			log.info("-------保存商户营收提现记录成功------");
			saveWalletInfo(AmountType.COMMISION);
			log.info("-------保存商户提现提现记录成功------");
		}catch(Exception e){
			log.error("自动保存商户营收分账提现统计记录异常",e);
		}
	}

	/*
	 * 分类统计钱包总数
	 */
	private int getWalletCount(AmountType amountType){
		int count = 0;
		switch(amountType){
		case AMOUNT:
			break;
		case SELLERAMOUNT:
			count = walletMapper.countSellerIncomeStatistics(countDate);
			break;
		case BALANCE:
			break;
		case COMMISION:
			count = walletMapper.countSellerCommisionStatistics(countDate);
			break;
		case ZBALANCE:
			break;
		case INTEGRAL:
			break;
		default :
			break;
		}
		return count;
	}
	
	/*
	 * 获取钱包余额记录数据列表
	 */
	private List<Map<String,Object>> getWalletBalance(Map<String,Object> wbMap,AmountType amountType){
		List<Map<String,Object>> wbList = new ArrayList<Map<String,Object>>() ;

		switch(amountType){
		case AMOUNT:
			break;
		case SELLERAMOUNT:
			wbList = walletMapper.listSellerIncomeStatistics(wbMap);
			break;
		case BALANCE:
			break;
		case COMMISION:
			wbList = walletMapper.listSellerCommisionStatistics(wbMap);
			break;
		case ZBALANCE:
			break;
		case INTEGRAL:
			break;
		default :
			break;
		}
		return wbList;
	}
	
	/*
	 * 插入钱包记录数据至钱包余额记录表中
	 */
	private int insertListToTable(List<Map<String,Object>> list,String table){
		
		for(Map<String,Object> map : list){
			map.put("recordDate", sdf.format(new Date()));
		}
		
		return sellerStatisticsMapper.insertSellerStatistics(list,table);
	}
	
	
	private void saveWalletInfo(AmountType amountType){
		Map<String,Object> countMap = new HashMap<String,Object>();
		
		int userWalletCount = getWalletCount(amountType);
		
		log.info("amountType:"+amountType+",统计记录总数为："+userWalletCount);
		
		int pageNo = 0;
		int pageSize = PAGE_SIZE;
		int startNo = 0;
		int result = 0;
		
		while(startNo < userWalletCount){
			
			//3.for 分页查询
			countMap.put("startNo", startNo);
			countMap.put("pageSize",pageSize);
			countMap.put("countDate",countDate);
			List<Map<String,Object>> wbList = getWalletBalance(countMap,amountType);
			log.info("金额类型:"+amountType+";页码："+(pageNo+1)+";获取记录数:"+wbList.size());
			if(wbList.size()>0){
				//4.写入对应表数据
				result =  insertListToTable(wbList,getTableName(amountType));
				if(result != wbList.size()){
					log.error("批量插入异常!");
				}
			}
			
			startNo = (++pageNo)*pageSize;
		}
	}
	
	private String getTableName(AmountType amountType){
		String table = "";
		switch(amountType){
		case AMOUNT:
			break;
		case SELLERAMOUNT:
			table = "s_income_statistics";
			break;
		case BALANCE:
			break;
		case COMMISION:
			table = "s_commision_statistics";
			break;
		case ZBALANCE:
			break;
		case INTEGRAL:
			break;
		default :
			break;
		}
		return table;
	}
	
}
enum AmountType{
	AMOUNT,
	SELLERAMOUNT,
	BALANCE,
	COMMISION,
	ZBALANCE,
	INTEGRAL;
}
