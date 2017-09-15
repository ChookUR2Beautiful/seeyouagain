/**
 * 2016年4月5日 下午6:12:16
 */
package com.xmniao.xmn.core.common;

import java.util.Map;

import com.xmniao.xmn.core.base.BaseResponse;

/**
 * @项目名称：saasService
 * @类名称：MapResponse
 * @类描述：通用response结果集
 * @创建人： zhangchangyuan
 * @创建时间 2016年4月5日 下午6:12:16
 * @version
 */
public class MapResponse extends BaseResponse{

	/**
	 * @param state
	 * @param info
	 */
	public MapResponse(int state, String info) {
		super(state, info);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 相应结果集
	 */
	private Map<Object, Object> response;


	public Map<Object, Object> getResponse() {
		return response;
	}

	public void setResponse(Map<Object, Object> response) {
		this.response = response;
	}
	
}
