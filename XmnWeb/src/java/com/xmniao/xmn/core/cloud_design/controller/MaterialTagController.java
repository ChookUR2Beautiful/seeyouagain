/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryTagRelation;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialTag;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCategoryTagRelationService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialTagService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MaterialTagController
 * 
 * 类描述： 物料标签Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-9-28 下午2:38:02
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name = "标签管理")
@Controller
@RequestMapping(value = "materialTag/manage")
public class MaterialTagController extends BaseController {

	/**
	 * 注入物料标签服务
	 */
	@Autowired
	private DMaterialTagService materialTagService;
	
	/**
	 * 注入物料分类关联标签服务
	 */
	@Autowired
	private DMaterialCategoryTagRelationService tagRelationService;

	/**
	 * 
	 * 方法描述：跳转到标签管理列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "cloud_design/materialTagManage";
	}
	
	/**
	 * 
	 * 方法描述：加载标签管理列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(DMaterialTag materialTag) {
		Pageable<DMaterialTag> pageable=new Pageable<DMaterialTag>(materialTag);
		List<DMaterialTag> list = materialTagService.getList(materialTag);
		long count = materialTagService.count(materialTag);
		pageable.setContent(list);
		pageable.setTotal(count);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：跳转到新增标签页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:05 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(){
		return "cloud_design/materialTagEdit";
	}
	
	/**
	 * 
	 * 方法描述：新增标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:29 <br/>
	 * @param materialTag
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(DMaterialTag materialTag){
		Resultable result = new Resultable();
		try {
			materialTagService.add(materialTag);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
		}finally{
			String[] data={"雏鸟云设计标签号",materialTag.getId().toString(),"添加","添加"};
			materialTagService.fireLoginEvent(data, result.getSuccess()==true?1:0);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：删除标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:28:30 <br/>
	 * @return
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public Resultable delete(DMaterialTag materialTag){
		Resultable result = new Resultable();
		try {
			Long id = materialTag.getId();
			int count = materialTagService.deleteById(id);
			if(count>0){
				result.setSuccess(true);
				result.setMsg("删除成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("删除失败!");
			this.log.error("删除标签失败："+e.getMessage(), e);
		}finally{
			String[] data={"雏鸟云设计标签编号",materialTag.getId().toString(),"删除","删除"};
			materialTagService.fireLoginEvent(data, result.getSuccess()==true?1:0);
		}
		
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑页面<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:26:09 <br/>
	 * @param materialTag
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="update/init")
	public String updateInit(DMaterialTag materialTag,Model model){
		DMaterialTag materialTagInfo = materialTagService.selectById(materialTag.getId());
		model.addAttribute("materialTagInfo", materialTagInfo);
		return "cloud_design/materialTagEdit";
	}
	
	/**
	 * 
	 * 方法描述：编辑标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:26:27 <br/>
	 * @param materialTag
	 * @return
	 */
	@RequestLogging(name="雏鸟云设计标签管理")
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(DMaterialTag materialTag){
		Resultable result=new Resultable();
		try {
			int count = materialTagService.update(materialTag);
			if(count>0){
				result.setSuccess(true);
				result.setMsg("更新成功！");
			}else{
				result.setSuccess(false);
				result.setMsg("更新失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("更新失败！");
		}finally{
			//写入 日志记录表
			String[] data={"雏鸟云设计标签编号",materialTag.getId().toString(),"修改","修改"};
			materialTagService.fireLoginEvent(data,result.getSuccess()==true?1:0);
		}
		
		return result;
		
	}
	
	/**
	 * 
	 * 方法描述：获取所有标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午4:30:28 <br/>
	 * @param productFailingSerial
	 * @return
	 */
	@RequestMapping(value = "getTags",method=RequestMethod.POST)
	@ResponseBody
	public Object getTags(DMaterialTag categoryTag) {
		Pageable<DMaterialTag> pageable = new Pageable<DMaterialTag>(categoryTag);
		List<DMaterialTag> categoryTags = materialTagService.getList(categoryTag);
		pageable.setContent(categoryTags);
		return pageable;
	}
	
	
	/**
	 * 
	 * 方法描述：获取关联标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午4:30:28 <br/>
	 * @param productFailingSerial
	 * @return
	 */
	@RequestMapping(value = "getRelationTags",method=RequestMethod.POST)
	@ResponseBody
	public Object getRelationTags(DMaterialCategoryTagRelation categoryTagRel) {
		Pageable<DMaterialCategoryTagRelation> pageable = new Pageable<DMaterialCategoryTagRelation>(categoryTagRel);
		String filterVal = categoryTagRel.getFilterVal();
		if(StringUtils.isNotBlank(filterVal)){
			categoryTagRel.setCategoryId(Long.valueOf(filterVal));
		}
		List<DMaterialCategoryTagRelation> categoryTags = tagRelationService.getList(categoryTagRel);
		pageable.setContent(categoryTags);
		return pageable;
	}

}
