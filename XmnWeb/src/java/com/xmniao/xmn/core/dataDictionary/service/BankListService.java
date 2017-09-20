package com.xmniao.xmn.core.dataDictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.dataDictionary.dao.BankListDao;
import com.xmniao.xmn.core.dataDictionary.entity.TBankList;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：BankListService
 * 
 * @类描述：银行
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年8月12日 上午10:29:00
 * 
 */
@Service
public class BankListService extends BaseService<TBankList> {

	@Autowired
	private BankListDao bankListDao;

	@Override
	protected BaseDao getBaseDao() {
		return bankListDao;
	}

	/**
	 * @author zhang'zhiwen
	 * @date 2015年8月12日 下午1:54:26
	 * @param bankList
	 * @param pageable
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void getListPage(TBankList bankList, Pageable<TBankList> pageable) {
		pageable.setContent(this.getList(bankList));
		pageable.setTotal(this.count(bankList));
	}

}
