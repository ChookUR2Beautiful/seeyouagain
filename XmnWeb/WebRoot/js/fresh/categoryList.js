var categoryList;
$(document).ready(function() {
	categoryList = $('#categoryList').page({
		url : 'fresh/category/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchSupplierForm'
	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		minuteStep:1,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	
	inserTitle(' > 积分超市> <a href="fresh/category/init.jhtml" target="right">分类管理</a>','categoryList',true);
	
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchSupplierForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
			identifier : "id",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
			//数据
			data:data.content,
			checkable : true,
			 //数据行
			cols:[{ 
				title : "<div style='width: 80%;'>分类名称</div>",// 标题
				name : "name",
				//sort : "up",
				width : 120,// 宽度
				type:"string",//数据类型		
				customMethod : function(value, data){	
					if(data.fid==0){
						return "<div style='width: 80%;'>"+value+"</div>";
					}else{
						return "<div style='color:#3280FC;width: 100%;'>      "+value+"</div>";
					}
				}
		},{
			title : "排序",// 标题
			name : "sort",
			//sort : "up",
			width : 220,// 宽度
			type:"int",//数据类型
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["add","delete"],// 不需要选择checkbox处理的权限
				width : 80,// 宽度
				// 当前列的中元素
				cols : [{
					title : "编辑",// 标题
					linkInfoName : "modal",
					width : 20,
					linkInfo : {
						modal : {
							url : "fresh/category/add/init.jhtml",
							position : "100px",
							width : "600px"
						},
						param : ["id"],
						permission : "edit"
					}
				},{
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/category/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},customMethod : function(value, data){	
						return "<a href='javascript:;' onclick='delete_fun("+data.id+")'>删除</a>"
					}
				}] 
	}},permissions);
	
}

function delete_fun(id){
	showSmConfirmWindow(function (){
		
		$.post(basePath+"/fresh/category/delete.jhtml",{"ids":id},function(data,status){
			if(status=="success"){
				showSmReslutWindow(data.success, data.msg);
				categoryList.reload();
			}else{
				window.messager.warning(data.msg);
			}	
		})
	},"确定要删除吗？");
}

/**
 * 批量删除
 */
$('#delete').click(function(){
	var ids = categoryList.getIds();
	if(!ids){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'fresh/category/delete.jhtml' + '?t=' + Math.random(),
			data : 'ids=' + ids,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					categoryList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"确定要删除吗？");
});



