var orderinvoiceList;
$(document).ready(function() {
	orderinvoiceList = $('#orderinvoiceList').page({
		url : 'businessman/orderinvoice/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(orderinvoiceList.getIds());
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
	
	inserTitle(' > 商家管理 > <a href="businessman/orderinvoice/init.jhtml" target="right">商家申请发票查询</a>','orderinvoiceSpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","businessman/orderinvoice/export.jhtml");
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
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家申请发票</caption>');
	html.push('<thead>');
	html.push('<tr>');
/*	html.push('<th style="width:20px;"><input type="checkbox" /></th>');*/
	
/*		if(!isEmptyObject(permissions)){
		html.push('<th style="width:80px;">操作</th>');
	}*/
	
	html.push('<th style="width:140px;">加入时间</th>');
	html.push('<th style="width:140px;">申请时间</th>');
	html.push('<th style="width:100px;">发票抬头</th>');
	html.push('<th style="width:100px;">税务登记号</th>');
	html.push('<th style="width:100px;">商家名称</th>');
	html.push('<th style="width:100px;">商家账号</th>');
	html.push('<th style="width:100px;">商家地址</th>');
	html.push('<th style="width:100px;">咨询电话</th>');
	/*html.push('<th style="width:100px;">所属区域</th>');*/
	html.push('<th style="width:100px;">已申请金额</th>');
	html.push('<th style="width:100px;">可申请金额</th>');
	html.push('<th style="width:100px;">申请金额</th>');
	html.push('<th style="width:100px;">处理状态</th>');
	html.push('<th style="width:100px;">操作员ID</th>');
	html.push('<th style="width:100px;">操作员名称</th>');
	html.push('<th style="width:100px;">意见</th>');
	html.push('<th style="width:100px;">备注</th>');
	html.push('<th style="width:100px;">修改时间</th>');
	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			/*html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');
			if(!isEmptyObject(permissions)){
				if(permissions.sc=='true'){
					html.push('<td><a href="javascript:remove('+data.content[i].id+')">删除</a></td>');
				}
			
			}*/
		    html.push('<td>' + (undefined == data.content[i].joinDate ? "-" : data.content[i].joinDate) + '</td>');
		    html.push('<td>' + (undefined == data.content[i].applyDate ? "-" : data.content[i].applyDate) + '</td>');
		    html.push('<td>' + (undefined == data.content[i].invoice ? "-" : data.content[i].invoice) + '</td>');
		    html.push('<td>' + (undefined == data.content[i].taxid ? "-" : data.content[i].taxid) + '</td>');
			var sellerName = (undefined == data.content[i].sellerName ? "-" : data.content[i].sellerName);
			html.push('<td title ="'+sellerName+'">' + substr(sellerName) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sellerUsername ? "-" : data.content[i].sellerUsername) + '</td>');
			
			var sellerAddress = (undefined == data.content[i].sellerAddress ? "-" : data.content[i].sellerAddress);
			html.push('<td title ="'+sellerAddress+'">' + substr(sellerAddress) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sellerTelephone ? "-" : data.content[i].sellerTelephone) + '</td>');
			/*html.push('<td>' + (undefined == data.content[i].regionId ? "-" : data.content[i].regionId) + '</td>');*/
			html.push('<td>' + (undefined == data.content[i].appliedAmount ? "-" : data.content[i].appliedAmount) + '</td>');
			html.push('<td>' + (undefined == data.content[i].availableAmount ? "-" : data.content[i].availableAmount) + '</td>');
			html.push('<td>' + (undefined == data.content[i].applyAmount ? "-" : data.content[i].applyAmount) + '</td>');
			html.push('<td>' + (undefined == data.content[i].strStatus ? "-" : data.content[i].strStatus) + '</td>');
			html.push('<td>' + (undefined == data.content[i].operationId ? "-" : data.content[i].operationId) + '</td>');
			html.push('<td>' + (undefined == data.content[i].operationName ? "-" : data.content[i].operationName) + '</td>');
			var opinion = (undefined == data.content[i].opinion ? "-" : data.content[i].opinion);
			html.push('<td title ="'+opinion+'">' + substr(opinion) + '</td>');
			html.push('<td>' + (undefined == data.content[i].note ? "-" : data.content[i].note) + '</td>');
			html.push('<td>' + (undefined == data.content[i].updateDate ? "-" : data.content[i].updateDate) + '</td>');
			html.push('</tr>');
		}
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="12">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 删除
 */
function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/orderinvoice/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					orderinvoiceList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}

