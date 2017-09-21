package com.xmniao.common;

/**
 * 分账比例枚举
 * 预设 分账比例
 * @author huangxiaobin
 *
 */
public enum LedgerRation {

	USER_RATION("userRation","用户","0.6"),
	MIKE_RATION("mikeRation","寻蜜客","0.25"),
	MEMBER_JOINT_RATION("bpartner_ration","用户区域合作商","0.05"),
	CONSUME_JOINT_RATION("cpartner_ration","消费区域合作商","0.05"),
	PLATFORM_RATION("platformRation","平台","0.05");
	
	private String rationName;
	
	private String description;
	
	private String ration;

	public String getRationName() {
		return rationName;
	}

	public void setRationName(String rationName) {
		this.rationName = rationName;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRation() {
		return ration;
	}

	public void setRation(String ration) {
		this.ration = ration;
	}

	private LedgerRation(String rationName,String description, String ration) {
		this.rationName = rationName;
		this.description  = description;
		this.ration = ration;
	}
	
	/**
	 * 根据分账方名称，获取其预置的分账比例值
	 * @param rationName 分账方 [user:用户 mike：寻蜜客 memberJoint：用户区域合作商 consumeJoint：消费合作商]
	 * @return
	 */
	public static String getRationByName(String rationName) {
		for (LedgerRation ration : LedgerRation.values()) {
			if (ration.getRationName().equals(rationName)) {
				return ration.getRation();
			}
		}
		return null;
	}
	
	/**
	 * 根据分账方名称，获取其预置的分账比例值
	 * @param rationName 分账方 [user:用户 mike：寻蜜客 memberJoint：用户区域合作商 consumeJoint：消费合作商]
	 * @return
	 */
	public static String getDescriptionByName(String rationName) {
		for (LedgerRation ration : LedgerRation.values()) {
			if (ration.getRationName().equals(rationName)) {
				return ration.getDescription();
			}
		}
		return null;
	}
}
