package com.xmniao.xmn.core.live_anchor.controller;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TLiveSalary;
import com.xmniao.xmn.core.live_anchor.entity.TLiveSalaryFail;
import com.xmniao.xmn.core.live_anchor.service.TLiveSalaryService;
import com.xmniao.xmn.core.util.PageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveSalaryController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年4月5日 下午5:26:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("/liveSalary")
public class LiveSalaryController extends BaseController{
	
	@Autowired
	private TLiveSalaryService liveSalaryService;
	
	@RequestMapping("/init")
	public Object init(){
		return "live_anchor/liveSalaryList";
	}

	@RequestMapping("init/list")
	@ResponseBody
	public Object getList(TLiveSalary liveSalary){
		Pageable<TLiveSalary> pageable = new Pageable<>(liveSalary);
		pageable.setContent(liveSalaryService.getLiveSalarys(liveSalary));
		pageable.setTotal(liveSalaryService.count(liveSalary));
		return pageable;
	}
	
	@RequestMapping("/update/init")
	@ResponseBody
	public Object updateInit(Integer id){
		ModelAndView modelAndView = new ModelAndView("live_anchor/liveSalaryEdit");
		TLiveSalary tLiveSalary = new TLiveSalary();
		tLiveSalary.setId(id);
		List<TLiveSalary> liveSalarys = liveSalaryService.getLiveSalarys(tLiveSalary);
		if(liveSalarys.isEmpty()||liveSalarys.size()>1){
			throw new RuntimeException("查出集合数为"+liveSalarys.size());
		}
		modelAndView.addObject("liveSalary",liveSalarys.get(0));
		return modelAndView;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object update(TLiveSalary liveSalary){
		if(liveSalary.getId()==null){
			return new Resultable(false, "id不能为空");
		}
		int result=liveSalaryService.updateSalary(liveSalary);
		if(result>0){
			return new Resultable(true,"操作成功");
		}else{
			return new Resultable(false, "操作失败");
		}
	}
	
	@RequestMapping(value="/confirmSalary",method=RequestMethod.POST)
	@ResponseBody         
	public Object confirmSalary(@RequestParam(required=true)Integer id){
		try {
			liveSalaryService.confirmSalary(id);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error("确认主播工资时出现异常",e);
			return Resultable.defeat("操作失败");
		}
	}
	
	@RequestMapping(value="/export",method=RequestMethod.POST)
	@ResponseBody  
	public void Export(TLiveSalary liveSalary,HttpServletRequest request,HttpServletResponse response){
		liveSalary.setLimit(PageConstant.PAGE_LIMIT_NO);
		List<TLiveSalary> liveSalarys = liveSalaryService.getLiveSalarys(liveSalary);
		Map<String,Object> params = new HashMap<>();
		params.put("list", liveSalarys);
		doExport(request,response,"live_anchor/liveSalary.xls",params);
	}
	
	@RequestMapping(value="fail/init")
	public Object failInit(){
		return "live_anchor/salaryFailList";
	}
	
	/**
	 * 
	 * 方法描述：获得失败列表
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月11日下午5:07:54 <br/>
	 * @return
	 */
	@SuppressWarnings(value={"unchecked","rawtypes"})
	@RequestMapping(value="/getFailList")
	@ResponseBody 
	public Object getFailList(){
		List<TLiveSalaryFail> fails= liveSalaryService.getFailList();
		return new Resultable(true,"操作成功",fails);
	}
	
	
	@RequestMapping(value="/salaryAgain")
	@ResponseBody 
	public Object salaryAgain(){
		boolean state=liveSalaryService.salaryAgain();
		if(state){
			return new Resultable(true,"生成成功");
			
		}else{
			return new Resultable(true,"生成失败,我知道了有主播的鸟蛋打赏获取失败，请前往->直播频道->打赏失败记录,操作归还后重新生成工资条");
		}
	}
	
	
	@RequestMapping(value="init/list/manual")
	@ResponseBody 
	public Object manualSalary() throws Exception{
		Map<String,String> map=liveSalaryService.manualSalary();
		return new Resultable("0".equals(map.get("state")), map.get("msg"));
	}
	
}
