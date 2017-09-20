//账号信息
var html;
var includeTradeIndex;
var picNum = $("#sellerPicsNum").val() | 0;
var VIEWTYPE = $('#viewType').val();
$(document).ready(function() {
	
	uploadImg();
	limitedDate({form:"#sellerForm",startDateName:"svalidity",endDateName:"evalidity",format : 'yyyy-mm-dd'});
	
	var sellerid = $('#selleridid').val();
	
	if(VIEWTYPE == "editSellerPending"){
		inserTitle(' > 编辑商家信息','editsellerInfo',false);
	}else{
		inserTitle(' > 商家管理 > <span><a href="businessman/seller/init.jhtml" target="right">商家信息管理</a><span> >编辑商家信息','editsellerInfo',true);
	}
	var flag =  false;
	if($("select[name='status']").val() == 3){
		flag = true;
	}
	
	//折上折说明
	var textarea = '<input placeholder="全单折扣 0-100" class="form-control" name="agioAgioNum"> <textarea placeholder="折上折说明……" class="form-control" name="agioInstruction"></textarea>';
	$("input[name='agioAgio']").on("change",function(){
		if($("#agioInstructionDiv").children().length>0){
			textarea = $("#agioInstructionDiv").html();
		}
		if($(this).val()==1){
			$("#agioInstructionDiv").html(textarea);
			$("#agioInstructionDiv").find("textarea").on("change",function(){
				console.log($(this).text());console.log($(this).val());
				$(this).text($(this).val());
				textarea = $("#agioInstructionDiv").html();
			});
		}
		if($(this).val()==0){
			$("#agioInstructionDiv").empty();
		}
	});
	$("#agioInstructionDiv").find("textarea").on("change",function(){
		console.log($(this).text());console.log($(this).val());
		$(this).text($(this).val());
		textarea = $("#agioInstructionDiv").html();
	});
	
	//直播分账开关
	$("input[name='liveLedgerOperating']").on("change",function(){
		if($(this).val()==0){
			$(".liveLedgerTable").hide();
		}
		if($(this).val()==1){
			$(".liveLedgerTable").show();
		}
	});
	
	var operation = $("input[name='liveLedgerOperating']:checked").val();
	if(operation==0){
		$(".liveLedgerTable").hide();
	}
	if(operation==1){
		$(".liveLedgerTable").show();
	}
	
	//
	var includeTrade = $("#includeTrade").html();
	$("#includeTrade").html(includeTrade).show();
	//
	
	//初始化联动菜单
	uniteArea();
	
	//
	includeTradeIndex = includeTradeSize();
	//
	
	//获取折扣信息
	getAgio();
	
	//初始化验证方法
	initValidator();
	
	/** 
	 * add by lifeng 2016年6月7日 13:51
	 * 初始化对应的寻蜜客信息下拉列表
	 */
	initXmerInfo();
	//
	
	$("input[name='give']").on("click",function(){
		if($(this).val() ==1){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			showWarningWindow("warning","温馨提示：总部帮忙签约时【所属业务员】必须为合作商管理员！");
		}
	});
	limitedDate({form:"#sellerDetailedForm",startDateName:"extensionSet.dateStart0",endDateName:"extensionSet.dateEnd0"});
	limitedDate({form:"#sellerDetailedForm",startDateName:"extensionSet.dateStart1",endDateName:"extensionSet.dateEnd1"});
	limitedDate({form:"#sellerDetailedForm",startDateName:"extensionSet.dateStart2",endDateName:"extensionSet.dateEnd2"});
});

function canRemoveIncludeTrade() {
	return includeTradeSize() > 1;
}

function canAddIncludeTrade() {
	return includeTradeSize() < 10;
}

function includeTradeSize() {
	return $("#includeTrade > .col-md-5").find(".input-group").length;
}

/**
 * 添加包含所选行业
 * 
 * @param object
 */
function addIncludeTrade(object) {
	if (canAddIncludeTrade()) {
		$(object).parents(".input-group").last().after(
				$(".includeTradeSelectTemp").html()).next().find(
				".includeTradeSelect").tradeLd({
			showConfig : [ {
				name : "traderRefs[" + includeTradeIndex + "].category",
				tipTitle : "请选择",
				width : "50%"
			}, {
				name : "traderRefs[" + includeTradeIndex + "].genre",
				tipTitle : "请选择",
				width : "50%"
			} ]
		});
		includeTradeIndex++;
	}
}
/**
 * 删除包含所选行业
 * 
 * @param object
 */
function removeIncludeTrade(object) {
	if (canRemoveIncludeTrade()) {
		$(object).parents(".input-group").remove();
	}
}

