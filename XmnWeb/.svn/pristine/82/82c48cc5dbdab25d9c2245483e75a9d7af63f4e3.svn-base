var enrollList1;//实名审核
var imgRoot = $("#fastfdsHttp").val();

$(document).ready(function() {
	inserTitle('> 积分超市> <a href="fresh/mallPackage/init.jhtml" target="right">商品专区</a>','enrollConfirm',true);
	
	pageInit();
	
	//上架
	putaway();
	
	//下架
	removeOffshelf();
});

/**
 * 加载页面数据
 */
function pageInit(){
	enrollList1 = $('#enrollList1').page({
		url : 'fresh/mallPackage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 10,//每页条数
		paramForm : 'searchForm1',
		param :{}
	});
}


/**
 * 查询实名审核信息成功回调函数
 * @param data
 * @param obj
 */
function success(data, obj) {
	var base =new Base64();
	data= $.evalJSON(base.decode(data)); 
	enrollList1.mergeData(data,obj);
	enrollList1.initPagenum();
	var formId = "searchForm1", title = "专区列表", subtitle = "条信息";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		data : data.content,
		caption : captionInfo,
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update"],// 不需要选择checkbox处理的权限
			width : 120,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/mallPackage/edit/init.jhtml",// url
						param : ["id"],// 参数
						permission : "update"// 列权限
					}
				 }
			]
		},
		cols : [ {
			title : "排序",
			name : "sort",
			type : "string",
			width : 100
		},{
			title : "产品编号",
			name : "id",
			type : "string",
			width : 100
		},{
			title : "产品名称",
			name : "title",
			type : "string",
			width : 210
		},
		{
			title : "套餐产品",
			name : "zbalance",
			type : "string",
			width : 210,
			customMethod:function(value,data){
				return '<a style="cursor:pointer;"  data-type="ajax" data-url="fresh/mallPackage/init/list/product.jhtml?id='+data.id+'" data-toggle="modal" data-width="950px">关联商品</a>';
			}
		},
		{
			title : "产品原价",
			name : "originalPrice",
			type : "string",
			width : 210
		},{
			title : "鸟币售价",
			name : "price",
			type : "string",
			width : 210
		},{
			title : "状态",
			name : "status",
			type : "string",
			width : 100,
			customMethod:function(value,data){
				return data.statusStr;
			}
		} ]
	}, permissions);
}



/**
 * 批量上线
 */	
function putaway(){
	$("#putaway").click(function(){
		console.log(enrollList1.getIds());
		if(!enrollList1.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		var data = {ids:enrollList1.getIds(),status:'1'};
		updateBatch(data,"你确定要上线选中信息？");
	});
}

/**
 * 批量下架
 */	
function removeOffshelf(){
	$("#removeOffshelf").click(function(){
		console.log(enrollList1.getIds());
		if(!enrollList1.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		if(!enrollList1.validateChose("status", "1", "已下线的商品不可执行此操作")){
			return;
		}
		var data = {ids:enrollList1.getIds(),status:'3'};
		updateBatch(data,"你确定要下线选中信息？");
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
				        url: "fresh/mallPackage/edit/statusBatch.jhtml",
				        data: data,
				        dataType: "json",
				        success: function(result){
							showSmReslutWindow(result.success, result.msg);
							enrollList1.reload();
				         }
				    });
			},title);
}

