package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 主播管理员实体类
 * @author yhl
 * 2016-8-13 11:41:56
 * */
public class LiveManagerInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3951117931353335089L;

	//'主键 id',
	private Long id;
	//管理员uid,
	private Integer uid;
	//'管理员用户id',
	private Long manager_id;
	//'上级主播 用户id',
	private Long anchor_id;
	//'管理员状态   1启用   0停用',
	private Integer status; 
	//'创建时间',
	private Date create_time;
	//'更新时间',
	private Date update_time;
	//直播用户类型： 1 主播 2 普通用户
	private Integer utype; 
	//用户当前等级ID
	private Integer rank_id;
	//用户当前等级数
	private Integer rank_no; 
	//用户当前等级头衔名称
	private String achievement; 
	//用户当前持有经验
	private Integer current_expe;
	//管理员表记录ID
	private Long managerId; 
	//管理员表记录ID
	private String nname;
	//管理员表记录ID
	private String avatar;
	//管理员表记录ID
	private Integer sex;
	//管理员phone
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public Long getAnchor_id() {
		return anchor_id;
	}

	public void setAnchor_id(Long anchor_id) {
		this.anchor_id = anchor_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getUtype() {
		return utype;
	}

	public void setUtype(Integer utype) {
		this.utype = utype;
	}

	public Integer getRank_id() {
		return rank_id;
	}

	public void setRank_id(Integer rank_id) {
		this.rank_id = rank_id;
	}

	public Integer getRank_no() {
		return rank_no;
	}

	public void setRank_no(Integer rank_no) {
		this.rank_no = rank_no;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public Integer getCurrent_expe() {
		return current_expe;
	}

	public void setCurrent_expe(Integer current_expe) {
		this.current_expe = current_expe;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "LiveManagerInfo [id=" + id + ", uid=" + uid + ", manager_id="
				+ manager_id + ", anchor_id=" + anchor_id + ", status="
				+ status + ", create_time=" + create_time + ", update_time="
				+ update_time + ", utype=" + utype + ", rank_id=" + rank_id
				+ ", rank_no=" + rank_no + ", achievement=" + achievement
				+ ", current_expe=" + current_expe + ", managerId=" + managerId
				+ ", nname=" + nname + ", avatar=" + avatar + ", sex=" + sex
				+ ", phone=" + phone
				+ "]";
	}

}
