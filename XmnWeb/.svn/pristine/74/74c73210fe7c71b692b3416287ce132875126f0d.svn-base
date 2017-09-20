package com.xmniao.xmn.core.marketingmanagement.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.SystemConstants;

public class TActivityPrize extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4822189992123160149L;
	
	private Integer pid;//主键
	private Integer aid;//活动ID
	private Integer prizeType;//派奖类型，1派送返利
	private Double prizeNum;//派奖数量
	private Integer uid;//用户ID
	private String nname;//用户昵称
	private String phone;//手机号码
	private Date ptime;//派奖时间
	private Integer piId;//对应信息ID
	private Integer sellerid;//商户ID
	private String sellername;//商户名称
	private String orderNo;//相关的订单编号
	private Integer status;//派送状态，0未派送，1已派送
	
	private String aname;//活动名称
	private String paytype;//支付方式
	private Double money;//支付金额
	private Double baseagio;//折扣
	private Date zdate;//支付时间
	private Date fdate;//分账到账时间
	
	private Date zdateStart;//支付时间查询条件
	private Date zdateEnd;//支付时间查询条件
	
	public String getPrizeTypeText(){
		return prizeType == 1 ? "派送返利" : "";
	}
	
	public String getStatusText(){
		return status == 0 ? "未派送" : "已派送";
	}
	
	public String getPaytypeText(){
		return SystemConstants.getPayTypeText(paytype);
	}
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}
	public Double getPrizeNum() {
		return prizeNum;
	}
	public void setPrizeNum(Double prizeNum) {
		this.prizeNum = prizeNum;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPtime() {
		return ptime;
	}
	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}
	public Integer getPiId() {
		return piId;
	}
	public void setPiId(Integer piId) {
		this.piId = piId;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getBaseagio() {
		return baseagio;
	}

	public void setBaseagio(Double baseagio) {
		this.baseagio = baseagio;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getZdate() {
		return zdate;
	}

	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public Date getZdateStart() {
		return zdateStart;
	}

	public void setZdateStart(Date zdateStart) {
		this.zdateStart = zdateStart;
	}

	public Date getZdateEnd() {
		return zdateEnd;
	}

	public void setZdateEnd(Date zdateEnd) {
		this.zdateEnd = zdateEnd;
	}

}
