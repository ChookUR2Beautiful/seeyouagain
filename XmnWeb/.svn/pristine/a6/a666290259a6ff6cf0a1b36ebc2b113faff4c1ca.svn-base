var sellerApplyList;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	sellerApplyList = $('#sellerApplyList').page({
		url : 'businessman/sellerApply/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(sellerApplyList.getIds());
	});
	
	
	/**
	 * 批量通过
	 */
	$('#passId').click(function() {
		if(!sellerApplyList.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		pass(sellerApplyList.getIds(),1)
	});
	
	/**
	 * 批量不通过
	 */
	$('#notPassId').click(function(){
		var ids = sellerApplyList.getIds();
		if(!ids){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		var modalTrigger = new ModalTrigger({
			type : 'ajax',
			url : 'businessman/sellerApply/updateState/init.jhtml?ids=' + ids ,
			toggle : 'modal'
		});
		modalTrigger.show();
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
	
	inserTitle(' > 商户管理 > <a href="businessman/sellerApply/init.jhtml" target="right">商户信息修改申请</a>','sellerApplySpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","businessman/sellerApply/export.jhtml");
		$form[0].submit();
	});
	
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/businessman/sellerApply/init.jhtml';
			location.href =url;
		}
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商户信息修改申请</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:2%;"><input type="checkbox" /></th>');
		html.push('<th style="width:5%;">操作</th>');
	}
	
	html.push('<th style="width:5%;">申请编号</th>');
	html.push('<th style="width:5%;">商家编号</th>');
	html.push('<th style="width:10%;">商家名称</th>');
	html.push('<th style="width:5%;">修改类型</th>');
	html.push('<th style="width:5%;">状态</th>');
	/*html.push('<th style="width:8%;">区域</th>');
	html.push('<th style="width:5%;">商圈</th>');
	html.push('<th style="width:6%;">头像</th>');
	html.push('<th style="width:8%;">商家地址</th>');
	html.push('<th style="width:8%;">参考地标</th>');*/
	html.push('<th style="width:10%;">申请时间</th>');
	html.push('<th style="width:10%;">处理时间</th>');
	html.push('<th style="width:10%;">未通过原因</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
		updateAddBtnHref("#addSellerBto",callbackParam)
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(!isEmptyObject(permissions)){
				//撤销的申请不可以审批
				if(data.content[i].status == 0){
					html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');
				}else{
					html.push('<th>-</th>');
				}
				html.push('<td>');
				/*if(permissions.del == 'true'){
					html.push('<a href="javascript:remove('+data.content[i].id+')">删除</a>');
				}*/
				if(permissions.view == 'true'){
					html.push('&nbsp;&nbsp;<a href="businessman/sellerApply/view/init.jhtml?id='+data.content[i].id+callbackParam+'">查看</a>');
				}
				html.push('</td>');
			}
			//申请编号
			html.push('<td>' + (undefined == data.content[i].id ? "-" : data.content[i].id) + '</td>');
			//商家编号
			html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
			
			var sellername = (undefined == data.content[i].sellerNameStr ? "-" : data.content[i].sellerNameStr);
			//商家名称
			html.push('<td title ="'+sellername+'">' + substr(sellername) + '</td>');
			
			//修改申请类型
			var source = "-";
			if(data.content[i].source == 1)
			{
				source = "基本信息";
			}
			else if(data.content[i].source == 2)
			{
				source = "商家图片";
			}
			html.push('<td>' +source+ '</td>');
			
			//状态
			var state = "-";
			if(data.content[i].status == 0)
			{
				state = "待审核";
			}
			else if(data.content[i].status == 1)
			{
				state = "审核通过";
			}
			else if(data.content[i].status == 2)
			{
				state = "审核未通过";
			}
			else if(data.content[i].status == 3)
			{
				state = "撤销申请修改";
			}
			html.push('<td>' +state+ '</td>');
		/*	//区域
			html.push('<td>' + (undefined == data.content[i].areaTitle ? "-" : data.content[i].areaTitle) + '</td>');
			//商圈
			html.push('<td>' + (undefined == data.content[i].businessTitle ? "-" : data.content[i].businessTitle) + '</td>');
			
			//头像
			if(undefined == data.content[i].logo){
				html.push('<td >-</td>');
			}else{
				var url =  imgRoot + (undefined == data.content[i].logo ? "-" : data.content[i].logo);
				html.push('<td ><img src="'+url+'" style="width: 50px;height: 50px;"></td>');
			}
		
			//商家地址
			var address =(undefined == data.content[i].address ? "-" : data.content[i].address);
			html.push('<td title ="'+address+'">' + substr(address) + '</td>');
			//参考地标
			var landmark = (undefined == data.content[i].landmark ? "-" : data.content[i].landmark);
			html.push('<td title ="'+landmark+'">' + substr(landmark) + '</td>');*/
			//申请时间
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			//处理时间
			html.push('<td>' + (undefined == data.content[i].edate ? "-" : data.content[i].edate) + '</td>');
			//未通过原因
			var reason = (undefined == data.content[i].reason ? "-" : data.content[i].reason);
			html.push('<td title ="'+reason+'">' + substr(reason) + '</td>');
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
			url : 'businessman/sellerApply/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					sellerApplyList.reload();
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
 * 批量通过
 */
function pass(id,status) {
	/*if(!id){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}*/
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/sellerApply/updateState.jhtml',
			data : {"ids":id,"status":status},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					sellerApplyList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"你确定执行批量通过？");
}
