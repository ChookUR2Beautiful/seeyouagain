<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>添加连锁店信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none!important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form  id="multipShopForm" >
						<input type="hidden" name="multipShopToken" value="${multipShopToken}">
						<input type="hidden" id="isType" name="isType" value="${isType}"/>
						<input type="hidden" id="selleridid" name="sellerid" value="${selleridList.sellerid}"/>
						<input type="hidden" id="picid" name="picid" value="${selleridList.picid}"/>
						<input type="hidden" id="detailedId" name="detailedId" value="${selleridList.detailedId}"/>
						<table class="table" style="text-align: center;" >
							<tr >
								<td class="sellerTitleCss">
									<h5>连锁店名称:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="sellername" placeholder="商家名称"
									value="${selleridList.sellername}"></td>
								<td class="sellerTitleCss">
									<h5>连锁店LOGO:</h5>
								</td>
								<td class="sellerContentCss" rowspan="2">
									<div class="col-md-9">
										<div id='sellerLogoId' ImgValidate="true"></div>
										<input type="hidden" id="url" name="url"
											value="${selleridList.url}" />
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<h5>地址:</h5>
								</td>
								<td><input type="text" class="form-control"
									name="address" placeholder="地址" value="${selleridList.address}">
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss">
									<h5>区域:</h5>
								</td>
								<td class="sellerContentCss">
									<div class="input-group" style="width:100%; text-align: left"
										id="areaSelect" initValue="${selleridList.area}"></div>
								</td>

								<td class="sellerTitleCss">
									<h5>商圈:</h5>
								</td>

								<td class="sellerTitleCss" style="text-align: left;"><select
									class="form-control" id="zoneid" name="zoneid"
									style="width:100%;" initValue="${selleridList.zoneid}">
										<option value="">请先选择区域再选择商圈</option>
								</select></td>

								<td class="sellerTitleCss">
									<h5>法人姓名:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="fullname" placeholder="法人姓名"
									value="${selleridList.fullname}"></td>
							</tr>
							<tr>
								<td>
									<h5>联系人手机:</h5>
								</td>
								<td><input type="hidden" class="form-control"
									name="oldPhoneid" placeholder="联系人手机"
									value="${selleridList.phoneid}"> <input type="text"
									class="form-control" name="phoneid" placeholder="联系人手机"
									value="${selleridList.phoneid}"></td>

								<td class="sellerTitleCss">
									<h5>连锁店电话:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="tel" placeholder="联系电话"
									value="${selleridList.tel}"></td>
								<td class="sellerTitleCss">
									<h5>参考地标:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" placeholder="参考地标"
									name="landmark"
									value="${selleridList.landmark}"></td>
							</tr>
						</table>
						<hr>
						<table class="table" style="text-align: center;">
						     <tr>
									<td style="width: 7.8%;">
											<h5>主账号:</h5> 
									</td>
									<td class="sellerContentCss">
										  <input type="hidden" name = "aid" id = "aid" value = "${sellerAccount.aid}">
									      <input type="hidden" id="selleridid" name="sellerid" value="${selleridList.sellerid}"/>
									     <input type="text" class="form-control" id="account" name="account"  placeholder="主账号"  value = "${selleridList.mainAccount}"/>
									     <input type="hidden" class="form-control" id="oldAccount" name="oldAccount"  value = "${sellerAccount.account}"/>
									</td>
									<td colspan="2" style="text-align: left;">
									<h5><label style="color:red;">（初始密码为联系人手机后六位）</label></h5> 
									</td>
									<td class="sellerContentCss"></td>
									<td class = "sellerTitleCss"></td>
									<td class="sellerContentCss"></td>
								</tr>
								<tr>
									<td class = "sellerTitleCss">
											<h5>真实姓名:</h5> 
									</td>
									<td class="sellerContentCss">
										  <input type="text" class="form-control"  name="accountName"  value = "${sellerAccount.fullname}">
									</td> 
									
									<td class = "sellerTitleCss">
											<h5>账号昵称:</h5> 
									</td>
									<td class="sellerContentCss">
										  <input type="text" class="form-control"  name="nname"  value = "${sellerAccount.nname}" readonly="readonly">
									</td>
								</tr>
						</table>
						<hr>
						<table class="table" style="text-align: center;">
									<tr>
										<td style="width: 7.8%;"><h5>子店:</h5></td>
										<td colspan="10" style="text-align: left">
												<textarea id="object" name="ids" rows="10" class="form-control" style="width:60%;"></textarea>
										</td>
									</tr>
						</table>
						
						<div align="center">
								<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
								<button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		window.onload = function(){
			var account = document.getElementById("account");
			if(account.value != "") {
				account.setAttribute("readonly",true);
			}
		}
	</script>
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/ux/js/ld2.js"></script>
    <script src="<%=path%>/js/businessman/multipleshop/editMultipShop.js"></script> 
</body>
</html>
