package com.xmniao.xmn.core.market.service.search;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;

import java.util.List;

/**
 * Created by yang.qiang on 2016/12/29.
 */
public interface SearchService {

    // 搜索关键字类型
    int SEARCH_WORD_TYPE_USER = 1;      // 用户账户的历史搜索
    int SEARCH_WORD_TYPE_MANUAL = 2;    // 后台设置
    int SEARCH_WORD_TYPE_DEAFULT = 3;   // 默认替换搜索关键字
    int SEARCH_WORD_TYPE_RECORD =4;     // 搜索记录

    List<String> queryHotWordList();

    List<String> queryHistoryWord(Integer integer);

    void cleanWord(Integer integer);

    List<ProductInfo> queryByWord(String word, String uid, Page page);


    String queryDefualtWord(String word);

    String queryDefualtWord();
}
