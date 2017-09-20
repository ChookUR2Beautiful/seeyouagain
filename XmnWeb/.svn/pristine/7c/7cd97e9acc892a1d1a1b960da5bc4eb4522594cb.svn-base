package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;import org.apache.commons.lang.StringUtils;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

public class VstarComment extends BaseEntity{
    /**
     * id
     */
    private Integer id;

    /**
     * 选手ID
     */
    private Integer playerId;

    /**
     * 昵称
     */
    private String nname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 点评内容
     */
    private String content;

    /**
     * 点评人会员编号
     */
    private Integer uid;

    /**
     * 点评时间
     */
    private Date createTime;

    /**
     * 评论状态 0正常 1删除
     */
    private Integer state;
    
    private String playerName;
    
    private String playerPhone;
    
    private String userName;
    
    private String userPhone;
    
    private String createTimeStr;
    
    public String getCreateTimeStr() {
    	if(createTime==null){
    		return "";
    	}
		return DateUtil.formatDate(createTime, DateUtil.Y_M_D_HMS);
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerPhone() {
		return playerPhone;
	}

	public void setPlayerPhone(String playerPhone) {
		this.playerPhone = playerPhone;
	}

	/**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 选手ID
     * @return player_id 选手ID
     */
    public Integer getPlayerId() {
        return playerId;
    }

    /**
     * 选手ID
     * @param playerId 选手ID
     */
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    /**
     * 昵称
     * @return nname 昵称
     */
    public String getNname() {
        return nname;
    }

    /**
     * 昵称
     * @param nname 昵称
     */
    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    /**
     * 手机号
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 点评内容
     * @return content 点评内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 点评内容
     * @param content 点评内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 点评人会员编号
     * @return uid 点评人会员编号
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 点评人会员编号
     * @param uid 点评人会员编号
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 点评时间
     * @return create_time 点评时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 点评时间
     * @param createTime 点评时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 评论状态 0正常 1删除
     * @return state 评论状态 0正常 1删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 评论状态 0正常 1删除
     * @param state 评论状态 0正常 1删除
     */
    public void setState(Integer state) {
        this.state = state;
    }
}