var sellerMarketingList;
var sellerid = $("input[name='sellerid']").val();
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	sellerMarketingList = $('#sellerMarketingList').page({
		url : 'businessman/sellerMarketing/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(sellerMarketingList.getIds());
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
	
	
	inserTitle('> <a href="businessman/sellerMarketing/init.jhtml?sellerid='+sellerid+'" target="right">商家营销信息</a>','sellerMarketingList');
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家营销信息</caption>');
	html.push('<thead>');
	html.push('<tr>');
//	var hasHandle = permissions && (permissions.update || permissions.del);
//	if(hasHandle){
//		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
//		html.push('<th style="width:80px;">操作</th>');
//	}
	
	html.push('<th style="width:80px;">操作</th>');
	
	html.push('<th style="width:100px;">商家</th>');
	html.push('<th style="width:100px;">活动编号</th>');
	html.push('<th style="width:100px;">活动</th>');
	html.push('<th style="width:100px;">是否参加</th>');
	html.push('<th style="width:100px;">加入时间</th>');
	html.push('<th style="width:100px;">开始时间</th>');
	html.push('<th style="width:100px;">结束时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
//			if(hasHandle){
//				html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');
//				html.push('<td>');
//				if(permissions.update){
//					html.push('<a href="javascript:" data-type="ajax" data-url="businessman/sellerMarketing/update/init.jhtml?id='+data.content[i].id+'&&isonline='+$("#isonline").val()+'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
//				}
//				if(permissions.del){
//					html.push('<a href="javascript:remove('+data.content[i].id+')">删除</a>&nbsp;&nbsp;');
//				}
//				html.push('</td>');
//			}
			
			html.push('<td>');
				if(data.content[i].isattend==0){
					html.push("<a href='javascript:#'  onclick='updateIsattend("+data.content[i].id+','+data.content[i].isattend+','+data.content[i].sellerid+','+data.content[i].aid+','+data.content[i].activityType+")'>暂停参与</a></br>");
				}else{
					html.push("<a href='javascript:#' style='color:#38b03f;' onclick='updateIsattend("+data.content[i].id+','+data.content[i].isattend+','+data.content[i].sellerid+','+data.content[i].aid+','+data.content[i].activityType+")'>重新参与</a></br>");
				}
				html.push("<a href='javascript:#'  onclick='exitActivity("+data.content[i].id+','+data.content[i].sellerid+','+data.content[i].activityType+")'>退出活动<div>");
			html.push('</td>');
				
			html.push('<td>' + (undefined == data.content[i].sellername ? "-" : data.content[i].sellername) + '</td>');
			html.push('<td>' + (undefined == data.content[i].aid ? "-" : data.content[i].aid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
			
			// 0：参加营销 1：不参加营销
			var StrisAttend = "-";
			if(data.content[i].isattend == 0){
				StrisAttend = "已参与";
			}
			else if(data.content[i].isattend == 1){
				StrisAttend = "已暂停";
			}
			html.push('<td>' + StrisAttend + '</td>');
			
			html.push('<td>' + (undefined == data.content[i].rdate ? "-" : data.content[i].rdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].edate ? "-" : data.content[i].edate) + '</td>');
			
			html.push('</tr>');
		}
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="8">暂无数据</td>');
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
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/sellerMarketing/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					sellerMarketingList.reload();
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
 * 暂停或者重新参与活动
 * @param sellerMaketingId
 * @param isattend
 * @param sellerid
 */
function updateIsattend(id,isattend,sellerid,aid,activityType) {
	var url;
	if(1 == activityType){  //刮刮卡
		url = 'marketingManagement/activityManagement/scratchCard/initSellerRelateNum/updateSellerMarketingIsattend.jhtml';
	}else if(2 == activityType){  //买赠
		url = 'marketingManagement/activityManagement/manzeng/initSellerRelateNum/updateSellerMarketingIsattend.jhtml';
	}else if(4 == activityType){  //佣金补贴
		 url = 'marketingManagement/activityManagement/commission/updateSellerMarketingIsAttend.jhtml';
	}else if(5 == activityType){  //折扣
		url = 'marketingManagement/activityManagement/discount/initSellerRelateNum/discountUpdateSellerMarketingIsattend.jhtml';
	}else if(6 == activityType){//优惠券活动
		url = 'marketingManagement/activityManagement/youhuiquan/initSellerRelateNum/updateSellerMarketingIsattend.jhtml';
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : url + '?t=' + Math.random(),
			data : 'id=' +id+'&isattend='+isattend+'&sellerid='+sellerid+'&aid='+aid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					sellerMarketingList.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"请确认此操作！");
}

/**
 * 退出活动
 * @param sellerMaketingId
 * @param sellerid
 */
function exitActivity(id,sellerid,activityType) {
	var url;
	if(1 == activityType){  //刮刮卡
		url = 'marketingManagement/activityManagement/scratchCard/initSellerRelateNum/scratchCardExitActivity.jhtml';
	}else if(2 == activityType){  //买赠
		url = 'marketingManagement/activityManagement/manzeng/initSellerRelateNum/manzengExitActivity.jhtml';
	}else if(4 == activityType){  //佣金补贴
		 url = 'marketingManagement/activityManagement/commission/commissionExitActivity.jhtml';
	}else if(5 == activityType){  //折扣
		url = 'marketingManagement/activityManagement/discount/initSellerRelateNum/discountExitActivity.jhtml';
	}else if(6 == activityType){
		url = 'marketingManagement/activityManagement/youhuiquan/initSellerRelateNum/youHuiQuanExitActivity.jhtml';
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : url + '?t=' + Math.random(),
			data : 'id=' +id+'&sellerid='+sellerid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					sellerMarketingList.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"退出活动后，本记录会删除，是否确认？");
}
