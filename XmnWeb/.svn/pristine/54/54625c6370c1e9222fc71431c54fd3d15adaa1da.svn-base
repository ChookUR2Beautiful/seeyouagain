var recordList;
var initListUrl = "liveGift/manage/init/giftBagSetToAddList.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveGift/manage/init.jhtml" target="right">礼物管理</a> >礼包礼物',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//导出
	$("#export").click(function(){
		var path="liveGift/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

/**
 * 加载页面数据
 */
function pageInit(){
	recordList = $("#recordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
}

function success(data, obj) {
	var formId = "shareForm", title = "勾选礼物后，点击【确定】，将礼物添加至礼包", subtitle = "";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : true,
		cols : [ {
			title : "礼物编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "礼物名称",
			name : "giftName",
			type : "string",
			width : 180
		}, {
			title : "礼物价格",
			name : "giftPriceStr",
			type : "string",
			width : 150
		}, {
			title : "获得经验",
			name : "giftExperience",
			type : "string",
			width : 150
		}, {
			title : "礼物图片",
			name : "giftAvatar",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		} ]
	}, permissions);
}

 
/**
 * 确认添加
 */
 $('#addConfirm').click(function(){
 	var ids = recordList.getIds();
 	var giftBagId=$("#giftBagId").val();//礼物礼包ID
 	if(!ids){
 		showWarningWindow("warning","请至少选择一条记录！");
 		return;
 	}
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'liveGift/manage/giftBagSetAddBatch.jhtml' + '?t=' + Math.random(),
 			data : {"ids":ids,"giftBagId":giftBagId},
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					recordList.reload();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	},"<p>确认执行添加操作？</p>");
 });