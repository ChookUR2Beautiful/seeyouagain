/**
 * 2016年8月17日 下午4:34:47
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PayBirdCoinListRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月17日 下午4:34:47
 * @version
 */
public class PayBirdCoinListRequest extends BaseRequest {
	
	private static final long serialVersionUID = -5048979895590067561L;
	
	@Min(value=1,message="当前页数不能小于1")
	@NotNull(message="页码不能为空")
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "PayBirdCoinListRequest [ page=" + page + "," + super.toString()+ "]";
	}
	
}
