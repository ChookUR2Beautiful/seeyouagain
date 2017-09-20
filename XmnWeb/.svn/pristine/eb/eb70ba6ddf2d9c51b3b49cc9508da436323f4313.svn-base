/**   
 * 文件名：TSellerApply.java   
 *    
 * 日期：2014年11月11日15时50分01秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSellerApply
 * 
 * 类描述：商户信息修改申请
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时50分01秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSellerApply extends BaseEntity  {
	
	private static final long serialVersionUID = 2075321613514997264L;
	
	private Integer id;// 申请ID
	private Integer sellerid;// 商家ID
	private String sellername;// 商家名称
	private String sellerNameStr;// 商家名称(修改前)
	private String logo;// 商家头像
	private String province;// 省编号
	private String city;// 市编号
	private String area;// 区编号
	private Integer zoneid;// 商圈编号
	private Integer status;// 0=待审核|1=审核通过|2=审核未通过
	private String reason;// 未通过原因
	private String address;// 商家地址
	private String landmark;// 参考地标
	
	private Date sdate;// 申请时间
	private Date edate;// 处理时间
	private Integer agioAgio;// '0全单  1折上折'
	
	private Date stdateStart;// 折扣开始时间（搜索条件）
	private Date stdateEnd;// 折扣开始时间（搜索条件）
	private Date endateStart;// 折扣结束时间（搜索条件）
	private Date endateEnd;// 折扣结束时间（搜索条件）
	private Object[] array;// 批量更新时id集合
	private String ids;// 批量更新时id集合
	
	private String areaTitle;// 批量更新时id集合
	private String businessTitle;// 批量更新时id集合
	
	//2016-04-18 t_seller_apply新增字段 add by hls
	private Integer source;//数据来源(1商家基本信息修改2修改图片、使用说明)
	private String sexplain;//使用说明
	private String phone;//联系方式
	private List<TSellerPicApply> sellerPicApply;//申请修改的图片
	//2016-04-18 t_seller_apply新增字段  add by hls
	private Double longitude;// 经度
	private Double latitude;// 纬度
	
	public String getSellerNameStr() {
		return sellerNameStr;
	}
	public void setSellerNameStr(String sellerNameStr) {
		this.sellerNameStr = sellerNameStr;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public List<TSellerPicApply> getSellerPicApply() {
		return sellerPicApply;
	}
	public void setSellerPicApply(List<TSellerPicApply> sellerPicApply) {
		this.sellerPicApply = sellerPicApply;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getSexplain() {
		return sexplain;
	}
	public void setSexplain(String sexplain) {
		this.sexplain = sexplain;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//获取状态文字说明
	public String getStatusText(){
		if(status == null) return null;
		if(status == 0) return "待审核";
		if(status == 1) return "审核通过";
		if(status == 2) return "审核未通过";
		if(status == 3) return "撤销申请修改";
		return null;
	}
	/**   
	 * 创建一个新的实例 TSellerApply.   
	 *      
	 */
	public TSellerApply() {
		super();
	}
	
	public TSellerApply(Integer id) {
		this.id = id;
	}
	
	
	/**
	 * @return the agioAgio
	 */
	public Integer getAgioAgio() {
		return agioAgio;
	}
	/**
	 * @param agioAgio the agioAgio to set
	 */
	public void setAgioAgio(Integer agioAgio) {
		this.agioAgio = agioAgio;
	}
	/**
	 * @return the zoneid
	 */
	public Integer getZoneid() {
		return zoneid;
	}
	/**
	 * @param zoneid the zoneid to set
	 */
	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}
	/**
	 * @return the areaTitle
	 */
	public String getAreaTitle() {
		return areaTitle;
	}
	/**
	 * @param areaTitle the areaTitle to set
	 */
	public void setAreaTitle(String areaTitle) {
		this.areaTitle = areaTitle;
	}
	/**
	 * @return the businessTitle
	 */
	public String getBusinessTitle() {
		return businessTitle;
	}
	/**
	 * @param businessTitle the businessTitle to set
	 */
	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}
	/**
	 * id
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * sellerid
	 * 
	 * @return the sellerid
	 */
	public Integer getSellerid() {
		return sellerid;
	}

	/**
	 * @param sellerid
	 *            the sellerid to set
	 */
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	
	/**
	 * sellername
	 * 
	 * @return the sellername
	 */
	public String getSellername() {
		return sellername;
	}

	/**
	 * @param sellername
	 *            the sellername to set
	 */
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	
	/**
	 * logo
	 * 
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	/**
	 * province
	 * 
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * city
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * area
	 * 
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	
	/**
	 * status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * reason
	 * 
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * address
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * landmark
	 * 
	 * @return the landmark
	 */
	public String getLandmark() {
		return landmark;
	}

	/**
	 * @param landmark
	 *            the landmark to set
	 */
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	/**
	 * sdate
	 * 
	 * @return the sdate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	/**
	 * @param sdate
	 *            the sdate to set
	 */
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	/**
	 * edate
	 * 
	 * @return the edate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEdate() {
		return edate;
	}

	/**
	 * @param edate
	 *            the edate to set
	 */
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	
	public Date getStdateStart() {
		return stdateStart;
	}

	public void setStdateStart(Date stdateStart) {
		this.stdateStart = stdateStart;
	}

	public Date getStdateEnd() {
		return stdateEnd;
	}

	public void setStdateEnd(Date stdateEnd) {
		this.stdateEnd = stdateEnd;
	}

	public Date getEndateStart() {
		return endateStart;
	}

	public void setEndateStart(Date endateStart) {
		this.endateStart = endateStart;
	}

	public Date getEndateEnd() {
		return endateEnd;
	}

	public void setEndateEnd(Date endateEnd) {
		this.endateEnd = endateEnd;
	}

	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	@Override
	public String toString() {
		return "TSellerApply [id=" + id + ", sellerid=" + sellerid
				+ ", sellername=" + sellername + ", logo=" + logo
				+ ", province=" + province + ", city=" + city + ", area="
				+ area + ", zoneid=" + zoneid + ", status=" + status
				+ ", reason=" + reason + ", address=" + address + ", landmark="
				+ landmark + ", sdate=" + sdate + ", edate=" + edate
				+ ", agioAgio=" + agioAgio + ", stdateStart=" + stdateStart
				+ ", stdateEnd=" + stdateEnd + ", endateStart=" + endateStart
				+ ", endateEnd=" + endateEnd + ", array="
				+ Arrays.toString(array) + ", ids=" + ids + ", areaTitle="
				+ areaTitle + ", businessTitle=" + businessTitle + ", source="
				+ source + ", sexplain=" + sexplain + ", phone=" + phone
				+ ", sellerPicApply=" + sellerPicApply + ", longitude="
				+ longitude + ", latitude=" + latitude + "]";
	}

}
