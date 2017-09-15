package com.xmniao.xmn.core.api.controller.live;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveRecommendSpecialRequest;
import com.xmniao.xmn.core.common.request.live.LiveShareRequest;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;
import com.xmniao.xmn.core.live.service.LiveRecommendSpecialService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 直播的好看推荐
 * Created by Administrator on 2017/3/29.
 */
@Controller
@RequestMapping("/live")
public class LiveRecommendSpecialApi implements BaseVControlInf {

    /**
     * 日志
     */
    private final Logger log = Logger.getLogger(LiveRecommendSpecialApi.class);

    /**
     * 验证
     */
    @Autowired
    private Validator validator;

    @Autowired
    private LiveRecommendSpecialService liveRecommendSpecialService;

    @RequestMapping(value = "/recommend/special" ,method= RequestMethod.POST,produces={"application/json;charset=utf-8"})
    @ResponseBody
    public Object queryLiveRecordInfo(LiveRecommendSpecialRequest liveRecommendSpecialRequest){
        //验证
        List<ConstraintViolation> result = validator.validate(liveRecommendSpecialRequest);
        if (result!=null && result.size()>0) {
            log.info("数据有问题："+result.get(0).getMessage());
            return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
        }
        return versionControl(liveRecommendSpecialRequest.getApiversion(), liveRecommendSpecialRequest);
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

    private Object versionOne(Object object) {
        LiveRecommendSpecialRequest liveRecommendSpecialRequest = (LiveRecommendSpecialRequest) object;
        return liveRecommendSpecialService.findRecommendActivityPic(liveRecommendSpecialRequest);
    }

}
