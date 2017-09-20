var ISTYPE
$(document).ready(function() {
	ISTYPE = $("#isType").val();

	var supplierId = $('#supplierId').val();
	if(ISTYPE == "add"){
		inserTitle(' > <span><a href="supplier/manager/add/init.jhtml?" target="right">添加供应商信息</a>','add',false);
	}else{
		inserTitle(' > <span><a href="supplier/manager/edit/init.jhtml?supplierId='+supplierId+'" target="right">编辑供应商信息</a>','edit',false);
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
	
});



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
			url = "supplier/manager/add.jhtml";
		}else{
			url = "supplier/manager/edit.jhtml";
		}
	
		var data = $('#multipShopForm').serializeArray();
			data = jsonFromt(data);
		$.post(url,data,function(result){
			if(result.success){
				showSmReslutWindow(result.success, result.msg);
				var url = contextPath + '/supplier/manager/init.jhtml';
				
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
	 
	
	for(var i=0;i<formId.length;i++){
		validate(formId[i],valiinfo[formId[i]],formSubmit(formId[i]));
	}
};	

/**
 * 验证方法
 */
var valiinfo={"multipShopForm":{rules:
      {
		name:{
			required:true, 
			rangelength:[2,20]
		},
		address:{
			 required:true, 
			maxlength:300
		}, 
		contacts:{
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
		postTemplateId:{
			required:function(){
				if($("#type").val()==1){
					return true;
				}else{
					return false;
				}
			}
		},
		mobile:{
			required:true, 
			digits:true,
			phoneNumber:true/*,
			checkPhoneid:true,
			checkAccount:true */
		},
	},
	messages:{
		name:{
			 required:"单位名名未填写", 
			rangelength:"单位名名长度为  2-20  个字符"
		},
		address:{
			 required:"地址未填写", 
			 maxlength:"地址最多300个字符"
		},
		contacts:{
			required:"负责人姓名未填写", 
			rangelength:"负责人姓名2-20个字符"
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
		postTemplateId:{
			required:"供应商必须选择运费模板"
		},
		mobile:{
			required:"联系人号码未填写", 
			digits:"请输入整数",
			phoneNumber:"请输入正确的手机号！"/*,
			checkPhoneid:"联系人手机已存在！",
			checkAccount:"你的手机号已经被其他人注册为登录帐号，请更换，谢谢！"*/
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





