var divideList;
var withdrawList;
$(document).ready(function() {
	divideList = $('#divide').page({
		url : 'president_office/destructionOrder/init/list.jhtml',
		contentType : 'application/json',
		success : divideSuccess,
		pageBtnNum : 10,
		paramForm : 'divideForm'
	});
	
	withdrawList = $('#withdraw').page({
		url : 'president_office/destructionOrder/init/list.jhtml',
		contentType : 'application/json',
		success : withdrawSuccess,
		pageBtnNum : 10,
		paramForm : 'withdrawForm'
	});

	//所属合作商
	$("#bpartner").chosenObject({
		hideValue : "corporate",
		showValue : "corporate",
		url : "business_cooperation/joint/jointList.jhtml",
		width : "100%"
	});
	
	//消费合作商
	$("#cpartner").chosenObject({
		hideValue : "corporate",
		showValue : "corporate",
		url : "business_cooperation/joint/jointList.jhtml",
		width : "100%"
	});
	
	$("input[data-bus=reset]").click(function(){
//		location.reload();
		$("#cpartner").trigger("chosen:updated");
		$("#bpartner").trigger("chosen:updated");
	});
	
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
	
	inserTitle(' > 总裁办  > <a href="president_office/destructionOrder/init.jhtml" target="right">销毁订单记录</a>','destructionOrder',true);
});

function divideSuccess(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;分账列表 &nbsp;&nbsp;共计【'+data.total+'】条&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#refundBillPendingHistoryListForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	//checkable :false,
	    	//identifier : "bid",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
				title : "订单号",// 标题
				name : "id",
				width : 120,// 宽度
				type:"string"//数据类型
			},{
				title : "订单创建时间",// 标题
				name : "createDate",
				width : 160,// 宽度
				type:"string"//数据类型
			},{
				title : "触发分账时间",// 标题
				name : "createDate",
				width : 160,// 宽度
				type:"string"//数据类型
			},{
				title : "处理时间",// 标题
				name : "handleTime",
				width : 160,// 宽度
				type:"string"//数据类型
			},{
				title : "会员编号",// 标题
				name : "memberId",
				width : 80,// 宽度
				type:"string"//数据类型
			},{
				title : "会员",// 标题
				name : "memberName",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "消费商家",// 标题
				name : "sellerName",
				width : 150,// 宽度
				type:"string"//数据类型
			},{
				title : "所属合作商",// 标题
				name : "bpartnerName",
				width : 150,// 宽度
				type:"string"//数据类型
			},{
				title : "消费合作商",// 标题
				name : "cpartnerName",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "所属向蜜客",// 标题
				name : "mikeName",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "消费折扣",// 标题
				name : "discount",
				width : 80,// 宽度
				type:"string"//数据类型
			},{
				title : "分账比例",// 标题
				name : "ratio",
				width : 150,// 宽度
				type:"string"//数据类型
			},{
				title : "分账状态",// 标题
				name : "stateInfo",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "争议描述",// 标题
				name : "note",
				width : 200,// 宽度
				type:"string"//数据类型
			},{
				title : "发送部门",// 标题
				name : "departmentName",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "部门意见",// 标题
				name : "opinion",
				width : 200,// 宽度
				type:"string"//数据类型
			},{
				title : "消费金额",// 标题
				name : "expense",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "会员返利",// 标题
				name : "memberAmount",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "向蜜客返佣金",// 标题
				name : "mikeAmount",
				width : 120,// 宽度
				type:"string"//数据类型
			},{
				title : "商家营收",// 标题
				name : "sellerAmount",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "所属合作商佣金",// 标题
				name : "bpartnerAmount",
				width : 120,// 宽度
				type:"string"//数据类型
			},{
				title : "消费合作商佣金",// 标题
				name : "cpartnerAmount",
				width : 120,// 宽度
				type:"string"//数据类型
			},{
				title : "平台收入",// 标题
				name : "platformAmount",
				width : 100,// 宽度
				type:"string"//数据类型
			}]
     },permissions);
	
	
