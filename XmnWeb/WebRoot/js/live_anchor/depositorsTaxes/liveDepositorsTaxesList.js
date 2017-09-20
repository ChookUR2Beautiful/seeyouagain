var liveDepositorsTaxesList;

$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveDepositorsTaxes/manage/init.jhtml" target="right">提现税费管理</a>',
			'depositorsTaxes', true);
		
	//加载页面数据
	pageInit();
});

/**
 * 加载页面数据
 */
function pageInit(){

	liveDepositorsTaxesList = $("#liveDepositorsTaxesInfoList").page({
		url : "liveDepositorsTaxes/manage/init/list.jhtml",
		success : success,
		pageBtnNum : 10,
//		paramForm : 'searchSpecialForm',
//		param : {
//			rType : "1"
//		}
	});	
}


 
 // ******************************提现税费******************************
 /**
  * 
  * @param data
  * @param obj
  */
 function success(data, obj) {
 	var title = "提现税费列表", subtitle = "个提现税费";
 	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
 			+ title+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
 	var callbackParam = "isBackButton=true";

 	obj.find('div').eq(0).scrollTablel({
 		identifier : "id",
 		callbackParam : callbackParam,
 		data : data.content,
 		caption : captionInfo,
 		// checkable : checkable,
 		// 操作列
 		handleCols : {
 			title : "操作",// 标题
 			queryPermission : [ "xg"],// 不需要选择checkbox处理的权限
 			width : 60,// 宽度
 			// 当前列的中元素
 			cols : [ 
                   {
 						title : "修改",// 标题
 						linkInfoName : "modal",
 						linkInfo : {
 							modal : {
// 								url : "livePageHome/manage/updateAnchorVideo/init.jhtml",
 								position : "",
 								width : "auto",
 								title : "修改"
 							},
 							param : [ "id" ],
 							permission : "xg"
 						},
 					    customMethod : function(value, data){
 				            var value = "<a href='javascript:editDepositorsTaxes("+data.type+", "+data.rateType+", "+data.rate+")'>" + "修改" + "</a>";
 				            return value;
 				        }
 					}
 			]
 		},
 		cols : [ {
 			title : "钱包拓展类型",
 			name : "typeName",
 			type : "string",
 			width : 80
 			/*,
		    customMethod : function(value, data){
		    	var typeDesc;
		    	var type = data.type;
		    	
		    	switch (parseInt(type)) { //必须转成int型
		    	case 0:
		    		typeDesc = "综合收益";
		    		break;		    	
		    	case 1:
		    		typeDesc = "V客推荐";
		    		break;
		    	case 2:
		    		typeDesc = "V客红包";
		    		break;
		    	case 3:
		    		typeDesc = "壕赚VIP红包";
		    		break;
		    	case 4:
		    		typeDesc = "壕赚商户充值红包";
		    		break;
		    	case 5:
		    		typeDesc = "V客创业管理奖金";
		    		break;
		    	case 6:
		    		typeDesc = "寻蜜客签约收益";
		    		break;		    		
		    	default:
		    		typeDesc = "";
		    		break;
		    	};
		    	return typeDesc;
		    }*/
 		},{
 			title : "手续费类型",
 			name : "rateType",
 			type : "string",
 			width : 60,
		    customMethod : function(value, data){
		    	var value ;
	            var rateType = data.rateType;
	            switch (parseInt(rateType)) { //必须转成int型
		    	case 1:
		    		value = "手续费比例";
		    		break;		    	
		    	case 2:
		    		value = "固定金额";
		    		break;
	            }
	            return value;
		    } 		
 		},{
 			title : "固定金额",
 			name : "rateType",
 			type : "string",
 			width : 60,
		    customMethod : function(value, data){
		    	var value ;
	            var rateType = data.rateType;
	            switch (parseInt(rateType)) { //必须转成int型
		    	case 1:
		    		value = "-";
		    		break;		    	
		    	case 2:
		    		value = data.rate == undefined ? "-" : data.rate;
		    		break;
	            }
	            return value;
		    }
 		},{
 			title : "比例",
 			name : "rateType",
 			type : "string",
 			width : 60,
		    customMethod : function(value, data){
		    	var value ;
	            var rateType = data.rateType;
	            switch (parseInt(rateType)) { //必须转成int型
		    	case 1:
		    		value = data.rate == undefined ? "-" : data.rate*100+"%";
		    		break;		    	
		    	case 2:
		    		value = "-";
		    		break;
	            }
	            return value;
		    }
 		}]
 	}, permissions);
 }

 function editDepositorsTaxes(type, rateType, rate){
		$("#type").val(type);
		if (rateType == "1"){ //比例
//			$("input[name=rateType]:eq(0)").attr("checked",'checked');
			$("input[name='rateType'][value='1']").prop( "checked", true );
			$("#rate").val(rate*100+"%");
			
		}else {  //固定
//		    $("input[type=radio][name=rateType][value='2']").attr("checked",'checked');
		    $("input[type=radio][name=rateType][value='2']").prop("checked",'checked');
		    $("#rate").val(rate);
		}
		
		
		$('#editliveDepositorsTaxesModal').modal();
	}
 
 $("#depositorsTaxesModalSubmit").on("click",function(){
	    var url="liveDepositorsTaxes/manage/update.jhtml";
	    
	    var type = $("#type").val();
		var rateType = $("input[name='rateType']:checked").val();
		if(rateType == ""){
			showWarningWindow("warning", "请选择费率类型!", 9999);
			return;
		}
		var rate=$("#rate").val();
		if(rate == ""){
			showWarningWindow("warning", "请输入手续费!", 9999);
			return;
		}
		if (rateType == "1") {// 比例 /^(100|[1-9]?\d(\.\d\d?)?)%$/
			var reg = /^(100|[1-9]?\d)%$/ ;
			if (reg.test(rate)) {
				rate = rate.substring(0, rate.indexOf("%"));
				rate = rate == 0 ? 0 : rate/100;
			} else {
				showWarningWindow("warning", "费率类型为按比例时, 手续费为0% 到100%", 9999);
				return;
			}
	   }else {
		   var reg = /^[0-9]+$|^[0-9]+\.[0-9]{1,2}$/ ;  //  ^(([1-9]\d{0,9})|0)(\.\d{1,2})?$
		   if (!reg.test(rate)) {
				showWarningWindow("warning", "费率类型为固定金额, 填写具体金额", 9999);
				return;
		   }
	   }
		
		$.ajax({
			 url : url,
			 type : "post",
			 dataType : "json",
			 async: false,
			 data:{"type":type, "rateType":rateType, "rate":rate},
			 success : function(data) {
				 if (data.success) {
					 showSmReslutWindow(data.success, data.msg);
					 $('#editliveDepositorsTaxesModal').modal('hide');
					 liveDepositorsTaxesList.reload();;
				 }
			 }
		});
		
	});
 