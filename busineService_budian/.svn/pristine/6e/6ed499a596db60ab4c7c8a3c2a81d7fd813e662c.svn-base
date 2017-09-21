package com.xmniao.domain.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 名称:  订单信息表
 * 描述: 
 * 类型: JAVA
 * 最近修改时间:2016-06-04 12:07:59<br>
 * @since  2016-06-04 12:07:59
 * @author admin
 */
@SuppressWarnings("serial")
public class BillEntity   implements Serializable{
	
	private Long bid;                    // 订单编号，12位长度。如：14 1015  111111。格式6位年月日加6为顺序数',		ledger:  id,                        
	private int uid;                     // 用户ID',													ledger:  memberid,                  
	private String nname;                // 用户昵称',													ledger:  membername,
	
	private int genussellerid;           // 所属商家ID',												ledger:  genussellerid,                       
	private String genusname;            // 所属商家名称',												ledger:  genusname,
	
	private int jointid;                 // 所属商家所属合作商ID',										ledger:  bpartnerid,                                
	private String corporate;            // 所属商家所属合作商名称',										ledger:  bpartnername, 
	
	private int sellerid;                // 消费商家ID',												ledger:  sellerid,                                 
	private String sellername;           // 消费商家名称',												ledger:  sellername, 
	
	private int consume_jointid;      	 // 经销商(消费商家所属合作商ID),									ledger:  cpartnerid,                        
	private String consume_corporate;    // 经销商(消费商家所属合作商名称),									ledger:  cpartnername,
	
	private Integer mikeid;				 	 // 寻蜜客ID',												ledger:  mikeid,          
	private String mikename;			 // 寻蜜客名称',												ledger:  mikename,
	
	
	private Integer parentMikeId;		 // 一级寻蜜客ID 												ledger:  parentMikeId,  
	private String  parentMikeName;		 // 一级寻蜜客名称												ledger:  parentMikeName,  
	
	private Integer topMikeId;			 // 二级寻蜜客ID												ledger:  topMikeId,  
	private String  topMikeName;		 // 二级寻蜜客名称												ledger:  topMikeName, 
	
	
	
