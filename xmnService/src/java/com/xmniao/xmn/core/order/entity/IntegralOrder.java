package com.xmniao.xmn.core.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.util.DateUtil;

/**
 * 积分兑换订单详情
 * @author yhl
 * 2016年6月28日15:14:361
 * */
public class IntegralOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7789132926922629314L;
	
		//主键ID
	   private int boid;
	   
	   //'订单bid',
	   private Long bid; 
	   
	   //'是否爆品，1是，0不是',
	   private int isbargain; 
	   
	   //'爆品id或菜品ID',
	   private Long bpid; 
	   
	   //'爆品或菜品名称',
	   private String pname;
	   
	   //'爆品价格',
	   private BigDecimal price;
	   
	   //'数量',
	   private int num; 
	   
	   //'记录时间',
	   private Date sdate; 
	   
	   //'记录时间字符串',
	   private String sdateStr; 
	   
	   //到期时间
	   private Date endTime;
	   
	   //到期时间字符串
	   private String endTimeStr;
	   
	   //'备注',
	   private String remark; 
	   
	   //'实际金额支付',
	   private BigDecimal amounts;
	   
	   //double(10,2) DEFAULT '0.00',
	   private double integral;
	   
	   //'0 待支付  1 已支付',
	   private int status; 
	   
	   //'订单总金额(未用字段)',
	   private BigDecimal all_amount; 
	   
	   //'兑换状态；0 未兑换；1 已兑换',
	   private int bstatus; 
	   
	   //'用户ID',
	   private Long uid; 
	   
	   //'商家ID',
	   private Long sellerid; 
	   
	   //'用户昵称',
	   private String uname;
	   
	   //'用户手机号',
	   private String phoneid;
	   
	   //'商户名',
	   private String sellername;
	   
	   //'订单消费码，8位长度数字。两位日加6位随机数，如果第一位为0则最后一位补0',
	   private int codeid;
	   
	   //'分账明细',
	   private String commission;
	   
	   //'会员所属商家',
	   private int genussellerid;
	   
	   //'会员所属商家名称',
	   private String genusname;
	   
	   //'所属合作商',
	   private int consume_jointid;
	   
	   //'所属合作商名称',
	   private String consume_jointname;
	   
	   //'分账状态',
	   private int hstatus;
	   
	   //商户logo
	   private String breviary;
	   
	   private Integer remindExpress;
	   

		public int getBoid() {
			return boid;
		}
	
		public void setBoid(int boid) {
			this.boid = boid;
		}
	
		public Long getBid() {
			return bid;
		}
	
		public void setBid(Long bid) {
			this.bid = bid;
		}
	
		public int getIsbargain() {
			return isbargain;
		}
	
		public void setIsbargain(int isbargain) {
			this.isbargain = isbargain;
		}
	
		public Long getBpid() {
			return bpid;
		}
	
		public void setBpid(Long bpid) {
			this.bpid = bpid;
		}
	
		public String getPname() {
			return pname;
		}
	
		public void setPname(String pname) {
			this.pname = pname;
		}
	
		public BigDecimal getPrice() {
			return price;
		}
	
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
	
		public int getNum() {
			return num;
		}
	
		public void setNum(int num) {
			this.num = num;
		}
	
		public Date getSdate() {
			return sdate;
		}
	
		public void setSdate(Date sdate) {
			this.sdate = sdate;
		}
	
		public String getRemark() {
			return remark;
		}
	
		public void setRemark(String remark) {
			this.remark = remark;
		}
	
	
		public BigDecimal getAmounts() {
			return amounts;
		}

		public void setAmounts(BigDecimal amounts) {
			this.amounts = amounts;
		}

		public double getIntegral() {
			return integral;
		}
	
		public void setIntegral(double integral) {
			this.integral = integral;
		}
	
		public int getStatus() {
			return status;
		}
	
		public void setStatus(int status) {
			this.status = status;
		}
	
		public BigDecimal getAll_amount() {
			return all_amount;
		}
	
		public void setAll_amount(BigDecimal all_amount) {
			this.all_amount = all_amount;
		}
	
		public int getBstatus() {
			return bstatus;
		}
	
		public void setBstatus(int bstatus) {
			this.bstatus = bstatus;
		}
	
		public Long getUid() {
			return uid;
		}
	
		public void setUid(Long uid) {
			this.uid = uid;
		}
	
		public Long getSellerid() {
			return sellerid;
		}
	
		public void setSellerid(Long sellerid) {
			this.sellerid = sellerid;
		}
	
		public String getUname() {
			return uname;
		}
	
		public void setUname(String uname) {
			this.uname = uname;
		}
	
		public String getPhoneid() {
			return phoneid;
		}
	
		public void setPhoneid(String phoneid) {
			this.phoneid = phoneid;
		}
	
		public String getSellername() {
			return sellername;
		}
	
		public void setSellername(String sellername) {
			this.sellername = sellername;
		}
	
		public int getCodeid() {
			return codeid;
		}
	
		public void setCodeid(int codeid) {
			this.codeid = codeid;
		}
	
		public String getCommission() {
			return commission;
		}
	
		public void setCommission(String commission) {
			this.commission = commission;
		}
	
		public int getGenussellerid() {
			return genussellerid;
		}
	
		public void setGenussellerid(int genussellerid) {
			this.genussellerid = genussellerid;
		}
	
		public String getGenusname() {
			return genusname;
		}
	
		public void setGenusname(String genusname) {
			this.genusname = genusname;
		}
	
		public int getConsume_jointid() {
			return consume_jointid;
		}
	
		public void setConsume_jointid(int consume_jointid) {
			this.consume_jointid = consume_jointid;
		}
	
		public String getConsume_jointname() {
			return consume_jointname;
		}
	
		public void setConsume_jointname(String consume_jointname) {
			this.consume_jointname = consume_jointname;
		}
	
		public int getHstatus() {
			return hstatus;
		}
	
		public void setHstatus(int hstatus) {
			this.hstatus = hstatus;
		}
	
		public String getBreviary() {
			return breviary;
		}

		public void setBreviary(String breviary) {
			this.breviary = breviary;
		}

		public String getSdateStr() {
			if (sdate == null) {
				return " ";
			}else {
				return DateUtil.format(sdate, DateUtil.defaultSimpleFormater);
			}
			
		}

		public void setSdateStr(String sdateStr) {
			this.sdateStr = sdateStr;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		
		public String getEndTimeStr() {
			if (endTime == null) {
				return "";
			}else {
				return DateUtil.format(endTime, DateUtil.daySimpleFormater);
			}
		}

		public void setEndTimeStr(String endTimeStr) {
			this.endTimeStr = endTimeStr;
		}

		public Integer getRemindExpress() {
			return remindExpress;
		}

		public void setRemindExpress(Integer remindExpress) {
			this.remindExpress = remindExpress;
		}
		
		

}
