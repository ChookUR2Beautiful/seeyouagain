package com.xmniao.xmn.core.vstar.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

public class TicketsOrder extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * t_vstar_tickets主键
     */
    private Integer activityId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 会员编号
     */
    private Integer uid;

    /**
     * 会员昵称
     */
    private String nname;

    /**
     * 会员手机号
     */
    private String phone;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 订单总价
     */
    private BigDecimal totalAmount;

    /**
     * 支付状态 0.待支付 1.已支付 2.已取消 3.已失败
     */
    private Integer payState;

    /**
     * 支付编号
     */
    private String payId;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 钱包支付金额
     */
    private BigDecimal balance;

    /**
     * 第三方支付金额
     */
    private BigDecimal cash;

    /**
     * 鸟币支付金额
     */
    private BigDecimal coin;

    /**
     * 优惠总金额
     */
    private BigDecimal preferential;

    /**
     * 下单时间
     */
    private Date createTime;

    /**
     * 支付/取消时间
     */
    private Date payTime;

    /**
     * 订单操作描述
     */
    private String description;

    /**
     * 参会人姓名
     */
    private String guestName;

    /**
     * 参会人联系电话
     */
    private String guestPhone;

    /**
     * 参会人所属公司/团体
     */
    private String guestGroup;
    
    
    private String payTimeStr;
    
    private Integer detailNo;
    
    public Integer getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(Integer detailNo) {
		this.detailNo = detailNo;
	}

	public String getPayTimeStr() {
    	if(payTime==null){
    		return "";
    	}
    	return DateUtil.formatDate(payTime, DateUtil.Y_M_D_HMS);
	}

	public void setPayTimeStr(String payTimeStr) {
		this.payTimeStr = payTimeStr;
	}

	private String title;
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * t_vstar_tickets主键
     * @return activity_id t_vstar_tickets主键
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * t_vstar_tickets主键
     * @param activityId t_vstar_tickets主键
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 订单编号
     * @return order_no 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 订单编号
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 会员编号
     * @return uid 会员编号
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 会员编号
     * @param uid 会员编号
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 会员昵称
     * @return nname 会员昵称
     */
    public String getNname() {
        return nname;
    }

    /**
     * 会员昵称
     * @param nname 会员昵称
     */
    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    /**
     * 会员手机号
     * @return phone 会员手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 会员手机号
     * @param phone 会员手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 购买数量
     * @return num 购买数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 购买数量
     * @param num 购买数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 订单总价
     * @return total_amount 订单总价
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 订单总价
     * @param totalAmount 订单总价
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 支付状态 0.待支付 1.已支付 2.已取消 3.已失败
     * @return pay_state 支付状态 0.待支付 1.已支付 2.已取消 3.已失败
     */
    public Integer getPayState() {
        return payState;
    }

    /**
     * 支付状态 0.待支付 1.已支付 2.已取消 3.已失败
     * @param payState 支付状态 0.待支付 1.已支付 2.已取消 3.已失败
     */
    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    /**
     * 支付编号
     * @return pay_id 支付编号
     */
    public String getPayId() {
        return payId;
    }

    /**
     * 支付编号
     * @param payId 支付编号
     */
    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    /**
     * 支付方式
     * @return pay_type 支付方式
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 支付方式
     * @param payType 支付方式
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 钱包支付金额
     * @return balance 钱包支付金额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 钱包支付金额
     * @param balance 钱包支付金额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 第三方支付金额
     * @return cash 第三方支付金额
     */
    public BigDecimal getCash() {
        return cash;
    }

    /**
     * 第三方支付金额
     * @param cash 第三方支付金额
     */
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    /**
     * 鸟币支付金额
     * @return coin 鸟币支付金额
     */
    public BigDecimal getCoin() {
        return coin;
    }

    /**
     * 鸟币支付金额
     * @param coin 鸟币支付金额
     */
    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }

    /**
     * 优惠总金额
     * @return preferential 优惠总金额
     */
    public BigDecimal getPreferential() {
        return preferential;
    }

    /**
     * 优惠总金额
     * @param preferential 优惠总金额
     */
    public void setPreferential(BigDecimal preferential) {
        this.preferential = preferential;
    }

    /**
     * 下单时间
     * @return create_time 下单时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 下单时间
     * @param createTime 下单时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 支付/取消时间
     * @return pay_time 支付/取消时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 支付/取消时间
     * @param payTime 支付/取消时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 订单操作描述
     * @return description 订单操作描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 订单操作描述
     * @param description 订单操作描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 参会人姓名
     * @return guest_name 参会人姓名
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * 参会人姓名
     * @param guestName 参会人姓名
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName == null ? null : guestName.trim();
    }

    /**
     * 参会人联系电话
     * @return guest_phone 参会人联系电话
     */
    public String getGuestPhone() {
        return guestPhone;
    }

    /**
     * 参会人联系电话
     * @param guestPhone 参会人联系电话
     */
    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone == null ? null : guestPhone.trim();
    }

    /**
     * 参会人所属公司/团体
     * @return guest_group 参会人所属公司/团体
     */
    public String getGuestGroup() {
        return guestGroup;
    }

    /**
     * 参会人所属公司/团体
     * @param guestGroup 参会人所属公司/团体
     */
    public void setGuestGroup(String guestGroup) {
        this.guestGroup = guestGroup == null ? null : guestGroup.trim();
    }
}