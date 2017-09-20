var allBillList;
$(document).ready(function() {
	allBillList = $('#refundBillList').page({
		url : 'billmanagerment/refundBillPending/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
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
	$('#sdate').datetimepicker({
		minView :2,
		autoclose : 1,
		autoclose : 1,
		maxView :3,
		startView : 2,
		format : 'yyyy-mm-dd'
	});
	
	inserTitle(' > 订单管理 > <a href="billmanagerment/refundBillPending/init.jhtml" target="right">待退款订单</a>','refundBillPending',true);
	$("#export").click(function(){
		$form = $("#searchForm").attr("action","billmanagerment/refundBillPending/export.jhtml");/*billmanagerment/refundBillPending/export.jhtml*/
		$form[0].submit();
	});
});
function queryBM(object,parms){
    var tags = document.getElementsByName("bumen") ;
    for(i=0;i<tags.length;i++){
    	$(tags[i]).attr("class", "btn btn-default");
    }
    $("#strStatus").val(parms);
	allBillList.reload();
	$(object).attr("class", "btn btn-success");
}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	/*var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info " >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">待退款订单<font style="float:right;">共计【'+data.total+'】条订单&nbsp;</font></caption>');

	html.push('<thead>');
	html.push('<tr>');
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:100px;">操作</th>');
	}
	
	html.push('<th >订单号</th>');
	html.push('<th >支付流水号</th>');
	html.push('<th >订单金额</th>');	
	html.push('<th >平台补贴占比</th>');
	html.push('<th >平台补贴金额</th>');	
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
	html.push('<th >商家申诉处理结果</th>');
	html.push('<th >退款流程状态</th>');
	html.push('<th >是否参与活动</th>');
	html.push('<th >活动描述</th>');	
	html.push('<th >备注</th>');	
	html.push('<th >派奖结果描述</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
if(null != data && data.content.length > 0)
	{
	for (var i = 0; i < data.content.length; i++) {
				
		html.push('<tr>');
		if(!isEmptyObject(permissions)){
			html.push('<td>');
			if(permissions.treatment == 'true'&&data.content[i].paytype!="1000011"){
				//html.push('<a href="javascript:;" data-type="ajax" data-url="billmanagerment/refundBillPending/confirm/init.jhtml?id='+data.content[i].id+'" data-toggle="modal">退款</a>&nbsp;&nbsp;');
				html.push('<a  href="javascript:updatejk('+data.content[i].id+","+data.content[i].bid+","+data.content[i].money+ ","+data.content[i].paytype+')">退款</a>');
			}
			html.push('</td>');
		}
		html.push('<td>' + (undefined == data.content[i].bid ? "-" : data.content[i].bid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].payid ? "-" : data.content[i].payid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].money ? "-" : '￥'+data.content[i].money) + '</td>');
		html.push('<td>');
		html.push('<a href="javascript:void();" data-title="订单金额详情" data-type="ajax" data-position="200" data-width="30%" data-url="billmanagerment/allbill/init/orderCmount.jhtml?bid='+data.content[i].bid+'" data-toggle="modal">'+(undefined == data.content[i].money ? "-" : '￥'+data.content[i].money)+'</a>&nbsp;&nbsp;');
		html.push('</td>');
		
		html.push('<td>' + (undefined == data.content[i].flatAgio+"%" ? "-" : data.content[i].flatAgio)+"%" + '</td>');
		html.push('<td>' + (undefined == data.content[i].flatMoney ? "-" : '￥'+data.content[i].flatMoney) + '</td>');
		
		html.push('<td>' + (undefined == data.content[i].codeid ? "-" : data.content[i].codeid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].payTypeText ? "-" : data.content[i].payTypeText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + '</td>');
			var sellername=(undefined == data.content[i].sellername ? "-" : data.content[i].sellername);
		html.push('<td title ="'+sellername+'">' + substr(sellername) + '</td>');
		html.push('<td>' + (undefined == data.content[i].orderDate ? "-" : data.content[i].orderDate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].zdate ? "-" : data.content[i].zdate) + '</td>')
		html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].adate ? "-" : data.content[i].adate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].qdate ? "-" : data.content[i].qdate) + '</td>');
		var qppeal=(undefined == data.content[i].qppeal ? "-" : data.content[i].qppeal);
		html.push('<td title ="'+qppeal+'">' + substr(qppeal) + '</td>');
		html.push('<td>' + (undefined == data.content[i].processing ? "-" : data.content[i].processing) + '</td>');
		html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
		
		var isActivityTemp = data.content[i].isActivity;
		var result = "-";
		if(1 == isActivityTemp){
			result = "是";
		}else if(0 == isActivityTemp){
			result = "否";
		}
		html.push('<td>' + result + '</td>');
		html.push('<td>' + ("" == data.content[i].activityContent || undefined== data.content[i].activityContent ? "-" : data.content[i].activityContent) + '</td>');
		
        var remarks=(undefined == data.content[i].remarks ? "-" : data.content[i].remarks);	
		html.push('<td title ="'+remarks+'">' + substr(remarks) + '</td>');
		html.push('<td>' + (undefined == data.content[i].activityRest ? "-" : data.content[i].activityRest) + '</td>');
		html.push('</tr>');
	}
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="25">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));*/

	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条订单&nbsp;</font></caption>';
	obj.find('div').eq(0).scrollTablel({
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
				title : "订单号",// 标题
				name : "bid",
				width : 120,// 宽度
				type:"stirng"//数据类型
				
		},{
			title : "支付流水号",// 标题
			name : "payid",
			//sort : "up",
			width : 150,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "订单金额",// 标题
			name : "money",
			//sort : "up",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
			
		},{
			title : "返利支付金额",// 标题
			name : "profit",
			width : 100,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "赠送支付金额",// 标题
			name : "giveMoney",
			//sort : "up",
			width : 100,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "优惠券支付金额",// 标题
			name : "cuser",
			//sort : "up",
			width : 120,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "佣金支付金额",// 标题
			name : "commision",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
			
		},
		
		{
			title : "平台补贴占比",// 标题
			name : "flatAgio",
			//sort : "up",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"number",//数据类型
			customMethod : function(value, data) {
				return data.flatAgio+"%";
			}
		},
		{
			title : "平台补贴金额",// 标题
			name : "flatMoney",
			//sort : "up",
			width : 100,// 宽度
			type:"number",//数据类型
			customMethod : function(value, data) {
				return "￥" + data.flatMoney;
			}
		},
		
		{
			title : "消费验证号",// 标题
			name : "codeid",
			width : 100,// 宽度
			type:"string"//数据类型
			
		}
		
		,
		{
			title : "支付方式",// 标题
			name : "payTypeText",
			width : 200,// 宽度
			type:"string"//数据类型
		}
		,
		{
			title : "用户昵称",// 标题
			name : "nname",
			width : 200,// 宽度
			type:"number"//数据类型
		},{
			title : "用户手机",// 标题
			name : "phoneid",
			//sort : "up",
			width : 110,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "消费商家",// 标题
			name : "sellername",
			width : 200,// 宽度
			type:"string"//数据类型
		}
		,
		{
			title : "下单时间",// 标题
			name : "orderDate",
			sort : "up",
			width : 160,// 宽度
			type:"date"//数据类型
		}
		,
		{
			title : "支付时间",// 标题
			name : "zdate",
			sort : "up",
			width : 160,// 宽度
			type:"date"//数据类型
		}
		,
		{
			title : "申请退款时间",// 标题
			name : "sdate",
			width : 160,// 宽度
			sort : "up",
			type:"date"//数据类型
		},
		{
			title : "商家申诉时间",// 标题
			name : "adate",
			sort : "up",
			width : 160,// 宽度
			type:"date"//数据类型
		},
		{
			title : "申诉处理时间",// 标题
			name : "qdate",
			sort : "up",
			width : 160,// 宽度
			type:"date"//数据类型
		},
		{
			title : "商家申诉原因",// 标题
			name : "qppeal",
			width : 250,// 宽度
			type:"string"//数据类型
		},
		{
			title : "商家申诉处理结果",// 标题
			name : "processing",
			width : 200,// 宽度
			type:"number"//数据类型
		},
		{
			title : "退款流程状态",// 标题
			name : "statusText",
			width : 120,// 宽度
			type:"string"//数据类型
		},
		
		{
			title : "派奖状态",// 标题
			name : "isActivity",
			type:"string",//数据类型
			width : 100,// 宽度
			customMethod : function(value, data) {
				var type = data.isactivity;
                //alert(type);
				var result;
				if(0 == type){
					result = "未派";
				}else if(1 == type){
					result = "已派";
				}else if(2 == type){
					result = "不用派";
				}else{
					result = "-";
				}
				return result;
			}
		},{
			title : "活动描述",// 标题
			name : "activityConent",
			width : 200,// 宽度
			type:"string"//数据类型
		},{
			title : "派奖结果描述",// 标题
			name : "activityRest",
			width : 200,// 宽度
			type:"string"//数据类型
		},{
			title : "备注",// 标题
			name : "remarks",
			width : 150,// 宽度
			type:"string"//数据类型
		},{
			title : "退款金额",// 标题
			name : "refuMoney",
			width : 150,// 宽度
			type:"string"//数据类型
		}],
			//操作列
		handleCols : {
				title : "操作",// 标题
				queryPermission : ["treatment","recoveryorder"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
					title : "退款",// 标题
					linkInfoName : "method",
					linkInfo : {
						method :"updatejk",
						param : ["id","bid","refuMoney","paytype"],// 参数（退款金额 = 点单金额要减去减免金额）
						permission : "treatment"// 单列权限
					}
				},{
					title : "恢复",// 标题
					linkInfoName : "method",
					linkInfo : {
						method :"recoveryOrder",
						param : ["bid"],// 参数
						permission : "recoveryorder"// 单列权限
					},
					customMethod : function(value, data) {
						if(data.billStatus==14&&data.billSource==2){
							return value;
						}else{
							return "";
						}
					}
				}] 
	}},permissions);
}

