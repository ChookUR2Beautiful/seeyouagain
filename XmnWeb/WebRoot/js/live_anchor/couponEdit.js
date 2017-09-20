var dataCount = 0;//当前添加抵扣券序号
var dataSize =10;//最多可添加抵扣券张数
var haveFreeChange=false;//赠送抵用券值切换标志
var formId = "editForm";
var jsonTextInit;
$(function(){
	
	if($("#cid").val()){
		inserTitle(' > <span>编辑粉丝券','couponEdit',false);
		dataCount = $("#datas").find(".input-group").size()>0?$("#datas").find(".input-group").size():0;
	}else{
		inserTitle(' > <span>添加粉丝券','couponEdit',false);
		dataCount = $("#datas").find(".input-group").size()>0?$("#datas").find(".input-group").size():0;
	}
	
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	//初始化是否赠送抵用券
	initHaveFree();
	
	//初始化是否悬浮显示
	initIsRecom();
	
	//初始化商家下拉框组件
	initSellerid();
	
	//初始化日期控件
	initDates();
	
	//初始化富文本编辑器
    $("textarea#fansDescription").ckeditor({
    	
    });
	
	
	//表单校验
	validate(formId, {
		rules : {
			cname : {
				required : true
			},
			denomination:{
				required : true,
				//测试环境去掉校验
				/*digits:true,
				range:[1,100000000]*/
			},
			defaultMaxNum:{
				required : true,
				digits:true
			},
			validityDesc : {
				required : true,
				maxlength:200
			},
			introduce : {
				required : true
			},
			rule :{
				required :true
			},
			dayNum:{
				required :true
			},
			saleStartTime:{
				required :true
			},
			saleEndTime:{
				required :true
			},
			startDate:{
				required :true
			},
			endDate:{
				required :true
			}
		},
		messages:{
			cname:{
				required:"请输入券名"
			},
			denomination:{
				required : "请输入价格",
				digits:"请输入正整数",
				range:"请输入1至100000000之间的正整数"
			},
			defaultMaxNum:{
				required : "请输入粉丝券数量",
				digits:"请输入正整数"
			},
			validityDesc:{
				required:"请输入粉丝券描述",
				maxlength:"最多输入200个字符"
			},
			introduce :{
				required:"请输入预告介绍"
			},
			rule:{
				required:"请输入使用须知"
			},
			dayNum:{
				required :"请选择使用时间"
			}
		}
	}, save);
	
});

/**
 * 保存信息
 */
function save() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='cid']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "liveCoupon/manage/add" + suffix;
	} else {// 修改操作
		url = "liveCoupon/manage/update" + suffix;
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
					var url = contextPath +'/liveCoupon/manage/init.jhtml';
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
		showWarningWindow('warning', "没做任何修改！");
	}
}



/**
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;
	var haveFree=$("input[name='haveFree']:checked").val();
	//赠送预售抵用券才校验抵用券数据
	if(haveFree=='1'){
		for(var i = 0;i < dataCount+1; i++) {
//			console.info('i='+i+';'+$("input[name='voucherList["+i+"].denomination']").val());
			
			var reg1 = new RegExp("^\[1-9][0-9]*$");
			var reg2=new RegExp("^(0|[1-9][0-9]*)(\.[0-9]{1,2})?$");
			var denomination=$("input[name='voucherList["+i+"].denomination']").val();
			var condition =$("input[name='voucherList["+i+"].condition']").val();
			if(!(undefined ==denomination || reg1.test(denomination))){ 
				submitDataError("input[name='voucherList["+i+"].denomination']","抵用券面值需为正整数");
				result=false;
				return false;
			}
			var compare=parseFloat(condition)>=parseFloat(denomination);
			if(!(undefined ==condition || (reg2.test(condition)&&compare ))){ 
				submitDataError("input[name='voucherList["+i+"].condition']","使用最低金额必须大于等于面值(最多保留两位小数)");
				result=false;
				return false;
			}
		}
	}
	
	var sellerid=$("#sellerid").val();
	if(sellerid ==undefined || sellerid ==""){
		showWarningWindow("warning","请选择关联商户!",9999);
		result=false;
		return result;
	}

	
	return result;
}

/**
 * 绑定单击添加抵用券事件
 */
