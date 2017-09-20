var businessList;
$(document).ready(function() {
	//inserTitle(' > 系统管理 > <a href="common/business/init.jhtml" target="right">商圈管理</a>','userSpan',true);
	inserTitle(' > 基础数据管理 > <a href="common/business/init.jhtml" target="right">商圈管理</a>','userSpan',true);
	businessList = $('#businessList').page({
		url : 'common/business/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});
		
	//区域查询
	var ld = $("#areaLdIds").areaLd({
		isChosen : true,
		showConfig : [{name:"tpareaid",tipTitle:"--省--"},{name:"tcareaid",tipTitle:"--市--"},{name:"taareaid",tipTitle:"--区--"}]
	});
	
	//商圈查询
	$("#bid").chosenObject({
		id : "bid",
		hideValue : "bid",
		showValue : "title",
		url : "common/business/businessInfo.jhtml",
		isChosen:true
	});
	//重置选择查询条件
	$("input[data-bus=reset]").click(function(){		
		$("#areaLdIds").find("select").trigger("chosen:updated");
		$("#bid").trigger("chosen:updated");
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
		html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商圈列表</caption>');
		html.push('<thead>');
		html.push('<tr>');
	var Ispermissions=isEmptyObject(permissions);
	if(!Ispermissions){
			//html.push('<th style="width:5%;"><input type="checkbox" /></th>');
			html.push('<th style="width:10%;">操作</th>');
	}
		html.push('<th style="width:15%px;">省份</th>');
		html.push('<th style="width:20%;">城市</th>');
		html.push('<th style="width:20%;">区域</th>');
/*	html.push('<th style="width:200px;">所属合作商</th>');*/
		html.push('<th style="width:15%;">商圈</th>');
		html.push('<th style="width:15%;">更新时间</th>');
		html.push('</tr>');
		html.push('</thead>');
		html.push('<tbody');
	
if(null != data && data.content.length > 0)
	{
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		if(!Ispermissions){
			/*if(permissions.sqsc=='true' && data.content[i].isSeller==0){
				html.push('<th><input type="checkbox" val=' + data.content[i].bid + '  /></th>');
			}else {
				html.push('<th></th>');
			}*/
			html.push('<td>');
			if(permissions.sqxg=='true'){
			html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="common/business/update/init.jhtml?bid='+data.content[i].bid+'"  data-toggle="modal" >修改</a>');
			}
		/*	if(permissions.sqsc=='true' && data.content[i].isSeller==0){
				html.push('&nbsp;&nbsp;<a href="javascript:remove('+data.content[i].bid+')">删除</a>');
			}*/
			html.push('</td>');
		}
		//省
		html.push('<td>' + (undefined == data.content[i].tptitle ? "-" : data.content[i].tptitle) + '</td>');
		//市
		html.push('<td>' + (undefined == data.content[i].tctitle ? "-" : data.content[i].tctitle) + '</td>');
		//区
		html.push('<td>' + (undefined == data.content[i].tatitle ? "-" : data.content[i].tatitle) + '</td>');
		//所属合作商
		/*html.push('<td>' + (undefined == data.content[i].corporate ? "-" : data.content[i].corporate) + '</td>');*/
		//商圈数
		html.push('<td>' + (undefined == data.content[i].title ? "-" : data.content[i].title) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
		
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

$('#delete').click(function() {	
		remove(businessList.getIds());
	});

/**
 * 删除
 */
/*function remove(bid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'common/business/delete.jhtml' + '?t=' + Math.random(),
			data : 'bid=' + bid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					businessList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/

