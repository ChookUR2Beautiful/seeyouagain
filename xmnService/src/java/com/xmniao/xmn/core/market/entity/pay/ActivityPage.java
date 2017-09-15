package com.xmniao.xmn.core.market.entity.pay;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @projectName: xmnService 
* @ClassName: ActivityPage    
* @Description:活动页分页   
* @author: liuzhihao   
 * @param <T>
* @date: 2016年12月29日 下午4:38:19
 */
@SuppressWarnings("serial")
public class ActivityPage<T> implements Serializable{

	private Integer state;
	
	private String info;
	
	private Integer totalCounts=0;
	
	private List<T> result;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(Integer totalCounts) {
		this.totalCounts = totalCounts;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ActivityPage [state=" + state + ", info=" + info + ", totalCounts=" + totalCounts + ", result=" + result + "]";
	}
	
}
