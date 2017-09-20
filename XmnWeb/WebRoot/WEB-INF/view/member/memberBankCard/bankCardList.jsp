<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<title>会员列表</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
  </head>
  
  <body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:120px;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户编号:</h5></td>
							<td style="width:380px;"><input type="text" class="form-control"  name="uId" style="width:95%;"></td>	
							<td style="width:120px;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卡号:</h5></td>
							<td style="width:380px;"><input type="text" class="form-control"  name="account" style="width:95%;"></td>	
							<td style="width:120px;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;证件号码:</h5></td>
							<td style="width:380px;"><input type="text" class="form-control"  name="identity" style="width:95%;"></td>	
							<td></td>		
						</tr>
						<tr>
							<td style="width:120px;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;持卡人姓名:</h5></td>
							<td style="width:380px;"><input type="text" class="form-control"  name="username" style="width:95%;"></td>	
							<td style="width:120px;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预留手机号:</h5></td>
							<td style="width:380px;"><input type="text" class="form-control"  name="mobileid" style="width:95%;"></td>	
							<td colspan="2">
								<div class="submit">
								<input type="button" data-bus="query" value="查询全部" class="submit radius-3">
								<input type="reset" data-bus="reset" value="重置全部" class="reset radius-3">
								</div>
						    </td>	
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	
	<div class="panel">
		<div class="panel-body data">
			<div id="bankCard" request-init ="${requestInit}">
				
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{jb:'${ btnAu['member/MemberBankCard/unbundlingBankCard']}'}</script> 
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/member/memberBankCard/memberBankCard.js"></script>
	
</body>
</html>