//	var html = [];
//	html.push('<table class="table table-hover table-bordered table-striped info" >');
//	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">分账列表</caption>');
//	
//	html.push('<thead>');
//	html.push('<tr>');
//	html.push('<th >订单号</th>');
//	html.push('<th >订单创建时间</th>');
//	html.push('<th >触发分账时间</th>');
//	html.push('<th >处理时间</th>');
//	html.push('<th >会员编号</th>');
//	html.push('<th >会员</th>');
//	html.push('<th >消费商家</th>');
//	html.push('<th >所属合作商</th>');
//	html.push('<th >消费合作商</th>');
//	html.push('<th >所属向蜜客</th>');
//	html.push('<th >消费折扣</th>');
//	html.push('<th >分账比例</th>');
//	html.push('<th >分账状态</th>');
//	html.push('<th >争议描述</th>');
//	html.push('<th >发送部门</th>');
//	html.push('<th >部门意见</th>');
//	html.push('<th >消费金额</th>');
//	html.push('<th >会员返利</th>');
//	html.push('<th >向蜜客返佣金</th>');
//	html.push('<th >商家营收</th>');
//	html.push('<th >所属合作商佣金</th>');
//	html.push('<th >消费合作商佣金</th>');
//	html.push('<th >平台收入</th>');
//	
//	html.push('</tr>');
//	html.push('</thead>');
//	html.push('<tbody>');
//	if(null != data && data.content.length > 0){
//		for (var i = 0; i < data.content.length; i++) {
//			html.push('<tr>');
//			html.push('<td>' + (undefined == data.content[i].id ? "-" : data.content[i].id) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].createDate ? "-" : data.content[i].createDate) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].createDate ? "-" : data.content[i].createDate) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].handleTime ? "-" : data.content[i].handleTime) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].memberId ? "-" : data.content[i].memberId) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].memberName ? "-" : data.content[i].memberName) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].sellerName ? "-" : data.content[i].sellerName) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].bpartnerName ? "-" : data.content[i].bpartnerName) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].cpartnerName ? "-" : data.content[i].cpartnerName) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].mikeName ? "-" : data.content[i].mikeName) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].discount ? "-" : data.content[i].discount) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].ratio ? "-" : data.content[i].ratio) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].stateInfo ? "-" : data.content[i].stateInfo) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].note ? "-" : data.content[i].note.replace(/\（.*/, "")) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].departmentName ? "-" : data.content[i].departmentName) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].opinion ? "-" : data.content[i].opinion) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].expense ? "-" : data.content[i].expense) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].memberAmount ? "-" : data.content[i].memberAmount) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].mikeAmount ? "-" : data.content[i].mikeAmount) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].sellerAmount ? "-" : data.content[i].sellerAmount) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].bpartnerAmount ? "-" : data.content[i].bpartnerAmount) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].cpartnerAmount ? "-" : data.content[i].cpartnerAmount) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].platformAmount ? "-" : data.content[i].platformAmount) + '</td>');
//			html.push('</tr>');
//		}
//	}else{
//		html.push('<tr>');
//		html.push('<td colspan="100">暂无数据</td>');
//		html.push('</tr>');
//	}
//	html.push('</tbody>');
//	html.push('</table>');
//	obj.find('div').eq(0).html(html.join(''));
}

function withdrawSuccess(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;提现列表 &nbsp;&nbsp;共计【'+data.total+'】条&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#refundBillPendingHistoryListForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	//checkable :false,
	    	//identifier : "bid",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
				title : "提现编号",// 标题
				name : "id",
				width : 130,// 宽度
				type:"string"//数据类型
			},{
				title : "客户名称",// 标题
				name : "corporateName",
				width : 130,// 宽度
				type:"string"//数据类型
			},{
				title : "发起人类型",// 标题
				name : "userType",
				width : 100,// 宽度
				type:"string",//数据类型
				customMethod : function(value, data) {
					var userType=data.userType;
					var userTypeText="-";
					if(userType==1){
						userTypeText="商户提现";
					}else if(userType==2){
						userTypeText="会员提现";
					}else if(userType==3){
						userTypeText="向蜜客提现";
					}else if(userType==4){
						userTypeText="合作商提现";
					}else if(userType==5){
						userTypeText="连锁店";
					}
						return userTypeText;
				}
			},{
				title : "提现时间",// 标题
				name : "withdrawDate",
				width : 160,// 宽度
				type:"string"//数据类型
			},{
				title : "处理时间",// 标题
				name : "handleTime",
				width : 160,// 宽度
				type:"string"//数据类型
			},{
				title : "提现类别",// 标题
				name : "withdrawType",
				width : 100,// 宽度
				type:"string",//数据类型
					customMethod : function(value, data) {
						var withdrawType=data.withdrawType;
						var withdrawTypeText="-";
						if(withdrawType==1){
							withdrawTypeText="从返利提现";
						}else if(withdrawType==2){
							withdrawTypeText="从佣金提现";
						}else if(withdrawType==3){
							withdrawTypeText="从营收提现";
						}
							return withdrawTypeText;
					}
			},{
				title : "订单状态",// 标题
				name : "stateInfo",
				width : 120,// 宽度
				type:"string"//数据类型
			},{
				title : "争议描述",// 标题
				name : "note",
				width : 160,// 宽度
				type:"string"//数据类型
			},{
				title : "发送部门",// 标题
				name : "departmentName",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "部门意见",// 标题
				name : "opinion",
				width : 180,// 宽度
				type:"string"//数据类型
			},{
				title : "账户总流水",// 标题
				name : "accountFlow",
				width : 150,// 宽度
				type:"string"//数据类型
			},{
				title : "佣金",// 标题
				name : "commission",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "已提现",// 标题
				name : "alreadyWithdraw",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "账户余额",// 标题
				name : "balance",
				width : 100,// 宽度
				type:"string"//数据类型
			},{
				title : "提现金额",// 标题
				name : "withdraw",
				width : 100,// 宽度
				type:"string"//数据类型
			}]
     },permissions);
	
	
