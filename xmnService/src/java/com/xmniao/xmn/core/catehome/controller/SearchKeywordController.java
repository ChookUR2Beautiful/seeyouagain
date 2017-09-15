package com.xmniao.xmn.core.catehome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.catehome.request.SearchKeywordsRequest;
import com.xmniao.xmn.core.catehome.service.SearchKeywordsService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.common.Response;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: SearchKeywordController    
* @Description:关键词搜索查询  
* @author: liuzhihao   
* @date: 2017年2月21日 下午6:16:14
 */
@Controller
@RequestMapping("home")
public class SearchKeywordController {
	
	//验证数据
	@Autowired
	private Validator validator;
	
	@Autowired
	private SearchKeywordsService searchService;

	@RequestMapping(value="/search/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object directOrder(SearchKeywordsRequest searchKeywordsRequest){
		//验证数据
		List<ConstraintViolation> result = validator.validate(searchKeywordsRequest);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		return searchService.searchSellersByKeywords(searchKeywordsRequest);
	}
}
