package com.xmniao.xmn.core.user_terminal.util;

public class UserConstants {
	public static final int CLIENT = 1;//用户端消息推送
	public static final int  RESULTNUM_INIT=0; //0 
	public static final int  RESULTNUM=0; //0 
	public static final int  WORD_LENGTH=12; //12 长度参数
	public static final int  FIRELOGIN_SUCCESS=1; //事件成功失败日志记录标志 1:成功
	public static final int  FIRELOGIN_ERROR=0; //事件成功失败日志记录标志 0:失败
	
	//拿到评论的状态值，为1为显示，0为不显示，如果状态为0，即使删除，评论的数目也不做更改
	public static final int TOPIC_SHOW = 1;
	public static final int TOPIC_BLANK = 0;
	// Type:0  代表是话题评论，1 代表回复评论。评论是针对话题的，  回复是指向评论的
	public static final int TOPIC_TYPE_0 = 0;
	public static final int TOPIC_TYPE_1 = 1;
	
	public static final int POST_COMMENT_1 = 1; //帖子列表 1 
	public static final int POST_COMMENT_2 = 2;//举报列表2
	public static final int POST_COMMENT_3 = 3;//回收站列表3
	
	public static final int BANNER_STATUS_STAY_ONLINE = 1;//待上线1
	public static final int BANNER_STATUS_ONLINE = 0;//已上线 0
	public static final int BANNER_STATUS_OFFLINE = 2;//已下线 2
	
	public static final Integer BANNER_POSITION_INDIANA = 6;//夺宝活动
	public static final Integer BANNER_POSITION_AUCTION = 5;//竞拍活动
	
	/**
	 * 是重点推荐
	 */
	public static final int BANNER_IS_EMPHASIS = 1;//是重点推荐
	/**
	 * 不是重点推荐
	 */
	public static final int BANNER_IS_NOT_EMPHASIS = 0;//不是重点推荐
	
}
