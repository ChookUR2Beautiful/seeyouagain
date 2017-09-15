/**
 * 2016年10月19日 下午2:14:21
 */
package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 * @项目名称：xmnService
 * @类名称：AnchorInfoBasic
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年10月19日 下午2:14:21
 * @version
 */
public class AnchorInfoBasicRequest extends BaseRequest {
	/**
	 *long
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message="主播ID不能为空")
	private Integer anchorId;
	private Integer userType;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	@Override
	public String toString() {
		return "AnchorInfoBasic [anchorId=" + anchorId + "]";
	}
	
	
}
