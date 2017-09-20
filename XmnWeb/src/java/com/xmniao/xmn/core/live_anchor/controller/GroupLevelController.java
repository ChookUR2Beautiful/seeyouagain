/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.GroupLevel;
import com.xmniao.xmn.core.live_anchor.service.GroupLevelService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：GroupLevelController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年4月25日 上午10:27:15
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping("/groupLevel")
public class GroupLevelController extends BaseController {

	@Autowired
	private GroupLevelService groupLevelService;

	@RequestMapping("/init")
	public Object init() {
		return "live_anchor/groupLevelList";
	}

	@RequestMapping("/update/init")
	public Object edit(Long id,Model model) {
		if(id!=null){
			GroupLevel object = groupLevelService.getObject(id);
			model.addAttribute("groupLevel", object);
		}
		return "live_anchor/groupLevelEdit";
	}

	@RequestMapping(value="/getLastLevel",method=RequestMethod.POST)
	@ResponseBody
	public Object getLastLevel(GroupLevel groupLevel) {
		Pageable<GroupLevel> pageable = new Pageable<>(groupLevel);
		pageable.setContent(groupLevelService.getLastLevels(groupLevel));
		return pageable;
	}

	@RequestMapping("/add")
	@ResponseBody
	public Object add(GroupLevel groupLevel) {
		try {
			Long id = groupLevel.getId();
			if(groupLevel.getLastLevelId()==null){
				groupLevel.setLevel(1);
			}else{
				GroupLevel object = groupLevelService.getObject(groupLevel.getLastLevelId());
				groupLevel.setLevel(object.getLevel()+1);
			}
			if (id != null) {
				groupLevel.setUpdateTime(new Date());
				groupLevelService.updateLevel(groupLevel);
			} else {
				groupLevel.setCreateTime(new Date());
				groupLevel.setUpdateTime(new Date());
				groupLevelService.addLevel(groupLevel);
			}
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error("添加等级时出现异常", e);
			return Resultable.defeat("操作失败");
		}
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object initList(GroupLevel groupLevel){
		Pageable<GroupLevel> pageable = new Pageable<>(groupLevel);
	  	pageable.setContent(groupLevelService.getList(groupLevel));
		pageable.setTotal(groupLevelService.count(groupLevel));
		return pageable;
	}

}