//验证重复元素，有重复返回false；否则返回true
function checkArrray(array){
	var nary = array.sort();
	var flag = true;
	for(var i = 0; i < nary.length - 1; i++){
	   if (nary[i] == nary[i+1]){
	      // alert("重复内容：" + nary[i]);
		   flag = false;
		   break;
	    }
	}
	return flag;
}

/**
 * 商家修改
 */
var UpdateSavaSeller =function (){
		var data = $('#sellerForm').serializeArray();
		data = jsonFromt(data);
		//如果是商家待审核页面则调用商家待审核方法
		if(VIEWTYPE == "editSellerPending"){
			url = "businessman/sellerPending/update/updateSeller.jhtml";
		}else{
			url = "businessman/seller/update/Seller.jhtml";
		}
		
		//判断所选择的经营行业是否重复
		var obj = $("[name$=genre]");
		var arrObj = new Array();
		for(var i = 0; i < obj.size(); i++){
			arrObj.push($(obj[i]).val());
		}
		if(!checkArrray(arrObj)){
			dataError("#checkMsg","经营行业不能重复");
			return false;
		}
		
		//判断是否总部帮忙签约 
	   /* if(data.give == 1){
	    	//是否选择业务员
	    	if(data.staffid){
	    		//检查业务员是否为商家管理员
	    		var flag = true;
	    		$.ajax({
	    			type : 'post',
	    			url : "businessman/seller/init/checkStaff.jhtml" + "?t=" + Math.random(),
	    			data : {staffid:data.staffid},
	    			dataType : 'json',
	    			async: false,
    				success : function(data) {
    					if(data <= 0){
    						showWarningWindow("warning","温馨提示：总部帮忙签约时【所属业务员】必须为合作商管理员！");
    						flag =  false;
    					}
    				}
	    		});
	    		if(flag == false){
	    			return flag;
	    		}
	    	}else{
	    		return; 
	    	}
	    }*/
		if(!valiImgData('#sellerForm',data)){
			if(!(checkData(data.sdate1,":input[name='sdate1']","不能为空") && checkData(data.sdate2,":input[name='sdate2']","不能为空"))){
				return false;
			}
			/*if((data.svalidity && data.evalidity) && !databj(data.svalidity,data.evalidity)){
				dataError("#sjaddevalidity","结束日期应晚于开始日期");
				return false;
			}else{*/
			//datasuccess("#sjaddevalidity");
			$.post(url,data,function(result){
				if(result.success){
					showSmReslutWindow(result.success, result.msg);
					$("input[name='liveLedger.id']").val(result.data.liveLedgerId);
						
					
				}else{
					window.messager.warning(result.msg);
				}
			},"json");
			//}
		}	
}


/**
 * 保存商家经纬度信息
 */
var sellerLandmarkUpdate = function (){
		var data = $('#sellerLandmarkFromId').serializeArray();
		//如果是商家待审核页面则调用商家待审核方法
		if(VIEWTYPE == "editSellerPending"){
			url = "businessman/sellerPending/update/updateSellerLandmark.jhtml";
		}else{
			url = "businessman/seller/update/SellerLandmark.jhtml";
		}
		showSmConfirmWindow(function(){
			$.post(url,data,function(result){
				if(result.success){
					showSmReslutWindow(result.success, result.msg);
				}else{
					window.messager.warning(result.msg);
				}
			},"json");
		},"确认已使用的是高德地图经纬度？")
}



/**
 * 保存商家详细信息
 */
