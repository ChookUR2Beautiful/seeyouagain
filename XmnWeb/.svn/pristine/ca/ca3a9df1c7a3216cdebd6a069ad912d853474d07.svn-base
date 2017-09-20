<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
</head>
<body style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<!-- loading -->
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main"><div class="example"><div class="panel panel-primary"><div class="panel-heading">基本信息</div><div class="panel-body">
	<form class="form-horizontal" role="form" id="editAppPushForm">
		<input type="hidden" name="tid" value="${appPush.tid}">
		<input type="hidden" id="isType" value="${isType}">
		<input type="hidden" name="client" value="${appPush.client}">
		<table width="100%" >
			<tbody>
				<tr>
					<td class="sellerTitleCss"><h5>&nbsp;&nbsp;信息标题:</h5></td>	
					<td class="sellerContentCss"><input type="text" class="form-control"  name="title" value="${appPush.title}"></td>
					<td class="sellerTitleCss"><h5>&nbsp;&nbsp;提醒方式:</h5></td>	
					<td class="sellerContentCss">							
						<select class="form-control"  name="remind" value="${appPush.remind}">
					        <option value="" >请选择提醒方式</option>
							<option value="0" <c:if test="${appPush.remind==0}">selected</c:if>>声音</option>
							<option value="1" <c:if test="${appPush.remind==1}">selected</c:if>>震动</option>
							<option value="2" <c:if test="${appPush.remind==2}">selected</c:if>>呼吸灯</option>									
						</select>	
					</td>
					<td class="sellerTitleCss"><h5>&nbsp;&nbsp;内容类型:</h5></td>	
					<td class="sellerContentCss">
					 <select class="form-control" id="contenttype" name="contenttype" value="${appPush.contenttype}" readonly>
						<%--<option value="1" <c:if test="${appPush.contenttype==1}">selected</c:if>>提示信息</option> 
							<option value="2" <c:if test="${appPush.contenttype==2}">selected</c:if>>订单提醒</option> --%>
							<option value="3" <c:if test="${appPush.contenttype==3}">selected</c:if>>营销信息</option>
							<%-- <option value="4" <c:if test="${appPush.contenttype==4}">selected</c:if>>系统消息</option> --%>
					 </select>	
					</td>
				</tr>
				<tr>
					<td><h5>&nbsp;&nbsp;后续动作类型:</h5></td>	
					<td>							
						<select class="form-control"  name="type"  value="${appPush.type}">
					        <option value="" >请选择后续动作类型</option>
							<option value="1" <c:if test="${appPush.type==1}">selected</c:if> >打开应用</option>
							<option value="2" <c:if test="${appPush.type==2}">selected</c:if> >网址</option>
							<%-- <option value="3" <c:if test="${appPush.type==3}">selected</c:if> >activity</option> --%>									
						</select>	
					</td>
					<td><h5>&nbsp;&nbsp;后续动作:</h5></td>	
					<td colspan="3"><input type="text" class="form-control"  name="action"  value="${appPush.action}"></td>
				</tr>
				<tr>
					<td><h5>&nbsp;&nbsp;推送方式:</h5></td>
					<td>
					    <small><label for="sendDate0"><input type="radio" id="sendDate0" name="sendDate" value="0" ${empty appPush.tdate?"checked":""} readonly>即时发送</label></small>
						<small><label for="sendDate1"><input type="radio" id="sendDate1" name="sendDate" value="1" ${empty appPush.tdate?"":"checked"} readonly>计划发送</label></small>
					</td>
					<td style="display: none;" class="sendDate"><h5>&nbsp;&nbsp;推送开始时间:</h5></td>
					<td style="display: none;" class="sendDate">
						<input type="text" name="tdate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:100%;" value="<fmt:formatDate value="${appPush.tdate}" pattern="yyyy-MM-dd HH:mm"/>"/>
					</td>
					<td style="display: none;" class="sendDate"><h5>&nbsp;&nbsp;推送结束时间:</h5></td><!-- 过期时间需要至少晚于发送时间30分钟 -->
					<td style="display: none;" class="sendDate">
						<input type="text" name ="edate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="float:left" value="<fmt:formatDate value="${appPush.edate}" pattern="yyyy-MM-dd HH:mm"/>" >
					</td>
				</tr>
				<tr>	
					<td><h5>&nbsp;&nbsp;信息内容:</h5></td>
					<td colspan="5">
					<input name="content" type="text" class="form-control" placeholder="信息内容" value="${appPush.content}"/>							
					</td>
				</tr>	
				<tr>
					
				</tr>
				<%-- <td style="width:90px;"><h5>&nbsp;&nbsp;客户端类型:</h5></td>	
					<td>							
						<select class="form-control"  name="client" readonly> <!-- 1 寻蜜鸟客户端|2 商户客户端|3 合作商客户端 -->
							<option value="1" <c:if test="${appPush.client==1}">selected</c:if>>寻蜜鸟客户端</option>
							<option value="2" <c:if test="${appPush.client==2}">selected</c:if>> 商户客户端</option>
							<option value="3" <c:if test="${appPush.client==3}">selected</c:if>>合作商客户端</option>										
						</select>	
					</td> --%>
				<tr>
					<td><h5>&nbsp;&nbsp;发送对象:</h5></td>
					<td id="sendObjectTypeTd">
						<small><label for="sendObjectType0"><input type="radio" id="sendObjectType0" name="sendObjectType" value="0" checked/><span id="objectStr"></span></label></small>
						<small><label for="sendObjectType1"><input type="radio" id="sendObjectType1" name="sendObjectType" value="1"/><span id="objectStr2"></span></label></small>
						<small><label for="sendObjectType2"><input type="radio" id="sendObjectType2" name="sendObjectType" value="2"/><span id="objectStr3"></span></label></small>
					</td>
				</tr>
				<tr>
					<td class="sendObjectTr" style="display:none;"><h5>&nbsp;&nbsp;选择发送对象:</h5></td>
					<td class="sendObjectTr" style="display:none;" colspan="3">
						<textarea id="object" class="form-control" rows="5" cols="" name="object">${appPush.object}</textarea>
					</td>
				</tr>
				<tr>
					<td class="cityTr" style="display:none;"><h5>&nbsp;&nbsp;选择发送区域:</h5></td>
					<td class="cityTr" style="display:none;" colspan="3">
						<div class="input-group" id="areaInfo"
								style="width: 80%;float:left;"
								initValue="${appPush.city}"></div>
					</td>
				</tr>
	 			</tbody>
	 		</table>
	 		<div style="height:20px"></div>
			<div align="center">
			    <button class="btn btn-danger" type="submit" id="ensure" ><i class="icon-save"></i>&nbsp;发送</button>&nbsp;&nbsp;
				<button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button>
			</div>
	</form>
	</div></div></div></div>
<jsp:include page="../common.jsp"></jsp:include>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script src="<%=path%>/ux/js/searchChosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script src="<%=path%>/js/common/editAppPush.js"></script>
</body>

</html>
