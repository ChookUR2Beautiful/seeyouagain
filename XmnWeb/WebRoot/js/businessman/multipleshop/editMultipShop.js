var ISTYPE
$(document).ready(function() {
	ISTYPE = $("#isType").val();
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'	
	});
	
	var sellerid = $('#selleridid').val();
	if(ISTYPE == "add"){
		inserTitle(' > <span><a href="businessman/multipShop/add/init.jhtml?sellerid='+sellerid+'&isType=add" target="right">添加连锁店信息</a>','addMultipShop',false);
	}else{
		inserTitle(' > <span><a href="businessman/multipShop/update/init.jhtml?sellerid='+sellerid+'" target="right">编辑连锁店信息</a>','editMultipShop',false);
	}
	
	/**
	 * 返回
	 */
	 $("#backId").on("click",function(){
		 muBack();
	 });
	
	//初始化联动菜单
	initMultipSelect();
	//添加校验
	initValidator();
	
	//挑选子店
	splitShop();
	
	$("#sellerLogoId").uploadImg({
		urlId : "url",
		showImg : $('#url').val()
	});
});

/**
 * 挑选子店
 */
function splitShop(){
	searchChosen = $("#object").searchChosen({
		 url : "businessman/multipShop/init/chosen.jhtml?isChose=true",
		 initUrl : "businessman/multipShop/init/findSellerByFatherid.jhtml?fatherid="+ ($('#selleridid').val()),
		 initId :"sellerid",
		 initTitle :"sellername"
	 });
}

/**
 * 初始化区域
 */
function initMultipSelect(){
	$("#areaSelect").areaLd({
		isChosen : false,
		lastChange : function($dom){
			if($dom.val()){
				if($dom.val()){
					$.ajax({
						url : "common/business/BusinessList.jhtml?areaId="+($dom.val()),
						dataType:"json",
						method : "get", 
						success:function(data){
							var businessV = $("#zoneid").attr("initValue");
							$("#zoneid").empty().append('<option value="">请选择商圈</option>');
							for(var i=0;i<data.length;i++){
								$("#zoneid").append('<option value="'+data[i].bid+'" '+(data[i].bid==businessV?'selected':'')+'>'+data[i].title+'</option>');
							}
						}
					});
				}else{
					$("#jointid").empty().append('<option value="">请先选择区域</option>').attr("readonly", false);
					$("#jointid_chosen").hide();
					$("#jointid").show();
				}
			}
		}
	});
}



/**
 * 商家修改
 */
var UpdateSavaSeller =function (){
		var url;
		if(ISTYPE == "add"){
			url = "businessman/multipShop/add.jhtml";
		}else{
			url = "businessman/multipShop/update.jhtml";
		}
	
		var data = $('#multipShopForm').serializeArray();
			data = jsonFromt(data);
		$.post(url,data,function(result){
			if(result.success){
				showSmReslutWindow(result.success, result.msg);
				var url = contextPath + '/businessman/multipShop/init.jhtml';
				
				setTimeout(function(){
        			location.href =url;
        		}, 1000);
			}else{
				window.messager.warning(result.msg);
			}
		},"json");
}

	/**
	 * 返回
	 */
	function muBack(){
		var url = contextPath + '/businessman/multipShop/init.jhtml';
			location.href =url;
	}

/**
 * 转换from表单
 */
function jsonFromt(data){
	var json = {};
	for(var i=0; i<data.length; i++){
		json[data[i].name] = data[i].value;
	}
	return json;
}



/**========================================================================================================
 * 初始化验证方法
 */
