//账号信息
var html;
var includeTradeIndex;
var picNum = $("#sellerPicsNum").val() | 0;
$(document).ready(function() {
	
	limitedDate({form:"#sellerForm",startDateName:"svalidity",endDateName:"evalidity",format : 'yyyy-mm-dd'});
	//折上折说明
	var textarea = '<input placeholder="全单折扣 0-100" class="form-control" name="agioAgioNum"><textarea placeholder="折上折说明……" class="form-control" name="agioInstruction"></textarea>';
	$("input[name='agioAgio']").on("change",function(){
		if($("#agioInstructionDiv").children().length>0){
			textarea = $("#agioInstructionDiv").html();
		}
		if($(this).val()==1){
			console.log(textarea);
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
		console.info("切换");
		if($(this).val()==0){
			console.info("关闭");
			$(".liveLedgerTable").hide();
		}
		if($(this).val()==1){
			console.info("开启");
			$(".liveLedgerTable").show();
		}
	});
	
	//
	var includeTrade = $("#includeTrade").html();
	$("#includeTrade").html(includeTrade).show();
	//
	
	//初始化联动菜单
	uniteArea();
	
	//
	includeTradeIndex = includeTradeSize();
	//
	
	
	//初始化图片
	uploadImg();
	//初始化账号信息
	accountInfo();
	
	//获取折扣信息
	getAgio();
	
	//初始化验证
	initValidator();
	
	/**
	 * add by lifeng 2016年6月7日 13:51
	 * 初始化对应的寻蜜客信息下拉列表
	 */
	initXmerInfo();
	//
	
	//加载title
	inserTitle(' > 添加商家信息','addsellerInfo',false);
	
	$("input[name='give']").on("click",function(){
		if($(this).val() ==1){
			showWarningWindow("warning","温馨提示：总部帮忙签约时【所属业务员】必须为合作商管理员！");
		}
	});
	
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
		// console.log("添加包含所选行业");
		//var categoryName = "includeTrade[" + includeTradeIndex + "].category";
		//var selleridName = "includeTrade[" + includeTradeIndex + "].sellerid";
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
		// console.log("删除包含所选行业");
		$(object).parents(".input-group").remove();
	}
}

/**
 * 初始化密码
 */
//选择控制商家总店id
$("input[name='account']").change(function(){
	var value = $(this).val();	
	if(value){
		$("input[name='password']").val(value.substr(value.length-6,value.length));
	}
});

//选择控制商家总店id
$("input[name='iszd']").change(function(){
	var name = $(this).val();	
	
	if(name==1){			
		$("#sellerForm").find("select[name=fatherid]").show();
		$("#sellerForm").find("#zdbt").show();
	}else if(name==0){
		$("#sellerForm").find("select[name=fatherid]").hide();
		$("#sellerForm").find("#zdbt").hide();
	}
});

/**
 * 初始化账号信息
 */
function accountInfo(){
	var $clone =  $("#tableinfo td").first().clone();
	reset($clone);
	html = ["<tr> ","<td>",$clone.html(),"</td> ","</tr>"].join("");
	//$(".removebtn").remove();
	$("#addAccountInfo").on("click",function(){
		var $info=$("#tableinfo");
		var len = $info.find("table").length;
		if(len==10){
			$("#sysmsg").text("最多只能添加10条账号信息");
			$(".msg-model").modal('show');
			return false
		};
		$info.append(html);
	});
	
	$("#tableinfo").on("click",".removebtn",function(){
		var len = $("#tableinfo").find("table").length;
		if(len==1){
			$("#sysmsg").text("必须留1条账号信息");
			$(".msg-model").modal('show');
			return false
		};
		var rtr = $(this).parents("tr")[1];
		$(rtr).remove();
	});
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
 * 商家添加，日期比较
 */
var UpdateSavaSeller =function (){
		var data = $('#sellerForm').serializeArray();
		data = jsonFromt(data);
		
		//判断所选择的经营行业是否重复
		var obj = $("[name$=genre]"); //获取以genre结尾的参数
		var arrObj = new Array();
		for(var i = 0; i < obj.size(); i++){
			arrObj.push($(obj[i]).val());
		}
		if(!checkArrray(arrObj)){
			dataError("#checkMsg","经营行业不能重复");
			return false;
		}
		
		//如果是总部帮忙签约则所属业务员必须为商家管理员
	    /*if(data.give == 1){
	    	if(data.staffid){
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
	    	}
	    }*/
		
		if(!valiImgData('#sellerForm',data)){
			data.isType = $("#isType").val();
			if(!(checkData(data.sdate1,":input[name='sdate1']","不能为空") && checkData(data.sdate2,":input[name='sdate2']","不能为空"))){
				return false;
			}
			
			if((data.svalidity && data.evalidity) && !databj(data.svalidity,data.evalidity)){
				dataError("#sjaddevalidity","结束日期应晚于开始日期");
				return false;
			}else{ 
				datasuccess("#sjaddevalidity"); 
				$.post("businessman/seller/add/Seller.jhtml",data,function(result){
					if(result.success){
						showSmReslutWindow(result.success, result.msg);
						var callbackParam="&isBackButton=true&callbackParam="+getParam("callbackParam",window.location.search.substr(1));
						var url = contextPath + "/businessman/seller/update/init.jhtml?sellerid="+ result.data.sellerid+callbackParam;
						
						setTimeout(function(){
		        			location.href =url;
		        		}, 1000);
					}else{
						window.messager.warning(result.msg);
					}
				},"json");
			}
		}
}

/**
 * 账号信息提交
 */
var submitAccount = function(){
	if(!$('#selleridid').val())
	{
		showWarningWindow("warning","请先添加商户基本信息！");
		return;
	}
	var requestValue=formValue($("#submitAccount"));
	//提交表单
	formAjax(requestValue);
}

var formAjax=function(info){
	
	$.ajax({
			contentType : 'application/json',
			type :info.method,
			url: info.url,
			data:info.data
 		}).done(function(data){
 			if(data.data){
 				var password = $('input[name=password]').val();
 				$('input[name=oldpassword]').val(password);
 				$('#aid').val(data.data);
 				
 				var oldAccount = $('input[name=account]').val();
 				$('input[name=oldAccount]').val(oldAccount);
 			}
 			showSmReslutWindow(data.success, data.msg);
	}).fail(function(data){
		window.messager.warning(data.msg);
	});
}

/**
 * 保存商家详细信息
 */
var sellerDetailedUpdate = function (){
	if(!$('#selleridid').val())
	{
		showWarningWindow("warning","请先添加商户基本信息！");
		return;
	}
		var data = $('#sellerDetailedForm').serializeArray();
		$.post("businessman/seller/add/SellerDetailed.jhtml",data,function(result){
			if(result.success){
				showSmReslutWindow(result.success, result.msg);
			}else{
				window.messager.warning(result.msg);
			}
		},"json");
	
}

/**
 * 保存商家经纬度信息
 */
var sellerLandmarkUpdate = function (){
	if(!$('#selleridid').val())
	{
		showWarningWindow("warning","请先添加商户基本信息！");
		return;
	}
		var data = $('#sellerLandmarkFromId').serializeArray();
		$.post("businessman/seller/add/SellerLandmark.jhtml",data,function(result){
			if(result.success){
				if(result.data){
				  $('#lid').val(result.data);
				}
				showSmReslutWindow(result.success, result.msg);
			}else{
				window.messager.warning(result.msg);
			}
		},"json");
	
}




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



/**
 * 账号信息js
 */
var serializeJSON = function(form) {
    var jsonObj = {};
    var array = new Array();
    var info = $(form).serializeArray();
    $.each(info, function(index,obj) {
        if (jsonObj[obj.name]) {
            array.push(jsonObj);
            jsonObj = {}; 
        }
        jsonObj[this.name] = obj.value || '';
    });
    if(!$.isEmptyObject(jsonObj))array.push(jsonObj);
    return JSON.stringify(array);
}

var formValue=function(form){
	var url= [$(form).attr("action"),".jhtml"].join("");
	var method= $(form).attr("method");
	var data = serializeJSON(form);
	var info={
		dataType:"json",
		url:url,
		method:method,
		data:data
	}
	return info;
}



/**
 * 刷新账号信息
 */
function reloadAccount(data){
	$('#tableinfo').empty();
	var html = [];
	for (var i = 0; i < data.length; i++) {
		html.push('<tr>');
		html.push('<td>');
		html.push('<table class="table " style="text-align: center;">');
		
		html.push('<tr>');
		html.push('<td style="width:20%;">');
		html.push('<button class="btn btn-warning removebtn" type="button" "><i class="icon-remove"></i></button>');
	    html.push('</td>');
		html.push('<td style="width:10%;">');
		html.push('<h5>账号:</h5>'); 
		html.push('</td>');
		html.push('<td style="width:30%;">');
		html.push('<input type="hidden" name = "aid" id = "aid" value = "'+ (undefined == data[i].aid ? "" : data[i].aid) +'">');
		html.push('<input type="hidden" id="selleridid" name="sellerid" value="'+ (undefined == data[i].sellerid ? "" : data[i].sellerid) +'"/>');
		html.push('<input type="text" class="form-control" name="account"  value = "'+ (undefined == data[i].account ? "" : data[i].account) +'" disabled="disabled">');
		html.push('</td>');
	
		html.push('<td style="width:10%;">');
		html.push('<h5>账号昵称:</h5>');
		html.push('</td>');
		html.push('<td style="width:30%;">');
		html.push('<input type="text" class="form-control"  name="nname"  value = "'+ (undefined == data[i].nname ? "" : data[i].nname) +'">');
		html.push('</td>');									
		html.push('</tr>');											
			
		html.push('<tr>');
		html.push('<td style="width:20%;">');
		html.push('</td>');
		html.push('<td style="width:10%;">');
		html.push('<h5>账号姓名:</h5> ');
		html.push('</td>');
		html.push('<td style="width:30%;">');
		html.push('<input type="text" class="form-control"  name="fullname"  value = "'+ (undefined == data[i].fullname ? "" : data[i].fullname) +'">'); 
		html.push('</td>');
		html.push('<td style="width:10%;">');
		html.push('<h5>登录密码:</h5> ');
		html.push('</td>');
		html.push('<td style="width:30%;">');
		html.push('<input type="hidden" class="form-control"  name="oldpassword"  value = "'+ (undefined == data[i].password ? "" : data[i].password) +'">');
		html.push('<input type="password" class="form-control"  name="password"  value = "'+ (undefined == data[i].password ? "" : data[i].password) +'">');
		html.push('</td>');		
		html.push('</tr>');
		
			html.push('<tr>');
		    html.push('<td style="width:20%;">');
			html.push('</td>');
			html.push('<td style="width:10%;">');
			html.push('<h5>二级密码:</h5> ');
			html.push('</td>');
			html.push('<td style="width:30%;">');
			html.push(' <input type="hidden" class="form-control"  name="oldlevelpass"  value = "'+ (undefined == data[i].levelpass ? "" : data[i].levelpass) +'">'); 
			html.push('<input type="password" class="form-control"  name="levelpass"   value = "'+ (undefined == data[i].levelpass ? "" : data[i].levelpass) +'">');  
			html.push('</td>');
			html.push('<td style="width:10%;">');
			html.push('<h5>手机号:</h5> ');
			html.push('</td>');
			html.push('<td style="width:30%;">');
			html.push('<input type="text" class="form-control"  name="phone"  value = "'+ (undefined == data[i].phone ? "" : data[i].phone) +'">');  
			html.push('</td>');					
			html.push('</tr>');
		
			html.push('<tr>');
			html.push('<td style="width:20%;">');
			html.push('</td>');
			html.push('<td style="width:10%;">');
			html.push('<h5>ios令牌:</h5> ');
			html.push('</td>');
			html.push('<td style="width:30%;">');
			html.push('<input type="text" class="form-control"  name="iostoken" value = "'+ (undefined == data[i].iostoken ? "" : data[i].iostoken) +'">');  
			html.push('</td>');
			html.push('<td style="width:10%;"></td>');
			html.push('<td style="width:30%;"></td>');
			html.push('</tr>');
		
	   html.push('</table>');
	   html.push('</td>');
	  html.push('</tr>');	
	}
	$('#tableinfo').html(html.join(''));
}

var reset=function(formInfo){
	$(':input',formInfo).not(':button, :submit, :reset').removeAttr('value').removeAttr('checked').removeAttr('selected').removeAttr('disabled');
	
}

/*
 * (add by lf 2016年6月6日 19:47:10)
 * 加载寻蜜客信息列表
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
