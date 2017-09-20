var pageDiv;
$(document)
		.ready(
				function() {

					pageDiv = $('#userFormDiv').page({
						url : 'businessman/coupon/list/detail.jhtml',
						success : success,
						pageBtnNum : 10,
						pageSize : 10,
						paramForm : 'userForm'
					});

					inserTitle(
							' > 商户赠品券 > <a href="businessman/coupon/init2.jhtml" target="right"> 所有赠品</a> > 领取详情',
							'userFormDiv', true);

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
			title : "领取会员",// 标题
			name : "uid",
			// sort : "up",
			width : 200,// 宽度
			leng : 200,// 显示长度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				return value + " (" + data.phone + ")";
			}
		}, {
			title : "优惠券编码",// 标题
			name : "serialNo",
			// sort : "up",
			width : 200,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
			
		}, {
			title : "领取时间",// 标题
			name : "getTime",
			width : 250,// 宽度
			type : "string"// 数据类型

		}, {
			title : "活得途径",// 标题
			name : "getWay",
			width : 250,// 宽度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				if(value == 1){
					return "自动发放";
				}else if(value == 2){
					return "主页领取";
				}else if(value == 3){
					return "抽奖活动";
				}else if(value == 4){
					return "消费满获得";
				}else if(value == 5){
					return "H5页面获得";
				}else if(value == 6){
					return "绑定商户获得";
				}
			}

		}, {
			title : "优惠券状态",// 标题
			name : "useStatus",
			width : 250,// 宽度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				if(value == 0){
					return "未使用";
				}else if(value == 1){
					return "锁定";
				}else if(value == 2){
					return "已使用";
				}
			}
		}, {
			title : "关联订单号",// 标题
			name : "bid",
			width : 250,// 宽度
			type : "string"// 数据类型

		}, {
			title : "操作",// 标题
			name : "useStatus",
			// sort : "up",
			width : 120,// 宽度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				if(value == 0){
					return "<a href='javascript:shutDown(\""
					+ data.cuid
					+ "\")'>"
					+ "验帐核销" + "</a>";
				}else{
					return "-";
				}
			}
		} ]
	}, permissions);
}

function shutDown(cuid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/coupon/shutdown/userCoupon.jhtml' + '?t='
					+ Math.random(),
			data : {
				'cuid' : cuid
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
	}, "是否验帐核销当前优惠劵？")
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
