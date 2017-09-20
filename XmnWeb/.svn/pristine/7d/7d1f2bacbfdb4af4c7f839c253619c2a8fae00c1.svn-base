var ISTYPE;
ISTYPE = $("#isType").val();
var dateCount = 0 , dateSize = 10,citypostageCount = 0 ,citypostageTemp = 10;
$(document).ready(function() {
	inserTitle(' > 积分商城 > <a href="fresh/postagetemplate/init.jhtml" target="right">运费模板管理</a> > 新增模板','allbillSpan',true);
	if(ISTYPE=="update"){
		$("#citypostageDIV").css("display","inline-block");
		$("#freeCondition").attr("checked",true);
		$("#freeConditionDIV").css('display','inline-block');
		//初始化修改数据
		initUpdateData();
	}else{
		$("#citypostageDIV").css('display','none');
		$("#freeCondition").attr("checked",false);
	}
	//验证提交添加或修改的数据
    initValidator();
});
/**
 * 显示和隐藏包邮条件div
 */
$("#freeCondition").on("change",function(){
	if($("#freeCondition").is(':checked')==true){
		$("#freeConditionDIV").css('display','inline-block');
	}else{
		$("#freeConditionDIV").css('display','none');
		if($(".postageFreeRuleTable").find(".postageFreeRuleGroup").size() > 1){
			$(".postageFreeRuleGroup").each(function(){
				if( $(this).attr('data') > 0){
					$(this).remove();
				}
			});
		}
	}
});
/**
 * 指定条件地区包邮加减操作
 */
$("#freeConditionDIV").on("click",".icon-plus",function() {
	if ($(this).parents(".postageFreeRuleTable").find(".postageFreeRuleGroup").size() < dateSize) {
		dateCount++;
		$(this).parents(".postageFreeRuleGroup").after(
				$("#postageFreeRuleTemp").find(".freeRuleTempTable > tbody").html().replace(/index/g,dateCount).replace(/datacount/g,dateCount)
		);
	}
});
$("#freeConditionDIV").on("click",".icon-minus",function() {
	if ($(this).parents(".postageFreeRuleTable").find(".postageFreeRuleGroup").size() > 1) {
		$(this).parents(".postageFreeRuleGroup").remove();
	}
});
/**
 *  显示和隐藏指定地区城市设置运费(指定地区城市设置运费加减操作)
 */
$(".citypostage").on("click",function(){
	var cityCount = $(this).parentsUntil(".layoutTable").parent().find(".cityPostageGroup").size();
	if(cityCount == 1 && citypostageCount == 0){
		$("#citypostageDIV").css("display","inline-block");
		//$("input[name='postageRuleList[0].baseWeight']").val($("input[name='baseWeight']").val());
		//$("input[name='postageRuleList[0].extraWeight']").val($("input[name='extraWeight']").val());
		citypostageCount = 1;
	}
	if((cityCount - 1) < citypostageTemp){
		var citypostageTempTitle = $("#citypostageTempTitle").find(".citypostageTempTableTitle > tbody").html();
		var cityTempStr = $("#citypostageTemp").find(".citypostageTempTable > tbody").html();
		var cityStr = cityTempStr.replace(/subscript/g,cityCount);
		if(cityCount == 1){
			$(this).parentsUntil(".layoutTable").find("#ptTitle").after(citypostageTempTitle);
		}
		
		$(this).parentsUntil(".layoutTable").find(".cityPostageGroup:last").after(
				cityStr
		);
		$("input[name='postageRuleList["+cityCount+"].baseWeight']").val($("input[name='baseWeight']").val());
		$("input[name='postageRuleList["+cityCount+"].extraWeight']").val($("input[name='extraWeight']").val());
	}else{
		//$(".citypostage").parent().after('<font color="red">指定地区不能超过10个！</font>');
	}
});
$('body').on('click',".deletecitypostage",function(){
	var len = $(this).parentsUntil(".postageRuleTable").parent().find(".cityPostageGroup").size();
	if(len > 1){
		if(len == 2){
//			$(this).parentsUntil(".postageRuleTable").parent().find('.cityPostageGroup').remove();
			$(this).parent().parent().parent().parent().find('.tempptTitle').remove();
		}
		$(this).parentsUntil(".cityPostageGroup").parent().remove();
	}else if(len == 1){
		$("#citypostageDIV").css("display","none");
		citypostageCount = 0;
	}
});


