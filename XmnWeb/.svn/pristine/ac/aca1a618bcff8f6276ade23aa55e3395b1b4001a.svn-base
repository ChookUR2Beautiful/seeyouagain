var activityList;
$(document).ready(function() {
	activityList = $('#activityList').page({
		url : 'marketingManagement/activitymanagement/init/list.jhtml',
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
	
	inserTitle(' > 营销管理 > <a href="marketingManagement/activitymanagement/init.jhtml" target="right">活动管理</a>','logSpan',true);

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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">营销活动列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	if(!isEmptyObject(permissions)){
		
		html.push('<th style="width:10%;">操作</th>');
	}

	html.push('<th style="width:10%;">活动名称</th>');
	html.push('<th style="width:10%;">活动类型</th>');
	html.push('<th style="width:10%;">开始时间</th>');
	html.push('<th style="width:10%;">结束时间</th>');
	html.push('<th style="width:10%;">活动规则</th>');
	html.push('<th style="width:10%;">活动描述</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
		if(!isEmptyObject(permissions)){
			html.push('<td>');
		
			if(permissions.update=='true'){
			
				html.push('<a href="javascript:viod(); " data-type="ajax"  data-url="marketingManagement/activitymanagement/update/init.jhtml?aid='+data.content[i].aid +'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
		    }
			if(permissions.check=='true'){
				
				html.push('<a href="javascript:viod(); " data-type="ajax" data-height="500px"  data-url="marketingManagement/activitymanagement/check/init.jhtml?aid='+data.content[i].aid +'"  data-toggle="modal" >查看</a>&nbsp;&nbsp;');
			}
			if(permissions.del=='true' && (data.content[i].isRelationSeller ==0)){
				
					html.push('<a href="javascript:remove('+data.content[i].aid+')">删除</a>');
				
				
			}
			html.push('</td>');
		}
			html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
			var type=data.content[i].type;
			var types="";
			if(type==1){types="抽奖返利活动";}else if(type==2){types="消费赠送活动";}else if(type==3){types="教育基金活动";}else if(type==4){types="消费补贴活动";}else{types="";}
			html.push('<td>' + (undefined == types ? "-" : types) + '</td>');
			html.push('<td>' + (undefined == data.content[i].startDate ? "-" : data.content[i].startDate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].endDate ? "-" : data.content[i].endDate) + '</td>');
					
		     var rules=(undefined == data.content[i].rule ? "-" : data.content[i].rule);
			html.push('<td title ="'+rules+'">' + substr(rules) + '</td>');
			 var eescriptions=(undefined == data.content[i].eescription ? "-" : data.content[i].eescription);
			html.push('<td title ="'+eescriptions+'">' + substr(eescriptions) + '</td>');
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
