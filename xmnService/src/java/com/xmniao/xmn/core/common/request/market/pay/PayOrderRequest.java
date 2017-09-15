package com.xmniao.xmn.core.common.request.market.pay;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
 * @projectName: xmnService
 * @ClassName: PayOrderRequest
 * @Description:支付订单的请求类
 * @author: liuzhihao
 * @date: 2016年12月26日 下午6:32:52
 */
@SuppressWarnings( "serial" )
public class PayOrderRequest extends BaseRequest {
    @NotNull( message = "鸟币支付类型不能为空" )
    private Integer birdType;// 鸟币支付类型
    
    @NotNull( message = "余额类型不能为空" )
    private Integer amountType;// 余额类型
    
    @NotNull( message = "微信类型不能为空" )
    private Integer wxType;// 微信类型
    
    @NotNull( message = "支付宝类型不能为空" )
    private Integer zfbType;// 支付宝类型

    @NotNull( message = "订单号不能为空" )
    private Integer orderNo;// 订单号

    public Integer getBirdType() {
        return birdType;
    }

    public void setBirdType(Integer birdType) {
        this.birdType = birdType;
    }

    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public Integer getWxType() {
        return wxType;
    }

    public void setWxType(Integer wxType) {
        this.wxType = wxType;
    }

    public Integer getZfbType() {
        return zfbType;
    }

    public void setZfbType(Integer zfbType) {
        this.zfbType = zfbType;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

	@Override
	public String toString() {
		return "PayOrderRequest [birdType=" + birdType + ", amountType=" + amountType + ", wxType=" + wxType + ", zfbType="
			+ zfbType + ", orderNo=" + orderNo + "]";
	}

}
