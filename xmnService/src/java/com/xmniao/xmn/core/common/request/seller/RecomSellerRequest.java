package com.xmniao.xmn.core.common.request.seller;

import java.util.List;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: RecomSellerRequest    
* @Description:推荐商铺列表请求类   
* @author: liuzhihao   
* @date: 2016年12月1日 下午2:17:33
 */
@SuppressWarnings("serial")
public class RecomSellerRequest extends BaseRequest{
	
	private Double minprice;//最低价格
	
	private Double maxprice;//最高价格	

	private Double lon;//坐标--经度
	
	private Double lat;//坐标--纬度
	
	private Integer zoneid;//商圈id
	
	private Integer cityid;//城市id
	
	private Integer tradeid;//分类(二级分类id)
	
	private Integer eattype;//吃货类型 
	
	private Integer page=1;//分页--页码
	
	private Integer pageSize=8;//分页--条数
	
	private Integer type=1;//推荐类型 1:帮你挑选店铺 2:商圈人气店铺 3:猜你喜欢商铺
	
	private Integer kind;
	
	private Integer status;
	
	private List<Integer> sellerIds;
	
	private String tradeName;//分类名称
	
	private String friendName;//吃货类型名称
	
	private String priceName;//价格名称
	
	private String zoneName;//商圈名称

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	public Integer getTradeid() {
		return tradeid;
	}

	public void setTradeid(Integer tradeid) {
		this.tradeid = tradeid;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Integer> getSellerIds() {
		return sellerIds;
	}

	public void setSellerIds(List<Integer> sellerIds) {
		this.sellerIds = sellerIds;
	}

	public Double getMinprice() {
		return minprice;
	}

	public void setMinprice(Double minprice) {
		this.minprice = minprice;
	}

	public Double getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(Double maxprice) {
		this.maxprice = maxprice;
	}

	public Integer getEattype() {
		return eattype;
	}

	public void setEattype(Integer eattype) {
		this.eattype = eattype;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	@Override
	public String toString() {
		return "RecomSellerRequest [minprice=" + minprice + ", maxprice=" + maxprice + ", lon=" + lon + ", lat=" + lat + ", zoneid="
			+ zoneid + ", cityid=" + cityid + ", tradeid=" + tradeid + ", eattype=" + eattype + ", page=" + page + ", pageSize="
			+ pageSize + ", type=" + type + ", kind=" + kind + ", status=" + status + ", sellerIds=" + sellerIds + ", tradeName="
			+ tradeName + ", friendName=" + friendName + ", priceName=" + priceName + ", zoneName=" + zoneName + "]";
	}
	
}
