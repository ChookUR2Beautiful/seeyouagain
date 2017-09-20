var pageDiv;
$(document)
		.ready(
				function() {

					pageDiv = $('#userFormDiv').page({
						url : 'businessman/valueCard/init/list.jhtml',
						success : success,
						pageBtnNum : 10,
						pageSize : 10,
						paramForm : 'userForm'
					});

					inserTitle(
							' > 商家管理 > <a href="/businessman/valueCard/init/list.jhtml" target="right"> 储值卡商户管理</a> >',
							'userFormDiv', true);

					$("input[data-bus=reset]").click(function() {
						$(".form-control").attr("value", "");
						$("#ld").find("select").trigger("chosen:updated");
					});
					
					$("#Kaptcha").click(function(){
			  			reloadCode();
			  			return false;
			  		});
					
					initRechargeValue();
					
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
		cols : [ {
			title : "类型",// 标题
			name : "sellerType",
			// sort : "up",
			width : 120,// 宽度
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
				}
			}
		}, {
			title : "商户名称",// 标题
			name : "sellerName",
			// sort : "up",
			width : 150,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
			
		}, {
			title : "适用商户",// 标题
			name : "childSeller",
			width : 100,// 宽度
			type : "string",// 数据类型
			isLink : true,
			link : {
				required :true,
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url :"businessman/valueCard/childSellerInit.jhtml",
						position : "60px",
						width : "auto",	
						hight : "auto",
						title : "适用商户" 
					}
				},
				param : ["sellerid","sellerType"],
				permission : "childList"
			}
		}, {
			title : "可充值金额",// 标题
			name : "combo",
			// sort : "up",
			width : 250,// 宽度
			leng : 200,// 显示长度
			type : "string"// 数据类型
			
		}, {
			title : "下级充值获得",// 标题
			name : "referrerRatio",
			// sort : "up",
			width : 120,// 宽度
			leng : 200,// 显示长度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				return value+"%";
			}
		}, {
			title : "下下级充值获得",// 标题
			name : "parentReferrerRatio",
			// sort : "up",
			width : 120,// 宽度
			leng : 200,// 显示长度
			type : "string",// 数据类型
			customMethod : function(value, data) {
				return value+"%";
			}
		}, {
			title : "累计限额",// 标题
			name : "limitRecharge",
			width : 120,// 宽度
			type : "string"// 数据类型
		}, {
			title : "累计充值",// 标题
			name : "totalRecharge",
			width : 120,// 宽度
			type : "string"// 数据类型
		}, {
			title : "累计剩余",// 标题
			name : "totalSurplus",
			width : 120,// 宽度
			type : "string"// 数据类型
		}, {
			title : "充值会员",// 标题
			name : "rechargeNum",
			width : 120,// 宽度
			type : "string"// 数据类型
		}],
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["close","recordList"],// 不需要选择checkbox处理的权限
			width : 80,// 宽度
			// 当前列的中元素
			cols : [{
				title : "修改",// 标题
				linkInfoName : "href",
				width : 20,
				linkInfo : {
					href: "businessman/valueCard/update.jhtml",
					param : ["sellerid"],
					permission : "close"
				},customMethod : function(value, data){
                    	return "<a href='javascript:updateValueCardModal(\""+data.sellerid+"\",\""+data.sellerType+"\",\""+data.sellerName+"\",\""+data.referrerRatio
                    	+"\",\""+data.parentReferrerRatio+"\",\""+data.limitRecharge+"\",\""+data.comboId+"\")'>修改</a>";
                }
			},{
				title : "释放储值卡",// 标题
				linkInfoName : "href",
				width : 20,
				linkInfo : {
					href: "businessman/valueCard/close.jhtml",
					param : ["sellerid","sellerType"],
					permission : "close"
				},customMethod : function(value, data){
                    if((data.status==0)){
                    	return "<a href='javascript:freeValueCardModal(\""+data.sellerid+"\",\""+data.sellerName+"\",\""+data.totalRecharge+"\",\""+data.sellerType+"\")'>释放储值卡</a>";
                    }
                }
			},{
				title : "充值记录",// 标题
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/rechargeDetail/init.jhtml",// url
					param : ["sellerid"],// 参数
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
	var p=$("#par");
	p.html('');
	var select=$('<select class="form-control" id="sellerid" name="sellerid" style="width:50%;"></select>').appendTo(p);
	var input=$('<input type="hidden" id="sellerName" name="sellerName">').appendTo(p);
	select.chosenObject({
		hideValue : "sellerid",
		showValue : "sellerName",
		url : "businessman/valueCard/init/sellerList.jhtml?sellerType="+type,
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"50%"
	})
	
	//$('[name^=sellerid]').trigger('chosen:updated');
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
	debugger;
	var sellerType = $("input[name='sellerType']:checked").val();
	var limitRecharge = $("input[name='limitRecharge']:checked").val();
	var sellerid = $("#sellerid").val();
	var referrerRatio =  $("#referrerRatio").val();
	var parentReferrerRatio =  $("#parentReferrerRatio").val();
	
	var combo = $("#rechargeValue input[type='checkbox']");
	var combostr='';
	for(var i = 0;i<combo.length;i++){
		if(combo[i].checked==true){
			combostr += combo[i].value;
			combostr +=',';
		}
	}
	combostr=combostr.substring(0,combostr.length-1);
	var url = contextPath+"/businessman/valueCard/add.jhtml"
	$.ajax({
		type : 'post',
		url : url,
		data :{'sellerType':sellerType,'sellerid':sellerid,'referrerRatio':referrerRatio,'parentReferrerRatio':parentReferrerRatio,'combo':combostr,'limitRecharge':limitRecharge},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				setTimeout(function(){
        			location.href =contextPath+'/businessman/valueCard/init.jhtml';
        		}, 1000);
		    }			
			showSmReslutWindow(data.success, data.msg);
			
			addValueCardModal();
		},
		error : function(data) {
			window.messager.warning(data.msg);
		}
	});
}


