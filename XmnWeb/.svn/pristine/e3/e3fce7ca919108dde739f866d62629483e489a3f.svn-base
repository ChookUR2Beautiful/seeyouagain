/**   
 * 文件名：TAdvertising.java   
 *    
 * 日期：2014年11月19日10时27分38秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.common.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.FastfdsConstant;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TAdvertising
 * 
 * 类描述：广告轮播
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时27分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TAdvertising extends BaseEntity {

	private static final long serialVersionUID = 4168778484498181971L;
	private Integer id;// 广告ID
	private String adbpic;// 广告图片
	private String picLow;  //低分辨率广告图片
	private String picMiddle;  //中分辨率广告图片
	private String content;// 广告文本
//	private String subtitle;// 副标题
	private String adburl;// 广告链接
	private Integer sort;// 排序
	private Integer isshow;// 状态：1 待上线, 0已上线 2 已下线
	private Integer type;// 1=寻蜜鸟客户端|2=商户客户端|3=合作商客户端|4=香蜜客圈子广告|5 寻蜜鸟客户端banner
	private String remarks;// 备注
	private Integer isall;
	private String province;
	private String city;
	private String area;
	private String areaText;
	private String share_img; // 分享图片
	private String share_cont; // 分享文案 (长度300个字符)
	private String shareUrl;// 分享链接
	private String shareTitle;// 分享标题

	private Date startTime;//开始时间
	private Date endTime;//结束时间
	
	private Date startTimeStart;
	private Date startTimeEnd;
	
	
//	public String getSubtitle() {
//		return subtitle;
//	}
//
//	public void setSubtitle(String subtitle) {
//		this.subtitle = subtitle;
//	}

	public enum Isshow {
		PENDING("待上线", 1), ONLINE("已发送", 0), OFFLINE("已下线", 2);
		private String text;
		private Integer index;

		private Isshow(String text, Integer index) {
			this.text = text;
			this.index = index;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}

		public static String getName(String index) {
			for (Isshow c : Isshow.values()) {
				if (c.getIndex().equals(index)) {
					return c.text;
				}
			}
			return null;
		}

	}

	public String getStatusText() {
		String statusText;
		switch (getIsshow()) {
		case 1:
			statusText = "待上线";
			break;
		case 0:
			statusText = "已上线";
			break;
		case 2:
			statusText = "已下线";
			break;
		default:
			statusText = null;
		}
		return statusText;
	}

	public String getShare_img() {
		return share_img;
	}

	public void setShare_img(String share_img) {
		this.share_img = share_img;
	}

	public String getShare_cont() {
		return share_cont;
	}

	public void setShare_cont(String share_cont) {
		this.share_cont = share_cont;
	}

	/**
	 * 取得区域的初始化值
	 * 
	 * @return
	 */
	public String getAreaInitValue() {
		String st = null;
		if (this.isall!=null && this.isall == 0) {
			if (this.province != null && this.province.length() > 0) {
				st = getProvince();
			}
			if (this.city != null && this.city.length() > 0) {
				st = getCity();
			}
			if (this.area != null && this.area.length() > 0) {
				st = getArea();
			}
		}
		return st;
	}

	//获取是否显示文字说明
	public String getIsshowText(){
		if(isshow == null) return null;
		switch (isshow) {
		case 1:
			return "待上线";
		case 0:
			return "已上线";
		case 2:
			return "已下线";
		default:
			return null;

		}


	}

	// 获取全国显示
	public String getIsallText() {
		if (isall == null)
			return null;
		if (isall == 1)
			return "是";
		return "否";
	}

	// 获取完整的图片地址
	public String getAdbpicUrl() {
		return FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP + adbpic;
	}

	public String getTypeText() {
		if (type == null)
			return null;
		switch (type) {
		case 1:
			return "寻蜜鸟客户端美食";
		case 2:
			return "商户客户端";
		case 3:
			return "合作商客户端";
		case 4:
			return "寻蜜客圈子广告";
		case 5:
			return "寻蜜鸟客户端banner";
		case 6:
			return "微信商城生鲜";
		case 7:
			return "微信商城附近美食";
		case 8:
			return "寻蜜鸟客户端生鲜";
		case 9:
			return "寻蜜客主页";
		default:
			return null;
		}
	}

	/**
	 * 创建一个新的实例 TAdvertising.
	 * 
	 */
	public TAdvertising() {
		super();
	}

	public TAdvertising(Integer id) {
		this.id = id;
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
	 * adbpic
	 * 
	 * @return the adbpic
	 */
	public String getAdbpic() {
		return adbpic;
	}

	/**
	 * @param adbpic
	 *            the adbpic to set
	 */
	public void setAdbpic(String adbpic) {
		this.adbpic = adbpic;
	}

	/**
	 * content
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * adburl
	 * 
	 * @return the adburl
	 */
	public String getAdburl() {
		return adburl;
	}

	/**
	 * @param adburl
	 *            the adburl to set
	 */
	public void setAdburl(String adburl) {
		this.adburl = adburl;
	}

	/**
	 * sort
	 * 
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * isshow
	 * 
	 * @return the isshow
	 */
	public Integer getIsshow() {
		return isshow;
	}

	/**
	 * @param isshow
	 *            the isshow to set
	 */
	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}

	/**
	 * type
	 * 
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * remarks
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsall() {
		return isall;
	}

	public void setIsall(Integer isall) {
		this.isall = isall;
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

	@Override
	public String toString() {
		return "TAdvertising [id=" + id + ", adbpic=" + adbpic + ", content="
				+ content + ", adburl=" + adburl + ", sort=" + sort
				+ ", isshow=" + isshow + ", type=" + type + ", remarks="
				+ remarks + ", isall=" + isall + ", province=" + province
				+ ", city=" + city + ", area=" + area + "]";
	}

	public String getAreaText() {
		StringBuilder sb = new StringBuilder();
		if (this.isall != null && this.isall == 1) {
			sb.append("全国");
		} else {
			/*
			 * if(StringUtils.hasLength(this.province)){
			 * sb.append(AreaHandler.getAreaHandler
			 * ().getAreaIdByTitle(Integer.parseInt(this.province)));
			 * sb.append(" - "); }
			 */
			if (StringUtils.hasLength(this.city)) {
				sb.append(AreaHandler.getAreaHandler().getAreaIdByTitle(
						Integer.parseInt(this.city)));
				sb.append(" - ");
			}
			if (StringUtils.hasLength(this.area)) {
				String delim = ",";
				int index = this.area.indexOf(delim);
				if (index == -1) {
					delim = ";";
				}
				String[] areas = StringUtils.paresToArray(this.area, delim);
				for (int i = 0; i < areas.length; i++) {
					if (i > 0) {
						sb.append(",");
					}
					sb.append(AreaHandler.getAreaHandler().getAreaIdByTitle(
							Integer.parseInt(areas[i])));
				}
			}

		}
		areaText = sb.toString();
		return areaText;
	}

	public void setAreaText(String areaText) {
		this.areaText = areaText;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getPicMiddle() {
		return picMiddle;
	}

	public void setPicMiddle(String picMiddle) {
		this.picMiddle = picMiddle;
	}

	public String getPicLow() {
		return picLow;
	}

	public void setPicLow(String picLow) {
		this.picLow = picLow;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTimeStart() {
		return startTimeStart;
	}

	public void setStartTimeStart(Date stratTimeStart) {
		this.startTimeStart = stratTimeStart;
	}

	public Date getStartTimeEnd() {
		return startTimeEnd;
	}

	public void setStartTimeEnd(Date startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}
}
