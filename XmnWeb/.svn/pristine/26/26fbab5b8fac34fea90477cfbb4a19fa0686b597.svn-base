var recordList;
var initListUrl = "experienceofficer/user/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
		' > 直播频道 > <a href="experienceofficer/activity/init.jhtml" target="right">会员美食体验卡管理</a> ',
		'userSpan', true);

	initExperienceofficerUser();

	//日期控件初始化
	liveDateInit();

});

//初始化体验活动
function initExperienceofficerUser() {

	recordList = $("#recordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

}



function success(data, obj) {
//	var formId = "searchForm",
//		title = "共",
//		subtitle = "个体验活动";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
		+ '<font>共计【'
		+ data.total
		+ '】&nbsp;个体验场次</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
	+ getFormParam($("#searchForm").serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : false,
		// 操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "update"], // 不需要选择checkbox处理的权限
			width : 80, // 宽度
			// 当前列的中元素
			cols : [
				{
					title : "启用停用",// 标题
					linkInfoName : "method",
					linkInfo : {
						method : "lockOrNot",
						param : ["cardId"],
						permission : "update"
					},
					customMethod : function(value, data){
						var result;
						console.info(data.statusStr +'?已过期');
						if(data.statusStr == '已过期'){
							result = '<a href="javascript:void(0);" title="该体验卡状态不合理" class="hidden"></a>';
							return result;
						}
						if(data.status == 1){//解锁
							result = value.replace(/lockOrNot/,"unlock");
							result = result.replace(/启用停用/,"启用")
						}else if(data.status == 0){
							result = value.replace(/lockOrNot/,"lock");
							result = result.replace(/启用停用/,"停用");
						}else{
							result = '<a href="javascript:void(0);" title="该体验卡状态不合理" class="hidden"></a>'
						}
						return result;
					}
				}
			]
		},
		cols : [ {
			title : "会员编号",
			name : "uid",
			type : "string",
			width : 100
		}, {
			title : "会员昵称",
			name : "nname",
			type : "string",
			width : 150
		}, {
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 100
		}, {
			title : "状态",
			name : "statusStr",
			type : "string",
			width : 100
		}, {
			title : "购买日期",
			name : "buyTimeStr",
			type : "string",
			width : 100
		}, {
			title : "到期日期",
			name : "limitTimeStr",
			type : "string",
			width : 100
		}, {
			title : "可使用次数",
			name : "unused",
			type : "string",
			width : 100
		}, {
			title : "已使用次数",
			name : "used",
			type : "string",
			width : 100
		}]
	}, permissions);
}


/**
 * 直播日期控件初始化
 */
function liveDateInit() {
	$('.form_datetime').datetimepicker({
		format : 'yyyymm',
		weekStart : 1,
		autoclose : true,
		startView : 3,
		minView : 3,
		forceParse : false,
		language : 'zh-CN'
	});
}

/**
 * 停用该体验卡
 */
function lock(id){
	showSmConfirmWindow(function(){
		lockOrNot(1,id);
	},"确定要停用该体验卡？");
	
}

/**
 * 启用该体验卡
 */
function unlock(id){
	showSmConfirmWindow(function(){
		lockOrNot(0,id);
	},"确定要启用该体验卡？");
}

/**
 * 
 * @param islock  0：解锁 ，1：锁定
 */
function lockOrNot(status,id){
	if(status!=undefined && id!=undefined){
		var data ={"cardId":id,"status":status}; 
		var url = "experienceofficer/user/update.jhtml";
		$.ajax({
			type : 'post',
			url : url + "?t="
					+ Math.random(),
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
				try {
					if (recordList != undefined) {
						recordList.reload();
					}
				} catch (err) {
					console.log(err);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
			}
		});
	}
}