function freeValueCardModal(sellerid,sellerName,totalRecharge,sellertype){
	$("#freeValueCardModal").modal();
	$("#totalRecharge").text("￥"+totalRecharge+"");
	$("#name").text(sellerName);
	$("#freeSellerid").val(sellerid);
	$("#freeSellertype").val(sellertype);
}

function freeValueCard(){
	var freeSellerid = $("#freeSellerid").val();
	var ucaptcha = $("input[name='ucaptcha']").val();
	var sellerType = $("#freeSellertype").val();
	if(ucaptcha ==undefined || ucaptcha.length != 4){
		window.messager.warning("验证码错误！");
		reloadCode();
		return;
	}
	
	var url = contextPath+'/businessman/valueCard/close.jhtml';
	
	$.ajax({
		type : 'post',
		url : url,
		data :{'sellerid':freeSellerid,'ucaptcha':ucaptcha,"sellerType":sellerType},
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

//初始化充值金额
function initRechargeValue(){
	var url =contextPath+"/businessman/valueCard/add/init.jhtml"
	$.ajax({
		type : 'post',
		url : url,
		data :{},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				var html ='';
				
				html+='&nbsp;&nbsp;<label style="font-size:14px;">充值金额:</label><ul style="display: inline-block;margin:0;padding:0;font-size:0;width: 370px;vertical-align: top;">';
				if(null !=data.data && data.data.length>0){
					for(var i = 0;i<data.data.length;i++){
						html+='<li style="display:inline-block;font-size:14px;"><input type="checkbox" name="combo" value="'+data.data[i].id+'" style="vertical-align: middle;"><span style="font-size:14px;vertical-align: middle;">'+data.data[i].rechAmount+'</span></li>';
					}
				}
				html+='</ul>';
				$("#rechargeValue").append(html);
				$("#updateRechargeValue").append(html);
		    }		
		},
		error : function(data) {
			window.messager.warning(data.msg);
		}
	});
}

//修改储值卡初始化模态框
function updateValueCardModal(sellerid,sellerType,sellerName,referrerRatio,parentReferrerRatio,limitRecharge,combo){
	$("#updateValueCardModal").modal();
	var str;
	if(sellerType == "1"){
		str = "单店-";
	}else if(sellerType == "2"){
		str = "连锁店-";
	}else if(sellerType == "3"){
		str = "区域代理-";
	}else{
		str = "--";
	}
	$("#updateSellerid").val(sellerid);
	$("#typeText").html(str+sellerName);
	$("#fText").html(""+referrerRatio+"%");
	$("#sText").html(""+parentReferrerRatio+"%");
	var updateLimitRecharge = $("input[name='updateLimitRecharge']");
	//清空缓存
	for(var i = 0;i<updateLimitRecharge.length;i++){
			updateLimitRecharge[i].checked=false;
	}
	//单选回显
	for(var i = 0;i<updateLimitRecharge.length;i++){
		if(updateLimitRecharge[i].value==limitRecharge){
			updateLimitRecharge[i].checked=true;
			continue;
		}
	}
	
	//复选框回显
	if(combo != undefined){
		var str = combo.split(",");
		var comb =$("input[name='combo']");
		if(str.length>0){
			//清空缓存
			for(var j=0;j<comb.length;j++){
				comb[j].checked=false;
			}
			
			for(var i=0;i<str.length;i++){
				for(var j=0;j<comb.length;j++){
					if(str[i]==comb[j].value){
						comb[j].checked=true;
						continue;
					}
				}
			}
		}
	}
}

/**
 * 修改储值卡
 */
function updateValueCard(){
	var limitRecharge = $("input[name='updateLimitRecharge']:checked").val();
	var sellerid = $("#updateSellerid").val();
	var combo = $("#updateRechargeValue input[type='checkbox']");
	var combostr='';
	for(var i = 0;i<combo.length;i++){
		if(combo[i].checked==true){
			combostr += combo[i].value;
			combostr +=',';
		}
	}
	combostr=combostr.substring(0,combostr.length-1);
	var url = contextPath+"/businessman/valueCard/update.jhtml"
	$.ajax({
		type : 'post',
		url : url,
		data :{'sellerid':sellerid,'comboId':combostr,'limitRecharge':limitRecharge},
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

function reloadCode(){
    document.getElementById("Kaptcha").src = "Kaptcha.jpg?time=" + Math.random();
}
