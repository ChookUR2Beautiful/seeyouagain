package com.xmn.saas.service.celebrity;

import com.xmn.saas.base.Page;
import com.xmn.saas.entity.celebrity.Celebrity;
import com.xmn.saas.entity.celebrity.CelebrityAriticle;
import com.xmn.saas.entity.celebrity.CelebrityOrder;
import com.xmn.saas.entity.common.SellerAccount;

import java.util.List;

/**
 * create 2016/11/30
 *
 * @author yangQiang
 */

public interface CelebrityService {

    List<Celebrity> queryCelebrityList(Page page);

    List<CelebrityOrder> queryCelebriyTaskList(SellerAccount sellerAccount, Page page);

    List<CelebrityAriticle> queryPublishedTaskList(SellerAccount sellerAccount, Page page);

    Celebrity queryCelebrityDetail(Long celebrityId);

    void submitCelebrityTask(CelebrityOrder celebrityTask);
}
