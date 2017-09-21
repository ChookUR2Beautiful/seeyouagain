package com.xmniao.dao.live;

import java.util.List;

import com.xmniao.domain.live.DebitcardSeller;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：DebitcardSellerDao
 * 
 * 类描述： 商家储值卡配置DAO
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月2日 下午2:25:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface DebitcardSellerDao {
	
	/**
	 * 
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年3月11日
	 * @param debitcardSeller
	 * @return DebitcardSeller
	 */
	DebitcardSeller getDebitcardSeller(DebitcardSeller debitcardSeller);

	/**
	 * 	
	 * 方法描述：查看商家是否开通储值卡
	 * 创建人： ChenBo
	 * 创建时间：2017年3月11日
	 * @param sellerid
	 * @return DebitcardSeller
	 */
	List<DebitcardSeller> getDebitcardForSellerid(Integer sellerid);
}