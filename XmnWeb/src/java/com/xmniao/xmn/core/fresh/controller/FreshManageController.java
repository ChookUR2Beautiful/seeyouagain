package com.xmniao.xmn.core.fresh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.ResultFile;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.common.service.CkeditorUpdateService;
import com.xmniao.xmn.core.common.service.TSequenceService;
import com.xmniao.xmn.core.fresh.entity.FreshBrand;
import com.xmniao.xmn.core.fresh.entity.FreshType;
import com.xmniao.xmn.core.fresh.entity.PostageTemplate;
import com.xmniao.xmn.core.fresh.entity.ProductDetail;
import com.xmniao.xmn.core.fresh.entity.ProductFailing;
import com.xmniao.xmn.core.fresh.entity.ProductFailingSerial;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;
import com.xmniao.xmn.core.fresh.entity.TSaleGroup;
import com.xmniao.xmn.core.fresh.entity.TSaleProperty;
import com.xmniao.xmn.core.fresh.entity.TSupplier;
import com.xmniao.xmn.core.fresh.service.FreshBrandService;
import com.xmniao.xmn.core.fresh.service.FreshManageService;
import com.xmniao.xmn.core.fresh.service.FreshTypeService;
import com.xmniao.xmn.core.fresh.service.PostageTemplateService;
import com.xmniao.xmn.core.fresh.service.ProductImportService;
import com.xmniao.xmn.core.fresh.service.SupplierService;
import com.xmniao.xmn.core.util.DateEditor;
import com.xmniao.xmn.core.util.FastfdsConstant;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshmanagermentController
 * 
 * 类描述： 生鲜频道生鲜列表控制器
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月5日 上午10:35:39
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "fresh/manage")
public class FreshManageController extends BaseController{
	private Logger log = LoggerFactory.getLogger(FreshManageController.class);

	/**
	 * 注入积分超市-产品管理服务
	 */
	@Autowired
	private FreshManageService freshManagermentService;
	
	/**
	 * 注入积分超市-产品导入服务
	 */
	@Autowired
	private ProductImportService productImportService;

	@Autowired
	private TSequenceService tSequenceService;

	@Autowired
	private CkeditorUpdateService ckeditorUpdateService;

	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private PostageTemplateService postageTemplateService;
	
	@Autowired
	private FreshTypeService freshTypeService;
	
	@Autowired
	private FreshBrandService freshBrandService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/*
	 * 跳转到生产管理列表界面
	 */
	@RequestMapping(value = "")
	public Object freshManage(HttpSession session) {
		
		log.info("info-页面跳转->" + "fresh/freshlist.jsp");
		ModelAndView modelAndView = new ModelAndView("fresh/manage/freshlist");
		ProductInfo productInfo = (ProductInfo) session.getAttribute("productInfo");
		if(productInfo!=null){
			session.removeAttribute("productInfo");
			modelAndView.addObject("productInfo",productInfo);
		}
		FreshType freshType = new FreshType();
		freshType.setLimit(-1);
		List<FreshType> freshTypes = freshTypeService.getFatherAndChilds(freshType);
		modelAndView.addObject("freshTypes",freshTypes);
		
		return modelAndView;
	}

