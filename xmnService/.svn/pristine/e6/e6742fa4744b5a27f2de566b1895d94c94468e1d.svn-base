package com.xmniao.xmn.core.api.controller.seller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmniao.xmn.core.common.request.seller.DebitcardH5Request;

/**
*      
* 类名称：BannerH5页面跳转
* 类描述：专享卡BannerH5页面
* 创建人：wdh   
* 创建时间：2016年11月14日 上午10:40:34     
*
 */
@Controller
@RequestMapping("sellerCard")
public class DebitBannerApi {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(DebitBannerApi.class);
		
	@RequestMapping(value="/debitBanner")
	public String list(DebitcardH5Request request,Model model){
		    model.addAttribute("type", request.getType());
			if (request.getType()==0 || request.getType()==2) {//BannerH5
				return "debitCard/cardExplain";
			}else if (request.getType()==1){ //说明H5
				return "debitCard/birdExplain";
			}
			log.info("专享卡，type必传字段错误！");
			return "live/error";
			
	}

	

	

}
