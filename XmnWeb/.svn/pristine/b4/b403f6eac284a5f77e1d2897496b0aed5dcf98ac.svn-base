var wordList;
$(document).ready(function() {
	wordList = $("#wordList").page({
		url : 'fresh/word/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize : 15,
		paramForm : 'searchForm'
	});

	inserTitle(' > 热门搜索管理> <a href="fresh/word/list/init.jhtml" target="right">所有关键字</a>', 'List', true);

});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var callbackParam = "isBackButton=true&callbackParam=" + getFormParam($("#searchForm").serialize());
	obj.find('div').eq(0).scrollTablel({
		tableClass : "table-bordered table-striped info",
		callbackParam : callbackParam,
		//数据
		data : data.content,
		//数据行
		cols : [ {
			title : "关键字", // 标题
			name : "word",
			//sort : "up",
			width : 100, // 宽度
			leng : 3, //显示长度
			type : "string", //数据类型					
		}, {
			title : "关键字来源", // 标题
			name : "type",
			width : 150, // 宽度
			type : "string", //数据类型
			customMethod : function(value, data) {
				if(value==4){
					return "真实搜索";
				}else if(value==2){
					return "手动添加";
				}
			}

		}, {
			title : "搜索次数", // 标题
			name : "wordSum",
			//sort : "up",
			width : 160, // 宽度
			type : "int"//数据类型
		}, {
			title : "排序", // 标题	
			name : "sort",
			//sort : "up",
			width : 120, // 宽度
			leng : 8, //显示长度
			type : "int", //数据类型
				customMethod : function(value, data) {
					if(data.type==2){
						return value;
					}else{
						return "-";
					}
				}
		}],
		//操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "edit","delete" ], // 不需要选择checkbox处理的权限
			width : 130, // 宽度
			// 当前列的中元素
			cols : [ {
				title : "编辑", // 标题
				linkInfoName : "modal",
				width : 20,
				linkInfo : {
					modal : {
						url : "fresh/word/edit/init.jhtml",
						position : "100px",
						width : "600px"
					},
					param : [ "id" ], // 参数
					permission : "edit" // 列权限

				},
				customMethod : function(value, data) {
					if (data.type == 2) {
						return "<a href='javascript:;' onclick='edit(" + data.id + ")'>编辑</a>";
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
							url : "fresh/word/delete.jhtml", // url
							position : "60px", // 模态框显示位置
							width : "800px"
						},
						param : [ "id" ], // 参数
						permission : "delete" // 列权限
					},
					customMethod : function(value, data) {
						if (data.type == 2) {
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

function edit(id){
	var but=$("<button>").attr("data-type","ajax").attr("data-url","fresh/word/edit/init.jhtml?id="+id).attr("data-toggle","modal").attr("style","display:none;");
	$("body").append(but);
	but.trigger("click");
}



function delete_fun(id) {
	showSmConfirmWindow(function() {
		$.post("fresh/word/delete.jhtml", {
			"id" : id
		}, function(data, status) {
			if (status == "success") {
				showSmReslutWindow(data.success, data.msg);
				wordList.reload();
			}
			else {
				window.messager.warning(data.msg);
			}
		})
	}, "确定要删除吗？");
}






