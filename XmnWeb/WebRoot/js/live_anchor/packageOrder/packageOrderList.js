var packageOrderList;
$(document).ready(function() {
	packageOrderList = $('#packageOrderList').page({
		url : 'packageOrder/manage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
		paramForm : 'packageOrderForm'
	});

	inserTitle(' > 直播频道> <a href="packageOrder/manage/init.jhtml" target="right">套餐订单</a>','packageOrderList',true);
	
	$("#export").click(function(){
		$form = $("#packageOrderForm").attr("action","packageOrder/manage/export.jhtml");
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
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条订单，总金额为【￥'+data.titleInfo.totalAmount+'】&nbsp;，实付金额为【￥'+data.titleInfo.realAmount+'】&nbsp;</font></caption>';
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
					title : "订单编号",// 标题
					name : "orderNo",
					//sort : "up",
					width : 120,// 宽度
					leng : 3,//显示长度
					type:"stirng"//数据类型
			},{
				title : "会员信息",// 标题
				name : "uname",
				width : 120,// 宽度
				type:"stirng",//数据类型
				customMethod : function(value, data) {
					return value+"<br/>("+(data.phone==null?"-":data.phone)+")"
				}
		},{
			title : "套餐信息",// 标题
			name : "title",
			//sort : "up",
			width : 120,// 宽度
			type:"stirng",//数据类型
			customMethod:function(value,data){
				return value+"<br/>("+data.sellername+")";
			}
		},{
			title : "支付状态",// 标题
			name : "status",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"stirng",//数据类型
			customMethod : function(value, data) {
				if(data.status==0){
					return "未支付";
				} 
				if(data.status==1){
					return "支付成功";
				} 
				if(data.status==2){
					return "支付失败";
				}
				if(data.status==3){
					return "取消支付";
				}
				if(data.status==4){
					return "退款成功";
				}
				return "-";
			}
		},{
			title : "支付方式",// 标题
			name : "paymentTypeText",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
		},{
			title : "数量",// 标题
			name : "nums",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
		},
		{
			title : "订单总金额（现金价）",// 标题
			name : "totalAmount",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
		},
		{
			title : "订单总金额（鸟币价）",// 标题
			name : "totalCoinAmount",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
		},
		{
			title : "下单时间",// 标题
			name : "createTime",
			width : 160,// 宽度
			type:"date"//数据类型
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["view","refund"],// 不需要选择checkbox处理的权限
				width : 100,// 宽度
				// 当前列的中元素
				cols : [{
					title : "退款",// 标题
					linkInfoName : "method",
					linkInfo : {
						method:"orderRefund",
						param : ["orderNo"],// 参数
						permission : "refund"// 列权限
					}
				},{
					title : "查看",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "packageOrder/manage/view.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px"
						},
						param : ["orderNo"],// 参数
						permission : "view"// 列权限
					}
				}] 
	}},permissions);
}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

/**
 * 订单退款
 * 修改 订单状态 并 注销发放的优惠券和抵用券
 * @param bid
 */
function orderRefund(bid){
	$("#orderNo").val(bid);
	$("#refundDescriptionModal").modal();
}

function refund(){
	var description = $("#description").val();
	var orderNo = $("#orderNo").val();
	
	var url=contextPath+"/packageOrder/manage/refund.jhtml";
	
	$.ajax({
		type : 'post',
		url : url,
		data :{'description':description,'orderNo':orderNo},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				setTimeout(function(){
        			location.href =contextPath+'/packageOrder/manage/init.jhtml';
        		}, 1000);
		    }			
			showSmReslutWindow(data.success, data.msg);
			
		},
		error : function(data) {
			window.messager.warning(data.msg);
		}
	});
}

