package com.xmniao.xmn.core.api.controller.kscloud;


import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.kscloud.request.KSCloudConfRequest;
import com.xmniao.xmn.core.kscloud.request.KSCloudHBRequest;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */
@Controller
@RequestMapping("/live/kscloud")
public class KSCloudConfAPi implements BaseVControlInf {

    private static final Logger log = LoggerFactory.getLogger(KSCloudConfAPi.class);

    @Autowired
    private Validator validator;
    @Autowired
    private KSCloudService ksCloudService;

    // 心跳
    @ResponseBody
    @RequestMapping(value = "/liveConf", method = {RequestMethod.POST, RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    public Object streamStart(KSCloudConfRequest request) {
        //
        //日志
        log.info("KSCloudConfRequest data : " + request.toString());
        //验证
        List<ConstraintViolation> result = validator.validate(request);
        if (result!=null && result.size()>0) {
            log.info("数据有问题："+result.get(0).getMessage());
            return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
        }
        return versionControl(request.getApiversion(), request);
    }


    public Object versionOne(Object obj){
        KSCloudConfRequest request = (KSCloudConfRequest) obj;
        return ksCloudService.liveConf(request);
    }

    @Override
    public Object versionControl(int v, Object object) {
        switch(v){
            case 1:
                return versionOne(object);
            default :
                return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
        }
    }


}
