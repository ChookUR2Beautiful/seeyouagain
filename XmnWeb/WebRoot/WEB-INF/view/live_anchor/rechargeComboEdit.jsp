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

<title>添加通告</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty rechargeCombo}">
			<input type="hidden" name="id" value="${rechargeCombo.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">充值类型：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="rechargeType" value="1" type="radio" ${rechargeCombo.rechargeType==1?"checked":""} ><span style="font-size: 12px;">苹果</span>
				<input name="rechargeType" value="2" type="radio" ${rechargeCombo.rechargeType==2?"checked":""} ><span style="font-size: 12px;">安卓/微信</span>
				<br>
				<input name="rechargeType" value="3" type="radio" ${rechargeCombo.rechargeType==3?"checked":""} ><span style="font-size: 12px;">PC充值（鸟人VIP）</span>
				<input name="rechargeType" value="4" type="radio" ${rechargeCombo.rechargeType==4?"checked":""} ><span style="font-size: 12px;">PC充值（鸟人GD）</span>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label">有效状态：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="status" value="1" type="radio" ${rechargeCombo.status==1?"checked":""} ><span style="font-size: 12px;">有效</span>
				<input name="status" value="2" type="radio" ${rechargeCombo.status==2?"checked":""} ><span style="font-size: 12px;">无效</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">充值金额：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="rechAmount" id="rechAmount"
					value="${rechargeCombo.rechAmount}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">兑换鸟豆：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="rechNormCoin" id="rechNormCoin"
					value="${rechargeCombo.rechNormCoin}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">赠送鸟豆：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="rechFreeCoin" id="rechFreeCoin"
					value="${rechargeCombo.rechFreeCoin}">
			</div>
		</div>
		
		<div class="form-group" id="comboSourceDiv" style="display:none;">
			<label class="col-md-4 control-label">App类型：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="comboSource" value="0" type="radio" ${rechargeCombo.comboSource==0?"checked":""} ><span style="font-size: 12px;">寻蜜鸟</span>
				<input name="comboSource" value="1" type="radio" ${rechargeCombo.comboSource==1?"checked":""} ><span style="font-size: 12px;">鸟人直播</span>
			</div>
		</div>
		
		<div class="form-group" id="productIdDiv" style="display:none;">
			<label class="col-md-4 control-label">苹果内购序列号：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="productId" id="productId"
					value="${rechargeCombo.productId}">
			</div>
		</div>
		<div class="form-group" style="display:none;">
			<label class="col-md-4 control-label">关联等级：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select  class="form-control" name="fansRankId" id="fansRankId" initValue="${rechargeCombo.fansRankId}">
				</select>
				<input type="hidden" class="form-control" name="fansRankName" id="fansRankName" value="${rechargeCombo.fansRankName }">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">绑定类型：<span style="color:red;">*</span></label>
			<div class="col-md-7" id="objectOrientedDiv">
				<input name="objectOrienteds[0]"  value="1" type="checkbox"  ><span style="font-size: 12px;">VIP客户</span>
				<input name="objectOrienteds[1]"  value="2" type="checkbox"  ><span style="font-size: 12px;">商户会员（鸟粉专享卡）</span>
				<input name="objectOrienteds[2]"  value="3" type="checkbox"  ><span style="font-size: 12px;">直销客户</span>
				<br>
				<input name="objectOrienteds[3]"  value="0" type="checkbox"  ><span style="font-size: 12px;">一般会员</span>
				<input name="objectOrienteds[4]"  value="4" type="checkbox"  ><span style="font-size: 12px;">营业厅会员</span>
				<input name="objectOrienteds[5]"  value="5" type="checkbox"  ><span style="font-size: 12px;">黄金庄园</span>
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
	<input name="objectOriented" id="objectOriented" type="hidden" value="${rechargeCombo.objectOriented }">
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/rechargeComboEdit.js?v=1.0.11"></script>
</body>
</html>
