<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加直播会员</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>
<body >
<br>
<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
					<div class="panel-body">
						<form id="editFrom" role="form" class="form-horizontal">
							<!-- 主播会员ID -->
							<input type="hidden" id="uid" name="uid" value="${anchor.uid}">
							<input type="hidden" id="isBinding" name = "isBinding" value="N">
							<!--  0 无寻蜜鸟会员和直播会员信息  1 存在寻蜜鸟会员信息，不存在直播会员信息  2存在寻蜜鸟会员和直播会员信息 -->
							<input type="hidden" id="role" name = "role" value="0">
							
							<input type="hidden" id="id" name="id" value="${anchor.id}">
							<input type="hidden" id="utype" name="utype" value="${anchor.utype}">
							<input type="hidden" id="changeToAnchor" name="changeToAnchor" value="">
							<!-- 操作类型: 与utype值保持一致，添加值对应角色-->
							<input type="hidden" id="operationType" name="operationType" value="2">
							<input type="hidden" id="liverToken" name="liverToken" value="${liverToken }">
							<div class="form-group">
								<label class="col-md-3 control-label">手机号：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" id="phone" name="phone" 
										value="${anchor.phone}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">昵称：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="nickname" id="nickname" placeholder="请输入1-12位字母,数字,下划线,中文(中文占两位)"
										value="${anchor.nickname}">
								</div>
							</div>
							
							<%-- <div class="form-group">
								<label class="col-md-3 control-label">选择上级：</label>
								<div class="col-md-7">
									<select class="form-control" id="superiorIdChosen" name="superiorIdChosen" style="width:100%;">
									</select> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">会员红包奖励权限：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="redPacketAuthority" value="0" type="radio" ${anchor.redPacketAuthority==0?"checked":""} ><span style="font-size: 12px;">正常可获取</span>
									<input name="redPacketAuthority" value="1" type="radio" ${anchor.redPacketAuthority==1?"checked":""} ><span style="font-size: 12px;">不可获取</span>
									<input name="redPacketAuthority" value="2" type="radio" ${anchor.redPacketAuthority==2?"checked":""} ><span style="font-size: 12px;">可获取但不可入账</span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">会员平台：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="userPlatform" value="1" type="radio" ${anchor.userPlatform==1?"checked":""} ><span style="font-size: 12px;">寻蜜鸟</span>
									<input name="userPlatform" value="2" type="radio" ${anchor.userPlatform==2?"checked":""} ><span style="font-size: 12px;">中脉</span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">会员类型：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="objectOriented" value="1" type="radio" ${anchor.objectOriented==1?"checked":""} ><span style="font-size: 12px;">VIP</span>
									<input name="objectOriented" value="3" type="radio" ${anchor.objectOriented==3?"checked":""} ><span style="font-size: 12px;">直销</span>
								</div>
							</div> --%>
							
							<!-- 绑定提示信息 begin -->
							<div class="form-group conflictTip" style="display:none;">
								<label class="col-md-7 control-label" id="conflictMsg"  style="color:#F00; font-size:12px;"></label>
								<div class="col-md-3">
									<a id="reloadSelect"  style="color:#00F; font-size:12px;" data-toggle="modal" href="#sm_confirm_window">点我重新绑定</a>
								</div>
							</div>
							<!-- 绑定提示信息 end -->
							
							<!-- 设为主播提示信息 begin -->
							<div class="form-group viewer_conflictTip" style="display:none;">
								<label class="col-md-7 control-label" id="viewer_conflictMsg"  style="color:#F00; font-size:12px;"></label>
								<div class="col-md-3">
									<a id="viewer_reloadSelect"  style="color:#00F; font-size:12px;" data-toggle="modal" href="#viewer_confirm_window">点我重新设置</a>
								</div>
							</div>
							<!-- 设为主播提示信息 end -->
							
							
							
							<div class="form-group">
								<div class="text-center" style="padding:10px 0 10px 0;">
									<button type="submit" class="btn btn-success" id="ensure">
										<span class="icon-ok" ></span> 保 存
									</button>
									&nbsp;&nbsp;
									<a class="btn btn-warning" href="liveMember/manage/init.jhtml?"><span class="icon-remove"></span>&nbsp;取消</a>
								</div>
							</div>
						</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 绑定寻蜜鸟会员账号操作提示 begin -->
	<div class="modal fade" id="sm_confirm_window" style="z-index: 9999;" data-position="100px">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
		        <h4 class="modal-title">操作提示</h4>
		      </div>
		      <div class="modal-body">
		     	 <div class="alert with-icon  alert-warning">
					<i class="icon-warning-sign"></i> 
					<div class="content">
						<p>该手机号已有对应的寻蜜鸟会员账号，操作人员请联系直播会员，确认该寻蜜鸟会员账号是否归该直播会员所有，并进行绑定！</p>
					</div>
				</div>
				<div>
					<h4>对应会员账号信息</h4>
					<div class="content">
						<table class="table">
							<tr>
								<td width="12%"><h5>会员昵称:</h5></h5></td>
								<td width="24%"><h5 id="usr_nname"></h5></td>
							</tr>
							<tr>
								<td width="12%"><h5>手机号码:</h5></h5></td>
								<td width="24%"><h5 id="phoneNum"></h5></td>
							</tr>
						</table>
					</div>
				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default unbinding" >不 绑 定</button>&nbsp;&nbsp;
		        <button type="button" class="btn btn-primary binding" >绑    定</button>
		      </div>
			</div>
		</div>
	</div>
	<!-- 绑定寻蜜鸟会员账号操作提示 end -->
	
	<!-- 将观众设为主播操作提示 begin -->
	<div class="modal fade" id="viewer_confirm_window" style="z-index: 9999;" data-position="100px">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
		        <h4 class="modal-title">操作提示</h4>
		      </div>
		      <div class="modal-body">
		     	 <div class="alert with-icon  alert-warning">
					<i class="icon-warning-sign"></i> 
					<div class="content">
						<p>该手机号已有对应的寻蜜鸟直播观众账号，操作人员请联系主播，确认该寻蜜鸟直播观众账号是否归该主播所有！</p>
					</div>
				</div>
				<div>
					<h4>直播观众账号信息</h4>
					<div class="content">
						<table class="table">
							<tr>
								<td width="12%"><h5>观众昵称:</h5></h5></td>
								<td width="24%"><h5 id="viewer_usr_nname"></h5></td>
							</tr>
							<tr>
								<td width="12%"><h5>手机号码:</h5></h5></td>
								<td width="24%"><h5 id="viewer_phoneNum"></h5></td>
							</tr>
						</table>
					</div>
				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default viewer_cancel" >取 消</button>&nbsp;&nbsp;
		        <button type="button" class="btn btn-primary viewer_confirm" >设为主播</button>
		      </div>
			</div>
		</div>
	</div>
	<!-- 将观众设为主播操作提示 begin end -->
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>					
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/liverEdit.js"></script>
</body>
</html>
