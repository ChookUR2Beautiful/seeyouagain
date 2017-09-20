var pageDiv;
$(document)
		.ready(
				function() {

					pageDiv = $('#userFormDiv').page({
						url : 'businessman/rechargeDetail/init/list.jhtml',
						success : success,
						pageBtnNum : 10,
						pageSize : 10,
						paramForm : 'userForm'
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
					
					inserTitle(
							' > 商家管理 > <a href="businessman/rechargeDetail/init.jhtml" target="right"> 商户充值记录</a>',
							'userFormDiv', true);

					$("input[data-bus=reset]").click(function() {
						$(".form-control").attr("value", "");
						$("#ld").find("select").trigger("chosen:updated");
					});
					
					$("#export").click(function(){
						$form = $("#userForm").attr("action","businessman/rechargeDetail/export.jhtml");
						$form[0].submit();
					});
					
				});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {

	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'
			+ data.total + '】条订单&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#searchBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
		tableClass : "table-bordered table-striped info",
		callbackParam : callbackParam,
		caption : captionInfo,
		// 数据
		data : data.content,
		// 数据行
		cols : [{
			title : "充值订单",// 标题
			name : "orderNo",
			// sort : "up",
			width : 200,// 宽度
			type : "string"// 数据类型
			
		},{
			title : "会员名称",// 标题
			name : "userName",
			// sort : "up",
			width : 120,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
			
		},{
			title : "会员手机",// 标题
			name : "phone",
			width : 120,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
			
		}, {
			title : "充值金额",// 标题
			name : "payment",
			// sort : "up",
			width : 100,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
//		}, {
//			title : "会员级别",// 标题
//			name : "userLevel",
//			width : 120,// 宽度
//			leng : 200,// 显示长度
//			type : "string"// 数据类型
		}, {
			title : "支付方式",// 标题
			name : "payTypeText",
			width : 120,// 宽度
			type : "string"// 数据类型
		}, {
			title : "充值来源",// 标题
			name : "sellername",
			width : 120,// 宽度
			type : "string"// 数据类型
		}, {
			title : "获得鸟豆",// 标题
			name : "realCoin",
			width : 120,// 宽度
			type : "string"// 数据类型				
		}, {
//			title : "原额度",// 标题
//			name : "qquota",
//			width : 100,// 宽度
//			type : "string"// 数据类型
//		}, {
//			title : "充值后额度",// 标题
//			name : "hquota",
//			width : 100,// 宽度
//			type : "string"// 数据类型
//		}, {
//			title : "获得分佣",// 标题
//			name : "upAmount",
//			width : 100,// 宽度
//			type : "string"// 数据类型
//		}, {
//			title : "上上级",// 标题
//			name : "topLevel",
//			width : 120,// 宽度
//			type : "string"// 数据类型
//		}, {
//			title : "获得分佣",// 标题
//			name : "topAmount",
//			width : 100,// 宽度
//			type : "string"// 数据类型
//		}, {
			title : "上级",// 标题
			name : "upLevel",
			width : 120,// 宽度
			type : "string"// 数据类型
		}, {
			title : "充值时间",// 标题
			name : "payTime",
			width : 180,// 宽度
			type : "string"// 数据类型
		}]
	}, permissions);
}

/**
 * 转换from表单
 */
function jsonFromt(data) {
	var json = {};
	for (var i = 0; i < data.length; i++) {
		json[data[i].name] = data[i].value;
	}
	return json;
}