	private double baseagio;             // 下单折扣，下单时商家所执行的折扣',									ledger:  discount,             
	private String number="123131123";   // 支付流水号',												ledger:  paycode, 
	private String payid;                // 支付ID，支付接口产生 用于查询支付记录使用',							ledger:  payid,                   
	private int paytype;              	 // 支付方式',													ledger:  paytype, 
	private int area;                 	 // 所消费地区的编号 如南山区 的编号',									ledger:  areaid,                   
	private int mike_type;            	 // 向蜜客类型  0 未绑定任何向蜜客  1 用户向蜜客 2中脉寻蜜客     3用户向蜜客(过期)',	ledger:  miketype, 
	private double money;                // 消费总金额',												ledger:  expense,      
	private double ledger_money;         // 订单可分账金额,												ledger:  ,2016年7月4日14:28:49新加    
	private double rebate;               // 本单可返利金额',												ledger:  rebate,
	private int is_virtual=1;            // 是否虚拟订单，默认为0 不是虚拟订单   1虚拟订单,							ledger:  isfictitious,       
	private double flat_agio;            // 平台补贴占比，默认为0  平台做促销活动时此字段表示平台给用户的折扣补贴',			ledger:  subsidy,             
	private double flat_money;           // 平台补贴金额												ledger:  subsidy_money,      
	private Date zdate;                	 // 支付时间                                                                         						 	ledger:  zdate,                   
	private double profit;               // 返利支付金额                                                                     						 	ledger:  profit,                 
	private double commision;            // 佣金支付金额                                                                     						 	ledger:  commision,           
	private double payment;              // 需支付金额                                                                       						 	ledger:  payment,               
	private double give_money;           // 赠送支付金额                                                                     						 	ledger:  givemoney,          
	private double cuser;                // 优惠券支付金额                                                                   						 	ledger:  cuser,                   
	private double cdenom;               // 优惠卷面额总数                                                                   						 	ledger:  cdenom,                 
	private double ratio;                // 佣金补贴比例(商户补贴比例)                                   	ledger:  ratio_subsidy,                     
	private double ratio_money;          // 佣金补贴金额                                                                     						 	ledger:  ratio_money                       
	private int status;				 	 // 订单状态                                                                        						 	ledger:        
	private int hstatus; 			 	 // 审核状态                                                                        						 	ledger:                            
	private String commission;           // 分账情况Json   											ledger:commisson 																							ledger:  commission, 
	private String proportion;			 // 分账比例
	private int windcontrol;			//风控
	private int cType;					//优惠券类型 1 平台 2商家
	private String uid_mb_ecno;			//会员脉宝EC号
	private String saas_channel;		//寻蜜客签约商家SAAS来源 null为非SAAS签约商户 1 常规SAAS签约 2脉客SAAS签约 3V客SAAS签约
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
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
	public int getJointid() {
		return jointid;
	}
	public void setJointid(int jointid) {
		this.jointid = jointid;
	}
	public String getCorporate() {
		return corporate;
	}
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public int getConsume_jointid() {
		return consume_jointid;
	}
	public void setConsume_jointid(int consume_jointid) {
		this.consume_jointid = consume_jointid;
	}
	public String getConsume_corporate() {
		return consume_corporate;
	}
	public void setConsume_corporate(String consume_corporate) {
		this.consume_corporate = consume_corporate;
	}
	public Integer getMikeid() {
		return mikeid;
	}
	public void setMikeid(Integer mikeid) {
		this.mikeid = mikeid;
	}
	public String getMikename() {
		return mikename;
	}
	public void setMikename(String mikename) {
		this.mikename = mikename;
	}
	public Integer getParentMikeId() {
		return parentMikeId;
	}
	public void setParentMikeId(Integer parentMikeId) {
		this.parentMikeId = parentMikeId;
	}
	public String getParentMikeName() {
		return parentMikeName;
	}
	public void setParentMikeName(String parentMikeName) {
		this.parentMikeName = parentMikeName;
	}
	public Integer getTopMikeId() {
		return topMikeId;
	}
	public void setTopMikeId(Integer topMikeId) {
		this.topMikeId = topMikeId;
	}
	public String getTopMikeName() {
		return topMikeName;
	}
	public void setTopMikeName(String topMikeName) {
		this.topMikeName = topMikeName;
	}
	public double getBaseagio() {
		return baseagio;
	}
	public void setBaseagio(double baseagio) {
		this.baseagio = baseagio;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getMike_type() {
		return mike_type;
	}
	public void setMike_type(int mike_type) {
		this.mike_type = mike_type;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getRebate() {
		return rebate;
	}
	public void setRebate(double rebate) {
		this.rebate = rebate;
	}
	public int getIs_virtual() {
		return is_virtual;
	}
	public void setIs_virtual(int is_virtual) {
		this.is_virtual = is_virtual;
	}
	public double getFlat_agio() {
		return flat_agio;
	}
	public void setFlat_agio(double flat_agio) {
		this.flat_agio = flat_agio;
	}
	public double getFlat_money() {
		return flat_money;
	}
	public void setFlat_money(double flat_money) {
		this.flat_money = flat_money;
	}
	public Date getZdate() {
		return zdate;
	}
	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public double getGive_money() {
		return give_money;
	}
	public void setGive_money(double give_money) {
		this.give_money = give_money;
	}
	public double getCuser() {
		return cuser;
	}
	public void setCuser(double cuser) {
		this.cuser = cuser;
	}
	public double getCdenom() {
		return cdenom;
	}
	public void setCdenom(double cdenom) {
		this.cdenom = cdenom;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	public double getRatio_money() {
		return ratio_money;
	}
	public void setRatio_money(double ratio_money) {
		this.ratio_money = ratio_money;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getHstatus() {
		return hstatus;
	}
	public void setHstatus(int hstatus) {
		this.hstatus = hstatus;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	public double getLedger_money() {
		return ledger_money;
	}
	public void setLedger_money(double ledger_money) {
		this.ledger_money = ledger_money;
	}
	
	public int getWindcontrol() {
		return windcontrol;
	}
	public void setWindcontrol(int windcontrol) {
		this.windcontrol = windcontrol;
	}
	
	public int getcType() {
		return cType;
	}
	public void setcType(int cType) {
		this.cType = cType;
	}
	
	
	/**
	 * @return the uid_mb_ecno
	 */
	public String getUid_mb_ecno() {
		return uid_mb_ecno;
	}
	/**
	 * @param uid_mb_ecno the uid_mb_ecno to set
	 */
	public void setUid_mb_ecno(String uid_mb_ecno) {
		this.uid_mb_ecno = uid_mb_ecno;
	}
	/**
	 * @return the saas_channel
	 */
	public String getSaas_channel() {
		return saas_channel;
	}
	/**
	 * @param saas_channel the saas_channel to set
	 */
	public void setSaas_channel(String saas_channel) {
		this.saas_channel = saas_channel;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BillEntity [bid=" + bid + ", uid=" + uid + ", nname=" + nname
				+ ", genussellerid=" + genussellerid + ", genusname="
				+ genusname + ", jointid=" + jointid + ", corporate="
				+ corporate + ", sellerid=" + sellerid + ", sellername="
				+ sellername + ", consume_jointid=" + consume_jointid
				+ ", consume_corporate=" + consume_corporate + ", mikeid="
				+ mikeid + ", mikename=" + mikename + ", parentMikeId="
				+ parentMikeId + ", parentMikeName=" + parentMikeName
				+ ", topMikeId=" + topMikeId + ", topMikeName=" + topMikeName
				+ ", baseagio=" + baseagio + ", number=" + number + ", payid="
				+ payid + ", paytype=" + paytype + ", area=" + area
				+ ", mike_type=" + mike_type + ", money=" + money
				+ ", ledger_money=" + ledger_money + ", rebate=" + rebate
				+ ", is_virtual=" + is_virtual + ", flat_agio=" + flat_agio
				+ ", flat_money=" + flat_money + ", zdate=" + zdate
				+ ", profit=" + profit + ", commision=" + commision
				+ ", payment=" + payment + ", give_money=" + give_money
				+ ", cuser=" + cuser + ", cdenom=" + cdenom + ", ratio="
				+ ratio + ", ratio_money=" + ratio_money + ", status=" + status
				+ ", hstatus=" + hstatus + ", commission=" + commission
				+ ", proportion=" + proportion + ", windcontrol=" + windcontrol
				+ ", cType=" + cType + ", uid_mb_ecno=" + uid_mb_ecno
				+ ", saas_channel=" + saas_channel + "]";
	}
}