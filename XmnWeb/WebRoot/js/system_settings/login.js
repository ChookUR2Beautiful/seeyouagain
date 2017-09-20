$(function(){
 		$("#Kaptcha").click(function(){
  			reloadCode();
  			return false;
  		});
 		validate();
 		msg();
 	});
 	
 	var validate = function(){
		$("form").validate({
			rules:{
				uname:{
					required:true,
					rangelength:[2,20]
				},
				upassword:{
					required:true,
					rangelength:[6,20]
				},
				ucaptcha:{
					required:true,
					rangelength:[4,4]
				}
			},
			messages:{
				uname:{
					required:"用户名未填写!",
					rangelength:$.validator.format("用户名长度为  2~20  个字符之间")
				},
				upassword:{
					required:"密码未填写!",
					rangelength:$.validator.format("用户密码长度为  6~20  个字符!")
				},
				ucaptcha:{
					required:"验证码未填写!",
					rangelength:$.validator.format("验证码错误!")
				}
			},
			onkeyup:false,
			 onfocusout:false,
			onclick : false, 
			errorContainer: "#msg",
			errorLabelContainer: $("#msg"),
			wrapper: "li",
			errorClass: "help-inline",
			errorElement: "span",
			showErrors:function(errorMap,errorList) {
		        $("#msg").empty();
		       
		        //$("#msg").hide();
				this.defaultShowErrors();
			},
			submitHandler : function(form) {  
					var requestValue=formValue(form);
		 			//提交表单
		 			formAjax(requestValue);
		 			return false;
			 }  
		});	
	}
 	
 	var formValue=function(form){
		var url= [$(form).attr("action"),".jhtml"].join("");
		var method= $(form).attr("method");
		var data = $( form ).serialize() ;
		var info={
			url:url,
			method:method,
			data:data
		}
		return info;
	}
 	
	var formAjax=function(info){
		$.ajax({
				type :info.method,
				url: info.url,
				data:info.data
	 		}).done(function(data){
	 			responseHandel(data);
		}).fail(function(data){
			//msg('操作失败');
		});
	}
	
	var responseHandel=function(data){
		if(data.success){
			location.href = data.data;
		}else{
			reloadCode();
		}
		msgshow(data.msg);
		
	}
	
	function msgshow(msg){
		$("#msg").text(msg);
		$("#msg").show();
	}
  	function msg(){
  		var msg =getUrlParam('msg');
  		if(!msg || msg==null){
  			return;
  		}
  		msgshow(msg);
  	}
  	//获取url中的参数
    function getUrlParam(name) {
    	//window.location.href=decodeURI(window.location.href); 
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return decodeURI(r[2]); return null; //返回参数值
    }
	
	function reloadCode() {
        document.getElementById("Kaptcha").src = "Kaptcha.jpg?time=" + Math.random();
    }
		