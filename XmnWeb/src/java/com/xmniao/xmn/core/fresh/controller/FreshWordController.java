/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.FreshWord;
import com.xmniao.xmn.core.fresh.service.FreshWordService;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：FreshWordController
 * 
 * 类描述： 关键字搜索
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年1月9日 上午9:41:03
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "fresh/word")
public class FreshWordController extends BaseController {

	@Autowired
	private FreshWordService freshWordService;

	@RequestMapping(value = "list/init")
	@ResponseBody
	private Object ListInit() {
		log.info("FreshWordController-->list/init");
		ModelAndView modelAndView = new ModelAndView("fresh/wordList");
		return modelAndView;
	}

	@RequestMapping(value = "list")
	@ResponseBody
	private Object List(FreshWord freshWord) {
		log.info("FreshWordController-->list------freshWord="+freshWord);
		if (StringUtils.isNotBlank(freshWord.getWord())) {
			freshWord.setLikeType(1);
		}
		Pageable<FreshWord> pageable = new Pageable<FreshWord>(freshWord);
		List<FreshWord> list = freshWordService.getPageList(freshWord);
		List<FreshWord> ten = freshWordService.getMaxTenWord(freshWord);
		list.addAll(ten);
		pageable.setContent(list);
		pageable.setTotal(freshWordService.count(freshWord));
		return pageable;
	}

	@RequestMapping(value = "edit/init")
	@ResponseBody
	private Object editInit(Long id, Integer type) {
		log.info("FreshWordController-->edit/init------id="+id);
		ModelAndView modelAndView = new ModelAndView();
		if (type!=null && type== 3) {
			modelAndView.setViewName("fresh/wordDefaultEdit");
				FreshWord freshWord = new FreshWord();
				freshWord.setType(type);
				FreshWord freshWord2 = freshWordService.getObject(freshWord);
				if(freshWord2!=null){
					modelAndView.addObject("freshWord", freshWord2);
				}
		} else {
			modelAndView.setViewName("fresh/wordEdit");
			if (id != null) {
				FreshWord freshWord = freshWordService.getObject(id);
				modelAndView.addObject("freshWord", freshWord);
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "add")
	@ResponseBody
	private Object add(FreshWord freshWord) {
		log.info("FreshWordController-->add------freshWord="+freshWord);
		try {
			Long id = freshWord.getId();
			if (id != null) {
				// 修改
				freshWord.setUpdateTime(new Date());
				freshWordService.update(freshWord);
			} else {
				// 添加
				freshWord.setCreateTime(new Date());
				freshWord.setUpdateTime(new Date());
				freshWord.setRecord(0);
				freshWordService.add(freshWord);
			}
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(true, "操作失败");
	}
	
	@RequestMapping(value = "delete")
	@ResponseBody
	private Object delete(@RequestParam(value="id",required=true)Long id){
		log.info("FreshWordController-->delete------id="+id);
		try {
			freshWordService.delete(id);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(true, "操作失败");
	}

}
