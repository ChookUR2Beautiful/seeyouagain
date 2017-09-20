var pageDiv;
$(document)
		.ready(
				function() {

					pageDiv = $('#freetryRecordDiv').page({
						url : 'businessman/activity/init/list/focusPoint/list.jhtml',
						success : success,
						pageBtnNum : 10,
						pageSize : 10,
						paramForm : 'freetryRecordForm'
					});

					inserTitle(
							' > 商户活动管理> <a href="businessman/activity/init.jhtml" target="right">所有活动</a> > 会员集点详情',
							'pageDiv', true);

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
						minView : 2,
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
			+ data.total + '】条记录&nbsp;</font></caption>';
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
			title : "会员id",// 标题
			name : "uid",
			// sort : "up",
			width : 200,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
		}, {
			title : "会员手机号",// 标题
			name : "phone",
			width : 250,// 宽度
			type : "string"// 数据类型

		}, {
			title : "获得点数",// 标题
			name : "givePoints",
			// sort : "up",
			width : 160,// 宽度
			type : "string"// 数据类型
		}, {
			title : "当前点数",// 标题
			name : "points",
			// sort : "up",
			width : 120,// 宽度
			type : "string"// 数据类型
		}, {
			title : "参与时间",// 标题
			name : "getTime",
			// sort : "up",
			width : 250,// 宽度
			type : "string"// 数据类型
		}, {
			title : "兑换次数",// 标题
			name : "converPoints",
			// sort : "up",
			width : 250,// 宽度
			type : "string"// 数据类型
		}, {
			title : "关联订单编号",// 标题
			name : "bid",
			// sort : "up",
			width : 250,// 宽度
			type : "string"// 数据类型
		} ]
	}, permissions);
}

/**
 * 终止进行中的红包活动
 */
function shutDown(id) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/fullReduction/shutdown.jhtml' + '?t='
					+ Math.random(),
			data : {
				'id' : id
			},
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					setTimeout(function() {
						pageDiv.reload();
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}, "确定终止吗？")
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
