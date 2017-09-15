package com.xmniao.xmn.core.base;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class MongoBaseResponse<T> implements Serializable{

	private Integer kind;
	
	private Integer page;
	
	private Integer source;//数据来源 0 : 商圈  1城市 2....
	
	private List<T> result;

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	
}
