package com.xmniao.xmn.core.live_anchor.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TLiveFloatAdvertise;
import com.xmniao.xmn.core.live_anchor.service.TLiveFloatAdvertiseService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 悬浮广告管理
 * @author Administrator
 *
 */
@RequestLogging(name="悬浮广告管理")
@Controller
@RequestMapping(value = "liveFloatAdvertise/manage")
public class LiveFloatAdvertiseController extends BaseController {
	
	@Autowired
	private TLiveFloatAdvertiseService liveFloatAdvertiseService;
	
	
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/liveFloatAdvertise/liveFloatAdvertiseManage";
	}
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TLiveFloatAdvertise liveFloatAdvertise){
		Pageable<TLiveFloatAdvertise> pageable = new Pageable<TLiveFloatAdvertise>(liveFloatAdvertise);
		pageable = liveFloatAdvertiseService.getLiveFloatAdvertiseInfoList(liveFloatAdvertise);
		this.log.info("SpecialTopicController-->list pageable=" + pageable);
		
		return pageable;
	}
	
	

	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true, tokenName="liveFloatAdvertiseToken")
	public ModelAndView addInit() {
		// @RequestParam(value = "liveType", required = false
		ModelAndView modelAndView = new ModelAndView("live_anchor/liveFloatAdvertise/editLiveFloatAdvertise");
		modelAndView.addObject("isType", "add");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="add")
	@ResponseBody
	private Object add(TLiveFloatAdvertise liveFloatAdvertise){
		log.info("liveFloatAdvertiseController-->add-------liveFloatAdvertise="+liveFloatAdvertise);
		try {
			Integer id = liveFloatAdvertise.getId();
			if(id==null){
				log.info("[执行修改套餐方法]id="+id);
				liveFloatAdvertiseService.saveAddActivity(liveFloatAdvertise);
			}
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value = "update/init")
	public String updateInit(TLiveFloatAdvertise liveFloatAdvertise, Model model) {
		try {
			if (liveFloatAdvertise != null) {
				Integer id = liveFloatAdvertise.getId();
				model.addAttribute("isType", "update");
				model.addAttribute("liveFloatAdvertiseInfo", liveFloatAdvertiseService.getLiveFloatAdvertiseInfo(id));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "live_anchor/liveFloatAdvertise/editLiveFloatAdvertise";
	}

	@RequestMapping(value = { "update" })
	@ResponseBody
	public Resultable update(TLiveFloatAdvertise liveFloatAdvertise) {
		Resultable result = new Resultable();
		try {
			int count = 0;
			if (liveFloatAdvertise != null) {
				count = liveFloatAdvertiseService.saveUpdateActivity(liveFloatAdvertise);
			}
			if (count > 0) {
				result.setMsg("更新数据成功!");
				result.setSuccess(true);
			} else {
				throw new Exception("更新数据出错");
			}
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	@RequestMapping("/beachOnLine/updateOptionStatus")
	@ResponseBody
	public Object updateOptionStatus(@RequestParam("ids") String ids, @RequestParam(value="state")Integer state){
		log.info("批量修改商家套餐状态statusOption："+ids);
		Resultable resultable=null;
		try {
			int count = 0;
			if(StringUtils.isNotBlank(ids)){
//				Object[] objects = StringUtils.paresToArray(floatAdvert.getIds(), ",");
				count= liveFloatAdvertiseService.updateStatusOption(ids, state);
			}

			log.info("更新成功 共计["+ count + "]条记录");
			resultable = new Resultable(true,"更新数据成功");
		} catch (Exception e) {
			log.error("修改失败",e);
			resultable = new Resultable(false,"更新数据成失败");
		}
		return resultable;
	}
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete( @RequestParam("ids") String ids){
		Resultable result=new Resultable();
		try {
			int count = 0;
			if(StringUtils.isNotBlank(ids)){
				count = liveFloatAdvertiseService.deleteById(ids);
			}
			if(count>0){
				result.setMsg("删除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	
}
