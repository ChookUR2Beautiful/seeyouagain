$(function(){
	

$("#videoImgImg").uploadImg({
	urlId : "videoImg",
	showImg : $('#videoImg').val()
});

	
	validate("videoFrom",{
		rules : {
			title : {
				required : true
			},
			description : {
				required : true
			},
			videoUrl : {
				required : true
			}
		},
		messages:{
			
		}
	},save);
	
	function save(){
		if(!$("#videoImg").val()){
			showWarningWindow("warning", "请输入视频截图!", 9999);
			return;
		}
		var data = $('#videoFrom').serializeArray();
		data = jsonFromt(data);
		
		$.ajax({
			type : 'post',
			url : "VstarContent/manage/main/edit/init/video.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					if(!$("#videoId").val()){
						videos.push(data.data.id)
					}
					loadVideo();
			    }else{
			    	showSmReslutWindow(data.success, data.msg);
			    }			
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});