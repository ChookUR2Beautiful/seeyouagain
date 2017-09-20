var pageDiv;
$(document).ready(function() {

	pageDiv = $('#redPackageDiv').page({
		url : 'businessman/redPackage/init/detail.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:10,
		paramForm : 'redPackageForm'
	});
	
	inserTitle(' > 商户会员红包 > <a href="businessman/redPackage/init.jhtml" target="right">所有红包</a> > 领取明细','redPackageDiv',true);
	
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
					name : "userId",
					// sort : "up",
					width : 200,// 宽度
					leng : 200,// 显示长度
					type:"string",// 数据类型
					customMethod : function(value, data) {
						return value+" ("+data.phone+")";
					}
			},{
				title : "领取金额",// 标题
				name : "denomination",
				width : 250,// 宽度
				type:"string"// 数据类型
				
		},{
			title : "领取时间",// 标题
			name : "recordTimeStr",
			// sort : "up",
			width : 160,// 宽度
			type:"string"// 数据类型
		},
		{
			title : "是否绑定商户",// 标题
			name : "isBanding",
			// sort : "up",
			width : 100,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data) {
				if(value == 0){
					return "未绑定";
				}else if(value == 1){
					return "已绑定";
				}else{
					return "-";
				}
			}
		},{
			title : "是否分享",// 标题
			name : "isShare",
			// sort : "up",
			width : 120,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data) {
				if(value == 0){
					return "未分享";
				}else if(value == 1){
					return "已分享";
				}else{
					return "-";
				}
			}
		},{
			title : "到账状态",// 标题
			name : "status",
			// sort : "up",
			width : 120,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data) {
				if(value == 0){
					return "未到账";
				}else if(value == 1){
					return "已到账";
				}else if(value == 2){
					return "失败";
				}else{
					return "-";
				}
			}
		}
		]},permissions);
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
