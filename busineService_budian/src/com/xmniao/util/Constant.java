package com.xmniao.util;



/**
 * @项目名称：saasService
 * @类名称：Constant
 * @类描述：	常量类
 * @创建人： zhangchangyuan
 * @创建时间 2016年3月29日 下午5:32:36
 * @version
 */
public class Constant {

	//短信发送部分
	public static final String APP_ID = "998899";
	
	//短信发送接口
	public static final String SMS_URL = "http://192.168.50.111:8080/smsService/";
	
	//当前版本号
	public static final String CURRENT_VERSION = "1";
	
	//分页,每页默认条数
	public static final Integer PAGE_LIMIT = 20;
	
	//手机号码正则表达式
	public static final String PHONE_REG = "^(1[\\d]{10})";
	
	/**
	 * 寻蜜鸟签约金额
	 */
	public static final Double SIGN_AMOUNT = 108D;
	
	/**
	 * 批量更新最大条数
	 */
	public static final Integer MAX_UPDATE_ROW = 500;
	
	/**
	 * 批量插入最大条数
	 */
	public static final Integer MAX_INSERT_ROW = 2000;
	
	/**
	 * 优惠券类型:普通优惠券
	 */
	public static final Integer COUPON_TYPE = 1;
	
	/**
	 * 用户登录缓存KEY
	 */
	public static final String USER_XMER_KEY = "USER_XMER_";
	
	/**
	 * 押金套餐缓存KEY
	 */
	public static String SAAS_PAKAGES_KEY = "SAAS_PAKAGES_KEY";
	
	/**
	 * 寻蜜客成就等级KEY
	 */
	public static String XMER_LEVEL_TITLE_KEY = "XMER_LEVEL_TITLE";
	
	
	/**
	 * 热点人物
	 */
	public static String XMER_RANK_KEY	="XMER_RANK";	
	
	/**
	 * 好友申请缓存KEY
	 */
	public static String FRIEND_APPLICANTS_KEY = "FRIEND_APPLICANTS_";
	
	/**
	 * 我的好友缓存KEY
	 */
	public static String MY_FRIEND_KEY = "MY_FRIEND_";
	
	/**
	 * 用户信息缓存KEY
	 */
	public static String XMER_INFO_KEY = "XMER_INFO_";
	
	/**
	 * 押金明细缓存KEY
	 */
	public static String MY_DEPOSIT_KEY = "MY_DEPOSIT_";
	
	/**
	 * 商户流水明细
	 */
	public static String SELLER_FLOW_KEY = "SELLER_FLOW_";
	
	
	/**
	 * 返回结果正确时
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * 返回结果不正确时
	 */
	public static final String ERROR = "error";
	
	/**
	 * 是
	 */
	public static final Integer STATUS_YES = 1;
	
	/**
	 * 否
	 */
	public static final Integer STATUS_NO = 0;
	
	/**
	 * 需要
	 */
	public static final Integer STATUS_NEED = 1;
	
	/**
	 * 不需要
	 */
	public static final Integer STATUS_NO_NEED = 2;
	
	/**
	 * 同意
	 */
	public static final Integer STATUS_AGREE = 1;
	
	/**
	 * 不同意
	 */
	public static final Integer STATUS_NO_AGREE = 0;
	
	/**
	 * 有
	 */
	public static final Integer STATUS_HAVE = 1;
	
	/**
	 * 没有
	 */
	public static final Integer STATUS_NO_HAVE = 0; 
	
	/**
	 * 商户申请状态：未验证
	 */
	public static final Integer SELLER_NOT_VERIFIED_STATUS=0;
	
	/**
	 * 商户状态：审核中
	 */
	public static final Integer SELLER_AUDIT_STATUS = 1;
	
	/**
	 *商户状态：未通过
	 */
	public static final Integer SELLER_NOT_PASSED_STATUS = 2;
	
	/**
	 * 商户状态：已签约
	 */
	public static final Integer SELLER_SIGNED_STATUS = 3;
	
