var recordList;
var initListUrl = "liveAnchorVideo/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveAnchorVideo/manage/init.jhtml" target="right">精彩视频管理</a> >精彩视频',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//上线
	putaway();
	
	//下线
	removeOffshelf();
	
	//导出
	$("#export").click(function(){
		var path="liveAnchorVideo/manage/export.jhtml";
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
	var formId = "shareForm", title = "精彩视频列表", subtitle = "个视频";
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
							url : "liveAnchorVideo/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改精彩视频"
						},
						param : [ "id" ],
						permission : "update"
					}
				},{
					title : "取消推荐",// 标题
					linkInfoName : "href",
					linkInfo : {
						param : [ "id" ],
						permission : "update"
					},
					customMethod : function(value, data) {
						if(data.recommended==1){
							return "<a href='javascript:;' onclick='updateHome("+data.id+",0)'>取消推荐</a>"
						}else{
							return "<a href='javascript:;' onclick='updateHome("+data.id+",1)'>设为推荐</a>"
						}
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
			title : "标题",
			name : "title",
			type : "string",
			width : 180
		}, {
			title : "主播",
			name : "anchorName",
			type : "string",
			width : 180
		}, {
			title : "是否推荐",
			name : "recommended",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				return value==0?'否':'是';
			}
		}
		, {
			title : "排序",
			name : "sort",
			type : "string",
			width : 180
		} ,{
			title : "封面",
			name : "coverUrl",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		}, {
			title : "状态",
			name : "status",
			type : "string",
			width : 180,
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

function updateHome(ids,recommended){
	var data = {ids:ids,recommended:recommended};
	if(recommended){
		updateBatch(data,"你确定要设置首页推荐吗?");
	}else{
		updateBatch(data,"你确定要取消首页推荐吗?");
	}
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
 			url : 'liveAnchorVideo/manage/delete.jhtml' + '?t=' + Math.random(),
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
 		if(!recordList.validateChose("status", "002", "视频已上线不能再次上线")){
 			return;
 		}
 		var data = {ids:recordList.getIds(),status:'001'};
 		updateBatch(data,"你确定要上线选中视频？");
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
 		if(!recordList.validateChose("status", "001", "视频已下线不能再次下线")){
 			return;
 		}
 		var data = {ids:recordList.getIds(),status:'002'};
 		updateBatch(data,"你确定要下线选中视频？");
 	});
 }
 
 $("#setHome").on("click",function(){
		if(!recordList.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		/*if(!recordList.validateChose("recommended", 1, "视频已设为首页推荐不能再次设置")){
			return;
		}*/
		var data = {ids:recordList.getIds(),recommended:1};
		updateBatch(data,"你确定要设为首页推荐？");
 });
 
 
/**
 * 批量更新商品上架状态
 * @param data
 * @param title
 */
 function updateBatch(data,title){
 	showSmConfirmWindow(function() {
 					$.ajax({
 				        type: "POST",
 				        url: "liveAnchorVideo/manage/updateBatch.jhtml",
 				        data: data,
 				        dataType: "json",
 				        success: function(result){
 							showSmReslutWindow(result.success, result.msg);
 							recordList.reload();
 				         }
 				    });
 			},title);
 }
 