	/*
	 * 加载生鲜管理列表数据
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Object getFreshInfoList(ProductInfo freshInfo) {
		log.info("查询条件：" + freshInfo);
		Pageable<ProductInfo> pageable = new Pageable<ProductInfo>(freshInfo);
		pageable.setContent(freshManagermentService
				.selectProductInfoList(freshInfo));
		pageable.setTotal(freshManagermentService
				.selectProductInfoCount(freshInfo));
		return pageable;
	}

	/*
	 * 添加生鲜产品信息
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	@SuppressWarnings("finally")
	public Object addFreshInfo(HttpServletRequest request,
			ProductInfo freshInfo, ProductDetail productDetail) {
		log.info("添加产品信息：" + freshInfo.toString() + ";\r\n产品详情："
				+ productDetail);
		String html = productDetail.getHtml();
		if(html!=null&&!"".equals(html)){
			productDetail.setHtml("<style type='text/css'>img{max-width: 100% !important;height: auto !important;}</style>"+html);
		}
		
		productDetail.setSort(request.getParameter("picsort") == null ? 0
				: request.getParameter("picsort").trim().equals("") ? 0
						: Integer.parseInt(request.getParameter("picsort")
								.trim()));
		Resultable resultable = null;
		try {
			Long codeId = tSequenceService.getAndUpdateSid(100001);
			freshManagermentService
					.addProduct(freshInfo, productDetail, codeId);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}

	/*
	 * 更新/编辑/修改生鲜产品信息
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object updateFreshInfo(HttpServletRequest request,
			ProductInfo freshInfo, ProductDetail productDetail) {
		log.info("更新后的产品信息：" + freshInfo + "\r\n更新后的产品详情:" + productDetail);
		String html = productDetail.getHtml();
		if(html!=null&&!"".equals(html)){
			productDetail.setHtml("<style type='text/css'>img{max-width: 100% !important;height: auto !important;}</style>"+html);
		}
		Resultable resultable = null;
		try {
			productDetail.setSort(request.getParameter("picsort") == null ? 0
					: request.getParameter("picsort").trim().equals("") ? 0
							: Integer.parseInt(request.getParameter("picsort")
									.trim()));
			freshManagermentService.updateProduct(freshInfo, productDetail);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}

	/*
	 * 跳转至查看生鲜产品详细页面
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "details")
	public ModelAndView getFreshDetails(Integer codeId) {
		log.info("获取产品详情codeId：" + codeId);
		ModelAndView modelAndView = new ModelAndView("fresh/freshdetail");
		try {
			ProductDetail detail = freshManagermentService
					.selectProductDetail(codeId);
			log.info(detail.toString());
			modelAndView.addObject("prductDetail", detail);
		} catch (NumberFormatException e) {
			this.log.error("页面初始化异常", e);
		} finally {
			return null;
		}

	}

	/*
	 * 导出生鲜列表数据
	 */
	@RequestMapping(value = "export")
	public void exportProductList(ProductInfo productInfo,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("导出产品列表...");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("list",
				freshManagermentService.selectProductInfoList(productInfo));
		 doExport(request, response, "fresh/productFailing.xls",
		 param);
	}

	/*
	 * 跳转至添加生鲜产品信息页面
	 */
	@RequestMapping(value = "addView")
	public ModelAndView addView(ProductInfo productInfo,HttpSession session) {
		session.setAttribute("productInfo", productInfo);
		log.info("新增产品信息界面：");
		Map<String, Object> freshInfo = new HashMap<String, Object>();
		freshInfo.put("store", 0);
		freshInfo.put("sort", 0);
		freshInfo.put("picsort", 0);
		
		//加载运费模板的信息
		PostageTemplate postageTemplate = new PostageTemplate();
		postageTemplate.setLimit(SellerConstants.PAGE_LIMIT_NO);
		List<PostageTemplate> postageTemplateList = postageTemplateService.getPostageTemplateList(postageTemplate);
		FreshType freshType = new FreshType();
		freshType.setLimit(-1);
		List<FreshType> freshTypes = freshTypeService.getFatherAndChilds(freshType);
		return new ModelAndView("fresh/manage/freshadd").addObject("isType",
				"add").addObject("freshInfo", freshInfo).addObject("postageTemplateList", postageTemplateList).addObject("freshTypes", freshTypes);
	}

	@RequestMapping(value = "/updateCheck")
	@ResponseBody
	private Object updateCheck(@RequestParam(value="codeId",required=true)Long codeId){
		log.info("检查参品更改条件：codeId="+codeId);
		Resultable resultable=freshManagermentService.updateCheck(codeId);
		return resultable;
	}
	
