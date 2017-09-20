$(function(){
	var chooseUrl;
	if($("#id").length){
		chooseUrl="groupLevel/getLastLevel.jhtml?lastLevelId="+$("#id").val();
	}else{
		chooseUrl="groupLevel/getLastLevel.jhtml";
	}
	
	$("#lastLevelId").chosenObject({
		hideValue : "id",
		showValue : "levelName",
		url : chooseUrl,
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%",
		defaultValue: "=最上级="
	});
	
	$("#picUrlImg").uploadImg({
		urlId : "levelPic",
		showImg : $('#levelPic').val()
	});
	
	validate("editFrom",{
		rules : {
			levelName : {
				required : true
			},
			minPerformance : {
				required : true
			},
			maxPerformance : {
				required : true
			},
			awardScale : {
				required : true
			}
		},
		messages:{
		}
	},save);
	
	function save(){
		if(!$("#levelPic").val()){
			showWarningWindow("warning","请上传等级图片!",9999);
			return;
		}
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		$.ajax({
			type : 'post',
			url : "groupLevel/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				levelList.reload();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});