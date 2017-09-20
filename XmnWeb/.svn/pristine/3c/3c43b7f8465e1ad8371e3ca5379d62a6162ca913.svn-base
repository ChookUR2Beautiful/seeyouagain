var supplierList;
$(document).ready(function() {
	supplierList = $('#supplierList').page({
		url : 'supplier/manager/init/list.jhtml',
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
	
	inserTitle(' > 供应商管理 > <a href="supplier/manager/init.jhtml" target="right">供应商列表</a>','supplierList',true);
	
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
				title : "ID",// 标题
				name : "supplierId",
				//sort : "up",
				width : 120,// 宽度
				type:"string",//数据类型		
		},{
			title : "类型",// 标题
			name : "type",
			//sort : "up",
			width : 220,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data) {
				if(data.type==1){
					return "供应商";
				} 
				if(data.type==2){
					return "设计单位";
				}
				if(data.type==3){
					return "个人"
				}
				return "-";
			}
		},{
					title : "单位",// 标题
					name : "name",
					//sort : "up",
					width : 120,// 宽度
					type:"string",//数据类型		
			},{
				title : "负责人",// 标题
				name : "contacts",
				width : 120,// 宽度
				type:"string"//数据类型
				
			}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["edit","delete"],// 不需要选择checkbox处理的权限
				width : 80,// 宽度
				// 当前列的中元素
				cols : [{
					title : "编辑",// 标题
					linkInfoName : "href",
					width : 20,
					linkInfo : {
						href: "supplier/manager/edit/init.jhtml",
						param : ["supplierId"],
						permission : "edit"
					}
				},{
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "supplier/manager/delete.jhtml",// url
						param : ["supplierId"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
                        if((data.status==0)){
                            var value1 = "<a href='javascript:confirmDelete(\""+data.supplierId+"\","+data.type+")'>" + "删除" + "</a>";
                            return value1;
                        }else{
                        	var value2 = '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
							return value2;
                        }
                    }
				}] 
	}},permissions);
	
}

/**
 * 删除操作
 */
 function confirmDelete(supplierId,type){
	 var flag = true;
	 $.ajax({
         url : "supplier/manager/delete/checkData.jhtml?supplierId="+supplierId+"&type="+type+ "&t=" + Math.random(),
         type : "post",
         dataType : "json",
         data:[supplierId,type],
         async:false,
         success : function(data) {
        	 if(!data.success){
        		 flag = false;
        	 }
         }
     });
	 if(!flag){
		 window.messager.warning("该供应商/设计师有正在进行的业务，不可删除！");
	 }else{
		 showSmConfirmWindow(function (){
			 $.ajax({
				 url : "supplier/manager/delete.jhtml",
				 type : "post",
				 dataType : "json",
				 data:'supplierId=' + supplierId,
				 success : function(result) {
					 if (result.success) {
						showSmReslutWindow(result.success, result.msg);
//							location.href =contextPath + "/supplier/manager/init.jhtml";
//				 			supplierList.reload();
							history.go(0);
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
