var giftList;
var initListUrl = "liveGift/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveGift/manage/init.jhtml" target="right">礼物管理</a>',
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
	giftList = $("#giftList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
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
function success(data, obj) {
	var formId = "searchForm", title = "礼物列表", subtitle = "个礼物";
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
				 },
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveGift/manage/delete.jhtml",// url
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
					linkInfoName : "href",
					linkInfo : {
						href:"liveGift/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
				}
			]
		},
		cols : [ {
			title : "礼物编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "礼物排序",
			name : "sortVal",
			type : "string",
			width : 150
		}, {
			title : "类型",
			name : "giftKind",
			type : "string",
			width : 150,
			customMethod:function(value,data){
				var result="";
				switch (value) {
				case 1:
					result="普通礼物";
					break;
				case 2:
					result="商品礼物";
					break;
				case 3:
					result="套餐礼物";
					break;
				default:
					result="普通礼物";
					break;
				}
				
				return result;
			}
		}, {
			title : "礼物名称",
			name : "giftName",
			type : "string",
			width : 180
		}, {
			title : "关联商品",// 标题
			name : "productNum",
			width : 150,// 宽度
			type:"string",//数据类型
			isLink : true,
			link : {
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "liveGift/manage/update/loadProductInfo/init.jhtml",
						position : "200px",
						width : "720px",	
						hight : "auto",
						title : "商品礼物" 
					}
				},
				param : ["id"],
				permission : "update",
			},
			customMethod : function(value, data) {
				if(data.productNum != null && data.productNum != ""){
					return $(value).html(data.productNum);
				}
				return "-";
			}
		}, {
			title : "关联套餐分类",
			name : "comboCategoryName",
			type : "string",
			width : 150
		}, {
			title : "礼物价格",
			name : "giftPriceStr",
			type : "string",
			width : 180
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
 * 删除操作
 */
 function confirmDelete(id,sequenceNo){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveGift/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id+'&sequenceNo='+sequenceNo,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 giftList.reload();
					 }, 2000);
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
         url : "liveGift/manage/setStatus.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"status":status},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				giftList.reload();
     			}, 1000);
     		} else {
     			window.messager.warning(result.msg);
     		}
         }
     });
 }
 
