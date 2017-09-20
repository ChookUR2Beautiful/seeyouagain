var refundBillPendingHistoryList;
$(document).ready(function() {

	refundBillPendingHistoryList = $('#refundBillPendingHistoryList').page({
		url : 'billmanagerment/refundBillPendingHistory/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
		paramForm : 'refundBillPendingHistoryListForm'
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
	$('.date').datetimepicker({
		minView :2,
		autoclose : 1,
		autoclose : 1,
		maxView :3,
		startView : 2,
		format : 'yyyy-mm-dd'
	});
	inserTitle(' > 订单管理 > <a href="billmanagerment/refundBillPendingHistory/init.jhtml" target="right">退款已处理订单</a>','refundBillPendingHistory',true);
    
	$("#export").click(function(){ 
		$form = $("#refundBillPendingHistoryListForm").attr("action","billmanagerment/refundBillPendingHistory/export.jhtml");
		$form[0].submit();
	});
});

function queryStatus(object,parms){
    var tags = document.getElementsByName("status") ;
    for(i=0;i<tags.length;i++){
    	$(tags[i]).attr("class", "btn btn-default");
    }
    var data = {status:parms};
	$.ajax({
        type: "POST",
        url : 'billmanagerment/refundBillPendingHistory/init/list.jhtml',
        data: data,
        dataType: "json",
        success: function(result){
        	$("#status").val(parms);
        	refundBillPendingHistoryList.reload();
         }
    });
	$(object).attr("class", "btn btn-success");
}
/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {

	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;退款已处理订单&nbsp;&nbsp;共计【'+data.total+'】条订单&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#refundBillPendingHistoryListForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	//checkable :false,
	    	//identifier : "bid",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
				title : "操作",// 标题
				name : "bid",
				width : 130,// 宽度
				leng : 3,//显示长度
				type:"string",//数据类型
				isLink : true,// 表示当前列是否是超链接 true:是 false：不是
				// 只有当isLink为true时 才有效
				link : {
					required : true,// 是否必须显示，如果为true则列没有权限则也会显示name所表示的数据
					linkInfoName : "modal", // href , modal ,method
					linkInfo : {
						modal : {
							url : "billmanagerment/allbill/view/init.jhtml",// url
							position:"100px",// 模态框显示位置
							width:"800px",
							title : "查看"
						}
					},
					param : ["bid"]// 参数
				},
				customMethod : function(value, data) {
					var $a = $(value);
					$a.html("查看");
					return $a[0].outerHTML;
				}
				
		},{
				title : "订单号",// 标题
				name : "bid",
				//sort : "up",
				width : 120,// 宽度
				leng : 8,//显示长度
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
				type:"number"//数据类型
				
			},{
				title : "平台补贴占比",// 标题
				name : "flatAgio",
				//sort : "up",
				width : 100,// 宽度
				leng : 8,//显示长度
				type:"number",//数据类型
				customMethod : function(value, data) {
					return data.flatAgio+"%";
				}
			},{
				title : "平台补贴金额",// 标题
				name : "flatMoney",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型
			},{
				title : "消费验证号",// 标题
				name : "codeid",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型
			},{
				title : "支付方式",// 标题
				name : "payTypeText",
				width : 150,// 宽度
				leng : 8,//显示长度
				type:"stirng"//数据类型
				
			},{
				title : "用户昵称",// 标题
				name : "nname",
				width : 150,// 宽度
				leng : 8,//显示长度
				type:"stirng"//数据类型
				
			},{
				title : "用户手机",// 标题
				name : "phoneid",
				width : 150,// 宽度
				leng : 8,//显示长度
				type:"stirng"//数据类型
				
			},{
				title : "消费商家",// 标题
				name : "sellername",
				width : 200,// 宽度
				type:"string"//数据类型
			},{
				title : "下单时间",// 标题
				name : "orderDate",
				width : 160,// 宽度
				type:"date"//数据类型
			},{
				title : "支付时间",// 标题
				name : "zdate",
				width : 160,// 宽度
				type:"date"//数据类型
			},{
				title : "申请退款时间",// 标题
				name : "sdate",
				width : 160,// 宽度
				type:"date"//数据类型
			},{
				title : "退款处理时间",// 标题
				name : "quitDate",
				width : 160,// 宽度
				type:"date"//数据类型
			},{
				title : "商家申诉时间",// 标题
				name : "adate",
				width : 160,// 宽度
				type:"date"//数据类型
			},{
				title : "申诉处理时间",// 标题
				name : "qdate",
				width : 160,// 宽度
				type:"date"//数据类型
			},{
				title : "商家申诉原因",// 标题
				name : "qppeal",
				width : 150,// 宽度
				leng : 8,//显示长度
				type:"string"//数据类型
				
			},{
				title : "商家申诉处理结果",// 标题
				name : "processing",
				width : 150,// 宽度
				leng : 8,//显示长度
				type:"string"//数据类型
				
			},{
				title : "退款流程状态",// 标题
				name : "statusText",
				width : 150,// 宽度
				leng : 8,//显示长度
				type:"string"//数据类型
				
			},{
				title : "派奖状态",// 标题
				name : "isActivity",
				type:"string",//数据类型
				width : 100,// 宽度
				customMethod : function(value, data) {
					var type = data.isactivity;
	                  //alert(type);
					var result;
					if(0 == type){
						result = "未派";
					}else if(1 == type){
						result = "已派";
					}else if(2 == type){
						result = "不用派";
					}else{
						result = "-";
					}
					return result;
				}
			},{
				title : "活动描述",// 标题
				name : "activityConent",
				width : 200,// 宽度
				type:"string"//数据类型
			},{
				title : "派奖结果描述",// 标题
				name : "activityRest",
				width : 200,// 宽度
				type:"string"//数据类型
			},{
				title : "备注",// 标题
				name : "remarks",
				width : 200,// 宽度
				type:"string"//数据类型
			}]
     },permissions);

	
	
	
}