$("#datas").on("click", ".icon-plus", function() {
	if(dataCount == dataSize-1){
		showWarningWindow("warning","最多可添加"+dataSize+"张抵用券!",9999);
		return;
	}
	if ($(this).parents(".plandiv").find(".input-group").size() < dataSize) {
		$(this).parents(".input-group").after( $(".dataTemp").html().replace(/index/g, dataCount));
		dataCount++;
	}
});

/**
 * 绑定单击删除抵用券事件
 */
$("#datas").on("click", ".icon-minus", function() {
	if ($(this).parents(".plandiv").find(".input-group").size() > 1) {
		$(this).parents(".input-group").remove();
		dataCount--;
	}
});


/**
 * 初始化是否赠送抵用券 
 */

function initHaveFree(){
//	debugger;
	var haveFree = $("input[name='haveFree']:checked").val();
	//haveFree 1 赠送 2 不赠送
	if (haveFree == '1') {
		$("#voucherTr").css("display","table-row");
		$("#dayNumTr").css("display","table-row");
//		
	} else {
		$("#voucherTr").css("display","none");
		$("#dayNumTr").css("display","none");
		$("input[name='haveFree'][value='2']").attr("checked",true);
	}
	
}

/**
 * 返回当前抵用券数量
 */
function currentDataSize(){
	return $("#datas").find(".input-group").size();
}

/**
 * 将.dataTemp中的index替换成具体的数值
 */
function replaceDataTempIndex(index) {
	return $(".dataTemp").html().replace(/index/g, index);
}

//赠送抵用券
$("input[name='haveFree']").change(function() {
//	debugger;
	initHaveFree();
	var haveFree = $("input[name='haveFree']:checked").val();
	if(haveFree == '1'){
		$("#plandiv").html(replaceDataTempIndex(0));
		dataCount = currentDataSize();
	}else{
		$("#plandiv").empty();
		$("input[name='invalidToday']").removeAttr('checked');
	}
	
});


/**
 * 抵用券面值失去焦点，校验方法
 * @param price
 */
function priceBlur(price){
	var reg = new RegExp("^\[1-9][0-9]*$");
	if(!reg.test($(price).val())){
		submitDataError($(price),"抵用券面值需为正整数");
		return;
	}else{
	submitDatasuccess($(price));
	}
}


/**
 * 使用最低金额失去焦点，校验方法
 * @param retail
 */
function retailBlur(retail){
	var reg=new RegExp("^(0|[1-9][0-9]*)(\.[0-9]{1,2})?$");
	if(!reg.test($(retail).val())){
		submitDataError($(retail),"使用最低金额必须大于等于面值(最多保留两位小数)");
		return;
	}else{
	submitDatasuccess($(retail));
	}
}

/**
 * 初始化是否悬浮显示
 */
function initIsRecom(){
	var isRecom=$("input[name='isRecom']:checked").val();
	if(isRecom==0){
		$("input[name='isRecom'][value='0']").attr("checked",true);
	}else{
		$("input[name='isRecom'][value='1']").attr("checked",true);
	}
}


//初始化商家下拉框
function initSellerid(){
	$("#sellerid").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/seller/getSellerIdAndName.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

/**
 * 初始化日期控件
 */
function initDates(){
	//限定销售日期
	limitedDate({
		form:"#editForm",
		startDateName:"saleStartTime",
		endDateName:"saleEndTime",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	
	//限定使用日期
	limitedDate({
		form:"#editForm",
		startDateName:"startDate",
		endDateName:"endDate",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	
	//限定使用开始日期大于等于销售开始日期
	limitedDate({
		form:"#editForm",
		startDateName:"saleStartTime",
		endDateName:"startDate",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	
	//限定使用结束日期大于等于销售结束日期
	limitedDate({
		form:"#editForm",
		startDateName:"saleEndTime",
		endDateName:"endDate",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	
	//限定不可使用日期
	limitedDate({
		form:"#editForm",
		startDateName:"forbidStartTime",
		endDateName:"forbidEndTime",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	
	//限定不可使用开始日期大于等于使用开始日期
	limitedDate({
		form:"#editForm",
		startDateName:"startDate",
		endDateName:"forbidStartTime",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	
	//限定不可使用结束日期小于等于使用结束日期
	limitedDate({
		form:"#editForm",
		startDateName:"forbidEndTime",
		endDateName:"endDate",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
}