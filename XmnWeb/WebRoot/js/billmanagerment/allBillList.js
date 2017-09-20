var allBillList;
$(document).ready(function() {
	allBillList = $('#allBillList').page({
		url : 'billmanagerment/allbill/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchBillForm'
	});
	
	//区域
	var ld = $("#ld").areaLd({
		//url : "billmanagerment/allbill/init/ld.jhtml",
		isChosen : true
	});
	// 商圈
	$("#zoneid").chosenObject({
		id : "zoneid",
		hideValue : "bid",
		showValue : "title",
		url : "common/business/businessInfo.jhtml",
		isChosen:true
	});
	//重置选择查询条件
	$("input[data-bus=reset]").click(function(){		
		$("#zoneid").trigger("chosen:updated");
	});
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		minuteStep:1,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	inserTitle(' > 订单管理 > <a href="billmanagerment/allbill/init.jhtml" target="right">所有订单</a>','allbillSpan',true);
	$("#export").click(function(){
		$form = $("#searchBillForm").attr("action","billmanagerment/allbill/export.jhtml");
		$form[0].submit();
	});
	$("input[data-bus=reset]").click(function(){

		$("#ld").find("select").trigger("chosen:updated");
	});	
	
	beachLiveLedger();
});

/**
 * 批量分账
 */	
function beachLiveLedger(){
	 $("#liveLedger").click(function(){
		 console.info("点击");
		var  data = allBillList.getIds();
		console.info("data:"+data);
		if(!data){
			
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		liveHandleLedger(data);
	 })
	
}


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条订单，总金额为【￥'+(0 == data.content.length ? "0" : data.content[0].totalAmount)+'】&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	checkable :true,
	    	checkdisenable:"disableCheck",
	    	identifier : "bid",
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
						required : true,// 是否必须显示，如果为true则列没有权限则也会显示name所表示的数据
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
						permission : null// 单列权限
					}
					
			},{
				title : "用户呢称",// 标题
				name : "nname",
				width : 200,// 宽度
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
			
		},
		/*{
			title : "寻蜜客ID",// 标题
			name : "xmerID",
			width : 120,// 宽度
			type:"stirng"//数据类型
		},
		{
			title : "寻蜜客姓名",// 标题
			name : "xmerName",
			width : 120,// 宽度
			type:"stirng"//数据类型
		},{
			title : "寻蜜客手机号",// 标题
			name : "xmerPhoneNo",
			width : 150,// 宽度
			type:"stirng"//数据类型
		},*/
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
		},{
			title : "区域",// 标题
			name : "cityArea",
			width : 150,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "所在商圈",// 标题
			name : "zoneName",
			width : 150,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "支付流水号",// 标题
			name : "payid",
			//sort : "up",
			width : 150,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "第三方支付账号",// 标题
			name : "thirdUid",
			//sort : "up",
			width : 160,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "支付方式",// 标题
			name : "payTypeText",
			width : 150,// 宽度
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
			title : "鸟币支付金额",// 标题
			name : "liveCoin",
			//sort : "up",
			width : 130,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
			
		},
		{
			title : "商家专享鸟币支付金额",// 标题
			name : "sellerCoin",
			//sort : "up",
			width : 180,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
			
		},
		{
			title : "商户营收金额",// 标题
			name : "sellerAmount",
			//sort : "up",
			width : 130,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
			
		},
		{
			title : "商户店外收益",// 标题
			name : "sellermoney",
			//sort : "up",
			width : 130,// 宽度
			type:"string"//数据类型
			
		},
		/*{
			title : "用户返利积分",// 标题
			name : "userMoney",
			//sort : "up",
			width : 130,// 宽度
			type:"string"//数据类型
			
		},*/
		{
			title : "寻蜜客分账金额",// 标题
			name : "mikeAmount",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "寻蜜客上级分账金额",// 标题
			name : "parentMikeAmount",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "寻蜜客上上级分账金额",// 标题
			name : "topMikeAmount",
			//sort : "up",
			width : 160,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "经销商分账金额",// 标题
			name : "cpartnerAmount",
			//sort : "up",
			width : 130,// 宽度
			type:"string"//数据类型
			
		},
