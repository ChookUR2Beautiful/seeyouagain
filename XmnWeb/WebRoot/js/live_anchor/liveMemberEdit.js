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
			referrerType : {
				required : true
			},
			limitBalance:{
				required:true,
				number:true,
				range:[0,1000000000]
			},
			juniorLimitRatio:{
				required:true,
				number:true,
				range:[0,100]
			}
		},
		messages:{
			referrerType:{
				required:"请选择推荐人类型"
			},
			limitBalance:{
				required:"请输入鸟币限制最低余额",
				number:"请输入0-1000000000之间的数字",
				range:"请输入0-1000000000之间的数字"
			},
			juniorLimitRatio:{
				required:"请输入红包下线充值限领比例",
				number:"请输入0-100之间的数字",
				range:"请输入0-100之间的数字"
			}
		}
	}, save);
	
	//鸟币限制最低余额显示初始化
	limitBalanceDivShowInit();
	
	//加载上级信息
//	loadSuperiorInfo();
	
	//编辑控制
	editControl();
	
});




/**
 * 保存礼物信息
 */
function save() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "liveMember/manage/add" + suffix;
	} else {// 修改操作
		url = "liveMember/manage/update" + suffix;
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
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					if (isAdd) {
						anchorList.reset();
					} else {
						anchorList.reload();
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
		showWarningWindow("warning", "没做任何修改！");
	}
}

/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	
	return result;
}


/**
 * 绑定鸟币消费余额限制设置change事件
 */
$("input[name='restrictive']").change(function(){
	limitBalanceDivShowInit();
});


/**
 * 鸟币最低余额限制，当余额低于设置值，不允许消费。
 */
function limitBalanceDivShowInit(){
	var restrictive=$("input[name='restrictive']:checked").val();
	//001 不限制，002 限制
	if(restrictive=='002'){
		$("#limitBalanceDiv").css("display","block");
	}else{
		$("#limitBalanceDiv").css("display","none");
		$("#limitBalance").val(0);
	}
}

/**
 * 编辑控制
 */
function editControl(){
	var objectOriented=$("#objectOriented").val();
	if(objectOriented=="1"){
		$("#juniorLimitRatioDiv").css("display","block");
	}else{
		$("#juniorLimitRatioDiv").css("display","none");
	}
	
}

/**
 * 加载上级信息
 * @param uid
 */
function loadSuperiorInfo(){
//	debugger;
	var superiorUid=$("#superiorUid").val();//上级uid
	if(superiorUid){
		$.ajax({
			type: "POST",
			url : "anchor/manage/getAnchorInfo.jhtml?t=new Date()",
			dataType : "json",
			data: {"uid":superiorUid},
			success : function(data){
				if(data != null){
					//上级显示手机号
					var superior=data.phone;
					$("#superior").val(superior);
					$("#superiorDiv").css("display","block");
				}
			}
		});
	}else{
		//不存在上级，且下线人数为0
		var juniors =$("#juniors").val();
		if(parseInt(juniors)==0){
			$("#superiorIdChosenDiv").css("display","block");
			initLiverId();
		}
	}
	
};


//初始化直播用户下拉框,排除等下级
function initLiverId(){
//	debugger;
	var currentUid=$("#uid").val();
	$("#superiorIdChosen").chosenObject({
		hideValue : "id",
		showValue : "showValue",//输入查询值
		showType:"multiple",//选项显示形式
		showParams:["phone","nickname"],//showType为multiple时生效,phone|nickname
		url : "anchor/manage/initLiverExclude.jhtml",
		filterVal:currentUid,
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}



