var cardList;
var sellerid = $("input[name='sellerid']").val();
$(document).ready(function() {
	cardList = $('#cardList').page({
		url : 'businessman/allianceShop/bindCardList.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param :{sellerid: sellerid}
	});

	inserTitle('> <a href="businessman/allianceShop/bindCardInit.jhtml?sellerid='+sellerid+'" target="right">银行卡管理</a>','cardList');

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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">银行卡管理</caption>');
	html.push('<thead>');
	html.push('<tr>');
	//var hasHandle = permissions && (permissions.update || permissions.del);
	if(permissions.xg){
		html.push('<th style="width:100px;">操作</th>');
	}
	html.push('<th style="width:8%;">连锁店名称</th>');
	html.push('<th style="width:8%;">证件类型</th>');
	html.push('<th style="width:8%;">证件号码</th>');
	html.push('<th style="width:8%;">银行卡类型</th>');
	html.push('<th style="width:8%;">银行卡号</th>');
	html.push('<th style="width:8%;">开户行名称</th>');
	html.push('<th style="width:8%;">支行名称</th>');
	
	html.push('<th style="width:8%;">所在区域</th>');
	html.push('<th style="width:8%;">持卡人姓名</th>');
	html.push('<th style="width:8%;">银行预留手机号</th>');
	html.push('<th style="width:8%;">银行卡应用类型</th>');
	html.push('<th style="width:8%;">银行卡提现用途</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				if(permissions.xg){
					html.push('<td>');
						html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="businessman/allianceShop/bindCardInit/updateCardInit.jhtml?sellerid='+$("#sellerid").val()+'&id='+ data.content[i].id+'&sellername='+$("#sellername").val()+'" data-toggle="modal" >修改</a>&nbsp;&nbsp;');
					html.push('</td>');
				}
				var sellername = (undefined == $("#sellername").val() ? "-" : $("#sellername").val());
				html.push('<td title ="'+sellername+'">' + substr(sellername,8) + '</td>');
				html.push('<td>' + data.content[i].idtypeText + '</td>');
				html.push('<td>' + (undefined == data.content[i].identity ? "-" : data.content[i].identity) + '</td>');
				html.push('<td>' + (undefined == data.content[i].cardTypeText ? "-" : data.content[i].cardTypeText) + '</td>');
				html.push('<td>' + (undefined == data.content[i].cardId ? "-" : data.content[i].cardId) + '</td>');
				
				
				html.push('<td>' + (undefined == data.content[i].bank ? "-" : data.content[i].bank) + '</td>');
				html.push('<td>' + (undefined == data.content[i].bankName ? "-" : data.content[i].bankName) + '</td>');
				html.push('<td>' + (undefined == data.content[i].whereArea ? "-" : data.content[i].whereArea) + '</td>');
				html.push('<td>' + (undefined == data.content[i].cardUserName ? "-" : data.content[i].cardUserName) + '</td>');
				html.push('<td>' + (undefined == data.content[i].cardPhone ? "-" : data.content[i].cardPhone) + '</td>');
				html.push('<td>' + (undefined == data.content[i].ispublicText ? "-" : data.content[i].ispublicText) + '</td>');
				html.push('<td>' + (undefined == data.content[i].cardPurposeText ? "-" : data.content[i].cardPurposeText) + '</td>');
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

/**
 * 删除
 */
/*function remove(cid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/comment/delete.jhtml' + '?t=' + Math.random(),
			data : 'cid=' + cid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					cardList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/

