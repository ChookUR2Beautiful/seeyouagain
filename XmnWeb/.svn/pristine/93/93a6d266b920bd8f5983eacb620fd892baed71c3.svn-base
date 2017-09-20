var sellerList;
$(document).ready(function() {
	sellerList = $('#sellerList').page({
		url : 'businessman/sellerMsg/init/sellerList.jhtml',
		success : success,
		pageBtnNum : 10,
//		param : {area : $("#area").val(),city : $("#city").val(),province : $("#province").val(),zoneid : $("#zoneid").val()},
		paramForm : 'searchForm'
	});
	
/*	//商圈
	$("#zoneid").chosenObject({
		id : "zoneid",
		hideValue : "bid",
		showValue : "title",
		url : "common/business/businessInfo.jhtml",
		limit : -1,
		isChosen:true
	});*/
	
	$("input[data-bus=reset]").click(function(){
		$("#zoneid").trigger("chosen:updated");
	});
	//保存商家信息
	save(sellerList.getIds());
	//返回
	back();
});

/**
 * 保存商家
 */
function save(id){
	$("#saveSellerId").on("click",function(){
		console.info($("input[name=sendObject]"));
		alert(id);
			$("input[name=sendObject]").val(id);
			$('#triggerModal').modal('hide');
	});
}

/**
 * 返回
 */
function back(){
	$("#sellerBavkId").on("click",function(){
			$('#triggerModal').modal('hide');
	});
}



/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	/*html.push('<th style="width:3%;"><input type="checkbox" /></th>');*/
	html.push('<th style="width:10%;">商家编号</th>');
	html.push('<th style="width:15;">商家名称</th>');
	html.push('<th style="width:15%;">商家帐号</th>');
	html.push('<th style="width:10%;">所在省</th>');
	html.push('<th style="width:10%;">所在市</th>');
	html.push('<th style="width:10%;">所在区</th>');
	html.push('<th style="width:22%;">所在商圈</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			/*html.push('<th><input type="checkbox" val=' + data.content[i].sellerid + '  /></th>');*/
			//商家编号
			html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
			
			//商家名称
			html.push('<td>' + (undefined == data.content[i].sellername ? "-" : data.content[i].sellername) + '</td>');
			
			//商家帐号
			var account = (undefined == data.content[i].account ? "-" : data.content[i].account);
			html.push('<td title ="'+account+'">' + account + '</td>');
			//所在省
			var ptitle = (undefined == data.content[i].ptitle ? "-" : data.content[i].ptitle);
			html.push('<td title ="'+ptitle+'">' + ptitle + '</td>');
			//所在市
			html.push('<td>' + (undefined == data.content[i].ctitle ? "-" : data.content[i].ctitle) + '</td>');
			//所在区
			html.push('<td>' + (undefined == data.content[i].atitle ? "-" : data.content[i].atitle) + '</td>');
			//所在商圈
			html.push('<td>' + (undefined == data.content[i].btitle ? "-" : data.content[i].btitle) + '</td>');
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