//function substr(obj,length){
//	if(obj.length > length){
//		obj = obj.substring(0,length) +"...";
//	}
//	return obj;
//}

/**
 * 退款请求
 * @param {} id
 * @param {} bid
 * @param {} money
 * @param {} remarks
 */		
function updatejk(id,bid,money,paytype) {
//	if(paytype=="1000001"){
//		showTkConfirmWindow(function(){
//			formAjax(id,bid,money);
//		});
//	}else{
		showSmConfirmWindow(function(){
			formAjax(id,bid,money);
		}, "你确定执行退款吗？");
//	}
}
/**
 * 恢复订单
 * @param {} id
 * @param {} bid
 * @param {} money
 * @param {} remarks
 */		
function recoveryOrder(bid) {
		showSmConfirmWindow(function(){
			recoveryAjax(bid);
		}, "你确定恢复吗？");
}
function recoveryAjax(bid){
	$.ajax({
		type : 'post',
		url : 'billmanagerment/refundBillPending/recoveryOrder.jhtml' + '?t=' + Math.random(),
		data : {
			'bid':bid			
		},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {			 				
          	$('#prompt').hide();
			if (data.success) {
				allBillList.reload();
		    }			
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
		}
	});
}
function formAjax(id,bid,money){
		$.ajax({
			type : 'post',
			url : 'billmanagerment/refundBillPending/confirm.jhtml' + '?t=' + Math.random(),
			data : {
				'id':id,
				'bid':bid,
				'money':money
				//'remarks':remarks				
			},
			async:false,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {			 				
	          	$('#prompt').hide();
	          	if (data.success) {
					allBillList.reload();
			    }	
				showSmReslutWindow(data.success, data.msg);
				
				//支付宝打开页面（response不为空才打开）
				var res = data.data.response;
				console.info("res:"+res);
				if(null!=data.data.response && ""!=data.data.response.trim()){
					
					var body = window.document.body;
					var response = $.parseHTML(data.data.response);
					$.each(response,function(){
						if(this.nodeName.toLowerCase()=="form"){
							$(body).append($(this));
							var id = $(this).attr("id");
							$(this).attr("target","tkwindow");
							var $form = $(body).find($(this));
							if($form.length>0){
								$form.submit();
								$form.remove();
							} 
							return;
						}
					}); 
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
}
var win;
function openWind(){
	if(win){
		win.close();
	}
	win = window.open('','tkwindow'); 
}

