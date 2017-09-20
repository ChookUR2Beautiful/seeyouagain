package com.xmniao.xmn.core.marketingmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.common.service.CkeditorUpdateService;
import com.xmniao.xmn.core.marketingmanagement.dao.BillBargainDao;
import com.xmniao.xmn.core.marketingmanagement.entity.BargainProduct;
import com.xmniao.xmn.core.marketingmanagement.entity.TBargainPrice;
import com.xmniao.xmn.core.marketingmanagement.entity.TBillBargain;
import com.xmniao.xmn.core.marketingmanagement.service.BargainProductService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 特价爆品订单
 * @author cao'yingde
 *
 */
@RequestLogging(name="特价爆品订单管理")
@Controller
@RequestMapping(value = "marketingManagement/bargainProduct")
public class BargainProductController extends BaseController {

	@Autowired
	private BargainProductService bargainProductService;
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private BillBargainDao billBargainDao;
	
	@Autowired
	private CkeditorUpdateService ckeditorUpdateService;
	 
	/**
	 * 初始特价爆品订单信息
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "marketingManagement/bargainProductList";
	}

	/**
	 * 获取特价爆品订单列表
	 * cao'yingde
	 * @param bargainProduct
	 * @return
	 */

	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(BargainProduct bargainProduct){
		Pageable<BargainProduct> pageable = new Pageable<BargainProduct>(bargainProduct);
		pageable.setContent(bargainProductService.getList(bargainProduct));
		pageable.setTotal(bargainProductService.count(bargainProduct));
		return pageable;
	}
	
	/**
	 * 添加特价爆品订单
	 * addInit
	 * @author：cao'yingde
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
//		ModelAndView modelAndView = new ModelAndView("marketingManagement/editBargainProduct");
		ModelAndView modelAndView = new ModelAndView("marketingManagement/bargainProductEdit");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	/**
	 * 添加特价爆品订单
	 * add
	 * @author：cao'yingde
	 */
	@RequestLogging(name="添加特价爆品订单")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(BargainProduct bargainProduct, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			bargainProductService.addOrUpdteBargainProduct(bargainProduct, request);
			resultable = new Resultable(true, "操作成功！");
			this.log.info("添加成功!");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			bargainProductService.fireLoginEvent(new String[]{"特价爆品编号",bargainProduct.getBpid().toString(),"添加特价爆品","添加"}, resultable.getSuccess()?1:0);
		}
		return resultable;
	}
	
	/**
	 * 初始化特价爆品订单编辑页面
	 */
	@RequestMapping(value = "update/init")
	@RequestToken(createToken=true,tokenName="bargainProductToken")
	public ModelAndView updateInit(ModelAndView model,
			@RequestParam("bpid") Integer bpid) {
		BargainProduct bargainProduct = bargainProductService.getObject(bpid.longValue());
		List<TBargainPrice> bargainPrice = (List<TBargainPrice>) bargainProductService.getBargainProduct((long)bargainProduct.getBpid());
		bargainProduct.setBargainPrice(bargainPrice);
		
		model.addObject("bargainProduct", bargainProduct);
//		model.setViewName("marketingManagement/editBargainProduct");
		model.setViewName("marketingManagement/bargainProductEdit");
		return model;
	}
	/**
	 * 修改商家信息
	 * 
	 * @param bargainProduct
	 * cao'yingde
	 * @return
	 */
	@RequestLogging(name="修改特价爆品订单信息")
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "update")
	public Object updateSeller(BargainProduct bargainProduct, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			bargainProductService.addOrUpdteBargainProduct(bargainProduct, request);
			resultable = new Resultable(true, "操作成功！");
			this.log.info("修改成功!");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			bargainProductService.fireLoginEvent(new String[]{"特价爆品编号",bargainProduct.getBpid().toString(),"修改特价爆品","修改"}, resultable.getSuccess()?1:0);
			return resultable;
		}
	}
	
	/**
	 *  根据订单ID查询爆品订单
	 */
	@RequestMapping(value = "billBargain/billBargainInit")
	public ModelAndView bargainProductByBidInit(@RequestParam("bid")Long bid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/billBargainList");
		modelAndView.addObject("bid", bid);
		modelAndView.addObject("requestInit", "billBargain/billBargainInit/list");
		return modelAndView;
	}
	
	@RequestMapping(value = "billBargain/billBargainInit/list")
	@ResponseBody
	public Object bargainProductByBidList(TBillBargain tBillBargain){
		Pageable<TBillBargain> pageable = new Pageable<TBillBargain>(tBillBargain);
		pageable.setContent(billBargainDao.getList(tBillBargain));
		pageable.setTotal(billBargainDao.count(tBillBargain));
		return pageable;
	}
	
	/**
	 * 获取商家列表
	 * 
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "InitSeller")
	@ResponseBody
	public Object getSellerIdAndSellerName(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = sellerService.getSellerIdAndSellerName(seller);
		return pageable;
	}
	
	
	/**
	 * 根据商家ID获取该商家已经被添加为爆品商家的次数
	 * @param sellerid
	 * @return
	 */
	@RequestMapping(value = "/init/getSellerCount")
	@ResponseBody
	public Object getSellerCount(TSeller seller){
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable.setTotal(bargainProductService.getSellerCount(seller));
		return pageable;
	}
	
	@RequestMapping(value = {"add/ckeditorUpload","update/ckeditorUpload"}, method = {RequestMethod.POST})
	public void uploadFile(@RequestParam("upload") MultipartFile filedata,HttpServletRequest request, HttpServletResponse response) {
		ckeditorUpdateService.ckeditorUpdate(filedata,request,response);
	}
	
	/**
	 * @Description: 特价爆品审核通过（上架）
	 * @Param:bpid
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年7月1日下午2:45:46
	 */
	@RequestLogging(name="特价爆品审核通过（上架）")
	@RequestMapping(value = "/updateStatus/putaway")
	@ResponseBody
	public Object putaway(HttpServletRequest request, String bpid) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
        	//调用service层
    		bargainProductService.putaway(bpid);
    		resultMap.put("success",true);
    		resultMap.put("msg","审核通过");
		} catch (Exception e) {
			resultMap.put("success",false);
			resultMap.put("msg", "审核失败");
		}
        //返回json数据
      	return JSON.toJSON(resultMap);
	}
	
	/**
	 * @Description: 特价爆品审核不通过
	 * @Param:bpid
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年7月1日下午2:45:46
	 */
	@RequestLogging(name="特价爆品审核不通过")
	@RequestMapping(value = "/updateStatus/nopass")
	@ResponseBody
	public Object nopass(HttpServletRequest request, String bpid) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
        	//调用service层
    		bargainProductService.nopass(bpid);
    		resultMap.put("success",true);
    		resultMap.put("msg","审核完成");
		} catch (Exception e) {
			resultMap.put("success",false);
			resultMap.put("msg", "审核失败");
		}
        //返回json数据
      	return JSON.toJSON(resultMap);
	}
	
	/**
	 * 根据商家的sellerid查询商家的折扣信息 
	 */
	@RequestMapping(value = "/init/getSellerBySellerid")
	@ResponseBody
	public Object getSellerBySellerid(Integer sellerid){
		TSeller sellerBySellerid = bargainProductService.getSellerBySellerid(sellerid);
		return sellerBySellerid;
	}
}
