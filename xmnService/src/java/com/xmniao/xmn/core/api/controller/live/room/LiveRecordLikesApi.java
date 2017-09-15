package com.xmniao.xmn.core.api.controller.live.room;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.seller.SellerCouponListApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;


/**
 * 
* 类名称：LiveRecordLiksApi   
* 类描述：   点赞接口
* 创建人：xiaoxiong   
* 创建时间：2016年12月21日 下午4:41:24
 */

@RequestMapping("/live")
@Controller
public class LiveRecordLikesApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(SellerCouponListApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	
	@RequestMapping("liveRecordLikes")
	@ResponseBody
	public Object liveRecordLiks(IDRequest request){
		log.info("IDRequest data:" + request.toString());
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

	private Object versionControlOne(Object object) {
		IDRequest request = (IDRequest) object;
		try {
			/*点赞累计 reids key*/
			String redisKey =Constant.LIVE_RECORD_LIKS + request.getId();
			/*redis 点赞数量*/
			String likeNums = sessionTokenService.getStringForValue(redisKey)+"";
			if(likeNums.equals("") || likeNums.equals("null")){
				/*如果没有 添加直播记录点赞数*/
				sessionTokenService.setStringForValue(redisKey, "1", 0, null);
			}else{
				likeNums = (Integer.valueOf(likeNums)+1)+"";
				sessionTokenService.setStringForValue(redisKey, likeNums, 0, null);
			}
			return new BaseResponse(ResponseCode.SUCCESS,"成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"点赞失败！");
		
	}

}
