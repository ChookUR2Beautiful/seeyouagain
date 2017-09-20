$(function(){
	$("#picUrlImg").uploadImg({
		urlId : "showSmallImg",
		showImg : $('#showSmallImg').val()
	});
	
	validate("editFrom",{
		rules : {
			word : {
				required : true
			},
			defaultWord : {
				required : true,
			}
		},
		messages:{
			word:{
				required:"搜索栏文字",
			},
			sort : {
				required : "默认搜索关键字",
			}
		}
	},save);
	
	function save(){
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		$.ajax({
			type : 'post',
			url : basePath+"/fresh/word/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					wordList.reload();
			    }			
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});