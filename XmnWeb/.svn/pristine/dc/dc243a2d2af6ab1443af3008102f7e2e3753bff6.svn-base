/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.ResultFile;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.base.Import.PoiImport;
import com.xmniao.xmn.core.common.service.TSequenceService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.fresh.dao.FreshManageDao;
import com.xmniao.xmn.core.fresh.dao.ProductFailingDao;
import com.xmniao.xmn.core.fresh.dao.SaleGroupDao;
import com.xmniao.xmn.core.fresh.dao.SalePropertyDao;
import com.xmniao.xmn.core.fresh.dao.SalePropertyValueDao;
import com.xmniao.xmn.core.fresh.dao.SupplierDao;
import com.xmniao.xmn.core.fresh.entity.FreshBrand;
import com.xmniao.xmn.core.fresh.entity.FreshType;
import com.xmniao.xmn.core.fresh.entity.ProductFailing;
import com.xmniao.xmn.core.fresh.entity.ProductFailingSerial;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;
import com.xmniao.xmn.core.fresh.entity.TSaleGroup;
import com.xmniao.xmn.core.fresh.entity.TSaleProperty;
import com.xmniao.xmn.core.fresh.entity.TSalePropertyValue;
import com.xmniao.xmn.core.fresh.entity.TSupplier;
import com.xmniao.xmn.core.util.FastfdsConstant;
import com.xmniao.xmn.core.util.NumberUtil;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：ProductImportService
 * 
 * 类描述: 积分超市-产品导入服务
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-7-15上午10:12:16
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class ProductImportService extends BaseService<ProductInfo> {
	
	private Logger log = LoggerFactory.getLogger(ProductImportService.class);
	
	private static final  Integer SORT_MIN=0;//产品排序最小值
	
	private static final  Integer SORT_MAX=Integer.MAX_VALUE;//产品排序最大值
	
	private static final  Integer QUALITY_MIN=0;//产品保质期最小值(天)
	
	private static final  Integer QUALITY_MAX=365;//产品保质期最大值
	
	/**
	 * 注入积分产品管理服务
	 */
	@Autowired
	private FreshManageDao freshmanagermentDao;
	
	/**
	 * 注入供应商服务
	 */
	@Autowired
	private SupplierDao supplierDao;
	
	/**
	 * 注入导入产品失败服务
	 */
	@Autowired
	private ProductFailingDao productFailingDao;
	
	/**
	 * 注入序列服务
	 */
	@Autowired
	private TSequenceService tSequenceService;
	
	@Autowired
	private FreshTypeService freshTypeService;
	
	@Autowired
	private FreshBrandService freshBrandService;
	
	@Autowired
	private SalePropertyDao salePropertyDao;
	
	@Autowired
	private SalePropertyValueDao salePropertyValueDao;
	
	@Autowired
	private SaleGroupDao saleGroupDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return freshmanagermentDao;
	}
	
	/**
	 * 
	 * 方法描述：获取导入产品失败序列号信息
	 * 创建人： huang'tao
	 * 创建时间：2016-7-16下午3:13:03
	 * @param productFailingSerial
	 * @return
	 */
	 public List<ProductFailingSerial> getFailingSerialInfo(ProductFailingSerial productFailingSerial){
		return productFailingDao.getFailingSerialInfo(productFailingSerial);
	}
	
	/**
	 * 
	 * 方法描述：获取产品导入失败信息
	 * 创建人： huang'tao
	 * 创建时间：2016-7-16下午4:17:28
	 * @param productFailing
	 * @return
	 */
	public List<ProductFailing> getProductFailingList(ProductFailing  productFailing){
		 return productFailingDao.getProductFailingList(productFailing);
	 }
	/**
	 * 导入产品信息
	 * 创建人： huang'tao
	 * 创建时间：2016-7-13下午4:54:01
	 * @param multipartFile
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultFile importProductInfo(MultipartFile multipartFile) {
		ResultFile rf=null;
		try {
			List<ProductInfo> productInfo = PoiImport.dataImport(multipartFile, ProductInfo.class);
			if(productInfo!=null&&productInfo.size()>0){
				if(productInfo.size()>1000){
					rf = new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, String.format("导入数据不能超过一千条"));
					return rf;
				}
				Resultable resultable =new Resultable();
				savaProductInfo( productInfo,resultable);
				rf = new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_SUCCESS, String.format(resultable.getMsg()));
			}else{
				rf = new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, String.format("导入失败,模板错误或数据为空!"));
			}
			return rf;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("文件上传失败", e);
			throw new ApplicationException("导入产品信息异常",e);
		}finally {
			fireLoginEvent( new String[]{"积分超市-产品管理-产品导入","导入操作","积分超市-产品管理-产品导入",""}, rf==null?0:rf.getStatus());
		}
	}
	
	/**
	 * 保存产品信息(t_product_info)及产品细节(t_product_details)
	 * 创建人： huang'tao
	 * 创建时间：2016-7-13下午5:04:04
	 * @param productInfo
	 * @param resultable
	 */
	private void savaProductInfo(List<ProductInfo> productInfo, Resultable resultable) {
			FreshBrand freshBrand = new FreshBrand();
			freshBrand.setStart(0);
			List<FreshBrand> freshBrands=freshBrandService.getList(freshBrand);
			FreshType freshType = new FreshType();
			freshType.setDstatus(0);
			List<FreshType> freshTypes = freshTypeService.getList(freshType);
			List<ProductInfo> efficientProducts=new ArrayList<ProductInfo>();//校验通过的产品集合
			List<ProductFailing> noneffectiveProducts=new ArrayList<ProductFailing>();//无效的产品集合
			List<TSaleProperty> salePrpos=new ArrayList<TSaleProperty>();	//商品分类集合
			List<TSaleGroup> saleGroups = new ArrayList<TSaleGroup>();	//商品规格集合
			Date currentDate=null;
			boolean result=true;
			Long importserial = tSequenceService.getAndUpdateSid(100005);
			for(ProductInfo product:productInfo){
				Long codeId = tSequenceService.getAndUpdateSid(100001);
				result=validateProductInfo(product,freshBrands,freshTypes);//校验有效性标志
				currentDate=new Date();
				product.setCodeId(codeId);
				product.setStore(product.getStock());
				/*String[] split = product.getClassaVal().split("\\|");
				this.log.debug("产品分类编号："+split[0]);
				//产品分类
				product.setClassa(Integer.valueOf(product.getClassaVal().split("\\|")[0]));
				//是否支持退款
				product.setAllowRefund("是".equals(product.getAllowRefundVal())?true:false);
				//配送方式
				product.setDeliveryType(Integer.valueOf(product.getDeliveryTypeVal().split("\\|")[0]));
				//是否精选
				product.setChoice("是".equals(product.getChoiceVal())?1:0);
				//是否含添加剂
				product.setIsAdditives("是".equals(product.getIsAdditivesVal())?1:0);*/
				
				product.setRdate(currentDate);
				product.setUdate(currentDate);
				if(result){
					efficientProducts.add(product);
					TSaleProperty tSaleProperty = new TSaleProperty();
					tSaleProperty.setCodeId(codeId);
					tSaleProperty.setProperty(product.getPrpoName());
					salePropertyDao.add(tSaleProperty);
					TSalePropertyValue salePropVal = new TSalePropertyValue();
					salePropVal.setPropertyId(tSaleProperty.getId());
					salePropVal.setValue(product.getGroupName());
					salePropertyValueDao.add(salePropVal);
					TSaleGroup tSaleGroup = new TSaleGroup();
					tSaleGroup.setCodeId(codeId);
					tSaleGroup.setAmount(product.getAmount());
					tSaleGroup.setPvIds(salePropVal.getId().toString());
					tSaleGroup.setPvIdsSort(salePropVal.getId().toString());
					tSaleGroup.setStock(product.getStock());
					tSaleGroup.setSales(0);
					saleGroups.add(tSaleGroup);
				}else{
					noneffectiveProducts.add(convertProduct(product,importserial));
				}
			}
			if(efficientProducts!=null&&efficientProducts.size()>0){
				freshmanagermentDao.addBatch(efficientProducts);
				freshmanagermentDao.addBatchDetail(efficientProducts);
				saleGroupDao.addBatch(saleGroups);
				
			}
			if(noneffectiveProducts!=null && noneffectiveProducts.size()>0){
				productFailingDao.addBatch(noneffectiveProducts);
				//插入t_product_failing_serial表信息
				ProductFailingSerial record=new ProductFailingSerial();
				record.setRdate(new Date());
				record.setImportserial(importserial);
				productFailingDao.insertFailingSerial(record);
			}
			resultable.setMsg("成功导入"+efficientProducts.size()+"条数据, 无效数据有"+noneffectiveProducts.size()+"条！");
	}
	
	

	/**
	 * 校验产品导入模板单行数据的有效性
	 * 创建人： huang'tao
	 * 创建时间：2016-7-14下午8:47:53
	 * @param product
	 * @param freshTypes 
	 * @param freshBrands 
	 * @return
	 */
	private boolean validateProductInfo(ProductInfo product, List<FreshBrand> freshBrands, List<FreshType> freshTypes) {
		
		boolean result=true;
		StringBuilder comments=new StringBuilder();//导入失败提示信息
		try {
			Double discount = 0d;//产品售价
//			BigDecimal purchasePrice = product.getPurchasePrice();//采购价
			BigDecimal cash=product.getCash();//积分价（现金）
			BigDecimal integral=product.getIntegral();//积分价（积分）
			
			//产品名称
			if(StringUtils.isBlank(product.getPname())){
				result=false;
				comments.append("产品名称为空;");
			}
			if(StringUtils.isNotBlank(product.getBrandName())){
				boolean hasBrand=false;
				for (FreshBrand freshBrand : freshBrands) {
					if(product.getBrandName().equals(freshBrand.getName())){
						product.setBrandId(freshBrand.getId());
						hasBrand=true;
						break;
					}
				}
				if(!hasBrand){
					result=false;
					comments.append("产品品牌无效;");
				}
			}
			//产品分类: 20001|美食,20002|食品,20003|美妆,20004|百货
			if(StringUtils.isBlank(product.getClassaVal())){
				result=false;
				comments.append("产品分类为空;");
			}else{
				//String classaCode = product.getClassaVal().split("\\|")[0];
				//boolean classaValidate="20001".equals(classaCode)||"20002".equals(classaCode)||"20003".equals(classaCode)||"20004".equals(classaCode);
				boolean hasBrand=false;
				for (FreshType freshType : freshTypes) {
					if(product.getClassaVal().equals(freshType.getName())){
						product.setClassa(freshType.getId());
						hasBrand=true;
						break;
					}
				}
				if(!hasBrand){
					result=false;
					comments.append("输入的产品分类无效;");
				}

			}
			//采购价(元)
			if(null==product.getPurchasePrice()){
				result=false;
				comments.append("采购价(元)为空;");
			}
			//原价(元)
			if(null==product.getPrice()){
				result=false;
				comments.append("原价(元)为空;");
			}
			//积分价(现金)
			if(null==product.getCash()){
				result=false;
				comments.append("积分价(现金)为空;");
			}
			//积分价(积分)
			if(null==product.getIntegral()){
				result=false;
				comments.append("积分价(积分)为空;");
			}
			//产品售价
			if(null != cash && null != integral){
				discount=Double.valueOf(NumberUtil.getDouble2Fixedpoint(cash.add(integral).doubleValue()));
				product.setDiscount(discount);
			}
			//售价与采购价1.1倍比较
			/*if(null!=discount && null!=purchasePrice){
				BigDecimal discountBd = new BigDecimal(discount);//售价
				BigDecimal purchasePriceBd = purchasePrice.multiply(new BigDecimal(1.1));//采购价1.1倍
				
				if(discountBd.compareTo(purchasePriceBd)<0){
					result=false;
					comments.append("产品售价需大于等于采购价的1.1倍;");
				}
				cash=purchasePriceBd;//积分价（元）(现金)=采购价1.1倍
				integral=discountBd.subtract(cash);//积分
				product.setCash(cash);
				product.setIntegral(integral);
			}*/
			//库存
			/*if(null == product.getStore()){
				result=false;
				comments.append("库存为空;");
			}*/
			//产品重量
			/*if(StringUtils.isBlank(product.getWeight())){
				result=false;
				comments.append("产品重量为空;");
			}*/
			//产品排序
			if(null==product.getSort()){
				result=false;
				comments.append("产品排序为空;");
			}else{
				boolean compareResult=product.getSort()<SORT_MIN || product.getSort()>SORT_MAX;
				if(compareResult){
					result=false;
					comments.append("产品排序值必须为介于"+SORT_MIN+"至"+SORT_MAX+"的整数;");
				}
			}
			//是否支持退款
			if(StringUtils.isBlank(product.getAllowRefundVal())){
				result=false;
				comments.append("是否支持退款为空;");
			}else{
				//是否支持退款
				boolean b="是".equals(product.getAllowRefundVal())||"否".equals(product.getAllowRefundVal());
				if(!b){
					result=false;
					comments.append("输入的是否支持退款无效;");
				}else{
					product.setAllowRefund("是".equals(product.getAllowRefundVal())?true:false);
				}
				
			}
			//规格名称
			if(StringUtils.isBlank(product.getPrpoName())){
				result=false;
				comments.append("规格名称为空;");
			}
			//规格选项
			if(StringUtils.isBlank(product.getGroupName())){
				result=false;
				comments.append("规格选项为空;");
			}
			//规格加价
			if(product.getAmount()==null){
				result=false;
				comments.append("规格加价;");
			}
			//规格库存
			if(product.getStock()==null){
				result=false;
				comments.append("规格库存;");
			}
			//
			//配送方式
			if(StringUtils.isBlank(product.getDeliveryTypeVal())){
				result=false;
				comments.append("配送方式为空;");
			}else{
				//配送方式 0快递 1虚拟物品 2到店自提 3兑换码'
				Integer DeliveryType=-1;
				if(product.getDeliveryTypeVal().contains("|")){
					DeliveryType= Integer.valueOf(product.getDeliveryTypeVal().split("\\|")[0]);
				}
				boolean b=DeliveryType==0||DeliveryType==1||DeliveryType==2||DeliveryType==3;
				if(!b){
					result=false;
					comments.append("输入的配送方式无效;");
				}else{
					product.setDeliveryType(DeliveryType);
				}
			}
			//快递计重
			if(null==product.getExpWeight()){
				result=false;
				comments.append("快递计重为空;");
			}
			//保质期
			/*if(null==product.getQuality()){
				result=false;
				comments.append("保质期为空;");
			}else{
				boolean compare=product.getQuality()<QUALITY_MIN || product.getQuality()>QUALITY_MAX;
				if(compare){
					result=false;
					comments.append("保质期必须为介于"+QUALITY_MIN+"至"+QUALITY_MAX+"的整数;");
				}
			}*/
			//生产日期
			/*if(null==product.getProduction()){
				result=false;
				comments.append("生产日期为空;");
			}*/
			//是否精选
			if(StringUtils.isBlank(product.getChoiceVal())){
				result=false;
				comments.append("是否精选为空;");
			}else{
				//是否精选
				boolean b="是".equals(product.getChoiceVal())||"否".equals(product.getChoiceVal());
				if(!b){
					result=false;
					comments.append("输入的是否精选无效;");
				}else{
					product.setChoice("是".equals(product.getChoiceVal())?1:0);
				}
			}
			//是否含添加剂
			/*if(StringUtils.isBlank(product.getIsAdditivesVal())){
				result=false;
				comments.append("是否含添加剂为空;");
			}else{
				boolean b="是".equals(product.getIsAdditivesVal())||"否".equals(product.getIsAdditivesVal());
				if(!b){
					result=false;
					comments.append("输入的是否含添加剂无效;");
				}else{
					product.setIsAdditives("是".equals(product.getIsAdditivesVal())?1:0);
				}
			}*/
			//供应商名称
			if(StringUtils.isBlank(product.getSupplierName())){
				result=false;
				comments.append("供应商名称为空;");
			}else{
				TSupplier tSupplier=new TSupplier();
				tSupplier.setSupplierName(product.getSupplierName());
				TSupplier supplier = supplierDao.getSupplierBySupplierName(tSupplier);
				if(null==supplier){
					result=false;
					comments.append("未找到对应的供应商信息，请检查供应商名称是否正确;");
				}else{
					product.setSupplierId(supplier.getSupplierId());
				}
			}
		} catch (Exception e) {
			result=false;
			comments.append("导入产品信息异常，请按照填写说明检查数据格式是否正确。");
			e.printStackTrace();
		}
		product.setComments(comments.toString());
		return result;
	}
	
	/**
	 * 方法描述：将产品信息转换为导入失败的产品信息
	 * 创建人： huang'tao
	 * 创建时间：2016-7-15下午3:26:36
	 * @param product
	 * @return
	 */
	private ProductFailing convertProduct(ProductInfo product,Long importserial) {
		ProductFailing pf = new ProductFailing();
		pf.setImportserial(importserial);
		pf.setStandard(product.getStandard());
		pf.setPname(product.getPname());
		pf.setGoodsname(product.getGoodsName());
		pf.setClassa(product.getClassaVal());
		pf.setPrice(product.getPrice()==null?"":product.getPrice().toString());
		pf.setDiscount(product.getDiscount()==null?"":product.getDiscount().toString());
		pf.setBatching(product.getBatching());
		pf.setWeight(product.getWeight());
		pf.setSuttle(product.getSuttle());
		pf.setQuality(product.getQuality()==null?"":product.getQuality().toString());
		pf.setIsadditives(product.getIsAdditivesVal());
		pf.setPacking(product.getPacking());
		pf.setProduction(product.getProductionStr());
		pf.setBrandname(product.getBrandName());
		pf.setCrafts(product.getCrafts());
		pf.setSelltype(product.getSellType());
		pf.setDeal(product.getDeal());
		pf.setPlace(product.getPlace());
		pf.setProvince(product.getProvince());
		pf.setCity(product.getCity());
		pf.setStored(product.getStored());
		pf.setStore(product.getStore()==null?"":product.getStore().toString());
		pf.setSales(product.getSales()==null?"":product.getSales().toString());
		pf.setChoice(product.getChoiceVal());
		pf.setCname(product.getCname());
		pf.setAddress(product.getAddress());
		pf.setLicenseid(product.getLicenseId());
		pf.setTel(product.getTel());
		pf.setRdate(new Date());
		pf.setSort(product.getSort()==null?"":product.getSort().toString());
		pf.setSuppliername(product.getSupplierName());
		pf.setSalesinfo(product.getSalesInfo());
		pf.setCash(product.getCash()==null?"":NumberUtil.getDouble4Fixedpoint(product.getCash().doubleValue()));
		pf.setIntegral(product.getIntegral()==null?"":NumberUtil.getDouble2Fixedpoint(product.getIntegral().doubleValue()));
		pf.setPurchaseprice(product.getPurchasePrice()==null?"":product.getPurchasePrice().toString());
		pf.setDeliverytype(product.getDeliveryTypeVal());
		pf.setAllowrefund(product.getAllowRefundVal());
		pf.setExpweight(product.getExpWeight()==null?"":product.getExpWeight().toString());
		pf.setGoodsserial(product.getGoodsSerial());
		pf.setBarcode(product.getBarcode());
		pf.setDelivery(product.getDelivery());
		pf.setPostnote(product.getPostnote());
		pf.setServicenote(product.getServicenote());
		pf.setRemarks(product.getRemarks());
		pf.setComments(product.getComments());
		pf.setPrpoName(product.getPrpoName());
		pf.setGroupName(product.getGroupName());
		pf.setAmount(product.getAmount());
		pf.setStock(product.getStock());
		return pf;
	}

}
