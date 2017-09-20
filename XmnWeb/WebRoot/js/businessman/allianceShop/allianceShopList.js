var pageDiv;
$(document).ready(function() {
	//标题
	inserTitle(' > 商家管理 > <span><a href="businessman/allianceShop/init.jhtml" target="right">区域代理管理</a>','allianceShop',true);
	pageDiv = $('#allianceShopDiv').page({
		url : 'businessman/allianceShop/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'allianceShopFrom'
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">区域代理列表<font style="float:right;">共计【'+data.total+'】个区域代理&nbsp;</font></caption>');
	html.push('<thead>');
	html.push('<tr>');
	if(permissions.xg=='true'){
		html.push('<th style="width:10%;">操作</th>');
	}
//	html.push('<th style="width:5%;">联盟店编号</th>');
	html.push('<th style="width:15%;">区域代理名称</th>');
	html.push('<th style="width:15%;">联系人&手机</th>');
	/*if(permissions.zh=='true'){
		html.push('<th style="width:8%;">账号</th>');
	}*/
	html.push('<th style="width:10%;">详细地址</th>');	
//	html.push('<th style="width:15%;">区域</th>');
	html.push('<th style="width:15%;">商圈</th>');
	html.push('<th style="width:15%;">关联商户</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(data && data.content.length > 0)
	{
			var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#allianceShopFrom").serialize());
			updateAddBtnHref("#addbtn",callbackParam);
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				if(permissions.xg=='true'||permissions.bindcard=='true'){
					html.push('<td>');
				}
				if(permissions.xg=='true'){
					html.push('<a href="businessman/allianceShop/update/init.jhtml?id='+data.content[i].id+callbackParam+'">修改</a>');
				}
				if(permissions.bindcard=='true'){
					html.push('|&nbsp;&nbsp;');
					html.push('<a href="businessman/allianceShop/bindCardInit/init.jhtml?sellerid='+data.content[i].id+'&sellername='+data.content[i].allianceName+callbackParam+'" >绑定银行卡</a>');
				}
				if(permissions.xg=='true'||permissions.bindcard=='true'){
					html.push('</td>');
				}
//				html.push('<td>' + (data.content[i].id||"-") + '</td>');
				
				//区域代理名称
				var allianceName = data.content[i].allianceName||'-';
				html.push('<td class="text-ellipsis" name="sellername" initValue="'+allianceName+'" title ="'+allianceName+'">'+substr(allianceName,10)+'</td>');
				//联系人&手机
				var salesman = data.content[i].salesman? '( '+data.content[i].salesman+' ) ': '';
				html.push('<td>' + salesman + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
				
				/*if(permissions.zh=='true'){
					//账号
					html.push('<td class="text-ellipsis"><a href="businessman/allianceShop/allianceAccount/init.jhtml?id='+data.content[i].id+callbackParam+'">查看</a></td>');	
				}	*/
				//详细地址
				var address = data.content[i].address||'-';
				html.push('<td class="text-ellipsis" title ="'+address+'">'+substr(address,10)+'</td>');
				
				/*var allAreaName = data.content[i].allAreaName||"-";
				html.push('<td class="text-ellipsis" title ="'+allAreaName+'">'+ substr(allAreaName,10)+'</td>');*/
				//商圈
				var allAreaName = data.content[i].allAreaName||"-";
				html.push('<td class="text-ellipsis" title ="'+allAreaName+'">'+ substr(allAreaName,10)+'</td>');
				//关联商户
				var subShopNum = data.content[i].subShopNum?data.content[i].subShopNum:'0';  //子店数
				html.push('<td><a href="businessman/allianceShop/subShopinit.jhtml?id='+data.content[i].id+callbackParam+'">'+subShopNum+'</a>&nbsp;&nbsp;</td>');	
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


