var ticketsList;
$(document).ready(function() {
	ticketsList = $('#ticketsList').page({
		url : 'fashionTickets/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize : 15,
		paramForm : 'searchActivityForm'
	});

	inserTitle(' > 新食尚大赛 > <a href="fashionTickets/init.jhtml" target="right">门票管理</a>', 'ticketsList', true);

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
			title : "活动ID", // 标题
			name : "id",
			//sort : "up",
			width : 100, // 宽度
			leng : 3, //显示长度
			type : "number", //数据类型					
		}, {
			title : "活动名称", // 标题
			name : "title",
			width : 200, // 宽度
			type : "string" //数据类型

		}, {
			title : "门票总数量", // 标题
			name : "seatSum",
			width : 80, // 宽度
			type : "string" //数据类型
		}, {
			title : "已售数量", // 标题
			name : "soldSum",
			width : 80, // 宽度
			type : "string" //数据类型

		}, {
			title : "剩余数量", // 标题
			name : "soldSum",
			width : 80, // 宽度
			type : "string", //数据类型
			customMethod : function(value, data) {
				return data.seatSum-data.soldSum<0?0:data.seatSum-data.soldSum;
			}

		}, {
			title : "销售日期", // 标题
			name : "saleStartTime",
			//sort : "up",
			width : 250, // 宽度
			type : "string", //数据类型
			customMethod : function(value, data) {
				return formatDate(new Date(data.saleStartTime), "yyyy-MM-dd HH:mm:ss")+"至"+formatDate(new Date(data.saleEndTime), "yyyy-MM-dd HH:mm:ss")
			}
		}, {
			title : "使用日期", // 标题
			name : "useStartTime",
			//sort : "up",
			width : 250, // 宽度
			leng : 8, //显示长度
			type : "string", //数据类型
			customMethod : function(value, data) {
				return formatDate(new Date(data.useStartTime), "yyyy-MM-dd HH:mm:ss")+"至"+formatDate(new Date(data.useEndTime), "yyyy-MM-dd HH:mm:ss")
			}
		},
		{
			title : "活动状态", // 标题
			name : "statusStr",
			width : 100, // 宽度
			type : "string" //数据类型
			
		} ],
		//操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "add","end","edit" ], // 不需要选择checkbox处理的权限
			width : 200, // 宽度
			// 当前列的中元素
			cols : [  {
					title : "下架", // 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/indiana/end.jhtml", // url
						param : [ "id" ], // 参数
						permission : "end" // 列权限
					},
					customMethod : function(value, data) {
						if (data.status==0&&new Date().getTime()<new Date(data.useEndTime).getTime()) {
							return "<a href='javascript:;' onclick='end_fun(" + data.id + ",1)'>下架</a>"
						}
						else if(data.status==1&&new Date().getTime()<new Date(data.useEndTime).getTime()){
							return "<a href='javascript:;' onclick='end_fun(" + data.id + ",0)'>上架</a>"
						}
						else {
							return '';
						}
					}
				},
				{
					title : "链接", // 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/indiana/end.jhtml", // url
						param : [ "id" ], // 参数
						permission : "end" // 列权限
					},
					customMethod : function(value, data) {
							return "<a href='"+fashionTicketsUrl+"&id="+data.id+"' target='_blank' >链接</a>"
					}
				},
				{
					title : "修改", // 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fashionTickets/edit/init.jhtml", // url
						param : [ "id" ], // 参数
						permission : "edit" // 列权限
					}
				}
				]
		}
	}, permissions);
}
function substr(obj, length) {
	if (obj.length > length) {
		obj = obj.substring(0, length) + "...";
	}
	return obj;
}

function end_fun(id,status) {
	showSmConfirmWindow(function() {
		$.post("fashionTickets/end.jhtml", {
			"id" : id,"status":status
		}, function(data, status) {
			if (status == "success") {
				showSmReslutWindow(data.success, data.msg);
				ticketsList.reload();
			}
			else {
				window.messager.warning(data.msg);
			}
		})
	}, "确定要"+(status==1?"下架":"上架")+"吗？");
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
	ticketsList.reload();
	$(object).attr("class", "btn btn-success");

}

$("input[type='reset']").on("click", function() {
	$("#status").val("");
	$("[name='bumen']").attr("class", "btn btn-default");
});


