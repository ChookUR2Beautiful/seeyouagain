package com.xmniao.xmn.core.common.request.personal;


import java.io.Serializable;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PersonalInfoRequset   
* 类描述：   用户/主播详情请求参数
* 创建人：yezhiyong   
* 创建时间：2016年12月12日 下午4:30:08   
* @version    
*
 */
public class PersonalInfoRequset implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1364252587331865547L;

	/**
	 * 用户uid
	 */
	private Integer uid;
	
	/**
	 * 用户uid
	 */
	private String sessiontoken;
    private Integer userType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getSessiontoken() {
		return sessiontoken;
	}

	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}

	@Override
	public String toString() {
		return "PersonalInfoRequset [uid=" + uid + ", sessiontoken="
				+ sessiontoken + "]";
	}
	
}
