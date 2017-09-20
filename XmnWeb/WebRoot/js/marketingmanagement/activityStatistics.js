var activityList;
$(document).ready(function() {

	$('#delete').click(function() {
		remove(activityList.getIds());
	});
	
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
	
	$("input[data-bus=reset]").click(function(){
		$("#aid").trigger("chosen:updated");
		$("#rule").html("");
		$("#eescription").html("").removeAttr("title");
		$("#type").html("");
		$("#startDate").html("");
		$("#endDate").html("");
	});
	  
	$("#aid").chosenObject({
		hideValue : "aid",
		showValue : "aname",
		url : "marketingManagement/activitymanagement/getActivitys.jhtml",
		isChosen:true
	}).on("chosen:hiding_dropdown", function (event){
		getActivity(event.target.value);
	});
	
	activityList = $('#activityList').page({
		url : 'marketingManagement/activityPrize/statistics/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});
	
	inserTitle(' > 营销管理 > <a href="marketingManagement/activityPrize/statistics/init.jhtml" target="right">活动统计</a>','activityPrize_count_statistics',true);

});

function restOption() {  
	$("#aid").chosenObject({
		isChosen:false
	});
	$("input[data-bus=reset]").click(function(){
		$("#aid").trigger("chosen:updated");
		$("#rule").html("");
		$("#eescription").html("").removeAttr("title");
		$("#type").html("");
		$("#startDate").html("");
		$("#endDate").html("");
	});
}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">营销活动统计列表</caption>');
	html.push('<thead>');
	html.push('<tr>');

	html.push('<th style="width:10%;">活动名称</th>');
	html.push('<th style="width:10%;">活动类型</th>');
	html.push('<th style="width:5%;">参与商家数</th>');
	html.push('<th style="width:5%;">参与用户数</th>');
	html.push('<th style="width:5%;">应派奖商家数</th>');
	html.push('<th style="width:5%;">应派奖数</th>');
	html.push('<th style="width:5%;">已派奖数</th>');
	html.push('<th style="width:5%;">未派奖数</th>');
	html.push('<th style="width:5%;">应派奖金额</th>');
	html.push('<th style="width:5%;">已派奖金额</th>');
	html.push('<th style="width:5%;">未派奖金额</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0)
	{
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(permissions.check){
				html.push('<td><a href="marketingManagement/activityPrize/init.jhtml?aid='+data.content[i].aid+callbackParam+'">' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</a></td>');
			}else{
				html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
			}
			var type=data.content[i].TYPE;
			var types="";
			if(type==1){types="抽奖类";}else if(type==2){types="满赠类";}else if(type==5){types="平台补贴类";}else if(type==6){types="优惠券赠送活动";}else if(type==7){types="赠送积分类";}else{types="";}
			html.push('<td>' + (undefined == types ? "-" : types) + '</td>');
			html.push('<td>' + (undefined == data.content[i].joinseller ? "-" : data.content[i].joinseller) + '</td>');
			html.push('<td>' + (undefined == data.content[i].uC ? "-" : data.content[i].uC) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sellerC ? "-" : data.content[i].sellerC) + '</td>');
			html.push('<td>' + (undefined == data.content[i].allC ? "-" : data.content[i].allC) + '</td>');
			html.push('<td>' + (undefined == data.content[i].yC ? "-" : data.content[i].yC) + '</td>');
			html.push('<td>' + (undefined == data.content[i].wC ? "-" : data.content[i].wC) + '</td>');
			html.push('<td>' + (undefined == data.content[i].allS ? "-" : "￥" + data.content[i].allS) + '</td>');
			html.push('<td>' + (undefined == data.content[i].yS ? "-" : "￥" + data.content[i].yS) + '</td>');
			html.push('<td>' + (undefined == data.content[i].wS ? "-" : "￥" + data.content[i].wS) + '</td>');
			html.push('</tr>');
		}
		
	}else
	{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 删除
 */
function remove(aid) {
	if(!aid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'marketingManagement/activitymanagement/delete.jhtml' + '?t=' + Math.random(),
			data : 'aid=' + aid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					activityList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}
function getActivity(aid){
	if(aid){
		$.ajax({
			url : "marketingManagement/activitymanagement/getActivity.jhtml?aid=" + aid,
			type : "get",
			dataType : 'json',
			success : function(data) {
				$("#rule").text(data.rule);
				$("#eescription").text(substr(data.eescription,20)).attr("title", data.eescription);
				$("#type").text(data.type == 1 ? "抽奖返利活动" : (data.type == 2 ? "消费赠送活动" : (data.type == 3 ? "教育基金活动" : "消费补贴活动")));
				$("#startDate").text(data.startDate);
				$("#endDate").text(data.endDate);
			}
		});
	}else{
		$("#aid").trigger("chosen:updated");
		$("#rule").text("");
		$("#eescription").text("");
		$("#type").text("");
		$("#startDate").text("");
		$("#endDate").text("");
	}
}