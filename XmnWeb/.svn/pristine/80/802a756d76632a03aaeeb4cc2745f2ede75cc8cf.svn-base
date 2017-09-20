var brandList;
var pageSize=15;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	brandList = $('#brandList').page({
		url : 'fresh/brand/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:pageSize,
		paramForm : 'brandForm'
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
	
	inserTitle(' > 积分超市 > <a href="fresh/brand/init.jhtml" target="right">品牌管理</a>','brandList',true);
	
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个品牌&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchSupplierForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
			identifier : "id",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content,
			checkable : true,
			 //数据行
			cols:[{
				title : "品牌ID",// 标题
				name : "id",
				//sort : "up",
				width : 120,// 宽度
				type:"int",//数据类型		
		},{
			title : "品牌LOGO",// 标题
			name : "logo",
			//sort : "up",
			width : 220,// 宽度
			type:"int",//数据类型
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		}, {
			title : "品牌名",
			name : "name",
			type : "string",
			width : 150
		}, {
			title : "品牌分类",
			name : "typeName",
			type : "string",
			width : 150
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				width : 80,// 宽度
				queryPermission : ["edit","delete"],// 不需要选择checkbox处理的权限
				// 当前列的中元素
				cols : [{
					title : "编辑",// 标题
					linkInfoName : "modal",
					width : 20,
					linkInfo : {
						modal : {
							url : "fresh/brand/add/init.jhtml",
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
						href : "fresh/brand/delete.jhtml",// url
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
		$.post(basePath+"/fresh/brand/delete.jhtml",{"ids":id},function(data,status){
			if(status=="success"){
				showSmReslutWindow(data.success, data.msg);
				brandList.reload();
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
	var ids = brandList.getIds();
	if(!ids){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'fresh/brand/delete.jhtml' + '?t=' + Math.random(),
			data : 'ids=' + ids,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					brandList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"确定要删除吗？");
});

$("#export").click(function(){
	var data=$("#exportForm").serializeArray();
	var obj=new Object(); 
	obj.name='limit';
	obj.value=pageSize;
	data.push(obj);
	data = jsonFromt(data);
	$.post(basePath+"/fresh/brand/export/check.jhtml",data,function(data1,status){
		if(status=='success'){
			$('#prompt').hide();
			if (data1.success) {
				$('#triggerModal').modal('hide');
				myPost(basePath+"/fresh/brand/export.jhtml",data);
		    }else{
		    	showSmReslutWindow(data1.success, data1.msg);
		    }		
		}else{
			window.messager.warning(data1.msg);
		}
	});
});


