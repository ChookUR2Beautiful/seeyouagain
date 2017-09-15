/**
 * 2016年8月29日 上午11:22:35
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：AnchorLiveRecord
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月29日 上午11:22:35
 * @version
 */
public class AnchorLiveRecordRequest extends BaseRequest {

	/**
	 *long
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message="年份不能为空")
	private Integer year;
	@NotNull(message="月份不能为空")
	private Integer month;
	@NotNull(message="页码不能为空")
	private Integer page;
//	@NotNull(message="类型不能为空")
	private Integer type;
	
	
	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getMonth() {
		return month;
	}


	public void setMonth(Integer month) {
		this.month = month;
	}


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	@Override
	public String toString() {
		return "AnchorLiveRecordRequest [year=" + year + ", month=" + month
				+ ", page=" + page + ", type=" + type + "]";
	}

}
