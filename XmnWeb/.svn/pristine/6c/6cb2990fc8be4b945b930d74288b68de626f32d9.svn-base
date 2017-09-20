package com.xmniao.xmn.core.businessman.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.businessman.dao.DebitcardOrderDao;
import com.xmniao.xmn.core.businessman.entity.TDebitcardOrder;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：debitcardOrderService
 * 
 * 类描述： 兑换专享卡记录(商户)
 * 
 * 创建人： caiyl
 * 
 * 创建时间：
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class ExchangeCardService extends BaseService<TDebitcardOrder> {
	
	/**
	 * 兑换专享卡记录dao
	 */
	@Autowired
	private DebitcardOrderDao debitcardOrderDao;


	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return debitcardOrderDao;
	}

	
	
	/**
	 * 获取兑换专享卡记录
	 * 
	 * @param seller
	 * @return
	 */
	public Pageable<TDebitcardOrder> geTDebitcardOrderInfoList(TDebitcardOrder debitcardOrder) {
		Pageable<TDebitcardOrder> debitcardOrderInfoList = new Pageable<TDebitcardOrder>(debitcardOrder);
		debitcardOrderInfoList.setContent(this.getdebitcardOrderDataList(debitcardOrder));
		// 总条数
		debitcardOrderInfoList.setTotal(this.getDebitcardOrderCount(debitcardOrder));
		return debitcardOrderInfoList;
	}
	
	
	
	public List<TDebitcardOrder> getdebitcardOrderDataList(TDebitcardOrder debitcardOrder){
		// 商家列表内容
		List<TDebitcardOrder> debitcardOrderList = debitcardOrderDao.getDebitcardOrderDataList(debitcardOrder);
		if (debitcardOrderList != null) {
			for (TDebitcardOrder bean: debitcardOrderList) {
				// pay_coin兑换鸟币X2 － 获得额度 ＝ 红包额度
				bean.setRedPacketLimit( bean.getPayCoin().multiply(new BigDecimal(Double.valueOf(2))).subtract(bean.getDenomination()) );
				// pay_coin兑换鸟币X2 ＝ 总额度
				bean.setTotalLimit( bean.getPayCoin().multiply(new BigDecimal(Double.valueOf(2))) );
			}
		}
		
		return debitcardOrderList;
	}
	
	public Long getDebitcardOrderCount(TDebitcardOrder debitcardOrder) {
		return debitcardOrderDao.debitcardOrderCount(debitcardOrder);
	}

	
}
