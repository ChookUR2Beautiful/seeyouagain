package com.xmniao.xmn.core.system_settings.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.system_settings.entity.Category;
import com.xmniao.xmn.core.system_settings.service.CategoryService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@RequestLogging(name="分类管理")
@Controller
@RequestMapping("system_settings/category")
public class CategoryController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("requestInit", "system_settings/category/init/list");
		mv.setViewName("system_settings/category/categoryList");
		return mv;
	}
	
	@RequestMapping("init/list")
	public ModelAndView initList(Category category){
		ModelAndView mv = new ModelAndView();
		List<Category> parentCategorys =null;
		Map<Integer, List<Category>> map =null;
		try{
			mv.addObject("page", category.getPage());
			parentCategorys  = categoryService.getParentList(category);
			map = categoryService.getSubList(parentCategorys);
		
			
			mv.addObject("total", categoryService.count(category));
			mv.setViewName("system_settings/category/categoryTable");
		}catch(Exception e){
			this.log.error("分类模块获取列表失败", e);
		}
		mv.addObject("parent", parentCategorys);
		mv.addObject("submap", map);
		return mv;
	}
	
	@RequestMapping("add/init")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("action", "system_settings/category/add.jhtml");
		mv.addObject("list", categoryService.getParent());
		mv.setViewName("system_settings/category/categoryModel");
		return mv;
	}
	
	@RequestLogging(name="新增分类")
	@RequestMapping("add")
	@ResponseBody
	public Object add(Category category){
		Resultable result=null;
		String str = "";
		try{
			category.setSdate(new Date());
			if(category.getOrderNum()==null){
				category.setOrderNum(0);
			}
			categoryService.add(category);
			result =  new Resultable(true, "操作成功");
		}catch(Exception e){
			this.log.error("添加分类失败", e);
			result =  new Resultable(false, "操作失败");
		}
		finally{
			String word = category.getTradename();
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			//添加到日志记录表
			String[] s={"分类",str,"新增"};
			categoryService.fireLoginEvent(s,result.getSuccess()?1:0);
		}
		return result;
	}
	
	
	@RequestMapping("update/init")
	public ModelAndView updateInit(@RequestParam("id") String id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("action", "system_settings/category/update.jhtml");
		mv.addObject("category", categoryService.getObject(Long.parseLong(id)));
		mv.setViewName("system_settings/category/categoryModel");
		return mv;
	}
	
	@RequestLogging(name="修改分类")
	@RequestMapping("update")
	@ResponseBody
	public Object update(Category category){
		Resultable result=null;
		try{
			categoryService.update(category);
			result =  new Resultable(true, "操作成功");
		}catch(Exception e){
			this.log.error("更新分类失败", e);
			result =  new Resultable(false, "操作失败");
		}finally{
			//添加到日志记录表
			String[] s={"分类编号",String.valueOf(category.getTid()),"修改","修改"};
			categoryService.fireLoginEvent(s,result.getSuccess()?1:0);
		}
		return result;
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="删除分类")
	@RequestMapping(value = "delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		String[] s={"分类编号",id,"删除","删除"};
		try {
			categoryService.deleteById(Long.parseLong(id));
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("分类删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			//写入日志记录表
			categoryService.fireLoginEvent(s,resultable.getSuccess()?1:0);
		}
		return resultable;
	}

}