	/*
	 * 跳转至更新/编辑/修改生鲜产品信息页面
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/updateView")
	public ModelAndView view(HttpServletRequest request, @RequestParam(value="pid",required=true)Integer pid,ProductInfo productInfo,HttpSession session) {
		log.info("编辑生鲜产品信息，pid:" + pid);
		session.setAttribute("productInfo", productInfo);
		//加载运费模板的信息
		PostageTemplate postageTemplate = new PostageTemplate();
		postageTemplate.setLimit(SellerConstants.PAGE_LIMIT_NO);
		List<PostageTemplate> postageTemplateList = postageTemplateService.getPostageTemplateList(postageTemplate);
		ModelAndView modelAndView = new ModelAndView("fresh/manage/freshadd");
		try {
			Map<String, Object> freshInfo = freshManagermentService
					.getFreshAllInfo(pid);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			freshInfo.put(
					"productionStr",
					freshInfo.get("production") == null ? "" : df
							.format(freshInfo.get("production")));
			log.info("-----输入产品信息：---"+freshInfo.toString());
			FreshType freshType = new FreshType();
			freshType.setLimit(-1);
			List<FreshType> freshTypes = freshTypeService.getFatherAndChilds(freshType);
			modelAndView.addObject("freshTypes", freshTypes);
			modelAndView.addObject("freshInfo", freshInfo);
			modelAndView.addObject("isType", "update");
			modelAndView.addObject("postageTemplateList", postageTemplateList);
		} catch (Exception e) {
			this.log.error("页面初始化异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 积分商城页面初始化
	 * 创建人： huang'tao
	 * 创建时间：2016-6-18下午2:59:59
	 * @return
	 */
	@RequestMapping("freshType/init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("requestInit", "fresh/manage/freshType/init/list");
		mv.setViewName("system_settings/freshType/freshTypeList");
		return mv;
	}
	
