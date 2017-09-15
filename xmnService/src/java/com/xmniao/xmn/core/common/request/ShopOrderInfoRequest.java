package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

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
public class ShopOrderInfoRequest extends BaseRequest {

	private static final long serialVersionUID = 428016166084882393L;


	@NotNull(message="分页不能为空")
	private Integer page;//页码：默认1 每页20条
	private Integer pageSize = 20;  //页数
	private String startdate;//开始时间
	private String enddate;//结束时间

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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

	@Override
	public String toString() {
		return "ShopOrderInfoRequest{" +
				"page=" + page +
				", pageSize=" + pageSize +
				", startdate='" + startdate + '\'' +
				", enddate='" + enddate + '\'' +
				'}';
	}
}
