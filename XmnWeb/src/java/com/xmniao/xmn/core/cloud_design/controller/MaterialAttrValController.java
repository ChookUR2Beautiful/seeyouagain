/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttr;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrVal;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCategoryAttrValService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MaterialAttrValController
 * 
 * 类描述： 物料规格细项Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-9-28 下午2:38:02
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name = "规格细项管理")
@Controller
@RequestMapping(value = "materialAttrVal/manage")
public class MaterialAttrValController extends BaseController {

	
	/**
	 * 注入物料规格细项服务
	 */
	@Autowired
	private DMaterialCategoryAttrValService categoryAttrValService;

	
	/**
	 * 
	 * 方法描述：跳转到新增规格细项页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:05 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(){
		return "cloud_design/materialAttrValEdit";
	}
	
	/**
	 * 
	 * 方法描述：新增标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:29 <br/>
	 * @param categoryAttr
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(DMaterialCategoryAttr categoryAttr){
		Resultable result = new Resultable();
		try {
//			categoryAttrService.add(categoryAttr);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
		}finally{
//			String[] data={"雏鸟云设计标签号",categoryAttr.getId().toString(),"添加","添加"};
//			categoryAttrService.fireLoginEvent(data, result.getSuccess()==true?1:0);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：根据物料规格ID获取物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:29 <br/>
	 * @param categoryAttr
	 * @return
	 */
	@RequestMapping(value="getCategoryAttrVals")
	@ResponseBody
	public Object getCategoryAttrVals(DMaterialCategoryAttrVal categoryAttrVal){
		Pageable<DMaterialCategoryAttrVal> pageable = new Pageable<DMaterialCategoryAttrVal>(categoryAttrVal);
		//filterVal 为categoryAttrId的值
		String filterVal = StringUtils.isBlank(categoryAttrVal.getFilterVal()) ? "-1":categoryAttrVal.getFilterVal();
		categoryAttrVal.setCategoryAttrId(Long.valueOf(filterVal));
		List<DMaterialCategoryAttrVal> categoryAttrValList = categoryAttrValService.getList(categoryAttrVal);
		pageable.setContent(categoryAttrValList);
		return pageable;
	}
	

}