var sellerDetailedUpdate = function (){ 
		debugger;
		var data = $('#sellerDetailedForm').serializeArray();
		if(vilidate()==0){
			return ;
		}
		var WIFIPassword=$("#sellerDetailedForm").find("input[name='sellerDetailed.WIFIPassword']").val();
        if(/[\u4E00-\u9FA5]/g.test(WIFIPassword)){
        	showSmReslutWindow(true, "wifi密码不能出现中文");
        	return;
        }
		//如果是商家待审核页面则调用商家待审核方法
		if(VIEWTYPE == "editSellerPending"){
			url = "businessman/sellerPending/update/updateSellerDetailed.jhtml";
		}else{
			url = "businessman/seller/update/SellerDetailed.jhtml";
		}
		
		$.post(url,data,function(result){
		
			if(result.success){
				

				if(undefined!=result.data){
				$("#id0").val(result.data[0]);
				$("#id1").val(result.data[1]);
				$("#id2").val(result.data[2]);
				var datemsg=result.data[4];
				} 
				console.info(undefined!=datemsg);
				if(undefined!=datemsg){//多人同时操作提示
					showSmReslutWindow(result.success, datemsg);
				}
				if(undefined!=result.msg){
					showSmReslutWindow(result.success, result.msg);
				}
			}else{
				window.messager.warning(result.msg);
			}
		},"json");	
}


	$("#AnalysisOfBusiness").click(function(){//商家等级计算
		
		var category=$("select[name=category]").val();//行业
		var genre=$("select[name=genre]").val();//行业
	    var baseagio=$("input[name=baseagio]").val();//折扣
	    var shopArea= $("#sellerDetailedForm").find("input[name='sellerDetailed.shopArea']").val();
	    var staffNumber=$("#sellerDetailedForm").find("input[name='sellerDetailed.staffNumber']").val();
	    var consume=$("#sellerDetailedForm").find("input[name='sellerDetailed.consume']").val();
	    var monthlyTurnover=$("#sellerDetailedForm").find("input[name='sellerDetailed.monthlyTurnover']").val();
	    var isChain=$("#isChain").val();
	    var  sellerGradeStr;
	   $("#sellerDetailedForm").find("input[type=text]").each(function(){
		   var name = $(this).attr("name");
		   if("sellerDetailed.sellerGradeStr"===name){
			   sellerGradeStr =  this;
			   return;
		   }
	   });
       if(isChain==1&&shopArea>=200&&staffNumber>=20&&consume>=30&&monthlyTurnover>=300000){
	   		$("#sellerGrade").val("1");
	   		$(sellerGradeStr).val("A");
	   		return;
        }
		if(shopArea>=100&&staffNumber>=10&&consume>=30&&monthlyTurnover>=150000){
		  if(category==1||category==2||(category==5&&genre==65)){
			 $("#sellerGrade").val("2");
			 $(sellerGradeStr).val("B+");
			 return;
		   }
		  $("#sellerGrade").val("3");
		  $(sellerGradeStr).val("B");
		  return;
	 }
	 if(shopArea>=20&&staffNumber>=5&&consume>=20&&monthlyTurnover>=50000){
		  if(category==1){
			 $("#sellerGrade").val("4");
			$(sellerGradeStr).val("C+");
			 return ;
		  }
	 }
	 $("#sellerGrade").val("5");
	 $(sellerGradeStr).val("C");
	 return ; 
	 
});
function databj(sdate,enddate){
	if(sdate && enddate){
		var s = dateParse(sdate).getTime(); 
		var e = dateParse(enddate).getTime();
		return e>s;
	}
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

function vilidate(){
	  var dateStart0=getObjectText("dateStart0");
	  var dateEnd0=getObjectText("dateEnd0");
	  var dateStart1=getObjectText("dateStart1");
	  var dateEnd1=getObjectText("dateEnd1");
	  var dateStart2=getObjectText("dateStart2");
	  var dateEnd2=getObjectText("dateEnd2")
	  var isAddMarketingList0=getObjectRadio("isAddMarketingList0");
	  var isAddMarketingList1=getObjectRadio("isAddMarketingList1");
	  var isAddMarketingList2=getObjectRadio("isAddMarketingList2");
	  if(isAddMarketingList0==1){
		  if(dateStart0.length>0&&dateEnd0.length>0){ 
		  }else{
			  showSmReslutWindow(true, "请填写摇一摇信息推广时间");
			  return 0;
		  }
	  }
	  if(isAddMarketingList1==1){
		  if(dateStart1.length>0&&dateEnd1.length>0){
			  
		  }else{
			  showSmReslutWindow(true, "请填写订单信息推广时间");
			  return 0;
		  }
	  }
	  if(isAddMarketingList2==1){
		  if(dateStart2.length>0&&dateEnd2.length>0){ 
		  }else{
			  showSmReslutWindow(true, "请填写列表信息推广时间");
			  return 0;
		  }
	  }  
}

function  getObjectText(obj){
	 var theVaule;
	$("#sellerDetailedForm").find("input[type=text]").each(function(){
		   var name = $(this).attr("name");
		   if(("extensionSet."+obj)===name){
			   theVaule =  $(this).val();  
		   }
	  });
	return theVaule;	
}
function  getObjectRadio(obj){
	 var theVaule;
	$("#sellerDetailedForm").find("input[type=radio]:checked").each(function(){
		   var name = $(this).attr("name");
		   if(("extensionSet."+obj)===name){
			   theVaule =  $(this).val();  
		   }
	  });
	return theVaule;	
}

/*
 * (add by lf 2016年6月6日 19:47:10)
 * 加载列表数据
 */
function initXmerInfo(){
	//寻蜜客
	$("#uid").chosenObject({
		hideValue : "uid",
		showValue : "phoneid",
		url : "businessman/seller/initXmer.jhtml",
		isChosen:true,
		isCode:true,
		width:"100%",
		defaultValue:"--未选择寻蜜客--"//默认的显示值
	});
	$("#uid").on("change",function(){
		var value =  $(this).find("option:selected").text();
		$("input[name='xmerPhone']").val(value);
	});
}
