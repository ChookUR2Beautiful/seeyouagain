var hotWordsList;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	hotWordsList = $('#hotWordsList').page({
		url : 'marketingManagement/hotWords/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(hotWordsList.getIds());
	});
	inserTitle(' > 订单管理 > <a href="marketingManagement/hotWords/init.jhtml" target="right"> 热门搜索设置</a>','hotWordsSpan',true);	
});

$(function(){
	//区域联动
	 $("#ld").areaLd({
			isChosen : true,
			showConfig : [ {
				name : "province",
				tipTitle : "--省--",
				width : '49%'
			}, {
				name : "city",
				tipTitle : "--市--",
				width : '49%'
			}]
		});
	 //重置
   $("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">热门搜索设置</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var hasHandle = permissions && (permissions.update || permissions.del);
	if(hasHandle){
/*		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
*/		html.push('<th style="width:80px;">操作</th>');
	}
	html.push('<th >城市</th>');	
	html.push('<th >热门关键词</th>');
	html.push('<th >热词来源</th>');
	html.push('<th >搜索次数</th>');
	html.push('<th >排序</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			var type=data.content[i].hotType;
			var types="";
			if(hasHandle){
				if(type==1){
/*				html.push('<th><input type="checkbox" val=' + data.content[i].hid + '  /></th>');
*/				html.push('<td>');
				}else{
/*					html.push('<th></th>');
*/					html.push('<td>');
				}
				if(permissions.update && type==1){
					html.push('<a href="javascript:void();" data-title="" data-type="ajax" data-position="" data-width="660px" data-url="marketingManagement/hotWords/update/init.jhtml?hid='+data.content[i].hid+'" data-toggle="modal">修改</a>&nbsp;&nbsp;');
					
				}
				if(permissions.del && type==1){
					html.push('<a href="javascript:remove('+data.content[i].hid+')">删除</a>&nbsp;&nbsp;');
				}
				html.push('</td>');
			}
			html.push('<td>' + (undefined == data.content[i].provinceName + "-"+ data.content[i].title ? "-" : data.content[i].provinceName + "-"+ data.content[i].title) + '</td>');
			html.push('<td>' + (undefined == data.content[i].hotWord ? "-" : data.content[i].hotWord) + '</td>');
			
			if(type==1){types="手工添加";}else if(type==2){types="真实搜索";}else{types="";}
			html.push('<td>' + (undefined == types ? "-" : types) + '</td>');
			html.push('<td>' + (undefined == data.content[i].hotNum ? "-" : data.content[i].hotNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].hotOrder ? "-" : data.content[i].hotOrder) + '</td>');
			html.push('</tr>');
		}
	}else{
		html.push('<tr>');
		html.push('<td colspan="100">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 删除
 */
function remove(hid) {
	if(!hid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'marketingManagement/hotWords/delete.jhtml' + '?hid=' + hid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					hotWordsList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}

