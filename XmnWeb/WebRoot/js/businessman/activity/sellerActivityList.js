var pageDiv;
$(document)
		.ready(
				function() {

					pageDiv = $('#sellerActivityDiv').page({
						url : 'businessman/activity/init/list.jhtml',
						success : success,
						pageBtnNum : 10,
						pageSize : 10,
						paramForm : 'sellerActivityForm'
					});

					inserTitle(
							' > 商户活动管理> <a href="businessman/activity/init.jhtml" target="right">活动列表</a>',
							'sellerActivityDiv', true);

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

function queryBM(object,parms){
    var tags = document.getElementsByName("bumen") ;
    for(i=0;i<tags.length;i++){
    	$(tags[i]).attr("class", "btn btn-default");
    }
    $("#type").val(parms);
    pageDiv.reload();
	$(object).attr("class", "btn btn-success");
	
	
}

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
							title : "活动名称",// 标题
							name : "activityName",
							width : 200,// 宽度
							type : "string"// 数据类型

						}, {
							title : "活动类型",// 标题
							name : "type",
							// sort : "up",
							width : 180,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								if(value == 1){
									return "限时秒杀";
								}else if(value == 2){
									return "免费尝新";
								}else if(value == 3){
									return "转盘抽奖";
								}else if(value == 4){
									return "会员集点";
								}else{
									return "-";
								}
							}
						}, {
							title : "参与人数",// 标题
							name : "joinNum",
							// sort : "up",
							width : 100,// 宽度
							type : "string"// 数据类型
						}, {
							title : "绑定新会员",// 标题
							name : "newVip",
							// sort : "up",
							width : 100,// 宽度
							type : "string"// 数据类型
						},{
							title : "有效期",// 标题
							name : "startDate",
							// sort : "up",
							width : 200,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								return data.startDate + "至" + data.endDate;
							}
						}, {
							title : "状态",// 标题
							name : "status",
							// sort : "up",
							width : 100,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								if (value == 0) {
									return "进行中";
								} else if (value ==1) {
									return "已结束";
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
											if (data.status == 0) {
												var value1 = "<a href='javascript:shutDown("
														+ data.id+","+data.type
														+ ")'>"
														+ "终止活动" + "</a>";
												return value1;
											} else {
												return "";
											}
										}
									},
									{
										title : "参与明细",// 标题
										linkInfoName : "href",
										linkInfo : {
											href : "businessman/activity/init/list/detail/init.jhtml",// url
											param : ['type','id'], // 参数
											permission : "list"// 列权限
										}
									} ]
						}
					}, permissions);
}

/**
 * 终止进行中的活动
 */
function shutDown(id,type) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/activity/shutdown.jhtml' + '?t='
					+ Math.random(),
			data : {
				'id' : id,
				'type':type
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
	}, "是否终止活动？")
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
