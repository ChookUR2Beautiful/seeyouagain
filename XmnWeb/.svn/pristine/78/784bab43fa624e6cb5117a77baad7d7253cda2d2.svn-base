var searchChosen = undefined;
var modelUrl = "";
$(document).ready(function() {
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
	
	startValidate();
		 
	var url = window.location.href;
	if(url.indexOf("user_terminal") > 0){
		$(":input[name='client']").val(1);
		$("#objectStr").html("全部");
		$("#objectStr2").html("自定义选择");
		$("#objectStr3").html("特定城市");
		modelUrl = "user_terminal";
	}
	if(url.indexOf("businessman") > 0){
		$(":input[name='client']").val(2);
		$("#objectStr").html("全部");
		$("#objectStr2").html("自定义选择");
		$("#sendObjectType2").hide();
		modelUrl = "businessman";
	}
	if(url.indexOf("business_cooperation") > 0){
		$(":input[name='client']").val(3);
		$("#objectStr").html("全部");
		$("#objectStr2").html("自定义选择");
		modelUrl = "business_cooperation";
		$("#sendObjectType2").hide();
	}
		 
		 
	if($("input[name='tdate']").val()){
		$(".sendDate").show();
	}
		 
	$("input[name='sendDate']").click(function(){
		var value = this.value;
		if(this.value == 0){
			$("input[name='tdate']").val("");
			$("input[name='edate']").val("");
			$(".sendDate").hide();
		}else{
			$(".sendDate").show();
		}
	});
	$("#areaInfo").areaLd({
//		isChosen : true,
//		isMultiple : true,// 区域是否支持多选（在isChosen为true时），
		separator : ",",
		showConfig : [ {
			name : "province",
			tipTitle : "--省--",
		}, {
			name : "city",
			tipTitle : "--市--",
		} ]
	});
	$("input[name='sendObjectType']").click(function(){
		var value = this.value;
		if(this.value == 0){
			$(".sendObjectTr").hide();
			$(".cityTr").hide();
		}else if(this.value == 2){
			$(".sendObjectTr").hide();
			$(".cityTr").show();
		}else{
			$(".cityTr").hide();
			$(".sendObjectTr").show();
			if(!searchChosen){
				if($(":input[name='client']").val() == 1){
					searchChosen = $("#object").searchChosen({
						url : "user_terminal/appPush/init/choseMember/init.jhtml",
						separator : "!@#"
					});
				}
				if($(":input[name='client']").val() == 2){
					searchChosen = $("#object").searchChosen({
						url : "businessman/appPush/init/choseSeller/init.jhtml"
					});
				}
				if($(":input[name='client']").val() == 3){
					searchChosen = $("#object").searchChosen({
						url : "business_cooperation/appPush/init/choseJoint/init.jhtml"
					});
				}
			}
		}
	});
	
	if ($("#isType").val() != 'add'){
		if($(":input[name='object']").val()){
			console.info($(":input[name='object']").val());
			$("#sendObjectTypeTd").empty().append("部分用户");
		}
		if($("#areaInfo").attr("initValue")){
			$("#sendObjectTypeTd").empty().append($("#areaInfo")[0].outerHTML);
		}
		inputToText("#editAppPushForm");
	}
});
function startValidate(){
	validate("editAppPushForm",{
		rules:{
			title:{
				required:true,
				maxlength:10
			},
			status:{
				required:true
			}, 
			type:{
				required:true
			},
			action:{
				required:true,
				maxlength:100
			},
			remind:{
				required:true
			},
			client:{
				required:true
			},
			contenttype:{
				required:true
			},
			content:{
				required:true,
				maxlength:30
			}
		},
		messages:{
			title:{
				required:"标题不能为空!",
				maxlength:"最大10个字符"
			},
			status:{
				required:"请选择一个状态!"
			}, 
			type:{
				required:"请选择一个类型!"
			},
			action:{
				required:"后续动作不能为空",
				maxlength:"内容描述最大100个字符"
			},
			remind:{
				required:"请选择一个提醒方式!"
			},
			client:{
				required:"请选择一个客户端类型!"
			},
			contenttype:{
				required:"请选择一个内容类型!"
			},
			content:{
				required:"内容不能为空!",
				maxlength:"最大30个字符"
			}
		}},formAjax);
}

function formAjax(){
	var data = jsonFromt($('#editAppPushForm').serializeArray());
	/* if(data.sendDate==0){
		if(!checkData(dateCompare(data.edate, new Date()) >= 1000*60*30, "input[name='edate']", "过期时间应晚于推送时间30分钟")){
			return false;
		}
	} */
	if(data.sendDate==1){
		if(!(checkData(data.tdate, "input[name='tdate']", "计划推送必须填写推送开始时间") &&
			 checkData(dateCompare(data.tdate, new Date()) >= 0, "input[name='tdate']", "推送开始时间应大于当前时间") &&
			 checkData(data.edate, "input[name='edate']", "计划推送必须填写推送结束时间") &&
		     checkData(dateCompare(data.edate, data.tdate) >= 1000*60*30, "input[name='edate']", "推送结束时间应晚于推送开始时间30分钟"))){
			return false;
		}
	}
	if(data.sendObjectType == 1){
		if(!checkData(data.object, searchChosen.next(), "不能为空")){
			return false;
		}
	}
	
	if(data.sendObjectType == 2 && !customValidate()){
		return false;
	}
	showSmConfirmWindow(function(){
		var url;
		if ($("#isType").val() == 'add') {
			url = modelUrl + '/appPush/add.jhtml' + '?t='
					+ Math.random();
		} else {
			url = modelUrl + '/appPush/update.jhtml' + '?t='
					+ Math.random();
		}
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				if (data.success) {
					window.history.back();
				}else{
					showSmReslutWindow(data.success, data.msg);
				}
			},
			error : function(XMLHttpRequest, textStatus,
					errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	},"请确认推送信息是否正确，提交后无法修改！");
}

function customValidate() {
	//var selectAray = [ "province", "city" ];
	//return checkSelect('#editAppPushForm', "#areaInfo", selectAray,true);
	var province=$("select[name='province']");
	var city=$("select[name='city']");
	var flag=true;
	if(province.val()==""){
		submitDataError(province,"请选择");
		flag= false;
	}
	if(city.val()==""){
		submitDataError(city,"请选择");
		flag= false;
	}
	return flag;
}