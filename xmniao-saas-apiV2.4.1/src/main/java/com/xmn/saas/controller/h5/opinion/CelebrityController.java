package com.xmn.saas.controller.h5.opinion;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.controller.h5.opinion.vo.CelebrityListRequest;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.celebrity.CelebrityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;

/**
 * create 2016/11/26
 * 网红晒照
 * @author yangQiang
 */

@RequestMapping("h5/opinion/celebrity")
@Controller("h5-opinion-celebrity-controller")
public class CelebrityController extends AbstractController{

    // 网红 服务类
    @Autowired
    private CelebrityService celebrityService;

    // Redis 服务类
    @Autowired
    private RedisService redisService;

    // 初始化日志处理类
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 网红晒照入口
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void celebrity(){

    }

    /**
     * 网红列表页面
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String listInput(){
        logger.info("调用[网红列表页面 /h5/opinion/celebrity/list GET]");
        return "opinion/celebrity/celebrity-list";
    }

    /**
     * 网红(主播列表)
     */
    @ResponseBody
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public void list(@Valid CelebrityListRequest request) throws IOException {
        logger.info("调用[网红(主播列表) /h5/opinion/celebrity/list] ");
    }

}
