package com.xmniao.xmn.core.api.controller.kscloud;

import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/2.
 */
@Controller
@RequestMapping("/live/kscloud/callback")
public class KSCloudCallbackAPi {


    // 推流开始的回调URL
    @ResponseBody
    @RequestMapping(value = "/stream-start", method = {RequestMethod.POST, RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    public Object streamStart(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> param = request.getParameterMap();

        MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "推流开始回调成功");
        return mapResponse;
    }


    // 推流结束的回调URL
    @ResponseBody
    @RequestMapping(value = "/stream-end", method = {RequestMethod.POST, RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    public Object streamEnd(HttpServletRequest request, HttpServletResponse response) {
        MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "推流结束回调成功");
        return mapResponse;
    }

    // 截图回调的URL
    @ResponseBody
    @RequestMapping(value = "/screenshot", method = {RequestMethod.POST, RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    public Object screenshot(HttpServletRequest request, HttpServletResponse response) {
        MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "截图回调成功");
        return mapResponse;
    }



}
