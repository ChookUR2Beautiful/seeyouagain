var allBillList;
$(document).ready(function() {
	/**
	 * 如果是商家页面查看进来的执行次操作
	 */
	if($('#selleridId').val()){
		sellerView($('#selleridId').val());
	}

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
	
	$("#export").click(function(){
		$form = $("#searchBillForm").attr("action","billmanagerment/allbill/export.jhtml");
		$form[0].submit();
	});
});

/**
 * 商家页面查看方法
 */
function sellerView(sellerid){
	allBillList = $('#allBillList').page({
		url : 'businessman/seller/initOrder/list.jhtml',
		success : success,
		pageBtnNum : 10,
		param : {sellerid : sellerid},
		paramForm : 'searchBillForm'
	});
	//$("#export").hide();
	inserTitle(' > <span><a href="businessman/seller/initOrder.jhtml?selleridId='+sellerid+'" target="right">商家订单信息</a>','sellerViewOrder',false);
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
	if(null != data && data.content.length > 0)
	{
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家订单信息<font style="float:right;">共计【'+data.total+'】条订单，总金额为【￥'+(undefined == data.content[0].totalAmount ? "0" : data.content[0].totalAmount)+'】&nbsp;</font></caption>');
	}
	html.push('<thead>');
	html.push('<tr>');
	/*if(!isEmptyObject(permissions)){
		html.push('<th style="width:60px;">操作</th>');
	}*/
	html.push('<th >用户呢称</th>');
	html.push('<th >用户手机</th>');	
	html.push('<th >订单号</th>');
	html.push('<th >支付方式</th>');
	html.push('<th >订单金额</th>');	
	html.push('<th >折扣</th>');
	html.push('<th >所属商家</th>');
	html.push('<th >消费商家</th>');
	html.push('<th >分账状态</th>');
	html.push('<th >订单状态</th>');
	html.push('<th >下单时间</th>');
	html.push('<th >返利金额</th>');
	html.push('<th >平台收益</th>');
	html.push('<th >运营商收益</th>');
	html.push('<th >商家营收</th>');
	html.push('<th >寻蜜客佣金</th>');
	html.push('<th >手续费</th>');
	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');

if(null != data && data.content.length > 0)
	{
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		/*if(!isEmptyObject(permissions)){
			html.push('<td>');
			if(permissions.detail == 'true'){
				html.push('<a href="javascript:void();" data-type="ajax" data-width="960px" data-url="billmanagerment/allbill/view/init.jhtml?bid='+data.content[i].bid+'" data-toggle="modal">查看</a>&nbsp;&nbsp;');
			}
			html.push('</td>');
		}*/
		html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + '</td>');
		html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].bid ? "-" : data.content[i].bid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].payTypeText ? "-" : data.content[i].payTypeText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].money ? "-" : '￥'+data.content[i].money) + '</td>');	
		html.push('<td>' + (undefined == data.content[i].baseagio ? "-" : data.content[i].baseagio) + '</td>');
		html.push('<td>' + (undefined == data.content[i].genusname ? "-" : data.content[i].genusname) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sellername ? "-" : data.content[i].sellername) + '</td>');
		html.push('<td>' + (undefined == data.content[i].hstatusText ? "-" : data.content[i].hstatusText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].rebate ? "-" : '￥'+data.content[i].rebate) + '</td>');
			var platformAmount=data.content[i].platformAmount;
			var platformAmounts;
			if(platformAmount=="" || platformAmount==undefined ){
					platformAmounts="-";
			}else{
					platformAmounts='￥'+data.content[i].platformAmount;
			}
		html.push('<td>' + platformAmounts+ '</td>');
			var bpartnerAmount=data.content[i].bpartnerAmount;
			var bpartnerAmounts;
			if(bpartnerAmount=="" || bpartnerAmount==undefined ){
					bpartnerAmounts="-";
			}else{
					bpartnerAmounts='￥'+data.content[i].bpartnerAmount;
			}
		html.push('<td>' + bpartnerAmounts + '</td>');
			var sellerAmount=data.content[i].sellerAmount;
			var sellerAmounts;
			if(sellerAmount=="" || sellerAmount==undefined ){
				sellerAmounts="-";
			}else{
				sellerAmounts='￥'+data.content[i].sellerAmount;
			}
		html.push('<td>' + sellerAmounts + '</td>');
			var mikeAmount=data.content[i].mikeAmount;
			var mikeAmounts;
			if(mikeAmount=="" || mikeAmount==undefined ){
				mikeAmounts="-";
			}else{
				mikeAmounts='￥'+data.content[i].mikeAmount;
			}
		html.push('<td  >' + mikeAmounts + '</td>');
		
			var feesMoney=data.content[i].feesMoney;
			var feesMoneys;
			if(feesMoney=="" || feesMoney==undefined ){
				feesMoneys="-";
			}else{
				feesMoneys='￥'+data.content[i].feesMoney;
			}
		html.push('<td title="描述：手续费按第三方提供的比例计算，例如U付2/1000计算，平台收益、合作商收益、寻蜜客佣金各付3/1">' + feesMoneys + '</td>');
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

