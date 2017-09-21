package com.xmniao.domain.order;

import java.math.BigDecimal;
import java.util.Date;

public class BillActivity {
    /**
     * 
     */
    private String id;

    /**
     * 活动类型 01:一元夺宝  02:竞拍活动 
     */
    private Integer activityType;

    /**
     * 活动编号
     */
    private Long activityId;

    /**
     * 商品编号(codeId)
     */
    private String productCodeId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品数量
     */
    private Integer productNum;

    /**
     * 商品缩略图
     */
    private String productBreviary;

    /**
     * 商品属性值id组合，无序的，","作分隔符
     */
    private String productPvIds;

    /**
     * 商品规格值用","作分隔符
     */
    private String productPvValue;

    /**
     * 实际已支付金额
     */
    private BigDecimal amountReceived;

    /**
     * 竞拍活动-保证金
     */
    private BigDecimal auctionDeposit;

    /**
     * 竞拍活动-尾款
     */
    private BigDecimal auctionBalance;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付类型 001鸟币
     */
    private String payType;

    /**
     * 收货人名字
     */
    private String receivingName;

    /**
     * 收货人号码
     */
    private String receivingPhone;

    /**
     * 收货城市
     */
    private String receivingCity;

    /**
     * 收货地址
     */
    private String receivingAddress;

    /**
     * 确认收货信息  01已确认  02 未确认
     */
    private Integer receivingConfirm;

    /**
     * 订单状态 01待付款 02待发货 03已发货 04已完成 05已关闭
     */
    private Integer state;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户类型 01 普通用户 02 机器人
     */
    private Integer userType;

    /**
     * 物流公司
     */
    private String logisticsType;

    /**
     * 物流编号
     */
    private String logisticsNum;

    /**
     * 下单时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    private String activityName;

    public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 活动类型 01:一元夺宝  02:竞拍活动 
     * @return activity_type 活动类型 01:一元夺宝  02:竞拍活动 
     */
    public Integer getActivityType() {
        return activityType;
    }

    /**
     * 活动类型 01:一元夺宝  02:竞拍活动 
     * @param activityType 活动类型 01:一元夺宝  02:竞拍活动 
     */
    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    /**
     * 活动编号
     * @return activity_id 活动编号
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * 活动编号
     * @param activityId 活动编号
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * 商品编号(codeId)
     * @return product_code_id 商品编号(codeId)
     */
    public String getProductCodeId() {
        return productCodeId;
    }

    /**
     * 商品编号(codeId)
     * @param productCodeId 商品编号(codeId)
     */
    public void setProductCodeId(String productCodeId) {
        this.productCodeId = productCodeId == null ? null : productCodeId.trim();
    }

    /**
     * 商品名
     * @return product_name 商品名
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 商品名
     * @param productName 商品名
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * 商品单价
     * @return product_price 商品单价
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 商品单价
     * @param productPrice 商品单价
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 商品数量
     * @return product_num 商品数量
     */
    public Integer getProductNum() {
        return productNum;
    }

    /**
     * 商品数量
     * @param productNum 商品数量
     */
    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    /**
     * 商品缩略图
     * @return product_breviary 商品缩略图
     */
    public String getProductBreviary() {
        return productBreviary;
    }

    /**
     * 商品缩略图
     * @param productBreviary 商品缩略图
     */
    public void setProductBreviary(String productBreviary) {
        this.productBreviary = productBreviary == null ? null : productBreviary.trim();
    }

    /**
     * 商品属性值id组合，无序的，","作分隔符
     * @return product_pv_ids 商品属性值id组合，无序的，","作分隔符
     */
    public String getProductPvIds() {
        return productPvIds;
    }

    /**
     * 商品属性值id组合，无序的，","作分隔符
     * @param productPvIds 商品属性值id组合，无序的，","作分隔符
     */
    public void setProductPvIds(String productPvIds) {
        this.productPvIds = productPvIds == null ? null : productPvIds.trim();
    }

    /**
     * 商品规格值用","作分隔符
     * @return product_pv_value 商品规格值用","作分隔符
     */
    public String getProductPvValue() {
        return productPvValue;
    }

    /**
     * 商品规格值用","作分隔符
     * @param productPvValue 商品规格值用","作分隔符
     */
    public void setProductPvValue(String productPvValue) {
        this.productPvValue = productPvValue == null ? null : productPvValue.trim();
    }

    /**
     * 实际已支付金额
     * @return amount_received 实际已支付金额
     */
    public BigDecimal getAmountReceived() {
        return amountReceived;
    }

