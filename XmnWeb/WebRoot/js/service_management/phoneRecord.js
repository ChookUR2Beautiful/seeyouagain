var orderList;
var mem = $('#memberListDiv');
var div;
$(document).ready(function() {
	inserTitle(' > 客服管理 > <a href="serviceManagement/phoneRecord/init.jhtml" target="right">电话呼入管理</a>','userSpan',true);
	$('.form_datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	$("#orderquery").click(function(){
		orderList = $('#orderList').page({
			url : 'serviceManagement/phoneRecord/order/list.jhtml',
			success : orderSuccess,
			pageBtnNum :5,
			pageSize:5,
			paramForm : 'orderForm'
		});
	});
	
	$("#memberquery").click(function(){
		div = $("#memberList").page({
			url : 'serviceManagement/phoneRecord/member/list.jhtml',
			dataType:"html",
			success : handle,
			pageBtnNum : 5,
			pageSize: 5,
			paramForm : 'memberForm'
		});
	});
	$("#membersquery").click(function(){
		div = $("#membersList").page({
			url : 'serviceManagement/phoneRecord/member/list.jhtml',
			dataType:"html",
			success : membersList,
			pageBtnNum : 5,
			pageSize: 5,
			paramForm : 'membersForm'
		});
	});
	$("#memberpquery").click(function(){
		div = $("#membersList").page({
			url : 'serviceManagement/phoneRecord/member/list.jhtml',
			dataType:"html",
			success : memberpList,
			pageBtnNum : 5,
			pageSize: 5,
			paramForm : 'memberpForm'
		});
	});
	$("#memberequery").click(function(){
		div = $("#membereList").page({
			url : 'serviceManagement/phoneRecord/member/list.jhtml',
			dataType:"html",
			success : membereList,
			pageBtnNum : 5,
			pageSize: 5,
			paramForm : 'membereForm'
		});
	});
	
	
	
	$("#guestMemberquery").click(function(){
		div = $("#guestMemberList").page({
			url : 'serviceManagement/phoneRecord/member/list.jhtml',
			dataType:"html",
			success : guestMemberList,
			pageBtnNum : 5,
			pageSize: 5,
			paramForm : 'guestSearchForm'
		});
	});
	$("#guestsMemberquery").click(function(){
		div = $("#guestsMemberList").page({
			url : 'serviceManagement/phoneRecord/member/list.jhtml',
			dataType:"html",
			success : guestsMemberList,
			pageBtnNum : 5,
			pageSize: 5,
			paramForm : 'guestsSearchForm'
		});
	});
	$("#guestpMemberquery").click(function(){
		div = $("#guestpMemberList").page({
			url : 'serviceManagement/phoneRecord/member/list.jhtml',
			dataType:"html",
			success : guestpMemberList,
			pageBtnNum : 5,
			pageSize: 5,
			paramForm : 'guestpSearchForm'
		});
	});
	$("#guesteMemberquery").click(function(){
		div = $("#guesteMemberList").page({
			url : 'serviceManagement/phoneRecord/member/list.jhtml',
			dataType:"html",
			success : guesteMemberList,
			pageBtnNum : 5,
			pageSize: 5,
			paramForm : 'guesteSearchForm'
		});
	});
	
	$("#merchantquery").click(function(){
		orderList = $('#merchantList').page({
			url : 'serviceManagement/phoneRecord/merchant/list.jhtml',
			success : merchantSuccess,
			pageBtnNum :5,
			pageSize:5,
			paramForm : 'merchantSearchForm'
		});
	});
	
	$("#merchantsquery").click(function(){
		orderList = $('#merchantsList').page({
			url : 'serviceManagement/phoneRecord/merchant/list.jhtml',
			success : merchantSuccess,
			pageBtnNum :5,
			pageSize:5,
			paramForm : 'merchantSearchsForm'
		});
	});
	$("#merchantpquery").click(function(){
		orderList = $('#merchantpList').page({
			url : 'serviceManagement/phoneRecord/merchant/list.jhtml',
			success : merchantSuccess,
			pageBtnNum :5,
			pageSize:5,
			paramForm : 'merchantSearchpForm'
		});
	});
	$("#merchantequery").click(function(){
		orderList = $('#merchanteList').page({
			url : 'serviceManagement/phoneRecord/merchant/list.jhtml',
			success : merchantSuccess,
			pageBtnNum :5,
			pageSize:5,
			paramForm : 'merchantSearcheForm'
		});
	});
	
	$("#cooperationquery").click(function(){
		orderList = $('#cooperationList').page({
			url : 'serviceManagement/phoneRecord/cooperation/list.jhtml',
			success : cooperationSuccess,
			pageBtnNum :5,
			pageSize:5,
			paramForm : 'cooperationSearchForm'
		});
	});
	
	$("#cooperationsquery").click(function(){
		orderList = $('#cooperationsList').page({
			url : 'serviceManagement/phoneRecord/cooperation/list.jhtml',
			success : cooperationSuccess,
			pageBtnNum :5,
			pageSize:5,
			paramForm : 'cooperationSearchsForm'
		});
	});
	
	$("#cooperationpquery").click(function(){
		orderList = $('#cooperationpList').page({
			url : 'serviceManagement/phoneRecord/cooperation/list.jhtml',
			success : cooperationSuccess,
			pageBtnNum :5,
			pageSize:5,
			paramForm : 'cooperationSearchpForm'
		});
	});
	
	$("#cooperationequery").click(function(){
		alert(1111);
		orderList = $('#cooperationeList').page({
			url : 'serviceManagement/phoneRecord/cooperation/list.jhtml',
			success : cooperationSuccess,
			pageBtnNum :5,
			pageSize:5,
			paramForm : 'cooperationSearcheForm'
		});
	});
	
	
});


