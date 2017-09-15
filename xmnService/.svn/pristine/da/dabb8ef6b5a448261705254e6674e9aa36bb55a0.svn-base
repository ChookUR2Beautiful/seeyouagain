package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 * 店铺流水提成明细
 */
public class StoreInfoListRequest extends BaseRequest {

	private static final long serialVersionUID = -2036408748103265640L;
	@NotNull(message="流水类型不能为空")
	private String type;//流水类型 0：全部 1：本月流水 2：上月流水 3：自定义流水
	private String startdate;//开始时间
	private String enddate;//结束时间
	@NotNull(message="页码不能为空")
	private Integer page;//页码：默认1 每页20条
	private Integer pageSize = 20;
	public String getType() {
		return type;
	}
	public void setType(String type) {
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
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "StoreInfoListRequest{" +
				"type='" + type + '\'' +
				", startdate='" + startdate + '\'' +
				", enddate='" + enddate + '\'' +
				", page=" + page +
				", pageSize=" + pageSize +
				'}';
	}
}
