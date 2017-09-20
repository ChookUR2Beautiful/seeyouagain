var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	$('.form_datetime').datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 1,
		minView : 0,
		minuteStep : 1,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	
	$('.form_datetime').datetimepicker('setStartDate',new Date());
	
	
	
	validate(formId, {
		rules : {
			playAmount : {
				required :true,
				digits:true,
				range:[1,99999]
			},
			content : {
				required:true
			},
			sendTime : {
				required:true
			}
		},
		messages:{
			playAmount:{
				required:"请输入播放次数",
				digits:"播放次数必须为正整数",
				range:"播放次数必须为正整数"
			},
			content:{
				required:"请输入广播内容"
			},
			sendTime : {
				required:"请输入发送时间"
			}
		}
	}, broadcastSave);
	
	
	// 广播设置 默认0 全房间 1指定房间
	$("input[name='assignRoom']").change(function() {
		assignRoomInit();
	});
	//广播设置初始化
	assignRoomInit();
	
	
	// 立即发送  默认0 否 1是
	$("input[name='immediate']").change(function() {
		immediateInit();
	});
	//立即发送初始化
	immediateInit();
	
	//加载正在直播的主播通告
	initRecordId();
	
});




/**
 * 保存广播信息
 */
function broadcastSave() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "liveBroadcast/manage/add" + suffix;
	} else {// 修改操作
		url = "liveBroadcast/manage/update" + suffix;
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
						broadcastList.reset();
					} else {
						broadcastList.reload();
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
//	debugger;
	var result=true;
	var assignRoom = $("input[name='assignRoom']:checked").val();
	var nickname=$("#nickname").val();
	var sendTime =new Date($("#sendTime").val());
	if(assignRoom=='1' && (nickname == null || nickname=="")){
		showWarningWindow("warning","请选择主播!",9999);
		rsult=false;
		return ;
	}
	
	var immediate = $("input[name='immediate']:checked").val();
	var currentTime = new Date();
	if(immediate==0&&(sendTime < currentTime)){
		showWarningWindow("warning","发送时间不能早于当前时间!",9999);
		rsult=false;
		return ;
	}
	
	return result;
}

/**
 * 广播设置(指定房间)初始化
 */
function assignRoomInit(){
//	debugger;
	var assignRoom = $("input[name='assignRoom']:checked").val();
	if (assignRoom == 1) {
		$("#anchorDiv").css("display","block");
	} else {
		$("input[name='assignRoom'][value='0'").attr("checked",true);//默认全房间
		$("#anchorDiv").css("display","none");
		
		$("#recordId").val('');
		$("#nickname").val('');
	}
}

/**
 * 立即发送初始化
 */
function immediateInit(){
//	debugger;
	var immediate = $("input[name='immediate']:checked").val();
	if(immediate == 1){
		$("#sendTimeDiv").css("display","none");
		$("#sendTime").val('');
	}else{
		$("input[name='immediate'][value='0'").attr("checked",true);
		$("#sendTimeDiv").css("display","block");
	}
}

//初始化主播房间
function initRecordId(){
//	debugger;
	$("#recordId").chosenObject({
		hideValue : "id",
		showValue : "nname",
		url : "liveBroadcast/manage/initRecordId.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}


//选择主播后，修改主播昵称隐藏域的值
$('body').on("click",'#recordId_chosen .chosen-results li',function(){
//	debugger;
	var id =  $('#recordId').find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "liveBroadcast/manage/getRecordById.jhtml?t=new Date()",
		dataType : "json",
		data: {"id":id},
		success : function(data){
			if(data != null){
				$("#nickname").val(data.nname);
			}
		}
	});
	
});
