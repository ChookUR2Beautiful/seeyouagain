package com.xmniao.xmn.core.live.response;

/**
 * 
* @projectName: xmnService 
* @ClassName: RichFriendResponse    
* @Description:壕友列表返回类   
* @author: liuzhihao   
* @date: 2017年3月1日 上午11:51:37
 */
public class RichFriendResponse {

	private String avatar ; //用户头像
	
	private String sex;//性别1男 2女 默认为1
	
	private String nname ;//昵称
	
	private String birdCoin;//贡献鸟币
	
	private String label;//标签 0直接壕友 1间接壕友
	
	private String level;//等级 0普通用户 1普通粉丝 2vip粉丝 3钻石粉丝
	
	private String levelName;//等级名称
	
	private String fuid;//用户ID
	
	private String levelImage;//等级图片
	
	private String friendCounts;//壕友数量

	
	private String birdCoinStatus;//贡献值状态 1 预计贡献  2直接贡献
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getBirdCoin() {
		return birdCoin;
	}

	public void setBirdCoin(String birdCoin) {
		this.birdCoin = birdCoin;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getFuid() {
		return fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	public String getLevelImage() {
		return levelImage;
	}

	public void setLevelImage(String levelImage) {
		this.levelImage = levelImage;
	}
	
	public String getFriendCounts() {
		return friendCounts;
	}

	public void setFriendCounts(String friendCounts) {
		this.friendCounts = friendCounts;
	}

	public String getBirdCoinStatus() {
		return birdCoinStatus;
	}

	public void setBirdCoinStatus(String birdCoinStatus) {
		this.birdCoinStatus = birdCoinStatus;
	}

	@Override
	public String toString() {
		return "RichFriendResponse [avatar=" + avatar + ", sex=" + sex + ", nname=" + nname + ", birdCoin=" + birdCoin + ", label="
			+ label + ", level=" + level + ", levelName=" + levelName + ", fuid=" + fuid + ", levelImage=" + levelImage
			+ ", friendCounts=" + friendCounts + ", birdCoinStatus=" + birdCoinStatus + "]";
	}
	
}
