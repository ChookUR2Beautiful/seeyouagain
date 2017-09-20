var ISTYPE;
$(document).ready(
	function() {
		ISTYPE = $("#isType").val();
		if (ISTYPE == "add") {
			inserTitle(' > <span><a href="rechargeReward/manage/add/init.jhtml?isType=add" target="right">配置V客充值奖励</a>','addliveLevel',false);
		} else {
			inserTitle(' > 配置V客充值奖励', 'editRechargeReward', false);
		}

		/**
		 * 返回
		 */
		$("#backId").on("click", function() {
			muBack();
		});
		

		$("#choice1").on("click", ".icon-plus", function(event) {
			if ($(this).parents(".col-md-8").find(".input-group")) {
				addChoice1Data(event);
			}
		});
		
		$("#choice1").on("change", ".ctype", function(event) {
			if ($(this).parents(".col-md-8").find(".input-group")) {
				onchangeCtype(event);
			}
		});
		
		$("#choice2").on("click", ".icon-plus", function(event) {
			if ($(this).parents(".col-md-8").find(".input-group")) {
				addChoice2Data(event);
			}
		});
		
		$("#choice2").on("change", ".ctype", function(event) {
			if ($(this).parents(".col-md-8").find(".input-group")) {
				onchangeTabBCtype(event);
			}
		});
	});

	var jsonTextInit;
	$(function() {
		var dataformInit = $("#liveLevelForm").serializeArray();
		jsonTextInit = JSON.stringify({
			dataform : dataformInit
		});
		//添加校验
		initValidator();
      }
	

);


/**
 * 初始化验证方法
 */
function initValidator(){
	
}	 

/** 切换优惠劵类型*/
function onchangeCtype(event) {
//    console.log("改变优惠劵类型:" + $(input).attr("typeName"));
	t = event.target;  
	var id = t.name.split('[')[1].split(']')[0]; //
/*    var $cid=$("select[name='tCoupon["+id+"].cid']");
    var $cidDiv = $("#cidDiv["+id+"]");
    $cidDiv.empty().append($cid);*/

    $("select[name='tCoupon["+id+"].cid']").chosenObject({
		id : "cid",
		hideValue : "value",
		showValue : "cname",
        filterVal : $(t).val(),
		url : "coupon/couponissue/queryYouHuiQuan.jhtml",
		isChosen : true
	});
    
}

/** 切换优惠劵类型*/
function onchangeTabBCtype(event) {
//    console.log("改变优惠劵类型:" + $(input).attr("typeName"));
	t = event.target;  
	var id = t.name.split('[')[1].split(']')[0]; //
    $("select[name='tCouponTabB["+id+"].cid']").chosenObject({
		id : "cid",
		hideValue : "value",
		showValue : "cname",
        filterVal : $(t).val(),
		url : "coupon/couponissue/queryYouHuiQuan.jhtml",
		isChosen : true
	});
    
}


/**
 * 返回
 */
function muBack(){
	var url = contextPath + '/rechargeReward/manage/init.jhtml';
		location.href =url;
}

//****************************************保存数据方法********************************
validate("excitationProjectAForm",{
	/*rules : {
		levelName : {
			required : true,
			checkLevelName:true,
			rangelength:[1, 20]
		}
	},
	messages:{
		levelName:{
			required:"请输入级别名称",
			checkLevelName:"级别名称已存在!" ,
			rangelength:"用户名长度为  1-20  个字符"
		}
	}*/
}, saveProjectDataA);


var hiddens;