/**
* 触发取消
 */
  $("#cancelId").on("click", function() {
              muBack();
   });
/**
 * 取消方法
 */
function muBack() {
    var url = contextPath + '/fresh/postagetemplate/init.jhtml';
    location.href = url;
}
/**
 * 修改数据初始化（如果是默认）
 */
function initUpdateData(){
	
}
/**
 * 验证并且提交添加或修改的数据
 */
var formId = [ "editPostageTemplaceForm" ];
function initValidator() {
    for (var i = 0; i < formId.length; i++) {
        validate(formId[i], valiinfo[formId[i]], formSubmit(formId[i]));
    }
}
/**
 * 验证方法
 */
var valiinfo = {
    "editPostageTemplaceForm" : {
        rules : {
        	title : {
                required : true
            }
        },
        messages : {
        	title : {
                required : "模板名称不能为空！"
            }
        }

    }
};
/**
 * 保存修改
 */
var UpdateSavaPostageTemplace = function() {
//	for(var i = 0;i < dateCount+1; i++) {
//		if($("input[name='postageRuleList["+i+"].area']").val()==''){ 
//			submitDataError("input[name='postageRuleList["+i+"].area']","指定地区不能为空");
//			return false;
//		}
//		if($("input[name='postageRuleList["+i+"].baseWeight']").val()==''){ 
//			submitDataError("input[name='postageRuleList["+i+"].baseWeight']","首重不能为空");
//			return false;
//		}
//		if($("input[name='postageRuleList["+i+"].baseFee']").val()==''){ 
//			submitDataError("input[name='postageRuleList["+i+"].baseFee']","运费不能为空");
//			return false;
//		}
//		if($("input[name='postageRuleList["+i+"].extraWeight']").val()==''){ 
//			submitDataError("input[name='postageRuleList["+i+"].extraWeight']","续重不能为空");
//			return false;
//		}
//		if($("input[name='postageRuleList["+i+"].extraFee']").val()==''){ 
//			submitDataError("input[name='postageRuleList["+i+"].extraFee']","续运费不能为空");
//			return false;
//		}
//	}
//	for(var i = 0;i < citypostageCount+1; i++) {
//		if($("input[name='postageFreeRuleList["+i+"].area']").val()==''){ 
//			submitDataError("input[name='postageFreeRuleList["+i+"].area']","包邮地区不能为空");
//			return false;
//		}
//		if($("input[name='postageFreeRuleList["+i+"].amount']").val()==''){ 
//			submitDataError("input[name='postageFreeRuleList["+i+"].amount']","包邮条件不能为空");
//			return false;
//		}
//		if($("input[name='postageFreeRuleList["+i+"].weight']").val()==''){ 
//			submitDataError("input[name='postageFreeRuleList["+i+"].weight']","包邮条件不能为空");
//			return false;
//		}
//	}
	
    var url;
    if (ISTYPE == "update") {
        url = "fresh/postagetemplate/update.jhtml";
    } else {
        url = "fresh/postagetemplate/add.jhtml";
    }
    var data = $('#editPostageTemplaceForm').serializeArray();
    //form转成json
    data = jsonFromt(data);
    //post提交请求
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
}
/**
 * 转换from表单为json数据格式
 */
function jsonFromt(data) {
    var json = {};
    for (var i = 0; i < data.length; i++) {
        json[data[i].name] = data[i].value;
    }
    return json;
}

/**
 * 提交表单方法
 */
var formHandle = {
        "editPostageTemplaceForm" : UpdateSavaPostageTemplace
};
function formSubmit(form) {
    return formHandle[form];
}

/**
 * 初始化区域
 */
function initArea(){
	$.post('fresh/postagetemplate/initArea.jhtml', '', function(result) {
		var a = JSON.parse(result);
		console.log(a);
    }, "json");
}