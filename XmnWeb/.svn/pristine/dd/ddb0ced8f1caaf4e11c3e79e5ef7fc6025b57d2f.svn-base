package com.xmniao.xmn.core.mybatisDemo.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.mybatisDemo.entity.MybatisplusDemo;
import com.xmniao.xmn.core.mybatisDemo.service.IMybatisplusDemoService;

/**
 * <p>
 * mybatisplus测试表 前端控制器
 * </p>
 *
 * @author ChenBo
 * @since 2017-09-14
 */
@Controller
@RequestMapping("/mybatisDemo/mybatisplusDemo")
public class MybatisplusDemoController {
	
	@Autowired
	private IMybatisplusDemoService mybatisplusDemoService;
	
	@RequestMapping("/select/{id}")
	@ResponseBody
	public Object select(@PathVariable("id") Integer id){
		MybatisplusDemo selectById = mybatisplusDemoService.selectById(id);
		return selectById;
	}
	
	
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(MybatisplusDemo mybatisplusDemo){
		boolean insert = mybatisplusDemoService.insert(mybatisplusDemo);
		return insert;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(MybatisplusDemo mybatisplusDemo){
		boolean updateById = mybatisplusDemoService.updateById(mybatisplusDemo);
		return updateById;
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Object delete(@PathVariable("id") Integer id){
		boolean deleteById = mybatisplusDemoService.deleteById(id);
		return deleteById;
	}
	
	
}
