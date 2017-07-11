/**
 * 
 */
package com.xmn.saas.entity.coupon.type;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   优惠券领取条件枚举
 * 创建人：huangk   
 * 创建时间：2016年10月10日 下午3:20:01      
 */

public enum AwardCondition {
	type0(0,"无限制"),
	type1(1,"消费满获得"),
	type2(2,"新用户绑定获得");
	
	private int value;

	private String desc;

	private AwardCondition(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDesc(int type) {
		for (AwardCondition enumType : AwardCondition.values()) {
			if (enumType.value == type) {
				return enumType.getDesc();
			}
		}
		return "" + type;
	}
}
