package com.xmniao.xmn.core.user_terminal.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.user_terminal.entity.TBrandSeller;
import com.xmniao.xmn.core.user_terminal.service.BrandSellerService;
import com.xmniao.xmn.core.util.NumberUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@RequestLogging(name = "品牌店管理")
@Controller
@RequestMapping(value = "user_terminal/brandSeller")
public class BrandSellerController extends BaseController {

	@Autowired
	private BrandSellerService brandSellerService;

	@Autowired
	private SellerService sellerService;

	@RequestMapping(value = "/init")
	public String init() {
		return "businessman/brandSeller/brandSellerList";
	}

	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(TBrandSeller brandSeller) {
		Pageable<TBrandSeller> pageable = new Pageable<TBrandSeller>(
				brandSeller);
		pageable.setContent(brandSellerService.getList(brandSeller));
		pageable.setTotal(brandSellerService.count(brandSeller));
		return pageable;
	}

	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView(
				"businessman/brandSeller/editBrandSeller");
		modelAndView.addObject("type", "add");
		return modelAndView;
	}

	@RequestLogging(name = "品牌店添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TBrandSeller brandSeller, HttpServletRequest request) {
		Resultable result = null;
		this.log.info(brandSeller);
		try {
			if (brandSeller != null) {
				brandSeller.setCreator(((TUser) request.getSession()
						.getAttribute("currentUs")).getUsername());
				brandSeller.setUpdator(((TUser) request.getSession()
						.getAttribute("currentUs")).getUsername());
				Date currentDate = new Date();
				brandSeller.setDateCreated(currentDate);
				brandSeller.setDateUpdated(currentDate);
				brandSeller.setRebate(new BigDecimal(brandSeller.getRebate())
						.divide(new BigDecimal(100)).doubleValue());
				brandSeller.setAgio(NumberUtil
						.getDoubleDivide100Value(brandSeller.getAgio()));
				this.log.info(brandSeller.getAgio());
				brandSellerService.add(brandSeller);
				result = new Resultable(true, "添加成功！");
				String[] s = { "品牌店", brandSeller.getBrandName(), "新增" };
				brandSellerService.fireLoginEvent(s,
						PartnerConstants.FIRELOGIN_NUMA);
			}
		} catch (Exception e) {
			log.error(e);
			result = new Resultable(false, "添加失败！");
			String[] s = { "品牌店", brandSeller.getBrandName(), "新增" };
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMB);
		}
		return result;
	}

	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(@RequestParam("brandId") Long brandId) {
		ModelAndView modelAndView = new ModelAndView(
				"businessman/brandSeller/editBrandSeller");
		modelAndView.addObject("type", "update");
		TBrandSeller brandSeller = brandSellerService.getObject(brandId);
		if (brandSeller.getArea() != null) {
			brandSeller.setArea(brandSeller.getArea().replace(';', ','));
		}
		modelAndView.addObject("brandSeller", brandSeller);
		return modelAndView;
	}

	@RequestLogging(name = "品牌店更新")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TBrandSeller brandSeller, HttpServletRequest request) {
		Resultable result = null;
		this.log.info(brandSeller);
		try {
			brandSeller.setUpdator(((TUser) request.getSession().getAttribute(
					"currentUs")).getUsername());
			brandSeller.setDateUpdated(new Date());

			if (null == brandSeller.getSort()) {
				brandSeller.setSort(0);
			}
			if (brandSeller.getIsAll() == 1) {
				brandSeller.setArea("");
				brandSeller.setProvince("");
				brandSeller.setCity("");
			}
			brandSeller.setRebate(NumberUtil
					.getDoubleDivide100Value(brandSeller.getRebate()));
			brandSeller.setAgio(NumberUtil.getDoubleDivide100Value(brandSeller
					.getAgio()));
			this.log.info(brandSeller.getAgio());
			brandSellerService.update(brandSeller);
			result = new Resultable(true, "更新成功！");
			String[] s = { "品牌店编号", String.valueOf(brandSeller.getBrandId()),
					"修改", "修改" };
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMA);// 添加到日志记录表
		} catch (Exception e) {
			log.error(e);
			result = new Resultable(false, "更新失败！");
			String[] s = { "品牌店编号", String.valueOf(brandSeller.getBrandId()),
					"修改", "修改" };
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMB);// 添加到日志记录表
		}
		return result;
	}

	@RequestLogging(name = "品牌店删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam("ids") String ids) {
		Resultable result = null;
		try {
			int rows = brandSellerService.deleteByIds(ids);
			if (rows > PartnerConstants.RESULTNUM) {
				log.info("删除成功");
				result = new Resultable(true, "操作成功");
				String[] s = { "品牌店编号", ids, "删除", "删除" };
				brandSellerService.fireLoginEvent(s,
						PartnerConstants.FIRELOGIN_NUMA);// 写入日志记录表
			}
		} catch (Exception e) {
			log.info("删除失败");
			log.error(e);
			result = new Resultable(false, "操作失败");
			String[] s = { "品牌店编号", ids, "删除", "删除" };
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMB);// 写入日志记录表
		}
		return result;
	}

	@RequestMapping(value = "/export")
	public void export(TBrandSeller brandSeller, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		brandSeller.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", brandSellerService.getList(brandSeller));
		doExport(request, response, "businessman/brandSeller.xls", params);
	}

	@RequestLogging(name = "品牌店上线")
	@RequestMapping(value = "/online")
	@ResponseBody
	public Resultable online(TBrandSeller brandSeller) {
		Resultable result = null;
		String[] s = { "品牌店编号", brandSeller.getBrandId().toString(), "上线", "上线" };
		try {
			brandSellerService.online(brandSeller);
			result = new Resultable(true, "操作成功");
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMA);// 写入日志记录表
		} catch (Exception e) {
			e.printStackTrace();
			result = new Resultable(false, "操作失败");
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMB);// 写入日志记录表
		}
		return result;
	}

	@RequestLogging(name = "品牌店下线")
	@RequestMapping(value = "/offline")
	@ResponseBody
	public Resultable offline(TBrandSeller brandSeller) {
		Resultable result = null;
		String[] s = { "品牌店编号", brandSeller.getBrandId().toString(), "下线", "下线" };
		try {
			brandSellerService.offline(brandSeller);
			result = new Resultable(true, "操作成功");
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMA);// 写入日志记录表
		} catch (Exception e) {
			e.printStackTrace();
			result = new Resultable(false, "操作失败");
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMB);// 写入日志记录表
		}
		return result;
	}

	@RequestMapping(value = "/branchSeller/init")
	public String subShopInit(Integer brandId, Model model) {
		model.addAttribute("brandId", brandId);
		return "businessman/brandSeller/branchSellerList";
	}

	@RequestMapping(value = "/branchSeller/init/list")
	@ResponseBody
	public Pageable<TSeller> subShopList(TSeller seller) {
		return sellerService.getBrandSubSeller(seller);
	}

	/**
	 * 添加商家为分店
	 * 
	 * @param sellerid
	 * @return
	 */
	@RequestLogging(name="添加商家为分店")
	@RequestMapping(value = "/branchSeller/add")
	@ResponseBody
	public Resultable addBranchSeller(TSeller seller) {
		Resultable resultable = null;
		String[] s = { "商家编号", seller.getSellerid().toString(), "添加为品牌店分店", "添加为品牌店分店" };
		try {
			sellerService.addBrandSellerForSeller(seller);
			resultable = new Resultable(true, "添加分店成功");
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMA);// 写入日志记录表
		} catch (Exception e) {
			e.printStackTrace();
			resultable = new Resultable(false, "添加分店失败");
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMB);// 写入日志记录表
		}
		return resultable;
	}

	/**
	 * 移除商家为分店
	 * 
	 * @param sellerid
	 * @return
	 */
	@RequestLogging(name="移除商家为分店")
	@RequestMapping(value = "/branchSeller/delete")
	@ResponseBody
	public Resultable deleteBranchSeller(TSeller seller) {
		Resultable resultable = null;
		String[] s = { "商家编号", seller.getSellerid().toString(), "删除为品牌店分店", "删除为品牌店分店" };
		try {
			sellerService.deleteBrandSellerForSeller(seller);
			resultable = new Resultable(true, "移除分店成功");
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMA);// 写入日志记录表
		} catch (Exception e) {
			e.printStackTrace();
			resultable = new Resultable(false, "移除分店失败");
			brandSellerService.fireLoginEvent(s,
					PartnerConstants.FIRELOGIN_NUMB);// 写入日志记录表
		}
		return resultable;
	}
}
