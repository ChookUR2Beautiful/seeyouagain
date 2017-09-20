var bankList;
var initListUrl = "dataDictionary/bank/init/list.jhtml"
$(function() {
	inserTitle(
			' > 基础数据管理 > <a href="dataDictionary/bank/init.jhtml" target="right">银行管理</a>',
			'userSpan', true);
	bankList = $("#bankList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});

});

function success(data, obj) {
	var formId = "shareForm", title = "银行列表", subtitle = "个银行";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "brandId",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update" ],// 不需要选择checkbox处理的权限
			width : 50,// 宽度
			// 当前列的中元素
			cols : [ {
				title : "修改",// 标题
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "dataDictionary/bank/update/init.jhtml",
						position : "",
						width : "auto",
						title : "修改银行"
					},
					param : [ "id" ],
					permission : "update"
				}
			} ]
		},
		cols : [ {
			title : "银行编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "银行名称",
			name : "bankName",
			type : "string",
			width : 150
		}, {
			title : "英文缩写",
			name : "abbrev",
			type : "string",
			width : 150
		}, {
			title : "是否支持提现",
			name : "status",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				switch (value) {
				case 0:
					result = "支付提现";
					break;
				case 1:
					result = "不支持提现";
					break;
				default:
					break;
				}
				return result;
			}
		}, {
			title : "更新时间",
			name : "sdate",
			type : "string",
			width : 150
		}, {
			title : "备注",
			name : "explains",
			type : "string",
			width : 150
		} ]
	}, permissions);
}