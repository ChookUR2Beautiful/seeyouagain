package com.xmn.saas.entity.member;

/**
 * 
*      
* 类名称：MemberBill   
* 类描述： 会员信息  
* 创建人：xiaoxiong   
* 创建时间：2016年9月30日 下午1:59:59     
*
 */
public class MemberBill {
	
	/**
	 * 客户名称
	 */
	private String name;
	
	/**
	 * 是否绑定会员
	 */
	private int isBind;
	
	/**
	 * 用户id
	 */
	private int uid;
	
	/**
	 * 消费次数
	 */
	private int count;
	
	/**
	 * 支付时间
	 */
	private String zdate;
	
	/**
	 * 用户手机号码
	 */
	private String phone;
	
	/**
	 * 头像
	 */
	private String avatar;

	
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZdate() {
		return zdate;
	}

	public void setZdate(String zdate) {
		this.zdate = zdate;
	}

	public String getName() {
		if(name==null||name.length()==0){
			if(phone!=null && phone.length()==11){
				name=phone.substring(0,3)+"****"+phone.substring(7,11);
			}
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsBind() {
		return isBind;
	}

	public void setIsBind(int isBind) {
		this.isBind = isBind;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
