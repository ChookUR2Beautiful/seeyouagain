package com.xmn.saas.dao.common;

import com.xmn.saas.entity.common.SellerAccount;

import java.util.List;
import java.util.Map;


public interface SellerAccountDao {
    int deleteByPrimaryKey(Integer aid);

    int insert(SellerAccount record);

    int insertSelective(SellerAccount record);

    SellerAccount selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(SellerAccount record);

    int updateByPrimaryKey(SellerAccount record);

    /**
     * 账号和密码查询用户
     *
     * @param sellerAccount
     * @return
     */
    SellerAccount login(SellerAccount sellerAccount);

    /**
     * 根据根据主键和密码查询用户
     *
     * @param sellerAccount
     * @return
     */
    int countAccountByAidAndPassword(SellerAccount sellerAccount);


    /**
     * 修改账号密码
     *
     * @param sellerAccount
     */
    void updatePassword(SellerAccount sellerAccount);

    /**
     * 根据手机号码查询账号
     *
     * @param subAccount
     * @return
     */
    int countAccountByPhone(SellerAccount subAccount);

    /**
     * 根据父账号,查询到所有子账号
     *
     * @param aid
     * @return
     */
    List<SellerAccount> selectSubAccountByParent(Integer aid);

    /**
     * 查询商户账户详情
     * @param aid
     * @return
     */
    Map<String,Object> selectAccountDetail(Integer aid);

    int updatePasswordByAccount(SellerAccount sellerAccount);

    /**
     * 根据账号(account)统计账户
     * @param phone
     * @return
     */
    int countAccountByAccount(String phone);

    /**
     * 根据SelleId查询子账号
     * @param sellerAccount
     * @return
     */
    List<SellerAccount> selectSubAccountBySellerId(SellerAccount sellerAccount);

    List<Integer> selectByAccount(String account);
}