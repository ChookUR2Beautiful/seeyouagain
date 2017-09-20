var ISTYPE;
var AGIO=1;//商家折扣
var formId = "editForm";
var jsonTextInit;
$(function(){
	
	ISTYPE = $("#isType").val();
	
	if(ISTYPE == "add"){
		inserTitle(' > <span>添加积分商品信息','addBargainProduct',false);
		$("#sellerInfoDiv").css("display","table-row");
	}else{
		inserTitle(' > <span>编辑积分商品信息','bargainProductEdit',false);
		$("#sellerInfoDiv").css("display","none");
	}
	
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	//初始化商家
	initSellerChosen();
	
	//初始化产品图片
	initProductImage();
	
	//表单校验
	validate(formId, {
		rules : {
			pname : {
				required : true
			},
			originalprice:{
				required : true,
				digits:true,
				range:[1,100000000]
			},
			purchasePrice:{
				required : true,
				digits:true
			}
		},
		messages:{
			pname:{
				required:"请输入商品名称"
			},
			originalprice:{
				required : "请输入原价",
				digits:"请输入正整数",
				range:"请输入正整数"
			},
			purchasePrice:{
				required : "请输入结算价",
				digits:"请输入正整数"
			}
		}
	}, saveProduct);
	
});

/**
 * 保存产品信息
 */
function saveProduct() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='bpid']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "marketingManagement/bargainProduct/add" + suffix;
	} else {// 修改操作
		url = "marketingManagement/bargainProduct/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				if (data.success) {
					var url = contextPath +'/marketingManagement/bargainProduct/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	} else {
		showSmReslutWindow(false, "没做任何修改！");
	}
}

/**
 * 初始化商家
 */
function initSellerChosen(){
	$("#sellerid").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "marketingManagement/bargainProduct/InitSeller.jhtml",
		isChosen:true,
		isCode:true,
		width:"100%"
	});
}

/**
 * 初始化产品图片
 */
function initProductImage(){
	$("#productImage").uploadImg({
		  urlId : "image",
		showImg : $('#image').val()
	});
}


/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	
	var sellerid=$("#sellerid").val();
	if(sellerid == null || sellerid==""){
		showWarningWindow("warning","请选择商家!",9999);
		rsult=false;
		return ;
	}
	
	var image=$("#image").val();
	if(image == null || image==""){
		showWarningWindow("warning","请上传商品图片!",9999);
		rsult=false;
		return ;
	}
	
	return result;
}

/**
 * 计算积分价
 */
function calculateIntegral(){
//	debugger;
	var originalPrice=$("#originalprice").val();//商品原价
	var purchasePrice=$("#purchasePrice").val();//结算价
	var cash=(purchasePrice*1.1).toFixed(2);//积分价现金部分
	var integral=(originalPrice*AGIO-cash).toFixed(2);//积分价积分部分
	
	$("#cash").val(cash);
	$("#integral").val(integral);
	
	if(integral<0){
		$("#purchasePrice").val('');
		showWarningWindow("warning","积分价（积分）不能为负数!",9999);
		return ;
	}
}

/**
 * 修改商家触发
 */
$("#sellerid").change( function() {
	var sellerid=$("#sellerid").val();
	var agio=getAgio(sellerid);
	AGIO=agio;
	console.info("AGIO = "+AGIO);
	calculateIntegral();
	
});

/**
 * 修改商品原价触发
 */
$("#originalprice").change( function() {
	calculateIntegral();
});

/**
 * 修改商品原价触发
 */
$("#purchasePrice").change( function() {
	calculateIntegral();
});

/**
 * 获取商家折扣
 */
function getAgio(sellerid){
	var agio=0;
	$.ajax({
		type : 'post',
		url : "marketingManagement/bargainProduct/init/getSellerBySellerid.jhtml" + "?t=" + Math.random(),
		data : {"sellerid":sellerid},
		async : false,
		success : function(data){
			if(data != null){
				agio = data.baseagio;
			}
		}
	});
	return agio;
}

