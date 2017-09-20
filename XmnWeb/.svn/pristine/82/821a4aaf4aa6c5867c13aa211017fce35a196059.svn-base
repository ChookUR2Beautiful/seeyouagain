package com.xmniao.xmn.core.vstar.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

public class VstarLiverContent extends BaseEntity{
    /**
     * id
     */
    private Long id;

    /**
     * 教学标题
     */
    private String title;

    /**
     * 教学分类ID(t_vstar_dict)
     */
    private Long vstarDictId;

    /**
     * 封面图片
     */
    private String coverImg;

    /**
     * 选手ID
     */
    private Integer anchorId;

    /**
     * 直播开始时间
     */
    private Date beginTime;

    /**
     * 直播结束时间
     */
    private Date endTime;

    /**
     * 预告视频
     */
    private String foreshowUrl;

    /**
     * 回放视频
     */
    private String playbackUrl;

    /**
     * 0-999，数量越大排列越前
     */
    private Integer sortVal;

    /**
     * 有效状态，1有效，2无效
     */
    private Integer status;

    /**
     * 资源内容类型,1视频教学,2 实时远程教学
     */
    private Integer contentType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 鸟币金额
     */
    private BigDecimal zbalance;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 观看人数
     */
    private Integer viewNum;

    /**
     * 付费人数
     */
    private Integer payNum;

    /**
     * 教学内容
     */
    private String contentText;
    
    private String fileIds;
    
    private String videoIds;
    
    private List<VstarLiverContentAttachment> contentAttachments;
    
    private String anchorImage;
    
    private List<String> anchorImageList;
    
    private String beginTimeStr;
    
    private String endTimeStr;
    

	public String getBeginTimeStr() {
    	if(beginTime==null){
    		return null;
    	}else{
    		return DateUtil.formatDate(beginTime, DateUtil.Y_M_D_HMS);
    	}
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		if(endTime==null){
    		return null;
    	}else{
    		return DateUtil.formatDate(endTime, DateUtil.Y_M_D_HMS);
    	}
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public List<String> getAnchorImageList() {
    	if(StringUtils.isBlank(anchorImage)){
    		return new ArrayList<>();
    	}
		return Arrays.asList(anchorImage.split(","));
	}

	public void setAnchorImageList(List<String> anchorImageList) {
		this.anchorImageList = anchorImageList;
	}

	public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	public String getAnchorImage() {
		return anchorImage;
	}

	public void setAnchorImage(String anchorImage) {
		this.anchorImage = anchorImage;
	}

	public List<VstarLiverContentAttachment> getContentAttachments() {
		return contentAttachments;
	}

	public void setContentAttachments(List<VstarLiverContentAttachment> contentAttachments) {
		this.contentAttachments = contentAttachments;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public String getVideoIds() {
		return videoIds;
	}

	public void setVideoIds(String videoIds) {
		this.videoIds = videoIds;
	}

	/**
     * id
     * @return id id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 教学标题
     * @return title 教学标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 教学标题
     * @param title 教学标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 教学分类ID(t_vstar_dict)
     * @return vstar_dict_id 教学分类ID(t_vstar_dict)
     */
    public Long getVstarDictId() {
        return vstarDictId;
    }

    /**
     * 教学分类ID(t_vstar_dict)
     * @param vstarDictId 教学分类ID(t_vstar_dict)
     */
    public void setVstarDictId(Long vstarDictId) {
        this.vstarDictId = vstarDictId;
    }

    /**
     * 封面图片
     * @return cover_img 封面图片
     */
    public String getCoverImg() {
        return coverImg;
    }

    /**
     * 封面图片
     * @param coverImg 封面图片
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    /**
     * 直播开始时间
     * @return begin_time 直播开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 直播开始时间
     * @param beginTime 直播开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 直播结束时间
     * @return end_time 直播结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 直播结束时间
     * @param endTime 直播结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 预告视频
     * @return foreshow_url 预告视频
     */
    public String getForeshowUrl() {
        return foreshowUrl;
    }

    /**
     * 预告视频
     * @param foreshowUrl 预告视频
     */
    public void setForeshowUrl(String foreshowUrl) {
        this.foreshowUrl = foreshowUrl == null ? null : foreshowUrl.trim();
    }

    /**
     * 回放视频
     * @return playback_url 回放视频
     */
    public String getPlaybackUrl() {
        return playbackUrl;
    }

    /**
     * 回放视频
     * @param playbackUrl 回放视频
     */
    public void setPlaybackUrl(String playbackUrl) {
        this.playbackUrl = playbackUrl == null ? null : playbackUrl.trim();
    }

    /**
     * 0-999，数量越大排列越前
     * @return sort_val 0-999，数量越大排列越前
     */
    public Integer getSortVal() {
        return sortVal;
    }

    /**
     * 0-999，数量越大排列越前
     * @param sortVal 0-999，数量越大排列越前
     */
    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    /**
     * 有效状态，1有效，2无效
     * @return status 有效状态，1有效，2无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 有效状态，1有效，2无效
     * @param status 有效状态，1有效，2无效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 资源内容类型,1视频教学,2 实时远程教学
     * @return content_type 资源内容类型,1视频教学,2 实时远程教学
     */
    public Integer getContentType() {
        return contentType;
    }

    /**
     * 资源内容类型,1视频教学,2 实时远程教学
     * @param contentType 资源内容类型,1视频教学,2 实时远程教学
     */
    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getZbalance() {
		return zbalance;
	}

	public void setZbalance(BigDecimal zbalance) {
		this.zbalance = zbalance;
	}

	/**
     * 金额
     * @return amount 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 观看人数
     * @return view_num 观看人数
     */
    public Integer getViewNum() {
        return viewNum;
    }

    /**
     * 观看人数
     * @param viewNum 观看人数
     */
    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    /**
     * 付费人数
     * @return pay_num 付费人数
     */
    public Integer getPayNum() {
        return payNum;
    }

    /**
     * 付费人数
     * @param payNum 付费人数
     */
    public void setPayNum(Integer payNum) {
        this.payNum = payNum;
    }

    /**
     * 教学内容
     * @return content_text 教学内容
     */
    public String getContentText() {
        return HtmlUtils.htmlUnescape(contentText);
    }

    /**
     * 教学内容
     * @param contentText 教学内容
     */
    public void setContentText(String contentText) {
        this.contentText = contentText == null ? null : contentText.trim();
    }
}