package com.xmniao.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.common.DateUtil;
import com.xmniao.common.MapUtil;
import com.xmniao.common.XmnConstants;
import com.xmniao.dao.WalletExpansionMapper;
import com.xmniao.dao.WalletExpansionRecordMapper;
import com.xmniao.dao.WalletExpensesMapper;
import com.xmniao.entity.WalletExpansion;
import com.xmniao.entity.WalletExpansionRecord;
import com.xmniao.entity.WalletExpenses;
import com.xmniao.exception.ParamBlankException;
import com.xmniao.service.WalletExService;
import com.xmniao.service.WalletService;
import com.xmniao.service.pay.WalletExpansionServiceImpl;
import com.xmniao.thrift.ledger.FailureException;

@Service
public class WalletExServiceImpl implements WalletExService {

	 /**
     * 注入redis缓存
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
	
	@Resource(name = "WalletExpansionServiceImpl")
	private WalletExpansionServiceImpl walletExpansionServiceImpl;

	@Autowired
	private WalletExpensesMapper walletExpensesMapper;
	
	
	@Autowired
	private WalletExpansionMapper walletExpansionMapper;
	

	@Autowired
	private WalletExpansionRecordMapper walletExpansionRecordMapper;
	
	@Autowired
	private WalletService walletService;
	
	@Override
	public BigDecimal withdrawalsExpansion(Map<String, String> map) {
		try {
			String commision = map.get("commision");
			String zbalance = map.get("zbalance");
			if (StringUtils.isNotBlank(commision)) {
				// 转入到可提现
				BigDecimal expenses = getExpansionExpense(new BigDecimal(commision), map.get("type")).getRateSum();
				BigDecimal realCommision = new BigDecimal(commision).setScale(2,BigDecimal.ROUND_DOWN).subtract(expenses);
				map.put("amount", realCommision.toString());
				map.put("option", "1");
				map.put("recordType",String.valueOf(XmnConstants.EX_RECORD_TYPE_3));
				Map<String, String> updateWalletExpansion = walletExpansionServiceImpl.updateWalletExpansion(map);
				if (!"0".equals(updateWalletExpansion.get("state"))) {
					throw new RuntimeException(updateWalletExpansion.get("msg"));
				}
				map.put("amount", expenses.toString());
				map.put("recordType",String.valueOf(XmnConstants.EX_RECORD_TYPE_7));
				Map<String, String> updateWalletExpansion2 = walletExpansionServiceImpl.updateWalletExpansion(map);
				if (!"0".equals(updateWalletExpansion2.get("state"))) {
					throw new RuntimeException(updateWalletExpansion2.get("msg"));
				}
				return expenses;
			} else if (StringUtils.isNotEmpty(zbalance)) {
				// 转入到不可提现
				map.put("amount", zbalance);
				map.put("option", "1");
				map.put("recordType",String.valueOf(XmnConstants.EX_RECORD_TYPE_4));
				Map<String, String> updateWalletExpansion = walletExpansionServiceImpl.updateWalletExpansion(map);
				if (!"0".equals(updateWalletExpansion.get("state"))) {
					throw new RuntimeException(updateWalletExpansion.get("msg"));
				}
				return BigDecimal.valueOf(0);
			}
			return BigDecimal.valueOf(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public WalletExpenses getExpansionExpense(BigDecimal commision, String type) {
		WalletExpenses walletExpenses = walletExpensesMapper.getExpenses(type);
		int rateType = walletExpenses.getRateType().intValue();
		BigDecimal rate = walletExpenses.getRate();
		if (rateType == 1) {
			// 比例
			walletExpenses.setRateSum(commision.multiply(rate).setScale(2,BigDecimal.ROUND_UP));
		} else if (rateType == 2) {
			// 固定金额
			walletExpenses.setRateSum(rate);
		}
		return walletExpenses;
	}

	@Override
	public List<WalletExpansionRecord> getBySource(String source) throws FailureException {
		if(StringUtils.isBlank(source)){
			throw new FailureException(1,"source订单号不能为空");
		}
		return walletExpansionRecordMapper.getBySource(source);
	}

	@Override
	public BigDecimal getExrtype1availableAmount(Long id) {
		HashMap<String,String> hashMap = new HashMap<>();
		hashMap.put("accountid",id.toString());
		hashMap.put("type", String.valueOf(XmnConstants.EX_RTYPE_1));
		WalletExpansion walletExpansion = walletExpansionMapper.getWalletExpansion(hashMap);
		if(walletExpansion==null){
			return BigDecimal.valueOf(0);
		}
		hashMap.put("sdate", DateUtil.formatDate(DateUtil.getLastWeekSdate(),DateUtil.Y_M_D));
		hashMap.put("edate", DateUtil.formatDate(new Date(),DateUtil.Y_M_D));
		hashMap.put("listType", "2");
		BigDecimal sumExpansion = walletExpansionRecordMapper.sumExpansion(hashMap);	//上周总收益
		if(walletExpansion.getAmount().compareTo(sumExpansion)<=0){
			return BigDecimal.valueOf(0);
		}else{
			return walletExpansion.getAmount().subtract(sumExpansion).setScale(2,BigDecimal.ROUND_DOWN);
		}
	}

	@Override
	public BigDecimal getExrtype3TodayRecord(WalletExpansion wa) {
		return walletExpansionRecordMapper.getTodayRecord(wa);
	}



}
