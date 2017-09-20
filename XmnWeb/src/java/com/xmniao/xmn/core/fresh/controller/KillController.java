/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.Kill;
import com.xmniao.xmn.core.fresh.service.KillService;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：IndianaController
 * 
 * 类描述： 竞拍
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月17日 上午11:56:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("fresh/kill")
public class KillController extends BaseController{
	
	@Autowired
	private KillService killService;
	
	@RequestMapping("/init")
	public Object init(){
		return "fresh/killList";
	}
	
	@RequestMapping("add/init")
	public Object addInit(){
		return "fresh/killEdit";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Object list(Kill kill){
		this.log.info("KillController-->list kill=" + kill);
		Pageable<Kill> pageable = new Pageable<>(kill);
		pageable.setContent(killService.getList(kill));
		pageable.setTotal(killService.count(kill));
		return pageable;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object save(Kill kill){
		log.info("添加秒杀活动  :"+kill);
		try {
			Long id = kill.getId();
			if(id!=null){
				log.info("[执行修改秒杀活动方法]id="+id);
				killService.updateKill(kill.getTransformBean());
			}else{
				log.info("[执行添加秒杀活动方法]");
				Kill transformBean = kill.getTransformBean();
				killService.save(transformBean);
			}
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false, "操作失败");
		}
	}
	
	@RequestMapping("edit/init")
	public Object editInit(Long id) throws Exception{
		ModelAndView modelAndView = new ModelAndView("fresh/killEdit");
		Kill kill=killService.getKill(id);
		modelAndView.addObject("activity",kill);
		return modelAndView;
	}
	
	@RequestMapping("/end")
	@ResponseBody
	public Object end(Long id){
		log.info("终止活动 id:"+id);
		try {
			killService.end(id);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false, "操作失败");
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id){
		log.info("删除秒杀活动 id:"+id);
		try {
			killService.deleteActivity(id);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false, "操作失败");
		}
	}
	
	
	@RequestMapping("/getKillChoose")
	@ResponseBody
	public Object getKillChoose(Kill kill){
		Pageable<Kill> pageable = new Pageable<>(kill);
		pageable.setContent(killService.getKillChoose(kill));
		return pageable;
	}
	
}