//	var html = [];
//	html.push('<table class="table table-hover table-bordered table-striped info" >');
//	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">提现列表</caption>');
//	html.push('<thead>');
//	html.push('<tr>');
//	html.push('<th >提现编号</th>');
//	html.push('<th >客户名称</th>');	
//	html.push('<th >发起人类型</th>');
//	html.push('<th >提现时间</th>');
//	html.push('<th >处理时间</th>');
//	html.push('<th >提现类别</th>');
//	html.push('<th >订单状态</th>');
//	html.push('<th >争议描述</th>');
//	html.push('<th >发送部门</th>');
//	html.push('<th >部门意见</th>');
///*	html.push('<th >账户总流水</th>');
//	html.push('<th >佣金</th>');
//	html.push('<th >已提现</th>');*/
//	html.push('<th >账户余额</th>');
//	html.push('<th >提现金额</th>');
//	html.push('</tr>');
//	html.push('</thead>');
//	html.push('<tbody>');
//	if(null != data && data.content.length > 0){
//		for (var i = 0; i < data.content.length; i++) {
//			html.push('<tr>');
//			html.push('<td>' + (undefined == data.content[i].id ? "-" : data.content[i].id) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].corporateName ? "-" : data.content[i].corporateName) + '</td>');
//			var userType=data.content[i].userType;
//			var userTypeText="";
//			if(userType==1){
//				userTypeText="商户提现";
//			}else if(userType==2){
//				userTypeText="会员提现";
//				
//			}else if(userType==3){
//				userTypeText="向蜜客提现";
//			}else if(userType==4){
//				userTypeText="合作商提现";
//			}else if(userType==5){
//				userTypeText="连锁店";
//			}
//			html.push('<td>' + (undefined == userTypeText ? "-" : userTypeText) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].withdrawDate ? "-" : data.content[i].withdrawDate) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].handleTime ? "-" : data.content[i].handleTime) + '</td>');
//			var withdrawType=data.content[i].withdrawType;
//			var withdrawTypeText="";
//			if(withdrawType==1){
//				withdrawTypeText="从返利提现";
//			}else if(withdrawType==2){
//				withdrawTypeText="从佣金提现";
//			}else if(withdrawType==3){
//				withdrawTypeText="从营收提现";
//			}
//			html.push('<td>' + (undefined == withdrawTypeText ? "-" : withdrawTypeText) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].stateInfo ? "-" : data.content[i].stateInfo) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].note ? "-" : data.content[i].note.replace(/\（.*/, "")) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].departmentName ? "-" : data.content[i].departmentName) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].opinion ? "-" : data.content[i].opinion) + '</td>');
//		/*	html.push('<td>' + (undefined == data.content[i].accountFlow ? "-" : data.content[i].accountFlow) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].commission ? "-" : data.content[i].commission) + '</td>');
//			html.push('<td>' + (undefined == data.content[i].alreadyWithdraw ? "-" : data.content[i].alreadyWithdraw) + '</td>');*/
//			html.push('<td>' + (undefined == data.content[i].balance ? "-" : data.content[i].balance) + '</td>');
//			var wr=(parseFloat(data.content[i].withdraw)+parseFloat(data.content[i].rationMoney)).toFixed(2);
//			html.push('<td>' +  wr + '</td>');
//			
//			html.push('</tr>');
//		}
//	}else{
//		html.push('<tr>');
//		html.push('<td colspan="100">暂无数据</td>');
//		html.push('</tr>');
//	}
//	html.push('</tbody>');
//	html.push('</table>');
//	obj.find('div').eq(0).html(html.join(''));
}