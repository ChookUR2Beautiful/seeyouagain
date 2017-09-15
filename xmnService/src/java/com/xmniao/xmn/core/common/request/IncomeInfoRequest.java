package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：IncomeInfoRequest   
* 类描述：   收入明细接口参数
* 创建人：yezhiyong   
* 创建时间：2016年5月27日 上午10:10:48   
* @version    
*
 */
public class IncomeInfoRequest extends BaseRequest {

	private static final long serialVersionUID = -6335574915451675771L;
	@NotNull(message="收入类型不能为空")
	private Integer type;//收入类型 0：全部  1：本月收入 2：上月收入 3：自定义收入
	private String startdate;//开始时间
	private String enddate;//结束时间
	@NotNull(message="分页不能为空")
	private Integer page;//页码：默认1 每页20条
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "IncomeInfoRequest [type=" + type + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", page=" + page + "]";
	}
}
