var activityList;
$(document).ready(function() {
	activityList = $('#activityList').page({
		url : 'fresh/indiana/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize : 15,
		paramForm : 'searchActivityForm'
	});

	inserTitle(' > 夺宝活动管理 > <a href="fresh/indiana/init.jhtml" target="right">所有活动</a>', 'activityList', true);

});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var callbackParam = "isBackButton=true&callbackParam=" + getFormParam($("#searchActivityForm").serialize());
	obj.find('div').eq(0).scrollTablel({
		tableClass : "table-bordered table-striped info",
		callbackParam : callbackParam,
		//数据
		data : data.content,
		//数据行
		cols : [ {
			title : "活动编号", // 标题
			name : "id",
			//sort : "up",
			width : 100, // 宽度
			leng : 3, //显示长度
			type : "number", //数据类型					
		}, {
			title : "活动标题", // 标题
			name : "title",
			width : 200, // 宽度
			type : "string" //数据类型

		}, {
			title : "期数", // 标题
			name : "boutTh",
			width : 80, // 宽度
			type : "string", //数据类型
			customMethod : function(value, data) {
				return "第"+value+"期";
			}

		}, {
			title : "开始时间", // 标题
			name : "createTime",
			//sort : "up",
			width : 160, // 宽度
			type : "string", //数据类型
			customMethod : function(value, data) {
				return formatDate(new Date(value), "yyyy-MM-dd HH:mm:ss");
			}
		}, {
			title : "结束时间", // 标题
			name : "endTime",
			//sort : "up",
			width : 160, // 宽度
			leng : 8, //显示长度
			type : "string", //数据类型
			customMethod : function(value, data) {
				if(data.endTime){
					return formatDate(new Date(data.endTime), "yyyy-MM-dd HH:mm:ss");
				}
				return formatDate(new Date(data.activityEndTime), "yyyy-MM-dd HH:mm:ss");
			}
		},
		{
			title : "关联商品", // 标题
			name : "pname",
			width : 200, // 宽度
			type : "string" //数据类型
		}
		,{
			title : "商品拆分份数", // 标题
			name : "point",
			width : 150, // 宽度
			type : "number" //数据类型
		},{
			title : "已出售份数", // 标题
			name : "saleNum",
			width : 150, // 宽度
			type : "number" //数据类型
		},
		 {
			title : "获奖者", // 标题
			name : "name",
			//sort : "up",
			width : 120, // 宽度
			leng : 8, //显示长度
			type : "string", //数据类型
			customMethod : function(value, data) {
				if(!data.name){
					return value
				}
				return value+"("+(data.giveType==1?'机器人':'用户')+")";
			}
		}
		, {
			title : "活动状态", // 标题
			name : "proceedStatusWeb",
			width : 100, // 宽度
			type : "string", //数据类型
			customMethod : function(value, data) {
				if (value == 1) {
					return "未开始"
				}
				else if (value == 2) {
					return "进行中";
				}
				else {
					return "已结束";
				}
			}
		} ],
		//操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "edit", "preview", "copyurl", "end", "delete" ], // 不需要选择checkbox处理的权限
			width : 200, // 宽度
			// 当前列的中元素
			cols : [ {
				title : "编辑", // 标题
				linkInfoName : "href",
				linkInfo : {
					href : "fresh/indiana/add/init.jhtml", // url
					param : [ "id" ], // 参数
					permission : "add" // 列权限

				},
				customMethod : function(value, data) {
					if (data.proceedStatusWeb == 1) {
						return "<a href='fresh/indiana/add/init.jhtml?id=" + data.id + "'>编辑</a>";
					}
					else {
						return '';
					}
				}
			}, {
					title : "立即结束", // 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/indiana/end.jhtml", // url
						param : [ "id" ], // 参数
						permission : "end" // 列权限
					},
					customMethod : function(value, data) {
						if (data.proceedStatusWeb == 2) {
							return "<a href='javascript:;' onclick='end_fun(" + data.id + ")'>立即结束</a>"
						}
						else {
							return '';
						}
					}
				},{
					title : "预设获奖人", // 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/indiana/end.jhtml", // url
						param : [ "id" ], // 参数
						permission : "setWinner" // 列权限
					},
					customMethod : function(value, data) {
						if (data.proceedStatusWeb == 2||data.proceedStatusWeb == 1) {
							return '<a  data-type="ajax" data-width="1200px" data-height="800px"  data-url="fresh/indiana/winner/init.jhtml?id='+data.id+'" data-toggle="modal">预设获奖人</a>'
							
						}
						else {
							return '';
						}
					}
				}, {
					title : "删除", // 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/indiana/end.jhtml", // url
						param : [ "id" ], // 参数
						permission : "delete" // 列权限
					},
					customMethod : function(value, data) {
						if (data.proceedStatusWeb == 1) {
							return "<a href='javascript:;' onclick='delete_fun(" + data.id + ")'>删除</a>"
						}
						else {
							return '';
						}
					}
				},{
					title : "关联订单", // 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/indiana/end.jhtml", // url
						param : [ "id" ], // 参数
						permission : "getWinner" // 列权限
					},
					customMethod : function(value, data) {
						if (data.proceedStatusWeb == 3) {
							return "<a href='javascript:;' onclick='withOrder(" + data.id + ")'>关联订单</a>"
						}
						else {
							return '';
						}
					}
				},{
					title : "购买列表", // 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/indiana/end.jhtml", // url
						param : [ "id" ], // 参数
						permission : "getWinner" // 列权限
					},
					customMethod : function(value, data) {
						if (data.proceedStatusWeb == 3) {
							return '<a  data-type="ajax" data-width="1200px" data-height="800px"  data-url="fresh/indiana/winner/init.jhtml?id='+data.id+'&type=1" data-toggle="modal">购买列表</a>'
						}
						else {
							return '';
						}
					}
				} ]
		}
	}, permissions);
}
function substr(obj, length) {
	if (obj.length > length) {
		obj = obj.substring(0, length) + "...";
	}
	return obj;
}

