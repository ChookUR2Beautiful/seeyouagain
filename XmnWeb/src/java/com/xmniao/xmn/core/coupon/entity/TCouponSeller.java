package com.xmniao.xmn.core.coupon.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * @项目名称：XmnWeb20150513E
 * 
 * @类名称：TCouponSeller
 * 
 * @类描述： 优惠券商家
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年5月29日 下午5:17:06
 * 
 */
public class TCouponSeller extends BaseEntity {

	private static final long serialVersionUID = 8450797955375797971L;

	private Integer csid;
	private Integer cid;// 优惠劵ID
	private Integer include;// 是否包含，0排除，1包含
	private Integer ltype;// 限制类型，1商家，2行业
	private String category;// 一级行业编号
	private Integer sellerid;// 商家编号或二行业编号
	private Integer status;// 关联状态，0无效，1有效

	private String sellerids;
	private String sellername;

	public enum INCLUDE {
		INCLUDE(1), EXCLUDE(0);

		private Integer value;

		private INCLUDE(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public enum LTYPE {
		SELLER(1), TRADE(2);

		private Integer value;

		private LTYPE(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public enum STATUS {
		VOID(0), VALID(1);

		private Integer value;

		private STATUS(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getInclude() {
		return include;
	}

	public void setInclude(Integer include) {
		this.include = include;
	}

	public Integer getLtype() {
		return ltype;
	}

	public void setLtype(Integer ltype) {
		this.ltype = ltype;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSellerids() {
		return sellerids;
	}

	public void setSellerids(String sellerids) {
		this.sellerids = sellerids;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	@Override
	public String toString() {
		return "TCouponSeller [csid=" + csid + ", cid=" + cid + ", include="
				+ include + ", ltype=" + ltype + ", category=" + category
				+ ", sellerid=" + sellerid + ", status=" + status
				+ ", sellerids=" + sellerids + ", sellername=" + sellername
				+ "]";
	}

}
