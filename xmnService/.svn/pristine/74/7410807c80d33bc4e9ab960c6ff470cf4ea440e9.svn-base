package com.xmniao.xmn.core.market.service.search.impl;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.dao.FreshActivityCommonDao;
import com.xmniao.xmn.core.market.dao.FreshWordDao;
import com.xmniao.xmn.core.market.dao.ProductInfoDao;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.entity.search.FreshWord;
import com.xmniao.xmn.core.market.service.home.HomeService;
import com.xmniao.xmn.core.market.service.product.ProductService;
import com.xmniao.xmn.core.market.service.search.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yang.qiang on 2016/12/29.
 */
@Service
public class SearchServiceImpl implements SearchService {
    private final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    @Autowired
    private SessionTokenService sessionTokenService;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private FreshWordDao freshWordDao;
    @Autowired
    private String fileUrl;
    @Autowired
    private HomeService homeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private  FreshActivityCommonDao freshActivityCommonDao;

    /**
     * 查询后台配置的热搜关键字
     * @return
     */
    @Override
    public List<String> queryHotWordList() {
        // 热搜关键字,展示数量
        int records = 10;

        List<String> wordList = freshWordDao.selectHotWord(records);
        int notEnoughNum = records - wordList.size();
        if (notEnoughNum > 0) {
            wordList.addAll(freshWordDao.selectHotRecord(notEnoughNum));
        }
        return wordList;
    }

    /**
     * 查询历史查询记录
     * @return
     * @param integer
     */
    @Override
    public List<String> queryHistoryWord(Integer uid) {
        List<String> wordList = freshWordDao.selectByUid(uid);
        return wordList;
    }

    /**
     * 清理历史查询记录
     * @param integer
     */
    @Override
    public void cleanWord(Integer uid) {
        freshWordDao.deleteByUid(uid);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<ProductInfo> queryByWord(String word, String sessionToken, Page page) {

        saveWord(word, sessionToken);

        // 查询表中默认搜索设置, 替换搜索词
        String defaultWord = this.queryDefualtWord(word);

        // 搜索商品列表
        StringBuffer sb = new StringBuffer();
        sb.append("%").append(defaultWord).append("%");
        List<ProductInfo> productList = productInfoDao.selectLikeWord(sb.toString(),page);

        // 加载活动
        HashMap<Integer,FreshActivityCommon> activitys = new HashMap<>();
        for (ProductInfo productInfo : productList) {
            if (productInfo.getActivityId() != null) {
                activitys.put(productInfo.getActivityId(),freshActivityCommonDao.selectByPrimaryKey(productInfo.getActivityId()));
            }
        }

        for (ProductInfo productInfo : productList) {
            productInfo.setBreviary(fileUrl + productInfo.getBreviary());
            productService.loadLabel(productInfo,activitys);
        }

        return productList;

    }

    /**
     * 保存用户搜索记录
     * @param word
     * @param sessionToken
     */
    private void saveWord(String word, String sessionToken) {
        try {
            this.recordWord(word);

            // 如果用户已登录将用户搜索记录到表中
            if(sessionToken != null){
                String uid = sessionTokenService.getStringForValue(sessionToken).toString();
                Integer userid = Integer.valueOf(uid);
                List<FreshWord> records = freshWordDao.selectByWordAndUid(userid,word);
                // 判断该关键字是否已添加记录
                if (records.size() > 0) {
                    // 已添加 更新updateTime
                    FreshWord freshWord = new FreshWord();
                    freshWord.setId(records.get(0).getId());
                    freshWord.setUpdateTime(new Date());
                    freshWordDao.updateByPrimaryKeySelective(freshWord);
                }else {
                    // 未添加 插入数据
                    FreshWord freshWord = new FreshWord();
                    freshWord.setUserid(userid.longValue());
                    freshWord.setWord(word);
                    freshWord.setCreateTime(new Date());
                    freshWord.setUpdateTime(new Date());
                    freshWord.setType(SearchService.SEARCH_WORD_TYPE_USER);   // 1:用户关键字
                    freshWordDao.insertSelective(freshWord);
                    // 清除大于20条 的用户历史搜索记录

                    // 查询需要删除的搜索历史, 保留 {historys} 条记录
                    int historys = 20;
                    List<Integer> ids = freshWordDao.selectHistoryIdByUid(userid,historys);
                    if(ids.size()>0){
                        freshWordDao.deleteByIds(ids);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("记录用户历史搜索出现异常",e);
        }
    }

    /**
     * 记录搜索关键字
     * @param word
     */
    private void recordWord(String word) {
        // 查询搜索记录
        FreshWord freshWord = freshWordDao.selectByWordAndType(word,SEARCH_WORD_TYPE_MANUAL);
        if (freshWord == null){
            // 热搜单词没有记录, 查询用户记录
            freshWord = freshWordDao.selectByWordAndType(word,SearchService.SEARCH_WORD_TYPE_RECORD);
            if (freshWord == null) {
                // 热搜单词, 用户记录没有记录, 将关键字记录
                FreshWord record = new FreshWord();
                record.setType(SEARCH_WORD_TYPE_RECORD);
                record.setCreateTime(new Date());
                record.setUpdateTime(new Date());
                record.setWord(word);
                freshWordDao.insertSelective(record);
            }else {
                // 热搜单词未记录, 用户记录有记录, 更新记录次数
                freshWordDao.updateIncrById(freshWord);
            }
        }else {
            // 热搜单词有记录, 更新记录次数
            freshWordDao.updateIncrById(freshWord);
        }
    }


    /**
     * 查询是否设置默认搜索关键字
     * 替换默认关键字
     * @param word
     * @return
     */
    @Override
    public String queryDefualtWord(String word) {
        String defaultWord = freshWordDao.selectDefaultWord(word);
        return defaultWord==null ? word : defaultWord;
    }

    @Override
    public String queryDefualtWord() {
        return freshWordDao.selectDefaultWordByType();
    }

}
