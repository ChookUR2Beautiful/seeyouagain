$(document).ready(function() {
	validate("orderQuestionpForm",{
		rules:{
			StringBid:{
				required:true
			},
			phoneid:{
				required:true,
				maxlength:255
			}
		},
		messages:{
			roleName:{
				required:"订单号不能为空！"
			},
			phoneid:{
				required:"手机号不能缺省!"
			}
		}},orderQuestionpForm),
	 	validate("guestpForm",{
			rules:{
				StringBid:{
					required:true
				},
				phoneid:{
					required:true,
					maxlength:255
				}
			},
			messages:{
				roleName:{
					required:"订单号不能为空！"
				},
				phoneid:{
					required:"手机号不能缺省!"
				}
			}},guestpForm),
	 	
	 	validate("merchantpForm",{
			rules:{
				StringBid:{
					required:true
				},
				phoneid:{
					required:true,
					maxlength:255
				}
			},
			messages:{
				roleName:{
					required:"订单号不能为空！"
				},
				phoneid:{
					required:"手机号不能缺省!"
				}
			}},merchantpForm),
	 	
	 	validate("noMemberpForm",{
			rules:{
				StringBid:{
					required:true
				},
				phoneid:{
					required:true,
					maxlength:255
				}
			},
			messages:{
				roleName:{
					required:"订单号不能为空！"
				},
				phoneid:{
					required:"手机号不能缺省!"
				}
			}},noMemberpForm),
	 	validate("cooperationpForm",{
			rules:{
				StringBid:{
					required:true
				},
				phoneid:{
					required:true,
					maxlength:255
				}
			},
			messages:{
				roleName:{
					required:"订单号不能为空！"
				},
				phoneid:{
					required:"手机号不能缺省!"
				}
			}},cooperationpForm); 
	
}); 
function cooperationpForm(){
	submit($("#cooperationpForm"));
	return false; 
}

function noMemberpForm(){
	submit($("#noMemberpForm"));
	return false; 
}

function merchantpForm(){
	submit($("#merchantpForm"));
	return false; 
}

function guestpForm(){
	submit($("#guestpForm"));
	return false; 
}

function orderQuestionpForm(){
	submit($("#orderQuestionpForm"));
	return false; 
}


function prepare(form){
	 var array = new Array();
	 var obj = formHandel(form);
	 var targetFomr = jsonFromt(form.serializeArray());
	 array.push(targetFomr);
	 if(obj){
		 array.push(obj); 
	 }
	 return merge(array);
	
}

function formHandel(form){
	if(form){
		var array = new Array();
		var dependentForm = $(form).attr("dependentForm");
		$.each(dependentForm.split(","),function(){
			var dependentFormId = this.toString();
			array.push(jsonFromt($("#"+dependentFormId).serializeArray()));
		});
		return merge(array);
	}
	
	
}
function merge(array){
	var obj = {};
	if(array.length>0){
		var key;
		$.each(array,function(){
			for(key in this){
				var value = obj[key];
				if(!value){
					obj[key]=this[key];
				}	
			}
		});
	}
	return obj;
	
}

function submit(form){
	var formData = prepare(form);
	$.ajax({
		type : 'post',
		url : 'serviceManagement/phoneRecord/add.jhtml',
		data : formData,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			 $('#triggerModal').modal('hide');
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}