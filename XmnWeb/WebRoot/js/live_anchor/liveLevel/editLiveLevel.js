var ISTYPE;

$(document).ready(
		function() {
			ISTYPE = $("#isType").val();
			var id = $('#id').val();
			if (ISTYPE == "add") {
				inserTitle(' > <span><a href="liveLevel/manage/add/init.jhtml?liveType='+$("#liveType").val()+'&isType=add" target="right">添加主播等级</a>','addliveLevel',false);
			} else {
				inserTitle(' > 编辑主播等级', 'editliveLevel', false);
			}

			/**
			 * 返回
			 */
			$("#backId").on("click", function() {
				muBack();
			});
			
			//初始化数据
			initData();
		});

var jsonTextInit;
$(function() {
	var dataformInit = $("#liveLevelForm").serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	//添加校验
	initValidator();
});


//***********************************页面控制******************************************
/*$("input[name='liveType']").trigger("change");
$("input[name='liveType']").on("change", function(){
	var ledgerType = $("input[name='liveType']:checked").val();
})
	var ledgerType = $("#liveType").val();
	switch (parseInt(ledgerType)) {
	case 1:
        $("#signLiveLevelForm").show();
        ($("#tmpLiveLevelForm"))[0].reset();
        $("#tmpLiveLevelForm").hide();
		break;
	default:
	    $("#tmpLiveLevelForm").show();
	    ($("#signLiveLevelForm"))[0].reset();
	    $("#signLiveLevelForm").hide();
		break;
	}
*/

function initData() {
	$("#levelIncome").bind("input propertychange", function() {
		calculateTopIncome();
	});
	
	$("#floatPerformance").bind("input propertychange", function() {
		calculateTopIncome();
	});
	
	//$("#levelIncome").on('change', calculateTopIncome());
	
	if (ISTYPE != "add") {
	    $("#liveHours").attr("disabled", "disabled");
	}else{
	    $("#liveHours").removeAttr("disabled");    //去除readonly属性, disabled不可选中
	}
}



/**========================================================================================================
 * 初始化验证方法
 */
function initValidator(){
	 /*
	 	验证级别名称唯一性，异步检验
	 */
	 $.validator.addMethod("checkLevelName", function(value, element) {
		// var $("#tableinfo").find();
		   var result = false;
	        // 设置同步
	        $.ajaxSetup({
	            async: false
	        });
	        var param = {
	        	levelName: value
	        };
	        $.post("liveLevel/manage/checkLevelName.jhtml", param, function(data){
	        	result = data;
	        	if($('#oldLevelName').val() == value){
	        		result = true;
	        	}
	        });
	        // 恢复异步
	        $.ajaxSetup({
	            async: true
	        });
	        return result;    
		}, "级别名称已存在");
	 
	 
	 //校验帐号规则
	 $.validator.addMethod("levelNameRule", function(value, element) {
		 if(value){
			 var reg=/^[\w\.\@]{0,20}$/;
			 if(reg.test(value)){
				 return true;
			 }
		 }
		}, "帐号只能为字母数字下划线或者@");
	 
	 
	 //带2位小数字点
	 $.validator.addMethod("checkDouble",function(value, element){
			 var len = value.length;
			 if(len > 12){
				 return false;
			 }
			 if(value >= 1000000000 || value < 0){
				 return false;
			 }
			 var indexOf = value.indexOf(".");
			 if(indexOf>0){
				 var numStr = value.substr(indexOf+1);
				 return !(numStr.length > 2);
			 }
			 return true;
			 
		 },"请填写数值, 最大值为999999999.99");
	 
}	 

function calculateTopIncome() {
	var levelIncome = parseFloat($("#levelIncome").val()) ;
	var floatPerformance = parseFloat($("#floatPerformance").val());
	
	levelIncome = !isNaN(levelIncome) ? levelIncome: 0;
	floatPerformance = !isNaN(floatPerformance) ? floatPerformance: 0;
	
	$("#topIncome").val(levelIncome + floatPerformance);
} 

/**
 * 返回
 */
function muBack(){
	var url = contextPath + '/liveLevel/manage/init.jhtml';
		location.href =url;
}

//****************************************保存数据方法********************************
validate("liveLevelForm",{
	rules : {
		levelName : {
			required : true,
			checkLevelName:true,
			rangelength:[1, 20]
		},
		levelIncome : {
			required : true,
			number:true,
			checkDouble:[10,2]
		},
		floatPerformance : {
			required : true,
			number:true,
			checkDouble:[10,2]
		},
		percentComplete : {
			required : true,
			digits:true,
		},
		percentComplete80 : {
			required : true,
			digits:true
		},
		percentComplete60 : {
			required : true,
			digits:true,
		},
		percentComplete40 : {
			required : true,
			digits:true
		},
		giftAllot : {
			required : true,
			number:true,
			checkDouble:[10,2]
		},
		liveHours : {
			required : true,
			digits:true
		}
	},
	messages:{
		levelName:{
			required:"请输入级别名称",
			checkLevelName:"级别名称已存在!" ,
			rangelength:"用户名长度为  1-20  个字符"
		},
		levelIncome:{
			required:"请输入级别薪酬",
			number:"级别薪酬必须为数字类型",
			checkDouble:"必须为2位小数点的数据"
		},
		floatPerformance:{
			required:"请输入浮动绩效",
			number:"浮动绩效必须为数字类型",
			checkDouble:"必须为2位小数点的数据"
		},
		percentComplete:{
			required:"请输入完成率100%场次",
			digits:"请输入整数",
		},
		percentComplete80:{
			required:"请输入完成率80%场次",
			digits:"请输入整数",
		},
		percentComplete60:{
			required:"请输入完成率60%场次",
			digits:"请输入整数",
		},
		percentComplete40:{
			required:"请输入完成率40%场次",
			digits:"请输入整数",
		},		
		giftAllot:{
			required:"请设置送礼分成",
			number:"送礼分成必须为数字类型",
			checkDouble:"必须为2位小数点的数据"
		},
		liveHours:{
			required:"请输入有效时长",
			digits:"请输入整数",
		}
	}
}, saveData);


//保存签约主播等级数据
function saveData(){
	var url;
	if(ISTYPE == "add"){
		url = "liveLevel/manage/add.jhtml";
	}else{
		url = "liveLevel/manage/update.jhtml";
	}
	
	var data = $("#liveLevelForm").serializeArray();
	var jsonText = JSON.stringify({
		dataform : data
	});
	
	data = jsonFromt(data);
	
	if (ISTYPE == "add" || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
				setTimeout(function() {
					window.location.href="liveLevel/manage/init.jhtml";
				}, 1000);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	
	} else {
		showWarningWindow('warning', "没做任何修改！");
	}
	
}

