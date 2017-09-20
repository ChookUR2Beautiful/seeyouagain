var notPayBillList;
$(document).ready(function() {
	notPayBillList = $('#notPayBillList').page({
		url : 'billmanagerment/notpaybill/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
		paramForm : 'searchNotPayBillForm'
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
	inserTitle(' > 订单管理 > <a href="billmanagerment/notpaybill/init.jhtml" target="right">待支付订单</a>','allbillSpan',true);
	
	$("#export").click(function(){
		$form = $("#searchNotPayBillForm").attr("action","billmanagerment/notpaybill/export.jhtml");
		$form[0].submit();
	});
});


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;待支付订单信息&nbsp;&nbsp;共计【'+data.total+'】条订单，总金额为【￥'+(0 == data.content.length ? "0" : data.content[0].notpaytotalAmount)+'】&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchNotPayBillForm").serialize());
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
					title : "用户编号",// 标题
					name : "uid",
					//sort : "up",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"stirng",//数据类型
					isLink : true,// 表示当前列是否是超链接 true:是 false：不是
					// 只有当isLink为true时 才有效
					link : {
						linkInfoName : "modal", // href , modal ,method
						linkInfo : {
							modal : {
								url : "member/memberList/viewWallet.jhtml",// url
								position:"300px",// 模态框显示位置
								width:"600px",
								title : "用户钱包信息"
							}
						},
						param : ["uid"],// 参数
						permission : "details"// 单列权限
					}
					
			},{
				title : "用户呢称",// 标题
				name : "nname",
				width : 150,// 宽度
				type:"stirng"//数据类型
				
		},{
			title : "用户手机",// 标题
			name : "phoneid",
			//sort : "up",
			width : 110,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "订单号",// 标题
			name : "bid",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
			
		},{
			title : "支付方式",// 标题
			name : "payTypeText",
			width : 130,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
			
		},
		{
			title : "订单金额",// 标题
			name : "money",
			//sort : "up",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
			
		},
		{
			title : "折扣",// 标题
			name : "baseagio",
			//sort : "up",
			width : 80,// 宽度
			type:"number",//数据类型
			customMethod : function(value, data) {
				return data.baseagio + "%";
			}
		},
		{
			title : "所属商家",// 标题
			name : "genusname",
			width : 200,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "消费商家",// 标题
			name : "sellername",
			width : 200,// 宽度
			type:"string"//数据类型
		},
		{
			title : "分账状态",// 标题
			name : "hstatusText",
			width : 300,// 宽度
			type:"string"//数据类型
		},
		{
			title : "订单状态",// 标题
			name : "statusText",
			width : 130,// 宽度
			type:"string"//数据类型
		},
		{
			title : "下单时间",// 标题
			name : "sdate",
			width : 160,// 宽度
			type:"date"//数据类型
		},
		{
			title : "返利金额",// 标题
			name : "rebate",
			//sort : "up",
			width : 100,// 宽度
			type:"number"//数据类型
		},
		{
			title : "平台收益",// 标题
			name : "platformAmount",
			width : 100,// 宽度
			//sort : "up",
			type:"number"//数据类型
		},
		{
			title : "运营商收益",// 标题
			name : "bpartnerAmount",
			//sort : "up",
			width : 100,// 宽度
			type:"number"//数据类型
		},
		{
			title : "商家营收",// 标题
			name : "sellerAmount",
			//sort : "up",
			width : 100,// 宽度
			type:"number"//数据类型
		},
		{
			title : "寻蜜客佣金",// 标题
			name : "mikeAmount",
			//sort : "up",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
		},
		{
			title : "手续费",// 标题
			name : "feesMoney",
			//sort : "up",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
		},
		{
			title : "平台补贴占比",// 标题
			name : "flatAgio",
			//sort : "up",
			width : 110,// 宽度
			leng : 8,//显示长度
			type:"number",//数据类型
			customMethod : function(value, data) {
				return data.flatAgio + "%";
			}
		},
		{
			title : "平台补贴金额",// 标题
			name : "flatMoney",
			//sort : "up",
			width : 110,// 宽度
			type:"number"//数据类型
		},
		{
			title : "派奖状态",// 标题
			name : "isActivity",
			type:"string",//数据类型
			width : 100,// 宽度
			customMethod : function(value, data) {
				var type = data.isActivity;
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
		},
		{
			title : "活动描述",// 标题
			name : "activityContent",
			width : 200,// 宽度
			type:"string"//数据类型
		},
		{
			title : "派奖结果描述",// 标题
			name : "activityRest",
			width : 150,// 宽度
			type:"string"//数据类型
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["details"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
					title : "查看",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "billmanagerment/allbill/view/init.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px"
						},
						param : ["bid"],// 参数
						permission : "details"// 列权限
					}
				}] 
	}},permissions);
	

	/*
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	if(null != data && data.content.length > 0)
	{
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">待支付订单信息<font style="float:right;">共计【'+data.total+'】条订单，总金额为【￥'+(undefined == data.content[0].notpaytotalAmount ? "0" : data.content[0].notpaytotalAmount)data.content[0].notpaytotalAmount+'】&nbsp;</font></caption>');
	}
	html.push('<thead>');
	html.push('<tr>');
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:60px;">操作</th>');
	}
	
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
	html.push('<th >平台补贴占比</th>');
	html.push('<th >平台补贴金额</th>');
	html.push('<th >派奖状态</th>');
	html.push('<th >活动描述</th>');
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
			if(permissions.details == 'true'){
				html.push('<a href="javascript:void();" data-title="" data-type="ajax" data-position="" data-width="960px" data-url="billmanagerment/notpaybill/view/init.jhtml?bid='+data.content[i].bid+'" data-toggle="modal">查看</a>&nbsp;&nbsp;');
			}
			html.push('</td>');
		}
		html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + '</td>');
		html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].bid ? "-" : data.content[i].bid) + '</td>');
		html.push('<td>' + (undefined == data.content[i].payTypeText ? "-" : data.content[i].payTypeText) + '</td>');
		html.push('<td>');
		html.push('<a href="javascript:void();" data-title="订单金额详情" data-type="ajax" data-position="200" data-width="30%" data-url="billmanagerment/allbill/init/orderCmount.jhtml?bid='+data.content[i].bid+'"  data-toggle="modal">'+(undefined == data.content[i].money ? "-" : '￥'+data.content[i].money)+'</a>&nbsp;&nbsp;');
		html.push('</td>');
		html.push('<td>' + (undefined == data.content[i].baseagio+"%" ? "-" : data.content[i].baseagio) +"%" +'</td>');
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
		html.push('<td>' + (undefined == data.content[i].flatAgio+"%" ? "-" : data.content[i].flatAgio)+"%" + '</td>');
		html.push('<td>' + (undefined == data.content[i].flatMoney ? "-" : "￥" +data.content[i].flatMoney)+ '</td>');
		
		var isActivityTemp = data.content[i].isActivity;
		var result = "-";
		if(0 == isActivityTemp){
			result = "未派";
		}else if(1 == isActivityTemp){
			result = "已派";
		}else if(2 == isActivityTemp){
			result = "不用派";
		}
		html.push('<td>' + result + '</td>');
		
		html.push('<td>' + ("" == data.content[i].activityContent || undefined== data.content[i].activityContent ? "-" : data.content[i].activityContent) + '</td>');
		
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
	obj.find('div').eq(0).html(html.join(''));
*/}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

