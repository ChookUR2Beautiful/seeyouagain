package com.xmniao.xmn.core.api.controller.live.room;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveRoomAdRequest;
import com.xmniao.xmn.core.live.service.LiveRoomAdService;
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
 * 直播间广告
 * Created by Administrator on 2017/5/22.
 */
@RequestMapping("/live")
@Controller
public class LiveRoomAdApi implements BaseVControlInf {

    private final Logger log = Logger.getLogger(LiveRoomAdApi.class);
    @Autowired
    private Validator validator;
    @Autowired
    private LiveRoomAdService liveRoomAdService;

    @RequestMapping(value="/room/adList",method= RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object getLiveRoomAd(LiveRoomAdRequest request) {
        log.info("getLiveRoomAd data:" + request.toString());
        List<ConstraintViolation> result = validator.validate(request);
        if(result.size() >0 && result != null){
            log.info("提交的数据有问题:"+result.get(0).getMessage());
            return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
        }
        return versionControl(request.getApiversion(), request);

    }

    @Override
    public Object versionControl(int v, Object object) {
        switch(v){
            case 1:
                return versionControlOne(object);
            default :
                return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
        }
    }

    public Object versionControlOne(Object object) {
        LiveRoomAdRequest request = (LiveRoomAdRequest) object;
        return liveRoomAdService.getLiveRoomAdTask(request);
    }
}
