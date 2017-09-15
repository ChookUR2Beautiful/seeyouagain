package com.xmniao.xmn.core.api.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明会h5
* 类名称：CaptionApi   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年12月9日 上午9:40:29
 */
@Controller
@RequestMapping("caption")
public class CaptionApi {
		
		/**
		 * 关于我们
		 * @author xiaoxiong
		 * @date 2016年12月9日
		 */
		@RequestMapping("/about")
		public String about(){
			
			return "caption/about";
		}
		
		/**
		 * 用户协议
		 * @author xiaoxiong
		 * @date 2016年12月9日
		 */
		@RequestMapping("/agreement")
		public String agreement(){
			
			return "caption/agreement";
		}
		
		/**
		 * 优惠卷使用说明
		 * @author xiaoxiong
		 * @date 2016年12月9日
		 */
		@RequestMapping("/explain")
		public String explain(){
			
			return "caption/coupon_explain";
		}
		
		/**
		 * 提现规则
		 * @author xiaoxiong
		 * @date 2016年12月9日
		 */
		@RequestMapping("/rule")
		public String rule(){
			
			return "caption/rule";
		}

}
