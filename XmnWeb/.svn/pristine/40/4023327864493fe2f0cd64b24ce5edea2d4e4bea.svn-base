var ISTYPE;
var dateCount = 0, dateSize = 10;
var checkPriceVal = true;
var checkSellerSelected = true;
var checkOriginalprice = true;
var checkOriginalpriceOnPur = true;
var baseagio;
$(document).ready(function() {
	
	//add by lifeng 20160715 18:20:02
	 //验证现价必须大于采购价格的1.05倍
	 $.validator.addMethod("checkPriceVal",function(value,element,params){
			return checkPriceVal;
		 },"售价必须大于采购价的1.05倍！");
	 
	 //验证必须选择了商家后再输入采购价
	 $.validator.addMethod("checkSellerSelected",function(value,element,params){
			return checkSellerSelected;
		 },"必须选择商家才能计算积分个数！");
	 
	//验证原价乘以折扣不能小于积分现金价
	 $.validator.addMethod("checkOriginalprice",function(value,element,params){
			return (checkOriginalprice==true && checkOriginalpriceOnPur == true);
		 },"原价乘以折扣不能小于积分现金价！");
	 //
	 
	ISTYPE = $("#isType").val();
	 $('.form-datetime').datetimepicker({
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView:2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd'
		}).on('changeDate', function(ev){
			$(this).blur();
		});
	 
	$('.form-time').datetimepicker({
			autoclose : 1,
			startView:1,
			maxView:1,
			minuteStep:1,
			format : 'hh:ii'	
		});
	var sellerid = $('#selleridid').val();
	if(ISTYPE == "add"){
		inserTitle(' > <span>添加积分商品信息','addBargainProduct',false);
	}else{
		dateCount = $("#dates").find(".input-group").size()>0?$("#dates").find(".input-group").size()-1:0;
		inserTitle(' > <span>编辑积分商品信息','editBargainProduct',false);
	}
	
	/**
	 * 返回
	 */
	 $("#addBto").on("click",function(){
		 muBack();
	 });
	
	//添加校验
	initValidator();
	//加载商家下拉
	uniteArea();
	//加载商家的折扣
	getSellerInfo();
	
	$("#productImage").uploadImg({
		  urlId : "image",
		showImg : $('#image').val()
	});
	$("input[name='quota']").click(function(){
		InitQuotanum(this.value);
	});
	var quota= $("#quotaValue").val();
	InitQuotanum(quota);
	
    // 网页编辑器
    $( 'textarea#ckeditor_standard' ).ckeditor({
//    	width:'98%', height: '150px', 
//    	toolbar: [
//			{ name: 'document', items: [ 'Source', '-', 'NewPage', 'Preview', '-', 'Templates' ] },	// Defines toolbar group with name (used to create voice label) and items in 3 subgroups.
//			[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ],			// Defines toolbar group without name.
//			{ name: 'basicstyles', items: [ 'Bold', 'Italic' ] }
//			,['Image'] 
//		]
    });
    
//    KindEditor.ready(function(K) {
//        window.editor = K.create('#editor_id',{
//        	themeType : 'default',
//        	uploadJson : 'kindeditUpload.jhtml',
//        	afterUpload:function(url) {
//                alert(url);
//            }
//        });
//    });
    
});
initDate();
$("#dates").on("click",".icon-plus",function() {
	if ($(this).parents(".col-md-8").find(".input-group").size() < dateSize) {
		dateCount++;
		$(this).parents(".input-group").after($(".dateTemp").html().replace(/index/g,dateCount));
		initDate();
	}
});
$("#dates").on(
		"click",
		".icon-minus",
		function() {
			if ($(this).parents(".col-md-8").find(".input-group")
					.size() > 1) {
				$(this).parents(".input-group").remove();
			} else {
				
			}
		});


function InitQuotanum(value){
	var input = $("input[name='quotanum']");
	var quotanumInit = $("input[name='quotanumInit']").val();
	var divNum= $("#allNum");
	if(value == 0){
		//input.hide();
		divNum.hide();
	}else if(value == 1){
		input.show().val(quotanumInit);
		divNum.show();
	}
}


function initDate() {
	var time = {
			format : "hh:ii",
			autoclose : true,
			todayBtn : false,//隐藏“今日”按钮
			minView : "hour",
			maxView : "hour",
			startView : "day",
			minuteStep : 1,
			pickerPosition : "bottom-left"
	};
	
	$(".time-start").each(function(index,element){
		$(element).siblings(".time-end").datetimepicker($.extend(time,{
		})).on("changeDate",function(){
			$(element).datetimepicker("setEndDate",$(element).siblings(".time-end").val());
		});
		$(element).siblings(".time-end").datetimepicker("setStartDate",$(element).val());
		$(element).siblings(".time-end").datetimepicker('update');
		
		$(element).datetimepicker($.extend(time,{
		})).on("changeDate",function(){
			$(element).siblings(".time-end").datetimepicker("setStartDate",$(element).val());
		});
		$(element).datetimepicker('update');
	});
}




