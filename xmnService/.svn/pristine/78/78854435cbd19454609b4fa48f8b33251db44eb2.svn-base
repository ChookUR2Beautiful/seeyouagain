package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：FocusLiveRequest   
* 类描述：    取消关注/关注主播/想看预告请求参数
* 创建人：yezhiyong   
* 创建时间：2016年8月25日 下午3:51:26   
* @version    
*
 */
public class FocusLiveRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2432887869906258176L;

	//主播id/预告记录id
	@NotNull(message="id不能为空")
	private Integer id;
	
	//类型: 0 取消关注 1 关注主播   2 想看预告
	@NotNull(message="类型不能为空")
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FocusLiveRequest [id=" + id + ", type=" + type + "]";
	}
	
}
