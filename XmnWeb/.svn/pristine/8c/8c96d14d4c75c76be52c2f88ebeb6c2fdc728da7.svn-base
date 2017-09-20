var advanceList;
var underwayList;
var playbackList;
var anchorVideoList;
var recommendSpecialList;
var initListUrl = "livePageHome/manage/init/list.jhtml";
var anchorVideoListUrl = "livePageHome/manage/init/anchorVideoList.jhtml";
var imgRoot = $("#fastfdsHttp").val();

var modalId;
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="livePageHome/manage/init.jhtml" target="right">首页推荐管理</a>',
			'userSpan', true);
	
	//直播日期控件初始化
	liveDateInit();
	
	//加载页面数据
	pageInit();
	
	changeTab();
	
	//导出
	$("#export").click(function(){
		var path="livePageHome/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

	//好看推荐初始化
	specialRecommendInit();	
});

/**
 * 加载页面数据
 */
function pageInit(){
	advanceList = $("#advanceList").page({
		url : initListUrl,
		success : advanceListCallBack,
		pageBtnNum : 10,
		paramForm : 'advanceSearchForm',
		param : {
			activityType : "5"
		}
	});
	
	underwayList = $("#underwayList").page({
		url : initListUrl,
		success : underwayListCallBack,
		pageBtnNum : 10,
		paramForm : 'underwaySearchForm',
		param : {
			activityType : "5"
		}
	});
	
	playbackList = $("#playbackList").page({
		url : initListUrl,
		success : playbackListCallBack,
		pageBtnNum : 10,
		paramForm : 'playbackSearchForm',
		param : {
			activityType : "5"
		}
	});
	
	anchorVideoList = $("#anchorVideoList").page({
		url : anchorVideoListUrl,
		success : anchorVideoListCallBack,
		pageBtnNum : 10,
		paramForm : 'anchorVideoSearchForm',
		param : {
			activityType : "5"
		}
	});

	recommendSpecialList = $("#recommendSpecialList").page({
		url : "livePageHome/manage/init/recommendSpecialList.jhtml",
		success : recommendSpecialListSuccess,
		pageBtnNum : 10,
		paramForm : 'searchSpecialForm',
		param : {
			rType : "1"
		}
	});	
}

function advanceListCallBack(data, obj) {
	var formId = "shareForm", title = "预告推荐列表", subtitle = "个预告";
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
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "livePageHome/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
				            return value;
				    }
				 },
				{
					title : "修改排序",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "livePageHome/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改排序"
						},
						param : [ "id" ],
						permission : "update"
					}
				}
			]
		},
		cols : [  {
			title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 150
		},{
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		},{
			title : "主播",
			name : "nname",
			type : "string",
			width : 150
		},{
			title : "商家",
			name : "sellername",
			type : "string",
			width : 150
		} ]
	}, permissions);
}


function underwayListCallBack(data, obj) {
	var formId = "shareForm", title = "直播推荐列表", subtitle = "个直播";
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
			title : "操作",// 标题
			queryPermission : [ "update","delete"],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveVideo/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
			            var value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
			            return value;
					}
				 },
				 {
						title : "修改排序",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "livePageHome/manage/update/init.jhtml",
								position : "",
								width : "auto",
								title : "修改排序"
							},
							param : [ "id" ],
							permission : "update"
						}
					}
				
			]
		},
		cols : [ {
			title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 150
		}, {
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		}, {
			title : "主播",
			name : "nname",
			type : "string",
			width : 150
		}, {
			title : "商家",
			name : "sellername",
			type : "string",
			width : 150
		} 
	  ]
	}, permissions);
}


/**
 * 
 * @param data
 * @param obj
 */
function playbackListCallBack(data, obj) {
	var formId = "shareForm", title = "回放推荐列表", subtitle = "个回放";
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
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "livePageHome/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
			            var value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
			            return value;
					}
				 },
				 {
					title : "修改排序",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "livePageHome/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改排序"
						},
						param : [ "id" ],
						permission : "update"
					}
				}
			]
		},
		cols : [  {
			title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 150
		},{
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		},{
			title : "主播",
			name : "nname",
			type : "string",
			width : 150
		},{
			title : "商家",
			name : "sellername",
			type : "string",
			width : 150
		} ]
	}, permissions);
}



/**
 * 
 * @param data
 * @param obj
 */
function anchorVideoListCallBack(data, obj) {
	var formId = "shareForm", title = "精彩视频推荐列表", subtitle = "个精彩视频";
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
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveVideo/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDeleteAnchorVideo("+data.id+")'>" + "删除" + "</a>";
				            return value;
				    }
				 },{
						title : "修改排序",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "livePageHome/manage/updateAnchorVideo/init.jhtml",
								position : "",
								width : "auto",
								title : "修改排序"
							},
							param : [ "id" ],
							permission : "update"
						}
					}
			]
		},
		cols : [  {
			title : "推荐排序",
			name : "sort",
			type : "string",
			width : 150
		},{
			title : "标题",
			name : "title",
			type : "string",
			width : 180
		},{
			title : "主播",
			name : "anchorName",
			type : "string",
			width : 150
		}]
	}, permissions);
}


