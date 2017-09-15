package com.xmniao.xmn.core.common.request.catehome;

import java.util.ArrayList;
import java.util.List;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: UneatRequest    
* @Description:看过不如一尝的请求类   
* @author: liuzhihao   
* @date: 2016年12月13日 下午6:19:31
 */
@SuppressWarnings("serial")
public class UneatRequest extends BaseRequest{

	@NotNull(message="纬度不能为空")
	private Double lon;//纬度
	
	@NotNull(message="经度不能为空")
	private Double lat;//经度
	
	@NotNull(message="城市id不能为空")
	private Integer cityid;//城市id
	
	private Integer zoneid;//商圈id
	
	private List<Integer> sellers = null;
	
	private String nostr;

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

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	public List<Integer> getSellers() {
		if(nostr != null){
			List<Integer> l = new ArrayList<Integer>();
			for(String s : nostr.split(",")){
				l.add(Integer.valueOf(s));
			}
			return l;
		}
		
		return null;
	}

	public void setSellers(List<Integer> sellers) {
		this.sellers = sellers;
	}

	public String getNostr() {
		return nostr;
	}

	public void setNostr(String nostr) {
		this.nostr = nostr;
	}
	
	
}
