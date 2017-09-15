package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *  描述 ：关注的主播实体类
 *  @author yhl
 *  2016年8月13日15:30:51
 * */
public class LiveFocusInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1283435480296027967L;
	
	//关注直播记录id 
	private Long id;
	//发起关注用户ID
	private Long liver_str_id;
	//被关注用户ID
	private Long liver_end_id;
	//创建时间
	private Date create_time;
	//更新时间
	private Date update_time;
	
//	//寻蜜鸟会员id
//	private Long uid;
//	//直播用户类型： 1 主播 2 普通用户
//	private Integer utype;
//	//用户当前等级ID
//	private Long rank_id;
//	//用户当前等级数
//	private Integer rank_no;
//	//用户当前等级头衔名称
//	private String achievement;
//	//用户当前持有经验
//	private Long current_expe;
	
	
	
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLiver_str_id() {
		return liver_str_id;
	}
	public void setLiver_str_id(Long liver_str_id) {
		this.liver_str_id = liver_str_id;
	}
	public Long getLiver_end_id() {
		return liver_end_id;
	}
	public void setLiver_end_id(Long liver_end_id) {
		this.liver_end_id = liver_end_id;
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
	

}
