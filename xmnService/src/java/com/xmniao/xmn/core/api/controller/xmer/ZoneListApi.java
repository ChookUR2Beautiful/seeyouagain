package com.xmniao.xmn.core.api.controller.xmer;

import com.xmniao.xmn.core.base.*;
import com.xmniao.xmn.core.catehome.service.XmnHomeService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.ZoneListRequest;
import com.xmniao.xmn.core.xmer.service.XmerService;
import com.xmniao.xmn.core.base.BaseVControlInf;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Controller
public class ZoneListApi implements BaseVControlInf {


    //日志
    private Logger log = Logger.getLogger(ZoneListApi.class);

    @Autowired
    private XmerService xmerService;

    //注入验证
    @Autowired
    private Validator validator;

    @RequestMapping(value="/zoneList",method= RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object xmerInfo(ZoneListRequest zoneListRequest){
        //验证
        List<ConstraintViolation> result = validator.validate(zoneListRequest);
        if(result.size() >0 && result != null){
            log.info("提交的数据有问题"+result.get(0).getMessage());
            return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题，请检查");
        }
        return versionControl(zoneListRequest.getApiversion(), zoneListRequest);
    }


    @Override
    public Object versionControl(int v, Object object) {
        switch(v){
            case 1: return versionControlOne(object);
            default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
        }
    }
    private Object versionControlOne(Object object) {
        ZoneListRequest zoneListRequest = (ZoneListRequest) object;
        return xmerService.queryZonesByArea(zoneListRequest);
    }

}
