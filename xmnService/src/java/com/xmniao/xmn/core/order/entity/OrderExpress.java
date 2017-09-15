package com.xmniao.xmn.core.order.entity;


/**
 * 物流信息对象
 * @author yhl
 * 2016年6月28日14:15:05
 * */
public class OrderExpress {

	//物流单号
	public String courier_number;
	
	//物流公司代号
	public String courier_type;
	
	//物流公司名称
	public String express_name;

	public String getCourier_number() {
		return courier_number;
	}

	public void setCourier_number(String courier_number) {
		this.courier_number = courier_number;
	}

	public String getCourier_type() {
		return courier_type;
	}

	public void setCourier_type(String courier_type) {
		this.courier_type = courier_type;
	}

	public String getExpress_name() {
		return express_name;
	}

	public void setExpress_name(String express_name) {
		this.express_name = express_name;
	}
	
	
}
