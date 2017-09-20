var recordList;
var initListUrl = "liveFansRank/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 打赏分红 > <a href="liveFansRank/manage/init.jhtml" target="right">粉丝级别管理</a> >粉丝级别',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//上线
	putaway();
	
	//下线
	removeOffshelf();
	
	//导出
	$("#export").click(function(){
		var path="liveFansRank/manage/export.jhtml";
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
	var formId = "shareForm", title = "粉丝级别列表", subtitle = "个级别";
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
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
		         {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveFansRank/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [ {
			title : "类型",
			name : "rankType",
			type : "string",
			width : 150,
			customMethod:function(value,date){
				var result="";
				if(value=="1"){
					result="壕赚等级";
				}else{
					result="V客等级";
				}
				return result;
			}
		}, {
			title : "级别",
			name : "rankNo",
			type : "string",
			width : 180
		}, {
			title : "级别名称",
			name : "rankName",
			type : "string",
			width : 180
		},/* {
			title : "充值金额",
			name : "recharges",
			type : "string",
			width : 180
		},*/ {
			title : "升级条件",
			name : "upgradeCondition",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				var upgradeCondition='';
				if(data.rewardHighest==null){
					upgradeCondition=data.rewardLowest +'+';
				}else{
					upgradeCondition=data.rewardLowest +'-'+data.rewardHighest;
				}
				return upgradeCondition;
			}
		}, {
			title : "返还模式",
			name : "rankDetailNum",
			type : "string",
			width : 200,
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "liveRankRestitution/manage/init.jhtml"
				},
				param : ["id"],
				permission : "update"
			},
			customMethod : function(value, data) {
				if(undefined==data.rankDetailNum){
					return "-";
				}else{
					var result;
					try{
						//$(value).html(data.recordNum)[0].outerHTML;
						result = $(value).length>0?$(value).html(data.rankDetailNum)[0].outerHTML:"-";
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		}/*, {
			title : "推荐充值送鸟币",
			name : "referrerCoins",
			type : "string",
			width : 180
		} , {
			title : "打赏送鸟币",
			name : "sendBean",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result='';
				switch (value) {
				case '001':
					result='否';
					break;
				case '002':
					result='是';
					break;
				default:
					break;
				}
				return result;
			}
		}, {
			title : "推荐奖励",
			name : "referrerReward",
			type : "string",
			width : 180
		}, {
			title : "鸟币消费抵消",
			name : "consumeRatio",
			type : "string",
			width : 180
		} */  ]
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
 			url : 'liveFansRank/manage/delete.jhtml' + '?t=' + Math.random(),
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
 				        url: "liveFansRank/manage/updateBatch.jhtml",
 				        data: data,
 				        dataType: "json",
 				        success: function(result){
 							showSmReslutWindow(result.success, result.msg);
 							recordList.reload();
 				         }
 				    });
 			},title);
 }
 