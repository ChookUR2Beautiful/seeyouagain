<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>用户</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet"> 
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
</head>
<body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
<form action="system_settings/roleAuthority/init/addBatch" method="post" >
<input type="hidden" name="roleId" value="${tr.roleId}">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<i class="icon-reorder"></i>&nbsp;&nbsp;资源列表
			
		</div>
		<div class="panel-body">
			<div class="text-center">
              <span class="text-muted"><h5>为角色名称: ${tr.roleName} 的角色分配权限</h5></span>
            </div>
				<ul class="nav nav-primary">
					<c:forEach var="parent" items="${map[key]}" varStatus="parentInfo">
						<li class="<c:if test="${parentInfo.first}">active</c:if>"><a data-toggle="tab" href="#${parent.id}">${parent.authorityName}</a></li>
					</c:forEach>
				</ul>
				<div class="tab-content" style="margin-top: 50px">
					<c:forEach var="parenta" items="${map[key]}" varStatus="info">
						<div id="${parenta.id}"
							class="tab-pane <c:if test="${info.first}">active</c:if>">
							<div class="list">
								<header> <span class="author">
									<h4>
										<i class="icon-reorder"></i>&nbsp;&nbsp; <span
											class="text-muted">${parenta.authorityName}列表</span>
									</h4> &nbsp;&nbsp; <span class="label label-badge">${fn:length(map[parenta.id])}&nbsp;个模块
								</span> &nbsp;&nbsp; <span class="text-muted"> 全选 &nbsp;&nbsp;<input
										value="${parenta.id}" id="${parenta.id}_checkbox"
										type="checkbox"></span>
								</span> </header>
								<div class="list">
									<section class="items items-hover"> <c:forEach
										var="parentb" items="${map[parenta.id]}">
										<div class="item" id="${parentb.id}" pid="${parenta.id }">
											<div class="item-heading">
												<span class="author">
													<h5>
														<i class="icon-reorder"></i>&nbsp;&nbsp; <span
															class="text-muted">${parentb.authorityName }</span>
													</h5> &nbsp;&nbsp; <span class="label label-badge label-info">${fn:length(map[parentb.id])}&nbsp;个按钮</span>
													&nbsp;&nbsp; <span class="text-muted"> 全选
														&nbsp;&nbsp;<input value="${parentb.id}"
														id="${parentb.id}_checkbox" type="checkbox">
												</span>
												</span>
											</div>
											<div class="item-content">
												<table class="table table-hover table-striped tablesorter table-data borderBottom" style="margin-top: 10px;">
													<c:if test="${fn:length(map[parentb.id])>8}">
														<tr>
															<c:forEach var="authority" items="${map[parentb.id]}" varStatus="info">
																<c:if test="${!empty authority.leaf&&authority.leaf==1}">
																	<c:set var="dependent" value="${ authority.id}"/>
																	<c:if test="${info.first}">
																		<c:set var="firstDependent" value="${ authority.id}"/>
																		<td >
																			<input value="${authority.id }" name="authorityId" dependent='' <c:if test="${authority.check}">checked="checked"</c:if> type="checkbox" id="${authority.id }" pid="${parentb.id }">&nbsp;&nbsp;${authority.authorityName}																						
																		</td>	
																	</c:if>	
																	<c:if test="${!info.first}">
																		<td >
																			<input value="${authority.id }" name="authorityId" dependent='${ firstDependent}' <c:if test="${authority.check}">checked="checked"</c:if> type="checkbox" id="${authority.id }" pid="${parentb.id }">&nbsp;&nbsp;${authority.authorityName}																						
																		</td>	
																	</c:if>	
																		
																	
																</c:if>		
																<c:if test="${empty authority.leaf||authority.leaf==0}">
																	<td >
																		<input value="${authority.id }" name="authorityId" dependent='${dependent}' <c:if test="${authority.check}">checked="checked"</c:if> type="checkbox" id="${authority.id }" pid="${parentb.id }">&nbsp;&nbsp;${authority.authorityName }							
																	</td>
																</c:if>			
																<c:if test="${info.count>=8&&info.count%8==0&&info.count<fn:length(map[parentb.id])}" >
																	</tr><tr>
																</c:if>
															</c:forEach>
														</tr>
													</c:if>
													<c:if test="${fn:length(map[parentb.id])<=8}">
														<tr>
															<c:forEach var="authority" items="${map[parentb.id]}" varStatus="info">
																<c:if test="${!empty authority.leaf&&authority.leaf==1}">	
																	<c:set var="dependent" value="${ authority.id}"/>
																	<c:if test="${info.first}">
																		<c:set var="firstDependent" value="${ authority.id}"/>
																		<td >
																			<input value="${authority.id }" name="authorityId" dependent='' <c:if test="${authority.check}">checked="checked"</c:if> type="checkbox" id="${authority.id }" pid="${parentb.id }">&nbsp;&nbsp;${authority.authorityName}																						
																		</td>	
																	</c:if>	
																	<c:if test="${!info.first}">
																		<td >
																			<input value="${authority.id }" name="authorityId" dependent='${ firstDependent}' <c:if test="${authority.check}">checked="checked"</c:if> type="checkbox" id="${authority.id }" pid="${parentb.id }">&nbsp;&nbsp;${authority.authorityName}																						
																		</td>	
																	</c:if>	
																</c:if>		
																<c:if test="${empty authority.leaf||authority.leaf==0}">
																	<td >
																		<input value="${authority.id }" name="authorityId" dependent='${dependent}' <c:if test="${authority.check}">checked="checked"</c:if> type="checkbox" id="${authority.id }" pid="${parentb.id }">&nbsp;&nbsp;${authority.authorityName }							
																	</td>
																</c:if>	
															</c:forEach>
														</tr>
													</c:if>
												</table>
												
											</div>
										</div>

									</c:forEach> </section>
								</div>
							</div>
						</div>

					</c:forEach>
				</div>
			
		</div>
		
	</div>
	<div class="panel panel-primary">
		<div class="panel-footer text-center" >
					<button class="btn btn-danger" type="submit" ><i class="icon-ok"></i>&nbsp;保存</button>
					<a href="system_settings/role/init.jhtml" class="btn btn-warning"  ><i class="icon-ok"></i>&nbsp;取消</a>
			</div>
		</div>
	</form>
	<jsp:include page="../common.jsp"></jsp:include>
	<script type="text/javascript" src="<%=path%>/js/system_settings/authority.js"></script>
</body>
</html>