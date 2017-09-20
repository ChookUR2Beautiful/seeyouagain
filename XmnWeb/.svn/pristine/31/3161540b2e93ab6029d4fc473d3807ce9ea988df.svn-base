<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
th {
	font-size: 13px;
	text-align: right;
	border-bottom: none !important;
}

td {
	border-bottom: none !important;
}
</style>
</head>

<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="panel panel-primary">
			<div class="panel-heading">添加品牌店</div>
			<div class="panel-body">
				<form id="brandSellerFrom" role="form">
					<c:if test="${brandSeller != null }">
						<input type="hidden" name="brandId" value="${brandSeller.brandId}">
					</c:if>
					<input type="hidden" name="type" value="${type}">
					<table class="table">
						<tr>
							<th>品牌店名称：</th>
							<td><input type="text" class="form-control" id="brandName"
								name="brandName"
								<c:if test="${brandSeller != null }">value="${brandSeller.brandName}"</c:if>
								placeholder="品牌商名称"></td>

							<th>开始时间：</th>
							<td><input type="text" class="form-control form-datetime"
								readOnly name="dateStart"
								<c:if test="${brandSeller != null }">value="<fmt:formatDate value="${brandSeller.dateStart}" pattern="yyyy-MM-dd HH:mm"/>"</c:if> /></td>
							<th>结束时间：</th>
							<td><input type="text" class="form-control form-datetime"
								readOnly name="dateEnd"
								<c:if test="${brandSeller != null }">value="<fmt:formatDate value="${brandSeller.dateEnd}" pattern="yyyy-MM-dd HH:mm"/>"</c:if> /></td>
						</tr>
						<tr>
							<th>返利（%）：</th>
							<td><input type="text" class="form-control" name="rebate"
								<c:if test="${brandSeller != null }">value="${brandSeller.rebatePercentage}"</c:if> /></td>
							<th>排序：</th>
							<td><input type="text" class="form-control" name="sort"
								<c:if test="${brandSeller != null }">value="${brandSeller.sort}"</c:if> /></td>
							<th>全单折扣：</th>
							<td><input type="text" class="form-control" name="agio"
								<c:if test="${brandSeller != null }">value="${brandSeller.agioPercent}"</c:if> /></td>
						</tr>
						<tr>
							<th>非新用户满返活动描述：</th>
							<td><input type="text" class="form-control" name="activCont"
								<c:if test="${brandSeller != null }">value="${brandSeller.activCont}"</c:if> /></td>
							<th>新用户满返活动描述：</th>
							<td><input type="text" class="form-control"
								name="activNewUser"
								<c:if test="${brandSeller != null }">value="${brandSeller.activNewUser}"</c:if> /></td>

							<th>品牌店图片描述：</th>
							<td><input type="text" class="form-control" name="bewrite"
								<c:if test="${brandSeller != null }">value="${brandSeller.bewrite}"</c:if>></td>
						</tr>
						<tr>
							<th>品牌店图片：</th>
							<td><input type="hidden" class="form-control" id="picUrl"
								name="picUrl"
								<c:if test="${brandSeller != null }">value="${brandSeller.picUrl}"</c:if> />
								<div id="brandSellerLogoId" style="width:100%;"></div>
								<div style="width:100%;float:left;">
									<small style="color:red;">高分辨率图片(640*160)</small>
								</div></td>
							<td><input type="hidden" class="form-control" id="picMiddle"
								name="picMiddle"
								<c:if test="${brandSeller != null }">value="${brandSeller.picMiddle}"</c:if> />
								<div id="picMiddleDiv" style="width:100%;"></div>
								<div style="width:100%;float:left;">
									<small style="color:red;">中分辨率图片(640*160)</small>
								</div></td>
							<td><input type="hidden" class="form-control" id="picLow"
								name="picLow"
								<c:if test="${brandSeller != null }">value="${brandSeller.picLow}"</c:if> />
								<div id="picLowDiv" style="width:100%;"></div>
								<div style="width:100%;float:left;">
									<small style="color:red;">低分辨率图片(640*160)</small>
								</div></td>

						</tr>
						<tr>
							<th>品牌店形象图：</th>
							<td><input type="hidden" class="form-control"
								id="brandImageHigh" name="brandImageHigh"
								<c:if test="${brandSeller != null }">value="${brandSeller.brandImageHigh}"</c:if> />
								<div id="brandImageHighDiv" style="width:100%;"></div>
								<div style="width:100%;float:left;">
									<small style="color:red;">高分辨率图片(640*160)</small>
								</div></td>
							<td><input type="hidden" class="form-control"
								id="brandImageMiddle" name="brandImageMiddle"
								<c:if test="${brandSeller != null }">value="${brandSeller.brandImageMiddle}"</c:if> />
								<div id="brandImageMiddleDiv" style="width:100%;"></div>
								<div style="width:100%;float:left;">
									<small style="color:red;">中分辨率图片(640*160)</small>
								</div></td>
							<td><input type="hidden" class="form-control"
								id="brandImageLow" name="brandImageLow"
								<c:if test="${brandSeller != null }">value="${brandSeller.brandImageLow}"</c:if> />
								<div id="brandImageLowDiv" style="width:100%;"></div>
								<div style="width:100%;float:left;">
									<small style="color:red;">低分辨率图片(640*160)</small>
								</div></td>
						</tr>
						<tr>
							<th>区域：</th>
							<td><label class="radio-inline">全国<input
									type="radio" name="isAll" id="allArea" value="1"
									<c:if test="${empty brandSeller or brandSeller.isAll==1}">checked="checked"</c:if> /></label>
								<label class="radio-inline">特定区域<input type="radio"
									name="isAll" id="specifyArea" value="0"
									<c:if test="${brandSeller.isAll==0}">checked="checked"</c:if> /></label>
							</td>
							<th>是否折上折：</th>
							<td><label class="radio-inline">全单返<input
									type="radio" name="agioAgio" value="1"
									<c:if test="${brandSeller.agioAgio==1}">checked="checked"</c:if> /></label>
								<label class="radio-inline">折上返<input type="radio"
									name="agioAgio" value="0"
									<c:if test="${brandSeller.agioAgio==0||brandSeller.agioAgio!=1}">checked="checked"</c:if> /></label></td>
							<th>上线状态：</th>
							<td><label class="radio-inline">待上线<input
									type="radio" name="isBrand" value="0"
									<c:if test="${empty brandSeller or brandSeller.isBrand == 0}">checked</c:if> /></label>
								<label class="radio-inline">已上线<input type="radio"
									name="isBrand" value="1"
									<c:if test="${brandSeller.isBrand == 1}">checked</c:if> /></label> <label
								class="radio-inline">已下线<input type="radio"
									name="isBrand" value="2"
									<c:if test="${brandSeller.isBrand == 2}">checked</c:if> /></label></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="5">
								<div id="areaDivOp"></div>
								<div id="areaDivCon">
									<div class="input-group" id="areaInfo"
										style="width: 30%;float:left;"
										initValue="${brandSeller.areaInitValue}"></div>
									<h5>
										<font style="color: red;float:left;">(区域可多选)</font>
									</h5>
								</div>
							</td>
						</tr>
					</table>

					<%-- <div class="form-group">
						<label for="brandName" class="col-md-1 control-label">品牌店名称：</label>
						<div class="col-md-3">
							<input type="text" class="form-control" id="brandName"
								name="brandName"
								<c:if test="${brandSeller != null }">value="${brandSeller.brandName}"</c:if>
								placeholder="品牌商名称">
						</div>
						<label class="col-md-1 control-label">开始时间：</label>
						<div class="col-md-3">
							<input type="text" class="form-control form-datetime" readOnly
								name="dateStart"
								<c:if test="${brandSeller != null }">value="<fmt:formatDate value="${brandSeller.dateStart}" pattern="yyyy-MM-dd HH:mm"/>"</c:if> />
						</div>
						<label class="col-md-1 control-label">结束时间：</label>
						<div class="col-md-3">
							<input type="text" class="form-control form-datetime" readOnly
								name="dateEnd"
								<c:if test="${brandSeller != null }">value="<fmt:formatDate value="${brandSeller.dateEnd}" pattern="yyyy-MM-dd HH:mm"/>"</c:if> />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">品牌店图片：</label>
						<div class="col-md-2 col-mod-offset-3">
							<input type="hidden" class="form-control" id="picUrl"
								name="picUrl"
								<c:if test="${brandSeller != null }">value="${brandSeller.picUrl}"</c:if> />
							<div id="brandSellerLogoId"></div>
							<div style="width:180px;float:left;">
								<small style="color:red;">高分辨率图片(640*160)</small>
							</div>
						</div>
						<div class="col-md-2 col-mod-offset-6">
							<input type="hidden" class="form-control" id="picMiddle"
								name="picMiddle"
								<c:if test="${brandSeller != null }">value="${brandSeller.picMiddle}"</c:if> />
							<div id="picMiddleDiv"></div>
							<div style="width:180px;float:left;">
								<small style="color:red;">中分辨率图片(640*160)</small>
							</div>
						</div>
						<div class="col-md-2">
							<input type="hidden" class="form-control" id="picLow"
								name="picLow"
								<c:if test="${brandSeller != null }">value="${brandSeller.picLow}"</c:if> />
							<div id="picLowDiv"></div>
							<div style="width:180px;float:left;">
								<small style="color:red;">低分辨率图片(640*160)</small>
							</div>
						</div>
						<label class="col-md-1 control-label">品牌店图片描述：</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="bewrite"
								<c:if test="${brandSeller != null }">value="${brandSeller.bewrite}"</c:if>>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">品牌店形象图：</label>
						<div class="col-md-2">
							<input type="hidden" class="form-control" id="brandImageHigh"
								name="brandImageHigh"
								<c:if test="${brandSeller != null }">value="${brandSeller.brandImageHigh}"</c:if> />
							<div id="brandImageHighDiv"></div>
							<div style="width:180px;float:left;">
								<small style="color:red;">高分辨率图片(640*160)</small>
							</div>
						</div>
						<div class="col-md-2">
							<input type="hidden" class="form-control" id="brandImageMiddle"
								name="brandImageMiddle"
								<c:if test="${brandSeller != null }">value="${brandSeller.brandImageMiddle}"</c:if> />
							<div id="brandImageMiddleDiv"></div>
							<div style="width:180px;float:left;">
								<small style="color:red;">中分辨率图片(640*160)</small>
							</div>
						</div>
						<div class="col-md-2">
							<input type="hidden" class="form-control" id="brandImageLow"
								name="brandImageLow"
								<c:if test="${brandSeller != null }">value="${brandSeller.brandImageLow}"</c:if> />
							<div id="brandImageLowDiv"></div>
							<div style="width:180px;float:left;">
								<small style="color:red;">低分辨率图片(640*160)</small>
							</div>
						</div>
					</div>


					<div class="form-group"></div>
					<div class="form-group">
						<label class="col-md-1 control-label">返利（%）：</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="rebate"
								<c:if test="${brandSeller != null }">value="${brandSeller.rebatePercentage}"</c:if> />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">排序：</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="sort"
								<c:if test="${brandSeller != null }">value="${brandSeller.sort}"</c:if> />
						</div>
					</div>
					<div class="form-group"></div>
					<div class="form-group">
						<label class="col-md-1 control-label">是否折上折：</label>
						<div class="col-md-2">
							<label class="radio-inline">全单返<input type="radio"
								name="agioAgio" value="1"
								<c:if test="${brandSeller.agioAgio==1}">checked="checked"</c:if> /></label>
							<label class="radio-inline">折上返<input type="radio"
								name="agioAgio" value="0"
								<c:if test="${brandSeller.agioAgio==0||brandSeller.agioAgio!=1}">checked="checked"</c:if> /></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">非新用户满返活动描述：</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="activCont"
								<c:if test="${brandSeller != null }">value="${brandSeller.activCont}"</c:if> />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">新用户满返活动描述：</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="activNewUser"
								<c:if test="${brandSeller != null }">value="${brandSeller.activNewUser}"</c:if> />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">全单折扣：</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="agio"
								<c:if test="${brandSeller != null }">value="${brandSeller.agio}"</c:if> />
						</div>
					</div>
					<div class="form-group" id="areaDivOp">
						<label class="col-md-1 control-label">区域：</label>
						<div class="col-md-2">
							<label class="radio-inline">全国<input type="radio"
								name="isAll" id="allArea" value="1"
								<c:if test="${brandSeller.isAll==1}">checked="checked"</c:if> /></label>
							<label class="radio-inline">特定区域<input type="radio"
								name="isAll" id="specifyArea" value="0"
								<c:if test="${brandSeller.isAll==0}">checked="checked"</c:if> /></label>
						</div>
					</div>
					<div class="form-group" id="areaDivCon">
						<label class="col-md-1 control-label">&nbsp;</label>
						<div class="col-md-6">
							<div class="input-group" id="areaInfo"
								style="width: 80%;float:left;"
								initValue="${brandSeller.areaInitValue}"></div>
							<h5>
								<font style="color: red;float:left;">(区域可多选)</font>
							</h5>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">上线状态：</label>
						<div class="col-md-2">
							<label class="radio-inline">待上线<input type="radio"
								name="isBrand" value="0"
								<c:if test="${empty brandSeller or brandSeller.isBrand == 0}">checked</c:if> /></label>
							<label class="radio-inline">已上线<input type="radio"
								name="isBrand" value="1"
								<c:if test="${brandSeller.isBrand == 1}">checked</c:if> /></label> <label
								class="radio-inline">已下线<input type="radio"
								name="isBrand" value="2"
								<c:if test="${brandSeller.isBrand == 2}">checked</c:if> /></label>
						</div>
					</div>
 --%>
					<div class="form-group">
						<div class="text-center" style="padding:10px 0 10px 0;">
							<button class="btn btn-danger" type="submit"
								id="updateSaveBrandSeller">
								<i class="icon-save"></i>&nbsp;保存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button"
								onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');">
								<i class="icon-reply"></i>&nbsp;取消
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/user_terminal/brandSeller/addBrandSeller.js"></script>
	<script type="text/javascript">
		$(function() {
			$("h5").addClass("text-right");
		});
	</script>
</body>

</html>
