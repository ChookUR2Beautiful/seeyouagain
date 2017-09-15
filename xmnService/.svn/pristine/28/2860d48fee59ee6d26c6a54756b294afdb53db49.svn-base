package com.xmniao.xmn.core.seller.entity;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.common.Constant;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 * 店铺相册请求参数实体类
 * @ClassName:SellerPhotoRequest
 * @Description:店铺相册请求参数
 * @Author:xw
 * @Date:2017年5月15日下午1:56:37
 */
public class SellerPhotoRequest extends BaseRequest{

	private static final long serialVersionUID = -1634167451729922640L;
	
	@NotNull(message="店铺id不能为空")
	private Integer sellerid;
	
	@Min(value=1,message="页码不能小于1")
	@NotNull(message="分页页码不能为空")
	private Integer page;

	/*分页页码，不传时默认设置10条*/
	private Integer pageSize = Constant.SELLER_PHOTO_PAGESIZE;
	
	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "SellerPhotoRequest [Sellerid=" + sellerid + ", page=" + page + ",pageSize=" + pageSize +",BaseRequest="+ super.toString() + "]";
	}
}
