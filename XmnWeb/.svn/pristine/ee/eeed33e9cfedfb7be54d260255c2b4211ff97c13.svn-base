var systemPushList;
var initListUrl = "coupon/couponissue/systemPush/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	
	//加载页面数据
	pageInit();
	
	//导出
	$("#export").click(function(){
		var path="liveGift/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	
	
	//退回
	removeOffshelf();

});

/**
 * 加载页面数据
 */
function pageInit(){
	systemPushList = $("#systemPushList").page({
		url : initListUrl,
		success : systemPushSuccess,
		pageBtnNum : 10,
		paramForm : 'systemPushForm',
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
function systemPushSuccess(data, obj) {
	var formId = "systemPushForm", title = "系统推送列表";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;' + title + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</caption>'; var callbackParam = "isBackButton=true&callbackParam=" + getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 120,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "状态设置",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveGift/manage/setStatus.jhtml",// url
						param : ["id"],// 参数
						permission : "setStatus"// 列权限
					},
					customMethod : function(value, data){
						var value="";
				        if(data.status==1){
				            value = "<a href='javascript:setStatus(\""+data.id+"\",\"" + 0 + "\")'>" + "停用" + "</a>";
				        }else if(data.status==0){
				            value = "<a href='javascript:setStatus(\""+data.id+"\",\"" + 1 + "\")'>" + "启用" + "</a>";
				        }
				        return value;
				    }
				 }
			]
		},
		cols : [
			// { title : "操作",
			// width : 50,
            // customMethod : function(data){
			// 	return "<input type='button' value='追回' class='btn-mini btn-primary'></input>";
			// } },
			{
			title : "用户编号",
			name : "uid",
			type : "string",
			width : 150
		}, {
			title : "用户昵称",
			name : "nname",
			type : "string",
			width : 180
		}, {
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 150
		}, {
			title : "优惠券名称",
			name : "cname",
			type : "string",
			width : 180
		}, {
			title : "优惠券类型",
			name : "ctypeStr",
			type : "string",
			width : 180,
			// customMethod:function(value,data){
			// 	var result="";
			// 	switch (value) {
			// 	case 0:
			// 		result="消费优惠劵";
			// 		break;
			// 	case 1:
			// 		result="商城券";
			// 		break;
			// 	default:
			// 		result="消费优惠劵";
			// 		break;
			// 	}
			// 	return result;
			// }
		}, {
			title : "数量",
			name : "issuevolume",
			type : "string",
			width : 150
		}, {
			title : "推送时间",
			name : "dateIssue",
			type : "string",
			width : 150
		} ]
	}, permissions);
}


/**
 * 批量退回
 */	
function removeOffshelf(){
	$("#removeOffshelf").click(function(){
		console.log(systemPushList.getValue('cdid').join(','));
		if(systemPushList.getValue('cdid')==undefined || systemPushList.getValue('cdid').length==0){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		if(!systemPushList.validateChose("userStatus", "0", "数据不符合条件，只有未使用状态的优惠券才可退回")){
			return;
		}
		var data = {cdids:systemPushList.getValue('cdid').join(','),userStatus:'3'};
		updateStatusBatch(data,"退回后优惠劵将会全部变更为失效状态，你确定要退回选中的优惠券？");
	});
}

/**
 * 批量更新优惠券状态
 * @param data
 * @param title
 */
 function updateStatusBatch(data,title){
 	showSmConfirmWindow(function() {
 					$.ajax({
				        type: "POST",
				        url: "coupon/couponissue/systemPush/update/updateStatusBatch.jhtml",
				        data: data,
				        dataType: "json",
 				        success: function(result){
 							showSmReslutWindow(result.success, result.msg);
 							systemPushList.reload();
 				         }
 				    });
 			},title);
 }
 
