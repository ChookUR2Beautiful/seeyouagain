$(document).ready(function() {
	 		$("#pasw").click(function(){
	 			updaePassword("#currentchangePasswordForm","#currentPasForm");
	 			return false;
	 		});
	 	});
	 	
		function updaePassword(form,modal){
			var rightFrame = window.parent.document.getElementsByName("right")[0].contentDocument.body;
			var changePasswordDiv = $(rightFrame).find("#currentchangePasswordDiv");
			if($(changePasswordDiv).length==0){
				changePasswordDiv = $("<div   id='currentchangePasswordDiv'>");
				$(rightFrame).append(changePasswordDiv);
			}
			$(changePasswordDiv).html($("#divForm").html());
			var modaldiv = $(rightFrame).find(modal);
			$(modaldiv).modal({backdrop:false,position:80});
			$(rightFrame).off("submit",form);
			 $(rightFrame).on("submit",form,function(event){
				 var $target = $(event.target);
				 var data = jsonFromt($target.serializeArray());
				 var array = new Array();
				 valiDateForm(data,$target,array);
				 if(array.length>0){
					 showMsg(modaldiv,array.join(""));
				 }else{
					hideMsg(modaldiv);
					ajaxSubmit(data,rightFrame,modaldiv,form);
					
				 }
				return false;
			});  
		}
		
		function showMsg(modaldiv,msg){
			var $msg = $(modaldiv).find("#msg");
			$msg.html(msg);
			$msg.show();
		}
		function hideMsg(modaldiv){
			var $msg = $(modaldiv).find("#msg");
			$msg.empty();
			$msg.hide();
		}
		
		function ajaxSubmit(data,rightFrame,modaldiv,form){
			$.ajax({
				cache :false,
				type : 'post',
				url : "currentChangePassword.jhtml",
				data : data,
				dataType : 'json',
				success : function(data) {
					if(!data.success){
						showMsg(modaldiv,data.msg);
					}else{
						$(modaldiv).modal('hide');
					}
					
				}
			});
		}
		
		function jsonFromt(data) {
			var json = {};
			for (var i = 0; i < data.length; i++) {
				json[data[i].name] = data[i].value;
			}
			return json;
		}
		
		function valiDateForm(data,form,array){
			$.map(data,function(value,key){
			 	vali(value,key,form,array);
			});	
		}
		
		function vali(value,key,form,array){
			var rules  = valiInfo.rules;
			var messages  = valiInfo.messages;
			var method,rule,result;
			for ( method in rules[key] ) {
				rule = { method: method, parameters: rules[key][ method ]};
				result = methods[method](value,rule.parameters,form);
				var el = findByName(form,key);
				var text = messages[key][ method ];
				if(!result){
					var p = "<p style='font-size:14px;color:#F00'>"+text+"</p>";
					array.push(p);
					return;
				}	
			}
		}
		
		
		 var valiInfo={
				 rules:{
						password:{
								required:true,
								rangelength:[6,20]
							},
						repassword:{
								required:true,
								rangelength:[6,20],
								equalTo:"#password"
							}
						},
					messages:{
						password:{
							required:"密码未填写!",
							rangelength:"密码长度为  6-20 个字符之间"
						},
						repassword:{
							required:"确认密码未填写!",
							rangelength:"密码长度为  6-20  个字符!",
							equalTo:"两次输入密码不一致!"
						}
					}
		 	}
		
		methods= {
			required: function( value, param,form ) {
				return $.trim( value ).length > 0;
			},
			rangelength: function( value, param,form ) {
				var length =  value.length;
				return ( length >= param[ 0 ] && length <= param[ 1 ] );
			},
			equalTo: function( value, param ,form) {
				var $target = $(form).find(param);
				return value == $target.val();
			}
		}