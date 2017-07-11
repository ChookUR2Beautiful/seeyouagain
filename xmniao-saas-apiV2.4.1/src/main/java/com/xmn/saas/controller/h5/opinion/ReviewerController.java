package com.xmn.saas.controller.h5.opinion;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Page;
import com.xmn.saas.entity.celebrity.Celebrity;
import com.xmn.saas.entity.celebrity.CelebrityAriticle;
import com.xmn.saas.service.celebrity.ReviewerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * create 2016/11/29
 *
 * @author yangQiang
 */
@Controller("h5-opinion-opinion-reviewer-controller")
@RequestMapping("/h5/opinion/reviewer")
public class ReviewerController extends AbstractController{
    // 初始化日志处理类
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReviewerService reviewerService;

    @Autowired
    private GlobalConfig globalConfig;

    /**
     * 名嘴列表页面
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView listPage(){
        logger.info("调用[名嘴列表页面 /h5/opinion/reviewer/list GET]页面");

        // TODO 跳转名嘴列表页面
        ModelAndView modelAndView = new ModelAndView("opinion/reviewer/reviewer-list");
        modelAndView.addObject("imgHost",globalConfig.getImageHost());
        return modelAndView;
    }

    /**
     * 名嘴列表
     * order 1: 根据费用排序   2: 根据食评次数排序
     */
    @ResponseBody
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public void list(@RequestParam(value = "order",defaultValue = "1") Integer order, Page page) throws IOException {
        logger.info("调用[名嘴列表接口 /h5/opinion/reviewer/list POST]");
        // TODO 分页, 排序查询名嘴列表
        try {

            // 查询名嘴列表
            List<Celebrity> celebrityList = reviewerService.queryReviewerList(page,order);
            // 查询名嘴总数
            Long count = reviewerService.countReviewer();

            // 封装数据
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("count",count);
            resultMap.put("reviewerList",celebrityList);


            success(resultMap,new HashMap<Class<?>, String[]>(){{
                put(Celebrity.class,new String[]{"id", "sex", "avatar", "name", "reviewPrice", "orderNum", "describe"});
            }});

        } catch (Exception e) {
            logger.error("调用[名嘴列表接口 /h5/opinion/reviewer/list POST] 出现异常",e);
            failure();
        }
    }

    /**
     * 名嘴详情页面
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public ModelAndView detailPage(@RequestParam("reviewerId") Long reviewerId){
        logger.info("调用[名嘴详情页面 /h5/opinion/reviewer/detail GET]");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("opinion/reviewer/reviewer-detail");

        // TODO 查询名嘴详情,并渲染页面
        Celebrity celebrity = reviewerService.queryReviewerDetail(reviewerId);
        CelebrityAriticle celebrityAriticle = reviewerService.queryMaxViewsAriticle(reviewerId);

        // 返回响应模型
        modelAndView.addObject("ariticle",celebrityAriticle);
        modelAndView.addObject("reviewer",celebrity);
        modelAndView.addObject("imgHost",  globalConfig.getImageHost());
        return modelAndView;
    }


    /**
     * 查看历史评价
     * @return
     */
    @RequestMapping(value = "article",method = RequestMethod.GET)
    public ModelAndView article(@RequestParam("articleId") Long articleId){
        logger.info("调用[历史评价图文页面 /h5/opinion/reviewer/article] articleId :"+articleId);
        ModelAndView modelAndView = new ModelAndView("opinion/reviewer/article");
        String content = reviewerService.queryAriticle(articleId);
        modelAndView.addObject(content);
        return modelAndView;
    }

}
