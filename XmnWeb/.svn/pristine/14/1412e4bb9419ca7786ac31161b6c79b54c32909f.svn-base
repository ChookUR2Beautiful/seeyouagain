var id;
$(document).ready(function() {
	if($('#id').length==0){
		inserTitle(' > <span>添加联盟店信息 </span>','allianceShopInfo',false);
	}else{
		id =$('#id').val();
		inserTitle(' > <span><a href="businessman/allianceShop/update/init.jhtml?id='+id+'" target="right">编辑联盟店信息</a>','allianceShopInfo',false);
	}
	/**
	 * 返回
	 */
	 $("#backId").on("click",function(){
		 muBack();
	 });
	
	//初始化联动菜单
	uniteArea();
	//添加校验
	initValidator();
	
	splitShop();
	
});


/**
 * 挑选子店
 */

function splitShop(){
	var idParam= "&id="+(id||"");
	searchChosen = $("#object").searchChosen({
		 url : "businessman/allianceShop/init/chosen.jhtml?isChose=true"+idParam,
		 initUrl : "businessman/allianceShop/init/findSellerByAllianceShopId.jhtml?id="+ (id||""),
		 initId :"sellerid",
		 initTitle :"sellername"
	 });
}

/**
 * 联动数据
 */
function uniteArea(){
	$("#areaSelect").areaLd({
		commonChange : function($dom, level){
			if(level != 2 || !$dom.val()){
				$("#zoneid").empty().append('<option value="">请先选择区域再选择商圈</option>');
			}
		},
		lastChange : function($dom){
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
			}
		}
	});
}





/**
 * 商家修改
 */
var UpdateSavaSeller =function (){
		var url  = [$('#allianceShopForm').attr("action"),".jhtml"].join("");
		var data = $('#allianceShopForm').serializeArray();
		data = jsonFromt(data);
		$.post(url,data,function(result){
			if(result.success){
				showSmReslutWindow(result.success, result.msg);
				var url = contextPath + '/businessman/allianceShop/init.jhtml';
				
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
		var url = contextPath + '/businessman/allianceShop/init.jhtml';
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
	  var result = true;
		if($('#oldAccount').val() == value){
			 return result;
	 	}
        // 设置同步
        $.ajaxSetup({
            async: false
        });
        var param = {
        	account: value
        };
        $.post("businessman/allianceShop/init/checkAccount.jhtml", param, function(data){
            result = data;
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
	 
	
	for(var i=0;i<formId.length;i++){
		validate(formId[i],valiinfo[formId[i]],formSubmit(formId[i]));
	}
};	

/**
 * 验证方法
 */
var valiinfo={"allianceShopForm":{
	rules:{
		allianceName:{
			required:true, 
			rangelength:[1,100]
		},
		phoneid:{
			required:true, 
			digits:true,
			phoneNumber:true
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
		zoneid:{
			required:true
		},
		address:{
			required:true, 
			maxlength:200
		},
		description:{
			maxlength:500
		},
		account:{
			required:true, 
			rangelength:[2,20],
			checkAccount:true 
		},
		password:{
			required:true, 
			rangelength:[6,16]
		},
		nname:{
			required:true,
			rangelength:[2,50]
		}, 
		fullname:{
			required:true, 
			rangelength:[2,20]
		},
		phone:{
			required:true, 
			digits:true,
			phoneNumber:true
		},
		status:{
			required:true
		}
	},
	messages:{
		allianceName:{
			required:"联盟店名称未填写", 
			rangelength:"联盟店名称有效长度为  100  个字符"
		},
		phoneid:{
			required:"联系人号码未填写", 
			digits:"请输入正整数",
			phoneNumber:"请输入正确的号码！"
		},
		province:{
			required:"省未选择!"
		},
		city:{
			required:"市未选择!"
		},
		area:{
			required:"区未选择!"
		},
		zoneid:{
			required:"区未选择!"
		},
		address:{
			required:"详细地址未填写!", 
			maxlength:"详细地址有效长度为  200 个字符"
		},
		description:{
			maxlength:"描述有效长度为 500 个字符"
		},
		account:{
			required:"账号未填写!",
			checkAccount:"帐号已存在!" ,
			rangelength:"账号长度有效长度为  6-20个字符!"
		},
		password:{
			required:"密码未填写!",
			rangelength:"密码长度有效长度为6-16个字符!"
		},
		nname:{	
			required:"昵称未填写!",
			rangelength:"账号昵称有效长度为  2-20个字符!"
		}, 
		fullname:{
			required:"真实姓名未填写!",
			rangelength:"真实姓名有效长度2-20个字符!"
		},
		phone:{
			required:"手机号未填写",
			digits:"请输入正整数",
			phoneNumber:" 请输入正确的手机号！"
		},
		status:{
			required:"状态未选择!"	
		}
	}
}};

var formId=["allianceShopForm"];
var formHandle={
		"allianceShopForm":UpdateSavaSeller
}

function formSubmit(form){
	return formHandle[form];
}





