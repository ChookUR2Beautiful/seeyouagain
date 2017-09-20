<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<form id="priceFrom" role="form" class="form-horizontal">
		<c:if test="${!empty ticketsPrice}">
			<input type="hidden" name="id" id="fashionTicketSeatId" value="${ticketsPrice.id}">
		</c:if>
			<div class="form-group" id="selectBody">
			<label class="col-md-4 control-label">选择座位：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="sid"  name="sid"
												initValue="${ticketsPrice.sid}" style="width:98%;"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">张数：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="number" class="form-control" name="buyNum" id="buyNum" min="0"
					value="${ticketsPrice.buyNum}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">售价：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="number" class="form-control" name="price" id="price" min="0"
					value="${ticketsPrice.price}">
			</div>
		</div>
		
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success">
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	
	<script type="text/javascript" src="<%=path%>/js/vstar/fashionTickets/priceEdit.js">
<!--

//-->
</script>
