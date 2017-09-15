package com.xmniao.xmn.core.market.controller.search;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.search.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yang qiang on 2016/12/22.
 */
@Controller("api-v1-market-search-controller")
@RequestMapping("/api/v1/market/search")
public class SearchController {
    private final Logger logger = LoggerFactory.getLogger(SearchController.class);
    @Autowired
    private SessionTokenService sessionTokenService;

    @Autowired
    private SearchService searchService;

    /**
     * @name 搜索热词列表获取接口
     * @url /api/v1/market/search/hot_word_list
     * @method POST
     * @response {"info":"请求成功","response":{"hotWordList":["牙膏","手机","面包","关键字"]},"state":100}
     */
    @RequestMapping(value = "/hot_word_list",method = RequestMethod.POST)
    public void hotWordList(
        HttpServletResponse httpResponse    //
        ) throws IOException {

        logger.info("调用[搜索热词列表获取接口 /api/v1/market/search/hot_word_list POST]接口");
        try {
            // 查询后台配置的热搜关键字
            List<String> hotWordList = searchService.queryHotWordList();

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("hotWordList", hotWordList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[搜索热词列表获取接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }
    /**
     * @name 历史搜索词列表获取接口
     * @url /api/v1/market/search/history_word_list
     * @method POST
     * @param sessiontoken 会话令牌
     */
    @RequestMapping(value = "/history_word_list",method = RequestMethod.POST)
    public void historyWordList(String sessiontoken,    // 会话令牌
                                HttpServletResponse httpResponse ) throws IOException {

        logger.info("调用[历史搜索词列表获取接口 /api/v1/market/search/history_word_list POST]接口, 参数: sessiontoken=" + sessiontoken);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            List<String> historyWordList = searchService.queryHistoryWord(Integer.valueOf(uid));

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("historyWordList", historyWordList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[历史搜索词列表获取接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    /**
     * @name 清除历史搜索记录接口
     * @url /api/v1/market/search/clean_word
     * @method POST
     * @param sessiontoken 会话令牌
     */
    @RequestMapping(value = "clean_word",method = RequestMethod.POST)
    public void cleanWord(String sessiontoken,    // 会话令牌
                          HttpServletResponse httpResponse ) throws IOException {

        logger.info("调用[清除历史搜索记录接口 /api/v1/market/search/clean_word POST]接口, 参数: sessiontoken=" + sessiontoken);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            searchService.cleanWord(Integer.valueOf(uid));

            new Response(ResponseCode.SUCCESS, "请求成功").write(
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[清除历史搜索记录接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    /**
     * @name 搜索商品 列表获取接口
     * @url /api/v1/market/search/list
     * @method POST
     * @param word      搜索词
     * @param pageSize  分页 页面大小
     * @param pageNum   分页 页数
     * @param sessiontoken  会话令牌
     *
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public void list(@RequestParam("word") String word,
                     String sessiontoken,    // 会话令牌
                     Page page,
                     HttpServletResponse httpResponse) throws IOException {
        logger.info("调用[搜索商品 列表获取接口 /api/v1/market/search/list POST]接口, 参数: word=" + word + "  ");
        try {
            word = word.trim();
            List<ProductInfo> productList = searchService.queryByWord(word,sessiontoken,page);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("productList", productList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(ProductInfo. class,new String[]{
                        "pid",      // type:int     产品的id
                        "pname",    // type:String  产品的具体名称
                        "price",    // type:double  产品原单品价格
                        "breviary", // type:String  产品列表缩略图
                        "choice",   // type:int     是否精选商品  0不是  1是
                        "integral", // type:int     积分支付金额
                        "cash",     // type:double  现金支付金额
                        "codeid",   // type:int     产品唯一标识编号
                        "store",    // type:int     剩余总库存
                        "labels",   // 商品标签id
                        "activityId"// 活动id
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[搜索商品 列表获取接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }
    }

    /**
     * @name:   默认搜索栏默认占位文字
     * @url:    /api/v1/market/search/default_word
     * @method: POST
     * @response
     */
    @RequestMapping(value = "default_word",method = RequestMethod.POST)
    public void defaultWord(HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[默认搜索占位文字 /api/v1/market/search/default_word POST]接口, 参数: ");
        try {
            String defaultWord = searchService.queryDefualtWord();
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("defaultWord", defaultWord);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[默认搜索占位文字]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }
}
