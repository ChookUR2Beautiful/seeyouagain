package com.xmniao.xmn.core.api.controller.catehome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.catehome.service.CateHomeService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.catehome.KeyWordRequest;
import com.xmniao.xmn.core.xmer.service.SellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* 类名称：SearchConnectListApi   
* 类描述：  搜索联想
* 创建人：xiaoxiong   
* 创建时间：2016年11月24日 上午9:58:38
 */

@Controller
@RequestMapping("/search/connect")
public class SearchConnectListApi implements BaseVControlInf{
	
	
	private final Logger log = Logger.getLogger(SearchConnectListApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private CateHomeService cateHomeService;
	
	
	@RequestMapping("/list")
	@ResponseBody
	public Object list(KeyWordRequest request){
		log.info("KeyWordRequest data:" + request.toString());
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
		try {
		List<String> keyWordList = new ArrayList<>();	
			
		KeyWordRequest request =(KeyWordRequest)object;
		//关键字处理	
		String keyWord =keyWord(request.getKeyWord());
		//查询参数
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("keyWord", keyWord);
		params.put("page", request.getPage());
		params.put("pageSize", request.getPageSize());
		params.put("areaId", request.getAreaId());
		keyWordList = cateHomeService.searchConnectList(params);
		Map<Object,Object> result = new HashMap<>();
		result.put("list", keyWordList);
		
		MapResponse mapResponse =  new MapResponse(ResponseCode.SUCCESS, "成功");
		mapResponse.setResponse(result);
		return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"错误");
	}
	/**
	 * 关键字处理
	 * @author xiaoxiong
	 * @date 2016年11月22日
	 */
	private static String keyWord(String keyWord) {
		String word="%";
		try {
			for(int i = 0; i<keyWord.length();i++){
				word+=keyWord.substring(i,i+1)+"%";
			}
		} catch (Exception e) {
			e.printStackTrace();
			word = "";
		}
		return word;
	}

}
