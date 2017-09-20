var refundBillList;
$(document).ready(function() {
	refundBillList = $('#refundBillList').page({
		url : 'president_office/refund/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
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
	inserTitle(' > 总裁办 > <a href="president_office/refund/init.jhtml" target="right">商家申诉待处理订单</a>','president_office_refundbillSpan',true);
});


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
/*function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家申诉订单<font style="float:right;">共计【'+data.total+'】条订单&nbsp;</font></caption>');
	
	html.push('<thead>');
	html.push('<tr>');
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:100px;">操作</th>');
	}
	html.push('<th >订单号</th>');
	html.push('<th >支付流水号</th>');
	html.push('<th >订单金额</th>');	
	html.push('<th >消费验证号</th>');
	html.push('<th >支付方式</th>');
	html.push('<th >用户昵称</th>');
	html.push('<th >消费商家</th>');
	html.push('<th >下单时间</th>');
	html.push('<th >支付时间</th>');
	html.push('<th >申请退款时间</th>');
	html.push('<th >商家申诉时间</th>');
	html.push('<th >申诉处理时间</th>');
	html.push('<th >商家申诉原因</th>');
	html.push('<th >客服处理结果</th>');
	html.push('<th >退款流程状态</th>');
	html.push('<th >备注</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	
if(null != data && data.content.length > 0)
	{	
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		if(!isEmptyObject(permissions)){
			html.push('<td>');
			if(permissions.treatment == 'true'){
				html.push('<a href="javascript:;" data-type="ajax" data-url="president_office/refund/update/init.jhtml?id='+data.content[i].id+'" data-toggle="modal">处理</a>&nbsp;&nbsp;');
			}
			html.push('</td>');
		}
		
		html.push('<td>' + (undefined == data.content[i].bid ? "-" : data.content[i].bid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].payid ? "-" : data.content[i].payid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].money ? "-" : '￥'+data.content[i].money) + '</td>');
		html.push('<td>');
		html.push('<a href="javascript:void();" data-title="" data-type="ajax" data-width="960px" data-url="billmanagerment/allbill/init/orderCmount.jhtml?bid='+data.content[i].bid+'" data-toggle="modal">'+(undefined == data.content[i].money ? "-" : '￥'+data.content[i].money)+'</a>&nbsp;&nbsp;');
		html.push('</td>');
		html.push('<td>' + (undefined == data.content[i].codeid ? "-" : data.content[i].codeid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].payTypeText ? "-" : data.content[i].payTypeText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + '</td>');
			var sellername=(undefined == data.content[i].sellername ? "-" : data.content[i].sellername);
		html.push('<td title ="'+sellername+'">' + substr(sellername) + '</td>');
		html.push('<td>' + (undefined == data.content[i].orderDate ? "-" : data.content[i].orderDate) +'</td>');
		html.push('<td>' + (undefined == data.content[i].zdate ? "-" : data.content[i].zdate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			
		html.push('<td>' + (undefined == data.content[i].adate ? "-" : data.content[i].adate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].qdate ? "-" : data.content[i].qdate) + '</td>');
		
		var qppeal=(undefined == data.content[i].qppeal ? "-" : data.content[i].qppeal);
		html.push('<td title ="'+qppeal+'">' + substr(qppeal)  + '</td>');
		html.push('<td>' + (undefined == data.content[i].processing ? "-" : data.content[i].processing) + '</td>');
		html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
			var remarks=(undefined == data.content[i].remarks ? "-" : data.content[i].remarks);
		html.push('<td title ="'+remarks+'">' + substr(remarks) + '</td>');
		
		html.push('</tr>');
	}
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="21">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}*/


//new style table, update by wangzhimin 2015/08/19 10:28:34
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;商家申诉订单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】条订单&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
	obj.find('div').eq(0).scrollTablel({
    	tableClass :"table-bordered table-striped info",
    	callbackParam : callbackParam,
    	caption : captionInfo,
		//数据
		data:data.content, 
		 //数据行
		cols:[{
			title : "订单号",// 标题
			name : "bid",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
		},{
			title : "支付流水号",// 标题
			name : "payid",
			width : 150,// 宽度
			type:"stirng"//数据类型
		},{
			title : "订单金额",// 标题
			name : "money",
			leng : 8,//显示长度
			type:"string",//数据类型
			isLink : true,// 表示当前列是否是超链接 true:是   false：不是
			// 只有当isLink为true时 才有效
			link : {
				linkInfoName : "modal", // href , modal ,method
				linkInfo : {
					modal : {
						url : "billmanagerment/allbill/init/orderCmount.jhtml",// url
						position:"200px",// 模态框显示位置
						width:"auto",
						title : "订单金额"
					}
				},
				param : ["bid"],// 参数
				permission : "treatment",
			},
			customMethod : function(value, data) {
				return $(value).html("￥" + data.money);
			},
			width : 150// 宽度
		},{
			title : "消费验证号",// 标题
			name : "codeid",
			//sort : "up",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
		},{
			title : "支付方式",// 标题
			name : "payTypeText",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
		},{
			title : "用户呢称",// 标题
			name : "nname",
			width : 150,// 宽度
			type:"stirng"//数据类型
		},{
			title : "消费商家",// 标题
			name : "sellername",
			width : 150,// 宽度
			type:"string"//数据类型
		},{
			title : "下单时间",// 标题
			name : "orderDate",
			width : 160,// 宽度
			type:"date"//数据类型
		},{
			title : "支付时间",// 标题
			name : "zdate",
			width : 160,// 宽度
			type:"date"//数据类型
		},{
			title : "申请退款时间",// 标题
			name : "sdate",
			width : 160,// 宽度
			type:"date"//数据类型
		},{
			title : "商家申诉时间",// 标题
			name : "adate",
			width : 160,// 宽度
			type:"date"//数据类型
		},{
			title : "申诉处理时间",// 标题
			name : "qdate",
			width : 160,// 宽度
			type:"date"//数据类型
		},{
			title : "商家申诉原因",// 标题
			name : "qppeal",
			//sort : "up",
			width : 150,// 宽度
			type:"stirng",//数据类型
			customMethod : function(value, data) {
				return substr(data.qppeal);
			}
		},{
			title : "客服处理结果",// 标题
			name : "processing",
			//sort : "up",
			width : 150,// 宽度
			type:"stirng"//数据类型
		},{
			title : "退款流程状态",// 标题
			name : "statusText",
			//sort : "up",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "备注",// 标题
			name : "remarks",
			width : 150,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data) {
				return substr(data.remarks);
			}
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["treatment"],// 不需要选择checkbox处理的权限
				width : 80,// 宽度
				// 当前列的中元素
				cols : [{
					title : "处理",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "president_office/refund/update/init.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px"
						},
						param : ["id"],// 参数
						permission : "treatment"// 列权限
					}
				}] 
	}},permissions);
}