package com.xmniao.xmn.core.coupon.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: CouponCardsRequest    
* @Description:我的卡卷请求类   
* @author: liuzhihao   
* @date: 2017年2月27日 上午10:20:43
 */
@SuppressWarnings("serial")
public class CouponCardsRequest extends BaseRequest{

	@NotNull(message="我的卡卷类型不能为空")
	private Integer type;//我的卡卷类型 1 充值卡 2寻蜜鸟礼券 3餐饮店铺礼券
	
	@NotNull(message="我的卡卷是否过期")
	private Integer isOverdue;//是否过期 0 过期 1 未过期
	
	@NotNull(message="分页码不能为空")
	private Integer page;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(Integer isOverdue) {
		this.isOverdue = isOverdue;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
}
