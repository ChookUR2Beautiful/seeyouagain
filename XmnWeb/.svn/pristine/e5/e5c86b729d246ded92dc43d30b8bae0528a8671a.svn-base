package com.xmniao.xmn.core.businessman.entity;

import com.xmniao.xmn.core.base.BaseEntity;

public class TIssueCardApply extends BaseEntity {
	    private static final long serialVersionUID = -8054377333749493507L;
		private Integer id;// 充值方案申请表ID
	    private Integer batchId;// 发行编号 
	    private String batchName;//发行卡名称 如 100元VIP会员卡
	    private Integer sellerid;//商家ID
	    private String sellername;//商家名称
	    private Double price;//面额(原充值金额)
	    private Double retail;//售价(到账金额，指最终充值到会员卡的金额)
	    private Double cash;//结算价
	    private String statrperiod;//开始有效期 表示在日期范围内可以使用
	    private String endperiod;//结束有效期
	    private Integer number;// 发行数量
	    private Integer cardstatus;// 申请状态 0处理中 1已上线 2已下线
	    private String cardstatusStr;// 申请状态 0处理中 1已上线 2已下线(用于页面显示)
	    private String reason;//审核不通过原因
	    private Double amount;//已售卡面额
	    private Double profit;//售卡收益
	    private Integer num;// 本批次已卖卡数量
	    private String rdate;//发行时间
	    private String udate;//更新时间
	    private Integer dstatus;//数据状态 默认0 0正常 1已删除
	    private String dstatusStr;//数据状态 默认0 0正常 1已删除(用于页面显示)
	    private Integer isDefault;//默认充值方案 0 不是默认充值方案 1是充值默认充值方案
	    private String isDefaultStr;//默认充值方案 0 不是默认充值方案 1是充值默认充值方案
	    
	    private String optype;//操作类型1、通过 2、不通过
	    private String ids;//批量操作，申请ID
	    
	    
	    
		public String getIsDefaultStr() {
			if(isDefault == 0) return "否";
			if(isDefault == 1) return "是";
			return isDefaultStr;
		}
		public void setIsDefaultStr(String isDefaultStr) {
			this.isDefaultStr = isDefaultStr;
		}
		public String getDstatusStr() {
			if(dstatus == 0) return "正常";
			if(dstatus == 1) return "已删除";
			return dstatusStr;
		}
		public void setDstatusStr(String dstatusStr) {
			this.dstatusStr = dstatusStr;
		}
		public String getCardstatusStr() {
			if(cardstatus == 0) return "待审核";
			if(cardstatus == 1) return "审核通过";
			if(cardstatus == 3) return "审核不通过";
			return cardstatusStr;
		}
		public void setCardstatusStr(String cardstatusStr) {
			this.cardstatusStr = cardstatusStr;
		}
		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}
		public String getOptype() {
			return optype;
		}
		public void setOptype(String optype) {
			this.optype = optype;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getBatchId() {
			return batchId;
		}
		public void setBatchId(Integer batchId) {
			this.batchId = batchId;
		}
		public String getBatchName() {
			return batchName;
		}
		public void setBatchName(String batchName) {
			this.batchName = batchName;
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
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public Double getRetail() {
			return retail;
		}
		public void setRetail(Double retail) {
			this.retail = retail;
		}
		public Double getCash() {
			return cash;
		}
		public void setCash(Double cash) {
			this.cash = cash;
		}
		public String getStatrperiod() {
			return statrperiod;
		}
		public void setStatrperiod(String statrperiod) {
			this.statrperiod = statrperiod;
		}
		public String getEndperiod() {
			return endperiod;
		}
		public void setEndperiod(String endperiod) {
			this.endperiod = endperiod;
		}
		public void setRdate(String rdate) {
			this.rdate = rdate;
		}
		public void setUdate(String udate) {
			this.udate = udate;
		}
		public Integer getNumber() {
			return number;
		}
		public void setNumber(Integer number) {
			this.number = number;
		}
		public Integer getCardstatus() {
			return cardstatus;
		}
		public void setCardstatus(Integer cardstatus) {
			this.cardstatus = cardstatus;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public Double getProfit() {
			return profit;
		}
		public void setProfit(Double profit) {
			this.profit = profit;
		}
		public Integer getNum() {
			return num;
		}
		public void setNum(Integer num) {
			this.num = num;
		}
		
		public String getRdate() {
			return rdate;
		}
		public String getUdate() {
			return udate;
		}
		public Integer getDstatus() {
			return dstatus;
		}
		public void setDstatus(Integer dstatus) {
			this.dstatus = dstatus;
		}
		public Integer getIsDefault() {
			return isDefault;
		}
		public void setIsDefault(Integer isDefault) {
			this.isDefault = isDefault;
		}
		/**
		 * 列表展示，该行是否禁止复选框可选
		 */
		public boolean isDisableCheck() {
			return cardstatus==0?false:true;
		}
		@Override
		public String toString() {
			return "TIssueCardApply [id=" + id + ", batchId=" + batchId
					+ ", batchName=" + batchName + ", sellerid=" + sellerid
					+ ", sellername=" + sellername + ", price=" + price
					+ ", retail=" + retail + ", cash=" + cash
					+ ", statrperiod=" + statrperiod + ", endperiod="
					+ endperiod + ", number=" + number + ", cardstatus="
					+ cardstatus + ", reason=" + reason + ", amount=" + amount
					+ ", profit=" + profit + ", num=" + num + ", rdate="
					+ rdate + ", udate=" + udate + ", dstatus=" + dstatus
					+ ", isDefault=" + isDefault + ", optype=" + optype + "]";
		}
		
		
}
