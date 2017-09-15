package com.xmniao.xmn.core.market.service.pay.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.market.pay.ActivityRequest;
import com.xmniao.xmn.core.market.dao.FreshActivityCommonDao;
import com.xmniao.xmn.core.market.dao.FreshActivityProductDao;
import com.xmniao.xmn.core.market.dao.ProductInfoDao;
import com.xmniao.xmn.core.market.entity.pay.ActivityPage;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityProduct;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.service.home.HomeService;
import com.xmniao.xmn.core.market.service.pay.ActivityService;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: ActivityServiceImpl    
* @Description:活动实现类    
* @author: liuzhihao   
* @date: 2016年12月22日 下午2:17:35
 */
@Service
public class ActivityServiceImpl implements ActivityService{
	
	//通过活动Dao
	@Autowired
	private FreshActivityCommonDao freshActivityCommonDao;
	
	//活动商品Dao
	@Autowired
	private FreshActivityProductDao freshActivityProductDao;
	
	//商品详情dao
	@Autowired
	private ProductInfoDao productInfoDao;
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private String fileUrl;

	/**
	 * 查看活动详情
	 * @param idRequest
	 * @return
	 */
	public Map<Object,Object> queryActivityInfo(ActivityRequest activityRequest){
		
		Map<Object,Object> map = new HashMap<Object,Object>();//返回结果集
		
		//通过活动ID查询活动详情
		FreshActivityCommon fac = new FreshActivityCommon();
		
		try{
			fac = freshActivityCommonDao.selectByPrimaryKey(activityRequest.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(fac != null){
			map.put("id", fac.getId());//活动ID
			map.put("image", fileUrl+ObjectUtils.toString(fac.getImg()));//活动图片
			map.put("title", ObjectUtils.toString(fac.getTitle()));//活动标题
			map.put("remark", ObjectUtils.toString(fac.getRemark()));//活动描述
			//活动起始时间
			String startDate="";
			//活动结束时间
			String endDate="";
			if(StringUtils.isNotEmpty(ObjectUtils.toString(fac.getBeginDate()))){
				startDate = DateUtil.format(fac.getBeginDate(), "yyyy.MM.dd HH:mm:ss");
			}
			if(StringUtils.isNotEmpty(ObjectUtils.toString(fac.getEndDate()))){
				endDate = DateUtil.format(fac.getEndDate(), "yyyy.MM.dd HH:mm:ss");
			}
			
			if(StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)){
				map.put("isDate", "");
			}else{
				map.put("isDate", "活动时间:"+startDate+"至"+endDate);
			}
			
			//通过活动ID查询参与活动的商品信息
			List<FreshActivityProduct> faps = new ArrayList<FreshActivityProduct>();
			Map<Object,Object> querymap = new HashMap<Object,Object>();
			querymap.put("id", activityRequest.getId());//活动ID
			querymap.put("pageNo", activityRequest.getPage());
			querymap.put("pageSize", 8);
			try{
				faps = freshActivityProductDao.findAllByActivityIdAndType(querymap);
			}catch(Exception e){
				e.printStackTrace();
			}
			List<Map<Object,Object>> products = new ArrayList<Map<Object,Object>>();
			if(!faps.isEmpty()){
				
				for(FreshActivityProduct fap : faps){
					Map<Object,Object> fapmap = new HashMap<Object,Object>();
					
					fapmap.put("salePrice", "￥"+ObjectUtils.toString(fap.getSalePrice())+"+"+ObjectUtils.toString(fap.getSellIntegral())+"积分");//商品活动价格
					fapmap.put("sellStore", ObjectUtils.toString(fap.getSellStore()));//活动商品库存
					
					//通过商品ID查询商铺详情
					
					ProductInfo productInfo = new ProductInfo();
					
					try{
						productInfo =	productInfoDao.selectByCodeId(fap.getCodeid().intValue());
					}catch(Exception e){
						e.printStackTrace();
					}
					
					if(productInfo != null){
						fapmap.put("productId", productInfo.getPid());//商品ID
						
						String goodsName = ObjectUtils.toString(productInfo.getGoodsname());
						
						if(StringUtils.isEmpty(goodsName)){
							goodsName = ObjectUtils.toString(productInfo.getPname());
						}
						fapmap.put("goodsName", goodsName);//商品名称
						fapmap.put("breviary", fileUrl+ObjectUtils.toString(productInfo.getBreviary()));//商品缩略图
						fapmap.put("price", "￥"+ObjectUtils.toString(productInfo.getPrice()));//商品原价
						fapmap.put("codeId", productInfo.getCodeid());
						homeService.loadStore(productInfo,activityRequest.getId().longValue());
						
						fapmap.put("stock", StringUtils.isNotEmpty(ObjectUtils.toString(productInfo.getStore()))?ObjectUtils.toString(productInfo.getStore()):0);
						products.add(fapmap);
					}
				}
				map.put("products", products);
			}
		}
		
		return map;
	}
	
	
	public ActivityPage<Map<Object,Object>> queryActivityProductList(ActivityRequest activityRequest){
		ActivityPage<Map<Object,Object>> activity = new ActivityPage<Map<Object,Object>>();
		List<Map<Object,Object>> products = new ArrayList<Map<Object,Object>>();
		//通过活动ID查询参与活动的商品信息
		Map<Object,Object> querymap = new HashMap<Object,Object>();
		querymap.put("id", activityRequest.getId());//活动ID
		querymap.put("pageNo", activityRequest.getPage());
		querymap.put("pageSize", 8);
		List<FreshActivityProduct> faps = freshActivityProductDao.findAllByActivityIdAndType(querymap);
		
		if(faps != null && !faps.isEmpty()){
			
			for(FreshActivityProduct fap : faps){
				Map<Object,Object> fapmap = new HashMap<Object,Object>();
				fapmap.put("salePrice", "￥"+ObjectUtils.toString(fap.getSalePrice())+"+"+ObjectUtils.toString(fap.getSellIntegral())+"积分");//商品活动价格
				fapmap.put("sellStore", ObjectUtils.toString(fap.getSellStore()));//活动商品库存
				//通过商品ID查询商铺详情
				
				ProductInfo productInfo = new ProductInfo();
				
				try{
					productInfo =	productInfoDao.selectByPrimaryKey(fap.getProductId());
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(productInfo != null){
					fapmap.put("productId", productInfo.getPid());//商品ID
					String goodsName = ObjectUtils.toString(productInfo.getGoodsname());
					
					if(StringUtils.isEmpty(goodsName)){
						goodsName = ObjectUtils.toString(productInfo.getPname());
					}
					fapmap.put("goodsName", goodsName);//商品名称
					fapmap.put("breviary", fileUrl+ObjectUtils.toString(productInfo.getBreviary()));//商品缩略图
					fapmap.put("price", "￥"+ObjectUtils.toString(productInfo.getPrice()));//商品原价
					fapmap.put("codeId", productInfo.getCodeid());
					homeService.loadStore(productInfo,activityRequest.getId().longValue());
					fapmap.put("stock", StringUtils.isNotEmpty(ObjectUtils.toString(productInfo.getStore()))?ObjectUtils.toString(productInfo.getStore()):0);
					
					products.add(fapmap);
					
				}
			}
			//统计一共有多少个活动商品
			int totalCounts = freshActivityProductDao.sumActivityProducts(activityRequest.getId());
			activity.setTotalCounts(totalCounts);
		}
		activity.setState(ResponseCode.SUCCESS);
		activity.setInfo("查询成功");
		activity.setResult(products);
		return activity;
	}
}
