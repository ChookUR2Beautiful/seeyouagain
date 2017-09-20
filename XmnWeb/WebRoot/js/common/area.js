var areaList;
$(document).ready(function() {
	//inserTitle(' > 系统管理 > <a href="common/area/init.jhtml" target="right">区域管理</a>','userSpan',true);
	inserTitle(' > 数据字典管理 > <a href="common/area/init.jhtml" target="right">区域管理</a>','userSpan',true);
	areaList = $('#areaList').page({
		url : 'common/area/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(areaList.getIds());
	});
	
	//区域搜索中查询
	var ld = $("#areaLdId").areaLd({
		isChosen : true,
		showConfig : [{name:"pareaId",tipTitle:"--省--"},{name:"careaId",tipTitle:"--市--"},{name:"aareaId",tipTitle:"--区--"}]
	});
	//重置选择查询条件
	$("input[data-bus=reset]").click(function(){		
		$("#areaLdId").find("select").trigger("chosen:updated");
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">区域列表</caption>');

	html.push('<thead>');
	html.push('<tr>');
	var b = permissions.xg==''&&permissions.sc=='';
	if(!b){
			//html.push('<th style="width:5%;"><input type="checkbox" /></th>');
			html.push('<th style="width:3%;">操作</th>');
	}
	html.push('<th style="width:8%;">省编号</th>');
	html.push('<th style="width:12%;">省份</th>');
	html.push('<th style="width:8%;">市编号</th>');
	html.push('<th style="width:12%;">城市</th>');
	html.push('<th style="width:8%;">区域编号</th>');
	html.push('<th style="width:12%;">区域</th>');
	html.push('<th style="width:15%;">所属合作商</th>');
	html.push('<th style="width:2%;">商圈</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{	
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		if(!b){
			html.push('<td>');
			if(permissions.xg == 'true'){
				html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="common/area/update/init.jhtml?areaId='+data.content[i].aareaId+'"  data-toggle="modal" >修改</a>');
			}
			/*if(permissions.sc == 'true'&&data.content[i].corporate==null){
				html.push('&nbsp;&nbsp;<a href="javascript:remove('+data.content[i].aareaId+')">删除</a>');
			}*/
			html.push('</td>');
		}
		html.push('<td>' + (undefined == data.content[i].pareaId ? "-" : data.content[i].pareaId) + '</td>');
		//省
		html.push('<td>' + (undefined == data.content[i].ptitle ? "-" : data.content[i].ptitle) + '</td>');
		
		html.push('<td>' + (undefined == data.content[i].aareaId ? "-" : data.content[i].aareaId) + '</td>');
		//市
		html.push('<td>' + (undefined == data.content[i].ctitle ? "-" : data.content[i].ctitle) + '</td>');
		//区
		html.push('<td>' + (undefined == data.content[i].careaId ? "-" : data.content[i].careaId) + '</td>');
		
		html.push('<td>' + (undefined == data.content[i].atitle ? "-" : data.content[i].atitle) + '</td>');
		//所属合作商
		html.push('<td>' + (undefined == data.content[i].corporate ? "-" : data.content[i].corporate) + '</td>');
		var td = (undefined == data.content[i].bnum ||data.content[i].bnum==0)?data.content[i].bnum:'<a href="javascript:viod(); " data-type="ajax"   data-url="common/area/init/areaInBusiness/init.jhtml?areaId='+data.content[i].aareaId+'"  data-toggle="modal" data-title="商圈数量" data-width="800px" data-backdrop="static">' +   data.content[i].bnum + '</a>'
		//商圈数
		html.push('<td>'+td+'</td>');
		html.push('</tr>');
	}
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="9">暂无数据</td>');
		html.push('</tr>');
	}
	
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 删除
 */
/*function remove(areaId) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'common/area/delete.jhtml' + '?t=' + Math.random(),
			data : 'areaId=' + areaId,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					areaList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/

