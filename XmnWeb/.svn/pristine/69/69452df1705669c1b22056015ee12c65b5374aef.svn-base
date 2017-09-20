var pageDiv;
$(document).ready(function() {

	// 行业类别
	$("#tradeSelect").tradeLd({
		isChosen : true,
		showConfig : [{name:"category",tipTitle:"请选择",width:'48%'},{name:"genre",tipTitle:"请选择",width:'49%'}]
	});
	
	// 商圈
	$("#zoneid").chosenObject({
		id : "zoneid",
		hideValue : "bid",
		showValue : "title",
		url : "common/business/businessInfo.jhtml",
		isChosen:true
	});
	
	// 合作商
	$("#jointid").chosenObject({
		id : "jointid",
		hideValue : "jointid",
		showValue : "corporate",
		url : "business_cooperation/joint/jointList.jhtml",
		isChosen:true
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
	inserTitle(' > 商家管理 > <span><a href="businessman/seller/init.jhtml" target="right">商家信息管理</a>','sellerList',true);

	$("#export").click(function(){
		$form = $("#sellerFromId").attr("action","businessman/seller/export.jhtml");
		$form[0].submit();
	});
	
	// 区域
	var ld = $("#ld").areaLd({
		//url : "businessman/seller/init/ld.jhtml",
		isChosen : true
	});
		
	pageDiv = $('#sellerInfoDiv').page({
		url : 'businessman/seller/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
		paramForm : 'sellerFromId'
	});
	
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/businessman/seller/init.jhtml';
			location.href =url;
		}
		$("#ld").find("select").trigger("chosen:updated");
		$("#tradeSelect").find("select").trigger("chosen:updated");
		$("#zoneid").trigger("chosen:updated");
		$("#jointid").trigger("chosen:updated");
	});
	/**
	 * 批量上线
	 */
	beachOnLine();
	
	/**
	 * 批量下线
	 */
	beachDownLine();
	
	/**
	 * 批量待上线
	 */	
	readyOnline();
	
	/**
	 * 批量设置平台补贴折扣
	 */
	batchSetFlatAgio();
	
	batchSellerDetailedIsFirst();
	
});

/**
 * 批量上线
 */	
