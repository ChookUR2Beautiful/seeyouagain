package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 项目名称： xmnService
 * 类名称： FlowInfoListRequest
 * 类描述：流水明细列表请求参数
 * 创建人： lifeng
 * 创建时间： 2016年5月24日下午1:34:03
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
public class FlowInfoListRequest extends BaseRequest {

	private static final long serialVersionUID = -8491752551867245174L;
	@NotNull(message="流水类型不能为空")
	private String type;//流水类型 0：全部 1：本月流水 2：上月流水 3：自定义流水
	private String startdate;//开始时间
	private String enddate;//结束时间
	@NotNull(message="页码不能为空")
	private Integer page;//页码：默认1 每页20条
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
	
}
