<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>活动管理</title>
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
</head>

<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<ul id="myTab" class="nav nav-tabs">
		<c:if test="${ btnAu['coupon/couponissue/systemPush/init/list']}">
			<li
				<c:if test="${ btnAu['coupon/couponissue/systemPush/init/list']}">class="active"</c:if>>
				<a href="#systemPush" data-toggle="tab">系统推送</a>
			</li>
		</c:if>
	
		<c:if test="${ btnAu['coupon/couponissue/yaoyiyao/init/list']}">
			<li
				<c:if test="${ empty btnAu['coupon/couponissue/systemPush/init/list'] && btnAu['coupon/couponissue/yaoyiyao/init/list']}">class="active"</c:if>>
				<a href="#tab1" data-toggle="tab">摇一摇</a>
			</li>
		</c:if>

		<c:if test="${ btnAu['coupon/couponissue/mansong/init/list']}">
			<li
				<c:if test="${ empty btnAu['coupon/couponissue/yaoyiyao/init/list'] && btnAu['coupon/couponissue/mansong/init/list']}"> class="active" </c:if>>
				<a href="#tab2" data-toggle="tab">满就送</a>
			</li>
		</c:if>

		<c:if
			test="${ btnAu['coupon/couponissue/sendshortmessage/init/list']}">
			<li
				<c:if test="${ empty btnAu['coupon/couponissue/yaoyiyao/init/list'] &&  empty btnAu['coupon/couponissue/mansong/init/list'] && btnAu['coupon/couponissue/sendshortmessage/init/list']}"> class="active" </c:if>>
				<a href="#tab3" data-toggle="tab">短信发送</a>
			</li>
		</c:if>
		<c:if
			test="${ btnAu['coupon/couponissue/share/init/list']}">
			<li
				<c:if test="${ empty btnAu['coupon/couponissue/yaoyiyao/init/list'] &&  empty btnAu['coupon/couponissue/mansong/init/list'] && empty btnAu['coupon/couponissue/sendshortmessage/init/list'] && btnAu['coupon/couponissue/share/init/list'] }"> class="active" </c:if>>
				<a href="#share" data-toggle="tab">分享发放优惠</a>
			</li>
		</c:if>
		<c:if test="${ btnAu['coupon/couponissue/youhuima/init/list']}">
			<li
				<c:if test="${ empty btnAu['coupon/couponissue/yaoyiyao/init/list'] &&  empty btnAu['coupon/couponissue/mansong/init/list'] && empty btnAu['coupon/couponissue/sendshortmessage/init/list']&& empty btnAu['coupon/couponissue/share/init/list'] && btnAu['coupon/couponissue/youhuima/init/list']}"> class="active" </c:if>>
				<a href="#tab4" data-toggle="tab">优惠码查询</a>
			</li>
		</c:if>
	</ul>
	<div class="tab-content">
	
		<!-- 系统推送begin -->
		<c:if  test="${ btnAu['coupon/couponissue/systemPush/init/list']}">
				<div id="systemPush"
					class="tab-pane <c:if test="${ btnAu['coupon/couponissue/systemPush/init/list']}">in active</c:if>">
					<div class="panel">
						<div class="panel-body">
							<form class="form-horizontal" role="form" id="systemPushForm">
								<input type="hidden" name="getWay" value="7">
								<table style="width:100%;">
									<tbody>
										<tr>
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户编号:</h5></td>
											<td style="width:25%;">
												<input type="text" class="form-control" name="uid" style="width:90%;" />
											</td>
											
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号码:</h5></td>
											<td style="width:25%;">
												<input type="text" class="form-control" name="phone" style="width:90%;" /></td>
											</td>
											<td style="width:5%;"><h5>优惠券类型：</h5></td>  
										    <td style="width:22%;">
											  	<select name="ctype" class="form-control" style="width:80%;">
												<option value="">--请选择--</option>
												<option value="0">消费优惠劵</option>
												<option value="1">商城优惠劵</option>
														<option value="5">平台通用优惠劵</option>
											    </select>
										    </td>
										</tr>
										<!-- <tr>
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;使用状态:</h5></td>
											<td style="width:25%;"><select name="userStatus"
												class="form-control" style="width:90%;">
													<option value="">--请选择--</option>
													<option value="0">未使用</option>
													<option value="1">锁定</option>
													<option value="2">已使用</option>
													<option value="3">已过期并退款</option>
											</select></td>
											<td colspan="4"></td>
										</tr> -->
										<tr>
											<td colspan="4"></td>
											<td colspan="2">
											  <div class="submit">
													<input class="submit radius-3" type="button" value="查询全部"
														data-bus='query' /><input class="reset radius-3"
														type="reset" value="重置全部" data-bus='reset' />
												</div>
											</td>
										<tr>
										
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-body data">
							<div class="btn-group" style="margin-bottom: 5px;">
								<a type="button" class="btn btn-success" id="systemPush" href="coupon/couponissue/systemPush/add/init.jhtml">
									<span class="icon-plus"></span>&nbsp;添加
								</a>
								<c:if test="${!empty btnAu['coupon/couponissue/systemPush/update'] && btnAu['coupon/couponissue/systemPush/update']}">
									<!-- <button type="button" class="btn btn-info" id="removeOffshelf" >&nbsp;退回</button> -->
								</c:if>
							</div>
							<div id="systemPushList"></div>
						</div>
					</div>
				</div>
		</c:if>
		<!-- 系统推送end -->
		
		<c:if test="${ btnAu['coupon/couponissue/yaoyiyao/init/list']}">
				<div id="tab1"
					class="tab-pane <c:if test=" ${ empty btnAu['coupon/couponissue/systemPush/init/list'] && btnAu['coupon/couponissue/yaoyiyao/init/list']}">in active</c:if>">
					<div class="panel">
						<div class="panel-body">
							<form class="form-horizontal" role="form" id="yaoyiyaoForm">
								<table style="width:100%;">
									<tbody>
										<tr>
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;活动编号:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" name="IssueId" style="width:90%;" /></td>
											
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总发行量:</h5></td>
											<td style="width:25%;">
												<div class="input-group" style="width:90%;float:left;">
													<input type="text" class="form-control"
														name="startIssueVolume" value="${param.startIssueVolume}">
													<span class="input-group-addon fix-border">--</span> <input
														type="text" class="form-control" name="endIssueVolume"
														value="${param.endIssueVolume}">
												</div>
											</td>
											<td style="width:5%;"><h5>优惠券类型：</h5></td>  
										    <td style="width:25%;">
											  	<select name="ctype" class="form-control" style="width:80%;">
												<option value="">--请选择--</option>
												<option value="0">消费优惠劵</option>
												<option value="1">商城优惠劵</option>
														<option value="5">平台通用优惠劵</option>
											    </select>
										    </td>
										</tr>
										<tr>
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;活动名称:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" name="activityName" style="width:90%;" /></td>
											
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</h5></td>
											<td style="width:25%;"><select name="status"
												class="form-control" style="width:90%;">
													<option value="">---请选择---</option>
													<option value="2">---待启动---</option>
													<option value="1">---已启动---</option>
													<option value="0">---已停止---</option>
												</select>
											</td>
											<td colspan="2"></td>
										</tr>
										<tr>
											<td colspan="4"></td>
											<td colspan="2">
											    <div class="submit">
													<input class="submit radius-3" type="button" value="查询全部"
														data-bus='query' /><input class="reset radius-3"
														type="reset" value="重置全部" data-bus='reset' />
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-body data">
							<div class="btn-group" style="margin-bottom: 5px;">
								<c:if
									test="${!empty btnAu['coupon/couponissue/yaoyiyao/add/init']}">
									<a type="button" class="btn btn-success" id="addyaoyiyao"
										href="coupon/couponissue/yaoyiyao/add/init.jhtml"><span
										class="icon-plus"></span>&nbsp;添加</a>
								</c:if>
								<%-- <c:if test="${!empty btnAu['coupon/couponissue/updateyaoyiyao/init']}">
				<button type="button" class="btn btn-success" onclick="mzActivityManager();"  title="商家与活动关联"><span class="icon-plus"></span>&nbsp;添加商家</button>
				</c:if> --%>
							</div>
							<div id="yaoyiyao"></div>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${ btnAu['coupon/couponissue/mansong/init/list']}">
				<div id="tab2"
					class="tab-pane <c:if test="${ empty btnAu['coupon/couponissue/yaoyiyao/init/list'] && btnAu['coupon/couponissue/mansong/init/list']}">in active</c:if>">
					<div class="panel">
						<div class="panel-body">
							<form class="form-horizontal" role="form" id="mansongForm">
								<table style="width:100%;">
									<tbody>
										<tr>
											<td style="width:6%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;活动编号:</h5></td>
											<td style="width:28%;"><input type="text"
												class="form-control" name="IssueId" style="width:81%;" /></td>
											
											<td style="width:6%;"><h5>&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</h5></td>
											<td style="width:28%;"><select name="status"
												class="form-control" style="width:81%;">
													<option value="">--请选择--</option>
													<option value="2">--待启动--</option>
													<option value="1">--已启动--</option>
													<option value="0">--已停止--</option>
												</select>
											</td>

											<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间:</h5></td>
											<td style="width:28%;">
												<div class="input-group" style="width:81%;float:left;">
													<input type="text" class="form-control form-datetime"
														name="startDateStart" value="${param.startDateStart}">
													<span class="input-group-addon fix-border">--</span> <input
														type="text" class="form-control form-datetime"
														name="endDateStart" value="${param.endDateStart}">
												</div>
											</td>
										</tr>
										
										<tr>
											<td style="width:6%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;活动名称:</h5></td>
											<td style="width:28%;"><input type="text"
												class="form-control" name="activityName" style="width:81%;" /></td>
											
											<td style="width:6%;"><h5>&nbsp;&nbsp;满送频率:</h5></td>
											<td style="width:28%;"><select class="form-control"
												name="rate" style="width:81%;">
													<option value="">--请选择--</option>
													<option value="1">--首单--</option>
													<option value="2">--首满--</option>
													<option value="3">--每次--</option>
												</select>
											</td>
											
											<td style="width:6%;"><h5>&nbsp;&nbsp;总发行量:</h5></td>
											<td style="width:28%;">
												<div class="input-group" style="width:81%;float:left;">
													<input type="text" class="form-control"
														name="startIssueVolume" value="${param.startIssueVolume}">
													<span class="input-group-addon fix-border">--</span> <input
														type="text" class="form-control" name="endIssueVolume"
														value="${param.endIssueVolume}">
												</div>
											</td>
										</tr>
										<tr>
											<td style="width:5%;"><h5>优惠券类型：</h5></td>  
										    <td style="width:25%;">
											  	<select name="ctype" class="form-control" style="width:80%;">
												<option value="">--请选择--</option>
												<option value="0">消费优惠劵</option>
												<option value="1">商城优惠劵</option>
														<option value="5">平台通用优惠劵</option>
											    </select>
										    </td>
										    <td colspan="4"></td>
										</tr>
										<tr>
											<td colspan="6">
												<div class="submit submit-sp" style="margin-right:58px;">
													<input class="submit radius-3" type="button" value="查询全部"
														data-bus='query' /> <input class="reset radius-3"
														type="reset" value="重置全部" data-bus='reset' />
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-body data">
							<div class="btn-group" style="margin-bottom: 5px;">
								<c:if
									test="${!empty btnAu['coupon/couponissue/mansong/add/init']}">
									<a type="button" class="btn btn-success" id="addmansong"
										href="coupon/couponissue/mansong/add/init.jhtml"><span
										class="icon-plus"></span>&nbsp;添加</a>
								</c:if>
							</div>
							<div id="mansong"></div>
						</div>
					</div>
				</div>
			</c:if>
			
			<!-- 短信发送begin -->
			<c:if
				test="${ btnAu['coupon/couponissue/sendshortmessage/init/list']}">
				<div id="tab3"
					class="tab-pane <c:if test="${ empty btnAu['coupon/couponissue/yaoyiyao/init/list'] &&  empty btnAu['coupon/couponissue/mansong/init/list'] && btnAu['coupon/couponissue/sendshortmessage/init/list']}">in active</c:if>">
					<div class="panel">
						<div class="panel-body">
							<form class="form-horizontal" role="form"
								id="sendshortmessageForm">
								<table style="width:100%;">
									<tbody>
										<tr>
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;活动编号:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" name="IssueId" style="width:90%;" /></td>
											
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发送时间:</h5></td>
											<td style="width:25%;">
												<div class="input-group" style="width:90%;float:left;">
													<input type="text" class="form-control form-datetime"
														name="startDateSend" value="${param.startDateSend}">
													<span class="input-group-addon fix-border">--</span> <input
														type="text" class="form-control form-datetime"
														name="endDateSend" value="${param.endDateSend}">
												</div>
											</td>
											<td style="width:5%;"><h5>优惠券类型：</h5></td>  
										    <td style="width:22%;">
											  	<select name="ctype" class="form-control" style="width:80%;">
												<option value="">--请选择--</option>
												<option value="0">消费优惠劵</option>
												<option value="1">商城优惠劵</option>
														<option value="5">平台通用优惠劵</option>
											    </select>
										    </td>
										</tr>
										<tr>
											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;活动名称:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" name="activityName" style="width:90%;" /></td>

											<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发送状态:</h5></td>
											<td style="width:25%;"><select name="sendStatus"
												class="form-control" style="width:90%;">
													<option value="">--请选择--</option>
													<option value="0">--待发送--</option>
													<option value="1">--已发送--</option>
											</select></td>
											<td colspan="2"></td>
										</tr>
											<td colspan="4"></td>
											<td colspan="2">
											  <div class="submit">
													<input class="submit radius-3" type="button" value="查询全部"
														data-bus='query' /><input class="reset radius-3"
														type="reset" value="重置全部" data-bus='reset' />
												</div>
											</td>
										<tr>
										
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-body data">
							<div class="btn-group" style="margin-bottom: 5px;">
								<%-- <c:if test="${!empty btnAu['marketingManagement/activityManagement/scratchCard/add']}"> --%>
								<a type="button" class="btn btn-success"
									id="addsendshortmessage"
									href="coupon/couponissue/sendshortmessage/add/init.jhtml"><span
									class="icon-plus"></span>&nbsp;添加</a>
								<%-- </c:if> --%>
								
								<a class="btn btn-success" id="addUsers" title="商家与短信优惠券关联"><spanclass="icon-plus"></span>&nbsp;添加用户</a>
								<%-- <c:if test="${!empty btnAu['coupon/couponissue/sendshortmessage/importUsers']}">
									<a class="btn btn-primary" id="importUsers"  title="商家与短信优惠券关联"><i class="icon-download-alt"></i>导入用户数据</a>
								</c:if>  --%>
								
								
							</div>
							<div id="sendshortmessage"></div>
						</div>
					</div>
				</div>
		</c:if>
		<!-- 短信发送 end-->
		
		<c:if test="${ btnAu['coupon/couponissue/share/init/list']}">
			<div id="share"
				class="tab-pane <c:if test="${ empty btnAu['coupon/couponissue/yaoyiyao/init/list'] &&  empty btnAu['coupon/couponissue/mansong/init/list'] && empty btnAu['coupon/couponissue/sendshortmessage/init/list'] && btnAu['coupon/couponissue/share/init/list']}">in active</c:if>">
				<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="shareForm">
							<div class="form-group">
								<label class="col-md-1 control-label">消费金额：</label>
								<div class="col-md-3">
									<div class="input-group">
										<input type="text" value="" name="amountStart"
											class="form-control form-date"> <span
											class="input-group-addon fix-border">--</span> <input
											type="text" value="" name="amountEnd"
											class="form-control form-date">
									</div>
								</div>
								
								<label class="col-md-1 control-label">活动区域：</label>
								<div class="col-md-3">
									<div class="input-group" id="shareAreaInfo" style="width:100%;float:left;"></div>
								</div>
								
								<label class="col-md-1 control-label">针对用户：</label>
								<div class="col-md-3" style="width: 21.5%;margin-bottom: 7px;">
									<select class="form-control" name="rate">
										<option value="">请选择</option>
										<option value="3">全部用户</option>
										<option value="1">新用户</option>
									</select>
								</div>
								<label class="col-md-1 control-label">优惠券类型：</label>
								<div class="col-md-3" style="width: 21.5%;">
								<select name="ctype" class="form-control">
										<option value="">--请选择--</option>
										<option value="0">消费优惠劵</option>
										<option value="1">商城优惠劵</option>
									<option value="5">平台通用优惠劵</option>
								</select>
								</div>
							</div>
							<div class="form-group">
								<div class="submit submit-sp" style="margin-right: 43px;">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' /><input class="reset radius-3" type="reset"
										value="重置全部" data-bus='reset' />
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="panel">
					<div class="panel-body data">
						<div class="btn-group" style="margin-bottom: 5px;">
							<div class="btn-group" style="margin-bottom: 5px;">
								<c:if test="${!empty btnAu['coupon/couponissue/share/add/init']}">
								<a type="button" class="btn btn-success" id="addShare"
									href="coupon/couponissue/share/add/init.jhtml"><span
									class="icon-plus"></span>&nbsp;添加</a>
								</c:if> 
							</div>
						</div>
						<div id="shareList"></div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${ btnAu['coupon/couponissue/youhuima/init/list']}">
				<div id="tab4"
					class="tab-pane  <c:if test="${ empty btnAu['coupon/couponissue/yaoyiyao/init/list'] &&  empty btnAu['coupon/couponissue/mansong/init/list'] && empty btnAu['coupon/couponissue/sendshortmessage/init/list'] && btnAu['coupon/couponissue/youhuima/init/list']}">in active</c:if>">
					<div class="panel">
						<div class="panel-body">
							<form class="form-horizontal" role="form" id="youhuimaForm">
								<table style="width:100%;">
									<tbody>
										<tr>
											<td style="width:8%;"><h5>&nbsp;&nbsp;活动编号:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" name="IssueId" style="width:90%;" /></td>
											<td style="width:8%;"><h5>&nbsp;&nbsp;活动名称:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" name="activityName" style="width:90%;" /></td>
											<td style="width:8%;"><h5>&nbsp;&nbsp;领取时间:</h5></td>
											<td style="width:25%;">
												<div class="input-group" style="width:90%;float:left;">
													<input type="text" class="form-control form-datetime"
														name="getTimeStart" value="${param.getTimeStart}">
													<span class="input-group-addon fix-border">--</span> <input
														type="text" class="form-control form-datetime"
														name="getTimeEnd" value="${param.getTimeEnd}">
												</div>
											</td>
										</tr>
										<tr>
											<td style="width:8%;"><h5>&nbsp;&nbsp;优惠券编号:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" name="cid" style="width:90%;" /></td>

											<td style="width:8%;"><h5>&nbsp;&nbsp;优惠券名称:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" name="cname" style="width:90%;" /></td>

											<td style="width:8%;"><h5>&nbsp;&nbsp;发行时间:</h5></td>
											<td style="width:25%;">
												<div class="input-group" style="width:90%;float:left;">
													<input type="text" class="form-control form-datetime"
														name="dateIssueStart" value="${param.dateIssueStart}">
													<span class="input-group-addon fix-border">--</span> <input
														type="text" class="form-control form-datetime"
														name="dateIssueEnd" value="${param.dateIssueEnd}">
												</div>
											</td>
										</tr>
										<tr>
											<td style="width:8%;"><h5>&nbsp;&nbsp;优惠券SN码:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" name="serial" style="width:90%;" /></td>
											<td style="width:8%;"><h5>&nbsp;&nbsp;获取状态:</h5></td>
											<td style="width:25%;"><select class="form-control"
												name="getStatus" style="width:90%;float:left">
													<option value="" selected="selected">全部</option>
													<option value="0">未获取</option>
													<option value="1">已获取</option>
													<option value="2">已锁定</option>
											</select></td>

											<td style="width:8%;"><h5>&nbsp;&nbsp;使用状态:</h5></td>
											<td style="width:25%;"><select class="form-control"
												name="userStatus" style="width:90%;float:left">
													<option value="" selected="selected">全部</option>
													<option value="0">未使用</option>
													<option value="2">已使用</option>
													<option value="1">已锁定</option>
											</select></td>
										</tr>
										<tr>
										<td style="width:8%;"><h5>&nbsp;&nbsp;用户编号:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control" name="uid" style="width:90%;" /></td>
										<td style="width:8%;"><h5>&nbsp;&nbsp;领取手机号:</h5></td>
										<td style="width:25%;"><input type="text"
												class="form-control" name="phone" style="width:90%;" /></td>
										<td style="width:8%;"><h5>优惠券类型：</h5></td>  
										    <td style="width:25%;">
											  	<select name="ctype" class="form-control" style="width:90%;">
												<option value="">--请选择--</option>
												<option value="0">消费优惠劵</option>
												<option value="1">商城优惠劵</option>
														<option value="5">平台通用优惠劵</option>
											    </select>
										    </td>
										</tr>
										<tr>
										<td colspan="4"></td>
										<td colspan="2" style="width:33%;"><div class="submit" style="text-align: left">&nbsp;
										<input class="submit radius-3" type="button" value="查询全部"data-bus='query' style="width:48.5%;"/>
										<input class="reset radius-3"  type="reset" value="重置全部" data-bus='reset' />
												</div></td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-body data">
							<div class="btn-group" style="margin-bottom: 5px;">
								<c:if test="${!empty btnAu['coupon/couponissue/youhuima/add']}">
									<button class="btn btn-success" id="publish" type="button">
										<span class="icon-plus"></span>&nbsp;发行
									</button>
								</c:if>
								<c:if test="${!empty btnAu['coupon/couponissue/youhuimaexport'] }">
								<button type="button" class="btn btn-default"  data-type="ajax" data-url="coupon/couponissue/youhuimaexport/init.jhtml" data-pisition=''  data-title='优惠码导出'  data-width="1150px"   data-toggle="modal" ><span class="icon-download-alt"></span>&nbsp;导出</button>
								</c:if>
							</div>
							<div id="youhuima"></div>

						</div>
					</div>
				</div>
			</c:if>
	</div>
	<script type="text/json" id="permissions">
		{yaoyiyaoupdate:'${ btnAu['coupon/couponissue/yaoyiyao/update/init']}',mansongupdate:'${ btnAu['coupon/couponissue/mansong/update/init']}',
			sendshortmessageupdate:'${ btnAu['coupon/couponissue/sendshortmessage/update/init']}',
			importUsers:'${ btnAu['coupon/couponissue/sendshortmessage/importUsers']}',
			shareList:'${ btnAu['coupon/couponissue/share/init/list']}',
			shareUpdate:'${ btnAu['coupon/couponissue/share/update/init']}',
			shareView:'${ btnAu['coupon/couponissue/share/view']}'
		}
   </script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script type="text/javascript">
		inserTitle(
				' > 优惠券管理  > <a href="coupon/couponissue/init.jhtml" target="right">优惠券发放管理</a>',
				'activityManagement', true);
		$(".form-datetime").datetimepicker({
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1,
			minuteStep : 1,
			format : 'yyyy-mm-dd hh:ii:ss'
		});
	</script>
	<!-- 系统推送begin -->
	<c:if test="${ btnAu['coupon/couponissue/systemPush/init/list']}">
		<script src="<%=path%>/js/coupon/couponissue/systemPushManage.js"></script>
	</c:if>
	<!-- 系统推送end -->
	
	<c:if test="${ btnAu['coupon/couponissue/yaoyiyao/init/list']}">
	<script src="<%=path%>/js/coupon/couponissue/yaoyiyao.js"></script>
	</c:if>
	<c:if test="${ btnAu['coupon/couponissue/mansong/init/list']}">
	<script src="<%=path%>/js/coupon/couponissue/mansong.js"></script>
	</c:if>
	<c:if test="${ btnAu['coupon/couponissue/sendshortmessage/init/list']}">
	<script src="<%=path%>/js/coupon/couponissue/sendshortmessage.js"></script>
	</c:if>
	<c:if test="${ btnAu['coupon/couponissue/share/init/list']}">
	<script src="<%=path%>/js/coupon/couponissue/share/share.js"></script>
	</c:if>
	<c:if test="${ btnAu['coupon/couponissue/youhuima/init/list']}">
	<script src="<%=path%>/js/coupon/couponissue/youhuima.js"></script>
	</c:if>
</body>
</html>