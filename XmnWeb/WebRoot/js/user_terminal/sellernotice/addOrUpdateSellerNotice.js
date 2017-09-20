$(function(){
	advertisingValidate();
})
 /**
  * 表单验证
  */
 function advertisingValidate(){
 	validate("editAdvertisingForm",{
			rules:{
				remark:{
					required:true,
					maxlength:1000
				}
			},
			messages:{
				remark:{
					required:"必填",
					maxlength:"用户须知最大长度为1000字符!"
				}
			}},formAjax);
 }
	function formAjax(){
			var url ;
			if($('#isType').val() ==  'add'){
						url = 'user_terminal/tsellernotice/add.jhtml' + '?t=' + Math.random();
					}else{
						url = 'user_terminal/tsellernotice/update.jhtml' + '?t=' + Math.random();
					}
			console.info($('#isType').val() ==  'add');
			var data = jsonFromt($('#editAdvertisingForm').serializeArray());
			$.ajax({
				type : 'post',
				url : url,
				data : data,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							sellerNoticeList.reset();
						}else{
							sellerNoticeList.reload();
						}
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
	}