	/**
	 * 加载积分商城分类列表
	 * 创建人： huang'tao
	 * 创建时间：2016-6-18下午3:00:26
	 * @param freshType
	 * @return
	 */
	@RequestMapping("freshType/init/list")
	public ModelAndView initList(FreshType freshType){
		ModelAndView mv = new ModelAndView();
		List<FreshType> parentFreshTypes =null;
		Map<Integer, List<FreshType>> map =null;
		try{
			mv.addObject("page", freshType.getPage());
			parentFreshTypes  = freshTypeService.getALLFather(freshType);
			map = freshManagermentService.getSubList(parentFreshTypes);
		
			
			mv.addObject("total", freshManagermentService.count(freshType));
			mv.setViewName("system_settings/freshType/freshTypeTable");
		}catch(Exception e){
			this.log.error("分类模块获取列表失败", e);
		}
		mv.addObject("parent", parentFreshTypes);
		mv.addObject("submap", map);
		return mv;
	}
	
	
	@RequestMapping("add/init")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("action", "fresh/manage/addFreshType.jhtml");
		mv.addObject("list", freshManagermentService.getParent());
		mv.setViewName("system_settings/freshType/freshTypeModel");
		return mv;
	}
	
	@RequestLogging(name="新增生鲜分类")
	@RequestMapping("addFreshType")
	@ResponseBody
	public Object addFreshType(FreshType freshType){
		Resultable result=null;
		String str = "";
		try{
			freshType.setRdate(new Date());
			if(freshType.getSort()==null){
				freshType.setSort(0);
			}
			freshManagermentService.add(freshType);
			result =  new Resultable(true, "操作成功");
		}catch(Exception e){
			this.log.error("添加分类失败", e);
			result =  new Resultable(false, "操作失败");
		}
		finally{
			String word = freshType.getName();
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			//添加到日志记录表
			String[] s={"分类",str,"新增"};
			freshManagermentService.fireLoginEvent(s,result.getSuccess()?1:0);
		}
		return result;
	}
	
	/**
	 * 积分商城-生鲜类别更新初始化
	 * 创建人： huang'tao
	 * 创建时间：2016-6-20上午9:16:43
	 * @param id
	 * @return
	 */
	@RequestMapping("update/init")
	public ModelAndView updateInit(@RequestParam("id") String id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("action", "fresh/manage/updateFreshType.jhtml");
		mv.addObject("freshType", freshManagermentService.getObject(Long.parseLong(id)));
		mv.setViewName("system_settings/freshType/freshTypeModel");
		return mv;
	}
	
	/**
	 * 积分商城-生鲜类别修改
	 * 创建人： huang'tao
	 * 创建时间：2016-6-20上午9:17:17
	 * @param freshType
	 * @return
	 */
	@RequestLogging(name="修改分类")
	@RequestMapping("updateFreshType")
	@ResponseBody
	public Object updateFreshType(FreshType freshType){
		Resultable result=null;
		try{
			freshManagermentService.update(freshType);
			result =  new Resultable(true, "操作成功");
		}catch(Exception e){
			this.log.error("更新分类失败", e);
			result =  new Resultable(false, "操作失败");
		}finally{
			//添加到日志记录表
			String[] s={"分类编号",String.valueOf(freshType.getId()),"修改","修改"};
			freshManagermentService.fireLoginEvent(s,result.getSuccess()?1:0);
		}
		return result;
	}
	
	/*
	 * 获取生鲜产品 分类数据
	 */
	@RequestMapping(value = "getLdAll", method = RequestMethod.POST)
	@ResponseBody
	public Object getClassa() {
		log.info("请求产品分类信息。。。");
		return freshManagermentService.getClassa();
	}

	@RequestMapping(value = "ckeditorUpload", method = { RequestMethod.POST })
	public void uploadFile3(@RequestParam("upload") MultipartFile filedata,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("文件上传Controller3");
		ckeditorUpdateService.ckeditorUpdate(filedata, request, response);
	}

	/**
	 * @Description: 获取供应商下拉列表
	 * @Param:seller
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月17日上午9:10:28
	 */
	@RequestMapping(value = "initSupplier",method=RequestMethod.POST)
	@ResponseBody
	public Object getSellerIdAndSellerName(TSupplier tSupplier) {
		Pageable<TSupplier> pageable = new Pageable<TSupplier>(tSupplier);
		pageable = supplierService.getPageableSupplier(tSupplier);
		return pageable;
	}
	
	/**
	 * @Description: 根据供应商id查询供应商的信息
	 * @Param:supplierId
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月18日上午10:14:51
	 */
	@RequestMapping(value = "getSupplierById",method=RequestMethod.POST)
	@ResponseBody
	public Object getSupplierById(@RequestParam("supplierId")String supplierId){
		return supplierService.getTSupplierById(supplierId);
	}
	
	
	/**
	 * 导入产品页面初始化
	 * 创建人： huang'tao
	 * 创建时间：2016-7-13下午2:06:44
	 * @return
	 */
	@RequestMapping(value="importProduct/init")
	public ModelAndView importProductInit(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("fresh/manage/importProduct");
		return mv;
	}
	
	/**
	 * 导入模版下载
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="importProduct/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response) {
		super.downloadTemplate(request, response, "fresh/importProductTemplate.xls", "产品导入模版");
	}
	
	/**
	 * 产品导入
	 * @throws IOException 
	 */
	@RequestLogging(name="积分超市-产品管理-产品导入")
	@RequestMapping(value = "importProduct/importData",method=RequestMethod.POST)
	public void importData(@RequestParam("importData")MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		try {
//			printWriter.println(JSON.toJSON(freshManagermentService.importProductInfo(multipartFile)));
			printWriter.println(JSON.toJSON(productImportService.importProductInfo(multipartFile)));
		} catch (Exception e) {
			printWriter.println(JSON.toJSON(new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, "导入失败,请重新导入")));
			log.error("文件上传失败", e);
		}
	}
	
	/**
	 * 
	 * 方法描述：初始化导入失败记录序列号
	 * 创建人： huang'tao
	 * 创建时间：2016-7-16下午3:01:29
	 * @param tSupplier
	 * @return
	 */
	@RequestMapping(value = "initFailingSerial",method=RequestMethod.POST)
	@ResponseBody
	public Object initFailingSerial(ProductFailingSerial productFailingSerial) {
		Pageable<ProductFailingSerial> pageable = new Pageable<ProductFailingSerial>(productFailingSerial);
		
		List<ProductFailingSerial> failingSerialInfoList = productImportService.getFailingSerialInfo(productFailingSerial);
		pageable.setContent(failingSerialInfoList);
		return pageable;
	}
	
	/*
	 * 导出无效产品数据
	 */
	@RequestMapping(value = "exportFailingInfo")
	public void exportFailingInfo(ProductFailing productFailing, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> param = new HashMap<String, Object>();
		productFailing.setOrder(PageConstant.NOT_ORDER);
		productFailing.setLimit(SellerConstants.PAGE_LIMIT_NO);
		List<ProductFailing> productFailingList = productImportService.getProductFailingList(productFailing);
		param.put("list", productFailingList);
		doExport(request, response, "fresh/productFailing.xls", param);
	}
	
	/**
	 * 方法描述：批量操作
	 * 创建人: lifeng
	 * 创建时间：2016年7月21日上午11:11:51
	 * @param 
	 * @return
	 */
	@RequestLogging(name = "产品上线、下线,设为精选、非精选")
	@ResponseBody
	@RequestMapping(value = "batch")
	public Object beachOnLine(ProductInfo productInfo) {
		Resultable resultable = null;
		try {
			Object[] objects = null;
			String ids = productInfo.getIds();
			String[] split = ids.split(",");
			for (String id : split) {
				resultable=freshManagermentService.updateCheck(id);
				if(!resultable.getSuccess()){
					return resultable;
				}
			}
			if (StringUtils.hasLength(ids)) {
				objects = StringUtils.paresToArray(ids, ",");
			}
			boolean num = freshManagermentService.batch(objects, productInfo);
			if (num) {
				resultable = new Resultable(true, "操作成功！");
				this.log.info("操作成功!");
			} else {
				resultable = new Resultable(false, "操作失败！");
				this.log.info("操作失败!");
			}
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败！");
		}
		return resultable;

	}
	
	/**
	 * 方法描述：修改精选排序
	 * 创建人： lifeng
	 * 创建时间：2016年8月12日下午2:21:56
	 * @param materialOrder
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateSort")
	public Object updateSortByPid(ProductInfo productInfo) {
		Resultable resultable = null;
		try {
			if (null != productInfo) {
				freshManagermentService.updateSortByPid(productInfo);
				resultable = new Resultable(true, "修改成功!");
			}
		} catch (Exception e) {
			this.log.error("处理异常",e);
			resultable = new Resultable(true, "操作失败!");
		}
		return resultable;
	}
	
	/**
	 * 查看产品的销售属性
	 */
	@RequestMapping(value = "viewSaleProperty")
	public ModelAndView viewWallet(ModelAndView model,
			@RequestParam("codeId") Long codeId) {
		model.setViewName("fresh/manage/viewSaleProperty");
		List<TSaleProperty> propertyList = freshManagermentService.getPropertyList(codeId);
		List<TSaleGroup> saleGroupList = freshManagermentService.getSaleGroupList(codeId);
		
		model.addObject("salePropertyList",propertyList);
		model.addObject("saleGroupList",saleGroupList);
		return model;
	}
	
	@RequestMapping(value = "getBrand")
	@ResponseBody
	private Object getBrand(FreshBrand freshBrand){
		Pageable<FreshBrand> pageable = new Pageable<FreshBrand>(freshBrand);
		List<FreshBrand> freshBrands=freshBrandService.getAll(freshBrand);
		pageable.setContent(freshBrands);
		return pageable;
	}
	
	@RequestMapping(value = "getSaleGroupList")
	@ResponseBody
	private Object getSaleGroupList(@RequestParam("codeId") Long codeId){
		ProductInfo productInfo = freshManagermentService.getByCodeId(codeId);
		List<TSaleGroup> saleGroupList = freshManagermentService.getSaleGroupList(codeId);
		Map<String,Object> map=new HashMap<>();
		map.put("productInfo", productInfo);
		map.put("saleGroupList", saleGroupList);
		return map;
	}
	
	@RequestMapping(value = "/getProductInfoByType")
	@ResponseBody
	private List<ProductInfo> getProductInfoByType(@RequestParam(required=true)Integer typeId){
		return  freshManagermentService.getProductInfoByType(typeId);
	}
	
	@RequestMapping(value = "/getProducDetailById")
	@ResponseBody
	public Object getProducDetailById(@RequestParam(required=true) Long codeId){
		ProductInfo productInfo = freshManagermentService.getByCodeId(codeId);
		ProductDetail productDetail = freshManagermentService.selectProductDetail(codeId.intValue());
		productInfo.setProductDetail(productDetail);
		Resultable success = Resultable.success();
		success.setData(productInfo);
		return success;
	}

}
