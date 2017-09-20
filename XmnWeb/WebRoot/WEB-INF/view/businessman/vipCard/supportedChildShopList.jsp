<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<title>充值详情</title>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
	#supportShopList{
	height:500px;
	}
</style>
</head>

<body>

	 	<form action="" id="mulShopForm">
	 		<input type="hidden" name="sellerId" id="sellerId" value="${sellerId }">
	 	</form>
		<div id="supportShopList"></div>
		<div id="scrollTablel"></div>
<script type="text/javascript">
	$(function(){
		$('#supportShopList').page({
			url : 'businessman/vipCard/listSupporSellerList.jhtml',
			success : success,
			pageBtnNum :10,
			pageSize:10,
			paramForm : 'mulShopForm'
		});
	});
	function success(data, obj) {
		
		var captionInfo = '<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">适用商户列表</caption>';
		var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#mulShopForm").serialize());
		obj.find('div').eq(0).scrollTablel({
		    	//checkable :false,
		    	//identifier : "bid",
		    	tableClass :"table-bordered table-striped info",
		    	callbackParam : callbackParam,
		    	caption : captionInfo,
				//数据
				data:data.content, 
				 //数据行
				cols:[{
						title : "商家编号",// 标题
						name : "sellerid",
						//sort : "up",
						width : 100,// 宽度
						type:"number",//数据类型					
				},{
					title : "商家名称",// 标题
					name : "sellerName",
					width : 150,// 宽度
					type:"string"//数据类型
				}],
				//操作列
				handleCols : { 
		}},permissions);
	}
	function substr(obj,length){
		if(obj.length > length){
			obj = obj.substring(0,length) +"...";
		}
		return obj;
	}
</script>
</body>
</html>
