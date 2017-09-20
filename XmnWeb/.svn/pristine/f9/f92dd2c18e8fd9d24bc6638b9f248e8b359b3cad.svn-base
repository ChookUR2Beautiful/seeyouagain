$(document).ready(function() {
	//默认加载折扣页面
	changePage(1);
});

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
 * 改变页面信息
 * @param state
 */
function changePage(state){
	if(state == 1){
		$('#salesVolumeDiv').hide();
		$('#turnoverDiv').hide();
		$('#drawMoneyDiv').hide();
		$('#orderDiv').hide();
		$('#memberDiv').hide();
		$('#commissionDiv').hide();
		$('#manageDetailsDiv').show();
		var pageDiv = $('#manageDetailsDiv').page({
			url : 'businessman/seller/list.jhtml',
			success : success,
			pageBtnNum : 10,
		});
		//
		$('#manageDetailsSearch').click(function(){
			//获取表单信息
			var data = $('#manageDetailsFromId').serializeArray();
			data = jsonFromt(data);
			pageDiv.reload(data);
		});
	}else if(state == 2){
		$('#manageDetailsDiv').hide();
		$('#salesVolumeDiv').hide();
		$('#turnoverDiv').hide();
		$('#drawMoneyDiv').hide();
		$('#orderDiv').hide();
		$('#commissionDiv').hide();
		$('#memberDiv').show();
		var pageDiv = $('#memberDiv').page({
			url : 'businessman/seller/list.jhtml',
			success : successMember,
			pageBtnNum : 10,
		});
		//
		$('#manageDetailsSearch').click(function(){
			//获取表单信息
			var data = $('#manageDetailsFromId').serializeArray();
			data = jsonFromt(data);
			pageDiv.reload(data);
		});
	}else if(state == 3){
		$('#manageDetailsDiv').hide();
		$('#memberDiv').hide();
		$('#salesVolumeDiv').hide();
		$('#turnoverDiv').hide();
		$('#drawMoneyDiv').hide();
		$('#commissionDiv').hide();
		$('#orderDiv').show();
		
		var pageDiv = $('#orderDiv').page({
			url : 'businessman/seller/list.jhtml',
			success : successOrder,
			pageBtnNum : 10,
		});
		//
		$('#manageDetailsSearch').click(function(){
			//获取表单信息
			var data = $('#manageDetailsFromId').serializeArray();
			data = jsonFromt(data);
			pageDiv.reload(data);
		});
	}else if(state == 4){
		$('#manageDetailsDiv').hide();
		$('#memberDiv').hide();
		$('#turnoverDiv').hide();
		$('#drawMoneyDiv').hide();
		$('#orderDiv').hide();
		$('#commissionDiv').hide();
		$('#salesVolumeDiv').show();
		
		var pageDiv = $('#salesVolumeDiv').page({
			url : 'businessman/seller/list.jhtml',
			success : successSalesVolume,
			pageBtnNum : 10,
		});
		//
		$('#manageDetailsSearch').click(function(){
			//获取表单信息
			var data = $('#manageDetailsFromId').serializeArray();
			data = jsonFromt(data);
			pageDiv.reload(data);
		});
	}else if(state == 5){
		$('#manageDetailsDiv').hide();
		$('#memberDiv').hide();
		$('#salesVolumeDiv').hide();
		$('#drawMoneyDiv').hide();
		$('#orderDiv').hide();
		$('#commissionDiv').hide();
		$('#turnoverDiv').show();
		
		var pageDiv = $('#turnoverDiv').page({
			url : 'businessman/seller/list.jhtml',
			success : successTurnover,
			pageBtnNum : 10,
		});
		//
		$('#manageDetailsSearch').click(function(){
			//获取表单信息
			var data = $('#manageDetailsFromId').serializeArray();
			data = jsonFromt(data);
			pageDiv.reload(data);
		});
	}else if(state == 6){
		$('#manageDetailsDiv').hide();
		$('#memberDiv').hide();
		$('#salesVolumeDiv').hide();
		$('#turnoverDiv').hide();
		$('#orderDiv').hide();
		$('#drawMoneyDiv').hide();
		$('#commissionDiv').show();
		
		var pageDiv = $('#commissionDiv').page({
			url : 'businessman/seller/list.jhtml',
			success : successCommission,
			pageBtnNum : 10,
		});
		//
		$('#manageDetailsSearch').click(function(){
			//获取表单信息
			var data = $('#manageDetailsFromId').serializeArray();
			data = jsonFromt(data);
			pageDiv.reload(data);
		});
		
	}else if(state == 7){
		$('#manageDetailsDiv').hide();
		$('#memberDiv').hide();
		$('#salesVolumeDiv').hide();
		$('#turnoverDiv').hide();
		$('#orderDiv').hide();
		$('#commissionDiv').hide();
		$('#drawMoneyDiv').show();
		
		var pageDiv = $('#drawMoneyDiv').page({
			url : 'businessman/seller/list.jhtml',
			success : successDrawMoney,
			pageBtnNum : 10,
		});
		//
		$('#manageDetailsSearch').click(function(){
			//获取表单信息
			var data = $('#manageDetailsFromId').serializeArray();
			data = jsonFromt(data);
			pageDiv.reload(data);
		});
	}
}

