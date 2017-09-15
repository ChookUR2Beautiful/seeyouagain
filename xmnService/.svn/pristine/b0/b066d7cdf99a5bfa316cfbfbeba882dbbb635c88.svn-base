package com.xmniao.xmn.core.market.controller.common;

import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.entity.FreshLabel;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yang.qiang on 2017/3/7.
 */
@Controller("api-v1-common-controller")
@RequestMapping( "/api/v1/market/common" )
public class CommonController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonService commonService;


    /**
     * @name        4.15.1 商品标签列表
     * @description 获取积分商品标签列表
     * @url         /api/v1/market/common/label_list
     * @method      POST
     */
    @ResponseBody
    @RequestMapping(value = "label_list",method = RequestMethod.POST)
    public void labelList(HttpServletResponse httpResponse) throws Exception {

        logger.info("调用[4.15.1 商品标签列表 /api/v1/market/common/label_list POST]接口");
        try {
            List<FreshLabel> labelList = commonService.queryLabels();


            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("labelList", labelList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(FreshLabel.class, new String[]{
                        "id",           //type:Integer  ID
                        "labelName",    //type:String   标签名称
                        "picUrl",       //type:String   pic路径
                        "type",         //type:Integer  类型: 1-活动, 2-新品, 3-热销, 4-精选
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.15.1 商品标签列表]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }



}