	/**
	 * 商户状态：未签约
	 */
	public static final Integer SELLER_NOT_SIGN_STATUS = 4;
	
	/**
	 * 未上线
	 */
	public static final Integer STATUS_NOT_ON_LINE = 0;	 
	
	/**
	 * 已上线
	 */
	public static final Integer STATUS_ON_LINE = 1;
	
	/**
	 * 预上线
	 */
	public static final Integer STATUS_APPOINT_ON_LINE = 2;
	
	/**
	 * 已下线
	 */
	public static final Integer STATUS_OFF_LINE = 3;
	
	/**
	 * 商家账号类型：商家管理账号类型
	 */
	public static final Integer SELLER_ACCOUNT_TYPE = 1;
	
	/**
	 * 商家账号类型：收银员账号类型
	 */
	public static final Integer SELLER_CASHIER_TYPE = 2;
	
	/**
	 * 商家账号类型：普通店员账号类型
	 */
	public static final Integer SELLER_CLERK_TYPE = 3;
	
	/**
	 * 折扣类型：常规折扣
	 */
	public static final Integer AGIO_ROUTINE_DISCOUNT_TYPE = 1;
	
	/**
	 *折扣类型：周末折扣 
	 */
	public static final Integer AGIO_WEEK_DISCOUNT_TYPE = 2;
	
	/**
	 * 折扣类型：自定义折扣
	 */
	public static final Integer AGIO_CUSTOM_DISCOUNT_TYPE = 3;
	
	/**
	 * 折扣状态：开启
	 */
	public static final Integer AGIO_DISCOUNT_STATUS_OPEN = 1;
	
	/**
	 * 折扣状态：关闭
	 */
	public static final Integer AGIO_DISCOUNT_STATUS_CLOSE = 2;
	
	/**
	 * 数据来源：全部资料
	 */
	public static final Integer APPLY_SOURCE_ALL = 0;
	
	/**
	 * 数据来源：商家基本信息修改
	 */
	public static final Integer APPLY_SOURCE_BASE = 1;
	
	/**
	 *  数据来源：商家图片信息修改
	 */
	public static final Integer APPLY_SOURCE_PIC = 2;

	/**
	 * 数据来源：商家其他信息修改
	 */
	public static final Integer APPLY_SOURCE_OTHER = 3;
	
	/**
	 * 审核状态：待审核
	 */
	public static final Integer STATUS_AUDIT = 0;
	
	/**
	 * 审核状态：审核通过
	 */
	public static final Integer STATUS_AUDIT_PASS = 1;
	
	/**
	 * 审核状态：审核未通过
	 */
	public static final Integer STATUS_AUDIT_NOT_PASS = 2;

	/**
	 * 审核状态：撤销审核申请
	 */
	public static final Integer STATUS_AUDIT_DROP = 3;
	
	/**
	 * 版本类型：安卓(android)
	 */
	public static final Integer VTYPE_ANDROID = 1;
	
	/**
	 * 版本类型：苹果(ios)
	 */
	public static final Integer VTYPE_IOS = 2;
	
	/**
	 * 版本类型：wp
	 */
	public static final Integer VTYPE_WP = 3;
	
	/**
	 * 版本类型：其他
	 */
	public static final Integer VTYPE_OTHER = 4;
	
	/**
	 *商家会员卡状态：未上线 
	 */
	public static final Integer CARD_STATUS_NOT_ON_LINE = 0;
	
	/**
	 * 商家会员卡状态：已上线 
	 */
	public static final Integer CARD_STATUS_ON_LINE = 1;
	
	/**
	 * 商家会员卡状态：已下线 
	 */
	public static final Integer CARD_STATUS_OFF_LINE = 2;
	
	/**
	 * 活动类型：寻蜜鸟平台活动
	 */
	public static final Integer ACTIVITY_TYPE_PLATFORM = 1;
	
	/**
	 * 活动类型：商家自定义活动
	 */
	public static final Integer ACTIVITY_TYPE_SELLER = 2;
	
