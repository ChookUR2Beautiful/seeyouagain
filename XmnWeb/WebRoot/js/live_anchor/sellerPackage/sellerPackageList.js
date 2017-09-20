var pageDiv;
$(document).ready(function() {
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	//标题
	inserTitle(' > 直播频道> <span><a href="sellerPackage/manage/init.jhtml" target="right">套餐管理</a>','sellerList',true);
	
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/sellerPackage/manage/init.jhtml';
			location.href =url;
		}
		setTimeout(function(){
			$("#ld").find("select").trigger("chosen:updated");
		});
	});
	
	initsellerId();//初始化商家信息
	
	pageDiv = $('#sellerPackageInfoDiv').page({
		url : 'sellerPackage/manage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'sellerPackageFromId'
	});
	
});


//初始化商家下拉框
function initsellerId(){
	$("#sellerId").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/seller/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"80%"
	});
}


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;全部商户套餐 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个套餐&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#sellerPackageFromId").serialize());
//	debugger;
	updateAddBtnHref("#addSellerPackageBto", callbackParam);

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data: data.content, 
		caption : captionInfo,
		checkable : true,
		//操作列
		handleCols : {
			title : "操作",// 标题
			handlePermission : ["sj","xj","tj"],//需要用到checkbox
			queryPermission : [ "xg","view" ],// 不需要选择checkbox处理的权限
			//width : 240,// 宽度
			// 当前列的中元素
			cols : [
			{
				title : "查看",// 标题
				linkInfoName : "href",
				width : 30,
				linkInfo : {
					href : "sellerPackage/manage/list/viewDetail.jhtml",
					param : ["id"],
					permission : "view"
				}
			},{
				title : "修改",// 标题
				linkInfoName : "href",
				width : 30,
				linkInfo : {
					href : "sellerPackage/manage/update/init.jhtml",
					param : ["id"],
					permission : "xg"
				}
			}] 
		},
		cols:[{
			title : "套餐",
			name : "title",
//			sort : "up",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				var type = data.highlyRecommended;
				var result;
				if(1 == type){ //是
					return data.title+ '&nbsp;<i class="icon icon-thumbs-up"></i>';  /*<span class="label label-info">重</span>*/
				}else{
					return data.title;
				}
				return result;
			}
		},{
			title : "关联商家",
			name : "sellername",
			type : "string",
			width : 150
		},{
			title : "状态",
			name : "statusDesc",
//			sort : "up",
			type : "string",
			width : 80
		},{
			title : "已售",
			name : "sales",
			type : "string",
			width : 80,
			leng : 8
		},{
			title : "剩余",
			name : "stock",
			type : "string",
			width : 80,
			leng : 8
		},{
			title : "价格",
			name : "sellingPrice",
			type : "string",
			width : 80,
			leng : 8
		},{
			title : "鸟币价",
			name : "sellingCoinPrice",
			type : "string",
			width : 80,
			leng : 8
		},{
			title : "销售时间",
			name : "saleStartTime",
			type : "string",
			leng : 8,
			customMethod : function(value, data) {
				var saleStartTime = data.saleStartTime?data.saleStartTime:'-';
				var saleEndTime = data.saleEndTime?data.saleEndTime:'-';
				return saleStartTime +'-'+saleEndTime;
			},
			width : 300
		},{
			title : "使用时间",
			name : "useStartTime",
			type : "string",
			isLink : false,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/sellerMarketing/init.jhtml"
				},
				param : ["sellerid","sellerGrade","isonline"],
				permission : "yx"
			},
			customMethod : function(value, data) {
//				return data.status == 3 ? $(value).html("营销信息") : "-";
				var useStartTime = data.useStartTime?data.useStartTime:'-';
				var useEndTime = data.useEndTime?data.useEndTime:'-';
				return useStartTime +'-'+useEndTime;
			},
			width : 300
		},{
			title : "赠送抵用券面值",
			name : "couponDesc",
			type : "string",
			/*customMethod : function(value, data) {
				return data.status == 3 ?  $(value).html("帐号") : "-";
			},*/
			width : 180
		}]
	},permissions);
	
}



function sellerPackageOption(flag, status){
	var ids = pageDiv.getIds();
	if(!pageDiv.getIds()){
		showWarningWindow("warning", "请至少选择一条套餐记录！");
		return;
	}
	/*if(flag == 1 && status == 1){
		if(!pageDiv.validateChose("status", "3, 1", "套餐已经售罄不能上架")){
			return;
		}
	}*/
	
	showSmConfirmWindow(function(){
		$.ajax({
			type:"POST",
			url:"sellerPackage/manage/beachOnLine/updateStatusOption.jhtml",
			data:{ids:ids,flag:flag,state:status},
			dataType:"json",
			success:function(data){
				if(data.success) {
					setTimeout(function() {
						pageDiv.reload();
					}, 1000);
				}
				
				showSmReslutWindow(data.success,data.msg);
			},
			error:function(data){
				showSmReslutWindow(data.success, data.msg);
			}
		});
		
	}, "确定修改吗？")
}


//导出
$("#beachOnLine").click(function(){
	sellerPackageOption(1, 1);
});	

//2.下架
$("#beachDownLine").click(function(){
	sellerPackageOption(1, 2);
});	

//重点推荐 0 否，1 是
$("#beachRecommended").click(function() {
	sellerPackageOption(2, 1);
})
