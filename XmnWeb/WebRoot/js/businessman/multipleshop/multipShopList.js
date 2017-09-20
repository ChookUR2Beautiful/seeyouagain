var pageDiv;
$(document).ready(function() {
	//区域
	/*var ld = $("#ld").areaLd({
		isChosen : true
	});*/
	
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
	//标题
	inserTitle(' > 商家管理 > <span><a href="businessman/multipShop/init.jhtml" target="right">连锁店管理</a>','sellerList',true);
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/businessman/multipShop/init.jhtml';
			location.href =url;
		}
		setTimeout(function(){
			$("#ld").find("select").trigger("chosen:updated");
		});
	});
	pageDiv = $('#sellerInfoDiv').page({
		url : 'businessman/multipShop/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'sellerFromId'
	});
	
});








/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	//20170315版本更改
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">连锁店列表<font style="float:right;">共计【'+data.total+'】个商家&nbsp;</font></caption>');
	html.push('<thead>');
	html.push('<tr>');
	if(permissions.xg=='true'){
		html.push('<th>操作</th>');
	}
//	html.push('<th>连锁店编号</th>');
	html.push('<th>连锁店名称</th>');
	html.push('<th>联系人&手机</th>');
//	html.push('<th>子店</th>');
//	if(permissions.zh=='true'){
//		html.push('<th>账号</th>');
//	}
		
//	html.push('<th>连锁店电话</th>');
	html.push('<th>详细地址</th>');
//	html.push('<th>区域</th>');
//	html.push('<th>参考地标</th>');
	html.push('<th>关联商户</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{		
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#sellerFromId").serialize());
		updateAddBtnHref("#addSellerBto",callbackParam)
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				if(permissions.xg=='true'||permissions.bindcard=='true'){
					html.push('<td>');
				}
				if(permissions.xg=='true'){
					html.push('<a href="businessman/multipShop/update/init.jhtml?sellerid='+data.content[i].sellerid+callbackParam+'">修改</a>&nbsp;&nbsp;');	
				}
				if(permissions.bindcard=='true'){
					html.push('|&nbsp;&nbsp;');
					html.push('<a href="businessman/multipShop/bindCardInit/init.jhtml?sellerid='+data.content[i].sellerid+'&sellername='+data.content[i].sellername+callbackParam+'" >绑定银行卡</a>');
				}
				if(permissions.xg=='true'||permissions.bindcard=='true'){
					html.push('</td>');
				}
				
//				html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
				
				//连锁店名称
				var sellername = data.content[i].sellername?data.content[i].sellername:'-';
				html.push('<td class="text-ellipsis" name="sellername" initValue="'+sellername+'" title ="'+sellername+'">'+substr(sellername,10)+'</td>');
				//联系人&手机
				var salesman = data.content[i].fullname? '( '+data.content[i].fullname+' ) ': '';
				html.push('<td>' + salesman + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
				
				//html.push('<td><a href="businessman/multipShop/subShopinit.jhtml?fatherid='+data.content[i].sellerid+callbackParam+'">查看</a>&nbsp;&nbsp;</td>');	
				//if(permissions.zh=='true'){
					//账号
					//html.push('<td class="text-ellipsis"><a href="businessman/multipShop/multipAccount/init.jhtml?sellerid='+data.content[i].sellerid+callbackParam+'&accountType=multip">查看</a></td>');	
				//}	
				//详细地址
				var address = data.content[i].address?data.content[i].address:'-';
				html.push('<td class="text-ellipsis" title ="'+address+'">'+substr(address,10)+'</td>');
				//连锁店电话
				//var tel = data.content[i].tel?data.content[i].tel:'-';
				//html.push('<td class="text-ellipsis" title ="'+tel+'">'+substr(tel,8)+'</td>');
				//区域
				//var title = data.content[i].title?data.content[i].title:'-';
				//html.push('<td class="text-ellipsis" title ="'+title+'">'+ substr(title,10)+'</td>');
				//var landmark = data.content[i].landmark?data.content[i].landmark:'-';
				//html.push('<td class="text-ellipsis" title ="'+landmark+'">'+ substr(landmark,10)+'</td>');
				
				//关联商户数
				var subShopNum = data.content[i].subShopNum?data.content[i].subShopNum:'0';  //子店数
				html.push('<td><a href="businessman/multipShop/subShopinit.jhtml?fatherid='+data.content[i].sellerid+callbackParam+'">'+subShopNum+'</a>&nbsp;&nbsp;</td>');	
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


