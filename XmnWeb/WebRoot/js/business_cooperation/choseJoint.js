var jointList;
$(document).ready(function() {
	jointList = $('#jointList').page({
		url : 'business_cooperation/appPush/init/choseJoint/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	//区域
	var ld = $("#ld").areaLd({
		isChosen : false
	});
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});
	
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">合作商</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:80px;"><input type="checkbox" id="allCheck"  onclick="checkSome(this);" ></input></th>');
	html.push('<th style="width:200px;">公司名称</th>');
	
	html.push('<th style="width:200px;">法人姓名</th>');
	html.push('<th style="width:200px;">联系人手机</th>');
	html.push('<th style="width:200px;">登陆账号</th>');
	html.push('<th style="width:100px;">城市</th>');
	html.push('<th style="width:200px;">区域</th>');
	html.push('<th style="width:200px;">地址</th>');
	html.push('<th style="width:200px;">开通时间</th>');
	html.push('<th style="width:70px;">状态</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			html.push('<td><input type="checkbox" items="oneCheck" name="'+data.content[i].jointid+'" sellerid="'+data.content[i].jointid+'" sellername="'+data.content[i].corporate+'"  onclick=\'javascript:window.parent.searchChosen.chose("'+data.content[i].jointid+'","'+data.content[i].corporate+'",this);\'></input>&nbsp;&nbsp;</td>');
			var corporate = (undefined == data.content[i].corporate ? "-" : data.content[i].corporate);
			html.push('<td title="'+corporate+'">' + substr(corporate,8) + '</td>');
			
			html.push('<td>' + (undefined == data.content[i].legalperson ? "-" : data.content[i].legalperson) + '</td>');
			html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].loginAccount ? "-" : data.content[i].loginAccount) + '</td>');
			html.push('<td>' + (undefined == data.content[i].cityTitle ? "-" : data.content[i].cityTitle) + '</td>');
			html.push('<td>' + (undefined == data.content[i].areaTitle ? "-" : data.content[i].areaTitle) + '</td>');
			var address = (undefined == data.content[i].address ? "-" : data.content[i].address);
			html.push('<td title="'+address+'">' + substr(address,8) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
			
			html.push('</tr>');
		}
	}else{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
	window.parent.searchChosen.choseload();
}
   $("#allCancel").click(function(){//取消按钮
	  window.parent.searchChosen.allCancel();
	});