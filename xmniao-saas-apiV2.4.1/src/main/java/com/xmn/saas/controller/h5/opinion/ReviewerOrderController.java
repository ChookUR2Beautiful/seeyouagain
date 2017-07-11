package com.xmn.saas.controller.h5.opinion;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Page;
import com.xmn.saas.entity.celebrity.Celebrity;
import com.xmn.saas.entity.celebrity.CelebrityAriticle;
import com.xmn.saas.entity.celebrity.CelebrityOrder;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.celebrity.ReviewerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * create 2016/11/29
 *
 * @author yangQiang
 */

@Controller("h5-opinion-reviewer-order-controller")
@RequestMapping("h5/opinion/reviewer/order")
public class ReviewerOrderController extends AbstractController{
    // 初始化日志处理类
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Redis 服务类
    @Autowired
    private RedisService redisService;

    @Autowired
    private GlobalConfig globalConfig;

    // 名嘴服务类
    @Autowired
    private ReviewerService reviewerService;


    @InitBinder
     public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
//        binder.registerCustomEditor(int.class, new IntEditor());
//        binder.registerCustomEditor(long.class, new LongEditor());
//        binder.registerCustomEditor(double.class, new DoubleEditor());
//        binder.registerCustomEditor(float.class, new FloatEditor());
    }

    /**
     * 我要食评任务提交页面
     */
    @RequestMapping(value = "submit",method = RequestMethod.GET)
    public ModelAndView submitPage(@RequestParam("reviewerId") Long reviewerId){
        logger.info("调用[我要食评提交页面 /h5/opinion/reviewer/order/submit GET]");
        // 查询名嘴信息
        Celebrity reviewer = reviewerService.queryReviewerDetail(reviewerId);

        // 返回响应数据
        ModelAndView modelAndView = new ModelAndView("opinion/reviewer/order-input");
        modelAndView.addObject("reviewer",reviewer);
        modelAndView.addObject("imgHost", globalConfig.getImageHost());
        return modelAndView;
    }

    /**
     * 提交食评任务接口
     */
    @ResponseBody
    @RequestMapping(value = "submit",method = RequestMethod.POST)
    public void submit(CelebrityOrder order) throws IOException {
        logger.info("调用[提交食评接口 /h5/opinion/reviewer/order/submit POST] 参数:"+order.toString());
        try {
            SellerAccount sellerAccount = redisService.getSellerAccount(this.getCookieToken());
            order.setSellerId(sellerAccount.getSellerid().longValue());
            reviewerService.submitOrder(order);
            success(order,new HashMap<Class<?>, String[]>(){{
                put(CelebrityOrder.class,new String[]{"id"});
            }});
        } catch (Exception e) {
            logger.error("调用[提交食评接口 /h5/opinion/reviewer/order/submit] 出现异常",e);
            failure();
        }
        // TODO 提交订单, 返回状态
    }

    /**
     * 任务列表页面
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView listPage(){
        logger.info("调用[任务列表页面 /h5/opinion/reviewer/order/list GET]");
        ModelAndView modelAndView = new ModelAndView("opinion/reviewer/order-list");
        // TODO 跳转页面
        return modelAndView;
    }


    /**
     * 进行中任务列表接口
     */
    @ResponseBody
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public void list(Page page) throws IOException {
        logger.info("调用[进行中任务列表接口 /h5/opinion/reviewer/order/list POST]");
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getCookieToken());

        // TODO 查询数据返回Json
        try {
            // 查询订单列表
            List<CelebrityOrder> celebrityOrders =  reviewerService.queryOrderList(sellerAccount,page);
            // 查询订单总数
            Integer count = reviewerService.queryOrderCount(sellerAccount);


            HashMap<String, Object> resultMap = new HashMap<>();

            resultMap.put("orderList",celebrityOrders);
            resultMap.put("orderCount",count);

            success(resultMap,new HashMap<Class<?>, String[]>(){{
                put(CelebrityOrder.class,new String[]{"celebrity","id","status","price","arrivalTime"});
                put(Celebrity.class, new String[]{"name","avatar","sex"});
            }},
            "yyyy-MM-dd");
        } catch (Exception e) {
            failure();
        }

    }


    /**
     * 已发任务列表接口
     */
    @ResponseBody
    @RequestMapping(value = "published_list",method = RequestMethod.POST)
    public void publishedList(Page page) throws IOException {
        logger.info("调用[已发布任务列表接口 /h5/opinion/reviewer/order/published_list POST]");

        try {
            SellerAccount sellerAccount = redisService.getSellerAccount(this.getCookieToken());
            List<CelebrityAriticle> ariticleList = reviewerService.queryAriticleList(sellerAccount,page);
            Integer count = reviewerService.countAriticle(sellerAccount);


            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("ariticleList",ariticleList);
            resultMap.put("ariticleCount",count);
            success(resultMap,new HashMap<Class<?>, String[]>(){{
                put(CelebrityAriticle.class,new String[]{"celebrity","id", "name", "image",  "views", "describe", "createTime", "sellerName","zoneName"});
                put(Celebrity.class,new String[]{"celebrity", "id", "sex", "avatar", "name"});
            }},
            "yyyy-MM-dd HH:mm");
        } catch (Exception e) {
            logger.error("调用已发布文章列表出现异常!",e);
            failure();
        }
    }
}
