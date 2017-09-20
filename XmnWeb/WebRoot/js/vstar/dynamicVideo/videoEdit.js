$(function(){
	var chooseUrl;
	
	$("#rid").chosenObject({
		hideValue : "id",
		showValue : "title",
		url : "livePageHome/manage/initAnchorVideoId.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
	validate("editFrom",{
		rules : {
			homeSort : {
				required : true
			}
			
		},
		messages:{
		}
	},save);
	
	function save(){
		if(!$("#rid").val()){
			showWarningWindow("warning","请选择精彩视频!",9999);
			return;
		}
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		$.ajax({
			type : 'post',
			url : "dynamicVideo/edit/video.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				loadVideo();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});