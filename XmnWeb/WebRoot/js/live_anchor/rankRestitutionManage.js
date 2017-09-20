var recordList;
var initListUrl = "liveRankRestitution/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 打赏分红 > <a href="liveRankRestitution/manage/init.jhtml" target="right">等级返还管理</a>',
			'userSpan', true);
	
	//加载关联等级
	initRankId();
	
	//加载页面数据
	pageInit();
	
	//上线
	putaway();
	
	//下线
	removeOffshelf();
	
	//导出
	$("#export").click(function(){
		var path="liveRankRestitution/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

/**
 * 加载页面数据
 */
function pageInit(){
	recordList = $("#recordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
}

function success(data, obj) {
	var formId = "shareForm", title = "级别返还模式列表", subtitle = "个模式";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : false,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 100,// 宽度
			// 当前列的中元素
			cols : [ 
		         {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRankRestitution/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [ {
			title : "等级",
			name : "rankName",
			type : "string",
			width : 150
		},{
			title : "类型",
			name : "objectOrientedVal",
			type : "string",
			width : 150,
			customMethod:function (value,data){
				var result=value;
				try {
					if (data.objectOriented == 2) {
						result += "<br/>" + data.conversionRate;
					}
				} catch (e) {
					console.log(e);
				}
				return result;
			}
		}, {
			title : "生效日期",
			name : "effectiveDateStr",
			type : "string",
			width : 150
		},{
			title:"奖励类型",
			name:"referrerLedgerType",
			type:"string",
			width:150,
			customMethod:function(value,data){
				var result = "";
				if(value==0){
					result="鸟币";
				}else if(value==1){
					result="余额";
				}
				return result;
			}
		}, {
			title : "直属推荐人",
			name : "referrerRatio",
			type : "string",
			width : 150
		}, {
			title : "直属推荐人上级",
			name : "parentReferrerRatio",
			type : "string",
			width : 150
		}, {
			title : "推荐奖励倍数",
			name : "referrerReward",
			type : "string",
			width : 150
		}, {
			title : "内购余额红包比例",
			name : "privateRedPacketCashRatio",
			type : "string",
			width : 150
		}, {
			title : "内购鸟币红包比例",
			name : "privateRedPacketCoinRatio",
			type : "string",
			width : 150
		}, {
			title : "内购打赏鸟豆区间",
			name : "privateConsumeZone",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				if(undefined==data.privateConsumeZone){
					return "-";
				}else{
					var result;
					try{
						result=data.privateConsumeZone.split(',').join('<br>');
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		}, {
			title : "内购鸟币红包返还比例(%)",
			name : "privateCoinZone",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				if(undefined==data.privateCoinZone){
					return "-";
				}else{
					var result;
					try{
						result=data.privateCoinZone.split(',').join('<br>');
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		}, {
			title : "内购余额红包返还比例(%)",
			name : "privateCashZone",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				if(undefined==data.privateCashZone){
					return "-";
				}else{
					var result;
					try{
						result=data.privateCashZone.split(',').join('<br>');
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		}  , {
			title : "外购余额红包比例",
			name : "publicRedPacketCashRatio",
			type : "string",
			width : 180
		}  , {
			title : "外购鸟币红包比例",
			name : "publicRedPacketCoinRatio",
			type : "string",
			width : 150
		}, {
			title : "外购打赏鸟豆区间",
			name : "publicConsumeZone",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				if(undefined==data.publicConsumeZone){
					return "-";
				}else{
					var result;
					try{
						result=data.publicConsumeZone.split(',').join('<br>');
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		}, {
			title : "外购鸟币红包返还比例(%)",
			name : "publicCoinZone",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				if(undefined==data.publicCoinZone){
					return "-";
				}else{
					var result;
					try{
						result=data.publicCoinZone.split(',').join('<br>');
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		}, {
			title : "外购余额红包返还比例(%)",
			name : "publicCashZone",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				if(undefined==data.publicCashZone){
					return "-";
				}else{
					var result;
					try{
						result=data.publicCashZone.split(',').join('<br>');
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		}  ]
	}, permissions);
}


 /**
  * 直播日期控件初始化
  */
 function liveDateInit(){
	 $('.form_datetime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd'
		});
 }
 
 
 /**
  * 批量删除
  */
 $('#delete').click(function(){
 	var ids = recordList.getIds();
 	if(!ids){
 		showWarningWindow("warning","请至少选择一条记录！");
 		return;
 	}
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'liveRankRestitution/manage/delete.jhtml' + '?t=' + Math.random(),
 			data : 'ids=' + ids,
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					recordList.reset();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	});
 });
 
 
 /**
  * 批量上线
  */	
 function putaway(){
 	$("#putaway").click(function(){
 		console.log(recordList.getIds());
 		if(!recordList.getIds()){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!recordList.validateChose("status", "002", "视频已上线不能再次上线")){
 			return;
 		}
 		var data = {ids:recordList.getIds(),status:'001'};
 		updateBatch(data,"你确定要上线选中视频？");
 	});
 }
 
 /**
  * 批量下线
  */	
 function removeOffshelf(){
 	$("#removeOffshelf").click(function(){
 		console.log(recordList.getIds());
 		if(!recordList.getIds()){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!recordList.validateChose("status", "001", "视频已下线不能再次下线")){
 			return;
 		}
 		var data = {ids:recordList.getIds(),status:'002'};
 		updateBatch(data,"你确定要下线选中视频？");
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
 				        url: "liveRankRestitution/manage/updateBatch.jhtml",
 				        data: data,
 				        dataType: "json",
 				        success: function(result){
 							showSmReslutWindow(result.success, result.msg);
 							recordList.reload();
 				         }
 				    });
 			},title);
 }
 
 
//初始化关联等级下拉框
 function initRankId(){
// 	debugger;
 	$("#rankId").chosenObject({
 		hideValue : "id",
 		showValue : "rankName",
 		url : "liveFansRank/manage/getFansRanks.jhtml",
 		isChosen:true,//是否支持模糊查询
 		isCode:true,//是否显示编号
 		isHistorical:false,//是否使用历史已加载数据
 		width:"100%"
 	});
 }
 