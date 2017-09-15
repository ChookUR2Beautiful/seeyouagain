/**
 * 2016年8月15日 下午5:25:46
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：EggtobirdmoneyRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月15日 下午5:25:46
 * @version
 */
public class EggtobirdmoneyRequest extends BaseRequest {
	@NotNull(message="客户ID不能为空")
	private Integer uid;//用户id
	@NotNull(message="鸟蛋数量不能为空")
	private Double eggNum;//鸟蛋数量
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Double getEggNum() {
		return eggNum;
	}
	public void setEggNum(Double eggNum) {
		this.eggNum = eggNum;
	}
	
	
}
