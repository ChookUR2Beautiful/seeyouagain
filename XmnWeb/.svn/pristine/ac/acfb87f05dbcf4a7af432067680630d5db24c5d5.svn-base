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
</head>

<body>
	<form class="form-horizontal" role="form" id="editAppVersionForm">
		<input type="hidden"  name="userId" value="${appVersion.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<input type="hidden"  name="id" value="${appVersion.id}">
	 	<table width="100%" class="borderBottom table table-form " >
		<tbody>
			<tr>
				<!-- 1=经销商版|2=商户版|3=用户版|4=商户版(演示)|5=用户版(演示)|6=其他 -->
					<th class="w-100px">应用类型:</th>	
					<td >
					<%-- <input type="text" class="form-control"  name="id"  value="${appVersion.apptype}"> --%>
						<select class="form-control"  name="apptype"  value="${appVersion.apptype}">
								<option value="" >请选择应用类型</option>
								<option value="1" <c:if test="${appVersion.apptype==1}">selected</c:if> >经销商版</option>
								<option value="2" <c:if test="${appVersion.apptype==2}">selected</c:if> >商户版</option>
								<option value="3" <c:if test="${appVersion.apptype==3}">selected</c:if> >用户版</option>
								<option value="4" <c:if test="${appVersion.apptype==4}">selected</c:if> >商户版(演示)</option>	
								<option value="5" <c:if test="${appVersion.apptype==5}">selected</c:if> >用户版(演示)</option>	
								<option value="6" <c:if test="${appVersion.apptype==6}">selected</c:if> >其他 </option>										
						</select>	
					</td>
				</tr>
				<tr>
				<!-- 1=Android|2=Ios|3=Wp|4=其他 -->
					<th class="w-100px">版本类型:</th>	
					<td>
						<input type="hidden" name="vtype" value="2"> 
						<select class="form-control"   disabled="disabled">
							<option selected="selected" >IOS</option>
						</select>
					</td>
				</tr>
				
					<tr>
					<th class="w-100px">更新版本号:</th>	
					<td><input type="text" class="form-control"  name="version"  value="${appVersion.version}"></td>
				</tr>
				
				<tr>
					<th class="w-100px">内部版本号:</th>	
					<td><input type="text" class="form-control"  name="inside"  value="${appVersion.inside}"></td>
				</tr>
								
				<tr>
				<!-- 1=已发布|2=停止更新 -->
					<th class="w-100px">版本状态:</th>	
					<td>
						<select class="form-control"  name="status"  value="${appVersion.status}">
								<option value="" >请选择版本类型</option>
								<option value="1" <c:if test="${appVersion.status==1}">selected</c:if> >已发布</option>
								<option value="2" <c:if test="${appVersion.status==2}">selected</c:if> >停止更新</option>								
						</select>	
					</td>
				</tr>
				
				<tr>
					<th class="w-100px">连接地址:</th>
					<td >
						<input type="text"  class="form-control" name ="url" placeholder="IOS应用的地址 格式如:http://www.xxx.com"  value = "${appVersion.url}"/>		
					
					</td>
				</tr>
				
				<tr>
					<th class="w-100px">强制升级:</th>	
					<td>
						<input type="radio" name="mustUpdate" <c:if test="${appVersion.mustUpdate == 1}">checked="checked"</c:if> value="1" >&nbsp;是  &nbsp;&nbsp;&nbsp;
						<input type="radio" name="mustUpdate" <c:if test="${appVersion.mustUpdate == 0 || appVersion.mustUpdate == null}">checked="checked"</c:if> value="0">&nbsp;否	
					</td>
				</tr>
				
				<tr>
					<th class="w-100px">更新内容:</th>	
					<td>
					
					 <textarea  name="content" cols="20" rows="3" class="form-control">${appVersion.content}</textarea>							
					</td>
				</tr>
				<tr >
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-success" ><span class="icon-ok"></span> 保存 </button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span> 取消</button>
					</td>
				</tr>
			
		</tbody>
	</table>
	 </form>
	 <script type="text/javascript">
	$(document).ready(function() {
			startValidate();
	});
		
	function startValidate(){
			validate("editAppVersionForm",{
				rules:{
					apptype:{
						required:true,
					},
					vtype:{
						required:true,
					},
					version:{
						required:true,
						maxlength:10
					},
					inside:{
						required:true,
						maxlength:10
					},
					status:{
						required:true
					},
					activeNo:{
						required:true,
						digits:true,
						maxlength:10
					},
					url:{
						required:true,
						url:true
					},
					content:{
						maxlength:300
					}
				},
				messages:{
					apptype:{
						required:"请选择一个应用类型!",
					},
					vtype:{
						required:"请选择一个版本类型！",
					},
					version:{
						required:"请输入一个版本号",
						maxlength:"最大长度为10个字符串!"
					},
					inside:{
						required:"请输入一个内部版本号",
						maxlength:"最大长度为10个字符串!"
					},
					status:{
						required:"请选择一个版本状态!"
					},
					activeNo:{
						required:"请输入激活数量！",
						digits:"数量为整数!",
						maxlength:"最大为10位整数999999999!"
					},
					url:{
						required:"链接地址未填写",
						url:"无效的链接地址"
					},
					content:{
						maxlength:"最大长度为300字符串!"
					}
				}},formAjax);
			 
		}
	
		function formAjax(){
			var url ;
			if($('#isType').val() ==  'add'){
				url = 'common/appVersion/add.jhtml' + '?t=' + Math.random();
			}else{
				url = 'common/appVersion/update.jhtml' + '?t=' + Math.random();
			}
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#editAppVersionForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							appVersionList.reset();
						}else{
							appVersionList.reload();
						}
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
		}
	 </script>
</body>
</html>
