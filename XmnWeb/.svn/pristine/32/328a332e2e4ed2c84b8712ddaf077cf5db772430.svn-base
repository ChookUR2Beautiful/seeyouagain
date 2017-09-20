var sellerMsgList;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	inserTitle(' > 商家管理 > <a href="businessman/sellerMsg/init.jhtml" target="right">商家站内消息管理</a>','sellerMsgList',true);
	sellerMsgList = $('#sellerMsgList').page({
		url : 'businessman/sellerMsg/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(sellerMsgList.getIds());
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
	
	//商圈
	$("#zoneid").chosenObject({
		id : "zoneid",
		hideValue : "bid",
		showValue : "title",
		url : "common/business/businessInfo.jhtml",
		isChosen:true
	});
	
	

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","businessman/sellerApply/export.jhtml");
		$form[0].submit();
	});
	
	$("input[data-bus=reset]").click(function(){
		$("#zoneid").trigger("chosen:updated");
	});
});



/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
	updateAddBtnHref("#addbtn",callbackParam);
	
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家站内消息列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	if(!isEmptyObject(permissions)){
		/*html.push('<th style="width:3%;"><input type="checkbox" /></th>');*/
		html.push('<th style="width:12%;">操作</th>');
	}
	
	html.push('<th style="width:10%;">创建时间</th>');
	html.push('<th style="width:15%;">标题</th>');
	html.push('<th style="width:15%;">副标题</th>');
	html.push('<th style="width:10%;">开始时间</th>');
	html.push('<th style="width:10%;">结束时间</th>');
	html.push('<th style="width:5%;">推送人数</th>');
	html.push('<th style="width:5%;">已读消息人数</th>');
	html.push('<th style="width:15%;">推送对象</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(!isEmptyObject(permissions)){
			/*	html.push('<th><input type="checkbox" val=' + data.content[i].msgId + '  /></th>');*/
				html.push('<td>');
				
				/*if(permissions.update == 'true'){
					html.push('<a href="businessman/sellerMsg/update/init.jhtml?msgId='+data.content[i].msgId+'">修改</a>&nbsp;&nbsp;');
				}*/
				if(permissions.view == 'true'){
					html.push('<a href="businessman/sellerMsg/view/init.jhtml?msgId='+data.content[i].msgId+callbackParam+'">查看</a>&nbsp;&nbsp;');
				}
				
				/*if(permissions.del == 'true'){
					html.push('<a href="javascript:remove('+data.content[i].msgId+')">删除</a>');
				}*/
				html.push('</td>');
			}
			//创建时间
			html.push('<td>' + (undefined == data.content[i].dateCreated ? "-" : data.content[i].dateCreated) + '</td>');
			//标题
			var title = (undefined == data.content[i].title ? "-" : data.content[i].title);
			html.push('<td title ="'+title+'">' + substr(title) + '</td>');
			//副标题
			var subtitle = (undefined == data.content[i].subtitle ? "-" : data.content[i].subtitle);
			html.push('<td title ="'+subtitle+'">' + substr(subtitle) + '</td>');
			//开始时间
			html.push('<td>' + (undefined == data.content[i].dateSend ? "-" : data.content[i].dateSend) + '</td>');
			//结束时间
			html.push('<td>' + (undefined == data.content[i].dateEndSend ? "-" : data.content[i].dateEndSend) + '</td>');
			//推送总人数
			html.push('<td>' + (undefined == data.content[i].sendNum ? "-" : data.content[i].sendNum) + '</td>');
			//已读消息人数
			html.push('<td>' + (undefined == data.content[i].readNum ? "-" : data.content[i].readNum) + '</td>');
			//推送对象
			var ptitle =(undefined == data.content[i].ptitle ? "" : data.content[i].ptitle);
			var ctitle =(undefined == data.content[i].ctitle ? "" : data.content[i].ctitle);
			var atitle =(undefined == data.content[i].atitle ? "" : data.content[i].atitle);
			var btitle =(undefined == data.content[i].btitle ? "" : data.content[i].btitle);
			var sobject="";
			if(ptitle){
				sobject += "-" + ptitle;
			}
			if(ctitle){
				sobject += "-" + ctitle; + "-" + atitle + "-" + btitle
			}
			if(atitle){
				sobject += "-" + atitle;
			}
			if(btitle){
				sobject += "-" + btitle;
			}
			
			if(sobject == ""){
				sobject="-";
			}else{
				sobject = sobject.substring(1, sobject.length);
			}
			
			html.push('<td >' + sobject + '</td>');
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
function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/sellerMsg/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					sellerMsgList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}

