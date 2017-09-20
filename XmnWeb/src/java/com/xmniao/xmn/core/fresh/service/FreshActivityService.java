/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.dao.ActivityGroupDao;
import com.xmniao.xmn.core.fresh.dao.ActivityProductDao;
import com.xmniao.xmn.core.fresh.dao.FreshActivityCommonDao;
import com.xmniao.xmn.core.fresh.dao.SaleGroupDao;
import com.xmniao.xmn.core.fresh.entity.ActivityGroup;
import com.xmniao.xmn.core.fresh.entity.ActivityProduct;
import com.xmniao.xmn.core.fresh.entity.FreshActivityCommon;
import com.xmniao.xmn.core.fresh.entity.FreshImage;
import com.xmniao.xmn.core.fresh.entity.JsonToActivityBean;
import com.xmniao.xmn.core.fresh.entity.JsonToGroupBean;
import com.xmniao.xmn.core.fresh.entity.Module;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;
import com.xmniao.xmn.core.fresh.entity.TSaleGroup;
import com.xmniao.xmn.core.fresh.util.FreshConstants;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：FreshActivityCommonService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2016年12月27日 上午10:09:33
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class FreshActivityService extends BaseService<FreshActivityCommon> {

	private static final String ACTIVITY_URL = PropertiesUtil.readValue("http.activity.url"); //客户端活动接口地址
	
	@Autowired
	private FreshActivityCommonDao freshActivityCommonDao;

	@Autowired
	private ActivityProductDao activityProductDao;

	@Autowired
	private ActivityGroupDao activityGroupDao;

	@Autowired
	private SaleGroupDao saleGroupDao;

	@Autowired
	private FreshManageService freshManageService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private FreshImageService freshImageService;
	
	@Autowired
	private ActivityProductService activityProductService;
	
	@Autowired
	private FreshManageService freshManagermentService;
	
	@Autowired
	private ActivityGroupService activityGroupService;
	
	@Override
	protected BaseDao getBaseDao() {
		return freshActivityCommonDao;
	}

	/**
	 * 方法描述：分页查询 创建人： jianming <br/>
	 * 创建时间：2016年12月27日上午11:47:39 <br/>
	 * 
	 * @param freshActivityCommon
	 * @return
	 */
	public List<FreshActivityCommon> getListPage(FreshActivityCommon freshActivityCommon) {
		return freshActivityCommonDao.selectList(freshActivityCommon);
	}

	/**
	 * 方法描述：统计条数 创建人： jianming <br/>
	 * 创建时间：2016年12月27日下午1:42:21 <br/>
	 * 
	 * @param freshActivityCommon
	 * @return
	 */
	public Long countPageable(FreshActivityCommon freshActivityCommon) {
		return freshActivityCommonDao.countPageable(freshActivityCommon);
	}

	/**
	 * 方法描述：保存活动 创建人： jianming <br/>
	 * 创建时间：2016年12月28日下午6:28:32 <br/>
	 * 
	 * @param freshActivityCommon
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveActivity(FreshActivityCommon freshActivityCommon) {
		List<ActivityProduct> activityProducts = freshActivityCommon.getActivityProducts();
		freshActivityCommonDao.add(freshActivityCommon);
		for (ActivityProduct activityProduct : activityProducts) {
			ProductInfo productInfo = freshManageService.getByCodeId(activityProduct.getCodeId());
			activityProduct.setBasePrice(productInfo.getCash());
			activityProduct.setActivityId(freshActivityCommon.getId());
			List<ActivityGroup> activityGroups = activityProduct.getActivityGroups();
			for (ActivityGroup activityGroup : activityGroups) {
				TSaleGroup saleGroup = saleGroupDao.findbyPvid(activityGroup);
				if (activityGroup.getStock() > saleGroup.getStock()) {
					throw new RuntimeException("活动设置库存不能大于基础库存");
				}
				saleGroupDao.transferStore(activityGroup);
				activityGroup.setActivityId(freshActivityCommon.getId());
				activityGroupDao.add(activityGroup);
			}
			freshManageService.transferStore(activityProduct);
			activityProduct.setActivityId(freshActivityCommon.getId());
			activityProductDao.insertSelective(activityProduct);
		}

	}

	/**
	 * 方法描述：根据id查询 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午1:49:53 <br/>
	 * 
	 * @param id
	 * @return
	 */
	public FreshActivityCommon getBykey(Integer id) {
		return freshActivityCommonDao.selectByPrimaryKey(id);
	}

	/**
	 * 方法描述：修改活动 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午2:21:54 <br/>
	 * 
	 * @param activity
	 * @param jsonArray
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateActivity(FreshActivityCommon activity, JSONArray jsonArray) {
		activity.setUpdateTime(new Date());
		freshActivityCommonDao.update(activity);
		List<String> ids = new ArrayList<String>();
		for (Object object : jsonArray) {
			Map classMap = new HashMap();
			classMap.put("saleGroupList", ActivityGroup.class);
			// 使用暗示，直接将json解析为指定自定义对象，其中List完全解析,Map没有完全解析
			JsonToGroupBean<ActivityGroup> diyBean = (JsonToGroupBean) JSONObject
					.toBean(JSONObject.fromObject(object.toString()), JsonToGroupBean.class, classMap);
			ActivityProduct activityProduct = activityProductDao.getByCodeId(new Long(diyBean.getCodeId()),activity.getId());
			if (activityProduct != null) {
				//修改操作
				activityProduct.setUpdateTime(new Date());
				activityProduct.setSellIntegral(diyBean.getIntegral());
				activityProduct.setSalePrice(diyBean.getCash());
				activityProduct.setSort(diyBean.getSort());
				List<ActivityGroup> saleGroupList = diyBean.getSaleGroupList();
				Integer stoce=saleGroupList.size()>0?0:diyBean.getStore();
				for (ActivityGroup activityGroup : saleGroupList) {
					ActivityGroup group = activityGroupDao.getByCodeIdAndPvid(activityGroup.getCodeId(),
							activityGroup.getPvIds(),activity.getId());
					group.setSort(activityGroup.getSort());
					group.setAmount(activityGroup.getAmount());
					if (group.getStock() != activityGroup.getStock()) {
						saleGroupDao.updateActivityGroup(group.getStock() - activityGroup.getStock(),
								activityGroup.getCodeId(), activityGroup.getPvIds());
					}
					group.setStock(activityGroup.getStock());
					activityGroupDao.updateByKey(group);
					stoce += activityGroup.getStock();
				}
				if(stoce!=activityProduct.getSellStore()){
					freshManageService.updateActivityProduct(activityProduct.getSellStore()-stoce,activityProduct.getCodeId());
				}
				activityProduct.setBeforeStore(stoce);
				activityProduct.setSellStore(stoce);
				activityProductDao.updateByPrimaryKeySelective(activityProduct);
				ids.add(activityProduct.getId().toString());
			}else{
				//添加操作
				activityProduct=new ActivityProduct();
				ProductInfo productInfo = freshManageService.getByCodeId(new Long(diyBean.getCodeId()));
				activityProduct.setCodeId(new Long(diyBean.getCodeId()));
				activityProduct.setSalePrice(diyBean.getCash());
				activityProduct.setSort(diyBean.getSort());
				activityProduct.setSellIntegral(diyBean.getIntegral());
				activityProduct.setBasePrice(productInfo.getCash());
				activityProduct.setActivityId(activity.getId());
				activityProduct.setCreateTime(new Date());
				activityProduct.setUpdateTime(new Date());
				List<ActivityGroup> saleGroupList = diyBean.getSaleGroupList();
				Integer store=saleGroupList.size()>0?0:diyBean.getStore();
				for (ActivityGroup activityGroup : saleGroupList) {
					TSaleGroup saleGroup = saleGroupDao.findbyPvid(activityGroup);
					store += activityGroup.getStock();
					if (activityGroup.getStock() > saleGroup.getStock()) {
						throw new RuntimeException("活动设置库存不能大于基础库存");
					}
					activityGroup.setPvValue(com.xmniao.xmn.core.util.StringUtils.listToString(activityGroup.getPvValues(), ','));
					saleGroupDao.transferStore(activityGroup);
					activityGroup.setActivityId(activity.getId());
					activityGroupDao.add(activityGroup);
				}
				activityProduct.setBeforeStore(store);
				activityProduct.setSellStore(store);
				activityProduct.setActivityId(activity.getId());
				activityProductDao.insertSelective(activityProduct);
				freshManageService.transferStore(activityProduct);
				ids.add(activityProduct.getId().toString());
			}
		}
		if (ids.size() > 0) {
			// 删除多余的商品,并还原库存
			List<ActivityProduct> activityProducts=activityProductDao.getByIds(activity.getId(),StringUtils.listToString(ids, ','));
			if(activityProducts!=null&&activityProducts.size()>0){
				for (ActivityProduct product : activityProducts) {
					List<ActivityGroup> activityGroups = activityGroupDao.getByCodeId(product.getCodeId(), activity.getId());
					for (ActivityGroup activityGroup : activityGroups) {
						saleGroupDao.updateActivityGroup(activityGroup.getStock(), activityGroup.getCodeId(), activityGroup.getPvIds());
						activityGroupDao.deleteByPrimaryKey(activityGroup.getId());
					}
					freshManageService.updateActivityProduct(product.getSellStore(), product.getCodeId());
					activityProductDao.clearStore(product.getId());
				}
			}
		}
	}
	
	

	/**
	 * 方法描述：活动是否在进行中 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午2:27:28 <br/>
	 * 
	 * @param id
	 * @return
	 */
	public FreshActivityCommon hasBeingActivity(Integer id) {
		return freshActivityCommonDao.hasBeingActivity(id);
	}

	/**
	 * 方法描述：终止活动
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午5:57:18 <br/>
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void endActivity(Integer id) {
		FreshActivityCommon freshActivityCommon = new FreshActivityCommon();
		freshActivityCommon.setId(id);
		freshActivityCommon.setStatus(1);
		freshActivityCommon.setUpdateTime(new Date());
		freshActivityCommon.setEndTime(new Date());
		freshActivityCommonDao.update(freshActivityCommon);
		activityProductDao.endActivity(freshActivityCommon.getId());
	}

	/**
	 * 方法描述：删除活动
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午6:12:11 <br/>
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteActivity(Integer id) {
		FreshActivityCommon freshActivityCommon = new FreshActivityCommon();
		freshActivityCommon.setId(id);
		freshActivityCommon.setStatus(2);
		freshActivityCommon.setUpdateTime(new Date());
		freshActivityCommonDao.update(freshActivityCommon);
		activityProductDao.endActivity(freshActivityCommon.getId());
	}

	/**
	 * 方法描述：加载预览数据
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午8:22:39 <br/>
	 * @param id
	 * @return
	 */
	public List<ActivityProduct> getPreview(Integer id) {
		return activityProductDao.getByPreview(id);
	}

	/**
	 * 方法描述：获取活动choose
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月4日上午10:29:52 <br/>
	 * @param freshActivityCommon
	 * @return
	 */
	public List<FreshActivityCommon> getActivityChoose(FreshActivityCommon freshActivityCommon) {
		return freshActivityCommonDao.selectAllActivity();
	}

	/**
	 * 方法描述：检查是否符合终止条件
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月13日上午10:31:45 <br/>
	 * @param id
	 * @return
	 */
	public Resultable checkEndCondition(Integer id) {
		Module module = new Module();
		module.setActivityId(id.longValue());
		module.setState(1);
		List<Module> list = moduleService.getList(module);
		if(list.size()>0){
			return new Resultable(false,"首页正在展示此活动,请先下架首页模块");
		}
		Module module2 = new Module();
		module2.setState(1);
		module2.setJumpUrl(ACTIVITY_URL+"?id="+id);
		List<Module> list2 = moduleService.getList(module2);
		if(list2.size()>0){
			return new Resultable(false,"首页正在展示此活动,请先下架首页模块");
		}
		FreshImage freshImage = new FreshImage();
		freshImage.setState(1);
		freshImage.setJumpUrl(ACTIVITY_URL+"?id="+id);
		List<FreshImage> list3 = freshImageService.getList(freshImage);
		if(list3.size()>0){
			return new Resultable(false,"首页正在展示此活动,请先下架首页模块");
		}
		return new Resultable(true,"改来改去");
	}
	
	public List<Map<String,Object>> getEditActivityVo(Integer id) throws Exception{
		List<ActivityProduct> activityProducts=activityProductService.getByActivityId(id);
		List<Map<String,Object>> activityProductVo=new ArrayList<>();
		for (ActivityProduct activityProduct : activityProducts) {
			ProductInfo productInfo = freshManagermentService.getByCodeId(activityProduct.getCodeId());
			HashMap<String,Object> vo = new HashMap<String,Object>();
			vo.put("codeId", activityProduct.getCodeId());
			vo.put("name", productInfo.getPname());
			vo.put("sellIntegral",activityProduct.getSellIntegral());
			vo.put("salePrice",activityProduct.getSalePrice());
			vo.put("sort",activityProduct.getSort());
			vo.put("pid",productInfo.getPid());
			JsonToGroupBean<JsonToActivityBean> jsonToGroupBean = new JsonToGroupBean<JsonToActivityBean>();
			jsonToGroupBean.setCash(activityProduct.getSalePrice());
			jsonToGroupBean.setCodeId(activityProduct.getCodeId().toString());
			jsonToGroupBean.setIntegral(activityProduct.getSellIntegral());
			jsonToGroupBean.setSort(activityProduct.getSort());
			jsonToGroupBean.setStore(activityProduct.getSellStore());
			jsonToGroupBean.setMaxStore(productInfo.getStore()+activityProduct.getSellStore());
			jsonToGroupBean.setType("edit");
			List<ActivityGroup> activityGroups = activityGroupService.getByCodeId(activityProduct.getCodeId(),id);
			List<JsonToActivityBean> saleGroupList=new ArrayList<>();
			for (ActivityGroup activityGroup : activityGroups) {
				TSaleGroup tSaleGroup= freshManagermentService.getGroupsByPvid(activityGroup.getCodeId(),activityGroup.getPvIds());
				JsonToActivityBean jsonToActivityBean = new JsonToActivityBean();
				jsonToActivityBean.setAmount(activityGroup.getAmount());
				jsonToActivityBean.setCodeId(activityGroup.getCodeId());
				jsonToActivityBean.setPvIds(activityGroup.getPvIds());
				jsonToActivityBean.setPvValues(activityGroup.getPvValues());
				jsonToActivityBean.setSort(activityGroup.getSort());
				jsonToActivityBean.setStock(activityGroup.getStock());
				jsonToActivityBean.setMaxStock(tSaleGroup.getStock()+activityGroup.getStock());
				saleGroupList.add(jsonToActivityBean);
			}
			vo.put("beforeStore",activityProduct.getSellStore());	
			jsonToGroupBean.setSaleGroupList(saleGroupList);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(jsonToGroupBean);
			vo.put("json", json);
			activityProductVo.add(vo);
		}
		return activityProductVo;
	}

	/**
	 * 方法描述：删除秒杀活动关联的场次
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月21日下午10:11:01 <br/>
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteByKillUpdate(Long id) {
		FreshActivityCommon freshActivityCommon = new FreshActivityCommon();
		freshActivityCommon.setSpikeId(id);
		List<FreshActivityCommon> activityCommons = freshActivityCommonDao.selectList(freshActivityCommon);
		for (FreshActivityCommon activityCommon : activityCommons) {
			List<ActivityProduct> activityProducts = activityProductService.getByActivityId(activityCommon.getId());
			if(activityProducts!=null&&activityProducts.size()>0){
				for (ActivityProduct product : activityProducts) {
					List<ActivityGroup> activityGroups = activityGroupDao.getByCodeId(product.getCodeId(), activityCommon.getId());
					for (ActivityGroup activityGroup : activityGroups) {
						saleGroupDao.updateActivityGroup(activityGroup.getStock(), activityGroup.getCodeId(), activityGroup.getPvIds());
						activityGroupDao.deleteByPrimaryKey(activityGroup.getId());
					}
					freshManageService.updateActivityProduct(product.getSellStore(), product.getCodeId());
					activityProductDao.clearStore(product.getId());
				}
			}
			activityCommon.setStatus(FreshConstants.ACTIVITY_COMMON_DELETE_STATUS);
			freshActivityCommonDao.update(activityCommon);
		}
	}
	
}
