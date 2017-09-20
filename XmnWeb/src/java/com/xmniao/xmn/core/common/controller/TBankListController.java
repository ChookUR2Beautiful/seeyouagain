package com.xmniao.xmn.core.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.common.entity.TBank;
import com.xmniao.xmn.core.common.service.TBankService;
@Controller
@RequestMapping(value = "common/tbanks")
public class TBankListController extends BaseController {

	@Autowired
	private TBankService tBankService;
    
	
	/**
	 * @author dong'jietao 
	 * @date 2015年5月11日 上午9:20:41
	 * @TODO 获取银行下拉列表信息
	 * @return
	 */
	@RequestMapping(value = "/getTBanks")
	@ResponseBody
	public Object getTBanks(String abbrev){
		return tBankService.getObjects(abbrev);
	}
}
