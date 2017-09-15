package com.xmniao.xmn.core.catehome.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;


/**
 * 
* @projectName: xmnService 
* @ClassName: SearchKeywordsRequest    
* @Description:搜索关键词请求类   
* @author: liuzhihao   
* @date: 2017年2月21日 上午9:51:42
 */
@SuppressWarnings("serial")
public class SearchKeywordsRequest extends BaseRequest{

	@NotNull(message="城市ID不能为空")
	private Integer cityId;//城市ID
	
	@NotNull(message="坐标-经度不能为空")
	private Double lon;//坐标-经度
	
	@NotNull(message="坐标-纬度不能为空")
	private Double lat;//坐标-纬度
	
	@NotNull(message="关键词不能为空")
	private String keyword;//关键词
	
	@NotNull(message="分页 页码不能为空")
	private Integer page;//分页 页码

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "SearchKeywordsRequest [cityId=" + cityId + ", lon=" + lon + ", lat=" + lat + ", keyword=" + keyword + ", page="
			+ page + "]";
	}
	
}
