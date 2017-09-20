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

<title>会员收益关系链 绑定上级信息 </title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty bursRelationInfo}">
			<input type="hidden" name="id" id="id" value="${bursRelationInfo.id}">
		</c:if>
		<input type="hidden" id="bindSuperiorInfoToken" name="bindSuperiorInfoToken" value="${bindSuperiorInfoToken }">
		<input type="hidden" name="uid" id="uid" value="${bursRelationInfo.uid}">
		<!-- 会员收益关系链，渠道类型 1.VIP 2.商家 3.直销 4.v客 -->
		<input type="hidden" name="objectOriented" id="objectOriented" value="${bursRelationInfo.objectOriented}">
		<!-- 上级UID -->
		<input type="hidden" name="superiorUid" id="superiorUid" value="${bursRelationInfo.superiorUid}">
		<!-- 会员层级 -->
		<input type="hidden" name="uidRelationChainLevel" id="uidRelationChainLevel" value="${bursRelationInfo.uidRelationChainLevel}">
		
		<div class="form-group">
			<label class="col-md-4 control-label">会员昵称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="nname" id="nname" readOnly="readonly"
					value="${bursRelationInfo.nname}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">会员手机号码：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="phone" id="phone" readOnly="readonly"
					value="${bursRelationInfo.phone}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">下线人数：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="juniors" id="juniors" readOnly="readonly"
					value="${bursRelationInfo.juniors }">
			</div>
		</div>
		
		
		<div class="form-group" id="superiorDiv" style="display:none;">
			<label class="col-md-4 control-label">上级：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="superior" id="superior" readOnly="readonly" initValue="${superiorInfo.id}"
					value="${superior}">
			</div>
		</div>
		
		<div class="form-group" id="superiorIdChosenDiv" style="display:none;">
			<label class="col-md-4 control-label">选择上级：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="superiorIdChosen" name="superiorIdChosen" initValue="${superiorInfo.id}" style="width:100%;">
				</select> 
			</div>
		</div>
		
		<div class="form-group" id="indirectUidDiv" style="display:none;">
			<label class="col-md-4 control-label">间接上级：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="indirectId" name="indirectId" initValue="${indirectInfo.id}" style="width:100%;">
				</select> 
			</div>
		</div>
		
		<div class="form-group" id="indirectTips" style="display:none;">
			<label class="col-md-1 control-label"></label>
			<div class="col-md-10">
				<div class="alert alert-primary">
					<h5>1、已存在上级，不允许重新绑定上级</h5>
					<h5>2、选择上级后，必须选择间接上级</h5>
					<h5>3、间接上级与上级必须存在相同起始源头(选择间接上级前，先选择上级)</h5>
					<h5>4、同一关系链中会员的间接上级不能重复</h5>
					<h5>5、间接上级不能相互绑定</h5>
					<h5>6、间接上级层级不能小于直接上级</h5>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success" id="submitBtn">
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/reward_dividends/bindRelationSuperiorInfo.js?v=1.0.11"></script>
</body>
</html>
