/**
 * 
 */
package com.xmniao.xmn.core.xmermanagerment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.billmanagerment.dao.AllBillDao;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.xmermanagerment.dao.BWalletRecordDao;
import com.xmniao.xmn.core.xmermanagerment.entity.BWalletRecord;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmerWalletRecord;
import com.xmniao.xmn.core.xmnpay.dao.WalletDao;
import com.xmniao.xmn.core.xmnpay.entity.Bwallet;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FinanceService
 * 
 * 类描述：个人财务管理接口
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-25下午3:22:06
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class FinanceService extends BaseService<Bwallet> {
	
	/**
	 * 钱包DAO
	 */
	@Autowired
	private WalletDao walletDao;
	
	/**
	 * 订单DAO
	 */
	@Autowired
	private AllBillDao allBillDao;
	
	/**
	 * 钱包记录DAO
	 */
	@Autowired
	private BWalletRecordDao walletRecordDao;
	
	/**
	 * 寻蜜客钱包交易记录服务
	 */
	@Autowired
	private BXmerWalletRecordService xmerWalletRecordService;

	/* (non-Javadoc)
	 * @see com.xmniao.xmn.core.base.BaseService#getBaseDao()
	 */
	@Override
	protected BaseDao getBaseDao() {
		return walletDao;
	}

	/**
	 * 获取钱包分页查询结果
	 * @param wallet
	 * @param pageable
	 */
	public void getWalletListPage(Bwallet wallet, Pageable<Bwallet> pageable) {
		pageable.setContent(this.getWalletList(wallet));
		pageable.setTotal(this.getWalletCount(wallet));
	}
	
	/**
	 * 获取钱包记录分页查询结果
	 * @param walletRecord
	 * @param pageable
	 */
	public void getWalletRecordListPage(BWalletRecord walletRecord, Pageable<BWalletRecord> pageable) {
		pageable.setContent(this.getWalletRecordList(walletRecord));
		pageable.setTotal(this.getWalletRecordCount(walletRecord));
	}
	
	/**
	 * 获取订单分页查询结果
	 * @param bill
	 * @param pageable
	 */
	public void getBillListPage(Bill bill, Pageable<Bill> pageable) {
		pageable.setContent(this.getBillListByUid(bill));
		pageable.setTotal(this.getBillCountByUid(bill));
	}

	

	/**
	 * 获取钱包记录数据条数
	 * @param walletRecord
	 * @return
	 */
	private Long getWalletRecordCount(BWalletRecord walletRecord) {
		return walletRecordDao.getWalletRecordCount(walletRecord);
	}

	/**
	 * 获取钱包记录列表
	 * @param walletRecord
	 * @return
	 */
	public List<BWalletRecord> getWalletRecordList(BWalletRecord walletRecord) {
		return walletRecordDao.getWalletRecordList(walletRecord);
	}

	/**
	 * 返回钱包数量
	 * @param bwallet
	 * @return
	 */
	private Long getWalletCount(Bwallet wallet) {
		return walletDao.getWalletCount(wallet);
	}

	/**
	 * 返回钱包列表
	 * @param bwallet
	 * @return
	 */
	private List<Bwallet> getWalletList(Bwallet wallet) {
		return walletDao.getWalletList(wallet);
	}
	
	/**
	 * 根据用户ID数组返回钱包列表
	 * @param objects 用户ID数组
	 * @return
	 */
	public List<Bwallet> selectWallet(Object[] objects){
		return walletDao.selectWallet(objects);
	}
	
	/**
	 * 根据用户ID数组，返回订单消费总额信息
	 * @param objects
	 * @return
	 */
	public List<Bill> getBillListByUids(Object[] objects){
		return allBillDao.getBillListByUids(objects);
	}

	
	/**
	 * 根据用户ID查询消费订单数(status为1,2,3)
	 * @param bill
	 * @return
	 */
	public Long getBillCountByUid(Bill bill) {
		return allBillDao.getBillCountByUid(bill);
	}

	/**
	 * 根据用户ID查询消费订单列表(status为1,2,3)
	 * @param bill
	 * @return
	 */
	public List<Bill> getBillListByUid(Bill bill) {
		return allBillDao.getBillListByUid(bill);
	}

	/**
	 * 方法描述：获取寻蜜客钱包交易记录列表
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-9下午3:46:41
	 * @param xmerWalletRecord
	 * @param pageable
	 */
	public void getXmerWalletRecordListPage(BXmerWalletRecord xmerWalletRecord,
			Pageable<BXmerWalletRecord> pageable) {
		List<BXmerWalletRecord> list = xmerWalletRecordService.getList(xmerWalletRecord);
		Long count = xmerWalletRecordService.count(xmerWalletRecord);
		pageable.setContent(list);
		pageable.setTotal(count);
		
	}
}
