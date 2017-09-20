var pageDiv;
$(document).ready(function() {
	console.info("......................");
	var url  = [$("#sellerInPendingfoDiv").attr("request-init"),".jhtml"].join("");
/*	inserTitle(' > 活动管理 > <a href="'+url+'" target="right">参与活动商家</a>','sellerApplayList',true);
*/    


	
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
	
	pageDiv = $('#sellerInPendingfoDiv').page({
		url : url,
		success : success,
		pageBtnNum : 10,
		paramForm : 'sellerPendingFromId'
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">活动参与商家列表<font style="float:right;">共计【'+data.total+'】个参与商家&nbsp;</font></caption>');
	html.push('<thead>');
	html.push('<tr>');
	if(permissions.updateIsattend||permissions.exitActivity){
		html.push('<th style="width:100px;">操作</th>');
		}
	html.push('<th style="width:100px;">商家编号</th>');
	html.push('<th style="width:150px;">商家名称</th>');
	html.push('<th style="width:200px;">商家手机号</th>');
	html.push('<th style="width:70px;">审核状态</th>');
	html.push('<th style="width:100px;">上线状态</th>');
	html.push('<th style="width:100px;">参与状态</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#sellerPendingFromId").serialize());
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				if(permissions.updateIsattend||permissions.exitActivity){
					html.push('<td>');
					
			   if(permissions.updateIsattend){
				if(data.content[i].isattend==0){
					html.push("<a href='javascript:#'  onclick='updateIsattend("+data.content[i].sellerMaketingId+','+data.content[i].isattend+','+data.content[i].sellerid+")'>暂停参与</a></br>");
				}else{
					html.push("<a href='javascript:#' style='color:#38b03f;' onclick='updateIsattend("+data.content[i].sellerMaketingId+','+data.content[i].isattend+','+data.content[i].sellerid+")'>重新参与</a></br>");
				}
			   }
				if(permissions.exitActivity){
				html.push("<a href='javascript:#'  onclick='exitActivity("+data.content[i].sellerMaketingId+','+data.content[i].sellerid+")'>退出活动<div>");
				  }  
				html.push('</td>');
				}
				html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
				var sellername = data.content[i].sellername?data.content[i].sellername:'-';
				sellername = substr(sellername,8);
				html.push('<td class="text-ellipsis" title ="'+data.content[i].sellername+'">'+sellername+'</td>');	
			
				html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
				
				//默认0未验证|1审核中|2未通过|3已签约|4未签约
				var status = "-";
				if(data.content[i].status == 1)
				{
					status = "审核中";
				}
				else if(data.content[i].status == 2)
				{
					status = "未通过";
				}
				else if(data.content[i].status == 3)
				{
					status = "已签约";
				}
				else if(data.content[i].status == 4)
				{
					status = "未签约";
				}
				else if(data.content[i].status == 0)
				{
					status = "未验证";
				}
				html.push('<td class="text-ellipsis" name="status" initValue="'+data.content[i].status+'">'+status+'</td>');	//状态
				
				
				// 0：未上线 1：已上线 2:预上线 3：已下线 默认为：0
				var Strisonline = "-";
				if(data.content[i].isonline == 3)
				{
					Strisonline = "已下线";
				}
				else if(data.content[i].isonline == 2)
				{
					Strisonline = "预上线";
				}
				else if(data.content[i].isonline == 1)
				{
					Strisonline = "已上线";
				}
				else if(data.content[i].isonline == 0)
				{
					Strisonline = "未上线";
				}
				html.push('<td class="text-ellipsis">'+Strisonline+'</td>');	//上线状态
				
				// 0：参加营销 1：不参加营销
				var StrisAttend = "-";
				if(data.content[i].isattend == 0)
				{
					StrisAttend = "已参与";
				}
				else if(data.content[i].isattend == 1)
				{
					StrisAttend = "已暂停";
				}
				html.push('<td class="text-ellipsis">'+StrisAttend+'</td>');	//上线状态
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

function updateIsattend(sellerMaketingId,isattend,sellerid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'marketingManagement/activityManagement/youhuiquan/initSellerRelateNum/updateSellerMarketingIsattend.jhtml' + '?t=' + Math.random(),
			data : 'id=' +sellerMaketingId+'&isattend='+isattend+'&sellerid='+sellerid+'&aid='+$('#aid').val(),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					pageDiv.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"请确认此操作！");
}
function exitActivity(sellerMaketingId,sellerid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'marketingManagement/activityManagement/youhuiquan/initSellerRelateNum/youHuiQuanExitActivity.jhtml' + '?t=' + Math.random(),
			data : 'id=' +sellerMaketingId+'&sellerid='+sellerid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					pageDiv.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"退出活动后，本记录会删除，是否确认？");
}
