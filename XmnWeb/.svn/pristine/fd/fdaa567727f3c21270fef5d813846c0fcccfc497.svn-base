var pageDiv;
$(document)
		.ready(
				function() {

					pageDiv = $('#sellerCouponDiv').page({
						url : 'businessman/coupon/list.jhtml',
						success : success,
						pageBtnNum : 10,
						pageSize : 10,
						paramForm : 'sellerCouponForm'
					});

					inserTitle(
							' > 商户抵用券> <a href="businessman/coupon/init1.jhtml" target="right">所有抵用券</a>',
							'sellerCouponDiv', true);

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
			+ data.total + '】条记录&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#searchBillForm").serialize());
	obj
			.find('div')
			.eq(0)
			.scrollTablel(
					{
						tableClass : "table-bordered table-striped info",
						callbackParam : callbackParam,
						caption : captionInfo,
						// 数据
						data : data.content,
						// 数据行
						cols : [ {
							title : "活动商户",// 标题
							name : "sellerName",
							// sort : "up",
							width : 200,// 宽度
							leng : 200,// 显示长度
							type : "string",// 数据类型
						}, {
							title : "优惠券名称",// 标题
							name : "cname",
							width : 200,// 宽度
							type : "string"// 数据类型

						}, {
							title : "抵用金额",// 标题
							name : "denomination",
							// sort : "up",
							width : 120,// 宽度
							type : "string"// 数据类型
						}, {
							title : "使用条件",// 标题
							name : "conditions",
							// sort : "up",
							width : 200,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								if(value != 0){
									return "消费满"+value;
								}else{
									return "无条件使用";
								}
							}
						}, {
							title : "领取条件",// 标题
							name : "awardCondition",
							// sort : "up",
							width : 200,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								if(value == 0){
									return "无限制";
								}else if(value == 1){
									return "消费满获得";
								}else if(value == 2){
									return "新用户绑定获得";
								}else{
									return "-";
								}
							}
						}, {
							title : "发放总数(张)",// 标题
							name : "sendNum",
							// sort : "up",
							width : 120,// 宽度
							type : "string"// 数据类型
						}, {
							title : "已领取数(张)",// 标题
							name : "awardNumber",
							// sort : "up",
							width : 120,// 宽度
							type : "string"// 数据类型
						}, {
							title : "已使用(张)",// 标题
							name : "useNumber",
							// sort : "up",
							width : 120,// 宽度
							type : "string"// 数据类型
						}, {
							title : "剩余数量(张)",// 标题
							name : "awardNumber",
							// sort : "up",
							width : 120,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								return data.sendNum-data.awardNumber;
							}
						}, {
							title : "活动时间",// 标题
							name : "startDate",
							// sort : "up",
							width : 250,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								return data.startDate + "至" + data.endDate;
							}
						}, {
							title : "状态",// 标题
							name : "sendStatus",
							// sort : "up",
							width : 100,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								if (value == 1) {
									return "进行中";
								} else if (value == 2) {
									return "已结束";
								} else if(value == 3){
									return "活动占用";
								}
							}
						} ],
						// 操作列
						handleCols : {
							title : "操作",// 标题
							queryPermission : [ "shutdown", "list" ],// 不需要选择checkbox处理的权限
							width : 200,// 宽度
							// 当前列的中元素
							cols : [
									{
										title : "终止",// 标题
										linkInfoName : "href",
										linkInfo : {
											param : [ "id" ],// 参数
											permission : "shutdown"// 列权限
										},
										customMethod : function(value, data) {
											if (data.sendStatus == 1) {
												var value1 = "<a href='javascript:shutDown(\""
														+ data.cid
														+ "\")'>"
														+ "终止活动" + "</a>";
												return value1;
											} else {
												return "";
											}
										}
									},
									{
										title : "领取明细",// 标题
										linkInfoName : "href",
										linkInfo : {
											href : "businessman/coupon/list/detail/init.jhtml",// url
											param : [ "cid" ], // 参数
											permission : "list"// 列权限
										}
									} ]
						}
					}, permissions);
}

/**
 * 终止进行中的红包活动
 */
function shutDown(cid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/coupon/shutdown.jhtml' + '?t='
					+ Math.random(),
			data : {
				'cid' : cid
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
