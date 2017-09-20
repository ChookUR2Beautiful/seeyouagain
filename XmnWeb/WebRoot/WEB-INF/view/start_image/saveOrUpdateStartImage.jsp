<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>

<body style="overflow-x: auto; overflow-y: auto; min-width: 1024px; background: #EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="panel panel-primary">
			<div class="panel-heading">添加启动图</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form"
					id="saveOrUpdateStartImageForm">
					<input type="hidden" name="id" value="${startImage.id}"> <input
						type="hidden" id="isType" value="${isType}">
					<table width="40%" align="center" valign="middle">
						<tbody>
							<tr>
								<th style="width: 100px;"><h5>&nbsp;&nbsp;客户端类型：</h5></th>
								<th colspan="2"><select class="form-control" name="type">
										<option value="">请选择</option>
										<option value="1" ${startImage.type==1?"selected":""}>商户版</option>
										<option value="2" ${startImage.type==2?"selected":""}>寻蜜鸟用户版</option>
										<option value="3" ${startImage.type==3?"selected":""}>合作商版</option>
								</select></th>
							</tr>
						    <%-- <tr>
								<th style="width:70px;"><h5>&nbsp;&nbsp;设备类型：</h5></th>	
								<th colspan="2">
									<select class="form-control"  name="atype">
										<option value="">请选择</option>
										<option value="1" ${startImage.atype==1?"selected":""}>Android</option>
										<option value="2" ${startImage.atype==2?"selected":""}>IOS</option>
										<option value="3" ${startImage.atype==3?"selected":""}>WP</option>
									</select>
								</th>
							</tr> --%>
							<tr>
								<th style="width: 70px;"><h5>&nbsp;&nbsp;位置：</h5></th>
								<th colspan="2">
									<h5>
										<label class="radio-showLocal"><input type="radio"name="showLocal" value="1"
											<c:if test="${startImage.showLocal == 1}">checked="checked"</c:if> />启动页</label>
										<label class="radio-showLocal"><input type="radio" name="showLocal" value="2"
										    <c:if test="${startImage.showLocal == 2}">checked="checked"</c:if> />直播间</label>
									</h5>
								</th>
							</tr>	
							<tr>
								<th style="width: 70px;"><h5>&nbsp;&nbsp;类型：</h5></th>
								<th colspan="2">
									<h5>
										<label class="radio-showType"><input type="radio" name="showType" value="1"
											<c:if test="${startImage.showType == 1}">checked="checked"</c:if> />图片</label>
										<label class="radio-showType"><input type="radio" name="showType" value="2"
											<c:if test="${startImage.showType == 2}">checked="checked"</c:if> />视频</label>
									</h5>
								</th>
							</tr>	
							<tr>
								<th style="width: 70px;"><h5>&nbsp;&nbsp;状态：</h5></th>
								<th colspan="2">
									<h5>
										<label class="radio-inline"><input type="radio" name="status" value="0"
											<c:if test="${startImage.status == 0}">checked="checked"</c:if> />启用</label>
										<label class="radio-inline"><input type="radio" name="status" value="1"
											<c:if test="${startImage.status == 1}">checked="checked"</c:if> />停用</label>
									</h5>
								</th>
							</tr>																			
							<%-- <tr>
									<th style="width:70px;"><h5>&nbsp;&nbsp;排序：</h5></th>	
									<th colspan="2"><input type="text" class="form-control"  name="index"  value="${startImage.index}"></th>
								</tr> --%>
							<tr >
								<th style="width: 100px;"><h5>&nbsp;&nbsp;启动图链接：</h5></th>
								<th colspan="2"><input type="text" class="form-control" placeholder="启动图链接"
									name="startUrl" value="${startImage.startUrl}"></th>
							</tr>
							<tr id="videoUrlInfo">
								<th style="width: 100px;"><h5>&nbsp;&nbsp;视频链接：</h5></th>
								<th colspan="2"><input type="text" class="form-control" placeholder="视频链接"
									name="videoUrl" value="${startImage.videoUrl}"></th>
							</tr>							
							<tr id="picUrlInfo">
								<th style="width: 100px;"><h5>&nbsp;&nbsp;广告图片：</h5></th>
								<th colspan="2">
									<table>
										<tr>
											<td>
												<div id="adbpicUpload"></div> <!-- <small style="color:red;">最优图片分辨率(640*160)</small> -->
												<input type="hidden" class="form-control" id="pic"
												name="pic" value="${startImage.pic}">
											</td>
											<%-- <td>
										    <div id="adbpicLowUpload"></div><small style="color:red;">低分辨率启动图(640*160)</small>
											<input type="hidden" class="form-control" id="picLow" name="picLow"  value="${startImage.picLow}">
										    </td>
										    <td>
										    <div id="adbpicMiddleUpload"></div><small style="color:red;">中分辨率启动图片(640*160)</small>
											<input type="hidden" class="form-control" id="picMiddle" name="picMiddle"  value="${startImage.picMiddle}">
										    </td> --%>
										</tr>
									</table>
								</th>
							</tr>
							<tr>
								<th style="width: 100px;"><h5>&nbsp;&nbsp;所在区域：</h5></th>
								<c:if test="${empty startImage}">
									<td colspan="2">
										<div name="area" id="area" style="width: 50%; float: left;"></div>
									</td>
								</c:if>
								<c:if test="${!empty startImage }">
									<td colspan="2">
										<div name="area" id="updateArea"
											style="width: 50%; float: left;"
											initvalue="${startImage.cityId}"></div>
									</td>
								</c:if>
							</tr>
							<tr id="countDownInfo">
								<th style="width: 100px;"><h5>&nbsp;&nbsp;倒数秒数：</h5></th>
								<th colspan="2"><input type="text" class="form-control" placeholder="倒数秒数"
									name="countDown" value="${startImage.countDown}"></th>
							</tr>							
							<!--<tr >
								<th style="width: 70px; margin:10px;"><h5>&nbsp;&nbsp;备注：</h5></th>
								<th colspan="2"><textarea name="remarks" cols="20" rows="3"
										class="form-control">${startImage.remarks}</textarea></th>
							</tr>-->
							<tr id="liveShowInfo">
								<th style="width: 100px; margin: 10px;"><h5>&nbsp;&nbsp;显示时效：</h5></th>
								<th colspan="2">
									  <input name="liveBegin" value="1" type="checkbox" <c:if test="${startImage.liveBegin == 1}">checked="checked"</c:if>><span style="font-size: 12px;">&nbsp;进入直播间显示广告</span></br>
									  <input name="liveEnd" value="1" type="checkbox" <c:if test="${startImage.liveEnd == 1}">checked="checked"</c:if>><span style="font-size: 12px;">&nbsp;直播间结束显示广告</span></br>
									  <input name="liveIn" value="1" type="checkbox" <c:if test="${startImage.liveIn == 1}">checked="checked"</c:if>>
									         <span style="font-size: 12px;">直播过程中，间隔&nbsp;<input type="text" class="form-control" name="intervals" value="${startImage.intervals} " style="width: 40px;">&nbsp;分钟，显示广告</span>								 
									  
								</th>
							</tr>
							<!-- <hr/> -->
							<tr>
								<th colspan="3" style="text-align: center;">
									<div align="center"  style="margin: 10px">
										<button type="submit" class="btn btn-success" id="ensure">
											<span class="icon-ok"></span> 保 存
										</button>&nbsp;&nbsp;
										<button type="reset" class="btn btn-default"
											data-dismiss="modal">
											<span class="icon-remove"></span> 取 消
										</button>
									</div>
								</th>
							</tr>

						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../common.jsp"></jsp:include>
				<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
				<script src="<%=path%>/resources/upload/upload.js"></script>
				<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
				<script src="<%=path%>/ux/js/ld2.js"></script>
				<script src="<%=path%>/ux/js/searchChosen.js"></script>
				<script src="<%=path%>/js/start_image/saveOrUpdateStartImage.js"></script>
</body>
</html>
