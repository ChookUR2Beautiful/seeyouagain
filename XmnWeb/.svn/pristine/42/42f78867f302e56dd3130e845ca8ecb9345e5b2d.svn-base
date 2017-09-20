var areaValue;
var areaName;
var isType = $("#isType").val();
$(document).ready(function() {

	var supplierId = $('#supplierId').val();
	if(isType == "add"){
		inserTitle(' > <span><a href="supplier/manager/add/init.jhtml?" target="right">添加运费模板</a>','add',false);
	}else{
		inserTitle(' > <span><a href="supplier/manager/edit/init.jhtml?supplierId='+supplierId+'" target="right">编辑运费模板</a>','edit',false);
	}
	
	/**
	 * 返回
	 */
	 $("#backId").on("click",function(){
		 muBack();
	 });
	
	 initView();
	
});



function addTemplate(){
	
	if($("input[name='templateName']").val()=="" || $("input[name='templateName']").val() == undefined){
		showSmReslutWindow(false,"模板名称未填写");
		return;
	}
	
	if($(".select-right ul li").length != 0){
		showSmReslutWindow(false,"部分地区尚未设置运费");
		return;
	}
	
	if(isType =="add"){
	 var url = 'transportFee/manage/add.jhtml';
	}else{
	 var url = 'transportFee/manage/edit.jhtml';
	}
	areaStr();
	
	var data = $('#postTemplateForm').serializeArray();
	data = jsonFromt(data);
	showSmConfirmWindow(function (){
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				setTimeout(function(){
        			location.href =contextPath+'/transportFee/manage/init.jhtml';
        		}, 1000);
		    }			
			showSmReslutWindow(data.success, data.msg);
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
	 },"确定保存吗？");
}

function areaStr(){
	var option = $("#supplierId option:selected").text();
	
	if($("#supplierId option:selected")  !=''){
		
		$(":input[name='supplierName']").val(option);
	}
	
	var length = $(".select-left ul li").length;
	for(var i =0;i<length;i++){
		 var val=$(".select-left ul li").eq(i).attr("value");
		 var name=$(".select-left ul li").eq(i).text();
		 if(i ==0){
			 areaValue=val;
			 areaName=name;
		 }else{
			 areaValue = areaValue+","+val;
			 areaName = areaName+","+name;
		 }
		 
	}
	$("#deliveryNo").val(areaValue);
	$("#deliveryCity").val(areaName);
}

$("#conditions").on("click","a",function(){
	var str=$(this).parent().siblings(".city-name").text().replace("，",",").split(",");
	var arr=$(this).parent().siblings(".city-name").attr("value").replace("，",",").split(",");
	for(var i=0;i<str.length;i++){
		$(".select-right ul").append("<li value='"+arr[i]+"'>"+str[i]+"</li>");
	}
	
	$(this).parent().parent().remove();
	if($("#conditions tr").length ==1){
		$("#conditions").append("<tr id = 'zanw'><td colspan='6' align='center'>暂无数据</td></tr>");
	}
})


function add(){
	
			areaStr();
			if(!validatorParam()){
				return;
			}
			
			var deliveryCity = $("#deliveryCity").val();
			var deliveryNo = $("#deliveryNo").val();
			var firstNums = $(":input[name='firstNums']").val();
			var firstItem = $(":input[name='firstItem']").val();
			var continueNums = $(":input[name='continueNums']").val();
			var continueItem = $(":input[name='continueItem']").val();
			$("#zanw").remove();
			$("#conditions").append("<tr><td class='city-name' value='"+deliveryNo+"'>"+deliveryCity+"</td><td>"+firstNums+"</td><td>"+firstItem+"</td><td>"+continueNums+"</td><td>"+continueItem+"</td><td><a href='javascript:;' class='deleteLine'>删除</td>");
			$("#conditions").append("<input type='hidden' name='conditions["+i+"].deliveryCity' value='"+deliveryCity+"'>"
					+"<input type='hidden' name='conditions["+i+"].firstNums' value='"+firstNums+"'>"
					+"<input type='hidden' name='conditions["+i+"].firstItem' value='"+firstItem+"'>"
					+"<input type='hidden' name='conditions["+i+"].deliveryNo' value='"+deliveryNo+"'>"
					+"<input type='hidden' name='conditions["+i+"].continueNums' value='"+continueNums+"'>"
					+"<input type='hidden' name='conditions["+i+"].continueItem' value='"+continueItem+"'></tr>")
			
			$('.select-left li').remove();
			$(":input[name='firstNums']").val("");
			$(":input[name='firstItem']").val("");
			$(":input[name='continueNums']").val("");
			$(":input[name='continueItem']").val("");
			
			$("input[name='method']").attr("disabled",true);
			i++;
		}
	
