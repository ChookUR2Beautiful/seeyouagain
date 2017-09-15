package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class EditUserCVRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	private Integer ishide;//是否隐藏个人简历信息 0：不隐藏 1：隐藏
	
	private Integer shieldshop;//屏蔽店铺Id
	
	private Integer id;//简历ID(为空时则新建简历)
	
	@NotNull(message="个人信息实体类不能为空")
	private String info;//个人信息实体类

	public Integer getIshide() {
		return ishide;
	}

	public void setIshide(Integer ishide) {
		this.ishide = ishide;
	}

	public Integer getShieldshop() {
		return shieldshop;
	}

	public void setShieldshop(Integer shieldshop) {
		this.shieldshop = shieldshop;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "EditUserCVRequest [ishide=" + ishide + ", shieldshop="
				+ shieldshop + ", id=" + id + ", info=" + info + "]";
	}
	
}
