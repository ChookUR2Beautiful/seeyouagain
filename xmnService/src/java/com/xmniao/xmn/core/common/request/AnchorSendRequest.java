/**
 * 2016年8月11日 下午3:38:30
 */
package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：AnchorSendRequest
 * @类描述：当月直播记录列表查询
 * @创建人： yeyu
 * @创建时间 2016年8月11日 下午3:38:30
 * @version
 */
public class AnchorSendRequest extends BaseRequest {

	@NotNull(message="客户ID不能为空")
	private String uid;//用户id
	@NotNull(message="页码不能为空")
	

	private Integer page;//页数
	@NotNull(message="年份不能为空")
	private int year;
	@NotNull(message="月份不能为空")
	private String month;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "AnchorSendRequest [uid=" + uid + ", page=" + page + ", year="
				+ year + ", month=" + month + "]";
	}

	
	
	
}
