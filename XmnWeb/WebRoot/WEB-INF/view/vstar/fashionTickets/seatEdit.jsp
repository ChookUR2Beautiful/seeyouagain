<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<form id="seatFrom" role="form" class="form-horizontal">
		<c:if test="${!empty fashionTicketSeat}">
			<input type="hidden" name="id" id="fashionTicketSeatId" value="${fashionTicketSeat.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">类型：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="seatName" id="seatName"
					value="${fashionTicketSeat.seatName}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">单个最大可容纳数量：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="seats" id="seats"
					value="${fashionTicketSeat.seats}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">开始编号：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="number" class="form-control" name="zoneRangeMin" id="zoneRangeMin" min="0"
					value="${fashionTicketSeat.zoneRangeMin}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">数量：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="num" id="num"
					value="${fashionTicketSeat.num}">
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
	
	<script type="text/javascript" src="<%=path%>/js/vstar/fashionTickets/seatEdit.js">
<!--

//-->
</script>
