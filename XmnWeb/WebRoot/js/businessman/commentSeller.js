var pageDiv;
$(document).ready(function() {

	pageDiv = $('#sellerInfoDiv').page({
		url : 'businessman/comment/seller/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'sellerFromId'
	});
	
	//区域
	var ld = $("#ld").areaLd({
		isChosen : true
	});
	
	$("input[data-bus=reset]").click(function(){
		$(".form-control").attr("value","");
		$("#ld").find("select").trigger("chosen:updated");
	});
	
	//删除
	$('#delete').click(function(){
		var  data = pageDiv.getIds();
		var arryIds = {'ids' : data};
		$.ajax({
	        type: "POST",
	        url: "businessman/seller/beachDeleteSeller.jhtml",
	        data: arryIds,
	        success: function(result){
	        	window.messager.success(result.msg);
	        	pageDiv.reload();
	         }
	    });
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

/**
 * 单个删除数据
 * @param id
 */
function deleteSeller(id){
	var data = {'ids' : id};
	$.ajax({
        type: "POST",
        url: "businessman/seller/beachDeleteSeller.jhtml",
        data: data,
        success: function(result){
        	window.messager.success(result.msg);
        	pageDiv.reload();
         }
    });
}

/**
 * 转换from表单
 */
function jsonFromt(data){
	var json = {};
	for(var i=0; i<data.length; i++){
		json[data[i].name] = data[i].value;
	}
	return json;
}


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html = [];
	inserTitle(' > 商家管理 > <a href="businessman/comment/seller/init.jhtml" target="right">全部商家评论</a>','commentSellerList',true);
	
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">全部商家评论</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:10%;">商家编号</th>');
	html.push('<th style="width:15%;">商家名称</th>');
	html.push('<th style="width:15%;">加入时间</th>');
	html.push('<th style="width:20%;">地址</th>');	
	html.push('<th style="width:10%;">咨询电话</th>');
	html.push('<th style="width:20%;">区域</th>');
	html.push('<th style="width:10%;">评论总数</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#sellerFromId").serialize());
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			//商家编号
			var sellerid = data.content[i].sellerid?data.content[i].sellerid:'-';
			html.push('<td>'+sellerid+'</td>');
			
			var sellername = data.content[i].sellername?data.content[i].sellername:'-';
			html.push('<td class="text-ellipsis" title ="'+sellername+'">'+substr(sellername,8)+'</td>');	
			
			//时间
			var signdate = data.content[i].signdate?data.content[i].signdate:'-';
			html.push('<td>'+signdate+'</td>');
			
			//地址
			var address = data.content[i].address?data.content[i].address:'-';
			html.push('<td class="text-ellipsis" title ="'+address+'">'+substr(address,8)+'</td>');
			//咨询电话
			var tel = data.content[i].tel?data.content[i].tel:'-';
			html.push('<td class="text-ellipsis">'+tel+'</td>');
			//区域
			var title = data.content[i].title?data.content[i].title:'-';
			html.push('<td class="text-ellipsis">'+title+'</td>');
			if(permissions.commentInit && data.content[i].commentCount > 0){
				html.push('<td class="text-ellipsis"><a href="businessman/comment/init.jhtml?sellerid='+data.content[i].sellerid+callbackParam+'">'+(data.content[i].commentCount?data.content[i].commentCount:'0')+'</a></td>');
			}else{
				html.push('<td class="text-ellipsis">'+(data.content[i].commentCount?data.content[i].commentCount:'0')+'</td>');
			}
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