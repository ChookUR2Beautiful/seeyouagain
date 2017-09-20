var businessAreaList;
$(document).ready(function() {

/*	$('#delete').click(function() {
		remove(orderinvoiceList.getIds());
	});*/
	inserTitle(' > 商务区域管理  > <a href="business_area/businessArea/init.jhtml" target="right">合作商区域分布</a>','orderinvoiceSpan',true);
	
	var ld = $("#sqy").areaLd({
		isChosen : true,
		showConfig : [{name:"shenNameid",tipTitle:"--省--"},{name:"cityNameid",tipTitle:"--市--"}]
	});
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/business_area/businessArea/init.jhtml';
			location.href =url;
		}
		
		$("#sqy").find("select").trigger("chosen:updated");
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
	
	businessAreaList = $('#businessAreaList').page({
		url : 'business_area/businessArea/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	/*$("#export").click(function(){
		$form = $("#searchForm").attr("action","businessman/orderinvoice/export.jhtml");
		$form[0].submit();
	});*/
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">合作商区域分布</caption>');
	html.push('<thead>');
	html.push('<tr>');
	/*html.push('<th style="width:20px;"><input type="checkbox" /></th>');*/	
	var Ispermissions=isEmptyObject(permissions);
	if(!Ispermissions){
		html.push('<th style="width:80px;">操作</th>');	
	}
	html.push('<th style="width:140px;">省份</th>');
	html.push('<th style="width:140px;">城市</th>');
	html.push('<th style="width:100px;">区域数</th>');
	html.push('<th style="width:100px;">商圈</th>');
	html.push('<th style="width:100px;">合作商数</th>');
	html.push('<th style="width:100px;">已签约数</th>');
	/*html.push('<th style="width:100px;">所属团队</th>');	*/	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');

	if(null != data && data.content.length > 0)
	{	
	var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
	for (var i = 0; i < data.content.length; i++) {		
		html.push('<tr>');
		if(!Ispermissions){
	/*	html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');*/	
		html.push('<td>');
			if(permissions.hzsquxq=='true'){
			html.push('<a href="business_area/businessArea/getBusinessAreaByid.jhtml?cityNameid='+data.content[i].cityNameid+callbackParam+'">查看</a>');		
			}
		html.push('</td>');
		}
		html.push('<td>' + (undefined == data.content[i].title ? "-" : data.content[i].title) + '</td>');
		html.push('<td>' + (undefined == data.content[i].cityName ? "-" : data.content[i].cityName) + '</td>');
		//区域
		var areaData = (undefined == data.content[i].areaCount ||data.content[i].areaCount == 0)?data.content[i].areaCount:'<a href="javascript:viod(); " data-type="ajax"   data-url="business_area/businessArea/init/getBusinessAreaInfo/init.jhtml?cityNameid='+data.content[i].cityNameid+'"  data-toggle="modal" data-title="区域详情" data-width="800px" data-backdrop="static">' +   data.content[i].areaCount + '</a>'
		html.push('<td>' + (undefined == areaData ? "-": areaData) + '</td>');
		//商圈
		var businessData = (undefined == data.content[i].businessCount ||data.content[i].businessCount == 0)?data.content[i].bnum:'<a href="javascript:viod(); " data-type="ajax"   data-url="business_area/businessArea/init/getBusinessDataInfo/init.jhtml?cityNameid='+data.content[i].cityNameid+'"  data-toggle="modal" data-title="商圈详情" data-width="800px" data-backdrop="static">' +   data.content[i].businessCount + '</a>'
		html.push('<td>' + (undefined == businessData ? "0": businessData) + '</td>');
		//合作商
		var cooperatorData = (undefined == data.content[i].jointCount ||data.content[i].jointCount == 0)?data.content[i].bnum:'<a href="javascript:viod(); " data-type="ajax"   data-url="business_area/businessArea/init/getcooperatorInfo/init.jhtml?cityNameid='+data.content[i].cityNameid+'"  data-toggle="modal" data-title="合作商详情" data-width="800px" data-backdrop="static">' +   data.content[i].jointCount + '</a>'
		html.push('<td>' + (undefined == cooperatorData ? "0" : cooperatorData) + '</td>');
		//商家
		var cooperatorData = (undefined == data.content[i].sellerSignCount ||data.content[i].sellerSignCount == 0)?data.content[i].bnum:'<a href="javascript:viod(); " data-type="ajax"   data-url="business_area/businessArea/init/getbusinessSignedInfo/init.jhtml?cityNameid='+data.content[i].cityNameid+'"  data-toggle="modal" data-title="已签约商家" data-width="800px" data-backdrop="static">' +   data.content[i].sellerSignCount + '</a>'
		html.push('<td>' + (undefined == cooperatorData ? "0" : cooperatorData) + '</td>');
	/*	html.push('<td>' + (undefined == data.content[i].teamName ? "-" : data.content[i].teamName) + '</td>');*/
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
/*function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/orderinvoice/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					orderinvoiceList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/

