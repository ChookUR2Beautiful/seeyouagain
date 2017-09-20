var pageDiv;
$(document)
		.ready(
				function() {

					pageDiv = $('#billFromDiv').page({
						url : 'businessman/fullReduction/init/detail.jhtml',
						success : success,
						pageBtnNum : 10,
						pageSize : 10,
						paramForm : 'billFrom'
					});

					inserTitle(
							' > 商户满减活动 > <a href="businessman/fullReduction/init.jhtml" target="right"> 所有活动</a> > 活动详情',
							'billFromDiv', true);

					$("input[data-bus=reset]").click(function() {
						$(".form-control").attr("value", "");
						$("#ld").find("select").trigger("chosen:updated");
					});

					$('.form-datetime').datetimepicker({
						weekStart : 1,
						todayBtn : 1,
						autoclose : 1,
						todayHighlight : 1,
						startView : 2,
						minView: 2,
						forceParse : 0,
						showMeridian : 1,
						format : 'yyyy-mm-dd'
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
		cols : [ {
			title : "订单编号",// 标题
			name : "bid",
			// sort : "up",
			width : 200,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
		}, {
			title : "参与会员",// 标题
			name : "nname",
			// sort : "up",
			width : 200,// 宽度
			leng : 200,// 显示长度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				return value + " (" + data.phoneid + ")";
			}
		}, {
			title : "消费金额",// 标题
			name : "money",
			width : 250,// 宽度
			type : "string"// 数据类型

		}, {
			title : "满就减",// 标题
			name : "fullReduction",
			width : 250,// 宽度
			type : "string"// 数据类型

		}, {
			title : "其他减免",// 标题
			name : "reduction",
			width : 250,// 宽度
			type : "string"// 数据类型

		}, {
			title : "实际支付",// 标题
			name : "payment",
			width : 250,// 宽度
			type : "string"// 数据类型

		}, {
			title : "交易时间",// 标题
			name : "zdate",
			// sort : "up",
			width : 160,// 宽度
			type : "string"// 数据类型
		}, {
			title : "状态",// 标题
			name : "hstatus",
			// sort : "up",
			width : 100,// 宽度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				if (value == 0) {
					return "未分账";
				} else if (value == 1) {
					return "已分账";
				} else {
					return "-";
				}
			}
		}, {
			title : "操作",// 标题
			name : "status",
			// sort : "up",
			width : 120,// 宽度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				return "<a href='javascript:;'>查看</a>";
			}
		} ]
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
