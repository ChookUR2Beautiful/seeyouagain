var agioRecordList;
$(document).ready(function() {
	agioRecordList = $('#agioRecordList').page({
		url : 'businessman/agioRecord/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(agioRecordList.getIds());
	});
	
	$('.form_datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	
	inserTitle(' > 商家管理 > <a href="businessman/agioRecord/init.jhtml" target="right">商家折扣设置查询</a>','userSpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","businessman/agioRecord/export.jhtml");
		$form[0].submit();
	});
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;商家折扣设置查询列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个记录&nbsp;</font></caption>';
	obj.find('div').eq(0).scrollTablel({
		data:data.content, 
		caption : captionInfo,
		cols:[{
			title : "商家编号",
			name : "sellerid",
			type : "string",
			width : 150
		},{
			title : "商家名称",
			name : "sellername",
			type : "string",
			width : 180
		},{
			title : "操作途径",
			name : "operationText",
			type : "string",
			width : 180
		},{
			title : "运营平台用户编号",
			name : "uid",
			type : "string",
			width : 200
		},{
			title : "折扣",
			name : "baseagio",
			type : "string",
			width : 100
		},{
			title : "营业收入",
			name : "income",
			type : "string",
			width : 150
		},{
			title : "商户分账",
			name : "sledger",
			type : "string",
			width : 150
		},{
			title : "用户分账",
			name : "yledger",
			type : "string",
			width : 150
		},{
			title : "平台分账",
			name : "pledger",
			type : "string",
			width : 150
		},{
			title : "起始日期",
			name : "stdate",
			type : "string",
			width : 180
		},{
			title : "结束日期",
			name : "endate",
			type : "string",
			width : 180
		},{
			title : "记录时间",
			name : "sdate",
			type : "string",
			width : 180
		}]},{});
	
	/*var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">折扣设置记录</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:100px;">商家</th>');
	html.push('<th style="width:100px;">操作途径</th>');
	html.push('<th style="width:130px;">运营平台用户编号</th>');
	html.push('<th style="width:100px;">折扣</th>');
	html.push('<th style="width:100px;">营业收入</th>');
	html.push('<th style="width:100px;">商户分账</th>');
	html.push('<th style="width:100px;">用户分账</th>');
	html.push('<th style="width:100px;">平台分账</th>');
	html.push('<th style="width:130px;">起始日期</th>');
	html.push('<th style="width:130px;">结束日期</th>');
	html.push('<th style="width:130px;">记录时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			var sellername = (undefined == data.content[i].sellername ? "-" : data.content[i].sellername)
			html.push('<td title ="'+sellername+'">' + substr(sellername) + '</td>');
			html.push('<td>' + (undefined == data.content[i].operationText ? "-" : data.content[i].operationText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].uid ? "-" : data.content[i].uid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].baseagio ? "-" : data.content[i].baseagio) + '</td>');
			html.push('<td>' + (undefined == data.content[i].income ? "-" : data.content[i].income) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sledger ? "-" : data.content[i].sledger) + '</td>');
			html.push('<td>' + (undefined == data.content[i].yledger ? "-" : data.content[i].yledger) + '</td>');
			html.push('<td>' + (undefined == data.content[i].pledger ? "-" : data.content[i].pledger) + '</td>');
			html.push('<td>' + (undefined == data.content[i].stdate ? "-" : data.content[i].stdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].endate ? "-" : data.content[i].endate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('</tr>');
		}
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));*/
}

/**
 * 删除
 */
function remove(id) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/agioRecord/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					agioRecordList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}

