package com.xmniao.xmn.core.businessman.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.businessman.entity.TDebitcardOrder;
import com.xmniao.xmn.core.businessman.service.ExchangeCardService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 兑换专享卡记录
 * @author Administrator
 *
 */
@RequestLogging(name="兑换专享卡记录")
@Controller
@RequestMapping(value = "businessman/exchangeCard")
public class ExchangeCardController extends BaseController {
	
	@Autowired
	private ExchangeCardService exchangeCardService;
	
	
	/**
	 * 初始化兑换专享卡
	 */
	@RequestMapping(value = "init")
	public Object init() {
		log.info("ExchangeCardController--> init");
		ModelAndView modelAndView = new ModelAndView("businessman/exchangeCard/exchangeCardList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TDebitcardOrder debitcardOrder){
		Pageable<TDebitcardOrder> pageable = new Pageable<TDebitcardOrder>(debitcardOrder);
		pageable = exchangeCardService.geTDebitcardOrderInfoList(debitcardOrder);
		this.log.info("ExchangeCardController-->list pageable=" + pageable);
		
		return pageable;
		
	}
	
	
	@RequestMapping(value = "/export")
	public void export(TDebitcardOrder debitcardOrder, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		log.info("兑换专享卡列表导出getList："+debitcardOrder);
		debitcardOrder.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", exchangeCardService.getdebitcardOrderDataList(debitcardOrder));
		doExport(request, response, "businessman/exchangeCard.xls", params);
	}
	
}
