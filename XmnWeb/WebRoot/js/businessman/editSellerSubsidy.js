var ISTYPE;
ISTYPE = $("#isType").val();
$(document).ready(function() {
	//把服务员选择框设置为不可编辑
	$("#phoneid").attr("readonly", true);
	var idsubsidy = $('#idsubsidy').val();
	if(ISTYPE == "add"){
		inserTitle(' > <span><a href="businessman/sellerSubsidy/add/init.jhtml?idsubsidy='+idsubsidy+'&isType=add" target="right">添加商家服务员推广管理</a>','addSellerSubsidy',false);
	}else{
		inserTitle(' > <span><a href="businessman/sellerSubsidy/update/init.jhtml?idsubsidy='+idsubsidy+'" target="right">编辑商家服务员推广管理</a>','editSellerSubsidy',false);
	}
	/**
	 * 取消
	 */
	 $("#backId").on("click",function(){
		 muBack();
	 });
	 //选择商家
	 selectShop();
	//验证
	 initValidator();
	//初始化联动菜单
//	uniteArea();
	//初始化联动菜单
	initMultipSelect();

});

/**
 * 挑选商家
 */
function selectShop(){
	searchChosen = $("#sellername").searchChosen({
		 url : "businessman/sellerSubsidy/init/chosen.jhtml?isChose=true&tabFlag=tab2",
		 initUrl : "businessman/multipShop/init/findSellerByFatherid.jhtml?fatherid="+ ($('#sellerid').val()),
		 initId :"sellerid",
		 initTitle :"sellername"
	 });
}

/*
function uniteArea(){
	//总店商家
	$("#sellerid").chosenObject({
		hideValue : "sellerid",
		showValue : "title",
		url : "businessman/sellerSubsidy/BargainSellerInit.jhtml",
		isChosen:true,
		isCode:false
	});
	$("#sellerid").on("change",function(){
		var value =  $(this).find("option:selected").text();
		$("input[name='sellername']").val(value);
	});
}*/

//function uniteArea(){
//	//总店商家
//	$("#sellerid").chosenObject({
//		hideValue : "sellerid",
//		showValue : "sellername",
//		url : "businessman/sellerSubsidy/getSpreadSeller.jhtml",
//		isChosen:true,
//		isCode:true
//	});
//	$("#sellerid").on("change",function(){
//		var value =  $(this).find("option:selected").text().replace(/[[1-9]\d*]/,"");
//		$("input[name='sellername']").val(value);
//		//选中之后将商家id传到后台查询该商店的所有服务员
//		var sellerid = $("#sellerid").val();
//		
//		selectWaiter(sellerid);
//	});
//}
/**
 * 初始化区域
 */
function initMultipSelect(){
	$("#areaSelect").areaLd({
		//url : "businessman/sellerSubsidy/init/ld.jhtml",
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
 * 商家服务员推广添加或修改
 */
var UpdateSavaSellerSpread = function (){
		var url;
		if(ISTYPE == "add"){
			url = "businessman/sellerSubsidy/spread/add.jhtml";
		}else{
			url = "businessman/sellerSubsidy/updateSellerSpread.jhtml";
		}
		/*alert(checkSelect("sellerSubsidyForm","sellerid_chosen",["sellername"],true));
		if(!checkSelect("sellerSubsidyForm","sellerid_chosen",["sellername"],true)){
			//submitDataError($("#sellerid"),"商家名称未选择!");
			 showSmReslutWindow(false, "商家名称未选择！");
			  return;
		}*/
		if(!checkids()){//商家名称校验
			return false;
		}
		var data = $('#sellerSpreadForm').serializeArray();
		data = jsonFromt(data);
		$.post(url,data,function(result){
			if(result.success){
				showSmReslutWindow(result.success, result.msg);
				setTimeout(function(){
					muBack();
        		}, 1000);
			}else{
				window.messager.warning(result.msg);
			}
		},"json");
};
function checkids(){//商家名称校验
	  if($("#sellerid").val().length!=0){
		  return true;
	  }
	  submitDataError($("#checkids"),"商家名称不能为空!");
	  return false;
	};

	/**
	 * 返回
	 */
	function muBack(){
		var url = contextPath + '/businessman/sellerSubsidy/init.jhtml';
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
var valiinfo={"sellerSpreadForm":{rules:
      {
		sellerid:{
			required:true
		},
		url:{
			required:true
		},
		tgStatus:{
			required:true
		},
		orderCount:{
			required:true,
			digits:true
		},
		phoneid:{
			required:true, 
			remote:function(){
				if(ISTYPE=="add"){
					return {
					url:"businessman/sellerSubsidy/spread/add/vailstaff.jhtml",//后台处理程序
                    type:"post",                        //数据发送方式
                    dataType:"json", //接受数据格式  
                    data:{phoneid:function(){return $("#phoneid").val();}}
					}
				}
				return false;
			}
			},
		brokerage:{
			required:true,
		},
		subsidyamount:{
			required:true,
			min:0,
			max:99999,
			
		}

	},
	messages:{
		sellerid:{
			required:"商家名称未选择！"
		},
		url:{
			required:"推广url地址未填写！"
		},
		tgStatus:{
			required:"推广状态未选择！"
		},
		orderCount:{
			required:"已推广订单数未填写！",
			digits:"请输入正整数!"
		},
		phoneid:{
			required:"服务员帐号未选择！",
			remote : "该服务员已添加过！"
//			digits:"请输入整数！",
//			phoneNumber:"请输入正确的手机号码！"
		},
		brokerage:{
			required:"已获得佣金总额未填写!", 
		},
		subsidyamount:{				
			required:"应发店员补贴未填写!",
			min:"店员补贴不能小于0",	
			max:"店员补贴不能大于99999"
		}
		
	}
}};

var formId=["sellerSpreadForm"];
var formHandle={
		"sellerSpreadForm":UpdateSavaSellerSpread
};

function formSubmit(form){
	
	return formHandle[form];
}