//保存签约主播等级数据
function saveProjectDataA(){
	var url;
	if(ISTYPE == "add"){
		url = "rechargeReward/manage/add.jhtml";
	}else{
		url = "rechargeReward/manage/update.jhtml";
	}
	
	var data = $("#excitationProjectAForm").serializeArray();
	data = jsonFromt(data);
	var jsonText = JSON.stringify({
		dataform : data
	});
	
	getRelationInfo();
	if(hiddens.length==0){
		showWarningWindow("warning", "请选择优惠券信息!", 9999);
		return;
	}
	var json = new Array();
	$.each(hiddens, function(i,item){
		json.push($(item).val());
	});
	var obj={json:json};
	json=$.toJSON(obj);
	data.productJson=json;
	
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
					window.location.href="rechargeReward/manage/init.jhtml";
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

function addChoice1Data(event) {
	t = event.target;  
	var cid = $("select[name='tCoupon["+t.id+"].cid']").val();  
	if (!cid) {
		 showWarningWindow('warning', "请选择优惠券！");
//		 submitDataError($("select[name='tCoupon["+t.id+"].cid']"), "请选择优惠券!");
		 return false;
	}
	var cname = $("select[name='tCoupon["+t.id+"].cid']").find("option:selected").text();  
	var num = $("input[name='tCoupon["+t.id+"].num']").val();
	if (!num) {
		showWarningWindow('warning', "请输入优惠券数量！");
//		submitDataError($("input[name='tCoupon["+t.id+"].num']"), "请输入优惠券数量!");
		 return false;
	} else {
		var reg = /^\d+$/;
		if (!reg.test(num)) {
			showWarningWindow('warning', "请输入整数数值！");
//			submitDataError($("input[name='tCoupon["+t.id+"].num']"), "请输入整数数值!");
			return false;
		}
	}
	
	var rankId = $("#choice1 input[name='verExcitationProjectList["+t.id+"].rankId']").val();;
	var map = {
		"type":"edit",
		"cid" : cid,
		"num" : num,
		"rankId":rankId
	};
	var tr = $("<tr id=" + cid + " class='text-center' >").append($("<td>").text(cname)).append($("<td>").text(num));
	tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
			  .html('<a href="javascript:;" onclick="deleteGroup(' + cid + ')">删除</a>')).append($("<input >").attr("type", "hidden").attr("name", "couponinfo").val($.toJSON(map)));
	
	//添加优惠券信息 $("#areaTable tr:last").after(tr);
	$("#productInfoTB"+t.id).append(tr);
}

function deleteGroup(pid){
	$("#"+pid).remove();
}

function getRelationInfo(){
	hiddens=$("#choice1").find("input[type=hidden][name=couponinfo]");
}


//****************************************保存数据方法********************************
validate("excitationProjectBForm",{
	/*rules : {
		levelName : {
			required : true,
			checkLevelName:true,
			rangelength:[1, 20]
		}
	},
	messages:{
		levelName:{
			required:"请输入级别名称",
			checkLevelName:"级别名称已存在!" ,
			rangelength:"用户名长度为  1-20  个字符"
		}
	}*/
}, saveProjectDataB);


var hiddensProjectB;

//保存B模式数据
function saveProjectDataB(){
	debugger;
	var url;
	if(ISTYPE == "add"){
		url = "rechargeReward/manage/add.jhtml";
	}else{
		url = "rechargeReward/manage/update.jhtml";
	}
	var data = $("#excitationProjectBForm").serializeArray();
	data = jsonFromt(data);
	var jsonText = JSON.stringify({
		dataform : data
	});
	
	getChoice2RelationInfo();
	//B模式取消优惠券必选校验	。modify by huang'tao 2017-07-31
	/*if(hiddensProjectB.length==0){
		showWarningWindow("warning", "请选择优惠券信息!", 9999);
		return;
	}*/
	var json = new Array();
	$.each(hiddensProjectB, function(i,item){
		json.push($(item).val());
	});
	var obj={json:json};
	json=$.toJSON(obj);
	data.productJson=json;
	
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
					window.location.href="rechargeReward/manage/init.jhtml";
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

function addChoice2Data(event) {
	t = event.target;  
	var cid = $("select[name='tCouponTabB["+t.id+"].cid']").val();  
	if (!cid) {
		showWarningWindow('warning', "请选择优惠券！");
		 return false;
	}
	var cname = $("select[name='tCouponTabB["+t.id+"].cid']").find("option:selected").text();  
	var num = $("input[name='tCouponTabB["+t.id+"].num']").val();
	if (!num) {
		showWarningWindow('warning', "请输入优惠券数量！");
		return false;
	} else {
		var reg = /^\d+$/;
		if (!reg.test(num)) {
//			submitDataError($("input[name='tCouponTabB["+t.id+"].num']"), "请输入整数数值!");
			showWarningWindow('warning', "请输入整数数值！");
			return false;
		}
	}
	var rankId = $("#choice2 input[name='verExcitationProjectList["+t.id+"].rankId']").val();
	var map = {
		"type":"edit",
		"cid" : cid,
		"num" : num,
		"rankId":rankId
	};
	var tr = $("<tr id=" + cid + " class='text-center' >").append($("<td>").text(cname)).append($("<td>").text(num));
	tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
			  .html('<a href="javascript:;" onclick="deleteGroup(' + cid + ')">删除</a>')).append($("<input >").attr("type", "hidden").attr("name", "couponinfo").val($.toJSON(map)));
	
	//添加优惠券信息 $("#areaTable tr:last").after(tr);
	$("#productInfoTabB"+t.id).append(tr);
}

function getChoice2RelationInfo(){
	hiddensProjectB=$("#choice2").find("input[type=hidden][name=couponinfo]");
}


