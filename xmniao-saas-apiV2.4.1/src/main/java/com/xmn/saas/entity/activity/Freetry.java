package com.xmn.saas.entity.activity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 免费尝新
 * 
 * @author jianming
 *
 */
public class Freetry extends Activity {
	private Integer id;

	private Integer sellerid;

	private String name; // 免费尝新名称
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate; // 开始日期;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate; // 结束日期

	private Date beginTime;

	private Date endTime;  //终止时间

	private Integer giveNumber; // 赠品数量

	private Integer joinNumber; // 参加人数

	private Double awardProportion; // 获奖几率


	private Integer prizeDrawNumber; // 每人可抽次数

	/**
	 * （0：不限制，1:限领一次)',
	 */
	private Integer limitNumber; // 是否限领一次

	/**
	 * 0.不重设 1.分享获得次数，通过分享获得一次抽奖机会 2.每天重置次数，通过每天0点重置抽取奖品次数 3.消费获得次数，通过消费获得一次抽奖机会
	 */
	private Integer setCondition; // 设置条件

	private String setConditionToString; // 设置条件中文格式

	private Integer views;

	private Integer status;

	private Integer newuserNum;

	private Date createTime;

	private Integer takeNum;
	
	private Integer useNumber;

	private AwardRelation[] awardRelations; // 礼物数组

	// json格式的礼物数组
	private String sellerCouponDetailsJson;

	private Integer activityType = 1; // 活动类型 1.免尝 2.大转盘 3.秒杀
	
	private float giveProportion;

	public  Integer  getActivityType() {
		return activityType;
	}
	
	public float getGiveProportion() {
		if(giveAwardCount==null||this.giveAwardCount==0){
			return 0f;
		}
		return (float)giveAwardCount/(float)joinNumber;
	}
	
	public void setGiveProportion(float giveProportion) {
		this.giveProportion = giveProportion;
	}

	public String getSellerCouponDetailsJson() {
		// 将每个vehicle对象拼接为json格式的对象,用于命令下发
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(awardRelations);
			return json.toString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// 用于命令下发
		return "";
	}

	public String getSetConditionToString() {
		if (this.setCondition != null) {
			switch (this.setCondition) {
			case 0:
				return "不重设";
			case 1:
				return "分享获得";
			case 2:
				return "每天重置";
			case 3:
				return "消费获得";
			default:
				break;
			}
		}
		return setConditionToString;
	}
	
	
	public Integer getUseNumber() {
		return useNumber;
	}

	public void setUseNumber(Integer useNumber) {
		this.useNumber = useNumber;
	}

	public void setSetConditionToString(String setConditionToString) {
		this.setConditionToString = setConditionToString;
	}

	public AwardRelation[] getAwardRelations() {
		return awardRelations;
	}

	public void setAwardRelations(AwardRelation[] awardRelations) {
		this.awardRelations = awardRelations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getGiveNumber() {
		return giveNumber;
	}

	public void setGiveNumber(Integer giveNumber) {
		this.giveNumber = giveNumber;
	}

	public Integer getJoinNumber() {
		return joinNumber;
	}

	public void setJoinNumber(Integer joinNumber) {
		this.joinNumber = joinNumber;
	}

	public Double getAwardProportion() {
		return awardProportion;
	}

	public void setAwardProportion(Double awardProportion) {
		this.awardProportion = awardProportion;
	}

	public Integer getPrizeDrawNumber() {
		return prizeDrawNumber;
	}

	public void setPrizeDrawNumber(Integer prizeDrawNumber) {
		this.prizeDrawNumber = prizeDrawNumber;
	}

	public Integer getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}

	public Integer getSetCondition() {
		return setCondition;
	}

	public void setSetCondition(Integer setCondition) {
		this.setCondition = setCondition;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNewuserNum() {
		return newuserNum;
	}

	public void setNewuserNum(Integer newuserNum) {
		this.newuserNum = newuserNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTakeNum() {
		return takeNum;
	}

	public void setTakeNum(Integer takeNum) {
		this.takeNum = takeNum;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	
}