function delete_fun(id) {
	showSmConfirmWindow(function() {
		$.post("fresh/indiana/delete.jhtml", {
			"id" : id
		}, function(data, status) {
			if (status == "success") {
				showSmReslutWindow(data.success, data.msg);
				activityList.reload();
			}
			else {
				window.messager.warning(data.msg);
			}
		})
	}, "确定要删除吗？");
}
function end_fun(id) {
	showSmConfirmWindow(function() {
		$.post("fresh/indiana/end.jhtml", {
			"id" : id
		}, function(data, status) {
			if (status == "success") {
				showSmReslutWindow(data.success, data.msg);
				activityList.reload();
			}
			else {
				window.messager.warning(data.msg);
			}
		})
	}, "确定要结束吗？");
}

/**
 * 日期插件
 */
$(function() {
	var today = new Date();
	var todaystr = addDate(today, 0);
	function addDate(date, days) {
		var d = new Date(date);
		d.setDate(d.getDate() + days);
		var month = d.getMonth() + 1;
		var day = d.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var val = d.getFullYear() + "-" + month + "-" + day;
		return val;
	}
});

function withOrder(id){
	$.post("fresh/activityOrder/getWinnerOrder.jhtml", {
		"id" : id
	}, function(data, status) {
		if (status == "success") {
			myPost("fresh/activityOrder/init.jhtml",{"orderNo":data.data.id});
		}
		else {
			window.messager.warning(data.msg);
		}
	})
}

function queryBM(object, parms) {
	var tags = document.getElementsByName("bumen");
	for (i = 0; i < tags.length; i++) {
		$(tags[i]).attr("class", "btn btn-default");
	}
	$("#status").val(parms);
	activityList.reload();
	$(object).attr("class", "btn btn-success");

}

$("input[type='reset']").on("click", function() {
	$("#status").val("");
	$("[name='bumen']").attr("class", "btn btn-default");
});


