var winnerList;
$(document).ready(function() {
	winnerList = $('#winnerList').page({
		url : 'fresh/indiana/winner/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize : 15,
		paramForm : 'winnerForm'
	});

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
			title : "夺宝编号", // 标题
			name : "id",
			//sort : "up",
			width : 100, // 宽度
			leng : 3, //显示长度
			type : "number", //数据类型					
		}, {
			title : "会员名称", // 标题
			name : "nname",
			width : 200, // 宽度
			type : "string", //数据类型
			customMethod : function(value, data) {
				return data.nname+"("+data.phone+") "+(data.type==2?"【预】":"");
			}
		}, {
			title : "购买时间", // 标题
			name : "createTime",
			width : 100, // 宽度
			type : "string"//数据类型
		}],
		//操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "edit", "preview", "copyurl", "end", "delete" ], // 不需要选择checkbox处理的权限
			width : 100, // 宽度
			// 当前列的中元素
			cols : [ {
				title : "预设", // 标题
				linkInfoName : "href",
				linkInfo : {
					href : "fresh/indiana/add/init.jhtml", // url
					param : [ "type" ], // 参数
					permission :"setWinner" // 列权限
				},
				customMethod : function(value, data) {
					if($("#type").val()){
						return '';
					}
					if (data.type == 0) {
						return "<a href='javascript:;' onclick='setWinner(" + data.id + ")'>预设</a>";
					}
					else if(data.type==2){
						return "<a href='javascript:;' onclick='cancel(" + data.id + ")'>取消</a>";
					}
				}
			}]
		}
	}, permissions);
}

function setWinner(id){
	$.ajax({
		type : 'post',
		url : "fresh/indiana/winner/setWinner.jhtml",
		data : {"id":id},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			winnerList.reload();
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}
function cancel(id){
	$.ajax({
		type : 'post',
		url : "fresh/indiana/winner/cancel.jhtml",
		data : {"id":id},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			winnerList.reload();
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}



