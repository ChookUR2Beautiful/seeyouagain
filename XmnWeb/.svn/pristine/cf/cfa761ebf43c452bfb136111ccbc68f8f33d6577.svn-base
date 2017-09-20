package com.xmniao.xmn.core.live_anchor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TLiveDepositorsTaxes;
import com.xmniao.xmn.core.live_anchor.service.TLiveDepositorsTaxesService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 提现税费管理
 * @author Administrator
 *
 */
@RequestLogging(name="提现税费管理【V客】")
@Controller
@RequestMapping(value = "liveDepositorsTaxes/manage")
public class LiveDepositorsTaxesController extends BaseController {
	
	@Autowired
	private TLiveDepositorsTaxesService liveDepositorsTaxesService;
	
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/depositorsTaxes/liveDepositorsTaxesList";
	}
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TLiveDepositorsTaxes liveDepositorsTaxes){
		Pageable<TLiveDepositorsTaxes> pageable = new Pageable<TLiveDepositorsTaxes>(liveDepositorsTaxes);
		pageable = liveDepositorsTaxesService.getLiveDepositorsTaxesInfoList(liveDepositorsTaxes);
		this.log.info("LiveDepositorsTaxesController-->list pageable=" + pageable);
		
		return pageable;
	}
	

	@RequestMapping(value = { "update" })
	@ResponseBody
	public Resultable update(TLiveDepositorsTaxes liveDepositorsTaxes) {
		Resultable result = new Resultable();
		try {
			liveDepositorsTaxesService.saveUpdateActivity(liveDepositorsTaxes);
			result.setMsg("更新数据成功!");
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	
}
