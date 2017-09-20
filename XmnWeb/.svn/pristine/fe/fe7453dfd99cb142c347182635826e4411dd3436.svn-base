var formId = "editFrom";
var liveRecordSelected;
$(function() {

	validate(formId, {
		rules : {
			nums : {
				required : true,
				digits:true,
				min:1,
			},
			days:{
				required:true,
				digits:true,
				min:1,
			}
		},
		messages:{
		}
	}, save);
	
	//初始化预告下拉框
	initUrsRecordId();
	
});




/**
 * 保存信息
 */
function save() {
	var url = "experienceofficer/user/add.jhtml";
	if($('#uid').val()==null ||$('#uid').val()==''){
		showSmResultTipWindow("请选择会员");
		return false;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
		
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
				recordList.reset();
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});

}


//初始化直播用户下拉框
function initUrsRecordId(){
	$("#uid").chosenObject({
		hideValue : "uid",
		showValue : "phone",
		showType:"multiple",//选项显示形式
		showParams:["uid","phone"],
		url : "experienceofficer/user/add/urs.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//选择商家后，修改商家名称 (to fix 编辑无法触发,暂时后台同步数据)
$('body').on("click",'#uid_chosen .chosen-results li',function(){
	var uid =  $("#uid").find("option:selected").val();
	console.info("liveRecordId..."+uid);
	$.ajax({
		type: "POST",
		url : "experienceofficer/user/add/urs.jhtml?t=new Date()",
		dataType : "json",
		data: {"uid":uid},
		success : function(data){
			if(data != null){
				$("#phone").val(data.content[0].phone);
				$("#nname").val(data.content[0].nname);
			}
		}
	});
});