/**
 * 删除首页通告推荐状态
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "livePageHome/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 pageInit();
					 }, 2000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 
 /**
  * 删除首页精彩视频推荐状态
  */
  function confirmDeleteAnchorVideo(id){
 	 showSmConfirmWindow(function (){
 		 $.ajax({
 			 url : "livePageHome/manage/deleteAnchorVideo.jhtml",
 			 type : "post",
 			 dataType : "json",
 			 data:'id=' + id,
 			 success : function(result) {
 				 if (result.success) {
 					 showSmReslutWindow(result.success, result.msg);
 					 setTimeout(function() {
 						 pageInit();
 					 }, 2000);
 				 } else {
 					 window.messager.warning(result.msg);
 				 }
 			 }
 		 });
 	 },"确定要删除吗？");
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
  * 切换选项卡
  */
 function changeTab(){
	var operationType=$("#operationType").val();

	$("#myTab li.active").removeClass("active");
	$("#myTab li").eq(operationType).addClass("active");
	$(".tab-content .active").removeClass("active");
	$(".tab-content .tab-pane").eq(operationType).addClass("active");
 }
 
 
 // ******************************好看推荐******************************
 /**
  * 
  * @param data
  * @param obj
  */
 function recommendSpecialListSuccess(data, obj) {
 	var formId = "shareForm", title = "好看推荐列表", subtitle = "个好看推荐";
 	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
 			+ title+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
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
 			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
 			width : 250,// 宽度
 			// 当前列的中元素
 			cols : [ 
 				 {
 					title : "删除",// 标题
 					linkInfoName : "href",
 					linkInfo : {
 						href : "liveVideo/manage/delete.jhtml",// url
 						param : ["id"],// 参数
 						permission : "delete"// 列权限
 					},
 					customMethod : function(value, data){
 				            var value = "<a href='javascript:confirmDeleteRecommendSpecial("+data.id+")'>" + "删除" + "</a>";
 				            return value;
 				    }
 				 },{
 						title : "修改排序",// 标题
 						linkInfoName : "modal",
 						linkInfo : {
 							modal : {
// 								url : "livePageHome/manage/updateAnchorVideo/init.jhtml",
 								position : "",
 								width : "auto",
 								title : "修改排序"
 							},
 							param : [ "id" ],
 							permission : "update"
 						},
 					    customMethod : function(value, data){
 				            var value = "<a href='javascript:editSort("+data.id+", "+data.homeSort+")'>" + "修改排序" + "</a>";
 				            return value;
 				        }
 					}
 			]
 		},
 		cols : [ {
 			title : "专题名称",
 			name : "title",
 			type : "string",
 			width : 180
 			
 		},{
 			title : "类型",
 			name : "topicTypeDesc",
 			type : "string",
 			width : 150
 		},{
 			title : "地区",
 			name : "anchorName",
 			type : "string",
 			width : 150,
			customMethod: function(value, data){
	            var isNationwide = data.isNationwide;
	            // 是否全国通用 (0:不是 1:是 )
	            if (isNationwide == 1)
	                return "全国";
	            else {
	            	var value = (data.provinceName == undefined ? "" :data.provinceName) + (data.cityName== undefined ? "" : "-"+ data.cityName);
	            	return value;
	            }
	        }
 		},{
 			title : "推荐排序",
 			name : "homeSort",
 			type : "string",
 			width : 150
 		}]
 	}, permissions);
 }

 function specialRecommendInit() {
	var ld = $("#ld").cityLd({
		isChosen : false
	});
	
    $("#special_choose").chosenObject({
		hideValue : "id",
		showValue : "title",
		url : "businessman/specialTopic/init/list.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%"
	});
	
 }
 
 $("#specialModalSubmit").on("click",function(){
		var rid=$("#special_choose").val();
		if(!rid){
			showWarningWindow("warning", "请选择专题!", 9999);
			return;
		}
		var sort=$("#homeSort").val();
		if(sort<0){
			showWarningWindow("warning", "请正确填写排序!", 9999);
			return;
		}
		var province=$("#specialModal").find("[name=province]").val();
		var city=$("#specialModal").find("[name=city]").val();
		var homeSort=$("#specialModal").find("[name=homeSort]").val();
		var data={
				"rid":rid,
				"rtype": 1,
				"homeSort":homeSort
		}
		if(province&&city){ //省市都不为空才保存
			data.provinceId=province;
			data.cityId=city;
		}

		$.ajax({
			 url : "livePageHome/manage/addRecommendSpecial.jhtml",
			 type : "post",
			 dataType : "json",
			 data:data,
			 success : function(result) {
				 if(result.success){
					 recommendSpecialList.reload();
				 }
			 }
		});
		$('#editSpecialModal').modal('hide');
	});
 
 
 /**
  * 删除首页好看推荐
  */
  function confirmDeleteRecommendSpecial(id){
 	 showSmConfirmWindow(function (){
 		 $.ajax({
 			 url : "livePageHome/manage/deleteSpecialRecommendById.jhtml",
 			 type : "post",
 			 dataType : "json",
 			 data:'id=' + id,
 			 success : function(result) {
 				 if (result.success) {
 					 showSmReslutWindow(result.success, result.msg);
 					 setTimeout(function() {
 						 pageInit();
 					 }, 2000);
 				 } else {
 					 window.messager.warning(result.msg);
 				 }
 			 }
 		 });
 	 },"确定要删除吗？");
  }
  
  function editSort(id, sort){
	    modalId=id;
	    
		$("#editSort_inp").val(sort);
		$('#editSpecialSortModal').modal();
	}
  
  
  $("#editSortSubmit").on("click",function(){
		var sort=$("#editSort_inp").val();
		if(sort<0){
			showWarningWindow("warning", "请正确填写排序!", 9999);
			return;
		}
		var id = $("").val();
		var url="livePageHome/manage/updateRecommendSpecialSort.jhtml";
		$.ajax({
			 url : url,
			 type : "post",
			 dataType : "json",
			 async: false,
			 data:{"id": modalId, "homeSort":sort},
			 success : function(data) {
				 if (data.success) {
					 $('#editSpecialSortModal').modal('hide');
					 recommendSpecialList.reload();
				 }
			 }
		});
	});
 
  
  
  
  
