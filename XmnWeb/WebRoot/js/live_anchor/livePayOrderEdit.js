var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			payment:{
				required: true,
				digits:true,
				range:[1,100000000]
			},
			realCoin : {
				required : true,
				digits:true,
				range:[1,100000000]
			},
			objectOriented:{
				required:true
			}
		},
		messages:{
			payment:{
				required:"请输入充值金额",
				digits:"请输入整数",
				range:"输入值必须为1-100000000之间的整数"
			},
			realCoin:{
				required:"充值鸟豆不能为空",
				digits:"请输入整数",
				range:"输入值必须为1-100000000之间的整数"
			},
			objectOriented:{
				required:"请选择会员类型"
			}
		}
	}, save);
	
	//初始化直播用户下拉框
	initLiverId();
	
	
});




/**
 * 保存信息
 */
function save() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "livePayOrder/manage/add" + suffix;
	} else {// 修改操作
		url = "livePayOrder/manage/update" + suffix;
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
				//提交后禁用提交按钮，防止重复提交表单
				$('#submitBtn').attr("disabled","disabled");
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					if (isAdd) {
						livePayOrderList.reset();
					} else {
						livePayOrderList.reload();
					}
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	} else {
		$('#prompt').hide();
		$('#triggerModal').modal('hide');
		showSmReslutWindow(false, "没做任何修改！");
	}
}

/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	var anchorId=$("#anchorId").val();
	if(anchorId == null || anchorId==""){
		showWarningWindow("warning","请选择会员!",9999);
		result=false;
		return result;
	}
	
	//2017-04-14 去掉充值类型与会员类型一致校验
	/*var uObjectOriented=$("#uObjectOriented").val();
	var objectOriented=$("input[name='objectOriented']:checked").val();
	if(uObjectOriented!="" && uObjectOriented!=objectOriented && objectOriented != 4){
		if(uObjectOriented!="" && uObjectOriented!=objectOriented && objectOriented != 4){
		var objectOrientedVal="";
		switch (uObjectOriented) {
		case "0":
			objectOrientedVal="一般会员";
			break;
		case "1":
			objectOrientedVal="VIP";
			break;
		case "2":
			objectOrientedVal="商家";
			break;
		case "3":
			objectOrientedVal="直销";
			break;
		case "4":
			objectOrientedVal="营业厅会员";
			break;
		default:
			objectOrientedVal="VIP";
			break;
		}
		showWarningWindow("warning","选择会员类型与充值的会员类型["+objectOrientedVal+"]不一致!",9999);
		result=false;
		return result;
	}*/
	
	return result;
}


//初始化直播用户下拉框
function initLiverId(){
//	debugger;
	$("#anchorId").chosenObject({
		hideValue : "id",
		showValue : "showValue",
		showType:"multiple",//选项显示形式
		showParams:["phone","nickname","objectOrientedVal"],//showType为multiple时生效,phone|nickname|objectOrientedVal
		url : "anchor/manage/initLiverId.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

/**
 * 修改直播用户后，修改关联信息
 */
$('body').on("click",'#anchorId_chosen .chosen-results li',function(){
//	debugger;
	var objectOriented=$("input[name='objectOriented']:checked").val();
	if(objectOriented==undefined || objectOriented == ""){
		 $("#anchorId").val("");
		 $("#anchorId").trigger("chosen:updated");
		 showWarningWindow("warning","请选择充值渠道!",9999);
		 return ;
	}
	
	var anchorId =  $("#anchorId").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "anchor/manage/getAnchorInfo.jhtml?t=new Date()",
		dataType : "json",
		data: {"id":anchorId,"objectOriented":objectOriented},
		success : function(data){
			if(data != null){
				$("#uid").val(data.uid);
				$("#uidRelationChain").val(data.uidRelationChain);
				$("#uObjectOriented").val(data.objectOriented);
				//加载上级信息
				if(data.superiorUid){
					$("#superiorUid").val(data.superiorUid);
					loadSuperiorInfo(data.superiorUid);
					loadSuperiorRankInfo(data.superiorUid,objectOriented);
				}else{
					$("#superiorUid").val("");
					$("#superior").val("");
					$("#superiorRankName").val("");
				}
			}
		}
	});
});

/**
 * 支付金额修改触发
 */
$("#payment").change(function(){
	var payment=$("#payment").val();
	var realCoin=payment*10;
	$("#realCoin").val(realCoin);
});


/**
 * 加载上级信息
 * @param uid
 */
function loadSuperiorInfo(uid){
	$.ajax({
		type: "POST",
		url : "anchor/manage/getAnchorInfo.jhtml?t=new Date()",
		dataType : "json",
		data: {"uid":uid},
		success : function(data){
			if(data != null){
				//上级显示手机号
				var superior=data.phone;
				$("#superior").val(superior);
			}
		}
	});
};

/**
 * 加载上级等级信息
 * @param uid
 */
function loadSuperiorRankInfo(uid,rankSource){
	$.ajax({
		type: "POST",
		url : "anchor/manage/getLiverRankInfo.jhtml?t=new Date()",
		dataType : "json",
		data: {"uid":uid,"rankSource":rankSource},
		success : function(data){
			if(data != null){
				var rankName=data.rankName;
				$("#superiorRankName").val(rankName);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
		
	});
};

