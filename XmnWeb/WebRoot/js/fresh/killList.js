var activityList;
$(document).ready(function() {
	activityList = $('#activityList').page({
		url : 'fresh/kill/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize : 15,
		paramForm : 'searchActivityForm'
	});

	inserTitle(' > 秒杀活动管理 > <a href="fresh/kill/init.jhtml" target="right">所有活动</a>', 'activityList', true);

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
			width : 150, // 宽度
			type : "string" //数据类型

		}, {
			title : "开始时间", // 标题
			name : "startTime",
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
			width : 120, // 宽度
			leng : 8, //显示长度
			type : "string", //数据类型
			customMethod : function(value, data) {
				if(data.terminTime){
					return formatDate(new Date(data.terminTime), "yyyy-MM-dd HH:mm:ss");
				}
				return formatDate(new Date(value), "yyyy-MM-dd HH:mm:ss");
			}
		},{
			title : "购买限制", // 标题
			name : "orderLimit",
			//sort : "up",
			width : 120, // 宽度
			leng : 8, //显示长度
			type : "string", //数据类型
			customMethod : function(value, data) {
				return value=='-'?'不限':'每人可购买'+value+'件'; 
			}
		}, {
			title : "订单总数", // 标题
			name : "billNum",
			width : 150, // 宽度
			type : "number" //数据类型
		}, {
			title : "活动状态", // 标题
			name : "status",
			width : 100, // 宽度
			type : "string", //数据类型
			customMethod : function(value, data) {
				if (data.status == 1) {
					return "已结束"
				}
				else if (new Date().getTime() - new Date(data.endTime).getTime() > 0) {
					return "已结束";
				}
				else if (new Date(data.startTime).getTime() - new Date().getTime() > 0) {
					return "未开始";
				}
				else {
					return "进行中";
				}
			}
		} ],
		//操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "edit", "preview", "copyurl", "end", "delete","add" ], // 不需要选择checkbox处理的权限
			width : 130, // 宽度
			// 当前列的中元素
			cols : [ {
				title : "编辑", // 标题
				linkInfoName : "href",
				linkInfo : {
					href : "fresh/kill/edit/init.jhtml", // url
					param : [ "id" ], // 参数
					permission : "edit" // 列权限

				},
				customMethod : function(value, data) {
					if (data.proceedStatus == 1) {
						return "<a href='fresh/kill/edit/init.jhtml?id=" + data.id + "'>编辑</a>";
					}
					else {
						return '';
					}
				}
			}, {
					title : "立即结束", // 标题
					linkInfoName : "href",
					linkInfo : {
						modal : {
							url : "fresh/kill/end.jhtml", // url
							position : "60px", // 模态框显示位置
							width : "800px"
						},
						param : [ "id" ], // 参数
						permission : "end" // 列权限
					},
					customMethod : function(value, data) {
						if (data.proceedStatus == 2) {
							return "<a href='javascript:;' onclick='end_fun(" + data.id + ")'>立即结束</a>"
						}
						else {
							return '';
						}
					}
				}, {
					title : "删除", // 标题
					linkInfoName : "href",
					linkInfo : {
						modal : {
							url : "fresh/kill/delete.jhtml", // url
							position : "60px", // 模态框显示位置
							width : "800px"
						},
						param : [ "id" ], // 参数
						permission : "delete" // 列权限
					},
					customMethod : function(value, data) {
						if (data.proceedStatus != 2) {
							return "<a href='javascript:;' onclick='delete_fun(" + data.id + ")'>删除</a>"
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
		$.post("fresh/kill/delete.jhtml", {
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
		$.post("fresh/kill/end.jhtml", {
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

/**
 * 复制到黏贴板
 */

function copy_fun(id)
{
var _Text=activityUrl+"?id="+id;
var Url2=document.getElementById("biao1");
Url2.value=_Text;
Url2.select(); // 选择对象
document.execCommand("Copy"); // 执行浏览器复制命令
alert("已复制好，可贴粘。");
}
