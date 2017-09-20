package com.xmniao.xmn.core.billmanagerment.util;

public class BillConstants {
	//refund 常量
	public static final int  REFUND_STATUS_PENDING= 0; //0 退款待处理
	public static final int  REFUND_STATUS_AGREE= 1;  //1 同意退款
	public static final int  REFUND_STATUS_CANCEL= 2; //2 已返现(已分账)
	public static final int  REFUND_STATUS_APPEAL= 3; //3 申诉
	public static final int  REFUND_STATUS_PROCESSED= 4; //4 申诉已处理(表示平台同意申诉)   这个很少用了
	public static final int  REFUND_STATUS_REJECT= 5; //5 驳回退款申请(表示拒绝用户申请退款，商户申诉成功)
	public static final int  REFUND_STATUS_TRANSFER= 6; //6 传送给总裁办
	public static final int  REFUND_STATUS_FAILURE= 7; //7 申诉失败(表示平台不同意商户申诉)
	public static final int  REFUND_STATUS_INHAND= 8; //8 平台退款处理中(平台已处理退款订单，等待退款接口回调更新退款申请及订单状态)
	public static final int  REFUND_STATUS_SUCCEED= 9; //9 平台退款成功(订单金额已打入支付帐号，订单状态更新为 退款成功)
	public static final int  REFUND_STATUS_REFUND_FAILURE= 10;  //10 平台退款失败(运营可以操作再次申请退款)
	public static final int  REFUND_STATUS_REFUND_PINTAI= 11;  //11 平台同意退款
	public static final int  REFUND_STATUS_REFUND_BRUSH= 12;  //12 刷单退款
	//bill 常量
	public static final int  BILL_STATUS_NOPAY= 0; //0 待支付
	public static final int  BILL_STATUS_STAYCASHBACK= 1;  //1 待返现
	public static final int  BILL_STATUS_CANCELREFUND= 2; //2 取消退款
	public static final int  BILL_STATUS_CONSUME= 3; //3 已消费(验证)
	public static final int  BILL_STATUS_A_REFUND_OF= 4; // 4 退款中(未消费  未分账之前都可以申请退款)
	public static final int  BILL_STATUS_REFUND= 5; //5 已退款(退款成功)
	public static final int  BILL_STATUS_CASHBACK= 6; //6 待返现，取消退款
	public static final int  BILL_STATUS_REFUND_APPEAL= 7; //7 退款中，已申诉
	public static final int  BILL_STATUS_APPEAL_SUCCEED= 8; //8 待返现，驳回申请退款(申诉成功)
	public static final int  BILL_STATUS_REBATE= 9; //9 返利中(点击触发返利)
	public static final int  BILL_STATUS_APPEAL_FAILURE= 10 ;  //10 申诉失败
	public static final int  BILL_STATUS_REFUND_DO= 11 ;  //11 平台退款处理中
	public static final int  BILL_STATUS_REFUND_FAILURE= 12 ;  //12 平台退款失败(运营根据失败原因，修正失败信息后可再次退款)
	public static final int  BILL_STATUS_AGREE_REFUND= 13 ;  //13 商家同意退款
	public static final int  BILL_STATUS_AGREE_PINTAI= 14 ;  //14  平台同意退款
	
	public static final int  KEFU= 0 ;  //0  客服处理标志
	public static final int  ZERO= 0 ;  //0  
	public static final int[]  REFUND_OTHER_STATUS= {1,2,3,6,8,9};//非退款其它状态
	public static final int[]  REFUND_ALL_STATUS= {1,2,3,4,5,6,7,8,9,10,11,12,13,14};//除待支付所有状态   Bill订单
	public static final int[]  REFUND_CUSTOMER_AGREES_STATUS= {7};//客服同意
	public static final int[]  REFUND_CEOS_AGREES_STATUS= {7};//总裁办同意
	public static final int[]  REFUND_PINTAI_AGREES_STATUS= {11};//总裁办同意
	public static final int[]  REFUND_WAIT_STATUS= {1,7,10,11};//待退款
    
	public static final String SERVICE="service";
	public static final String CEOS="ceos";
	public static final String PINTAI="pintai";
	public static final String SHOPS="shops";
	public static final String FAILURE="failure";
	public static final String BRUSHORDER="brushOrder";
	public static final String IsPresidentHandleY="1";
	public static final String IsPresidentHandleN="0";
	
	
	public static final String return_code_30000="30000";//钱包退款成功
	public static final String return_code_30010="30010";//构建第三方支付请求数据成功
	public static final String return_code_30019="30019";//已提交退款请求，第三方平台还在退款处理中
	public static final String return_code_30002="30002";//已退款，重复退款
	public static final String return_code_30011="30011";//正在进行退款处理，属重复退款(所有类型适用)
	
	public static final String RECOVERY_CODE_100="100";//恢复订单返回码code
	
}
