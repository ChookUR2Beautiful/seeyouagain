package com.xmniao.xmn.core.common.request.vod;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;
/**
 * 获取视频播放信息请求参数
* 类名称：DescribeVodPlayInfoRequest   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年8月27日 上午5:53:53   
* 修改人：xiaoxiong   
* 修改时间：2016年8月27日 上午5:53:53   
* 修改备注：   
* @version    
*
 */

public class DescribeVodPlayInfoRequest extends BaseRequest{
	
	@NotNull(message="视频名称不能为空")
	private String filename;
	
	private Integer pageno;
	
	private Integer pagesize;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	
	
}
