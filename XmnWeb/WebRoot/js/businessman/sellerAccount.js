var sellerAccountList;
var sellerid = $("input[name='sellerid']").val();
var accountType = $("#accountType").val();
$(document).ready(function() {
	sellerAccountList = $('#sellerAccountList').page({
		url : 'businessman/sellerAccount/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param:{sellerid : sellerid}
	});

	$('#delete').click(function() {
		remove(sellerAccountList.getIds());
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
	
	inserTitle(' > <a href="businessman/sellerAccount/init.jhtml?sellerid='+sellerid+'" target="right">账号管理</a>','sellerAccounList',false);
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">账号列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:100px;">操作</th>');
	}
	html.push('<th >帐号</th>');
	html.push('<th >帐号昵称</th>');	
	html.push('<th >真实姓名</th>');
	html.push('<th >联系人手机</th>');
	html.push('<th >账号类型</th>');
	html.push('<th >寻蜜鸟账号</th>');
	html.push('<th >添加时间</th>');
	html.push('<th >备注</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(!isEmptyObject(permissions)){
				html.push('<th><input type="checkbox" val=' + data.content[i].aid + '/></th>');
				html.push('<td>');
				if(permissions.xg=='true'){
					html.push('<a href="javascript:void();" data-type="ajax" data-url="businessman/sellerAccount/update/init.jhtml?aid='+data.content[i].aid+'&accountType='+accountType+'" data-toggle="modal">修改</a>&nbsp;&nbsp;');
				}
				if(permissions.sc=='true' && data.content[i].type != 1 && data.content[i].type != 4){
					html.push('<a href="javascript:remove('+data.content[i].aid+')">删除</a>');
				}
				html.push('</td>');
			}
			
			html.push('<td>' + (undefined == data.content[i].account ? "-" : data.content[i].account) + '</td>');
			html.push('<td title ="'+data.content[i].nname+'">' + (undefined == data.content[i].nname ? "-" :substr(data.content[i].nname,8)) + '</td>');
			html.push('<td title ="'+data.content[i].fullname+'">' + (undefined == data.content[i].fullname ? "-" :substr(data.content[i].fullname,8)) + '</td>');
			html.push('<td>' + (undefined == data.content[i].phone ? "-" : data.content[i].phone) + '</td>');
			html.push('<td>' + (undefined == data.content[i].typeText ? "-" : data.content[i].typeText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].uid ? "-" : data.content[i].uid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td title ="'+data.content[i].remarks+'">' + (undefined == data.content[i].remarks ? "-" : substr(data.content[i].remarks,8)) + '</td>');
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
function remove(aid) {
	if(!aid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/sellerAccount/delete.jhtml' + '?t=' + Math.random(),
			data : 'aid=' + aid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					//级联删除商家服务员推广管理信息
					deleteSpreadByAids(aid);
					sellerAccountList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"删除后，不影响其对应寻蜜鸟账号和钱包，但也将不会获得返利，是否继续删除？");
}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

/**
 * 根据商户ID删除商家服务员推广管理信息
 * @param aid
 */
function deleteSpreadByAids(aid){
	if(aid != null&&aid != ""){
		$.ajax({
			type : 'post',
			url : 'businessman/sellerSubsidy/spread/delete.jhtml' + '?t=' + Math.random(),
			data : 'aid=' + aid,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
	}
}

