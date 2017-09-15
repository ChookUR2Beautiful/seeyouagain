package com.xmniao.xmn.core.common.request;

import java.io.Serializable;

import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AppUpdateRequest   
* 类描述：   获取app客户端下载url请求参数
* 创建人：yezhiyong   
* 创建时间：2016年6月16日 下午2:36:54   
* @version    
*
 */
public class AppLoadUrlRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4528443932680956282L;
	@Max(2)
	@Min(1)
	@NotNull(message="手机类型不能为空")
	private Integer vtype;//检查类型:1 Android   2 Ios
	public Integer getVtype() {
		return vtype;
	}
	public void setVtype(Integer vtype) {
		this.vtype = vtype;
	}
	@Override
	public String toString() {
		return "AppUpdateRequest [vtype=" + vtype + "]";
	}

}
