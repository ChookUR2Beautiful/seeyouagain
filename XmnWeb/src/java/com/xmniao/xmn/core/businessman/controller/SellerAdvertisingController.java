package com.xmniao.xmn.core.businessman.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.common.service.AdvertisingService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@RequestLogging(name="商家管理")
@Controller
@RequestMapping(value = "businessman/advertising")
public class SellerAdvertisingController extends BaseController {

	@Autowired
	private AdvertisingService advertisingService;
	
	/**
	 * init(初始化列表页面)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init() {
		return "businessman/sellerAdvertisingList";
	}
	
	/**
	 * list(列表数据初始化)
	 * 
	 * @param advertising
	 * @return
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(TAdvertising advertising) {
		log.info(advertising.getType());
		Pageable<TAdvertising> pageable = new Pageable<TAdvertising>(advertising);
		pageable.setContent(advertisingService.getSellerADList(advertising));
		pageable.setTotal(advertisingService.count(advertising));
		return pageable;
	}
	
	/**
	 * export(导出列表到Excel)
	 * @param advertising
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value="export")
	public void export(TAdvertising advertising, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		advertising.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", advertisingService.getList(advertising));
		doExport(request, response, "common/advertising.xls", params);
	}
	
	/**
	 * addInit(添加初始化)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("businessman/editAdvertising");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	/**
	 * add(添加)
	 * 
	 * @param advertising
	 * @return
	 */
	@RequestMapping(value="/add")
	@RequestLogging(name="商家广告轮番图添加")
	@ResponseBody
	public Object add(TAdvertising advertising){
		Resultable resultable = null;
		try{
			advertisingService.add(advertising);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");

			//写入 日志记录表
			String word = advertising.getContent();
			String str ="";
			if(word.length()<PartnerConstants.WORD_LENGTH){
				str = word;
			}else{
				str = word.substring(PartnerConstants.RESULTNUM_INIT, PartnerConstants.WORD_LENGTH)+"...";
			}
			String[] s = {"商家广告轮番图",str,"新增"};
			advertisingService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);
		}catch(Exception e){
			this.log.error("添加异常"+e);
			resultable = new Resultable(false, "操作失败");
			
			//写入 日志记录表
			String word = advertising.getContent();
			String str = "";
			if (word.length() <= PartnerConstants.WORD_LENGTH){
				str = word;
			}else{
				str = word.substring(PartnerConstants.RESULTNUM_INIT, PartnerConstants.WORD_LENGTH)+"...";
			}
			String[] s={"商家广告轮番图",str,"新增"};
			advertisingService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);
		}
		
		
		return resultable;
	}
	
	/**
	 * updateInit(修改初始化)
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/update/init")
	public ModelAndView updateInit(@RequestParam("id") String id){
		ModelAndView modelAndView = new ModelAndView("businessman/editAdvertising");
		modelAndView.addObject("isType", "update");
		try {
			TAdvertising advertising = advertisingService.getObject(new Long(id));
			modelAndView.addObject("advertising", advertising);
		} catch (Exception e) {
			this.log.error("修改初始化异常",e);
		}
		return modelAndView;
	}
	
	/**
	 * update(修改)
	 * @param advertising
	 * @return
	 */
	@RequestMapping("/update")
	@RequestLogging(name="商家广告轮播图修改")
	@ResponseBody
	public Object update(TAdvertising advertising){
		Resultable resultable = null;	
		try{
			if(advertising.getIsall()==1){
				advertising.setProvince("");
				advertising.setCity("");
				advertising.setArea("");
			}
			advertisingService.update(advertising);
			this.log.info("修改成功");
			resultable = new Resultable(true,"操作成功");
			//添加到日志记录表
			String[] s={"商家广告轮番图编号",String.valueOf(advertising.getId()),"修改","修改"};
			advertisingService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);
		}catch(Exception e){
			this.log.error("修改异常"+e);
			resultable = new Resultable(false,"操作失败");
			//添加到日志记录表
			String[] s={"商家广告轮番图编号",String.valueOf(advertising.getId()),"修改","修改"};
			advertisingService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);
		}
		return resultable;
	}

	/**
	 * delete(删除)
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@RequestLogging(name="商家广告轮番图删除")
	@ResponseBody
	public Object delete(@RequestParam("id") String id){
		Resultable resultable = null;
		try{
			Integer resultNum = advertisingService.delete(id.split(","));
			if(resultNum>PartnerConstants.RESULTNUM){
				resultable = new Resultable(true,"操作成功");
				//写入日志记录表
				String[] s={"商家广告轮番图编号",id,"删除","删除"};
				advertisingService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);
			}
		}catch(Exception e){
			this.log.error("删除异常",e);
			resultable = new Resultable(false,"操作失败");
			//写入日志记录表
			String[] s={"商家广告轮番图编号",id,"删除","删除"};
			advertisingService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);
		}
		return resultable;
	}

}
