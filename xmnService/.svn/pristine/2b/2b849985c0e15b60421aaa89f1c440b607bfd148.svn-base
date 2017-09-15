package com.xmniao.xmn.core.common.request;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CateHomeRequest   
* 类描述：   发现美食首页信息请求参数
* 创建人：yezhiyong   
* 创建时间：2016年6月21日 上午9:08:28   
* @version    
*
 */
public class CateHomeRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4289624415948446986L;

	@NotNull(message="市级编号不能为空")
	private Integer city;//市级编号
	
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "CateHomeRequest [city=" + city + "]";
	}
	
}
