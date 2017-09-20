var pageDiv;
$(document)
		.ready(
				function() {

					pageDiv = $('#userFormDiv').page({
						url : 'businessman/vipValueCard/init/list.jhtml',
						success : success,
						pageBtnNum : 10,
						pageSize : 10,
						paramForm : 'userForm'
					});

					inserTitle(
							' > 商家管理 > <a href="businessman/vipValueCard/init/list.jhtml" target="right"> 会员储值卡管理</a> >',
							'userFormDiv', true);

					$("input[data-bus=reset]").click(function() {
						$(".form-control").attr("value", "");
						$("#ld").find("select").trigger("chosen:updated");
					});
					
					$("#Kaptcha").click(function(){
			  			reloadCode();
			  			return false;
			  		});
					
				});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {

	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'
			+ data.total + '】条订单&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#searchBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
		tableClass : "table-bordered table-striped info",
//		callbackParam : callbackParam,
		caption : captionInfo,
		// 数据
		data : data.content,
		// 数据行
		cols : [{
			title : "卡序号",// 标题
			name : "id",
			// sort : "up",
			width : 100,// 宽度
			type : "string"// 数据类型
			
		},{
			title : "会员昵称",// 标题
			name : "userName",
			// sort : "up",
			width : 150,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
			
		},{
			title : "会员账号",// 标题
			name : "account",
			width : 150,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
			
		}, {
			title : "卡类型",// 标题
			name : "type",
			width : 150,// 宽度
			leng : 200,// 显示长度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				if(value == 1){
					return "单店";
				}
				if(value == 2){
					return "连锁店";
				}
				if(value == 3){
					return "区域代理";
				}else{
					return "-";
				}
			}
		}, {
			title : "储值卡所属",// 标题
			name : "sellername",
			// sort : "up",
			width : 150,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
			
		}, {
			title : "适用商户",// 标题
			name : "applySeller",
			width : 100,// 宽度
			type : "string",// 数据类型
			isLink : true,
			link : {
				required : true,
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url :"businessman/vipValueCard/applySellerInit.jhtml",
						position : "60px",
						width : "auto",	
						hight : "auto",
						title : "适用商户" 
					}
				},
				param : ["sellerid","type"],
				permission : "childList"
			}							
		}, {
			title : "充值额度",// 标题
			name : "totalLimit",
			width : 150,// 宽度
			type : "string"// 数据类型
		}, {
			title : "当前额度",// 标题
			name : "presentLimit",
			width : 150,// 宽度
			type : "string"// 数据类型
		}, {
			title : "已消费额度",// 标题
			name : "consumerLimit",
			width : 150,// 宽度
			type : "string"// 数据类型
		}],
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["recordList"],// 不需要选择checkbox处理的权限
			width : 100,// 宽度
			// 当前列的中元素
			cols : [{
				title : "充值记录",// 标题
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/rechargeDetail/init.jhtml",// url
					param : ["uid","sellerid"],// 参数
					permission : "recordList"// 列权限
				}
			}] 
}
	}, permissions);
}

/**
 * 转换from表单
 */
function jsonFromt(data) {
	var json = {};
	for (var i = 0; i < data.length; i++) {
		json[data[i].name] = data[i].value;
	}
	return json;
}

function addValueCardModal(){
	$("#addValueCardModal").modal();
	initSeller();
}

$("input[name='sellerType']").on('change',function(){
	var type = $("input[type='radio']:checked").val();
	$("#sellerid").chosenObject({
		hideValue : "sellerid",
		showValue : "sellerName",
		url : "businessman/valueCard/init/sellerList.jhtml?sellerType="+type,
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"50%"
	});
})

function initSeller(){
	var type = $("input[type='radio']:checked").val();
	
	var sellerType = type==undefined?1:type;
	
	$("#sellerid").chosenObject({
		hideValue : "sellerid",
		showValue : "sellerName",
		url : "businessman/valueCard/init/sellerList.jhtml?sellerType="+sellerType,
		isChosen:true,
		isCode:true,
		isHistorical:false,
		width:"50%"
	});
}

/**
 * 开通储值卡
 */
function addValueCard(){
	var sellerType = $("input[type='radio']:checked").val();
	var sellerid = $("#sellerid").val();
	var url = contextPath+"/businessman/valueCard/add.jhtml"
	$.ajax({
		type : 'post',
		url : url,
		data :{'sellerType':sellerType,'sellerid':sellerid},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				setTimeout(function(){
        			location.href =contextPath+'/businessman/valueCard/init.jhtml';
        		}, 1000);
		    }			
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(data) {
			window.messager.warning(data.msg);
		}
	});
}


function freeValueCardModal(sellerid,sellerName,totalRecharge){
	$("#freeValueCardModal").modal();
	$("#totalRecharge").text("￥"+totalRecharge+"");
	$("#name").text(sellerName);
	$("#freeSellerid").val(sellerid);
}

function freeValueCard(){
	var freeSellerid = $("#freeSellerid").val();
	var ucaptcha = $("input[name='ucaptcha']").val();
	if(ucaptcha ==undefined || ucaptcha.length != 4){
		window.messager.warning("验证码错误！");
		reloadCode();
		return;
	}
	
	var url = contextPath+'/businessman/valueCard/close.jhtml';
	
	$.ajax({
		type : 'post',
		url : url,
		data :{'sellerid':freeSellerid,'ucaptcha':ucaptcha},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				setTimeout(function(){
        			location.href =contextPath+'/businessman/valueCard/init.jhtml';
        		}, 1000);
		    }		
			showSmReslutWindow(data.success, data.msg);
			if(!data.success){
				reloadCode();
			}
		},
		error : function(data) {
			window.messager.warning(data.msg);
			reloadCode();
		}
	});
}

function reloadCode(){
    document.getElementById("Kaptcha").src = "Kaptcha.jpg?time=" + Math.random();
}