/**
 * 验证参数
 */
	function validatorParam(){
		if($("input[name='firstNums']").val()=="" || $("input[name='firstNums']").val() == undefined){
			showSmReslutWindow(false,"首件数量未填写");
			return false
		}
		if($("input[name='firstItem']").val()=="" || $("input[name='firstItem']").val() == undefined){
			showSmReslutWindow(false,"首件价格未填写");
			return false
		}
		if($("input[name='continueNums']").val()=="" || $("input[name='continueNums']").val() == undefined){
			showSmReslutWindow(false,"续件数量未填写");
			return false
		}
		if($("input[name='continueItem']").val()=="" || $("input[name='continueItem']").val() == undefined){
			showSmReslutWindow(false,"续件价格未填写");
			return false
		}
		if($("input[name='deliveryCity']").val()=="" || $("input[name='deliveryCity']").val() == undefined){
			showSmReslutWindow(false,"配送城市未填写");
			return false
		}
		return true;
	}
	
	function goBack(){
		history.back();
	}

	function initView(){
    	if(isType =="add"){
    		$("input[name='type']").val("001");
			}else{
				var type = $("input[name='type']").val();
				if(type=="002"){
				$("input[name='method']").attr("checked",false);
				$("input[name='method']").eq(1).click();
				}
				$("input[name='method']").attr("disabled",true);
				var str=$("input[name='cityStr']").val();
				var list=$(".select-right ul li");
				var arr=str.substring(1, str.length-1).split(",");
				for(var i=0;i<arr.length;i++){
					for(var j=0;j<list.length;j++){
						if(list.eq(j).text()==arr[i].replace(" ","")){
							list.eq(j).remove();
						}
					}
				}
			}
    	
        $(".address-select ul").on("click","li",function () {
           $(this).addClass("active").siblings().removeClass("active");
        });
        $(".shift-left-btn").on("click",function () {
            if($(".select-right li.active").html()!= undefined){
                $(".select-left ul").append("<li value='"+$('.select-right li.active').val()+"'>"+$('.select-right li.active').html()+"</li>");
                $('.select-right li.active').remove();
            }
        });
        $(".shift-right-btn").on("click",function () {
            if($(".select-left li.active").html()!=undefined){
                $(".select-right ul").append("<li value='"+$('.select-left li.active').val()+"'>"+$('.select-left li.active').html()+"</li>");
                $('.select-left li.active').remove();
            }
        });
        $(".box input[name='method']").on("click",function () {
        	$(this).addClass("active").siblings().removeClass("active");
            if($(this).attr("id")=="piece"){
                $(".tab-box").find("p.first").html("<label>首件</label><input type='text' placeholder='1件' name='firstNums'/><input type='text' placeholder='8.00' name='firstItem'/>");
                $(".tab-box").find("p.second").html("<label>续件</label><input type='text' placeholder='1件' name='continueNums'/><input type='text' placeholder='8.00' name='continueItem'/>");
                $("#conditions th").eq(1).text("首件（个）");
                $("#conditions th").eq(3).text("续件（个）");
                $("input[name='type']").val("001");
            }else{
            	$(".tab-box").find("p.first").html("<label>首重</label><input type='text' placeholder='1.0kg' name='firstNums'/><input type='text' placeholder='8.00' name='firstItem'/>");
                $(".tab-box").find("p.second").html("<label>续重</label><input type='text' placeholder='1.0kg' name='continueNums'/><input type='text' placeholder='8.00' name='continueItem'/>");
                $("#conditions th").eq(1).text("首重（kg）");
                $("#conditions th").eq(3).text("续重（kg）");
                $("input[name='type']").val("002");
            }
        });
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


var formId=["postTemplateForm"];

var formHandle={
		"postTemplateForm":addTemplate
}

function formSubmit(form){
	return formHandle[form];
}





