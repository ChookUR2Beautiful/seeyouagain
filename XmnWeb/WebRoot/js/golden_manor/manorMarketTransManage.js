var sellList;
var buyList;

var initListUrl = "manorMarketTrans/manage/init/list.jhtml";
$(function() {
	inserTitle(
			' > 黄金庄园 > <a href="manorMarketTrans/manage/init.jhtml" target="right">市集管理</a>',
			'userSpan', true);
	//加载列表数据
	pageInit();

});

/**
 * 加载页面数据
 */
function pageInit(){
	//tab1
	sellList = $("#sellList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchFormSell',
		param : {
			transType : "1"  //交易类型 1.出售 2.求购
		}
	});
	
	//tab2
	buyList = $("#buyList").page({
		url : initListUrl,
		success : buySuccess,
		pageBtnNum : 10,
		paramForm : 'searchFormBuy',
		param : {
			transType : "2" //交易类型 1.出售 2.求购
		}
	});	
}

function success(data, obj) {
	var formId = "searchFormSell";
	var title = "会员列表 ", subtitle = "个会员";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "delete"],// 不需要选择checkbox处理的权限
			width : 60,// 宽度
			// 当前列的中元素
			cols : [ 
		        {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
//						href : "manorMember/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
						var value = "";
						if (data.transState == 0){
							 value = "<a href='javascript:updateTransState(\""+data.id+"\", \""+data.purchasable+"\")'>" + "删除" + "</a>";
						}
					    return value;
				    }
				}
			]
		},
		cols : [ {
			title : "发布者",
			name : "uid",
			type : "string",
			width : 100,
			customMethod:function(value,data){
				var value = data.nname + ( data.phone == null || data.phone == undefined  ? "" : " (" + data.phone +")" );
				return value;
			}
		},{
			title : "目前等级",
			name : "levelName",
			type : "string",
			width : 80
		}, {
			title : "发布类型",
			name : "transTypeDesc",
			type : "string",
			width : 60
		},{
			title : "出售数量",
			name : "propNumber",
			type : "string",
			width : 80
		}, {
			title : "出价",
			name : "unitPrice",
			type : "string",
			width : 80
		}, {
			title : "最高出价",
			name : "rulingPrice",
			type : "string",
			width : 80	
		}, {
			title : "信息回复",
			name : "giveName",
			type : "string",
			width : 60,
			customMethod:function(value,data){
				var value = "<a href='javascript:chatListView(\""+data.id+"\")'>" + data.chatNumber + "</a>";
				return value;
			}
		}, {
			title : "状态",
			name : "transStateDesc",
			type : "string",
			width : 60
		}, {
			title : "发布时间",
			name : "initiateDate",
			type : "string",
			width : 120
		} ]
	}, permissions);
}



function buySuccess(data, obj) {
	var formId = "searchFormBuy";
	var title = "会员列表 ", subtitle = "个会员";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "delete"],// 不需要选择checkbox处理的权限
			width : 80,// 宽度
			// 当前列的中元素
			cols : [ 
		        {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
//						href : "manorMember/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
						var value = "";
						if (data.transState == 0){
							 value = "<a href='javascript:updateTransState(\""+data.id+"\", \""+data.purchasable+"\")'>" + "删除" + "</a>";
						}
					    return value;
				    }
				}
			]
		},
		cols : [ {
			title : "发布者",
			name : "uid",
			type : "string",
			width : 100,
			customMethod:function(value,data){
				var value = data.nname + ( data.phone == null || data.phone == undefined ? "" : " (" + data.phone +")" );
				return value;
			}
		},{
			title : "目前等级",
			name : "levelName",
			type : "string",
			width : 120
		}, {
			title : "发布类型",
			name : "transTypeDesc",
			type : "string",
			width : 60
		},{
			title : "收购金额",
			name : "unitPrice",
			type : "string",
			width : 80
		}, {
			title : "发布描述",
			name : "title",
			type : "string",
			width : 160
		}, {
			title : "信息回复",
			name : "giveName",
			type : "string",
			width : 60,
			customMethod:function(value,data){
				var value = "<a href='javascript:chatListView(\""+data.id+"\")'>" + data.chatNumber + "</a>";
				return value;
			}
		}, {
			title : "状态",
			name : "transStateDesc",
			type : "string",
			width : 60
		}, {
			title : "发布时间",
			name : "initiateDate",
			type : "string",
			width : 120
		} ]
	}, permissions);
}


/* 下线发布信息 */
function updateTransState(id, transState){
	 //交易状态 0.发布中 1.已结束 2.已删除 3.
	 var status = 2;  
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "manorMarketTrans/manage/update.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{id:id, transState:status},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
//						 anchorList.reload();
						 pageInit();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要修改记录吗？");
}

/* ********************庄园交易动态*********** */
var dynamicList;
function chatListView(transId) {
	$("#chatInfoTB").html("");
//	var dialogueType = 1;  //动态类型 1.出价 2.留言
	dynamicList = $("#dynamicList").page({
		url : 'manorMarketTrans/manage/list/viewDynamic.jhtml',
		success : dynamicSuccess,
		pageBtnNum : 10,
//		paramForm : 'searchForm',
		param : {
			transId:transId,
			dialogueType : "1"  //交易类型 1.出售 2.求购
		}
	});
}

function dynamicSuccess(data, obj) {
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
//		callbackParam : callbackParam,
		data : data.content,
//		caption : captionInfo,
		// checkable : checkable,
		cols : [ {
			title : "留言用户",
			name : "uid",
			type : "string",
			width : 120,
			customMethod:function(value,data){
				var value = (data.nname == undefined  ? "" :data.nname) + ( data.phone == null || data.phone == undefined  ? "" : " (" + data.phone +")" );
				return value;
			}
		}, {
			title : "出价",
			name : "rulingPrice",
			type : "string",
			width : 80
		},{
			title : "留言",
			name : "content",
			type : "string",
			width : 120
		}, {
			title : "回复",
			name : "reversion",
			type : "string",
			width : 160
		} ]
	}, permissions);
	
	// 显示模态框数据
	$('#chatRecordModal').modal();
	
	// 点击关闭遮罩层
	$(".close-shade").on("click", function() {
		$(".shade-box,.shade-content").hide();
	});
}



