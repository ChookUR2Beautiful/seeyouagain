inserTitle(' > 会员管理 > <span><a href="member/MemberBankCard/init.jhtml" target="right">银行卡管理</a>','bankCard', true);
var mem = $('#bankCard');
var pageDiv;
$(function() {
	var url = [ $(mem).attr("request-init"), ".jhtml" ].join("");
	pageDiv = $(mem).page({
		url : url,
		success : handle,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});
});

function handle(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;银行卡信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total + '】条&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#searchForm").serialize());
	obj.find('div').eq(0).scrollTablel({
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "jb" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ {
				title : "解绑银行卡",// 标题
				linkInfoName : "method",
				linkInfo : {
					method : "jb",
					param : [ "id", "uId", "type", "account", "cardTypeText" ],
					permission : "jb"
				}
			} ]
		},
		cols : [ {
			title : "用户编号",
			name : "uId",
			//					sort : "up",
			type : "string",
			width : 150
		}, {
			title : "证件类型",
			name : "idtypeText",
			//					sort : "up",
			type : "string",
			width : 150
		}, {
			title : "证件号码",
			name : "identity",
			//					sort : "up",
			type : "string",
			width : 160
		}, {
			title : "卡类型",
			name : "cardTypeText",
			//sort : "up",
			type : "string",
			width : 150
		}, {
			title : "卡号",
			name : "account",
			type : "string",
			width : 150
		}, {
			title : "开户行名称",
			name : "bank",
			type : "string",
			width : 150
		}, {
			title : "支行名称",
			name : "bankname",
			type : "string",
			width : 150
		}, {
			title : "所在区域",
			name : "whereArea",
			type : "string",
			width : 150
		}, {
			title : "持卡人姓名",
			name : "username",
			type : "string",
			width : 150
		}, {
			title : "预留手机号",
			name : "mobileid",
			type : "string",
			width : 150
		}, {
			title : "银行卡应用类型",
			name : "ispublicText",
			type : "string",
			width : 150
		}, {
			title : "银行卡提现用途",
			name : "cardPurposeText",
			type : "string",
			width : 200
		} ]
	}, permissions);

}
var jb = function(id, uId, type, account, cardTypeText) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : "POST",
			url : "member/MemberBankCard/unbundlingBankCard.jhtml",
			data : {
				"id" : id,
				"uId" : uId,
				"type" : type,
				"account" : account
			},
			dataType : "json",
			success : function(result) {
				showSmReslutWindow(result.success, result.msg);
				pageDiv.reload();
			}
		});
	}, "确定要解除" + cardTypeText + "绑定?");
}