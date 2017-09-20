/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.entity.DMaterial;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttr;
import com.xmniao.xmn.core.cloud_design.service.DMaterialAttrService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialService;
import com.xmniao.xmn.core.common.service.CkeditorUpdateService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MaterialController
 * 
 * 类描述： 物料Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-22 下午8:57:30 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name = "物料管理")
@Controller
@RequestMapping(value = "materials/manage")
public class DMaterialController extends BaseController {
	
	
	/**
	 * 注入物料服务
	 */
	@Autowired
	private DMaterialService materialService;
	
	
	/**
	 * 商品物料规格服务
	 */
	@Autowired
	private DMaterialAttrService materialAttrService;
	
	/**
	 * 注入ckeditService服务
	 */
	@Autowired
	private CkeditorUpdateService ckeditorUpdateService;
	
	
	/**
	 * 
	 * 方法描述：跳转到物料管理列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "cloud_design/materialManage";
	}
	
	/**
	 * 
	 * 方法描述：加载物料列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(DMaterial material) {
		Pageable<DMaterial> pageable=new Pageable<DMaterial>(material);
		Object json =null;
		try {
			List<DMaterial> list = materialService.getList(material);
			long count = materialService.count(material);
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
	 * 方法描述：跳转到新增物料页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午3:19:38 <br/>
	 * @return
	 */
	@RequestMapping(value = "add/init")
	public String addInit(Model model){
		model.addAttribute("isType", "add");
		return "cloud_design/materialEdit";
	}
	
	/**
	 * 
	 * 方法描述：新增物料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-25上午10:41:35 <br/>
	 * @param material
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(DMaterial material){
		Resultable result=new Resultable();
		try {
			result=materialService.saveInfo(material);
			JSON.toJSONString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑物料页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-25下午3:41:17 <br/>
	 * @param material
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView upateInit(DMaterial material,ModelAndView modelAndView){
		DMaterial materialInfo=materialService.setMaterialInfo(material);
		modelAndView.addObject("materialInfo", materialInfo);
		modelAndView.setViewName("cloud_design/materialEdit");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：更新物料信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-25下午6:33:29 <br/>
	 * @param material
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(DMaterial material){
		Resultable result = new Resultable();
		result=materialService.updateInfo(material);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：富文本编辑器文件上传 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6下午9:33:21 <br/>
	 * @param filedata
	 * @param request
	 * @param response
	 */
	@RequestMapping(value ={"add/ckeditorUpload","update/ckeditorUpload"} , method = { RequestMethod.POST })
	public void uploadFile3(@RequestParam("upload") MultipartFile filedata,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("文件上传Controller3");
		ckeditorUpdateService.ckeditorUpdate(filedata, request, response);
	}
	
	/**
	 * 
	 * 方法描述：批量更新商品上架状态<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:11:30 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="updateBatch")
	@ResponseBody
	public Object updateBatch(@RequestParam("ids") String ids,@RequestParam("status") String status){
		Resultable result=new Resultable();
		result=materialService.updateBatch(ids,status);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：获取商品物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-28上午11:54:22 <br/>
	 * @param materialAttr
	 * @return
	 */
	@RequestMapping(value="getShoppingMaterialAttrs")
	@ResponseBody
	public Object getShoppingMaterialAttrs(DMaterialAttr materialAttr){
		//TODO
		Resultable result=new Resultable();
		try {
			List<DMaterialAttr> list = materialAttrService.getList(materialAttr);
			result.setData(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return result;
	}
	
}
