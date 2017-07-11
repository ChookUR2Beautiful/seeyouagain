package com.xmn.saas.dao.sellercard;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmn.saas.entity.sellercard.DebitcardSeller;
/**
 * 
* @projectName: xmnService 
* @ClassName: DebitcardSellerDao    
* @Description:充值卡dao   
* @author: liuzhihao   
* @date: 2017年2月23日 上午11:33:21
 */
@Repository
public interface DebitcardSellerDao {

    DebitcardSeller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DebitcardSeller record);

    int updateByPrimaryKey(DebitcardSeller record);
	
	/**
	 * 根据店铺ID查询充值卡信息
	 * @param sellerid
	 * @return
	 */
	DebitcardSeller findBySellerId(@Param("sellerid")String sellerid);
	
	/**
	 * 通过sellerid查询商家类型
	 * @param sellerid
	 * @return
	 */
	DebitcardSeller selectBySellerId(@Param("sellerid")String sellerid);
}