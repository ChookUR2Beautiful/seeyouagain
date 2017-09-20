var allianceAccountList;
var id = $("#id").val();
$(document).ready(function() {
	allianceAccountList = $('#allianceAccountList').page({
		url : 'businessman/allianceShop/allianceAccount/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		param:{id : id}
	});
	
	inserTitle(' > <a href="businessman/allianceShop/allianceAccount/init.jhtml?id='+id+'" target="right">联盟店账号</a>','allianceAccount',false);
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">联盟店账号</caption>');
	html.push('<thead>');
	html.push('<tr>');
	if(permissions.xg=='true'){
		html.push('<th style="width:100px;">操作</th>');
	}
	
	html.push('<th >所属联盟店</th>');
	html.push('<th >登录帐号</th>');
	html.push('<th >帐号昵称</th>');	
	html.push('<th >真实姓名</th>');
	html.push('<th >联系人手机</th>');
	html.push('<th >添加时间</th>');
	html.push('<th >状态</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(permissions.xg=='true'){
				html.push('<td><a href="javascript:void();" data-type="ajax" data-url="businessman/allianceShop/allianceAccount/update/init.jhtml?aid='+data.content[i].aid+'" data-toggle="modal">修改</a>&nbsp;&nbsp;</td>');
			}
			
			html.push('<td>' + (undefined == data.content[i].allianceName ? "-" : substr(data.content[i].allianceName,8)) + '</td>');
			html.push('<td>' + (undefined == data.content[i].account ? "-" : data.content[i].account) + '</td>');
			html.push('<td title ="'+data.content[i].nname+'">' + (undefined == data.content[i].nname ? "-" :substr(data.content[i].nname,8)) + '</td>');
			html.push('<td title ="'+data.content[i].fullname+'">' + (undefined == data.content[i].fullname ? "-" :substr(data.content[i].fullname,8)) + '</td>');
			html.push('<td>' + (undefined == data.content[i].phone ? "-" : data.content[i].phone) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			var status = data.content[i].status;
			var statusStr;
			statusStr=(status==0?"启用":status==1?"不启用":"-");
			
			html.push('<td>' + statusStr + '</td>');
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

