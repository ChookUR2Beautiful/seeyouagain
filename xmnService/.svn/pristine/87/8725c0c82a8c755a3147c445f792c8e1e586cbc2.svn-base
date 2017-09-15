/**
 * 2016年8月23日 下午5:57:17
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：SelfGift
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月23日 下午5:57:17
 * @version
 */
public class SelfGiftRequest extends BaseRequest {
	//大礼包初始化key
	@NotNull(message="大礼包初始化key不能为空")
	private String randomkey;
	public String getRandomkey() {
		return randomkey;
	}
	public void setRandomkey(String randomkey) {
		this.randomkey = randomkey;
	}
	@Override
	public String toString() {
		return "SelfGiftRequest [randomkey=" + randomkey + "]";
	}
	
	
	
}
