var adjustMentBill;
$(document).ready(function() {
	adjustMentBill = $('#adjustMentBill').page({
		url : 'billmanagerment/adjustmentbill/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:10,
		paramForm : 'searchForm'
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
	inserTitle(' > 订单管理 > <a href="illmanagerment/adjustmentbill/init.jhtml" target="right">调单处理</a>','refundbillSpan',true);

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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家申诉订单<font style="float:right;">共计【'+data.total+'】条订单&nbsp;</font></caption>');
	
	html.push('<thead>');
	html.push('<tr>');
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:100px;">操作</th>');
	}
	html.push('<th >申请时间</th>');
	html.push('<th >申请商家</th>');
	html.push('<th >订单号</th>');
	html.push('<th >用户手机</th>');
	html.push('<th >订单金额</th>');
	html.push('<th >订单状态</th>');
	html.push('<th >分账状态</th>');
	html.push('<th >调单状态</th>');
	html.push('<th >派奖结果描述</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	
if(null != data && data.content.length > 0)
	{	
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		if(!isEmptyObject(permissions)){
			html.push('<td>');
			if(permissions.updateHandlestatu == 'true'&&data.content[i].handlestatu==1){
				html.push('<a href="javascript:updatetg('+data.content[i].adid+',3)">通过</a>&nbsp;&nbsp;');
				html.push('<a href="javascript:updatetg('+data.content[i].adid+',2)">不通过</a>');
			}
			html.push('</td>');
		}
		html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sellername ? "-" : data.content[i].sellername) + '</td>');
		html.push('<td>' + (undefined == data.content[i].bid ? "-" : data.content[i].bid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].money ? "-" : data.content[i].money) + '</td>');
		html.push('<td>' + (undefined == data.content[i].status ? "-" : data.content[i].statusText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].hstatus ? "-" : data.content[i].hstatusText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].handlestatu ? "-" : data.content[i].handlestatuText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].activityRest ? "-" : data.content[i].activityRest) + '</td>');
		html.push('</tr>');
	  }
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="21">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

//function substr(obj,length){
//	if(obj.length > length){
//		obj = obj.substring(0,length) +"...";
//	}
//	return obj;
//}
function updatetg(adid,handlestatu) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'billmanagerment/adjustmentbill/update.jhtml' + '?t=' + Math.random(),
			data : {
				'adid':adid,
				'handlestatu':handlestatu
			},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {			 				
	          	$('#prompt').hide();
				if (data.success) {
					adjustMentBill.reload();
			    }			
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"你确定执行通过？");
}
