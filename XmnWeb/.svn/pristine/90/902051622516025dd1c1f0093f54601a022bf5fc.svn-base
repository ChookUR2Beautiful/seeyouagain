var livePayRecordList;
var initListUrl = "liveRechargeAndRedPacket/manage/count/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
var formId="searchForm";
$(function() {
	inserTitle( ' > 打赏分红 > <a href="liveRechargeAndRedPacket/manage/init.jhtml" target="right">充值与红包统计</a>', 'userSpan', true);
	
	liveDateInit();
	
	//加载统计数据
	loadCountData();
});

function loadCountData(){
	//加载累计充值总额
	loadRechargeTotal();
	//加载指定区间充值总额
	loadRechargeOfTime();
	//加载指定区间各项充值金额的总额及人数(统计区间:2017-01-20至2017-01-20)
	loadRechargeGroupByPayment();
	//加载累计红包总额
	loadRedPacketTotal();
	//加载指定区间红包总额
	loadRedPacketOfTime();
	
	pageInit();
}

/**
 * 加载累计充值总额
 */
function loadRechargeTotal(){
	$.ajax({
		 url : "liveRechargeAndRedPacket/manage/count/loadRechargeTotal.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#' + formId).serializeArray()),
		 success : function(result) {
			 if (result.success) {
				 var data=result.data;
				 var content='';
					//加载统计区间表单数据
				 if(data){
					 content +="<tr>"
//		                 + "       <td>"+data.data.startTime+"至"+data.data.endTime+"</td>"				//统计时间区间
		                 + "       <td>"+data.col1+"</td>"  				//充值总数
		                 + "       <td>"+data.col2+"</td>"				 	//充值总人数
		                 + "       <td>"+data.col3+"</td>"	 				//线上充值金额
		                 + "       <td>"+data.col4+"</td>"  				//充值人数
		                 + "       <td>"+data.col5+"</td>"  				//线下充值金额
		                 + "       <td>"+data.col6+"</td>"					//充值人数
		                 + "</tr>" ;
				 }else{
					 content +="<tr ><td colspan='6'>暂无数据</td></tr>";
				 }
					 
			        $("#rechargeTotal").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}

/**
 * 加载指定区间充值总额
 */
function loadRechargeOfTime(){
	$.ajax({
		 url : "liveRechargeAndRedPacket/manage/count/loadRechargeOfTime.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#' + formId).serializeArray()),
		 success : function(result) {
			 if (result.success) {
				 var data=result.data;
				 var content='';
					//加载统计区间表单数据
				 if(data){
					 content +="<tr>"
		                 + "       <td style='width:130px;' >"+data.startTime+"至"+data.endTime+"</td>"				//统计时间区间
		                 + "       <td style='width:130px;' >"+data.col1+"</td>"  				//充值总数
		                 + "       <td style='width:130px;' >"+data.col2+"</td>"				//充值总人数
		                 + "       <td style='width:130px;' >"+data.col3+"</td>"	 			//线上充值金额
		                 + "       <td style='width:130px;' >"+data.col4+"</td>"  				//充值人数
		                 + "       <td style='width:130px;' >"+data.col5+"</td>"  				//线下充值金额
		                 + "       <td style='width:130px;' >"+data.col6+"</td>"					//充值人数
		                 + "</tr>" ;
				 }else{
					 content +="<tr ><td colspan='7'>暂无数据</td></tr>";
				 }
					 
			        $("#rechargeOfTime").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}

function loadRechargeGroupByPayment(){
	
}

/**
 * 加载累计红包总额
 */
function loadRedPacketTotal(){
	$.ajax({
		 url : "liveRechargeAndRedPacket/manage/count/loadRedPacketTotal.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#' + formId).serializeArray()),
		 success : function(result) {
			 if (result.success) {
				 var data=result.data;
				 var content='';
					//加载统计区间表单数据
				 if(data){
					 content +="<tr>"
		                 + "       <td style='width:130px;' >"+data.col1+"</td>"  				//实际发放总额
		                 + "       <td style='width:130px;' >"+data.col2+"</td>"				//红包个数
		                 + "       <td style='width:130px;' >"+data.col3+"</td>"	 		    //有效可领取总额
		                 + "       <td style='width:130px;' >"+data.col4+"</td>"  				//有效实际领取总额
		                 + "       <td style='width:130px;' >"+data.col5+"</td>"  				//红包个数
		                 + "       <td style='width:130px;' >"+data.col6+"</td>"				//无人认领总额
		                 + "       <td style='width:130px;' >"+data.col7+"</td>"				//红包个数
		                 + "       <td style='width:130px;' >"+data.col8+"</td>"				//限制认领总额
		                 + "       <td style='width:130px;' >"+data.col9+"</td>"				//红包个数
		                 + "</tr>" ;
				 }else{
					 content +="<tr ><td colspan='9'>暂无数据</td></tr>";
				 }
					 
			        $("#redPacketTotal").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}

/**
 * 加载指定区间红包总额
 */
function loadRedPacketOfTime(){
	$.ajax({
		 url : "liveRechargeAndRedPacket/manage/count/loadRedPacketOfTime.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#' + formId).serializeArray()),
		 success : function(result) {
			 if (result.success) {
				 var data=result.data;
				 var content='';
					//加载统计区间表单数据
				 if(data){
					 content +="<tr>"
						 + "       <td style='width:150px;' >"+data.startTime+"至"+data.endTime+"</td>"				//统计时间区间
		                 + "       <td style='width:130px;' >"+data.col1+"</td>"  				//实际发放总额
		                 + "       <td style='width:130px;' >"+data.col2+"</td>"				//红包个数
		                 + "       <td style='width:130px;' >"+data.col3+"</td>"	 		    //有效可领取总额
		                 + "       <td style='width:130px;' >"+data.col4+"</td>"  				//有效实际领取总额
		                 + "       <td style='width:130px;' >"+data.col5+"</td>"  				//红包个数
		                 + "       <td style='width:130px;' >"+data.col6+"</td>"				//无人认领总额
		                 + "       <td style='width:130px;' >"+data.col7+"</td>"				//红包个数
		                 + "       <td style='width:130px;' >"+data.col8+"</td>"				//限制认领总额
		                 + "       <td style='width:130px;' >"+data.col9+"</td>"				//红包个数
		                 + "</tr>" ;
				 }else{
					 content +="<tr ><td colspan='10'>暂无数据</td></tr>";
				 }
					 
			        $("#redPacketOfTime").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}

function pageInit(){
	livePayRecordList = $("#livePayRecordList").page({
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
	var formId = "shareForm", title = "每天各项充值金额的总数及人数", subtitle = "";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>统计区间('
			+ data.titleInfo.startTime+"至"+data.titleInfo.endTime
			+ ')' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
	    checkable : false,
		cols : [ {
			title : "等级",
			name : "fansRankNameStr",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return value;
			}
		}, {
			title : "金额",
			name : "payment",
			type : "string",
			width : 150
		}, {
			title : "充值总金额",
			name : "paymentSum",
			type : "string",
			width : 150
		}, {
			title : "充值总人数",
			name : "people",
			type : "string",
			width : 150
		} ]
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
	 
	 
	 $('input[name="startTime"]').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			minuteStep :30,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd',
			startDate : new Date(),
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
			minView : 0,
			minuteStep :30,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd',
			startDate: $("input[name='startTime']").val()
		}).on( "changeDate", function() {
					$("input[name='startTime']").datetimepicker("setEndDate", $("input[name='endTime']").val());
				});
 }
 
//查询全部
 $("#querySubmit").click(function(){
	 loadCountData();
 });	

 //重置
 $("#queryReset").click(function(){
	
 	$("#startTime").val("");
 	$("#endTime").val("");
 	
 	 loadCountData();
 });	