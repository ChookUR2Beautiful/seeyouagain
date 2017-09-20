var recordList;
var initListUrl = "liveShare/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 打赏分红 > <a href="liveShare/manage/init.jhtml" target="right">邀请分享管理</a> ',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//上线
	putaway();
	
	//下线
	removeOffshelf();
	
	//导出
	$("#export").click(function(){
		var path="liveShare/manage/export.jhtml";
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
	var formId = "searchForm", title = "邀请分享列表", subtitle = "个分享";
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
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
		         {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveShare/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [ {
			title : "编号",
			name : "id",
			type : "string",
			width : 120
		}, {
			title : "标题",
			name : "title",
			type : "string",
			width : 180
		}, {
			title : "分享位置",
			name : "position",
			type : "string",
			width : 120,
			customMethod:function(value,data){
				//分享链接位置：001 商家详情，002 直播间，003 首页， 004商家列表页
				var result='-';
				switch (value) {
				case '001':
					result='商家详情';
					break;
				case '002':
					result='直播间';
					break;
				case '003':
					result='首页';
					break;
				case '004':
					result='商家列表页';
					break;
				default:
					break;
				}
				return result;
			}
		}, {
			title : "使用平台",
			name : "appType",
			type : "string",
			width : 120,
			customMethod:function(value,data){
				//应用类型：001 用户端，002 微信端
				var result='-';
				switch (value) {
				case '001':
					result='用户端';
					break;
				case '002':
					result='微信端';
					break;
				default:
					break;
				}
				return result;
			}
		}, {
			title : "页面类型",
			name : "pageType",
			type : "string",
			width : 120,
			customMethod:function(value,data){
				//分享页面类型：001 下载页， 002 已有页面，003 外部链接
				var result='-';
				switch (value) {
				case '001':
					result='下载页';
					break;
				case '002':
					result='已有页面';
					break;
				case '003':
					result='外部链接';
					break;
				default:
					break;
				}
				return result;
			}
		}  ,{
			title : "页面",
			name : "pageContent",
			type : "string",
			width : 120,
			customMethod:function(value,data){
				//分享页面：001 商家详情页，002 直播分享页 ，003 回放分享页 ，004 寻蜜客邀请页， 005 积分商品详情页 
				var result='-';
				switch (value) {
				case '001':
					result='商家详情页';
					break;
				case '002':
					result='直播分享页';
					break;
				case '003':
					result='回放分享页';
					break;
				case '004':
					result='寻蜜客邀请页';
					break;
				case '005':
					result='积分商品详情页';
					break;
				default:
					break;
				}
				return result;
			}
		} ,{
			title : "默认图片",
			name : "picUrl",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		}, {
			title : "状态",
			name : "status",
			type : "string",
			width : 120,
			customMethod : function(value, data) {
				var result='';
				switch (value) {
				case '001':
					result='上线';
					break;
				case '002':
					result='下线';
					break;
				default:
					break;
				}
				return result;
			}
		} ]
	}, permissions);
}


 /**
  * 直播日期控件初始化
  */
 function liveDateInit(){
	 $('.form_datetime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd'
		});
 }
 
 
 /**
  * 批量删除
  */
 $('#delete').click(function(){
 	var ids = recordList.getIds();
 	if(!ids){
 		showWarningWindow("warning","请至少选择一条记录！");
 		return;
 	}
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'liveShare/manage/delete.jhtml' + '?t=' + Math.random(),
 			data : 'ids=' + ids,
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					recordList.reset();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	});
 });
 
 
 /**
  * 批量上线
  */	
 function putaway(){
 	$("#putaway").click(function(){
 		console.log(recordList.getIds());
 		if(!recordList.getIds()){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!recordList.validateChose("status", "002", "数据已上线不能再次上线")){
 			return;
 		}
 		var data = {ids:recordList.getIds(),status:'001'};
 		updateBatch(data,"你确定要上线选中数据？");
 	});
 }
 
 /**
  * 批量下线
  */	
 function removeOffshelf(){
 	$("#removeOffshelf").click(function(){
 		console.log(recordList.getIds());
 		if(!recordList.getIds()){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!recordList.validateChose("status", "001", "数据已下线不能再次下线")){
 			return;
 		}
 		var data = {ids:recordList.getIds(),status:'002'};
 		updateBatch(data,"你确定要下线选中数据？");
 	});
 }
 
/**
 * 批量更新商品上架状态
 * @param data
 * @param title
 */
 function updateBatch(data,title){
 	showSmConfirmWindow(function() {
 					$.ajax({
 				        type: "POST",
 				        url: "liveShare/manage/updateBatch.jhtml",
 				        data: data,
 				        dataType: "json",
 				        success: function(result){
 							showSmReslutWindow(result.success, result.msg);
 							recordList.reload();
 				         }
 				    });
 			},title);
 }
 