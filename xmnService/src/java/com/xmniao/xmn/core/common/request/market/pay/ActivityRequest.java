package com.xmniao.xmn.core.common.request.market.pay;

/**
 * 
* @projectName: xmnService 
* @ClassName: ActivityRequest    
* @Description:通用活动请求类   
* @author: liuzhihao   
* @date: 2016年12月22日 下午9:19:09
 */
public class ActivityRequest {

	private Integer id;//活动id
	
	private Integer page=1;//分页

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "ActivityRequest [id=" + id + ", page=" + page + "]";
	}
	
	
}
