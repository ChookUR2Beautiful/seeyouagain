package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
* 项目名称：xmnService   
* 类名称：WorksListRequest   
* 类描述：岗位列表请求参数bean   
* 创建人：liuzhihao   
* 创建时间：2016年5月20日 下午6:23:06   
* @version    
*
 */
public class WorksListRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="不能为空")
	private String id;//岗位标签id
	
	private String salary;//薪资范围
	
	private String scale;//店铺规模范围

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	@Override
	public String toString() {
		return "WorksListRequest [id=" + id + ", salary=" + salary + ", scale="
				+ scale + "]";
	}
	
	

}
