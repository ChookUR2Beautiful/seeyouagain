package com.xmn.saas.controller.api.v1.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.entity.shop.Trade;
import com.xmn.saas.service.shop.TradeServcie;

/**
 * 
*      
* 类名称：TradeController   
* 类描述：   商家经营分类
* 创建人：xiaoxiong   
* 创建时间：2016年9月26日 下午8:03:08   
* 修改人：xiaoxiong   
* 修改时间：2016年9月26日 下午8:03:08   
* 修改备注：   
* @version    
*
 */

@Controller(value ="api-v1-trade-controller" )
@RequestMapping("/api/v1/shop/trade")
public class TradeController {

	@Autowired
	private TradeServcie tradeService;
	
	@ResponseBody
	@RequestMapping(value = "/list")
	public void list(Integer pid) throws Exception{
		if(pid==null){
			new Response(ResponseCode.DATAERR,"分类父ID不能为空",null).write();
			return;
		}
		//查询商家经营分类
		List<Trade> tradeList=tradeService.list(pid);
		
		if(tradeList==null){
			new Response(ResponseCode.FAILURE,"失败").write();
			return;
		}
		
		new Response(ResponseCode.SUCCESS, "成功", tradeList).write(new HashMap<Class<?>, String[]>(){{
				put(Trade.class, new String[] { "id", "name" });
		}});
		
	}
	
	/**
	 * 
	 * @Description: 查询所有分类
	 * @author xiaoxiong
	 * @date 2016年10月13日
	 */
	@ResponseBody
	@RequestMapping(value = "/all")
	public void all() throws Exception{
		
		//查询商家经营分类
		List<Trade> tradeList=tradeService.list(0);
		
		if(tradeList==null){
			new Response(ResponseCode.FAILURE,"失败").write();
			return;
		}
		List<Trade> result=queryGenre(tradeList);
		
		new Response(ResponseCode.SUCCESS, "成功", result).write(new HashMap<Class<?>, String[]>(){{
				put(Trade.class, new String[] { "id", "name","genres"});
		}});
		
	}

	private List<Trade> queryGenre(List<Trade> tradeList) {
		
		List<Trade> result=new ArrayList<>();
		
		for(Trade trade:tradeList){
			
			List<Trade> genre=tradeService.list(trade.getId());
			trade.setGenres(genre);
			result.add(trade);
		}
		return result;
	}
	
}
