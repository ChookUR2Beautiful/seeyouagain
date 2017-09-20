var sellerAccountList;
var accountType = $("#accountType").val();
$(document).ready(function() {
	sellerAccountList = $('#sellerAccountList').page({
		url : 'businessman/multipShop/subShop/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});
	
	inserTitle(' > <a href="businessman/multipShop/subShop/init.jhtml?fatherid='+$("#fatherid")+'" target="right">关联商户</a>','sellerAccounList',false);
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">关联商户列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th >商家编号</th>');
	html.push('<th >商家名称</th>');	
//	html.push('<th >商家联系电话</th>');
	html.push('<th >商家地址</th>');
//	html.push('<th >区域</th>');
	html.push('<th>授权管理</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0)
	{  
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
			html.push('<td title ="'+data.content[i].sellername+'">' + (undefined == data.content[i].sellername ? "-" :substr(data.content[i].sellername,8)) + '</td>');
//			html.push('<td title ="'+data.content[i].tel+'">' + (undefined == data.content[i].tel ? "-" :data.content[i].tel) + '</td>');
			html.push('<td>' + (undefined == data.content[i].address ? "-" : data.content[i].address) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].title ? "-" : data.content[i].title) + '</td>');
			html.push('<td><a href="javascript:void();" data-title="授权管理" data-type="ajax" data-position="100"  data-width="33%"  data-url="businessman/sellerDetailed/authorizedInit.jhtml?sellerid='+data.content[i].sellerid+'" data-toggle="modal">授权管理</a></td>');	
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
function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