function initValidator(){
	 
	 /*
	 	验证帐号唯一性，异步检验
	 */
	 $.validator.addMethod("checkAccount", function(value, element) {
		// var $("#tableinfo").find();
		   var result = false;
	        // 设置同步
	        $.ajaxSetup({
	            async: false
	        });
	        var param = {
	        	account: value
	        };
	        $.post("businessman/multipShop/checkMultipShopAccount.jhtml", param, function(data){
	        	result = data;
	        	if($('#oldAccount').val() == value){
	        		result = true;
	        	}
	        });
	        // 恢复异步
	        $.ajaxSetup({
	            async: true
	        });
	        return result;    
		}, "帐号已存在");
	 
	 
	/**
	  * 校验必须为手机号
	 */
	 $.validator.addMethod("phoneNumber", function(value, element) {
		    var result = true;
	        if(value.length!=11)
	        {
	        	result =  false;
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
	 
	 /**
	  * 校验电话格式
	 */
	 $.validator.addMethod("checkTel", function(value, element) {
		 if(value){
			 var reg = /^[0-9\-]{6,20}$/; 
			 if(reg.test(value)){
				 return true;
			 }
		 }
	}, "请输入正确的电话号码！");
	 
	
	for(var i=0;i<formId.length;i++){
		validate(formId[i],valiinfo[formId[i]],formSubmit(formId[i]));
	}
};	

/**
 * 验证方法
 */
var valiinfo={"multipShopForm":{rules:
      {
		sellername:{
			required:true, 
			rangelength:[2,20]
		},
		address:{
			 required:true, 
			maxlength:300
		}, 
		fullname:{
			required:true, 
			rangelength:[2,20]
		},
		province:{
			required:true
		},
		city:{
			required:true
		},
		area:{
			required:true
		},
		phoneid:{
			required:true, 
			digits:true,
			phoneNumber:true/*,
			checkPhoneid:true,
			checkAccount:true */
		},
		tel:{
			required:true, 
			digits:true,
			rangelength:[6,20],
			checkTel:true
		},
		account:{
			required:true, 
			rangelength:[6,20],
			//number:true,
			//phoneNumber:true,
			checkAccount:true,
			accountRule:true
		},
		password:{
			 required:true, 
			rangelength:[6,16]
		},
		nname:{				
			rangelength:[2,50]
		},
		accountName:{
			rangelength:[2,20]
		},
		levelpass:{
			required:true, 
			rangelength:[6,100] 
		},
	},
	messages:{
		sellername:{
			 required:"用户名未填写", 
			rangelength:"用户名长度为  2-20  个字符"
		},
		address:{
			 required:"地址未填写", 
			 maxlength:"地址最多300个字符"
		},
		fullname:{
			required:"法人姓名未填写", 
			rangelength:"法人姓名2-20个字符"
		},
		province:{
			required:"省未填写"
		},
		city:{
			required:"市未填写"
		},
		area:{
			required:"区未填写"
		},
		phoneid:{
			required:"联系人号码未填写", 
			digits:"请输入整数",
			phoneNumber:"请输入正确的手机号！"/*,
			checkPhoneid:"联系人手机已存在！",
			checkAccount:"你的手机号已经被其他人注册为登录帐号，请更换，谢谢！"*/
		},
		tel:{
			required:"联系人号码未填写", 
			digits:"请输入整数",
			rangelength:"电话的长度为8-20的数字",
			checkTel:"无效的值"
		},
		account:{
			required:"账号未填写", 
			rangelength:"账号昵称长度为6-20个字符",
			//number:"帐号必须为手机号！",
			//phoneNumber:"帐号必须为手机号！",
			checkAccount:"帐号已存在!" ,
			accountRule:"帐号只能为字母数字下划线或者@"
		},
		password:{
			required:"密码未填写", 
			rangelength:"密码长度为6-16个字符"
		},
		nname:{				
			rangelength:"账号昵称长度为2-50个字符"
		},
		accountName:{				
			rangelength:"账号姓名长度为2-20个字符"
		},
		levelpass:{
			required:"二级密码未填写", 
			rangelength:"二级密码长度为6-100个字符"
		},
	}
}};

var formId=["multipShopForm"];
var formHandle={
		"multipShopForm":UpdateSavaSeller
}

function formSubmit(form){
	return formHandle[form];
}





