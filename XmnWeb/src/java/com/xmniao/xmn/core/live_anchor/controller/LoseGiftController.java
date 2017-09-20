/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.LoseGift;
import com.xmniao.xmn.core.live_anchor.service.TLiveGivedGiftService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LoseGiftController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年5月28日 上午10:04:50 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("/loseGift")
public class LoseGiftController extends BaseController{
	
	@Autowired
	private TLiveGivedGiftService liveGivedGiftService;
	
	@RequestMapping("/init")
	public Object init(){
		return "live_anchor/loseGiftList";
	}
	
	
	@RequestMapping("init/list")
	@ResponseBody
	public Object list(LoseGift gift){
		Pageable<LoseGift> pageable = new Pageable<>(gift);
		if(!liveGivedGiftService.setLiver(gift)){
			return pageable;
		}
		pageable.setContent(liveGivedGiftService.getLostGift(gift));
		pageable.setTotal(liveGivedGiftService.countLostGift(gift));
		return pageable;
	}
	
	
	@RequestMapping("/giveBack")
	@ResponseBody
	public Object giveBack(@RequestParam(required=true)String ids){
		if(StringUtils.isBlank(ids)){
			return Resultable.defeat("参数有误");
		}
		List<String> list=Arrays.asList(ids.split(","));
		return liveGivedGiftService.giveBack(list);
	}

}
