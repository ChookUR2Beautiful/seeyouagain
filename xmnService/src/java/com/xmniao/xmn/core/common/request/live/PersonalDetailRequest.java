/**
 * 2016年8月16日 下午4:07:05
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PersonalListRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月16日 下午4:07:05
 * @version
 */
@SuppressWarnings("serial")
public class PersonalDetailRequest extends BaseRequest {
	@NotNull(message="主播ID不能为空")
	private Integer uid;//用户id
	@NotNull(message="类型不能为空")
	private    Integer type;
	@NotNull(message="分页页码不能为空")
	private Integer page;//页数
	private Integer month;//月份
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "PersonalDetailRequest [uid=" + uid + ", type=" + type
				+ ", page=" + page + ", month=" + month + "]";
	}
	
	
}
