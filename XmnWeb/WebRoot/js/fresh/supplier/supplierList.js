var supplierList;
$(document).ready(function() {
	supplierList = $('#supplierList').page({
		url : 'fresh/supplier/list.jhtml',
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
	
	inserTitle(' > 供应商管理 > <a href="fresh/supplier/init.jhtml" target="right">供应商详情</a>','supplierList',true);
	
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
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个供应商&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchSupplierForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
	    	//checkable :false,
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "供应商名称",// 标题
					name : "supplierName",
					//sort : "up",
					width : 120,// 宽度
					type:"string",//数据类型		
			},{
				title : "供应商联系人",// 标题
				name : "contacts",
				width : 120,// 宽度
				type:"string"//数据类型
				
			},{
				title : "供应商联系电话",// 标题
				name : "phone",
				//sort : "up",
				width : 120,// 宽度
				type:"string"//数据类型
				
			},{
				title : "供应商地址",// 标题
				name : "address",
				//sort : "up",
				width : 220,// 宽度
				type:"string"//数据类型
			},{
				title : "状态",// 标题
				name : "status",
				//sort : "up",
				width : 220,// 宽度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.status==0){
						return "正常";
					} 
					if(data.status==1){
						return "废弃";
					} 
					return "-";
				}
			}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["add","delete"],// 不需要选择checkbox处理的权限
				width : 80,// 宽度
				// 当前列的中元素
				cols : [{
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/supplier/delete.jhtml",// url
						param : ["supplierId"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
                        if((data.status==0)){
                            var value1 = "<a href='javascript:confirmDelete(\""+data.supplierId+"\")'>" + "删除" + "</a>";
                            return value1;
                        }else{
                        	var value2 = '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
							return value2;
                        }
                    }
				},
				{
					title : "编辑",// 标题
					linkInfoName : "modal",
					width : 20,
					linkInfo : {
						modal : {
							url : "fresh/supplier/edit/init.jhtml",
							position : "100px",
							width : "600px"
						},
						param : ["supplierId"],
						permission : "edit"
					}
				}] 
	}},permissions);
	
}

/**
 * 删除操作
 */
 function confirmDelete(supplierId){
	 var flag = true;
	 $.ajax({
         url : "fresh/supplier/checkSupplierId.jhtml",
         type : "post",
         dataType : "json",
         data:'supplierId=' + supplierId,
         async:false,
         success : function(data) {
        	 if(data != null){
        		 flag = false;
        	 }
         }
     });
	 if(!flag){
		 window.messager.warning("产品已存在，对应供应商不能删除!");
	 }else{
		 showSmConfirmWindow(function (){
			 $.ajax({
				 url : "fresh/supplier/delete.jhtml",
				 type : "post",
				 dataType : "json",
				 data:'supplierId=' + supplierId,
				 success : function(result) {
					 if (result.success) {
						 showSmReslutWindow(result.success, result.msg);
						 setTimeout(function() {
							 supplierList.reload();
						 }, 3000);
					 } else {
						 window.messager.warning(result.msg);
					 }
				 }
			 });
		 },"确定要删除吗？");
	 }
 }
 
//添加供应商信息
 function formSubmit(){
 	var form =$("body").find(".modal-body form");
 	var action = $(form).attr("action");
 	var method = $(form).attr("method");
 	$.ajax({
 		url : action + '?t=' + Math.random(),
 		type : method,
 		data :jsonFromt($(form).serializeArray()),
 		dataType : 'json',
 		cache:false
 	}).done(function ( data ) {
 		$('#triggerModal').modal('hide');
 		if(data.success){
 			supplierList.reload();
 		}
 		showSmReslutWindow(data.success, data.msg);
 	});
 	return false;
 }
