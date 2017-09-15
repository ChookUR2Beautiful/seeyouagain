/**
 * 2016年5月23日 下午4:03:19
 */
package com.xmniao.xmn.core.common.request;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * @项目名称：xmnService
 * @类名称：SaasSignRequest
 * @类描述：寻蜜客签约商户请求参数
 * @创建人： zhangchangyuan
 * @创建时间 2016年5月23日 下午4:03:19
 * @version
 */
public class SaasSignRequest  extends BaseRequest{
	private static final long serialVersionUID = 1L;

	@NotNull(message="商铺名称不能为空")
	private String sellername;		//	商铺名称
	
	@NotNull(message="商铺资料省名称不能为空")
	private String province;		//	省名称
	
	@NotNull(message="商铺资料市名称不能为空")
	private String city;		//	市名称
	
	@NotNull(message="商铺资料区名称不能为空")
	private String area;		//	区名称
	
	private String address;			//	详细地址
	
	@NotNull(message="经营类型不能为空")
	private Integer category;			//	经营类型ID
	
	@NotNull(message="二级经营类型不能为空")
	private Integer genre;			//二级经营类型ID
	
	private String tradename;//二级分类名称
	
	private String typename;//类别名称
	
	@NotNull(message="类别描述不能为空")
	private String bewrite;			//类别描述
	
	@NotNull(message="本店法人名字不能为空")
	private String fullname;		//	法人名字
	
	@NotNull(message="手机号码不能为空")
	private String phoneid;			//	本店法人手机
	
	private String tel;		//	本店座机电话
	
	private String consume;	//人均消费
	
	@NotNull(message="签约开始时间不能为空")
	private String svalidity;		//	店铺签约开始时间
	
	@NotNull(message="签约结束时间不能为空")
	private String evalidity;		//	店铺签约结束时间
	
	@NotNull(message="店铺折扣不能为空")
	private Double agio;		//	折扣设置
	
	@NotNull(message="开业时间不能为空")
	private String sdate;		//	开业时间
	
	@NotNull(message="提交状态不能为空")
	private Integer sendtype;		//	发送状态 0：存草稿箱 1：提交审核
	
	private Integer status;	//签约状态
	
	private Integer agio_type;	//折扣类型
	
	private Integer isonline;	//是否上线
	
	private Integer data_source;	// 数据来源

	private Integer xmn_service;	//是否同意寻蜜鸟协议
	
	private Integer id;		//sellerid
	
	private Integer uid;	//签约对应的用户ID
	
	private Date signdate;//签约时间
	
	private Double longitude;//经度
	
	private Double latitude;//纬度
	
	private Date operadate;	//更新时间 
	
	public Date getSigndate() {
		return signdate;
	}

	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getGenre() {
		return genre;
	}

	public void setGenre(Integer genre) {
		this.genre = genre;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	public String getConsume() {
		return consume;
	}

	public void setConsume(String consume) {
		this.consume = consume;
	}

	public String getSvalidity() {
		return svalidity;
	}

	public void setSvalidity(String svalidity) {
		this.svalidity = svalidity;
	}

	public String getEvalidity() {
		return evalidity;
	}

	public void setEvalidity(String evalidity) {
		this.evalidity = evalidity;
	}

	public Double getAgio() {
		return agio;
	}

	public void setAgio(Double agio) {
		this.agio = agio;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public Integer getSendtype() {
		return sendtype;
	}

	public void setSendtype(Integer sendtype) {
		this.sendtype = sendtype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAgio_type() {
		return agio_type;
	}

	public void setAgio_type(Integer agio_type) {
		this.agio_type = agio_type;
	}

	public Integer getIsonline() {
		return isonline;
	}

	public void setIsonline(Integer isonline) {
		this.isonline = isonline;
	}

	public Integer getData_source() {
		return data_source;
	}

	public void setData_source(Integer data_source) {
		this.data_source = data_source;
	}

	public Integer getXmn_service() {
		return xmn_service;
	}

	public void setXmn_service(Integer xmn_service) {
		this.xmn_service = xmn_service;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBewrite() {
		return bewrite;
	}

	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public String getTradename() {
		return tradename;
	}

	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	public Date getOperadate() {
		return operadate;
	}

	public void setOperadate(Date operadate) {
		this.operadate = operadate;
	}

	@Override
	public String toString() {
		return "SaasSignRequest [sellername=" + sellername + ", province="
				+ province + ", city=" + city + ", area=" + area + ", address="
				+ address + ", category=" + category + ", genre=" + genre
				+ ", tradename=" + tradename + ", typename=" + typename
				+ ", bewrite=" + bewrite + ", fullname=" + fullname
				+ ", phoneid=" + phoneid + ", tel=" + tel + ", consume="
				+ consume + ", svalidity=" + svalidity + ", evalidity="
				+ evalidity + ", agio=" + agio + ", sdate=" + sdate
				+ ", sendtype=" + sendtype + ", status=" + status
				+ ", agio_type=" + agio_type + ", isonline=" + isonline
				+ ", data_source=" + data_source + ", xmn_service="
				+ xmn_service + ", id=" + id + ", uid=" + uid + ", signdate="
				+ signdate + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}

}