function uniteArea(){
	//总店商家
	$("#sellerid").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "marketingManagement/bargainProduct/InitSeller.jhtml",
		isChosen:true,
		isCode:true,
		width:"100%"
	});
	$('body').on("change","#sellerid",function(){
//	$("#sellerid").on("change",function(){
		var value =  $(this).find("option:selected").text().replace(/[[1-9]\d*]/,"");
		$("input[name='sellername']").val(value);
		//获取选中的sellerid
		//var strAndVal = $(this).find("option:selected").text();
		//selleridR = strAndVal.subString(strAndVal.indexOf("["),strAndVal.indexOf("]"));
		getSellerInfo();
	});
}

/*
 * (add by lifeng 2016年7月22日11:28:10)
 * 获取经销商列表 
 */
//重写方法
/**
 * 单个下拉狂
 */
var chosenData = {};
$.fn.chosenObject = function selectInfo(param){
	var $this = $(this);
	var isInit = true;
	var defaults = {
		id : "zoneid",//当前页面select框的id
		hideValue : "bid",//实际传到后台进行筛选的值
		showValue : "title",//下拉框显示的值
		url : "",//"common/business/businessInfo.jhtml",//请求数据的url
		limit : 50,//分页参数
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
	    defaultValue:"--请选择--"//默认的显示值
	};
	var opts = $.extend(defaults, param);
	//传到后台的参数	
	var params = {"limit":opts.limit}
	//获取数据
	var getData = function(search){
		var key = opts.url;
		/*if(isInit){
			isInit = false;
			params[opts.hideValue] = $this.attr("initValue");
		}else{
			params[opts.hideValue] = undefined;
		}*/
		if(search){
			params[opts.showValue] = search;
		}
		key += JSON.stringify(params);
		if(chosenData[key]){
			return chosenData[key];
		}else{
			$.ajax({
				type: "POST",
				url : opts.url,
				async : false,
				dataType : "json",
				data: params,
				success : function(data){
					chosenData[key] = data.content;
				}
			});
			return chosenData[key];
		}
	}
	
	//拼装数据
	var build = function(search){
		var data = getData(search);
		if(data){
			$this.empty().append('<option value="">'+opts.defaultValue+'</option>');
			for(var i=0;i<data.length;i++){
				var hide = data[i][opts.hideValue];
				var show = data[i][opts.showValue];
				//如果含有编号则拼装编号
				if(opts.isCode){
					$this.append('<option value="'+ hide +'" '+(hide ==$this.attr("initValue")?'selected':'')+'>'+"["+hide+"]"+ show +'</option>');
				}else{
					$this.append('<option value="'+ hide +'" '+(hide ==$this.attr("initValue")?'selected':'')+'>'+ show +'</option>');
				}
				
			}
		}
	}
	build();
	
	
	//判断是否可以模糊搜索
	if(opts.isChosen){
		$this.chosen({
				search_contains : true,
				allow_single_deselect : false,
				remote_function : function(search){
					build(search);
				},
				width: opts.width?opts.width:"90%"
		});
	}
	$.extend($this, {
		initData : build
	});
	return $this;
}

/**
 * 商家修改
 */
