package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;

import com.xmniao.xmn.core.base.BaseEntity;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class Kill extends BaseEntity{
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 活动标题
	 */
	private String title;

	/**
	 * 开始时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 活动图片
	 */
	private String image;

	/**
	 * 活动状态: 01未开始 02进行中 03已结束 04已删除
	 */
	private Integer state;

	/**
	 * 限制购买数(null为不限制)
	 */
	private Integer orderLimit;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 场次json字符
	 */
	private String boutListJson;
	
	private List<FreshActivityCommon>  activityCommons;
	
	private Integer billNum;	//订单总数
	
	private Date terminTime; //终止时间
	
	private Integer proceedStatus; //进行状态
	
	private Integer labelId;  //商品标签id
	
	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public Integer getProceedStatus() {
    	if(state!=null&&(state==1||state==2)){
    		return 3;
    	}
    	if(proceedStatus==null&&startTime!=null&&endTime!=null){
    		if(new Date().getTime()>endTime.getTime()){
    			return 3;
    		}else if(new Date().getTime()<startTime.getTime()){
    			return 1;
    		}else{
    			return 2;
    		}
    	}
		return proceedStatus;
	}
	
	public void setProceedStatus(Integer proceedStatus) {
		this.proceedStatus = proceedStatus;
	}

	public Date getTerminTime() {
		return terminTime;
	}

	public void setTerminTime(Date terminTime) {
		this.terminTime = terminTime;
	}

	public Integer getBillNum() {
		return billNum;
	}

	public void setBillNum(Integer billNum) {
		this.billNum = billNum;
	}

	public List<FreshActivityCommon> getActivityCommons() {
		return activityCommons;
	}

	public void setActivityCommons(List<FreshActivityCommon> activityCommons) {
		this.activityCommons = activityCommons;
	}

	public String getBoutListJson() {
		return boutListJson;
	}

	public void setBoutListJson(String boutListJson) {
		this.boutListJson = boutListJson;
	}

	/**
	 * 主键
	 * 
	 * @return id 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 活动标题
	 * 
	 * @return title 活动标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 活动标题
	 * 
	 * @param title
	 *            活动标题
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * 开始时间
	 * 
	 * @return start_time 开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 开始时间
	 * 
	 * @param startTime
	 *            开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 结束时间
	 * 
	 * @return end_time 结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 结束时间
	 * 
	 * @param endTime
	 *            结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 活动图片
	 * 
	 * @return image 活动图片
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 活动图片
	 * 
	 * @param image
	 *            活动图片
	 */
	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	/**
	 * 活动状态: 01未开始 02进行中 03已结束 04已删除
	 * 
	 * @return state 活动状态: 01未开始 02进行中 03已结束 04已删除
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 活动状态: 01未开始 02进行中 03已结束 04已删除
	 * 
	 * @param state
	 *            活动状态: 01未开始 02进行中 03已结束 04已删除
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 限制购买数(null为不限制)
	 * 
	 * @return order_limit 限制购买数(null为不限制)
	 */
	public Integer getOrderLimit() {
		return orderLimit;
	}

	/**
	 * 限制购买数(null为不限制)
	 * 
	 * @param orderLimit
	 *            限制购买数(null为不限制)
	 */
	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}

	/**
	 * 更新时间
	 * 
	 * @return update_time 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 创建时间
	 * 
	 * @return create_time 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public Kill getTransformBean(){
		if(StringUtils.isBlank(this.boutListJson)){
			return null;
		}
		JSONObject fromObject = JSONObject.fromObject(boutListJson);
		JSONArray jsonArray = fromObject.getJSONArray("json");
		List<FreshActivityCommon> list = new ArrayList<FreshActivityCommon>();
			for (Object object : jsonArray) {
				Map classMap=new HashMap(); 
				classMap.put("productJson", JsonToGroupBean.class);
				//使用暗示，直接将json解析为指定自定义对象，其中List完全解析,Map没有完全解析  
				JSONObject jsonObject = JSONObject.fromObject(object.toString());
				String[] dateFormats = new String[] {"yyyy-MM-dd HH:mm"};  
				JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats)); 
				KillBean<ActivityGroup> diyBean=(KillBean<ActivityGroup>) JSONObject.toBean(jsonObject,KillBean.class,classMap); 
				FreshActivityCommon freshActivityCommon = new FreshActivityCommon();
				freshActivityCommon.setBeginDate(diyBean.getBeginTime());
				freshActivityCommon.setEndDate(diyBean.getEndTime());
				freshActivityCommon.setImg(diyBean.getImage());
				freshActivityCommon.setTitle(diyBean.getTitle());
				freshActivityCommon.setType(3);
				freshActivityCommon.setSpikeId(diyBean.getSpikeId());
				freshActivityCommon.setOrderLimit(diyBean.getOrderLimit());
				freshActivityCommon.setCreateTime(new Date());
				freshActivityCommon.setUpdateTime(new Date());
				List<JsonToGroupBean<ActivityGroup>> productJson = diyBean.getProductJson();
				ArrayList<ActivityProduct> activityProducts = new ArrayList<ActivityProduct>();
				for (JsonToGroupBean<ActivityGroup> jsonToGroupBean : productJson) {
					ActivityProduct activityProduct = new ActivityProduct();
					activityProduct.setCodeId(new Long(jsonToGroupBean.getCodeId()));
					activityProduct.setSellIntegral(jsonToGroupBean.getIntegral());
					activityProduct.setSalePrice(jsonToGroupBean.getCash());
					activityProduct.setSort(jsonToGroupBean.getSort());
					activityProduct.setCreateTime(new Date());
					activityProduct.setUpdateTime(new Date());
					List<ActivityGroup> saleGroupList = jsonToGroupBean.getSaleGroupList();
					Integer store=saleGroupList.size()>0?0:jsonToGroupBean.getStore();
					List<ActivityGroup> activityGroups=new ArrayList<ActivityGroup>();
					for (Object saleGroup : saleGroupList) {
						DynaBean newDynaBean = (DynaBean)saleGroup;
						List<String> pvValues=(List<String>) newDynaBean.get("pvValues");
						JSONArray data = JSONArray.fromObject(pvValues);
						Integer object2 = new Integer(newDynaBean.get("maxStock").toString());
						ActivityGroup activityGroup = new ActivityGroup();
						activityGroup.setPvValue(com.xmniao.xmn.core.util.StringUtils.listToString(data, ','));
						activityGroup.setCodeId(Long.parseLong( newDynaBean.get("codeId").toString()));
						activityGroup.setPvIds((String) newDynaBean.get("pvIds"));
						activityGroup.setMaxStock(object2);
						activityGroup.setPvValues(pvValues);
						activityGroup.setAmount(new BigDecimal(newDynaBean.get("amount").toString()));
						activityGroup.setStock(new Integer((String)newDynaBean.get("stock").toString()));
						activityGroup.setSort(new Integer((String)newDynaBean.get("sort").toString()));
						activityGroups.add(activityGroup);
						store+=activityGroup.getStock();
					}
					activityProduct.setSellStore(store);
					activityProduct.setBeforeStore(store);
					activityProduct.setActivityGroups(activityGroups);
					activityProducts.add(activityProduct);
				}
				freshActivityCommon.setActivityProducts(activityProducts);
				list.add(freshActivityCommon);
			}
			this.activityCommons=list;
			return this;
	}
}