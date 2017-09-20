var appPushList;
$(document).ready(function() {
	appPushList = $('#appPushList').page({
		url : 'user_terminal/appPush/init/list.jhtml',
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
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/appPush/init.jhtml" target="right">客户端消息推送</a>','userSpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","user_terminal/appPush/export.jhtml");
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">营销信息推送</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	var Ispermissions=isEmptyObject(permissions)
	
	if(!Ispermissions){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:80px;">操作</th>');
	}
	
	//html.push('<th style="width:100px;">tid</th>');
	html.push('<th style="width:140px;">推送时间</th>'); 
	html.push('<th style="width:100px;">发送给谁</th>'); 
	html.push('<th style="width:100px;">客户端类型</th>');
	html.push('<th style="width:100px;">信息标题</th>');
	html.push('<th style="width:100px;">信息内容</th>');
	html.push('<th style="width:80px;">发送状态</th>');
	html.push('<th style="width:100px;">后续动作类型</th>');
	html.push('<th style="width:100px;">后续动作</th>');
	html.push('<th style="width:80px;">提醒方式</th>');
	html.push('<th style="width:100px;">内容类型</th>');
	html.push('<th style="width:140px;">创建时间</th>');
	html.push('<th style="width:140px;">过期时间</th>');
	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		for (var i = 0; i < data.content.length; i++) {
			// 1 寻蜜鸟客户端|2 商户客户端|3 合作商客户端
			var client= data.content[i].client;
			var client1= "";
			if(client==1){client1="寻蜜鸟客户端";
			}else if(client==2){client1="商户客户端";
			}else if(client==3){client1="合作商客户端";}
			
			// 发送状态|0=待发送|1=已发送
			var status=data.content[i].status;
			var status1="";
			if(status==0){status1="待发送";}else if(status==1){status1="已发送";}
			
			//后续动作类型1=打开应用|2=网址|3=activity
			var type=data.content[i].type;
			var type1="";
			if(type==1){type1="打开应用";
			}else if(type==2){type1="网址";
			}else if(type==3){type1="activity";}
			
			//提醒方式  0=声音|1=震动|2=呼吸灯
			var remind=data.content[i].remind;
			var remind1="";
			if(remind==0){remind1="声音";
			}else if(remind==1){remind1="震动";
			}else if(remind==2){remind1="呼吸灯";}
			
			//内容类型 1=提示信息|2=订单提醒|3=营销信息|4 系统消息
			var contenttype=data.content[i].contenttype;
			var contenttype1="";
			if(contenttype==1){contenttype1="提示信息";}
			else if(contenttype==2){contenttype1="订单提醒";}
			else if(contenttype==3){contenttype1="营销信息";}
			else if(contenttype==4){contenttype1="系统消息";}
			
							
			html.push('<tr>');
			
			if(!Ispermissions){
				html.push('<th><input type="checkbox" val=' + data.content[i].tid + '  /></th>');
				html.push('<td>');
				if(permissions.update=='true'){
					html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="user_terminal/appPush/update/init.jhtml?tid='+data.content[i].tid+'"  data-toggle="modal" >查看</a>&nbsp;&nbsp;');
				}
				if(permissions.del=='true'){
/*					html.push('<a href="javascript:remove('+data.content[i].tid+')">删除</a>');
*/				}
				html.push('</td>');
			}
			html.push();
			
			//html.push('<td>' + (undefined == data.content[i].tid ? "-" : data.content[i].tid) + '</td>');		
			html.push('<td>' + (undefined == data.content[i].tdate ? "即时发送" : data.content[i].tdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].object ? "-" : data.content[i].object) + '</td>');
			html.push('<td>' + (undefined == client1? "-" : client1) + '</td>');// 1 寻蜜鸟客户端|2 商户客户端|3 合作商客户端
			html.push('<td>' + (undefined == data.content[i].title ? "-" : data.content[i].title) + '</td>');
			html.push('<td>' + (undefined == data.content[i].content ? "-" : data.content[i].content) + '</td>');
			html.push('<td>' + (undefined == status1 ? "-" : status1) + '</td>');// 发送状态|0=待发送|1=已发送
			html.push('<td>' + (undefined == type1 ? "-" : type1) + '</td>');// 1=打开应用|2=网址|3=activity
			html.push('<td>' + (undefined == data.content[i].action ? "-" : data.content[i].action) + '</td>');// 后续动作网页地址activity名称等
			html.push('<td>' + (undefined == remind1 ? "-" : remind1) + '</td>');//提醒方式  0=声音|1=震动|2=呼吸灯
			html.push('<td>' + (undefined == contenttype1 ? "-" : contenttype1) + '</td>');//内容类型 1=提示信息|2=订单提醒|3=营销信息|4 系统消息
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
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
			url : 'user_terminal/appPush/delete.jhtml' + '?t=' + Math.random(),
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

//function add() {
//	alert('添加');
//	showEditWindow('添加营销消息推送', 'body');
//}