    /**
     * 实际已支付金额
     * @param amountReceived 实际已支付金额
     */
    public void setAmountReceived(BigDecimal amountReceived) {
        this.amountReceived = amountReceived;
    }

    /**
     * 竞拍活动-保证金
     * @return auction_deposit 竞拍活动-保证金
     */
    public BigDecimal getAuctionDeposit() {
        return auctionDeposit;
    }

    /**
     * 竞拍活动-保证金
     * @param auctionDeposit 竞拍活动-保证金
     */
    public void setAuctionDeposit(BigDecimal auctionDeposit) {
        this.auctionDeposit = auctionDeposit;
    }

    /**
     * 竞拍活动-尾款
     * @return auction_balance 竞拍活动-尾款
     */
    public BigDecimal getAuctionBalance() {
        return auctionBalance;
    }

    /**
     * 竞拍活动-尾款
     * @param auctionBalance 竞拍活动-尾款
     */
    public void setAuctionBalance(BigDecimal auctionBalance) {
        this.auctionBalance = auctionBalance;
    }

    /**
     * 支付时间
     * @return pay_time 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 支付时间
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 支付类型 001鸟币
     * @return pay_type 支付类型 001鸟币
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 支付类型 001鸟币
     * @param payType 支付类型 001鸟币
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 收货人名字
     * @return receiving_name 收货人名字
     */
    public String getReceivingName() {
        return receivingName;
    }

    /**
     * 收货人名字
     * @param receivingName 收货人名字
     */
    public void setReceivingName(String receivingName) {
        this.receivingName = receivingName == null ? null : receivingName.trim();
    }

    /**
     * 收货人号码
     * @return receiving_phone 收货人号码
     */
    public String getReceivingPhone() {
        return receivingPhone;
    }

    /**
     * 收货人号码
     * @param receivingPhone 收货人号码
     */
    public void setReceivingPhone(String receivingPhone) {
        this.receivingPhone = receivingPhone == null ? null : receivingPhone.trim();
    }

    /**
     * 收货城市
     * @return receiving_city 收货城市
     */
    public String getReceivingCity() {
        return receivingCity;
    }

    /**
     * 收货城市
     * @param receivingCity 收货城市
     */
    public void setReceivingCity(String receivingCity) {
        this.receivingCity = receivingCity == null ? null : receivingCity.trim();
    }

    /**
     * 收货地址
     * @return receiving_address 收货地址
     */
    public String getReceivingAddress() {
        return receivingAddress;
    }

    /**
     * 收货地址
     * @param receivingAddress 收货地址
     */
    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress == null ? null : receivingAddress.trim();
    }

    /**
     * 确认收货信息  01已确认  02 未确认
     * @return receiving_confirm 确认收货信息  01已确认  02 未确认
     */
    public Integer getReceivingConfirm() {
        return receivingConfirm;
    }

    /**
     * 确认收货信息  01已确认  02 未确认
     * @param receivingConfirm 确认收货信息  01已确认  02 未确认
     */
    public void setReceivingConfirm(Integer receivingConfirm) {
        this.receivingConfirm = receivingConfirm;
    }

    /**
     * 订单状态 01待付款 02待发货 03已发货 04已完成 05已关闭
     * @return state 订单状态 01待付款 02待发货 03已发货 04已完成 05已关闭
     */
    public Integer getState() {
        return state;
    }

    /**
     * 订单状态 01待付款 02待发货 03已发货 04已完成 05已关闭
     * @param state 订单状态 01待付款 02待发货 03已发货 04已完成 05已关闭
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 用户手机号
     * @return user_phone 用户手机号
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 用户手机号
     * @param userPhone 用户手机号
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 用户名
     * @return user_name 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 用户类型 01 普通用户 02 机器人
     * @return user_type 用户类型 01 普通用户 02 机器人
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 用户类型 01 普通用户 02 机器人
     * @param userType 用户类型 01 普通用户 02 机器人
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 物流公司
     * @return logistics_type 物流公司
     */
    public String getLogisticsType() {
        return logisticsType;
    }

    /**
     * 物流公司
     * @param logisticsType 物流公司
     */
    public void setLogisticsType(String logisticsType) {
        this.logisticsType = logisticsType == null ? null : logisticsType.trim();
    }

    /**
     * 物流编号
     * @return logistics_num 物流编号
     */
    public String getLogisticsNum() {
        return logisticsNum;
    }

    /**
     * 物流编号
     * @param logisticsNum 物流编号
     */
    public void setLogisticsNum(String logisticsNum) {
        this.logisticsNum = logisticsNum == null ? null : logisticsNum.trim();
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
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}