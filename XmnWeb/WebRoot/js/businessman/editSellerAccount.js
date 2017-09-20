$(document).ready(function() {
	//修改账号
	var chagneAccount = true;
	//编辑时默认给密码赋值
	if($('#isType').val() !=  'add'){
		$("input[name=password]").val("......");
	}
	$("#account").change(function(){
		initAccount();
		chagneAccount = true;
	});
	/*
	 * 绑定现有会员账号，
	 */
	$(".binding").click(function() {
		$('#sm_confirm_window').modal('hide');
		updateAccountText(true);
	});
	/*
	 * 不绑定现有会员账号 
	 */
	$(".unbinding").click(function() {
		$('#sm_confirm_window').modal('hide');
		updateAccountText(false);
	});
	 /*
 	验证帐号唯一性，异步检验
 */
 $.validator.addMethod("checkAccount", function(value, element,param) {
	// var $("#tableinfo").find();
	   var result = false;
        // 设置同步
        $.ajaxSetup({
            async: false
        });
        var param = {
        	account: value
        };
        $.post("businessman/sellerAccount/init/checkAccount.jhtml", param, function(data){
            result = data.checkAccount;
            if(data.seller != null){
            	existSellerName=data.seller.sellerid+"  "+data.seller.sellername;
            	param = existSellerName;
            }
            console.info("返回的结果是："+data);
        });
        // 恢复异步
        $.ajaxSetup({
            async: true
        });
        return result;    
	}, "帐号已存在");
	
	 //绑定账号，添加寻蜜鸟会员账号与现有会员账号是否有冲突
 	$.validator.addMethod("isConflict", function(value, element) {
			if(!chagneAccount){
				return true;
			}
	        // 设置同步
	        $.ajaxSetup({
	            async: false
	        });
	        var param = {
	        	account: value
	        };
	        $.post("businessman/sellerAccount/init/isConflict.jhtml", param, function(data){
	            if(data.isConflict){
	            	updateAccountText(false);
	            	chagneAccount = false;
	            	$('#usr_nname').text(data.urs.nname);
	            	$('#usr_regtime').text((new Date(data.urs.regtime))  
                            .format("yy年MM月dd日 hh:mm"));
	            	var statusStr = ""; 
	            	if(data.urs.status==null){
	            		statusStr ="";
	            	}else if(data.urs.status==1){
	            		statusStr ="正常";
	            	}else if(data.urs.status==2){
	            		statusStr ="锁定";
	            	}else if(data.urs.status==3){
	            		statusStr ="注销";
	            	}else if(data.urs.status==4){
	            		statusStr ="黑名单";
	            	}else{
	            		statusStr ="";
	            	}
	            	$('#usr_status').text(statusStr);
	            	$('#wallet_amount').text(data.wallet.amount);
	            	$('#wallet_balance').text(data.wallet.balance);
	            	$('#wallet_commision').text(data.wallet.commision);
	            	$('#wallet_zbalance').text(data.wallet.zbalance);
	            	$('#wallet_integral').text(data.wallet.integral);
	            	
	            	$('#sm_confirm_window').modal();
	            }
	        });
	        // 恢复异步
	        $.ajaxSetup({
	            async: true
	        });
	        return true;    
		}, "该帐号已存在寻蜜鸟会员");
	 
	/**
	  * 校验必须为手机号
	 */
	 $.validator.addMethod("phoneNumber", function(value, element) {
		    var result = true;
	        if(value.length!=11)
	        {
	        	result =  false;
	        }else{
		        var phoneNum=/^\d{11}$/;
		        if(!phoneNum.test(value)){
		        	result = false;
		        }
	        }
	        return result;    
	}, "请输入正确的手机号码！");
	
	
	 //校验帐号规则
	 $.validator.addMethod("accountRule", function(value, element) {
		 if(value){
			 var reg=/^[\w\.\@]{0,20}$/;
			 if(reg.test(value)){
				 return true;
			 }
		 }
		}, "帐号只能为字母数字下划线或者@");
	 
	validate("editSellerAccountForm",{
		rules:{
			account:{
				required:true,
				phoneNumber:true,
				remote:{
					type : 'post',
					url : 'businessman/sellerAccount/init/checkAccount.jhtml' + '?t=' + Math.random(),
					dataType : 'json',
					data:{
						account: function() {
				            return $("#account").val();
				        }
					}
				},
				isConflict:true,
			},
			nname:{
				rangelength:[2,20]
			},
			fullname:{
				rangelength:[2,20]
			},
			password:{
				required:true,
				rangelength:[6,16]
			},
			levelpass:{
				required:true,
				rangelength:[6,100]
			},
			type:{
				required:true
			},
			remarks:{
				
				rangelength:[1,100]
			}
		},
		messages:{
			account:{
				required:"登录账号未填写!",
				phoneNumber:"请输入正确的手机号码！",
				isConflict:"该账号已有寻蜜鸟会员账号!"
			},
			nname:{
				rangelength:"账号昵称长度为  2-20个字符!"
			},
			fullname:{
				rangelength:"账号姓名长度2-20个字符!"
			},
			password:{
				required:"密码未填写!",
				rangelength:"密码长度为6-16个字符!"
			},
			levelpass:{
				required:"二级密码不能为空!",
				rangelength:"二级密码长度为6-100个字符!"
			},
			type:{
				required:"请选择一个账号类型"
			},
			remarks:{
				
				rangelength:"最大长度为100"
			}
		}},formAjax);
}); 
/*
 * 更新新增账号与现有寻蜜鸟会员账号的绑定提示信息
 */
function updateAccountText(isBinding){
	if(isBinding){
		$('#isBinding').val(true);
		$('#conflictMsg').text("该账号已有寻蜜鸟账号，已绑定!(需保存后生效)");
		$('#ensure').attr("disabled",false);
	}else{
		$('#isBinding').val(false);
		$('#conflictMsg').text("该账号已有寻蜜鸟账号，未绑定,不允许保存!");
		$('#ensure').attr("disabled",true);
	}
	$('.conflictTip').show();
	//$('#reloadSelect').css('display','block'); 
}
/*
 * 初始化默认新增账号与现有寻蜜鸟会员账号的绑定提示信息，即默认是不冲突的
 */
function initAccount(){
	$('#conflictMsg').text("");
	$('#isBinding').val(false);
	$('#ensure').attr("disabled",false);
	//$('#reloadSelect').css('display','none'); 
	$('.conflictTip').hide();
}

function formAjax(){
	var url ;
	if($('#isType').val() ==  'add'){
		url = 'businessman/sellerAccount/add.jhtml' + '?t=' + Math.random();
	}else{
		url = 'businessman/sellerAccount/update.jhtml' + '?t=' + Math.random();
	}
	$("input[name=phone]").val($("#account").val());
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt($('#editSellerAccountForm').serializeArray()),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			 $('#triggerModal').modal('hide');
			if (data.success) {
				if($('#isType').val() ==  'add'){
					sellerAccountList.reset();
				}else{
					sellerAccountList.reload();
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