/************************  美味撩味****************************/

  /**
   * 初始化直播标签分类
   */
  function liveRecordClassifyIdInit(){
  	$("#classifyId").chosenObject({
  		hideValue : "id",
  		showValue : "classifyName",
  		url : "businessman/classify/liveRecordClassifyInit.jhtml",
  		isChosen:true,//是否支持模糊查询
  		isCode:true,//是否显示编号
  		isHistorical:false,//是否使用历史已加载数据
  		width:"40%"
  	});
  }

  //选择分类后，初始化标签
  $('body').on("click",'#classifyId_chosen .chosen-results li',function(){
  	//初始化联动标签
  	liveRecordTagInit();
  	// 当原始select中的选项发生变化时通知chosen更新选项列表
  	$('#tagId').trigger('chosen:updated');
  });



  /**
   * 初始化直播标签分类
   */
  function initAddLiveRecordClassifyIdInit(index){
  	var currentId="#classifyId"+index;
  	$(currentId).chosenObject({
  		hideValue : "id",
  		showValue : "classifyName",
  		url : "businessman/classify/liveRecordClassifyInit.jhtml",
  		isChosen:true,//是否支持模糊查询
  		isCode:true,//是否显示编号
  		isHistorical:false,//是否使用历史已加载数据
  		width:"40%"
  	});
  }

  //选择分类后，初始化标签
  function bindClassifyChange(index){

  	$('body').on("click",'#classifyId'+index+'_chosen .chosen-results li',function(){
  		//初始化联动标签
  		addliveRecordTagInit(index);
  		
  		// 当原始select中的选项发生变化时通知chosen更新选项列表
  		$('#tagId'+index).trigger('chosen:updated');
  	});
  }




  /**
   * 初始化直播标签
   */
  function liveRecordTagInit(){
  	var classifyId=$("#classifyId").val();
  	$("#tagId").chosenObject({
  		hideValue : "id",
  		showValue : "tagName",
  		url : "businessman/classify/liveRecordTagInit.jhtml",
  		isChosen:true,//是否支持模糊查询
  		isCode:true,//是否显示编号
  		filterVal:classifyId,////过滤的值 (classifyId=1)
  		isHistorical:false,//是否使用历史已加载数据
  		width:"40%"
  	});
  }

  /**
   * 初始化直播标签
   */
  function addliveRecordTagInit(index){
  	var classifyId=$("#classifyId"+index).val();
  	$("#tagId"+index).chosenObject({
  		hideValue : "id",
  		showValue : "tagName",
  		url : "businessman/classify/liveRecordTagInit.jhtml",
  		isChosen:true,//是否支持模糊查询
  		isCode:true,//是否显示编号
  		filterVal:classifyId,////过滤的值 (classifyId=1)
  		isHistorical:false,//是否使用历史已加载数据
  		width:"40%"
  	});
  }
