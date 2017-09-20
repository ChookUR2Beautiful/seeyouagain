var orderinvoiceList;
$(document).ready(function() {
	var sellerid = $('#sellerid').val();
	orderinvoiceList = $('#sellerAgioGrid').page({
		url : 'businessman/sellerAgio/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'sellerAgioFrom',
		param : {sellerid:sellerid}
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
	var sellerid = $('#sellerid').val();
	var fartherSellerId = $('#fartherSellerId').val();
	inserTitle(' > <a href="businessman/sellerAgio/init.jhtml?sellerid='+sellerid+'" target="right">折扣列表</a>','sellerAgioList',false);
});

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
			url : 'businessman/sellerAgio/delete.jhtml' + '?t=' + Math.random(),
			data : 'aid=' + aid,
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

/**
 ** 乘法函数，用来得到精确的乘法结果
 ** 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
 ** 调用：accMul(arg1,arg2)
 ** 返回值：arg1乘以 arg2的精确结果
 **/
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家折扣</caption>');
	html.push('<thead>');
	html.push('<tr>');
	// add by huang'tao 2016-07-21  隐藏删除操作
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:20px;display:none"><input type="checkbox" /></th>');
		html.push('<th style="width:80px;display:none">操作</th>');
	}
	//html.push('<th style="width:100px;">使用状态</th>');
	html.push('<th style="width:100px;">折扣类型</th>');
	html.push('<th style="width:50px;">折扣</th>');
	html.push('<th style="width:170px;">折扣状态</th>');
	html.push('<th style="width:50px;">营业收入</th>');
	html.push('<th style="width:50px;">商户占比</th>');
	html.push('<th style="width:50px;">用户占比</th>');
	html.push('<th style="width:50px;">平台占比</th>');
	html.push('<th style="width:150px;">起始日期</th>');
	html.push('<th style="width:150px;">结束日期</th>');
	html.push('<th style="width:150px;">修改时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			
			if(!isEmptyObject(permissions)){
			 if(data.content[i].id == -1)
			 {
				 html.push('<th style="display:none">-</th>'); 
			 }else{
				 html.push('<th style="display:none"><input type="checkbox" val=' + data.content[i].id + '  /></th>'); 
			 }
				
				html.push('<td style="display:none">');
				//当前折扣不可修改， 而且是常规折扣
				if(data.content[i].id != -1 && data.content[i].type == 1)
				{
					if(permissions.xg=='true'){
						html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="businessman/sellerAgio/update/init.jhtml?aid='+data.content[i].id+'"  data-toggle="modal" >修改</a>&nbsp;&nbsp');
					}
					if(permissions.sc=='true'){
						html.push('<a href="javascript:remove('+data.content[i].id+')">删除</a>');
					}
				}else{
					html.push('-')
				}
				html.push('</td>');
			}
			//使用状态
		/*	var useState = '-';
			if(data.content[i].id == -1){
				useState = "当前折扣";
				html.push('<td><font color="red">' + useState + '</font></td>');
			}else{
				//useState = "历史折扣";
				html.push('<td>' + useState + '</td>');
			}*/
			
			
			
			//折扣类型
			var type = '-';
			if(data.content[i].type == 1)
			{
				type = '常规折扣';
			}
			else if (data.content[i].type == 2)
			{
				type = '周末折扣';
			}
			else if (data.content[i].type == 3)
			{
				type = '自定义折扣';
			}
			else
			{
				 type = '-';
			}
			html.push('<td>' + type + '</td>');
			//折扣
			html.push('<td>' + accMul((undefined == data.content[i].baseagio ? "-" : data.content[i].baseagio),100)+" %" + '</td>');
			//折扣状态
			var status = '-';
			if(data.content[i].status == 1)
			{
				status = '启用';
			}
			else if (data.content[i].status == 2)
			{
				status = '关闭';
			}
			else
			{
				 status = '-';
			}
			html.push('<td>' + status + '</td>');
			//营业收入
			html.push('<td>' + accMul((undefined == data.content[i].income ? "-" : data.content[i].income),100)+" %" + '</td>');
			//商户占比
			html.push('<td>' + accMul((undefined == data.content[i].sledger ? "-" : data.content[i].sledger),100)+" %" + '</td>');
			//用户占比
			html.push('<td>' +accMul( (undefined == data.content[i].yledger ? "-" : data.content[i].yledger),100)+" %" + '</td>');
			//平台占比
			html.push('<td>' + accMul((undefined == data.content[i].pledger ? "-" : data.content[i].pledger),100)+" %" + '</td>');
			//起始日期
			html.push('<td>' + (undefined == data.content[i].stdate ? "-" : data.content[i].stdate) + '</td>');
			//结束日期
			html.push('<td>' + (undefined == data.content[i].endate ? "-" : data.content[i].endate) + '</td>');
			//修改时间
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
	obj.find('div').eq(0).html(html.join(''));
}


