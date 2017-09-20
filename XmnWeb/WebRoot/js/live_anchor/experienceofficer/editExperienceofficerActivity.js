var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
var liveRecordSelected;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			enrollTime:{
				required: true,
			},
			limitNum : {
				required : true,
				digits:true,
				min:1,
			},
			liveRecordId:{
				required:true
			}
		},
		messages:{
			enrollTime:{
				required:"请选择开抢时间",
			},
			limitNum:{
				required:"请输入名额",
			},
			liveRecordId:{
				required:"请选择通告"
			}
		}
	}, save);
	
	//初始化预告下拉框
	initLiveRecordId();
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		minuteStep:1,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
});




/**
 * 保存信息
 */
function save() {
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "experienceofficer/activity/add" + suffix;
	} else {// 修改操作
		url = "experienceofficer/activity/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
		
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
						recordList.reset();
					} else {
						recordList.reload();
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


//初始化直播用户下拉框
function initLiveRecordId(){
	$("#recordId").chosenObject({
		hideValue : "id",
		showValue : "zhiboTitle",
		showType:"multiple",//选项显示形式
		showParams:["zhiboTitle","nname"],
		url : "experienceofficer/activity/init/liveRecord.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//选择商家后，修改商家名称 (to fix 编辑无法触发,暂时后台同步数据)
$('body').on("click",'#recordId_chosen .chosen-results li',function(){
	var liveRecordId =  $("#recordId").find("option:selected").val();
	console.info("liveRecordId..."+liveRecordId);
	$.ajax({
		type: "POST",
		url : "liveRecord/manage/init/detail.jhtml?t=new Date()",
		dataType : "json",
		data: {"id":liveRecordId},
		success : function(data){
			if(data != null){
				$("#sellerid").val(data.data.sellerid);
				$("#sellername").val(data.data.sellerAlias);
				$("#zhiboAddress").val(data.data.zhiboAddress);
				$("#planStartDate").val(data.data.planStartDate);
			}
		}
	});
});

