package com.xmniao.xmn.core.live_anchor.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveRobot
 *
 * 类描述：机器人实体类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-24下午7:42:09
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class AndroidAvatarForm {

	private List<BLiveAnchorImage> robotAvatars = new ArrayList<BLiveAnchorImage>();
	
	private String relativePath;

	/**
	 * @return the robotAvatars
	 */
	public List<BLiveAnchorImage> getRobotAvatars() {
		return robotAvatars;
	}

	/**
	 * @param robotAvatars the robotAvatars to set
	 */
	public void setRobotAvatars(List<BLiveAnchorImage> robotAvatars) {
		this.robotAvatars = robotAvatars;
	}

	/**
	 * @return the relativePath
	 */
	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * @param relativePath the relativePath to set
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	
	
	
    
}