function beachOnLine(){
	$("#beachOnLine").click(function(){
		if(!pageDiv.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		if(!pageDiv.validateChose("status", "3", "商家还未签约不能上线")){
			return;
		}
		if(!pageDiv.validateChose("isonline", "0,2,3", "商家已上线不能再次上线")){
			return;
		}
		var data = {ids:pageDiv.getIds(),isonline:1};
		requestBeach(data,"你确定要上线选中商家？");
	});
}

/**
 * 批量预上线
 */	
function readyOnline(){
	$("#readyOnline").click(function(){
		if(!pageDiv.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		if(!pageDiv.validateChose("status", "3", "商家还未签约不能预上线")){
			return;
		}
		if(!pageDiv.validateChose("isonline", "0,1,3", "商家已经是预上线不能再次预上线")){
			return;
		}
		var data = {ids:pageDiv.getIds(),isonline:2};
		requestBeach(data,"你确定要预上线选中商家？");
	});
}

/**
 * 批量下线
 */	
function beachDownLine(){
	 $("#beachDownLine").click(function(){
		    var flag=true;
			if(!pageDiv.getIds()){
				showWarningWindow("warning","请至少选择一条记录！");
				return;
			}
			if(!pageDiv.validateChose("isonline", "1,2", "商家还未上线不能下线")){
				return;
			}
			DownLine(flag);
	 })
	
}




function DownLine(flag){
	
	if(flag){
		var  data = pageDiv.getIds();
		var modalTrigger = new ModalTrigger({
			type : 'ajax',
			url : 'businessman/seller/downLine.jhtml?ids=' + data+'&&isonline=3' ,
			toggle : 'modal',
			position:''
		 });
		 modalTrigger.show();
		 }
}

/**
 * 上线公用
 * 
 * @param {}
 *            data
 * @param {}
 *            title
 */
function requestBeach(data,title){
	showSmConfirmWindow(function() {
					$.ajax({
				        type: "POST",
				        url: "businessman/seller/beachOnLine.jhtml",
				        data: data,
				        dataType: "json",
				        success: function(result){
							showSmReslutWindow(result.success, result.msg);
							pageDiv.reload();
				         }
				    });
			},title);
}


function sellerOption(flag,status){
	
	var ids = pageDiv.getIds();
	
	if(!pageDiv.getIds()){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	/*if(flag ==1){
		if(!pageDiv.validateChose("status", "3", "商家还未签约不能预上线")){
			return;
		}
	}*/
	
	showSmConfirmWindow(function(){
		$.ajax({
			type:"POST",
			url:"businessman/seller/beachOnLine/statusOption.jhtml",
			data:{ids:ids,flag:flag,state:status},
			dataType:"json",
			success:function(data){
				if(data.success) {
					setTimeout(function() {
						pageDiv.reload();
					}, 1000);
				}
				showSmReslutWindow(data.success,data.msg);
			},
			error:function(data){
				showSmReslutWindow(data.success, data.msg);
			}
		});
		
	},"确定修改吗？")
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
 * * 乘法函数，用来得到精确的乘法结果 * 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。 *
 * 调用：accMul(arg1,arg2) * 返回值：arg1乘以 arg2的精确结果
 */
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	//obj.find('div').eq(0).html($.renderGridView(sellerModel,data));
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;全部商家信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个商家&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#sellerFromId").serialize());
	updateAddBtnHref("#addSellerBto",callbackParam);
	obj.find('div').eq(0).scrollTablel({
		identifier : "sellerid",
		callbackParam : callbackParam,
		data:data.content, 
		caption : captionInfo,
		//操作列
		handleCols : {
			title : "操作",// 标题
			handlePermission : ["sx","xx","setFlatAgio","isFirst","resetPassW"],//需要用到checkbox
			queryPermission : ["xg", "ck","init"],// 不需要选择checkbox处理的权限
			width : 180,// 宽度
			// 当前列的中元素
			cols : [{
				title : "重置提现密码",// 标题
				linkInfoName : null,
				width : 50,
				linkInfo : {
					modal : {
						url : null,
						position : null,
						width : null
					},
					param : ["sellerid"],
					permission : "resetPassW"
				},
				customMethod : function(value, data) {
					var result = '<a href="javascript:void(0)" title="只有已签约的商家才有重置提现密码功能" class="hidden"></a>';
					if(data.status!=undefined && data.status == 3){
						result = '<a href="javascript:resetPassW('+data.sellerid+', '+2+');">重置提现密码</a>';
					}
					return result;
				}
			},{
				title : "绑定银行卡",// 标题
				linkInfoName : "href",
				// width : 50,
				linkInfo : {
					href : "businessman/seller/bindCardInit/init.jhtml",
					param : ["sellerid","sellername"],
					permission : "bdcard"
				},
				customMethod : function(value, data) {
					var result = '<a href="javascript:void(0)" title="只有已签约的商家才有重绑定银行卡功能"  class="hidden"></a>';
					if(data.status!=undefined && data.status == 3){
						result = value;
					}
					return result;
				}
			},{
				title : "是否锁定提现功能",// 标题
				linkInfoName : "method",
				// width : 50,
				linkInfo : {
					method : "lockOrNot",
					param : ["sellerid"],
					permission : "islock"
				},
				customMethod : function(value, data){
//					console.log(value);
					var result;
					if(data.status!=undefined && data.status == 3 || data.status == 5){
						if(data.isLock != undefined && data.isLock == 1){//解锁
							result = value.replace(/lockOrNot/,"unlock");
							result = result.replace(/是否锁定提现功能/,"解锁提现")
						}else if(data.isLock != undefined && data.isLock == 0){
							result = value.replace(/lockOrNot/,"lock");
							result = result.replace(/是否锁定提现功能/,"锁定提现");
						}else{
							result = '<a href="javascript:void(0);" title="是否锁定字段的值为空或者不合理" class="hidden"></a>'
						}
					}else{
						result = '<a href="javascript:void(0)" title="只有已签约的商家才有是否锁定提现功能" class="hidden"></a>'
					}
					return result;
				}
			},{
				title : "查看",// 标题
				linkInfoName : "href",
				// width : 30,
				linkInfo : {
					href : "businessman/seller/getInit.jhtml",
					param : ["sellerid"],
					permission : "ck"
				}
			},{
				title : "修改",// 标题
				linkInfoName : "href",
				// width : 30,
				linkInfo : {
					href : "businessman/seller/update/init.jhtml",
					param : ["sellerid"],
					permission : "xg"
				}
			},{
				title : "二维码",// 标题
				linkInfoName : "method",
				// width : 30,
				linkInfo : {
					method : "matrixView",
					param : ["sellerid","sellername"],
					permission : "init"
				}

			}] 
		},
		cols:[{
			title : "商家编号",
			name : "sellerid",
//			sort : "up",
			type : "string",
			width : 150
		},{
			title : "签约时间",
			name : "signdate",
//			sort : "up",
			type : "string",
			width : 160
		},{
			title : "商家名称",
			name : "sellername",
			//sort : "up",
			type : "string",
//			isLink : true,
//			link : {
//				linkInfoName : "href",
//				linkInfo : {
//					href : "businessman/seller/initOrder.jhtml"
//				},
//				param : ["sellerid"],
//				permission : "order",
//			},
			width : 150,
			leng : 8		
		},{
				title : "鸟币支付",
				name : "liveCoinPayStr",
				type : "string",
				width : 150,
				leng : 8,
				customMethod : function(value, data) {
					if(data.isPublic ==0){
						return value;
					}else{
						return "<a href='javascript:updateCoinStatus("+data.sellerid+","+data.liveCoinPay+");'>"+value+"</a>"; 
					}
				}
		},{
			title : "商家等级",
			name : "sellerGradeStr",
			type : "string",
			width : 150,
			leng : 8
		},{
			title : "商家手机号",
			name : "phoneid",
			type : "string",
			width : 150,
			leng : 8
		},{
			title : "所属行业",
			name : "hyText",
			type : "string",
			width : 150,
			leng : 8
		},{
			title : "连锁店",
			name : "lssellername",
			type : "string",
			width : 150,
			leng : 8
		},{
			title : "账号",
			name : "lssellername",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/sellerAccount/init.jhtml"
				},
				param : ["sellerid"],
				permission : "zh",
			},
			customMethod : function(value, data) {
				return data.status == 3 ?  $(value).html("帐号") : "-";
			},
			width : 150
		},{
			title : "折扣",
			name : "sellerGradeStr",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/sellerAgio/init.jhtml"
				},
				param : ["sellerid"],
				permission : "zk",
			},
			customMethod : function(value, data) {
				return data.status == 3 ? $(value).html("折扣") : "-";
			},
			width : 150
		},{
			title : "平台补贴折扣(%)",
			name : "flatAgioPercentValue",
			type : "string",
			width : 150,
			leng : 8
		},{
			title : "寻蜜客会员编号",
			name : "uid",
			type : "string",
			width : 120,
			leng : 8
		},{
			title : "寻蜜客姓名",
			name : "xmerName",
			type : "string",
			width : 120,
			leng : 8
		},{
			title : "营销信息",
			name : "flatAgio",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/sellerMarketing/init.jhtml"
				},
				param : ["sellerid","sellerGrade","isonline"],
				permission : "yx",
			},
			customMethod : function(value, data) {
				return data.status == 3 ? $(value).html("营销信息") : "-";
			},
			width : 150
		},{
			title : "钱包",
			name : "flatAgio",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "businessman/seller/viewWallet.jhtml",
						position : "200px",
						width : "auto",	
						title : " 钱包" 
					}
						
				},
				param : ["sellerid"],
				permission : "wallet",
			},
			customMethod : function(value, data) {
				return data.status == 3 ? $(value).html("钱包") : "-";
			},
			width : 150
		},{
			title : "地址",
			name : "address",
			type : "string",
			width : 400,
			leng : 8
		},{
			title : "区域",
			name : "title",
			type : "string",
			width : 150,
			leng : 8 
		},{
			title : "商圈",
			name : "btitle",
			type : "string",
			width : 150,
			leng : 8 
		},{
			title : "归属合作商",
			name : "corporate",
			type : "string",
			width : 300,
			leng : 8 
		},{
			title : "归属业务员",
			name : "salesman",
			type : "string",
			width : 150,
			leng : 8
		},{
			title : "审核状态",
			name : "statusText",
			type : "string",
			isLink : true,
			link : {
				required : true,
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "businessman/seller/init/examineInfo.jhtml",
						position : "200px",
						width : "auto",	
						title : " 未通过原因" 
					}
				},
				param : ["sellerid"],
				permission : "zh",
			},
			customMethod : function(value, data) {
				if(undefined==data.statusText){
					return "-";
				}else{
					var result;
					try{
						result = data.status==2 ? data.statusText+"<br>"+$(value).html("[原因]")[0].outerHTML:data.statusText;
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			},
			width : 150
		},{
			title : "上线状态",
			required : true,
			name : "isonlineText",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "businessman/seller/init/examineInfo.jhtml",
						position : "200px",
						width : "auto",	
						title : " 下线原因" 
					}
				},
				param : ["sellerid","isonline"],
				permission : "zh",
			},
			customMethod : function(value, data) {
				if(undefined==data.isonlineText){
					return "-";
				}else if(data.isonline==3){
					var result;
					try{
						result = $(value).length>0?data.isonlineText+"<br>"+$(value).html("[原因]")[0].outerHTML:"-";
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}else{
					return data.isonlineText
				}
			},
			width : 150
		},{
			title : "上下线时间",
			name : "dateOperate",
			type : "string",
			width : 150,
			leng : 8
		},/*{
			title : "物料发送",
			required : true,
			name : "materielStatus",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "businessman/seller/toMaterielPage.jhtml",
						position : "200px",
						width : "auto",	
						title : " 物料发送" 
					}
				},
				param : ["sellerid","uid","email","materielStatus"],
				permission : "zh",
			},
			customMethod : function(value, data) {
				if(data.materielStatus==0){
					return $(value).html("未发送");
				}else if(data.materielStatus==1){
					return $(value).html("已发送");
				}else{
					return "-";
				}
			},
			width : 150
		},*/{
			title : "达人评论",
			name : "flatAgio",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/expert/init.jhtml"
				},
				param : ["sellerid","sellername"],
				permission : "dr",
			},
			customMethod : function(value, data) {
				return  $(value).html("达人评论");
			},
			width : 150
		},{
			title : "是否开启首次",
			name : "isFirstText",
			type : "string",
			width : 150,
			leng : 8
		},{
			title : "分账模式",
			name : "ledgerMode",
			type : "string",
			width : 150,
			leng : 8,
			customMethod : function(value, data) {
				if(data.ledgerMode==0){
					return "正常分账模式";
				}else if(data.ledgerMode==2){
					return "仅商家参与分账模式";
				}else{
					return "-";
				}
			},
		},{
//			title : "是否开通点菜",
//			name : "isOpenBookingText",
//			type : "string",
//			customMethod : function(value, data) {
//				if(data.category == 1){
//					return data.isOpenBookingText!=undefined ? data.isOpenBookingText : "-";
//				}else{
//					return "-";
//				}
//			},
//			width : 150,
//			leng : 8
//		},{
//			title : "分类",
//			name : "foodClassNum",
//			type : "string",
//			isLink : true,
//			link : {
//				linkInfoName : "href",
//				linkInfo : {
//					href : "businessman/seller/food/class/add/init.jhtml"
//				},
//				param : ["sellerid"],
//				permission : "foodClass",
//			},
//			customMethod : function(value, data) {
//				var result;
//				if(data.category == 1 && data.isOpenBooking==1){
//					if(data.foodClassNum != undefined && data.foodClassNum === 0){
////						result='<a href="javascript:void();" data-position="200px" data-type="ajax" data-url="businessman/seller/food/class/add/init.jhtml?sellerid='+data.sellerid+'" data-toggle="modal">添加分类</a>';
//						result='<a href="businessman/seller/food/class/init.jhtml?sellerid='+data.sellerid+"&"+callbackParam+'">分类管理</a>';
//					}else{
//						result = (typeof value == "string"?value.replace(/businessman\/seller\/food\/class\/add\/init.jhtml/,"businessman/seller/food/class/init.jhtml"):"-");
//					}
//				}else{
//					result = "-";
//				}
//				
//				return result;
//			},
//			width : 150,
//			leng : 8
//		},{
			title : "菜品数量",
			name : "foodNum",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/seller/food/add/init.jhtml"
				},
				param : ["sellerid"],
				permission : "food",
			},
			customMethod : function(value, data) {
				var result;
//				if(data.category == 1 &&  data.isOpenBooking==1){
					if(data.foodNum != undefined &&data.foodNum === 0){
//						if(data.foodClassNum!= undefined && data.foodClassNum > 0){//商家菜品分类存在
//							result='<a href="javascript:void();" data-position="200px" data-type="ajax" data-url="businessman/seller/food/add/init.jhtml?sellerid='+data.sellerid+'" data-toggle="modal">添加菜品</a>';
							result='<a href="businessman/seller/food/init.jhtml?sellerid='+data.sellerid+"&"+callbackParam+'">菜品管理</a>';
//						}else{////商家菜品分类不存在
//							result = "<a href='javascript:showWarningWindow("+'"warning","请先添加分类！");'+"'>菜品管理</a>";
//						}
					}else{
						result = typeof value == "string"?value.replace(/businessman\/seller\/food\/add\/init.jhtml/,"businessman/seller/food/init.jhtml"):"-";
					}
//				}else{
//					result = "-";
//				}
				
				return result;
			},
			width : 150,
			leng : 8
		},{
			title : "意见",
			name : "examineinfo",
			type : "string",
			width : 400,
			leng : 8
		},{
			title : "是否公开",
			name : "isPublic",
			type : "string",
			width : 120,
			leng : 8,
			customMethod : function(value, data) {
				if(value == 0){
					return "否";
				}else if(value == 1){
					return "是";
				}else{
					return "-";
				}
			}
		},{
			title : "是否付费",
			name : "isPaid",
			type : "string",
			width : 120,
			leng : 8,
			customMethod : function(value, data) {
				if(value == 0){
					return "否";
				}else if(value == 1){
					return "是";
				}else{
					return "-";
				}
			}
		},{
			title : "是否参与分红",
			name : "joinDividend",
			type : "string",
			width : 120,
			leng : 8,
			customMethod : function(value, data) {
				if(value == 0){
					return "否";
				}else if(value == 1){
					return "是";
				}else{
					return "-";
				}
			}
		}]
	},permissions);
}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

