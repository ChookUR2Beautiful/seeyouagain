var pageDiv;
$(document).ready(function() {

	pageDiv = $('#roulleteRecordDiv').page({
		url : 'businessman/activity/init/list/roullete/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:10,
		paramForm : 'roulleteRecordForm'
	});
	
	inserTitle(' > 商户活动管理> <a href="businessman/activity/init.jhtml" target="right">所有活动</a> > 轮盘抽奖详情','pageDiv',true);
	
	$("input[data-bus=reset]").click(function(){
		$(".form-control").attr("value","");
		$("#ld").find("select").trigger("chosen:updated");
	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView: 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd'
	});


});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条记录&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			// 数据
			data:data.content, 
			 // 数据行
			cols:[{
					title : "领取会员",// 标题
					name : "uid",
					// sort : "up",
					width : 200,// 宽度
					leng : 200,// 显示长度
					type:"string",// 数据类型
					customMethod : function(value, data) {
						return value+"("+data.phone+")";
					}
			},{
				title : "尝新奖品",// 标题
				name : "cname2",
				width : 250,// 宽度
				type:"string",// 数据类型
				customMethod : function(value, data) {
					if(data.cname != undefined){
						return data.cname;
					}else{
						return "未中奖";
					}
				}
		},{
			title : "获得时间",// 标题
			name : "getTime",
			// sort : "up",
			width : 160,// 宽度
			type:"string"// 数据类型
		},{
			title : "奖品状态",// 标题
			name : "useStatus",
			// sort : "up",
			width : 120,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data) {
				if(value == 0){
					return "未使用";
				}else if(value == 1){
					return "锁定";
				}else if(value == 2){
					return "已使用"
				}else{
					return "-"
				}
			}
		},{
			title : "使用时间",// 标题
			name : "useTime",
			// sort : "up",
			width : 250,// 宽度
			type:"string"// 数据类型
			}
		]},permissions);
}

/**
 * 终止进行中的红包活动
 */
 function shutDown(id){
	 showSmConfirmWindow(function(){
		 $.ajax({
 			type : 'post',
 			url : 'businessman/fullReduction/shutdown.jhtml' + '?t=' + Math.random(),
 			data :{'id':id},
 			dataType : 'json',
 			success : function(data){
 				if (data.success) {
						setTimeout(function(){
							pageDiv.reload();
		        		}, 1000);
				    }			
					showSmReslutWindow(data.success, data.msg);
 			},
 			error : function() {
 				window.messager.warning(data.msg);
 			}
 		});
	 },"确定终止吗？")
}
 
/**
 * 转换from表单
 */
function jsonFromt(data){
	var json = {};
	for(var i=0; i<data.length; i++){
		json[data[i].name] = data[i].value;
	}
	return json;
}
