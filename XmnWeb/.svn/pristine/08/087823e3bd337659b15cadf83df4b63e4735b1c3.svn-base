$(function(){
	
	$("#commontId").chosenObject({
		hideValue : "id",
		showValue : "sellername",
		url : "businessman/recommend/commentChoose.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
	validate("editFrom",{
		rules : {		
			sort : {
				required : true
			}
			
		},
		messages:{
		}
	},save);
	
	function save(){
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		$.ajax({
			type : 'post',
			url : "businessman/recommend/addComment.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				loadComment();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});