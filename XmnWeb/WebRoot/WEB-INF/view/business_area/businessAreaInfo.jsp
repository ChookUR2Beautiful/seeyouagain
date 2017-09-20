<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<input type="hidden" id="cityNameid" value="${cityNameid}">
	<div id="areaList"></div>
	<script type="text/javascript">
		$(document).ready(function() {
		var areaList; 
		areaList = $('#areaList').page({
				url : 'business_area/businessArea/init/getBusinessAreaInfo/init/list.jhtml',
				success : success,
				pageBtnNum : 3,
				param:{cityNameid:$("#cityNameid").val()}
			});
		 
		});
	
	/*------------------------分隔线-------------------------------------- 	 */
	function success(data, obj) {
		var html = [];
		html.push('<table class="table table-hover table-bordered table-striped info" >');
		html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">区域分布</caption>');
		html.push('<thead>');
		html.push('<tr>');
			
		
		html.push('<th style="width:140px;">省份</th>');
		html.push('<th style="width:140px;">城市</th>');
		html.push('<th style="width:100px;">区域</th>');
		
	
		html.push('</tr>');
		html.push('</thead>');
		html.push('<tbody');

		if(null != data && data.content.length > 0)
		{	
		
		for (var i = 0; i < data.content.length; i++) {		
			html.push('<tr>');
			html.push('<td>' + (undefined == data.content[i].title ? "-" : data.content[i].title) + '</td>');
			html.push('<td>' + (undefined == data.content[i].cityName ? "-" : data.content[i].cityName) + '</td>');
			html.push('<td>' + (undefined == data.content[i].areaName ? "-" : data.content[i].areaName) + '</td>');
			html.push('</tr>');
		}
		
		}
		else
		{
			html.push('<tr>');
			html.push('<td colspan="20">暂无数据</td>');
			html.push('</tr>');
		}
		html.push('</tbody>');
		html.push('</table>');
		obj.find('div').eq(0).html(html.join(''));
   	}
	
	
	

	</script>

</body>
</html>
