<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
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
<title>查看商家申请修改信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">

<!-- 图片弹出样式 -->
<link rel="stylesheet"
	href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/jquery.fancybox.css?v=2.1.5"
	media="screen" />
<link rel="stylesheet"
	href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-buttons.css?v=1.0.5" />
<link rel="stylesheet"
	href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7" />


<style type="text/css">
td {
	border-bottom: none !important;
}
</style>
</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">商家基本信息</div>
				<div class="panel-body">
					<form action="" role="form">
						<input type="hidden" id="selleridid" name="sellerid"
							value="${selleridList.sellerid}" />
						<table class="table" style="text-align: left;">
							<tr>
								<td style="width:6%;">
									<h5>商家名称:</h5>
								</td>
								<td style="width:24%;">
									<h5>
										<span>${selleridList.sellername}</span>
									</h5>
								</td>
								<c:if test="${sellerApply.source == 1}">
									<td style="width:6%;">
										<h5>地址:</h5>
									</td>
									<td colspan="1">
										<h5>
											<span>${selleridList.address}</span>
										</h5>
									</td>
									<td style="width:6%;">
										<h5>咨询电话:</h5>
									</td>
									<td>
										<h5>
											<span>${selleridList.tel}</span>
										</h5>
									</td>
								</c:if>
							</tr>

							<c:if test="${sellerApply.source == 1}">
								<tr>
									<td style="width:80px;">
										<h5>区域:</h5>
									</td>
									<td>
										<h5>
											<div class="input-group" id="ld" style="width:100%"
												initValue="${selleridList.area}"></div>
										</h5>
									</td>

									<td style="width:80px;">
										<h5>商圈:</h5>
									</td>
									<td><select class="form-control" id="zoneid" name="zoneid"
										style="width:100%;" initValue="${selleridList.zoneid}"
										disabled="disabled">
									</select></td>

									<td style="width:6%;">
										<h5>参考地标:</h5>
									</td>
									<td style="width:24%;">
										<h5>
											<span>${selleridList.landmark}</span>
										</h5>
									</td>
								</tr>
							</c:if>
							<c:if test="${sellerApply.source == 1}">
								<tr>
									<td style="width: 6%;">
										<h5>经度：</h5>
									</td>
									<td style="width:24%;">
										<h5>
											<span>${selleridList.longitude}</span>
										</h5>
									</td>
									<td style="width: 6%;">
										<h5>纬度：</h5>
									</td>
									<td style="width:24%;">
										<h5>
											<span>${selleridList.latitude}</span>
										</h5>
									</td>
								</tr>
								<tr>
									<td>
										<h5>使用须知:</h5>
									</td>
									<td><textarea rows="3.5" cols="30" readonly="readonly">${selleridList.sexplain}</textarea>
									</td>
									<td style="width:6%;"></td>
									<td style="width:24%;"></td>
								</tr>
							</c:if>

							<c:if test="${sellerApply.source == 2}">
								<c:if test="${type0 == 0 }">
									<tr>
										<td>
											<h5>logo图:</h5>
										</td>
										<td colspan="5"><c:if
												test="${!empty selleridList.purlList}">
												<%-- <c:forEach var="setting" items="${selleridList.purlList}">
												<c:if test="${setting.islogo == 0 }"> --%>
														<a
															href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.url}"
															rel="slide" rel="slide" title="商家logo图" class="fancybox">
															<img alt="商家环logo图" tabIndex="-3"
															src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.url}"
															style="width: 100px;height: 100px;">
														</a>
										&nbsp;&nbsp;&nbsp;&nbsp;
								<%-- 					</c:if>
									</c:forEach> --%>
											</c:if></td>
									</tr>
								</c:if>
								<c:if test="${type1 == 0 }">
									<tr>
										<td>
											<h5>环境图片:</h5>
										</td>
										<td colspan="5"><c:if
												test="${!empty selleridList.purlList}">
												<c:forEach var="setting" items="${selleridList.purlList}">
													<c:if test="${setting.islogo == 0 }">
														<a
															href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${setting.picurl}"
															rel="slide" rel="slide" title="商家环境图片" class="fancybox">
															<img alt="商家环境图片" tabIndex="-3"
															src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${setting.picurl}"
															style="width: 100px;height: 100px;">
														</a>
										&nbsp;&nbsp;&nbsp;&nbsp;
													</c:if>
												</c:forEach>
											</c:if></td>
									</tr>
								</c:if>

								<c:if test="${type2 == 0 }">
									<tr>
										<td>
											<h5>封面图:</h5>
										</td>
										<td colspan="5"><c:if
												test="${!empty selleridList.purlList}">
												<c:forEach var="setting" items="${selleridList.purlList}">
													<c:if test="${setting.islogo == 2 }">
														<a
															href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${setting.picurl}"
															rel="slide" rel="slide" title="商家封面图" class="fancybox">
															<img alt="商家封面图" tabIndex="-3"
															src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${setting.picurl}"
															style="width: 100px;height: 100px;">
														</a>
										&nbsp;&nbsp;&nbsp;&nbsp;
													</c:if>
									</c:forEach>
											</c:if></td>
									</tr>
								</c:if>
							</c:if>
						</table>
					</form>
				</div>
			</div>
		</div>

		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">商家申请修改信息</div>
				<div class="panel-body">
					<form action="">
						<input type="hidden" id="id" name="id" value="${sellerApply.id}" />
						<input type="hidden" id="status" name="status"
							value="${sellerApply.status}" />
						<table class="table" style="text-align: left;">
							<tr>
								<td style="width:6%;">
									<h5>商家名称:</h5>
								</td>
								<td style="width:24%;"><c:if
										test="${sellerApply.source == 1}">
										<c:if
											test="${sellerApply.sellername != selleridList.sellername}">
											<h5>
												<span style="color: red">${sellerApply.sellername}</span>
											</h5>
										</c:if>
										<c:if
											test="${sellerApply.sellername == selleridList.sellername}">
											<h5>
												<span>${selleridList.sellername}</span>
											</h5>
										</c:if>
									</c:if> <c:if test="${sellerApply.source == 2}">
										<h5>
											<span>${selleridList.sellername}</span>
										</h5>
									</c:if></td>

								<c:if test="${sellerApply.source == 1}">
									<td style="width:6%;">
										<h5>地址:</h5>
									</td>
									<td colspan="1"><c:if
											test="${sellerApply.address != selleridList.address}">
											<h5>
												<span style="color: red">${sellerApply.address}</span>
											</h5>
										</c:if> <c:if test="${sellerApply.address == selleridList.address}">
											<h5>
												<span>${selleridList.address}</span>
											</h5>
										</c:if></td>
									<td style="width:6%;">
										<h5>咨询电话:</h5>
									</td>
									<td><c:if test="${sellerApply.phone != selleridList.tel}">
											<h5>
												<span style="color: red">${sellerApply.phone}</span>
											</h5>
										</c:if> <c:if test="${sellerApply.phone == selleridList.tel}">
											<h5>
												<span>${sellerApply.phone}</span>
											</h5>
										</c:if></td>
								</c:if>
							</tr>
							<c:if test="${sellerApply.source == 1}">
								<tr>
									<td>
										<h5>区域:</h5>
									</td>
									<td><c:if test="${sellerApply.area != selleridList.area}">
											<div class="input-group" id="areaSelectApply"
												style="width:100%;border:solid 3px red;"
												initValue="${sellerApply.area}"></div>
										</c:if> <c:if test="${sellerApply.area == selleridList.area}">
											<div class="input-group" id="areaSelectApply"
												style="width:100%" initValue="${selleridList.area}"></div>
										</c:if></td>

									<td style="width:80px;">
										<h5>商圈:</h5>
									</td>
									<td><c:if test="${sellerApply.source == 1}">
											<c:if test="${sellerApply.zoneid != selleridList.zoneid}">
												<div style="border:solid 3px red;">
													<select class="form-control" id="zoneApplyid" name="zoneid"
														style="width:100%;" initValue="${sellerApply.zoneid}"
														disabled="disabled">
													</select>
												</div>
											</c:if>
											<c:if test="${sellerApply.zoneid == selleridList.zoneid}">
												<select class="form-control" id="zoneApplyid" name="zoneid"
													style="width:100%;" initValue="${selleridList.zoneid}"
													disabled="disabled">
												</select>
											</c:if>
										</c:if></td>

									<td style="width:6%;">
										<h5>参考地标:</h5>
									</td>
									<td style="width:24%;">
										<h5>
											<c:if
												test="${sellerApply.landmark != selleridList.landmark }">
												<span style="color: red">${sellerApply.landmark}</span>
											</c:if>
											<c:if
												test="${sellerApply.landmark == selleridList.landmark }">
												<span>${sellerApply.landmark}</span>
											</c:if>
										</h5>
									</td>
								</tr>
							</c:if>
							<c:if test="${sellerApply.source == 1}">
								<tr>
									<td style="width: 6%;">
										<h5>经度：</h5>
									</td>
									<td style="width:24%;">
										<h5>
											<c:if
												test="${sellerApply.longitude != selleridList.longitude }">
												<span style="color: red">${sellerApply.longitude}</span>
											</c:if>
											<c:if
												test="${sellerApply.longitude == selleridList.longitude }">
												<span>${sellerApply.longitude}</span>
											</c:if>
										</h5>
									</td>
									<td style="width: 6%;">
										<h5>纬度：</h5>
									</td>
									<td style="width:24%;">
										<h5>
											<h5>
												<c:if
													test="${sellerApply.latitude != selleridList.latitude }">
													<span style="color: red">${sellerApply.latitude}</span>
												</c:if>
												<c:if
													test="${sellerApply.latitude == selleridList.latitude }">
													<span>${sellerApply.latitude}</span>
												</c:if>
											</h5>
										</h5>
									</td>
								</tr>
								<td>
									<h5>使用须知:</h5>
								</td>
								<td><c:if test="${sellerApply.source == 1}">
										<c:if test="${sellerApply.sexplain != selleridList.sexplain}">
											<textarea rows="3.5" cols="30" readonly="readonly"
												style="border:solid 3px red;">${sellerApply.sexplain}</textarea>
										</c:if>
										<c:if test="${sellerApply.sexplain == selleridList.sexplain}">
											<textarea rows="3.5" cols="30" readonly="readonly">${sellerApply.sexplain}</textarea>
										</c:if>
									</c:if></td>
							</c:if>
							<tr>

							</tr>
							<c:if test="${sellerApply.source == 2}">

								<c:if test="${type0 ==0 }">
									<tr>
									<td>
										<h5>logo图:</h5>
									</td>
									<td colspan="5"><c:if
											test="${!empty sellerApply.sellerPicApply}">
											<c:forEach var="piclst" items="${sellerApply.sellerPicApply}">
												<c:if test="${piclst.type == 0 && sellerApply.source == 2}">
													<c:choose>
														<c:when
															test="${fn:contains(selleridList.purlList, piclst.picurl)}">
															<a
																href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																rel="slide" rel="slide" title="商家logo图" class="fancybox">
																<img alt="商家logo图" tabIndex="-3"
																src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																style="width: 100px;height: 100px;">
															</a>
											    &nbsp;&nbsp;&nbsp;&nbsp;
										    </c:when>
														<c:otherwise>
															<a
																href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																rel="slide" rel="slide" title="商家logo图" class="fancybox">
																<img alt="商家logo图" tabIndex="-3"
																src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																style="width: 100px;height: 100px; border:solid 3px red;">
															</a>
											    &nbsp;&nbsp;&nbsp;&nbsp;
										    </c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</c:if></td>
								</tr>
								</c:if>
								<c:if test="${type1 ==0 }">
									<tr>
									<td>
										<h5>环境图片:</h5>
									</td>
									<td colspan="5"><c:if
											test="${!empty sellerApply.sellerPicApply}">
											<c:forEach var="piclst" items="${sellerApply.sellerPicApply}">
												<c:if test="${piclst.type == 1 && sellerApply.source == 2}">
													<c:choose>
														<c:when
															test="${fn:contains(selleridList.purlList, piclst.picurl)}">
															<a
																href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																rel="slide" rel="slide" title="商家环境图片" class="fancybox">
																<img alt="商家环境图片" tabIndex="-3"
																src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																style="width: 100px;height: 100px;">
															</a>
											    &nbsp;&nbsp;&nbsp;&nbsp;
										    </c:when>
														<c:otherwise>
															<a
																href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																rel="slide" rel="slide" title="商家环境图片" class="fancybox">
																<img alt="商家环境图片" tabIndex="-3"
																src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																style="width: 100px;height: 100px; border:solid 3px red;">
															</a>
											    &nbsp;&nbsp;&nbsp;&nbsp;
										    </c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</c:if></td>
								</tr>
								</c:if>
								<c:if test="${type2 ==0 }">
									<tr>
									<td>
										<h5>封面图:</h5>
									</td>
									<td colspan="5"><c:if
											test="${!empty sellerApply.sellerPicApply}">
											<c:forEach var="piclst" items="${sellerApply.sellerPicApply}">
												<c:if test="${piclst.type == 2 && sellerApply.source == 2}">
													<c:choose>
														<c:when
															test="${fn:contains(selleridList.purlList, piclst.picurl)}">
															<a
																href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																rel="slide" rel="slide" title="商家封面图" class="fancybox">
																<img alt="商家封面图" tabIndex="-3"
																src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																style="width: 100px;height: 100px;">
															</a>
											    &nbsp;&nbsp;&nbsp;&nbsp;
										    </c:when>
														<c:otherwise>
															<a
																href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																rel="slide" rel="slide" title="商家封面图" class="fancybox">
																<img alt="商家封面图" tabIndex="-3"
																src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclst.picurl}"
																style="width: 100px;height: 100px; border:solid 3px red;">
															</a>
											    &nbsp;&nbsp;&nbsp;&nbsp;
										    </c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</c:if></td>
								</tr>
								</c:if>
							</c:if>
						</table>
					</form>
				</div>
			</div>


		</div>
	</div>

	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<!--图片弹出  -->
	<script type="text/javascript"
		src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/jquery.fancybox.js?v=2.1.5"></script>
	<script type="text/javascript"
		src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
	<script type="text/javascript"
		src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>
	<script type="text/javascript"
		src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712//source/helpers/jquery.fancybox-media.js?v=1.0.6"></script>
	<script type="text/javascript"
		src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/lib/jquery.mousewheel-3.0.6.pack.js"></script>

	<!-- 图片缩放 -->
	<script type="text/javascript"
		src="<%=path%>/ux/shiftzoom/shiftzoom.js"></script>
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/geodata.js"></script>
	<script type="text/javascript"
		src="<%=path%>/ux/shiftzoom/cvi_tip_lib.js"></script>
	<script src="<%=path%>/js/businessman/viewSellerApply.js"></script>
	<script type="text/javascript">
		var logo = '${sellerApply.logo}';//商家申请修改图标
		if (logo == "") {
			$("#logo").hide();
		} else {
			$("#logo").show();
		}

		var logoSeller = '${selleridList.url}';//商家原始信息图标
		if (logoSeller == "") {
			$("#logoSeller").hide();
		} else {
			$("#logoSeller").show();
		}
	</script>
</body>
</html>