	/**
	 * 参加营销活动
	 */
	public static final Integer IS_ATTENDED_ACTIVITY = 0;
	
	/**
	 * 不参加营销活动
	 */
	public static final Integer IS_NOT_ATTENDED_ACTIVITY = 1;
	
	/**
	 * 优惠卷发放类型：自动发放
	 */
	public static final Integer COUPON_SEND_TYPE_AUTO = 1;
	
	/**
	 * 优惠卷发放类型：主页领取
	 */
	public static final Integer COUPON_SEND_TYPE_RECEIVE = 2;
	/**
	 * 优惠卷类型：普通优惠卷
	 */
	public static final Integer COUPON_ORDINARY_TYPE = 1;
	
	/**
	 * 优惠卷发放状态：发送中
	 */
	public static final Integer COUPON_SEND_STATUS = 1;
	
	/**
	 * 优惠卷发放状态：发送完
	 */
	public static final Integer COUPON_SEND_STATUS_FINSH = 2;
	
	/**
	 * 优惠卷使用状态：未使用
	 */
	public static final Integer COUPON_USE_STATUS_UNUSE = 0;
	
	/**
	 * 优惠卷使用状态：锁定
	 */
	public static final Integer COUPON_USE_STATUS_LOCK = 1;
	
	/**
	 * 优惠卷使用状态：已使用
	 */
	public static final Integer COUPON_USE_STATUS_USED= 2;
	
	/**
	 * 优惠卷发放状态：发送暂停
	 */
	public static final Integer COUPON_SEND_STATUS_PAUSE = 3;
	
	/**
	 * 优惠卷类型：随机优惠卷
	 */
	public static final Integer COUPON_RANDOM_TYPE= 2;
	
	/**
	 * 推送类型：消息推送
	 */
	public static final Integer PUSH_TYPE_NEWS = 1;
	
	/**
	 *推送类型：短信推送 
	 */
	public static final Integer PUSH_TYPE_MSG = 2;
	
	/**
	 * 充值卡的数据状态：正常
	 */
	public static final Integer ISSUE_CARD_DSSTATUS_RIGHT = 0;
	
	/**
	 * 充值卡的数据状态：删除
	 */
	public static final Integer ISSUE_CARD_DSSTATUS_REMOVE = 1;
	
	/**
	 * 充值方式：不是默认充值方式
	 */
	public static final Integer ISDEFAULT_YES = 0;
	
	/**
	 * 充值方式：是默认充值方式
	 */
	public static final Integer ISDEFAULT_NO = 1;
	
	/**
	 * 修改卡信息审核状态：待审核
	 */
	public static final Integer APPLY_STATUS_AUDIT_BEFORE = 0;
	
	/**
	 * 修改卡信息审核状态：审核通过
	 */
	public static final Integer APPLY_STATUS_AUDIT_PASS = 1;
	
	/**
	 * 修改卡信息审核状态：审核不通过
	 */
	public static final Integer APPLY_STATUS_AUDIT_NO_PASS = 2;
	
	/**
	 * 修改卡信息审核状态：已删除
	 */
	public static final Integer APPLY_STATUS_AUDIT_REMOVE = 3;
	
	/**
	 * 申请修改卡信息类型：申请开通预付卡
	 */
	public static final Integer APPLY_TYPE_PREPAY_CARD = 1;
	
	/**
	 * 申请修改卡信息类型：申请修改会员卡说明
	 */
	public static final Integer APPLY_TYPE_MENBER_CARD = 2;
	
	/**
	 * 会员卡类型：商家会员卡
	 */
	public static final Integer CARD_TYPE_SELLER = 1;
	
	/**
	 * 会员卡类型：生鲜会员卡
	 */
	public static final Integer CARD_TYPE_FRESH = 2;
	
	/**
	 * 会员卡状态：激活
	 */
	public static final Integer CARD_STATUS_OPEN = 1;
	
	/**
	 * 会员卡状态：注销
	 */
	public static final Integer CARD_STATUS_LOGOUT = 2;
	
