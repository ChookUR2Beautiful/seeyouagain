var comboList;
var initListUrl = "liveRechargeCombo/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveRechargeCombo/manage/init.jhtml" target="right">充值管理</a>',
			'userSpan', true);
	comboList = $("#comboList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	//加载关联等级
	initRankId();
	
	$('select.chosen-select').chosen({
	    no_results_text: '没有找到',    // 当检索时没有找到匹配项时显示的提示文本
	    disable_search_threshold: 2, // 10 个以下的选择项则不显示检索框
	    search_contains: true         // 从任意位置开始检索
	}).on('change', function(){
	    var arr=$(this).val();
	    if(arr!=null){
	    	$("#oriented").val(arr.toString());
	    }else{
	    	$("#oriented").val('');
	    }
	});
	
	$("input[data-bus=reset]").click(function(){
		$("#objectOriented").val("");
		$("select.chosen-select").trigger("chosen:updated");
	});	
	
});

function success(data, obj) {
	var formId = "shareForm", title = "充值列表", subtitle = "个套餐";
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
	    checkable : $("#checkable").val(),
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","detail","delete" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
		         {
					title : "修改",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "liveRechargeCombo/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改充值套餐"
						},
						param : [ "id" ],
						permission : "update"
					}
				} 
			]
		},
		cols : [ {
			title : "充值套餐编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "充值类型",
			name : "rechargeTypeStr",
			type : "string",
			width : 150
		}, {
			title : "充值金额",
			name : "rechAmount",
			type : "string",
			width : 150
		} , {
			title : "兑换鸟豆",
			name : "rechNormCoin",
			type : "string",
			width : 150
		} , {
			title : "IOS内购序列号",
			name : "productId",
			type : "string",
			width : 150
		} , {
			title : "绑定类型",
			name : "objectOriented",
			type : "string",
			width : 150,
			customMethod : function(value, data){
				value=value.replace(/0/i,"一般会员");
				value=value.replace(/1/i,"VIP");
				value=value.replace(/2/i,"商家");
				value=value.replace(/3/i,"直销");
				value=value.replace(/4/i,"营业厅会员");
				value=value.replace(/5/i,"黄金庄园");
				return value;
			}
		}, {
			title : "累计充值人数",
			name : "rechargeNums",
			type : "string",
			width : 150
		},{
			title:"有效状态",
			name:"status",
			type:"string",
			width:150,
			customMethod:function(value,data){
				var result="-";
				switch (value){
					case 1:
						result="有效";
						break;
					case 2:
						result="无效";
						break;
					default :
						result="有效";

				}
				return result;
			}

		}]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveRechargeCombo/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 comboList.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 
 /**
  * 批量删除
  */
 $('#delete').click(function(){
 	var ids = comboList.getIds();
 	if(!ids){
 		showWarningWindow("warning","请至少选择一条记录！");
 		return;
 	}

	 if(!comboList.validateChoseStrict("status", "1", "套餐已删除不能再次删除")){
		 return;
	 }

 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'liveRechargeCombo/manage/delete.jhtml' + '?t=' + Math.random(),
 			data : 'ids=' + ids,
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					comboList.reset();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	});
 });
 
 
//初始化关联等级下拉框
 function initRankId(){
// 	debugger;
 	$("#fansRankIdReq").chosenObject({
 		hideValue : "id",
 		showValue : "rankName",
 		url : "liveFansRank/manage/getFansRanks.jhtml",
 		isChosen:true,//是否支持模糊查询
 		isCode:true,//是否显示编号
 		isHistorical:false,//是否使用历史已加载数据
 		width:"80%"
 	});
 }