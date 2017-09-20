var formId = "editFrom";
var recordList;
var jsonTextInit;

$(function() {
	inserTitle(
		' > 直播频道 > <a href="experienceofficer/setting/init.jhtml" target="right">美食体验卡配置</a> ',
		'userSpan', true);
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	// 验证提交添加或修改的数据
	initValidator();
	pariceShowHide($("input[name='isFree']:checked").val()==1);
	validate(formId, {
		rules : {
			price:{
				required: true,
				number:true ,
				biggerZero:true,
			},
			isFree:{
				required: true,
			},
			nums : {
				required : true,
				digits:true,
				min:1,
			},
			days:{
				required:true,
				digits:true,
				min:1
			}
		},
		messages:{
			price:{
				required: "填写金额",
				number:"输入数字",
				biggerZero:"价格需大于0",
			},
			isFree:{
				required:"请选择是否免费",
			},
			nums:{
				required:"请输入体验次数",
				digits:"整数啊，大神",
				min:"不能小于1",
			},
			days:{
				required:"请输入有效天数",
				digits:"整数啊，大神",
				min:"不能小于1",
			}
		}
	}, save);
});

function save(){
	var url = "experienceofficer/setting/save.jhtml";
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	if($("input[name='isFree']:checked").val()==1){
		$("input[name='price']").val(0);
	}
	
	if (jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
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
	} else {
		$('#prompt').hide();
		$('#triggerModal').modal('hide');
		showSmReslutWindow(false, "没做任何修改！");
	}

}
$("input[name='isFree']").on("change",function(){
	pariceShowHide($(this).val()==1);
});
function pariceShowHide(isFree){
	if(isFree){
		$('#price_id').hide();
	}else{
		$('#price_id').show();
	}
}
function initValidator(){
$.validator.addMethod("biggerZero",function(value,element,params){
		if(value > 0){
			return true;
		}else{
			return false;
		}
},"大于0的数");
}
