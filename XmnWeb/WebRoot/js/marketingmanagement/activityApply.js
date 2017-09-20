var sellerApplyList,customizeApplyList;
var imgRoot = $("#fastfdsHttp").val();
var parm =[];
$(document).ready(function() {
	sellerApplyList = $('#activityApplyList').page({
		url : 'marketingManagement/activityApply/init/list.jhtml',
		success : platformSuccess,
		pageBtnNum : 15,
		paramForm : 'platformForm'
	});
	customizeApplyList = $('#customizeApplyList').page({
		url : 'marketingManagement/activityApply/init/list.jhtml',
		success : customizeSuccess,
		pageBtnNum : 15,
		paramForm : 'customizeForm'
	});
	/**
	 * 批量通过
	 */
	$('#passId').click(function() {
		if(!sellerApplyList.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		pass(sellerApplyList.getIds(),1,1);
	});
	
	/**
	 * 批量不通过
	 */
	$('#notPassId').click(function(){
		var ids = sellerApplyList.getIds();
		if(!ids){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		$('#reason').val("");
		$('#callType').val("1");
		$('#refuseForm>#ids').val(ids);
		$('#refuseModal').modal();
	});
	/**
	 * 定制活动批量通过
	 */
	$('#passIdCustomize').click(function() {
		if(!customizeApplyList.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		pass(customizeApplyList.getIds(),2,1);
	});
	
	/**
	 * 定制活动批量不通过
	 */
	$('#notPassIdCustomize').click(function(){
		var ids = customizeApplyList.getIds();
		if(!ids){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		$('#reason').val("");
		$('#callType').val("2");
		$('#refuseForm>#ids').val(ids);
		$('#refuseModal').modal();
	});
	
	/*
	 * 确认拒绝
	 */
	$("#ensure").click(function() {
		$('#refuseModal').modal('hide');
		pass($('#refuseForm>#ids').val(),$("#callType").val(),2,$("#reason").val());

	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		minView :2,
		format : 'yyyy-mm-dd'
	});
	inserTitle(' > 营销活动管理  > <a href="marketingManagement/activityApply/init.jhtml" target="right">活动申请</a>','activityApplySpan',true);

	$("#export").click(function(){
		$form = $("#platformForm").attr("action","marketingManagement/activityApply/export.jhtml");
		$form[0].submit();
	});
	$("#exportCustomize").click(function(){
		$form = $("#platformForm").attr("action","marketingManagement/activityApply/export.jhtml");
		$form[0].submit();
	});
	
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + 'marketingManagement/activityApply/init.jhtml';
			location.href =url;
		}
	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function platformSuccess(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;平台活动申请&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#platformForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
	    	checkable :true,
	    	identifier : "id",
	    	checkdisenable : "disableCheck",//check复选框依据checkdisenable : "disableCheck" 对应的值来设置为是否禁止可选
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "申请编号",// 标题
					name : "id",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"number",//数据类型					
			},{
				title : "商家编号",// 标题
				name : "sellerid",
				width : 100,// 宽度
				type:"string"//数据类型
				
			},{
				title : "商家名称",// 标题
				name : "sellername",
				width : 200,// 宽度
				type:"string"//数据类型
			},{
				title : "活动ID",// 标题
				name : "activityId",
				width : 100,// 宽度
				type:"number",//数据类型
				isLink : true,
				link : {
					required : true,
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "marketingManagement/activityApply/init/activity.jhtml",
							position : "60px",
							width : "auto",	
							hight : "auto",
							title : "活动详情" 
						}
					},
					param : ["activityId"],
					permission : "view",
				}
			},{
				title : "活动名称",// 标题
				name : "activityName",
				width : 200,// 宽度
				type:"string",//数据类型
			},{
				title : "活动类型",// 标题
				name : "type",
				width : 120,// 宽度
				type:"number",//数据类型
				customMethod : function(value, data) {
					if(data.type==1){
						return "平台活动";
					} 
					if(data.type==2){
						return "自定义活动";
					} 
					return "-";
				}
			},{
				title : "联系方式",// 标题
				name : "phone",
				width : 120,// 宽度
				type:"string"//数据类型
				
			},{
				title : "审核状态",// 标题
				name : "status",
				width : 120,// 宽度
				type:"number",//数据类型
				customMethod : function(value, data) {
					if(data.status==0){
						return "待审核";
					} 
					if(data.status==1){
						return "审核通过";
					} 
					if(data.status==2){
						return "审核不通过";
					} 
					return "-";
				}
				
			},{
				title : "申请时间",// 标题
				name : "sdate",
				width : 160,// 宽度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.sdate != null){
						return new Date(data.sdate).format("yyyy-MM-dd hh:mm:ss");
					} 
					return "-";
				}
			},{
				title : "处理时间",// 标题
				name : "edate",
				width : 160,// 宽度
				leng : 8,//显示长度
				type:"number",//数据类型
				customMethod : function(value, data) {
					if(data.edate != null){
						return new Date(data.edate).format("yyyy-MM-dd hh:mm:ss");
					} 
					return "-";
				}
			},{
				title : "未通过原因",// 标题
				name : "reason",
				width : 200,// 宽度
				type:"number"//数据类型
				
			}],
			//操作列
			/*handleCols : {
				title : "操作",// 标题
				queryPermission : ["view","update","supporSeller"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
					title : "通过",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "marketingManagement/activityApply/censor.jhtml?status=1",// url
							position:"60px",// 模态框显示位置
							width:"800px",
							title : "商家会员卡详情" 	
						},
						param : ["id"],// 参数
						permission : "supporSeller"// 列权限
					}
				},{
					title : "拒绝",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "marketingManagement/activityApply/censor.jhtml?status=2",// url
						param : ["id"],// 参数
						permission : "supporSeller"// 列权限
					}
				}] 
	}*/},permissions);
}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function customizeSuccess(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;自定义活动申请&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#customizeForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
//	    	checkable :true,
//	    	identifier : "id",
//	    	checkdisenable : "disableCheck",//check复选框依据checkdisenable : "disableCheck" 对应的值来设置为是否禁止可选
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["view","update","supporSeller"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
					title : "查看",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "marketingManagement/activityApply/init/viewCustomizeApply.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px",
							title : "商户定制活动申请详情" 	
						},
						param : ["id"],// 参数
						permission : "view"// 列权限
					}
				}
			]} ,
			 //数据行
			cols:[{
					title : "申请编号",// 标题
					name : "id",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"number",//数据类型					
			},{
				title : "商家编号",// 标题
				name : "sellerid",
				width : 100,// 宽度
				type:"string"//数据类型
				
			},{
				title : "商家名称",// 标题
				name : "sellername",
				width : 200,// 宽度
				type:"string"//数据类型
			},{
				title : "活动内容",// 标题
				name : "eescription",
				width : 300,// 宽度
				type:"string",//数据类型
			},{
				title : "联系方式",// 标题
				name : "phone",
				width : 120,// 宽度
				type:"string"//数据类型
				
			},{
				title : "回复状态",// 标题
				name : "status",
				width : 120,// 宽度
				type:"number",//数据类型
				customMethod : function(value, data) {
					if(data.status==0){
						return "待回复";
					} 
					if(data.status==1){
						return "已回复";
					} 
					return "-";
				}
				
			},{
				title : "申请时间",// 标题
				name : "sdate",
				width : 160,// 宽度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.sdate != null){
						return new Date(data.sdate).format("yyyy-MM-dd hh:mm:ss");
					} 
					return "-";
				}
			},{
				title : "处理时间",// 标题
				name : "edate",
				width : 160,// 宽度
				leng : 8,//显示长度
				type:"number",//数据类型
				customMethod : function(value, data) {
					if(data.edate != null){
						return new Date(data.edate).format("yyyy-MM-dd hh:mm:ss");
					} 
					return "-";
				}
			},{
				title : "平台回复",// 标题
				name : "reason",
				width : 200,// 宽度
				type:"number"//数据类型
				
			}],
			},permissions);
}
/**
 * 批量通过
 */
function pass(id,type,status,reason) {
	/*if(!id){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}*/
	parm[0]=id;
	parm[1]=type;
	parm[2]=status;
	parm[3]=reason;
	if(status==2){
		dealApply();
	}else{
		//showSmConfirmWindow(dealApply(id,status,reason),"你确定执行批量通过？");
		showSmConfirmWindow(dealApply,"你确定执行批量通过？");
	}
	
}
var dealApply = function() {
	$.ajax({
		type : 'post',
		url : 'marketingManagement/activityApply/censor.jhtml',
		data : {"ids":parm[0],"status":parm[2],"reason":parm[3]},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();

			if (data.success) {
				if(parm[1]==2){
					customizeApplyList.reload();
				}else{
					sellerApplyList.reload();
				}
				
			}

			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
		}
	});
}