var enrollList1;//全部选手
var imgRoot = $("#fastfdsHttp").val();


$(document).ready(function() {
	inserTitle('> 新食尚大赛> <a href="VstarReward/manage/init.jhtml" target="right">推荐奖励管理</a>','enrollConfirm',true);
	
	pageInit();
	
	//导出
	$("#export").click(function(){
		debugger;
		var formId="searchForm";
		var url="VstarReward/manage/init/getCurrentSize.jhtml";
		var size=getCurrentSize(formId,url);
		if(size>1000){
			showWarningWindow("warning", "单次最多可导出1000条数据，请输入查询条件！",9999);
			return ;
		}
		
		var path="VstarReward/manage/export.jhtml";
		$form = $("#searchForm1").attr("action",path);
		$form[0].submit();
	});	
});

/**
 * 加载页面数据
 */
function pageInit(){
	enrollList1 = $('#enrollList1').page({
		url : 'VstarReward/manage/init/list.jhtml',
		success : success1,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 10,//每页条数
		paramForm : 'searchForm1',
		param :{}
	});
	
}

/**
 * 查询全部选手信息成功回调函数
 * @param data
 * @param obj
 */
function success1(data, obj) {
	var formId = "searchForm1", title = "奖励列表", subtitle = "条信息";
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
			
		},
		cols : [ {
			title : "选手编号",
			name : "playerId",
			type : "string",
			width : 80
		},{
			title : "选手姓名",
			name : "nname",
			type : "string",
			width : 100
		},{
			title : "选手手机",
			name : "phone",
			type : "string",
			width : 100
		} ,{
			title : "获得鸟币",
			name : "receiveCoin",
			type : "string",
			width : 100
		},{
			title : "推荐人",
			name : "referrerName",
			type : "string",
			width : 100
		},{
			title : "推荐人手机",
			name : "referrerPhone",
			type : "string",
			width : 100
		},{
			title : "获得时间",
			name : "receiveTimeStr",
			type : "string",
			width : 100
		}]
	}, permissions);
}



/**
 * 更新报名审核状态
 * @param id
 * @param status
 * @param telphones
 */
function update(id,status){
	 $.ajax({
        url : "VstarEnroll/manage/update.jhtml",
        type : "post",
        dataType : "json",
        data:{"id":id,"status":status},
        success : function(result) {
       	 if (result.success) {
    			showSmReslutWindow(result.success, result.msg);
    			setTimeout(function() {
    				pageInit();
    			}, 1000);
    		} else {
    			window.messager.warning(result.msg);
    		}
        }
    });
}

/**
 * 更新报名限制状态
 * @param id
 * @param confining
 */
function confine(id,confining){
	$.ajax({
        url : "VstarEnroll/manage/update.jhtml",
        type : "post",
        dataType : "json",
        data:{"id":id,"confining":confining},
        success : function(result) {
       	 if (result.success) {
    			showSmReslutWindow(result.success, result.msg);
    			setTimeout(function() {
    				pageInit();
    			}, 1000);
    		} else {
    			window.messager.warning(result.msg);
    		}
        }
    });
}

/**
 * 试播审核
 */
 function confirmPilot(id,status){
	 if(id==undefined || id == '' || status== undefined || status == ''){
		 window.messager.warning("报名信息不完整");
	 }
	 
	 var tips="是否通过申请";
	 if(status=="7"){
		 tips="是否通过申请";
	 }else if(status=="8"){
		 tips="是否拒绝申请";
	 }
	 
	 showSmConfirmWindow(function (){
		 $.ajax({
		        url : "VstarEnroll/manage/update.jhtml",
		        type : "post",
		        dataType : "json",
		        data:{"id":id,"status":status},
		        success : function(result) {
		       	 if (result.success) {
		    			showSmReslutWindow(result.success, result.msg);
		    			setTimeout(function() {
		    				pageInit();
		    			}, 1000);
		    		} else {
		    			window.messager.warning(result.msg);
		    		}
		        }
		    });
	 },tips);
 }

 
//初始化赛区下拉框
 function initDivisionId(){
 	debugger;
 	$("#divisionId").chosenObject({
 		hideValue : "id",
 		showValue : "divisionName",
 		url : "division/initDivisionId.jhtml",
 		isChosen:true,//是否支持模糊查询
 		isCode:true,//是否显示编号
 		isHistorical:false,//是否使用历史已加载数据
 		width:"85%"
 	});
 }