function memberpList(data){
	var table  =$("#memberpList").find("table");
	if(table.length==0){
		$("#memberpList").prepend(data);
		table  =$("#memberpList").find("table");
	}else{
		$(table).html(data);
	}
}
function membereList(data){
	var table  =$("#membereList").find("table");
	if(table.length==0){
		$("#membereList").prepend(data);
		table  =$("#membereList").find("table");
	}else{
		$(table).html(data);
	}
}
function membersList(data){
	var table  =$("#membersList").find("table");
	if(table.length==0){
		$("#membersList").prepend(data);
		table  =$("#membersList").find("table");
	}else{
		$(table).html(data);
	}
}
function handle(data){
	var table  =$("#memberList").find("table");
	if(table.length==0){
		$("#memberList").prepend(data);
		table  =$("#memberList").find("table");
	}else{
		$(table).html(data);
	}

}

function guestMemberList(data){
	var table  =$("#guestMemberList").find("table");
	if(table.length==0){
		$("#guestMemberList").prepend(data);
		table  =$("#guestMemberList").find("table");
	}else{
		$(table).html(data);
	}

}
function guestsMemberList(data){
	var table  =$("#guestsMemberList").find("table");
	if(table.length==0){
		$("#guestsMemberList").prepend(data);
		table  =$("#guestsMemberList").find("table");
	}else{
		$(table).html(data);
	}
	
}
function guestpMemberList(data){
	var table  =$("#guestpMemberList").find("table");
	if(table.length==0){
		$("#guestpMemberList").prepend(data);
		table  =$("#guestpMemberList").find("table");
	}else{
		$(table).html(data);
	}
	
}
function guesteMemberList(data){
	var table  =$("#guesteMemberList").find("table");
	if(table.length==0){
		$("#guesteMemberList").prepend(data);
		table  =$("#guesteMemberList").find("table");
	}else{
		$(table).html(data);
	}
	
}

/**
 * 企业查询结构
 * 
 * @param data
 * @param obj
 */
function cooperationSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">合作商</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var hasHandle = permissions && (permissions.update || permissions.del);
	if(hasHandle){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:80px;">操作</th>');
	}
	html.push('<th style="width:200px;">公司名称</th>');
	html.push('<th style="width:100px;">法人名称</th>');
	html.push('<th style="width:150px;">合作商联系手机</th>');
	html.push('<th style="width:100px;">登录账号</th>');
	html.push('<th style="width:50px;">商户数</th>');
	html.push('<th style="width:50px;">已签约数</th>');
	html.push('<th style="width:50px;">商圈数</th>');
	html.push('<th style="width:50px;">已启动商圈</th>');
	html.push('<th style="width:70px;">城市</th>');
	html.push('<th style="width:150px;">区域</th>');
	html.push('<th style="width:70px;">状态</th>');
	if(permissions.getWallet){
		html.push('<th style="width:70px;">钱包</th>');
	}
	html.push('<th style="width:200px;">地址</th>');
	html.push('<th style="width:200px;">开通时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
		updateAddBtnHref("#addJoint",callbackParam);
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(hasHandle){
				html.push('<th><input type="checkbox" val=' + data.content[i].jointid + '  /></th>');
				html.push('<td>');
				if(permissions.update){
					html.push('<a href="business_cooperation/joint/update/init.jhtml?jointid='+data.content[i].jointid+callbackParam+'">修改</a>&nbsp;&nbsp;');
				}
				if(permissions.del){
					html.push('<a href="javascript:remove('+data.content[i].jointid+')">删除</a>&nbsp;&nbsp;');
				}
				html.push('</td>');
			}
			var corporate = (undefined == data.content[i].corporate ? "-" : data.content[i].corporate);
			html.push('<td title="'+corporate+'">' + substr(corporate,8) + '</td>');
			html.push('<td>' + (undefined == data.content[i].legalperson ? "-" : data.content[i].legalperson) + '</td>');
			html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].loginAccount ? "-" : data.content[i].loginAccount) + '</td>');
			
			html.push('<td>' + (undefined == data.content[i].sellerNum ? "-" : data.content[i].sellerNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].agreeNum ? "-" : data.content[i].agreeNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].areaNum ? "-" : data.content[i].areaNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].startnum ? "-" : data.content[i].startnum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].cityTitle ? "-" : data.content[i].cityTitle) + '</td>');
			var areaTitle = (undefined == data.content[i].areaTitle ? "-" : data.content[i].areaTitle);
			html.push('<td title="'+areaTitle+'">' + substr(areaTitle,8) + '</td>');
			html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
			if(permissions.getWallet){
				html.push('<td><a data-type="ajax" data-url="business_cooperation/joint/getWallet.jhtml?jointid='+data.content[i].jointid+'" data-toggle="modal" href="javascript:">钱包</a>&nbsp;&nbsp;</td>');
			}
			var address = (undefined == data.content[i].address ? "-" : data.content[i].address);
			html.push('<td title="'+address+'">' + substr(address,8) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
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
}

/**
 * 会员查询结构
 * 
 * @param data
 * @param obj
 */
function memberSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	
	html.push('<thead>');
	html.push('<tr>');
	
	html.push('<th >订单号</th>');
	html.push('<th >订单金额</th>');
	html.push('<th >返利金额</th>');
	html.push('<th >折扣</th>');
	html.push('<th >消费商家</th>');
	html.push('<th >订单状态</th>');
	
	html.push('<th >下单时间</th>');
	
	
	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			
			html.push('<td>' + (undefined == data.content[i].bid ? "-" : data.content[i].bid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].money ? "-" : '￥'+data.content[i].money) + '</td>');
			html.push('<td>' + (undefined == data.content[i].rebate ? "-" : '￥'+data.content[i].rebate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].baseagio ? "-" : data.content[i].baseagio) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sellername ? "-" : data.content[i].sellername) + '</td>');
			html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');			
			html.push('</tr>');
		}
	}else{
		html.push('<tr>');
		html.push('<td colspan="100">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}
/**
 * 订单查询结构
 * 
 * @param data
 * @param obj
 */
function orderSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">分账报表</caption>');
	
	html.push('<thead>');
	html.push('<tr>');
	
	html.push('<th >订单号</th>');
	html.push('<th >订单金额</th>');
	html.push('<th >返利金额</th>');
	html.push('<th >折扣</th>');
	html.push('<th >消费商家</th>');
	html.push('<th >订单状态</th>');
	
	html.push('<th >下单时间</th>');
	
	
	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			
			html.push('<td>' + (undefined == data.content[i].bid ? "-" : data.content[i].bid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].money ? "-" : '￥'+data.content[i].money) + '</td>');
			html.push('<td>' + (undefined == data.content[i].rebate ? "-" : '￥'+data.content[i].rebate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].baseagio ? "-" : data.content[i].baseagio) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sellername ? "-" : data.content[i].sellername) + '</td>');
			html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');			
			html.push('</tr>');
		}
	}else{
		html.push('<tr>');
		html.push('<td colspan="100">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

function merchantSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">全部商家信息<font style="float:right;">共计【'+data.total+'】个商家&nbsp;</font></caption>');
	html.push('<thead>');
	html.push('<tr>');

	html.push('<th style="width:100px;">商家编号</th>');
	html.push('<th style="width:250px;">加入时间</th>');
	html.push('<th style="width:150px;">商家名称</th>');
	html.push('<th style="width:200px;">商家手机号</th>');
	html.push('<th style="width:150px;">所属行业</th>');
	
	
	html.push('<th style="width:100px;">连锁店</th>');
	if(permissions.zh=='true'){
		html.push('<th style="width:50px;">账号</th>');
	}
	
	if(permissions.zk=='true'){
		html.push('<th style="width:50px;">折扣</th>');
	}
	if(permissions.yx){
		html.push('<th style="width:100px;">营销信息</th>');
	}
	
	if(permissions.wallet){
		html.push('<th style="width:100px;">钱包</th>');	
	}
	
	html.push('<th style="width:100px;">地址</th>');	
	//html.push('<th style="width:100px;">咨询电话</th>');
	html.push('<th style="width:100px;">区域</th>');
	html.push('<th style="width:100px;">商圈</th>');
	html.push('<th style="width:100px;">归属合作商</th>');
	html.push('<th style="width:70px;">归属业务员</th>');
	html.push('<th style="width:70px;">审批状态</th>');
	html.push('<th style="width:70px;">上线状态</th>');	
	html.push('<th style="width:100px;">意见</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#sellerFromId").serialize());
		updateAddBtnHref("#addSellerBto",callbackParam);
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
				//时间
				var signdate = data.content[i].signdate?data.content[i].signdate:'-';
				html.push('<td title ="'+signdate+'">'+ substr(signdate,10)+'</td>');
				
				//商家名称
				var sellername = data.content[i].sellername?data.content[i].sellername:'-';
				if(permissions.order=='true'){
					html.push('<td class="text-ellipsis" name="sellername" initValue="'+sellername+'" title ="'+sellername+'"><a href="businessman/seller/initOrder.jhtml?selleridId='+data.content[i].sellerid+callbackParam+'">'+substr(sellername,4)+'</a></td>');
				}else{
					html.push('<td class="text-ellipsis" name="sellername" initValue="'+sellername+'" title ="'+sellername+'">'+substr(sellername,4)+'</td>');
				}
				html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
				//所属行业
				var typeName =   (undefined == data.content[i].typename ? "" : data.content[i].typename) + "-" +  (undefined == data.content[i].tradename ? "" : data.content[i].tradename);
				html.push('<td class="text-ellipsis" title ="'+typeName+'">'+substr(typeName,4)+'</td>');	
					
				//连锁店
				var lssellername = data.content[i].lssellername?data.content[i].lssellername:'-';
				html.push('<td class="text-ellipsis" title ="'+lssellername+'">'+ substr(lssellername,4)+'</td>');
				//已签约才可以查看帐号和折扣
				if(data.content[i].status == 3)
				{
					if(permissions.zh=='true'){
						//账号
						html.push('<td class="text-ellipsis"><a href="businessman/sellerAccount/init.jhtml?sellerid='+data.content[i].sellerid+callbackParam+'">账号</a></td>');	
					}
					
					if(permissions.zk=='true'){
						//折扣
						html.push('<td class="text-ellipsis"><a href="businessman/sellerAgio/init.jhtml?sellerid='+data.content[i].sellerid+callbackParam+'">折扣</a></td>');
					}
					if(permissions.yx){
						html.push('<td class="text-ellipsis"><a href="businessman/sellerMarketing/init.jhtml?sellerid='+data.content[i].sellerid+callbackParam+'">营销信息</a></td>');
					}
					
					if(permissions.wallet){
						//钱包
						html.push('<td class="text-ellipsis"><a href="javascript:void();" data-type="ajax" data-url="businessman/seller/viewWallet.jhtml?sellerid='+data.content[i].sellerid+'" data-width="30%" data-toggle="modal">钱包</a></td>');
					}
				}else
				{
					//账号
					if(permissions.zh=='true'){
						html.push('<td>-</td>');
					}
					//折扣
					if(permissions.zk=='true'){
						html.push('<td>-</td>');
					}
					if(permissions.yx){
						html.push('<td>-</td>');
					}
					if(permissions.wallet){
						html.push('<td>-</td>');
					}
				}
				
				//平台补贴
				/*var flatAgio = data.content[i].flatAgio?data.content[i].flatAgio:"0";
				if(data.content[i].status == 3){
					html.push('<td class="text-ellipsis" title ="点击修改平台补贴">');
					html.push('<a href="javascript:void();" data-type="ajax" data-url="businessman/seller/update/initFlatAgio.jhtml?sellerid='+data.content[i].sellerid+'&type=1" data-title="修改平台补贴" data-toggle="modal">'+accMul(flatAgio,100)+" %"+'</a>&nbsp;&nbsp;');
					html.push('</td>');
				}else{
					html.push('<td class="text-ellipsis">'+accMul(flatAgio,100)+" %"+'</td>');
				}*/
				
				//地址
				var address = data.content[i].address?data.content[i].address:'-';
				html.push('<td class="text-ellipsis" title ="'+address+'">'+substr(address,4)+'</td>');
			/*	//咨询电话
				var tel = data.content[i].tel?data.content[i].tel:'-';
				html.push('<td class="text-ellipsis" title ="'+tel+'">'+substr(tel,8)+'</td>');*/
				//区域
				var title = data.content[i].title?data.content[i].title:'-';
				html.push('<td class="text-ellipsis" title ="'+title+'">'+ substr(title,4)+'</td>');
				//商圈
				var btitle = data.content[i].btitle?data.content[i].btitle:'-';
				html.push('<td class="text-ellipsis" title ="'+btitle+'">'+substr(btitle,4)+'</td>');
				//归属合作商
				var corporate = data.content[i].corporate?data.content[i].corporate:'-';
				corporate = substr(corporate,4);
				html.push('<td class="text-ellipsis"  title ="'+data.content[i].corporate+'">'+corporate+'</td>');
				//归属业务员
				var salesman = data.content[i].salesman?data.content[i].salesman:'-';
				html.push('<td class="text-ellipsis">'+salesman+'</td>');
				//默认0未验证|1审核中|2未通过|3已签约|4未签约
				var status = "-";
				if(data.content[i].status == 1)
				{
					status = "审核中";
				}
				else if(data.content[i].status == 2)
				{
					status = "未通过";
				}
				else if(data.content[i].status == 3)
				{
					status = "已签约";
				}
				else if(data.content[i].status == 4)
				{
					status = "未签约";
				}
				else if(data.content[i].status == 0)
				{
					status = "未验证";
				}else if(data.content[i].status == 5)
				{
					status = "暂停合作";
				}
				html.push('<td class="text-ellipsis" name="status" initValue="'+data.content[i].status+'">'+status+'</td>');	//状态
				
				//'0 未上线    1上线'
				var isonline = "-";
				if(data.content[i].isonline == 0)
				{
					isonline = "未上线";
				}
				else if(data.content[i].isonline == 1)
				{
					isonline = "已上线";
				}else if(data.content[i].isonline == 2){
					isonline = "待上线";
				}
				html.push('<td class="text-ellipsis" name="isonline" initValue="'+data.content[i].isonline+'">'+isonline+'</td>');	//状态
				var examineinfo = data.content[i].examineinfo?data.content[i].examineinfo:'-';
				html.push('<td class="text-ellipsis" title ="'+examineinfo+'">'+substr(examineinfo,4)+'</td>');	//意见
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