package com.xmniao.xmn.core.market.service.activity;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.entity.activity.indiana.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yang.qiang on 2017/2/21.
 */
public interface IndianaService {
    Rule getRule();

    List<FreshActivityIndiana> getIndianas(Page page);

    FreshActivityIndiana getIndiana(Integer activityId);

    List<FreshActivityIndianaDduonum> getRecentlyRecord(Long boutId, Page page);

    Integer payIndiana(Integer uid, Integer boutId, Integer point) throws Exception;

    Map<String, Object> getUserWallet(String uid) throws Exception;

    List<IndianaRecord> userRecord(String uid, Page page);

    Map<String,Object> banner();

    List<String> getIndianaNumbers(String uid, Integer boutId);

    FreshActivityIndiana getIndianaByBoutId(Integer boutId);

    Integer countUserBoutJoins(String uid, Integer id);

    FreshActivityIndianaBout queryBout(Integer boutId);
}
