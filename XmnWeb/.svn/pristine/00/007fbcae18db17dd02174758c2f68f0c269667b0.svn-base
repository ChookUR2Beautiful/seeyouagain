var recordList;
var initListUrl = "announcement/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 基础数据管理 > <a href="announcement/manage/init.jhtml" target="right">公告管理</a>',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//上线
	putaway();
	
	//下线
	removeOffshelf();
	
	//导出
	$("#export").click(function(){
		var path="announcement/manage/export.jhtml";
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
	var formId = "shareForm", title = "公告列表", subtitle = "个公告";
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
		         	linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "announcement/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改公告"
						},
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
			width : 150
		}, {
			title : "公告内容",
			name : "content",
			type : "string",
			width : 180
		},{
			title : "应用类型",
			name : "app",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				var result='';
				switch (value) {
				case 1:
					result='商户端';
					break;
				case 2:
					result='用户端';
					break;
				default:
					break;
				}
				return result;
			}
		},{
			title : "状态",
			name : "status",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				var result='';
				switch (value) {
				case 0:
					result='无效';
					break;
				case 1:
					result='有效';
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
 			url : 'announcement/manage/delete.jhtml' + '?t=' + Math.random(),
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
 		if(!recordList.validateChose("status", "002", "角色已启用不能再次启用")){
 			return;
 		}
 		var data = {ids:recordList.getIds(),status:'001'};
 		updateBatch(data,"你确定要启用选中角色？");
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
 		if(!recordList.validateChose("status", "001", "角色已冻结不能再次冻结")){
 			return;
 		}
 		var data = {ids:recordList.getIds(),status:'002'};
 		updateBatch(data,"你确定要冻结选中角色？");
 	});
 }
 
/**
 * 批量更新角色状态
 * @param data
 * @param title
 */
 function updateBatch(data,title){
 	showSmConfirmWindow(function() {
 					$.ajax({
 				        type: "POST",
 				        url: "announcement/manage/updateBatch.jhtml",
 				        data: data,
 				        dataType: "json",
 				        success: function(result){
 							showSmReslutWindow(result.success, result.msg);
 							recordList.reload();
 				         }
 				    });
 			},title);
 }
 