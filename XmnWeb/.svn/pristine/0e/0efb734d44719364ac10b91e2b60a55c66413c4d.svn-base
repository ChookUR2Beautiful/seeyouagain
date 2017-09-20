package com.xmniao.xmn.core.businessman.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.AllianceAccount;
import com.xmniao.xmn.core.businessman.service.AllianceAccountService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@RequestLogging(name="商家管理 >> 联盟店管理")
@Controller
@RequestMapping("businessman/allianceShop/allianceAccount")
public class AllianceAccountController extends BaseController{

	@Autowired
	private AllianceAccountService allianceAccountService;
	
	/**
	 * 
	 * allianceAccountInit(联盟店帐号初始化页面)
	 * 
	 * @author：chen'heng
	 */
	@RequestMapping(value = "init")
	public String allianceAccountInit() {
		return "businessman/allianceShop/allianceShopAccountList";
	}

	/**
	 * 
	 * allianceAccountList(联盟店帐号列表数据初始化)
	 * 
	 * @author：chen'heng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object allianceAccountList(AllianceAccount allianceAccount) {
		Pageable<AllianceAccount> pageable = new Pageable<AllianceAccount>(allianceAccount);
		pageable.setContent(allianceAccountService.getList(allianceAccount));
		pageable.setTotal(allianceAccountService.count(allianceAccount));
		return pageable;
	}
	
	
	/**
	 * 
	 * allianceAccountAddInit(添加联盟店帐号初始化)
	 * 
	 * @author：chen'heng
	 */
	@RequestMapping(value = "update/init")
	public ModelAndView allianceAccountAddInit(@RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView();
		try{
			modelAndView.addObject("account", allianceAccountService.getObject(Long.valueOf(aid)));
		}catch(Exception e){
			log.error("联盟店帐号列表初始化异常", e);
		} finally{
			modelAndView.setViewName("businessman/allianceShop/allianceShopAccountModel");
		}
		return modelAndView;
	}
	
	/**
	 * 
	 * update(修改联盟商店帐号信息)
	 * 
	 * @author：chen'heng
	 */
	@RequestLogging(name="修改联盟店帐号信息")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(AllianceAccount allianceAccount) {
		Resultable resultable = null;
		
		try {
			allianceAccount.setUdate(new Date());
			// 修改联盟商店帐号信息
			allianceAccountService.update(allianceAccount);
			resultable = new Resultable(true, "修改成功！");
			this.log.info("修改成功!");
		} catch (Exception e) {
			this.log.error("修改联盟商店帐号信息", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"联盟店帐号编号",allianceAccount.getAid().toString(),"修改操作","修改"};
			allianceAccountService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;
	}
	
	/**
	 * 
	 * checkAccount(检查帐号唯一性)
	 * 
	 * @author：chen'heng
	 */
	@RequestMapping(value = "init/checkAccount")
	@ResponseBody
	public Boolean checkAccount(@RequestParam("account")String account) {
		return !(allianceAccountService.checkAccount(account));
	}
	
	
}