//		{
//			title : "商户店外收益比例",// 标题
//			name : "jointBpartner",
//			//sort : "up",
//			width : 130,// 宽度
//			type:"string"//数据类型
//			
//		},
		{
			title : "平台分账金额",// 标题
			name : "platformAmount",
			//sort : "up",
			width : 130,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "中脉分账金额",// 标题
			name : "otherAmount",
			//sort : "up",
			width : 120,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "钱包支付金额",// 标题
			name : "walletAmount",
			//sort : "up",
			width : 110,// 宽度
			type:"number"//数据类型
		},
		{
			title : "第三方支付金额",// 标题
			name : "payment",
			//sort : "up",
			width : 110,// 宽度
			type:"number"//数据类型
		},
		{
			title : "优惠券面额总数",// 标题
			name : "cdenom",
			//sort : "up",
			width : 120,// 宽度
			type:"number"//数据类型
			
		},
		{
			title : "优惠券支付金额",// 标题
			name : "cuser",
			//sort : "up",
			width : 120,// 宽度
			type:"number"//数据类型
		},
		{
			title : "优惠券类型",// 标题
			name : "couponType",
			//sort : "up",
			width : 120,// 宽度
			type:"number",//数据类型
			customMethod : function(value, data) {
				var type = data.couponType;
                  //alert(type);
				var result;
				if(1 == type){
					result = "消费优惠劵";
				}else if(2 == type){
					result = "商家券";
				}else if(3 == type){
					result = "粉丝券";
				}else{
					result = "-";
				}
				return result;
			}
		},
		{
			title : "减免金额",// 标题
			name : "reduction",
			width : 80,// 宽度
			type:"string"//数据类型
		},
		{
			title : "商家活动减免金额",// 标题
			name : "fullReduction",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "赠送积分",// 标题
			name : "integral",
			//sort : "up",
			width : 130,// 宽度
			type:"string"//数据类型
			
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
			title : "所属商家编号",// 标题
			name : "genussellerid",
			width : 110,// 宽度
			leng : 8,//显示长度
			type:"number",//数据类型
			isLink : true,// 表示当前列是否是超链接 true:是 false：不是
			// 只有当isLink为true时 才有效
			link : {
				linkInfoName : "modal", // href , modal ,method
				linkInfo : {
					modal : {
						url : "billmanagerment/allbill/viewWalletInit.jhtml",// url
						position:"300px",// 模态框显示位置
						width:"600px",
						title : "商家钱包信息"
					}
				},
				param : ["sellerid"],// 参数
				permission : "detail"// 单列权限
			},
			customMethod : function(v,d){
				var $value = $(v);
				$value.attr("data-url","billmanagerment/allbill/viewWalletInit.jhtml?sellerid="+d.genussellerid);
				return $value[0].outerHTML;
			}
		},
		{
			title : "消费商家编号",// 标题
			name : "sellerid",
			width : 110,// 宽度
			leng : 8,//显示长度
			type:"number",//数据类型
			isLink : true,// 表示当前列是否是超链接 true:是 false：不是
			// 只有当isLink为true时 才有效
			link : {
				linkInfoName : "modal", // href , modal ,method
				linkInfo : {
					modal : {
						url : "billmanagerment/allbill/viewWalletInit.jhtml",// url
						position:"300px",// 模态框显示位置
						width:"600px",
						title : "商家钱包信息"
					}
				},
				param : ["sellerid"],// 参数
				permission : "detail"// 单列权限
			}
		},
		{
			title : "分账模式",// 标题
			name : "ledgerMode",
			width : 230,// 宽度
			type:"string",//数据类型
			customMethod:function(value, data) {
				if(data.ledgerMode==0){
					return "正常分账模式";
				}else if(data.ledgerMode==1){
					return "不参与分账模式";
				}else if(data.ledgerMode==2){
					return "仅商家参与分账模式";
				}else{
					return "-";
				}
			}
				
		},
		{
			title : "直播分账开关",// 标题
			name : "liveLedgerStr",
			width : 230,// 宽度
			type:"string"//数据类型
		},
		{
			title : "直播分账方式",// 标题
			name : "liveLedgerStyleStr",
			width : 230,// 宽度
			type:"string"//数据类型
		},
		{
			title : "直播分账类型",// 标题
			name : "liveLedgerModeStr",
			width : 230,// 宽度
			type:"string"//数据类型
				
		},
		{
			title : "直播分账比例",// 标题
			name : "liveLedgerRatioStr",
			width : 230,// 宽度
			type:"string"//数据类型
		},
		{
			title : "分账状态",// 标题
			name : "hstatusText",
			width : 230,// 宽度
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
			title : "支付时间",// 标题
			name : "zdate",
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
//		{
//			title : "平台收益",// 标题
//			name : "platformAmount",
//			width : 100,// 宽度
//			//sort : "up",
//			type:"number"//数据类型
//		},
//		{
//			title : "运营商收益",// 标题
//			name : "bpartnerAmount",
//			//sort : "up",
//			width : 100,// 宽度
//			type:"number"//数据类型
//		},
//		{
//			title : "商家营收",// 标题
//			name : "sellerAmount",
//			//sort : "up",
//			width : 100,// 宽度
//			type:"number"//数据类型
//		},
//		{
//			title : "寻蜜客佣金",// 标题
//			name : "mikeAmount",
//			//sort : "up",
//			width : 100,// 宽度
//			leng : 8,//显示长度
//			type:"number"//数据类型
//		},
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
			title : "是否首单",// 标题
			name : "isFirstName",
			width : 80,// 宽度
			type:"string"//数据类型
		},
		{
			title : "推荐人手机号",// 标题
			name : "rPhone",
			width : 80,// 宽度
			type:"string"//数据类型
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
		},{
			title : "活动描述",// 标题
			name : "activityContent",
			width : 200,// 宽度
			type:"string"//数据类型
		}
		,{
			title : "派奖结果描述",// 标题
			name : "activityRest",
			width : 200,// 宽度
			type:"string"//数据类型
		}/*,
		{
				title : "用户手机",// 标题
				name : "phoneid",
				width : 50,// 宽度
				leng : 8,//显示长度
				type:"stirng",//数据类型
				isLink : true,// 表示当前列是否是超链接 true:是 false：不是
				// 只有当isLink为true时 才有效
				link : {
					linkInfoName : "href", // href , modal ,method
					linkInfo : {
						href : "billmanagerment/allbill/view/init", // 表示超链接调用的连接
					},
					param : ["bid"],// 参数
					permission : "detail"// 单列权限
				}
			}*/],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["detail"],// 不需要选择checkbox处理的权限
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
						permission : "detail"// 列权限
					}
				},{
					title : "分账",// 标题
					linkInfoName : "method",
					linkInfo : {
						method :"liveHandleLedger",
						param : ["bid"],// 参数
						permission : "detail"// 单列权限
					},
					customMethod : function(value, data) {
						if(!data.disableCheck){
							return value;
						}else{
							return "";
						}
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

function liveHandleLedger(bids){
	showSmConfirmWindow(function(){
		liveHandLedgerAjax(bids);
	}, "你确定手动分账吗？");
}
function liveHandLedgerAjax(bids){
	$.ajax({
		type : 'post',
		url : 'billmanagerment/allbill/liveLedger.jhtml' + '?t=' + Math.random(),
		data : {
			'bids':bids			
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
