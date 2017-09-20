$(function(){
	

/*$("#videoImgImg").uploadImg({
	urlId : "videoImg",
	showImg : $('#videoImg').val()
});
*/
	
	validate("videoFrom",{
		rules : {
			title : {
				required : true
			},
			videoUrl : {
				required : true
			},
			duration : {
				required : true
			}
		},
		messages:{
			
		}
	},save);
	
	$("#is_pay").on("click",function(){
		if($("#is_pay:checked").length){
			$("#pay_model").show();
		}else{
			$("#pay_model").hide();
			$("#pay_model").find("input[type='text']").val('');
			$("#pay_model").find("input[type='number']").val('');
			$("#pay_model").find("input[type='radio']").prop("checked", false);
			$("#pay_model").find("input[type='checkbox']").prop("checked", false);
		}
	});
	
	
	function save(){
		if($("#is_pay:checked").length){
			if(!$("#pay_model").find("[name='amount']").val()){
				showWarningWindow("warning", "请输入金额!", 9999);
				return
			}
			if(!$("#pay_model").find("[name='zbalance']").val()){
				showWarningWindow("warning", "请输入鸟币金额!", 9999);
				return
			}
			if($("#look:checked").length){
				if(!$("[name='experienceTime']").val()){
					showWarningWindow("warning", "请输入试看时间!", 9999);
					return
				} 
			}
		}
		var data = $('#videoFrom').serializeArray();
		data = jsonFromt(data);
		
		$.ajax({
			type : 'post',
			url : "vstarLiverContent/video/edit/init.jhtml",
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