/**
 * 折扣
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered  info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:20px; line-height:40px;">折扣信息</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
	html.push('<th style="width:100px;">操作</th>');
	html.push('<th style="width:100px;">ID</th>');
	html.push('<th style="width:150px;">操作时间</th>');
	html.push('<th style="width:150px;">折扣类型</th>');
	html.push('<th style="width:150px;">当前折扣</th>');
	html.push('<th style="width:150px;">当前折扣开始时间</th>');
	html.push('<th style="width:150px;">当前折扣结束时间</th>');	
	html.push('<th style="width:150px;">最新折扣</th>');
	html.push('<th style="width:150px;">最新折扣开始时间</th>');
	html.push('<th style="width:150px;">最新折扣结束时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for(var i=0;i<data.content.length;i++){
			html.push('<tr>');
			html.push('<th><input type="checkbox" /></th>');
			html.push('<td>操作</td>');
			html.push('<td>'+data.content[i].sellerid+'</td>');
			html.push('<td>'+data.content[i].signdate+'</td>');
			var sellername = data.content[i].sellername?data.content[i].sellername:'-';
			html.push('<td class="text-ellipsis">'+data.content[i].sellername+'</td>');	
			//总店
			html.push('<td class="text-ellipsis"><a href="http://www.baidu.com">查看</a></td>');	
			//账号
			html.push('<td class="text-ellipsis"><a href="http://www.baidu.com">查看</a></td>');
			//地址
			var address = data.content[i].address?data.content[i].address:'-';
			html.push('<td class="text-ellipsis">'+address+'</td>');
			//咨询电话
			var tel = data.content[i].tel?data.content[i].tel:'-';
			html.push('<td class="text-ellipsis">'+tel+'</td>');
			//区域
			var title = data.content[i].title?data.content[i].title:'-';
			html.push('<td class="text-ellipsis">'+title+'</td>');
			//经营情报
			html.push('<td class="text-ellipsis"><a href="http://www.baidu.com">查看</a></td>');
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
 * 会员
 * 
 * @param data
 * @param obj
 */
