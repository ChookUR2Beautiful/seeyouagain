var giftList;
var initListUrl = "freshAuction/manage/bidding/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	//加载页面数据
	loadBiddingList();
});

/**
 * 加载页面数据
 */
function loadBiddingList(){
	giftList = $("#giftList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
}

/**
 * 查询礼物成功回调函数
 * @param data
 * @param obj
 */
function success(data, obj) {
	var formId = "searchForm", title = "购买列表", subtitle = "个购买记录";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			
		},
		cols : [ {
			title : "会员名称",
			name : "userName",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				var userName=data.userName==undefined?"":data.userName;
				var phone=data.phone;
				result=userName+"("+phone+")";
				return result;
			}
		}, {
			title : "出价",
			name : "risePrice",
			type : "string",
			width : 150
		}, {
			title : "出价时间",
			name : "createTimeStr",
			type : "string",
			width : 180
		}]
	}, permissions);
}

