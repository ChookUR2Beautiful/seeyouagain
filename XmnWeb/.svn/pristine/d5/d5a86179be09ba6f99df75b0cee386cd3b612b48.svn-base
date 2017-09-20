package com.xmniao.xmn.core.fresh.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.SystemConstants;

/**
 *@ClassName:TBillFresh
 *@Description:生鲜订单表实体
 *@author hls
 *@date:2016年1月5日下午4:09:31
 */
public class TBillFresh extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2168463284551015566L;

	private Long bid;//订单编号
	
	private Integer uid;//用户id
	
	private Integer wareNum;//商品数量
	
	private String phoneid;//用户手机号
	
	private Double money;//消费总金额
	
	private Double profit; //返利支付
	
	private Double commision;//佣金支付
	
	private Double give_money;//赠送支付
	
	private Double cardpay;//会员卡支付
	
	private Double payment;//第三方支付
	
	private Integer status;//订单状态(用于接收sql查询出来的字段)
	private String bstatus;//订单状态(用于页面显示)
	
	private Integer wstatus;//物流状态(用于接收sql查询出来的字段)
	private String wlstatus;//物流状态(用于页面显示)
	
	private String logistics;//物流单号
	
	private Date sdate;//下单时间(用于接收sql查询出来的字段)
	private String xddate;//下单时间(用于页面显示)
	
	private String paytype;//支付方式
	
	private String payid;//支付ID
	
	private String number;//支付流水号
	
	private String tel;//联系电话
	
	private String mobile;//收货人联系电话
	
    private Date zdate;//支付时间(用于接收sql查询出来的字段)
    private String zfdate;//支付时间(用于页面显示)
    
    private Date ddate;//发货时间(用于接收sql查询出来的字段)
    private String fhdate;//发货时间(用于页面显示)
    
    private Date ydate;//收货时间(用于接收sql查询出来的字段)
    private String shdate;//收货时间(用于页面显示)
    
    private String express;//配送方式(物流公司代码)
    private String expressStr;//配送方式(物流公司名称)
    
    private String address;//配送地址
    
    private Integer noId;//会员卡编号
    
    private Date edate;
  /*
   * 2016-04-01新增字段 add by hls
   */
    private Double integral;//积分支付
    private Integer phoneType;//客户端类型(用于接收sql查询出来的字段)
    private String phoneTypeStr;//客户端类型(用于页面显示)
    private Double cuser;//现金卷支付金额
    private Double cdenom;//现金卷面额总数
    private String nname;//用户昵称

    /*
     * 2016-04-05新增字段 add by hls
     */
    private String username;//收货人姓名
    /*
     * 2016-04-06新增字段 add by hls
     */
    private List<TProductBill> productList;  
    private String productDetail;//商品详情
     
    /*
     * 2016-04-08新增字段 add by hls
     */
    private String exsdate;//导出起始时间
    private String exedate;//导出结束时间
    
	public String getExpressStr() {
		if("sfexpress".equals(express)) return "顺丰速递";
		if("zto".equals(express)) return "中通快递";
		if("yto".equals(express)) return "圆通快递";
		if("yunda".equals(express)) return "韵达快递";
		if("sto".equals(express)) return "申通快递";
		if("ttkdex".equals(express)) return "天天快递";
		if("jd".equals(express)) return "京东快递";
		return expressStr;
	}

	public void setExpressStr(String expressStr) {
		this.expressStr = expressStr;
	}

	public String getExsdate() {
		return exsdate;
	}

	public void setExsdate(String exsdate) {
		this.exsdate = exsdate;
	}

	public String getExedate() {
		return exedate;
	}

	public void setExedate(String exedate) {
		this.exedate = exedate;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public List<TProductBill> getProductList() {
		return productList;
	}

	public void setProductList(List<TProductBill> productList) {
		this.productList = productList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getCuser() {
		return cuser;
	}

	public void setCuser(Double cuser) {
		this.cuser = cuser;
	}

	public Double getCdenom() {
		return cdenom;
	}

	public void setCdenom(Double cdenom) {
		this.cdenom = cdenom;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public Integer getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(Integer phoneType) {
		this.phoneType = phoneType;
	}

	public String getPhoneTypeStr() {
		if(phoneType==null) return "--";
		if(phoneType==1) return "安卓";
		if(phoneType==2) return "Ios";
		if(phoneType==3) return "Wp";
		if(phoneType==4) return "微信服务号";
		if(phoneType==5) return "其他";
		return phoneTypeStr;
	}

	public void setPhoneTypeStr(String phoneTypeStr) {
		this.phoneTypeStr = phoneTypeStr;
	}



	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getWareNum() {
		return wareNum;
	}

	public void setWareNum(Integer wareNum) {
		this.wareNum = wareNum;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	public Double getGive_money() {
		return give_money;
	}

	public void setGive_money(Double give_money) {
		this.give_money = give_money;
	}

	public Double getCardpay() {
		return cardpay;
	}

	public void setCardpay(Double cardpay) {
		this.cardpay = cardpay;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBstatus() {
		if(status==null) return "--";
		if(status==0) return "待支付";
		if(status==1) return "已支付";
		if(status==2) return "取消订单";
		if(status==3) return "交易成功";
		if(status==4) return "用户退款处理中";
		if(status==5) return "退款成功";
		if(status==6) return "支付退款处理中";
		if(status==7) return "支付退款失败";
		if(status==8) return "平台退款处理中";
		return "--";
	}

	public void setBstatus(String bstatus) {
		this.bstatus = bstatus;
	}

	public Integer getWstatus() {
		return wstatus;
	}

	public void setWstatus(Integer wstatus) {
		this.wstatus = wstatus;
	}

	public String getWlstatus() {
		if(wstatus==null) return "--";
		if(wstatus==0) return "未发货";
		if(wstatus==1) return "已发货";
		if(wstatus==2) return "已收货";
		return "--";
	}

	public void setWlstatus(String wlstatus) {
		this.wlstatus = wlstatus;
	}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public String getXddate() {
		if(null!=sdate) return DateUtil.smartFormat(sdate);
		return "--";
	}

	public void setXddate(String xddate) {
		this.xddate = xddate;
	}

	public String getPaytype() {
		String payTypeText = SystemConstants.getPayTypeText(paytype);
		if(payTypeText.equals("")) return "--";
		return payTypeText;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getZdate() {
		return zdate;
	}

	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}

	public String getZfdate() {
		if(null!=zdate) return DateUtil.smartFormat(zdate);
		return "--";
	}

	public void setZfdate(String zfdate) {
		this.zfdate = zfdate;
	}

	public Date getDdate() {
		return ddate;
	}

	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}

	public String getFhdate() {
		if(null==ddate) return "--";
		return DateUtil.smartFormat(ddate);
	}

	public void setFhdate(String fhdate) {
		this.fhdate = fhdate;
	}

	public Date getYdate() {
		return ydate;
	}

	public void setYdate(Date ydate) {
		this.ydate = ydate;
	}

	public String getShdate() {
		if(null==ydate) return "--";
		return DateUtil.smartFormat(ydate);
	}

	public void setShdate(String shdate) {
		this.shdate = shdate;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNoId() {
		return noId;
	}

	public void setNoId(Integer noId) {
		this.noId = noId;
	}

	@Override
	public String toString() {
		return "TBillFresh [bid=" + bid + ", uid=" + uid + ", wareNum="
				+ wareNum + ", phoneid=" + phoneid + ", money=" + money
				+ ", profit=" + profit + ", commision=" + commision
				+ ", give_money=" + give_money + ", cardpay=" + cardpay
				+ ", payment=" + payment + ", status=" + status + ", bstatus="
				+ bstatus + ", wstatus=" + wstatus + ", wlstatus=" + wlstatus
				+ ", logistics=" + logistics + ", sdate=" + sdate + ", xddate="
				+ xddate + ", paytype=" + paytype + ", payid=" + payid
				+ ", number=" + number + ", tel=" + tel + ", mobile=" + mobile
				+ ", zdate=" + zdate + ", zfdate=" + zfdate + ", ddate="
				+ ddate + ", fhdate=" + fhdate + ", ydate=" + ydate
				+ ", shdate=" + shdate + ", express=" + express + ", address="
				+ address + ", noId=" + noId + ", edate=" + edate
				+ ", integral=" + integral + ", phoneType=" + phoneType
				+ ", phoneTypeStr=" + phoneTypeStr + ", cuser=" + cuser
				+ ", cdenom=" + cdenom + ", nname=" + nname + ", username="
				+ username + ", productList=" + productList
				+ ", productDetail=" + productDetail + ", exsdate=" + exsdate
				+ ", exedate=" + exedate + "]";
	}

	
	public static String expresstype(String express){
		if("sfexpress".equals(express)) return "顺丰速递";
		if("zto".equals(express)) return "中通快递";
		if("yto".equals(express)) return "圆通快递";
		if("yunda".equals(express)) return "韵达快递";
		if("sto".equals(express)) return "申通快递";
		if("ttkdex".equals(express)) return "天天快递";
		if("jd".equals(express)) return "京东快递";
		return "--";
	}
	


}
