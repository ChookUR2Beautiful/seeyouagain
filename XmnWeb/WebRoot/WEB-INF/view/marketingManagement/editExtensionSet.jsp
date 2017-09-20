<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">

</head>

<body>
	<form class="form-horizontal" role="form" id="editExtensionSetForm">
		<input type="hidden"  name="id" value="${extensionSet.id}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;商家名称：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="sellername"  disabled="disabled" value="${extensionSet.sellername}"></th>
				</tr>
				
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;推广开始时间:</h5></th>
					<td colspan="2"><input type="text"
						class="form-control form-datetime"
						title="<fmt:formatDate value="${extensionSet.dateStart}" pattern="yyyy-MM-dd"/>"
						name=dateStart id="sjaddsvalidity" placeholder="推广开始日期"
						value="<fmt:formatDate value="${extensionSet.dateStart}" pattern="yyyy-MM-dd"/>"></td>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;推广结束时间:</h5></th>	
					<td colspan="2"><input type="text"
						class="form-control form-datetime" name="dateEnd"
						id="sjaddevalidity" placeholder="推广结束日期"
						value="<fmt:formatDate value="${extensionSet.dateEnd}" pattern="yyyy-MM-dd"/>">
					</td>
				</tr>
				
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;排序：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="sort"  value="${extensionSet.sort}"></th>
				</tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button  class="btn btn-success" type="submit" id="save"><i class="icon-save"></i>&nbsp;保存</button>&nbsp;
 						&nbsp;&nbsp;
						<button type="reset" class="btn btn-default"  data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
</body>
 <script src="<%=path%>/js/marketingmanagement/editExtensionSet.js"></script>
 <script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
</html>
