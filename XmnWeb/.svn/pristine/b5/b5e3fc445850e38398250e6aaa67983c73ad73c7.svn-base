/*//账号信息
var html;
var picNum = $("#sellerPicsNum").val() | 0;										
$(document).ready(function() {
	
	uploadImg();
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
	
	//初始化账号信息
	accountInfo();
	//保存商家详细信息
	//sellerDetailedUpdate();
	var sellerid = $('#selleridid').val();
	inserTitle(' > <span><a href="businessman/sellerPending/update/init.jhtml?sellerid='+sellerid+'" target="right">编辑商家信息</a>','editsellerInfo',false);
	
	//初始化联动菜单
	uniteArea();
	
	//获取折扣信息
	getAgio();
	
	//初始化验证方法
	initValidator();
	
});



*//**
 * 保存商家申请信息
 *//*
var applyInfo = function (){
		var data = $('#sellerInfoFromId').serializeArray();
		data = jsonFromt(data);
			$.ajax({
		        type: "POST",
		        url: "businessman/sellerPending/update/updateInfo.jhtml",
		        data: data,
		        dataType: "json",
		        success: function(result){
		        	if(result){
		        		showSmReslutWindow(result.success, data.msg);
						//window.messager.success(result.msg);
					}else{
						window.messager.warning(result.msg);
					}
		         }
		    });
}


*//**
 * 保存商家详细信息
 *//*
var sellerDetailedUpdate = function (){
		var data = $('#sellerDetailedForm').serializeArray();
		$.post("businessman/sellerPending/update/updateSellerDetailed.jhtml",data,function(result){
			if(result.success){
				showSmReslutWindow(result.success, result.msg);
				//window.messager.success(result.msg);
			}else{
				window.messager.warning(result.msg);
			}
		},"json");
	
}


*//**
 * 保存商家经纬度信息
 *//*
var sellerLandmarkUpdate = function (){
		var data = $('#sellerLandmarkFromId').serializeArray();
		$.post("businessman/sellerPending/update/updateSellerLandmark.jhtml",data,function(result){
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

*//**
 * ==================================================================================================================================================
 * 初始化账号信息
 *//*
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
	//==================================================================================================================================================
}

*//**
 * 账号信息提交
 *//*
var submitAccount = function(){
	var requestValue=formValue($("#submitAccount"));
	//提交表单
	formAjax(requestValue);
}



*//**
 * 商家修改，日期比较
 *//*
var UpdateSavaSeller =function (){
		var data = $('#sellerForm').serializeArray();
		data = jsonFromt(data);
		if(!valiImgData('#sellerForm',data)){
			if(!(checkData(data.sdate1,":input[name='sdate1']","不能为空") && checkData(data.sdate2,":input[name='sdate2']","不能为空"))){
				return false;
			}
			if((data.svalidity && data.evalidity) && !databj(data.svalidity,data.evalidity)){
				dataError("#sjaddevalidity","结束日期应晚于开始日期");
				return false;
			}else{
				datasuccess("#sjaddevalidity");
				$.post("businessman/sellerPending/update/updateSeller.jhtml",data,function(result){
					if(result.success){
						showSmReslutWindow(result.success, result.msg);
						//window.messager.success(result.msg);
					}else{
						window.messager.warning(result.msg);
					}
				},"json");
			}
		}
}

function databj(sdate,enddate){
	console.info("ggg");
	if(sdate && enddate){
		console.info("sssssss");
		var s = dateParse(sdate).getTime(); 
		var e = dateParse(enddate).getTime();
		return e>s;
	}
}






*//**
 * 转换from表单
 *//*
function jsonFromt(data){
	var json = {};
	for(var i=0; i<data.length; i++){
		json[data[i].name] = data[i].value;
	}
	return json;
}


*//**
 * ===========================================================================================================================
 * 账号信息js
 *//*
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


var formAjax=function(info){
	
	$.ajax({
			contentType : 'application/json',
			type :info.method,
			url: info.url,
			data:info.data
 		}).done(function(data){
 			if(data.data){
 				var password = $('input[name=password]').val()
 				$('input[name=oldpassword]').each(function(){
 					$(this).val(password);
 				});
 				
 				$('input[name=aid]').each(function(){
 					$(this).val(data.data);
 				});
 			}
 			showSmReslutWindow(data.success, data.msg);
	}).fail(function(data){
		window.messager.warning(data.msg);
	});
}

*//**
 * 刷新账号信息
 *//*
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
			html.push('<input type="text" class="form-control"  name="iostoken"  value = "'+ (undefined == data[i].iostoken ? "" : data[i].iostoken) +'">');  
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
// ===========================================================================================================================
*/