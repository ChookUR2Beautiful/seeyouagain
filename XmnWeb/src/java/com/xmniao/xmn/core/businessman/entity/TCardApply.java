package com.xmniao.xmn.core.businessman.entity;

import com.xmniao.xmn.core.base.BaseEntity;

public class TCardApply extends BaseEntity {
	    private static final long serialVersionUID = -1953956845719382268L;
	    private Integer id;// 开通会员卡申请表ID
	    private Integer sellerid;// 商家ID
	    private String sellername;//商家名称
	    private Integer applytype;//申请类型
	    private String applytypeStr;//申请类型（用于详情页面显示）1 申请开通预付卡 2 申请修改会员卡说明
	    private String instructions;//使用说明(会员特权)
	    private String applydate;// 申请时间
	    private Integer applystatus;//审核状态
	    private String applystatusStr;//审核状态（用于详情页面显示）0 待审核 1 审核通过 2 审核不通过 3 已删除
	    private String reason;// 审核不通过理由
	    private Integer sellerNum;//适用商家数
	    private Integer assueid;//充值方案id
	    private String ids;//批量操作，申请ID
	    
	    private String udate;//更新时间
	    
	    private String optype;//操作类型1、通过 2、不通过
	    
	    
	    
	    
		
		public String getOptype() {
			return optype;
		}
		public void setOptype(String optype) {
			this.optype = optype;
		}
		public String getApplytypeStr() {
			if(applytype == 1) return "开通会员卡";
			if(applytype == 2) return "修改使用说明";
			return applytypeStr;
		}
		public void setApplytypeStr(String applytypeStr) {
			this.applytypeStr = applytypeStr;
		}
		public String getApplystatusStr() {
			if(applystatus == 0) return "待审核";
			if(applystatus == 1) return "审核通过";
			if(applystatus == 2) return "审核不通过";
			if(applystatus == 3) return "已删除";
			return applystatusStr;
		}
		public void setApplystatusStr(String applystatusStr) {
			this.applystatusStr = applystatusStr;
		}
		public String getUdate() {
			return udate;
		}
		public void setUdate(String udate) {
			this.udate = udate;
		}
		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}
		public Integer getAssueid() {
			return assueid;
		}
		public void setAssueid(Integer assueid) {
			this.assueid = assueid;
		}
		public Integer getSellerNum() {
			return sellerNum;
		}
		public void setSellerNum(Integer sellerNum) {
			this.sellerNum = sellerNum;
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
		public String getSellername() {
			return sellername;
		}
		public void setSellername(String sellername) {
			this.sellername = sellername;
		}
		public Integer getApplytype() {
			return applytype;
		}
		public void setApplytype(Integer applytype) {
			this.applytype = applytype;
		}
		public String getInstructions() {
			return instructions;
		}
		public void setInstructions(String instructions) {
			this.instructions = instructions;
		}
		public String getApplydate() {
			return applydate;
		}
		public void setApplydate(String applydate) {
			this.applydate = applydate;
		}
		public Integer getApplystatus() {
			return applystatus;
		}
		public void setApplystatus(Integer applystatus) {
			this.applystatus = applystatus;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		/**
		 * 列表展示，该行是否禁止复选框可选
		 */
		public boolean isDisableCheck() {
			return applystatus==0?false:true;
		}
		@Override
		public String toString() {
			return "TCardApply [id=" + id + ", sellerid=" + sellerid
					+ ", sellername=" + sellername + ", applytype=" + applytype
					+ ", applytypeStr=" + applytypeStr + ", instructions="
					+ instructions + ", applydate=" + applydate
					+ ", applystatus=" + applystatus + ", applystatusStr="
					+ applystatusStr + ", reason=" + reason + ", sellerNum="
					+ sellerNum + ", assueid=" + assueid + ", ids=" + ids
					+ ", udate=" + udate + ", optype=" + optype + "]";
		}
		
		
}
