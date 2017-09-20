package com.xmniao.xmn.core.billmanagerment.entity;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 *@ClassName:BillBargain
 *@Description:爆品订单实体类
 *@author hls
 *@date:2016年5月5日下午2:26:16
 */
public class BillBargain extends BaseEntity {
	private static final long serialVersionUID = 4444455134955603457L;
	private Integer boid;//主键id
	private String bid;//订单id
	private Integer isbargain;//是否爆品1.是 0.不是
	private Integer bpid;//爆品id(菜品id)
	private String pname;//爆品名称
	private Double price;//爆品价格
	private Double pricelow;//爆品起始价格
	private Double pricetop;//爆品结束价格
	private Integer num;//数量
	private Date sdate;//记录时间
	private String sdateStr;//记录时间（用于显示）
	private Date startdate;//记录起始时间
	private Date enddate;//记录结束时间
	private String remark;//备注
	private Double amount;//实际金额支付金额
	private Double integral;//实际积分支付金额
	private Integer status;//支付状态 0 待支付 1 已支付
	private String statusStr;//支付状态 0 待支付 1 已支付(用于页面展示)
	private Double allAmount;//订单总金额(未用字段)
	private Integer bstatus;//兑换状态；0 未兑换；1 已兑换
	private String bstatusStr;//兑换状态；0 未兑换；1 已兑换(用于页面展示)
	private Integer uid;//用户ID
	private Integer sellerid;//商家编号
	//2016-05-10 新增字段
	private String sellername;//商家名称
	private String uname;//用户昵称
	private String phoneid;//用户手机号
	//2016-05-28 新增字段 add by hls
	private String commission;//分账明细
	private Integer hstatus;//分账状态
	//2016-05-29新增字段 add by hls
	private Double balance;//余额支付金额
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	/**
	 * 解析线下积分商品订单（t_bill_bargan）分账明细 add by hls
	 */
	//分账后剩余金额（平台分账金额）xmnaioMoney
	public String getXmnaioMoney(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("xmnaioMoney");
		}
		return str;
	}
	//用户所属商户分账金额bSerllerMoney
	public String getBSerllerMoney(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("bSerllerMoney");
		}
		return str;
	}
	//经销商分账金额sellerAreaMoney
	public String getSellerAreaMoney(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("sellerAreaMoney");
		}
		return str;
	}
	//商品成本sellerBackMoney
	public String getSellerBackMoney(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("sellerBackMoney");
		}
		return str;
	}
	
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public Integer getHstatus() {
		return hstatus;
	}
	public void setHstatus(Integer hstatus) {
		this.hstatus = hstatus;
	}
	public String getHstatusText(){
		if(hstatus == null) return "";
		switch (hstatus) {
		case 0:
			return "未分账";
		case 1:
			return "结算通过";
		case 2:
			return "结算未通过";
		case 3:
			return "结算争议";
		case 4:
			return "核算通过";
		case 5:
			return "核算不通过";
		case 6:
			return "核算争议";
		case 7:
			return "财务通过";
		case 8:
			return "财务不通过";
		case 9:
			return "已分账";
		case 10:
			return "分账处理中【触发自动分账】";
		case 11:
			return "分账处理中【自动转手动处理中】";
		default:
			return "";
		}
	}
	public String getStatusStr() {
		if(status==0) return "待支付";
		if(status==1) return "已支付";
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getBstatusStr() {
		if(bstatus==0) return "未兑换";
		if(bstatus==1) return "已兑换";
		return bstatusStr;
	}
	public void setBstatusStr(String bstatusStr) {
		this.bstatusStr = bstatusStr;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
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
	public Double getPricelow() {
		return pricelow;
	}
	public void setPricelow(Double pricelow) {
		this.pricelow = pricelow;
	}
	public Double getPricetop() {
		return pricetop;
	}
	public void setPricetop(Double pricetop) {
		this.pricetop = pricetop;
	}
	public String getSdateStr() {
		if(null!=sdate) return DateUtil.smartFormat(sdate);
		return sdateStr;
	}
	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Integer getBoid() {
		return boid;
	}
	public void setBoid(Integer boid) {
		this.boid = boid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public Integer getIsbargain() {
		return isbargain;
	}
	public void setIsbargain(Integer isbargain) {
		this.isbargain = isbargain;
	}
	public Integer getBpid() {
		return bpid;
	}
	public void setBpid(Integer bpid) {
		this.bpid = bpid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getIntegral() {
		return integral;
	}
	public void setIntegral(Double integral) {
		this.integral = integral;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Double getAllAmount() {
		return allAmount;
	}
	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}
	public Integer getBstatus() {
		return bstatus;
	}
	public void setBstatus(Integer bstatus) {
		this.bstatus = bstatus;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	@Override
	public String toString() {
		return "BillBargain [boid=" + boid + ", bid=" + bid + ", isbargain="
				+ isbargain + ", bpid=" + bpid + ", pname=" + pname
				+ ", price=" + price + ", pricelow=" + pricelow + ", pricetop="
				+ pricetop + ", num=" + num + ", sdate=" + sdate
				+ ", sdateStr=" + sdateStr + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", remark=" + remark + ", amount="
				+ amount + ", integral=" + integral + ", status=" + status
				+ ", statusStr=" + statusStr + ", allAmount=" + allAmount
				+ ", bstatus=" + bstatus + ", bstatusStr=" + bstatusStr
				+ ", uid=" + uid + ", sellerid=" + sellerid + ", sellername="
				+ sellername + ", uname=" + uname + ", phoneid=" + phoneid
				+ ", commission=" + commission + ", hstatus=" + hstatus
				+ ", balance=" + balance + "]";
	}
	
}
