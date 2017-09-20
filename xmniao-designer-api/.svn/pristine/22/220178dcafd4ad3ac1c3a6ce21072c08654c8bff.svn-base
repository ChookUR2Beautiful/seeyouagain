package com.xmn.designer.controller.api.v1.customize;

import com.xmn.designer.base.AbstractController;
import com.xmn.designer.controller.api.v1.customize.vo.CustomizeSubmitRequest;
import com.xmn.designer.entity.account.User;
import com.xmn.designer.entity.customize.OrderMaterialCustomize;
import com.xmn.designer.entity.material.MaterialCategoryAttr;
import com.xmn.designer.entity.order.Order;
import com.xmn.designer.exception.CustomException;
import com.xmn.designer.service.base.RedisService;
import com.xmn.designer.service.customize.CustomizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create 2016/11/14
 *
 * @author yangQiang
 */

@Controller("api-v1-custom-controller")
@RequestMapping(value = "api/v1/customize")
public class CustomizeController extends AbstractController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomizeService customService;

    @Autowired
    private RedisService redisService;

    /**
     * 获取定制物料的可选定制规格参数
     */
    @ResponseBody
    @RequestMapping(value = "spec_list",method = RequestMethod.POST)
    public void standardList() throws IOException {
        logger.info("[调用获取定制物料可选规格参数列表 api/v1/customize/spec_list ]");

        List<MaterialCategoryAttr> result;
        try {
            // 查询定制订单规格列表
            result = customService.getCustomStandard();
            // 返回响应参数
            success(result,new HashMap<Class<?>, String[]>(){{
                put(MaterialCategoryAttr.class,new String[]{
                        "id","customizable","multipleChoice","name","vals"});
            }});
        } catch (Exception e) {
            logger.error("调用[获取定制物料可选规格参数列表 api/v1/customize/spec_list ] 出现异常!",e);
            failure();
            return;
        }
    }

    /**
     * 提交定制物料订单
     */
    @ResponseBody
    @RequestMapping(value = "submit",method = RequestMethod.POST)
    public void submit(@Valid CustomizeSubmitRequest request, BindingResult result) throws IOException {

        logger.info("调用[提交定制物料订单 api/v1/customize/submit]  参数 : " + request.toString());
        // 封装订单信息
        OrderMaterialCustomize customizeOrder;
        Map<String,Object> response = null;
        try {
            customizeOrder = request.convertToOrderMaterialCustomize();
            Order order = request.convertToOrder();
            // 获取用户
            String sessionToken = this.getToken();
            User designerUser = redisService.getDesignerUser(sessionToken);

            // 调用服务类生成订单
            response = customService.submitCustomize(designerUser, customizeOrder, order);
            success(response, "yyyy-MM-dd");
        }catch (CustomException e){
            failure(e);
        }catch (Exception e) {
            logger.error("调用[提交定制物料订单 api/v1/customize/submit] 出现异常",e);
            failure();
            return;
        }

    }

    /**
     * 确认设计接口
     */
    @ResponseBody
    @RequestMapping(value = "confirm",method = RequestMethod.POST)
    public void confirm(@RequestParam("orderNo")String orderNo) throws IOException {
        logger.info("调用[确认定制设计接口] orderNo:"+orderNo);
        try {

            User designerUser = redisService.getDesignerUser(this.getToken());
            customService.confirmDesign(orderNo);
            success();

        } catch (CustomException e){
            failure(e);
        } catch (Exception e) {
            logger.error("调用[确认定制设计接口]出现异常",e);
            failure();
        }

    }


}
