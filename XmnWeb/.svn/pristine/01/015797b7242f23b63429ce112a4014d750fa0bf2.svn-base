package com.xmniao.xmn.core.live_anchor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackage;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackageIssueRef;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackagePic;
import com.xmniao.xmn.core.live_anchor.service.TSellerPackageService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 连锁店
 * @author Administrator
 *
 */
@RequestLogging(name="套餐管理")
@Controller
@RequestMapping(value = "sellerPackage/manage")
public class SellerPackageController extends BaseController {

	
	@Autowired
	private TSellerPackageService sellerPackageService;
	
	
	private String sellerPackagePath="live_anchor/sellerPackage/";
	
	/**
	 * 初始化专题信息
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/sellerPackage/sellerPackageList";
	}
	
	
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSellerPackage sellerPackage){
		Pageable<TSellerPackage> pageable = new Pageable<TSellerPackage>(sellerPackage);
		
		pageable = sellerPackageService.getSellerPackageInfoList(sellerPackage);
		this.log.info("SpecialTopicController-->list pageable=" + pageable);
		
		return pageable;
	}
	
	
	/**
	 * 
	 * addInit(专题添加初始化)
	 * 
	 * @author：caiyl
	 */
	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true, tokenName="sellerPackageToken")
	public ModelAndView addInit() {

		ModelAndView modelAndView = new ModelAndView("live_anchor/sellerPackage/editSellerPackage");
		modelAndView.addObject("isType", "add");

		return modelAndView;
	}
	
	@RequestMapping(value="add")
	@ResponseBody
	private Object add(TSellerPackage sellerPackage){
		Resultable result = new Resultable();
		
		log.info("SellerPackageController-->add-------sellerPackage="+sellerPackage);
		try {
			Integer id = sellerPackage.getId();
			if(id!=null){
				log.info("[执行修改套餐方法]id="+id);
			}else{
				log.info("[执行修改套餐方法]id="+id);
				//1.保存套餐主信息; 2.保存套餐图片;3.保存抵用券 ; 4.保存套餐券关联关系
				sellerPackageService.saveActivity(sellerPackage);
			}
			result.setMsg("操作成功!");
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setMsg("删除失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete(TSellerPackage sellerPackage) {
		Resultable result = new Resultable();
		
		try {
			int count = sellerPackageService.deleteById(sellerPackage.getId());
			if (count > 0) {
				result.setMsg("删除成功!");
				result.setSuccess(true);
			} else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("删除失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	@RequestMapping(value = "update/init")
	public ModelAndView updateInit(TSellerPackage sellerPackage, ModelAndView model) {
		
		try {
			Integer recordId = sellerPackage.getId();
			if (recordId != null){
				TSellerPackage sellerPackageInfo = sellerPackageService.getObject(recordId.longValue());  // 1.套餐信息
				//套餐图片
				List<TSellerPackagePic> sellerPackagePicList = sellerPackageService.getSellerPackagePicInfoList(recordId);
				if (sellerPackagePicList != null&& sellerPackagePicList.size() > 0)
					sellerPackageInfo.setSellerPackagePicList(sellerPackagePicList);

				//套餐抵用券
				List<TSellerPackageIssueRef> sellerPackageIssueRef = sellerPackageService.getFansCouponIssueRefInfoList(recordId);
				if (sellerPackageIssueRef != null && sellerPackageIssueRef.size() > 0)
					sellerPackageInfo.setVoucherList(sellerPackageIssueRef);
				
				model.addObject("myPackageInfo", sellerPackageInfo);
			}
			model.addObject("isType", "update");
			model.setViewName("live_anchor/sellerPackage/editSellerPackage");
			
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
		}
		
		return model;
	}

	@RequestMapping(value = { "update", "setAdvance" })
	@ResponseBody
	public Resultable update(TSellerPackage sellerPackage) {
		Resultable result = new Resultable();
		
		try {
			sellerPackageService.updateActivity(sellerPackage);
			result.setMsg("更新数据成功!");
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	
	
//***************************查看详情开始********************************
	
	@RequestMapping(value="list/viewDetail")
	public ModelAndView sellerPackageDetail(TSellerPackage sellerPackage){
		ModelAndView modelAndView = new ModelAndView(sellerPackagePath+"sellerPackageDetail");
		
		try{
			TSellerPackage sellerPackageInfo = new TSellerPackage();
			Integer pid = sellerPackage.getId();  //套餐编号
			if (pid != null) { // 套餐图片
				// 1.套餐信息
				sellerPackageInfo = sellerPackageService.getObject(pid.longValue());
				//套餐图片信息
				List<TSellerPackagePic> sellerPackagePicList = sellerPackageService.getSellerPackagePicInfoList(pid);
				if (sellerPackagePicList.size() > 0)
					sellerPackageInfo.setSellerPackagePicList(sellerPackagePicList);
			}
			
			modelAndView.addObject("sellerPackageInfo", sellerPackageInfo);
			modelAndView.addObject("isType","view");
			
		}catch(Exception e){
			this.log.error("页面初始化异常", e);
		}
		
		return modelAndView;
	}

	
//	*****************************状态操作*****************************************

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年2月28日下午4:54:45 <br/>
	 * @param sellerPackage
	 * @param flag
	 * @param state
	 * @return
	 */
	@RequestMapping("/beachOnLine/updateStatusOption")
	@ResponseBody
	public Object updateStatusOption(TSellerPackage sellerPackage, @RequestParam(value="flag")String flag, @RequestParam(value="state")String state){
		Resultable result= new Resultable();
		try {
			log.info("批量修改商家套餐状态statusOption："+sellerPackage);
			String pids = sellerPackage.getIds();
			if (pids != null && !"".equals(pids)) {
				if ("1".equals(flag)) {// 上架， 下架
					sellerPackage.setStatus(Integer.valueOf(state));
					sellerPackage.setHighlyRecommended(0);  //取消重点推荐
				} else if ("2".equals(flag)) { // 推荐
					sellerPackage.setHighlyRecommended(Integer.valueOf(state));
				}
				sellerPackageService.updateStatusOption(sellerPackage);
				result.setMsg("更新数据成功!");
				result.setSuccess(true);
				
				log.info("更新成功");
			} else {
				result.setMsg("更新失败!");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			this.log.error("页面初始化异常", e);
			result.setMsg("更新失败!");
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	
	
}
