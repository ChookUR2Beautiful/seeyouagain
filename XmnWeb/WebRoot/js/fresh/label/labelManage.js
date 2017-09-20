var labelList;
var normalLabelList;
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(' > 积分超市管理 > <a href="freshLabel/manage/init.jhtml" target="right">商品标签管理</a>',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/freshLabel/manage/init.jhtml';
			location.href =url;
		}
		setTimeout(function(){
			$("#ld").find("select").trigger("chosen:updated");
		});
	});

});

/**
 * 加载页面数据
 */
function pageInit(){
	var initListUrl = "freshLabel/manage/init/list.jhtml";
	labelList = $("#labelList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
//		paramForm : 'searchForm',
		param : {
			type : "1"
		}
	});
	var normalLabelListUrl = "freshLabel/manage/init/list.jhtml";
	normalLabelList = $("#normalLabelList").page({
		url : normalLabelListUrl,
		success : normalLabelSuccess,
		pageBtnNum : 10,
//		paramForm : 'giftBagSetForm',
		param : {
			type : "0"
		}
	});
	
}

/**
 * 查询礼物成功回调函数
 * @param data
 * @param obj
 */
function success(data, obj) {
	var formId = "shareForm", title = "活动标签列表", subtitle = "活动标签";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+ data.total+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());
//	updateAddBtnHref("#addFreshLabelBto", callbackParam);

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
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "freshLabel/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
				            return value;
				    }
				 },
		         {
					title : "修改",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "freshLabel/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改礼物"
						},
						param : [ "id" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [ {
			title : "序号",
			name : "rowNo",
			type : "string",
			width : 150
		}, {
			title : "标签名",
			name : "labelName",
			type : "string",
			width : 180
		}, {
			title : "标签图标",
			name : "picUrl",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		} ]
	}, permissions);
}



/**
 * 查询礼包礼物成功回调函数
 * @param data
 * @param obj
 */
function normalLabelSuccess(data, obj) {
	var formId = "shareForm", title = "常规标签列表", subtitle = "个常规标签";
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
			queryPermission : [ "update","updateGiftBagSet" ],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "状态设置",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "freshLabel/manage/setStatus.jhtml",// url
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
				 },
				 {
					title : "修改",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "freshLabel/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改礼物"
						},
						param : [ "id" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [ {
			title : "序号",
			name : "rowNo",
			type : "string",
			width : 150
		}, {
			title : "标签名",
			name : "labelName",
			type : "string",
			width : 180
		}, {
			title : "标签图标",
			name : "picUrl",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		} , {
			title : "标签状态",
			name : "status",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				switch (value) {
				case 1:
					result = "已启用";
					break;
				default:
					result = "已停用";
					break;
				}
				return result;
			}
		} ]
	}, permissions);
}


/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "freshLabel/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				if (result.success) {
					$('#prompt').hide();
					showSmReslutWindow(result.success, result.msg);
					setPageActive(true);
					setTimeout(function() {
						labelList.reload();
					}, 1000);
				} else {
					window.messager.warning(result.msg);
				}
			}
		 });
	 },"确定要删除吗？");
 }
 
 
/**
 * 礼物状态设置
 * @param id
 * @param status
 */
 function setStatus(id,status){
	 $.ajax({
         url : "freshLabel/manage/setStatus.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"status":status},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setPageActive(false);
     			setTimeout(function() {
     				normalLabelList.reload();
     			}, 1000);
     		} else {
     			window.messager.warning(result.msg);
     		}
         }
     });
 }
 
 function setPageActive(active){
	 active == true ? ($("#tab2").removeClass("in active"), $("#tab1").addClass("in active")): ($("#tab1").removeClass("in active") , $("#tab2").addClass("in active"));
 }