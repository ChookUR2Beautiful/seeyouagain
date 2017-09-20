var divisionList;
$(document).ready(function() {
	divisionList = $('#divisionList').page({
		url : 'division/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize : 15,
		paramForm : 'searchActivityForm'
	});

	inserTitle(' > 新食尚大赛 > <a href="division/init.jhtml" target="right">赛区管理</a>', 'ticketsList', true);

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
			title : "赛区编号", // 标题
			name : "id",
			//sort : "up",
			width : 50, // 宽度
			leng : 3, //显示长度
			type : "number", //数据类型					
		}, {
			title : "赛区", // 标题
			name : "divisionName",
			width : 100, // 宽度
			type : "string" //数据类型

		}, {
			title : "城市", // 标题
			name : "cityNames",
			width : 400, // 宽度
			type : "string" //数据类型

		}
		],
		//操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "add","edit" ], // 不需要选择checkbox处理的权限
			width : 200, // 宽度
			// 当前列的中元素
			cols : [  
				{
					title : "编辑",// 标题
					linkInfoName : "modal",
					width : 20,
					linkInfo : {
						modal : {
							url : "division/add/init.jhtml",
							position : "100px",
							width : "600px"
						},
						param : ["id"],
						permission : "edit"
					}
				}
				]
		}
	}, permissions);
}
function substr(obj, length) {
	return obj;
}






