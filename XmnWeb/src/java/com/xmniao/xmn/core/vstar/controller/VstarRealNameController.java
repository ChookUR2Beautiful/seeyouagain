/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.vstar.entity.TVstarEnroll;
import com.xmniao.xmn.core.vstar.entity.TVstarPlayerSetting;
import com.xmniao.xmn.core.vstar.service.TVstarEnrollService;
import com.xmniao.xmn.core.vstar.service.TVstarPlayerSettingService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：VstarRealNameController
 * 
 * 类描述： 新时尚大赛实名审核Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-1 下午6:25:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping(value = "VstarRealName/manage")
public class VstarRealNameController extends BaseController {
	
	/**
	 * 注入新时尚大赛报名服务
	 */
	@Autowired
	private TVstarEnrollService starEnrollService;
	
	@Autowired
	private TVstarPlayerSettingService playerSettingService;
	
	
	/**
	 * 
	 * 方法描述：跳转到报名审核管理页面 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "vstar/vstarRealNameManage";
	}
	
	/**
	 * 
	 * 方法描述：加载实名审核列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TVstarEnroll vstarEnroll) {
		Pageable<TVstarEnroll> pageable=new Pageable<TVstarEnroll>(vstarEnroll);
		Object json =null;
		try {
			List<TVstarEnroll> list = starEnrollService.getListRealInfo(vstarEnroll);
			long count = starEnrollService.count(vstarEnroll);
			pageable.setContent(list);
			pageable.setTotal(count);
			json = JSON.toJSON(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 
	 * 方法描述：跳转到修改报名信息页面
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1下午3:01:05
	 * @return
	 */
	@RequestMapping(value="update/init")
	public String updateInit(TVstarEnroll vstarEnroll,Model model){
		model.addAttribute("vstarEnroll", vstarEnroll);
		return "vstar/enrollEdit";
	}
	
	/**
	 * 
	 * 方法描述：更新报名审核状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-13下午12:03:48 <br/>
	 * @param liveRecord
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TVstarEnroll vstarEnroll){
		Resultable result=new Resultable();
		try {
			Integer update = starEnrollService.updateInfo(vstarEnroll);
			if(update>0){
				result.setSuccess(true);
				result.setMsg("操作成功!");
			}else{
				result.setSuccess(true);
				result.setMsg("操作失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("更新报名审核状态："+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：批量更新实名审核状态<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:11:30 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="updateRealNameBatch")
	@ResponseBody
	public Object updateRealNameBatch(@RequestParam("ids") String ids,@RequestParam("status") String status){
		Resultable result=new Resultable();
		result=starEnrollService.updateRealNameBatch(ids,status);
		
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到审核设置页面
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1下午3:01:05
	 * @return
	 */
	@RequestMapping(value="auditSet/init")
	public String auditSetInit(TVstarPlayerSetting vstarSetting,Model model){
		TVstarPlayerSetting vstarSettingInfo = playerSettingService.getFirstObject();
		model.addAttribute("vstarSettingInfo", vstarSettingInfo);
		return "vstar/auditSetEdit";
	}
	
	/**
	 * 
	 * 方法描述：更新新时尚大赛配置信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-13下午12:03:48 <br/>
	 * @param liveRecord
	 * @return
	 */
	@RequestMapping(value="auditSet/add")
	@ResponseBody
	public Resultable auditSetAdd(TVstarPlayerSetting vstarSetting){
		Resultable result=new Resultable();
		try {
			playerSettingService.add(vstarSetting);
			result.setSuccess(true);
			result.setMsg("操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("更新报名审核状态："+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：更新新时尚大赛配置信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-13下午12:03:48 <br/>
	 * @param liveRecord
	 * @return
	 */
	@RequestMapping(value="auditSet/update")
	@ResponseBody
	public Resultable auditSetUpdate(TVstarPlayerSetting vstarSetting){
		Resultable result=new Resultable();
		try {
			Integer update = playerSettingService.update(vstarSetting);
			if(update>0){
				result.setSuccess(true);
				result.setMsg("操作成功!");
			}else{
				result.setSuccess(false);
				result.setMsg("操作失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("更新报名审核状态："+e.getMessage(), e);
		}
		return result;
	}

}
