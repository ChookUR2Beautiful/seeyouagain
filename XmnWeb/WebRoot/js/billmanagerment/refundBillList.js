var refundBillList;
$(document).ready(function() {
	refundBillList = $('#refundBillList').page({
		url : 'billmanagerment/refund/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
		paramForm : 'searchForm'
	});

	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	inserTitle(' > 订单管理 > <a href="billmanagerment/refund/init.jhtml" target="right">商家申诉待处理订单</a>','refundbillSpan',true);

});


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条订单&nbsp;</font></caption>';
	obj.find('div').eq(0).scrollTablel({
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
				title : "订单号",// 标题
				name : "bid",
				width : 120,// 宽度
				type:"stirng"//数据类型
				
		},{
			title : "支付流水号",// 标题
			name : "payid",
			//sort : "up",
			width : 150,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "订单金额",// 标题
			name : "money",
			//sort : "up",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
			
		},{
			title : "需支付金额",// 标题
			name : "payment",
			//sort : "up",
			width : 100,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "返利支付金额",// 标题
			name : "profit",
			width : 110,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "赠送支付金额",// 标题
			name : "giveMoney",
			//sort : "up",
			width : 110,// 宽度
			type:"stirng"//数据类型
			
		}
		,{
			title : "平台补贴占比",// 标题
			name : "flatAgio",
			//sort : "up",
			width : 110,// 宽度
			type:"stirng"//数据类型
			
		}
		,{
			title : "平台补贴金额",// 标题
			name : "flatMoney",
			//sort : "up",
			width : 110,// 宽度
			type:"stirng"//数据类型
			
		}
		,{
			title : "派奖状态",// 标题
			name : "activityText",
			//sort : "up",
			width : 100,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "活动描述",// 标题
			name : "activityConent",
			width : 160,// 宽度
			type:"stirng"//数据类型
			
		},
		
		{
			title : "消费验证号",// 标题
			name : "codeid",
			width : 100,// 宽度
			type:"string"//数据类型
			
		}
		
		,
		{
			title : "支付方式",// 标题
			name : "payTypeText",
			width : 150,// 宽度
			type:"string"//数据类型
		}
		,
		{
			title : "用户昵称",// 标题
			name : "nname",
			width : 200,// 宽度
			type:"number"//数据类型
		},
		{
			title : "消费商家",// 标题
			name : "sellername",
			width : 200,// 宽度
			type:"string"//数据类型
		}
		,
		{
			title : "下单时间",// 标题
			name : "orderDate",
			width : 160,// 宽度
			type:"date"//数据类型
		}
		,
		{
			title : "支付时间",// 标题
			name : "zdate",
			width : 160,// 宽度
			type:"date"//数据类型
		}
		,
		{
			title : "申请退款时间",// 标题
			name : "sdate",
			width : 160,// 宽度
			type:"date"//数据类型
		},
		{
			title : "商家申诉时间",// 标题
			name : "adate",
			width : 160,// 宽度
			type:"date"//数据类型
		},
		{
			title : "申诉处理时间",// 标题
			name : "qdate",
			width : 160,// 宽度
			type:"date"//数据类型
		},
		{
			title : "商家申诉原因",// 标题
			name : "qppeal",
			width : 250,// 宽度
			type:"string"//数据类型
		},
		{
			title : "退款流程状态",// 标题
			name : "statusText",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "备注",// 标题
			name : "remarks",
			width : 150,// 宽度
			type:"string"//数据类型
		},{
			title : "派奖结果描述",// 标题
			name : "activityRest",
			width : 200,// 宽度
			type:"string"//数据类型
		}],
			//操作列
		handleCols : {
				title : "操作",// 标题
				queryPermission : ["treatment"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
					title : "处理",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "billmanagerment/refund/update/init.jhtml",// url
							position:"200px",// 模态框显示位置
							width:"30%"
						},
						param : ["id"],// 参数
						permission : "treatment"// 列权限
					}
				}] 
	}},permissions);
}

//function substr(obj,length){
//	if(obj.length > length){
//		obj = obj.substring(0,length) +"...";
//	}
//	return obj;
//}

