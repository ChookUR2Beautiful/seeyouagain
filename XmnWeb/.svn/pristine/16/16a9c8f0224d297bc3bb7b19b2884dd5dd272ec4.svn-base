var enrollList1;//全部选手
var imgRoot = $("#fastfdsHttp").val();


$(document).ready(function() {
	inserTitle('> 新食尚大赛> <a href="VstarPlayer/manage/init.jhtml" target="right">选手管理</a>','enrollConfirm',true);
	
	//加载区域控件
	$("#ld1").areaLd({
		showConfig : [{name:"provinceId",tipTitle:"--省--"},{name:"cityId",tipTitle:"--市--"},{name:"areaId",tipTitle:"--区--"}],
		isChosen : true
	});
	
	//初始化赛区下拉框
	initDivisionId();
	

	pageInit();

	initDate();
	
	//导出
	$("#export").click(function(){
		debugger;
		var formId="searchForm1";
		var url="VstarPlayer/manage/init/getCurrentSize.jhtml";
		var size=getCurrentSize(formId,url);
		if(size>2000){
			showWarningWindow("warning", "单次最多可导出2000条数据，请输入查询条件！",9999);
			return ;
		}
		
		var path="VstarPlayer/manage/export.jhtml";
		$form = $("#searchForm1").attr("action",path);
		$form[0].submit();
	});	
});

/**
 * 加载页面数据
 */
function pageInit(){
	enrollList1 = $('#enrollList1').page({
		url : 'VstarPlayer/manage/init/list.jhtml',
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
	var formId = "searchForm1", title = "选手列表", subtitle = "条信息";
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
			
		},
		cols : [ {
			title : "选手编号",
			name : "id",
			type : "string",
			width : 80
		},{
			title : "姓名",
			name : "nname",
			type : "string",
			width : 100
		},{
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 100
		} ,{
			title : "状态",
			name : "statusText",
			type : "string",
			width : 100
		},{
			title : "区域",
			name : "areaText",
			type : "string",
			width : 100
		},{
			title : "赛区",
			name : "divisionName",
			type : "string",
			width : 100
		},{
			title : "直播场次",
			name : "vstarLiveCount",
			type : "string",
			width : 100
		},{
			title : "点赞数",
			name : "likeCount",
			type : "string",
			width : 100
		},{
			title : "粉丝数",
			name : "fansNum",
			type : "string",
			width : 100
		},{
			title : "打赏数(鸟蛋)",
			name : "rewardEgg",
			type : "string",
			width : 100
		}, {
			title : "签约商户数",// 标题
			name : "sellerNum",
			width : 150,// 宽度
			type:"string",//数据类型
			isLink : true,
			link : {
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "VstarPlayer/manage/init/loadProductInfo/init.jhtml",
						position : "200px",
						width : "1028px",	
						hight : "auto",
						title : "签约商户" 
					}
				},
				param : ["uid"],
				permission : "update",
			},
			customMethod : function(value, data) {
				if(data.sellerNum != null && data.sellerNum != ""){
					return $(value).html(data.sellerNum);
				}
				return "-";
			}
		},{
			title : "评论数",
			name : "commentCount",
			type : "string",
			width : 100
		},{
			title : "推荐人",
			name : "referrerInfo",
			type : "string",
			width : 100,
			escape:false//html转义
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
// 	debugger;
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



/**
 * 直播日期控件初始化
 */
function initDate(){

	$('input[name="startTime"]').datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd',
		endDate: $("input[name='endTime']").val()
	}).on("changeDate",function() {
		$("input[name='endTime']").datetimepicker("setStartDate",$("input[name='startTime']").val());
	});

	$('input[name="endTime').datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd',
		startDate: $("input[name='startTime']").val()
	}).on( "changeDate", function() {
		$("input[name='startTime']").datetimepicker("setEndDate", $("input[name='endTime']").val());
	});
}

