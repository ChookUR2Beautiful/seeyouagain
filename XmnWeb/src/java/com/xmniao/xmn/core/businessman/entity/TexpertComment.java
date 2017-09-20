package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

public class TexpertComment extends BaseEntity {
	private static final long serialVersionUID = 4257434968381693521L;
	public Integer sellerid;// 商家id
	public String sellername;// 商家名称
	public Integer id;// 达人评论表ID
	public String name;// 达人姓名
	public String experttitle;// 达人头衔
	public String expertpic;// 达人头像
	public String content;// 评论内容
	public Date sdate;// 创建时间
	public String sdateStr;
	public Date udate;// 更新时间
	public String udateStr;

	public String getSdateStr() {
		if (null != sdate)
			return DateUtil.smartFormat(sdate);
		if (null != sdate)
			return "--";
		return sdateStr;
	}

	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}

	public String getUdateStr() {
		if (null != udate)
			return DateUtil.smartFormat(udate);
		if (null != udate)
			return "--";
		return udateStr;
	}

	public void setUdateStr(String udateStr) {
		this.udateStr = udateStr;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExperttitle() {
		return experttitle;
	}

	public void setExperttitle(String experttitle) {
		this.experttitle = experttitle;
	}

	public String getExpertpic() {
		return expertpic;
	}

	public void setExpertpic(String expertpic) {
		this.expertpic = expertpic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

}