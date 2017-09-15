package com.xmniao.xmn.core.live.entity;

import com.xmniao.xmn.core.util.StrUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */
public class LiveHomeRecordEntity implements Serializable {
    private static final long serialVersionUID = -205645699643529149L;

    private Integer uid = 0;
    private Integer sex = 0;
    private Integer signType = 0;
    private String avatar = "";  //头像
    private String groupId = "";  //群组号
    private String nname = "";  //主播昵称
    private Integer anchorId = 0;  //主播id
    private String zbPhone = "";  //主播手机号码
    private Integer liveRecordId = 0;  //主播记录Id
    private String vedioUrl = "";  //视频流
    private String liveTime = "";  //直播时长
    private String title = "";  //直播标题
    private String cover = "";  //预告封面
    private Integer zhiboType = -1;  //直播类型 -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4历史通告 5结束直播
    private Integer sellerId = 0;  //店铺id
    private Integer existRoomLock = 0;  //房间是否加锁
    private String sellername = "";  //店铺名称
    private String anchorRoomNo = "";  //房间编号
    private Integer viewCount = 0;  //观看人数
    private Integer liveStartType = 0;  //开播类型 0 通告开播  1自定义开播
    private List<String> liveTagNameList = null;  //直播标签
    private String tag = "";  // 标签(消费过,已收藏，浏览过，附近的，优质的)
    private Integer lable = 0;  //标签号标签(0 空1消费过,2已收藏，3浏览过，4附近的，5优质的)
    private Integer isSell = 0;  //预售状态 1预售中  0无
    private String start_date = "";  //开播时间
    private String defaultDesc = "";   //占位图 文字说明
    private String defaultImg = "";  //占位图
    private Integer isNormalLive = 1;  //是否是正常的直播，0 否 1是
    private String zhiboAddress = "";  //直播地址
    private String distance = "";  //距离
    private Integer livePlatform = 1;  // 直播使用平台  1 腾讯直播  2 金山云直播
    private String liveRtmpUrl = "";    // 金山云直播url（推流或拉流），只有livePlatform = 2 才会有值

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public Integer getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(Integer anchorId) {
        this.anchorId = anchorId;
    }

    public String getZbPhone() {
        return zbPhone;
    }

    public void setZbPhone(String zbPhone) {
        this.zbPhone = zbPhone;
    }

    public Integer getLiveRecordId() {
        return liveRecordId;
    }

    public void setLiveRecordId(Integer liveRecordId) {
        this.liveRecordId = liveRecordId;
    }

    public String getVedioUrl() {
        return vedioUrl;
    }

    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getZhiboType() {
        return zhiboType;
    }

    public void setZhiboType(Integer zhiboType) {
        this.zhiboType = zhiboType;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getExistRoomLock() {
        return existRoomLock;
    }

    public void setExistRoomLock(Integer existRoomLock) {
        this.existRoomLock = existRoomLock;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getAnchorRoomNo() {
        return anchorRoomNo;
    }

    public void setAnchorRoomNo(String anchorRoomNo) {
        this.anchorRoomNo = anchorRoomNo;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLiveStartType() {
        return liveStartType;
    }

    public void setLiveStartType(Integer liveStartType) {
        this.liveStartType = liveStartType;
    }

    public List<String> getLiveTagNameList() {
        return liveTagNameList;
    }

    public void setLiveTagNameList(List<String> liveTagNameList) {
        this.liveTagNameList = liveTagNameList;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getLable() {
        return lable;
    }

    public void setLable(Integer lable) {
        this.lable = lable;
    }

    public Integer getIsSell() {
        return isSell;
    }

    public void setIsSell(Integer isSell) {
        this.isSell = isSell;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public Integer getIsNormalLive() {
        return isNormalLive;
    }

    public void setIsNormalLive(Integer isNormalLive) {
        this.isNormalLive = isNormalLive;
    }

    public String getDefaultDesc() {
        return defaultDesc;
    }

    public void setDefaultDesc(String defaultDesc) {
        this.defaultDesc = defaultDesc;
    }

    public String getZhiboAddress() {
        return zhiboAddress;
    }

    public void setZhiboAddress(String zhiboAddress) {
        this.zhiboAddress = zhiboAddress;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Integer getLivePlatform() {
        return livePlatform;
    }

    public void setLivePlatform(Integer livePlatform) {
        this.livePlatform = livePlatform;
    }

    public String getLiveRtmpUrl() {
        return liveRtmpUrl;
    }

    public void setLiveRtmpUrl(String liveRtmpUrl) {
        this.liveRtmpUrl = liveRtmpUrl;
    }

    @Override
    public String toString() {
        return "LiveHomeRecordEntity{" +
                "uid=" + uid +
                ", sex=" + sex +
                ", signType=" + signType +
                ", avatar='" + avatar + '\'' +
                ", groupId='" + groupId + '\'' +
                ", nname='" + nname + '\'' +
                ", anchorId=" + anchorId +
                ", zbPhone='" + zbPhone + '\'' +
                ", liveRecordId=" + liveRecordId +
                ", vedioUrl='" + vedioUrl + '\'' +
                ", liveTime='" + liveTime + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", zhiboType=" + zhiboType +
                ", sellerId=" + sellerId +
                ", existRoomLock=" + existRoomLock +
                ", sellername='" + sellername + '\'' +
                ", anchorRoomNo='" + anchorRoomNo + '\'' +
                ", viewCount=" + viewCount +
                ", liveStartType=" + liveStartType +
                ", liveTagNameList=" + liveTagNameList +
                ", tag='" + tag + '\'' +
                ", lable=" + lable +
                ", isSell=" + isSell +
                ", start_date='" + start_date + '\'' +
                ", defaultDesc='" + defaultDesc + '\'' +
                ", defaultImg='" + defaultImg + '\'' +
                ", isNormalLive=" + isNormalLive +
                ", zhiboAddress='" + zhiboAddress + '\'' +
                ", distance='" + distance + '\'' +
                ", livePlatform=" + livePlatform +
                ", liveRtmpUrl='" + liveRtmpUrl + '\'' +
                '}';
    }
}
