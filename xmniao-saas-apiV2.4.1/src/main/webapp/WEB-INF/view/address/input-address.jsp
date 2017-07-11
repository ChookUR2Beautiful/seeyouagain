<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>地址管理</title>
<meta name="renderer" content="webkit">
<meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/component.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css" />
<style type="text/css">
	.address-input-wrap input {
	width: 200px
}
</style>
</head>

<body class="padd-fill-tb bg-color-01">
	<input id="addressId" type="hidden" />
	<div class="container-wrap">
		<div class="fill-list-module">
			<div class="list-wrap">
				<div class="list-item">
					<span class="item-input-wrap address-input-wrap"><input name="nname" id="nname"
						type="text" placeholder="需与身份证姓名一致" /> </span><span class="item-name">收货人</span>

				</div>

				<div class="list-item" style="width: 70%; float: right;">
					<div class="activeity-radio-input" style="text-align: right">
						<span class="activeity-radio-item limit-fsize"> <input
							name="sex" type="radio" class="reset-radio-input" id="sex-man2"
							value="0" /> <label class="reset-radio" for="sex-man2"> <span
								class="item-radio-discount"> <strong>先生</strong>
							</span>
						</label>
						</span> <span class="activeity-radio-item limit-fsize"> <input
							name="sex" type="radio" class="reset-radio-input" id="sex-woman2"
							value="1" /> <label class="reset-radio" for="sex-woman2">
								<span class="item-radio-discount"> <strong>小姐</strong>
							</span>
						</label>
						</span>
					</div>
				</div>
			</div>

			<div class="list-wrap">
				<div class="list-item">
					<span class="item-input-wrap address-input-wrap"><input name="phone" id="phone"
						type="tel" placeholder="配送人联系你的电话" /></span><span class="item-name">联系电话</span>
				</div>
			</div>
			<div class="list-wrap">
				<div class="list-item">
					<span class="item-input-wrap address-input-wrap"><input id="addressArea"
						type="text" placeholder="请选择所在区域" /></span><span class="item-name">所在区域</span>
				</div>
			</div>
			<div class="list-wrap">
				<div class="list-item">
					<span class="item-input-wrap address-input-wrap"><input name="address"
						id="address" type="text" placeholder="请尽量详细地址，不少于5个字" /></span><span
						class="item-name">详细地址</span>
				</div>
			</div>
		</div>
		<div class="list-address-wrap">
			<div class="activeity-radio-input address-item">
				<span class="activeity-radio-item"> <input name="isDefault"
					type="checkbox" class="reset-checkbox-input"  id="address-check2" />
					<label class="reset-radio" for="address-check2"> <span
						class="item-radio-discount"> <strong>是否设置为默认地址</strong>
					</span>
					</label>
				</span>
			</div>
		</div>
	</div>
	<div class="floor-module">
		<div class="floor-links-col-2 links-type2" id="floor">
			<a href="javascript:;" class="floor-links links-type2 links-disabled"
				id="btn-submit">保存</a>
		</div>
	</div>
	<div class="maskLayer">
		<div class="maskLayer-wrap">
			<div class="addressLayer-contain" id="address-place">
				<div class="addressLayer-item" id="address-province" data-value="0">
					<span class="item-dect">省</span><sub class="icon-wrap"></sub>
				</div>
				<div class="addressLayer-item" id="address-city">
					<span class="item-dect">市</span><sub class="icon-wrap"></sub>
				</div>
				<div class="addressLayer-item" id="address-area">
					<span class="item-dect">区</span><sub class="icon-wrap"></sub>
				</div>
				<div class="addressLayer-btn">
					<span id="address-cancel" class="address-btn-item">取消</span> <span
						id="address-confirm" class="address-btn-item">确定</span>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/component.js"></script>
	<script type="text/javascript" src="${ctx}/js/address/addressmodule.js"></script>
	<script type="text/javascript">
	var basePath = "${ctx}";
	var sellerAddress=eval(${sellerAddress});
	$("input[type=text]").attr("autocomplete","off");
	$(function(){
		inputInit();
		checkStepOneStaus();
	    $("input[type='text']").bind('keyup',function(){
	        checkStepOneStaus();
	    });
    
    function inputInit(){
    	if(sellerAddress){
    		this.floorItme="floor-item ";
    		$("#nname").val(sellerAddress.nname);
            $("input[name=sex][value="+sellerAddress.sex+"]").attr("checked","checked");
        	$("#phone").val(sellerAddress.phone);
        	var addressAreaText='';
        	if(sellerAddress.province==sellerAddress.city){
        		addressAreaText=sellerAddress.city+(sellerAddress.areaName==null?"":sellerAddress.areaName);
        	}else{
        		addressAreaText=sellerAddress.province+sellerAddress.city+(sellerAddress.areaName==null?"":sellerAddress.areaName);
        	}
        	$("#addressArea").val(addressAreaText);
        	$("#address").val(sellerAddress.address);
        	if(sellerAddress.isDefault){
	        	$("input[name=isDefault]").attr("checked","checked");
        	}
        	$("#address-province").attr("data-value",sellerAddress.provinceId);
        	$("#addressId").val(sellerAddress.id);
        	$("#address-city").attr("data-value",sellerAddress.cityId);
       		$("#address-area").attr("data-value",sellerAddress.areaId);
       		$("#btn-submit").attr("class","floor-item btn-gray");
       		$("#floor").prepend('<a href="javascript:;" class="floor-item btn-white" id="btn-found">删除</a>');
    	}else{
    		this.floorItme="floor-links links-type2 links-disabled";
    	}
    	
    }
    
    function checkStepOneStaus(){
        /*名称*/
        var infoName = $("#nname").val();
        var radioCheck = $("input[name=sex][checked=checked]");
        var infoIphone =$("#phone").val();
        var infoArea = $("#addressArea").val();
        var infoDetail = $("#address").val();
        var provinceId = $("#address-province").attr("data-value");
        var cityId = $("#address-city").attr("data-value");
        var areaId = $("#address-area").attr("data-value");
        if(!(infoName&&infoIphone&&infoArea&&infoDetail!=''&&provinceId&&cityId&&areaId)){
        	if(this.floorItme=="floor-links links-type2 links-disabled"){
	            $('#btn-submit').addClass("links-disabled");
        	}else{
        		$('#btn-submit').attr("class",this.floorItme+"btn-gray");
        	}
        }else{
        	if(this.floorItme=="floor-links links-type2 links-disabled"){
	            $('#btn-submit').removeClass("links-disabled");
        	}else{
        		$('#btn-submit').attr("class",this.floorItme+"btn-blue");
        	}
        }
    }
    
    var key = 1;
    /*保存*/
    $('#btn-submit').bind('click',function(){
        /*名称*/
        if(!key){
        	return;
        }
        key = 0;
        	window.setTimeout(function(){
        		key=1;
        	},1000); 
        	var id=$("#addressId").val();
        var nname =$("#nname").val();
        var sex = $("input[name=sex]:checked").val();
        var phone =$("#phone").val();
        var addressArea = $("#addressArea").val();
        var address = $("#address").val();
        var isDefault =  $("#address-check2").is(':checked');
        var provinceId = $("#address-province").attr("data-value");
        var cityId = $("#address-city").attr("data-value");
        var areaId = $("#address-area").attr("data-value");
       	isDefault= isDefault?1:0;
        if(nname.length<=1){
            Tips.show('请填写名称');
        }else if(sex!=0&&sex!=1){
            Tips.show('请选择性别');
        }else if(!phone){
            Tips.show('请填写手机号码');
        }else if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
            Tips.show('请正确填写手机号码');
        }else if(address.length<5){
            Tips.show('详细地址长度必须大于5个字');
        }else if(!(provinceId&&cityId)){
        	Tips.show('请填写所在区域');
        }else{
        	var url=basePath+"/h5/address/save";
           	if(id){
           		url+="?id="+id;
           	}
			var sellerAddress = {'nname':nname,'sex':sex,'phone':phone,'address':address,'isDefault':isDefault,"provinceId":provinceId,"cityId":cityId,"areaId":areaId}
            $.ajax({
                method: "POST",
                url:url,
                data:sellerAddress,
                dataType:"json",
                success: function (data) {
                	if(data.state==0){
	                    window.location.href=basePath + "/h5/address?sessionToken="+data.result;
                	}else if(data.state==500){
                		Tips.show('请填写所在区域');
                	}
                },
                error:function(data){
	                   Tips.show('系统繁忙,请稍后再试');
                }

            })
        }

    });
    /*删除*/
    $("#btn-found").bind('click',function(){
        var dialogObject = {};
        dialogObject.title='提示';
        dialogObject.content = '是否删除当前收货地址';
        dialogObject.secondBtnEvent=function(){
        	$.ajax({
        	type:"post",
        	url: basePath + '/h5/address/remove',
        	data:"id="+$("#addressId").val(),
        	dataType:"json",
        	success:function(data){
        		window.location=basePath + "/h5/address?sessionToken="+data.result;
        	}
        });
        }
        var dialogWrap = new dialog(dialogObject)

    })
   
    /*鼠标在地址焦点*/
    $("#addressArea").focus(function(){
        $(".maskLayer").show();
    })
   
    $("#address-place .addressLayer-item").on('click',function(event){
       var targerEvent = $(this).attr('id');
        var _this = this;
       var pid;
       //验证是否有上级目录,没有则不显示
       if(targerEvent=='address-province'){
       		pid=0;
       }else if(targerEvent == 'address-city'){
            var p=$("#address-province").attr("data-value");
            if(!p||p==0){
            	return false;
            }
            pid=p;
       }else if(targerEvent == 'address-area'){
           var p=$("#address-city").attr("data-value");
            if(!p){
            	return false;
            }
            pid=p;
       }
       addressAjax(pid,$(_this));
    })
    function addressAjax(pid,oThis){
        $.ajax({
            method: "GET",
            url: basePath+"/h5/address/list_area",
            dataType:"json",
            data:{'pid':pid},
            success: function (data) {
                var aAddArrat =[];
                $.each(data.result,function(i, val){
                    aAddArrat.push({text:val.name,value:val.id})
                })
                new selector({
                    options: aAddArrat,
                    success: function(res){
                        oThis.find(".item-dect").text(res.text);
                        oThis.attr("data-value",res.value);
                       	var targerEvent=oThis.attr("id");
                        if(targerEvent=='address-province'){
					       		$("#address-city").attr("data-value","").find(".item-dect").text("市");
					       		$("#address-area").attr("data-value","").find(".item-dect").text("区");
					     }else if(targerEvent == 'address-city'){
					            $("#address-area").attr("data-value","").find(".item-dect").text("区");
					     }
                    }
                });
            },
            error:function(data){
                console.log('数据出错')
            }

        })
    }
    /*地址遮罩层确定*/
    $("#address-confirm").bind('click',function(){
        if(!($("#address-province").attr("data-value")&&$("#address-city").attr("data-value"))){
        	//选项为空
        	return;
        }
        $(".maskLayer").hide();
        var addressIputText = '';
        addressIputText+=$("#address-province").find(".item-dect").text();
        if(!($("#address-province").find(".item-dect").text()==$("#address-city").find(".item-dect").text())){
	         addressIputText+=$("#address-city").find(".item-dect").text();
        }
        if($("#address-area").find(".item-dect").text()!="区"){
	        addressIputText+=$("#address-area").find(".item-dect").text();
        }
		$("#addressArea").val(addressIputText);
		checkStepOneStaus();
    })
    /*地址遮罩层取消*/
    $("#address-cancel").bind('click',function(){
        $(".maskLayer").hide();
    })

});

function inputValClear(){
    $("input[type='text'],input[type='tel']").each(function () {
        $(this).val("");
    });
    $("input[type='radio'],input[type='checkbox']").each(function () {
        $(this).attr("checked",false);
    });

}

</script>
</body>
</html>