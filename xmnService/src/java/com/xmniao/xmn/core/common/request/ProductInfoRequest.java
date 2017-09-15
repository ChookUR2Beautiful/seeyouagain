package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* 项目名称：xmnService   
* 类名称：ProductInfoRequest   
* 类描述：积分商城-积分商品详情接口请求实体类   
* 创建人：liuzhihao   
* 创建时间：2016年6月20日 下午8:06:22   
* @version    
*
 */
public class ProductInfoRequest{

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -2662852098030170978L;
	@NotNull(message="积分商品id不能为空")
	private String codeid;//积分商品id
	
	@NotNull(message="城市编号不能为空")
	private Integer cityid;//城市ID
	
	public String getCodeid() {
		return codeid;
	}
	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
	
	public Integer getCityid() {
		return cityid;
	}
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	@Override
	public String toString() {
		return "ProductInfoRequest [codeid=" + codeid + "]";
	}
	
	
}
