/**   
 * 文件名：TSellerApply.java   
 *    
 * 日期：2014年11月11日15时50分01秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSellerApply
 * 
 * 类描述：商户信息修改申请
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时50分01秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSellerInternalInfo extends BaseEntity  {
	
	private static final long serialVersionUID = 2075321613514997264L;
	
	private Integer  msgId; //消息ID,
	private String   title;//标题
	private String   subtitle; //副标题
	private String   urlMessageImg; // 消息图标url
	private String   urlArticleFocusImg;// 文章图标url
	private Date     dateSend; //开始推送时间 
	private Date     dateEndSend; //结束推送时间 
	private String   sendObject; //'发送对象，为空发送给所有用户。不为空发送给指定用户。{object:[{"商家编号":"3456","商家名称":"面点王"}]}',
	private String   content; //'文章内容 
	private Integer  sendNum; //发送人数
	private Integer  readNum; //已阅读人数
	private Integer  dataState; //'数据状态：-1：删除，0：待发送，1：已发送',
	private String   creator;   //创建人
	private Date     dateCreated; //创建时间
	private String   updator;//更新人
	private Date     dateUpdated;//修改时间
	
	private Integer  rsId; //消息关联商家主键id,
	private Integer  sellerid;//商家id
	
	private Date dateSendStart;// 发送时间（搜索条件）
	private Date dateSendEnd;// 发送时间（搜索条件）
	private Date cdateStart;// 创建时间（搜索条件）
	private Date cdateEnd;// 创建时间（搜索条件）
	private Date dateEndSendS;// 结束推送时间 （搜索条件）
	private Date dateEndSendE;// 结束推送时间 （搜索条件）
	private Integer sendNumStart;//点赞数(搜索条件)
	private Integer sendNumEnd;//点赞数(搜索条件)
	private Integer readNumStart;//点赞数(搜索条件)
	private Integer readNumEnd;//点赞数(搜索条件)
	private String area;//区域id
	private String city;//市id
	private String province;//省id
	private String zoneid;//商圈id
	private Integer isSendImmediate;//是否即时发送 0：否 1：是 
	
	private String ptitle;// 省名称
	private String ctitle;// 市名称
	private String atitle;// 区名称
	private String btitle;// 商圈标题
	
	
	/**
	 * 创建一个实例
	 */
	public TSellerInternalInfo() {
		super();
	}
	
	
	
	/**
	 * @return the ptitle
	 */
	public String getPtitle() {
		return ptitle;
	}



	/**
	 * @param ptitle the ptitle to set
	 */
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}



	/**
	 * @return the ctitle
	 */
	public String getCtitle() {
		return ctitle;
	}



	/**
	 * @param ctitle the ctitle to set
	 */
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}



	/**
	 * @return the atitle
	 */
	public String getAtitle() {
		return atitle;
	}



	/**
	 * @param atitle the atitle to set
	 */
	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}



	/**
	 * @return the btitle
	 */
	public String getBtitle() {
		return btitle;
	}



	/**
	 * @param btitle the btitle to set
	 */
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}



	/**
	 * @return the dateEndSend
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateEndSend() {
		return dateEndSend;
	}



	/**
	 * @param dateEndSend the dateEndSend to set
	 */
	public void setDateEndSend(Date dateEndSend) {
		this.dateEndSend = dateEndSend;
	}



	/**
	 * @return the dateEndSendS
	 */
	public Date getDateEndSendS() {
		return dateEndSendS;
	}



	/**
	 * @param dateEndSendS the dateEndSendS to set
	 */
	public void setDateEndSendS(Date dateEndSendS) {
		this.dateEndSendS = dateEndSendS;
	}



	/**
	 * @return the dateEndSendE
	 */
	public Date getDateEndSendE() {
		return dateEndSendE;
	}



	/**
	 * @param dateEndSendE the dateEndSendE to set
	 */
	public void setDateEndSendE(Date dateEndSendE) {
		this.dateEndSendE = dateEndSendE;
	}



	/**
	 * @return the isSendImmediate
	 */
	public Integer getIsSendImmediate() {
		return isSendImmediate;
	}



	/**
	 * @param isSendImmediate the isSendImmediate to set
	 */
	public void setIsSendImmediate(Integer isSendImmediate) {
		this.isSendImmediate = isSendImmediate;
	}



	/**
	 * @return the rsId
	 */
	public Integer getRsId() {
		return rsId;
	}



	/**
	 * @param rsId the rsId to set
	 */
	public void setRsId(Integer rsId) {
		this.rsId = rsId;
	}



	/**
	 * @return the sellerid
	 */
	public Integer getSellerid() {
		return sellerid;
	}



	/**
	 * @param sellerid the sellerid to set
	 */
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}



	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}



	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}



	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}



	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}



	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}



	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}




	/**
	 * @return the zoneid
	 */
	public String getZoneid() {
		return zoneid;
	}



	/**
	 * @param zoneid the zoneid to set
	 */
	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}



	/**
	 * @return the msgId
	 */
	public Integer getMsgId() {
		return msgId;
	}




	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}




	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}




	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}




	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}




	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}




	/**
	 * @return the urlMessageImg
	 */
	public String getUrlMessageImg() {
		return urlMessageImg;
	}




	/**
	 * @param urlMessageImg the urlMessageImg to set
	 */
	public void setUrlMessageImg(String urlMessageImg) {
		this.urlMessageImg = urlMessageImg;
	}




	/**
	 * @return the urlArticleFocusImg
	 */
	public String getUrlArticleFocusImg() {
		return urlArticleFocusImg;
	}




	/**
	 * @param urlArticleFocusImg the urlArticleFocusImg to set
	 */
	public void setUrlArticleFocusImg(String urlArticleFocusImg) {
		this.urlArticleFocusImg = urlArticleFocusImg;
	}



	
	/**
	 * @return the dateSend
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateSend() {
		return dateSend;
	}




	/**
	 * @param dateSend the dateSend to set
	 */
	public void setDateSend(Date dateSend) {
		this.dateSend = dateSend;
	}




	/**
	 * @return the sendObject
	 */
	public String getSendObject() {
		return sendObject;
	}




	/**
	 * @param sendObject the sendObject to set
	 */
	public void setSendObject(String sendObject) {
		this.sendObject = sendObject;
	}




	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}




	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}




	/**
	 * @return the sendNum
	 */
	public Integer getSendNum() {
		return sendNum;
	}




	/**
	 * @param sendNum the sendNum to set
	 */
	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}




	/**
	 * @return the readNum
	 */
	public Integer getReadNum() {
		return readNum;
	}




	/**
	 * @param readNum the readNum to set
	 */
	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}




	/**
	 * @return the dataState
	 */
	public Integer getDataState() {
		return dataState;
	}




	/**
	 * @param dataState the dataState to set
	 */
	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}




	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}




	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}




	/**
	 * @return the dateCreated
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateCreated() {
		return dateCreated;
	}




	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}




	/**
	 * @return the updator
	 */
	public String getUpdator() {
		return updator;
	}




	/**
	 * @param updator the updator to set
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}




	/**
	 * @return the dateUpdated
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateUpdated() {
		return dateUpdated;
	}




	/**
	 * @param dateUpdated the dateUpdated to set
	 */
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}




	/**
	 * @return the dateSendStart
	 */
	public Date getDateSendStart() {
		return dateSendStart;
	}




	/**
	 * @param dateSendStart the dateSendStart to set
	 */
	public void setDateSendStart(Date dateSendStart) {
		this.dateSendStart = dateSendStart;
	}




	/**
	 * @return the dateSendEnd
	 */
	public Date getDateSendEnd() {
		return dateSendEnd;
	}




	/**
	 * @param dateSendEnd the dateSendEnd to set
	 */
	public void setDateSendEnd(Date dateSendEnd) {
		this.dateSendEnd = dateSendEnd;
	}




	/**
	 * @return the cdateStart
	 */
	public Date getCdateStart() {
		return cdateStart;
	}




	/**
	 * @param cdateStart the cdateStart to set
	 */
	public void setCdateStart(Date cdateStart) {
		this.cdateStart = cdateStart;
	}




	/**
	 * @return the cdateEnd
	 */
	public Date getCdateEnd() {
		return cdateEnd;
	}




	/**
	 * @param cdateEnd the cdateEnd to set
	 */
	public void setCdateEnd(Date cdateEnd) {
		this.cdateEnd = cdateEnd;
	}




	/**
	 * @return the sendNumStart
	 */
	public Integer getSendNumStart() {
		return sendNumStart;
	}




	/**
	 * @param sendNumStart the sendNumStart to set
	 */
	public void setSendNumStart(Integer sendNumStart) {
		this.sendNumStart = sendNumStart;
	}




	/**
	 * @return the sendNumEnd
	 */
	public Integer getSendNumEnd() {
		return sendNumEnd;
	}




	/**
	 * @param sendNumEnd the sendNumEnd to set
	 */
	public void setSendNumEnd(Integer sendNumEnd) {
		this.sendNumEnd = sendNumEnd;
	}




	/**
	 * @return the readNumStart
	 */
	public Integer getReadNumStart() {
		return readNumStart;
	}




	/**
	 * @param readNumStart the readNumStart to set
	 */
	public void setReadNumStart(Integer readNumStart) {
		this.readNumStart = readNumStart;
	}




	/**
	 * @return the readNumEnd
	 */
	public Integer getReadNumEnd() {
		return readNumEnd;
	}




	/**
	 * @param readNumEnd the readNumEnd to set
	 */
	public void setReadNumEnd(Integer readNumEnd) {
		this.readNumEnd = readNumEnd;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TSellerInternalInfo [msgId=" + msgId + ", title=" + title
				+ ", subtitle=" + subtitle + ", urlMessageImg=" + urlMessageImg
				+ ", urlArticleFocusImg=" + urlArticleFocusImg + ", dateSend="
				+ dateSend + ", sendObject=" + sendObject + ", content="
				+ content + ", sendNum=" + sendNum + ", readNum=" + readNum
				+ ", dataState=" + dataState + ", creator=" + creator
				+ ", dateCreated=" + dateCreated + ", updator=" + updator
				+ ", dateUpdated=" + dateUpdated + ", dateSendStart="
				+ dateSendStart + ", dateSendEnd=" + dateSendEnd
				+ ", cdateStart=" + cdateStart + ", cdateEnd=" + cdateEnd
				+ ", sendNumStart=" + sendNumStart + ", sendNumEnd="
				+ sendNumEnd + ", readNumStart=" + readNumStart
				+ ", readNumEnd=" + readNumEnd + "]";
	}
}
