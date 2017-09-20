<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<form class="form-horizontal" role="form" id="editBusinessForm">
		<input type="hidden" name="hid" value="${hotWords.hid}"> <input
			type="hidden" id="isType" value="${isType}"> <input
			type="hidden" name="hotStatus" value="1">
		<table width="100%">
			<tbody>
				<c:if test="${isType=='add'}">
					<tr>
						<th class="sellerTitleCss"><h5>城市区域:</h5></th>
						<th colspan="1">
							<div class="input-group" id="areaId" style="width: 100%"
								initValue="${hotWords.areaId}"></div>
						</th>
					</tr>
				</c:if>
				<c:if test="${!empty hotWords}">
					<tr>
						<th><h5>城市区域:</h5></th>
						<input type="hidden" name="areaId" value="${hotWords.areaId}">
						<c:if test="${!empty hotWords.provinceName}">
							<th><select class="form-control"
								disabled="disabled">
									<option>${hotWords.provinceName}</option>
							</select></th>
						</c:if>
						<c:if test="${!empty hotWords.title}">
							<th><select class="form-control"
								disabled="disabled">
									<option>${hotWords.title}</option>
							</select></th>
						</c:if>
						
					</tr>
				</c:if>

				<tr>
					<th class="sellerTitleCss"><h5>关键词:</h5></th>
					<th colspan="2"><input type="text" class="form-control"
						placeholder="关键词" name="hotWord" value="${hotWords.hotWord}">
					</th>
				</tr>
				<tr>
					<td class="sellerTitleCss">
						<h5>排序:</h5>
					</td>
					<td class="sellerContentCss" colspan="2"><input type="text"
						name="hotOrder" class="form-control" placeholder="排序"
						value="${hotWords.hotOrder}"></td>


				</tr>

				<tr>
					<th colspan="3" style="text-align: center;">
						<button type="submit" class="btn btn-success">
							<span class="icon-ok"></span> 保 存
						</button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal">
							<span class="icon-remove"></span> 取 消
						</button>
					</th>
				</tr>
			</tbody>
		</table>
	</form>
	<script src="<%=path%>/js/marketingmanagement/editHotWords.js"></script>
</body>
</html>
