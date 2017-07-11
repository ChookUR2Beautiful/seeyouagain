package com.xmn.saas.service.celebrity.impl;

import com.xmn.saas.base.Page;
import com.xmn.saas.entity.celebrity.Celebrity;
import com.xmn.saas.entity.celebrity.CelebrityAriticle;
import com.xmn.saas.entity.celebrity.CelebrityOrder;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.celebrity.CelebrityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create 2016/11/30
 *
 * @author yangQiang
 */

@Service
public class CelebrityServiceImpl implements CelebrityService{

    @Override
    public List<Celebrity> queryCelebrityList(Page page) {
        return null;
    }

    @Override
    public List<CelebrityOrder> queryCelebriyTaskList(SellerAccount sellerAccount, Page page) {
        return null;
    }

    @Override
    public List<CelebrityAriticle> queryPublishedTaskList(SellerAccount sellerAccount, Page page) {
        return null;
    }

    @Override
    public Celebrity queryCelebrityDetail(Long celebrityId) {
        return null;
    }

    @Override
    public void submitCelebrityTask(CelebrityOrder celebrityTask) {

    }
}
