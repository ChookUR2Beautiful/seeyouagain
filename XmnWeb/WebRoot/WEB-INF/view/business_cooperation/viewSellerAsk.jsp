<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<script type="text/javascript">
</script>
</head>

<body>
	<form class="form-horizontal" role="form" id="editSellerAskForm">
		<input type="hidden"  name="aid" value="${sellerAsk.aid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;问题名称</h5></th>
					<th colspan="2">
					<input disabled="disabled" type="text" class="form-control" name="askname" value="${sellerAsk.askname}"> 
						</th>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;问题答案:</h5></th>
					<th colspan="2"><textarea name="content" rows="10" disabled="disabled"
							class="form-control" placeholder="问题答案" >${sellerAsk.content}</textarea>
					</th>
				</tr>
 				<tr>
	 					<th colspan="3" style="text-align: center;"> 
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>返回</button>
	 					</th>
	 				</tr>
	 			</tbody>
	 		</table>
	 </form>
</body>
</html>
