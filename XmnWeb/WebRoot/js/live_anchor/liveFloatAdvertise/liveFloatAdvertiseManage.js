var pageDiv;
var imgRoot = $("#fastfdsHttp").val();

$(document).ready(
	function() {
		pageDiv = $('#liveFloatAdvertiseInfoDiv').page({
			url : 'liveFloatAdvertise/manage/init/list.jhtml',
			success : success,
			pageBtnNum : 10,
			paramForm : 'liveFloatAdvertiseSearchFrom'
		});

		// 标题
		inserTitle(' > 直播频道> <span><a href="liveFloatAdvertise/manage/init.jhtml" target="right">悬浮广告管理</a>',
				'sellerList', true);

		/**
		 * 重置
		 */
		$("input[data-bus=reset]").click(function() {
			if (location.href.indexOf("?") > 0) {
				var url = contextPath+ '/liveFloatAdvertise/manage/init.jhtml';
				location.href = url;
			}
			setTimeout(function() {
				$("#ld").find("select").trigger("chosen:updated");
			});
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

	});

function success(data, obj) {
	var formId = "liveFloatAdvertiseSearchFrom", title = "悬浮广告列表", subtitle = "个悬浮广告";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+ data.total+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	updateAddBtnHref("#addLiveFloatAdvertiseBto", callbackParam);
	obj.find('div').eq(0).scrollTablel(
		   {identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			checkable : true,
			// 操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : [ "update"],// 不需要选择checkbox处理的权限
				width : 150,// 宽度
				// 当前列的中元素
				cols : [ {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						/*
						 * linkInfoName : "modal", modal : { url :
						 * "liveFloatAdvertise/manage/update/init.jhtml",
						 * position : "", width : "auto", title :
						 * "修改悬浮广告" },
						 */
						href : "liveFloatAdvertise/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
				  } ]
			},
			cols : [{
					title : "广告",
					name : "title",
					type : "string",
					width : 150
				}, {
					title : "广告时间",
					name : "endTime",
					type : "string",
					width : 260,
					customMethod : function(value, data) {// 销售时间
						var startTime = data.startTime ? data.startTime: '-';
						var endTime = data.endTime ? data.endTime: '-';
						return startTime + '-' + endTime;
					}
				}, {
					title : "广告图",
					name : "picUrl",
					type : "string",
					width : 150,
					customMethod : function(value, data) {
						return '<img style="width:50px;height:50px;" src="'
								+ imgRoot + value + '"/>';
					}
				}, {
					title : "显示位置",
					name : "showPositionDesc",
					type : "string",
					width : 150
				}, {
					title : "状态",
					name : "status",
					type : "string",
					width : 150,
					customMethod : function(value, data) {
						var result;
						switch (value) {
						case 0:
							result = "未开始";
							break;
						case 1:
							result = "上线";
							break;
						case 2:
							result = "已结束";
							break;
						default:
							result = "已下线";
							break;
						}
						return result;
					}
			   }]
		}, permissions);
}

/**
 * 批量删除
 */
$('#deleteBto').click(function() {
	var ids = pageDiv.getIds();
	if (!ids) {
		showWarningWindow("warning", "请至少选择一条记录！");
		return;
	}
	alert(ids);
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'liveFloatAdvertise/manage/delete.jhtml' + '?t=' + Math.random(),
			data : 'ids=' + ids,
			dataType : 'json',
			success : function(data) {
				$('#prompt').hide();
				if (data.success) {
					pageDiv.reset();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
});

function liveFloatAdvertiseOption(status) {
	var ids = pageDiv.getIds();
	if (!pageDiv.getIds()) {
		showWarningWindow("warning", "请至少选择一条套餐记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : "POST",
			url : "liveFloatAdvertise/manage/beachOnLine/updateOptionStatus.jhtml",
			data : {
				ids : ids,
				state : status
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					setTimeout(function() {
						pageDiv.reload();
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(data) {
				showSmReslutWindow(data.success, data.msg);
			}
		});

	}, "确定修改吗？");
}


// 导出
$("#beachOnLine").click(function() {
	liveFloatAdvertiseOption(1);
});

// 2.下架
$("#beachDownLine").click(function() {
	liveFloatAdvertiseOption(3);
});
