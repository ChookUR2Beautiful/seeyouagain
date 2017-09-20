var activityList;
$(document).ready(function() {
	activityList = $('#activityList').page({
		url : 'marketingManagement/activityPrize/init/list.jhtml',
		//url:'marketingManagement/activityManagement/manzeng/activityPrize/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

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
	
	inserTitle(' > <a href="marketingManagement/activityPrize/init.jhtml?aid='+$("input[name='aid']").val()+'" target="right">活动派奖</a>','activityPrize',false);

});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">活动派奖详情列表</caption>');
	html.push('<thead>');
	html.push('<tr>');

	html.push('<th style="width:10%;">活动名称</th>');
	html.push('<th style="width:5%;">用户昵称</th>');
	html.push('<th style="width:5%;">用户手机</th>');
	html.push('<th style="width:5%;">订单号</th>');
	html.push('<th style="width:5%;">支付方式</th>');
	html.push('<th style="width:5%;">订单金额</th>');
	html.push('<th style="width:5%;">折扣</th>');
	html.push('<th style="width:5%;">参与商家</th>');
	html.push('<th style="width:8%;">支付时间</th>');
	html.push('<th style="width:8%;">派奖时间</th>');
	html.push('<th style="width:8%;">到账时间</th>');
	html.push('<th style="width:8%;">赠送金额</th>');
	html.push('<th style="width:8%;">派奖状态</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
			html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + '</td>');
			html.push('<td>' + (undefined == data.content[i].phone ? "-" : data.content[i].phone) + '</td>');
			html.push('<td>' + (undefined == data.content[i].orderNo ? "-" : data.content[i].orderNo) + '</td>');
			html.push('<td>' + (undefined == data.content[i].paytypeText ? "-" : data.content[i].paytypeText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].money ? "-" : "￥" + data.content[i].money) + '</td>');
			html.push('<td>' + (undefined == data.content[i].baseagio ? "-" : data.content[i].baseagio * 100 + "%") + '</td>');
			html.push('<td>' + (undefined == data.content[i].sellername ? "-" : data.content[i].sellername) + '</td>');
			html.push('<td>' + (undefined == data.content[i].zdate ? "-" : data.content[i].zdate) + '</td>');
			
			html.push('<td>' + (undefined == data.content[i].ptime ? "-" : data.content[i].ptime) + '</td>');
			
			html.push('<td>' + (undefined == data.content[i].fdate ? "-" : data.content[i].fdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].prizeNum ? "-" : "￥" + data.content[i].prizeNum) + '</td>');
			var StrStatus = "-";
			if(data.content[i].status == 0){
				StrStatus = "未派奖";
			}else if(data.content[i].status == 1){
				StrStatus = "已派奖";
			}
			html.push('<td>' + StrStatus + '</td>');
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