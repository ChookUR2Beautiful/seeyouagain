/**
 * 2016年10月17日 下午5:01:22
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService
 * @类名称：SearchAnchorLiveRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年10月17日 下午5:01:22
 * @version
 */
public class SearchAnchorLiveRequest extends BaseRequest {
	/**
	 *long
	 */
	private static final long serialVersionUID = 1L;
	private String parameterText;
	@NotNull(message="页码不能为空")
	private Integer page;
	public String getParameterText() {
		return parameterText;
	}
	public void setParameterText(String parameterText) {
		this.parameterText = parameterText;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "SearchAnchorLiveRequest [parameterText=" + parameterText
				+ ", page=" + page + "]";
	}
	
	
}
