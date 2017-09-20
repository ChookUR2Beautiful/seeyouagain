var sellerDetailedList;
$(document).ready(function() {
	sellerDetailedList = $('#sellerDetailedList').page({
		url : 'businessman/sellerDetailed/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(sellerDetailedList.getIds());
	});

	inserTitle(' > 商家管理 > <a href="businessman/sellerDetailed/init.jhtml" target="right">点赞管理</a>', 'userSpan', true);

	$("#export").click(function() {
		$form = $("#searchForm").attr("action", "businessman/sellerDetailed/export.jhtml");
		$form[0].submit();
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">点赞管理</caption>');
	html.push('<thead>');
	html.push('<tr>');
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:10%;">操作</th>');
	}
	
	html.push('<th style="width:10%;">商家名称</th>');
	html.push('<th style="width:10%;">人均消费</th>');
	html.push('<th style="width:10%;">返现描述</th>');
	html.push('<th style="width:10%;">是否有WIFI</th>');
	html.push('<th style="width:10%;">是否有停车位</th>');
	html.push('<th style="width:10%;">商家介绍</th>');
	html.push('<th style="width:10%;">推荐菜品</th>');
	html.push('<th style="width:10%;">参考地标</th>');
	html.push('<th style="width:10%;">点赞数</th>');

	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
			for (var i = 0; i < data.content.length; i++) {
				var iswifi = data.content[i].iswifi;
				var iswifi1 = "";
				// 0=没有|1=免费提供|2=有偿提供
				if (iswifi == 0) {
					iswifi1 = "没有";
				} else if (iswifi == 1) {
					iswifi1 = "免费提供";
				} else if (iswifi == 2) {
					iswifi1 = "有偿提供";
				}
		
				var isparking = data.content[i].isparking;
				var isparking1 = "";
				// 0=没有|1=免费停车位|2=有偿停车位
				if (isparking == 0) {
					isparking1 = "没有";
				} else if (isparking == 1) {
					isparking1 = "免费停车位";
				} else if (isparking == 2) {
					isparking1 = "有偿停车位";
				}
		
				html.push('<tr>');
				
				if(!isEmptyObject(permissions)){
					html.push('<td>');
					if(permissions.update == 'true'){
						html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="businessman/sellerDetailed/update/init.jhtml?sellerid=' + data.content[i].sellerid + '"  data-toggle="modal" >修改点赞数</a>');
					}
					html.push('</td>');
				}
				//商家名称
				var sellername = (undefined == data.content[i].sellername ? "-" : data.content[i].sellername);
				html.push('<td title ="'+data.content[i].sellername+'">' + substr(sellername) + '</td>');
				
				html.push('<td>' + (undefined == data.content[i].consume ? "-" : data.content[i].consume) + '</td>');
				//返现描述
				var returnrmb = (undefined == data.content[i].returnrmb ? "-" : data.content[i].returnrmb);
				html.push('<td title ="'+data.content[i].returnrmb+'">' + substr(returnrmb,10) + '</td>');
				html.push('<td>' + (undefined == iswifi1 ? "-" : iswifi1) + '</td>');
				html.push('<td>' + (undefined == isparking1 ? "-" : isparking1) + '</td>');
				//商家介绍
				var introduce = (undefined == data.content[i].introduce ? "-" : data.content[i].introduce)
				html.push('<td title ="'+data.content[i].introduce+'">' + substr(introduce,10) + '</td>');
				//推荐菜品
				var dishes = (undefined == data.content[i].dishes ? "-" : data.content[i].dishes);
				html.push('<td title ="'+data.content[i].dishes+'">' + substr(dishes,10) + '</td>');
				//参考地表
				var landmark = (undefined == data.content[i].landmark ? "-" : data.content[i].landmark);
				html.push('<td title ="'+data.content[i].landmark+'">' + substr(landmark,10) + '</td>');
				html.push('<td>' + (undefined == data.content[i].number ? "-" : data.content[i].number) + '</td>');
		
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
function remove(sellerid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/sellerDetailed/delete.jhtml' + '?t=' + Math.random(),
			data : 'sellerid=' + sellerid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					sellerDetailedList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}