	/**
	 * 会员卡状态：锁定
	 */
	public static final Integer CARD_STATUS_LOCK = 3;
	
	/**
	 * 图片类型：商铺环境图
	 */
	public static final Integer SELLER_PIC_TYPE = 1;
	
	/**
	 * 图片类型：商铺菜品图
	 */
	public static final Integer SELLER_FOOD_TYPE = 2;
	
	/**
	 * 调单状态：处理中
	 */
	public static final Integer HANDLER_STATUS_ADJUST_APPLY_PROCESSING = 1;
	
	/**
	 * 调单状态：不通过
	 */
	public static final Integer HANDLER_STATUS_ADJUST_APPLY_NOT_PASS = 2;
	
	/**
	 * 调单状态：通过
	 */
	public static final Integer HANDLER_STATUS_ADJUST_APPLY_PASS = 3;
	
	/**
	 * 调单状态：取消
	 */
	public static final Integer HANDLER_STATUS_ADJUST_APPLY_CANCEL = 4;
	
	/**
	 * 提现对象：商户提现
	 */
	public static final Integer CASH_WITHDRAWALS_RECORD_SELLER = 1;
	
	/**
	 * 提现对象：会员提现
	 */
	public static final Integer CASH_WITHDRAWALS_RECORD_MENBER = 2;
	
	/**
	 * 提现对象：向蜜客提现
	 */
	public static final Integer CASH_WITHDRAWALS_RECORD_XMN = 3;
	
	/**
	 * 提现状态：提现待处理
	 */
	public static final Integer CASH_STATUS_PROCESS = 0;
	
	/**
	 * 提现状态：提现成功
	 */
	public static final Integer CASH_STATUS_SUCCESS = 1;
	
	/**
	 * 提现状态：提现失败
	 */
	public static final Integer CASH_STATUS_FAIL = 2;
	
	/**
	 * 提现状态：提现银行处理中
	 */
	public static final Integer CASH_STATUS_BANK_PROCESSING= 3;
	
	/**
	 * 提现状态：平台退款中
	 */
	public static final Integer CASH_STATUS_PLATFORM_BACK = 4;
	
	/**
	 * 提现状态：取消提现
	 */
	public static final Integer CASH_STATUS_CANCEL = 5;
	
	/**
	 * 提现状态：提现请求失败
	 */
	public static final Integer CASH_STATUS_REQUEST_FAIL = 6;
	
	/**
	 * 提现渠道：用户版app
	 */
	public static final Integer CASH_APP_TYPE_USER = 1;
	
	/**
	 * 提现渠道：商户版app
	 */
	public static final Integer CASH_APP_TYPE_SELLER = 2;
	
	/**
	 * 提现渠道：合作商户app
	 */
	public static final Integer CASH_APP_TYPE_BUSSINESS = 3;
	
	/**
	 * 支付平台：汇付
	 */
	public static final Integer PAY_PLATFORM_HF = 0;
	
	/**
	 * 支付平台：U付
	 */
	public static final Integer PAY_PLATFORM_UF = 1;
	
	/**
	 * 支付平台：融宝
	 */
	public static final Integer PAY_PLATFORM_RB = 2;
	
	/**
	 * 支付平台：支付宝
	 */
	public static final Integer PAY_PLATFORM_ZFB = 3;
	
	/**
	 * 支付平台：盛付通
	 */
	public static final Integer PAY_PLATFORM_SFT = 4;
	

	
	/**
	 * 支付平台：块钱
	 */
	public static final Integer PAY_PLATFORM_KQ = 5;
	
	/**
	 * 支付平台：通联
	 */
	public static final Integer PAY_PLATFORM_TL = 6;
	
	/**
	 * 证件类型：身份证
	 */
	public static final Integer ID_TYPE_IDENTIFICATION= 1;
	
	/**
	 * 证件类型：护照
	 */
	public static final Integer ID_TYPE_PASSPORT = 2;
	
	/**
	 * 证件类型：驾驶证
	 */
	public static final Integer ID_TYPE_DRIVER = 3;
	
