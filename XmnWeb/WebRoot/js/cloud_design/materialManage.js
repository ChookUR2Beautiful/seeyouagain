var materialList;
var initListUrl = "materials/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 雏鸟云设计 > <a href="materials/manage/init.jhtml" target="right">商品管理</a>',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//上线
	putaway();
	
	//下线
	removeOffshelf();
	
	//导出
	$("#export").click(function(){
		var path="materials/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

/**
 * 加载页面数据
 */
function pageInit(){
	materialList = $("#materialList").page({
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
	var formId = "searchForm", title = "商品列表", subtitle = "个商品";
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
			width : 100,// 宽度
			// 当前列的中元素
			cols : [ 
		         {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "materials/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
				 } ,
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "materials/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete2"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
				            return value;
				    }
				 }
			]
		},
		cols : [  {
			title : "商品",
			name : "picUrl",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				var html='';
				html+='<div class="good-table-imgmodule">'
					+    '<img src="'+imgRoot+value+'"  class="good-table-img" style=""/>'
		            +    '<div class="good-table-img-title">'+data.title+'</div>'
		            +    '<div class="good-table-img-price">'
		            +    '   <span>原价：<em>￥'+ data.basePrice +'</em> </span>' 
//		            +    '   <del>原价：￥'+ data.basePrice +'</del>' 
		            +    '</div>'
		            +'</div>';
				return html;
			}
		}, {
			title : "商品编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "月销量",
			name : "monthlySales",
			type : "string",
			width : 150
		},{
			title : "创建时间",
			name : "createTimeStr",
			type : "string",
			width : 150
		} ,{
			title : "状态",
			name : "statusStr",
			type : "string",
			width : 150
		} ]
	}, permissions);
}


/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "materials/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{'id':id},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 materialList.reload();
					 }, 2000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 
 /**
  * 批量上线
  */	
 function putaway(){
 	$("#putaway").click(function(){
 		console.log(materialList.getIds());
 		if(!materialList.getIds()){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!materialList.validateChose("status", "002", "商品已上线不能再次上线")){
 			return;
 		}
 		var data = {ids:materialList.getIds(),status:'001'};
 		updateBatch(data,"你确定要上线选中产品？");
 	});
 }
 
 /**
  * 批量下线
  */	
 function removeOffshelf(){
 	$("#removeOffshelf").click(function(){
 		console.log(materialList.getIds());
 		if(!materialList.getIds()){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!materialList.validateChose("status", "001", "商品已下线不能再次下线")){
 			return;
 		}
 		var data = {ids:materialList.getIds(),status:'002'};
 		updateBatch(data,"你确定要下线选中产品？");
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
 				        url: "materials/manage/updateBatch.jhtml",
 				        data: data,
 				        dataType: "json",
 				        success: function(result){
 							showSmReslutWindow(result.success, result.msg);
 							materialList.reload();
 				         }
 				    });
 			},title);
 }
