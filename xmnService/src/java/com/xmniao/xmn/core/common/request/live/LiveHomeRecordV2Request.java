package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 *
 */
public class LiveHomeRecordV2Request extends BaseRequest {

	private static final long serialVersionUID = 3913893905471041369L;

	//页码
	@NotNull(message="页码不能为空")
	private Integer page = 1;  // 1,2栏目，第一页特殊处理
	private Integer pageSize = 10;
	@NotNull(message="栏目类型不能为空")
	private Integer tabType = 1;  // 1 金牌推荐栏目 2 新人推荐栏目 3 缤纷娱乐栏目 4美食撩味栏目
	private Integer tagId;  // 新人推荐栏目，美食撩味栏目 可根据标签刷选直播
	private Integer rangeId;  //范围 0 附近 1 全国
	private Double longitude;  //用户经度
	private Double latitude;  //用户纬度
	private Integer city_id; //城市Id

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

	public Integer getTabType() {
		return tabType;
	}

	public void setTabType(Integer tabType) {
		this.tabType = tabType;
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


	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getRangeId() {
		return rangeId;
	}

	public void setRangeId(Integer rangeId) {
		this.rangeId = rangeId;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	@Override
	public String toString() {
		return "LiveHomeRecordV2Request{" +
				"page=" + page +
				", pageSize=" + pageSize +
				", tabType=" + tabType +
				", tagId=" + tagId +
				", rangeId=" + rangeId +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", city_id=" + city_id +
				'}';
	}
}
