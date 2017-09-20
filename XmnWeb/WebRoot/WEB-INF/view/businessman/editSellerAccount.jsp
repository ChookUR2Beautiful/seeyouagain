<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<form class="form-horizontal" role="form" id="editSellerAccountForm">
		<input type="hidden" name="sellerToken" value="${sellerAccountToken}">
		<input type="hidden"  name="userId" value="${sellerAccount.aid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<input type="hidden" id="isBinding" name = "isBinding" value="false">
		<table width="100%">
			<tbody>
				
				<input type="hidden" class="form-control"  name="aid"  value="${sellerAccount.aid}"></th>
				<input type="hidden" class="form-control"  name="sellerid"  value="${param.sellerid!=null?param.sellerid:sellerAccount.sellerid}"></th>
				<input type="hidden" class="form-control"  name="sdate"  value="<fmt:formatDate value="${sellerAccount.sdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"></th>
				
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;账号(手机号)</h5></th>	
					<th colspan="2">
					<input type="text" class="form-control" id="account" name="account" 
						${'update'==isType?'disabled=disabled':''} value="${sellerAccount.account}">
					</th>
					<input type="hidden"  name="phone">
				</tr>
				<tr class="conflictTip" style="display:none;"><th></th>
				<th><h5 id="conflictMsg"  style="color:#F00; font-size:12px;"></h5></th>
				<th><a id="reloadSelect"  style="color:#00F; font-size:12px;" data-toggle="modal" href="#sm_confirm_window"<%-- data-target="#sm_confirm_window" --%>>点我重新绑定</a></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;账号昵称</h5></th>	
					<th colspan="2">
					 <input type="text" class="form-control"  name="nname"  value="${sellerAccount.nname}">
					</th>
					 
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;真实姓名</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="fullname"  value="${sellerAccount.fullname}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;登录密码</h5></th><input type="hidden" name="oldpassword" value="${sellerAccount.password}"/>
					<th colspan="2"><input type="password" class="form-control"  name="password" value="${sellerAccount.password}"  ></th>
				</tr>

				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;备注</h5></th>
					<th colspan="2"><textarea name="remarks" rows="3" class="form-control" placeholder="备注">${sellerAccount.remarks}</textarea></th>									
				</tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
	 			</tr>
	 			</tbody>
	 		</table>
	 </form>
<script src="<%=path%>/js/businessman/editSellerAccount.js"></script>
</body>

</html>
