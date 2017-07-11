package com.xmn.saas.controller.h5.opinion;

import com.xmn.saas.service.base.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * create 2016/11/26
 *
 * @author yangQiang
 */

@RequestMapping(value = "h5/opinion")
@Controller("h5-opinion-controller")
public class OpinionController {
    // 初始化日志处理类
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    /**
     * 舆论引爆 入口
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String opinion(@RequestParam("sessionToken")String sessionToken, HttpServletResponse response){
        logger.info("调用[舆论引爆入口 /h5/opinion] sessionToken = " + sessionToken);

        return "index";
    }

}
