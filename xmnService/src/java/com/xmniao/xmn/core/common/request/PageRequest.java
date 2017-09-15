package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.common.Constant;

/**
 * 
* 项目名称：xmnService   
* 类名称：PageRequest   
* 类描述：分页请求实体类   
* 创建人：liuzhihao   
* 创建时间：2016年6月21日 下午3:22:36   
* @version    
*
 */
@SuppressWarnings("serial")
public class PageRequest extends BaseRequest{
	
	@NotNull(message="页数不能为空")
	@Min(value=1,message="页数不能小于1")
	private Integer page;//页数
	
	@Min(value=1,message="每页显示数量不能小于1")
	private Integer pageSize = Constant.PAGE_LIMIT;
	
	private String fileurl;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageRequest [page=" + page + ", pageSize=" + pageSize
				+ ", fileurl=" + fileurl + "]";
	}
	
}