	/**
	 * 活动-赠送频率：首单
	 */
	public static final Integer ACTIVITY_RATE_FIRST = 1;
	
	/**
	 * 活动-赠送频率：首满
	 */
	public static final Integer ACTIVITY_RATE_FIRST_FULL = 2;
	
	/**
	 * 活动-赠送频率：每次
	 */
	public static final Integer ACTIVITY_RATE_EVERY = 3;
	
	/**
	 * 优惠卷发放对象：全部
	 */
	public static final Integer COUPON_SEND_OBJ_ALL = 0;
	
	/**
	 * 优惠卷发放对象：高频次
	 */
	public static final Integer COUPON_SEND_OBJ_HIGHT_FREQ = 1;
	
	/**
	 * 优惠卷发放对象：中频次
	 */
	public static final Integer COUPON_SEND_OBJ_MIDDLE_FREQ = 2;
	
	/**
	 * 优惠卷发放对象：低频次
	 */
	public static final Integer COUPON_SEND_OBJ_LOW_FREQ= 3;
	
	/**
	 * 优惠卷发放对象：高消费
	 */
	public static final Integer COUPON_SEND_OBJ_HIGHT_CONS =5;
	
	/**
	 * 优惠卷发放对象：中消费
	 */
	public static final Integer COUPON_SEND_OBJ_MIDDLE_CONS = 6;
	
	/**
	 * 优惠卷发放对象：低消费
	 */
	public static final Integer COUPON_SEND_OBJ_LOW_CONS = 7;
	
	/**
	 * 优惠卷发放对象：角色A
	 */
	public static final Integer COUPON_SEND_OBJ_ROLEA = 10;
	
	/**
	 * 优惠卷发放对象：角色B
	 */
	public static final Integer COUPON_SEND_OBJ_ROLEB = 11;
	
	/**
	 * 优惠卷发放对象：角色C
	 */
	public static final Integer COUPON_SEND_OBJ_ROLEC = 12;
	
	/**
	 * 优惠卷发放对象：角色D
	 */
	public static final Integer COUPON_SEND_OBJ_ROLED = 13;
	
	/**
	 * 押金(SAAS)套餐状态：已删除
	 */
	public static final Integer SAASPACKAGE_STATUS_DELETE = 0;
	
	/**
	 * 押金(SAAS)套餐状态：正常
	 */
	public static final Integer SAASPACKAGE_STATUS_NORMAL = 1;
	
	/**
	 * 押金(SAAS)套餐状态：已下架
	 */
	public static final Integer SAASPACKAGE_STATUS_UNSHELVE = 2;
	
	
	/** 一元夺宝机器人中奖类型 **/
	public static final int INDIANA_ROBOT_GIVE_TYPE = 1;

	/** 一元夺宝机器人中奖状态 **/
	public static final int INDIANA_ROBOT_STATUS_TYPE = 2;
	
	/** 一元夺宝用户中奖类型 **/
	public static final int INDIANA_USER_GIVE_TYPE = 0;
	
	/** 一元夺宝用户中奖状态 **/
	public static final int INDIANA_USER_STATUS_TYPE = 1;
	
	/** 活动订单一元夺宝类型 **/
	public static final int INDIANA_BILL_TYPE=1;
	
	/** 活动订单竞拍类型 **/
	public static final int INDIANA_AUCTION_BILL_TYPE = 2;	
	
	/** 活动订单待付款 状态 **/
	public static final Integer BILL_ACTIVITY_STATE_TYPE = 1;
	
	/** 活动订单待发货状态 **/
	public static final Integer BILL_ACTIVITY_STATE = 2;
	
	public static final int INDIANA_ROBOT_TYPE = 1;

	/** 夺宝活动正常结束状态 **/
	public static final int INDIANA_FINISH_STATUS = 3;
	
	
	/** 签约主播每场有效直播时间  **/
	public static final int LIVER_WORKTIME=5400;
	
	/** 兼职主播每场有效直播时间   **/
	public static final int PART_LIVER_WORKTIME=3600;
	
	
}
