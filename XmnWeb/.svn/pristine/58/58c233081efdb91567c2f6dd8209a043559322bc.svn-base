var ISTYPE;
$(document)
		.ready(
				function() {
					ISTYPE = $("#isType").val();
					var idsubsidy = $('#idsubsidy').val();
					if (ISTYPE == "add") {
						inserTitle(
								' > <span><a href="businessman/sellerSubsidy/waiterConfig/add/init.jhtml?idsubsidy='
										+ idsubsidy
										+ '&isType=add" target="right">添加商家服务员推广配置</a>',
								'addSellerSubsidy', false);
						// 选择商家（由于添加和修改共用一个页面，而修改时不需要选择商家）
						selectShop();
					} else{
						inserTitle(
								' > 编辑商家服务员推广配置',
								'addSellerSubsidy', false);
						//改变商家名称输入框样式
						$("#sellername").attr("readonly", true);
						//给配置状态填充值
						var selectInit = $("select").attr("initValue");
						var _html = '';
						if(selectInit == ""){
							_html += '<option value="" selected="selected">请选择</option>';
						}else{
							_html += '<option value="">请选择</option>';
						}
						if(selectInit == 0){
							_html +='<option value="0" selected="selected">开启</option>';
						}else{
							_html +='<option value="0">开启</option>';
						}
						if(selectInit == 1){
							_html += '<option value="1" selected="selected">关闭</option>';
						}else{
							_html += '<option value="1">关闭</option>';
						}
						$('.sellerContentCss select').html(_html);
					}

					/**
					 * 取消
					 */
					$("#cancelId").on("click", function() {
						muBack();
					});
					// 验证
					initValidator();
					// 初始化联动菜单
					uniteArea();
				});

/**
 * 挑选商家
 */
function selectShop() {
	searchChosen = $("#sellername")
			.searchChosen(
					{
						url : "businessman/sellerSubsidy/init/chosen.jhtml?isChose=true&tabFlag=tab1",
						initUrl : "businessman/multipShop/init/findSellerByFatherid.jhtml",
						initId : "sellerid",
						initTitle : "sellername"
					});
}

function uniteArea() {
	// 总店商家
	$("#sellerid").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/sellerSubsidy/BargainSellerInit.jhtml",
		isChosen : true,
		isCode : true
	});
	$("#sellerid").on(
			"change",
			function() {
				var value = $(this).find("option:selected").text().replace(
						/[[1-9]\d*]/, "");
				$("input[name='sellername']").val(value);
			});
}
/**
 * 商家服务员配置修改
 */
var UpdateSavaWaiterConfig = function() {
	var url;
	if (ISTYPE == "add") {
		url = "businessman/sellerSubsidy/waiterConfig/add.jhtml";
	} else {
		url = "businessman/sellerSubsidy/waiterConfig/update.jhtml";
	}
	/*
	 * alert(checkSelect("sellerSubsidyForm","sellerid_chosen",["sellername"],true));
	 * if(!checkSelect("sellerSubsidyForm","sellerid_chosen",["sellername"],true)){
	 * //submitDataError($("#sellerid"),"商家名称未选择!"); showSmReslutWindow(false,
	 * "商家名称未选择！"); return; }
	 */
	var data = $('#addWaiterConfigForm').serializeArray();
	data = jsonFromt(data);
	$.post(url, data, function(result) {
		if (result.success) {
			showSmReslutWindow(result.success, result.msg);
			setTimeout(function() {
				muBack();
			}, 1000);
		} else {
			window.messager.warning(result.msg);
		}
	}, "json");
};


/**
 * 返回
 */
function muBack() {
	var url = contextPath + '/businessman/sellerSubsidy/init.jhtml';
	location.href = url;
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

/**
 * ========================================================================================================
 * 初始化验证方法
 */
function initValidator() {
	/**
	 * 校验订单起始金额不能于结束金额
	 */
	$.validator.addMethod("sMoney", function(value, element) {
		var endMoney = $("#endMoney").val().trim();
		var startMoney = $("#startMoney").val().trim();
		var result = true;
		if(startMoney!=""&&endMoney!=""){
			if (parseFloat(startMoney) > parseFloat(endMoney)) {
				result = false;
				return result;
			}
		}
		return result;
	}, "订单起始金额不能大于结束金额");
	/**
	 * 校验订单结束金额不能小于起始金额
	 */
	$.validator.addMethod("eMoney", function(value, element) {
		var endMoney = $("#endMoney").val().trim();
		var startMoney = $("#startMoney").val().trim();
		var result = true;
		if(startMoney!=""&&endMoney!=""){
			if (parseFloat(endMoney) < parseFloat(startMoney)) {
				result = false;
				return result;
				
			}
		}
		return result;
	}, "订单结束金额不能小于起始金额");

	for (var i = 0; i < formId.length; i++) {
		validate(formId[i], valiinfo[formId[i]], formSubmit(formId[i]));
	}
};
/**
 * 验证方法
 */
var valiinfo = {
	"addWaiterConfigForm" : {
		rules : {
			sellerid : {
				required : true
			},
			status : {
				required : true
			},
			startMoney : {
				required : true,
				min : 0,
				sMoney : true
			},
			endMoney : {
				required : true,
				min : 0,
				eMoney : true
			},
			money : {
				required : true,
			}

		},
		messages : {
			sellerid : {
				required : "商家名称未选择！"
			},
			status : {
				required : "配置状态未选择！"
			},
			startMoney : {
				required : "订单起始金额未填写！",
				min : "订单起始金额不能小于0"
			},
			endMoney : {
				required : "订单结束金额未填写！",
				min : "订单结束金额不能小于0",
			},
			money : {
				required : "服务员奖励未填写!",
			}
		}
	}
};

var formId = [ "addWaiterConfigForm" ];
var formHandle = {
	"addWaiterConfigForm" : UpdateSavaWaiterConfig
};

function formSubmit(form) {
	return formHandle[form];
}
