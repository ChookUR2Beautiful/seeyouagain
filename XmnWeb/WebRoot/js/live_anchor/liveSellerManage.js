var recordList;
var initListUrl = "liveSeller/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveSeller/manage/init.jhtml" target="right">推荐商家</a>',
			'userSpan', true);
	
	//直播日期控件初始化
	liveDateInit();
	
	//默认查询正在直播的通告
	$("#zhiboType").val(1);
	
	//加载页面数据
	pageInit();
	
	//导出
	$("#export").click(function(){
		var path="liveSeller/manage/export.jhtml";
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
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "上移",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveSeller/manage/up.jhtml",// url
						param : ["id"],// 参数
						permission : "up"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						if(data.zhiboType==1){//直播才显示上移，下移操作
							value = "<a href='javascript:upOrDown(\""+data.id+"\",\"" + "up" + "\")'>" + "上移" + "</a>";
						}
			            return value;
				    }
				} ,{
					title : "下移",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveSeller/manage/down.jhtml",// url
						param : ["id"],// 参数
						permission : "down"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						if(data.zhiboType==1){
							value = "<a href='javascript:upOrDown(\""+data.id+"\",\"" + "down" + "\")'>" + "下移" + "</a>";
						}
			            return value;
				    }
				} ,
				{
					title : "预告设置",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveSeller/manage/setAdvance.jhtml",// url
						param : ["id"],// 参数
						permission : "setAdvance"// 列权限
					},
					customMethod : function(value, data){
						var value="";
				        if(data.zhiboType==0){
				            value = "<a href='javascript:setAdvance(\""+data.id+"\",\"" + -1 + "\")'>" + "取消预告" + "</a>";
				        }else if(data.zhiboType==-1){
				            value = "<a href='javascript:setAdvance(\""+data.id+"\",\"" + 0 + "\")'>" + "设为预告" + "</a>";
				        }
				        return value;
				    }
				 },
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveSeller/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete("+data.id+","+data.sequenceNo+")'>" + "删除" + "</a>";
				            return value;
				    }
				 },
		         {
					title : "修改",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "liveSeller/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改通告"
						},
						param : [ "id" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [ {
			title : "序号",
			name : "sequenceNo",
			type : "string",
			width : 150
		}, {
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		}, {
			title : "关注人数",
			name : "concernedNums",
			type : "string",
			width : 150
		}, {
			title : "主播",
			name : "nname",
			type : "string",
			width : 150
		}, {
			title : "直播时间",
			name : "planStartDateStr",
			type : "string",
			width : 150
		}, {
			title : "商家",
			name : "sellername",
			type : "string",
			width : 150
		} , {
			title : "地点",
			name : "zhiboAddress",
			type : "string",
			width : 150
		} , {
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
 * 删除操作
 */
 function confirmDelete(id,sequenceNo){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveSeller/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id+'&sequenceNo='+sequenceNo,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 recordList.reload();
					 }, 2000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 /**
  * 预告设置
  * @param id
  * @param zhiboType
  */
 function setAdvance(id,zhiboType){
	 $.ajax({
         url : "liveSeller/manage/setAdvance.jhtml",
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
 * 
 * @param id
 * @param operationType
 */
 function upOrDown(id,operationType){
	 $.ajax({
         url : "liveSeller/manage/upOrDown.jhtml",
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
	case 2:
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
 