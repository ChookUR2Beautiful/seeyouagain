package com.xmn.saas.controller.h5.opinion;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Page;
import com.xmn.saas.controller.h5.opinion.vo.CelebrityTaskSubmitRequest;
import com.xmn.saas.entity.celebrity.Celebrity;
import com.xmn.saas.entity.celebrity.CelebrityAriticle;
import com.xmn.saas.entity.celebrity.CelebrityOrder;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.celebrity.CelebrityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * create 2016/11/28
 * 网红晒单 任务Controller
 * @author yangQiang
 */

@Controller("h5-opinion-celebrity-task-controller")
@RequestMapping("h5/opinion/celebrity/task")
public class CelebrityOrderController extends AbstractController {

    // 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Redis服务类
    @Autowired
    private RedisService redisService;

    // 网红(主播)服务类
    @Autowired
    private CelebrityService celebrityService;

    /**
     * 任务列表页面
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView listPage(){
        // TODO
        return null;
    }

    /**
     * 进行中的 网红晒照 进行中任务列表
     */
    @ResponseBody
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public void list(Page page) throws IOException {
        logger.info("调用[网红晒照进行中任务列表接口  h5/opinion/celebrity/task/list]");

        try {
            // 调用服务类查询进行中任务列表
            SellerAccount sellerAccount = redisService.getSellerAccount(this.getCookieToken());
            List<CelebrityOrder> celebrityTasks = celebrityService.queryCelebriyTaskList(sellerAccount,page);

            // 返回接口
            success(celebrityTasks);

        } catch (Exception e) {
            logger.error("调用[网红晒照进行中任务列表接口] 出现异常",e);
            failure();
        }
    }



    @ResponseBody
    @RequestMapping(value = "published_list",method = RequestMethod.POST)
    public void pulishedList(Page page) throws IOException {
        logger.info("调用[网红晒照-已发布任务列表接口 /h5/opinion/celebrity/task/published_list POST]");

        try {
            // 调用服务类 查询已发布任务
            SellerAccount sellerAccount = redisService.getSellerAccount(this.getCookieToken());
            List<CelebrityAriticle> tasks = celebrityService.queryPublishedTaskList(sellerAccount,page);

            // 返回数据
            success(tasks);
        } catch (Exception e) {
            logger.error("调用[网红晒照-已发布任务列表接口] 出现异常",e);
            failure();
        }
    }

    /**
     * 网红晒照提交填写需求页面
     * @param celebrityId 网红id
     */
    @RequestMapping(value = "submit", method = RequestMethod.GET)
    public ModelAndView submitInput(@Param("celebrityId") Long celebrityId){
        logger.info("调用[网红晒照任务提交页面 /h5/opinion/celebrity/task/submit GET] celebritId = " + celebrityId);

        ModelAndView modelAndView = new ModelAndView("opinion/celebrity/task/task-submit-input");
        Celebrity celebrity = celebrityService.queryCelebrityDetail(celebrityId);
        modelAndView.addObject(celebrity);

        return modelAndView;
    }

    /**
     * 提交任务接口
     */
    @ResponseBody
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public void submit(@Valid CelebrityTaskSubmitRequest request , BindingResult result) throws IOException {
        logger.info("调用[提交网红晒照接口 h5/opinion/celebrity/task/submit  POST] 参数:"+request.toString());
        try {
            if (!request.doValidate(result)) {
                return;
            }

            // 封装 网红晒图 任务类
            CelebrityOrder celebrityTask = request.convertToCelebrityTask();
            SellerAccount sellerAccount = redisService.getSellerAccount(this.getCookieToken()); // 根据Cookie获取商户信息
            celebrityTask.setSellerId(sellerAccount.getSellerid().longValue());                 // 绑定任务 商户

            // 调用服务类 提交 网红晒照 任务
            celebrityService.submitCelebrityTask(celebrityTask);

            success();
        } catch (Exception e) {
            logger.error("调用[提交网红晒照接口] 出现异常!",e);
            failure();
        }

    }

}
