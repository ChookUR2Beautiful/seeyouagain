package com.xmniao.quartz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.xmniao.dao.ExpensesMpper;
import com.xmniao.dao.MentionAccountMapper;
import com.xmniao.dao.UpdateWalletBalanceMapper;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.quartz.AutoMentionService;
import com.xmniao.service.impl.MentionServiceImpl;
import com.xmniao.thrift.ledger.FailureException;

/**
 * 自动提现监听
 * 
 * @author YangJing
 * 
 */
@Service("autoMentionService")
public class AutoMentionServiceImpl implements AutoMentionService {

	/**
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(AutoMentionServiceImpl.class);

	/**
	 * 钱包Mapper
	 */
	@Autowired
	private WalletMapper walletMapper;

	/**
	 * 提现方式Mapper
	 */
	@Autowired
	private MentionAccountMapper mentionAccountMapper;

	@Autowired
	private UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;

	@Autowired
	public UpdateWalletBalanceMapper updateWalletBalanceMapper;

	/**
	 * 自动提现手续费Mapper
	 */
	@Autowired
	private ExpensesMpper expensesMpper;

	/**
	 * redisTemplate
	 */
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private MentionServiceImpl mentionServiceImpl;

	/**
	 * 自动提现redis名称
	 */
	@Resource(name = "autoRedisName")
	private String autoRedisName;

	/**
	 * 默认提现金额
	 */
	//private double tMoney = 50000;

	/**
	 * 自动提现
	 * 
	 * @throws FailureException
	 */
	
	@Override
	public void autoMention() {

		log.info("自动提现监听启动");

		// 营业收入提现集合
		List<Map<String, Object>> incomeUserList = walletMapper
				.getMentionIncomeUsers();

		// 营业收入提现
		for (Map<String, Object> incomeUser : incomeUserList) {
			 try {
				 mentionServiceImpl.mentionIncome(incomeUser);
			} catch (FailureException e) {
				log.error("自动提现营收异常，用户："+incomeUser.get("sellerId")+"",e);
			}
		}

		// 佣金提现集合
		List<Map<String, Object>> commisionUserList = walletMapper
				.getMentionCommisionUsers();

		// 提现佣金
		for (Map<String, Object> commisionUser : commisionUserList) {
			 try {
				 mentionServiceImpl.mentionCommision(commisionUser);
			 } catch (FailureException e) {
				 log.error("自动提现佣金异常，用户："+commisionUser.get("sellerId")+"",e);
			 }
		}

		log.info("自动提现监听结束");
	}
	
	
}
