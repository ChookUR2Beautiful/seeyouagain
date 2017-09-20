<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<title>适用商户</title>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
#supportShopList {
	height: 500px;
}
</style>
</head>

<body>

	<form action="" id="mulShopForm">
		<input type="hidden" name="sellerid" id="sellerId"
			value="${vCard.sellerid}"> <input type="hidden"
			name="sellerType" id="sellerType" value="${vCard.sellerType}">
	</form>
	<div id="supportShopList"></div>
	<div id="scrollTablel"></div>
	<script type="text/javascript">
	
	var sellerType = '${vCard.sellerType}';
	
	var supportShopList=$(function() {
			$('#supportShopList')
					.page(
							{
								url : 'businessman/valueCard/childSellerInit/childSellerList.jhtml',
								success : success,
								pageBtnNum : 10,
								pageSize : 10,
								paramForm : 'mulShopForm'
							});
		});
		function success(data, obj) {

			var captionInfo = '<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">适用商户列表</caption>';
			var callbackParam = "isBackButton=true&callbackParam="
					+ getFormParam($("#mulShopForm").serialize());
			obj
					.find('div')
					.eq(0)
					.scrollTablel(
							{
								tableClass : "table-bordered table-striped info",
								callbackParam : callbackParam,
								caption : captionInfo,
								//数据
								data : data.content,
								// 操作列
								//数据行
								cols : [ {
									title : "商家编号",// 标题
									name : "sellerid",
									//sort : "up",
									width : 90,// 宽度
									type : "number",//数据类型					
								}, {
									title : "商家名称",// 标题
									name : "sellername",
									width : 140,// 宽度
									type : "string"//数据类型
								}, {
									title : "商家地址",// 标题
									name : "address",
									width : 150,// 宽度
									type : "string"//数据类型
								}, {
									title : "是否可用储值卡",// 标题
									name : "useValueCard",
									width : 100,// 宽度
									type : "string",//数据类型
									customMethod : function(value, data) {
										if(sellerType==1){
											return "可用";
										}else{
											if (value == 1) {
												return "可用";
											} else {
												return "不可用";
											}
										}
									}
								} ],
								//操作列
								handleCols : {
									title : "操作",// 标题
									queryPermission : ["limit"],
									width : 80,// 宽度
									// 当前列的中元素
									cols : [
											{
												title : "限制",// 标题
												linkInfoName : "href",
												linkInfo : {
													href : "businessman/valueCard/limit.jhtml",// url
													param : [ "sellerid"],// 参数
													permission : "limit"// 列权限
												},
												 customMethod : function(value, data) {
													if(sellerType==1){
														return "<a href='javascript:;' style='color:gray'>限制</a>";
													}else{
														if(data.useValueCard == 0){
															return "<a href='javascript:limitSeller(\""+data.sellerid+"\",\"1\");'>解除</a>";
														}else{
															return "<a href='javascript:limitSeller(\""+data.sellerid+"\",\"0\");'>限制</a>";
														}
													}
												} 
											} ]
								}
							}, permissions);
		}
		function substr(obj, length) {
			if (obj.length > length) {
				obj = obj.substring(0, length) + "...";
			}
			return obj;
		}
		
		function limitSeller(sellerid,status){
			
			var fatherid = $("#sellerId").val();
			var type = $("#sellerType").val();
			
			var url = contextPath+"/businessman/valueCard/limit.jhtml"
			$.ajax({
				type : 'post',
				url : url,
				data :{'sellerid':sellerid,'useValueCard':status,'fatherid':fatherid},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						setTimeout(function(){
		        			/* location.href =contextPath+'/businessman/valueCard/childSellerInit.jhtml?sellerid='+fatherid+'&sellerType='+type; */
							location.href =contextPath+'/businessman/valueCard/init.jhtml';
		        		}, 1000);
				    }			
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(data) {
					window.messager.warning(data.msg);
				}
			});
		}
	</script>
</body>
</html>
