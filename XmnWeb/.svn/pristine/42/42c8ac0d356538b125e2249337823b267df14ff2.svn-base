var businessAreaList;
$(document).ready(function() {
	businessAreaList = $('#staffRankingList').page({
		url : 'business_area/staffRanking/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

/*	$('#delete').click(function() {
		remove(orderinvoiceList.getIds());
	});*/
	
	
/*	var ld = $("#ysqy").areaLd({
		isChosen : true,
		showConfig : [{name:"province",tipTitle:"--省--"},{name:"cityName",tipTitle:"--市--"},{name:"areaName",tipTitle:"--区域--"}]
	});*/
   var ld = $("#ysqy").areaLd({
		isChosen : true,
		showConfig : [{name:"tpNameid",tipTitle:"--省--"},{name:"cityNameid",tipTitle:"--市--"},{name:"areaNameid",tipTitle:"--区--"}]
	});
	
		//合作商(搜索查询)
	$("#jointid").chosenObject({
		id : "jointid",
		hideValue : "jointid",
		showValue : "corporate",
		url : "business_cooperation/joint/jointList.jhtml",
		limit : -1,
		isChosen:true
	});
	
	$("input[data-bus=reset]").click(function(){
		$("#ysqy").find("select").trigger("chosen:updated");
		$("#jointid").trigger("chosen:updated");
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
	
	inserTitle(' > 商务区域管理  > <a href="business_area/staffRanking/init.jhtml" target="right">业务员排行</a>','orderinvoiceSpan',true);

	
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">业务员排行</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	/*html.push('<th style="width:20px;"><input type="checkbox" /></th>');	*/
	html.push('<th style="width:140px;">员工姓名</th>');
	html.push('<th style="width:140px;">手机号码</th>');
/*	html.push('<th style="width:100px;">所属公司</th>');*/
	html.push('<th style="width:100px;">公司法人</th>');
	html.push('<th style="width:100px;">已签约数</th>');
	html.push('<th style="width:100px;">审核中数</th>');
	/*html.push('<th style="width:100px;">未验证数</th>');*/
	html.push('<th style="width:100px;">签到数</th>');
	html.push('<th style="width:100px;">未通过数</th>');
	/*html.push('<th style="width:100px;">审核中数</th>');*/
	html.push('<th style="width:100px;">商家总数</th>');
	html.push('<th style="width:100px;">所属合作商</th>');
	html.push('<th style="width:100px;">所在城市</th>');
	html.push('<th style="width:100px;">所属区域</th>');
		
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');

if(null != data && data.content.length > 0)
	{		
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
/*		html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');	*/				
		html.push('<td>' + (undefined == data.content[i].fullname ? "-" : data.content[i].fullname) +'</td>');
		html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
	/*	html.push('<td>' + (undefined == data.content[i].corporate ? "-" : data.content[i].corporate) + '</td>');*/
		html.push('<td>' + (undefined == data.content[i].legalperson ? "-" : data.content[i].legalperson) + '</td>');
		if(data.content[i].sellerSignCount==0){
			html.push('<td>' +"0"+ '</td>');
		}else{
			var td = (undefined == data.content[i].sellerSignCount ? "-" :'<a href="javascript:viod();" data-type="ajax"   data-url="business_area/staffRanking/init/signedInfo/init.jhtml?staffid='+data.content[i].staffid+'"  data-toggle="modal" data-title="已签约详细信息" data-width="800px" data-backdrop="static">' +   data.content[i].sellerSignCount + '</a>')
			//已签约
	      html.push('<td>'+td+'</td>');
		}
		if(data.content[i].sellerAuditCount==0){
			html.push('<td>' +"0"+ '</td>');
		}else{
			var td = (undefined == data.content[i].sellerAuditCount ? "-" :'<a href="javascript:viod();" data-type="ajax"   data-url="business_area/staffRanking/init/ExamineBusinesses/init.jhtml?staffid='+data.content[i].staffid+'"  data-toggle="modal" data-title="审核中详细信息" data-width="800px" data-backdrop="static">' +   data.content[i].sellerAuditCount + '</a>')
			//审核中数
			html.push('<td>'+td+'</td>');
		}
		if(data.content[i].registerRecordCount==0){
			html.push('<td>' +"0"+ '</td>');
		}else{
			var td = (undefined == data.content[i].registerRecordCount? "-" :'<a href="javascript:viod();" data-type="ajax"   data-url="business_area/staffRanking/init/initSign/init.jhtml?staffid='+data.content[i].staffid+'"  data-toggle="modal" data-title="已签到详细信息" data-width="800px" data-backdrop="static">' +   data.content[i].registerRecordCount + '</a>')
			//已签到
			html.push('<td>'+td+'</td>');
		}
		if(data.content[i].sellerNoPassCount==0){
			html.push('<td>' +"0"+ '</td>');
		}else{
			var td = (undefined == data.content[i].sellerNoPassCount ? "-" :'<a href="javascript:viod();" data-type="ajax"   data-url="business_area/staffRanking/init/NoPassBusinesses/init.jhtml?staffid='+data.content[i].staffid+'"  data-toggle="modal" data-title="未通过详细信息" data-width="800px" data-backdrop="static">' +   data.content[i].sellerNoPassCount + '</a>')
			//未通过数
			html.push('<td>'+td+'</td>');
		}
		if(data.content[i].sellerCount==0){
			html.push('<td>' +"0"+ '</td>');
		}else{
			var td = (undefined == data.content[i].sellerCount? "-" :'<a href="javascript:viod();" data-type="ajax"   data-url="business_area/staffRanking/init/initBusinesses/init.jhtml?staffid='+data.content[i].staffid+'"  data-toggle="modal" data-title="商家详细信息" data-width="800px" data-backdrop="static">' +   data.content[i].sellerCount + '</a>')
			//商家数
			html.push('<td>'+td+'</td>');
		}
		html.push('<td>' + (undefined == data.content[i]. corporate? "-" : data.content[i].corporate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].cityName ? "-" : data.content[i].cityName) + '</td>');
		html.push('<td>' + (undefined == data.content[i].areaName ? "-" : data.content[i].areaName) + '</td>');
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