function updateCoinStatus(sellerid,liveCoinPay){
	liveCoinPay = liveCoinPay ==0?1:0;
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/seller/update/coinStatus.jhtml' + '?t='
					+ Math.random(),
			data : {
				'sellerid' : sellerid,
				'liveCoinPay':liveCoinPay
			},
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					setTimeout(function() {
						pageDiv.reload();
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}, "确定修改吗？")
}

/**
 * 批量设置平台补贴折扣
 */
function batchSetFlatAgio(){
	$("#batchSetFlatAgio").on("click",function(){
		if(!pageDiv.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		var ids = pageDiv.getIds();
		console.log(ids);
		var modalTrigger = new ModalTrigger({
			title:"批量设置平台补贴折扣",
			type : 'ajax',
			url : 'businessman/seller/updateFlatAgioBatch/init.jhtml?ids='+ids ,
			height: "auto",
			width: "800px",
			toggle : 'modal',
			position:''
		 });
		 modalTrigger.show();
		
	});
}

/**
 * 批量设置商家详细信息是否允许首次
 */

function batchSellerDetailedIsFirst(){
	$("#batchIsFirst").on("click",function(){
		if(!pageDiv.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		var ids = pageDiv.getIds();
		console.log(ids);
		var modalTrigger = new ModalTrigger({
			title:"批量设置商家详细信息是否允许首次",
			type : 'ajax',
			url : 'businessman/sellerDetailed/updateIsFirstBatch/init.jhtml?sellerIds='+ids ,
			height: "auto",
			width: "800px",
			toggle : 'modal',
			position:''
		 });
		 modalTrigger.show();
		
	});
}

/**
 * 锁定提现
 */
function lock(sellerId){
	showSmConfirmWindow(function(){
		lockOrNot(1,sellerId);
	},"确定要锁定提现功能？");
	
}

/**
 * 解锁提现
 */
function unlock(sellerId){
	showSmConfirmWindow(function(){
		lockOrNot(0,sellerId);
	},"确定要解锁提现功能？");
}

/**
 * 
 * @param islock  0：解锁 ，1：锁定
 */
function lockOrNot(islock,sellerId){
	if(islock!=undefined && sellerId!=undefined){
		var data ={"sellerid":sellerId,"isLock":islock}; 
		var url = "businessman/seller/islock.jhtml";
		$.ajax({
			type : 'post',
			url : url + "?t="
					+ Math.random(),
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
				try {
					if (pageDiv != undefined) {
						pageDiv.reload();
					}
				} catch (err) {
					console.log(err);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
			}
		});
	}
}


//重置提现密码
function resetPassW(sellerid, userType){
	showSmConfirmWindow(function(){
		resetPassWord(sellerid,userType);
	}, "重置后需要在APP中重新设置，是否确认？");		
}

function resetPassWord(sellerid, userType){
	$.ajax({
		type : 'post',
		url : 'businessman/seller/resetDepositPassW.jhtml' + '?t=' + Math.random(),
		data : {
			'sellerid':sellerid,
			'userType':userType
		},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {			 				
          	$('#prompt').hide();
        	showSmReslutWindow(data.success, data.msg);
        	setTimeout(function(){$("#searchForm").get(0).submit();}, 2000);
			
		}
	});
}

/**
 * 查看二维码
 * @param 
 */
function matrixView(sellerid,sellername){
		var data = 'sellerid=' + sellerid + '&sellername='+ encodeURIComponent(sellername) + '&aid=';
		var base = $("base").attr("href");//获取base标签的href属性值 
		showCodeWindow('<img width="512" alt="二维码" src="'+base+'/getBigMatrix.jhtml?'+data+'">');
//把字符串传进后台加密
//	$.ajax({
//		 url : "businessman/seller/init/base64ToEncrypt.jhtml",
//		 type : "post",
//		 dataType : "json",
//		 data:'sellerid=' + sellerid + '&sellername='+ sellername + '&aid=',
//		 success : function(code) {
//			 var base = $("base").attr("href");//获取base标签的href属性值 
//			 showCodeWindow('<img width="200" height="200" alt="二维码" src="'+base+'/getMatrix.jhtml?text='+code+'">');
//		 }
//	});
}
/**
 * 弹出二维码模态框
 */
function showCodeWindow(content) {
	$('#lg_edit_window').find('.modal-title').html('商家二维码');
	$('#lg_edit_window').find('.modal-body').html('<div class="content" align="center">' + content + '</div>');
	$('#lg_edit_window').modal();
//	setTimeout(function(){
//		$('#sm_edit_window').modal('hide');
//	}, 2000);
}
