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
    <link rel="stylesheet" href="${ctx}/css/marketing.css"/>
</head>
<body class="padd-fill-tb bg-color-01">

    <div class="container-wrap">
        <div class="spacing-list-module" id="list">
            <!--data-init 默认,1为默认地址、data-href 跳转路径、data-Name 用户名称 、data-sex 性别、data-iphone 电话号、data-area 地区、data-address 详细地址-->
        </div>
    </div>
    <div class="floor-module">
        <a class="floor-links links-type2" href="${ctx}/h5/address/input" id="gostepfour">添加收货地址</a>
    </div>



<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/component.js"></script>
<script type="text/javascript">
	
$(function(){
    $.ajax({
        method: "GET",
        url: "${ctx}/h5/address/list",
        dataType:"json",
        success: function (data) {
       		 var list= $("#list");
           	var result = data.result;
           	if(result.length==0){
           		list.attr("class","address-banner-module");
           		var html='<div class="address-banner-img"><img src="${ctx}/imgs/address/bird@2x.png" alt="图片"/></div>';
           		html+='<h3>你还没收货地址哦~</h3>';
           		html+='<p>为了能顺利下单，马上添加一个吧！</p>';
           		list.append(html);
           	}
            $.each(result,function(i,item){
            	var addressAreaText='';
	        	if(item.province==item.city){
	        		addressAreaText=item.city+(item.areaName==null?'':item.areaName)+item.address;
	        	}else{
	        		addressAreaText=item.province+item.city+(item.areaName==null?'':item.areaName)+item.address;
	        	}
            	list.attr("class","spacing-list-module");
	            var html = '<a class="list-wrap item-icon-right item-select" href="${ctx}/h5/address/input?id='+item.id+'" >';
	            html += '<i class="icon-wrap icon-arrow-right"></i>';
	            html += '<div class="list-subhead"><span class="list-name">'+item.nname+'</span>&nbsp;|&nbsp;<span class="list-iphone">'+item.phone+'</span></div>';
	            html += '<div class="list-desc"><span class="list-desc-text">'+addressAreaText+'</span></div>';
	            if(item.isDefault==1){
		            html += '<sub class="icon-wrap icon-mark"></sub>';
	            }
	            html += '</a>';
	           	list.append(html);
            });
        },
        error:function(data){
            console.log('数据出错')
        }

    });
    
   
});
</script>
</body>
</html>