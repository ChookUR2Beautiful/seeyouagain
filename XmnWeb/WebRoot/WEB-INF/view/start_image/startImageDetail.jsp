<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<form class="form-horizontal" role="form" id="startImageDetailForm">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%" >
			<tbody>
			    <!-- <tr style=" border-bottom-style:solid;border-bottom-width: 1px;">
					<th colspan="4" style="text-align: center;"><h3>启动图详情</h3></th>
				</tr> -->
				<tr>
					<th style="width:15%;"><h4>客户端类型:</h4></th>
					<td style="width:35%;">
						<small>
							<c:if test="${startImage.type == 1}">商户版</c:if>
							<c:if test="${startImage.type == 2}">寻蜜鸟用户版</c:if>
							<c:if test="${startImage.type == 3}">合作商版</c:if>
						</small>
					</td>
					<th style="width:15%;"><h4>设备类型:</h4></th>
					<td style="width:35%;">
						<small>
							<c:if test="${startImage.atype == 1}">Android</c:if>
							<c:if test="${startImage.atype == 2}">IOS</c:if>
							<c:if test="${startImage.atype == 3}">WP</c:if>
						</small>
					</td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>状态:</h4></th>
					<td style="width:35%;">
						<small>
							<c:if test="${startImage.status == 0}">启用</c:if>
							<c:if test="${startImage.status == 1}">停用</c:if>		
						</small>
					</td>
					<th style="width:15%;"><h4>排序:</h4></th>
					<td style="width:35%;"><small>${startImage.index}</small></td>
				</tr>
				<tr>	
					<th style="width:15%;"><h4>启动图:</h4></th>
					<td style="width:35%;">
						<small>
							<img style="width:50px;height:50px;" id="image_id"/>	
						</small>
					</td>				
					<th style="width:15%;"><h4>低分辨率启动图:</h4></th>
					<td style="width:35%;">
						<small>
							<img style="width:50px;height:50px;" id="imagelow_id"/>	
						</small>
					</td>		
				</tr>
				<tr>
				   <th style="width:15%;"><h4>中分辨率启动图:</h4></th>
					  <td style="width:35%;">
						<small>
							<img style="width:50px;height:50px;" id="imagemiddle_id"/>	
						</small>
					</td>
				    
				</tr>
				<tr>
				 <th style="width:15%;"><h4>启动图链接:</h4></th>
				<td colspan="3"  style="width:85%;"><small>${startImage.startUrl }</small></td>
				</tr>
				<tr>
				 <th style="width:15%;"><h4>备注:</h4></th>
				<td colspan="3"  style="width:85%;"><small>${startImage.remarks}</small></td>
				</tr>
				<%-- <tr>	
					<th style="width:15%;"><h4>&nbsp;&nbsp;添加时间:</h4></th>
					<td style="width:35%;"><small><fmt:formatDate value="${startImage.sdate}" pattern="yyyy-MM-dd HH:mm:ss"/></small></td>				
					<th style="width:15%;"><h4>&nbsp;&nbsp;修改时间:</h4></th>
					<td style="width:35%;"><small><fmt:formatDate value="${startImage.edate}" pattern="yyyy-MM-dd HH:mm:ss"/></small></td>
				</tr> --%>
 				<tr>
 					<th colspan="4" style="text-align: center;"> 
						<button type="reset" class="btn btn-success" data-dismiss="modal"><span class="icon-reply-all"></span>  关 闭  </button>
 					</th>
 				</tr>
 			</tbody>
	 	</table>
	 </form>
	 <script type="text/javascript">
	 	var imageRoot = $("#fastfdsHttp").val();
	 	$("#image_id").attr("src", imageRoot+"${startImage.pic}");
	 	$("#imagelow_id").attr("src", imageRoot+"${startImage.picLow}");
	 	$("#imagemiddle_id").attr("src", imageRoot+"${startImage.picMiddle}");
	 </script>
</body>
</html>
