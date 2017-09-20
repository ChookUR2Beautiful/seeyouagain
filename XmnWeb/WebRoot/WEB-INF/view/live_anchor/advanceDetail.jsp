<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>预告详情</title>
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
<link href="<%=path%>/resources/web/css/showtips.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none !important;
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
					<form id="editForm">
						<!-- 主播会员ID -->
						<input type="hidden" name="uid" id="uid" value="${uid}" >
						<!-- 通告ID -->
						<input type="hidden" name="recordId" id="recordId" value="${recordBean.id}" >
						<!-- 主播房间号 -->
						<input type="hidden" name="anchorRoomNo" id="anchorRoomNo" value="${recordBean.anchorRoomNo}" >
						<!-- 商户ID -->
						<input type="hidden" name="sellerid" id="sellerid" value="${recordBean.sellerid}" >
						<!-- 首次添加预告详情 -->
						<input type="hidden" name="isAdd" id="isAdd" value="${empty recordBean.haveCoupon}" >
						
						<c:if test="${!empty couponAnchor}">
							<!-- 主键ID -->
							<input type="hidden" name="anchorCid" id="anchorCid" value="${couponAnchor.anchorCid}">
						</c:if>
						<table class="table" style="text-align: center;width:50%">
							<tr>
								<td class="sellerTitleCss">
									<h5>提供粉丝券:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
								<!-- 预告详情页提供粉丝券设置,已保存数据不允许变更此项 -->
									<div class="col-md-3">
										<input name="haveCoupon" value="1" type="radio" ${recordBean.haveCoupon==1?"checked":""} ><span style="font-size: 12px;">是</span>
										<input name="haveCoupon" value="0" type="radio" ${recordBean.haveCoupon==0?"checked":""} ><span style="font-size: 12px;">否</span>
									</div>
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>券名:<span style="color:red;">*</span></h5></td>
								<td >
										<select class="form-control" id="cid" name="cid" ${!empty recordBean.haveCoupon ? "disabled":""}
											initValue="${couponAnchor.cid }" style="width:100%;">
										</select>
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>设置价格(元):<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text" readonly="readonly"
									class="form-control" name="denomination" id = "denomination" 
									value="">
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>原价(元):<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text" readonly="readonly"
									class="form-control" name="originalPrice" id = "originalPrice" 
									value="">
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>粉丝券数量:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="sendNum" id="sendNum"  onblur="sendNumValidate()"
									value="${couponAnchor.sendNum}">
									<input type="hidden" name="stock" id="stock" value="${couponAnchor.stock}">
									<input type="hidden" name="currentSendNum" id="currentSendNum" value="${couponAnchor.sendNum}">
									<input type="hidden" name="stockChange" id="stockChange" value="">
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>使用时间:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group">
									<input type="text" class="form-control form-datetime" id="startDate"
										name="startDate" value="${couponAnchor.startDateStr}">
									<span class="input-group-addon fix-border">--</span> <input
										type="text" class="form-control form-datetime" id="endDate"
										name="endDate" value="${couponAnchor.endDateStr}">
								</div>
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>下架时间:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group">
									<input type="text" class="form-control form-datetime" id="soldOutTime" style="width:162%"
										name="soldOutTime" value="${couponAnchor.soldOutTimeStr}">
								</div>
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>粉丝券描述:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"	readonly="readonly"
									class="form-control" name="validityDesc" id="validityDesc"
									value="">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>预告介绍:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="introduce" id = "introduce" 
									value="${recordBean.introduce}">
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>使用须知:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"	readonly="readonly"
									class="form-control" name="rule" id="rule" 
									value="">
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>赠送预售抵用券:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="col-md-3">
										<input name="haveFree" value="1" type="radio" disabled="true"><span style="font-size: 12px;">是</span>
										<input name="haveFree" value="2" type="radio" disabled="true"><span style="font-size: 12px;">否</span>
									</div>
								</td>
							</tr>
							
							<tr id="voucherTr" class="on-off">
								<td class="sellerTitleCss">
									<h5>赠送金额:<span style="color:red;">*</span></h5>
								</td>
								<td class="sellerContentCss" align="left">
									<div id="datas">
										<!-- 抵用券列表 -->
										<div class="col-sm-6 plandiv" id="plandiv">
										
										</div>	
									</div>
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss" >
									<h5>预售状态:<span style="color:red;">*</span></h5></td>
								<td >
										<select class="form-control" id="sendStatus" name="sendStatus" style="width:100%;">
											<option value="1" ${couponAnchor.sendStatus==1?"selected":""}>预售中</option>
											<option value="2" ${couponAnchor.sendStatus==2?"selected":""}>预售结束</option>
											<option value="3" ${couponAnchor.sendStatus==3?"selected":""}>已售罄</option>
										</select> 

								</td>
							</tr>

							
						</table>
						<div align="center">
							<button class="btn btn-danger" type="submit">
								<i class="icon-save"></i>&nbsp;保 存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button"
								onclick="window.history.back();">
								<i class="icon-reply"></i>&nbsp;取消
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="hidden dataTemp">
		<div class="input-group">
			<input  type="text"	name="voucherList[index].denomination" id="" class="form-control" style="width:100px;" />
			<span	class="input-group-addon">元 &nbsp;&nbsp;&nbsp;&nbsp;满</span>  
			<input  type="text"	name="voucherList[index].condition" id="" class="form-control" style="width:100px;" />
			<span	class="input-group-addon">元可用</span> 
			<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus"	style="cursor:pointer"></i></span>
			<span class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i>
			</span>
		</div>
	</div>

	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/js/live_anchor/advanceDetail.js"></script>
	
</body>
</html>
