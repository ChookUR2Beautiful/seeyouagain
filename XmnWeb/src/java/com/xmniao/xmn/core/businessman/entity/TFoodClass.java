package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：TFoodClass
 * 
 * @类描述： 商家菜品分类表
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年7月6日 上午10:34:28
 * 
 */
public class TFoodClass extends BaseEntity {

	private static final long serialVersionUID = -6769109702256987607L;

	private Integer fid;// 类别id
	private Integer sellerId;// 商家编号
	private String className;// 类别名称
	private Integer status;// 分类状态：1可用，2已删除(标记已删除的数据其分类及分类下的菜品不显示)
	private Integer foodNum;// 菜品数量
	private Date sdate;// 创建时间

	private String sellerName;// 商家名称

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFoodNum() {
		return foodNum;
	}

	public void setFoodNum(Integer foodNum) {
		this.foodNum = foodNum;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	@Override
	public String toString() {
		return "TFoodClass [fid=" + fid + ", sellerId=" + sellerId
				+ ", className=" + className + ", status=" + status
				+ ", foodNum=" + foodNum + ", sdate=" + sdate + ", sellerName="
				+ sellerName + "]";
	}
}
