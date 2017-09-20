var bargainProductList;
$(document).ready(function() {
	bargainProductList = $('#bargainProductList').page({
		url : 'marketingManagement/bargainProduct/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView:2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd'
	});

	inserTitle(' > <a href="marketingManagement/bargainProduct/init.jhtml" target="right"> 积分商品列表</a>','bargainProductSpan',true);	
});

$(function(){
	 //重置
   $("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});
});

function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个积分商品&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
	updateAddBtnHref("#addBto", "&"+callbackParam);
	obj.find('div').eq(0).scrollTablel({
	    	//checkable :false,
	    	identifier : "bpid",
	    	//tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
			//数据
			data:data.content,
			caption : captionInfo,
			 //数据行
			cols:[
			    {
					title : "商品编号",// 标题
					name : "bpid",
					width : 80,
					leng : 12,//显示长度
					type:"string",//数据类型
				},{
					title : "商家编号",// 标题
					//sort : "up",
					name : "sellerid",
					width : 120,
					leng : 12,//显示长度
					type:"stirng",//数据类型
				},{
					title : "商家名称",// 标题
					//sort : "up",
					name : "sellername",
					width : 240,
					leng : 50,//显示长度
					type:"stirng",//数据类型				
				},{
						title : "商家地址",// 标题
						name : "address",
						width : 260,
						leng : 50,//显示长度
						type:"stirng",//数据类型
				},{
					title : "积分商品名称",// 标题
					name : "pname",
					width : 240,
					leng : 50,//显示长度
					type:"stirng",//数据类型
				},{
					title : "状态",// 标题
					name : "status",
					width : 80,
					leng : 10,//显示长度
					type:"stirng",//数据类型
					customMethod : function(value, data) {
						var value ="-";
						if(data.status == 0){
							value = "下架";
						}
						if(data.status == 1 ){
							value = "上架";
						}
						if(data.status == 2){
							value = "审核中";
						}
						if(data.status == 3){
							value = "不通过";
						}
						return value;
					}
			    },{
					title : "原价",// 标题
					name : "originalprice",
				//	sort : "up",
					width : 120,
					leng : 10,//显示长度
					type:"number",//数据类型
				},{
					title : "积分价",// 标题
					name : "cash",
					width : 250,
					type:"string",//数据类型
					customMethod : function(value, data) {
						var value ="";
						if((data.cash == null || data.cash == undefined) && (data.integral == null || data.integral == undefined)){
							return "-";
						}
						if(data.cash == 0 && data.integral == 0){
							return "-";
						}
						if(data.cash != null && data.cash != undefined){
							value += data.cash + "元（人民币）";
						}
						if(data.integral != null && data.integral != undefined){
							if(data.cash != null && data.cash != undefined){
								value += "+";
							}
							value += data.integral + "个（积分）";
						}
						return value;
					}
				},{
					title : "结算价",// 标题
					name : "purchasePrice",
					//sort : "up",
					width : 80,
					leng : 10,//显示长度
					type:"number",//数据类型
				}
			],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["update"],// 不需要选择checkbox处理的权限
				width : 150,// 宽度
				cols : [
				{
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "marketingManagement/bargainProduct/update/init.jhtml",
						param : ["bpid"],// 参数
						permission : "update"// 列权限
					}
				},
				{
					title : "审核不通过",// 标题
					linkInfoName : "href",
					linkInfo : {
					href : "marketingManagement/bargainProduct/updateStatus/nopass.jhtml",// url
					param : ["bpid"],// 参数
					permission : "updateStatus"// 列权限
				    },
					customMethod : function(value, data){
						if(data.status==2){
							var value1 = "<a href=\"javascript:nopass('"+data.bpid+"')\">" + "不通过" + "</a>";
							return value1;
						}else{
							var value2 = '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
							return value2;
						}
					}
				},
				{
					title : "审核通过",// 标题
					linkInfoName : "href",
					linkInfo : {
					href : "marketingManagement/bargainProduct/updateStatus/putaway.jhtml",// url
					param : ["bpid"],// 参数
					permission : "updateStatus"// 列权限
				    },
					customMethod : function(value, data){
						if(data.status==2){
							var value1 = "<a href=\"javascript:putaway('"+data.bpid+"')\">" + "通过" + "</a>";
							return value1;
						}else{
							var value2 = '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
							return value2;
						}
					}
				}] 
	}},permissions);
}

function putaway(bpid){
	showSmConfirmWindow(function (){
		 $.ajax({
	         url : "marketingManagement/bargainProduct/updateStatus/putaway.jhtml",
	         type : "post",
	         dataType : "json",
	         data:'bpid=' + bpid,
	         success : function(result) {
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			setTimeout(function() {
	     				bargainProductList.reload();
	     			}, 3000);
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	 },"确定通过审核？");
}

function nopass(bpid){
	showSmConfirmWindow(function (){
		 $.ajax({
	         url : "marketingManagement/bargainProduct/updateStatus/nopass.jhtml",
	         type : "post",
	         dataType : "json",
	         data:'bpid=' + bpid,
	         success : function(result) {
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			setTimeout(function() {
	     				bargainProductList.reload();
	     			}, 3000);
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	 },"确定不通过审核？");
}

