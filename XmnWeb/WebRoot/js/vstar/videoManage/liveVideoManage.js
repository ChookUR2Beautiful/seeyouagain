var recordList;
var historyList;
var initListUrl = "liveVideo/manage/init/list/vstar.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveVideo/manage/init.jhtml" target="right">直播管理</a>',
			'userSpan', true);
	
	//直播日期控件初始化
	liveDateInit();
	
	//加载页面数据
	pageInit();
	
	//导出
	$("#export").click(function(){
		var path="liveVideo/manage/export.jhtml";
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
	
	historyList = $("#historyList").page({
		url : initListUrl,
		success : historySuccess,
		pageBtnNum : 10,
		paramForm : 'historySearchForm',
		param : {
			activityType : "5"
		}
	});
}

function success(data, obj) {
	var formId = "shareForm", title = "直播列表", subtitle = "个直播";
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
			queryPermission : [ "update","delete","up","down" ],// 不需要选择checkbox处理的权限
			width : 280,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "状态设置",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveVideo/manage/setState.jhtml",// url
						param : ["id"],// 参数
						permission : "setState"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var params="\"" + data.id + "\"";
				        if(data.zhiboType==1){
				        	params +=",\"" +'5' + "\"";
				        	params +=",\"" + 'endLive' +"\""; //结束直播
				            value = "<a href='javascript:setState(" + params + ")'>" + "结束" + "</a>";
				        }else if(data.zhiboType==2){
				        	
				        }
				        return value;
				    }
				 } ,{
						title : "置顶",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "liveVideo/manage/update/sendTop.jhtml",// url
							param : ["id"],// 参数
							permission : "update"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							if(data.zhiboType==1){//直播才显示置底操作
								value = "<a href='javascript:sendTop(\""+data.id + "\")'>" + "置顶" + "</a>";
							}
				            return value;
					    }
					} ,{
						title : "置底",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "liveVideo/manage/update/sendBack.jhtml",// url
							param : ["id"],// 参数
							permission : "update"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							if(data.zhiboType==1){//直播才显示置底操作
								value = "<a href='javascript:sendBack(\""+data.id + "\")'>" + "置底" + "</a>";
							}
				            return value;
					    }
					} 
			]
		},
		cols : [ {
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		}, {
			title : "商家/活动",
			name : "sellerAlias",
			type : "string",
			width : 150
		}, {
			title : "开播时间",
			name : "planStartDateStr",
			type : "string",
			width : 150
		}, {
			title : "直播结束",
			name : "planEndDateStr",
			type : "string",
			width : 150
		}, {
			title : "直播时长",
			name : "zhiboDuration",
			type : "string",
			width : 150
		}, {
			title : "主播",
			name : "playerName",
			type : "string",
			width : 150
		}, {
			title : "粉丝数",
			name : "fansCount",
			type : "string",
			width : 150
		}, {
			title : "打赏数",
			name : "incomeEggNums",
			type : "string",
			width : 150
		}, {
			title : "观看人数",
			name : "seeCount",
			type : "string",
			width : 150
		}, {
			title : "封面",
			name : "zhiboCover",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		} ]
	}, permissions);
}


function historySuccess(data, obj) {
	var formId = "shareForm", title = "直播列表", subtitle = "个直播";
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
		cols : [ {
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		}, {
			title : "商家/活动",
			name : "sellerAlias",
			type : "string",
			width : 150
		}, {
			title : "开播时间",
			name : "planStartDateStr",
			type : "string",
			width : 150
		}, {
			title : "直播结束",
			name : "planEndDateStr",
			type : "string",
			width : 150
		}, {
			title : "直播时长",
			name : "zhiboDuration",
			type : "string",
			width : 150
		}, {
			title : "主播",
			name : "playerName",
			type : "string",
			width : 150
		}, {
			title : "粉丝数",
			name : "fansCount",
			type : "string",
			width : 150
		}, {
			title : "打赏数",
			name : "incomeEggNums",
			type : "string",
			width : 150
		}, {
			title : "观看人数",
			name : "seeCount",
			type : "string",
			width : 150
		}, {
			title : "封面",
			name : "zhiboCover",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		} ]
	}, permissions);
}

 
 /**
  * 预告设置
  * @param id
  * @param zhiboType
  */
 function setAdvance(id,zhiboType){
	 $.ajax({
         url : "liveVideo/manage/setAdvance.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"zhiboType":zhiboType},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				recordList.reload();
     			}, 1000);
     		} else {
     			window.messager.warning(result.msg);
     		}
         }
     });
 }
 
 
 /**
  * 暂停直播
  * @param id
  * @param zhiboType
  */
 function setState(id,zhiboType,operationType){
	 $.ajax({
         url : "liveVideo/manage/setState.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"zhiboType":zhiboType,"operationType":operationType},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				recordList.reload();
     			}, 1000);
     		} else {
     			window.messager.warning(result.msg);
     		}
         }
     });
 }
 
/**
 * 
 * @param id
 * @param operationType
 */
 function upOrDown(id,operationType){
	 $.ajax({
         url : "liveVideo/manage/upOrDown.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"operationType":operationType},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				recordList.reload();
     			}, 1000);
     		} else {
     			showWarningWindow("warning", result.msg);
     		}
         }
     });
 }
 
 /**
  * 直播置底
  * @param id
  */
  function sendBack(id){
 	 $.ajax({
          url : "liveVideo/manage/update/sendBack.jhtml",
          type : "post",
          dataType : "json",
          data:{"id":id},
          success : function(result) {
         	 if (result.success) {
      			showSmReslutWindow(result.success, result.msg);
      			setTimeout(function() {
      				recordList.reload();
      			}, 1000);
      		} else {
      			showWarningWindow("warning", result.msg);
      		}
          }
      });
  }
  
  /**
   * 直播置顶
   * @param id
   */
   function sendTop(id){
  	 $.ajax({
       url : "liveVideo/manage/update/sendTop.jhtml",
       type : "post",
       dataType : "json",
       data:{"id":id},
       success : function(result) {
      	 if (result.success) {
   			showSmReslutWindow(result.success, result.msg);
   			setTimeout(function() {
   				recordList.reload();
   			}, 1000);
   		} else {
   			showWarningWindow("warning", result.msg);
       		}
           }
       });
   }
 
 /**
  * 切换tab页，重新加载数据
  * @param zhiboType
  */
 function tabChange(zhiboType){
//	 debugger;
	 $("#zhiboType").val(zhiboType);
	 slide(zhiboType);
	 pageInit();
 }
 
 
 /**
  * 切换样式
  * @param i
  */
 function slide(zhiboType){
	switch (zhiboType) {
	case 1:
		i=1;
		break;
	case 3:
		i=2;
		break;
	default:
		i=1;
		break;
	}
 	$('#myTab li').eq(i-1).addClass('active').siblings().removeClass('active');
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
 