var logList;
$(document).ready(function() {
	logList = $('#logList').page({
		url : 'system_settings/log/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(logList.getIds());
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
	
	inserTitle(' > 系统管理 > <a href="system_settings/log/init.jhtml" target="right">日志管理</a>','logSpan',true);

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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">操作日志</caption>');
	html.push('<thead>');
	html.push('<tr>');
	/*if(!isEmptyObject(permissions)){
		html.push('<th style="width:2%;"><input type="checkbox" /></th>');
		html.push('<th style="width:3%;">操作</th>');
	}*/
	
	
	html.push('<th style="width:5%;">操作者</th>');
	/*去掉IP
	html.push('<th style="width:100px;">Ip</th>');*/
	
	html.push('<th style="width:9%;">操作时间</th>');
	
	//改为动作
	html.push('<th style="width:30%;">动作</th>');
	
/*	
	html.push('<th style="width:100px;">状态</th>');
	html.push('<th style="width:100px;">参数</th>');*/
	//去掉上面两列
	
	
	html.push('<th style="width:50%;">操作描述</th>');	
	html.push('</tr>');
	/*
	 * 去掉参数栏， 调整显示顺序，修改日志类型为  操作类型
	*/
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0){
		for (var i = 0; i < data.content.length; i++) {
			/*var logState= data.content[i].logState;
			var logState1="";
			if(logState==0){logState1="失败";
			}else if(logState==1){logState1="成功";
			}else{logState1="-";}*/
			
			html.push('<tr>');
			/*if(!isEmptyObject(permissions)){
				html.push('<th><input type="checkbox" val=' + data.content[i].logId + '  /></th>');
				if(permissions.del=="true"){
					html.push('<td><a href="javascript:remove('+data.content[i].logId+')">删除</a></td>');
				}
			}*/
			//html.push('<td>' + (undefined == data.content[i].logId ? "-" : data.content[i].logId) + '</td>');	
			html.push('<td>' + (undefined == data.content[i].username ? "-" : data.content[i].username) + '</td>');
			/*
			html.push('<td>' + (undefined == data.content[i].logIp ? "-" : data.content[i].logIp) + '</td>');
			*/
			
			html.push('<td>' + (undefined == data.content[i].logDate ? "-" : data.content[i].logDate) + '</td>');
			
			html.push('<td>' + (undefined == data.content[i].logNote ? "-" : data.content[i].logNote) + '</td>');
			/*
			html.push('<td>' + (logState1) + '</td>');
			html.push('<td>' + (undefined == data.content[i].logParame ? "-" : data.content[i].logParame) + '</td>');
			*/
			
			
			html.push('<td>' + (undefined == data.content[i].logRemark ? "-" : data.content[i].logRemark) + '</td>');
			
			
			
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
/*function remove(logId) {
	if(!logId){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'system_settings/log/delete.jhtml' + '?t=' + Math.random(),
			data : 'logId=' + logId,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					logList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/