function successMember(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered  info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:20px; line-height:40px;">会员信息</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
	html.push('<th style="width:100px;">操作</th>');
	html.push('<th style="width:100px;">ID</th>');
	html.push('<th style="width:250px;">时间</th>');
	html.push('<th style="width:150px;">会员数量</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for(var i=0;i<data.content.length;i++){
		html.push('<tr>');
		html.push('<th><input type="checkbox" /></th>');
		html.push('<td>操作</td>');
		html.push('<td>'+data.content[i].sellerid+'</td>');
		html.push('<td>'+data.content[i].signdate+'</td>');
		var sellername = data.content[i].sellername?data.content[i].sellername:'-';
		html.push('<td class="text-ellipsis">'+data.content[i].sellername+'</td>');	
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 订单
 * 
 * @param data
 * @param obj
 */
function successOrder(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered  info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:20px; line-height:40px;">订单信息</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
	html.push('<th style="width:100px;">操作</th>');
	html.push('<th style="width:100px;">ID</th>');
	html.push('<th style="width:250px;">时间</th>');
	html.push('<th style="width:150px;">订单数量</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for(var i=0;i<data.content.length;i++){
		html.push('<tr>');
		html.push('<th><input type="checkbox" /></th>');
		html.push('<td>操作</td>');
		html.push('<td>'+data.content[i].sellerid+'</td>');
		html.push('<td>'+data.content[i].signdate+'</td>');
		var sellername = data.content[i].sellername?data.content[i].sellername:'-';
		html.push('<td class="text-ellipsis">'+data.content[i].sellername+'</td>');	
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 销售额
 * 
 * @param data
 * @param obj
 */
function successSalesVolume(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered  info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:20px; line-height:40px;">销售额</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
	html.push('<th style="width:100px;">操作</th>');
	html.push('<th style="width:100px;">ID</th>');
	html.push('<th style="width:250px;">时间</th>');
	html.push('<th style="width:150px;">销售额</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for(var i=0;i<data.content.length;i++){
		html.push('<tr>');
		html.push('<th><input type="checkbox" /></th>');
		html.push('<td>操作</td>');
		html.push('<td>'+data.content[i].sellerid+'</td>');
		html.push('<td>'+data.content[i].signdate+'</td>');
		var sellername = data.content[i].sellername?data.content[i].sellername:'-';
		html.push('<td class="text-ellipsis">'+data.content[i].sellername+'</td>');	
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 营业额
 * 
 * @param data
 * @param obj
 */
function successTurnover(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered  info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:20px; line-height:40px;">营业额</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
	html.push('<th style="width:100px;">操作</th>');
	html.push('<th style="width:100px;">ID</th>');
	html.push('<th style="width:250px;">时间</th>');
	html.push('<th style="width:150px;">营业额</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for(var i=0;i<data.content.length;i++){
		html.push('<tr>');
		html.push('<th><input type="checkbox" /></th>');
		html.push('<td>操作</td>');
		html.push('<td>'+data.content[i].sellerid+'</td>');
		html.push('<td>'+data.content[i].signdate+'</td>');
		var sellername = data.content[i].sellername?data.content[i].sellername:'-';
		html.push('<td class="text-ellipsis">'+data.content[i].sellername+'</td>');	
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 佣金
 * 
 * @param data
 * @param obj
 */
function successCommission(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered  info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:20px; line-height:40px;">佣金</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
	html.push('<th style="width:100px;">操作</th>');
	html.push('<th style="width:100px;">ID</th>');
	html.push('<th style="width:250px;">时间</th>');
	html.push('<th style="width:150px;">佣金</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for(var i=0;i<data.content.length;i++){
		html.push('<tr>');
		html.push('<th><input type="checkbox" /></th>');
		html.push('<td>操作</td>');
		html.push('<td>'+data.content[i].sellerid+'</td>');
		html.push('<td>'+data.content[i].signdate+'</td>');
		var sellername = data.content[i].sellername?data.content[i].sellername:'-';
		html.push('<td class="text-ellipsis">'+data.content[i].sellername+'</td>');	
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 已提现
 * 
 * @param data
 * @param obj
 */
function successDrawMoney(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered  info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:20px; line-height:40px;">已提现</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
	html.push('<th style="width:100px;">操作</th>');
	html.push('<th style="width:100px;">ID</th>');
	html.push('<th style="width:250px;">时间</th>');
	html.push('<th style="width:150px;">已提现</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for(var i=0;i<data.content.length;i++){
		html.push('<tr>');
		html.push('<th><input type="checkbox" /></th>');
		html.push('<td>操作</td>');
		html.push('<td>'+data.content[i].sellerid+'</td>');
		html.push('<td>'+data.content[i].signdate+'</td>');
		var sellername = data.content[i].sellername?data.content[i].sellername:'-';
		html.push('<td class="text-ellipsis">'+data.content[i].sellername+'</td>');	
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}