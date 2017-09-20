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
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<script src="<%=path%>/resources/webuploader/webuploader.js"></script> 
<script src="<%=path%>/resources/upload/upload.js"></script>

</head>

<body>
	<form class="form-horizontal" role="form" id="editAdvertisingForm">
		<input type="hidden"  name="id" value="${advertising.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;广告图片：</h5></th>	
					<th colspan="2">
						<div id="adbpicUpload"></div>
						<input type="hidden" class="form-control" id="adbpic" name="adbpic"  value="${advertising.adbpic}">
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;广告文本：</h5></th>	
					<th colspan="2"><input type="text" class="form-control" name="content"  value="${advertising.content}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;广告链接：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="adburl"  value="${advertising.adburl}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;排序：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="sort"  value="${advertising.sort}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;是否显示：</h5></th>	
					<th colspan="2">
						<select class="form-control"  name="isshow">
							<option value="">请选择</option>
							<option value="0" ${advertising.isshow==0?"selected":""}>显示</option>
							<option value="1" ${advertising.isshow==1?"selected":""}>不显示</option>
						</select>
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;类型：</h5></th>	
					<th colspan="2">
						<select class="form-control"  name="type">
							<option value="">请选择</option>
							<option value="1" ${advertising.type==1?"selected":""}>寻蜜鸟客户端</option>
							<option value="2" ${advertising.type==2?"selected":""}>商户客户端</option>
							<option value="3" ${advertising.type==3?"selected":""}>合作商客户端</option>
							<option value="4" ${advertising.type==4?"selected":""}>寻蜜客圈子广告</option>
						</select>
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;备注：</h5></th>	
					<th colspan="2">
			<%-- 		<input type="text" class="form-control"  name="remarks"  value="${advertising.remarks}"> --%>					
					<textarea  name="remarks" cols="20" rows="3" class="form-control">${advertising.remarks}</textarea>
					</th>
				</tr>
				
 				<tr>
	 					<th colspan="3" style="text-align: center;"> 
	 						<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
	 					</th>
	 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script type="text/javascript">
	 	
	 $.validator.addMethod("isURL",function(value,element){
		 var strRegex = '^((https|http|ftp|rtsp|mms)?://)'
	           /*  + '?(([0-9a-z_!~*\'().&=+$%-]+: )?[0-9a-z_!~*\'().&=+$%-]+@)?' //ftp的user@
	            + '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184
	            + '|' // 允许IP和DOMAIN（域名）
	            + '([0-9a-z_!~*\'()-]+.)*' // 域名- www.
	            + '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名
	            + '[a-z]{2,6})' // first level domain- .com or .museum
	            + '(:[0-9]{1,4})?' // 端口- :80
	            + '((/?)|' // a slash isn't required if there is no file name 
	            + '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$'*/;
		var re = new RegExp(strRegex);
		return (re.test(value));
		 },"请输入正确的链接");
	 
		 $(document).ready(function() {
				validate("editAdvertisingForm",{
					rules:{
						content:{
								required:true
						},
						adburl:{
							required:true,
							maxlength:100,
							isURL:true
						},
						sort:{
							required:true,
						 	digits:true,
						 	range:[0,2147483647]
						},
						isshow:{
							required:true
						},
						type:{
							required:true
						},
						remarks:{
							maxlength:100
						}
					},
					messages:{
						content:{
								required:"广告文本不能为空!"
						},
						adburl:{
							required:"广告链接不能为空!",
							maxlength:"链接最大长度为100字符!",
							isURL:"请输入正确的URL格式"
						},
						sort:{
							required:"排序值不能为空！",
						 	digits:"排序值只能为整数!",
						 	range:"取值为1-2147483647!"
						},
						isshow:{
							required:"请选择是否显示!"
						},
						type:{
							required:"请选择类型!"
						},
						remarks:{
							maxlength:"备注的最大长度为100字符!"
						}
					}},formAjax);
				
				$("#adbpicUpload").uploadImg({
					urlId : "adbpic",
					showImg : $("#adbpic").val()
				});
				
				
			}); 
			
			function formAjax(){
				
					var url ;
					if($('#isType').val() ==  'add'){
						url = 'common/advertising/add.jhtml' + '?t=' + Math.random();
					}else{
						url = 'common/advertising/update.jhtml' + '?t=' + Math.random();
					}
					$.ajax({
						type : 'post',
						url : url,
						data : jsonFromt($('#editAdvertisingForm').serializeArray()),
						dataType : 'json',
						beforeSend : function(XMLHttpRequest) {
							$('#prompt').show();
						},
						success : function(data) {
							$('#prompt').hide();
							 $('#triggerModal').modal('hide');
							if (data.success) {
								if($('#isType').val() ==  'add'){
									advertisingList.reset();
								}else{
									advertisingList.reload();
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
