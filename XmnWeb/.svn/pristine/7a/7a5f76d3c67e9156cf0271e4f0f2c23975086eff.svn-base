var pageDiv;
$(document).ready(function() {
	pageDiv = $('#chosenShopList').page({
		url : 'businessman/allianceShop/init/chosen/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'sellerFromId'
	});
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});
	
});
function checkSome(obj){
	$("input:checkbox").each(function(){
		this.checked=obj.checked;
	});
   $("input:checkbox").each(function(){
	  if(undefined!=$(this).attr("sellername")){
		   window.parent.searchChosen.chose($(this).attr("sellerid"),$(this).attr("sellername"),this);  
	   }
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家列表<font style="float:right;">共计【'+data.total+'】个商家&nbsp;</font></caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:5%;"><input type="checkbox" id="allCheck" onclick="checkSome(this);" ></th>');
	html.push('<th style="width:10%;">商家编号</th>');
	html.push('<th style="width:18%;">商家名称</th>');
	html.push('<th style="width:10%;">商家手机号</th>');
	html.push('<th style="width:15%;">地址</th>');	
	html.push('<th style="width:15%;">区域</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
					html.push('<td>');
					html.push('&nbsp;&nbsp;<input type="checkbox" items="oneCheck" name="'+data.content[i].sellerid+'" sellerid="'+data.content[i].sellerid+'" sellername="'+data.content[i].sellername+'"  onclick=\'javascript:window.parent.searchChosen.chose("'+data.content[i].sellerid+'","'+data.content[i].sellername+'",this);\'></input>&nbsp;&nbsp;');
					html.push('</td>');	
				html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
				
				//商家名称
				var sellername = data.content[i].sellername?data.content[i].sellername:'-';
				html.push('<td class="text-ellipsis" name="sellername" initValue="'+sellername+'" title ="'+sellername+'">'+substr(sellername,10)+'</td>');
				
				//商家手机号
				html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
					
				//地址
				var address = data.content[i].address?data.content[i].address:'-';
				html.push('<td class="text-ellipsis" title ="'+address+'">'+substr(address,10)+'</td>');
				//区域
				var title = data.content[i].sellerArea||'-';
				html.push('<td class="text-ellipsis" title ="'+title+'">'+ substr(title,10)+'</td>');
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
	window.parent.searchChosen.choseload();
}