var UpdateSavaSeller =function (){
		var url;
		if(ISTYPE == "add"){
			url = "marketingManagement/bargainProduct/add.jhtml";
		}else{
			url = "marketingManagement/bargainProduct/update.jhtml";
		}
		var data = jsonFromt($('#multipShopForm').serializeArray());
		if(!checkids()){//商家名称校验
			return false;
		}
		var valiImg = valiImgData('#multipShopForm',jsonFromt($('#multipShopForm').serializeArray()));
		if(valiImg){
			return false;
		}
/*		if(!(checkData(dateCompare(data.startdate, new Date()) >= 0, "input[name='startdate']", "销售开始日期应大于当前日期") &&
				checkData(dateCompare(data.enddate, data.startdate) >= 0, "input[name='enddate']", "销售结束日期应晚于销售开始日期"))){
			return false;
		} 
		for(var i = 0;i < dateCount+1; i++) {
			if($("input[name='bargainPrice["+i+"].startTime']").val() > $("input[name='bargainPrice["+i+"].endTime']").val()){ 
				submitDataError("input[name='bargainPrice["+i+"].startTime']","销售开始时间应小于销售结束时间");
				submitDataError("input[name='bargainPrice["+i+"].endTime']","销售结束时间应大于销售开始时间");
				return false;
			}else{
				submitDatasuccess("input[name='bargainPrice["+i+"].startTime']");
				submitDatasuccess("input[name='bargainPrice["+i+"].endTime']");
			}
			
			if($("input[name='bargainPrice["+i+"].startTime']").val() =='' && $("input[name='bargainPrice["+i+"].endTime']").val() ==''){ 
				submitDataError("input[name='bargainPrice["+i+"].startTime']","销售开始时间不为空");
				submitDataError("input[name='bargainPrice["+i+"].endTime']","销售结束时间不为空");
				return false;
			}else{
				submitDatasuccess("input[name='bargainPrice["+i+"].startTime']");
				submitDatasuccess("input[name='bargainPrice["+i+"].endTime']");
			}	
		}
		*/
		$.post(url,data,function(data){
			showSmReslutWindow(data.success, data.msg);
			// 添加成功后跳转到列表页面
			var url = contextPath +'/marketingManagement/bargainProduct/init.jhtml';
			setTimeout(function() {
				location.href = url;
			}, 1000);
		},"json");
}
	function checkids(){//商家名称校验
	  if($("#sellerid").val().length!=0){
		  var flag = validateSellerCount();
		  if(flag){
			  submitDataError($("#checkids"),"该商家已被添加过两次，请重新选择商家!");
			  return false;
		  }else{
			  submitDatasuccess($("#checkids"));
			  return true;
	  	  }
	  }  
	  submitDataError($("#checkids"),"商家名称不能为空!");
	  return false;
	};
	
	/**
	 * 返回
	 */
	function muBack(){
		var url = contextPath + '/businessman/multipShop/init.jhtml';
			location.href =url;
	}
	
	//验证用户所选择的商家是否已经被添加过两次，是则不能再次选择改商家，否则可以
	function validateSellerCount(){
		if(ISTYPE != "add"){  //修改的时候也要限制用户通过模糊查询出来的商家是否已经添加过两次
			/**
			 * 通过原始(添加的时候选择)的商家ID与用户重新选择（注意用户通过模糊查询进行选择）的商家ID作比较，
			 * 假如不相等，则说明此时用户选择的商家ID有可能是已经添加过两次的，就需要进行判断，限制；
			 * 假如相等，说明用户在修改爆品信息的时候，对于商家ID这一项没有更改过
			 */
			if($("#originalSellerId").val() != $("#sellerid").val()){
				return doValidate();
			}else{
				return false;
			}
		}else{
			return doValidate();
		}
	}

	function doValidate(){
		var data = jsonFromt($('#multipShopForm').serializeArray());
		var flag = false;
		$.ajax({
			type : 'post',
			url : "marketingManagement/bargainProduct/init/getSellerCount.jhtml" + "?t=" + Math.random(),
			data : data,
			async : false,
			success : function(data){
				if(Number(data.total) >= 100){
					  flag = true;
				  }
			}
		});
		return flag;
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



/**========================================================================================================
 * 初始化验证方法
 */
function initValidator(){
	 
	 /**
	  * 校验价钱
	 */
	 $.validator.addMethod("price", function(value, element) {
		 if(value){
			 var reg = /^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$/; 
			 if(reg.test(value)){
				 return true;
			 }
		 }
	}, "请输入正确价钱！");
	 
	for(var i=0;i<formId.length;i++){
		validate(formId[i],valiinfo[formId[i]],formSubmit(formId[i]));
	}
};	

/**
 * 验证方法
 */
var valiinfo={"multipShopForm":{rules:
      {
//		activityname:{
//			required:true, 
//			rangelength:[1,20]
//		},
		pname:{
			 required:true, 
			 rangelength:[1,20]
		}, 		
//		startdate:{
//			 required:true, 
//		},
//		enddate:{
//			required:true, 
//		},
		originalprice:{
			required:true,
			number:true,
			maxlength:10,
			price:true,
			checkSellerSelected:true,
			checkOriginalprice:true 
		},
		purchasePrice:{
			required:true,
			number:true,
			maxlength:10,
			price:true,
			checkSellerSelected:true
		},
		price:{
			required:true,
			number:true,
			maxlength:10,
			price:true,
			checkPriceVal:true
		},quotanum:{
			required:true,
			digits:true,
			min:0,
			maxlength:10,
		},
//		isrebate:{
//			required:true, 
//		},
//		refund:{
//			 required:true, 
//		},
		cash:{
			 required:true
		},
		status:{
			required:true
		},
		purchasePrice:{
			required:true
		}
	},
	messages:{
//		activityname:{
//			 required:"特价活动标题未填写", 
//			 rangelength:"用户名长度为  1-20  个字符"
//		},
		pname:{
			 required:"特价商品名称未填写", 
			 rangelength:"特价商品名称长度为  1-20  个字符"
		},		
//		startdate:{
//			required:"销售开始日期未填写", 
//		},
//		enddate:{
//			required:"销售结束日期未填写", 
//		},
		originalprice:{
			required:"原价未填写",
			number:"请输入数字",
			maxlength:"原价超出最大价钱",
			price:"请输入正确的金额",
			checkSellerSelected:"必须选择商家才能计算积分个数 ",
			checkOriginalprice:"原价乘以折扣不能小于积分现金价"
		},
		purchasePrice:{
			required:"采购价未填写",
			number:"请输入数字",
			maxlength:"采购价超出最大价钱",
			price:"请输入正确的金额",
			checkSellerSelected:"必须选择商家才能计算积分个数"
		},
		price:{
			required:"现价未填写",
			number:"请输入数字",
			maxlength:"现价超出最大价钱",
			price:"请输入正确的金额",
			checkPriceVal:"现价必须大于采购价的1.05倍"
		},
		quotanum:{
			required:"限制数量未填写",
			digits:"请输入正整数",
			min:"最低不能小于0",
			maxlength:"现价超出最大数字",
		},
//		isrebate:{
//			required:"是否支持返利未填写", 
//		},
//		refund:{
//			required:"是否可以退款未填写", 
//		},
		cash:{
			 required:"积分价未填写"
		},
		status:{
			required:"状态未填写"
		},
		purchasePrice:{
			required:"产品采购价必填"
		}
	}
}};

var formId=["multipShopForm"];
var formHandle={
		"multipShopForm":UpdateSavaSeller
}

function formSubmit(form){
	return formHandle[form];
}

function htmlspecialchars(str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}

/**
 * add by lifeng 20160715 18:20:02
 * 当键盘输入原价的时候，触发的事件
 */
$("#originalprice").on("keyup",function(){
	getSellerInfo();
	var doubleBaseagio = parseFloat(baseagio);
	console.log(doubleBaseagio);
	var originalprice = parseFloat($("#originalprice").val())*doubleBaseagio;
	originalprice = !isNaN(originalprice)?originalprice:0;
	var cash = !isNaN(parseFloat($("#cash").val())) ? parseFloat($("#cash").val()) : 0;
	//var integral = $("#integral").val();
	if(originalprice < cash){
		checkOriginalprice = false;
		$("#integral").val(0);
		submitDataError($("#originalprice"),"原价乘以折扣不能小于积分现金价!");
	}else{
		$("#integral").val((originalprice - cash).toFixed(2));
		checkOriginalprice = true;
	}
	
	var integral = !isNaN(parseFloat($("#integral").val()))?parseFloat($("#integral").val()):0;
	$("#price").val((integral + cash).toFixed(2));
});

/**
 * add by lifeng 20160715 18:20:02
 * 当输入产品采购价的时候，自动生成积分价
 */
$("#purchasePrice").on("keyup",function(){
	var cash = $("#cash").val();
	var price = $("#price").val();
	var doubleBaseagio = parseFloat(baseagio);
	console.log(doubleBaseagio);
	var originalprice = parseFloat($("#originalprice").val())*doubleBaseagio;
	originalprice = !isNaN(originalprice)?originalprice:0;
	var cash = !isNaN(parseFloat($("#cash").val())) ? parseFloat($("#cash").val()) : 0;
	//if(cash != "undefined"){
	var pruchasePrice = parseFloat($("#purchasePrice").val());
	$("#cash").val((!isNaN(pruchasePrice)　?　pruchasePrice*1.1 :　0).toFixed(2));
	//}
	if(originalprice < cash){
		checkOriginalpriceOnPur = false;
		$("#integral").val(0);
		submitDataError($("#originalprice"),"原价乘以折扣不能小于积分现金价!");
	}else{
		$("#integral").val((originalprice - cash).toFixed(2));
		checkOriginalpriceOnPur = true;
	}
	cash = parseFloat($("#cash").val());
	cash = !isNaN(cash)?cash:0;
	var integral = !isNaN(parseFloat($("#integral").val()))?parseFloat($("#integral").val()):0;
	$("#price").val((integral + cash).toFixed(2));
});


/**
 * add by lifeng 20160715 18:20:02
 * 根据商家sellerid获取商家的折扣
 */
function getSellerInfo(){
	setTimeout(function(){
		var selid = $("#sellerid_chosen .chosen-single span").html();
		selid = selid.substring(selid.indexOf("[")+1,selid.indexOf("]"));
		console.log(selid);
		//var sellerid = $("#sellerid_chosen").val();
		if(selid != null && selid != '' && selid != 'undefined'){
			var data = {"sellerid":selid};
			$.ajax({
				type : 'post',
				url : "marketingManagement/bargainProduct/init/getSellerBySellerid.jhtml" + "?t=" + Math.random(),
				data : data,
				async : false,
				success : function(data){
					if(data != null){
						baseagio = data.baseagio;
					}
				}
			});
			checkSellerSelected = true;
		
		}else{
			checkSellerSelected = false;
		}
	},50);
}


