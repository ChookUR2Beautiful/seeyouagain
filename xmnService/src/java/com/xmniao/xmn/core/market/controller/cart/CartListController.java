package com.xmniao.xmn.core.market.controller.cart;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.controller.cart.vo.CartListRequest;
import com.xmniao.xmn.core.market.exception.CustomException;



/**
 * 购物车列表
 * 
 * @author zhouxiaojian
 *
 */
@RequestMapping( "/api/v1/cart" )
@Controller
public class CartListController implements BaseVControlInf {
    
    //验证数据
    @Autowired
    private Validator validator;
    
    private MapResponse response = null;
    
    @RequestMapping(value="/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object list(CartListRequest  request){
        //验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if(result != null && !result.isEmpty()){
            return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
        }
        return versionControl(request.getApiversion(),request);
    }
    

    public Object versionOne(Object object) {
        try {
            return new BaseResponse(ResponseCode.SUCCESS,"请求成功");
        } catch (CustomException e) {
            e.printStackTrace();
            return new BaseResponse(ResponseCode.FAILURE,"请求失败");
        }
    }

    @Override
    public Object versionControl(int v, Object object) {
        switch (v) {
            case 1:
                return versionOne(object);
            default:
                return new BaseResponse(ResponseCode.ERRORAPIV, "版本号不正确,请重新下载客户端");
        }
    }

}
