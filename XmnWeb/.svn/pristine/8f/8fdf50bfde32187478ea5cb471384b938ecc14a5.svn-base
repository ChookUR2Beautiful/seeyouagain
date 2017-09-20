<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<form id="editFrom" role="form" class="form-horizontal">
	<c:if test="${!empty division}">
		<input type="hidden" name="id" id="id" value="${division.id}">
	</c:if>
	<div class="form-group">
		<label class="col-md-4 control-label">用户：<span
			style="color:red;">*</span></label>
		<div class="col-md-7">
			<select class="form-control" id="userId" name="userId"
				style="width:41%;float:left" initValue="${activity.codeId}"></select>
		</div>
	</div>

	<div class="form-group">
		<label class="col-md-4 control-label">定义门票：<span
			style="color:red;">*</span></label>
		<div class="col-md-7">
			<input type="button" class="btn btn-success" id="addInput" value="添加"></input>
			<div id="orderInputs"></div>
		</div>
	</div>
	<div class="form-group">
		<div class="text-center" style="padding:10px 0 10px 0;">
			<button type="button" class="btn btn-success" id="submit">
				<span class="icon-ok"></span> 保 存
			</button>
			&nbsp;&nbsp;
			<button type="reset" class="btn btn-default" data-dismiss="modal">
				<span class="icon-remove"></span> 取 消
			</button>
		</div>
	</div>
</form>
<div style="display:none;" id="chooseInput">
	<div style="width: 50%;float: left;">
		<select class="form-control" name="seatSelect"
			style="width:41%;float:left"></select>
	</div>
	<select class="form-control" name="seatSelect_2" style="width:50%;">
	</select>
	<a href="javascript:;" name="deleteBut">删除</a>
</div>
<script src="<%=path%>/js/vstar/ticketsOrder/orderGive.js" />

