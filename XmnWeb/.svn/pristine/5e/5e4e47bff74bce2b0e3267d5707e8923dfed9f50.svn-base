var appPushList;
var client = $(":hidden[name='client']").val();
var clientStr;
switch (client) {
case '1':
	clientStr = 'user_terminal';
	break;
case '2':
	clientStr = 'businessman';
	break;
case '3':
	clientStr = 'business_cooperation';
	break;
default:
	break;
}
$(document).ready(function() {
	appPushList = $('#appPushList').page({
		url : clientStr + '/appPush/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(appPushList.getIds());
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
	
//	inserTitle(' > '+(client==1?'用户客户端':(client==2?'商家管理':'合作商管理'))+' > <a href="'+clientStr+'/appPush/init.jhtml" target="right">消息推送管理</a>', clientStr + 'AppPush',true);
	inserTitle(' > 营销管理 > <a href="'+clientStr+'/appPush/init.jhtml" target="right">'+(client==1?' 用户消息推送管理':(client==2?' 商家消息推送管理':' 合作商消息推送管理'))+'</a>', clientStr + 'AppPush',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action", clientStr + "/appPush/export.jhtml");
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">'+(client==1?'用户消息推送列表':(client==2?'商家消息推送列表':'合作商消息推送列表'))+'</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	var Ispermissions=isEmptyObject(permissions)
	
	if(!Ispermissions){
/*		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
*/		html.push('<th style="width:80px;">操作</th>');
	}
	//html.push('<th style="width:100px;">tid</th>');
	
//	html.push('<th style="width:100px;">发送给谁</th>'); 
	html.push('<th style="width:100px;">客户端类型</th>');
	html.push('<th style="width:100px;">信息标题</th>');
	html.push('<th style="width:100px;">信息内容</th>');
	html.push('<th style="width:80px;">发送状态</th>');
	html.push('<th style="width:110px;">后续动作类型</th>');
	html.push('<th style="width:100px;">后续动作</th>');
	html.push('<th style="width:80px;">提醒方式</th>');
	html.push('<th style="width:100px;">内容类型</th>');
	html.push('<th style="width:120px;">发送时机</th>');
	html.push('<th style="width:120px;">创建时间</th>'); 
	html.push('<th style="width:120px;">推送开始时间</th>'); 
	html.push('<th style="width:120px;">推送结束时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
		updateAddBtnHref("#addAppPush",callbackParam);
		for (var i = 0; i < data.content.length; i++) {		
			html.push('<tr>');
			if(!Ispermissions){
/*				html.push('<th><input type="checkbox" val=' + data.content[i].tid + '  /></th>');
*/				html.push('<td>');
				if(permissions.update=='true'){
					html.push('<a href="'+clientStr+'/appPush/update/init.jhtml?tid='+data.content[i].tid+callbackParam+'" >查看</a>&nbsp;&nbsp;');
				}
				if(permissions.del=='true'){
					html.push('<a href="javascript:remove('+data.content[i].tid+')">删除</a>');
				}
				html.push('</td>');
			}
			html.push();
			
			//html.push('<td>' + (undefined == data.content[i].tid ? "-" : data.content[i].tid) + '</td>');
//			var object = (undefined == data.content[i].object ? "全部" : data.content[i].object);
//			html.push('<td title=\''+object+'\'>' + substr(object,15) + '</td>');
			html.push('<td>' + (undefined == data.content[i].clientText? "-" : data.content[i].clientText) + '</td>');// 1 寻蜜鸟客户端|2 商户客户端|3 合作商客户端
			var title = (undefined == data.content[i].title ? "-" : data.content[i].title);
			html.push('<td title="'+title+'">' + substr(title,8) + '</td>');
			var content = (undefined == data.content[i].content ? "-" : data.content[i].content);
			html.push('<td title="'+content+'">' + substr(content,15) + '</td>');
			html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');// 发送状态|0=待发送|1=已发送
			html.push('<td>' + (undefined == data.content[i].typeText ? "-" : data.content[i].typeText) + '</td>');// 1=打开应用|2=网址|3=activity
			var action = (undefined == data.content[i].action ? "-" : data.content[i].action);
			html.push('<td title="'+action+'">' + substr(action,10) + '</td>');
			html.push('<td>' + (undefined == data.content[i].remindText ? "-" : data.content[i].remindText) + '</td>');//提醒方式  0=声音|1=震动|2=呼吸灯
			html.push('<td>' + (undefined == data.content[i].contenttypeText ? "-" : data.content[i].contenttypeText) + '</td>');//内容类型 1=提示信息|2=订单提醒|3=营销信息|4 系统消息
			html.push('<td>' + (undefined == data.content[i].sendWay ? "-" : data.content[i].sendWay) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].tdate ? "-" : data.content[i].tdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].edate ? "-" : data.content[i].edate) + '</td>');// 过期时间|说明:过期时间需要至少晚于发送时间30分钟	
			html.push('</tr>');
		}
	}else{
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
function remove(tid) {
	if(!tid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : clientStr + '/appPush/delete.jhtml' + '?t=' + Math.random(),
			data : 'tid=' + tid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					appPushList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}
